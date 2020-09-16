$(document).ready(function () {
    getUsers();
});

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
        var userId = user.userId;
        var gender = "";
        //alert(user.gender);
        if (user.gender == 0) {
            gender = "女";
        } else if(user.gender == 1) {
            gender = "男";
        } else {
            gender = "未设置";
        }
        var dom = '<tr>\n' +
            '                  <td>' + user.userId + '</td>\n' +
            '                  <td>' + user.username + '</td>\n' +
            '                  <td>' + user.age + '</td>\n' +
            '                  <td>' + gender + '</td>\n' +
            '                  <td>' + user.userTelephone + '</td>\n' +
            '                  <td>' + user.userEmail + '</td>\n' +
            '                  <td>' + user.birthday + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + userId +')">修改</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteUser(' + userId +')">删除</a></td>\n'+
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteUser(userId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/deleteUser",
        xhrFields: { withCredentials: true },
        data : {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "users.html";
            }else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("删除, " + data.responseText);
        }
    });
}

function setAndEdit(userId) {
    if (window.localStorage) {
        localStorage.userId = userId;
        location.href = 'usersDetail.html';
    } else {
        alert("Your browser do not support this technology.");
    }
}