package com.yd.etravel.service.booking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

@Service(value = "bookingService")
@Transactional(propagation = Propagation.SUPPORTS)
public class BookingManagerImpl implements IBookingManager {
    @Autowired(required = true)
    private IBookingDAO bookingDAO;
    @Autowired(required = true)
    private IExtraItemDAO extraItemDAO;
    @Autowired(required = true)
    private IRoomAvailabilityDAO roomAvailabilityDAO;
    @Autowired(required = true)
    private IUserDAO userDAO;
    private MailMessage bookingOverDueNotification;
    @Autowired(required = true)
    private MailMessage bookingConfirmation;
    private int onlineExpire;
    private int onrequestExpire;

    public int getOnlineExpire() {
	return this.onlineExpire;
    }

    public void setOnlineExpire(final int onlineExpire) {
	this.onlineExpire = onlineExpire;
    }

    public int getOnrequestExpire() {
	return this.onrequestExpire;
    }

    public void setOnrequestExpire(final int onrequestExpire) {
	this.onrequestExpire = onrequestExpire;
    }

    public MailMessage getBookingConfirmation() {
	return this.bookingConfirmation;
    }

    public void setBookingConfirmation(final MailMessage bookingConfirmation) {
	this.bookingConfirmation = bookingConfirmation;
    }

    public void setBookingDAO(final IBookingDAO bookingDAO) {
	this.bookingDAO = bookingDAO;
    }

    public void setExtraItemDAO(final IExtraItemDAO extraItemDAO) {
	this.extraItemDAO = extraItemDAO;
    }

    public void setRoomAvailabilityDAO(
	    final IRoomAvailabilityDAO roomAvailabilityDAO) {
	this.roomAvailabilityDAO = roomAvailabilityDAO;
    }

    public void setUserDAO(final IUserDAO userDAO) {
	this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public BookingDTO save(final BookingDTO bookingDTO) throws ServiceException {
	try {

	    // Check Availability
	    final List<RoomDailyAvailability> roomDailyAvaList = this.roomAvailabilityDAO
		    .findAllRoomDailyAvailabilityByRoomAvailabilityIdAndDateRange(
			    bookingDTO.getRoomAvalabiltyId(), bookingDTO
				    .getHotelBooking().getCheckInDate(),
			    bookingDTO.getHotelBooking().getCheckOutDate());
	    final boolean avalable = checkAvailability(roomDailyAvaList,
		    bookingDTO.getHotelBooking().getNoOfRoom());

	    if (!avalable) {
		throw new ServiceException(
			ValidationHelper
				.getMessageHolder("etravel.booking.noAvailability"));
	    }

	    // Save booking

	    Booking booking = (Booking) this.bookingDAO.save(bookingDTO
		    .getBooking());

	    if (StringUtils.isEmpty(booking.getCode())) {
		final StringBuilder sb = new StringBuilder().append('B');
		final int len = booking.getId().toString().length();
		for (int i = len; i < IBooking.BOOKING_NUMBER_LENGTH; i++) {
		    sb.append('0');
		}
		sb.append(booking.getId().toString());
		booking.setCode(sb.toString());
		booking = (Booking) this.bookingDAO.save(booking);
	    }

	    bookingDTO.setBooking(booking);
	    bookingDTO.setBookingNumber(booking.getCode());

	    // Save Hotel booking
	    bookingDTO.getHotelBooking().setBooking(booking);
	    HotelBooking hotelBooking = bookingDTO.getHotelBooking();
	    hotelBooking.setRoomAvalabiltyId(bookingDTO.getRoomAvalabiltyId());
	    hotelBooking = (HotelBooking) this.bookingDAO.save(hotelBooking);
	    bookingDTO.setHotelBooking(hotelBooking);

	    // Save Room booking
	    bookingDTO.getRoomBooking().setHotelBooking(hotelBooking);
	    RoomBooking roomBooking = bookingDTO.getRoomBooking();
	    roomBooking = (RoomBooking) this.bookingDAO.save(roomBooking);
	    bookingDTO.setRoomBooking(roomBooking);

	    // Save Extra Item

	    bookingDTO.setExtraItemBookingList(addExtraItem(
		    bookingDTO.getExtraItemBookingList(), booking));

	    // Save Payment
	    if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_CASH_DES)) {
		bookingDTO.getPayment().setBooking(booking);

		final Payment payment = (Payment) this.bookingDAO
			.save(bookingDTO.getPayment());
		bookingDTO.setPayment(payment);

	    }

	    final GregorianCalendar calendar = new GregorianCalendar();
	    calendar.setTime(new Date());

	    // set expire date
	    if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_ONLINE_DES)) {

		calendar.add(Calendar.HOUR, this.onlineExpire);
		booking.setExpireDate(calendar.getTime());

	    } else if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_ON_REQUEST_DES)) {

		calendar.add(Calendar.HOUR, this.onrequestExpire);
		booking.setExpireDate(calendar.getTime());
	    }

	    updateRoomDailyAvailability(roomDailyAvaList, bookingDTO
		    .getHotelBooking().getNoOfRoom());

	    if (booking.getPaymentMethod().equals(
		    IBooking.BOOKING_PAYMENT_METHOD_CASH_DES)) {
		final User bookingUser = (User) this.userDAO.findBySample(
			booking.getBookingUser()).get(0);
		bookingUser.getFunctionSet();
		sendConfirmation(
			new UserHelper().getUserProfile(null, bookingUser),
			roomBooking);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
	return bookingDTO;
    }

    private void sendConfirmation(final IUserProfile profile,
	    final RoomBooking booking) {

	this.bookingConfirmation.setParam(booking.getParams());
	this.bookingConfirmation.addParam(profile.getParams());
	this.bookingConfirmation.sendMail();

    }

    private boolean checkAvailability(
	    final List<RoomDailyAvailability> roomDailyAvList,
	    final Integer room) throws PersistenceException {
	boolean flag = true;

	for (final RoomDailyAvailability roomDailyAva : roomDailyAvList) {
	    if (room > roomDailyAva.getAvailabalUnit()) {
		flag = false;
		break;
	    }
	}
	return flag;
    }

    private boolean updateRoomDailyAvailability(
	    final List<RoomDailyAvailability> availList, final Integer room)
	    throws PersistenceException {
	boolean flag = true;
	for (final RoomDailyAvailability rd : availList) {
	    final int updatedUnit = rd.getAvailabalUnit().intValue()
		    - room.intValue();
	    if (updatedUnit < 0) {
		flag = false;
		break;
	    }
	    rd.setAvailabalUnit(updatedUnit);
	    this.roomAvailabilityDAO.update(rd);
	}
	return flag;
    }

    @Override
    public List<RoomBooking> findAllBooking() throws ServiceException {
	try {
	    return this.bookingDAO.findAllBooking();
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Override
    public List<BookingReportDTO> findBookingDetail(
	    final BookingReportSearchDTO dto) throws ServiceException {
	try {
	    return this.bookingDAO.findBookingDetail(dto);
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    private ArrayList<ExtraItemBooking> addExtraItem(
	    final ArrayList<ExtraItemBooking> arraListList,
	    final Booking booking) throws PersistenceException {

	for (ExtraItemBooking extraItemBooking : arraListList) {
	    extraItemBooking.setBooking(booking);
	    try {
		extraItemBooking = (ExtraItemBooking) this.bookingDAO
			.merge(extraItemBooking);
	    } catch (final PersistenceException e) {
		throw e;
	    }
	}

	return arraListList;

    }

    /**
     * uses to confirm bookings(on_request) once got payment to payed
     */
    @Transactional
    @Override
    public RoomBooking saveBookingConfirm(final String bookingid,
	    final BigDecimal ammount) throws ServiceException {
	try {
	    final RoomBooking roomBooking = this.bookingDAO
		    .findRoomBooking(bookingid);

	    final Booking booking = roomBooking.getHotelBooking().getBooking();

	    booking.setStatusDes(IBooking.BOOKING_SATATUS_PAID_DES);
	    booking.setStatus(IBooking.BOOKING_SATATUS_PAID);

	    if (ammount != null) {
		final Payment payment = new Payment();
		payment.setBooking(booking);
		payment.setTotalPrice(ammount);
		this.bookingDAO.save(payment);
	    }
	    this.bookingDAO.save(booking);

	    sendConfirmation(
		    new UserHelper().getUserProfile(null,
			    booking.getBookingUser()), roomBooking);
	    return roomBooking;

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    @Override
    public RoomBooking findRoomBooking(final String bookingid)
	    throws ServiceException {
	try {
	    final RoomBooking roomBooking = this.bookingDAO
		    .findRoomBooking(bookingid);
	    return roomBooking;

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    @Transactional
    @Override
    public void saveFailedOnRequestBookings() throws ServiceException {
	try {

	    final List<RoomBooking> bookingList = this.bookingDAO
		    .findExpiredBookings(new Date(),
			    IBooking.BOOKING_SATATUS_ON_REQUEST,
			    IBooking.BOOKING_PAYMENT_METHOD_ON_REQUEST_DES);

	    if (bookingList != null && !bookingList.isEmpty()) {
		final Map<String, IUserProfile> userMap = findUserMap(bookingList);
		saveCancelBooking(bookingList);
		sendCancelNotification(userMap, bookingList);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    @Transactional
    @Override
    public void saveFailedOnlineBookings() throws ServiceException {
	try {

	    final List<RoomBooking> bookingList = this.bookingDAO
		    .findExpiredBookings(new Date(),
			    IBooking.BOOKING_SATATUS_ON_REQUEST,
			    IBooking.BOOKING_PAYMENT_METHOD_ONLINE_DES);

	    if (bookingList != null && !bookingList.isEmpty()) {
		final Map<String, IUserProfile> userMap = findUserMap(bookingList);
		saveCancelBooking(bookingList);
		sendCancelNotification(userMap, bookingList);
	    }

	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}

    }

    private void sendCancelNotification(
	    final Map<String, IUserProfile> userMap,
	    final List<RoomBooking> bookingList) {
	for (final RoomBooking roomBooking : bookingList) {
	    this.bookingOverDueNotification.setParam(roomBooking.getParams());
	    this.bookingOverDueNotification.addParam(userMap.get(
		    roomBooking.getHotelBooking().getBooking().getBookingUser()
			    .getName()).getParams());
	    this.bookingOverDueNotification.sendMail();
	}
    }

    private Map<String, IUserProfile> findUserMap(
	    final List<RoomBooking> bookingList)

    throws ServiceException {
	final Map<String, IUserProfile> map = new HashMap<String, IUserProfile>();
	try {
	    final UserHelper helper = new UserHelper();
	    for (final RoomBooking b : bookingList) {
		final User user = b.getHotelBooking().getBooking()
			.getBookingUser();
		if (StringUtils.isNotEmpty(user)) {
		    map.put(user.getName(), helper.getUserProfile(null, user));
		}
	    }

	} catch (final Exception e) {
	    throw new ServiceException(null, e);
	}
	return map;
    }

    @Transactional
    @Override
    public void saveCancelBooking(final List<RoomBooking> bookingList)
	    throws ServiceException {
	try {
	    for (final RoomBooking rb : bookingList) {
		final HotelBooking booking = rb.getHotelBooking();

		final List<RoomDailyAvailability> roomDailyAvaList = this.roomAvailabilityDAO
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
		this.bookingDAO.update(booking.getBooking());
	    }
	} catch (final PersistenceException e) {
	    throw new ServiceException(null, e);
	}
    }

    private boolean updateRevertRoomDailyAvailability(
	    final List<RoomDailyAvailability> availList, final Integer room)
	    throws PersistenceException {
	final boolean flag = true;
	for (final RoomDailyAvailability roomDailyAvil : availList) {
	    roomDailyAvil.addAvailabalUnit(room);
	    this.roomAvailabilityDAO.update(roomDailyAvil);
	}
	return flag;
    }

}
