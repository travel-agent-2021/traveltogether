$(document).ready(function () {
    validateLogin();
    //getUsers();
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
})

function getUsers() {
    var userList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/user/getAllUsers",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                userList = data.data;
                loadInfo(userList);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function loadInfo(userList) {
    if (userList === "" || userList == null) {
        return;
    }
    for (var i = 0; i < userList.length; i++) {
        var user = userList[i];
        var dom = '<tr>\n' +
            '                  <td>' + user.userId + '</td>\n' +
            '                  <td>' + user.username + '</td>\n' +
            '                  <td>' + user.age + '</td>\n' +
            '                  <td>' + (user.gender == 0? '女' : '男') + '</td>\n' +
            '                  <td>' + user.userTelephone + '</td>\n' +
            '                  <td>' + user.userEmail + '</td>\n' +
            '                  <td>' + user.birthday + '</td>\n' +
            '                </tr>'
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}


function validateLogin () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/admin/index",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                var admin = data.data;
                $("#user-name").text("管理员：" + data.data.adminAccount)
            }else {
                alert("获取信息失败，" + data.data.errMsg);
                if (data.data.errCode === 40003) {
                    window.location.href = "login.html";
                }
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}