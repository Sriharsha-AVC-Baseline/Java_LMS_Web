package com.itt.cancel_leaves;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import com.itt.database.Connect;
import com.itt.message_constants.LeaveStatusEnum;

public class DBOperationForCancelLeave {
	
	
	public void SubmitCancelledLeave(String empID, Date from, Date to,String leaveType) throws ClassNotFoundException, SQLException {
		
		Connect c = Connect.getConnection();
		
		PreparedStatement cancelQuery = c.connection.prepareStatement("update leave_records set status=? where emp_id=? and Leave_type=? and from_date=? and to_date=? and status=?");
		cancelQuery.setString(1, LeaveStatusEnum.CANCELLED.toString());
		cancelQuery.setString(2, empID);
		cancelQuery.setString(3,leaveType);
		cancelQuery.setDate(4, new java.sql.Date(from.getTime()));
		cancelQuery.setDate(5, new java.sql.Date(to.getTime()));
		cancelQuery.setString(6, LeaveStatusEnum.PENDING.toString());
		cancelQuery.executeUpdate();
}
}