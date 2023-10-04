package com.itt.database;

import java.sql.*;
import javax.sql.*;

public class Connect {
	
	private  String username = "root";
	private  String password = "rootAVC64";
	public  Connection connection;
	private static  Connect con;
	
	private Connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		this.connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?useSSL=false",username,password);
	
		}

	public static Connect getConnection() throws ClassNotFoundException, SQLException
	{
		if (con == null) {
			con = new Connect();
		}

		return con;
	}

}
