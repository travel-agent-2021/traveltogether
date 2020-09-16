$(document).ready(function () {
    initAgencyOptions();
    initData();
});

function initData() {
    var orderId = localStorage["orderId"];
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
var orderState ="";
if(data.orderStatus == 0){
    orderState ="已付款";
}else if(data.orderStatus == 1){
    orderState ="已付款进行中";
}else if(data.orderStatus == 2){
    orderState ="已付款已完成";
}else if(data.orderStatus == 3){
    orderState ="订单被取消";
}
        $("#orderId").val(data.orderId);
        $("#username").text("用户名 : "+data.username);
        $("#agencyTitle").text("经销商名称 : "+data.agencyTitle);
        $("#itemName").text("商品名 : "+data.itemName);
        $("#orderCreateDate").text("订单创建日期 : "+data.orderCreateDate);
        $("#orderPrice").text("订单金额 : "+data.orderPrice);
        $("#orderStatus").val(data.orderStatus);
        $("#orderDetail").text("补充信息 : "+data.orderDetail);
        $("#orderTravelers").text("旅客人数 : "+data.orderTravelers);

}

function updateOrder() {
    var orderId = $("#orderId").val();
    var orderStatus =  $("#orderStatus").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/updateOrder",
        xhrFields: { withCredentials: true },
        data: {
            "orderId": orderId,
            "orderStatus": orderStatus,
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！");
                window.location.href = "orders.html";
            }else {
                alert("修改失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });
}


function initAgencyOptions() {
    var agencyList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/agency/getAllAgencies",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                agencyList = data.data;
                loadAgencyOptions(agencyList);
            }else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("添加失败, " + data.responseText);
        }

    });
}

function loadAgencyOptions(agencyList) {
    if (agencyList == null || agencyList.length === 0) {
        return;
    }
    for (let i = 0; i < agencyList.length; i++) {
        let agency = agencyList[i];
        let dom = '<option value ="' + agency.agencyId + '">' + agency.agencyTitle + '</option>';
        $("#agencyId").append($(dom));
    }
}