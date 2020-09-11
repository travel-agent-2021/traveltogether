$("#login").on("click", function () {
    var telephone = $("#telephone").val();
    var password = $("#password").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/login",
        xhrFields: { withCredentials: true },
        data: {
            "telephone": telephone,
            "password": password
        },
        success: function(data) {
            if (data.status === "success") {
                window.location.href = "index.html";
            }else {
                alert("登录失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("登录失败, " + data.responseText);
        }
    });
});
