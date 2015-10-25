
$(function () {
    if ($("#UserName").val() == "") {
        $("#UserName")
            .addClass("color_gray font_14")//添加灰色字体样式
            .val("输入用户名或手机号码")//添加文字
    }
    $("#UserName")
    .bind({
        "focus": function () {
            $(this).removeClass("color_gray font_14");
            if ($(this).val() == "输入用户名或手机号码") {
                $(this).val("");
            }
        },
        "blur": function () {
            if ($(this).val() == "") {
                $(this).val("输入用户名或手机号码").addClass("color_gray font_14");
            }
        }
    });
    $(".button_submit").hover(function (e) {
        $(this).toggleClass("button_submitover");
    })
    submit(false);
});

function submit(flag) {
    if (flag == true) {
        $("#btnload").show();
        $("#submit").hide();
    }
    else if (flag == false) {
        $("#btnload").hide();
        $("#submit").show();
    }
}
function CheckData() {
    if ($("#ConfirmCode").val() == "") {
        return false;
    }
    submit(true);
    return true;
}
function LoadVerifyImage() {
    $("#valiCode").attr('src', "../Account/GetValidateCode?time=" + (new Date()).getTime());
}