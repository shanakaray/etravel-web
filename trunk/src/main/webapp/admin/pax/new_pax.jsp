<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.yd.etravel.domain.hotel.Pax"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<bean:define id="thisbean" name="paxForm"
	type="com.yd.etravel.web.pax.PaxFrom" />
<table width="100%">
	<tr>
		<td>
		     <html:form action="/admin/createPax">
				<html:hidden property="id"/>
				<table align="left" border="0" width="50%">
					<tr>
						<td>
							Hotel
						</td>
						<td colspan="2">
							<html:select property="hotelId" onchange="this.form.action='./hotelOnChange.do';this.form.submit();">
								<html:optionsCollection property="hotelList" label="name" value="id" />
								<option value="0" <logic:equal value="0" property="hotelId" name="paxForm">selected="selected"</logic:equal> >please select</option>
								
							</html:select>
						</td>
					</tr>
										
					<tr>
						<td>
							Infant Age (Min - Max)
						</td>
						<td>
							<html:text property="infantMin" size="3" maxlength="3" />
						</td>
						<td>
							<html:text property="infantMax" size="3" maxlength="3" />
						</td>
					</tr>
					
					<tr>
						<td>
							Child Age (Min - Max)
						</td>
						<td>
							<html:text property="childMin" size="3" maxlength="3" />
						</td>
						<td>
							<html:text property="childMax" size="3" maxlength="3" />
						</td>
					</tr>
					
					<tr>
						<td>
							Adult Age (Min - Max)
						</td>
						<td>
							<html:text property="adultMin" size="3" maxlength="3" />
						</td>
						<td>
							<html:text property="adultMax" size="3" maxlength="3" />
						</td>
					</tr>
					
					
					<tr>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
					</tr>
					<tr>
						<td colspan="3" align="left">
							<html:submit value="Save" />
						</td>
					</tr>
				</table>
			</html:form>
		</td>
	</tr>
	<tr>
		<td>
		<display:table class="its" id="i" cellspacing="2" export="true"
				style="width:100%" name="<%=thisbean.getPaxList()%>" sort="list"
				pagesize="20" requestURI="/admin/initPaxCreate.do">
				<display:column title="Hotel Name" style="width:20%" sortable="true"
					class="sortable"  ><%=thisbean.getHotelName(((Pax)i).getHotel().getId())%></display:column>
												
				<display:column title="Infant" style="width:10%" sortable="true"
					class="sortable" sortProperty="infantMin" ><%=((Pax)i).getInfantMin()+"-"+((Pax)i).getInfantMax() %></display:column>	
				
				<display:column title="Child" style="width:10%" sortable="true"
					class="sortable" sortProperty="childMin" ><%=((Pax)i).getChildMin()+"-"+((Pax)i).getChildMax() %></display:column>	
					
				<display:column title="Adult" style="width:10%" sortable="true"
					class="sortable"  sortProperty="adultMin" ><%=((Pax)i).getAdultMin()+"-"+((Pax)i).getAdultMax() %></display:column>	
				
				<display:column media="html" title="Actions" style="width:10%"
					sortable="true" class="sortable">
					<a
						href="../admin/initPaxEdit.do?id=<bean:write name='i' property='id'/>&hotelId=<bean:write name='i' property='hotel.id'/>"><img
							src="../images/editico.gif" />
					</a>
					<a
						href="../admin/deletePax.do?id=<bean:write name='i' property='id'/>&hotelId=<bean:write name='i' property='hotel.id'/>"><img
							src="../images/deleteico.gif" />
					</a>
				</display:column>
			</display:table>
		</td>
	</tr>	
</table>
</body>
</html>