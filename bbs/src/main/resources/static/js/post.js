
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
		var IdStr = name;
		$("#userId").html(IdStr);
		$("#userId").click(function(){
			window.location.href="./mypage?name="+escape(IdStr);
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
function pagination(){//板块帖子分页
	var plateId = checkPlateCookie();
	var plateName =$("#plateTitle").html();
	postInit(1);
	$.get("http://10.12.45.102:81/api/post/max","id="+plateId,function(pageSum){
		if(pageSum){
			var paginationNum = pageSum/10;
			for(var i=1;i<paginationNum+1;i++){
				var pagination = "<span id='page_"+i+"'>"+i+"</span>";
				$("#newReply").before(pagination);
			}
			var paginationSum=[];
			for(var i=0;i<paginationNum+1;i++){
				paginationSum[i]=$("#page_"+i);
			}
			$.each(paginationSum, function(i){
				$(this).click(function(){
					if(i==1){
							location.reload(true);
						}
					else postInit(i);
				})
			})
		}

	})
	$(".sand").click(function(){//发帖功能
		var sandTitle = $("#replyTitle").val();
		var sandContent = $("#replyContent").val();
		if(sandContent&&sandTitle){
			var nowTime = new Date();
			var postTime = nowTime.getFullYear()+"-"+nowTime.getMonth()+"-"+nowTime.getDate()+" "+nowTime.getHours()+":"+nowTime.getMinutes()+":"
			+nowTime.getSeconds()+"."+nowTime.getMilliseconds(); 
			var author=$("#userId").html();
			$.get("http://10.12.45.102:81/api/post/add","BelongTo="+plateId+"&title="+sandTitle+"&author="+author+"&LastOne="+author+"&LastTime="+postTime,function(dataId){
				if(dataId){
					$(".sand").html("发送中");
					setTimeout(function(){
						var nowTime = new Date();
						var postTime = nowTime.getFullYear()+"-"+nowTime.getMonth()+"-"+nowTime.getDate()+" "+nowTime.getHours()+":"+nowTime.getMinutes()+":"
						+nowTime.getSeconds()+"."+nowTime.getMilliseconds(); 
						var author=$("#userId").html();
						$.get("http://10.12.45.102:81/api/replay/add","father="+dataId+"&author="+author+"&content="+sandContent+"&times="+postTime,function(data){
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
function postInit(pageNum){//板块帖子初始化
	var plateId = checkPlateCookie();
	var plateName =$("#plateTitle").html();
	var allPostId = [];
	var allPostTitle = [];
	$.get("http://10.12.45.102:81/api/post/get","id="+plateId+"&page="+pageNum,function(postData){
		if(postData){
			var tempId=0,tempTitle;
			if(pageNum!=1){
				$(".post").empty();
			}
			for(var i=0;i<postData.length;i++){
				var platePostObj = "<div><a class='name' href='./mypage?name="+postData[i].author+"'>"+postData[i].author+"</a></div><span id='postTitle_"+i+"' class='title' href=''>"+postData[i].title+"</span><span class='time'>"+postData[i].lastTime+"</span>";
				allPostId[i]= postData[i].id;
				allPostTitle[i]= postData[i].title;
				$(".post").append(platePostObj);
			}
			var postObj=[];
			for(var i=0;i<postData.length;i++){
				postObj[i]=$("#postTitle_"+i);
			}
			$.each(postObj, function(i){
				$(this).click(function(){
					window.open("./post?id="+allPostId[i]+"&postTitle="+escape(allPostTitle[i])+"&plateName="+escape(plateName));
				})
			})
		}
	})

}
function topPostInit(){//置顶帖子初始化
	var plateId = checkPlateCookie();
	var plateName =$("#plateTitle").html();
	var allPostId = [];
	var allPostTitle = [];
	$.get("http://10.12.45.102:81/api/post/top","id="+plateId,function(postTop){
		if(postTop){
			var tempId=0,tempTitle;
			for(var i=0;i<postTop.length;i++){
				var plateTopObj = "<span class='thetop'>置顶</span><span id='postTop_"+i+"' class='thetoppost' >"+postTop[i].title+"</span></br>";
				allPostId[i]= postTop[i].id;
				allPostTitle[i]= postTop[i].title;
				$(".up").append(plateTopObj);
			}

			var postObj=[];
			for(var i=0;i<postTop.length;i++){
				postObj[i]=$("#postTop_"+i);
			}
			$.each(postObj, function(i){
				$(this).click(function(){
					window.open("./post?id="+allPostId[i]+"&postTitle="+escape(allPostTitle[i])+"&plateName="+escape(plateName));
				})
			})
		}
		
	})
}
function postPagination(){//帖子回复的分页
	var url = window.location.search;
	postReplay("1");
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strs = url.split('&');
		var idStr = strs[0].split('=');
		$.get("http://10.12.45.102:81/api/replay/max","id="+idStr[1],function(pageSum){
			if(pageSum){
				var paginationNum = pageSum/10;
				for(var i=1;i<paginationNum+1;i++){
					var pagination = "<span id='page_"+i+"'>"+i+"</span>";
					$("#newReply").before(pagination);
				}
				var paginationSum=[];
				for(var i=0;i<paginationNum+1;i++){
					paginationSum[i]=$("#page_"+i);
				}
				$.each(paginationSum, function(i){
					$(this).click(function(){
						if(i==1){
							location.reload(true);
						}
						else postReplay(i);
					})
				})
			}

		})
		$(".sand").click(function(){//回复功能
			var sandContent = $("#replyContent").val();
			if(sandContent){
				var nowTime = new Date();
				var postTime = nowTime.getFullYear()+"-"+nowTime.getMonth()+"-"+nowTime.getDate()+" "+nowTime.getHours()+":"+nowTime.getMinutes()+":"
				+nowTime.getSeconds()+"."+nowTime.getMilliseconds(); 
				var author=$("#userId").html();
				$.get("http://10.12.45.102:81/api/replay/add","father="+idStr[1]+"&author="+author+"&content="+sandContent+"&times="+postTime,function(data){
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
}
function postReplay(postpage){//帖子内容及回复初始化
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strs = url.split('&');
		var idStr = strs[0].split('=');
		var titleStr = strs[1].split('=');
		var ownerPlate = strs[2].split('=');
		$("#ownerPlate").html(unescape(ownerPlate[1]));
		$("#title").html(unescape(titleStr[1]));
		$.get("http://10.12.45.102:81/api/replay/select","id="+idStr[1]+"&page="+postpage,function(postContent){
			if(postContent){
				if(postpage==1){
					$("#content").html(postContent[0].content);
					$("#author").html(postContent[0].author);
					$("#ownerTime").html(postContent[0].times);
				}
				else{
					$("#towerOwner").remove();
					for(var i=0;i<10;i++){
						$("#floor_"+i).remove();
					}
				}
				for(var i=0;i<postContent.length;i++){
					if(i!=0||postpage!=1){
						var floorNum = (postpage-1)*10+i+1;
						var floorObj  = "<div id='floor_" +i + "'><div class='me'><img class='headshot' src='/api/user/img?id="+postContent[i].author+"' width=80px height=80px><div id='floorName_" +i+ "' class='name'>名字</div></div><div id='floorContent_"+i+"  ' class='message' >"+postContent[i].content+"</div><div class='tail'><span id='floorTime_"+i+"' class='tailtime'>time</span><span class='tailfloor'>第"+floorNum+"楼</span></div><div class='clear'></div> <hr/></div>"
						$("#page_1").before(floorObj);
						$("#floorName_"+i).html(postContent[i].author);
						$("#floorTime_"+i).html(postContent[i].times);
					}
				}

			}
			else return;
		})

}
else{
	window.close();
}
}

