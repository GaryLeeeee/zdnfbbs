$(document).ready(function(){
	userNameFormat();
	passwdFormat();
	passwdReapeat();
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
