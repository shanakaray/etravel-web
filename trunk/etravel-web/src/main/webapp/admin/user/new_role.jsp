<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="displaytag" prefix="display"%>
<bean:define id="thisbean" name="roleForm" type="com.yd.etravel.web.user.RoleForm" />
<html:form action="/admin/createRole">
<html:hidden property="id"/>
<table  >
<tr>
<td>Role:</td><td><html:text property="name" size="20" maxlength="25" /></td></tr>
<tr><td>Description:</td><td><html:textarea property="discription" cols="30" rows="3" /></td></tr>
<tr><td>Active:</td><td><html:checkbox property="active" /></td></tr>
<tr><td colspan="2"><html:submit value="Save" /></td></tr>


</table>
</html:form>
<display:table class="its" id="i" cellspacing="2" export="true"  cellpadding="3" 
	style="width:100%;border:#f3f3f3 1px solid; border-collapse:collapse;" name="<%=thisbean.getAllRoles()%>" sort="list" pagesize="20"  requestURI="/admin/initRoleCreate.do">
<display:column title="Name" style="width:20%" sortable="true" property="name" class="sortable"/>
<display:column title="Description" style="width:60%" sortable="true" property="code" class="sortable"/>
<display:column title="Active" style="width:10%" sortable="true" property="active" class="sortable"/>
<display:column media="html" title="Actions" style="width:10%" sortable="true" class="sortable">
<a href="../admin/initRoleEdit.do?id=<bean:write name='i' property='id'/>"><img src="../images/editico.gif" title="Edit"/></a>
<a href="../admin/deleteRole.do?id=<bean:write name='i' property='id'/>"><img src="../images/deleteico.gif" title="Delete"/></a>
</display:column>

</display:table>
