<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.roomtype.RoomType;"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<bean:define id="thisbean" name="roomTypeForm" type="com.yd.etravel.web.roomtype.RoomTypeForm" />


<html:form action="/admin/createRoomType">
<html:hidden property="id"/>
<table >
<tr>
<td>Room Type :</td>
<td><html:text property="name" size="20" maxlength="35" /></td></tr>

<tr>
<td>Maximum Passengers :</td>
<td><html:text property="maxPassengers" size="10" maxlength="10" /></td></tr>

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
	style="width:100%" name="<%=thisbean.getAllRoomType()%>" sort="list" pagesize="20"  requestURI="/admin/initRoomTypeCreate.do">
<display:column title="Room Type " style="width:30%" sortable="true" property="name" class="sortable" />
<display:column title="Comments" style="width:30%" sortable="true" property="comments" class="sortable"/>
<display:column title="Maximum Passengers" style="width:25%" sortable="true" property="maxPassengers" class="sortable"/>
<display:column title="Active" style="width:10%" sortable="true" property="active" class="sortable" />
<display:column media="html" title="Actions" style="width:5%" sortable="true" class="sortable">
<a href="../admin/initRoomTypeEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/></a>
<a href="../admin/deleteRoomType.do?id=<bean:write name='i' property='id'/>"><img src="../images/deleteico.gif"/></a>

</display:column>
</display:table>
