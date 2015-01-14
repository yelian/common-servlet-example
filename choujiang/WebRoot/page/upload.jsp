<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<html>
	<head><title>upload</title></head>
	<body>
		<form action="uploadExcel.htm" method="post" enctype="multipart/form-data">
			<input type="file" name="excel" />
			<input type="submit" />
		</form>
		<%-- <form action="uploadExcel.htm" method="post" enctype="multipart/form-data"><br>请选择要上传的文件:
            <input type="file" name="file" size=50 accept="application/vnd.ms-excel">
            <input type="submit" value="提交">
        </form> --%>
	</body>
</html>