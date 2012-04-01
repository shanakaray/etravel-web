<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<html>
	<head>
	</head>

	<body>
		<html:form action="/admin/ResetPw">
			<html:hidden property="userId" />
			<table width="35%">
				<tr>
					<td>
						<fieldset>
							<table>
								<tr>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td>
										Old Password
									</td>
									<td>
										<html:password property="oldPw" size="20" maxlength="20"/>
									</td>
								</tr>
								<tr>
									<td>
										New Password
									</td>
									<td>
										<html:password property="newPw" size="20" maxlength="20"/>
									</td>
								</tr>
								<tr>
									<td>
										Re-New Password
									</td>
									<td>
										<html:password property="newRepPw" size="20" maxlength="20" />
									</td>
								</tr>
								<tr>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td colspan="2">
										<html:submit property="Save">Save</html:submit>
									</td>
								</tr>
							</table>
						</fieldset>
					</td>
				</tr>
			</table>
		</html:form>
	</body>

</html>
