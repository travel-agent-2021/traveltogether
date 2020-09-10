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

            var formDom = document.getElementById("agencyImageSource");
 console.log("before check");
            function check() {
                var file = fileDom.files[0];
                // check if input contains a valid image file
                if (!file || file.type.indexOf("image/") < 0) {
                    return false;
                }

                return true;
            }
console.log("after check");
            var img = file;
            img.addEventListener("submit",function(){
                    var imgCanvas = document.createElement('canvas');
                    imgContest = imgCanvas.getContext('2d');
                    //确保canvas元素的大小和图片尺寸一致
                    imgCanvas.width = this.width;
                    imgCanvas.height = this.height;

                    //渲染图片到canvas中
                    imgContest.drawImage(this,0,0,this.width,this.height);

                    //用data url的形式取出
                    var imgAsDataURL = imgCanvas.toDataURL("image");

                    //保存到本地存储中
                    try{
                        localStorage.setItem(key,imgAsDataURL);
                        console.log("save, " + file.type.indexOf("image/"));
                    }
                    catch(e){
                        console.log("Storage failed" + e);
                    }
                },false);
                img.src = src;


    }