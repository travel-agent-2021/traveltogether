$("#addAgency").on("click", function () {
    var agencyAccount = $("#agencyAccount").val();
    var password = $("#password").val();
    var agencyTitle = $("#agencyTitle").val();
    var agencyTelephone = $("#agencyTelephone").val();
    var agencyAddress = $("#agencyAddress").val();
    var agencyEmail = $("#agencyEmail").val()
    var agencyImageSource = $("#agencyImageSource").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/agency/addAgency",
        xhrFields: { withCredentials: true },
        data: {
            "agencyAccount": agencyAccount,
            "password": password,
            "agencyTitle": agencyTitle,
            "agencyTelephone": agencyTelephone,
            "agencyAddress": agencyAddress,
            "agencyEmail": agencyEmail,
            "agencyImageSource": agencyImageSource
        },
        success: function(data) {
            if (data.status === "success") {
                alert("添加成功！");
                window.location.href = "agencies.html";
            }else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("添加失败, " + data.responseText);
        }
    });
});