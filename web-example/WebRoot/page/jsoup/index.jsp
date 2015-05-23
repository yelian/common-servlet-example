<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Top Customers with Callback</title>
</head>
<body>
    <div id="divCustomers">
    </div>
    <script type="text/javascript">
        function onCustomerLoaded(result, methodName) {
            var html = '<ul>';
            for (var i = 0; i < result.length; i++) {
                html += '<li>' + result[i].id + ':' + result[i].name + '</li>';
            }
            html += '</ul>';
            document.getElementById('divCustomers').innerHTML = html;
        }
    </script>
    <script type="text/javascript" src="http://localhost:8080/web-example/jsonp.htm?jsonp=onCustomerLoaded"></script>
</body>
</html>