package com.huadian;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SubServlet
 */
public class SubServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession(true);
		String username = (String) session.getAttribute("username");
		int id =Integer.parseInt(request.getParameter("flowerId"));
		String flowername= request.getParameter("flowerName");
		
		Connection con; 
	     Statement sqlselect; 
	     Statement update;
	     Statement delete; 
	     String uri = "jdbc:mysql://127.0.0.1:3307/flowershop?"+"user=root&password=123456&characterEncoding=utf-8";
	     try{ 
	           con=DriverManager.getConnection(uri);
	           String sqlname = "select * from shoppingcar where flower='"+flowername+"' and username='"+username+"'";
	           sqlselect=con.createStatement();  
	           ResultSet rs=sqlselect.executeQuery(sqlname);
	              while(rs.next()){
	            	  String name = rs.getString("flower");
	            	  String flowernumber = rs.getString("number");
	            	  Double flowerprice=rs.getDouble("price");
	            	  int number = Integer.parseInt(flowernumber);
	            	  if(name.equals(flowername)) {
	            		  double nowprice = flowerprice/number;
	            		  number = number-1;
	            		  if(number==0) {
	            			  
	            			  String updatename = "delete from shoppingcar where flower='"+flowername+"' and username='"+username+"'";
		            		  delete=con.createStatement();
		            		  int n =delete.executeUpdate(updatename);
		    	              if(n != 0){ 
		    	            	  System.out.print("删除成功");
		    	                  RequestDispatcher dispatcher=
		    	                  request.getRequestDispatcher("Showcar");//转发
		    	                  dispatcher.forward(request,response);
		    	              }
		    	              else{
		    	            	
		    	  				 request.getRequestDispatcher("Showcar").forward(request, response);
		    	              }
	            			  
	            			  
	            			  
	            		  }else {
	            		  
	            		  flowerprice = nowprice*number;
	            		  String updatename = "update shoppingcar set number="+number+",price="+flowerprice+"where flower='"+flowername+"' and username='"+username+"'";
	            		  update=con.createStatement();
	            		  int n =update.executeUpdate(updatename);
	    	              if(n != 0){ 
	    	            	  System.out.print("修改成功");
	    	                  RequestDispatcher dispatcher=
	    	                  request.getRequestDispatcher("Showcar");//转发
	    	                  dispatcher.forward(request,response);
	    	              }
	    	              else{
	    	            	  String backNews="插入失败";
	    	  				 request.getRequestDispatcher("Showcar").forward(request, response);
	    	              }
	            	 }
	    	           
	             }
	        }
		          
	       
	              con.close();
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	     
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
