
#nav{ margin:0px; padding:0px;}  
#nav{ width:600px; height:40px; margin:0 auto;}  
#nav ul{ list-style:none;}  
#nav ul li{ float:left; line-height:40px; text-align:center; position:relative;}  
#nav ul li a{ text-decoration:none; color:#000; display:block;padding:0px 10px;}  
#nav ul li a:hover{ color:#FFF; background:#333}  
#nav ul li ul{ position:absolute; display:none;}  
#nav ul li ul li{ float:none; line-height:30px; text-align:left;}  
#nav ul li ul li a{ width:100%;}  
#nav ul li ul li a:hover{ background-color:#06f;}  
#nav ul li:hover ul{ display:block} 

$(document).ready(function () {
            $(".navgation input").each(function () {
                var this_div = $(".navgation div");
                var _inx = $(".navgation input").index(this);
                $(this).click(
                function () { this_div.eq(_inx).slideToggle(); },
                function () { this_div.eq(_inx).slideToggle(); }
           );
            });
        });