/*获取列表中的歌曲信息*/  
window.onload=function(){
	var infolist=new Array();
	var rank=document.getElementsByClassName('rank');
	for(k=0;k<rank.length;k++){
	var rli=rank[k].getElementsByTagName("li");
	for(j=0;j<rli.length;j++){
		var liid = $('.rank').eq(k).find('li').eq(j).attr("id");
		getInfo(liid);
		$('.rank').eq(k).find('li').eq(j).html('<span class="singername">'+infolist[0].singer+'</span>--<span class="songname">'+infolist[0].song+'</span>');
	}
	}
	
	function getInfo(id){ 
	$.ajax( {  
		type : "post",  
     url : "getMusicDetailByID",  
     data : "id="+id,  
     dataType: "json", 
     //contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     async:false, 
     success : function(msg) {  
        	$.each(msg.detailInfo, function (index, item){
	      		var temp={};		
	   			temp.song=item.song;
	   			temp.singer=item.singer;
	   			temp.id=item.id;
	   			infolist[0] = temp;
	  		 });
        }  
    });	
}
}
