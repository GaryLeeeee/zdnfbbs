$(document).ready(function(){
	checkCookie();
	plateInit();
	noticeInit();

})

function checkCookie(){//检查登录状态
	var userMsg=checkLoginStatus();

	var userName;
	if(userMsg){
	 	userName = userMsg;
	}
	else{
		userName = null;
	}
	if(userName){
		document.getElementById("form1").style.display="inline";
		$("#loginName").val(userName);
		$("#login_form").replaceWith(" ");
		$("#loginName").click(function(){//个人信息页面跳转
			window.open("/mypage?name="+userName);
		})
	}
	else{
		document.getElementById("form1").style.display="none";
		
			$("#login").click(function(){
			var loginName = $("input[name=username]").val();
			var loginPassWd = $("input[name=password]").val();
			$.get("http://10.12.45.102:81/api/user/istrue","name="+loginName+"&passwd="+loginPassWd,function(loginData){
				if(loginData){
					location.reload(true);
				}
				else{
					alert("账号或密码错误");
				}
			})
		})
		
		
		}
	}

