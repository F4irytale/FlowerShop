<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
     <%@page import="com.bean.*"  %>
       <jsp:useBean id="regUser" class="com.bean.User" scope="session"></jsp:useBean>
       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="Register" method="post">
	<input type="text" name="username" />
	<input type="text" name="password" />
	<input type="text" name="telephone" />
	<input type="text" name="address" />
<input type="submit" value="提交" />

<jsp:getProperty name="regUser" property="backnews"/>
</body>
</html>