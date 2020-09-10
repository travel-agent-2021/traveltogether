$(document).ready(function () {
    var itemId = localStorage["itemId"];
    // alert(userId);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getItemById",
        xhrFields: { withCredentials: true },
        data : {
            "itemId": itemId
        },
        success: function(data) {
            if (data.status === "success") {
                loadData(data.data);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
});


function loadData(data) {
    $("#itemId").val(data.itemId);
    $("#itemName").val(data.itemName);
    $("#price").val(data.itemPrice);
    $("#duration").val(data.duration);
    $("#minTourist").val(data.min_tourists);
    $("#maxTourist").val(data.max_tourists);
    $("#agencyId").val(data.agencyId);
    $("#agencyTitle").val(data.agencyTitle);
    $("#detail").val(data.itemDetail);
}

function updateItem() {
    var itemId =  $("#itemId").val();
    var itemName = $("#itemName").val();
    var price = $("#price").val();
    var duration = $("#duration").val();
    var minTourist = $("#minTourist").val();
    var maxTourist = $("#maxTourist").val();
    var agencyId =  $("#agencyId").val();
    var detail = $("#detail").val(data.itemDetail);

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/updateUser",
        xhrFields: { withCredentials: true },
        data: {
            "itemId": itemId,
            "itemName": itemName,
            "item_price": price,
            "duration": duration,
            "minTourist": minTourist,
            "maxTourist": maxTourist,
            "agencyId": agencyId,
            "itemDetail": detail
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！");
                window.location.href = "users.html";
            }else {
                alert("修改失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });
}