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
    $("#orderId").val(data.orderId);
    $("#userId").val(data.userId);
    $("#agencyId").val(data.agencyId);
    $("#itemId").val(data.itemId);
    $("#orderCreateDate").val(data.orderCreateDate);
    $("#orderPrice").val(data.orderPrice);
    $("#orderStatus").val(data.orderStatus);
    $("#orderDetail").val(data.orderDetail);
    $("#orderTravelers").text(data.orderTravelers);
}

function updateOrder() {
    var orderId = $("#orderId").val();
    var userId = $("#userId").val();
    var agencyId = $("#agencyId").val();
    var itemId = $("#itemId").val();
    var orderCreateDate = $("#orderCreateDate").val();
    var orderPrice = $("#orderPrice").val();
    var orderStatus =  $("#orderStatus").val();
    var orderDetail = $("#orderDetail").val();
    var orderTravelers =  $("#orderTravelers").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/updateOrder",
        xhrFields: { withCredentials: true },
        data: {
            "orderId": orderId,
            "userId": userId,
            "agencyId": agencyId,
            "itemId": itemId,
            "orderCreateDate": orderCreateDate,
            "orderPrice": orderPrice,
            "orderStatus": orderStatus,
            "orderDetail": orderDetail,
            "orderTravelers": orderTravelers
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