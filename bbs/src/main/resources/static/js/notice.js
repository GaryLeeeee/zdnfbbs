function noticeInit(){//公告栏初始化
	var noticeSkip=[];
	$.post("api/notice/title",function(noticeData){
		if(noticeData){
			
			for(var i=0;i<noticeData.length;i++){
				$("#noticeBoard_"+i).html(noticeData[i]);
				noticeSkip[i]= noticeData[i];

			}


		}
		else return;
	})
	setTimeout(function(){
		var noticeObj=[];
		for(var i=0;i<noticeSkip.length;i++){
			noticeObj[i]=$("#noticeBoard_"+i);
		}
		$.each(noticeObj, function(i){
			$(this).click(function(){
				window.open("./notice?title="+noticeSkip[i]);
			})
  				
		});
				
			
			
		
	
},500)
	
}
function noticeMsg(){//显示公告栏页面内容
	var url = window.location.search;
	if(url.indexOf("?")!=-1){
		var tempStr = url.substr(1);
		$.post("http://10.12.20.182:81//api/notice/content",tempStr,function(noticeContentData){
			if(noticeContentData){
			
				$("#noticeContent").html(noticeContentData);
			
			
			}
			else{
				alert("网错错误");
				window.close();
			}
			
		})
	}
	else{
		alert("网错错误");
		window.close();
	}
}