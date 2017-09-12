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
	if(!isNull(usermsg)){
		
		username = usermsg;
	}
	else{
		username = null;
	}
	
	var dataName=$("#userId").html();
	if(!isNull(username)&&username==dataName)
	{
 		$.post("/api/user/userinfo","name="+dataName,function(userData){//登陆者私人信息页面
 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			var personerObj = "<ul>微信:<a id='wechatContent'></a></ul>"
 			+"<ul>联系电话:<a id='telnumContent'></a></ul>"+"<ul>权限:<a id='powerContent'></a></ul>";
 			$("#nameTitle").after(personerObj);
 			$("#wechatContent").html(userData.wechat);
 			$("#telnumContent").html(userData.telnum);
 			$("#powerContent").html(userData.power);
 			$("#introduceContent").html(userData.introduce);
 			$("#historyPost").after("<h3 id='delPost' class='littletitle'>删除</h3>");
 			$("#historyReply").after("<h3 id='delReply' class='littletitle'>删除</h3>");
 		})
 		$("#exit").click(function(){
 			DelCookie("id");
 			DelCookie("key");	
 		})
 	}
 	else
 	{
 		
 		$.post("/api/user/userinfo","name="+dataName,function(userData){//游客信息页面

 			$("#nameContent").html(userData.name);
 			$("#sexContent").html(userData.sex);
 			$("#introduceContent").html(userData.introduce);
 			$("#wechatContent").remove();
 			$("#powerContent").remove();
 			$("#telnumContent").remove();
 			$("#historyPost").remove();
 			$("#historyReply").remove();
 		})
 		
 	}


 }
function PlateContent(page){//发过的帖子初始化
	var dataName=$("#userId").html();
	var hrObj = "<hr  style='height:1px; border:none; border-top:1px solid #ccd0d2;' width=85%>";
	$("#morePost").remove();
	var nextPage = parseInt(page)+1;
	$.post("/api/user/userpost","name="+dataName+"&page="+page,function(userData){
		if(!isNull(userData)){
			for(var i in userData){
				var userPlateObj = "<div  class='mypost'><a id='postEver"+userData[i].id+"'  href='./post?id="+userData[i].id+"'><xmp>"+userData[i].title+"</xmp></a></div>";
				$("#userPlate").append(userPlateObj);
				$("#userPlate").append(hrObj);
				
			}
			$("#userPlate").append("<div class='button' id='morePost' style='margin:10px 30%'>点击加载更多</div>");
			$("#morePost").click(function(page){
				$("#postTArea").off('click',"#delPost");
				PlateContent(nextPage);
			});
		}
		else{
			$("#userPlate").append("<p style='margin:10px 30%'>无</p>");
		}

	})
	$("#postTArea").on("click","#delPost",function(){
		if($("#delPost").hasClass('littletitle')){
			$("#delPost").removeClass('littletitle');
			$("#delPost").toggleClass('clickTitle');
			$(".mypost>a").each(function(){$(this).attr("href","javascritp:void(0);");}).click(function(){
				var idStr = $(this).attr("id");
				idStr = idStr.replace("postEver","");
				$(this).remove();
				$.post("/api/post/delete","postid="+idStr,function(msg){
					return;
				})
				
			})
			return ;
		}
		else{
			$("#delPost").removeClass('clickTitle');
			$("#delPost").toggleClass('littletitle');
			$(".mypost>a").each(function(){
				var idStr = $(this).attr("id");
				idStr = idStr.replace("postEver","");
				$(this).attr("href","./post?id="+idStr);
			}).click(function(){ return;});
		}
	})

	

}
function ReplyContent(page){//收到的评论初始化
	var dataName=$("#userId").html();
	var hrObj = "<hr  style='height:1px; border:none; border-top:1px solid #ccd0d2;' width=85%>";
	$("#moreReply").remove();
	var nextPage = parseInt(page)+1;
	$.post("/api/user/userreplay","name="+dataName+"&page="+page,function(userData){
		if(!isNull(userData)){
			for(var i in userData){
				if(userData[i].isfirst==1) continue;
				var userReplyObj = "<div  class='myreply'><a id='replyEver"+userData[i].id+"'  href='./post?id="+userData[i].father+"#floorContent_"+userData[i].id+"'><xmp>"+userData[i].content+"</xmp></a></div>";
				$("#userReply").append(userReplyObj);
				$("#userReply").append(hrObj);
				
			}

			$("#userReply").append("<div class='button' id='moreReply' style='margin:10px 30%'>点击加载更多</div>");
			$("#moreReply").click(function(page){
				$("#replyTArea").off('click',"#delReply");
				ReplyContent(nextPage);
			});
			
		}
		else{
			$("#userReply").append("<p style='margin:10px 30%'>无</p>");
		}

	})
	
	
	var hrefTemp =new Array();
	$("#replyTArea").on("click","#delReply",function(){
		if($("#delReply").hasClass('littletitle')){
			$("#delReply").removeClass('littletitle');
			$("#delReply").toggleClass('clickTitle');
			$(".myreply>a").each(function(i){hrefTemp[i] = $(this).attr("href");$(this).attr("href","javascritp:void(0);");}).click(function(){
				var idStr = $(this).attr("id");
				idStr = idStr.replace("replyEver","");
				$(this).remove();
				$.post("/api/replay/delete","replyid="+idStr,function(msg){
					return;
				})	
			})
		}
		else{
			$("#delReply").removeClass('clickTitle');
			$("#delReply").toggleClass('littletitle');
			$(".myreply>a").each(function(i){
				$(this).attr("href",hrefTemp[i]);
			}).click(function(){ return;});
		}
	})
	
	

}

function HeadSetting(){//头像显示
	var ownerName = $("#userId").html();
	if(!isNull(ownerName)){
		$.get("http://osqfm1e16.bkt.clouddn.com/"+ownerName,function(msg){
			if(msg.error=="Document not found"){
				$("#headphoto").attr('src',"http://osqfm1e16.bkt.clouddn.com/root"); 
			}
			else{
				$("#headphoto").attr('src',"http://osqfm1e16.bkt.clouddn.com/"+ownerName); 
			}
		})
		
	}

}
function isNull( str ){
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
