function now(a){$.ajax({type:"post",url:"getMusicDetailByID",data:"id="+a,dataType:"json",async:!1,success:function(a){$.each(a.detailInfo,function(a,n){playById.mp3=n.song_path,playById.song=n.song,playById.singer=n.singer,playById.number=n.number,playById.id=n.id,playById.amount=n.amount,playById.playTime=n.play_time,playById.musicGroup=n.music_group})}}),nowNumber=playById.number,lastSongID=playById.amount,nowId=playById.id}function last(a){var n=playlist[1].musicGroup;$.ajax({type:"post",url:"getMusicDetailByNumber",data:"number="+a+"&musicGroup="+n.toString(),dataType:"json",contentType:"application/x-www-form-urlencoded; charset=utf-8",async:!1,success:function(a){$.each(a.detailInfo,function(a,n){var t={};t.mp3=n.song_path,t.song=n.song,t.album=n.song_path,t.singer=n.singer,t.playTime=n.play_time,t.id=n.id,playlist[0]=t})}})}var playById={},plyByNum={},firstSongID=1,lastSongID,nowNumber,nowId;