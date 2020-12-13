<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


</head>

<bod leftMargin="0" topMargin="0" marginwidth="0" marginheight="0">
	<table width="90%" border="0" cellspacing="1" cellpadding="2" align="center">

				<div class="layui-input-block">
					<a href="Show" target="right" >商品信息</a>
					<a href="Showcar" target="right" style="margin-left: 50px;">我的订单</a>
					<a href="Admin" target="right"  style="margin-left: 50px;">个人中心</a>
					<a href="jump.jsp" target="right" style="margin-left: 50px;">退出登录</a>
				</div>

	</table>
</bod>
</html>
