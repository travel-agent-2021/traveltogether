$(document).ready(function () {
    let userId = window.localStorage.getItem("userId");
    checkLogin();
    initPersonalInfo(userId);

});

function checkLogin() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
            } else {
                alert("用户未登录");
                window.location.href = "login.html";
            }
        },
        error: function(data) {
            window.location.href = "login.html";
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function initPersonalInfo (userId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/getUserById",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                let user = data.data;
                loadPersonalInfo(user);
            } else {
                alert("请先登录");
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadPersonalInfo(user) {
    $("#user_name").text("用户名：" + user.username);
    $("#user_telephone").text("手机号：" + user.userTelephone);
    if (user.userImageSource == null || user.userImageSource === ""){
        $("#user_image").attr("src", "img/test.jpg");
    } else {
        $("#user_image").attr("src", user.userImageSource);
    }

}