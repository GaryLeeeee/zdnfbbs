$(document).ready(function(){
	
		checkCookie();
		msgInit();
		HeadSetting();
		PlateContent(1);
		ReplyContent(1);
		
	
	
})
function checkCookie()//检查cookie确认页面信息与登录人信息
{
	
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strs = url.split('=');
		$("#userId").html(unescape(strs[1]));
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
 		$.post("/api/user/userinfo","name="+dataName,function(userData){//登陆者私人信息页面
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
 		
 		$.post("/api/user/userinfo","name="+dataName,function(userData){//游客信息页面

 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			$("#introduceContent").html(userData.introduce);
 		})
 	}


 }
function PlateContent(page){//发过的帖子初始化
	var dataName=$("#userId").html();
	$(".morePost").remove();
	var nextPage = parseInt(page)+1;
	$.post("/api/user/userpost","name="+dataName+"&page="+page,function(userData){
		if(!isNull(userData)){
			for(var i in userData){
				var userPlateObj = "<a id='postEver"+i+"' class='Plate' href='./post?id="+userData[i].id+"'>"+userData[i].title+"</a></br>";
				$("#userPlate").append(userPlateObj);
			}
			$("#userPlate").append("<div class='morePost'>点击加载更多</div>");
			$(".morePost").click(function(page){PlateContent(nextPage)});
		}
		else{
			$("#userPlate").append("<p>无</p>");
		}

	})
	

}
function ReplyContent(page){//收到的评论初始化
	var dataName=$("#userId").html();
	$(".moreReply").remove();
	var nextPage = parseInt(page)+1;
	$.post("/api/user/userreplay","name="+dataName+"&page="+page,function(userData){
		if(!isNull(userData)){
			
			for(var i in userData){
				var userReplyObj = "<a id='replyEver"+i+"' class='Reply' href='./post?id="+userData[i].father+"'>"+userData[i].content+"</a></br>";
				$("#userReply").append(userReplyObj);
			}
			$("#userReply").append("<div class='moreReply'>点击加载更多</div>");
			$(".moreReply").click(function(page){ReplyContent(nextPage)});
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
function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
