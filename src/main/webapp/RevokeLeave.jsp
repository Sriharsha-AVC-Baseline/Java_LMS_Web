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
	
	<script
    src="https://code.jquery.com/jquery-3.3.1.js"
    integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
    crossorigin="anonymous">
</script>
	
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
<%String menu = "<h1 class=\"display-6\" style=\"background-color: lime;height:20%;text-align:center\">Welcome InTimeTec</h1>";
	String type,name = (String)session.getAttribute("name");
	session.setAttribute("name", name);
	String pass = (String)session.getAttribute("pass");
	request.setAttribute("pass", pass);
	List<LeaveRecords> records = (List<LeaveRecords>)request.getAttribute("employeeList");
	request.setAttribute("employeeList", records);
%>
<style>
	.form-group{
	width:60%;
	padding:1%;
	margin:auto;
	}
	
	body{
	flex-direction: column;
	 min-height: 100vh;
	 display: flex;
	}
	
	
	.center{
		margin: auto;
		width:80%;
		padding: 10px;
	}
</style>



</head>
<body>



<div id="header"></div>

<h1 style="text-align: center;"> <%=name %> </h1>


<form method="post" action="RevokeBack" >
  <div class="form-group">
    <label for="exampleFormControlSelect1">Leave Type</label>
    <select class="form-control" id="select" name="selectLeave">
    <option value="none" name="type">Select....</option>
     <c:forEach items="${employeeList}" var="i">
     	<option value="${i.getEmployeeID()} ${i.getLeaveType()} ${i.getLeaveStatus()} ${i.getFromDate()} ${i.getToDate()} ${i.getNumberOfDays()}"> ${i.getEmployeeID()} &nbsp; ${i.getLeaveType()} &nbsp; ${i.getLeaveStatus()} &nbsp; From : ${i.getFromDate()} &nbsp; to : ${i.getToDate()} &nbsp; Number Of Days : ${i.getNumberOfDays() } </option>
     </c:forEach>
    </select>

    <div class="form-check" style="margin: auto;">
  <input class="form-check-input" type="radio" name="Decision" id="flexRadioDefault1" value="Revoke" onclick="getValue('Revoke')">
  <label class="form-check-label" for="flexRadioDefault1" >
    Revoke
  </label>
  </div >
  <div class="form-check">
    <input class="form-check-input" type="radio" name="Decision" id="flexRadioDefault1" value="Later" onclick="getValue('Think Later')">
   <label class="form-check-label" for="flexRadioDefault1" >
    Think Later
  </label>
  </div>
  
  <h2 style="color: black;text-align:center" name="Status" id = "Decision"></h2>
    <button value="Submit" class="btn btn-primary" type="submit">Submit</button>
    </form>
</div>
  
    


<script>

function getValue(result)
{
	if(result.localeCompare("Revoke")==0)
		{
	document.getElementById("Decision").innerHTML = "Decision Taken : "+result;
	document.getElementById("Decision").style.color="green";
}

	else if(result.localeCompare("Think Later")==0)
	{
	document.getElementById("Decision").innerHTML = "Decision Taken : "+result;
	document.getElementById("Decision").style.color="black";
}
}


</script>
<!-- Footer -->
<footer style="background-color: black;margin-top: auto;">

  <!-- Copyright -->
  <div >
  <h2 style="text-align: center; color: aqua;">IntimeTec 2009 Copyright:</h2>
  </div>
  <!-- Copyright -->

</footer>
<!-- Footer -->
</body>
</html>