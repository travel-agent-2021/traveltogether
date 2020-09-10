$(document).ready(function () {
    getItems();
});


function getItems() {
    var itemList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/item/getAllItems",
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
    if (itemList === "" || userList == null) {
        return;
    }
    for (var i = 0; i < itemList.length; i++) {

    }
}