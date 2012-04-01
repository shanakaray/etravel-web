<%@ page language="java"%>
<%@page import="com.yd.etravel.util.IConstants"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="displaytag" prefix="display"%>
<script type="text/javascript">
 $(function() {
    $('#chkpw').click(
    function() {
     if(this.checked){
     	$('#pwone').removeAttr("disabled");
        $('#pwtwo').removeAttr("disabled");
     }else{
		$('#pwone').val('').attr("disabled","disabled");
        $('#pwtwo').val('').attr("disabled","disabled");     
     }
    }
    );


  });
  
  
</script>
   <bean:define id="thisbean" name="userForm"
	type="com.yd.etravel.web.user.UserForm" />      
		
	<table width="100%">
	<tr>
		<td>
			
		<table align="left" border="0">
			<html:form action="/admin/createUser">
			<html:hidden property="id"/>
				
				<tr>
					<td>
						&nbsp;First Name
						<br>
					</td>
					<td>
						 <html:text property="firstName" maxlength="50" size="20" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Last Name
						<br>
					</td>
					<td>
						<html:text property="lastName" maxlength="50" size="20" />
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
						<html:text property="contact" maxlength="30" size="20" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;E-Mail
					</td>
					<td>
						<html:text property="email" maxlength="30" size="20" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;User Name
					</td>
					<td>
					    <logic:notEqual value="admin" name="userForm" property="name">
						 <html:text property="name" maxlength="20" size="20" />
						</logic:notEqual>
						<logic:equal value="admin" name="userForm" property="name">
						 <html:text property="name" maxlength="20" size="20" readonly="true" />
						</logic:equal>
						
					</td>
				</tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td colspan="2"><fieldset><table>
				<tr>
					<td colspan="2">
						<html:checkbox property="passwordReset" styleId="chkpw" >Set&nbsp;Password</html:checkbox>
					</td>
					
				</tr>
				
				<tr>
					<td>
						&nbsp;Password
					</td>
					<td>
						<html:password property="pw" maxlength="20" size="20" styleId="pwone" />
					</td>
				</tr>
				<tr>
					<td>
						&nbsp;Re-Password
					</td>
					<td>
						<html:password property="repw" maxlength="20" size="20" styleId="pwtwo" />
					</td>
				</tr>
				
				</table></fieldset></td></tr>
				<tr><td colspan="2">&nbsp;</td></tr>
				<tr><td>&nbsp;</td>
					<td>
						&nbsp;Access Roles
					</td>
					
				</tr>
				
				<tr><td>&nbsp;</td>
					<td >
					
					    <logic:notEqual value="admin" name="userForm" property="name">
						 <html:select multiple="true" property="roleIds" size="10">
						 <html:optionsCollection property="allRoles" label="name" value="id" />
						</html:select>
						</logic:notEqual>
						
						<logic:equal value="admin" name="userForm" property="name" >
						 <html:select disabled="true" multiple="true" property="roleIds" size="10" >
						 <html:optionsCollection property="allRoles" label="name" value="id" />
						 </html:select>
						 <input type="hidden" name="roleIds" value="<%=IConstants.IUserRoles.SYSADMIN_ROLE_ID%>"/>
						</logic:equal>
					
						
					</td>
					
				</tr>
				
				<tr><td>
							Active:
						</td>
						<td>
						<logic:notEqual value="admin" name="userForm" property="name">
						 <html:checkbox property="active" />
						</logic:notEqual>
						
						<logic:equal value="admin" name="userForm" property="name" >
						 <html:checkbox property="active" disabled="true" />
						 <html:hidden property="active" value="true"/>
						</logic:equal>
							
						</td>
					</tr>
				
				<tr>
					<td>
						&nbsp;
						<html:submit value="Save" />
						&nbsp;
						<html:reset/>
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</html:form>
		</table>
	</td>
	</tr>
	            <tr>    <td>
						&nbsp;
					</td> </tr>
	<tr><td>
	
		<display:table class="its" id="i" cellspacing="2" export="true"
				style="width:100%" name="<%=thisbean.getAllUsers()%>" sort="list"
				pagesize="20" requestURI="/admin/initUserCreate.do">
				
				
				<display:column title="Name" style="width:20%" sortable="true"
					class="sortable"><bean:write name='i' property='firstName'/> <bean:write name='i' property='lastName'/></display:column>
				
				<display:column property="name" title="User Name" style="width:20%" sortable="true"
					class="sortable"></display:column>
					
				<display:column property="contact" title="Contact" style="width:20%" sortable="true"
					class="sortable"></display:column>
					
				<display:column property="email" title="E-mail" style="width:20%" sortable="true"
					class="sortable"></display:column>
					
				<display:column property="active" title="Active" style="width:20%" sortable="true"
					class="sortable"></display:column>				
					
				<display:column media="html" title="Actions" style="width:10%"
					sortable="true" class="sortable">
					<a
						href="../admin/initUserEdit.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/editico.gif" />
					</a>
					
					
					<logic:notEqual value="admin" name="i" property="name">
					<a
						href="../admin/deleteUser.do?id=<bean:write name='i' property='id'/>"><img
							src="../images/deleteico.gif" />
					</a>
					</logic:notEqual>
					
				</display:column>
			</display:table>
</td></tr></table>
