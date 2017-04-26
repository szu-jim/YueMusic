// JavaScript Document
// JS实现选项卡切换
    window.onload=function(){
        var atop=document.getElementById("left");
		var top=document.getElementById("right");
        var a=atop.getElementsByTagName("a");
        var ul=top.getElementsByTagName("ul");
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
                ul[this.index].className="singer";
            }
        }
    }