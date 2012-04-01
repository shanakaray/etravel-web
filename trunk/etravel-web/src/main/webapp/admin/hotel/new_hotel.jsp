<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="displaytag" prefix="display"%>
<%@ taglib uri="struts-logic" prefix="logic"%>


<bean:define id="thisbean" name="hotelForm"
	type="com.yd.etravel.web.hotel.HotelForm" />

<table width="100%">

	<tr>
		<td>
			<html:form action="/admin/createHotel">
				<html:hidden property="id"/>
				<table align="left" border="0" width="50%">
					<tr>
						<td>
							Hotel Name
						</td>
						<td>
							<html:text property="name" size="20" maxlength="35" />
						</td>
					</tr>
					<tr>
						<td>
							Address
						</td>
						<td>
							<html:textarea property="address" cols="35" rows="3" />
						</td>
					</tr>

					<tr>
						<td>
							Country
						</td>
						<td>
							<select name="country">
								<option value="Sri Lanka">
									Sri Lanka
								</option>
							</select>
						</td>
					</tr>
					<tr>
					<tr>
						<td>
							City
						</td>
						<td>
							<html:text property="city" size="20" maxlength="35" />
						</td>
					</tr>
					<tr>

						<td>
							Contact
						</td>
						<td>
							<html:text property="contact" size="20" maxlength="35" />
						</td>
					</tr>
					<tr>
						<td>
							Email
						</td>
						<td>
							<html:text property="email" size="20" maxlength="35" />
						</td>
					</tr>
					<tr>
						<td>
							Rating
						</td>
						<td>
							<html:text property="rating" size="5" maxlength="1" />
						</td>
					</tr>
					<tr>
						<td>
							Super user
						</td>
						<td>
							<html:select property="superUserId" multiple="true">
								<html:optionsCollection property="adminList" label="name"
									value="id" />
							</html:select>
						</td>
					</tr>
					<tr>
					<tr>
						<td>
							Active:
						</td>
						<td>
							<html:checkbox property="active" />
						</td>
					</tr>
					
					<tr>
						<td colspan="2" align="left">
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
				style="width:100%" name="<%=thisbean.getHotelList()%>" sort="list"
				pagesize="20" requestURI="/admin/initHotelCreate.do">
				<display:column title="Name" style="width:20%" sortable="true"
					property="name" class="sortable" />
				<display:column title="Address" style="width:40%" sortable="true"
					property="address" class="sortable" />
				<display:column title="Contact" style="width:10%" sortable="true"
					property="contact" class="sortable" />
				<display:column title="E-Mail" style="width:10%" sortable="true"
					property="email" class="sortable" />
				<display:column title="Active" style="width:10%" sortable="true"
					property="active" class="sortable" />
				<display:column media="html" title="Actions" style="width:10%"
					sortable="true" class="sortable">
					<a
						href="../admin/initHotelEdit.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/editico.gif" />
					</a>
					<a
						href="../admin/deleteHotel.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/deleteico.gif" />
					</a>
				</display:column>
			</display:table>
		</td>
	</tr>
</table>





