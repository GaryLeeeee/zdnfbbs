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
	var result;
    $.ajax({
        
        url : 'api/user/user',
        async:false,
        success : function(userStatus){
        	if(userStatus&&userStatus!="false"){
        		console.log(userStatus);
        		result = userStatus;
        	}
            
            

        }
    })
    return result;
	}
