package com.itt.generate_employee_list;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.itt.database.Connect;
import com.mysql.cj.xdevapi.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/employee")
public class EmployeeList extends HttpServlet{
	
	
	
	public String getEmpName() {
		return empName;
	}


	public String getEmpMail() {
		return empMail;
	}


	public String getManagerName() {
		return managerName;
	}


	public String getLeadName() {
		return leadName;
	}


	public String getEmpPhone() {
		return empPhone;
	}


	private String empName,empMail,managerName,leadName,empPhone;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Connect connect = null;
		try {
			connect = Connect.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con=null;
		ResultSet executiveResultSet=null,leadsResultSet=null,managerResultSet=null;
		List<EmployeeList> executiveList = new ArrayList<EmployeeList>();
		List<EmployeeList> leadList = new ArrayList<EmployeeList>();
		List<EmployeeList> managerList = new ArrayList<EmployeeList>();
		try {
			con = connect.connection;
			java.sql.Statement getExecutiveList =  con.createStatement();
			String getExecutiveStatement = "SELECT DISTINCT E.EMP_NAME,E.EMP_MAIL,E.EMP_PHONE,M.EMP_NAME,L.EMP_NAME FROM EMPLOYEE E,EMPLOYEE L,EMPLOYEE M,EXECU EX,MANAGER MAN,LEADS LD WHERE EX.EXEC_ID=E.EMP_ID AND EX.MANAGER_ID=M.EMP_ID AND EX.LEAD_ID=L.EMP_ID";
			executiveResultSet = getExecutiveList.executeQuery(getExecutiveStatement);
			EmployeeList temp;
			while(executiveResultSet.next())
			{
				temp = new EmployeeList();
				temp.empName=executiveResultSet.getString(1);
				temp.empMail=executiveResultSet.getString(2);
				temp.empPhone=executiveResultSet.getString(3);
				temp.managerName=executiveResultSet.getString(4);
				temp.leadName=executiveResultSet.getString(5);
				executiveList.add(temp);
			}
			java.sql.Statement getLeadsList =  con.createStatement();
			String getLeadStatement = "SELECT DISTINCT E.EMP_NAME,E.EMP_MAIL,E.EMP_PHONE,M.EMP_NAME FROM EMPLOYEE E,EMPLOYEE L,EMPLOYEE M,MANAGER MAN,LEADS LD WHERE LD.LEAD_ID=E.EMP_ID AND LD.MANAGER_ID=M.EMP_ID";
			leadsResultSet = getExecutiveList.executeQuery(getLeadStatement);
			EmployeeList temp2;
			while(leadsResultSet.next())
			{
				temp2 = new EmployeeList();
				temp2.leadName=leadsResultSet.getString(1);
				temp2.empMail=leadsResultSet.getString(2);
				temp2.empPhone=leadsResultSet.getString(3);
				temp2.managerName=leadsResultSet.getString(4);
				leadList.add(temp2);
			}
			java.sql.Statement getManagerList = con.createStatement();
			String getManagerStatement = "SELECT DISTINCT E.EMP_NAME,E.EMP_MAIL,E.EMP_PHONE FROM EMPLOYEE E,MANAGER M WHERE E.EMP_ID=M.MANAGER_ID ";
			managerResultSet = getManagerList.executeQuery(getManagerStatement);
			EmployeeList temp3;
			while(managerResultSet.next())
			{
				temp3 = new EmployeeList();
				temp3.managerName = managerResultSet.getString(1);
				temp3.empMail = managerResultSet.getString(2);
				temp3.empPhone = managerResultSet.getString(3);
				managerList.add(temp3);
			}
			
		} catch ( SQLException e) {
			e.printStackTrace();
		}
		
	
		
		
		req.setAttribute("executiveList", executiveList);
		req.setAttribute("leadList", leadList);
		req.setAttribute("managerList", managerList);
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("EmployeeList.jsp");
		requestDispatcher.include(req, resp);
	}

}
