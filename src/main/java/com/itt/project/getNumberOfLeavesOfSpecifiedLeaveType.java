package com.itt.project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.itt.database.Connect;

public class getNumberOfLeavesOfSpecifiedLeaveType {
	public List<Integer> getNumberOfLeavesOfSpecificLeaveType(String employeeID) throws Exception
	{
		Connect c = Connect.getConnection();
		List<Integer> remainingLeaves = new ArrayList<>();
		
		String statement = "SELECT * FROM LEAVES WHERE EMP_ID=?";
		PreparedStatement getNumberOfLeaves = c.connection.prepareStatement(statement);
		
		getNumberOfLeaves.setString(1, employeeID);
		ResultSet result = getNumberOfLeaves.executeQuery();
		while(result.next())
		{
			String a = result.getString(1);
			for(int i=2;i<=8;i++)
			{
				remainingLeaves.add(result.getInt(i));
			}
		}
		return remainingLeaves;
		
	}
}
