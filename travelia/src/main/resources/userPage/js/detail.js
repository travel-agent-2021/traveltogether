$(document).ready(function () {
    var itemId = getItemId();
    initItemInfo(itemId);
});

function getItemId() {
    var itemId = window.localStorage['itemId'];
    if (itemId == null) {
        window.location.href = "index.html";
        return 0;
    }
    // window.localStorage.removeItem('itemId');
    return itemId;
}

function removeItemId() {
    if (window.localStorage.getItem("itemId") != null) {
        window.localStorage.removeItem('itemId');
    }
}

function initItemInfo(itemId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getItemById",
        xhrFields: { withCredentials: true },
        data: {
            "itemId": itemId
        },
        success: function(data) {
            if (data.status === "success") {
                var item = data.data;
                setItemInfo(item);
            } else {
                alert("获取信息失败, " + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function setItemInfo(item) {
    $("#item_title").text(item.itemName);
    $("#item_title_1").text(item.itemName);
    $("#duration").text(item.duration + "天");
    $("#min_tourist").text(item.minTourists + "人");
    $("#max_tourist").text(item.maxTourists + "人");
    $("#agency_title").text(item.agencyTitle);
    $("#agency_telephone").text(item.agencyTelephone);
    $("#agency_address").text(item.agencyAddress);
    $("#total_order_times").text(item.totalOrderTimes + "件");
    $("#item_detail").text(item.itemDetail);
    $("#item_price").text("￥" + item.itemPrice);
    // to do
    // set image
}

// to do
function placeOrder() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                var user = data.data;
            } else {
                if (data.data.errorCode === 0) {
                    alert();
                }
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    })
}
