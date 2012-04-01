<%@ page language="java"%>
<%@page import="com.yd.etravel.util.IConstants"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>
<%@ taglib uri="user-access" prefix="user"%>
<script type="text/javascript">
function calroomPrice(){
 var  price=$('#roomPrice').val();
 var nights=$('#nights').val();
 var noofrooms=$('#noOfRooms').val();
 var itemcost=$('#itemcost').val();
 
 
 if($('#singleNight').attr('checked')){
 	$('#roomPriceDiv').html(roundUp(new Number(price*noofrooms)));
 	$('#totalCost').html(roundUp (new Number(price*noofrooms) + new Number(itemcost) ));
 }else{
 	$('#roomPriceDiv').html(roundUp (new Number(price*nights*noofrooms)));
 	$('#totalCost').html(roundUp (new Number(price*nights*noofrooms) + new Number(itemcost)) );
 }
}


function roundUp(original){
return  new Number(Math.round(original*100) / 100).toFixed(2);
}


$(document).ready(function() {calroomPrice();});

</script>

<bean:define id="thisbean" name="bookingForm" type="com.yd.etravel.web.booking.BookingForm" />
<html:form action="/admin/createBooking">
<html:hidden property="id"/>
<html:hidden name="roomDTO"	property="roomSeasonalRate.totalCost" styleId="roomPrice"/>
<html:hidden name="searchRequestDTO" property="noOfNights" styleId="nights"/>
<input id="itemcost" type="hidden" name="itemcost" value="<bean:write name='itemscost' scope='session' />"/>
<table border="0" width="80%">



<logic:notPresent name="<%=IConstants.IUser.USER_PROFILE%>">

<tr>
<td valign="top">
<fieldset>
<legend>New Customer</legend>
<table>
<tr>
					<td>
						&nbsp;First Name
						<br>
					</td>
					<td>
						<html:text property="firstName" maxlength="50" size="25"  /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Last Name
						<br>
					</td>
					<td>
						<html:text property="lastName" maxlength="50" size="25" /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				
				
				
				<tr>
					<td>
						&nbsp;Address
					</td>
					<td>
						<html:textarea property="address" />
					</td>
				</tr>

				<tr>
					<td>
						&nbsp;Contact
					</td>
					<td>
						<html:text property="contact" maxlength="30" size="20"/><span style="color: #CC0000;">&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;E-Mail
					</td>
					<td>
						<html:text property="email" maxlength="30" size="25"/><span style="color: #CC0000;">&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;User Name
					</td>
					<td>
						<html:text property="cusUsername" maxlength="20" size="20" /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Password
					</td>
					<td>
						<html:password property="cusPassword" maxlength="30" size="20" /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Re-Password
					</td>
					<td>
						<html:password property="repassword" maxlength="20" size="20"  /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
</table>				
</fieldset>

</td>

<td valign="top">

<fieldset>
<legend>Existing User</legend>
<table>
					<tr>
					<td colspan="2"><html:checkbox property="registerd" value="true">Already Registered</html:checkbox></td>
					</tr>
					<tr>
					<td>
						User Name
					</td>
					<td>
						<html:text property="RUsername" maxlength="40" size="20"  /><span style="color: #CC0000;" >&#8226;</span>
					</td>
					</tr>
					<tr>
					<td>
						Password
					</td>
					<td>
						<html:password property="RPassword" maxlength="25" size="20"  /><span style="color: #CC0000;" >&#8226;</span>
					</td>
					</tr>
</table></fieldset>



				</td>

</tr>

<tr>
<td>&nbsp;</td>
<td>&nbsp;</td></tr>
<tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td></tr>
<tr>				
</logic:notPresent>
<logic:present name="<%=IConstants.IUser.USER_PROFILE%>">
<user:access property="sys.logic.booking.changebookinguser">
<tr>
<td valign="top">
<fieldset>
<legend>New Customer</legend>
<table>
<tr>
	<td colspan="2"><html:checkbox property="newCustomer" value="true">New Customer</html:checkbox></td>
</tr>
<tr>
					<td>
						&nbsp;First Name
						<br>
					</td>
					<td>
						<html:text property="firstName" maxlength="50" size="25"  /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Last Name
						<br>
					</td>
					<td>
						<html:text property="lastName" maxlength="50" size="25" /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				
				
				
				<tr>
					<td>
						&nbsp;Address
					</td>
					<td>
						<html:textarea property="address" />
					</td>
				</tr>

				<tr>
					<td>
						&nbsp;Contact
					</td>
					<td>
						<html:text property="contact" maxlength="30" size="20"/><span style="color: #CC0000;">&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;E-Mail
					</td>
					<td>
						<html:text property="email" maxlength="30" size="25"/><span style="color: #CC0000;">&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;User Name
					</td>
					<td>
						<html:text property="cusUsername" maxlength="20" size="20" /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Password
					</td>
					<td>
						<html:password property="cusPassword" maxlength="30" size="20" /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Re-Password
					</td>
					<td>
						<html:password property="repassword" maxlength="20" size="20"  /><span style="color: #CC0000;" >&#8226;</span>
					</td>
				</tr>
</table>				
</fieldset>

</td>
</tr>
</user:access>
</logic:present>


<tr>
<td colspan="2">
<fieldset>
<legend>Booking Information</legend>
<table>
<tr>
<td> No of Rooms :</td>
<td><input type="hidden" name="guest" value="<logic:present name='<%=IConstants.IUser.USER_PROFILE%>'>false</logic:present><logic:notPresent name='<%=IConstants.IUser.USER_PROFILE%>'>true</logic:notPresent>"> 
<html:text onkeyup="calroomPrice();" property="noOfRoom" size="10" maxlength="10" styleId="noOfRooms"/><span style="color: #CC0000;" >&#8226;</span></td></tr>

<tr>
<td> Single Night Pay :</td>
<td><html:checkbox value="true"   property="singleNight" styleId="singleNight" onclick="calroomPrice();" /></td></tr>

<tr>
<td> No of Guest :</td>
<td><html:text property="totalPax" size="10" maxlength="10" /><span style="color: #CC0000;" >&#8226;</span></td></tr>

<tr><td>Payment Method
						</td>
						<td colspan="2">
							<html:select property="paymentMethodId">
								<option value="0" <logic:equal value="0" property="paymentMethodId" name="bookingForm">selected="selected"</logic:equal> >please select</option>
								<option value="1" <logic:equal value="1" property="paymentMethodId" name="bookingForm">selected="selected"</logic:equal> >Online</option>
								<user:access property="sys.logic.paymenttype.cash"><option value="2" <logic:equal value="2" property="paymentMethodId" name="bookingForm">selected="selected"</logic:equal> >Cash</option></user:access>
								<user:access property="sys.logic.paymenttype.onrequest"><option value="3" <logic:equal value="3" property="paymentMethodId" name="bookingForm">selected="selected"</logic:equal> >On Request</option></user:access>
								<user:access property="sys.logic.paymenttype.later"><option value="3" <logic:equal value="3" property="paymentMethodId" name="bookingForm">selected="selected"</logic:equal> >Pay Later!</option></user:access>
							</html:select><span style="color: #CC0000;" >&#8226;</span>
						</td>
					</tr>

		<user:access property="sys.logic.paymenttype.onrequest">
				<tr>
				<td>Agent </td>
 
					<td >
						<html:select property="agentId"> 
						<option value="-1" <logic:equal value="-1" property="agentId" name="bookingForm">selected="selected"</logic:equal> >please select</option>
						<html:optionsCollection property="allAgent" label="name" value="id" />
						</html:select>
					</td>
				</tr>
		</user:access>
		
<user:access property="sys.logic.booking.changebookinguser">
<tr>
	<td>Customer </td>
	<td >
		<html:select property="bookedUserId">
	    <option value="-1" <logic:equal value="-1" property="bookedUserId" name="bookingForm">selected="selected"</logic:equal> >please select</option>
		
		<logic:notEmpty property="allCustomers"  name="bookingForm">
			<logic:iterate  name="bookingForm" id="u" property="allCustomers" type="com.yd.etravel.domain.user.User" >
			<option value="<bean:write name="u" property="id"/>" <logic:equal value="u.id" name="bookingForm" property="bookedUserId" >selected="selected"</logic:equal> >
			<bean:write name="u" property="name"/>&nbsp;[<bean:write name="u" property="firstName"/>&nbsp;<bean:write name="u" property="lastName"/>]
			</option>
			</logic:iterate>		
		</logic:notEmpty>
		</html:select>
	</td>
</tr>
</user:access>
		

<tr><td>Comments:</td><td><html:textarea property="comments" cols="30" rows="3" /></td></tr>

<tr><td>Room Price:</td><td><div id="roomPriceDiv">0.00</div> </td></tr>
<tr><td>Items Price:</td><td><div><bean:write name="itemscost" scope="session" /></div></td></tr>
<tr><th>Total Price:</th><th><div id="totalCost"><bean:write name="itemscost" scope="session" /></div></th></tr>


<tr>
<td>&nbsp;</td>
<td>&nbsp;</td></tr>
<tr>
<td colspan="2" align="left"><html:submit value="Booking" /></td>
</tr>

</table>
</fieldset>
</td><td></td></tr>

<tr height="50px">
	<td valign="bottom" colspan="2"><span style="color: #CC0000;" >&#8226;</span> Required fields.</td>
</tr>
</table>

</html:form>

<display:table class="its" id="i" cellspacing="2" export="true"
	style="width:100%" name="<%=thisbean.getRoomDTO()%>" sort="list" pagesize="20"  requestURI="/admin/initSearch.do">
<display:column title="Hotel" style="width:15%" sortable="true" property="hotel.name" class="sortable" />
<display:column title="Room Type" style="width:15%" sortable="true" property="roomType.name" class="sortable" />
<display:column title="Available rooms " style="width:10%" sortable="true" property="roomAvailability.availableUnit" class="sortable"/>
<display:column title="Max passengers" style="width:15%" sortable="true" property="roomType.maxPassengers" class="sortable" />
<display:column title="Price" style="width:15%" sortable="true" property="roomSeasonalRate.totalCost" class="sortable" />
</display:table>

