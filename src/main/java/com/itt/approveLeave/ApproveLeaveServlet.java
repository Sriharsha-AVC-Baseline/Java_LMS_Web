package com.itt.approveLeave;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itt.check_leaves.LeaveRecords;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/approveRejectLeave")
public class ApproveLeaveServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<LeaveRecords> execRecords= new ArrayList<LeaveRecords>();
		List<LeaveRecords> leadRecords = new ArrayList<LeaveRecords>();
		List<LeaveRecords> employeeRecords = new ArrayList<LeaveRecords>();
		HttpSession session = req.getSession();
		String empId = (String)session.getAttribute("pass");
		String designation = (String)session.getAttribute("desig");
		try {
			
			if(designation.equalsIgnoreCase(com.itt.message_constants.DesignationsEnum.Lead.toString()))
			{
			 execRecords = new GetLeaveRequestsFromDatabase().getLeaveRecordsOfExecutives(empId,false);
			 employeeRecords.addAll(execRecords);
			}
			 
			else if(designation.equalsIgnoreCase(com.itt.message_constants.DesignationsEnum.Manager.toString()))
			 {
				 leadRecords = new GetLeaveRequestsFromDatabase().getLeaveRecordsOfLeads(empId);
				 execRecords = new GetLeaveRequestsFromDatabase().getLeaveRecordsOfExecutives(empId,true);
				 employeeRecords.addAll(execRecords);
				 employeeRecords.addAll(leadRecords);
			 }
			 
			 
			
			 employeeRecords.stream().forEach(e->System.out.println(e.getEmployeeID()+" "+e.getLeaveType()+" "+e.getNumberOfDays()));
			 
			 	req.setAttribute("employeeList", employeeRecords);
			 	RequestDispatcher dispatch = req.getRequestDispatcher("ApproveLeave.jsp");
				dispatch.include(req, resp);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
