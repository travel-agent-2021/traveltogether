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