// JavaScript Document
// JS实现选项卡切换
    window.onload=function(){
		var temp={};
        var atop=document.getElementById("left");
		var top=document.getElementById("right");
        var a=atop.getElementsByTagName("a");
        var ul=top.getElementsByTagName("ol");
		
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
        	   			temp.song=item.song;
        	   			temp.singer=item.singer;
        	   			temp.id=item.id;
        	  		 });
                }  
            });	
        }
		
        for(var i=0;i<a.length;i++){
            a[i].index=i;
            a[i].onmouseover=function(){
                for(var j=0;j<a.length;j++){
                    a[j].className="";
                }
                this.className="on";
                for(var j=0;j<ul.length;j++){
                    ul[j].className="hide";
                }
                ul[this.index].className="";
            }
        }
        
        var rank=document.getElementsByClassName('rank');
    	for(k=0;k<rank.length;k++){
    	var rli=rank[k].getElementsByTagName("li");
    	for(j=0;j<rli.length;j++){
    		var liid = $('.rank').eq(k).find('li').eq(j).attr("id");
    		getInfo(liid);
    		$('.rank').eq(k).find('li').eq(j).html('<span class="singername">'+temp.singer+'</span>--<span class="songname">'+temp.song+'</span>');
    	}
    	}
    }