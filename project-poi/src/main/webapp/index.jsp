<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="resources.messages" var="messagesBundle"/>

<html> 
<head>
<title>project-poi主页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"></script>
<c:set var="baseurl" value="${pageContext.request.contextPath}/"></c:set>

<script lang="text/javascript">
//启动加在项目
$(function(){
	var baseurl="${pageContext.request.contextPath}";
	//alert(baseurl);
	
});

//点击事件
function importForword(){
	//jquerySubByFId('importForm',importsubmit_callback,null);
	window.location.href="${baseurl}/excel/import/couponView.action";
	
}
function exportForword(){
	window.location.href="${baseurl}/excel/export/couponView.action";
	
}
</script>

</HEAD>
<BODY>
    <form id="importForm" name="importForm" action="${baseurl}/import/importSubmit.action" method="post" enctype="multipart/form-data">
	<TABLE border="0" cellSpacing="0" cellPadding="0" width="100%" bgColor="#c4d8ed">
		<TBODY>
			<TR>
				<TD>
					<TABLE class="toptable grid" border="1" cellSpacing="1" cellPadding="4" align="center">
						<TBODY>
							<TR>
								<TD colspan="2"  align="center" class="category">
									<a id="importForwordButton" href="javascript:void(0)" onclick="importForword()">跳转到导入操作页面</a>
									<a id="exportForwordButton" href="javascript:void(0)" onclick="exportForword()">跳转到导出操作页面</a>
									<a id="closebtn" href="javascript:void(0)" onclick="parent.closemodalwindow()">返回主页</a>
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

