$(document).ready(function () {
    getItems();
    getAgencies();
});

function getAgencies() {
    var agencyList = [];
    $.ajax(
        {
            type: "GET",
            url: "http://localhost:8080/agency/getAllAgencies",
            xhrFields: {withCredentials: true},
            success: function (data) {
                if (data.status === "success") {
                    agencyList = data.data;
                    loadAgencies(agencyList);
                } else {
                    alert("获取信息失败01，" + data.data.errMsg);
                }
            },
            error: function (data) {
                alert("获取信息失败02, " + data.responseText);
            }
        }
    )

}

function loadAgencies(agencyList) {
    if (agencyList === "" || agencyList == null) {
        return;
    }
    for (var i = 0; i < agencyList.length; i++) {
        var agency = agencyList[i];
        var agencyName = agency.agencyTitle;
        var agencyID = agency.agencyId;

        var dom = '<option value = "' + agencyID + '">' + agencyName + '</option>';
        $("#selectAgency").append($(dom));
    }
}


function getItems() {
    var itemList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/item/getAllItems",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
                itemList = data.data;
                loadInfo(itemList);
            }else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadInfo(itemList) {
    if (itemList == null || itemList === "") {
        return;
    }

    for (let i = 0; i < itemList.length; i++) {
        let item = itemList[i];
        let itemId = item.itemId;
        let checkStatus = "";
        if (item.checkStatus === 0) {
            checkStatus = "未审核";
        } else {
            checkStatus = "已审核";
        }
        let dom = '<tr>\n' +
            '                  <td>' + item.itemId + '</td>\n' +
            '                  <td>' + checkStatus + '</td>\n' +
            '                  <td>' + item.itemName + '</td>\n' +
            '                  <td>' + item.itemCreateDate + '</td>\n' +
            '                  <td>' + item.itemPrice + '</td>\n' +
            '                  <td>' + item.duration + '</td>\n' +
            '                  <td>' + item.agencyTitle + '</td>\n' +
            '                  <td><a class="btn btn-primary"  href="#" onclick="setAndEdit(' + itemId +')">修改</a>\n' +
            '                      <a class="btn btn-warning"  href="#" onclick="deleteItem(' + itemId +')">删除</a></td>\n'+
            '                </tr>';
        $("#dataTable tbody").append($(dom));
    }
    $("#dataTable").DataTable();
}

function deleteItem(itemId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/deleteItem",
        xhrFields: { withCredentials: true },
        data : {
            "itemId": itemId
        },
        success: function(data) {
            if (data.status === "success") {
                alert("删除成功！");
                window.location.href = "items.html";
            }else {
                alert("删除失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("删除, " + data.responseText);
        }
    });
}
function selectItems() {
    let agencyId = $("#selectAgency").val();
    let checkStatus = $("#selectItemStatus").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getOrdersByOptions",
        xhrFields: {withCredentials: true},
        data: {
            "agencyId": agencyId,
            "checkStatus": checkStatus
        },
        success: function (data) {
            if (data.status === "success") {
                loadInfo(data.data);
            } else {
                //
            }
        },
        error: function (data) {
            alert(data.responseText);
        }
    });
}
function setAndEdit(itemId) {
    if (window.localStorage) {
        localStorage.itemId = itemId;
        window.location.href = 'itemsDetail.html';
    } else {
        alert("Your browser do not support this technology.");
    }
}