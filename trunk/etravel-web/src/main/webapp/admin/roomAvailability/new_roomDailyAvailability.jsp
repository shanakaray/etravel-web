<%@ page language="java"%>
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="com.yd.etravel.domain.room.availability.RoomDailyAvailability"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>
<link id="cssfile" rel="stylesheet" type="text/css"
		href="../../css/styles.css" />
<display:table class="its" id="i" export="true" defaultsort="3" style="width:100%" name='<%=session.getAttribute("allRoomDailyAvailability")%>' sort="list" pagesize="50">
<display:column title="Hotel" style="width:10%" sortable="true" property="hotelName" class="sortable" />
<display:column title="Room Type" style="width:10%" sortable="true" property="roomTypeName" class="sortable" />
<display:column title="Date" style="width:10%" sortable="true" property="dateString" class="sortable" sortProperty="date" />
<display:column title="Allocated Room" style="width:15%" sortable="true" property="allocatedUnit" class="sortable" />
<display:column title="Available Room" style="width:15%" sortable="true" property="availableUnit" class="sortable" />
<display:column title="Active" style="width:10%" sortable="true" property="active" class="sortable" ><br></display:column>
</display:table>
<td align="right"><a href="javascript:close();"><img src="../../images/close_yel.gif" alt="Close" width="50" height="14" border="0" /></a></td>