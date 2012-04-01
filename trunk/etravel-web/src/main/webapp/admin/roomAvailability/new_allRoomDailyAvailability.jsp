<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<display:table class="its" id="i"  cellspacing="2" export="true" requestURI="../admin/findAllRoomDailyAvailability.do" defaultsort="3" 	style="width:100%" name='<%=session.getAttribute("allRoomDailyAvailability")%>' sort="list" pagesize="50">
<display:column title="Hotel" style="width:10%" sortable="true" property="hotelName" class="sortable" />
<display:column title="Room Type" style="width:10%" sortable="true" property="roomTypeName" class="sortable" />
<display:column title="Date" style="width:10%" sortable="true" property="dateString" class="sortable" sortProperty="date" />
<display:column title="Allocated Room" style="width:15%" sortable="true" property="allocatedUnit" class="sortable" />
<display:column title="Availabal Room" style="width:15%" sortable="true" property="availableUnit" class="sortable" />
<display:column title="Active" style="width:10%" sortable="true" property="active" class="sortable" ><br></display:column>
</display:table>
