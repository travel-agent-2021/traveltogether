$(document).ready(function () {
    validateLogin();
});

function validateLogin() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                var user = data.data;
                $("#user-name-label").text("欢迎您：" + user.username);
                $("#loginAndRegister").hide();
                $("#logoutPanel").show();
            } else {
                console.log("用户未登录");
                // nothing to do
                $("#loginAndRegister").show();
                $("#logoutPanel").hide();
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

$("#logout").on("click", function () {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/logout",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                window.location.href = "index.html";
            } else {
                alert("退出登录失败, " + data.data.errMsg);
                // nothing to do
            }
        },
        error: function(data) {
            alert("退出登录失败, " + data.responseText);
        }
    });
});