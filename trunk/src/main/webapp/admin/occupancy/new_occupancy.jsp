<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.roomType.RoomType"%>
<%@page import="com.yd.etravel.domain.occupancy.Occupancy"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<bean:define id="thisbean" name="occupancyForm" type="com.yd.etravel.web.occupancy.OccupancyForm" />

<script type="text/javascript" charset="utf-8">
// Numbers only - (No of children, adults, passengers, rooms)

function isNumber(str) {
str=str.value;
	pattern= /^[0-9]+$/
		if (!str.match(pattern)){
		alert("Please enter valid number");
		document.location.reload();
		 return false
		 }else{
		  return true;
		  }
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

<html:form action="/admin/createOccupancy">
<html:hidden property="id"/>
<table >
<tr>
<td>Occupancy :</td>
<td><html:text property="name" size="20" maxlength="35" /></td></tr>

<tr>
<td>Room Type :</td>

					<td >
						<select  name="roomTypeids"  >
						 <logic:iterate id="i" name="occupancyForm" property="allRoomType" type="com.yd.etravel.domain.roomType.RoomType">
						   <option value="<bean:write name='i' property='id'/>" <%=(session.getAttribute("roomTypeId").toString()).equals((((RoomType) i).getId().toString()))? "selected":""%>>
						   <bean:write name="i" property="name"/></option>
						 </logic:iterate>
						</select>
					</td>
					
				</tr>

<tr><td>Adult:</td><td><html:text property="adult" size="10" maxlength="10" onkeyup="isNumber(this);"/></td></tr>
<tr><td>Child:</td><td><html:text property="child" size="10" maxlength="10" onkeyup="isNumber(this);"/></td></tr>
<tr><td>Infant:</td><td><html:text property="infant" size="10" maxlength="10" onkeyup="isNumber(this);"/></td></tr>

<tr><td>Comments:</td><td><html:textarea property="comments" cols="30" rows="3" /></td></tr>

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
	style="width:100%" name="<%=thisbean.getAllOccupancy()%>" sort="list" pagesize="20"  requestURI="/admin/initOccupancyCreate.do">
<display:column title="Name" style="width:15%" sortable="true" property="name" class="sortable" />
<display:column title="Room Type" style="width:10%" sortable="true" property="roomType.name" class="sortable"/>
<display:column title="Adult" style="width:10%" sortable="true" property="adult" class="sortable"/>
<display:column title="Child" style="width:10%" sortable="true" property="child" class="sortable"/>
<display:column title="Infant" style="width:10%" sortable="true" property="infant" class="sortable"/>
<display:column title="Total" style="width:10%" sortable="true" property="totalPax" class="sortable"/>
<display:column title="Comments" style="width:15%" sortable="true" property="comments" class="sortable"/>
<display:column title="Active" style="width:5%" sortable="true" property="active" class="sortable" />
<display:column media="html" title="Actions" style="width:5%" sortable="true" class="sortable">
<a href="../admin/initOccupancyEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/></a>
<a href="../admin/deleteOccupancy.do?id=<bean:write name='i' property='id'/>"><img src="../images/deleteico.gif"/></a>

</display:column>
</display:table>
