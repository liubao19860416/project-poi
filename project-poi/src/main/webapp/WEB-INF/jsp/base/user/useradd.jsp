<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html>
<head>
<title>用户添加</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<SCRIPT type="text/javascript">
$(function (){
    /******表单校验*************/
    //表单校验初始方法
	$.formValidator.initConfig({
		formID : "sysusereditform",
		theme : "Default",
		onError : function(msg, obj, errorlist) {
			//alert(msg);
		}
	});
	//用户账号
	$("#sysuser_userid").formValidator({
		onShow : "",
		onCorrect:"&nbsp;"
	}).inputValidator({
		min : 1,
		max : 20,
		onError : "请输入用户账号(最长10个字符)"
	});
});
	
	function sysusersave(){
		//$.formValidator.pageIsValid()对表单中的内容全部校验一遍
		if($.formValidator.pageIsValid()){
			 //将表单数据提交至useraddsubmit.action
			 //使用ajax执行post提交,使用jquery的from提交组件来完成
			 
			//使用jquery的ajax from提交，指定from的id和回调方法，提交的url使用提from中的action
			jquerySubByFId('sysusereditform',sysusersave_callback,null,"json");
		}
	}
	
	function sysusersave_callback(value){
		/* //alert(value.result);
		if(value.resultInfo.type==1){//成功了
			$.messager.alert('提示信息',value.resultInfo.message,'success');
		}else if(value.resultInfo.type==0){
			$.messager.alert('提示信息',value.resultInfo.message,'error');
		} */
		message_alert(value);
		//成功了刷新页面
		if(value.resultInfo.type==TYPE_RESULT_SUCCESS){//如果操作成功执行删除操作
			
			parent.sysuserquery();//刷新父页面
			parent.closemodalwindow();//关闭窗口
			
		}
	}
	
</SCRIPT>

</head>
<body>
<form id="sysusereditform" action="${baseurl}user/useraddsubmit.action" method="post">
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
		bgColor=#c4d8ed>
		<TBODY>
			<TR>
				<TD background=images/r_0.gif width="100%">
					<TABLE cellSpacing=0 cellPadding=0 width="100%">
						<TBODY>
							<TR>
								<TD>&nbsp;系统用户信息</TD>
								<TD align=right>&nbsp;</TD>
							</TR>
						</TBODY>
					</TABLE>
				</TD>
			</TR>
			<TR>
				<TD>
					<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
						align=center>
						<TBODY>

							<TR>
								<TD height=30 width="15%" align=right>用户账号：</TD>
								<TD class=category width="35%">
									<div>
										<input type="text" id="sysuser_userid" name="sysuser.userid" />
									</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
									<div id="sysuser_useridTip"></div>
								</TD>
								<TD height=30 width="15%" align=right>用户名称：</TD>
								<TD class=category width="35%">
									<div>
										<input type="text" id="sysuser_username"
											name="sysuser.username" />
									</div>
									<div id="sysuser_usernameTip"></div>
								</TD>
							</TR>
							<TR>
							    <TD height=30 width="15%" align=right>用户密码：</TD>
								<TD class=category width="35%">
									<input type="password" name="sysuser.pwd" />
								</TD>
								<TD height=30 width="15%" align=right>用户类型：</TD>
								<TD class=category width="35%">
									<select name="sysuser.groupid">
										<option value="">请选择</option>
										<option value="1">卫生局</option>
										<option value="2">卫生院</option>
										<option value="3">卫生室</option>
										<option value="4">供货商</option>
										<option value="0">系统管理员</option>
									</select>
								</TD>
							</TR>
							<TR>
							    <TD height=30 width="15%" align=right>所属单位名称：</TD>
								<TD class=category width="35%">
									<input type="text" name="sysuser.sysid" />
								</TD>
								
								<TD height=30 width="15%" align=right>用户状态：</TD>
								<TD class=category width="35%">
									<input type="radio" name="sysuser.userstate" value="1"/>正常
									<input type="radio" name="sysuser.userstate" value="0"/>暂停
								</TD>
							</TR>
							<tr>
							  <td colspan=4 align=center class=category>
								<a id="submitbtn"  class="easyui-linkbutton"   iconCls="icon-ok" href="#" onclick="sysusersave()">提交</a>
								<a id="closebtn"  class="easyui-linkbutton" iconCls="icon-cancel" href="#" onclick="parent.closemodalwindow()">关闭</a>
							  </td>
							</tr>
						</TBODY>
					</TABLE>
				</TD>
			</TR>

		</TBODY>
	</TABLE>

</form>
</body>
</html>