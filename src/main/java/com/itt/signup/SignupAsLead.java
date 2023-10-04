package com.itt.signup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import com.itt.database.Connect;
import com.itt.project.Lead;

public class SignupAsLead {
	public void signupAsLead(com.itt.project.Employee emp) throws ClassNotFoundException, SQLException
	{
		com.itt.database.Connect con = Connect.getConnection();
		Map<String, Integer> managersAvailable = new LinkedHashMap<String, Integer>();
		Statement st = con.connection.createStatement();
		String myManager=null;
		int numberOfLeadsUnderHim = 0;
		ResultSet managers = st.executeQuery("SELECT * FROM MANAGER WHERE LEADS_UNDER_ME=(SELECT MIN(LEADS_UNDER_ME) FROM MANAGER)");
		int managerCount = 0;
		String managerID=null;
		while(managers.next())
		{
			myManager = managers.getString(1);
			numberOfLeadsUnderHim = managers.getInt(2);
			break;
		}
		
		/*
		 * int selectedManager = (int) (Math.random()%managerCount); managers =
		 * st.executeQuery("SELECT *FROM MANAGER"); int iterator=0;
		 * while(managers.next()) { if(iterator==selectedManager) { managerID =
		 * (String)managers.getString(1); } iterator++; }
		 */
		
		Lead leadEmployee = (Lead)emp;
		PreparedStatement insertStatement1 = con.connection.prepareStatement("INSERT INTO LEADS VALUES(?,?,?)");
		insertStatement1.setString(1, leadEmployee.employeeID);
		insertStatement1.setString(2, myManager);
		insertStatement1.setLong(3, 0);
		insertStatement1.executeUpdate();
		
		PreparedStatement updateManagerQuery = con.connection.prepareStatement("UPDATE MANAGER SET LEADS_UNDER_ME=? WHERE MANAGER_ID=?");
		updateManagerQuery.setInt(1, ++numberOfLeadsUnderHim);
		updateManagerQuery.setString(2, myManager);
		updateManagerQuery.executeUpdate();
	}
}
