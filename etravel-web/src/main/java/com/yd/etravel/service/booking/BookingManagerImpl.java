package com.yd.etravel.service.booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yd.etravel.domain.booking.Booking;
import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.booking.HotelBooking;
import com.yd.etravel.domain.booking.Payment;
import com.yd.etravel.domain.booking.RoomBooking;
import com.yd.etravel.domain.custom.booking.BookingDTO;
import com.yd.etravel.domain.custom.booking.BookingReportDTO;
import com.yd.etravel.domain.custom.booking.BookingReportSearchDTO;
import com.yd.etravel.domain.room.availability.RoomDailyAvailability;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.persistence.dao.booking.IBookingDAO;
import com.yd.etravel.persistence.dao.extraitem.IExtraItemDAO;
import com.yd.etravel.persistence.dao.room.availability.IRoomAvailabilityDAO;
import com.yd.etravel.persistence.dao.user.IUserDAO;
import com.yd.etravel.persistence.exception.PersistenceException;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;
import com.yd.etravel.service.user.UserHelper;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.util.IConstants.IBooking;
import com.yd.etravel.util.StringUtils;
import com.yd.etravel.util.mail.MailMessage;

/**
 * 
 * @author Dharsahana
 * 
 */
public class BookingManagerImpl implements IBookingManager {

    private IBookingDAO bookingDAO;
    private IExtraItemDAO extraItemDAO;
    private IRoomAvailabilityDAO roomAvailabilityDAO;
    private IUserDAO userDAO;
    private MailMessage bookingMessage;
    private MailMessage bookingConfirmation;
    private int onlineExpire;
    private int onrequestExpire;

    public int getOnlineExpire() {
	return onlineExpire;
    }

    public void setOnlineExpire(int onlineExpire) {
	this.onlineExpire = onlineExpire;
    }

    public int getOnrequestExpire() {
	return onrequestExpire;
    }

    public void setOnrequestExpire(int onrequestExpire) {
	this.onrequestExpire = onrequestExpire;
    }

    public MailMessage getBookingConfirmation() {
	return bookingConfirmation;
    }

    public void setBookingConfirmation(MailMessage bookingConfirmation) {
	this.bookingConfirmation = bookingConfirmation;
    }

    public void setBookingDAO(IBookingDAO bookingDAO) {
	this.bookingDAO = bookingDAO;
    }

    public void setExtraItemDAO(IExtraItemDAO extraItemDAO) {
	this.extraItemDAO = extraItemDAO;
    }

    public void setRoomAvailabilityDAO(IRoomAvailabilityDAO roomAvailabilityDAO) {
	this.roomAvailabilityDAO = roomAvailabilityDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
	this.userDAO = userDAO;
    }

    public MailMessage getBookingMessage() {
	return bookingMessage;
    }

    public void setBookingMessage(MailMessage bookingMessage) {
	this.bookingMessage = bookingMessage;
    }

    public BookingDTO save(BookingDTO bookingDTO) throws ServiceException {
	try {

	    // Check Availability
	    List<RoomDailyAvailability> roomDailyAvaList = roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    bookingDTO.getRoomAvalabiltyId(), bookingDTO
				    .getHotelBooking().getCheckInDate(),
			    bookingDTO.getHotelBooking().getCheckOutDate());
	    boolean avalable = this.checkAvailability(roomDailyAvaList,
		    bookingDTO.getHotelBooking().getNoOfRoom());

	    if (!avalable) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.booking.noAvailability"));
	    }

	    // Save booking

	    Booking booking = (Booking) bookingDAO
		    .save(bookingDTO.getBooking());

	    if (StringUtils.isEmpty(booking.getCode())) {
		StringBuilder sb = new StringBuilder().append('B');
		int len = booking.getId().toString().length();
		for (int i = len; i < IBooking.BOOKING_NUMBER_LENGTH; i++) {
		    sb.append('0');
		}
		sb.append(booking.getId().toString());
		booking.setCode(sb.toString());
		booking = (Booking) bookingDAO.save(booking);
	    }

	    bookingDTO.setBooking(booking);
	    bookingDTO.setBookingNumber(booking.getCode());

	    // Save Hotel booking
	    bookingDTO.getHotelBooking().setBooking(booking);
	    HotelBooking hotelBooking = bookingDTO.getHotelBooking();
	    hotelBooking.setRoomAvalabiltyId(bookingDTO.getRoomAvalabiltyId());
	    hotelBooking = (HotelBooking) bookingDAO.save(hotelBooking);
	    bookingDTO.setHotelBooking(hotelBooking);

	    // Save Room booking
	    bookingDTO.getRoomBooking().setHotelBooking(hotelBooking);
	    RoomBooking roomBooking = bookingDTO.getRoomBooking();
	    roomBooking = (RoomBooking) bookingDAO.save(roomBooking);
	    bookingDTO.setRoomBooking(roomBooking);

	    // Save Extra Item

	    bookingDTO.setExtraItemBookingList(addExtraItem(
		    bookingDTO.getExtraItemBookingList(), booking));

	    // Save Payment
	    if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_CASH_DES)) {
		bookingDTO.getPayment().setBooking(booking);

		Payment payment = (Payment) bookingDAO.save(bookingDTO
			.getPayment());
		bookingDTO.setPayment(payment);

	    }

	    GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(new Date());

	    // set expire date
	    if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_ONLINE_DES)) {

		calendar.add(GregorianCalendar.HOUR, onlineExpire);
		booking.setExpireDate(calendar.getTime());

	    } else if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_ON_REQUEST_DES)) {

		calendar.add(GregorianCalendar.HOUR, onrequestExpire);
		booking.setExpireDate(calendar.getTime());
	    }

	    updateRoomDailyAvailability(roomDailyAvaList, bookingDTO
		    .getHotelBooking().getNoOfRoom());

	    if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_CASH_DES)) {
		User bookingUser= (User) userDAO.findBySample( booking.getBookingUser() ).get(0);
		bookingUser.getFunctionSet();
		sendConfirmation(
			new UserHelper().getUserProfile(null,bookingUser
				), roomBooking);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return bookingDTO;
    }

    private void sendConfirmation(IUserProfile profile, RoomBooking booking) {

	bookingConfirmation.setParam(booking.getParams());
	bookingConfirmation.addParam(profile.getParams());
	bookingConfirmation.sendMail();

    }

    private boolean checkAvailability(
	    List<RoomDailyAvailability> roomDailyAvList, Integer room)
	    throws PersistenceException {
	boolean flag = true;

	for (RoomDailyAvailability roomDailyAva : roomDailyAvList) {
	    if (room > roomDailyAva.getAvailabalUnit()) {
		flag = false;
		break;
	    }
	}
	return flag;
    }

    private boolean updateRoomDailyAvailability(
	    List<RoomDailyAvailability> availList, Integer room)
	    throws PersistenceException {
	boolean flag = true;
	for (RoomDailyAvailability rd : availList) {
	    int updatedUnit = rd.getAvailabalUnit().intValue()
		    - room.intValue();
	    if (updatedUnit < 0) {
		flag = false;
		break;
	    }
	    rd.setAvailabalUnit(updatedUnit);
	    roomAvailabilityDAO.update(rd);
	}
	return flag;
    }

    public List<RoomBooking> findAllBooking() throws ServiceException {
	try {
	    return bookingDAO.findAllBooking();
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public List<BookingReportDTO> findBookingDetail(BookingReportSearchDTO dto)
	    throws ServiceException {
	try {
	    return bookingDAO.findBookingDetail(dto);
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    private ArrayList<ExtraItemBooking> addExtraItem(
	    ArrayList<ExtraItemBooking> arraListList, Booking booking)
	    throws PersistenceException {

	for (ExtraItemBooking extraItemBooking : arraListList) {
	    extraItemBooking.setBooking(booking);
	    try {
		extraItemBooking = (ExtraItemBooking) bookingDAO
			.merge(extraItemBooking);
	    } catch (PersistenceException e) {
		throw e;
	    }
	}

	return arraListList;

    }

    /**
     * uses to confirm bookings(on_request) once got payment to payed
     */
    public RoomBooking saveBookingConfirm(String bookingid, BigDecimal ammount)
	    throws ServiceException {
	try {
	    RoomBooking roomBooking = bookingDAO.findRoomBooking(bookingid);

	    Booking booking = roomBooking.getHotelBooking().getBooking();

	    booking.setStatusDes(IBooking.BOOKING_SATATUS_PAID_DES);
	    booking.setStatus(IBooking.BOOKING_SATATUS_PAID);

	    if (ammount != null) {
		Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setTotalPrice(ammount);
		bookingDAO.save(payment);
	    }
	    bookingDAO.save(booking);

	    sendConfirmation(
		    new UserHelper().getUserProfile(null,
			    booking.getBookingUser()), roomBooking);
	    return roomBooking;

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    public RoomBooking findRoomBooking(String bookingid)
	    throws ServiceException {
	try {
	    RoomBooking roomBooking = bookingDAO.findRoomBooking(bookingid);
	    return roomBooking;

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    public void saveFailedOnRequestBookings() throws ServiceException {
	try {

	    List<RoomBooking> bookingList = bookingDAO.findExpiredBookings(
		    new Date(), IBooking.BOOKING_SATATUS_ON_REQUEST,
		    IBooking.BOOKING_PAYMENT_METHOD_ON_REQUEST_DES);

	    if (bookingList != null && !bookingList.isEmpty()) {
		Map<String, IUserProfile> userMap = findUserMap(bookingList);
		saveCancelBooking(bookingList);
		sendCancelNotification(userMap, bookingList);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    public void saveFailedOnlineBookings() throws ServiceException {
	try {

	    List<RoomBooking> bookingList = bookingDAO.findExpiredBookings(
		    new Date(), IBooking.BOOKING_SATATUS_ON_REQUEST,
		    IBooking.BOOKING_PAYMENT_METHOD_ONLINE_DES);

	    if (bookingList != null && !bookingList.isEmpty()) {
		Map<String, IUserProfile> userMap = findUserMap(bookingList);
		saveCancelBooking(bookingList);
		sendCancelNotification(userMap, bookingList);
	    }

	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    private void sendCancelNotification(Map<String, IUserProfile> userMap,
	    List<RoomBooking> bookingList) {
	for (RoomBooking hb : bookingList) {
	    bookingMessage.setParam(hb.getParams());
	    bookingMessage.addParam(userMap.get(
		    hb.getHotelBooking().getBooking().getBookingUser()
			    .getName()).getParams());
	    bookingMessage.sendMail();
	}
    }

    private Map<String, IUserProfile> findUserMap(List<RoomBooking> bookingList)

    throws ServiceException {
	Map<String, IUserProfile> map = new HashMap<String, IUserProfile>();
	try {
	    UserHelper helper = new UserHelper();
	    for (RoomBooking b : bookingList) {
		User user = b.getHotelBooking().getBooking().getBookingUser();
		if (StringUtils.isNotEmpty(user))
		    map.put(user.getName(), helper.getUserProfile(null, user));
	    }

	} catch (Exception e) {
	    throw new ServiceException(null, e);
	}
	return map;
    }

    public void saveCancelBooking(List<RoomBooking> bookingList)
	    throws ServiceException {
	try {
	    for (RoomBooking rb : bookingList) {
		HotelBooking booking = rb.getHotelBooking();

		List<RoomDailyAvailability> roomDailyAvaList = roomAvailabilityDAO
			.findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
				booking.getRoomAvalabiltyId(),
				booking.getCheckInDate(),
				booking.getCheckOutDate());
		updateRevertRoomDailyAvailability(roomDailyAvaList,
			booking.getNoOfRoom());
		booking.getBooking().setCancelDate(new Date());
		booking.getBooking().setStatus(
			IBooking.BOOKING_SATATUS_CANCELED);
		booking.getBooking().setStatusDes(
			IBooking.BOOKING_SATATUS_CANCELED_DES);
		bookingDAO.update(booking.getBooking());
	    }
	} catch (PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    private boolean updateRevertRoomDailyAvailability(
	    List<RoomDailyAvailability> availList, Integer room)
	    throws PersistenceException {
	boolean flag = true;
	for (RoomDailyAvailability rd : availList) {
	    rd.addAvailabalUnit(room);
	    roomAvailabilityDAO.update(rd);
	}
	return flag;
    }

}
