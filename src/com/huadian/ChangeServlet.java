package com.huadian;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChangeServlet
 */

public class ChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeServlet() {
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
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session=request.getSession(true);
		String username = (String) session.getAttribute("username");
		String password = request.getParameter("password");
		String telephone = request.getParameter("telephone");
		String address = request.getParameter("address");
		
		 Connection con; 
	     Statement sql; 
	     String uri = "jdbc:mysql://127.0.0.1:3307/flowershop?"+"user=root&password=123456&characterEncoding=utf-8";
	      try{ 
	           con=DriverManager.getConnection(uri);
	           String sqlname="update user set password='"+password+"',telephone='"+telephone+"',address='"+address+"' where username='"+username+"'";
	           sql=con.createStatement();  
	           
	              int n =sql.executeUpdate(sqlname);
	              System.out.print(n);
	              if(n != 0){ 
	            	  System.out.print("插入成功");
	             
	                  RequestDispatcher dispatcher=
	                  request.getRequestDispatcher("Admin");//转发
	                  dispatcher.forward(request,response);
	              }
	              else{
	            	  String backNews="插入失败";
	            	 
	  				 request.getRequestDispatcher("admin.jsp").forward(request, response);
	              }
	           con.close();
	      }catch(Exception e) {
	    	  e.printStackTrace();
	      }
	      
	      
	}

}
