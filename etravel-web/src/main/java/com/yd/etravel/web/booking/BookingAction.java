/**
 * 
 */
package com.yd.etravel.web.booking;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yd.etravel.domain.booking.Booking;
import com.yd.etravel.domain.booking.ExtraItemBooking;
import com.yd.etravel.domain.booking.HotelBooking;
import com.yd.etravel.domain.booking.Payment;
import com.yd.etravel.domain.booking.RoomBooking;
import com.yd.etravel.domain.custom.booking.BookingDTO;
import com.yd.etravel.domain.custom.search.RoomDTO;
import com.yd.etravel.domain.custom.search.SearchRequestDTO;
import com.yd.etravel.domain.custom.user.UserSearchDTO;
import com.yd.etravel.domain.extraitem.ExtraItem;
import com.yd.etravel.domain.user.User;
import com.yd.etravel.service.exception.ServiceException;
import com.yd.etravel.service.message.ValidationHelper;
import com.yd.etravel.service.util.IUserProfile;
import com.yd.etravel.util.IConstants.IBooking;
import com.yd.etravel.util.IConstants.ICommon;
import com.yd.etravel.util.IConstants.IUser;
import com.yd.etravel.util.IConstants.IUserFunctions;
import com.yd.etravel.util.IConstants.IUserRoles;
import com.yd.etravel.util.IpgUtil;
import com.yd.etravel.util.ServiceHelper;
import com.yd.etravel.web.common.BaseAction;

/**
 * @author Dharshana
 * 
 */
public class BookingAction extends BaseAction {

	private static final String CLIENT_URL = "virtualPaymentClientURL";
	private static final String VERSION = "vpc_Version";
	private static final String COMMAND = "vpc_Command";
	private static final String ACCESS_CODE = "vpc_AccessCode";
	private static final String MERCH_TX_REF = "vpc_MerchTxnRef";
	private static final String MERCHANT = "vpc_Merchant";
	private static final String ORDER_INFO = "vpc_OrderInfo";
	private static final String AMOUNT = "vpc_Amount";
	private static final String RETURN_URL = "vpc_ReturnURL";
	private static final String LOCALE = "vpc_Locale";
	private static final String TICKET_NO = "vpc_TicketNo";

	@Override
	protected ActionForward add(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	private void addIpgPrams(final HttpServletRequest request,
			final BookingDTO bookingDTO) {
		addIpgPrams(request, bookingDTO.getBooking().getCode(), bookingDTO
				.getBooking().getPaidAmountCts());
	}

	private void addIpgPrams(final HttpServletRequest request,
			final String bookingId, final String annountInCts) {
		final IpgUtil ipg = ServiceHelper.getInstance().getIpgUtil();
		request.setAttribute(CLIENT_URL, ipg.getVirtualPaymentClientURL());
		request.setAttribute(VERSION, ipg.getVpc_Version());
		request.setAttribute(COMMAND, ipg.getVpc_Command());
		request.setAttribute(ACCESS_CODE, ipg.getVpc_AccessCode());
		request.setAttribute(MERCH_TX_REF, bookingId);
		request.setAttribute(MERCHANT, ipg.getVpc_Merchant());
		request.setAttribute(ORDER_INFO, ipg.getVpc_OrderInfo());
		request.setAttribute(AMOUNT, annountInCts);
		request.setAttribute(RETURN_URL, ipg.getVpc_ReturnURL());
		request.setAttribute(LOCALE, ipg.getVpc_Locale());
		request.setAttribute(TICKET_NO, bookingId);
	}

	@Override
	protected ActionForward back(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ActionForward create(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final BookingForm bookingForm = (BookingForm) form;
		double payed = 0.0;
		if (bookingForm.isRegisterd()) {

			try {
				final IUserProfile profile = getUserManager().authanticateUser(
						bookingForm.getRUsername(), bookingForm.getRPassword());
				switchUser(request, profile);

			} catch (final ServiceException ex) {
				bookingForm.setRUsername(ICommon.EMPTYSTRING);
				bookingForm.setRPassword(ICommon.EMPTYSTRING);
				throw ex;
			}

		} else if (bookingForm.isGuest()) {

			final IUserProfile profile = getUserManager().saveCustomer(
					getUser(bookingForm));
			switchUser(request, profile);
		}

		SearchRequestDTO searchRequestDTO = new SearchRequestDTO();
		if (request.getSession().getAttribute("searchRequestDTO") != null) {
			searchRequestDTO = (SearchRequestDTO) request.getSession()
					.getAttribute("searchRequestDTO");
		}
		BookingDTO bookingDTO = new BookingDTO();
		RoomDTO roomDTO = new RoomDTO();
		ArrayList<ExtraItem> extraItemList = new ArrayList<ExtraItem>();
		// this should get from form bean
		if (request.getSession().getAttribute("roomDTO") != null) {
			roomDTO = (RoomDTO) request.getSession().getAttribute("roomDTO");
		}

		if (request.getSession().getAttribute("extraItem") != null) {
			extraItemList = getExtraitemList(request);
		}

		if (roomDTO.getRoomType().getMaxPassengers() < (bookingForm
				.getTotalPax() / bookingForm.getNoOfRoom())) {
			throw new ServiceException(
					ValidationHelper
							.getMessageHolder("etravel.booking.max.pax.limit.exceed"));
		}

		double extraItemPrice = 0.0;

		final ArrayList<ExtraItemBooking> etbList = new ArrayList<ExtraItemBooking>();

		for (final ExtraItem object : extraItemList) {
			final ExtraItemBooking eib = getExtraItemBooking(object);
			etbList.add(eib);
			extraItemPrice = extraItemPrice
					+ eib.getExtraItem().getCost().doubleValue();
		}

		bookingDTO.setExtraItemBookingList(etbList);

		final IUserProfile profile = (IUserProfile) request.getSession()
				.getAttribute(IUser.USER_PROFILE);
		Long userId = profile.getId();
		// current user can change booking user(Hotel Admin)
		if (profile.hasFunction(IUserFunctions.BOOKING_USER_CAN_CHANGE)) {
			// creates a new Customer.
			if (bookingForm.isNewCustomer()) {
				userId = getUserManager().saveCustomer(newUser(bookingForm))
						.getId();
			} else {
				// selects a existing customer.
				if ((bookingForm.getBookedUserId() != null)
						&& (bookingForm.getBookedUserId().longValue() > 0)) {
					userId = bookingForm.getBookedUserId();
				}
			}

		}
		final Booking booking = new Booking();
		booking.setBookingUser(getUserManager().findUserById(userId));
		booking.setBookingDate(new Date());

		if ((bookingForm.getAgentId() != null)
				&& (bookingForm.getAgentId().longValue() > 0)) {
			booking.setAgent(getUserManager().findUserById(
					bookingForm.getAgentId()));
		}

		booking.setDepatureDate(searchRequestDTO.getCheckIn());

		final double totalPrice = bookingForm.getNoOfRoom()
				* roomDTO.getRoomSeasonalRate().getTotalCost().doubleValue()
				* searchRequestDTO.getNoOfNights();
		final double tot = totalPrice + extraItemPrice;

		booking.setTotalPrice(tot);

		final double roomPrice = bookingForm.getNoOfRoom()
				* roomDTO.getRoomSeasonalRate().getTotalCost().doubleValue()
				* searchRequestDTO.getNoOfNights();

		booking.setRoomPrice(roomPrice);

		if (bookingForm.isSingleNight()) {
			payed = (bookingForm.getNoOfRoom() * roomDTO.getRoomSeasonalRate()
					.getTotalCost().doubleValue())
					+ extraItemPrice;

		} else {
			payed = booking.getTotalPrice().doubleValue();
		}

		booking.setPaidAmount(payed);
		booking.setSingleNight(bookingForm.isSingleNight());

		booking.setActive(true);
		bookingDTO.setBooking(booking);
		bookingDTO.setRoomAvalabiltyId(roomDTO.getId());

		final HotelBooking hotelBooking = getHotelbooking(searchRequestDTO,
				roomDTO, booking);
		hotelBooking.setNoOfRoom(bookingForm.getNoOfRoom());
		bookingDTO.setHotelBooking(hotelBooking);

		bookingDTO.setRoomBooking(getRoomBooking(bookingForm, roomDTO,
				hotelBooking));

		// On request

		if (bookingForm.getPaymentMethodId().equals(
				IBooking.BOOKING_PAYMENT_METHOD_ON_REQUEST)) {
			handleOnRequestBooking(bookingDTO);
		} else if (bookingForm.getPaymentMethodId().equals(
				IBooking.BOOKING_PAYMENT_METHOD_CASH)) {
			handleCashBooking(payed, bookingDTO);
		} else if (bookingForm.getPaymentMethodId().equals(
				IBooking.BOOKING_PAYMENT_METHOD_ONLINE)) {
			handleOnlineBooking(bookingDTO);
		}

		// Save booking
		bookingDTO = getBookingManager().save(bookingDTO);
		request.getSession().setAttribute("bookingDTO", bookingDTO);

		if (bookingForm.getPaymentMethodId().equals(
				IBooking.BOOKING_PAYMENT_METHOD_ONLINE)) {
			addIpgPrams(request, bookingDTO);
			return mapping.findForward("ipg");
		} else {
			return mapping.findForward(SUCCESS);
		}
	}

	private RoomBooking getRoomBooking(final BookingForm bookingForm,
			final RoomDTO roomDTO, final HotelBooking hotelBooking) {
		final RoomBooking roomBooking = new RoomBooking();
		roomBooking.setHotelBooking(hotelBooking);
		roomBooking.setActive(true);
		roomBooking.setRoom(roomDTO.getRoom());
		roomBooking.setTotalPax(bookingForm.getTotalPax());
		roomBooking.setPrice(roomDTO.getRoomSeasonalRate().getTotalCost());
		return roomBooking;
	}

	private HotelBooking getHotelbooking(
			final SearchRequestDTO searchRequestDTO, final RoomDTO roomDTO,
			final Booking booking) {
		final HotelBooking hotelBooking = new HotelBooking();
		hotelBooking.setActive(true);
		hotelBooking.setBooking(booking);
		hotelBooking.setCheckInDate(searchRequestDTO.getCheckIn());
		hotelBooking.setCheckOutDate(searchRequestDTO.getCheckOut());
		hotelBooking.setHotel(roomDTO.getHotel());
		return hotelBooking;
	}

	@SuppressWarnings("unchecked")
	private ArrayList<ExtraItem> getExtraitemList(
			final HttpServletRequest request) {
		return ((ArrayList<ExtraItem>) request.getSession().getAttribute(
				"extraItem"));
	}

	@Override
	protected ActionForward delete(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected ActionForward edit(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected ActionForward find(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return mapping.findForward(SUCCESS);
	}

	@Override
	protected ActionForward forward(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final BookingForm bookingForm = (BookingForm) form;
		final RoomBooking booking = getBookingManager().findRoomBooking(
				bookingForm.getBookingId());
		addIpgPrams(request, bookingForm.getBookingId(), booking
				.getHotelBooking().getBooking().getPaidAmountCts());
		return mapping.findForward("ipg");
	}

	protected ExtraItemBooking getExtraItemBooking(final ExtraItem object) {
		final ExtraItemBooking eib = new ExtraItemBooking();
		eib.setActive(true);
		eib.setExtraItem(object);
		eib.setComments(object.getBookingComments());
		eib.setPrice(object.getCost());
		return eib;
	}

	private User getUser(final BookingForm bookingForm) {
		final User user = new User();
		user.setId((bookingForm.getUid() != null)
				&& (bookingForm.getUid().longValue() > 0) ? bookingForm
				.getUid() : null);
		user.setName(bookingForm.getCusUsername());
		user.setAddress(bookingForm.getAddress());
		user.setContact(bookingForm.getContact());
		user.setEmail(bookingForm.getEmail());
		user.setCode(bookingForm.getNic());
		user.setFirstName(bookingForm.getFirstName());
		user.setLastName(bookingForm.getLastName());
		user.setPassword(bookingForm.getCusPassword());
		user.setActive(true);
		return user;
	}

	private void handleCashBooking(final double payed,
			final BookingDTO bookingDTO) {
		bookingDTO.getBooking().setStatusDes(IBooking.BOOKING_SATATUS_PAID_DES);
		bookingDTO.getBooking().setStatus(IBooking.BOOKING_SATATUS_PAID);
		bookingDTO.getBooking().setPaymentMethod(
				IBooking.BOOKING_PAYMENT_METHOD_CASH_DES);

		final Payment payment = new Payment();
		payment.setTotalPrice(payed);
		bookingDTO.setPayment(payment);
		addInfoMessages(BOOKING_CONFIRM_MSG);
	}

	private void handleOnlineBooking(final BookingDTO bookingDTO) {
		bookingDTO.getBooking().setPaymentMethod(
				IBooking.BOOKING_PAYMENT_METHOD_ONLINE_DES);
		bookingDTO.getBooking().setStatusDes(
				IBooking.BOOKING_SATATUS_ON_REQUEST_DES);
		bookingDTO.getBooking().setStatus(IBooking.BOOKING_SATATUS_ON_REQUEST);
	}

	private void handleOnRequestBooking(final BookingDTO bookingDTO) {
		bookingDTO.getBooking().setStatusDes(
				IBooking.BOOKING_SATATUS_ON_REQUEST_DES);
		bookingDTO.getBooking().setStatus(IBooking.BOOKING_SATATUS_ON_REQUEST);
		bookingDTO.getBooking().setPaymentMethod(
				IBooking.BOOKING_PAYMENT_METHOD_ON_REQUEST_DES);
		addInfoMessages(BOOKING_CONFIRM_MSG);
	}

	@Override
	protected ActionForward init(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		final BookingForm bookingForm = (BookingForm) form;
		bookingForm.reset(mapping, request);

		if (request.getSession().getAttribute("roomDTO") != null) {
			bookingForm.setRoomDTO((RoomDTO) request.getSession().getAttribute(
					"roomDTO"));
		}

		final UserSearchDTO userSearchDTO = new UserSearchDTO();
		final Set<Long> roleIds = new HashSet<Long>();
		roleIds.add(IUserRoles.AGENT_ROLE_ID);
		userSearchDTO.setRoleIds(roleIds);

		final ArrayList<User> agentCol = (ArrayList<User>) getUserManager()
				.findUsers(userSearchDTO);
		bookingForm.setAllAgent(agentCol);

		if (hasAccess(IUserFunctions.BOOKING_USER_CAN_CHANGE, request)) {

			roleIds.add(IUserRoles.CUSTOMER_ROLE_ID);
			userSearchDTO.setRoleIds(roleIds);
			bookingForm.setAllCustomers(getUserManager().findUsers(
					userSearchDTO));
		}

		return mapping.findForward(SUCCESS);
	}

	private User newUser(final BookingForm bookingForm) {
		final User user = new User();
		user.setName(bookingForm.getCusUsername());
		user.setAddress(bookingForm.getAddress());
		user.setContact(bookingForm.getContact());
		user.setEmail(bookingForm.getEmail());
		user.setCode(bookingForm.getNic());
		user.setFirstName(bookingForm.getFirstName());
		user.setLastName(bookingForm.getLastName());
		user.setPassword(bookingForm.getCusPassword());
		user.setActive(true);
		return user;
	}

	@Override
	public ActionForward process(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return mapping.findForward(SUCCESS);
	}

	@Override
	protected ActionForward save(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	public ActionForward search(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected ActionForward send(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	@Override
	protected ActionForward sort(final ActionMapping mapping,
			final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		return null;
	}

	private void switchUser(final HttpServletRequest request,
			final IUserProfile profile) {
		request.getSession().removeAttribute(IUser.USER_PROFILE);
		request.getSession().setAttribute(IUser.USER_PROFILE, profile);
		Thread.currentThread().setName(profile.getUsername());
	}

}
