package com.tap.student;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Drive extends HttpServlet {
	ResultSet res = null;
	Connection con;
	PreparedStatement pstmt = null;
	private final String  url = "jdbc:mysql://localhost:3306/tap_bank";
	private final String nm = "root";
	private final String pwd = "Shiva#1025";
	
	
	
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
			try {
				String query2 = "select * from drives";
				Statement stmt = con.createStatement();
				ResultSet res2 = stmt.executeQuery(query2);
				resp.setContentType("text/html");
				PrintWriter writer = resp.getWriter();
				
				writer.println("<h3>Drives conducted at Tap Academy</h3>");
				
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
				req.getRequestDispatcher("/eligible").include(req, resp);
			} catch (Exception e) {
			}
		}
}
