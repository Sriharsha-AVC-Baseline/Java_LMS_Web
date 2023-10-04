<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.itt.generate_employee_list.EmployeeList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

	<script
    src="https://code.jquery.com/jquery-3.3.1.js"
    integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
    crossorigin="anonymous">
</script>

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
	integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
	crossorigin="anonymous"></script>
	
	<style>
	td{
	style="text-align:center;
	}
	</style>
	
	
	
<style>

	.table{
		width:86%;
		margin:auto;
	}


</style>

</head>
<body>
	<h1 style="text-align: center; background-color: black; color: aqua;">InTimeTec
		Family</h1>

	<br>
	<%
	
	List<EmployeeList> execList = (ArrayList<EmployeeList>) request.getAttribute("executiveList");
	List<EmployeeList> leadList = (ArrayList<EmployeeList>) request.getAttribute("leadList");
	List<EmployeeList> managerList = (ArrayList<EmployeeList>) request.getAttribute("managerList");
	request.setAttribute("execList", execList);
	request.setAttribute("leadList", leadList);
	request.setAttribute("managerList", managerList);
	/* PrintWriter pw = response.getWriter();
	pw.println(execList.get(0)); */
	//execList.stream().forEach(e->pw.println("<h5> "+e.execName+" "+e.empPhone+" "+e.empMail+" "+e.leadName+" "+e.managerName+" </h5"));
%>
	<h4 style="text-align:center; ">Executives</h4>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">Employee Name</th>
				<th scope="col">Phone number</th>
				<th scope="col">Email ID</th>
				<th scope="col">His Lead</th>
				<th scope="col">His Manager</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="i" items="${execList}" >
		<tr>
		<td><c:out value="${i.getEmpName()}"></c:out></td>
		<td><c:out value="${i.getEmpPhone()}"></c:out></td>
		<td><c:out value="${i.getEmpMail()}"></c:out></td>
		<td><c:out value="${i.getLeadName()}"></c:out></td>
		<td><c:out value="${i.getManagerName()}"></c:out></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		
		<br>
		<br>
		<h4 style="text-align:center; ">Leads</h4>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">Employee Name</th>
				<th scope="col">Phone number</th>
				<th scope="col">Email ID</th>
				<th scope="col">His Manager</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="i" items="${leadList}" >
		<tr>
		<td><c:out value="${i.getLeadName()}"></c:out></td>
		<td><c:out value="${i.getEmpPhone()}"></c:out></td>
		<td><c:out value="${i.getEmpMail()}"></c:out></td>
		<td><c:out value="${i.getManagerName()}"></c:out></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
		
		<br>
		<br>
		<h4 style="text-align:center; ">Manager</h4>
	<table class="table">
		<thead class="thead-light">
			<tr>
				<th scope="col">Employee Name</th>
				<th scope="col">Phone number</th>
				<th scope="col">Email ID</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="i" items="${managerList}" >
		<tr>
		<td><c:out value="${i.getManagerName()}"></c:out></td>
		<td><c:out value="${i.getEmpPhone()}"></c:out></td>
		<td><c:out value="${i.getEmpMail()}"></c:out></td>
		</tr>
		</c:forEach>
		</tbody>
		</table>
</body>
</html>