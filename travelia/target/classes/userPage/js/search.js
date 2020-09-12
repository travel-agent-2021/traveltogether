
$(document).ready(function () {
    var pgindex = 1; //当前页
    var obj = document.getElementById("frameContent");  //获取内容层
    var pages = document.getElementById("pages");         //获取翻页层

    var allpages = Math.ceil(parseInt(obj.scrollHeight) / parseInt(obj.offsetHeight));//获取页面数量
    pages.innerHTML += '<li><a  id="last_page">上一页</a></li>';
    for (var i = 1; i <= allpages; i++) {
        pages.innerHTML += "<li><a  id='page"+i+"'>" + i + '</a></li>&nbsp;'; //循环输出第几页
        $("#page"+i).on("click", function(){
            showPage(i);
        });
    }
    pages.innerHTML += "<li> <a id='next_page'>下一页</a></li>";

    $("#next_page").on("click", function(){
        gotoPage(1);
    });
    $("#last_page").on("click", function(){
        gotoPage(-1);
    });

    function gotoPage(value){
        value === -1 ? showPage(pgindex - 1) : showPage(pgindex + 1);
    }

    function showPage(pageINdex) {
        obj.scrollTop = (pageINdex - 1) * parseInt(obj.offsetHeight);//根据高度，输出指定的页
        pgindex = pageINdex;
    }
});

