<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="resources.messages" var="messagesBundle"/>

<html> 
<head>
<title>project-poi导入页</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<c:set var="baseurl" value="${pageContext.request.contextPath}/"></c:set>
<script type="text/javascript" src="${baseurl}js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${baseurl}js/ajaxfileupload.js"></script>

<script lang="text/javascript">
//启动加在项目
$(function(){
	var baseurl="${pageContext.request.contextPath}";
	//alert(baseurl);
	
});

//点击触发提交事件
function importSubmit(){
	//jquerySubByFId('importForm',importsubmit_callback,null);
	
	$("#importForm").submit();
	
}
</script>

</HEAD>
<BODY>
    <form id="importForm" name="importForm" action="${baseurl}/excel/import/couponSubmit.action" method="post" enctype="multipart/form-data">
	<TABLE border="0" cellSpacing="0" cellPadding="0" width="100%" bgColor="#c4d8ed">
		<TBODY>
			<TR>
				<TD width="100%">
					<TABLE cellSpacing="0" cellPadding="0" width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;POI信息导入</TD>
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
								<TD height="30" align="right">导入说明：</TD>
								<TD >
								1、导入文件为Excel 97-2003版本，文件扩展名为.xls，如果使用高版本的Excel请另存为Excel 97-2003版本。
								<br>2、点击 <a class="blue" href="${pageContext.request.contextPath}/template/template.xls"><u style="color: red;font-weight: 5;">导入信息模板</u></a> 下载，并按照说明录入药品信息。
								<br>3、导入文件内容填写完毕请在下方选择导入文件，点击 导入按钮。
								</TD>
							</TR>
							<TR>
								<TD height="30" align="right">选择导入文件</TD>
								<TD class=category>
								<input type="file" name="importFile" />
								</TD>
							</TR>
							<TR>
								<TD colspan="2"  align="center" class="category">
									<input type="submit" value="提交" />
									<a id="submitbtn" href="javascript:void(0)" onclick="importSubmit()">导入</a>
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
	
	<hr />
	<hr />
		<input id="fileToUpload" type="file" size="20" name="fileToUpload" />
		<button id="buttonUpload" >AJAX无刷新文件上传</button>
		<br/>
		<br/>
		<div id="pictureDiv"></div>
	<hr />
	<hr />
	<!-- src="http://www.baidu.com" -->
	<iframe src="#" width="950" height="880" scrolling="no"
		marginwidth="0" marginheight="0" border="0" frameborder="0"
		style="border: none;"></iframe>


</BODY>

<!-- 使用ajaxFileUpload.js插件实现ajax无刷新上传文件 -->
<script>  
jQuery(function(){   
	  $("#buttonUpload").click(function(){     
	     //加载图标   
	     /** $("#loading").ajaxStart(function(){
	        $(this).show();
	     }).ajaxComplete(function(){
	        $(this).hide();
	     });**/
	     
		var data = { name: 'my name', description: 'short description' };
		$.ajaxFileUpload({  
			             url:'${baseurl}/excel/fileUpload.action', //需要链接到服务器地址  
			             secureuri:false,
			             //data: data,
			             fileElementId:'fileToUpload',  //文件选择框的id属性  
			             dataType: 'json',        //服务器返回的格式，可以是json  
			             success: function (data, status) //相当于java中try语句块的用法  
				             {      
				                $("#pictureDiv").append("<img src=\""+data+"\" style=\"width:150px;height:80px;\" />");  
				                alert("OK");
				             },  
			             error: function (data, status, e) //相当于java中catch语句块的用法  
				             {  
				                $.each(data,function(i,n){  
				                    $("#pictureDiv").append("<span>图片加载失败！</span>");      
				                }); 
				                alert("error");
				             }  
			         });  
	         });  
        });  
</script>

</HTML>

