function setCookie(username,password)
{	var deadLine = new Date();
	deadLine.setTime(deadLine.getTime()+30*24*60*60*1000);
	document.cookie="ZDNFBBS_userName="+escape(username)+"&"+"ZDNFBBS_passWd="+escape(password)+";"+"expires="+deadLine.toGMTString(); 
}

function GetCookieValue(name) {
  var cookieValue = null;
  if (document.cookie && document.cookie != '') {
 var cookies = document.cookie.split(';');
 for (var i = 0; i < cookies.length; i++) {
   var cookie = jQuery.trim(cookies[i]);
   if (cookie.substring(0, name.length + 1) == (name + '=')) {
 cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
 break;
   }
 }
  }
  return cookieValue;
}
function DelCookie(name) {
  var exp = new Date();
  exp.setTime(exp.getTime() + (-1 * 24 * 60 * 60 * 1000));
  var cval = GetCookieValue(name);
  document.cookie = name + "=" + cval + "; expires=" + exp.toGMTString();
}
function checkLoginStatus(){
	var result;
    $.ajax({
        url : 'api/user/user',
        async:false,
        success : function(userStatus){
        	if(!isNull(userStatus)&&userStatus!="false"){
        		result = userStatus;
        	}
        	else result =false;
        }
    })
    return result;
	}
