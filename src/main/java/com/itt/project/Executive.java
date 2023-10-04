package com.itt.project;

import java.util.Date;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.*;

public class Executive extends Employee implements Serializable {
	SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	Date from = new Date();
	Date to = new Date();
	Scanner scan = new Scanner(System.in);
	BufferedReader bff = new BufferedReader(new InputStreamReader(System.in));
	Lead ld;
	Manager mg;
	public int lve_choice;

	public Executive(String name, String add, int desig, Date d, String phno,String mail,int gen,Lead l, Manager m,String password) {
		super(name, add, desig, d, phno,mail,gen,password);
		this.ld = l;
		this.mg = m;
		// TODO Auto-generated constructor stub
	}

}
