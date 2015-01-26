<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>抽奖结果</title>
	<script src="js/jquery-1.3.2.min.js"></script>
	<script src="js/jquery.kxbdmarquee.js"></script>
	<script type="text/javascript">
		$(function(){
			reSize();
			$("#marquee1").kxbdMarquee({direction:"down"});
			$("#marquee2").kxbdMarquee({isEqual:false, direction:"up"});
			$("#marquee3").kxbdMarquee({direction:"down"});
			//$("#marquee4").kxbdMarquee({direction:"up",isEqual:false});			
		});
		
		function reSize(){
			if($.browser.msie){
				$("body").css({overflow:"hidden" });
				var bgHeight = $("#bg").height();
				var cgheadHeight = $("#cj_head").height();
				//console.log(bgHeight + ":" + cgheadHeight + ":" + (bgHeight - cgheadHeight));
				$("#marquee1").height(bgHeight - cgheadHeight - 60);
				$("#marquee2").height(bgHeight - cgheadHeight - 60);
				$("#marquee3").height(bgHeight - cgheadHeight - 60);
			}
		}
	</script>
	
	<style type="text/css">
		*{margin:0;padding:0;}
		body{font-size:12px;}
		a{color:#333;}
		ul{list-style:none;}
		
		#awardAera{text-align: center; heigth: 100%; overflow: hidden;}
		#title{float:left; width:260px;height:70%;overflow:hidden;}
		
		#marquee1{float:left; width:246px;height:70%;overflow:hidden;}
		#marquee1 ul li{padding:1px 0;}
		
		#marquee2{float:left; width:246px;height:70%;overflow:hidden;}
		#marquee2 ul li{padding:1px 0;}
		
		#marquee3{float:left; width:246px;height:70%;overflow:hidden;}
		#marquee3 ul li{padding:1px 0;}
		
		<%--#marquee4{position:absolute;top:150px;left:800px;width:200px;height:200px; overflow:hidden;}
		#marquee4 ul li{float:left; width:180px; padding:10px; line-height:20px;}--%>
		
		p{TEXT-ALIGN: center; color: red; font-size:36px; font-weight:900; margin-bottom:30px; }
	</style>
  </head>
  
  <body onresize="reSize();">
  	<div style="position:absolute; width: 100%; height: 100%; z-index:-1" id="bg">
			<img src="img/01.jpg" height="100%" width="100%"/>
	</div>
	<div id="cj_head">
		<p>九江银行2014年行庆晚会抽奖结果：</p>
		<p>幸运之星：${award4}</p>
	</div>
	<div id="awardAera">
		<div id="title">
			<p>中奖名单：</p>
		</div>
	    <div id="marquee1">
			<ul>
				${award1 }
			</ul>
		</div>
		
		<div id="marquee2">
			<ul>
				${award2 }
			</ul>
		</div>
		
		<div id="marquee3">
			<ul>
				${award3 }
			</ul>
		</div>
	</div>
	<%--<div id="marquee4">
		<ul>
			${award4 }
		</ul>
	</div>
  --%></body>
</html>
