<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="com.bean.Search" %>
<%@ page import="com.sun.rowset.*" %>
<jsp:useBean id="searchcar" class="com.bean.Search" scope="session"/>
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
    <th>数量</th>
    <th>价格</th>
    <th>操作</th>
  </tr>
<jsp:setProperty name="searchcar" property="pageSize" param="pageSize"/>
<jsp:setProperty name="searchcar" property="currentPage" param="currentPage"/>
<%    
      CachedRowSetImpl rowSet=searchcar.getRowSet();
      if(rowSet==null) {
         out.print("没有查询到结果，无法浏览");
         return;  
      }
      rowSet.last(); 
      int totalRecord=rowSet.getRow();
      out.println("全部记录数"+totalRecord); //全部记录数
      int pageSize=searchcar.getPageSize();  //每页显示的记录数
      int totalPages = searchcar.getTotalPages();
      if(totalRecord%pageSize==0)
           totalPages = totalRecord/pageSize;//总页数
      else
           totalPages = totalRecord/pageSize+1;
      searchcar.setPageSize(pageSize);
      searchcar.setTotalPages(totalPages);
      if(totalPages>=1) {
         if(searchcar.getCurrentPage()<1)
              searchcar.setCurrentPage(searchcar.getTotalPages());
         if(searchcar.getCurrentPage()>searchcar.getTotalPages())
              searchcar.setCurrentPage(1);
         int index=(searchcar.getCurrentPage()-1)*pageSize+1;
         rowSet.absolute(index);  //查询位置移动到currentPage页起始位置
         boolean boo=true;
         for(int i=1;i<=pageSize&&boo;i++) {
			int id = rowSet.getInt(1);
            String flower=rowSet.getString(3);
            String number=rowSet.getString(4);
            String price=rowSet.getString(5);
          
            out.print("<tr>");
            out.print("<td>"+flower+"</td>");
          
            out.print("<td>"+number+"</td>");
            out.print("<td>"+price+"</td>");
            out.print("<td><a href='Sub?flowerId="+id+"&flowerName="+flower+"&flowerPrice="+price+"'>删除</a></td>");   
            out.print("</tr>");
            boo=rowSet.next();
         }
     }
%>
</table>
  <BR>每页最多显示<jsp:getProperty name="searchcar" property="pageSize"/>条信息
 <BR>当前显示第<Font color=blue>
     <jsp:getProperty name="searchcar" property="currentPage"/>
   </Font>页,共有
   <Font color=blue><jsp:getProperty name="searchcar" property="totalPages"/>
   </Font>页。
   
   <Table>
  <tr><td><FORM action="" method=post>
          <Input type=hidden name="currentPage" value=
         "<%=searchcar.getCurrentPage()-1 %>">
           <Input type=submit name="g" value="上一页"></FORM></td>
      <td><FORM action="" method=post>
          <Input type=hidden name="currentPage"
           value="<%=searchcar.getCurrentPage()+1 %>">
          <Input type=submit name="g" value="下一页"></FORM></td></tr>
 <tr><td> <FORM action="" method=post>
</Table>
</body>
</html>