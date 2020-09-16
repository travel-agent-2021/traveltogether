$(document).ready(function () {
    getOrders();
    getAgencies();
});

function getAgencies() {
    var agencyList = [];
    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/agency/getAllAgencies",
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.status === "success") {
                    agencyList = data.data;
                    loadAgencies(agencyList);
                } else {
                    alert("获取信息失败01，" + data.data.errMsg);
                }
            },
            error: function (data) {
                alert("获取信息失败02, " + data.responseText);
            }
        }
    )

}

function getOrders() {
    var orderList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/order/getAllOrders",
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                orderList = data.data;
                loadInfo(orderList);
            } else {
                alert("获取信息失败01，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("获取信息失败02, " + data.responseText);
        }
    });
}

function loadAgencies(agencyList) {
    if (agencyList === "" || agencyList == null) {
        return;
    }
    for (var i = 0; i < agencyList.length; i++) {
        var agency = agencyList[i];
        var agencyName = agency.agencyTitle;
        var agencyID = agency.agencyId;

        var dom = '<option value = "' + agencyID + '">' + agencyName + '</option>';
        $("#selectAgency").append($(dom));
    }
}

function loadInfo(orderList) {
    if (orderList === "" || orderList == null) {
        return;
    }
    $("#dataTable tbody").empty();
    for (let i = 0; i < orderList.length; i++) {
        let order = orderList[i];
        let orderId = order.orderId;
        //alert(orderId);
        let orderStatus = "";
        if (order.orderStatus === 0) {
            orderStatus = "已付款";
        } else if (order.orderStatus === 1) {
            orderStatus = "进行中";
        } else if (order.orderStatus === 2) {
            orderStatus = "已完成";
        } else if (order.orderStatus === 3) {
            orderStatus = "已取消";
        }

        let dom = '<tr>\n' +
            '                  <td>' + order.orderId + '</td>\n' +
            '                  <td>' + order.orderCreateDate + '</td>\n' +
            '                  <td>' + order.orderPrice + '</td>\n' +
            '                  <td>' + orderStatus + '</td>\n' +
            '                  <td>' + order.username + '</td>\n' +
            '                  <td>' + order.agencyTitle + '</td>\n' +
            '                  <td>' + order.orderTravelers + '</td>\n' +
            '                  <td>' + order.orderDetail + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + orderId + ')">查看</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteOrder(' + orderId + ')">删除</a></td>\n' +
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteOrder(orderId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/deleteOrder",
        xhrFields: {withCredentials: true},
        data: {
            "orderId": orderId
        },
        success: function (data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "orders.html";
            } else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("删除, " + data.responseText);
        }
    });
}

function selectOrders() {
    let agencyId = $("#selectAgency").val();
    let orderStatus = $("#selectOrderStatus").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getOrdersByOptions",
        xhrFields: {withCredentials: true},
        data: {
            "agencyId": agencyId,
            "orderStatus": orderStatus
        },
        success: function (data) {
            if (data.status === "success") {
                loadInfo(data.data);
            } else {
                //
            }
        },
        error: function (data) {
            alert(data.responseText);
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