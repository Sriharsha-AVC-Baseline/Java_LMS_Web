package com.itt.signup;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itt.project.Employee;

public class SignupAsManager {
	
	public void signupAsManager(com.itt.project.Employee emp) throws ClassNotFoundException, SQLException
	{
		com.itt.database.Connect con = com.itt.database.Connect.getConnection();
		com.itt.project.Manager managerEmployee = (com.itt.project.Manager)emp;
		PreparedStatement insertStatement1 = con.connection.prepareStatement("INSERT INTO MANAGER VALUES(?,?)");
		insertStatement1.setString(1, managerEmployee.employeeID);
		insertStatement1.setLong(2, 0);
		insertStatement1.executeUpdate();
		System.out.println("Executed");
	}

}
