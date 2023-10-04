package com.itt.database;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.*;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Prepare;


public class Database {
	
	Connect c1 = Connect.getConnection();
	Connect c2 = Connect.getConnection();
	
	//Path p = Path.of("C:\\Users\\sriharsha.s.bhat\\eclipse-workspace\\Leave_management_system_v1.1\\LMS_Database.txt");
	Connect c = Connect.getConnection();
	Connection con;
	public static final String username = "root";
	public static final String password = "rootAVC64";
	
	
	public Database() throws ClassNotFoundException, SQLException, IOException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connect c = Connect.getConnection();
		Connect c2 = Connect.getConnection();
		Connection con = c.connection;
		
//		this.c = Connect.getInstance();
//		this.con = c.getConnection();
		
		String database="",temp=null;
		//File databaseTables = new File(p.toFile().toString());
		//BufferedReader bff = new BufferedReader(new FileReader(databaseTables));
//		while((temp = bff.readLine())!=null)
//		{
//			database = database + temp;
//		}
//		System.out.println(database);
		
		// If the Application is running for the first time 
		try {
			Statement createTablesStatement = con.createStatement();
		//	createTablesStatement.execute(database);
			
		}
		catch(SQLException sq)
		{
			System.out.println(sq);
		}
		
	}
	
	
	
	

	
	
	
	
	
	
	

}
