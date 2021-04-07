<%@page import="com.mysql.cj.protocol.Message"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@page import="classes.item" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<form method="post" action="item.jsp">
	<center>
	Item Code : <input name="itemCode" type="text"><br><br><br>
	Item Name : <input name="itemName" type="text"><br><br><br>
	Item Price : <input name="itemPrice" type="text"><br><br><br>
	Item Description : <input name="itemDesc" type="text"><br><br><br>
	<input name="btmSubmit" type="submit" value="Save">
	</center>
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