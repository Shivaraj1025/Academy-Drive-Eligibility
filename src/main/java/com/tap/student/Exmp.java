package com.tap.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Exmp extends HttpServlet {
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
//			login
			String username = req.getParameter("username");	
			String password = req.getParameter("password");
			
			try {
				String query = "select * from tapstudent where un = ? and pwd = ?";
				pstmt = con.prepareStatement(query);
				pstmt.setString(1, username);
				pstmt.setString(2, password);
				res = pstmt.executeQuery();
				
				if(res.next() == true) {
					writer.println("<h3>Welcome "+ res.getString(2) +"!</h3>");
//					drive
					String query2 = "select * from drives";
					Statement stmt = con.createStatement();
					ResultSet res2 = stmt.executeQuery(query2);
					
					writer.println("<table border = 1>\r\n"
							+ "	<tr>\r\n"
							+ "	<th>id</th>\r\n"
							+ "	<th>Name</th>\r\n"
							+ "	<th>10th</th>\r\n"
							+ "	<th>12th</th>\r\n"
							+ "	<th>Grad</th>\r\n"
							+ "	<th>Profile</th>\r\n"
							+ "	<th>Package</th>\r\n"
							+ "	<th>Skills</th>\r\n"
							+ "	</tr>");
					
					while(res2.next() == true) {
						int id = res2.getInt(1);
						String name = res2.getString(2);
						int ten = res2.getInt(3);
						int twe = res2.getInt(4);
						int grad = res2.getInt(5);
						String profile = res2.getString(6);
						float pac = res2.getFloat(7);
						String skills = res2.getString(8);
						
						writer.println("<tr>\r\n"
								+ "		<td>"+id+"</td>\r\n"
								+ "		<td>"+name+"</td>\r\n"
								+ "		<td>"+ten+"</td>\r\n"
								+ "		<td>"+twe+"</td>\r\n"
								+ "		<td>"+grad+"</td>\r\n"
								+ "		<td>"+profile+"</td>\r\n"
								+ "		<td>"+pac+"</td>\r\n"
								+ "		<td>"+skills+"</td>\r\n"
								+ "	</tr>");
						
					}
					writer.println("</table>");
				
				}else {
					RequestDispatcher rd = req.getRequestDispatcher("/invalidlogin.html");
					rd.forward(req, resp);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			}
		
		@Override
			public void destroy() {
				try {
					res.close();
					pstmt.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	

}
