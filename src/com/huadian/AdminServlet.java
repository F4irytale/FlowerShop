package com.huadian;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.Search;
import com.sun.rowset.CachedRowSetImpl;

/**
 * Servlet implementation class AdminServlet
 */

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
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
		
		 Connection con; 
	     Statement sql; 
	     String uri = "jdbc:mysql://127.0.0.1:3307/flowershop?"+"user=root&password=123456&characterEncoding=utf-8";
	      try{ 
	           con=DriverManager.getConnection(uri);
	           String sqlname="select * from user where username = '"+username+"'";
	           sql=con.createStatement();  
	           
	              ResultSet rs=sql.executeQuery(sqlname);
	              System.out.print(rs);
	            	  Search searchuser = new Search();
	            	  CachedRowSetImpl rowSet=new CachedRowSetImpl();   
	                  rowSet.populate(rs);
	                  searchuser.setRowSet(rowSet);
	                  session.setAttribute("searchuser", searchuser);
	                  RequestDispatcher dispatcher=
	                  request.getRequestDispatcher("admin.jsp");//×ª·¢
	                  dispatcher.forward(request,response);
	           con.close();
	      }
	      catch(SQLException exp){
	          String backNews=""+exp;
	          //fail(request,response,username,backNews);
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
