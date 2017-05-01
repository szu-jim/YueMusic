// JavaScript Document
var playlist=new Array();	
var firstSongID;
var lastSongID;
var nowNumber;

$('.rank li').click(function(){
	var this_id = $(this).attr("id");
	now(this_id);
	audioEle.src=playlist[1].mp3;
	audioEle.play();    //播放
	$('#play').removeClass('playback').addClass('pause');
	$(this).parents('.right').find('li').css('background-color','RGB(247,247,247');
	$(this).parents('#right').find('li').css('background-color','RGB(247,247,247');
	$(this).css('background-color','RGB(230,230,230');
	$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[1].singer+'</div>'+'<div class="p_songname">'+playlist[1].song+'</div>');		//获取歌名，歌手名
	$('.progress .right').html(playlist[1].playTime);
	timeout = setInterval(updateProgress, 1000);
	//event.stopPropagation();	
})

function now(id){ 

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
	   			temp.mp3=item.song_path;
	   			temp.song=item.song;
	   			temp.album=item.song_path;
	   			temp.singer=item.singer;
	   			temp.number=item.number;
	   			temp.id=item.id;
	   			temp.amount=item.amount;
	   			temp.playTime=item.play_time;
	   			temp.musicGroup=item.music_group;
	   			playlist[1] = temp;
	  		 });
        }  
    });
	
	//alert("yes"+playlist[1].number);
	nowNumber = playlist[1].number;
	firstSongID = 1;
	lastSongID = playlist[1].amount;	
	//alert(lastSongID);
}
 function last(id){ 
	 var musicGroup = playlist[1].musicGroup;
	$.ajax( {  
		type : "post",  
     url : "getMusicDetailByNumber",  
     data : "number="+id+"&musicGroup="+musicGroup.toString(),   
     dataType: "json", 
     contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     async:false, 
     success : function(msg) {  
        	$.each(msg.detailInfo, function (index, item){
	      		var temp={};		
	   			temp.mp3=item.song_path;
	   			temp.song=item.song;
	   			temp.album=item.song_path;
	   			temp.singer=item.singer;
	   			temp.playTime=item.play_time;
	   			temp.id=item.id;
	   			playlist[0] = temp;
	  		 });
        }  
    });
		
		
 }

function next(id){ 
	var musicGroup = playlist[1].musicGroup;
	$.ajax( {  
		type : "post",  
     url : "getMusicDetailByNumber",  
     data : "number="+id+"&musicGroup="+musicGroup.toString(),    
     dataType: "json", 
     contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     async:false, 
     success : function(msg) {  
        	$.each(msg.detailInfo, function (index, item){
	      		var temp={};		
	   			temp.mp3=item.song_path;
	   			temp.song=item.song;
	   			temp.album=item.song_path;
	   			temp.singer=item.singer;
	   			temp.playTime=item.play_time;
	   			temp.id=item.id;
	   			playlist[2] = temp;
	  		 });
        }  
    });
	
	
}

function return2First(){ 
	var musicGroup = playlist[1].musicGroup;
	//alert(singer);
	$.ajax( {  
		type : "post",  
     url : "getMusicDetailByNumber",  
     data : "number="+firstSongID+"&musicGroup="+musicGroup.toString(),   
     dataType: "json", 
     contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     async:false, 
     success : function(msg) {  
        	$.each(msg.detailInfo, function (index, item){
	      		var temp={};		
	   			temp.mp3=item.song_path;
	   			temp.song=item.song;
	   			temp.album=item.song_path;
	   			temp.singer=item.singer;
	   			temp.playTime=item.play_time;
	   			temp.id=item.id;
	   			playlist[4] = temp;
	  		 });
        }  
    });
	
	
}

function return2Last(){ 
	var musicGroup = playlist[1].musicGroup;
	$.ajax( {  
		type : "post",  
     url : "getMusicDetailByNumber",  
     data : "number="+lastSongID+"&musicGroup="+musicGroup.toString(),  
     dataType: "json", 
     contentType: "application/x-www-form-urlencoded; charset=utf-8", 
     async:false, 
     success : function(msg) {  
        	$.each(msg.detailInfo, function (index, item){
	      		var temp={};		
	   			temp.mp3=item.song_path;
	   			temp.song=item.song;
	   			temp.album=item.song_path;
	   			temp.singer=item.singer;
	   			temp.playTime=item.play_time;
	   			temp.id=item.id;
	   			playlist[3] = temp;
	  		 });
        }  
    });
	
	
}






