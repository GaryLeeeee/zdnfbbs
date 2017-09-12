
function IsPC() {
    var userAgentInfo = navigator.userAgent;
    var Agents = ["Android", "iPhone",
                "SymbianOS", "Windows Phone",
                "iPad", "iPod"];
    var flag = true;
    for (var v = 0; v < Agents.length; v++) {
        if (userAgentInfo.indexOf(Agents[v]) > 0) {
            flag = false;
            break;
        }
    }
    return flag;
}
function isNull( str ){//判空
	if ( str == "" ) return true;
	var regu = "^[ ]+$";
	var re = new RegExp(regu);
	return re.test(str);
}
function dateFormat(PostDate){//判断时间差
	if(isNull(PostDate)) return;
	else{
		var nowTime = new Date();
		var month = nowTime.getMonth()+1;
		var a = PostDate.split(' ');
		var postDateFlag = a[0].split('-');
		var postTimeFlag = a[1].split(':');
		if(nowTime.getFullYear()>parseInt(postDateFlag[0]))  return parseInt(nowTime.getFullYear())-parseInt(postDateFlag[0])+"年前";
		else if(parseInt(month)>parseInt(postDateFlag[1]))  return parseInt(month)-parseInt(postDateFlag[1])+"个月前";
		else if(parseInt(nowTime.getDate())>parseInt(postDateFlag[2]))  return parseInt(nowTime.getDate())-parseInt(postDateFlag[2])+"天前";
		else if(parseInt(nowTime.getHours())>parseInt(postTimeFlag[0]))  return parseInt(nowTime.getHours())-parseInt(postTimeFlag[0])+"小时前";
		else if(parseInt(nowTime.getMinutes())>parseInt(postTimeFlag[1]))  return parseInt(nowTime.getMinutes())-parseInt(postTimeFlag[1])+"分钟前";
		else return "刚刚";
	}
}

function xssFormat(postContent){
	if(postContent.search(/<script>/)!=-1){ 
		postContent=postContent.replace(/<script>/g, "<xmp><script></xmp>");
		postContent=postContent.replace(/<\/script>/g, "<xmp></script></xmp>");
	}
	if(postContent.search(/javascript:/)!=-1) postContent=postContent.replace(/javascript:/g, "<xmp>  javascript:  </xmp>");
	if(postContent.search(/<from>/)!=-1) {
		postContent=postContent.replace(/<\/from>/g, "<xmp></from></xmp>");
	}
	if(postContent.search(/style=/)!=-1) {
		postContent=postContent.replace(/style=/g, "<xmp>style=</xmp>");
	}

	if(postContent.search(/class="img" <xmp>style=<\/xmp>/)!=-1) {
		postContent=postContent.replace(/class="img" <xmp>style=<\/xmp>/g, "class='img' style=");
	}
	console.log(postContent);
	return postContent;

}

var keywordFormat = function(titleContent,keyword){
	titleContent = titleContent.replace(keyword,"</xmp><xmp class='reply-title' style='color:#F18C73'>"+keyword+"</xmp><xmp class='reply-title'>");
	return titleContent;
}

var searchReplyFormat = function(titleContent,keyword){
	if(titleContent.indexOf(keyword)>15){
		titleContent = titleContent.slice(titleContent.indexOf(keyword));
		titleContent = "..."+titleContent;
	}
	titleContent = keywordFormat(titleContent,keyword);
	return titleContent;
}
function isUTF8MB4(str){
	var i;
	for(i=0;i<str.length;i++){
	var value = str.charCodeAt(i);
	var arr = str.split("");
	if( value > 0x8800) {
		arr.splice(i,1);
		str = arr.join(""); 
	}
	}
	return str;
}