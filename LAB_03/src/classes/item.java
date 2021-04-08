package classes;
import java.sql.*;

public class item {
	
	public Connection connect()
	{
	 Connection con = null;

	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf","root", "");
	 //For testing
	 System.out.print("Successfully connected");
	 }
	 catch(Exception e)
	 {
	 e.printStackTrace();
	 return null;
	 }

	 return con;
	}
	
	public String insertItem(String code, String name, String price, String desc) {
		try {
			Connection con = connect();
			System.out.print(con);
			String query = "insert into items (itemID,itemCode,itemName,itemPrice,itemDesc) values (?, ?, ?, ?, ?);"; 
					PreparedStatement preparedStmt = con.prepareStatement(query); 
					// binding values
					preparedStmt.setInt(1, 0); 
					preparedStmt.setString(2, code); 
					preparedStmt.setString(3, name); 
					preparedStmt.setDouble(4, Double.parseDouble(price)); 
					preparedStmt.setString(5, desc);
					
					preparedStmt.execute(); 
					con.close();

					
					return "True";

		}catch(Exception ee){
			return "False";
		}
	}
		
		public String selectItem() {
			String output = ""; 
			try
			 { 
				 Connection con = connect(); 
				 if (con == null)
				 { 
					 	return "Error while connecting to the database for reading."; 
				 } 
				 
			 output = "<div class='container'><table class='table'><tr><th>Item Code</th>" 
						 +"<th scope='col'>Item Name</th><th>Item Price</th>"
						 + "<th scope='col'>Item Description</th>" 
						 + "<th scope='col'>Update</th><th>Remove</th></tr>"; 
			 
			 String query = "select * from items"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 // iterate through the rows in the result set
			 while (rs.next()) 
			 { 
				 String itemID = Integer.toString(rs.getInt("itemID")); 
				 String itemCode = rs.getString("itemCode"); 
				 String itemName = rs.getString("itemName"); 
				 String itemPrice = Double.toString(rs.getDouble("itemPrice")); 
				 String itemDesc = rs.getString("itemDesc"); 
				 // Add into the html table
				 output += "<tr><td>" + itemCode + "</td>"; 
				 output += "<td>" + itemName + "</td>"; 
				 output += "<td>" + itemPrice + "</td>"; 
				 output += "<td>" + itemDesc + "</td>";
				 // buttons
				 output += "<td><input name='btnUpdate' " 
							 + " type='button' value='Update'></td>"
							 + "<td><form method='post' action='items.jsp'>"
							 + "<input name='btnRemove' " 
							 + " type='submit' value='Remove'>"
							 + "<input name='itemID' type='hidden' " 
							 + " value='" + itemID + "'>" + "</form></td></tr>"; 
				 } 
					 con.close(); 
					 // Complete the html table
					 output += "</table></div>"; 
				 } 
				catch (Exception e) 
				{ 
					 output = "Error while reading the items."; 
					 System.err.println(e.getMessage()); 
				} 
				return output; 
	}

}
