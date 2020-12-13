package com.huadian;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bean.*;
import com.mysql.*;

/**
 * Servlet implementation class Login
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String username=request.getParameter("username").trim(),
		password=request.getParameter("password").trim();
		HttpSession session=request.getSession(true);
		username=handleString(username);
	     password=handleString(password);
		 Connection con; 
	     Statement sql; 
	     String uri = "jdbc:mysql://127.0.0.1:3307/flowershop?"+"user=root&password=123456&characterEncoding=utf-8";
	     boolean boo=(username.length()>0)&&(password.length()>0);  
	      try{ 
	           con=DriverManager.getConnection(uri);
	           String sqlname="select * from user where username = '"+username+"' and password ='"+password+"'";
	           sql=con.createStatement();  
	           if(boo){
	              ResultSet rs=sql.executeQuery(sqlname);
	              boolean have=rs.next();
	              if(have==true){ 
	            	  session.setAttribute("username", username);
	                 //登录成功后执行
	                  //success(request,response,username,password); 
	                  RequestDispatcher dispatcher=
	                  request.getRequestDispatcher("frame.jsp");//转发
	                  dispatcher.forward(request,response);
	              }
	              else{
	                 
	                  
	                  response.getWriter().write("未找到");
	              }
	           }
	           else{
	        	     response.getWriter().write("不为空");
	                
	           }
	           con.close();
	      }
	      catch(SQLException exp){
	          String backNews=""+exp;
	          //fail(request,response,username,backNews);
	      }
	}


}
