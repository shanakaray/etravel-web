<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.hotel.Hotel"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>




<html:form action="/admin/findSearch">
<html:hidden property="id"/>
<table width="488" height="493">

<tr>
 <td><label for="name" >Hotel</label></td>
 <td><bean:write name="roomDTO" property="hotel.name"></bean:write></td>
</tr>

<tr>
 <td><label for="checkIn" >Check In Date</label></td>
 <td><bean:write name="searchRequestDTO" property="checkIn"></bean:write></td>
</tr>

<tr>
 <td><label for="checkOut" >Check Out Date</label></td>
 <td><bean:write name="searchRequestDTO" property="checkOut"></bean:write></td>
</tr>




<tr>
 <td><label for="name" >Room Type</label></td>
 <td><bean:write name="roomDTO" property="roomType.name"></bean:write></td>
</tr>



<!-- <tr>
 <td><label for="Occupancy">Occupancy</label></td>
 <td><label for="adult">Adult<br><br></label><bean:write name="roomDTO" property="occupancy.adult"></bean:write></td>
 <td><br></td>
 <td><label for="child">Child<br><br></label><bean:write name="roomDTO" property="occupancy.child"></bean:write></td>
 <td><br></td>
 <td><label for="infant">Infant<br><br></label><bean:write name="roomDTO" property="occupancy.infant"></bean:write></td>
 <td><br></td>
 <td><label for="totalPax">Total<br><br></label><bean:write name="roomDTO" property="occupancy.totalPax"></bean:write></td>
</tr> -->

<tr>
 <td><label for="toDate" >Season<br><br></label></td>
 <td><bean:write name="roomDTO" property="roomSeasonalRate.season.name"></bean:write></td>
</tr>



<tr>
 <td><label for="RoomPrice">Room Price</label></td>

 <td><label for="childCost">Adult<br><br></label><bean:write name="roomDTO" property="roomSeasonalRate.adultCost"></bean:write></td>
 <td><br></td>
  <td><label for="childCost">Child<br><br></label><bean:write name="roomDTO" property="roomSeasonalRate.childCost"></bean:write></td>
 <td><br></td>
 <td><label for="infantCost">Infant<br><br></label><bean:write name="roomDTO" property="roomSeasonalRate.infantCost"></bean:write></td>
 <td><br></td>
 <td><label for="totalCost">Total<br><br></label><bean:write name="roomDTO" property="roomSeasonalRate.totalCost"></bean:write></td>
</tr>

<tr>
 <td><label for="unit" >Allocated Room</label></td>
 <td><bean:write name="roomDTO" property="roomAvailability.unit"></bean:write></td>
</tr>

<tr>
 <td><label for="availableUnit" >Available Room</label></td>
 <td><bean:write name="roomDTO" property="roomAvailability.availableUnit"></bean:write></td>
</tr>
<tr>
<td colspan="2" align="left"><html:button value="Continue" property=""/></td>
</tr>
</table><br>
</html:form>



