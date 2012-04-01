<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.domain.hotel.Hotel"%>
<%@page import="com.yd.etravel.domain.season.Season"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>

<bean:define id="thisbean" name="seasonForm" type="com.yd.etravel.web.season.SeasonForm" />

<script type="text/javascript" charset="utf-8">
            $(function(){
            if( $.trim( $('#fromDate').val() ) == ""){
             $('.date-pick').datePicker({startDate:'01/01/1996'}).val(new Date().asString()).trigger('change');
            }else{
             $('.date-pick').datePicker({startDate:'01/01/1996'}).trigger('change');
            }
            
            });
		</script>

<html:form action="/admin/createSeason">
<html:hidden property="id"/>
<table >
<tr>
<td>Season :</td>
<td><html:text property="name" size="20" maxlength="35" /></td></tr>

<tr>
<td>Hotel :</td>

					<td >
						<select  name="hotelIds"  >
						 <logic:iterate id="i" name="seasonForm" property="allHotel" type="com.yd.etravel.domain.hotel.Hotel">
						   <option value="<bean:write name='i' property='id'/>" <%=(session.getAttribute("hotelId").toString()).equals((((Hotel) i).getId().toString()))? "selected":""%>>
						   <bean:write name="i" property="name"/></option>
						 </logic:iterate>
						</select>
					</td>
					
				</tr>

<tr>
 <td><label for="fromDate">From Date:</label></td>

<td> <html:text property="fromDate" styleId="fromDate" styleClass="date-pick" /></td>

</tr>

<tr>
 <td><label for="toDate">To Date:</label></td>
 <td><html:text property="toDate" styleId="toDate" styleClass="date-pick" /></td>

</tr>

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
	style="width:100%" name="<%=thisbean.getAllSeason()%>" sort="list" pagesize="20"  requestURI="/admin/initSeasonCreate.do">
<display:column title="Name" style="width:15%" sortable="true" property="name" class="sortable" />
<display:column title="Hotel " style="width:10%" sortable="true" property="hotel.name" class="sortable"/>
<display:column title="From Date" style="width:10%" sortable="true" property="fromDateString" class="sortable" sortProperty="fromDate"/>
<display:column title="To Date" style="width:10%" sortable="true" property="toDateString" class="sortable" sortProperty="toDate"/>
<display:column title="Comments" style="width:15%" sortable="true" property="comments" class="sortable"/>
<display:column title="Active" style="width:5%" sortable="true" property="active" class="sortable" />
<display:column media="html" title="Actions" style="width:5%" sortable="true" class="sortable">
<a href="../admin/initSeasonEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/></a>
<a href="../admin/deleteSeason.do?id=<bean:write name='i' property='id'/>"><img src="../images/deleteico.gif"/></a>

</display:column>
</display:table>
