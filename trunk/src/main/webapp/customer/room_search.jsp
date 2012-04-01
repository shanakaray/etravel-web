<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.hotel.Hotel"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>


<bean:define id="thisbean" name="searchForm" type="com.yd.etravel.web.search.SearchForm" />

<script type="text/javascript" charset="utf-8">
            $(function(){
            if( $.trim( $('#fromDate').val() ) == ""){
             $('.date-pick').datePicker().val(new Date().asString()).trigger('change');
            }else{
             $('.date-pick').datePicker().trigger('change');
            }
            
            });
</script>

<html:form action="/admin/findSearch">
<html:hidden property="id"/>
<table >
<tr>
	<td>Hotel :</td>
	<td >
		<select  name="hotelIds"  >
  	   <logic:iterate id="i" name="searchForm" property="allHotel" type="com.yd.etravel.domain.hotel.Hotel">
    	<option value="<bean:write name='i' property='id'/>" <%=(session.getAttribute("hotelId").toString()).equals((((Hotel) i).getId().toString()))? "selected":""%>>
		<bean:write name="i" property="name"/></option>
		</logic:iterate>
		</select>
	  </td>
</tr>

<tr>
 <td><label for="fromDate">Check In Date:</label></td>
<td> <html:text property="checkIn" styleId="checkIn" styleClass="date-pick" /></td>
</tr>

<tr>
 <td><label for="toDate">Check Out Date:</label></td>
 <td><html:text property="checkOut" styleId="checkOut" styleClass="date-pick" /></td>
</tr>
<tr>
<td colspan="2" align="left"><html:submit value="Search" /></td>
</tr>
</table>
</html:form>

<display:table class="its" id="i" cellspacing="2" export="true"
	style="width:100%" name="<%=thisbean.getSearchResultsDTO().getRoomDTO()%>" sort="list" pagesize="20"  requestURI="/admin/initSearch.do">
<display:column title="Room Name" style="width:15%" sortable="true" property="room.name" class="sortable" />
<display:column title="Room Type" style="width:15%" sortable="true" property="roomType.name" class="sortable" />
<display:column title="Number of rooms " style="width:10%" sortable="true" property="roomAvailability.unit" class="sortable"/>
<display:column media="html" title="Actions" style="width:5%" sortable="true" class="sortable">
<a href="../admin/processSearch.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/ alt="Select"></a>
</display:column>
</display:table>

