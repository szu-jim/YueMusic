<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<html>
  <head>
  <script src="http://libs.baidu.com/jquery/1.9.1/jquery.js"></script> 
<script src="http://apps.bdimg.com/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
<link type="text/css" href="css/base.css" rel="stylesheet" />
<link type="text/css" href="css/indexPhoto.css" rel="stylesheet" />
<script src="js/indexPhoto.js">  </script>
 <script type="text/javascript">  
                
       function test(singer){ 
       	//alert(obj); //[object HTMLInputElement] 
       	//alert(obj.id); //myinput 
       	//alert(obj.value); //javascript中onclick中的this 
       	var playlist=new Array();
       	var a;
       	$.ajax( {  
               type : "post",  
               url : "getMusicDetailByNumber",  
               data : "number=2&singer="+singer.toString(),  
               dataType: "json", 
               async:false, 
               success : function(msg) {  
               	$.each(msg.detailInfo, function (index, item){
   		      		var temp={};
   		      		a=item.song_path;
   		   			temp.mp3=item.song_path;
   		   			temp.song=item.song;
   		   			temp.album=item.song_path;
   		   			temp.singer=item.singer;
   		   			playlist[index] = temp;
   		  		 });
               }  
           });
       	//if(playlist.length>0){
        //		alert(""+playlist[0].mp3);	
       	//}
       	alert(a);
       } 
  </script>  
  </head>
  <body> 
  <div id="myinput" onclick="javascript:test('侧田');">感动 </div>
  <!--  
  <input id="myinput" type="button" value="男人KTV" onclick="javascript:test('rank51');"/> 
  
  <input id="myinput" type="button" value="爱的习惯" onclick="javascript:test('rank53');"/> 
  -->
  
 
  </body>
</html>
