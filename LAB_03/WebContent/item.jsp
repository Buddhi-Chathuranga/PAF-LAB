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



<form method="post" action="item.jsp" >
	<div class="form-group">Item Code : <input name="itemCode" type="text" class="form-control" ></div><br><br>
	<div class="form-group">Item Name : <input name="itemName" type="text" class="form-control"></div><br><br>
	<div class="form-group">Item Price : <input name="itemPrice" type="text" class="form-control"></div><br><br>
	<div class="form-group">Item Description : <input name="itemDesc" type="text" class="form-control"></div><br>
	<center><input name="btmSubmit" type="submit" value="Save" class="btn btn-primary"></center>
</form>
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