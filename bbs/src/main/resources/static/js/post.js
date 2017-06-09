
function checkPlateCookie()//检查板块信息
{
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var str = url.split('&');
		var strs = str[0].split('=');
		var idStrs = str[1].split('=');
		$("#plateTitle").html(unescape(strs[1]));
		return idStrs[1]
	}
	else{
		window.close();
	}
	}
function checkUserStatus(){//检查登录人
	var name = checkLoginStatus();
	if(name){
		var IdStr = name.split('=');
		$("#userId").html(IdStr[1]);
		$("#userId").click(function(){
			window.location.href="./mypage.html?name="+escape(IdStr[1]);
		})
	}
	else{
		return;
	}
}


function checkPostCookie()//检查帖子信息
{
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strs = url.split('=');
		$("#postTitle").html(strs[1]);
	}
	else{
		window.close();
	}


}
function postInit(){//板块帖子初始化
	var plateId = checkPlateCookie();
	var plateName =$("#plateTitle").html();
	$.get("http://10.12.38.53:81/api/post/get","id="+plateId+"&page=1",function(postData){
			if(postData){
				var tempId=0,tempTitle;
				for(var i=0;i<postData.length;i++){
					$("#postTitle_"+i).html(postData[i].title);
					tempId= postData[0].id;
					tempTitle= postData[0].title;
					$("#postTitle_"+i).click(function(){

						window.location.href = "./post?id="+tempId+"&postTitle="+escape(tempTitle)+"&plateName="+escape(plateName);

					})
				}
				
			}
			})
			
		}

function postReplay(){//帖子内容及回复初始化
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strs = url.split('&');
		var idStr = strs[0].split('=');
		var titleStr = strs[1].split('=');
		var ownerPlate = strs[2].split('=');
		var postpage=1;
		$.get("http://10.12.38.53:81/api/replay/select","id="+idStr[1]+"&page="+postpage,function(postContent){
			if(postContent){
					if(postpage==1){
						$("#ownerPlate").html(unescape(ownerPlate[1]));
						$("#title").html(unescape(titleStr[1]));
						$("#content").html(postContent[0].content);
						$("#author").html(postContent[0].author);
						$("#ownerTime").html(postContent[0].times);
					}
					for(var i=1;i<postContent.length;i++){
						var floorNum = (postpage-1)*10+i+1;
						var floorObj  = "<div id='floor_" +i + "'><div class='me'><img class='headshot' src='./static/a.jpg' width=80px height=80px><div id='floorName_" +i+ "' class='name'>名字</div></div><textarea id='floorContent_"+i+"  ' class='message' rows='5' cols='5' readonly='readonly'>"+postContent[i].content+"</textarea><div class='tail'><span id='floorTime_"+i+"' class='tailtime'>time</span><span class='tailfloor'>第"+floorNum+"楼</span></div><div class='clear'></div> <hr/></div>"
						$("#newReply").before(floorObj);
						console.log(postContent[i].content);
						$("#floorName_"+i).html(postContent[i].author);
						$("#floorTime_"+i).html(postContent[i].times);
					}
					
			}
			else return;
			})
			
		}
		else{
		window.close();
			}
		$(".sand").click(function(){
		var sandContent = $("#replyContent").val();
		console.log("1");
		console.log(sandContent);
		if(sandContent){
			var nowTime = new Date();
			var postTime = nowTime.getFullYear()+"-"+nowTime.getMonth()+"-"+nowTime.getDate()+" "+nowTime.getHours()+":"+nowTime.getMinutes()+":"
			+nowTime.getSeconds()+"."+nowTime.getMilliseconds(); 
			console.log(postTime);
			var author=$("#userId").html();
			$.get("http://10.12.38.53:81/api/replay/add","father="+idStr[1]+"&author="+author+"&content="+sandContent+"&times="+postTime,function(data){
			if(data){
				$(".sand").html("发送中");
				setTimeout(function(){
					location.reload(true);
				},500)
			}
			else{
				alert("校园网波动，请稍后重试");
			}
		}
		)
		}
	})

	 

}

function topPostInit(){//置顶帖子初始化
	var plateId = $("#plateTitle").html();
	$.get("http://10.12.38.53:81/api/post/top","id="+plateId,function(postTop){
		if(postTop){
			for(var i=0;i<postTop.length;i++){
					$("#postTop_"+i).html(postData[i].title);
					$("#postTop_"+i).click(function(){
						window.location.href = "./post.html?postId="+postData[i].id;
					})
				}
		}
		
	})
}