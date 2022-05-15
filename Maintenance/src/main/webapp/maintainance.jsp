<%@page import="com.Maintenance"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Maintenance Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Maintenance Management V10.1</h1>
<form id="formItem" name="formItem">
 Id: 
 <input id="id" name="id" type="text" 
 class="form-control form-control-sm">
 <br> Item name: 
 <input id="area" name="area" type="text" 
 class="form-control form-control-sm">
 <br> Grid Name: 
 <input id="gridName" name="gridName" type="text" 
 class="form-control form-control-sm">
 <br> Complaint Type: 
 <input id="compType" name="compType" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
 Maintenance maintainObj = new Maintenance(); 
 out.print(maintainObj.readItems()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
