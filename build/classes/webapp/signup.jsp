<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<% String link="https://picsum.photos/200/300"; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/css/bootstrap.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/2.3.2/css/bootstrap-responsive.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.4.1/js/bootstrap.js"></script>

<title>Insert title here</title>

<style>
 	body{
 	background-image:url("https://wallpapers.com/images/featured/sky-mvehfqz6w2ges2dj.jpg");
 	 
 	}

</style>

</head>

<%
	String name = request.getParameter("Exec");
%>

<body>
<h1 class="display-6" style="background-color: rgba(240,240,240,0.5);color: black;height:20%;text-align: center;">Welcome InTimeTec</h1>


<div style="margin: 5%;background-color: rgba(240,240,240,0.8);">


<form action="Signup" method="post" style="padding-left: 10%;padding-right: 10%;">
<br><br>
<h2>Enter These Details : </h2>
<br>
  <div class="form-group" >
    <h6 for="name">Name :</h6>
    <input type="text" class="form-control" id="name" name="name">
  </div>
  <div class="form-group">
    <label for="pwd"><h6>Phone number :</h6></label>
    <input type="number" class="form-control" id="pwd" name="phone">
  </div>
   <div class="form-group">
    <label for="pwd"><h6>Email :</h6></label>
    <input type="email" class="form-control" id="pwd" name="email">
  </div>
  <div class="form-group">
    <label for="exampleFormControlTextarea1"><h6>Permanent Address : </h6></label>
    <textarea class="form-control" id="exampleFormControlTextarea1" rows="3" name=address></textarea>
  </div>
  
  <br>
  <h6>Gender</h6>
  <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" name="gender">
    <option selected>Choose</option>
    <option value="1" name="Exec">Male</option>
    <option value="2" name="lead">Female</option>
    <option value="3" name="manager">Others</option>
  </select>
  <br><br>
  
  <h6>Date of Birth : </h6>
 
<input type="date" class="form-control" id="exampleInputPassword1" placeholder="Date of Birth" name="date">

<br>
  
  <h6>Designation : </h6>

  <select class="custom-select my-1 mr-sm-2" id="inlineFormCustomSelectPref" name="value">
    <option selected>Choose</option>
    <option value="Executive" name="Exec">Executive</option>
    <option value="Lead" name="lead">Lead</option>
    <option value="Manager" name="manager">Manager</option>
  </select>
  
  
  <br>
  <div>
   <h6 for="name">Password :</h6>
    <input type="password" class="form-control" id="pass" name="pass">
  </div>
  
  <br>
  
   <button type="Submit" class="btn btn-success">Submit</button>
   <br><br>
  </form>

 </div>
 
 

</body>
</html>