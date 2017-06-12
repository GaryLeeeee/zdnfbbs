
function plateInit(){//论坛板块数据初始化
	setTimeout(function(){
		$.get("http://10.12.45.102:81/api/plate/all",function(plateData){

			if(plateData){
				for(var i=0;i<plateData.length;i++){
				$("#test_"+i).val(plateData[i].name);
			}
			var plateObj=[];
			for(var i=0;i<plateData.length;i++){
				plateObj[i]=$("#test_"+i);
			}
		$.each(plateObj, function(i){
			$(this).click(function(){
				window.open("./platepost?title="+escape(plateData[i].name)+"&id="+plateData[i].id);
			})
				
			
			})
			}
		})
	},0)

	
}

