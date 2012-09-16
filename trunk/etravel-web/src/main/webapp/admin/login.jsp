<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html lang="true">
  <%--<link id="cssfile" rel="stylesheet" type="text/css" href="../css/styles.css" />--%>
  <link id="cssfile2" rel="stylesheet"  type="text/css" href="../css/AdminStyles.css"/>
  <head>
  <style type="text/css" >
body{
	margin:0;
	padding:0;
	background-image: url(../images/bg_loop.jpg);
	background-repeat: repeat-x;
}
body,td,th {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 11px;
}
.LoginBorder{
	position:absolute;
	top: 50%;
	left: 50%;
	width:712px;
	height:400px;
	margin-top: -200px;
	margin-left: -356px;
	border: 4px solid #7BA400;
	background-color: #99CC00;
}
  </style>
  <html:base />
  <title>User Login</title>
  </head>
  <body>
  <div class="LoginBorder">
  <html:form action="/admin/authanticate" method="post">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" >
<tr>
                    <td valign="bottom" style=" height:193px; background:#E2F3A7;" >
                    <table width="100%" border="0" cellspacing="10" cellpadding="0">
                      <tr>
                      	<td height="66">&nbsp;</td>
                        <td width="56px" class="CompanyLogo">&nbsp;</td>
                        <td class="CompanyHeading">Hotel Booking System</td>
                      </tr>
                    </table></td>
        </tr>
                  <tr>
                    <td class="lineBG" style="height:8px;"></td>
                  </tr>
                  <tr>
                    <td class="loginBottom"  align="right" valign="top" >
                    <table cellspacing="0" cellpadding="0" border="0" width="350px">
                <tr>
                          <td  class="loginHeader"><img src="../images/loginText.jpg" border="0"></td>
                        </tr>
                        <tr>
                          <td align="left" valign="top" >
                          <table width="70%" border="0" cellspacing="0" cellpadding="3">
                              <tr>
                                <td colspan="3" class="errorText">
                                
        <logic:present name="<%=Globals.ERROR_KEY%>">
        <html:messages name="<%=Globals.ERROR_KEY%>" id="msg">
          <strong>&#187;&nbsp;</strong><logic:present name="msg"><bean:write name="msg"/></logic:present><br>
        </html:messages>
        </logic:present>
                                
                                                             </td>
                              </tr>
                              <tr>
                                <td class="logintext">User Name</td>
                                <td class="logintextBlack"><html:text property="username" size="18" maxlength="25" styleClass="logintextBlack"/></td>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td class="logintext">Password</td>
                                <td class="logintextBlack"><html:password property="password" size="18"  maxlength="25"  styleClass="logintextBlack"/></td>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                                <td><table border="0" cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td><input name="RememberMe" type="checkbox" value=""></td>
                                      <td class="logintextSmall">Remember Me</td>
                                    </tr>
                                  </table></td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                                <td align="left"><%--<img src="../images/btnSubmit.jpg" border="0">--%>
                                  <html:submit styleId="button"></html:submit></td>
                                <td></td>
                              </tr>
                            </table></td>
                        </tr>
                    </table></td>
                  </tr>
                </table>
  </html:form>
  </div>
  </body>
</html:html>
