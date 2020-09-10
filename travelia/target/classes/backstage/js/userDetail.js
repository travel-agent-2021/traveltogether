$(document).ready(function () {
    var userId = localStorage["userId"];
    alert(userId);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/getUserById",
        xhrFields: { withCredentials: true },
        data : {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                loadData(data.data);
            }else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("删除, " + data.responseText);
        }
    });
});


function loadData(data) {



}