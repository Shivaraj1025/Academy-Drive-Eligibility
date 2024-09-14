package com.tap.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCookie")
public class DeleteCookie extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		Cookie ck1 = new Cookie("browser","");
		ck1.setMaxAge(0);
		resp.addCookie(ck1);
		Cookie ck2 = new Cookie("os","");
		ck2.setMaxAge(0);
		resp.addCookie(ck2);
		
		writer.println("<h3>Cookies Deleted</h3>");
	}

}
