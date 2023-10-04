package com.itt.project;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Lead extends Employee implements Serializable {
	BufferedReader bff = new BufferedReader(new InputStreamReader(System.in));
	public Queue<Executive> exceutiveLeaveRequests = new LinkedList<Executive>();
	public Queue<Integer> exceutiveLeaveChoice = new LinkedList<Integer>();
	public Queue<Integer> executiveNumberOfLeaves = new LinkedList<Integer>();
	Manager mg;
	public int lve_choice;
	Scanner scan = new Scanner(System.in);

	public Lead(String name, String add, int desig, Date d, String p,String mail,int gen,Manager m,String password) {
		super(name, add, desig, d, p,mail,gen,password);
		this.mg = m;
		// TODO Auto-generated constructor stub
	}

	
	

}
