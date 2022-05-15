package com.CustomerService.customer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class customer {
	
	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/customer", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}

	//read customer details 
		public String readCustomers() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'; ><tr><th>ElectricityAccountNo</th>" + "<th>CustomerName</th><th>CustomerAddress</th>"
						+ "<th>PremisesID</th>"
						+ "<th>Update</th><th>Remove</th></tr>";

				String query = "select * from customers";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String ElectricityAccountNo = Integer.toString(rs.getInt("ElectricityAccountNo"));
					String CustomerName = rs.getString("CustomerName");
					String CustomerAddress = rs.getString("CustomerAddress");
					String PremisesID = rs.getString("PremisesID");

					// Add into the html table
					output += "<tr><td><input id='hidCusIDUpdate' name='hidCusIDUpdate'type='hidden' value='" + ElectricityAccountNo
							+ "'>" + ElectricityAccountNo + "</td>";
					output += "<td>" + CustomerName + "</td>";
					output += "<td>" + CustomerAddress + "</td>";
					
					output += "<td>" + PremisesID + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
							+ "<td>"
							+ "<input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-orderid = '"
							+ ElectricityAccountNo + "'></td>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the customers.";
				System.err.println(e.getMessage());
			}
			return output;
		}

		// add customer to Member List
		public String insertCustomer(String CustomerName, String CustomerAddress, String PremisesID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " insert into customer(`ElectricityAccountNo`,`CustomerName`,`CustomerAddress`,`PremisesID`)"
						+ " values (?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, CustomerName);
				preparedStmt.setString(3, CustomerAddress);
				preparedStmt.setString(4, PremisesID);
				// execute the statement

				preparedStmt.execute();
				con.close();
				String newCustomers = readCustomers();
				output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while inserting the Order.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}

		// update customer details
		public String updateCustomer(int ElectricityAccountNo, String CustomerName, String CustomerAddress, String PremisesID)

		{
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE customers SET CustomerName=?,CustomerAddress=?,PremisesID=? WHERE orderID=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values

				preparedStmt.setString(1, CustomerName);
				preparedStmt.setString(2, CustomerAddress);
				preparedStmt.setString(3, PremisesID);
				preparedStmt.setInt(4, Integer.parseInt(ElectricityAccountNo));

				// execute the statement
				preparedStmt.execute();
				con.close();
				String newCustomers = readCustomers();
				output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;

		}

		// delete customer
		public String deleteCustomer(String ElectricityAccountNo) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from customers where ElectricityAccountNo=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(ElectricityAccountNo));
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newCustomers = readCustomers();
				output = "{\"status\":\"success\", \"data\": \"" + newCustomers + "\"}";
			} catch (Exception e) {
				output = "{\"status\":\"error\", \"data\":\"Error while updating the customers.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}

		

}
