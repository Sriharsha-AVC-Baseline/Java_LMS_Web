package com.itt.project;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.itt.check_leaves.LeaveRecords;
import com.itt.database.Connect;
import com.itt.message_constants.LeaveStatusEnum;

public class GetLeaveRecords {
	
	List<LeaveRecords> leaveRecordOfEmployee = new ArrayList<>();
	
	public List<LeaveRecords> getLeaveRecords(String empId) throws ClassNotFoundException, SQLException
	{
		Connect c = Connect.getConnection();
		PreparedStatement getRecordsStatement = c.connection.prepareStatement("SELECT *FROM LEAVE_RECORDS WHERE EMP_ID=?");
		getRecordsStatement.setString(1, empId);
		ResultSet results = getRecordsStatement.executeQuery();
		LeaveRecords temporary = new LeaveRecords();
		while(results.next())
		{
			temporary.setEmployeeID(results.getString(1));
			temporary.setLeaveType(results.getString(2));
			temporary.setFromDate(new java.util.Date(results.getDate(3).getTime()));
			temporary.setToDate(new java.util.Date(results.getDate(4).getTime()));
			temporary.setLeaveStatus(results.getString(5));
			temporary.setComment(results.getString(6));
			temporary.setNumberOfDays(results.getInt(7));
			leaveRecordOfEmployee.add(temporary);
			temporary = new LeaveRecords();
		}
		
		leaveRecordOfEmployee.stream().forEach(e->System.out.println(e.getEmployeeID()+" "+e.getLeaveType()+" "+e.getLeaveStatus()+" "+e.getNumberOfDays()));
		return leaveRecordOfEmployee;
	}
	
	public List<LeaveRecords> getPendingLeaves(String empID) throws ClassNotFoundException, SQLException
	{
		Connect c = Connect.getConnection();
		PreparedStatement state = c.connection.prepareStatement("SELECT *FROM LEAVE_RECORDS WHERE STATUS=? AND EMP_ID=?");
		state.setString(1, LeaveStatusEnum.PENDING.toString());
		state.setString(2, empID);
		ResultSet results = state.executeQuery();
		LeaveRecords temporary = new LeaveRecords();
		while(results.next())
		{
			temporary.setEmployeeID(results.getString(1));
			temporary.setLeaveType(results.getString(2));
			temporary.setFromDate(new java.util.Date(results.getDate(3).getTime()));
			temporary.setToDate(new java.util.Date(results.getDate(4).getTime()));
			temporary.setLeaveStatus(results.getString(5));
			temporary.setComment(results.getString(6));
			temporary.setNumberOfDays(results.getInt(7));
			leaveRecordOfEmployee.add(temporary);
			temporary = new LeaveRecords();
		}
		
		return leaveRecordOfEmployee;
		
	}

}
