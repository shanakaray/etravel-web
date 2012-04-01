<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<bean:define id="thisbean" name="paymentForm" type="com.yd.etravel.web.booking.PaymentForm" />


<html:form action="/admin/createPayment">
<html:hidden property="id"/>
<table >

<tr>
<td>Card Number</td>
<td><html:text property="cardNumber" size="10" maxlength="10" /></td></tr>
<tr>
<td> Expiry Date</td>
<td><html:text property="expirDate" size="10" maxlength="10" /></td></tr>
<tr>
<td> Security Code </td>

<td><html:text property="securityCode" size="10" maxlength="10" /></td></tr>
<tr>
<td> Address </td>
<td><html:text property="address" size="10" maxlength="10" /></td></tr>

<tr>
<td> City </td>
<td><html:text property="city" size="10" maxlength="10" /></td></tr>

<tr>
<td>Zip Code</td>
<td><html:text property="zipCode" size="10" maxlength="10" /></td></tr>

<tr>
<td> Country </td>
<td><html:text property="country" size="10" maxlength="10" /></td></tr>

<tr>
<td> Amount </td>
<td><html:text property="amount" size="10" maxlength="10" /></td></tr>


<tr>
<td>&nbsp;</td>
<td>&nbsp;</td></tr>
<tr>
<td colspan="2" align="left"><html:submit value="Pay" /></td>
</tr>
</table>
</html:form>

<!--
<display:table class="its" id="i" cellspacing="2" export="true"
	style="width:100%" name="" sort="list" pagesize="20"  requestURI="/admin/initSearch.do">
<display:column title="Hotel" style="width:15%" sortable="true" property="hotel.name" class="sortable" />
<display:column title="Room Type" style="width:15%" sortable="true" property="roomType.name" class="sortable" />
<display:column title="Available rooms " style="width:10%" sortable="true" property="roomAvailability.availableUnit" class="sortable"/>
<display:column title="Max passengers" style="width:15%" sortable="true" property="occupancy.totalPax" class="sortable" />
<display:column title="Adult" style="width:15%" sortable="true" property="roomSeasonalRate.adultCost" class="sortable" />
<display:column title="Child" style="width:15%" sortable="true" property="roomSeasonalRate.childCost" class="sortable" />
<display:column title="Infant" style="width:15%" sortable="true" property="roomSeasonalRate.infantCost" class="sortable" />
</display:table>-->

