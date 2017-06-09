$(document).ready(function(){
	checkCookie();
	msgInit();
	PlateContent();
	ReplyContent();
})
function checkCookie()//检查cookie确认页面信息与登录人信息
{
	
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strs = url.split('=');
		$("#userId").html(strs[1]);
	}
	else{
		window.close();
	}


}
function msgInit(){//个人信息初始化
	
	var usermsg=checkLoginStatus();
	var username;
	if(usermsg){
		var temp = usermsg.split("=");
	 	username = temp[1];
	}
	else{
		username = null;
	}
	
	var dataName=$("#userId").html();
	if(username&&username==dataName)
	{
 		$.get("http://10.12.48.91:81/api/user/userinfo","name="+dataName,function(userData){//登陆者私人信息页面
 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			var personerObj = "<ul>密码<a id='passwdContent'></a></ul>"+"<ul>微信<a id='wechatContent'></a></ul>"
 			+"<ul>联系电话<a id='telnumContent'></a></ul>"+"<ul>权限<a id='powerContent'></a></ul>";
 			$("#nameTitle").after(personerObj);
 			$("#passwdContent").html(userData.passwd);
 			$("#wechatContent").html(userData.wechat);
 			$("#telnumContent").html(userData.telnum);
 			$("#powerContent").html(userData.power);
 			$("#introduceContent").html(userData.introduce);

 		})
 	}
 	else
 	{
 		
 		$.get("http://10.12.48.91:81/api/user/userinfo","name="+dataName,function(userData){//游客信息页面

 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			$("#introduceContent").html(userData.introduce);
 		})
 	}
 	setTimeout(function(){
 		window.ppageSum=1;
 		while(dataName&& !$("#userPlate").html("<a>无</a>")){
 			$.get("http://10.12.48.91:81/api/user/userpost","name="+dataName+"&page="+window.ppageSum,function(userData){
 				if(userData){
 					var pagination = "<span id='p_page_"+window.ppageSum+"'>"+window.ppageSum+"</span>";
 					$("#userPlate").after(paginnation);
 					if(window.rpageSum==1&&userData){
 						for(var i in userData){
 							var userPlateObj = "<a class='Plate'>"+userData[i]+"</a>";
 							$("#userPlate").append(userPlateObj);
 						}
 					}
 					window.ppageSum++;

 				}
 				else{
 					if(window.ppageSum==1){
 						$("#userPlate").append("<a>无</a>");
 					
 					}
 					

 				}
 			})
 		}
 	},0)
 	setTimeout(function(){
 		window.rpageSum=1;
 		while(dataName&& !$("#userReply").html("<a>无</a>")){
 			$.get("http://10.12.48.91:81/api/user/userreplay","name="+dataName+"&page="+window.rpageSum,function(userData){
 				if(userData){
 					var pagination = "<span id='r_page_"+window.rpageSum+"'>"+window.rpageSum+"</span>";
 					$("#userReply").after(paginnation);
 					if(window.rpageSum==1){
 						for(var i in userData){
 							var userReplyObj = "<a class='Reply'>"+userData[i]+"</a>";
 							$("#userReply").append(userReplyObj);
 						}
 					}
 					window.rpageSum++;

 				}
 				else{
 					if(window.rpageSum==1){
 						$("#userReply").append("<a>无</a>");
 					}
 					
 				}
 			})

 		}
 	},0)

 }
function PlateContent(){//发过的帖子初始化
	if(window.ppageSum==1) return;
	var clickNum = 1;
	for(var i in window.ppageSum){
		$("#p_page_"+i).click(function(){
			if(i==clickNum) return;
			else{
				$("#userPlate").remove(".Plate");
				$.get("http://10.12.48.91:81/api/user/userpost","name="+dataName+"&page="+i,function(userData){
					for(var i in userData){
						var userPlateObj = "<a  class='Plate'>"+userData[i]+"</a>";
						$("#userPlate").append(userPlateObj);
					}
				})
			}
		})
	}

}
function ReplyContent(){//收到的评论初始化
	if(window.rpageSum==1) return;
	var clickNum = 1;
	for(var i in window.rpageSum){
		$("#r_page_"+i).click(function(){
			if(i==clickNum) return;
			else{
				$("#userReply").remove(".Reply");
				$.get("http://10.12.48.91:81/api/user/userreplay","name="+dataName+"&page="+i,function(userData){
					for(var i in userData){
						var userReplyObj = "<a class='Reply'>"+userData[i]+"</a>";
						$("#userReply").append(userPlateObj);
					}
				})
			}
		})
	}

}
