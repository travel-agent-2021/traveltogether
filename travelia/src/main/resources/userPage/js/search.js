$(document).ready(function () {
    let searchItemName = window.localStorage.getItem("searchItemName")
    initSearchItems(searchItemName);
    getLatestItems();
});

function initSearchItems(itemName) {
    $("#searchResult").empty();
    $("#hiddenResult").empty();
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
                initPagination(itemsList.length);
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
        }else{
            imageSource = item.itemImageSources;
              }

        let detail = "";
        if (item.itemDetail === "") {
            detail = "无描述信息";
        } else if (item.itemDetail.length < 30) {
            detail = item.itemDetail;
        } else {
            detail = item.itemDetail.substring(0, 31) + "...";
        }
        let dom = '<div class="property span9">\n' +
            '                                            <div class="row">\n' +
            '                                                <div class="image span3">\n' +
            '                                                    <div class="content">\n' +
            '                                                        <a onclick="getItemDetails(' + item.itemId + ')"></a>\n' +
            '                                                        <img src="' + imageSource + '" alt="" style="width:100%;height:208px">\n' +
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
            '                                                    <p>' + detail + '</p>\n' +
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
        $("#latestItems").append($(dom));
    }
}



$("#searchItems").on("click", searchItems);


function searchItems() {
    let itemName = $("#item_name").val();
    $("#searchResult").children().remove();
    initSearchItems(itemName);
}

function initPagination(length) {
    //创建分页
    var num_entries = length;
    console.log("length:" + length);

    let initPagination = function() {
        // 创建分页
        $("#Pagination").pagination(num_entries, {
            num_edge_entries: 1, //边缘页数
            num_display_entries: 4, //主体页数
            callback: pageselectCallback,
            items_per_page: 4 //每页显示4项
        });
    }();

    function pageselectCallback(page_index, jq) {
        let max_elem = Math.min((page_index + 1) * 4, num_entries);
        let prev_content = document.getElementById("searchResult").getElementsByClassName("property span9");
        let hiddenresult = document.getElementById("hiddenResult");
        let length = prev_content.length;
        // console.log(prev_content);
        console.log("prev: " + prev_content.length);
        for (let i = 0; i < length; i++) {
            //debugger;
            hiddenresult.appendChild(prev_content[0]);
        }
        // 获取加载元素
        for(let i = page_index * 4; i < max_elem; i++) {
            let content = document.getElementById("hiddenResult").getElementsByClassName("property span9")[0];
            if (content != null) {
                document.getElementById('searchResult').appendChild(content);
            }
        }
        return false;
    }
}
