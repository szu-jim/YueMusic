// JavaScript Document
$(function () {
            var container = $('#container');
            var list = $('#list');
            var buttons = $('#buttons span');
            var prev = $('#prev');
            var next = $('#next');
            var index = 1;  /*代表当前图片*/
            var picNum = 5;   /*图片个数*/
			var picLen = 750;  /*单张图片长度*/
            var interval = 3000;  /*滚动时间*/
            var timer;

            function myAnimate (offset) {
                var left = parseInt(list.css('left')) + offset;
                if (offset>0) {
                    offset = '+=' + offset;
                }
                else {
                    offset = '-=' + Math.abs(offset);
                }
                list.animate({'left': offset}, 300, function () {
                    if(left > -200){
                        list.css('left', -picLen * picNum);
                    }
                    if(left < (-picLen * picNum)) {
                        list.css('left', -picLen);
                    }
                });
            }

            function showButton() {
                buttons.eq(index-1).addClass('on').siblings().removeClass('on');
            }

            function play() {
                timer = setInterval(function () {
                    next.trigger('click');
                }, interval);
            }
            function stop() {
                clearInterval(timer);
            }

            next.bind('click', function () {
                if (list.is(':animated')) {
                    return;
                }
                if (index == 5) {
                    index = 1;
                }
                else {
                    index += 1;
                }
                myAnimate(-picLen);
                showButton();
				change();
            });

            prev.bind('click', function () {
                if (list.is(':animated')) {
                    return;
                }
                if (index == 1) {
                    index = 5;
                }
                else {
                    index -= 1;
                }
                myAnimate(picLen);
                showButton();
				change();
            });
			
			/*改变背景颜色*/
			function change(){
			switch(index){
				case 1:$('.main-top').css('background-color','RGB(236,233,226)');break;
				case 2:$('.main-top').css('background-color','RGB(6,0,96)');break;
				case 3:$('.main-top').css('background-color','RGB(57,57,57)');break;
				case 4:$('.main-top').css('background-color','RGB(94,155,199)');break;
				case 5:$('.main-top').css('background-color','RGB(4,6,19)');break;
			}
			}

			/*以下为执行代码*/
			
            buttons.each(function () {
                 $(this).bind('click', function () {
                     if (list.is(':animated') || $(this).attr('class')=='on') {
                         return;
                     }
                     var myIndex = parseInt($(this).attr('index'));  //获取要按下的index值
                     var offset = -picLen * (myIndex - index);

                     myAnimate(offset);
                     index = myIndex;
                     showButton();
					 change();
                 })
            });

            container.hover(stop, play);

            play();
			
			

        });