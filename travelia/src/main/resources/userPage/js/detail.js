$(document).ready(function () {
    let itemId = getItemId();
    initItemInfo(itemId);
    initRelatedItems(itemId);
});

function getItemId() {
    let itemId = window.localStorage['itemId'];
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
    console.log("src"+item.itemImageSources[0]);
    var previewDom = document.getElementById("big_image");
    var previewDom2 = document.getElementById("little-1");
     if (item.itemImageSources == null || item.itemImageSources.length === 0) {
                previewDom.src="assets/img/tmp/property-large-1.jpg";
                previewDom2.src="assets/img/tmp/property-large-1.jpg";
            }else{
                       previewDom.src =item.itemImageSources;
                       previewDom2.src =item.itemImageSources;
                  }
                  console.log(previewDom.src);

}

function initRelatedItems(itemId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getRelatedItems",
        xhrFields: { withCredentials: true },
        data: {
            "itemId": itemId
        },
        success: function(data) {
            if (data.status === "success") {
                let itemList = data.data;
                loadRelatedItems(itemList);
            } else {
                alert("获取信息失败, " + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadRelatedItems(itemList) {
    if (itemList == null || itemList.length === 0) {
        let dom = '<h3>无相关推荐</h3>';
        $("#relatedItems").append($(dom));
        return;
    }
    for (let i = 0; i < itemList.length; i++) {
        let item = itemList[i];
        let imageSource = "";
        if (item.itemImageSources == null || item.itemImageSources.length === 0) {
            imageSource = "assets/img/tmp/property-small-1.png";
        }else{
            imageSource = item.itemImageSources;
             }
        var dom = '<div class="property">\n' +
            '                                                <div class="image">\n' +
            '                                                    <a href="#" onclick="getItemDetails(' + item.itemId + ')"></a>\n' +
            '                                                    <img src="' + imageSource + '" alt="">\n' +
            '                                                </div>\n' +
            '                                                <div class="wrapper">\n' +
            '                                                    <div class="title">\n' +
            '                                                        <h3>\n' +
            '                                                            <a href="#" onclick="getItemDetails(' + item.itemId + ')">' + item.itemName + '</a>\n' +
            '                                                        </h3>\n' +
            '                                                    </div>\n' +
            '                                                    <div class="location">' + item.agencyTitle + '</div>\n' +
            '                                                    <div class="price">￥ ' + item.itemPrice + '</div>\n' +
            '                                                </div>\n' +
            '                                            </div>';
        $("#relatedItems").append($(dom));
    }
}

function getItemDetails(itemId) {
    if (window.localStorage) {
        localStorage.itemId = itemId;
        window.location.href = 'Detail.html';
    } else {
        alert("Your browser does not support this technology.");
    }
}


// to do
function placeOrder() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                let user = data.data;
                let itemPrice = $("#item_price").text().substring(1);
                let orderTravelers = $("#order_travelers").val();
                $("#order_price").text("订单总价：" + itemPrice * orderTravelers);
                $("#myModal").modal('show');
            } else {
                alert("请先登录");
                window.location.href = "login.html";
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    })
}

function success() {
    let itemId = getItemId();
    let userId = $("#user_id").text();
    let orderDetail = "";
    let orderTravelers = $("#order_travelers").val();
    let orderPrice = $("#order_price").text().substring(5);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/addOrder",
        xhrFields: { withCredentials: true },
        data: {
            "itemId": itemId,
            "userId": userId,
            "orderDetail": orderDetail,
            "orderTravelers": orderTravelers,
            "orderPrice": orderPrice
        },
        success: function(data) {
            if (data.status === "success") {
                alert("下单成功！");
                $("#myModal").modal("hide");
            } else {
                alert("下单失败！" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}
