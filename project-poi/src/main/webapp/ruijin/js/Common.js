//去除字符串的前后空白
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
//判断字符串是否全为数字
String.prototype.IsNum = function () {
    var reg = /^\d+$/g;
    return reg.test(this);
}
//判断字符串是否为数字或英文
String.prototype.IsNumorEn = function () {
    var reg = /^[A-Za-z0-9]+$/g;
    return reg.test(this);
}
//判断字符串是否为有效email地址
String.prototype.IsEmail = function () {
    //var reg = /^([a-z0-9A-Z]+[-|\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\.)+[a-zA-Z]{2,3}$/g;
    var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
    return reg.test(this);
}
String.prototype.IsBirthday = function () {
    var reg = /^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-9]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/;
    return reg.test(this);
}
//校验护照
String.prototype.IsPassport = function () {
    var reg = /^((p|P)\d{7})$|^((g|G)\d{8})$/;
    return reg.test(this);
}
function isChn(str) {
    var reg = /^[\u4E00-\u9FA5]+$/;
    if (!reg.test(str)) {
        return false;
    }
    return true;
}
function isEn(str) {
    var reg = /^[A-Z a-z]+$/;
    if (!reg.test(str)) {
        return false;
    }
    return true;
}
function isChnEn(str) {
    if (isChn(str) || isEn(str)) {
        return true;
    }
    else {
        return false;
    }
}
//身份证号码验证
String.prototype.isIDCard = function () {
    var iSum = 0;
    var info = "";
    var sId = this;

    var aCity = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙 江", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖 北", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外" };

    if (sId.length == 18) {
        if (!/^\d{17}(\d|x)$/i.test(sId)) {
            return false;
        }
    }
    else {
        if (!/^\d{15}$/i.test(sId)) {
            return false;
        }
    }

    sId = sId.replace(/x$/i, "a");
    //非法地区
    if (aCity[parseInt(sId.substr(0, 2))] == null) {
        return false;
    }

    var sBirthday;
    if (sId.length == 18) {
        sBirthday = sId.substr(6, 4) + "-" + Number(sId.substr(10, 2)) + "-" + Number(sId.substr(12, 2));
    }
    else {
        sBirthday = "19" + sId.substr(6, 2) + "-" + Number(sId.substr(8, 2)) + "-" + Number(sId.substr(10, 2));
    }

    var d = new Date(sBirthday.replace(/-/g, "/"))

    //非法生日
    if (sBirthday != (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate())) {
        return false;
    }
    if (sId.length == 18) {
        for (var i = 17; i >= 0; i--) {
            iSum += (Math.pow(2, i) % 11) * parseInt(sId.charAt(17 - i), 11);
        }

        if (iSum % 11 != 1) {
            return false;
        }
    }
    return true;

}

//确认删除
function ConfirmDelete() {
    if (confirm("您确定要删除吗？")) {
        if (confirm("删除后将再也无法显示该数据，确定继续吗？")) {
            return true;
        }
    }

    return false;
}
//获取URL参数
function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].substr(strs[i].indexOf("=") + 1));
        }
    }
    return theRequest;
}
//获取URL参数
function getQuery(key) {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = unescape(strs[i].substr(strs[i].indexOf("=") + 1));
        }
    }
    return theRequest[key];
}
function Sleep(numberMillis) {
    var now = new Date();
    var exitTime = now.getTime() + numberMillis;
    while (true) {
        now = new Date();
        if (now.getTime() > exitTime)
            return;
    }
}
function turnoff(obj, loading) {
    if (loading == "on")
        document.getElementById(obj).style.display = "block";
    else
        document.getElementById(obj).style.display = "none";
}
function cutstring(str, len) {
    if (str.length > len) {
        return str.substr(0, len) + "..";
    }
    else {
        return str;
    }
}
function getRootPath() {
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    return (prePath);
//    return (prePath + postPath);
}
$(document).ajaxStart(function () {
    $("#loading").show();
})
$(document).ajaxStop(function () {
    $("#loading").hide();
})