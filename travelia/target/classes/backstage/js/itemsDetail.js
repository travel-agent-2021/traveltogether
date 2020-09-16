$(document).ready(function () {
    initAgencyOptions();
    initData();
});

function initData() {
    var itemId = localStorage["itemId"];
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/getItemById",
        xhrFields: {withCredentials: true},
        data: {
            "itemId": itemId
        },
        success: function (data) {
            if (data.status === "success") {
                console.log("itemImageSources" + data.data.itemImageSources);
                loadData(data.data);
            } else {
                alert("获取信息失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function loadData(data) {
    $("#itemId").text(data.itemId);
    $("#itemName").val(data.itemName);
    $("#itemPrice").val(data.itemPrice);
    $("#duration").val(data.duration);
    $("#minTourists").val(data.minTourists);
    $("#maxTourists").val(data.maxTourists);
    $("#agencyId").val(data.agencyId);
    $("#agencyTitle").val(data.agencyTitle);
    $("#detail").text(data.itemDetail);
    $("#itemImageSources").val(data.itemImageSources);
    if (data.checkStatus === 0) {
        visibilityShowUI();
        $("#itemHeader").text("商品详情 （未审核）");
    } else if (data.checkStatus === 1) {
        visibilityHideUI();
        $("#itemHeader").text("商品详情 （已审核）");
    } else if (data.checkStatus === 2) {
        visibilityHideUI();
        $("#itemHeader").text("商品详情 （未通过）");
    }

    var previewDom = document.getElementById("preview");

    previewDom.src = $("#itemImageSources").val();
    console.log("final" + previewDom.src);
}

function visibilityHideUI() {
    var ui = document.getElementById("checkBtn1");
    ui.style.visibility = "hidden";
    var ui2 = document.getElementById("checkBtn2");
    ui2.style.visibility = "hidden";
}


function visibilityShowUI() {
    var ui = document.getElementById("checkBtn1");
    ui.style.visibility = "visible";
    var ui2 = document.getElementById("checkBtn2");
    ui2.style.visibility = "visible";
}


function updateItem() {
    var itemId = $("#itemId").val();
    var itemName = $("#itemName").val();
    var itemPrice = $("#itemPrice").val();
    var duration = $("#duration").val();
    var minTourists = $("#minTourists").val();
    var maxTourists = $("#maxTourists").val();
    var agencyId = $("#agencyId").val();
    var detail = $("#detail").val();
    var itemImageSources = $("#itemImageSources").val();

    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/updateItem",
        xhrFields: {withCredentials: true},
        data: {
            "itemId": itemId,
            "itemName": itemName,
            "itemPrice": itemPrice,
            "duration": duration,
            "minTourists": minTourists,
            "maxTourists": maxTourists,
            "agencyId": agencyId,
            "itemDetail": detail,
            "itemImageSources": itemImageSources
        },
        success: function (data) {
            if (data.status === "success") {
                alert("修改成功！");
                window.location.href = "items.html";
            } else {
                alert("修改失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("修改失败, " + data.responseText);
        }
    });
}


function initAgencyOptions() {
    var agencyList = [];
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/agency/getAllAgencies",
        xhrFields: {withCredentials: true},
        success: function (data) {
            if (data.status === "success") {
                agencyList = data.data;
                loadAgencyOptions(agencyList);
            } else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("添加失败, " + data.responseText);
        }

    });
}

function loadAgencyOptions(agencyList) {
    if (agencyList == null || agencyList.length === 0) {
        return;
    }
    for (let i = 0; i < agencyList.length; i++) {
        let agency = agencyList[i];
        let dom = '<option value ="' + agency.agencyId + '">' + agency.agencyTitle + '</option>';
        $("#agencyId").append($(dom));
    }
}


function readImage() {
    var fileDom = document.getElementById("file");
    var previewDom = document.getElementById("preview");
    fileDom.addEventListener("change", e => {
        var file = fileDom.files[0];
        // check if input contains a valid image file
        if (!file || file.type.indexOf("image/") < 0) {
            fileDom.value = "";
            previewDom.src = "";
            return;
        }

        // use FileReader to load image and show preview of the image
        var fileReader = new FileReader();
        fileReader.onload = e => {
            previewDom.src = e.target.result;
        };
        fileReader.readAsDataURL(file);
    });

    var formDom = document.getElementById("form");

    function check() {
        var file = fileDom.files[0];
        // check if input contains a valid image file
        if (!file || file.type.indexOf("image/") < 0) {
            return false;
        }

        return true;
    }

}


function Download() {
    //cavas 保存图片到本地  js 实现
    //------------------------------------------------------------------------
    //1.确定图片的类型  获取到的图片格式 data:image/Png;base64,......
    var type = 'png';
    //var d=convertImageToCanvas("preview");
    var d = document.getElementById("preview");


    var canvas = document.createElement("canvas");
    if (d.width >= 598 || d.height >= 598) {
        //var rate = (d.width < d.height ? d.width / d.height : d.height / d.width) / 2;
        var rate = (590 / d.width);
        canvas.width = d.width * rate;
        canvas.height = d.height * rate;
        canvas.getContext("2d").drawImage(d, 0, 0, d.width, d.height, 0, 0, d.width * rate, d.height * rate);
    } else {
        canvas.width = d.width;
        canvas.height = d.height;
        canvas.getContext("2d").drawImage(d, 0, 0);
    }


    var imgdata = canvas.toDataURL(type);
    //console.log(imgdata);
    //2.0 将mime-type改为image/octet-stream,强制让浏览器下载
    var fixtype = function (type) {
        type = type.toLocaleLowerCase().replace(/jpg/i, 'jpeg');
        var r = type.match(/png|jpeg|bmp|gif/)[0];
        return 'image/' + r;
    };
    //imgdata=imgdata.replace(fixtype(type),'image/octet-stream');
    //3.0 将图片保存到本地
    var savaFile = function (data, filename) {
        var save_link = document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
        save_link.href = data;
        save_link.download = filename;
        var event = document.createEvent('MouseEvents');
        event.initMouseEvent('click', true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);
        save_link.dispatchEvent(event);
    };
    var filename = '' + new Date().getSeconds() + '.' + type;
    //savaFile(imgdata,filename,"image");
    console.log("filename" + filename);
    $("#itemImageSources").val(filename);

    //var saveImage = canvas.toDataURL('image/png');
    var b64 = imgdata.substring(22);


    $.ajax({
        url: "http://localhost:8080/agency/saveImg",
        type: 'post',
        data: {"pp": b64},
        success: function (data) {
            alert("图片上传成功");
            $("#itemImageSources").val(data.data);
            // alert('保存成功');
        }

    });

}


function checkItem(status) {
    let itemId = $("#itemId").text().substring(5);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/checkItem",
        xhrFields: {withCredentials: true},
        data: {
            "itemId": itemId,
            "checkStatus": status
        },
        success: function (data) {
            if (data.status === "success") {
                alert("审核成功");
                window.location.href = "items.html";
            } else {
                alert("审核失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            //alert("添加失败, " + data.responseText);
        }
    });
}