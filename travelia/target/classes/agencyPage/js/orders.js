$(document).ready(function () {
    var fetchAgencyId = localStorage["storeAgencyId"];
    console.log(fetchAgencyId);
    getOrders(fetchAgencyId);
});



function getOrders(fetchAgencyId) {
    var orderList = [];

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/order/getOrdersByAgencyAccount",
        data: { "agencyId": fetchAgencyId},
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                orderList = data.data;
                loadInfo(orderList);
            }else {
                alert("获取信息失败01，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败02, " + data.responseText);
        }
    });
}


function loadInfo(orderList) {
    if (orderList === "" || orderList == null) {
        return;
    }
    for (var i = 0; i < orderList.length; i++) {
        var order = orderList[i];
        var orderId = order.orderId;
        //alert(orderId);

        var dom = '<tr>\n' +
            '                  <td>' + order.orderId + '</td>\n' +
            '                  <td>' + order.orderCreateDate + '</td>\n' +
            '                  <td>' + order.orderPrice + '</td>\n' +
            '                  <td>' + order.orderStatus + '</td>\n' +
            '                  <td>' + order.userId + '</td>\n' +
            '                  <td>' + order.agencyId + '</td>\n' +
            '                  <td>' + order.orderTravelers + '</td>\n' +
            '                  <td>' + order.orderDetail + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + orderId +')">查看</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteOrder(' + orderId +')">删除</a></td>\n'+
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteOrder(orderId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/deleteOrder",
        xhrFields: { withCredentials: true },
        data : {
            "orderId": orderId
        },
        success: function(data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "orders.html";
            }else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("删除, " + data.responseText);
        }
    });
}

function setAndEdit(orderId) {
    if (window.localStorage) {
        localStorage.orderId = orderId;
        location.href = 'ordersDetail.html';
    } else {
        alert("Your browser do not support this technology.");
    }
}