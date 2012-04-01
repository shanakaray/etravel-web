<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java"%>
<%@page import="com.yd.etravel.util.IConstants.IBooking"%>
<%@page import="com.yd.etravel.util.IConstants"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <script type="text/javascript" charset="utf-8">
                     
      $(function()
		{
			$('.date-pick').datePicker({startDate:'01/01/1996'});
		});
		
		function cle(){
		document.bookingRepForm.bookingId.value='';
		document.bookingRepForm.bookedFromString.value='';
		document.bookingRepForm.bookedToString.value='';
		document.bookingRepForm.hotelId.value='-1';
		document.bookingRepForm.roomId.value='-1';
		
		}
         
		</script>
 
  <body>
  <table width="100%" border="0">
  <bean:define id="thisbean" name="bookingRepForm" type="com.yd.etravel.web.booking.BookingReportForm" />		
			<tbody>
				<tr>
					<td>
					    <html:form action="/admin/searchBookingRep">
						<table width="50%" border="0">
							
								<tr>
									<td>
										Hotel
									</td>
									<td>
										<html:select property="hotelId" onchange="this.form.action='./initBookingRepSearch.do';this.form.submit();">
                            			<logic:notEmpty name="bookingRepForm" property="hotelList" >
										<html:optionsCollection property="hotelList" label="name" value="id" />
										</logic:notEmpty>
										<option value="-1" <logic:equal value="-1" property="hotelId" name="bookingRepForm">selected="selected"</logic:equal> >please select</option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										Room Type
									</td>
									<td>
										<html:select property="roomId">
						   				<logic:notEmpty name="bookingRepForm" property="allRoom" >
										<html:optionsCollection property="allRoom" label="roomType.name" value="id" />
										</logic:notEmpty>
										<option value="-1" <logic:equal value="-1" property="roomId" name="bookingRepForm">selected="selected"</logic:equal> >please select</option>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										Booking Id 
									</td>
									<td>
										<html:text property="bookingId" size="12" maxlength="15"/>
									</td>
								</tr>
								<tr>
									<td>
										Booked From 
									</td>
									<td>
										<html:text styleId="bookedFromString" styleClass="date-pick" property="bookedFromString" size="12" maxlength="12" readonly="true"/>
									</td>
								</tr>
								<tr>
									<td>
										Booked To
									</td>
									<td>
										<html:text styleClass="date-pick" property="bookedToString" size="12" maxlength="12" readonly="true"/>
									</td>
								</tr>
								

								<tr>
									<td><html:submit>Search</html:submit></td>
									<td><input type="button" onclick="cle()" value="Clear"/></td>
								</tr>

							
						</table>
						</html:form>

					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<display:table class="its" id="i" cellspacing="2" export="true"	style="width:100%" name="<%=thisbean.getBookingList()%>" sort="list" pagesize="50"  requestURI="" defaultsort="10" defaultorder="descending">
						<display:column title="Booking Id" style="width:8%" sortable="true" property="bookingCode" class="sortable" />
						<display:column title="Booked By" style="width:8%" sortable="true" class="sortable" >
						<bean:write name="i" property="userFName"/> <bean:write name="i" property="userLName"/> 
						</display:column>
						
						<display:column title="Booked User name" style="width:7%" sortable="true" property="userName" class="sortable" />
						<display:column title="Status" style="width:8%" sortable="true" class="sortable" property="status"/>
						<!--  display:column title="Agent" style="width:10%" sortable="true" property="agentName" class="sortable" /-->
						<display:column title="Hotel" style="width:10%" sortable="true" property="hotelName" class="sortable" />
						<display:column title="Room Type" style="width:10%" sortable="true" property="roomTypeName" class="sortable"/>
						
						<display:column title="Rooms" style="width:5%" sortable="true" property="noOfRoom" class="sortable"/>	
						<display:column title="Check In" style="width:10%" sortable="true" property="checkInDateString" sortProperty="checkInDate" class="sortable"/>
						<display:column title="Check Out" style="width:10%" sortable="true" property="checkOutDateString" sortProperty="checkOutDate" class="sortable"/>
						<display:column title="Booking Date" style="width:10%" sortable="true" property="bookingDateString" sortProperty="bookingDate" class="sortable"/>
						<display:column title="Canceled Date" style="width:10%" sortable="true" property="cancelDateString" sortProperty="bookingDate" class="sortable"/>
						<display:column title="Expired on" style="width:15%" sortable="true" property="expireDateString" sortProperty="expireDate" class="sortable"/>
						
						
						<display:column title="Total"  style="width:10%;text-align: right;" sortable="true" property="totalPrice" class="sortable"/>
						<display:column title="Payed"  style="width:10%;text-align: right;" sortable="true" property="paidAmount" class="sortable"/>
						
						<display:column title="Actions"  style="width:5%;text-align: right;" sortable="false"  class="sortable">
							<logic:equal value="<%=IConstants.IBooking.BOOKING_SATATUS_ON_REQUEST%>" name="i" property="status">
							<a href="../admin/repay.do?bookingId=<bean:write name='i' property='bookingCode'/>">Reserve Now</a>
							</logic:equal>
						</display:column>
											
						</display:table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
