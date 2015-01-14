<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.1-free/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.1-free/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="jquery-easyui-1.4.1-free/demo.css">
	<script type="text/javascript" src="jquery-easyui-1.4.1-free/jquery.min.js"></script>
	<script type="text/javascript" src="jquery-easyui-1.4.1-free/jquery.easyui.min.js"></script>

	<style type="text/css">
        table
        {
            border-collapse: collapse;
            border: none;
            width: 200px;
        }
        td
        {
            border: solid #000 1px;
        }
    </style>
    <script type="text/javascript">
	    function evalCell(){
	    	//$.each("td[name^='R']").function(){};
	    	var msg = "";
	    	$("input[name^='R']").each(function(index){
	    		var name = $(this).attr("name");
	    		var value = $(this).val();
	    		msg += name+"="+value+"&";
	    	});
	    	
	    	msg = msg.substring(0, msg.length-1);
	    	console.log(msg);
			$.ajax({
				 type: "get",
				 url: "eval.htm",
				 data: "info="+encodeURIComponent(msg),
				 success: function(ret){
					 var obj = eval("(" + ret + ")");
					 for(attr in obj){
						 $("td[name='"+attr+"']").text(obj[attr]);
					 }
				 	 console.log( "Data Saved: " + ret );
				 }									
			});
		}
    </script>
</head>
<body>
	<table border="1">
		${table}
	</table>
	<input type="button" value="提交" onclick="evalCell();"/>
</body>
</html>