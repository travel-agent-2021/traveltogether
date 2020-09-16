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
    $("#orderId").text("订单id: " + data.orderId);
    $("#agencyTitle").text("经销商名称: " + data.agencyTitle);
    $("#itemName").text("商品名称: "+data.itemName);
    $("#orderCreateDate").text("订单创建时间: " + data.orderCreateDate);
    $("#orderPrice").text("订单价格: " + data.orderPrice);
    let statusStr = "";
    if (data.orderStatus === 0) {
        statusStr = "已付款";
    } else if (data.orderStatus === 1) {
        statusStr = "已付款进行中";
    } else if (data.orderStatus === 2) {
        statusStr = "已付款已完成";
    } else if (data.orderStatus === 3) {
        statusStr = "订单被取消";
    }

    $("#orderStatus").text("订单状态: " + statusStr);
    $("#orderDetail").text("订单详情: " + data.orderDetail);
    $("#orderTravelers").text("订单游客数: " + data.orderTravelers);
    //removeUserOrderId();
}


function confirmDelete() {
    let r = confirm("确定要删除订单吗？");
    if (r) {
       deleteOrder();
    }
}

function deleteOrder() {
    let orderId = $("#orderId").text().substring(5);
    console.log(orderId);
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