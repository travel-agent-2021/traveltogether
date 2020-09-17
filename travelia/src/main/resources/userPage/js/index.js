$(document).ready(function () {
    getHottestItems();
    getLatestItems();
});


function getHottestItems() {
    var itemsList = [];
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getHottestItems",
        xhrFields: { withCredentials: true },
        data: {
            "agencyId": -1
        },
        success: function(data) {
            if (data.status === "success") {
                itemsList = data.data;
                loadHottest(itemsList);
            }else {
                alert("获取数据失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取数据失败, " + data.responseText);
        }
    });
}


function getLatestItems() {
    var itemsList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/item/getLatestItems",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                itemsList = data.data;
                loadLatest(itemsList);
            }else {
                alert("获取数据失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取数据失败, " + data.responseText);
        }
    });

}

function loadHottest(itemsList) {
    if (itemsList == null || itemsList === []) {
        return;
    }

    for (let i = 0; i < itemsList.length; i++) {
        let item = itemsList[i];
        let imageSource = "";

        if (item.itemImageSources == null || item.itemImageSources.length === 0) {
            imageSource = "assets/img/tmp/property-small-1.png";
        }else{
            imageSource = item.itemImageSources;
        }

        console.log("img"+imageSource);
        let dom = '<div class="property span3">\n' +
            '                <div class="image">\n' +
            '                    <div class="content">\n' +
            '                        <a href="#" onclick="getItemDetails(' + item.itemId + ')"></a>\n' +
            '                        <img src="' + imageSource +'" alt="" style="width:100%;height:208px">\n' +
            '                    </div>\n' +
            '                    <div class="price">￥' + item.itemPrice + '</div>\n' +
            '                </div>\n' +
            '            <div class="title">\n' +
            '                <h2><a href="#" onclick="getItemDetails(' + item.itemId + ')">' + item.itemName + '</a></h2>\n' +
            '            </div>\n' +
            '            <div class="location">旅行社：' + item.agencyTitle + '</div>\n' +
            '            <div class="area">\n' +
            '                <span class="key">天数：</span>\n' +
            '                <span class="value">' + item.duration + '</span>\n' +
            '            </div>\n' +
            '        </div>';
        $("#hottestItems").append($(dom));
    }
}

function loadLatest(itemsList) {
    if (itemsList == null || itemsList === []) {
        return;
    }
    for (var i = 0; i < itemsList.length; i++) {
        var item = itemsList[i];
        var imageSource = "";
        if (item.itemImageSources == null || item.itemImageSources.length === 0) {
            imageSource = "assets/img/tmp/property-small-1.png";
        }else{
                     imageSource = item.itemImageSources;
                 }
        var dom = '<div class="property">\n' +
            '                                                <div class="image">\n' +
            '                                                    <a href="#" onclick="getItemDetails(' + item.itemId + ')"></a>\n' +
            '                                                    <img src="' + imageSource + '" alt="" style="width:100%;height:80px">\n' +
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
        $("#latestItems").append($(dom));
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

