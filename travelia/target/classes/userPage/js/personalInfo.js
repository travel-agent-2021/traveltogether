var userModel;

$(document).ready(function () {
    let userId = window.localStorage.getItem("userId");
    $("#user_id").val(userId);
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
                userModel = data.data;
                loadPersonalInfo(userModel)
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
    if (user == null) {
        return;
    }
    $("#user_name").text("用户名：" + user.username);
    $("#user_telephone").text("手机号：" + user.userTelephone);
    if (user.userImageSource == null || user.userImageSource === ""){
        $("#user_image").attr("src", "img/test.jpg");
    } else {
        $("#user_image").attr("src", user.userImageSource);
    }
    $("#username").val(user.username);
    $("#gender").val(user.gender);
    console.log(user.gender);
    $("#birthday").val(user.birthday);
    $("#telephone").val(user.userTelephone);
    $("#email").val(user.userEmail);
}

$("#updateUserBtn").on("click", function () {
    let userId = $("#user_id").val();
    alert(userId);
    let username = $("#username").val();
    let gender = $("#gender").val();
    let birthday = $("#birthday").val();
    let telephone = $("#telephone").val();
    let email = $("#email").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/updateUser",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId,
            "username": username,
            "telephone": telephone,
            "password": "",
            "gender": gender,
            "birthday": birthday,
            "email": email,
            "userImageSource": ""
        },
        success: function(data) {
            if (data.status === "success") {
                alert("更新成功！");
                $("#closeModalBtn").click();
                initPersonalInfo(userModel.userId);
            } else {
                alert("更新失败" + data.data.errMsg);
            }
        },
        error: function(data) {
            window.location.href = "login.html";
            alert("获取信息失败, " + data.responseText);
        }
    });

});

$("#myModal").on("show.bs.modal", function () {
    loadPersonalInfo(userModel);
});