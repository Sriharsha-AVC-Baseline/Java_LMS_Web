package com.itt.approveLeave;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itt.check_leaves.LeaveRecords;
import com.itt.project.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ApproveRejectBack")
public class ApproveRejectLeaveBackendServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		List<LeaveRecords> records;
		records = (List<LeaveRecords>) req.getAttribute("employeeList");
		String a = (String) req.getParameter("value");
		String b = (String) req.getParameter("type");
		pw.println(a);
		pw.println(b);
		records.stream().forEach(e->pw.println(e.getEmployeeID()+" "+e.getNumberOfDays()));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		PrintWriter pw = resp.getWriter();
		HttpSession session2 = req.getSession();
		String empID,leaveType,leaveStatus,fromDate,toDate;
		int numberOfLeaves;
		String decisionOnWebPage = (String) req.getParameter("Decision");
		String details = (String) req.getParameter("selectLeave");
		String decision="later";
		String[] leaveDetails = details.split("[ ]");
		empID = leaveDetails[0];
		leaveType = leaveDetails[1];
		leaveStatus = leaveDetails[2];
		fromDate = leaveDetails[3];
		toDate = leaveDetails[4];
		numberOfLeaves = Integer.parseInt(leaveDetails[5]);
		
		if(decisionOnWebPage.equalsIgnoreCase("Approve"))
		{
			if(((String)session.getAttribute("desig")).equals(com.itt.message_constants.DesignationsEnum.Lead.toString()))
			{
				decision = com.itt.message_constants.LeaveStatusEnum.APPROVED_BY_LEAD.toString();
			}
			else
			{
			decision = com.itt.message_constants.LeaveStatusEnum.APPROVED.toString();
			}
		}
		else if(decisionOnWebPage.equalsIgnoreCase("Reject"))
		{
			decision = com.itt.message_constants.LeaveStatusEnum.REJECTED.toString();
		}
		
		try {
			new DecideLeaveBackend().decideLeave(empID, leaveType, leaveStatus, fromDate, toDate, decision);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pw.println("<h2 style=\"text-align:center\">Leave "+decision+"</h2>");
		RequestDispatcher dispatch = req.getRequestDispatcher((String)session.getAttribute("desig")+".jsp");
		dispatch.forward(req, resp);
	}
}
