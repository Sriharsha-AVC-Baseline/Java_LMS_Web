package com.itt.check_leaves;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.itt.database.Connect;


public class CheckLeaves {
	public Leaves provideLeaves(String empID) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connect c = Connect.getConnection();
		Connection checkingLeavesConnection = c.connection;
		PreparedStatement seeLeavesDetails = checkingLeavesConnection.prepareStatement("SELECT *FROM LEAVES WHERE EMP_ID=?");
		seeLeavesDetails.setString(1, empID);
		ResultSet getLeaveDetails = seeLeavesDetails.executeQuery();
		Leaves leaves = new Leaves();
		while(getLeaveDetails.next())
		{
		String empId = getLeaveDetails.getString(1);
		leaves.casualLeave= getLeaveDetails.getInt(2);
		leaves.earnedLeave= getLeaveDetails.getInt(3);
		leaves.dutyLeave= getLeaveDetails.getString(4);
		leaves.sickLeave = getLeaveDetails.getInt(5);
		leaves.maternityLeaves= getLeaveDetails.getInt(6);
		leaves.parentalLeaves= getLeaveDetails.getInt(7);
		leaves.leaveWithoutPay = getLeaveDetails.getInt(8);
		}
		System.out.println(leaves.casualLeave+" Value "+leaves.dutyLeave);
		return leaves;
	}

}
