
function checkPlateCookie()//检查板块信息
{
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var idStrs = tempStr.split('=');
		$.get("/api/plate/namebyid","id="+idStrs[1],function(f_plateName){
					$("#plateTitle").html(f_plateName);
				})
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
	$.get("/api/post/max","id="+plateId,function(pageSum){
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
			+nowTime.getSeconds(); 
			console.log(postTime);
			var author=$("#userId").html();
			$.post("/api/post/add","BelongTo="+plateId+"&title="+sandTitle+"&author="+author+"&LastOne="+author+"&LastTime="+postTime,function(dataId){
				if(dataId){
					$(".sand").html("发送中");
					setTimeout(function(){
						var nowTime = new Date();
						var month = nowTime.getMonth()+1;
						var postTime = nowTime.getFullYear()+"-"+month+"-"+nowTime.getDate()+" "+nowTime.getHours()+":"+nowTime.getMinutes()+":"
						+nowTime.getSeconds(); 

						var author=$("#userId").html();
						$.post("/api/replay/add","father="+dataId+"&author="+author+"&content="+sandContent+"&times="+postTime,function(data){
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
	$.get("/api/post/get","id="+plateId+"&page="+pageNum,function(postData){
		if(postData){
			var tempId=0,tempTitle;
			if(pageNum!=1){
				$("#allPost").empty();
			}
				
			for(var i=0;i<postData.length;i++){
				var platePostObj = "<div class='post'><div class='postimg'><img id='authorImg_"+i+"' src=\"/api/user/img?id="+postData[i].author+"\" /></div><div class='postcontant'><div><a class='name' href='./mypage?name="+postData[i].author+"'>"+postData[i].author+"</a></div><span id='postTitle_"+i+"' class='title' href=''>"+postData[i].title+"</span><div style='width:90%'><span class='reply'>总回复："+postData[i].num+"</span><span class='time'>"+postData[i].lastTime+"</span></div></div></div>";
				$("#allPost").append(platePostObj);;
					
				
				allPostId[i]= postData[i].id;
				allPostTitle[i]= postData[i].title;
				
			}
			var postObj=[];
			for(var i=0;i<postData.length;i++){
				postObj[i]=$("#postTitle_"+i);
			}
			$.each(postObj, function(i){
				$(this).click(function(){
					window.open("./post?id="+allPostId[i]);
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
	$.get("/api/post/top","id="+plateId,function(postTop){
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
					window.open("./post?id="+allPostId[i]);
				})
			})
		}
		
	})
}
function postPagination(){//帖子回复的分页
	var url = window.location.search;
	
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		var strsId = tempStr.split('=');
		$.get("/api/replay/max","id="+strsId[1],function(pageSum){
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
				postReplay(1);
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
				var month = nowTime.getMonth()+1;
				var postTime = nowTime.getFullYear()+"-"+month+"-"+nowTime.getDate()+" "+nowTime.getHours()+":"+nowTime.getMinutes()+":"
				+nowTime.getSeconds();
				var author=$("#userId").html();
				$.get("/api/replay/add","father="+strsId[1]+"&author="+author+"&content="+sandContent+"&times="+postTime,function(data){
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
		var strsId = tempStr.split('=');
		$.get("/api/post/getallbyid","id="+strsId[1],function(allData){
			$("#title").html(allData[0].title);
			setTimeout(function(){
				if(allData[0]){
				$.get("/api/plate/namebyid","id="+allData[0].BelongTo,function(f_plateName){
					$("#ownerPlate").html(f_plateName);
				})
				}
				else{
				alert("ID错误");
				}
				},500)
			
		})
		$.get("/api/replay/select","id="+strsId[1]+"&page="+postpage,function(postContent){
			if(postContent){
				if(postpage==1){
					$("#content").html(postContent[0].content);
					$("#author").html(postContent[0].author);
					$("#ownerTime").html(postContent[0].times);
					$("#ownerphoto").attr('src',"/api/user/img?id="+postContent[0].author)
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
						var floorObj  = "<div id='floor_" +i + "'><div class='me'>"+"<a href=\"/mypage?name="+escape(postContent[i].author)+"\"><img class='headshot' src='/api/user/img?id="+postContent[i].author+"' width=80px height=80px ></a><div id='floorName_" +i+ "' class='name'>名字</div></div><div id='floorContent_"+i+"  ' class='message' >"+postContent[i].content+"</div><div class='tail'><span id='floorTime_"+i+"' class='tailtime'>time</span><span class='tailfloor'>第"+floorNum+"楼</span></div><div class='clear'></div> <hr/></div>"
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

