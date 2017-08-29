$(document).ready(function(){
	searchListener();
})
var postSearch = function(word, pageNum) {
	$('#replyS').css('color', '#959595');
	$.post("/api/post/searchpost", {"keyword": word , "page": pageNum}, function(sPost) {
		if (isNull(sPost)) {
				$(".result-list").append("<div class='item post'><p class='post-title'><span>已无更多结果</span></p></div>");
		} else {
			for (var i in sPost) {
				var userPlateObj = "<div class='item post'><a class='item-link' href='./post?id="+sPost[i].id +"'></a><p class='post-title'>"+keywordFormat(sPost[i].title,word)+"</p><p class='post-info'>"+sPost[i].author+"  "+dateFormat(sPost[i].lastTime)+"</p></div>";
				$(".result-list").append(userPlateObj);
			}
		}
		$('#postS').css('color', '#ff9191');
	})
}
var replySearch = function(word, pageNum) {
	$('#postS').css('color', '#959595');
	$.post("/api/replay/searchreply", {"keyword": word , "page": pageNum}, function(sReply) {
		if (isNull(sReply)) {
			$(".result-list").append("<div class='item post'><p class='post-title'><span>已无更多结果</span></p></div>");
		} else {
			
			for (var i in sReply) {
				var userReplyObj = "<div class='item post'><a class='item-link' href='./post?id="+sReply[i].father+"#"+sReply[i].id+"'></a><p class='reply-title'>"+searchReplyFormat(sReply[i].content,word)+"</p><p class='post-info'>"+sReply[i].author+"  "+dateFormat(sReply[i].times)+"</p></div>";
				$(".result-list").append(userReplyObj);
			}
		}
		$('#replyS').css('color', '#ff9191');
	})
}


function searchListener() {
	var searchObj = "<div id='page-content'><div id='page-search'><div class='filters'><a id='postS' href='javascript:void(0);'>帖子</a><a id='replyS' href='javascript:void(0);' style='border: 0px;'>回复</a></div><div class='result-list'></div></div></div>";
	var resultFlag = 1;
	var i = 1;
	$(document).on('click',".iconfont",function(){
		i=1;
		$("#page-content").empty();
		$("#page-content").append(searchObj);
		var keyword = $('input').val();
		postSearch(keyword,i);
	})
	$(document).on('keypress',"input", function(event) {   
        if (event.keyCode == "13") {  
             $(".iconfont").click();
        }
    })
	setTimeout(function(){
		$(window).scroll(function() {
		var keyword = $('input').val();
       	var scrollTop = $(this).scrollTop();
　　   	var scrollHeight = $(document).height();
　　    var windowHeight = $(this).height();
　　			if(scrollTop + windowHeight == scrollHeight){
            i++;
            switch(resultFlag)
            {
            	case 1:
            	postSearch(keyword,i);
            	break;
            	case 2:
            	replySearch(keyword,i);
            	break;
            }
       }
    })	
},200)

    $('body').on('click','#postS',function(){
    	var keyword = $('input').val();
    	if(resultFlag==1){ return;}
    	else {
    		resultFlag=1;
    		i=1;
    		$(".result-list").empty();
    		postSearch(keyword,i);
    	}
    })
    $('body').on('click','#replyS',function(){
    	var keyword = $('input').val();
    	if(resultFlag==2){ return;}
    	else {
    		resultFlag=2;
    		i=1;
    		$(".result-list").empty();
    		replySearch(keyword,i);
    	}
    })
}

