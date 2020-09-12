
$(document).ready(function () {
    initSearchItems("");
    getLatestItems();
    /*var pgindex = 1; //当前页
    var obj = document.getElementById("frameContent");  //获取内容层
    var pages = document.getElementById("pages");         //获取翻页层

    var allpages = Math.ceil(parseInt(obj.scrollHeight) / parseInt(obj.offsetHeight));//获取页面数量
    pages.innerHTML += '<li><a  id="last_page">上一页</a></li>';
    for (var i = 1; i <= allpages; i++) {
        pages.innerHTML += "<li><a  id='page" + i + "'>" + i + '</a></li>'; //循环输出第几页
        $("#page" + i).on("click", function(){
            showPage(i);
        });
    }
    pages.innerHTML += "<li> <a id='next_page'>下一页</a></li>";

    $("#next_page").on("click", function(){
        gotoPage(1);
    });
    $("#last_page").on("click", function(){
        gotoPage(-1);
    });

    function gotoPage(value){
        value === -1 ? showPage(pgindex - 1) : showPage(pgindex + 1);
    }

    function showPage(pageINdex) {
        obj.scrollTop = (pageINdex - 1) * parseInt(obj.offsetHeight);//根据高度，输出指定的页
        pgindex = pageINdex;
    }*/
});

function initSearchItems(itemName) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/searchItemsByItemName",
        xhrFields: { withCredentials: true },
        data: {
            "itemName": itemName
        },
        success: function(data) {
            if (data.status === "success") {
                let itemsList = data.data;
                loadSearchItems(itemsList);
            } else {
                alert("获取数据失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取数据失败, " + data.responseText);
        }
    })
}

function loadSearchItems(itemList) {
    if (itemList == null || itemList.length === 0) {
        let dom = '<h3>无相关商品</h3>';
        $("#searchResult").append($(dom));
        return;
    }
    for (let i = 0; i < itemList.length; i++) {
        let item = itemList[i];
        let imageSource = "";
        if (item.itemImageSources == null || item.itemImageSources.length === 0) {
            imageSource = "assets/img/tmp/property-small-1.png";
        }
        let dom = '<div class="property span9">\n' +
            '                                            <div class="row">\n' +
            '                                                <div class="image span3">\n' +
            '                                                    <div class="content">\n' +
            '                                                        <a onclick="getItemDetails(' + item.itemId + ')"></a>\n' +
            '                                                        <img src="' + imageSource + '" alt="">\n' +
            '                                                    </div>\n' +
            '                                                </div>\n' +
            '                                                <div class="body span6">\n' +
            '                                                    <div class="title-price row">\n' +
            '                                                        <div class="title span4">\n' +
            '                                                            <h2><a onclick="getItemDetails(' + item.itemId + ')">' + item.itemName + '</a></h2>\n' +
            '                                                        </div>\n' +
            '                                                        <div class="price">￥' + item.itemPrice + '</div>\n' +
            '                                                    </div>\n' +
            '                                                    <div class="location">' + item.agencyTitle + '</div>\n' +
            '                                                    <p>Etiam at ante id enim dictum posuere id vel est. Praesent at\n' +
            '                                                        massa quis risus cursus tristique vel non orci. Phasellus ut\n' +
            '                                                        nisi non odio</p>\n' +
            '                                                    <div class="area">\n' +
            '                                                        <span class="key">销量</span>\n' +
            '                                                        <span class="value">' + item.totalOrderTimes + '</span>\n' +
            '                                                    </div>\n' +
            '                                                </div>\n' +
            '                                            </div>\n' +
            '                                        </div>';
        $("#searchResult").append($(dom));
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


function loadLatest(itemsList) {
    if (itemsList == null || itemsList === []) {
        return;
    }
    for (var i = 0; i < itemsList.length; i++) {
        var item = itemsList[i];
        var imageSource = "";
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
        $("#latestItems").append($(dom));
    }
}



$("#searchItems").on("click", searchItems);


function searchItems() {
    let itemName = $("#item_name").val();
    $("#searchResult").children().remove();
    initSearchItems(itemName);
}

window.onkeypress = function (ev) {
    if (ev.code === "13") {
        ev.cancleBubble = true;
        ev.returnValue = false;
        searchItems();
        return false;
    }
};