package com.itt.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itt.check_leaves.CheckLeaves;
import com.itt.check_leaves.LeaveRecords;
import com.itt.check_leaves.Leaves;
import com.itt.database.Database;
import com.itt.message_constants.ConstantEnum;
import com.itt.project.GetLeaveRecords;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ID = req.getParameter("UserID");
		String pass = req.getParameter("pwd");
		String[] details = new String[2];
		Database db = null;
		
		try {
			db = new Database();
			details = new LoginBackend().signIn(ID, pass);
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String resultMessage=null;
		PrintWriter pw = resp.getWriter();
		RequestDispatcher reqDis;
		if(details[0].equalsIgnoreCase(ConstantEnum.INVALID.toString()))
		{
			resultMessage = "<h4 style=\"color:red\">Invalid Username or password!!!<h4>";
			
			reqDis = req.getRequestDispatcher("/");
			resp.setContentType("text/html");
		}
		else
		{
			HttpSession session = req.getSession();
			reqDis = req.getRequestDispatcher("/"+details[1]+".jsp");
			session.setAttribute("name", details[0]);
			session.setAttribute("desig", details[1]);
			session.setAttribute("pass", ID);
			List<LeaveRecords> leaveRecords=null;
			try {
				 leaveRecords = new GetLeaveRecords().getLeaveRecords(ID);
			}
			catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Leaves leave = null;
			CheckLeaves checkleave = new CheckLeaves();
			try {
				 
				leave = checkleave.provideLeaves(ID);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			 session.setAttribute("leaveRecords", leaveRecords);
			 session.setAttribute("leave", leave);
			// leaveRecords.stream().forEach(e->System.out.println(e.getEmployeeID()+" "+e.getLeaveType()+" "+e.getLeaveStatus()+" "+e.getNumberOfDays()));
				
		}
		reqDis.include(req, resp);
		
		
	//	pw.write(resultMessage);
	}

}
