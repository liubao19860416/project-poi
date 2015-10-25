<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/base/tag.jsp"%>
<html>
<head>
<title>datagrid测试</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">

<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/jsp/base/common_css.jsp"%>
<%@ include file="/WEB-INF/jsp/base/common_js.jsp"%>
<SCRIPT type="text/javascript">
	//定义datagrid的列
	var datagrid_columns = [ [ {
		title : '用户账号',
		field : 'userid',
		width : 120
	}, {
		title : '用户名称',
		field : 'username',
		width : 120
	}, {
		title : '用户类型',
		field : 'groupid',
		width : 120,
		//value表示单元格的值,row就是一行的数据即json数据，index序号
		formatter: function(value,row,index){

			if(value=='0'){
				return '系统管理员';
			}else if(value=='1'){
				return '卫生局';
			}else if(value=='2'){
				return '卫生院';
			}else if(value=='3'){
				return '卫生室';
			}else if(value=='4'){
				return '供货商';
			}
		}

	}

	] ];

	var datagrid_toolbar = [ {//定义的工具栏
		id : 'btnadd',
		text : 'Add',
		iconCls : 'icon-add',
		handler : function() {
			$('#btnsave').linkbutton('enable');
			alert('add')
		}
	}, {
		id : 'btncut',
		text : 'Cut',
		iconCls : 'icon-cut',
		handler : function() {
			$('#btnsave').linkbutton('enable');
			alert('cut')
		}
	} ];

	//加载datagrid
	$(function() {
		$('#test').datagrid({
			//这里边填写datagrid的加载参数
			title : 'My Title',
			iconCls : 'icon-save',
			width : 600,
			height : 350,
			nowrap : false,//是否换行，设置为true表示不换行可以提高加载速度
			striped : true,//条纹显示效果
			url : 'user/userquery_result.action',//datagrid需要json数据
			idField : 'code',//json数据集的主键，如果设置错误会导致datagrid的获取当前选中行数等方法的异常
			pagination : true,//是否显示分页区域
			rownumbers : true,//是否显示行号
			columns : datagrid_columns,
			toolbar : datagrid_toolbar
		});

	});
</SCRIPT>

</head>

<body>
	<!-- datagrid数据列表 -->
	<table id="test"></table>
</body>
</html>