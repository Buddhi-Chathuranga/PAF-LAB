<%@page import="com.mysql.cj.protocol.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="classes.item"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div class="container">
		<form method="post" action="item.jsp">
			<div class="form-group">
				<label for="text">Item Code : </label> <input name="itemCode"
					type="text" class="form-control" placeholder="Enter Item Code">
			</div>
			<div class="form-group">
				<label for="text">Item Name : </label> <input name="itemName"
					type="text" class="form-control" placeholder="Enter Item Name">
			</div>
			<div class="form-group">
				<label for="text">Item Price : </label> <input name="itemPrice"
					type="number" min="0.00" max="10000.00" step="0.01"
					class="form-control" placeholder="Enter Item Price">
			</div>
			<div class="form-group">
				<label for="text">Item Description : </label> <input name="itemDesc"
					type="text" class="form-control"
					placeholder="Enter Item Description">
			</div>

			<button name="btmSubmit" type="submit" value="Save"
				class="btn btn-default">Submit</button>
		</form>
	</div>
	<br>
	<br>
<%
	item ob1 = new item();
	out.print(ob1.selectItem()); 
%>
<%
//add
	if(request.getParameter("itemCode") != null){
		item ob = new item();
		String stsMsg = ob.insertItem(request.getParameter("itemCode").toString(),request.getParameter("itemName").toString(),request.getParameter("itemPrice").toString(),request.getParameter("itemDesc").toString());
		session.setAttribute("statusMsg", stsMsg); 
	}
//delete
	if (request.getParameter("itemID") != null){ 
		 item itemObj = new item(); 
		 String stsMsg = itemObj.deleteItem(request.getParameter("itemID")); 
		 session.setAttribute("statusMsg", stsMsg); 
	 }
//update 
	 if (request.getParameter("itemCode") != null && (session.getAttribute("updateMode") != null)) {
		 System.out.println("update called");
			item itemObj = new item();
			String stsMsg = itemObj.updateItem(Integer.parseInt(String.valueOf(session.getAttribute("updateMode"))),request.getParameter("itemCode"), request.getParameter("itemName"), request.getParameter("itemPrice"),request.getParameter("itemDesc"));

		//System.out.println("xxxxx");
			session.setAttribute("statusMsg", stsMsg);
		} 
	 	else if (request.getParameter("itemCode") != null) { //insert new
			item itemObj = new item();
			String stsMsg = itemObj.insertItem(request.getParameter("itemCode"), request.getParameter("itemName"),
			request.getParameter("itemPrice"), request.getParameter("itemDesc"));
			session.setAttribute("statusMsg", stsMsg);
	}
%>
	<center><div class="container">
	<div class="alert alert-success">
		<% out.print(session.getAttribute("statusMsg"));%>
		<br>
		</div></center>
		<br>
	</div>




</body>
</html>