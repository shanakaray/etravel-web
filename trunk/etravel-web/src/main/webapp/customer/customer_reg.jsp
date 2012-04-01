<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
  <head>
   
  </head>
  
  <body>
    <table align="left" border="0">
			<html:form action="/customer/createCustomer">
			
				<tr>
					<td colspan="2">
					
					</td>
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
				
				<tr>
					<td>
						&nbsp;
						<html:submit value="Register" />
						&nbsp;
						<html:reset/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr height="110px">
					<td valign="bottom" colspan="2"><span style="color: #CC0000;" >&#8226;</span> Required fields.</td>
				</tr>
			</html:form>
		</table>
  </body>
</html>
