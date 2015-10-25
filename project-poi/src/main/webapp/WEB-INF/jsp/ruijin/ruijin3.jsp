<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html style="overflow-y: scroll">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>预约挂号</title>
<link href="./ruijin/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="./ruijin/css/Site.css" rel="stylesheet" type="text/css">
<script src="./ruijin/js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="./ruijin/js/jquery.unobtrusive-ajax.min.js" type="text/javascript" />
<script src="./ruijin/js/Common.js" type="text/javascript" />
<script src="./ruijin/js/Home.js" type="text/javascript" />
</head><body onload="load(&#39;menutop2&#39;,&#39;预约挂号&#39;)">
	<div id="page">
		<div id="header">
			<div class="top_info">
				<div class="top_info_right">
					欢迎您， <b> 保 </b> [ <a href="http://yuyue.rjh.com.cn/Account/LogOff">退出</a>
					]

				</div>
			</div>
			<div class="top_logo">
				<a href="http://yuyue.rjh.com.cn/Home/Index" title="瑞金自助服务平台"
					id="topa"></a>
			</div>
			<div class="menutop_container">
				<ul class="menutop">
					<li id="menutop1"><a href="http://yuyue.rjh.com.cn/Home/Index">首页</a></li>
					<li id="menutop2" class="selected"><a
						href="./预约挂号_files/预约挂号.html">应用中心</a></li>
					<li id="menutop3"><a href="http://yuyue.rjh.com.cn/User/Index">个人中心</a></li>
					<li id="menutop4"><a
						href="http://yuyue.rjh.com.cn/Guahao/Quick">快速预约</a></li>
					<li id="menutop5"><a href="http://yuyue.rjh.com.cn/Info/Terms">关于我们</a></li>
				</ul>
			</div>
		</div>
		<div id="main">
			<link href="./ruijin/css/User.css" rel="stylesheet" type="text/css">
			<div class="menuleft_container">
				<ul class="menuleft">
					<span>我的应用</span>
					<li id="预约挂号" class="selected"><a class="lv1"
						href="./预约挂号_files/ruijin3.html">预约挂号</a></li>
					<li id="账户管理"><a class="lv1"
						href="http://yuyue.rjh.com.cn/Asset/Index">账户管理</a></li>
					<li id="我的预约单"><a class="lv1"
						href="http://yuyue.rjh.com.cn/Guahao/Mine">我的预约单</a></li>
					<li id="我的专家"><a class="lv1"
						href="http://yuyue.rjh.com.cn/Guahao/Expert">我的专家</a></li>
					<li id="我的化验单"><a class="lv1"
						href="http://yuyue.rjh.com.cn/ExternalQuery/MyReports">我的化验单</a></li>
				</ul>
			</div>
			<div class="content_container">

				<link href="./ruijin/css/Guahao.css" rel="stylesheet"
					type="text/css">
				<script src="./ruijin/css/Guahao.js" type="text/javascript"></script>

				<script>
    $(document).ready(function () {
        $(function () {
            ChangeSelected($("#OrderType").val());

            //if ($("#OrderType").val() == "doctor") {
            //    register = "1";
            //}
            //else if ($("#OrderType").val() == "disease") {
            //    register = "2";
            //}
            //else if ($("#OrderType").val() == "common") {
            //    register = "3";
            //}
            register = "";
            $.ajax({
                type: "post",
                url: "../Guahao/GetDeptL2",
                data: 'hospitalid=' + $("#HospitalId").val() + '&deptl1=' + $("#DeptL1").val() + '&registertype=' + register + '&usein=textbox',
                global: false,
                success: function (data) {
                    if (data != undefined && data != "-1") {
                        $("#dept").html(data);
                    }
                    else {
                        $("#dept").html("请手动输入查询");
                    }
                }
            });

            if ($("#DeptL2").val() != "") {
                Search('0', 'search');
            }
        });
    });
</script>

				<div class="index_search">
					<div>
						<ul class="search_memu">
							<li id="doctor" class="selected"><a
								onclick="javascript:ChangeSelected(&#39;doctor&#39;, &#39;false&#39;);">专家/特约专家</a></li>
							<li id="disease" class=""><a
								onclick="javascript:ChangeSelected(&#39;disease&#39;, &#39;false&#39;);">专&nbsp;&nbsp;&nbsp;&nbsp;病</a></li>
							<li id="common"><a
								onclick="javascript:ChangeSelected(&#39;common&#39;, &#39;false&#39;);">普&nbsp;&nbsp;&nbsp;&nbsp;通</a></li>
						</ul>
					</div>
					<div
						style="margin-top: 60px; margin-left: 120px; font-size: 14.5px; font-weight: bold;">
						<table>
							<tbody>
								<tr style="display: none">
									<td style="width: 150px; text-align: right; height: 36px;">请选择医院：</td>
									<td><select id="HospitalId" name="HospitalId"><option
												value="42502656400">瑞金医院</option>
									</select></td>

								</tr>
								<tr style="display: none">

									<td style="width: 150px; text-align: right; height: 36px;">请选择一级科室：</td>
									<td><select id="DeptL1" name="DeptL1"
										onchange="javascript:GetDeptL2(&#39;false&#39;);"><option
												value="">请选择</option>
											<option value="02">全科医疗科</option>
											<option value="03">内科</option>
											<option value="04">外科</option>
											<option value="05">妇产科</option>
											<option value="07">儿科</option>
											<option value="08">小儿外科</option>
											<option value="10">眼科</option>
											<option value="11">耳鼻咽喉科</option>
											<option value="12">口腔科</option>
											<option value="13">皮肤科</option>
											<option value="15">精神科</option>
											<option value="16">传染科</option>
											<option value="19">肿瘤科</option>
											<option value="21">康复医学科</option>
											<option value="31">病理科</option>
											<option value="32">医学影像科</option>
											<option value="50">中医科</option>
									</select></td>
								</tr>
								<tr>
									<td style="width: 150px; text-align: right; height: 36px;">请选择就诊科室：</td>
									<td><input id="DeptL2" name="DeptL2"
										onfocus="javascript:DeptL2Show(this);" type="text" value=""></td>

								</tr>
								<tr>
									<td style="width: 150px; text-align: right; height: 36px;">请输入关键字：</td>
									<td><input id="Keyword" name="Keyword"
										onblur="javascript:RemoveOnfocus(this);"
										onfocus="javascript:AddOnfocus(this);" type="text"
										value="医生姓名/医生专长/疾病名称"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="search_button">
						<table>
							<tbody>
								<tr>
									<td style="width: 200px">&nbsp;</td>
									<td class="search_button_search"><img
										src="./ruijin/pic/btn_search.png"
										onclick="javascript:Search(&#39;0&#39;, &#39;search&#39;);">
									</td>
									<td style="width: 80px">&nbsp;</td>
									<td class="search_button_reset"><img
										src="./ruijin/pic/btn_reset.png"
										onclick="javascript:ResetIndex();"></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div>
						<img src="./ruijin/pic/lbl_search.png" class="search_label">
					</div>
				</div>
				<div class="index_label">
					<img src="./ruijin/pic/lbl_guhao.png">
				</div>
				<div class="index_doctor" id="divorderlist">
					<div class="doctor_index">
						<div class="doctor_line"></div>
						<div class="doctor_image">
							<a
								href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011094&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
								target="_blank"><img class="doctorphoto"
								src="./ruijin/pic/00000000011094.jpg" title="汪启迪"
								id="img4250265640000000000011094"></a>
						</div>
						<div class="doctor_info">
							<table class="doctor_info_table">
								<tbody>
									<tr>
										<td><a class="doctor_info_name"
											href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011094&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
											target="_blank">汪启迪</a></td>
									</tr>
									<tr>
										<td>副主任医师</td>
									</tr>
									<tr>
										<td>门诊内分泌</td>
									</tr>
									<tr>
										<td class="doctor_info_disease">主诊常见病：</td>
									</tr>
									<tr>
										<td title="糖尿病，甲状腺疾病，及其他内分泌代谢疾病的临床诊疗">糖尿病，甲状腺疾病，及其他内分泌代谢疾病的临床诊疗</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="empty_content">暂无排班信息</div>
					</div>
					<div class="doctor_index">
						<div class="doctor_line"></div>
						<div class="doctor_image">
							<a
								href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011102&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
								target="_blank"><img class="doctorphoto"
								src="./ruijin/pic/00000000011102.jpg" title="苏颋为"
								id="img4250265640000000000011102"></a>
						</div>
						<div class="doctor_info">
							<table class="doctor_info_table">
								<tbody>
									<tr>
										<td><a class="doctor_info_name"
											href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011102&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
											target="_blank">苏颋为</a></td>
									</tr>
									<tr>
										<td>副主任医师</td>
									</tr>
									<tr>
										<td>门诊内分泌</td>
									</tr>
									<tr>
										<td class="doctor_info_disease">主诊常见病：</td>
									</tr>
									<tr>
										<td title="肾上腺疾病、垂体疾病">肾上腺疾病、垂体疾病</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="divDoctor1" class="doctor_content">
							<div style="width: 540px">
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-13 星期四</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110500000169" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110500000170" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110500000171" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-20 星期四</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110500000181" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110500000182" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110500000183" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-27 星期四</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110500000193" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110500000194" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110500000195"
												title="科室：门诊内分泌
名额：1
门诊类型：专家
参考价格：17"
												onclick="javascript:Reservation(&#39;42502656400&#39;, &#39;00000000011102&#39;, &#39;苏颋为&#39;, &#39;副主任医师&#39;, &#39;门诊内分泌&#39;, &#39;肾上腺疾病、垂体疾病&#39;, &#39;星期四下午&#39;, &#39;瑞金医院&#39;, &#39;门诊内分泌&#39;, &#39;2014-11-27 星期四&#39;, &#39;15:00-15:59&#39;, &#39;17&#39;, &#39;&#39;, &#39;14110500000195&#39;, &#39;doctor&#39;, &#39;false&#39;);"
												class="doctor_content_available">预约</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-12-04 星期四</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110500000779" class="doctor_content_cancelled">停诊</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110500000686" class="doctor_content_cancelled">停诊</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110500000586" class="doctor_content_cancelled">停诊</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="doctor_index">
						<div class="doctor_line"></div>
						<div class="doctor_image">
							<a
								href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011192&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
								target="_blank"><img class="doctorphoto"
								src="./ruijin/pic/00000000011192.jpg" title="张翼飞"
								id="img4250265640000000000011192"></a>
						</div>
						<div class="doctor_info">
							<table class="doctor_info_table">
								<tbody>
									<tr>
										<td><a class="doctor_info_name"
											href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011192&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
											target="_blank">张翼飞</a></td>
									</tr>
									<tr>
										<td>副主任医师</td>
									</tr>
									<tr>
										<td>门诊内分泌</td>
									</tr>
									<tr>
										<td class="doctor_info_disease">主诊常见病：</td>
									</tr>
									<tr>
										<td title="擅长肥胖、代谢综合症、糖尿病及相关疾病的临床诊治">擅长肥胖、代谢综合症、糖尿病及相关疾病的临床诊治</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="divDoctor2" class="doctor_content">
							<div style="width: 540px">
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-12 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14101400000565" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14101400000465" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14101400000364" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-19 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14102100000669" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14102100000570" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14102100000470" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-26 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14102800000525" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14102800000422" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14102800000314" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-12-03 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110400000643" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110400000535" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110400000425" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="doctor_index">
						<div class="doctor_line"></div>
						<div class="doctor_image">
							<a
								href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011281&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
								target="_blank"><img class="doctorphoto"
								src="./ruijin/pic/00000000011281.jpg" title="李小英"
								id="img4250265640000000000011281"></a>
						</div>
						<div class="doctor_info">
							<table class="doctor_info_table">
								<tbody>
									<tr>
										<td><a class="doctor_info_name"
											href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011281&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
											target="_blank">李小英</a></td>
									</tr>
									<tr>
										<td>主任医师</td>
									</tr>
									<tr>
										<td>门诊内分泌</td>
									</tr>
									<tr>
										<td class="doctor_info_disease">主诊常见病：</td>
									</tr>
									<tr>
										<td title="性腺疾病，内分泌肿瘤及其他内分泌疾病">性腺疾病，内分泌肿瘤及其他内分泌疾病</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="divDoctor3" class="doctor_content">
							<div style="width: 540px">
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-12 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14101400000564" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14101400000464" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14101400000363" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-19 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14102100000668" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14102100000569" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14102100000469" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-26 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14102800000524" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14102800000421" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14102800000313" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-12-03 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110400000642" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110400000534"
												title="科室：门诊内分泌
名额：1
门诊类型：专家
参考价格：20"
												onclick="javascript:Reservation(&#39;42502656400&#39;, &#39;00000000011281&#39;, &#39;李小英&#39;, &#39;主任医师&#39;, &#39;门诊内分泌&#39;, &#39;性腺疾病，内分泌肿瘤及其他内分泌疾病&#39;, &#39;星期三下午&#39;, &#39;瑞金医院&#39;, &#39;门诊内分泌&#39;, &#39;2014-12-03 星期三&#39;, &#39;14:00-14:59&#39;, &#39;20&#39;, &#39;&#39;, &#39;14110400000534&#39;, &#39;doctor&#39;, &#39;false&#39;);"
												class="doctor_content_available">预约</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110400000424" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="doctor_index">
						<div class="doctor_line"></div>
						<div class="doctor_image">
							<a
								href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011319&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
								target="_blank"><img class="doctorphoto"
								src="./ruijin/pic/00000000011319.jpg" title="陆洁莉"
								id="img4250265640000000000011319"></a>
						</div>
						<div class="doctor_info">
							<table class="doctor_info_table">
								<tbody>
									<tr>
										<td><a class="doctor_info_name"
											href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011319&hid=42502656400&l1=03&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
											target="_blank">陆洁莉</a></td>
									</tr>
									<tr>
										<td>副主任医师</td>
									</tr>
									<tr>
										<td>门诊内分泌</td>
									</tr>
									<tr>
										<td class="doctor_info_disease">主诊常见病：</td>
									</tr>
									<tr>
										<td title="糖尿病、甲状腺疾病的诊治">糖尿病、甲状腺疾病的诊治</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="divDoctor4" class="doctor_content">
							<div style="width: 540px">
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-12 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14101400000563" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14101400000463" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14101400000362" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-19 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14102100000667" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14102100000568" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14102100000468" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_x">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-11-26 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14102800000523" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14102800000420" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14102800000312" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
								<table class="doctor_content_table_y">
									<tbody>
										<tr>
											<td class="doctor_content_title">2014-12-03 星期三</td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">13:00-13:59<label
												id="lbl14110400000641" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">14:00-14:59<label
												id="lbl14110400000533" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">15:00-15:59<label
												id="lbl14110400000423" class="doctor_content_unavailable">已满</label></td>
										</tr>
										<tr>
											<td class="doctor_content_table_td">&nbsp;</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
					<div class="orderpager">
						<div class="orderpager_text">&nbsp;</div>
						<div class="orderpager_button">
							<a href="javascript:void(0);"
								onclick="javascript:SelectPage(0, &#39;index&#39;);">首页</a>
						</div>
						<div class="orderpager_button">
							<a href="javascript:void(0);"
								onclick="javascript:SelectPage(2, &#39;index&#39;);">上页</a>
						</div>
						<div class="orderpager_button">
							<a href="javascript:void(0);"
								onclick="javascript:SelectPage(4, &#39;index&#39;);">下页</a>
						</div>
						<div class="orderpager_button">
							<a href="javascript:void(0);"
								onclick="javascript:SelectPage(4, &#39;index&#39;);">末页</a>
						</div>
						<div class="orderpager_text">第4页 / 共5页</div>
					</div>
				</div>
				<div>
					<input id="OrderType" name="OrderType" type="hidden" value="doctor">
					<input id="DoctorKey" name="DoctorKey" type="hidden" value="">
				</div>
				<div>
					<input id="hospitalid" name="hospitalid" type="hidden"
						value="42502656400"> <input id="doctorid" name="doctorid"
						type="hidden" value="00000000011102"> <input
						id="doctorname" name="doctorname" type="hidden" value="苏颋为">
					<input id="grade" name="grade" type="hidden" value="副主任医师">
					<input id="deptl2" name="deptl2" type="hidden" value="门诊内分泌">
					<input id="desc" name="desc" type="hidden" value="肾上腺疾病、垂体疾病">
					<input id="worktime" name="worktime" type="hidden" value="星期四下午">
					<input id="hospitalname" name="hospitalname" type="hidden"
						value="瑞金医院"> <input id="resdeptl2" name="resdeptl2"
						type="hidden" value="门诊内分泌"> <input id="date" name="date"
						type="hidden" value="2014-11-27 星期四"> <input id="time"
						name="time" type="hidden" value="15:00-15:59"> <input
						id="fee" name="fee" type="hidden" value="17"> <input
						id="address" name="address" type="hidden" value=""> <input
						id="templateid" name="templateid" type="hidden"
						value="14110500000195"> <input id="ordertype"
						name="ordertype" type="hidden" value="doctor">
				</div>
				<div id="popupLayerScreenLocker"
					style="height: 1515px; width: 1423px; position: absolute; left: 0px; top: 0px; opacity: 0.5; z-index: 1000; display: block; background: rgb(0, 0, 0);">
				</div>
				<div id="popupLayer_respopup"
					style="visibility: visible; position: absolute; z-index: 1002; left: 316.5px; top: 5px; display: block;">
					<div id="resreservation"
						style="background-image: url(http://yuyue.rjh.com.cn/Images/Doctor/reservation.png);">
						<div class="reservation_blank">
							<label
								onclick="javascript:ClosePopup(&#39;popupLayer_respopup&#39;);"
								style="float: right; width: 20px; height: 20px; color: #666; text-align: center; font: bold 18px Simsun; cursor: pointer;">×</label>
						</div>
						<div id="resdoctor">
							<div class="resdoctor_index">
								<div class="resdoctor_photo">
									<a
										href="http://yuyue.rjh.com.cn/Guahao/Doctor?did=00000000011102&hid=42502656400&l1=&l2=%e9%97%a8%e8%af%8a%e5%86%85%e5%88%86%e6%b3%8c"
										target="_blank"><img src="./ruijin/pic/00000000011102.jpg"
										class="resdoctor_photo_size" title="苏颋为"
										id="resimg4250265640000000000011102"></a>
								</div>
								<div class="resdoctor_doctor">
									<table class="resdoctor_doctor_table">
										<tbody>
											<tr>
												<td class="resdoctor_doctor_name">苏颋为</td>
											</tr>
											<tr>
												<td>副主任医师</td>
											</tr>
											<tr>
												<td>门诊内分泌</td>
											</tr>
											<tr>
												<td class="resdoctor_doctor_disease">主诊常见病：</td>
											</tr>
											<tr>
												<td title="肾上腺疾病、垂体疾病">肾上腺疾病、垂体疾病</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<div class="resdoctor_btn_collect">
								<input type="button" value="加入收藏" class="btn_collect"
									onclick="javascript:CollectDoctor(this, &#39;42502656400&#39;, &#39;&#39;, &#39;门诊内分泌&#39;, &#39;00000000011102&#39;);">
							</div>
						</div>
						<div id="restemplate">
							<div class="restemplate_user">
								<table class="restemplate_user_table">
									<tbody>
										<tr>
											<td class="restemplate_user_td">病人姓名：</td>
											<td><label id="cardname">刘保</label></td>
										</tr>
										<tr>
											<td class="restemplate_user_td">证件号码：</td>
											<td><label id="cardno">13063419850416313X</label></td>
										</tr>
										<tr>
											<td class="restemplate_user_td">手机号码：</td>
											<td><label id="mobile">18611478781</label></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="restemplate_content">
								<table class="restemplate_content_table">
									<tbody>
										<tr>
											<td class="restemplate_content_td">预约医院：</td>
											<td>瑞金医院</td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约科室：</td>
											<td>门诊内分泌</td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约专家：</td>
											<td>苏颋为</td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约日期：</td>
											<td>2014-11-27 星期四</td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约时间：</td>
											<td>15:00-15:59</td>
										</tr>
										<tr>
											<td class="restemplate_content_td">挂号价格：</td>
											<td class="restemplate_content_td_fee">17[仅供参考]</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div id="rescardinfo">
							<div class="rescardinfo_cardinfo">
								<table class="rescardinfo_table">
									<tbody>
										<tr>
											<td>请选择医疗卡号：<a
												href="http://yuyue.rjh.com.cn/User/AddCard"
												class="link_color" target="_blank">绑定卡号</a></td>
										</tr>
										<tr>
											<td><select id="UserCardId" name="UserCardId"
												onchange="javascript:GetCardCredential();"><option
														value="">刘保 初诊</option>
													<option value="141771">刘保 132002101445509</option></select></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="rescardinfo_verifycode">
								<table class="rescardinfo_table_code">
									<tbody>
										<tr>
											<td colspan="3">请填写验证码：</td>
										</tr>
										<tr class="rescardinfo_verifycode_tr">
											<td class="rescardinfo_verifycode_td"><input
												id="VerifyCode" name="VerifyCode"
												onblur="javascript:RemoveOnfocus(this);"
												onfocus="javascript:AddOnfocus(this);" type="text"></td>
											<td><img id="VerifyImage"
												src="./ruijin/GetValidateCode" alt="验证码"></td>
											<td><label class="rescardinfo_verifycode_label"
												onclick="javascript:LoadVerifyImage();">换一张</label></td>
										</tr>
										<tr>
											<td class="rescardinfo_verifycode_tr" colspan="3"><label
												id="ErrorCode"></label></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="rescardinfo_btn_reservation">
								<img src="./ruijin/pic/btn_reservation.png"
									onclick="javascript:SaveReservation(&#39;42502656400&#39;, &#39;14110500000195&#39;, &#39;苏颋为&#39;, &#39;瑞金医院&#39;, &#39;门诊内分泌&#39;, &#39;2014-11-27 星期四&#39;, &#39;15:00-15:59&#39;, &#39;17&#39;, &#39;&#39;, &#39;doctor&#39;);">
							</div>
						</div>
					</div>
				</div>
				<div id="popupLayer_confirmpopup">
					<div id="confirminfo">
						<div class="reservation_blank">
							<label
								onclick="javascript:ClosePopup(&#39;popupLayer_confirmpopup&#39;);"
								style="float: right; width: 20px; height: 20px; color: #666; text-align: center; font: bold 18px Simsun; cursor: pointer;">×</label>
						</div>
						<div id="restemplatesp" style="width: 370px">
							<div class="restemplate_user">
								<table class="restemplate_user_table">
									<tbody>
										<tr>
											<td class="restemplate_user_td">病人姓名：</td>
											<td><label id="confirm_cardname"></label></td>
										</tr>
										<tr>
											<td class="restemplate_user_td">证件号码：</td>
											<td><label id="confirm_cardno"></label></td>
										</tr>
										<tr>
											<td class="restemplate_user_td">医疗卡号：</td>
											<td><label id="confirm_medicalcard"></label></td>
										</tr>
									</tbody>
								</table>
							</div>
							<div class="restemplate_content">
								<table class="restemplate_content_table">
									<tbody>
										<tr>
											<td class="restemplate_content_td">预约医院：</td>
											<td><label id="confirm_hospitalname"></label></td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约科室：</td>
											<td><label id="confirm_resdeptl2"></label></td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约专病：</td>
											<td><label id="confirm_doctorname"></label></td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约日期：</td>
											<td><label id="confirm_date"></label></td>
										</tr>
										<tr>
											<td class="restemplate_content_td">预约时间：</td>
											<td><label id="confirm_time"></label></td>
										</tr>
										<tr>
											<td class="restemplate_content_td">挂号价格：</td>
											<td class="restemplate_content_td_fee"><label
												id="confirm_fee"></label></td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<div id="rescardinfosp"
							style="color: #808080; letter-spacing: 1px; margin-top: 20px;">
							<h2>预约成功！</h2>
							<p>
								预约成功短信已发送到手机<label id="confirm_mobile" class="tootip"></label>
							</p>
							<p>
								届时请携带预约时所留原始证件及就诊医疗卡准时至医院挂号。保留短信，以资凭证。<br> 如取消需要<span
									class="tootip">提前1日14:00之前</span>
							</p>
							<p>
								如遇专家停诊，我们会以电话或短信通知到您，请保持手机畅通！<br> 您也可以关注“我的预约单”了解订单状态。
							</p>
							<a class="order-list-link" hidefocus="hidefocus"
								href="http://yuyue.rjh.com.cn/Guahao/Mine">查看我的预约单»</a>
						</div>
					</div>
				</div>
				<div id="popupLayer_logonpopup">
					<script src="./ruijin/js/LogOn.js" type="text/javascript"></script>
					<script src="./ruijin/js/Guahao.js" type="text/javascript"></script>

					<div class="logon_container">
						<div>
							<label
								onclick="javascript:ClosePopup(&#39;popupLayer_logonpopup&#39;);"
								style="float: right; width: 20px; height: 20px; color: #666; text-align: center; font: bold 18px Simsun; cursor: pointer;">×</label>

						</div>
						<div class="content_title">登录</div>
						<div class="content_title_line"></div>
						<div class="main_content clearfix">
							<div class="color_red">
								<label id="ErrorCode"></label>
							</div>
							<div>
								<div class="main_content_item">
									<div class="logon_label">登录名：</div>
									<div class="logon_field">
										<input id="UserName" name="UserName" type="text" value=""
											class="color_gray font_14">
									</div>
								</div>
								<div class="main_content_item">
									<div class="logon_label">密码：</div>
									<div class="logon_field">
										<input id="Password" name="Password" type="password">
									</div>
								</div>
								<div class="main_content_item">
									<div class="logon_label">验证码：</div>
									<div class="logon_field">
										<input id="VerifyCodeLog" name="VerifyCodeLog"
											title="看不清？点击更换" type="text"> <img id="VerifyImage"
											src="./ruijin/GetValidateCode(1)" alt="验证码"
											style="cursor: pointer"> <label
											class="rescardinfo_verifycode_label"
											onclick="javascript:LoadVerifyImage();">换一张</label>
									</div>
								</div>
								<div>
									<input id="BtnLogon" type="button" value="登录"
										onclick="javascript:LogOn();"> <a class="link_color"
										href="http://yuyue.rjh.com.cn/Account/FindPwd" target="_blank">忘记密码？</a>
									| <a class="link_color"
										href="http://yuyue.rjh.com.cn/Account/Register"
										target="_blank">快速注册</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="popupLayer_infopopup">
					<script src="./ruijin/js/Guahao.js" type="text/javascript"></script>

					<div class="basic_container">
						<div>
							<label
								onclick="javascript:ClosePopup(&#39;popupLayer_infopopup&#39;);"
								style="float: right; width: 20px; height: 20px; color: #666; text-align: center; font: bold 18px Simsun; cursor: pointer;">×</label>

						</div>
						<div class="content_title">就诊</div>
						<div class="content_title_line"></div>
						<div class="main_content clearfix">
							<div class="color_red">
								<label id="ErrorCode"></label>
							</div>
							<div>
								<div class="main_content_item">
									<div class="info_label">
										<span style="color: #3FA1EC">初诊（从未在瑞金医院就诊过）：</span>
									</div>
									<div class="info_field">
										<span style="color: #3FA1EC">初诊用户预约前，请正确填写真实姓名、证件类型、证件号码。</span>
									</div>
									<div>
										<a class="link_color_right"
											href="http://yuyue.rjh.com.cn/User/Index" target="_blank">点此维护</a>
									</div>
								</div>
								<div class="main_content_item">
									<div class="info_label">
										<span style="color: #3FA1EC">复诊（持有在瑞金医院就诊过的医疗卡）：</span>
									</div>
									<div class="info_field">
										<span style="color: #3FA1EC">复诊用户预约前，请正确填写真实姓名、证件类型、证件号码、医疗卡类型、医疗卡号码。</span>
									</div>
									<div>
										<a class="link_color_right"
											href="http://yuyue.rjh.com.cn/User/AddCard" target="_blank">点此维护</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="popupLayer_cardpopup">
					<script src="./ruijin/js/Guahao.js" type="text/javascript"></script>

					<div class="basic_container">
						<div>
							<label
								onclick="javascript:ClosePopup(&#39;popupLayer_cardpopup&#39;);"
								style="float: right; width: 20px; height: 20px; color: #666; text-align: center; font: bold 18px Simsun; cursor: pointer;">×</label>

						</div>
						<div class="content_title">就诊</div>
						<div class="content_title_line"></div>
						<div class="main_content clearfix">
							<div class="color_red">
								<label id="ErrorCode"></label>
							</div>
							<div>
								<div class="main_content_item">

									<div class="info_field">
										<span style="color: #3FA1EC">您以前从未在瑞金医院就诊过。（初诊）</span>

									</div>
									<div>
										<a class="link_color_right"
											onclick="javascript:ReservationCard();">请点击</a>
									</div>
								</div>
								<div class="main_content_item">

									<div class="info_field">
										<span style="color: #3FA1EC">您持有在瑞金医院就诊过的社保或医联(自费)卡。（复诊）</span>
									</div>
									<div>
										<a class="link_color_right"
											href="http://yuyue.rjh.com.cn/User/AddCard" target="_blank">请点击</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="popupLayer_deptl2popup"
					style="visibility: visible; position: absolute; z-index: 3002; left: 678px; top: 287px; display: none;">
					<link href="./ruijin/css/DeptL2.css" rel="stylesheet"
						type="text/css">

					<div id="deptl2content">
						<label class="close" onclick="javascript:DeptL2Hidden();">×</label>
						<ul id="tip">
							<li id="tip0" class="selected" onclick="tipselect(0)">热门科室</li>
							<li id="tip1" onclick="tipselect(1)">内科科室</li>
							<li id="tip2" onclick="tipselect(2)">外科科室</li>
							<li id="tip3" onclick="tipselect(3)">医技科室</li>
						</ul>
						<div id="dept">
							<div id="dept0">
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊内分泌</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊消化</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊高血压</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊心脏</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊肾脏</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊普外</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊神内</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊感染病</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊呼吸科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊妇科</label>
							</div>
							<div id="dept1">
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊普内科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊消化</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊心脏</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊内分泌</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊肾脏</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊血液</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊神内</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊呼吸科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊高血压</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊感染病</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">老年病门诊</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊皮肤科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊儿内</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊中医内</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">针灸科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">推拿科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊康复科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊心理科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">营养门诊</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">护理专病门诊</label>
							</div>
							<div id="dept2">
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊普外</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊乳腺疾病诊治中心</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊心外</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊胸外</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊泌外</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊神外</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊功能神外</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊灼伤整形</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊骨科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊伤科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊妇科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">妇产科生殖医学中心门</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊儿外</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊眼科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊耳鼻喉科</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊中医外</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊中医五官</label>
							</div>
							<div id="dept3">
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">门诊放射</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊放疗</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">肿瘤科门诊</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">病理科</label>
								<label class="lbldeptl2"
									onclick="javascript:DeptL2Select(this);">核医学门诊</label> <label
									class="lbldeptl2" onclick="javascript:DeptL2Select(this);">门诊B超</label>
							</div>
						</div>
					</div>
				</div>
				<div id="loadingScreenLocker"
					style="height: 1515px; width: 1423px; position: absolute; left: 0px; top: 0px; opacity: 0.5; z-index: 2000; display: none; background: rgb(0, 0, 0);">
				</div>
				<div id="loadingLayer"
					style="visibility: visible; width: 100px; line-height: 100px; position: absolute; z-index: 2002; text-align: right; font-weight: bold; font-size: 14px; left: 661.5px; top: 181.5px; display: none; background: url(http://yuyue.rjh.com.cn/Images/Doctor/loadingcomment.gif) no-repeat;">
					&nbsp;</div>




			</div>
			<div class="clear"></div>
		</div>
		<div id="footer">

			<div class="foot">Copyrights 2005~2013 rjh.com.cn All Right
				Reserved</div>
			<div class="foot">党委办公室、院长办公室主办 党委宣传科协办 计算机中心制作
				联系电话：021-64370045</div>
			<div class="foot">沪ICP备08114583</div>
		</div>
	</div>


</body>
</html>