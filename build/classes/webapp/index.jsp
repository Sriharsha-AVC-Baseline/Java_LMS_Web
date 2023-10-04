<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
	
 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>

<title>Bootstrap demo</title>
</head>

<%
	String menu = "<h1 class=\"display-6\" style=\"background-color: lime;height:20%\">Welcome InTimeTec</h1>";
%>

<body style="text-align: center;">

	<div>

		<%= menu %>
		</br>
		</br>
	</div>
	<div class="container">
		<div>
			
			<h2 style=\"color: olive;\">Leave Management System</h2>
			
			<form method="post" action="Login" style="text-align: left;">
				<div class="form-group">
					<label for="name" style="text-align: left;">Employee ID:</label> <input
						type="text" class="form-control" id="emp_ID"
						placeholder="Enter ID" name="empID">
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" id="pwd" placeholder="Enter password"
						name="pwd">
				</div>


</div>
				</br>
				<button type="submit" class="btn btn-primary">Submit</button>
				
			
			</form>
		</div>
		<br>
		
		<a href="signup.jsp" class="link-primary">New Employee? Signup here...</a>
		<br>
		<br>
		
		<br/>
		<h6 style="text-align:center;">Note : Your Password will be Your full name(along with space) + First letter of your Designation (Uppercase) + month of your birth + year of your birth</h6>
		<h6>Example : name = "exec 1",designation = Executive,Month of birth = June,Year of Birth=2001</h6>
		<h6>Password = "exec 1EJun2001"</h6>
		
</body>
</html>