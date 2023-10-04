package com.itt.signup;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.catalina.Manager;

import com.itt.database.Connect;
import com.itt.project.*;

public class SignupBackend {
	
	public String signup(com.itt.project.Employee emp) throws  SQLException, ClassNotFoundException
	{
		Connect con = Connect.getConnection();

		try {
			
			 System.out.println("This is executing");
		PreparedStatement insertStatement = con.connection.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?,?)");
		insertStatement.setString(1, emp.employeeName);
		insertStatement.setString(2, emp.employeeID);
		insertStatement.setString(3, emp.employeePass);
		insertStatement.setString(4, emp.employeeAddress);
		insertStatement.setString(5, emp.employeePhoneNumber);
		insertStatement.setString(6, emp.employeeMail);
		insertStatement.setDate(7, new java.sql.Date(emp.employeeDateOfBirth.getTime()));
		insertStatement.setString(8, emp.gender);
		insertStatement.setString(9, emp.designation);
		insertStatement.executeUpdate();
		PreparedStatement insertLeaves = con.connection.prepareStatement("INSERT INTO LEAVES(EMP_ID) VALUES(?)");
		insertLeaves.setString(1, emp.employeeID);
		insertLeaves.executeUpdate();
		System.out.println("This is executing");
		
		
		
		if(emp instanceof com.itt.project.Manager)
		{
			System.out.print("A");
			new SignupAsManager().signupAsManager(emp);
		}
		
		else if(emp instanceof com.itt.project.Lead)
		{
			new SignupAsLead().signupAsLead(emp);
		}
		
		else if(emp instanceof com.itt.project.Executive)
		{
			new SignupAsExecutive().signupAsExecutive(emp);
		}
		
		
		return "Welcome to this Organization "+emp.employeeName+" ";
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
			return "Employee already exist";
		}
		
		
	}
	

}
