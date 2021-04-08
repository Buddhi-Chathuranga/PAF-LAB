<%@page import="com.mysql.cj.protocol.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="classes.item" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="container">
<form method="post" action="item.jsp">
    <div class="form-group">
      <label for="text">Item Code : </label>
      <input name="itemCode" type="text" class="form-control" placeholder="Enter Item Code" >
    </div>
    <div class="form-group">
      <label for="text">Item Name : </label>
      <input name="itemName" type="text" class="form-control" placeholder="Enter Item Name" >
    </div>
    <div class="form-group">
      <label for="text">Item Price : </label>
      <input name="itemPrice" type="number" min="0.00" max="10000.00" step="0.01" class="form-control" placeholder="Enter Item Price" >
    </div>
    <div class="form-group">
      <label for="text">Item Description : </label>
      <input name="itemDesc" type="text" class="form-control" placeholder="Enter Item Description" >
    </div>
    
    <button name="btmSubmit" type="submit" value="Save" class="btn btn-default">Submit</button>
  </form>
  </div>
  <br><br>
<%
System.out.println("Inner Java");
String msgSelect;
	if(request.getParameter("itemCode") != null){
		item ob = new item();
		String msg = ob.insertItem(request.getParameter("itemCode").toString(),request.getParameter("itemName").toString(),request.getParameter("itemPrice").toString(),request.getParameter("itemDesc").toString());
		
		if(msg=="True"){
			System.out.println("Success Insert");
			msgSelect = ob.selectItem();
		}
		else{
			System.out.println("Unsuccess Insert");
		}
	}
%>

<%
	item ob = new item();
	out.print(ob.selectItem()); 
%>



	

</body>
</html>