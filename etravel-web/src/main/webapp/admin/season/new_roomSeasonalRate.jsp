<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.season.Season"%>
<%@page import="com.yd.etravel.domain.room.Room;"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<bean:define id="thisbean" name="roomSeasonalRateForm" type="com.yd.etravel.web.season.RoomSeasonalRateForm" />
<script type="text/javascript" charset="utf-8">
// Numbers only - (No of children, adults, passengers, rooms)

function isNumber(str) {
	pattern= /^[0-9]+$/
		if (!str.match(pattern)) return false
			else return true;
}

// Numbers with fullstop & comma only - (Prices)
function isDecimal(str) {
str=str.value;

	pattern= /^[0-9\.]+$/
		if (!str.match(pattern)){
		alert("Please enter valid number");
		document.location.reload();
		 return false
		 }else{
		  return true;
		  }
}
</script>

<html:form action="/admin/createRoomSeasonalRate" >
<html:hidden property="id"/> 
<table >

<tr>
<td>Hotel :</td>

					<td >
						<html:select property="hotelId" onchange="this.form.action='./forwardRoomSeasonalRate.do';this.form.submit();">
								<html:optionsCollection property="hotelList" label="name" value="id" />
								<option value="0" <logic:equal value="0" property="hotelId" name="roomSeasonalRateForm">selected="selected"</logic:equal> >please select</option>
						</html:select>
					</td>
					
</tr>

<tr>
<td>Room Type:</td>

					<td >
						<html:select property="roomId">
						 		<html:optionsCollection property="allRoom" label="roomType.name" value="id" />
								<option value="0" <logic:equal value="0" property="roomId" name="roomSeasonalRateForm">selected="selected"</logic:equal> >please select</option>

						</html:select>
					</td>
					
				</tr>
<tr>
<tr>
<td>Season :</td>

					<td >
						<html:select property="seasonId">
						 <html:optionsCollection property="allSeason" label="name" value="id" />
						 <option value="0" <logic:equal value="0" property="seasonId" name="roomSeasonalRateForm">selected="selected"</logic:equal> >please select</option>
						</html:select>
					</td>
					
				</tr>
				
<tr>

<tr><td>Rate:</td><td><html:text property="totalCost" size="10" maxlength="10" onkeyup="isDecimal(this);" /></td></tr>

<tr><td>Active:</td><td><html:checkbox property="active" /></td></tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td></tr>
<tr>
<td colspan="2" align="left"><html:submit value="Save" /></td>
</tr>
</table>
</html:form>

<display:table class="its" id="i" cellspacing="2" export="true"
	style="width:100%" name="<%=thisbean.getAllRoomSeasonalRate()%>" sort="list" pagesize="20"  requestURI="/admin/initRoomSeasonalRateCreate.do">
<display:column title="Hotel" style="width:25%" sortable="true" property="room.hotel.name" class="sortable"/>
<display:column title="Room Type" style="width:25%" sortable="true" property="room.roomType.name" class="sortable"/>
<display:column title="Season " style="width:25%" sortable="true" property="season.name" class="sortable"/>
<display:column title="Rate" style="width:15%;text-align: right;" sortable="true" property="totalCost" class="sortable"/>

<display:column title="Active" style="width:10%;text-align: center;" sortable="true" property="active" class="sortable" />
<display:column media="html" title="Actions" style="width:5%" sortable="true" class="sortable">
<a href="../admin/initRoomSeasonalRateEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/></a>
<a href="../admin/deleteRoomSeasonalRate.do?id=<bean:write name='i' property='id'/>"><img src="../images/deleteico.gif"/></a>

</display:column>
</display:table>
