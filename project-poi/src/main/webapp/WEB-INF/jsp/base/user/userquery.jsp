<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html>
<head>
<title>用户查询列表</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>

<SCRIPT type="text/javascript">
//定义用户管理列表的列
var datagrid_columns = [ [ {
	title : '用户账号',
	field : 'userid',
	width : 120
}, {
	title : '用户名称',
	field : 'username',
	width : 200
}, {
	title : '用户类型',
	field : 'groupname',
	width : 120

},{
	title : '所属单位',
	field : 'sysmc',
	width : 160
},{
	title : '状态',
	field : 'userstate',
	width : 80,
	formatter: function(value,row,index){
		if(value=='1'){
			return '正常';
		}else if(value=='0'){
			return '暂停';
		}
	}
},{
	title : '修改',
	field : 'opt2',
	width : 60,
	formatter: function(value,row,index){
		return "<a href=javascript:useredit('"+row.id+"');>修改</a>";
	}
},{
	title : '删除',
	field : 'opt1',
	width : 60,
	formatter: function(value,row,index){
		return "<a href=javascript:userdelete('"+row.id+"');>删除</a>";
	}
}

] ];
//定义的工具栏
var datagrid_toolbar = [ {
	id : 'btnadd',
	text : '添加',
	iconCls : 'icon-add',
	handler : useradd
} ];

//加载datagrid
$(function() {
	$('#sysuserlist').datagrid({
		//这里边填写datagrid的加载参数
		title : '用户列表',
		nowrap : false,//是否换行，设置为true表示不换行可以提高加载速度
		striped : true,//条纹显示效果
		url : '${baseurl}user/userquery_result.action',//datagrid需要json数据
		idField : '',//json数据集的主键，如果设置错误会导致datagrid的获取当前选中行数等方法的异常
		pagination : true,//是否显示分页区域
		rownumbers : true,//是否显示行号
		columns : datagrid_columns,
		toolbar : datagrid_toolbar
	});

});

//用户查询
function sysuserquery(){
	
	//从查询条件所在的from里边通过jquery提供的方法，将from中的参数找到组成一个json对象
	var formdata = $("#sysuserlistForm").serializeJson();
	
	$('#sysuserlist').datagrid('load',formdata);

}

//用户添加
function useradd(){
	createmodalwindow('添加用户',850,230,'${baseurl}user/useradd.action');
}

//用户删除
function userdelete(id){
	//提示用户是否确认删除
	_confirm('您确定要执行删除操作吗?', null, function() {
		//将删除的id设置到删除form中
		$("#deleteid").val(id);
	 	//调用ajaxFrom提交，执行删除
		jquerySubByFId('deleteuserForm',userdelete_callback,null,"json");
	});

	
}
function userdelete_callback(data){
	message_alert(data);
	if(data.resultInfo.type==TYPE_RESULT_SUCCESS){//如果操作成功执行删除操作
		sysuserquery();
	}
	
}
//用户修改显示
function useredit(id){
	//弹出修改容器
	createmodalwindow('修改用户',850,230,'${baseurl}user/useredit.action?id='+id);
}

//回调函数原理
var fun_1=function fun_1(name){
	alert('fun_1'+name);
	
};

function fun_2(){
	
	alert('fun_2');
}


//将js函数传到test_fun,fun为一个js函数
function test_fun(fun){
	alert(fun);
	fun('张三');
}
function test_fun2(fun2){
	test_fun(fun2);
}
//调用 test_fun，将fun_1函数的指针（内存地址）
//test_fun2(fun_1);


</SCRIPT>

</head>

<body>
    <!--  查询条件 -->
    <form id="sysuserlistForm">
	<TABLE class="table_search">
		<TBODY>
			<TR>
				<TD class="left">用户账号：</td>
				<td><INPUT type="text" name="sysuserCustom.userid" /></TD>
				<TD class="left">用户名称：</TD>
				<td><INPUT type="text" name="sysuserCustom.username" /></TD>

				<TD class="left">单位名称：</TD>
				<td><INPUT type="text" name="sysuserCustom.sysmc" /></TD>
				<TD class="left">用户类型：</TD>
				<td><select name="sysuserCustom.groupid">
						<option value="">请选择</option>
						<c:forEach items="${grouplist}" var="dictinfo">
						   <option value="${dictinfo.dictcode}">${dictinfo.info}</option>
						</c:forEach>
					
				</select></TD>
				<td><a id="btn" href="#" onclick="sysuserquery()"
					class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
			</TR>


		</TBODY>
	</TABLE>
	</form>
	<form id="deleteuserForm" action="${baseurl}user/userdelete.action" method="post">
	   <input type="hidden" id="deleteid" name="deleteid" />
	</form>
   <!-- 数据列表 -->
	<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
		<TBODY>
			<TR>
				<TD>
					<table id="sysuserlist"></table>
				</TD>
			</TR>
		</TBODY>
	</TABLE>
</body>

</html>