$(document).ready(function () {
    var userId = localStorage["userId"];
    // alert(userId);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/getUserById",
        xhrFields: { withCredentials: true },
        data : {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                loadData(data.data);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
});


function loadData(data) {
    $("#userId").val(data.userId);
    $("#username").val(data.username);
    // $("#password").val(data.encryptPassword);
    $("#age").val(data.age);
    $("#gender").val(data.gender);
    $("#birthday").val(data.birthday);
    $("#email").val(data.userEmail);
    $("#telephone").val(data.userTelephone);
}

function updateUser() {
    var userId = $("#userId").val();
    var username = $("#username").val();
    var password = $("#password").val();
    var telephone = $("#telephone").val();
    var age = $("#age").val();
    var gender = $("#gender").val();
    var birthday = $("#birthday").val()
    var email = $("#email").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/updateUser",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId,
            "username": username,
            "password": password,
            "telephone": telephone,
            "age": age,
            "gender": gender,
            "birthday": birthday,
            "email": email
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！");
                window.location.href = "users.html";
            }else {
                alert("修改失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });
}

function uploadFile() {
    //alert("SAdf");
    var data = new FormData($("#imageForm"));
    $.ajax({
        type: "POST",
        dataType: "application/x-www-form-urlencoded",
        url: "http://localhost:8080/user/upload",
        data: data,
        contentType: false,
        processData: false,
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                alert("上传成功");
                alert(data.data);
            } else {
                alert("上传失败！" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("上传失败！" + data.responseText + "das");
        }
    });


}