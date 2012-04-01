<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.room.availability.RoomAvailability"%>
<%@page import="com.yd.etravel.domain.room.Room"%>
<%@page import="com.yd.etravel.domain.occupancy.Occupancy"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<bean:define id="thisbean" name="roomAvailabilityForm" type="com.yd.etravel.web.roomavailability.RoomAvailabilityForm" />
<script type="text/javascript" charset="utf-8">
            $(function(){
            if( $.trim( $('#fromDate').val() ) == ""){
             $('.date-pick').datePicker({startDate:'01/01/1996'}).val(new Date().asString()).trigger('change');
            }else{
             $('.date-pick').datePicker({startDate:'01/01/1996'}).trigger('change');
            }
            
            });
            
            // Open another browser window
	function MM_openBrWindow(theURL,winName,features) { //v2.0
  		window.open(theURL,winName,features);
	}	
		</script>

<html:form action="/admin/createRoomAvailability">
<html:hidden property="id"/>
<table >

<tr><td>Hotel</td><td>     <html:select property="hotelId" onchange="this.form.action='./avilhotelOnChange.do';this.form.submit();">
                            <logic:notEmpty name="roomAvailabilityForm" property="hotelList" >
								<html:optionsCollection property="hotelList" label="name" value="id" />
							</logic:notEmpty>
							<option value="-1" <logic:equal value="-1" property="hotelId" name="roomAvailabilityForm">selected="selected"</logic:equal> >please select</option>
							</html:select></td></tr>

<tr>
<td>Room type:</td>

					<td>
						<html:select property="roomId" onchange="this.form.action='./roomOnChange.do';this.form.submit();">
						   <logic:notEmpty name="roomAvailabilityForm" property="allRoom" >
								<html:optionsCollection property="allRoom" label="roomType.name" value="id" />
							</logic:notEmpty>
							<option value="-1" <logic:equal value="-1" property="roomId" name="roomAvailabilityForm">selected="selected"</logic:equal> >please select</option>
						</html:select>
					</td>
					
				
<tr>
<tr>
 <td><label for="fromDate">From Date:</label></td>
<td> 
<html:text property="fromDate" styleId="fromDate" styleClass="date-pick" />

</td>
</tr>

<tr>
 <td><label for="toDate">To Date:</label></td>
 <td><html:text property="toDate" styleId="toDate" styleClass="date-pick" /></td>
</tr>
<tr><td>Unit:</td><td><html:text property="unit" size="20" maxlength="35" /></td></tr>

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
	style="width:100%" name="<%=thisbean.getAllRoomAvailability()%>" sort="list" pagesize="20"  requestURI="/admin/initRoomAvailabilityCreate.do">
<display:column title="Hotel" style="width:15%" sortable="true" property="hotelName" class="sortable" />
<display:column title="Room Type" style="width:15%" sortable="true" property="roomTypeName" class="sortable" />

<display:column title="From Date" style="width:15%" sortable="true" property="fromDateString" class="sortable" sortProperty="fromDate"/>
<display:column title="To Date" style="width:15%" sortable="true" property="toDateString" class="sortable" sortProperty="toDate"/>
<display:column title="Allocated Room" style="width:10%" sortable="true" property="unit" class="sortable" />
<display:column title="Active" style="width:10%" sortable="true" property="active" class="sortable" />
<display:column media="html" title="Actions" style="width:10%" sortable="true" class="sortable">
<a href="javascript:MM_openBrWindow('../admin/findRoomDailyAvailability.do?id=<bean:write name='i' property='id'/>','','width=600,height=600,scrollbars=yes')"><img src="../images/icons/enable/info_20_en.gif"/></a>
<a href="../admin/initRoomAvailabilityEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/></a>
<a href="../admin/deleteRoomAvailability.do?id=<bean:write name='i' property='id'/>"><img src="../images/deleteico.gif"/></a>

</display:column>
</display:table>

<!-- onchange="this.form.action='./hotelOnChange.do';this.form.submit();"-->