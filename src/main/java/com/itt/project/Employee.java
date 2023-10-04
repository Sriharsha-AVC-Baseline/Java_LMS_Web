package com.itt.project;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.itt.message_constants.DesignationsEnum;

import java.util.*;
import java.io.*;

import com.itt.message_constants.*;



public abstract class Employee extends Exception implements Serializable {
	public String employeeName;
	public String employeeID;
	public String employeePass;
	public String employeeMail;
	public Date employeeDateOfBirth;
	public Date leaveFrom, leaveTo;
	public String employeeAddress;
	public String designation ;
	enum genderOptions{MALE,FEMALE,OTHERS};
	public String gender;
	public String employeePhoneNumber;

	Map<String, Integer> leave_arr = new LinkedHashMap<>();

	Employee(String name, String add, int desig, Date d, String phno,String mailID,int gnder,String password) {
		this.employeeName = name;
		this.employeeAddress = add;
		this.employeePhoneNumber = phno;
		this.employeeMail = mailID;
		this.employeePass = password;
		employeeDateOfBirth = d;
		switch(desig)
		{
		case 1:this.designation = DesignationsEnum.Executive.toString();break;
		case 2:this.designation = DesignationsEnum.Lead.toString();break;
		case 3:this.designation = DesignationsEnum.Manager.toString();break;
		}
		
		
		switch(gnder) {
		case 1: this.gender = genderOptions.MALE.toString();break;
		case 2: this.gender = genderOptions.FEMALE.toString();break;
		case 3: this.gender = genderOptions.OTHERS.toString();break;
		}
		
		
		generate_id();
		leave_arr.put(leaveNames.CASUAL_LEAVE.toString(), 10);
		leave_arr.put(leaveNames.EARNED_LEAVE.toString(), 15);
		leave_arr.put(leaveNames.DUTY_LEAVE.toString(), 0);
		leave_arr.put(leaveNames.SICK_LEAVE.toString(), 12);
		leave_arr.put(leaveNames.MATERNITY_LEAVE.toString(), 120);
		leave_arr.put(leaveNames.PARENTAL_LEAVE.toString(), 7);
		leave_arr.put(leaveNames.LEAVE_WITHOUT_PAY.toString(), 180);

	}

	void generate_id() {
		// String temp_Str = emp_name.substring(0,emp_name.indexOf(" "));
		// StringBuffer strbf = new StringBuffer(emp_name);
		// strbf.
		/*
		 * byte[] arr = employeeName.getBytes(); for (int i = 0; i < arr.length; i++) {
		 * if (arr[i] == 32) { arr[i] = 65; continue; } arr[i] = (byte) (((arr[i] - 65)
		 * % 26) + 65); } this.employeeID = new String(arr);
		 */
		String[] datePass = this.employeeDateOfBirth.toString().split(" ");
		for(int i=0;i<datePass.length;i++)
		{
			System.out.println(datePass[i]);
		}
		this.employeeID = this.employeeName + String.valueOf(this.designation.charAt(0)) + datePass[1] + datePass[5];
		this.employeeID.replace(" ", "_");
		
		System.out.println("\nYour New Employee ID is " + this.employeeID);
		System.out.println("Welcome " + this.employeeName + " In this organization!!!\n");
	}



}
