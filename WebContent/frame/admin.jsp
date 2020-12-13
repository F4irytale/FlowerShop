<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="com.bean.Search" %>
<%@ page import="com.sun.rowset.*" %>
<jsp:useBean id="searchuser" class="com.bean.Search" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%    
      CachedRowSetImpl rowSet=searchuser.getRowSet();
      if(rowSet==null) {
         out.print("没有查询到结果，无法浏览");
         return;  
      }
      rowSet.last(); 
      int totalRecord=rowSet.getRow();
      
      int pageSize=searchuser.getPageSize();  //每页显示的记录数
      int totalPages = searchuser.getTotalPages();
      if(totalRecord%pageSize==0)
           totalPages = totalRecord/pageSize;//总页数
      else
           totalPages = totalRecord/pageSize+1;
      searchuser.setPageSize(pageSize);
      searchuser.setTotalPages(totalPages);
      if(totalPages>=1) {
         if(searchuser.getCurrentPage()<1)
        	 searchuser.setCurrentPage(searchuser.getTotalPages());
         if(searchuser.getCurrentPage()>searchuser.getTotalPages())
        	 searchuser.setCurrentPage(1);
         int index=(searchuser.getCurrentPage()-1)*pageSize+1;
         rowSet.absolute(index);  //查询位置移动到currentPage页起始位置
         boolean boo=true;
         for(int i=1;i<=pageSize&&boo;i++) {
			
            String username=rowSet.getString(2);
            String password=rowSet.getString(3);
            String telephone=rowSet.getString(4);
            String address=rowSet.getString(5);
            
            
          	out.print("<form action='Change' method='post'><input type='text' name='username' value="+username+" disabled><br><input type='text' name='password' value="+password+"><br><input type='text' name='telephone' value="+telephone+"><br><textarea style='resize:none;'>"+address+"</textarea><br><input type='submit' value='提交' ></form>");
            boo=rowSet.next();
         }
     }
%>

</body>
</html>