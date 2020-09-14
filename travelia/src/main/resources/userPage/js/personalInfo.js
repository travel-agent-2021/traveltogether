$(document).ready(function () {
    checkLogin();
    let userId = window.localStorage.getItem("userId");
    $("#user_id").val(userId);
    initPersonalInfo(userId);
    initUserOrders(userId);
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


function initPersonalInfo(userId) {
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
    console.log($("#gender").val());
    $("#gender").val(user.gender);
    console.log($("#gender").val());
    $("#birthday").val(user.birthday);
    $("#telephone").val(user.userTelephone);
    $("#email").val(user.userEmail);
}


$("#updateUserBtn").on("click", updateUser);

$("#showModal").on("click", function () {
    $("#myModal").modal('show');
});

$("#showPasswordModal").on("click", function () {
    $("#updatePasswordModal").modal('show');
});

function closeModal() {
    initPersonalInfo($("#user_id").val());
    $("#myModal").modal("hide");
}


function updateUser() {
    let userId = $("#user_id").val();
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
                $("#myModal").modal('hide');
                initPersonalInfo(userId);
            } else {
                alert("更新失败" + data.data.errMsg);
            }
        },
        error: function(data) {
            window.location.href = "login.html";
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function initUserOrders(userId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getOrdersByUserId",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                let orderList = data.data;
                loadUserOrders(orderList);
            } else {
                alert("请先登录");
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function loadUserOrders(orderList) {
    if (orderList == null) {
        return;
    }
    for (let i = 0; i < orderList.length; i++) {
        let order = orderList[i];
        let statusStr = "";
        if (order.orderStatus === 0) {
            statusStr = "未付款";
        } else if (order.orderStatus === 1) {
            statusStr = "未完成";
        } else if (order.orderStatus === 2) {
            statusStr = "已完成";
        } else if (order.orderStatus === 3) {
            statusStr = "以取消";
        }

        let dom = '<tr><td>' + order.orderId + '</td>\n' +
            '                                <td>' + statusStr + '</td>\n' +
            '                                <td>' + order.orderCreateDate + '</td>\n' +
            '                                <td>' + order.itemName + '</td>\n' +
            '                                <td>' + order.orderPrice + '</td>\n' +
            '                                <td>' + order.agencyTitle + '</td>' +
            '                                <td><a href="#" onclick="showOrderDetails(' + order.orderId + ')">详细信息</a></td></tr>';
        $("#dataTable tbody").append($(dom));
    }
}

$("#updatePasswordBtn").on("click", function () {
    let userId = $("#user_id").text();
    console.log("userid：" + userId);
    let password = $("#oldPassword").val();
    let newPassword = $("#newPassword").val();
    let confirmedNewPassword = $("#confirmNewPassword").val();
    if (newPassword !== confirmedNewPassword) {
        alert("两次密码不一致，请重试");
        return;
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/updatePassword",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId,
            "password": password,
            "newPassword": newPassword
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！,请重新登录");
                $("#updatePasswordModal").modal('hide');
                window.location.href = "login.html";
            } else {
                alert(data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });
});


function showOrderDetails(orderId) {
    if (window.localStorage) {
        localStorage.userOrderId = orderId;
        window.location.href = 'orderDetails.html';
    } else {
        alert("Your browser does not support this technology.");
    }
}