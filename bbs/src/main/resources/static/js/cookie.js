function setCookie(username,password)
{	var deadLine = new Date();
	deadLine.setTime(deadLine.getTime()+30*24*60*60*1000);
	document.cookie="ZDNFBBS_userName="+escape(username)+"&"+"ZDNFBBS_passWd="+escape(password)+";"+"expires="+deadLine.toGMTString(); 
}

function getCookie(username)

{

	if(document.cookie.length>0)

	{

		c_start=document.cookie.indexOf("ZDNFBBS_userName="+username);

		if(c_start!=-1)

		{


			

			c_end=document.cookie.indexOf(";",c_start);

			if(c_end==-1)
				c_end=document.cookie.length;


			return unescape(document.cookie.substring(c_start,c_end));

		}

	}

	return"";

}
function checkLoginStatus(){
	var l_start = document.cookie.indexOf("ZDNF_name=");
	if(l_start!=-1){
		var l_end=document.cookie.indexOf(";",l_end);
		if(l_end==-1){
			l_end=document.cookie.length;
		}
		return unescape(document.cookie.substring(l_start,l_end));
	}
	return false;
}
