package com.Validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.*;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class login extends GenericServlet {
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
	  String user= req.getParameter("us");
	  String pass= req.getParameter("ps");
	    Connection cn=null;
	    PreparedStatement pst=null;
	    ResultSet rs=null;
	    
	    PrintWriter p=resp.getWriter();
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		cn=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=abcd&useSSL=true");
		pst=cn.prepareStatement("select * from login.user where username=?");
		pst.setString(1, user);
		rs=pst.executeQuery();
		if(rs.next()) {
			String dpass=rs.getString("password");
			if(pass.equals(dpass)) {
				RequestDispatcher rd=req.getRequestDispatcher("Profile.html");
				rd.forward(req, resp);
			}
			else {
				RequestDispatcher rd=req.getRequestDispatcher("index.html");
				rd.include(req, resp);
				p.println("<head><style>h3{color:red;}</style></head><body><h3>check password</h3></body>");
				//p.println("<body><script>document.getElementById.('passError').innerHTML='Check Password';"
				//+"document.getElementById.('passError').style.color='red';</script></body>");
				
			}
		}
		else {
			
			RequestDispatcher rd=req.getRequestDispatcher("index.html");
			rd.include(req, resp);
			p.println("<head><style>h3{color:red;}</style></head><body><h3>Check Username</h3></body>"
			+" If Your not Register Please Register"
			+"<body><a href='Signup.html'>SignUp</a></body>");
			//p.println("<body><script>document.getElementById.('userError').innerHTML='Check Username';"
			//+"document.getElementById.('userError').style.color='red';</script></body>");
			
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
    
	}
		
	}


