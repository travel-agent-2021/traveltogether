$(document).ready(function () {
    initData();
});

function initData() {
    var orderId = localStorage["userOrderId"];
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
}

