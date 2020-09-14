$(document).ready(function () {
    let orderId = getUserOrderId();
    initData(orderId);
});

function getUserOrderId() {
    let orderId = localStorage["userOrderId"];
    if (orderId == null) {
        window.location.href = "index.html";
    }
    return orderId;
}

function removeUserOrderId() {
    if (window.localStorage) {
        window.localStorage.removeItem("userOrderId");
    } else {
        alert("Your browser does not support this technology.");
    }
}

function initData(orderId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getOrderById",
        xhrFields: { withCredentials: true },
        data : {
            "orderId": orderId
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
}


function loadData(data) {
    $("#orderId").val(data.orderId);
    $("#agencyTitle").val(data.agencyTitle);
    $("#itemName").val(data.itemName);
    $("#orderCreateDate").val(data.orderCreateDate);
    $("#orderPrice").val(data.orderPrice);
    let statusStr = "";
    if (data.orderStatus === 0) {
        statusStr = "未付款";
    } else if (data.orderStatus === 1) {
        statusStr = "未完成";
    } else if (data.orderStatus === 2) {
        statusStr = "已完成";
    } else if (data.orderStatus === 3) {
        statusStr = "已取消";
    }
    $("#orderStatus").val(statusStr);
    $("#orderDetail").val(data.orderDetail);
    $("#orderTravelers").val(data.orderTravelers);
    //removeUserOrderId();
}


function confirmDelete() {
    let r = confirm("确定要删除订单吗？");
    if (r) {
       deleteOrder();
    }
}

function deleteOrder() {
    let orderId = $("#orderId").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/deleteOrder",
        xhrFields: { withCredentials: true },
        data : {
            "orderId": orderId
        },
        success: function(data) {
            if (data.status === "success") {
                alert("订单已删除！");
                showPersonalInfo();
            }else {
                alert(data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function showPersonalInfo() {
    let userId = $("#user_id").text();
    if (window.localStorage) {
        localStorage.userId = userId;
        // removeUserOrderId();
        window.location.href = 'personalInfo.html';
    } else {
        alert("Your browser does not support this technology.");
    }
}