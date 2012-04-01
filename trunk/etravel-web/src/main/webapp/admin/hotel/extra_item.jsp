<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
	<body>

        <bean:define id="thisbean" name="extraForm" type="com.yd.etravel.web.extraitem.ExtraItemForm" />		
		<table width="100%" border="0">

			<tbody>
				<tr>
					<td>
					    <html:form action="/admin/createExtra">
					    <html:hidden property="id"/>
						<table width="100%" border="0">
							<tbody>
								<tr>
									<td>
										Item Name 
									</td>
									<td>
										<html:text property="name" size="20" maxlength="35" />
									</td>
								</tr>
								<tr>
									<td>
										Item Code
									</td>
									<td>
										<html:text property="code" size="20" maxlength="20" />
									</td>
								</tr>
								<tr>
									<td>
										Price
									</td>
									<td>
										<html:text property="cost" size="20" maxlength="35" />
									</td>
								</tr>
								<tr>
									<td>
										Currency
									</td>
									<td>
										<html:select property="currency">
											<html:options name="extraForm" property="currencyList"/>
										</html:select>
									</td>
								</tr>
								<tr>
									<td>
										Comment
									</td>
									<td>
										<html:textarea property="comments" cols="30"></html:textarea>
									</td>
								</tr>
								<tr>
								    <td>
										&nbsp;
									</td>
									<td >
										Hotels
									</td>
								</tr>
								<tr>
								    <td>
										&nbsp;
									</td>
									<td>
										<html:select size="6" multiple="true" property="hotelId" >
											<html:optionsCollection property="hotelList" label="name"
												value="id" />
										</html:select>
									</td>
								</tr>

								<tr>
									<td><html:submit>Save</html:submit></td>
									<td>&nbsp;</td>
								</tr>

							</tbody>
						</table>
						</html:form>

					</td>
				</tr>
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<display:table class="its" id="i" cellspacing="2" export="true"	style="width:100%" name="<%=thisbean.getExtraItemList()%>" sort="list" pagesize="20"  requestURI="/admin/initExtraCreate.do">
						<display:column title="Name" style="width:25%" sortable="true" property="name" class="sortable" />
						<display:column title="Code" style="width:25%" sortable="true" property="code" class="sortable"/>
						<display:column title="Price" style="width:25%" sortable="true"  class="sortable">
						    <bean:write name="i" property="cost"/> <bean:write name="i" property="currency"/>
						</display:column>
						
						<display:column media="html" title="Actions" style="width:5%" sortable="true" class="sortable">
						<a href="../admin/initExtraEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif"/ alt="Edit"></a>
						<a
						href="../admin/deleteExtra.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/deleteico.gif" />
					   </a>
						</display:column>
						
						</display:table>
					</td>
				</tr>
			</tbody>
		</table>
	</body>
</html>
