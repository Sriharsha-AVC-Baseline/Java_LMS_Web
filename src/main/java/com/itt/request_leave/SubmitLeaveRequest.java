package com.itt.request_leave;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import com.itt.database.Connect;
import com.itt.message_constants.DesignationsEnum;
import com.itt.message_constants.LeaveStatusEnum;
import com.itt.message_constants.leaveNames;
import com.itt.project.GetDateDifference;
import com.itt.project.getDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/SubmitLeaveRequest")
public class SubmitLeaveRequest extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession s = req.getSession();
		PrintWriter pw = resp.getWriter();
		String pass = (String) s.getAttribute("pass");
		String leaveStatus = LeaveStatusEnum.PENDING.toString();
		String fDate = req.getParameter("fromDate");
		String tDate = req.getParameter("toDate");
		String comment = req.getParameter("comment");
		String leaveType = req.getParameter("selectLeave");
		String designation = (String) s.getAttribute("desig");
		String splitArray[] = leaveType.split("[ ]");
		Date fromDate = null,toDate=null;
		try {
			 fromDate = new getDate().readDate(fDate);
			 toDate = new getDate().readDate(tDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int numberOfDays;
		try {
			numberOfDays = new GetDateDifference().differenceBetweenDates(fromDate, toDate);
			Connect con = Connect.getConnection();
			PreparedStatement preparedStatement = con.connection.prepareStatement("INSERT INTO leave_records VALUES(?,?,?,?,?,?,(select datediff(?,?)))");
			preparedStatement.setString(1, pass);
			preparedStatement.setString(2, splitArray[0].trim());
			preparedStatement.setDate(3, (new java.sql.Date(fromDate.getTime())));
			preparedStatement.setDate(4, (new java.sql.Date(toDate.getTime())));
			preparedStatement.setString(5, leaveStatus);
			preparedStatement.setString(6, comment);
			preparedStatement.setDate(7, (new java.sql.Date(toDate.getTime())));
			preparedStatement.setDate(8, (new java.sql.Date(fromDate.getTime())));
			preparedStatement.executeUpdate();
			pw.println("<h1 style=\"text-align:center\"> Leave applied </h1>");
			
			
			
			
			// Insert request into leads and manager 
			
			if(designation.equalsIgnoreCase(DesignationsEnum.Executive.toString()))
			{
				PreparedStatement ps = con.connection.prepareStatement("SELECT MANAGER_ID,LEAD_ID FROM EXECU WHERE EXEC_ID=?");
				ps.setString(1, pass);
				ResultSet resultOfMentors = ps.executeQuery();
				String managerId="",LeadId="";
				while(resultOfMentors.next())
				{
					managerId = resultOfMentors.getString(1);
					LeadId = resultOfMentors.getString(2);
				}
				
				
				System.out.print(managerId+" "+LeadId);
				
				
				//PreparedStatement insertIntoLeadRecord = con.connection.prepareStatement("")
				
			}
			
			
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pw.println("<h1 style=\"text-align:center\"> Oops! Something Went Wrong</h1>");
		}
		HttpSession session = req.getSession();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher((String) session.getAttribute("desig")+".jsp");
		dispatcher.include(req, resp);
	}

}
