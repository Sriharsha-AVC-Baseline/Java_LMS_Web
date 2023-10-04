<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@page import="java.util.*"%>
<%@page import="com.itt.project.*"%>
<%@page import="com.itt.check_leaves.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
<%String menu = "<h1 class=\"display-6\" style=\"background-color: lime;height:20%;text-align:center\">Welcome InTimeTec</h1>";
	String type,name = (String)session.getAttribute("name");
	session.setAttribute("name", name);
	String pass = (String)session.getAttribute("pass");
	request.setAttribute("pass", pass);
	List<LeaveRecords> records = (List<LeaveRecords>) request.getAttribute("pendingLeaves");
%>
<style>
	.form-group{
	width:60%;
	padding:1%;
	margin:auto;
	}
</style>



</head>
<body>



<h1><%= menu %></h1>

<h1 style="text-align: center;"> <%=name %> </h1>

<form method="post" action="SubmitCancelRequest">




  <div class="form-group">
    <label for="exampleFormControlSelect1">Leave Type</label>
    <select class="form-control" id="select" name="selectLeave" onchange="splitString()">
    <option value="none" name="type">Select....</option>
     <c:forEach items="${pendingLeaves}" var="i">
     	<option name="type" value="${i.getLeaveType()} ${i.getFromDate()} ${i.getToDate()}"> ${i.getLeaveType()} From: ${i.getFromDate()} To: ${i.getToDate()} (${i.getNumberOfDays()} days)</option>
     </c:forEach>
    </select>
  </div>
  
  <div class="form-group">
   <br>
    <input class="btn btn-primary" type="submit" value="Submit">
  </div>
  
</form>
<script>
function splitString()
{
	var a = document.getElementsById('select').value;
	alert("Hello");
}


document.getElementById("date2").addEventListener("change",function()
		{
	var selectedLeave = document.getElementById("select").value;
	const array = selectedLeave.split("_");
	
	
	
	var date1 = document.getElementById("date1").value;
	var date2 = document.getElementById("date2").value;
	var initialDate = new Date(date1);
	var endDate = new Date(date2);
	var dateDiff = Math.abs(endDate - initialDate);
	var numberOfDays = Math.ceil(dateDiff / (1000 * 60 * 60 * 24));
	if(array[0]==="CASUAL" && numberOfDays > 2)
	{
	alert("Warning : Casual leaves cannot be applied for more than two days");
	}
	
	if(date1 >= date2)
		{
		alert("Invalid date");
		document.getElementById("date1").value=Date.parse(0);
		document.getElementById("date2").value=Date.parse(0);
		}
		});
</script>

</body>
</html>