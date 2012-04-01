<%@ page language="java"%>
<%@page import="java.math.BigDecimal"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>
<html>
	<body>
	<bean:define id="r" name="roomDTO" type="com.yd.etravel.domain.custom.search.RoomDTO"/>
	<bean:define id="s" name="searchRequestDTO" type="com.yd.etravel.domain.custom.search.SearchRequestDTO"/>
		
			<table width="100%" >
				<tr>
					<td align="left">
						<table width="50%"  >
							<html:form action="/admin/initBookingCreate">
							<html:hidden property="id" />
							<tr>
								<th colspan="2"> 
									General<br> 
								</th>
							</tr>
							<tr><td valign="top"><br></td><td valign="top"><br></td></tr><tr>
								<td>
									<strong>Hotel</strong>
								</td>
								<td>
									<bean:write name="roomDTO" property="hotel.name" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Check In Date</strong>
								</td>
								<td>
									<bean:write name="searchRequestDTO" property="checkInString" />
								</td>
							</tr>

							<tr>
								<td>
									<strong>Check Out Date</strong>
								</td>
								<td>
									<bean:write name="searchRequestDTO" property="checkOutString" />
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>No. of Nights</strong>
								</td>
								<td>
									<bean:write name="searchRequestDTO" property="noOfNights" />
								</td>
							</tr>

							<tr>
								<td>
									<strong>Room Type</strong>
								</td>
								<td>
									<bean:write name="roomDTO" property="roomType.name" />
								</td>
							</tr>
							<tr>
								<td>
									<strong>Max Guest</strong>
								</td>
								<td>
									<bean:write name="roomDTO" property="roomType.maxPassengers" />
								</td>
							</tr>
							<tr><td><strong>Price (per day)</strong></td>
								<td>
									<bean:write name="roomDTO"
										property="roomSeasonalRate.totalCost" />

								</td>
							</tr>
							<tr><td><strong>Price (for <bean:write name="searchRequestDTO" property="noOfNights" /> day(s))</strong></td>
								<td>
									<%= r.getRoomSeasonalRate().getTotalCost().multiply(new BigDecimal(s.getNoOfNights()))%></td>
							</tr>
							
							<tr>
								<td>
									<strong>Season</strong>
								</td>
								<td>
									<bean:write name="roomDTO"
										property="roomSeasonalRate.season.name"></bean:write>
								</td>
							</tr>
							
							<tr>
								<td>
									<strong>Available Rooms</strong>
								</td>
								<td>
									<bean:write name="roomDTO"
										property="roomAvailability.availableUnit"></bean:write>
								</td>
							</tr>
						
			
				
				<tr>
					<td colspan="2" align="left">
						<html:submit value="Continue" /></td>
					
				</tr>
				</html:form>
			</table>
			</td>
			</tr>
		


<tr>
 <td>
<display:table class="its" id="i" cellspacing="2" export="true"
	style="width:100%" name='<%=session.getAttribute("extraItem")%>' sort="list" pagesize="20"  >
<display:column title="Item Name" style="width:10%" sortable="true" property="name" class="sortable" />
<display:column title="Item Code" style="width:10%" sortable="true" property="code" class="sortable" />
<display:column title="Price" style="width:10%" sortable="true" property="cost" class="sortable" />
<display:column title="Detail" style="width:10%" sortable="true" property="comments" class="sortable"/>
<display:column title="Comments" style="width:10%" sortable="true" property="bookingComments" class="sortable"/>


</display:table>

  </td>
</tr>
</table>
</body>
</html>
