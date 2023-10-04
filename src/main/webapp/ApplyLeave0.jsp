<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@page import="java.util.*"%>
<%@page import="com.itt.check_leaves.Leaves"%>

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
</style>

<script>

$(function(){
	  $("#header").load("Header.html"); 
	  $("#footer").load("footer.html"); 
	});

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
	alert("Warning : Casual leaves cannot be applied for more than 2 days");
	}
	
	if(date1 >= date2)
		{
		alert("Invalid date");
		document.getElementById("date1").value=Date.parse(0);
		document.getElementById("date2").value=Date.parse(0);
		}
		});
</script>



</head>
<body>



<div id="header"></div>

<h1 style="text-align: center;"> <%=name %> </h1>

<form method="post" action="SubmitLeaveRequest">

  <div class="form-group">
    <label for="exampleFormControlSelect1">Leave Type</label>
    <select class="form-control" id="select" name="selectLeave" onchange="splitString()">
    <option value="none" name="type">Select....</option>
     <c:forEach items="${leaveList}" var="i">
     	<option name="type" value="${i}"> ${i} </option>
     </c:forEach>
    </select>
  </div>
  <div class="form-group" style="align-items: flex-end;">
    <label for="exampleFormControlSelect2">From : </label>
	<input type="date" name = "fromDate" id="date1">
	<label for="exampleFormControlSelect2">&emsp; To : </label>
	<input type="date" name = "toDate" id="date2">
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1">Example text area</label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name="comment"></textarea>
    <br>
    <input class="btn btn-primary" type="submit" value="Submit">
  </div>
  
</form>

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