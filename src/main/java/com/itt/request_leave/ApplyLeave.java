package com.itt.request_leave;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import com.itt.database.Database;
import com.mysql.cj.Session;
import com.mysql.cj.xdevapi.DbDoc;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.itt.message_constants.*;
import com.itt.project.getNumberOfLeavesOfSpecifiedLeaveType;




@WebServlet("/ApplyLeave")
public class ApplyLeave extends HttpServlet {
	

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		List<String> leaveList = new ArrayList<String>();
		List<Integer> remainingLeaves;
		String name = (String) req.getSession().getAttribute("name");
		String pass = (String) req.getSession().getAttribute("pass");
		Database db = null;
		leaveNames[] leaves = leaveNames.values();
		int numberOfLeaves=0;
		List<String> leaveNamesAsPerDatabase = new ArrayList<>();
		System.out.println(name);
		try {
			remainingLeaves = new getNumberOfLeavesOfSpecifiedLeaveType().getNumberOfLeavesOfSpecificLeaveType(pass);
			
			for(int i=0;i<leaves.length;i++)
			{
				leaveNamesAsPerDatabase.add(leaves[i].toString());
				 numberOfLeaves = remainingLeaves.get(i);
					if(leaves[i].equals(leaveNames.DUTY_LEAVE))
							{
						leaveList.add(leaves[i].toString());
						continue;
							}
					if(numberOfLeaves > 1)
					{
					leaveList.add(leaves[i].toString() +"\t "+String.valueOf(numberOfLeaves)+" days left");
					}
					else if(numberOfLeaves == 1)
					{
						leaveList.add(leaves[i].toString() +"\t "+String.valueOf(numberOfLeaves)+" day left");
					}
					else if(numberOfLeaves == 0)
					{
						leaveList.add(leaves[i].toString() +"\t "+String.valueOf(numberOfLeaves)+" exhausted");
					}
					}
		}
		 catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		req.setAttribute("leaveList", leaveList);
		req.setAttribute("leaveNamesAsPerDatabase", leaveNamesAsPerDatabase);
		req.setAttribute("numberOfLeaves", numberOfLeaves);
		RequestDispatcher requestDispatch = req.getRequestDispatcher("ApplyLeave.jsp");	
		requestDispatch.include(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
