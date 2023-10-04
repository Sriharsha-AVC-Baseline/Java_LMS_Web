package com.itt.cancel_leaves;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;

import com.itt.project.getDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/SubmitCancelRequest")
public class SubmitCancelLeave extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter pw = resp.getWriter();
		String selectedLeave = (String)req.getParameter("selectLeave");
		String leaveDetails[] = selectedLeave.split("[ ]");
		HttpSession session = req.getSession();
		String pass = (String)session.getAttribute("pass");
		String leaveType = leaveDetails[0];
		Date from=null,to=null;
		try {
			 from = new getDate().readDate(leaveDetails[1]);
			 to = new getDate().readDate(leaveDetails[2]);
			 new DBOperationForCancelLeave().SubmitCancelledLeave(pass, from, to, leaveType);
			 pw.println("<h3 style=\"text-align:center\"> Leave Canceled</h3>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 pw.println("<h3 style=\"text-align:center\">Oops something went wrong!</h3>");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher((String) session.getAttribute("desig")+".jsp");
		dispatcher.forward(req, resp);
		
}
}
