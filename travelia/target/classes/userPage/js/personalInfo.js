$(document).ready(function () {
    checkLogin();
    let userId = window.localStorage.getItem("userId");
    $("#user_id").val(userId);
    initPersonalInfo(userId);
    initUserOrders(userId);
});

function checkLogin() {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/validateLogin",
        xhrFields: { withCredentials: true },
        success: function(data) {
            if (data.status === "success") {
            } else {
                alert("用户未登录");
                window.location.href = "login.html";
            }
        },
        error: function(data) {
            window.location.href = "login.html";
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function initPersonalInfo(userId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/getUserById",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                let user = data.data;
                loadPersonalInfo(user);
            } else {
                alert("请先登录");
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}

function loadPersonalInfo(user) {
    if (user == null) {
        return;
    }
    $("#user_name").text("用户名：" + user.username);
    $("#user_telephone").text("手机号：" + user.userTelephone);
    $("#username").val(user.username);
    console.log($("#gender").val());
    $("#gender").val(user.gender);
    console.log($("#gender").val());
    $("#birthday").val(user.birthday);
    $("#telephone").val(user.userTelephone);
    $("#email").val(user.userEmail);
    $("#userImageSource").val(user.userImageSource);

        if (userImageSource == null || userImageSource === ""){
            $("#user_image").attr("src", "img/test.jpg");
        } else {
            $("#user_image").attr("src", $("#userImageSource").val());
        }


}


$("#updateUserBtn").on("click", updateUser);

$("#showModal").on("click", function () {
    $("#myModal").modal('show');
});

$("#showPasswordModal").on("click", function () {
    $("#updatePasswordModal").modal('show');
});

function closeModal() {
    initPersonalInfo($("#user_id").val());
    $("#myModal").modal("hide");
}


function updateUser() {
    let userId = $("#user_id").val();
    let username = $("#username").val();
    let gender = $("#gender").val();
    let birthday = $("#birthday").val();
    let telephone = $("#telephone").val();
    let email = $("#email").val();
    let userImageSource = $("#userImageSource").val();


        var previewDom = document.getElementById("preview");

    previewDom.src =$("#userImageSource").val();

    console.log("final"+previewDom.src);
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/updateUser",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId,
            "username": username,
            "telephone": telephone,
            "password": "",
            "gender": gender,
            "birthday": birthday,
            "email": email,
            "userImageSource": userImageSource
        },
        success: function(data) {
            if (data.status === "success") {
                alert("更新成功！");
                $("#myModal").modal('hide');
                initPersonalInfo(userId);
            } else {
                alert("更新失败" + data.data.errMsg);
            }
        },
        error: function(data) {
            window.location.href = "login.html";
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function initUserOrders(userId) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/order/getOrdersByUserId",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId
        },
        success: function(data) {
            if (data.status === "success") {
                let orderList = data.data;
                loadUserOrders(orderList);
            } else {
                alert("请先登录");
            }
        },
        error: function(data) {
            alert("获取信息失败, " + data.responseText);
        }
    });
}


function loadUserOrders(orderList) {
    if (orderList == null) {
        return;
    }
    for (let i = 0; i < orderList.length; i++) {
        let order = orderList[i];
        let statusStr = "";
        if (order.orderStatus === 0) {
            statusStr = "已付款";
        } else if (order.orderStatus === 1) {
            statusStr = "未完成";
        } else if (order.orderStatus === 2) {
            statusStr = "已完成";
        } else if (order.orderStatus === 3) {
            statusStr = "已取消";
        }

        let dom = '<tr><td>' + order.orderId + '</td>\n' +
            '                                <td>' + statusStr + '</td>\n' +
            '                                <td>' + order.orderCreateDate + '</td>\n' +
            '                                <td>' + order.itemName + '</td>\n' +
            '                                <td>' + order.orderPrice + '</td>\n' +
            '                                <td>' + order.agencyTitle + '</td>' +
            '                                <td><a href="#" onclick="showOrderDetails(' + order.orderId + ')">详细信息</a></td></tr>';
        $("#dataTable tbody").append($(dom));
    }
}

$("#updatePasswordBtn").on("click", function () {
    let userId = $("#user_id").text();
    console.log("userid：" + userId);
    let password = $("#oldPassword").val();
    let newPassword = $("#newPassword").val();
    let confirmedNewPassword = $("#confirmNewPassword").val();
    if (newPassword !== confirmedNewPassword) {
        alert("两次密码不一致，请重试");
        return;
    }
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/user/updatePassword",
        xhrFields: { withCredentials: true },
        data: {
            "userId": userId,
            "password": password,
            "newPassword": newPassword
        },
        success: function(data) {
            if (data.status === "success") {
                alert("修改成功！,请重新登录");
                $("#updatePasswordModal").modal('hide');
                window.location.href = "login.html";
            } else {
                alert(data.data.errMsg);
            }
        },
        error: function(data) {
            alert("修改失败, " + data.responseText);
        }
    });
});


function showOrderDetails(orderId) {
    if (window.localStorage) {
        localStorage.userOrderId = orderId;
        window.location.href = 'orderDetails.html';
    } else {
        alert("Your browser does not support this technology.");
    }
}
 function readImage(){
    var fileDom = document.getElementById("file");
            var previewDom = document.getElementById("preview");
            fileDom.addEventListener("change", e=>{
                var file = fileDom.files[0];
                // check if input contains a valid image file
                if (!file || file.type.indexOf("image/") < 0) {
                    fileDom.value = "";
                    previewDom.src = "";
                    return;
                }

                // use FileReader to load image and show preview of the image
                var fileReader = new FileReader();
                fileReader.onload = e=>{
                    previewDom.src = e.target.result;
                };
                fileReader.readAsDataURL(file);
            });

            var formDom = document.getElementById("form");
console.log("fd"+formDom);
console.log("previewDom.src"+previewDom.src);
            function check() {
                var file = fileDom.files[0];
                // check if input contains a valid image file
                if (!file || file.type.indexOf("image/") < 0) {
                    return false;
                }

                return true;
            }

    }


function Download(){
        //cavas 保存图片到本地  js 实现
        //------------------------------------------------------------------------
        //1.确定图片的类型  获取到的图片格式 data:image/Png;base64,......
        var type ='png';//你想要什么图片格式 就选什么吧
        //var d=convertImageToCanvas("preview");
        var d=document.getElementById("preview");

         var canvas = document.createElement("canvas");
                          var rate = (d.width < d.height ? d.width / d.height : d.height / d.width) / 2;
                          canvas.width = d.width * rate;
                          canvas.height = d.height * rate;
                          canvas.getContext("2d").drawImage(d, 0, 0, d.width, d.height, 0, 0, d.width * rate, d.height * rate);

        var imgdata=canvas.toDataURL(type);
        //console.log(imgdata);
        //2.0 将mime-type改为image/octet-stream,强制让浏览器下载
        var fixtype=function(type){
            type=type.toLocaleLowerCase().replace(/jpg/i,'jpeg');
            var r=type.match(/png|jpeg|bmp|gif/)[0];
            return 'image/'+r;
        };
        //imgdata=imgdata.replace(fixtype(type),'image/octet-stream');
        //3.0 将图片保存到本地
        var savaFile=function(data,filename)
        {
            var save_link=document.createElementNS('http://www.w3.org/1999/xhtml', 'a');
            save_link.href=data;
            save_link.download=filename;
            var event=document.createEvent('MouseEvents');
            event.initMouseEvent('click',true,false,window,0,0,0,0,0,false,false,false,false,0,null);
            save_link.dispatchEvent(event);
        };
        var filename=''+new Date().getSeconds()+'.'+type;
        //我想用当前秒是可以解决重名的问题了 不行你就换成毫秒
        //savaFile(imgdata,filename,"image");
//console.log(imgdata);
        //alert("filename"+filename);
        $("#userImageSource").val(filename);

        //var saveImage = canvas.toDataURL('image/png');
        var b64 = imgdata.substring(22);

//console.log(b64);
        $.ajax({
        url: "http://localhost:8080/agency/saveImg",
        type:'post',
        data: { "pp": b64},
        success:function (data)
        {
        alert("图片上传成功");
        $("#userImageSource").val(data.data);

           // alert('保存成功');
        }

        });

//console.log(b64);
//console.log(pp);
        };
