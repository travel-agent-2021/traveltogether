function addItem() {

    var itemName = $("#itemName").val();
    var itemPrice = $("#itemPrice").val();
    var duration = $("#duration").val();
    var minTourists = $("#minTourists").val();
    var maxTourists = $("#maxTourists").val();
    var agencyId = $("#agencyId").val()
    var itemDetail = $("#itemDetail").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/addItem",
        xhrFields: { withCredentials: true },
        data: {
            "itemName": itemName,
            "itemPrice": itemPrice,
            "duration": duration,
            "minTourists": minTourists,
            "maxTourists": maxTourists,
            "agencyId": agencyId,
            "itemDetail": itemDetail
        },
        success: function(data) {
            if (data.status === "success") {
                alert("添加成功！");
                window.location.href = "items.html";
            }else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("添加失败, " + data.responseText);
        }
    });

}