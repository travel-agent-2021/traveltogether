$(Document).ready(function () {
    validateLogin();
});

$("#logout").on("click", function () {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/agency/logout",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                window.location.href = "login.html";
            }else {
                alert("退出登录失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("退出登录失败, " + data.responseText);
        }
    });
    return false;
});

function validateLogin () {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/agency/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                var agency = data.data;
                $("#user-name").text("经销商：" + data.data.agencyAccount);
                storeId(data.data.agencyId);
            }else {
                if (data.data.errCode === 40003) {
                    window.location.href = "login.html";
                } else {
                     alert("获取信息失败，" + data.data.errMsg);
                }
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function storeId(storeAgencyId){
    if (window.localStorage) {
            localStorage.storeAgencyId = storeAgencyId;
        } else {
            alert("Your browser do not support this technology.");
        }
}