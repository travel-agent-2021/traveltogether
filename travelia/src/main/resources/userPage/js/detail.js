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
function success() {
    alert("支付成功！");
}
