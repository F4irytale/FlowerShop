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

import com.bean.User;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (Exception e) {}
	}
	
	public String handleString(String s){
		try {
			byte bb[]=s.getBytes("ISO-8859-1");
			s=new String(bb);
		} catch (Exception e) {}
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
		User reguser = new User();
		HttpSession session=request.getSession(true);
		String username=request.getParameter("username").trim();
		String password=request.getParameter("password").trim();
		String telephone = request.getParameter("telephone").trim();
		String address = request.getParameter("address").trim();
		
		 username=handleString(username);
	     password=handleString(password);
	     telephone=handleString(telephone);
	     address=handleString(address);
	     
		 Connection con; 
	     Statement sql; 
	     String uri = "jdbc:mysql://127.0.0.1:3307/flowershop?"+"user=root&password=123456&characterEncoding=utf-8";
	     boolean boo=(username.length()>0)&&(password.length()>0)&&(telephone.length()>0)&&(address.length()>0);  
	     System.out.print(boo);
	      try{ 
	           con=DriverManager.getConnection(uri);
	           String sqlname="insert into user(username,password,telephone,address) values('"+username+"','"+password+"','"+telephone+"','"+address+"')";
	           sql=con.createStatement();  
	           if(boo){
	              int n =sql.executeUpdate(sqlname);
	              System.out.print(n);
	              if(n != 0){ 
	            	  System.out.print("插入成功");
	                 //注册成功后执行
	                  //success(request,response,username,password); 
	                  RequestDispatcher dispatcher=
	                  request.getRequestDispatcher("login.jsp");//转发
	                  dispatcher.forward(request,response);
	              }
	              else{
	            	  String backNews="注册失败";
	            	  reguser.setBacknews(backNews);
	            	  session.setAttribute("regUser", reguser);
	  				 request.getRequestDispatcher("register.jsp").forward(request, response);
	              }
	           }
	           else{
	        	   	  String backNews="必填项不为空";
	        	   	reguser.setBacknews(backNews);
	        	   	session.setAttribute("regUser", reguser);
	  				request.getRequestDispatcher("register.jsp").forward(request, response);
	           }
	           
	           con.close();
	      }
	      catch(SQLException exp){
	          String backNews=""+exp;
	          //fail(request,response,username,backNews);
	      }
	}

}
