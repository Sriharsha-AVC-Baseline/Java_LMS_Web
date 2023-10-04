

<%@page import="com.itt.check_leaves.LeaveRecords"%>
<%@page import="com.itt.check_leaves.Leaves"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.itt.check_leaves.Leaves.*"%>
<%@page import="com.itt.project.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js"
	integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT"
	crossorigin="anonymous"></script>

<!-- https://www.youtube.com/watch?v=KqpIIaCJggY 
<c:set var="Income" scope="session" value="${4000*4}"/>  
<c:out value="${Income}"/>  

-->

<style>
.dropdown {
	float: right;
	padding: 10px;
	width: 20%;
	text-align: right;
}

#round {
	background-color: rgba(210, 210, 210, 0.6);
	border: none;
	color: black;
	padding: 10px;
	padding-right: 10px;
	text-align: center;
	display: inline-block;
	font-size: 20px;
	font-family: inherit;
	margin-right: 10px;
	border-radius: 50%;
	background-color: orange;
}

.dropdown .content {
	visibility: hidden;
	font-weight: bold;
	font-size: 17px
}

#leavedetails {
	border: solid, 5px text-align:center;
	margin: auto;
	width: 50%;
	font-size: 20px;
	font-weight: bold;
}

.center {
	margin: auto;
	width: 70%;
	font-size: 20px;
	font-weight: bold;
	display: flex;
	align-items: center;
}
</style>

<title>Bootstrap demo</title>

</head>

<%
String menu = "<h1 class=\"display-6\" style=\"background-color: lime;height:20%;text-align:center\">Welcome InTimeTec</h1>";
String name, designation, pass;
Leaves l = (Leaves) session.getAttribute("leave");
name = (String) session.getAttribute("name");
designation = (String) session.getAttribute("desig");
pass = (String) session.getAttribute("pass");
//List<LeaveRecords> records = (ArrayList<LeaveRecords>) session.getAttribute("leaveRecords");
List<LeaveRecords> records = new GetLeaveRecords().getLeaveRecords(pass);
request.setAttribute("leaveRecords", records);
System.out.println("JSP START");
records.stream().forEach(e->System.out.println(e.getEmployeeID()+" "+e.getLeaveType()+" "+e.getLeaveStatus()+" "+e.getNumberOfDays()));
System.out.println("JSP END");
%>
<body>
	<h1><%=menu%></h1>


	<div class="dropdown">
		<button id="round" onclick="showHide('content')">
			&nbsp;
			<%=Character.toUpperCase(name.charAt(0))%>
			&nbsp;
		</button>
		<div class="content" id="content"
			style="visibility: hidden; border: 10px;">
			<ul style="list-style: none; text-align: right;" id="list">
				<li><%=name%></li>
				<li><%=designation%></li>
				<li><a href="#" onclick="showHide('leavedetails')">Check my
						leaves</a></li>
				<li><a href="#" onclick="showHide('leaveRecords')">Check my
						Records</a></li>
				<li><a href="index.jsp" style="color: red;">Logout</a></li>
			</ul>
		</div>
	</div>

	<br>
	<br>
	<h3 class="display-4" style="text-align: center;">
		Welcome
		<%=name%>, you are logged in as
		<%=designation%>
	</h3>

	<br>
	<br>
	<div class="center">
	<div>
		<a style="text-decoration: none;" href="applyeave"><button
				type="button" class="btn btn-primary btn-lg">Apply For
				leave</button></a>
	</div>
	<div>
		<a style="text-decoration: none;" href="CancelLeave">
		<button
			type="button" class="btn btn-primary btn-lg" style="box-sizing: border-box;
    margin-left: 20px;">Cancel a leave</button></a>
	</div>
		</div>
	<br>
	<br>
	<div id="details" class="center"></div>
	<%--  <ul style="list-style: none;">
  <li>Casual leaves : <h3 style="text-align:right;color:green"><%= l.casualLeave %></h3></li>
  <li>Earned Leaves : <h3 style="text-align:right;color:green"><%= l.earnedLeave %></h3></li>
  <li>Duty Leaves : <h3 style="text-align:right;color:green"><%= l.dutyLeave %></h3></li>
  <li>Sick Leaves : <h3 style="text-align:right;color:green"><%= l.sickLeave %></h3></li>
  <li>Maternity Leaves : <h3 style="text-align:right;color:green"><%= l.maternityLeaves %></h3></li>
  <li>Parental Leaves : <h3 style="text-align:right;color:green"><%= l.parentalLeaves %></h3></li>
  <li>Leave without pay : <h3 style="text-align:right;color:green"><%= l.leaveWithoutPay %></h3></li>
  </ul> --%>

	<table class="table" id="leavedetails" style="visibility: hidden;">
		<thead class="table-dark" style="text-align: center;">
			<th>Leave Types</th>
			<th>Leaves left</th>
		</thead>
		<tbody style="text-align: center;">
			<tr>
				<td>Casual leave</td>
				<td><%=l.casualLeave%> days</td>
			</tr>
			<tr>
				<td>Earned Leaves</td>
				<td><%=l.earnedLeave%> days</td>
			</tr>
			<tr>
				<td>Duty Leaves</td>
				<td><%=l.dutyLeave%></td>
			</tr>
			<tr>
				<td>Sick Leaves</td>
				<td><%=l.sickLeave%> days</td>
			</tr>
			<tr>
				<td>Maternity Leaves</td>
				<td><%=l.maternityLeaves%> days</td>
			</tr>
			<tr>
				<td>Parental Leaves</td>
				<td><%=l.parentalLeaves%> days</td>
			</tr>
			<tr>
				<td>Leave without pay</td>
				<td><%=l.leaveWithoutPay%> days</td>
			</tr>
		</tbody>

	</table>
	
	<table class="table" id="leaveRecords" style="visibility: hidden;">
		<thead class="table-dark" style="text-align: center;">
			<tr>
				<th scope="col">Leave Type</th>
				<th scope="col">From</th>
				<th scope="col">To</th>
				<th scope="col">Status</th>
				<th scope="col">Number Of Leaves</th>
			</tr>
		</thead>
		<tbody style="text-align: center;">
		<c:forEach var="i" items="${leaveRecords}">
		<tr>
				<td>${i.getLeaveType()}</td>
				<td>${i.getFromDate()}</td>
				<td>${i.getToDate()}</td>
				<td>${i.getLeaveStatus()}</td>
				<td>${i.getNumberOfDays()}</td>
		</tr>
		</c:forEach>
		</tbody>
	</table>



	<script>
		function myFunction() {
			document.querySelector('#leavedetails').style.visibility = 'visible';
		}

		function showHide(parameter) {
			var id = ('#' + parameter);
			var status = document.querySelector(id).style.visibility;
			if (status == 'hidden') {
				document.querySelector(id).style.visibility = 'visible';
			} else if (status == 'visible') {
				document.querySelector(id).style.visibility = 'hidden';
			}
			/* (status=='visible') ? document.querySelector(id).style.visibility='visible': document.querySelector(id).style.visibility='hidden';' */
		}
	</script>

</body>



</html>