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
            if( $.trim( $('#checkIn').val() ) == ""){
             $('#checkIn').datePicker();//.val(new Date().asString()).trigger('change');
             
            // var myDate=new Date();
			// myDate.setDate(myDate.getDate()+2);
			 $('#checkOut').datePicker();//.val(myDate.asString()).trigger('change');
			 
            }else{
             $('.date-pick').datePicker().trigger('change');
            }
            
            });
            
               // Open another browser window
	function MM_openBrWindow(theURL,winName,features) { //v2.0
  		window.open(theURL,winName,features);
	}	
            
</script>
<table width="100%">

<tr><td>
<table>

<html:form action="/admin/findSearch">
<html:hidden property="tjwToken"/>
<tr>
	<td>Hotel :</td>
	   <td >
		<html:select property="hotelId" onchange="this.form.action='./initSearch.do';this.form.submit();">
  	     <html:optionsCollection name="searchForm" property="allHotel" label="name" value="id" />
  	     <!-- <option value="-1" <logic:equal value="-1" property="hotelId" name="searchForm">selected="selected"</logic:equal> >please select</option>-->
		</html:select>
	  </td>
</tr>
<!--  tr>
<td>Room type:</td>

					<td>
						<html:select property="roomId" >
						   <logic:notEmpty name="searchForm" property="allRoom" >
								<html:optionsCollection property="allRoom" label="roomType.name" value="roomType.id" />
							</logic:notEmpty>
							<option value="-1" <logic:equal value="-1" property="roomId" name="searchForm">selected="selected"</logic:equal> >please select</option>
						</html:select>
					</td>
					
				
</tr-->
<tr>
 <td><label for="fromDate">Check In Date:</label></td>
 <td><html:text property="checkIn" styleId="checkIn" styleClass="date-pick" /></td>
</tr>

<tr>
 <td><label for="toDate">Check Out Date:</label></td>
 <td><html:text property="checkOut" styleId="checkOut" styleClass="date-pick" /></td>
</tr>
<tr>
<td colspan="2" align="left"><html:submit value="Search" /></td>
</tr>


</table></td></tr>

 <tr><td>
<display:table class="its" id="i" cellspacing="2" export="false"
	style="width:80%" name="<%=thisbean.getSearchResultsDTO().getRoomDTO()%>" sort="list"  pagesize="20"  requestURI="/admin/findSearch.do">
<display:column style="width:5%"  ><input type="radio" name="id" value="<bean:write name="i" property="id"/>" /></display:column>

<display:column title="Room Type" style="width:15%" sortable="true" property="roomType.name" class="sortable" />
<display:column title="Available Room " style="width:10%" sortable="true" property="roomAvailability.availableUnit" class="sortable"/>
<display:column title="Price " style="width:10%" sortable="true" property="roomSeasonalRate.totalCost" class="sortable"/>

</display:table>
  </td>
</tr>

<tr>
 <td colspan="2">&nbsp;</td>
</tr>
<tr>
 <td colspan="2">&nbsp;</td>
</tr>


<tr>
 <td colspan="2" ="top">&nbsp;</td>
</tr>
<tr>
 <td colspan="2"><label for="extraItem"><strong>Extra Items</strong></label></td>
</tr>
<tr>
 <td colspan="2">
<display:table class="its" id="i" cellspacing="0" export="false"
	style="width:80%" name="<%=thisbean.getSearchResultsDTO().getExtraItemDTO()%>" sort="list" pagesize="20"  requestURI="/admin/findSearch.do">
<display:column style="width:2%" sortable="true" class="sortable" >
	<input type="checkbox" name="extraItemId" value="<bean:write name="i" property="extraItem.id"/>" />
</display:column>
<display:column title="Item Name" style="width:5%" sortable="true" property="extraItem.name" class="sortable" />
<display:column title="Item Code" style="width:5%" sortable="true" property="extraItem.code" class="sortable" />
<display:column title="Price" style="width:5%" sortable="true" property="extraItem.cost" class="sortable" />
<display:column title="Detail" style="width:20%;vertical-align:top" sortable="true" property="extraItem.comments" class="sortable"/>
<display:column title="Comments" style="width:20%" sortable="true" class="sortable">
<textarea style="width:100%" name="commets" rows="3"></textarea>
</display:column>

</display:table>

  </td>
</tr>

<tr>
<td colspan="2"><input type="button" value="Book" onclick="this.form.action='./../admin/processSearch.do';this.form.submit();" ></td>

</tr>
</html:form>
</table>