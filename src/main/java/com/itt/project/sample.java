package com.itt.project;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/Submit")
public class sample extends HttpServlet {

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String des = (String)req.getParameter("value");
		String name = (String)req.getParameter("name");
		long phone = (long)Long.parseLong((String)req.getParameter("phone"));
		System.out.println(name+" "+phone+" "+des);
		req.setAttribute("name", name);
		req.setAttribute("phone", phone);
		
		PrintWriter pwrite = resp.getWriter();
		
		
		RequestDispatcher reqDis = req.getRequestDispatcher("/signup.jsp");
		resp.setContentType("text/html");
		pwrite.println("Hello people");
		reqDis.include(req, resp);
	}
	
	
}
