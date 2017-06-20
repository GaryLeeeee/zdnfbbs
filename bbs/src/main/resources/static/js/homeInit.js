$(document).ready(function(){

	checkCookie();
	//plateInit();
	//noticeInit();

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
		//document.getElementById("form1").style.display="inline";
		//$("#loginName").text(userName);
		//$.get("api/user/getid","name="+userName,function(userId){
		//	$("#headphoto").attr('href',"api/user/img?id="+userId); 
		//})
		//$("#login_form").replaceWith(" ");
		$("#loginName").attr('href',"mypage?name="+escape(userName)); 	
	}
	else{
		//document.getElementById("form1").style.display="none";
		
		}
	}

