package com.itt.approveLeave;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.itt.database.Connect;

public class DecideLeaveBackend {
	
	
	public void decideLeave(String empId,String leaveType,String leaveStatus,String fromDate,String toDate,String decision) throws ClassNotFoundException, SQLException
	{
		if(decision.equalsIgnoreCase("later"))
		{
			return;
		}
		
		
			String state = "update leave_records set status=? where EMP_ID=? and leave_type=? and FROM_DATE=? and to_date=? and status=?;";
			Connect connect = Connect.getConnection();
			PreparedStatement approveStatement = connect.connection.prepareStatement(state);
			approveStatement.setString(1, decision);
			approveStatement.setString(2, empId);
			approveStatement.setString(3, leaveType);
			approveStatement.setString(4, fromDate);
			approveStatement.setString(5, toDate);
			approveStatement.setString(6, com.itt.message_constants.LeaveStatusEnum.PENDING.toString());
			approveStatement.executeUpdate();
			
			if(decision.equals(com.itt.message_constants.LeaveStatusEnum.APPROVED.toString()) || decision.equals(com.itt.message_constants.LeaveStatusEnum.APPROVED_BY_LEAD.toString()))
			{
				deductLeave(empId,leaveType,fromDate,toDate);
		}
	}
	
	public void deductLeave(String empId,String leaveType,String fromDate,String toDate) throws ClassNotFoundException, SQLException
	{
		String deductQuery = "update leaves set "+ leaveType +" = "+ leaveType +" - (SELECT datediff(?,?)) where emp_id=?;";
		PreparedStatement deductStatement = Connect.getConnection().connection.prepareStatement(deductQuery);
		
		deductStatement.setString(1, toDate);
		deductStatement.setString(2, fromDate);
		deductStatement.setString(3, empId);
		deductStatement.executeUpdate();
	}

}
