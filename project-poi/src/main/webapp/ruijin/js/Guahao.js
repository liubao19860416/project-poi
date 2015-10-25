function SelectPage(index, sort) {
    if (sort == "index") {
        Search(index);
    }
    else if (sort == "doctor") {
        SearchDoctor(index);
    }
    else if (sort == "expert") {
        SearchExpert(index);
    }
    else if (sort == "quick") {
        SearchQuick(index);
    }
}

function Search(index) {
    if ($("#OrderType").val() == "doctor") {
        strurl = "../Guahao/SearchDoctor";
    }
    else if ($("#OrderType").val() == "disease") {
        strurl = "../Guahao/SearchDisease";
    }
    else if ($("#OrderType").val() == "common") {
        strurl = "../Guahao/SearchCommon";

        if ($("#DeptL2").val() == "") {
            alert("请选择就诊科室");
            return false;
        }
    }
    var key = $("#Keyword").val();
    if (key == "医生姓名/医生专长/疾病名称") {
        key = "";
    }
    $.ajax({
        type: "post",
        url: strurl,
        data: 'keyword=' + key + '&hospitalid=' + $("#HospitalId").val() + '&deptl1=' + $("#DeptL1").val() + '&deptl2=' + $("#DeptL2").val() + '&pageindex=' + index,
        success: function (data) {
            if (data != undefined && data != "-1") {
                $("#divorderlist").html(data);
            }
            else {
                $("#divorderlist").html("查询失败，请稍候重试");
            }
        }
    });
}

function SearchDoctor(index) {
    $.ajax({
        type: "post",
        url: "../Guahao/SearchDoctorDept",
        data: 'hospitalid=' + $("#HospitalId").val() + '&deptl1=' + $("#DeptL1").val() + '&deptl2=' + $("#DeptL2").val() + '&pageindex=' + index,
        global: false,
        success: function (data) {
            if (data != undefined && data != "-1") {
                $("#doctordept").html(data);
            }
            else {
                $("#doctordept").html("查询失败，请稍候重试");
            }
        }
    });
}

function SearchExpert(index) {
    $.ajax({
        type: "post",
        url: "../Guahao/SearchExpert",
        data: 'pageindex=' + index,
        success: function (data) {
            if (data != undefined && data != "-1") {
                $("#content").html(data);
            }
            else {
                $("#content").html("查询失败，请稍候重试");
            }
        }
    });
}

function SaveReservation(hospitalid, templateid, doctorname, hospitalname, resdeptl2, date, time, fee, address, ordertype) { //ORG3选项改变时激活
    $("#ErrorCode").html("");

    //ClosePopup("popupLayer_respopup");
    //GetConfirm($("#cardname").text(), $("#cardno").text(), $("#mobile").text(), hospitalname, resdeptl2, doctorname, date, time, fee, $("#UserCardId  option:selected").text());
    //return false;
    //验证码
    if ($("#VerifyCode").val().trim() == "") {
        $("#ErrorCode").html("请输入验证码");
        $("#VerifyCode").focus();
        return false;
    }
    if ($("#VerifyCode").val().length != 4) {
        $("#ErrorCode").html("验证码长度为4位");
        $("#VerifyCode").focus();
        return false;
    }

    var strdata = 'ordertype=' + ordertype + '&vefirycode=' + $("#VerifyCode").val() + '&usercardid=' + $("#UserCardId").val() + '&hospitalid=' + hospitalid + '&templateid=' + templateid + '&doctorname=' + doctorname + '&hospitalname=' + hospitalname + '&resdeptl2=' + resdeptl2 + '&date=' + date + '&time=' + time + '&fee=' + fee + '&address=' + address;

    $.ajax({
        type: "post",
        url: "../Guahao/SaveReservation",
        data: strdata,
        success: function (data) {
            if (data != undefined && data == "1") {
                //alert("预约成功");
                ClosePopup("popupLayer_respopup");
                //window.location.href("../Guahao/Mine");
                GetConfirm($("#cardname").text(), $("#cardno").text(), $("#mobile").text(), hospitalname, resdeptl2, doctorname, date, time, fee, $("#UserCardId  option:selected").text());
            }
            else if (data != undefined && data == "-1") {
                alert("预约失败，请刷新界面后重试");
                ClosePopup("popupLayer_respopup");
            }
            else if (data != undefined && data == "-2") {
                ClosePopup("popupLayer_respopup");
                //$("#DoctorKey").val(doctorname);
                OpenPopup("popupLayer_logonpopup");
            }
            else if (data != undefined && data == "-3") {
                $("#ErrorCode").html("验证码输入错误");
                LoadVerifyImage();
            }
            else if (data != undefined && data == "-4") {
                $("#ErrorCode").html("生殖医学中心仅限自费卡预约及挂号");
                LoadVerifyImage();
            }
            else {
                alert(data);
                ClosePopup("popupLayer_respopup");
            }
        }
    });
}

function ChangeSelected(sort) { //ORG3选项改变时激活  
    $("#doctor").removeClass("selected");
    $("#disease").removeClass("selected");
    $("#common").removeClass("selected");

    $("#" + sort).addClass("selected");
    $("#OrderType").val(sort);
}

/*function GetDeptL2(flag) { //ORG3选项改变时激活
    if ($("#OrderType").val() == "doctor") {
        register = "1";
    }
    else if ($("#OrderType").val() == "disease") {
        register = "2";
    }
    else if ($("#OrderType").val() == "common") {
        register = "3";
    }
    else{
        register = "1";
    }
    $.ajax({
        type: "post",
        url: "../Guahao/GetDeptL2",
        data: 'hospitalid=' + $("#HospitalId").val() + '&deptl1=' + $("#DeptL1").val() + '&registertype=' + register,
        dataType: "xml",
        global: false,
        success: function (xml) {
            $("#DeptL2").find("option").remove();
            $(xml).find("data").each(function () {
                var value = $(this).children("Value").text();
                var text = $(this).children("Text").text();

                $("#DeptL2").append("<option value=\"" + value + "\">" + text + "</option>");
            });
            $("#DeptL2").focus();

            if (flag == "true") {
                $("#DeptL2").val($("#TDeptL2").val());
                Search("0", "search");
            }
        }
    });
}*/

function LoadDoctorImage(objid, hospitalid, doctorid) {
    $.ajax({
        type: "post",
        url: "../Guahao/GetDoctorPhoto",
        data: 'hospitalid=' + hospitalid + '&doctorid=' + doctorid,
        dataType: "xml",
        global: false,
        success: function (xml) {
            $(xml).find("data").each(function () {
                var success = $(this).children("Success").text();
                var message = $(this).children("Message").text();
                if (success == "true") {
                    $("#" + objid).attr('src', message);
                }
                else {
                    $("#" + objid).attr('src', message);
                }
            });
        }
    });
}

function DivNext(div, src, length) {
    $("#divDoctor" + div).scrollLeft($("#divDoctor" + div).scrollLeft() + length);
    if ($("#divDoctor" + div).scrollLeft() < length || ($("#divDoctor" + div)[0].scrollWidth - $("#divDoctor" + div).scrollLeft()) / length <= 1) {
        $("#next" + div).css("visibility", "hidden");
        $("#prev" + div).css("visibility", "visible");
    }
    else {
        $("#next" + div).css("visibility", "visible");
        $("#prev" + div).css("visibility", "visible");
    }
}

function DivPrev(div, src, length) {
    $("#divDoctor" + div).scrollLeft($("#divDoctor" + div).scrollLeft() - length);
    if ($("#divDoctor" + div).scrollLeft() == 0) {
        $("#prev" + div).css("visibility", "hidden");
        $("#next" + div).css("visibility", "visible");
    }
    else {
        $("#prev" + div).css("visibility", "visible");
        $("#next" + div).css("visibility", "visible");
    }
}

function DoctorDivNext(src) {
    $("#divDoctor0").scrollLeft($("#divDoctor0").scrollLeft() + 950);
    if ($("#divDoctor0").scrollLeft() < 950 || ($("#divDoctor0")[0].scrollWidth - $("#divDoctor0").scrollLeft()) / 950 <= 1) {
        $("#next0").css("visibility", "hidden");
        $("#prev0").css("visibility", "visible");
    }
    else {
        $("#next0").css("visibility", "visible");
        $("#prev0").css("visibility", "visible");
    }
}

function DoctorDivPrev(src) {
    $("#divDoctor0").scrollLeft($("#divDoctor0").scrollLeft() - 950);
    if ($("#divDoctor0").scrollLeft() == 0) {
        $("#prev0").css("visibility", "hidden");
        $("#next0").css("visibility", "visible");
    }
    else {
        $("#prev0").css("visibility", "visible");
        $("#next0").css("visibility", "visible");
    }
}

function Reservation(hospitalid, doctorid, doctorname, grade, deptl2, desc, worktime, hospitalname, resdeptl2, date, time, fee, address, templateid, ordertype, status) {
    var photo = $("#img" + hospitalid + doctorid).attr('src');

    if (photo == undefined) {
        photo = $("#resimg" + hospitalid + doctorid).attr('src');
    }

    if (photo == undefined) {
        photo = "~/Upload/Doctor/default.jpg";
    }

    $("#hospitalid").val(hospitalid);
    $("#doctorid").val(doctorid);
    $("#doctorname").val(doctorname);
    $("#grade").val(grade);
    $("#deptl2").val(deptl2);
    $("#desc").val(desc);
    $("#worktime").val(worktime);
    $("#hospitalname").val(hospitalname);
    $("#resdeptl2").val(resdeptl2);
    $("#date").val(date);
    $("#time").val(time);
    $("#fee").val(fee);
    $("#address").val(address);
    $("#templateid").val(templateid);
    $("#ordertype").val(ordertype);

    $.ajax({
        type: "post",
        url: "../Guahao/GetReservation",
        data: 'hospitalid=' + hospitalid + '&doctorid=' + doctorid + '&doctorname=' + doctorname + '&grade=' + grade + '&deptl2=' + deptl2 + '&desc=' + desc + '&worktime=' + worktime + '&hospitalname=' + hospitalname + '&resdeptl2=' + resdeptl2 + '&date=' + date + '&time=' + time + '&fee=' + fee + '&address=' + address + '&photo=' + photo + '&templateid=' + templateid + '&ordertype=' + ordertype + '&status=' + status,
        global: false,
        success: function (data) {
            if (data != undefined && data != "-1" && data != "-2" && data != "-3" && data != "-4") {
                if (ordertype == "doctor"){
                    $("#resreservation").css("background-image", "url(../Images/Doctor/reservation.png)");
                }
                else if (ordertype == "disease" || ordertype == "common") {
                    $("#resreservation").css("background-image", "url(../Images/Doctor/reservationsp.png)");
                }
                $("#resreservation").html(data);
                GetCardCredential();
                OpenPopup("popupLayer_respopup");
            }
            else if (data != undefined && data == "-2") {
                //window.location.href("../Account/LogOn");
                //$("#DoctorKey").val(doctorname);
                OpenPopup("popupLayer_logonpopup");
            }
            else if (data != undefined && data == "-3") {
                OpenPopup("popupLayer_infopopup");
            }
            else if (data != undefined && data == "-4") {
                OpenPopup("popupLayer_cardpopup");
            }
            else {
                alert("预约失败，请刷新界面后重试");
            }
        }
    });
}

function LoadVerifyImage() {
    $("#VerifyImage").attr('src', "../Guahao/GetValidateCode?time=" + (new Date()).getTime());
    //$("#VerifyImage")[0].src = "../Guahao/GetValidateCode?time=" + (new Date()).getTime();
}

function GetCardCredential() { //ORG3选项改变时激活
    $.ajax({
        type: "post",
        url: "../Guahao/GetCardCredential",
        data: 'cardid=' + $("#UserCardId").val(),
        dataType: "xml",
        global: false,
        success: function (xml) {
            $(xml).find("data").each(function () {
                var CardHolderName = $(this).children("CardHolderName").text();
                var CardHolderCredentialID = $(this).children("CardHolderCredentialID").text();

                $("#cardname").text(CardHolderName);
                $("#cardno").text(CardHolderCredentialID);
            });
        }
    });
}

function GetConfirm(cardname, cardno, mobile, hospitalname, resdeptl2, doctorname, date, time, fee, usercard) { //ORG3选项改变时激活
    //alert(cardname + cardno + mobile + hospitalname + resdeptl2 + ndoctorname + date + time + fee + usercard);

    $("#confirm_cardname").text(cardname);
    $("#confirm_cardno").text(cardno);
    $("#confirm_mobile").text(mobile);
    $("#confirm_hospitalname").text(hospitalname);
    $("#confirm_resdeptl2").text(resdeptl2);
    $("#confirm_doctorname").text(doctorname);
    $("#confirm_date").text(date);
    $("#confirm_time").text(time);
    $("#confirm_fee").text(fee + "[仅供参考]");
    var medicalcard = usercard.replace(cardname, "");
    $("#confirm_medicalcard").text(medicalcard);

    OpenPopup("popupLayer_confirmpopup");
}

function CollectDoctor(ctrl, hospitalid, deptl1, deptl2, doctorid) {
    var status;
    if (ctrl.value == "加入收藏") {
        status = "false";
    }
    else {
        status = "true";
    }
    $.ajax({
        type: "post",
        url: "../Guahao/CollectDoctor",
        data: 'hospitalid=' + hospitalid + '&deptl1=' + deptl1 + '&deptl2=' + deptl2 + '&doctorid=' + doctorid + '&flag=' + status,
        global: false,
        success: function (data) {
            if (data != undefined && data == "1") {
                //$(ctrl).attr('src', src);
                if (status == "false") {
                    $(ctrl).val("取消收藏");
                    alert("恭喜您，收藏成功");
                }
                else {
                    $(ctrl).val("加入收藏");
                    alert("取消成功");
                }
            }
            else if (data != undefined && data == "-2") {
                alert("操作失败，请登陆成功后重试");
            }
            else {
                alert("操作失败，请刷新界面后重试");
            }
        }
    });
}

function LogOn() {
    //var url;
    //if (window.location.pathname.substr(window.location.pathname.lastIndexOf("/") + 1) == "Index") {
    //    str = "?id=" + $("#HospitalId").val() + "&l1=" + $("#DeptL1").val() + "&l2=" + encodeURIComponent($("#DeptL2").val()) + "&key=" + encodeURIComponent($("#DoctorKey").val()) + "&sort=" + $("#OrderType").val();
    //    url = window.location.href.substr(0, window.location.href.indexOf("?")) + str;
    //}
    //else if (window.location.pathname.substr(window.location.pathname.lastIndexOf("/") + 1) == "Doctor") {
    //    url = window.location.href;
    //}

    $("#ErrorCode").html("");
    //验证码
    if ($("#UserName").val().trim() == "输入用户名或手机号码" || $("#UserName").val().trim() == "") {
        $("#ErrorCode").html("请输入用户名");
        $("#UserName").focus();
        return false;
    }
    if ($("#Password").val().trim() == "") {
        $("#ErrorCode").html("请输入密码");
        $("#Password").focus();
        return false;
    }
    if ($("#VerifyCodeLog").val().trim() == "") {
        $("#ErrorCode").html("请输入验证码");
        $("#VerifyCodeLog").focus();
        return false;
    }
    if ($("#VerifyCodeLog").val().length != 4) {
        $("#ErrorCode").html("验证码长度为4位");
        $("#VerifyCodeLog").focus();
        return false;
    }

    $.ajax({
        type: "post",
        url: "../Guahao/LogOn",
        data: 'username=' + $("#UserName").val() + '&password=' + $("#Password").val() + '&vefirycode=' + $("#VerifyCodeLog").val(),
        global: false,
        success: function (data) {
            if (data != undefined && data == "1") {
                ClosePopup("popupLayer_logonpopup");
                //window.location.href(url);
                Reservation($("#hospitalid").val(), $("#doctorid").val(), $("#doctorname").val(), $("#grade").val(), $("#deptl2").val(), $("#desc").val(), $("#worktime").val(), $("#hospitalname").val(), $("#resdeptl2").val(), $("#date").val(), $("#time").val(), $("#fee").val(), $("#address").val(), $("#templateid").val(), $("#ordertype").val(), 'false');
            }
            else if (data != undefined && data == "-1") {
                alert("登陆失败，请刷新界面后重试");
                ClosePopup("popupLayer_logonpopup");
            }
            else if (data != undefined && data == "-3") {
                $("#ErrorCode").html("验证码输入错误");
                LoadVerifyImage();
            }
            else {
                $("#ErrorCode").html(data);
            }
        }
    });
}

function ReservationCard() {
    ClosePopup("popupLayer_cardpopup");
    Reservation($("#hospitalid").val(), $("#doctorid").val(), $("#doctorname").val(), $("#grade").val(), $("#deptl2").val(), $("#desc").val(), $("#worktime").val(), $("#hospitalname").val(), $("#resdeptl2").val(), $("#date").val(), $("#time").val(), $("#fee").val(), $("#address").val(), $("#templateid").val(), $("#ordertype").val(), 'true');
}

function OpenPopup(str) {
    $("#popupLayerScreenLocker").height($(document).height() + "px");
    $("#popupLayerScreenLocker").width($(document).width() + "px");
    //$("#popupLayerScreenLocker").width($(document.body).outerWidth(true) + "px");
    $("#popupLayerScreenLocker").css({
        position: "absolute",
        background: "#000",
        left: "0",
        top: "0",
        opacity: "0.5",
        display: "none"
    });
    $("#popupLayerScreenLocker").css("z-index", 1000);

    $("#popupLayerScreenLocker").fadeIn();

    $("#popupLayerScreenLocker").click(function () {
        ClosePopup(str);
    });

    var popupElement = $("#" + str)
    var zIndex = 1002;
    popupElement.css({
        visibility: "hidden",
        width: "",
        height: "",
        position: "absolute",
        "z-index": zIndex
    });

    if (popupElement.width() < $(window).width()) {
        //var leftPosition = (document.documentElement.offsetWidth - popupElement.width()) / 2;
        var leftPosition = document.documentElement.scrollLeft + ($(window).width() - popupElement.width()) / 2;
    } else {
        var leftPosition = document.documentElement.scrollLeft + 5;
    }

    if (popupElement.height() < $(window).height()) {
        var topPosition = document.documentElement.scrollTop + ($(window).height() - popupElement.height()) / 2;
    } else {
        var topPosition = document.documentElement.scrollTop + 5;
    }

    var positions = {
        left: leftPosition + "px",
        top: topPosition + "px"
    };

    popupElement.css(positions);

    popupElement.css("visibility", "visible");

    popupElement.fadeIn();
}

function ClosePopup(str) {
    $("#" + str).fadeOut();
    $("#popupLayerScreenLocker").fadeOut();
}

function Test() {
//    $.ajax({
//        type: "post",
//        url: "/Guahao/Test",
//        data: '',
//        cache: false,
//        success: function (data) {
//        }
    //    });
    alert("暂不开放，敬请期待");
}

function AddOnfocus(ctrl) {
    $(ctrl).addClass("onfocus");

    if (ctrl.id == "Keyword" && ctrl.value == "医生姓名/医生专长/疾病名称") {
        ctrl.value = "";
    }
}
function RemoveOnfocus(ctrl) {
    $(ctrl).removeClass("onfocus");

    if (ctrl.id == "Keyword" && ctrl.value == "") {
        ctrl.value = "医生姓名/医生专长/疾病名称";
    }
}

$(document).ajaxStart(function () {
    $("#loadingScreenLocker").height($(document).height() + "px");
    $("#loadingScreenLocker").width($(document).width() + "px");
    //$("#loadingScreenLocker").width($(document.body).outerWidth(true) + "px");
    $("#loadingScreenLocker").css({
        position: "absolute",
        background: "#000",
        left: "0",
        top: "0",
        opacity: "0.5",
        display: "none"
    });
    $("#loadingScreenLocker").css("z-index", 2000);

    $("#loadingScreenLocker").fadeIn();

    var popupElement = $("#loadingLayer");
    var zIndex = 2002;
    popupElement.css({
        background: "url(../Images/Doctor/loadingcomment.gif) no-repeat",
        visibility: "hidden",
        width: "100px",
        height: "",
        "line-height": "100px",
        position: "absolute",
        "z-index": zIndex,
        "text-align": "right",
        "font-weight": "bold",
        "font-size": "14px"
    });

    if (popupElement.width() < $(window).width()) {
        //var leftPosition = (document.documentElement.offsetWidth - popupElement.width()) / 2;
        var leftPosition = document.documentElement.scrollLeft + ($(window).width() - popupElement.width()) / 2;
    } else {
        var leftPosition = document.documentElement.scrollLeft + 5;
    }

    if (popupElement.height() < $(window).height()) {
        var topPosition = document.documentElement.scrollTop + ($(window).height() - popupElement.height()) / 2;
    } else {
        var topPosition = document.documentElement.scrollTop + 5;
    }

    var positions = {
        left: leftPosition + "px",
        top: topPosition + "px"
    };

    popupElement.css(positions);

    popupElement.css("visibility", "visible");

    popupElement.fadeIn();
});
$(document).ajaxStop(function () {
    $("#loadingScreenLocker").fadeOut();
    $("#loadingLayer").fadeOut();
});

function ResetIndex() { //ORG3选项改变时激活  
    $("#HospitalId option").eq(0).attr('selected', 'true');
    $("#DeptL1").val("");
    $("#DeptL2").val("");
    $("#Keyword").val("医生姓名/医生专长/疾病名称");
}

function ResetQuick() { //ORG3选项改变时激活  
    $("#HospitalId option").eq(0).attr('selected', 'true');
    $("#DeptL1").val("");
    $("#DeptL2").val("");
    $("#StartDate").datepicker('setDate', new Date());
    $("#EndDate").datepicker('option', 'minDate', $("#StartDate").val());
    //$("#EndDate").datepicker('setDate', new Date());
    var date = new Date();
    date.setDate(date.getDate() + 28);
    $("#EndDate").datepicker('setDate', date);
}

function SearchQuick(index) {
    //if ($("#DeptL1").val() == "") {
    //    alert("请选择一级科室");
    //    return false;
    //}

    if ($("#DeptL2").val() == "") {
        alert("请选择就诊科室");
        return false;
    }

    $.ajax({
        type: "post",
        url: "../Guahao/SearchQuick",
        data: 'hospitalid=' + $("#HospitalId").val() + '&deptl1=' + $("#DeptL1").val() + '&deptl2=' + $("#DeptL2").val() + '&sdt=' + $("#StartDate").val() + '&edt=' + $("#EndDate").val() + '&pageindex=' + index,
        success: function (data) {
            if (data != undefined && data != "-1") {
                $("#divquicklist").html(data);
            }
            else {
                $("#divquicklist").html("查询失败，请稍候重试");
            }
        }
    });
}

function DeptL2Show(str) {
    var popupElement = $("#popupLayer_deptl2popup");
    var zIndex = 3002;
    popupElement.css({
        visibility: "hidden",
        width: "",
        height: "",
        position: "absolute",
        "z-index": zIndex
    });

    var point = getAbsolutePosition(str);

    var positions = {
        left: point.x + "px",
        top: point.y + 32 + "px"
    };

    popupElement.css(positions);

    popupElement.css("visibility", "visible");

    popupElement.fadeIn();
}

function DeptL2Hidden() {
    $("#popupLayer_deptl2popup").fadeOut();
}

function DeptL2Select(ctrl) {
    $("#DeptL2").val(ctrl.innerText);
    $("#popupLayer_deptl2popup").fadeOut();
}


function getAbsolutePosition(element) {
    var point = { x: element.offsetLeft, y: element.offsetTop, w: element.offsetWidth, h: element.offsetHeight };
    //Recursion
    if (element.offsetParent) {
        var parentPoint = getAbsolutePosition(element.offsetParent);
        point.x += parentPoint.x;
        point.y += parentPoint.y;
    }
    return point;
};

function tipselect(index) {
    $("#tip0,#tip1,#tip2,#tip3").removeClass("selected");
    $("#dept0,#dept1,#dept2,#dept3").hide();

    $("#tip" + index).addClass("selected");
    $("#dept" + index).show();
}
