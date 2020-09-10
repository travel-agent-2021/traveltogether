$(document).ready(function () {
    validateLogin();
});

function validateLogin() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/admin/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                var user = data.data;
                // $("#user-name").text("管理员：" + data.data.adminAccount)
            } else {
                console.log("用户未登录")
                // nothing to do
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}