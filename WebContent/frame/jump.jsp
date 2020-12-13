<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    session.invalidate(); 
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script type="text/javascript">
    if (window.top!=window.self){
        window.top.location="${pageContext.request.contextPath }/login.jsp"; //这是重要的！
    }

</script>
<body>

</body>
</html>