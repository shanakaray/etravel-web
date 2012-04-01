<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@page import="com.yd.etravel.domain.room.Room"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<body>
<bean:define id="thisbean" name="roomForm" type="com.yd.etravel.web.room.RoomForm" />
<html:form action="/admin/createRoom">
<html:hidden property="id"/>

<table width="100%">
<tr><td>
<table>
<tr><td>Hotel</td><td><html:select property="hotelId" onchange="this.form.action='./roomHotelOnChange.do';this.form.submit();">
								<html:optionsCollection property="hotelList" label="name" value="id" />
								<option value="0" <logic:equal value="0" property="hotelId" name="roomForm">selected="selected"</logic:equal> >please select</option>
							</html:select></td></tr>




							
<tr><td>Room Type</td><td><html:select property="roomTypeId">
								<html:optionsCollection property="roomTypeList" label="name" value="id" />
								<option value="0" <logic:equal value="0" property="roomTypeId" name="roomForm">selected="selected"</logic:equal> >please select</option>
								
							</html:select></td></tr>
							
<tr><td>Number of Rooms</td><td><html:text property="noOfRooms" maxlength="6" size="6"/></td></tr>

<tr><td>&nbsp;</td><td>&nbsp;</td></tr>								

<tr><td><html:submit>Save</html:submit></td><td>&nbsp;</td></tr>	
</table>
</td></tr>
<tr><td>
<display:table class="its" id="i" cellspacing="2" export="true"
				style="width:100%" name="<%=thisbean.getRoomList()%>" sort="list"
				pagesize="50" requestURI="/admin/initRoomCreate.do">
				
			
				
				<display:column title="Hotel Name" style="width:30%" sortable="true"
					class="sortable"  ><%=thisbean.getHotelName(((Room)i).getHotel().getId())%></display:column>
												
				<display:column title="Room Type" style="width:30%" sortable="true"
					class="sortable" ><%=thisbean.getTypeName( ((Room)i).getRoomType().getId()) %></display:column>	
				
				<display:column title="No of rooms" style="width:30%" sortable="true"
					class="sortable" property="noOfRoom" ></display:column>	
					
								
				<display:column media="html" title="Actions" style="width:10%"
					sortable="true" class="sortable">
					<a
						href="../admin/initRoomEdit.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/editico.gif" />
					</a>
					<a
						href="../admin/deleteRoom.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/deleteico.gif" />
					</a>
				</display:column>
			</display:table>
</td></tr>
</table>


</html:form>
</body>
</html>