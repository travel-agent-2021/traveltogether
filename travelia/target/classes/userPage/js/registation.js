$("#register").on("click", function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var telephone = $("#telephone").val();
    var confirmPassword = $("#confirmPassword").val();
    if (password !== confirmPassword) {
        alert("两次输入密码不相同，请重试！");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/register",
        xhrFields: { withCredentials: true },
        data: {
            "telephone": telephone,
            "password": password,
            "username": username,
        },
        success: function(data) {
            if (data.status === "success") {
                alert("注册成功！");
                window.location.href = "login.html";
            }else {
                alert("注册失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("注册失败, " + data.responseText);
        }
    });

});