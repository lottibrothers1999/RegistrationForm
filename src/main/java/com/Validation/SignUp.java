package com.Validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SignUp extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		String na=req.getParameter("na");
		String mb=req.getParameter("mb");
		String user=req.getParameter("us");
		String pass=req.getParameter("ps");
		String email=req.getParameter("em");
		 Connection cn=null;
		 PreparedStatement pst=null;
		 
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=abcd&useSSL=true");
			pst=cn.prepareStatement("insert into login.userinfo values(?,?,?,?)");
			pst.setString(1, na);
			pst.setString(2, mb);
			pst.setString(3, user);
			pst.setString(4, email);
			pst.execute();
			pst=cn.prepareStatement("insert into login.user values(?,?)");
			pst.setString(1, user);
			pst.setString(2, pass);
			pst.execute();
			PrintWriter p=resp.getWriter();
			p.println("<html><body><h1>Successfully Registered</h1></body></html>");
			req.getRequestDispatcher("index.html").include(req, resp);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}