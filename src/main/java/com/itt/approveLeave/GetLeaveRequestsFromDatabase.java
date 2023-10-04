package com.itt.approveLeave;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.itt.check_leaves.LeaveRecords;
import com.itt.database.Connect;
import com.itt.message_constants.LeaveStatusEnum;

public class GetLeaveRequestsFromDatabase {
	
	List<com.itt.check_leaves.LeaveRecords> executiveLeaveRecords = new ArrayList<>();
	List<com.itt.check_leaves.LeaveRecords> leadLeaveRecords = new ArrayList<>();
	
	public List<com.itt.check_leaves.LeaveRecords> getLeaveRecordsOfExecutives(String pass,boolean iAmTheManager) throws SQLException, ClassNotFoundException
	{
		String statement;
		if(iAmTheManager==true)
		{
		 statement = "select *from leave_records where emp_id=(select ex.exec_id from execu ex,manager m where  m.manager_id=ex.manager_id and m.manager_id=?) and status=?";
		}
		else
		{
			 statement = "select *from leave_records where emp_id=(select ex.exec_id from execu ex,leads l where l.lead_id=ex.lead_id and l.lead_id=?) and status=?";
		}
		
		
		Connect connect = Connect.getConnection();
		
		PreparedStatement execStatement = connect.connection.prepareStatement(statement);
		execStatement.setString(1, pass);
		execStatement.setString(2, LeaveStatusEnum.PENDING.toString());
		ResultSet empRequests = execStatement.executeQuery();
		executiveLeaveRecords = getLeaveRecordsOfEmployees(empRequests);
	//	EMP_ID             | LEAVE_TYPE   | FROM_DATE  | TO_DATE    | STATUS  | COMMENT | NUMBER_OF_DAYS
		System.out.println("Entered into exec");
		executiveLeaveRecords.stream().forEach(e->System.out.println(e.getNumberOfDays()+" "+e.getEmployeeID()));
		return executiveLeaveRecords;
		
	}
	
	
	public List<com.itt.check_leaves.LeaveRecords> getLeaveRecordsOfLeads(String pass) throws SQLException, ClassNotFoundException
	{
		
		String statement = "select *from leave_records where emp_id=(select lds.lead_id from leads lds,manager m where m.manager_id=lds.manager_id and m.manager_id=?) and status=?";
		Connect connect = Connect.getConnection();
		PreparedStatement leadStatement = connect.connection.prepareStatement(statement);
		leadStatement.setString(1, pass);
		leadStatement.setString(2, LeaveStatusEnum.PENDING.toString());
		ResultSet empRequests = leadStatement.executeQuery();
		leadLeaveRecords = getLeaveRecordsOfEmployees(empRequests);
		System.out.println("Entered into Leads");
		leadLeaveRecords.stream().forEach(e->System.out.println(e.getNumberOfDays()+" "+e.getEmployeeID()));
	//	EMP_ID             | LEAVE_TYPE   | FROM_DATE  | TO_DATE    | STATUS  | COMMENT | NUMBER_OF_DAYS
		return leadLeaveRecords;
		
	}
	
	
	public List<com.itt.check_leaves.LeaveRecords> getLeaveRecordsOfEmployees(ResultSet empRequests) throws SQLException
	{
		List<com.itt.check_leaves.LeaveRecords> employeeRecords = new ArrayList<>();
		while(empRequests.next())
		{
			LeaveRecords empRecords = new LeaveRecords();
			empRecords.setEmployeeID(empRequests.getString(1));
			empRecords.setLeaveType(empRequests.getString(2));
			empRecords.setFromDate(new java.util.Date(empRequests.getDate(3).getTime()));
			empRecords.setToDate(new java.util.Date(empRequests.getDate(4).getTime()));
			empRecords.setLeaveStatus(empRequests.getString(5));
			empRecords.setComment(empRequests.getString(6));
			empRecords.setNumberOfDays(empRequests.getInt(7));
			employeeRecords.add(empRecords);
		}
		employeeRecords.stream().forEach(e->System.out.println(e.getLeaveStatus()+" "+e.getLeaveType()));
		return employeeRecords;
	}
	

}
