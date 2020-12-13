package com.huadian;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Search;
import com.sun.rowset.CachedRowSetImpl;

/**
 * Servlet implementation class Addtocar
 */

public class Addtocar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addtocar() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        try{ 
             Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e){} 
     }
     public String handleString(String s){
        try{  byte bb[]=s.getBytes("iso-8859-1");
              s=new String(bb);
        }
        catch(Exception ee){} 
        return s;  
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
		int oldtotal =Integer.parseInt(request.getParameter("total"));
		String flowername= request.getParameter("flowerName");
		Double flowerprice=Double.parseDouble(request.getParameter("flowerPrice"));

		 Connection con; 
	     Statement sqlselect; 
	     Statement update; 
	     Statement insert; 
	     Statement sub;
	     Statement subup;
	     String uri = "jdbc:mysql://127.0.0.1:3307/flowershop?"+"user=root&password=123456&characterEncoding=utf-8";
	      try{ 
	           con=DriverManager.getConnection(uri);
	           String sqlname = "select * from shoppingcar where flower='"+flowername+"' and username='"+username+"'";;
	           sqlselect=con.createStatement();  
	           ResultSet rs=sqlselect.executeQuery(sqlname);
	         if(oldtotal>0) {
	        	 if(rs.next()==false) {
	            	  
	            	  String insertname="insert into shoppingcar(username,flower,number,price) values('"+username+"','"+flowername+"',1,"+flowerprice+")";
		              insert=con.createStatement();
	        		  int n =insert.executeUpdate(insertname);
	        		  if(n != 0){ 
		            	  String subname ="select * from flower where name='"+flowername +"'";
		            	  sub=con.createStatement();  
		   	           	  ResultSet rssub=sub.executeQuery(subname);
		   	           	  System.out.print(rssub);
		   	           	  while(rssub.next()){
		   	           		  String total = rssub.getString("total");
		   	           		  int newTotal = Integer.parseInt(total);
		   	           		  System.out.print(newTotal);
		   	           		  if(newTotal>=1) {
		   	           			  newTotal = newTotal - 1;
		   	           		 String subupname = "update flower set total="+newTotal+" where name ='"+ flowername +"'";
		            		  subup=con.createStatement();
		            		  int m =subup.executeUpdate(subupname);
		    	              if(m != 0){ 
		    	            	  System.out.print("减少库存成功");
		    	            	  RequestDispatcher dispatcher=
		    	    	                  request.getRequestDispatcher("Show");//转发
		    	    	                  dispatcher.forward(request,response);
		    	              }
		   	           		  }else {
		   	           			  RequestDispatcher dispatcher=
	  	    	                  request.getRequestDispatcher("Show");//转发
	  	    	                  dispatcher.forward(request,response);
		   	           		  }
		   	           	  }         	  
		   	           	}
	        		 
	        		  		
		              }
		              else{
	              	rs.beforeFirst();
		              while(rs.next()){
		            	  String name = rs.getString("flower");
		            	  String flowernumber = rs.getString("number");
		            	  int number = Integer.parseInt(flowernumber);
		            	  if(name.equals(flowername)) {
		            		  number = number+1;
		            		  flowerprice = flowerprice*number;
		            		  String updatename = "update shoppingcar set number="+number+",price="+flowerprice+"where flower='"+flowername+"' and username='"+username+"'";
		            		  update=con.createStatement();
		            		  int n =update.executeUpdate(updatename);
		    	              if(n != 0){ 
		    	            	  String subname ="select * from flower where name='"+flowername +"'";
		    	            	  sub=con.createStatement();  
		    	   	           	  ResultSet rssub=sub.executeQuery(subname);
		    	   	           	  System.out.print(rssub);
		    	   	           	  while(rssub.next()){
		    	   	           		  String total = rssub.getString("total");
		    	   	           		  int newTotal = Integer.parseInt(total);
		    	   	           		  System.out.print(newTotal);
		    	   	           		  if(newTotal>=1) {
		    	   	           			  newTotal = newTotal - 1;
		    	   	           		 String subupname = "update flower set total="+newTotal+" where name ='"+ flowername +"'";
		    	            		  subup=con.createStatement();
		    	            		  int m =subup.executeUpdate(subupname);
		    	    	              if(m != 0){ 
		    	    	            	  System.out.print("减少库存成功");
		    	    	            	  RequestDispatcher dispatcher=
		    	    	    	                  request.getRequestDispatcher("Show");//转发
		    	    	    	                  dispatcher.forward(request,response);
		    	    	              }
		    	   	           		  }else {
		    	   	           		RequestDispatcher dispatcher=
	  	    	    	                  request.getRequestDispatcher("Show");//转发
	  	    	    	                  dispatcher.forward(request,response);
		    	   	           		  }
		    	   	           	  } 
		    	              }
		    	              else{
		    	  				 request.getRequestDispatcher("Show").forward(request, response);
		    	              }
		    	           
		            	  }
		              }
			          
		              }
         	  }else {
         		 request.getRequestDispatcher("Show").forward(request, response);
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
