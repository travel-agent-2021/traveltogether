$("#addUser").on("click", function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var telephone = $("#telephone").val();
    var age = $("#age").val();
    var gender = $("#gender").val();
    var birthday = $("#birthday").val()
    var email = $("#email").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/addUser",
        xhrFields: { withCredentials: true },
        data: {
            "username": username,
            "password": password,
            "telephone": telephone,
            "age": age,
            "gender": gender,
            "birthday": birthday,
            "email": email
        },
        success: function(data) {
            if (data.status === "success") {
                alert("添加成功！");
                window.location.href = "login.html";
            }else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("添加失败, " + data.responseText);
        }
    });
});