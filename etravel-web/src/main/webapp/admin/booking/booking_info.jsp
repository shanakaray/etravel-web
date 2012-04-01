<%@ page language="java"%>
<%@page import="com.yd.etravel.domain.custom.booking.BookingDTO"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>
<html>
	<body>
		<html:form action="/admin/initSearch">
			<html:hidden property="id" />
			<table width="80%">
				<tr>
					<td>
						<table width="60%">

							<tr>
								<th colspan="2">
									Booking Summary
								</th>
							</tr>
							
							<logic:present name="ipgForm">
							<tr>
								<th>
									<strong>Transaction</strong>
								</th>
								<th>
									<bean:write name="ipgForm" property="txStatus" />
								</th>
							</tr>
							<tr>
								<th>
									<strong>Transaction Response</strong>
								</th>
								<th>
									<bean:write name="ipgForm" property="responseDesc" />
								</th>
							</tr>
							</logic:present>
							
							<tr>
								<td>
									<strong>Booking Number</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="booking.code" />
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Hotel</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="hotelBooking.hotel.name" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Room Type</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="roomBooking.room.roomType.name" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Check In Date</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="hotelBooking.checkInDateString" />
								</td>
							</tr>

							<tr>
								<td>
									<strong>Check Out Date</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="hotelBooking.checkOutString" />
								</td>
							</tr>
							
							
							
							<tr>
								<td>
									<strong>Room Price</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="booking.roomPrice" />&nbsp;USD
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>No of Room</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="hotelBooking.noOfRoom" />
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>No of Passengers</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="roomBooking.totalPax" />
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>No of Nights</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="hotelBooking.noOfNights" />
								</td>
							</tr>
							
							
							
							<tr>
								<td>
									<strong>Status</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="booking.statusDes" />
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Payment Method</strong>
								</td>
								<td>
									<bean:write name="bookingDTO" property="booking.paymentMethod" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Total Price</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDTO" property="booking.totalPrice" />&nbsp;USD</strong>
								</td>
							</tr>
							<tr>
								<td>
									<strong>Paid</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDTO" property="booking.paidAmount" />&nbsp;USD</strong>
								</td>
							</tr>
							<%if(((BookingDTO)session.getAttribute("bookingDTO")).getBooking().getAgent()!=null){ %>
							<tr>
								<td>
									<strong>Agent</strong>
								</td>
								<td><strong>
									<bean:write name="bookingDTO" property="booking.agent.firstName" />&nbsp;<bean:write name="bookingDTO" property="booking.agent.lastName" /></strong>
								</td>
							</tr>
                          <%} %>
						</table>
					</td>
				</tr>
				
				
				<tr>
					<td colspan="2">&nbsp;
					</td>
				</tr>
				<tr>
					<td colspan="2">&nbsp;
					</td>
				</tr>
				
				
				<tr>
								<th colspan="2">
									Extra Items
								</th>
							</tr>
			<tr>
 <td colspan="2">
<display:table class="its" id="i" cellspacing="2" export="true"
	style="width:100%" name='<%=((BookingDTO)session.getAttribute("bookingDTO")).getExtraItemBookingList()%>' sort="list" pagesize="20"  >
<display:column title="Item Name" style="width:10%" sortable="true" property="extraItem.name" class="sortable" />
<display:column title="Item Code" style="width:10%" sortable="true" property="extraItem.code" class="sortable" />
<display:column title="Detail" style="width:10%" sortable="true" property="comments" class="sortable"/>
<display:column title="Price per Night" style="width:10%;text-align: right;" sortable="true" property="extraItem.cost" class="sortable" />


</display:table>

  </td>
</tr>
				
				
				
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>

					<td colspan="2" align="left">
						<html:submit value="Home" /></td>
					</td>
				</tr>
				</table>
		</html:form>

	</body>
</html>

