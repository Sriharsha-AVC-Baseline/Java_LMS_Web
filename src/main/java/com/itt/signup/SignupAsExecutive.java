package com.itt.signup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.itt.project.Executive;

public class SignupAsExecutive {
	public void signupAsExecutive(com.itt.project.Employee emp) throws ClassNotFoundException, SQLException
	{
		com.itt.database.Connect con = com.itt.database.Connect.getConnection();
		Statement st = con.connection.createStatement();
		ResultSet Leads = st.executeQuery("SELECT *FROM LEADS WHERE EXECS_UNDER_ME=(SELECT MIN(EXECS_UNDER_ME) FROM LEADS)");
		int numberOfExecsUnderThisLead = 0;
		String myLeadID=null,hisManagerID=null;
		while(Leads.next())
		{
			numberOfExecsUnderThisLead = Leads.getInt(3);
			myLeadID = Leads.getString(1);
			hisManagerID = Leads.getString(2);
		}
		
		Executive execEmployee = (Executive)emp;
		PreparedStatement insertStatement1 = con.connection.prepareStatement("INSERT INTO EXECU VALUES(?,?,?)");
		insertStatement1.setString(1, execEmployee.employeeID);
		insertStatement1.setString(2, hisManagerID);
		insertStatement1.setString(3, myLeadID);
		insertStatement1.executeUpdate();
		
		PreparedStatement updateLeadQuery = con.connection.prepareStatement("UPDATE LEADS SET EXECS_UNDER_ME=? WHERE LEAD_ID=?");
		updateLeadQuery.setInt(1, ++numberOfExecsUnderThisLead);
		updateLeadQuery.setString(2, myLeadID);
		updateLeadQuery.executeUpdate();
	}
}
