// JavaScript Document
// JS实现选项卡切换
    window.onload=function(){
        var atop=document.getElementById("left");
		var top=document.getElementById("right");
        var a=atop.getElementsByTagName("a");
        var ul=top.getElementsByTagName("ol");
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
    		$('.rank').eq(k).find('li').eq(j).html('<span class="singername">'+infolist[0].singer+'</span>--<span class="songname">'+infolist[0].song+'</span>');
    	}
    	//alert(""+infolist[0].singer)
    	}
    }