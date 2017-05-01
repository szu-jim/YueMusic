// JavaScript Document
$(function(){
        var audioEle = $("audio")[0];
        var playType = localStorage.playType || 0;
        if(playType == 0){
			$('.type1').css('opacity','1');
		}else if(playType == 1){
			$('.type2').css('opacity','1');
		}

		//播放按键
		$('#play').click(function(){
			if ($(this).hasClass('playback')){					
				audioEle.play();    //播放				
				$(this).removeClass('playback').addClass('pause');
				$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[0].singer+'</div>'+'<div class="p_songname">'+playlist[0].song+'</div>');		//获取歌名，歌手名
				$('.progress .right').html(playlist[0].playTime);
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
		
		
		//顺序播放
		var audio = document.getElementById("audio"); 
		audio.loop = false;
		audio.addEventListener('ended', function () {  
    	mynext();}, false);
		
		//下一首
		$('.fastforward').click(function(){
			mynext();
		});
		function mynext(){
			//alert(lastSongID);
			if(playType == 0){
				if(nowNumber < lastSongID){
					//last(nowNumber);
					nowNumber = nowNumber + 1;
					next(nowNumber);
					audioEle.src=playlist[2].mp3;
					audioEle.play();    //播放
					$('#'+playlist[2].id).parents('.right').find('li').css('background-color','RGB(247,247,247');
					$('#'+playlist[2].id).parents('#right').find('li').css('background-color','RGB(247,247,247');
					$('#'+playlist[2].id).css('background-color','RGB(230,230,230');
					$('#play').removeClass('playback').addClass('pause');
					$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[2].singer+'</div>'+'<div class="p_songname">'+playlist[2].song+'</div>');		//获取歌名，歌手名
					$('.progress .right').html(playlist[2].playTime);
			 	}else{
			 		nowNumber = firstSongID;
			 		//alert("yes");
			 		return2First();
			 		audioEle.src=playlist[4].mp3;
			 		audioEle.play();    //播放
			 		$('#'+playlist[4].id).parents('.right').find('li').css('background-color','RGB(247,247,247');
			 		$('#'+playlist[4].id).parents('#right').find('li').css('background-color','RGB(247,247,247');
			 		$('#'+playlist[4].id).css('background-color','RGB(230,230,230');
			 		$('#play').removeClass('playback').addClass('pause');
			 		$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[4].singer+'</div>'+'<div class="p_songname">'+playlist[4].song+'</div>');		//获取歌名，歌手名
			 		$('.progress .right').html(playlist[4].playTime);
				}
			}else if(playType == 1){
					var time=new Date();
					nowNumber = time.getTime() % playlist[1].amount;
					//alert(nowNumber);
					next(nowNumber);
					audioEle.src=playlist[2].mp3;
					audioEle.play();    //播放
					$('#'+playlist[2].id).parents('.right').find('li').css('background-color','RGB(247,247,247');
					$('#'+playlist[2].id).parents('#right').find('li').css('background-color','RGB(247,247,247');
					$('#'+playlist[2].id).css('background-color','RGB(230,230,230');
					$('#play').removeClass('playback').addClass('pause');
					$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[2].singer+'</div>'+'<div class="p_songname">'+playlist[2].song+'</div>');		//获取歌名，歌手名
					$('.progress .right').html(playlist[2].playTime);
			}
		}
		
		//上一首	
		$('.rewind').click(function(){
			
		if(playType == 0){	
			if(nowNumber > firstSongID){
				//next(nowNumber);
				nowNumber = nowNumber - 1;
				last(nowNumber);
				audioEle.src=playlist[0].mp3;
				audioEle.play();    //播放
				$('#'+playlist[0].id).parents('.right').find('li').css('background-color','RGB(247,247,247');
				$('#'+playlist[0].id).parents('#right').find('li').css('background-color','RGB(247,247,247');
				$('#'+playlist[0].id).css('background-color','RGB(230,230,230');
				$('#play').removeClass('playback').addClass('pause');
				$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[0].singer+'</div>'+'<div class="p_songname">'+playlist[0].song+'</div>');		//获取歌名，歌手名
				$('.progress .right').html(playlist[0].playTime);
		 	}else{
		 		nowNumber = lastSongID;
		 		return2Last();
		 		audioEle.src=playlist[3].mp3;
		 		audioEle.play();    //播放
		 		$('#'+playlist[3].id).parents('.right').find('li').css('background-color','RGB(247,247,247');
		 		$('#'+playlist[3].id).parents('#right').find('li').css('background-color','RGB(247,247,247');
		 		$('#'+playlist[3].id).css('background-color','RGB(230,230,230');
		 		$('#play').removeClass('playback').addClass('pause');
		 		$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[3].singer+'</div>'+'<div class="p_songname">'+playlist[3].song+'</div>');		//获取歌名，歌手名
		 		$('.progress .right').html(playlist[3].playTime);
		 	}
		}else if(playType == 1){
			var time=new Date();
			nowNumber = time.getTime() % playlist[1].amount;
			//alert(nowNumber);
			last(nowNumber);
			audioEle.src=playlist[0].mp3;
			audioEle.play();    //播放
			$('#'+playlist[0].id).parents('.right').find('li').css('background-color','RGB(247,247,247');
			$('#'+playlist[0].id).parents('#right').find('li').css('background-color','RGB(247,247,247');
			$('#'+playlist[0].id).css('background-color','RGB(230,230,230');
			$('#play').removeClass('playback').addClass('pause');
			$('.progress .middle .p_top').html('<div class="p_singername">'+playlist[0].singer+'</div>'+'<div class="p_songname">'+playlist[0].song+'</div>');		//获取歌名，歌手名
			$('.progress .right').html(playlist[0].playTime);
		}
	
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
		$('.progress .slider').slider({step: 0.1, slide: function(event, ui){
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