

import java.sql.*;
import javax.sql.*;

public class Connect {
	
	private String username = "root";
	private String password = "rootAVC64";
	
	private Connect() {}
	
	public static Connect getInstance()
	{
		return new Connect();
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/LMS?useSSL=false",username,password);
		return con;
	}

}
