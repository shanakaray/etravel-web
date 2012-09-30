<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="displaytag" prefix="display"%>
<%@ taglib uri="struts-logic" prefix="logic"%>


<bean:define id="thisbean" name="imageForm"
	type="com.yd.etravel.web.content.ImageForm" />

<table width="100%">

	<tr>
		<td><html:form method="post" action="/admin/upload"
			enctype="multipart/form-data">
			<html:hidden property="pk" />
			<html:hidden property="object" />
			<table align="left" border="0" width="50%">
				<tr>
					<td>Image Name</td>
					<td><html:text property="name" size="20" maxlength="35" /></td>
				</tr>
				<tr>
					<td>Title</td>
					<td><html:text property="title" maxlength="35" /></td>
				</tr>

				<tr>
					<td>File</td>
					<td><html:file property="formFile" /></td>
				</tr>



				<tr>
					<td colspan="2" align="left"><html:submit value="Save" /></td>
				</tr>
			</table>
		</html:form></td>

		<div>
			<logic:iterate id="i" collection="<%= thisbean.getImages() %>" type="Long">
				<img src="image.img?id=<bean:write name="i"/>" width="200px"
					height="200px" />
			</logic:iterate>
	</div>

	</tr>
</table>

</body>
</html>