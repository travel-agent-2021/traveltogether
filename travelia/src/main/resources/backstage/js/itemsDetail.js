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
    $("#itemPrice").val(data.itemPrice);
    $("#duration").val(data.duration);
    $("#minTourists").val(data.minTourists);
    $("#maxTourists").val(data.maxTourists);
    $("#agencyId").val(data.agencyId);
    $("#agencyTitle").val(data.agencyTitle);
    $("#detail").text(data.itemDetail);
}

function updateItem() {
    var itemId =  $("#itemId").val();
    var itemName = $("#itemName").val();
    var itemPrice = $("#itemPrice").val();
    var duration = $("#duration").val();
    var minTourists = $("#minTourists").val();
    var maxTourists = $("#maxTourists").val();
    var agencyId =  $("#agencyId").val();
    var detail = $("#detail").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/updateItem",
        xhrFields: { withCredentials: true },
        data: {
            "itemId": itemId,
            "itemName": itemName,
            "itemPrice": itemPrice,
            "duration": duration,
            "minTourists": minTourists,
            "maxTourists": maxTourists,
            "agencyId": agencyId,
            "itemDetail": detail
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！");
                window.location.href = "items.html";
            }else {
                alert("修改失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });
}