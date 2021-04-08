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

					
					return "Insert successfully";

		}catch(Exception ee){
			return "Error while Inserting the items";
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
				 output += "<td>" + "<form method='post' action='item.jsp'>"
							+ "<input name='btnUpdate' type='submit' value='Update'></td>"
							+ "<input name='updateMode' type='hidden' value='" + itemID + "'>" + "</form>" + "<td>"
							+ "<form method='post' action='item.jsp'>"
							+ "<input name='btnRemove' type='submit' value='Remove'>"
							+ "<input name='itemID' type='hidden' value='" + itemID + "'>" + "</form></td></tr>";
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
		
	public String deleteItem(String itemID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "DELETE FROM items WHERE itemID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(itemID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String updateItem(int itemID, String code, String name, String price, String desc) {
		Connection con = connect();
		String output = "";
		if (con == null) {
			return "Error while updateing to the database";
		}

		// create a prepared statement
		String query = " update items set itemCode= ? , itemName = ? , itemPrice = ? , itemDesc = ?  where itemID = ? ";
		PreparedStatement preparedStmt;
		try {
			preparedStmt = con.prepareStatement(query);

			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
			preparedStmt.setInt(5, itemID);

			preparedStmt.executeUpdate();
			con.close();
			output = "updated successfully";
		} catch (SQLException e) {
			output = "Error while updateing";
			System.err.println(e.getMessage());
		}

		return output;
	}
}
