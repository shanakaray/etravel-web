<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page language="java"%>
<%@page import="com.yd.etravel.util.DateUtil"%>
<%@page
	import="com.yd.etravel.domain.custom.room.availability.RoomAvailabilityDTO"%>
<%@page import="com.yd.etravel.domain.custom.booking.BookingReportDTO"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<script type="text/javascript">
   $(function(){
            if( $.trim( $('#bookedtoString').val() ) == ""){
             $('#bookedFromString').datePicker({startDate:'01/01/1996'}).val(new Date().asString()).trigger('change');
             var myDate=new Date();
			 myDate.setDate(myDate.getDate()+14);
             $('#bookedtoString').datePicker({startDate:'01/01/1996'}).val(myDate.asString()).trigger('change');
            }else{
             $('.date-pick').datePicker({startDate:'01/01/1996'}).trigger('change');
            }
            
            });
    // Open another browser window
	function MM_openBrWindow(theURL,winName,features) { //v2.0
  		window.open(theURL,winName,features);
	}       
  </script>
	<body>
		<table width="100%" border="0">
			<bean:define id="thisbean" name="reservationSheetForm"
				type="com.yd.etravel.web.reservation.ReservationSheetForm" />
			<tbody>
				<tr>
					<td>
						<html:form action="/admin/findReservationSheet">
							<table width="50%" border="0">

								<tr>
									<td >
										Hotel
									</td>
									<td>
										<html:select property="hotelId"
											>
											<logic:notEmpty name="reservationSheetForm"
												property="hotelList">
												<html:optionsCollection property="hotelList" label="name"
													value="id" />
											</logic:notEmpty>
											<option value="-1"
												<!-- <logic:equal value="-1" property="hotelId" name="reservationSheetForm">selected="selected"</logic:equal>>
												please select
											</option-->
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										From
									</td>
									<td>
										<html:text styleId="bookedFromString" styleClass="date-pick"
											property="startDate" size="12" maxlength="12" readonly="true" />
									</td>
								</tr>
								<tr>
									<td>
										To
									</td>
									<td>
										<html:text styleId="bookedtoString" styleClass="date-pick" 
											property="endDate" size="12" maxlength="12" readonly="true" />
									</td>
								</tr>


								<tr>
									<td>
										<html:submit>Search</html:submit>
									</td>
									<td>
										<input type="button" onclick="cle()" value="Clear" />
									</td>
								</tr>


							</table>
						</html:form>

					</td>
				</tr>

				<tr>
					<td colspan="2">
						<div
							style="width: 1024px; height: 500px; overflow: auto; background-color: #ffffff;background-image:url(../images/mgFade.jpg);">
							
							<%if(!thisbean.getRoomAvailabilitySet().isEmpty()){%>
							<table border="1" bordercolor="black">
								<tr height="30px">
									<th>
										&nbsp;
									</th>
									<%
                    if(thisbean.getStartDateToDate()!=null){
                    Calendar c=Calendar.getInstance();
                    c.setTime(thisbean.getStartDateToDate());
                    c.getTimeInMillis();
                    
                    while(true){
                   
                    if(c.getTimeInMillis()>thisbean.getEndDateToDate().getTime()){
                    	break;
                    }else{
                     
                     %>
									<th><%=DateUtil.format(c.getTime())%></th>
									<% 
										}
										c.add(Calendar.DATE,1);
                                        c.getTimeInMillis();
											}
										}
									%>
								</tr>
								<%
									if (thisbean.getRoomAvailabilitySet() != null
											&& !thisbean.getRoomAvailabilitySet().isEmpty()) {
											Iterator iterator=thisbean.getRoomAvailabilitySet().iterator();
											String tmp="";
										while (iterator.hasNext()) {
											RoomAvailabilityDTO dto = (RoomAvailabilityDTO) iterator.next();
											for (int y = 0; y < dto.getUnit(); y++) {
								%>


								<tr height="25px" >
									<td style="background-color:<%=y%2==1?"#F4EDFA":"#A7A0AD"%>;"><strong><%=dto.getRoomTypeName().equals(tmp)?"":dto.getRoomTypeName()%></strong></td>
									<%  tmp=dto.getRoomTypeName();
										if (thisbean.getStartDateToDate() != null) {
														Calendar c = Calendar.getInstance();
														c.setTime(thisbean.getStartDateToDate());
														
														c.getTimeInMillis();
														long bookinid=0;
														while (true) {
															Calendar d = Calendar.getInstance();
															d.setTime(thisbean.getEndDateToDate());
															d.getTimeInMillis();

															if (c.getTimeInMillis() > d.getTimeInMillis()) {
																break;
															} else {
																
																boolean found = false;
																
																for (int b = 0; b < thisbean.getBookingList().size(); b++) {

																	BookingReportDTO bdto = (BookingReportDTO) thisbean.getBookingList().get(b);
																	
																	
																	
																	if (bdto.getCheckOutDate().after(
																			d.getTime())) {
																		bdto.setCheckOutDate(d.getTime());
																	}
																	if (bdto.getCheckInDate().before(
																			thisbean.getStartDateToDate())) {
																		bdto.setCheckInDate(thisbean.getStartDateToDate());
																	}
																	
																	if(bdto.getRoomTypeId().equals(dto.getRoomTypeId())
																			&& bdto.getNoOfRoom().intValue() > 0
																			&& (bdto.getCheckInDate().getTime() == c.getTimeInMillis())){
																	   bookinid=bdto.getBookingId().longValue();
																	}
																	
																	if (bdto.getRoomTypeId().equals(dto.getRoomTypeId())
																			&& bdto.getNoOfRoom().intValue() > 0
																			&& (((bdto.getCheckInDate().getTime() == c.getTimeInMillis())|| 
																					((bookinid==bdto.getBookingId()) && bdto.getCheckOutDate().getTime() == c.getTimeInMillis()))
																					
																					|| 
																							
																					
																					((bookinid==bdto.getBookingId()) &&
																					(c.getTime().getTime() > bdto.getCheckInDate().getTime() ) 
																					&& (c.getTime().getTime() < bdto.getCheckOutDate().getTime()))
																					
																					 ) )
																					
																					{

																		if ((bookinid==bdto.getBookingId())&& bdto.getCheckOutDate()
																				.getTime() == c
																				.getTimeInMillis()) {
																			Integer rc = bdto.getNoOfRoom();
																			rc = rc - 1;
																			bdto.setNoOfRoom(rc);
																			
																		}
																		found = true;
									%>

									<td style="<%=thisbean.getColur(bdto.getBookingCode())%>"><a href="javascript:MM_openBrWindow('../admin/viewBookingDetail.do?bookingId=<%=bdto.getBookingId()%>','','width=600,height=330,scrollbars=yes');" ><%=bdto.getBookingCode()%></a>
									<br/><%=bdto.getStatus()%>
									</td>
									<%  
										break;
																	}
																}
																
																
																if (!found) {
									%>

									<td style="background-color:<%=y%2==1?"#F4EDFA":"#A7A0AD"%>;">
										&nbsp;
									</td>
									<%
										}
										
										
										c.add(Calendar.DATE, 1);
										c.getTimeInMillis();
										
															}
														}
													}
									%>
								</tr>


								<%
									}
										}
									}
								%>
							</table><%}%>
						</div>


					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
