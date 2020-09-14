$(document).ready(function () {
    var fetchItemAgencyId = localStorage["storeItemAgencyId"];
    console.log(fetchItemAgencyId);
    getItems(fetchItemAgencyId);
});


function getItems(fetchItemAgencyId) {
    var itemList = [];
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getItemByAgencyId",
        data: { "agencyId": fetchItemAgencyId},
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                itemList = data.data;
                loadInfo(itemList);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadInfo(itemList) {
    if (itemList == null || itemList === "") {
        return;
    }

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
        var dom = '<tr>\n' +
            '                  <td>' + item.itemId + '</td>\n' +
            '                  <td>' + item.itemName + '</td>\n' +
            '                  <td>' + item.itemCreateDate + '</td>\n' +
            '                  <td>' + item.itemPrice + '</td>\n' +
            '                  <td>' + item.duration + '</td>\n' +
            '                  <td>' + item.agencyTitle + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + itemId +')">修改</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteItem(' + itemId +')">删除</a></td>\n'+
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteItem(itemId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/deleteItem",
        xhrFields: { withCredentials: true },
        data : {
            "itemId": itemId
        },
        success: function(data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "items.html";
            }else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("删除, " + data.responseText);
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