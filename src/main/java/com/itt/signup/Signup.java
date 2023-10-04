package com.itt.signup;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itt.project.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

enum employeeType {EXECUTIVE,LEAD,MANAGER};


@WebServlet("/Signup")
public class Signup extends HttpServlet {
	
	
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	
		String name,email,address,designation,password;
		long phoneNumber;
		int gender;
		
		PrintWriter pw = response.getWriter();
		
		
		java.util.Date dateOfBirth=null;
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		email = request.getParameter("email");
		name = request.getParameter("name");
		phoneNumber = Long.parseLong(request.getParameter("phone"));
		address = request.getParameter("address");
		password = request.getParameter("password");
		gender = Integer.parseInt(request.getParameter("gender"));
			try {
				
				dateOfBirth =  sd.parse((String)request.getParameter("date"));
				pw.println();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			designation = request.getParameter("value");	
			String result=null;
			Employee e=null;
			if(designation.equalsIgnoreCase(employeeType.MANAGER.toString()))
			{
				 e = new Manager(name,address,3,dateOfBirth,String.valueOf(phoneNumber),email,gender,password);	
			}
			else if(designation.equalsIgnoreCase(employeeType.LEAD.toString()))
			{
				e = new Lead(name,address,2,dateOfBirth,String.valueOf(phoneNumber),email,gender,null,password);	
			}
			else if(designation.equalsIgnoreCase(employeeType.EXECUTIVE.toString()))
			{
				e = new Executive(name,address,1,dateOfBirth,String.valueOf(phoneNumber),email,gender,null,null,password);	
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
				result = new SignupBackend().signup(e);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			if(result!=null)
			{
				RequestDispatcher reqDsip = request.getRequestDispatcher("/");
				reqDsip.include(request, response);
				pw.write("<h1 style=\"text-align:center;margin-top:30%;color:green\">"+result+"<h1>");
			}
			else
			{
				RequestDispatcher reqDsip = request.getRequestDispatcher("/");
				reqDsip.include(request, response);
				pw.write("<h1 style=\"text-align:center;margin-top:30%;color:green\">"+result+"<h1>");
			}
			
			
	}

}
