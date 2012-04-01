<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.yd.etravel.service.util.IUserProfile"%>
<%@page import="com.yd.etravel.util.IConstants.IUser"%>
<%@page import="com.yd.etravel.util.IConstants"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<script type="text/javascript" src="../js/jquery/jquery.droppy.js"></script>
<link href="../css/AdminStyles.css" rel="stylesheet" type="text/css" />
<style>
/* Basic code - don't modify */
#nav {
	display: block;
	margin: 0;
	padding: 0;
	position: relative;
	font-family: Arial,Helvetica, sans-serif;
}

#nav li {
	display: block;
	list-style: none;
	margin: 0;
	padding: 0;
	float: right;
	position: relative;
	font-family: Arial,Helvetica, sans-serif;
}

#nav a {
	display: block;
	font-family: Arial,Helvetica, sans-serif;
}

#nav ul {
	display: none;
	position: absolute;
	left: 0;
	margin: 0;
	padding: 0;
	font-family: Arial, Helvetica, sans-serif;
}

* html #nav ul {
	line-height: 0;
}  /* IE6 "fix" */
#nav ul a {
	zoom: 0;
}  /* IE6/7 fix */
#nav ul li {
	float: none;
}

#nav ul ul {
	top: 0;
}

/* Essentials - configure this */
#nav ul {
	width: 150px;
}

#nav ul ul {
	left: 150px;
}

/* Everything else is theming */
#nav {
	background-color: #75766e;
	height: 24px;
	font-family: Arial,Helvetica, sans-serif;
}

#nav * :hover {
	background-color: none;
}

#nav a {
	border-right: 1px solid white;
	color: white;
	font-size: 11px;
	padding: 6px;
	line-height: 1;
	font-weight:bolder;
	font-family: Arial, Helvetica, sans-serif;
}
 
#nav li.hover a {
	background-color: #cac192;
}

#nav ul {
	top: 25px;
}

#nav ul li a {
	background-color: #cac192;
}

#nav ul a.hover {
	background-color: #75766e;
}

#nav ul a {
	border-bottom: 1px solid white;
	border-right: none;
	opacity: 0.9;
	filter: alpha(opacity = 90%);
}
/* #nav ul a { border-bottom: none; } - I also needed this for IE6/7 */
</style>
<script type='text/javascript'>
//  $(function() {
//    $('#nav').droppy({speed: 100});
//  });
</script>

<table align="center" width="100%" class="menuText" cellpadding="0"
	cellspacing="0" border="0">

	<tr align="center">
		<td width="30%" bgcolor="#75766e">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				bgcolor="#75766e">
				<tr>
					<td width="90px;">
						&nbsp;
					</td>
					<td class="greetingText" width="90">
						Welcome :
					</td>
					<td class="menuTextBold">
						<logic:present name="<%=IConstants.IUser.USER_PROFILE%>">
							<bean:write name="<%=IConstants.IUser.USER_PROFILE%>"
								property="firstname" />
							<bean:write name="<%=IConstants.IUser.USER_PROFILE%>"
								property="lastname" />
						</logic:present>
						<logic:notPresent name="<%=IConstants.IUser.USER_PROFILE%>">
                         Guest
                         </logic:notPresent>
					</td>
				</tr>
			</table>
		</td>
		

	</tr>
</table>



