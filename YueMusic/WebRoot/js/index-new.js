// JavaScript Document
var playById={};
var plyByNum={};	
var firstSongID=1;
var lastSongID;
var nowNumber;
var nowId;


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
	   			playById.mp3=item.song_path;
	   			playById.song=item.song;
	   			//temp.album=item.song_path;
	   			playById.singer=item.singer;
	   			playById.number=item.number;
	   			playById.id=item.id;
	   			playById.amount=item.amount;
	   			playById.playTime=item.play_time;
	   			playById.musicGroup=item.music_group;
	  		 });
        }  
    });	
	nowNumber = playById.number;
	lastSongID = playById.amount;
	nowId = playById.id;	
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
/*
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
*/	





