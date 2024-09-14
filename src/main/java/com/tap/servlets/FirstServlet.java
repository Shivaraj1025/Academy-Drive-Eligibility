package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
//@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		System.out.println("Init() method called");
	}
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter writer = res.getWriter();
		
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String[] techskills = req.getParameterValues("techskills");
		
//		writer.print("<h3>Well Come "+ name  +"</h3>");
		System.out.println("name = " + name);
		System.out.println("password = " + password);
		for(String i : techskills) {
			System.out.println("Tech SKills = " + i);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/staticresp.html");
		rd.forward(req, res);
	}
	

}
