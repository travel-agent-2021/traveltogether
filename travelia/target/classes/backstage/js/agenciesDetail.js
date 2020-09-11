$(document).ready(function () {
    var agencyId = localStorage["agencyId"];
    // alert(agencyId);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/agency/getAgencyById",
        xhrFields: { withCredentials: true },
        data : {
            "agencyId": agencyId
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

    $("#agencyId").val(data.agencyId);
    $("#agencyAccount").val(data.agencyAccount);
    $("#password").val(data.encryptPassword);
    $("#agencyTitle").val(data.agencyTitle);
    $("#agencyTelephone").val(data.agencyTelephone);
    $("#agencyAddress").val(data.agencyAddress);
    $("#agencyEmail").val(data.agencyEmail);
    $("#agencyImageSource").val(data.agencyImageSource);


    var previewDom = document.getElementById("preview");

    console.log($("#agencyImageSource").val());
    console.log($("#agencyImageSource").val().substring(47));
previewDom.src =$("#agencyImageSource").val().substring(47);
    //previewDom.src = $("#agencyImageSource").val();



console.log("previewDom.src"+previewDom.src);

}

function updateAgency() {
    var agencyId = $("#agencyId").val();
    var agencyAccount = $("#agencyAccount").val();
    var password = $("#password").val();
    var agencyTitle = $("#agencyTitle").val();
    var agencyTelephone = $("#agencyTelephone").val();
    var agencyAddress = $("#agencyAddress").val();
    var agencyEmail = $("#agencyEmail").val();
    var agencyImageSource = $("#agencyImageSource").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/agency/updateAgency",
        xhrFields: { withCredentials: true },
        data: {
            "agencyId": agencyId,
            "agencyAccount": agencyAccount,
            "password":password,
            "agencyTitle": agencyTitle,
            "agencyTelephone": agencyTelephone,
            "agencyAddress": agencyAddress,
            "agencyEmail": agencyEmail,
            "agencyImageSource": agencyImageSource,
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！");
                window.location.href = "agencies.html";
            }else {
                alert("修改失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });

}