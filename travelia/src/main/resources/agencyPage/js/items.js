$(document).ready(function () {
    var fetchItemAgencyId = localStorage["storeItemAgencyId"];
    $("#agency_id").val(fetchItemAgencyId);
    console.log(fetchItemAgencyId);
    getItems(fetchItemAgencyId);
});


function getItems(fetchItemAgencyId) {
    var itemList = [];
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getItemByAgencyId",
        data: {"agencyId": fetchItemAgencyId},
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                itemList = data.data;
                loadInfo(itemList);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadInfo(itemList) {
    if (itemList == null || itemList === "") {
        return;
    }
    $("#dataTable tbody").empty();
    for (var i = 0; i < itemList.length; i++) {
        var item = itemList[i];
        var itemId = item.itemId;
        var imageSource = "";
        var rec = "";
        if (item.itemImageSources[0] == null) {
            imageSource = "image/default.png";
        } else {
            imageSource = itemList[i].itemImageSources[0].substring(57);
        }
        if (item.checkStatus === 0) {
            checkStatus = "未审核";
        } else if (item.checkStatus === 1) {
            checkStatus = "已审核";
        } else if (item.checkStatus === 2) {
            checkStatus = "未通过";
        }
        var dom = '<tr>\n' +
            '                  <td>' + item.itemId + '</td>\n' +
            '                  <td>' + checkStatus + '</td>\n' +
            '                  <td>' + item.itemName + '</td>\n' +
            '                  <td>' + item.itemCreateDate + '</td>\n' +
            '                  <td>' + item.itemPrice + '</td>\n' +
            '                  <td>' + item.duration + '</td>\n' +
            '                  <td>' + item.agencyTitle + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + itemId + ')">修改</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteItem(' + itemId + ')">删除</a></td>\n' +
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteItem(itemId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/deleteItem",
        xhrFields: {withCredentials: true},
        data: {
            "itemId": itemId
        },
        success: function (data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "items.html";
            } else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("删除, " + data.responseText);
        }
    });
}

function selectItems() {
    let agencyId = $("#agency_id").val();
    let status = $("#selectItemStatus").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getItemsByOptions",
        xhrFields: {withCredentials: true},
        data: {
            "agencyId": agencyId,
            "checkStatus": status
        },
        success: function (data) {
            if (data.status === "success") {
                loadInfo(data.data);
            }
        },
        error: function (data) {
        }
    });
}


function setAndEdit(itemId) {
    if (window.localStorage) {
        localStorage.itemId = itemId;
        window.location.href = 'itemsDetail.html';
    } else {
        alert("Your browser do not support this technology.");
    }
}