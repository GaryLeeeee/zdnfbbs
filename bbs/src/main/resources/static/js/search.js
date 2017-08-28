(function(){
	searchListener();
})
var postSearch = function(word, pageNum) {
	$.post("/api/post/searchpost", {"keyword":word,"page":pageNum}, function(sPost) {
		if (isNull(sPost)) {
				$(".result-list").append("<div class='item post'><p class='post-title'><span>已无更多结果</span></p></div>");
		} else {
			for (var i in sPost) {
				var userPlateObj = "<div class='item post'><a class='item-link' href='./post?id="+sPost[i].id +"'></a><p class='post-title'>"+keywordFormat(sPost[i].title,word)+"</p><p class='post-info'>"+sPost[i].author+"  "+dateFormat(sPost[i].lastTime)+"</p></div>";
				$(".result-list").append(userPlateObj);
			}
		}
	})
}
var replySearch = function(word, pageNum) {
	$.post("/api/replay/searchreply", {"keyword":word , "page":pageNum}, function(sReply) {
		if (isNull(sReply)) {
			$(".result-list").append("<div class='item post'><p class='post-title'><span>已无更多结果</span></p></div>");
		} else {
			
			for (var i in sReply) {
				var userReplyObj = "<div class='item post'><a class='item-link' href='./post?id="+sReply[i].BelongTo+"#"+sReply[i].id+"'></a><p class='reply-title'>"+searchReplyFormat(sReply[i].title,word)+"</p><p class='post-info'>"+sReply[i].author+"  "+dateFormat(sReply[i].lastTime)+"</p></div>";
				$(".result-list").append(userReplyObj);
			}
		}
	})
}


function searchListener() {
	var searchObj = "<div id='page-content'><div id='page-search'><div class='filters'><a id='postS' href=''>帖子</a><a id='replyS' href='' style='border: 0px;'>回复</a></div></div></div>";
	var resultFlag = 1;
	var i = 1;
	$("#search").click(function(){
		i=1;
		$("#page-content").empty();
		$("#page-content").append(searchObj);
		var keyword = $('input').val();
		postSearch(keyword,i);
	})
	$(window).scroll(function() {
       if($(document).scrollTop() <= 50) {
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
    }
    $('body').on('click','#postS',function(){
    	if(resultFlag==1){ return;}
    	else {
    		resultFlag=2;
    		i=1;
    		replySearch(keyword,i);
    	}
    })
    $('body').on('click','#replyS',function(){
    	if(resultFlag==2){ return;}
    	else {
    		resultFlag=1;
    		i=1;
    		postSearch(keyword,i);
    	}
    })
}

