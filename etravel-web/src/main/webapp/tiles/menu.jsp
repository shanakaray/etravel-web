<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.yd.etravel.util.IConstants"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="user-access" prefix="user"%>
<script type="text/javascript" src="../js/jquery/jquery.droppy.js"></script>
<link href="../css/AdminStyles.css" rel="stylesheet" type="text/css" />
<style>
/* Basic code - don't modify */
#nav {
	display: block;
	margin: 0;
	padding: 0;
	position: relative;
	font-family: Arial,Helvetica, sans-serif;
}

#nav li {
	display: block;
	list-style: none;
	margin: 0;
	padding: 0;
	float: right;
	position: relative;
	font-family: Arial,Helvetica, sans-serif;
}

#nav a {
	display: block;
	font-family: Arial,Helvetica, sans-serif;
}

#nav ul {
	display: none;
	position: absolute;
	left: 0;
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
}

* html #nav ul {
	line-height: 0;
}  /* IE6 "fix" */
#nav ul a {
	zoom: 0;
}  /* IE6/7 fix */
#nav ul li {
	float: none;
}

#nav ul ul {
	top: 0;
}

/* Essentials - configure this */
#nav ul {
	width: 150px;
}

#nav ul ul {
	left: 150px;
}

/* Everything else is theming */
#nav {
	background-color: #99CC00;
	height: 24px;
	font-family: Arial,Helvetica, sans-serif;
}

#nav * :hover {
	background-color: none;
}

#nav a {
	border-left: 1px solid white;
	color: white;
	font-size: 11px;
	padding: 6px;
	line-height: 1;
	font-weight:bolder;
	font-family: Arial, Helvetica, sans-serif;
}
 
#nav li.hover a {
	background-color: #7BA400;
}

#nav ul {
	top: 25px;
}

#nav ul li a {
	background-color: #7BA400;
}

#nav ul a.hover {
	background-color: #000;
}

#nav ul a {
	border-bottom: 1px solid white;
	border-right: none;
	opacity: 0.9;
	filter: alpha(opacity = 90%);
}
</style>
<script type='text/javascript'>
  $(function() {
    $('#nav').droppy({speed: 100});
  });
</script>

<table align="center" width="100%" class="menuText" cellpadding="0"
	cellspacing="0" border="0">

	<tr align="center">
		<td width="30%" bgcolor="#99CC00">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="20">&nbsp;					</td>
			  <td class="greetingText" width="60">
						Welcome :					</td>
			  <td class="menuTextBold">&nbsp;
	  <logic:present name="<%=IConstants.IUser.USER_PROFILE%>">
							<bean:write name="<%=IConstants.IUser.USER_PROFILE%>"
								property="firstname" />
							<bean:write name="<%=IConstants.IUser.USER_PROFILE%>"
								property="lastname" />
						</logic:present>
						<logic:notPresent name="<%=IConstants.IUser.USER_PROFILE%>">
                         Guest
                         </logic:notPresent>
					</td>
			  </tr>
			</table>
		</td>
		<td align="left" width="100%" class="menuText">
			<ul id='nav'>
				<li>
					<a href='../admin/logout.do'>Log out</a>
				</li>

					<user:access property="sys.menu.reservation.main">
					<li>
					 <a href='#'>Reservation</a>
					<ul>
						<user:access property="sys.menu.reservation.search">
						<li>
							<a href='../admin/initSearch.do'>Search</a>
						</li>
						</user:access>
						<user:access property="sys.menu.reservation.viewbookings">
						<li>
							<a href='../admin/initViewBooking.do'>View Bookings </a>
						</li>
						</user:access>
						<user:access property="sys.menu.reservation.bookingreport">
						<li>
							<a href='../admin/initBookingRepSearch.do'>Booking Report </a>
						</li>
						</user:access>
						<user:access property="sys.menu.reservation.reservationreport">
						<li>
							<a href='../admin/initReservationSheet.do'>Reservation Report </a>
						</li>
						</user:access>
					</ul>
				</li>
				</user:access>
				
				<user:access property="sys.menu.manageallocation.main">
				<li>
					<a href='#'>Manage Allocation</a>
					<ul>
						<user:access property="sys.menu.manageallocation.roomavailability">
						<li>
							<a href='../admin/initRoomAvailabilityCreate.do'>Room Availability</a>
						</li>
						</user:access>
						<user:access property="sys.menu.manageallocation.dailyavailability">
						<li>
							<a href='../admin/findAllRoomDailyAvailability.do'>Daily Availability </a>
						</li>
						</user:access>
					</ul>
				</li>
				</user:access>

				<user:access property="sys.menu.manageseason.main">
				<li>
					<a href='#'>Manage Season</a>
					<ul>
						<user:access property="sys.menu.manageseason.newseason">
						<li>
							<a href='../admin/initSeasonCreate.do'>New Season</a>
						</li>
						</user:access>
						<user:access property="sys.menu.manageseason.seasonalrate">
						<li>
							<a href='../admin/initRoomSeasonalRateCreate.do'>Seasonal Rate</a>
						</li>
						</user:access>
					</ul>
				</li>
				</user:access>

				<user:access property="sys.menu.manageroom.main">
				<li>
					<a href='#'>Manage Room</a>
					<ul>
						<user:access property="sys.menu.manageroom.roomtype">
						<li>
							<a href='../admin/initRoomTypeCreate.do'>Room Type</a>
						</li>
						</user:access>
						
						<user:access property="sys.menu.manageroom.newroom">
						<li>
							<a href='../admin/initRoomCreate.do'>New Room</a>
						</li>
						</user:access>
						<!--  <li>
							<a href='../admin/initOccupancyCreate.do'>Occupancy</a>
						</li>-->
						
					</ul>
				</li>
				</user:access>
				
				<user:access property="sys.menu.managehotel.main">
				<li>
					<a href='#'>Manage Hotel</a>
					<ul>
						<user:access property="sys.menu.managehotel.newhotel">
						<li>
							<a href='../admin/initHotelCreate.do'>New Hotel</a>
						</li>
						</user:access>
						<!-- li>
							<a href='../admin/initPaxCreate.do'>Pax</a>
						</li -->
						<user:access property="sys.menu.managehotel.extraitem">
						<li>
							<a href='../admin/initExtraCreate.do'>Extra Items</a>
						</li>
						</user:access>
					</ul>
				</li>
				</user:access>
				
				
				
				<user:access property="sys.menu.manageuser.main">
				<li>
					<a href='#'>Manage User</a>
					<ul>
										
						<user:access property="sys.menu.manageuser.resetpw">
						<li>
							<a href='../admin/initPwChange.do'>Reset Password</a>
						</li>
						</user:access>
						<user:access property="sys.menu.manageuser.newuser">
						<li>
							<a href='../admin/initUserCreate.do'>New User</a>
						</li>
						</user:access>
						<user:access property="sys.menu.manageuser.newcustomer">
						<li>
							<a href='../customer/initCustomerCreate.do'>New Customer</a>
						</li>
						</user:access>
						<user:access property="sys.menu.manageuser.newrole">
						<li>
							<a href='../admin/initRoleCreate.do'>New User Role</a>
						</li>
						</user:access>
						<user:access property="sys.menu.manageuser.assignrolefunctions">
						<li>
							<a href='../admin/initFunction.do'>Assign Role Functions</a>
						</li>
						</user:access>
						
					</ul>
				</li>
				</user:access>


			</ul>
		</td>

	</tr>
</table>



