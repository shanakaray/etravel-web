<%@ page language="java"%>
<%@page import="org.apache.struts.Globals"%>
<%@page import="com.yd.etravel.util.IConstants.ICommon"%>
<%@page import="com.yd.etravel.util.IConstants"%>
<%@ taglib uri="struts-bean" prefix="bean"%>
<%@ taglib uri="struts-html" prefix="html"%>
<%@ taglib uri="struts-nested" prefix="nested"%>
<%@ taglib uri="struts-logic" prefix="logic"%>
<%@ taglib uri="struts-tiles" prefix="tiles"%>
<%@ taglib uri="displaytag" prefix="display"%>
<%@ taglib uri="msg-info" prefix="msg"%>
<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Cache-Control", "no-cache");
%>
<html>
<script type="text/javascript" src="../js/jquery/jquery.js"></script>
<script type="text/javascript" src="../js/jquery/wymeditor/jquery.wymeditor.pack.js"></script>
<script src="../js/jquery/jquery.popup.js"></script>
<script type="text/javascript" src="../js/jquery/date.js"></script>
<!--[if IE]><script type="text/javascript" src="../js/jquery/jquery.bgiframe.min.js"></script><![endif]-->
<!-- jquery.datePicker.js -->
<script type="text/javascript" src="../js/jquery/jquery.datePicker.js"></script>
<link id="cssfile" rel="stylesheet" type="text/css"	href="../css/styles.css" />
<link href="../css/tv.css" rel="stylesheet" type="text/css" />
<link href="../css/AdminStyles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
 $(function() {
 $("#popErrors #ok").bind("click", function() {
        $("#screenInputMask").hide();
        $("#popErrors").hide();
        
 });
 });

$(document).ready(function() {
<logic:present name="<%=Globals.ERROR_KEY%>">
  showErrors();
</logic:present>

<logic:present name="<%=IConstants.ICommon.INFO_MSG_KEY%>">
	<logic:notEmpty name="<%=IConstants.ICommon.INFO_MSG_KEY%>">
		$("#infomsg").fadeIn("slow",function() {
		 setTimeout(function(){$("#infomsg").fadeOut("slow");},4000);
		 });
	</logic:notEmpty>
</logic:present>
});

function showErrors(){ 
$("#screenInputMask").css("height", $(document).height()).css("width", $(document).width()).show();
$("#popErrors").show();
$("#popErrors #ok").focus();
}
</script>

<style type="text/css" >
body{
	margin:0;
	padding:0;
	background-image: url(../images/bg_loop.jpg);
	background-repeat: repeat-x;
}

</style>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="height:100%;">
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
    <td align="center" valign="top"><table width="95%" border="0" cellspacing="0" cellpadding="0"  class="mainBorder">
        <tr>
          <td align="left" valign="top"><div style="width:100%;" class="templateBody">
              <table width="100%"  border="0" cellspacing="0" cellpadding="0" >
                <tr >
                  <td style="width:95%; background-color:#6d6d6d ; height:55px;" align="left" valign="top">
                  
                  <tiles:insert attribute="header" ignore="true" /></td>                  
                </tr>                
                <tr>
                 <td align="left" valign="top" class="menuText"><tiles:insert attribute="menu" ignore="true" /></td>
                </tr>
                 <tr>
                 <td align="left"  valign="top" class="templateBG">
                 
                 <div  style="height:100%;background-color:transparent; ">
                 
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="3" height="16px" align="left"><div id='infomsg'  class='info' style='display: none;background:#0278c3;height:16px;'>
                    	<msg:info/></div>
                    </td>
                  </tr>
                  
                  <tr height="45px">
                    <td class="pageHeading" colspan="3"  ><tiles:getAsString name="title" ignore="true" /></td>
                  </tr>
                
                  <tr>
                    <td width="1%">&nbsp;</td>
                    <td width="94%" class="bodyText" height="500px" valign="top"><tiles:insert attribute="body" ignore="true" /></td>
                    <td width="5%">&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td></td>
                    <td>&nbsp;</td>
                  </tr>
                </table>
              
                 
                 </div>
                 
                 </td>
                </tr>            
                
              </table>
            </div>
            <div style="position:relative; direction:rtl; width:520px;  float:right; height:20px; padding-top:2px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:xx-small;" align="right"  ></div></td>

        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
</table>
<div id="screenInputMask" class="inputMask"></div>
<div id="popErrors" class="centered" style="display: none;">
  <table width="100%" cellpadding="0" cellspacing="0" border="0">
  <tr class="errorText">
  <th align="left" width="25" scope="col"><img src="../images/imgErrrorSmall.gif" border="0"></th>
  <th align="left" scope="col" width="100%"><strong>Errors</strong></th>
  </tr>
   <%-- <th  >  <br></th>--%>
    <tbody id="errorBody" >
      <logic:present name="<%=Globals.ERROR_KEY%>">
        <html:messages name="<%=Globals.ERROR_KEY%>" id="msg">
          <tr><td colspan="2"><strong>&#187;&nbsp;</strong><logic:present name="msg"><bean:write name="msg"/></logic:present></td></tr>
        </html:messages>
      </logic:present>
   
    <tr>
      <td colspan="2"><br></td>
    </tr>
    <tr>
      <td colspan="2"><input type="button" id="ok" value="Ok" />
        <br></td>
    </tr></tbody>
  </table>
</div>

</body>
</html>
