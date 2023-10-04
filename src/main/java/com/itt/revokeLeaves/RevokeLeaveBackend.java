package com.itt.revokeLeaves;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.itt.approveLeave.DecideLeaveBackend;
import com.itt.check_leaves.LeaveRecords;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
@WebServlet("/RevokeBack")
public class RevokeLeaveBackend extends HttpServlet {
	
	
		
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
			String empID,leaveType,leaveStatus,fromDate,toDate;
			int numberOfLeaves;
			String a = (String) req.getParameter("Decision");
			String details = (String) req.getParameter("selectLeave");
			String decision="later";
			String[] leaveDetails = details.split("[ ]");
			empID = leaveDetails[0];
			leaveType = leaveDetails[1];
			leaveStatus = leaveDetails[2];
			fromDate = leaveDetails[3];
			toDate = leaveDetails[4];
			numberOfLeaves = Integer.parseInt(leaveDetails[5]);
			
			if(a.equalsIgnoreCase("Revoke"))
			{
				decision = com.itt.message_constants.LeaveStatusEnum.REVOKED.toString();
			}
			
			
			try {
				
				new DecideRevokeBackend().decideLeave(empID, leaveType, leaveStatus, fromDate, toDate, decision);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw.println("<h2 style=\"text-align:center\">Leave "+decision+"</h2>");
			RequestDispatcher dispatch = req.getRequestDispatcher((String)session.getAttribute("desig")+".jsp");
			dispatch.forward(req, resp);
		}
	
}
