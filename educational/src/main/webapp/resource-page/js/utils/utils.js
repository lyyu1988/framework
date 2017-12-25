var utils=(function ($) {
    var path=window.location.pathname.split("/")[1],
        CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".split(""),
        menuId="tabMenuId";

    var uuid=function () {
        var chars = CHARS, uuid = new Array(36), rnd=0, r;
        for (var i = 0; i < 36; i++) {
            if (i==8 || i==13 ||  i==18 || i==23) {
                uuid[i] = '-';
            } else if (i==14) {
                uuid[i] = '4';
            } else {
                if (rnd <= 0x02) rnd = 0x2000000 + (Math.random()*0x1000000)|0;
                r = rnd & 0xf;
                rnd = rnd >> 4;
                uuid[i] = chars[(i == 19) ? (r & 0x3) | 0x8 : r];
            }
        }
        return uuid.join('').replace(/-/g,"");
    };

    var tabAdd=function (element,title,url,filter) {
        var id=uuid();
        var f="main-tab";
        if(filter && typeof filter=="string"){
            f=filter;
        }

        if(url.substr(0,1)!="/"){
            url="/"+url;
        }

        if(url.indexOf("?")==-1){
            url=url+"?";
        }else{
            url=url+"&";
        }

        url=url+menuId+"="+id;

        $.ajax({
            url:"/"+path+url,
            dataType:"html",
            success:function (data) {
                var content="<div id='"+id+"'>"+data+"</div>";
                element.tabAdd(f, {
                    title: title, //用于演示
                    content: content,
                    id: id
                });
                element.tabChange(f, id); //切换到：用户管理
            }
        });
    };

    return {tabAdd:tabAdd};
})(jQuery);
