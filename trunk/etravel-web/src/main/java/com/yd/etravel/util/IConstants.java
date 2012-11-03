package com.yd.etravel.util;

public interface IConstants {

	public interface IBooking {
		public static final String BOOKING_SATATUS_CANCELED = "Canceled";
		public static final String BOOKING_SATATUS_CANCELED_DES = "Canceled";
		public static final String BOOKING_SATATUS_ON_REQUEST = "On_Request";
		public static final String BOOKING_SATATUS_ON_REQUEST_DES = "On_Request";
		public static final String BOOKING_SATATUS_BOOKING = "CBO";
		public static final String BOOKING_SATATUS_PAID = "Paid";
		public static final String BOOKING_SATATUS_PAID_DES = "Payment_Confirmed";
		public static final String BOOKING_SATATUS_PAYMENT_FAILD = "Payment_Failed";
		public static final int BOOKING_NUMBER_LENGTH = 6;
		public static final String BOOKING_PAYMENT_METHOD_PLEASE_SELECT = "0";
		public static final String BOOKING_PAYMENT_METHOD_ONLINE = "1";
		public static final String BOOKING_PAYMENT_METHOD_CASH = "2";
		public static final String BOOKING_PAYMENT_METHOD_ON_REQUEST = "3";
		public static final String BOOKING_PAYMENT_METHOD_PLEASE_SELECT_DES = "Please Select";
		public static final String BOOKING_PAYMENT_METHOD_ONLINE_DES = "Online";
		public static final String BOOKING_PAYMENT_METHOD_CASH_DES = "Cash";
		public static final String BOOKING_PAYMENT_METHOD_ON_REQUEST_DES = "On Request";
	}

	public interface IBundle {
		public static final String ETRAVEL = "etravel";
	}

	public interface ICommon {
		public static final String INFO_MSG_KEY = "com.yd.web.infomsg";
		public static final String EMPTYSTRING = "";
		public static final char LINE_FEED = '\r'; // (aka u000A);
		public static final String STRING_ZERO = "0";
		public static final int CHOMP_LENGTH = 32;
		public static final String EMAILVALIDATION = ".+@.+\\.[a-z]+";
		public static final String MSGRES = "MessageResources";
	}

	public interface ICurrency {
		public static final String USD = "USD";
		public static final String STALIN_POUND = "SP";
		public static final String EURO = "EU";
	}

	public interface IForwards {
		public static final String SUCCESS = "success";
		public static final String SHOWALL = "showall";
		public static final String ERROR = "error";
		public static final String FILEDETAILS = "filedetails";
		public static final String LOGIN = "login";
		public static final String CONFIRM = "conform";
		public static final String NOTALLOWED = "notallowed";
	}

	public interface IMsg {
		public static final String COM_SAVE_MSG = "etravel.info.common.save";
		public static final String COM_UPDATE_MSG = "etravel.info.common.update";
		public static final String COM_DELETE_MSG = "etravel.info.common.delete";
		public static final String BOOKING_CONFIRM_MSG = "etravel.info.booking.successful";
		public static final String USER_REGISTRATION_MSG = "etravel.info.user.registration.successful";
	}

	public interface IOccupancy {
		public static final String COMMON_OCCUPANCY_NAME = "--Any--";

	}

	public interface IPax {
		public static final String ADULT = "Adult";
		public static final String CHILD = "Child";
		public static final String INFANT = "Infant";
	}

	public interface IServiceNames {
		public static final String USER = "userService";
		public static final String BOOKING = "bookingService";
		public static final String HOTEL = "hotelService";
		public static final String ROOM = "roomService";
		public static final String IPG = "ipgService";
		public static final String CONTENT = "contentService";
	}

	public interface IUser {
		public static final String USER_PROFILE = "com.yd.etravel.profile";
		public static final String HISTORY_TOKEN = "com.yd.history.token";
	}

	public interface IUserFunctions {
		public static final String IS_USER_CAN_LOGIN = "sys_logic_isusercanlogin";
		public static final String BOOKING_USER_CAN_CHANGE = "sys.logic.booking.changebookinguser";
		public static final String BOOKINGREPORT_ASSIGNHOTELS_ONLY = "sys.logic.booking.report.asign.hotels.only";
	}

	public interface IUserRoles {
		public static final String HOTELADMINROLENAME = "Hotel_Admin";
		public static final String SYSADMIN_ROLE_NAME = "Sys_Admin";
		public static final String CUSTOMERROLENAME = "Customer";
		public static final String AGENTROLENAME = "Agent";
		public static final Long HOTEL_ADMIN_ROLE_ID = 1L;
		public static final Long SYSADMIN_ROLE_ID = 2L;
		public static final Long CUSTOMER_ROLE_ID = 3L;
		public static final Long AGENT_ROLE_ID = 4L;
	}

}
