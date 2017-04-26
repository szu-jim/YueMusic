// JavaScript Document
$(function () {
            var container = $('#container');
            var list = $('#list');
            var buttons = $('#buttons span');
            var prev = $('#prev');
            var next = $('#next');
            var index = 1;
            var len = 5;
            var interval = 3000;
            var timer;


            function animate (offset) {
                var left = parseInt(list.css('left')) + offset;
                if (offset>0) {
                    offset = '+=' + offset;
                }
                else {
                    offset = '-=' + Math.abs(offset);
                }
                list.animate({'left': offset}, 300, function () {
                    if(left > -200){
                        list.css('left', -750 * len);
                    }
                    if(left < (-750 * len)) {
                        list.css('left', -750);
                    }
                });
            }

            function showButton() {
                buttons.eq(index-1).addClass('on').siblings().removeClass('on');
            }

            function play() {
                timer = setTimeout(function () {
                    next.trigger('click');
					change();
                    play();
                }, interval);
            }
            function stop() {
                clearTimeout(timer);
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
                animate(-750);
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
                animate(750);
                showButton();
				change();
            });

            buttons.each(function () {
                 $(this).bind('click', function () {
                     if (list.is(':animated') || $(this).attr('class')=='on') {
                         return;
                     }
                     var myIndex = parseInt($(this).attr('index'));
                     var offset = -750 * (myIndex - index);

                     animate(offset);
                     index = myIndex;
                     showButton();
					 change();
                 })
            });

            container.hover(stop, play);

            play();
			
			function change(){
			switch(index){
				case 1:$('.main-top').css('background-color','RGB(236,233,226)');break;
				case 2:$('.main-top').css('background-color','RGB(6,0,96)');break;
				case 3:$('.main-top').css('background-color','RGB(57,57,57)');break;
				case 4:$('.main-top').css('background-color','RGB(94,155,199)');break;
				case 5:$('.main-top').css('background-color','RGB(4,6,19)');break;
			}
			}

        });