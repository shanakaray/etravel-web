<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="displaytag" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
    $().ready(function() {  
     $('#addbtn').click(function() {  
      return !$('#select1 option:selected').remove().appendTo('#select2');  
     });  
     $('#removebtn').click(function() {  
      return !$('#select2 option:selected').remove().appendTo('#select1');  
     });
     
     $('#form1').submit(function() {  
 		$('#select2 option').each(function(i) {  
   		$(this).attr("selected", "selected");  
  	 	});  
 	  });
 	  
     $('#select2 option:selected').removeAttr("selected"); 
     $('#select1 option:selected').removeAttr("selected"); 
    });  
</script>
	<head>
		<meta http-equiv="Content-Type"
			content="text/html; charset=ISO-8859-1">
		<title>Assign User Role Functions</title>
	</head>
	<body>
		<html:form action="/admin/saveFunction" styleId="form1" >
			<table>
				<tr>
					<td>
						User Role
					</td>
					
					<td colspan="2">
						<html:select property="roleId" name="functionForm"
							onchange="this.form.action='initFunction.do';this.form.submit();">
							<html:optionsCollection property="allRoles" label="name"
								value="id" name="functionForm" />
						</html:select>
					</td>
				<tr> 
					<td valign="top">
					  All Functions 
					</td>
					<td></td>
					<td valign="top">
					 Assigned Functions 
					</td>
					
				<tr>
					<td>
						<html:select property="allFunctionIds" name="functionForm"
							multiple="true" style="width:350;height:300" styleId="select1">
							<html:optionsCollection property="remainFuctionList" label="key"
								value="id" name="functionForm" />
						</html:select>
					</td>
					<td><html:button property="Add" styleId="addbtn">&gt;&gt;</html:button><br>
					    <html:button property="Remove" styleId="removebtn">&lt;&lt;</html:button></td>
					<td>
						<html:select property="functionIds" name="functionForm"
							multiple="true" style="width:350;height:300" styleId="select2">
							<html:optionsCollection property="selectedFuctionList" label="key"
								value="id" name="functionForm"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						
					</td>
					<td></td>
				</tr>
				<tr>
					<td>
						<html:submit>Save</html:submit>
					</td>
					<td></td>
				</tr>
			</table>
		</html:form>
	</body>
</html>