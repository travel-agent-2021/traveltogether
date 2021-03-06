$(document).ready(function () {
    getAgencyOptions();
});

function getAgencyOptions() {
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

function addItem() {
    let itemName = $("#itemName").val();
    let itemPrice = $("#itemPrice").val();
    let duration = $("#duration").val();
    let minTourists = $("#minTourists").val();
    let maxTourists = $("#maxTourists").val();
    let agencyId = $("#agencyId").val();
    let itemDetail = $("#itemDetail").val();
    let itemImageSources = $("#itemImageSources").val();
    console.log("addI iis" + itemImageSources);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/addItem",
        xhrFields: {withCredentials: true},
        data: {
            "itemName": itemName,
            "itemPrice": itemPrice,
            "duration": duration,
            "minTourists": minTourists,
            "maxTourists": maxTourists,
            "agencyId": agencyId,
            "itemDetail": itemDetail,
            "itemImageSources": itemImageSources,
            "checkStatus": 1
        },
        success: function (data) {
            if (data.status === "success") {
                alert("提交添加成功！");
                window.location.href = "items.html";
            } else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("添加失败, " + data.responseText);
        }
    });

}

function addItemImageResources() {

    var itemImageSources = $("#itemImageSources").val();
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/item/addItemImageSources",
        xhrFields: {withCredentials: true},
        data: {
            "itemImageSources": itemImageSources
        },
        success: function (data) {
            if (data.status === "success") {
                alert("添加成功！");
                window.location.href = "items.html";
            } else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function (data) {
            alert("添加失败, " + data.responseText);
        }
    });

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
    console.log("fd" + formDom);
    console.log("previewDom.src" + previewDom.src);

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
        }

    });

};
