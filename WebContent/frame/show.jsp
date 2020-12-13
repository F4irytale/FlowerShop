<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bean.Search" %>
<%@ page import="com.sun.rowset.*" %>
<jsp:useBean id="search" class="com.bean.Search" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border=2>
  <tr>
    <th>花名</th>
    <th>花语</th>
    <th>单价</th>
    <th>库存</th>
    <th>图片</th>
    <th>操作</th>
  </tr>
<jsp:setProperty name="search" property="pageSize" param="pageSize"/>
<jsp:setProperty name="search" property="currentPage" param="currentPage"/>
<%    
      CachedRowSetImpl rowSet=search.getRowSet();
      if(rowSet==null) {
         out.print("没有查询到结果，无法浏览");
         return;  
      }
      rowSet.last(); 
      int totalRecord=rowSet.getRow();
      out.println("全部记录数"+totalRecord); //全部记录数
      int pageSize=search.getPageSize();  //每页显示的记录数
      int totalPages = search.getTotalPages();
      if(totalRecord%pageSize==0)
           totalPages = totalRecord/pageSize;//总页数
      else
           totalPages = totalRecord/pageSize+1;
      search.setPageSize(pageSize);
      search.setTotalPages(totalPages);
      if(totalPages>=1) {
         if(search.getCurrentPage()<1)
              search.setCurrentPage(search.getTotalPages());
         if(search.getCurrentPage()>search.getTotalPages())
              search.setCurrentPage(1);
         int index=(search.getCurrentPage()-1)*pageSize+1;
         rowSet.absolute(index);  //查询位置移动到currentPage页起始位置
         boolean boo=true;
         for(int i=1;i<=pageSize&&boo;i++) {
			int id = rowSet.getInt(1);
            String name=rowSet.getString(2);
            String price=rowSet.getString(3);
            String total=rowSet.getString(4);
            String img=rowSet.getString(5);
            String disc=rowSet.getString(6);
            out.print("<tr>");
            out.print("<td>"+name+"</td>");
            out.print("<td>"+disc+"</td>");
            out.print("<td>"+price+"</td>");
            out.print("<td>"+total+"</td>");
            out.print("<td>"+img+"</td>");
            out.print("<td><a href='Add?flowerId="+id+"&flowerName="+name+"&flowerPrice="+price+"&total="+total+"'>下单</a></td>");
            out.print("</tr>");
            boo=rowSet.next();
         }
     }
%>
</table>
  <BR>每页最多显示<jsp:getProperty name="search" property="pageSize"/>条信息
 <BR>当前显示第<Font color=blue>
     <jsp:getProperty name="search" property="currentPage"/>
   </Font>页,共有
   <Font color=blue><jsp:getProperty name="search" property="totalPages"/>
   </Font>页。
   
   <Table>
  <tr><td><FORM action="" method=post>
          <Input type=hidden name="currentPage" value=
         "<%=search.getCurrentPage()-1 %>">
           <Input type=submit name="g" value="上一页"></FORM></td>
      <td><FORM action="" method=post>
          <Input type=hidden name="currentPage"
           value="<%=search.getCurrentPage()+1 %>">
          <Input type=submit name="g" value="下一页"></FORM></td></tr>
 <tr><td> <FORM action="" method=post>
</Table>
</body>
</html>