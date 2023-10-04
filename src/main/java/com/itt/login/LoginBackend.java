package com.itt.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itt.database.Connect;
import com.itt.message_constants.ConstantEnum;

public class LoginBackend {
	
	public String[] signIn(String name,String pass) throws ClassNotFoundException, SQLException
	{
		String employeeDetails[] = new String[2];
		Connect c= Connect.getConnection();
		Connection loginConnection =c.connection;
		PreparedStatement desigEmployee = loginConnection.prepareStatement("SELECT EMP_NAME,EMP_DESG FROM EMPLOYEE WHERE EMP_ID=? AND EMP_PASS=?");
		desigEmployee.setString(1, name);
		desigEmployee.setString(2, pass);
		ResultSet result = desigEmployee.executeQuery();
		while(result.next())
		{
			employeeDetails[0] = result.getString(1);
			employeeDetails[1] = result.getString(2);
			return employeeDetails;
		}
		employeeDetails[0] = ConstantEnum.INVALID.toString();
		
		return employeeDetails;
		
	}

}
