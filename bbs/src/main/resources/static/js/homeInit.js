$(document).ready(function(){
	checkCookie();
	plateInit();
	noticeInit();
})

function checkCookie(){//检查登录状态
	var userMsg=checkLoginStatus();
	var userName;
	if(userMsg){
		var temp = userMsg.split("=");
	 	userName = temp[1];
	}
	else{
		userName = null;
	}
	if(userName){
		document.getElementById("button").style.display="inline";
		var userNameObj = "<div id='skipping' >"+userName+"</div>";
		$("#login_form").replaceWith(userNameObj);
		$("#personnalMsg").click(function(){//个人信息页面跳转
			window.location.href="./mypage.html?name="+userName;
		})
	}
	else{
		document.getElementById("button").style.display="none";
		$("#login").click(function(){
			var loginName = $("input[name=username]").val();
			var loginPassWd = $("input[name=password]").val();
			console.log(loginName);
			$.get("http://10.12.38.53:81/api/user/istrue","name="+loginName+"&passwd="+loginPassWd,function(loginData){
				console.log(loginData);
				if(loginData){
					document.cookie = "ZDNF_name="+loginName;
					setCookie(loginName,loginPassWd);
					$("#login_form").remove();
					$("#loginName").val(loginName);
				}
				else{
					return;
				}
			})
		})
		}
	}

