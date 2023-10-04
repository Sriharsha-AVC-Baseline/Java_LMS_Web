package com.itt.project;

import java.util.*;
import java.io.*;

public class Manager extends Employee implements  Serializable {


	BufferedReader bff = new BufferedReader(new InputStreamReader(System.in));
	Scanner scan = new Scanner(System.in);

	public Manager(String name, String add, int desig, Date d, String p,String mail,int gen,String password) {
		super(name, add, desig, d, p,mail,gen,password);
		// TODO Auto-generated constructor stub
	}

	
}
