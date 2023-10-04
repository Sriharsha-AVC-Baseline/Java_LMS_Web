package com.itt.cancel_leaves;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itt.check_leaves.LeaveRecords;
import com.itt.project.GetLeaveRecords;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/CancelLeave")
public class CancelLeave extends HttpServlet {
	
	List<LeaveRecords> PendingLeaves = new ArrayList<>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		String designation = (String) session.getAttribute("desig");
		String empID = (String) session.getAttribute("pass");
		String name = (String) session.getAttribute("name");
		List<LeaveRecords> pendingRecords = null;
		try {
			 pendingRecords = new GetLeaveRecords().getPendingLeaves(empID);
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		List<LeaveRecords> records = (List<LeaveRecords>) session.getAttribute("leaveRecords");
		RequestDispatcher requestDispatch = req.getRequestDispatcher("CancelLeave.jsp");
		req.setAttribute("pendingLeaves", pendingRecords);
		requestDispatch.include(req, resp);
		
	}
	
}
