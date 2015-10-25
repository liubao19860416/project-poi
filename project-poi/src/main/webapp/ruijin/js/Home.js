function load(str1,str2) {
    $("#" + str1).addClass("selected");
    $("#" + str2).addClass("selected");
}

$(function () {
    var s = getRootPath() + "/Home/Index";
    $("#topa").attr("href", s);
});