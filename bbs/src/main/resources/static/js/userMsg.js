$(document).ready(function(){
	
		checkCookie();
		msgInit();
		PlateContent(1);
		ReplyContent(1);
		HeadSetting();
	
	
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
		
		username = usermsg;
	}
	else{
		username = null;
	}
	
	var dataName=$("#userId").html();
	if(username&&username==dataName)
	{
 		$.get("/api/user/userinfo","name="+dataName,function(userData){//登陆者私人信息页面
 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			var personerObj = "<ul>密码<a id='passwdContent'></a></ul>"+"<ul>微信:<a id='wechatContent'></a></ul>"
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
 		
 		$.get("/api/user/userinfo","name="+dataName,function(userData){//游客信息页面

 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			$("#introduceContent").html(userData.introduce);
 		})
 	}


 }
function PlateContent(page){//发过的帖子初始化
	var dataName=$("#userId").html();
	$("#userPlate").remove(".Plate");
	$.get("/api/user/userpost","name="+dataName+"&page="+page,function(userData){
		if(userData){
			for(var i in userData){
				var userPlateObj = "<a id='postEver"+i+"' class='Plate'>"+userData[i].title+"</a></br>";
				$("#userPlate").append(userPlateObj);
			}
			var allObj=[];
			for(var i=0;i<userData.length;i++){
				allObj[i]=$("#postEver"+i);
			}
			$.each(allObj, function(i){
				$(this).click(function(){
					
					window.open("./post?id="+userData[i].id);
				})
			})
		}
		else{
			$("#userPlate").append("<p>无</p>");
		}

	})


}
function ReplyContent(page){//收到的评论初始化
	var dataName=$("#userId").html();
	$("#userReply").remove(".Reply");
	$.get("/api/user/userreplay","name="+dataName+"&page="+page,function(userData){
		if(userData){
			console.log(userData.length);
			for(var i in userData){
				var userReplyObj = "<a id='replyEver"+i+"' class='Reply'>"+userData[i].content+"</a></br>";
				$("#userReply").append(userReplyObj);
			}
			var allObj=[];
			for(var i=0;i<userData.length;i++){
				allObj[i]=$("#replyEver"+i);
			}
			$.each(allObj, function(i){
				$(this).click(function(){
					window.open("./post?id="+userData[i].father);
				})
			})
		}
		else{
			$("#userReply").append("<p>无</p>");
		}

	})


}
function HeadSetting(){//头像显示
	var ownerName = $("#userId").html();
	if(ownerName){
		
		$("#headphoto").attr('src',"/api/user/img?id="+ownerName); 
	}

}
