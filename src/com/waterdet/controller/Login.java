package com.waterdet.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.waterdet.dao.*;
/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String userID=request.getParameter("login-email");
		String password=request.getParameter("login-password");
		if(userID==null||password==null){
			response.sendRedirect("page_ready_login.html");
			return;
		}
		UserDao userinfo=new UserDao(userID);
		String pass=userinfo.getUserPassword();
		if(pass==null){
			response.sendRedirect("page_ready_login.html");
			return;
		}
		/**********用户名密码正确**********************/
		if(pass.equals(password)){
			response.sendRedirect("index.html");
		}
		/*************************************/
		else{
			response.sendRedirect("page_ready_login.html");
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
