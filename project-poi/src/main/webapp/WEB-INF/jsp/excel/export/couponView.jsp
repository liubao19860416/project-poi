<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="resources.messages" var="messagesBundle"/>

<html> 
<head>
<title>project-poi导出页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<c:set var="baseurl" value="${pageContext.request.contextPath}/"></c:set>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"></script>

<script lang="text/javascript">
//启动加在项目
$(function(){
	var baseurl="${pageContext.request.contextPath}";
	//alert(baseurl);
	
});

//点击触发提交事件
function exportSubmit2(){
	
	$("#exportForm").submit();
}

function exportSubmit(){
	
    //var queryString = $('#exportForm').serialize();
	//document.exportForm.action = "${baseurl}/excel/export/couponSubmit.action?"+queryString,
	//document.exportForm.submit();
    //var formData =JSON.stringify(param);
    //contentType:"application/vnd.ms-excel",
	//$("#exportForm").attr("action","${baseurl}/excel/export/couponSubmit.action");
	
    var param={};
    param.userName= $("#userName").val();
    param.password=$("#password").val();

    console.log(JSON.stringify(param));

 $.ajax({
     "type": "POST",
     "url":"${baseurl}/excel/export/couponSubmit.action",
     "data":JSON.stringify(param),
     "dataType": "json",
     "contentType": "application/json",
     "success": function(data) {
     	//window.alert(data);
     	var str="<a href=\""+data+"\" target=\"_blank\">点击下载文件</a>";
     	var str2="&nbsp&nbsp&nbsp;<input type='button' value='关闭' onclick='window.close()'><br/>";
     	//window.showModalDialog(data.downloadURL,"dialogWidth=200px;dialogHeight=100px");
     	//window.location.href="{pageContext.request.contextPath}/storeAction_listAll.action";
     	var myWindow = window.open(data, '下载窗口', 'width=200,height=100,menubar=no,resizable=no,toolbar=no,location=no,status=no');
		myWindow.document.write(str);
		myWindow.document.write(str2);
		//window.location.reload();
     	//window.open(data);
     }
 });
 
}
</script>

</HEAD>
<BODY>
    <form id="exportForm" name="exportForm" action="${baseurl}/excel/export/couponSubmit.action" method="post">
	<TABLE border="0" cellSpacing="0" cellPadding="0" width="100%" bgColor="#c4d8ed">
		<TBODY>
			<TR>
				<TD  width="100%">
					<TABLE cellSpacing="0" cellPadding="0" width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;POI信息导出</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD>
					<TABLE class="toptable grid" border="1" cellSpacing="1" cellPadding="4" align="center">
						<TBODY>
							<TR>
								<TD colspan="2"  align="center" class="category">
									用户姓名：
									<input type="text" id="userName" name="userName" value="" />
								</TD>
								<TD colspan="2"  align="center" class="category">
									用户年龄：
									<input type="password" id="password" name="password" value="" />
								</TD>
								<TD colspan="2"  align="center" class="category">
									<!-- <input type="submit" value="提交" /> -->
									<a id="submitbtn" href="javascript:void(0)" onclick="exportSubmit()">导出</a>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
	</form>
</BODY>
</HTML>

