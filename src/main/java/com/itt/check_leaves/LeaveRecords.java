package com.itt.check_leaves;


import java.util.*;

import com.itt.project.getDate;
public class LeaveRecords {
	private String employeeID;
	private String LeaveType;
	private String LeaveStatus;
	private Date fromDate;
	private Date toDate;
	private int numberOfDays;
	private String comment;
	
	
	
	// Getters
	public String getEmployeeID() {
		return employeeID;
	}
	public String getLeaveType() {
		return LeaveType;
	}
	public String getLeaveStatus() {
		return LeaveStatus;
	}
	public String getFromDate() {
		return new getDate().returnDate(fromDate);	
	}
	public String getToDate() {
		return new getDate().returnDate(toDate);	
	}
	public int getNumberOfDays() {
		return numberOfDays;
	}
	public String getComment() {
		return comment;
	}
	
	
	
	//Setters
	public void setEmployeeID(String id)
	{
		this.employeeID = id;
	}
	public void setLeaveType(String LeaveType)
	{
		this.LeaveType = LeaveType;
	}
	public void setLeaveStatus(String LeaveStatus)
	{
		this.LeaveStatus = LeaveStatus;
	}
	public void setFromDate(Date from)
	{
		this.fromDate = from;
	}
	public void setToDate(Date to)
	{
		this.toDate = to;
	}
	public void setNumberOfDays(int number)
	{
		this.numberOfDays = number;
	}
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
}
