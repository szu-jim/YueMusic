/**
 * 
 */
window.onload=function(){
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