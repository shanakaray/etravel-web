<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>Booking Details</title>
      
	<link id="cssfile" rel="stylesheet" type="text/css"	href="../css/styles.css" />
	<link href="../css/tv.css" rel="stylesheet" type="text/css" />
	<link href="../css/AdminStyles.css" rel="stylesheet" type="text/css" />

  </head>
  <bean:define id="thisbean" name="bookingDetailForm" type="com.yd.etravel.web.booking.BookingDetailForm" />
  <body style="background-image:url(../images/mgFade.jpg);">
  
  <table><tr><td>&nbsp;</td><td>
    <table align="left">

							
						   <tr>
								<td>
									<strong>Booking Number</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.bookingCode" />
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Hotel</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.hotelName" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Room Type</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.roomTypeName" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Check In Date</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.checkInDateString" />
								</td>
							</tr>

							<tr>
								<td>
									<strong>Check Out Date</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.checkOutDateString" />
								</td>
							</tr>
							
							
							
							
							
							<tr>
								<td>
									<strong>No of Rooms</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.noOfRoom" />
								</td>
							</tr>
							
														
							<tr>
								<td>
									<strong>No of Nights</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.noOfNights" />
								</td>
							</tr>
							
							
							
							<tr>
								<td>
									<strong>Status</strong>
								</td>
								<td>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.status" />
								</td>
							</tr>
							
							
							<tr>
								<td>
									<strong>Total Amount</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.totalPrice" />&nbsp;USD</strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Room Price</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.totalCost" />&nbsp;USD</strong>
								</td>
							</tr>
							
							
							
							<tr>
								<td>
									<strong>Paid Amount</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.paidAmount" />&nbsp;USD</strong>
								</td>
							</tr>
							
							
							
							<tr><td>&nbsp;</td><td>&nbsp;</td></tr>
							 <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
							<tr>
								<td colspan="2">
									<fieldset>
									<legend>Booked Customer details</legend>
									<table>
									
									<tr><td>Name:</td><td><bean:write name="bookingDetailForm" property="bookingReportDTO.userFName" />&nbsp;<bean:write name="bookingDetailForm" property="bookingReportDTO.userLName" /></td></tr>
									<tr><td>Address:</td><td><bean:write name="bookingDetailForm" property="bookingReportDTO.userAddress" /></td></tr>
									<tr><td>Contact:</td><td><bean:write name="bookingDetailForm" property="bookingReportDTO.userContact" /></td></tr>
									<tr><td>E-Mail:</td><td><bean:write name="bookingDetailForm" property="bookingReportDTO.userEmail" /></td></tr>
									
									</table>
									</fieldset>							
									</td>
							</tr>
							 <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
							 <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
							<tr>
								<td>
									<strong>Agent</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.agentName" /></strong>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Booked Date</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDetailForm" property="bookingReportDTO.bookingDateString" /></strong>
								</td>
							</tr>
                         
						</table>
  </td></tr>
  <tr><td>&nbsp;</td><td>&nbsp;</td></tr>
  <tr><td>&nbsp;</td><td>
  <display:table class="its" id="i" cellspacing="2" export="false" requestURI=""
	style="width:100%" name='<%=thisbean.getItemBookingList()%>' sort="list" pagesize="20"  >
<display:column title="Item Name" style="width:10%" sortable="true" property="extraItem.name" class="sortable" />
<display:column title="Item Code" style="width:10%" sortable="true" property="extraItem.code" class="sortable" />
<display:column title="Detail" style="width:10%" sortable="true" property="comments" class="sortable"/>
<display:column title="Price" style="width:10%;text-align: right;" sortable="true" property="extraItem.cost" class="sortable" />


</display:table>
  </td></tr>
  </table>
</html>
