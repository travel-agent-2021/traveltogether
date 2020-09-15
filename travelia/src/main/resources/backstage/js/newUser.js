$("#addUser").on("click", function () {
    var username = $("#username").val();
    var password = $("#password").val();
    var telephone = $("#telephone").val();
    var age = $("#age").val();
    var gender = $("#gender").val();
    var birthday = $("#birthday").val()
    var email = $("#email").val();
    var userImageSource = $("#userImageSource").val();

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
            "email": email,
            "userImageSource":userImageSource
        },
        success: function(data) {
            if (data.status === "success") {
                alert("添加成功！");
                window.location.href = "users.html";
            }else {
                alert("添加失败，" + data.data.errMsg);
            }
        },
        error: function(data) {
            alert("添加失败, " + data.responseText);
        }
    });
});


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
            canvas.width = d.width;
            canvas.height = d.height;
            // 坐标(0,0) 表示从此处开始绘制，相当于偏移。
            canvas.getContext("2d").drawImage(d, 0, 0);

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
