// JavaScript Document
$(function(){
	
	var playItem={};
	var nowId,nowNum;
	var musicGroup;
	var audioEle = $("audio")[0];
    var playType = localStorage.playType || 0;
	var timeout;
    if(playType == 0){
		$('.type1').css('opacity','1');
	}else if(playType == 1){
		$('.type2').css('opacity','1');
	}


	function now(id){ 

	$.ajax( {  
		type : "post",  
    	url : "getMusicDetailByID",  
    	data : "id="+id,  
    	dataType: "json", 
    	 contentType: "application/x-www-form-urlencoded; charset=utf-8", 
    	 async:false, 
    	 success : function(msg) {  
        	$.each(msg.detailInfo, function (index, item){		
        		playItem.mp3=item.song_path;
        		playItem.song=item.song;
        		playItem.singer=item.singer;
        		playItem.number=item.number;                  //歌曲在列表中的顺序
        		playItem.id=item.id;
        		playItem.amount=item.amount;                  //歌曲类别总数
        		playItem.playTime=item.play_time;
        		playItem.musicGroup=item.music_group;
	  		 }); 
		nowId = playItem.id;
		nowNum = playItem.number;
		} 
    });		
}
      
	function next(num){ 
		musicGroup = playItem.musicGroup;
		$.ajax( {  
			type : "post",  
	     url : "getMusicDetailByNumber",  
	     data : "number="+num+"&musicGroup="+musicGroup.toString(),    
	     dataType: "json", 
	     contentType: "application/x-www-form-urlencoded; charset=utf-8", 
	     async:false, 
	     success : function(msg) {  
	        	$.each(msg.detailInfo, function (index, item){		
	        		playItem.mp3=item.song_path;
	        		playItem.song=item.song;
	        		playItem.singer=item.singer;
	        		playItem.playTime=item.play_time;
	        		playItem.id=item.id;
		  		 });
	        	nowId = playItem.id;
	        }  
	    });
		
		
	}
		
		//顺序播放
	audioEle.loop = false;
	function eventListen(e,type,handler){
		if(e.addEventListener){
			e.addEventListener(type,handler,false);
		}else if(e.attachEvent){
			e.attachEvent("on"+type,handler);
		}else{
			e["on"+type]=handler;
		}
	}
	eventListen(audioEle,'ended',function(){mynext()});
		
		$('.rank li').click(function(){
			var this_id = $(this).attr("id");
			now(this_id);			
			setBG(playItem);
			setName(playItem);			
})

		//播放按键
		$('#play').click(function(){
			if ($(this).hasClass('playback')){	
				audioEle.play();    //播放
				$(this).removeClass('playback').addClass('pause');
				$('.progress .middle .p_top').html('<div class="p_singername">'+playItem.singer+'</div>'+'<div class="p_songname">'+playItem.song+'</div>');		//获取歌名，歌手名
				$('.progress .right').html(playItem.playTime);
				timeout = setInterval(updateProgress, 1000);				
			}else if($(this).hasClass('pause')){
				audioEle.pause();    //暂停
				$(this).removeClass('pause').addClass('playback');
				clearInterval(updateProgress);
			}
		})
		
		$('.type1').click(function(){
			playType = localStorage.playType = 0;
			$('.type1').css('opacity','1');
			$('.type2').css('opacity','0.3');
		}) 
		$('.type2').click(function(){
			playType = localStorage.playType = 1;
			$('.type2').css('opacity','1');
			$('.type1').css('opacity','0.3');
		})
		
		
		function setBG(playBy){
			$('#'+playBy.id).parents('.right').find('li').css('background-color','RGB(247,247,247');
			$('#'+playBy.id).parents('#right').find('li').css('background-color','RGB(247,247,247');
			$('#'+playBy.id).css('background-color','RGB(230,230,230');
		}
		
		function setName(playBy){
			audioEle.src=playBy.mp3;
			audioEle.play();    //播放
			$('#play').removeClass('playback').addClass('pause');
			$('.progress .middle .p_top').html('<div class="p_singername">'+playBy.singer+'</div>'+'<div class="p_songname">'+playBy.song+'</div>');		//获取歌名，歌手名
			$('.progress .right').html(playBy.playTime);
			timeout = setInterval(updateProgress, 1000);
		}
		
		//下一首
		$('.fastforward').click(function(){
			mynext();
		});
		
		function mynext(){
			if(playType == 0){
				if(nowNum < playItem.amount){
					nowNum ++;				
			 	}else{
			 		nowNum = 1;					
				}
			}else if(playType == 1){
					nowNum = Math.floor(Math.random()*playItem.amount+1);
			}
			next(nowNum);
			setBG(playItem);
			setName(playItem);			
		}
		
		//上一首	
		$('.rewind').click(function(){
			
		if(playType == 0){
				if(nowNum > 1){
					nowNum --;				
			 	}else{
			 		nowNum = playItem.amount;					
				}
			}else if(playType == 1){
				nowNum = Math.floor(Math.random()*playItem.amount+1);
			}
			next(nowNum);
			setBG(playItem);
			setName(playItem);
		});
		
		//左时间计时
		var setProgress = function(value){
			var currentSec = parseInt(value%60) < 10 ? '0' + parseInt(value%60) : parseInt(value%60);
			var currentMin = parseInt(value/60) < 10 ? '0' + parseInt(value/60) : parseInt(value/60);
			ratio = value / audioEle.duration * 100;

			$('.timer').html(currentMin+':'+currentSec);
			$('.progress .pace').css('width', ratio + '%');
			$('.progress .slider a').css('left', ratio + '%');
		}
		var updateProgress = function(){
			setProgress(audioEle.currentTime);
		}
		
		//使用slider控件实现播放条
		$('.progress .slider').slider({min: 0, max: 100, step: 0.1, slide: function(event, ui){
		$(this).addClass('enable');
		setProgress(audioEle.duration * ui.value / 100);
		clearInterval(timeout);
	    }, stop: function(event, ui){
		audioEle.currentTime = audioEle.duration * ui.value / 100;
		$(this).removeClass('enable');
		timeout = setInterval(updateProgress, 500);
	    }});
		
		
		
		
		// 使用slider控件实现音量控制条
	var setVolume = function(value){
		audio.volume = localStorage.volume = value;
		$('.volume .pace').css('width', value * 100 + '%');
		$('.volume .slider a').css('left', value * 100 + '%');
	}

	var volume = localStorage.volume || 0.5;
	audio.volume = localStorage.volume || 0.5;
	$('.volume .slider').slider({max: 1, min: 0, step: 0.01, value: volume, slide: function(event, ui){
		setVolume(ui.value);
		$(this).addClass('enable');
		$('.mute').removeClass('enable');
	}, stop: function(){
		$(this).removeClass('enable');
	}}).children('.pace').css('width', volume * 100 + '%');

	$('.mute').click(function(){
		if ($(this).hasClass('enable')){
			setVolume($(this).data('volume'));
			$(this).removeClass('enable');
		} else {
			$(this).data('volume', audio.volume).addClass('enable');
			setVolume(0);
		}
	});
})