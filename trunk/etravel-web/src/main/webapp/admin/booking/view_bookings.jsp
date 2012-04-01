<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<display:table class="its" id="i"  cellspacing="2" export="true" requestURI="../admin/initViewBooking.do" defaultsort="2" 	style="width:100%" name='<%=session.getAttribute("bookingList")%>' sort="list" pagesize="50">
<display:column title="Reservation Id " style="width:15%" sortable="true" property="hotelBooking.booking.id" class="sortable" />
<display:column title="Status " style="width:15%" sortable="true" property="hotelBooking.booking.status" class="sortable" />

<display:column title="Hotel " style="width:15%" sortable="true" property="hotelBooking.hotel.name" class="sortable" />
<display:column title="Register Date  " style="width:15%" sortable="true" property="hotelBooking.booking.createdDate" class="sortable" />
<display:column title="Grand Total  " style="width:15%;text-align: right;" sortable="true" property="hotelBooking.booking.totalPrice" class="sortable" />
 
</display:table>
