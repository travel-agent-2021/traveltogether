$(Document).ready(function () {
    validateLogin();
});

$("#logout").on("click", function () {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/admin/logout",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                window.location.href = "login.html";
            }else {
                alert("退出登录失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("退出登录失败, " + data.responseText);
        }
    });
    return false;
});

function validateLogin () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/admin/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                var admin = data.data;
                $("#user-name").text("管理员：" + data.data.adminAccount)
            }else {
                if (data.data.errCode === 40003) {
                    window.location.href = "login.html";
                } else {
                     alert("获取信息失败，" + data.data.errMsg);
                }
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}