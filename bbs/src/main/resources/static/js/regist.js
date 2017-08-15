$(document).ready(function(){
	userNameFormat();
	passwdFormat();
	passwdReapeat();
	telNumFormat();
	registing();
})
function userNameFormat(){
	$("input[name='name']").blur(function(){
		var rName = $("input[name='name']").val();
		if(rName.length<2||rName.length>10){
			$("#useNameRepeat").css('display','inline-block');
		}
		else{
			$.post("/api/user/getid","name="+rName,function(msg){
				if(isNull(msg)){
					$("#useNameRepeat").css('display','none');
					return;
				}
				else{
					$("#useNameRepeat").text("用户名已存在");
					$("#useNameRepeat").css('display','inline-block');
				}
			})
		}
	})
}

function passwdFormat(){
	
	$("input[name='passwd']").blur(function(){
		var rPasswd = $("input[name='passwd']").val();
		if(rPasswd.length<8||rPasswd.length>20){
			$("#passwdLength").css('display','inline-block');
		}
		else{
			$("#passwdLength").css('display','none');
		}
})
}

function passwdReapeat(){
	
	$("input[name='rePasswd']").blur(function(){
		var rPasswd = $("input[name='rePasswd']").val();
		if(rPasswd.length<8||rPasswd.length>20){
			$("#passwdRepeat").css('display','inline-block');
		}
		else{
			$("#passwdRepeat").css('display','none');
		}
})
}
function telNumFormat(){
	
	$("input[name='telnum']").blur(function(){
		var telNum = $("input[name='telnum']").val();
		if(telNum.length<11){
			$("#phoneLength").css('display','inline-block');
			$("#phoneLength").text("手机号填写错误");
		}
		else{
			$("#phoneLength").css('display','none');
		}
		telNumRepeat(function(flag){
			if(flag){$("#phoneLength").css('display','none');}
			else{
				$("#phoneLength").css('display','inline-block');
				$("#phoneLength").text("手机号已被注册");
			}
		})
})
}
function telNumRepeat(callback){
	var telNum = $("input[name='telnum']").val();
	$.post("/message/exist","tel="+telNum,function(msg){
		if(isNull(msg)){
			callback(true);
		}
		else callback(false);
		
	})
}
function getVer(){
	var telNum = $("input[name='telnum']").val();
	telNumRepeat(function(flag){
	if(telNum.length<11){
			$("#phoneLength").css('display','inline-block');
			$("#phoneLength").text("手机号填写错误");
		}
	else if(!flag){
				$("#phoneLength").css('display','inline-block');
				$("#phoneLength").text("手机号已被注册");
	}
	else{
		var times=60;
		$("#getVerify").attr("onclick","return;");
		$.post("/message/send","telnum="+telNum,function(msg){return;});
		$("#getVerify").text(times);
		$("#getVerify").css("background-color","#c6c6c6");
		var interval =setInterval(function(){
			times = parseInt(times)-1;
			$("#getVerify").text(times);
			if(times==0){
				$("#getVerify").text("获取验证码");
				$("#getVerify").css("background-color","#F18C73");
				$("#getVerify").attr("onclick","getVer()");
				 clearInterval(interval);
			}
		},1000);
	}
	})
}
function registing(){
	$(".warning-info").each(function(){
		if($(this).css("display")=="none"){
		$("#registing").css("background-color","#F18C73");
	}
	else{
		$("#registing").css("background-color","#c6c6c6");
	}
	})
}
