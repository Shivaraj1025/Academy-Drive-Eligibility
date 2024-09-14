package com.tap.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		resp.setContentType("text/html");
		PrintWriter writer = resp.getWriter();
		
		if(cookies != null) {
			for(int i=0;i<cookies.length;i++) {
				String name = cookies[i].getName();
				String value = cookies[i].getValue();
				
				writer.println("<h3>Name: "+name+"</h3>");
				writer.println("<h3>Value: "+value+"</h3>");
			}
		}else {
			writer.println("<h3>There are no cookies available");
		}
	}
}
