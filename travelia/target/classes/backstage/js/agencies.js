$(document).ready(function () {
    getAgencies();
});

function getAgencies() {
    var agencyList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/agency/getAllAgencies",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                agencyList = data.data;
                loadInfo(agencyList);
            }else {
                alert("获取信息失败01，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败02, " + data.responseText);
        }
    });
}


function loadInfo(agencyList) {
    if (agencyList === "" || agencyList == null) {
        return;
    }
    for (var i = 0; i < agencyList.length; i++) {
        var agency = agencyList[i];
        var agencyId = agency.agencyId;
        //alert(agencyId);

        var dom = '<tr>\n' +
            '                  <td>' + agency.agencyId + '</td>\n' +
            '                  <td>' + agency.agencyAccount + '</td>\n' +
            '                  <td>' + agency.encryptPassword + '</td>\n' +
            '                  <td>' + agency.agencyTitle + '</td>\n' +
            '                  <td>' + agency.agencyTelephone + '</td>\n' +
            '                  <td>' + agency.agencyEmail + '</td>\n' +
            '                  <td>' + agency.agencyAddress + '</td>\n' +
            '                  <td>' + agency.agencyImageSource + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + agencyId +')">修改</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteAgency(' + agencyId +')">删除</a></td>\n'+
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteAgency(agencyId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/agency/deleteAgency",
        xhrFields: { withCredentials: true },
        data : {
            "agencyId": agencyId
        },
        success: function(data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "agencies.html";
            }else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("删除, " + data.responseText);
        }
    });
}

function setAndEdit(agencyId) {
    if (window.localStorage) {
        localStorage.agencyId = agencyId;
        location.href = 'agenciesDetail.html';
    } else {
        alert("Your browser do not support this technology.");
    }
}