var myPlayer=null;
var page=1;
var shakeStatus = 0; 
var initTop = 0;
function scrollCallback(page,direction){}
function scrollEvent(){
    section_sum = $("section").length;
    direction = ($(window).scrollTop()>=initTop?1:0);
    initTop = $(window).scrollTop();
    for(i=1;i<=section_sum;i++){
        if(
            $(window).scrollTop()>=$("#section-"+i).offset().top-500 
            &&(
                $(window).scrollTop()<($("#section-"+(i+1))[0]? ($("#section-"+(i+1)).offset().top-500) : ($("body").height()))
            )
        ){
            scrollCallback(i,direction);

        }
    }
}
window.onscroll = scrollEvent;


function playVideo(src){
    $(".video-layer").html('<div class="video-wrap"><div class="video-header"><img src="http://static.event.mihoyo.com/bh3_homepage/images/common/close.png" class="close-btn" onclick=\'$(".video-layer").hide();myPlayer.dispose();$(".video-layer").html("");\'/></div><video id="pv-video" class="video-js" preload="none" controls width="1024" height="576"poster="" data-setup="{}"><source src="'+src+'" type=\'video/mp4\'><p class="vjs-no-js">avascript<a href="http://videojs.com/html5-video-support/" target="_blank">supports HTML5 video</a></p></video><div class="video-bottom"></div></div>');
    myPlayer = videojs('pv-video');
    $(".video-layer").show();
    myPlayer.play();
}

 function getQueryString(_name){
    var reg = new RegExp("(^|&)"+ _name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)
        return(unescape(r[2])); 
    return null;
}

function setCookie(_name,_value,_time)
{
    var Days = 30;
    var exp = new Date();
    if(!_time)
        exp.setTime(exp.getTime() + Days*24*60*60*1000);
    else
        exp.setTime(exp.getTime() + _time);
    document.cookie = _name + "="+ escape (_value) + ";expires=" + exp.toGMTString();
}

function delCookie(_name)
{
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(_name);
    if(cval!=null) document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}

function getCookie(_name)
{
    var arr = document.cookie.match(new RegExp("(^| )"+_name+"=([^;]*)(;|$)"));
    if(arr != null)
    return unescape(arr[2]);
    return null;
}

var isMobile = {  
      Android: function() {  
        return navigator.userAgent.match(/Android/i) ? true : false;  
      },  
      BlackBerry: function() {  
        return navigator.userAgent.match(/BlackBerry/i) ? true : false;  
      },  
      iOS: function() {  
        return navigator.userAgent.match(/iPhone|iPad|iPod/i) ? true : false;  
      },  
      Windows: function() {  
        return navigator.userAgent.match(/IEMobile/i) ? true : false;  
      },  
      any: function() {  
        return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Windows());  
      }  
    };  

if(getQueryString("origin")){
    setCookie("bh3_promote_origin",getQueryString("origin"));
    $("#code").qrcode({ 
          render: "canvas", 
          width: 200,  
          height: 200, 
          text: "http://"+window.location.host+"/pre_register/index.php?redirect=1&origin="+getQueryString("origin")+"&referrer="+(getCookie("bh3_promote_referrer")?getCookie("bh3_promote_referrer"):"").substr(0,80)
      }); 
    if(isMobile.any()){  
        $("#pre-register-link").attr("href","http://"+window.location.host+"/pre_register/index.php?redirect=1&origin="+getQueryString("origin")+"&referrer="+(getCookie("bh3_promote_referrer")?getCookie("bh3_promote_referrer"):"").substr(0,80));
        $("#pre-register-link").css("display","block");
    }  
}
else if(getCookie("bh3_promote_origin")){
    $("#code").qrcode({ 
          render: "canvas", 
          width: 200,  
          height: 200, 
          text: "http://"+window.location.host+"/pre_register/index.php?redirect=1&origin="+getCookie("bh3_promote_origin")+"&referrer="+(getCookie("bh3_promote_referrer")?getCookie("bh3_promote_referrer"):"").substr(0,80)
      }); 
    if(isMobile.any()){  
        $("#pre-register-link").attr("href","http://"+window.location.host+"/pre_register/index.php?redirect=1&origin="+getCookie("bh3_promote_origin")+"&referrer="+(getCookie("bh3_promote_referrer")?getCookie("bh3_promote_referrer"):"").substr(0,80));
        $("#pre-register-link").css("display","block");
    }  
}
else{
    $("#code").qrcode({ 
          render: "canvas", 
          width: 200, 
          height: 200, 
          text: "http://"+window.location.host+"/pre_register/index.php?redirect=1&origin=-1"+"&referrer="+(getCookie("bh3_promote_referrer")?getCookie("bh3_promote_referrer"):"").substr(0,80)
    }); 
    if(isMobile.any()){  
        $("#pre-register-link").attr("href","http://"+window.location.host+"/pre_register/index.php?redirect=1&origin=-1"+"&referrer="+(getCookie("bh3_promote_referrer")?getCookie("bh3_promote_referrer"):"").substr(0,80));
        $("#pre-register-link").css("display","block");
    }  
}

// $("#pre-register-btn").bind("click",function(){$('.contact-layer').fadeIn(200)});
// $("#news").bind("click",function(){$('.contact-layer').fadeIn(200)});
// $("#social-btn").bind("click",function(){$('.contact-layer').fadeIn(200)})

$(function () {
  $('.js-nav-title').each(function() {
    var i = 0;
    var targets = [];
    var titles = $(this).children('[data-for]').each(function() {
      var id = $(this).data('for')
      var t = $('#'+id)
      if (i++)
        t.hide();
      targets.push(t);
      $(this).click(function() {
        titles.removeClass('on')
        for (var i = targets.length - 1; i >= 0; i--) {
          targets[i].hide()
        }
        console.log($(targets));
        $('#'+$(this).data('for')).show();
        $(this).addClass('on')
      })
    });
    console.log($(targets).length);
  })
})