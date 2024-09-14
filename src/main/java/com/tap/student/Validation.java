package com.tap.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Validation extends HttpServlet {
	ResultSet res = null;
	Connection con;
	PreparedStatement pstmt = null;
	String url = "jdbc:mysql://localhost:3306/tap_bank";
	String nm = "root";
	String pwd = "Shiva#1025";
	
	
	
		public void init() throws ServletException {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,  nm, pwd);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");
			PrintWriter writer = resp.getWriter();
			
			String username = req.getParameter("username");	
			String password = req.getParameter("password");
			
			try {
				
				String query = "select * from tapstudent where un = ? and pwd = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				res = pstmt.executeQuery();
				
				if(res.next() == true) {
					writer.println("<h2>Well Come to Tap Academy</h2>");
					req.getRequestDispatcher("/drive").include(req, resp);
				}else {
					RequestDispatcher rd = req.getRequestDispatcher("/invalidlogin.html");
					rd.forward(req, resp);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
