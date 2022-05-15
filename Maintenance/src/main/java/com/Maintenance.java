package com;

import java.sql.*;

public class Maintenance {

    public Connection connect(){
    	
        //database connection details
        String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "maintenance_management";
        String dbUsername = "root";
        String dbPassword = "";
        
        Connection conn = null;
        
        try {
        	//connecting the database
        	Class.forName(dbDriver);
        	conn = DriverManager.getConnection(dbURL+dbName, dbUsername, dbPassword);
        	
        } catch(Exception e) {
        	e.printStackTrace();
        }
        
        return conn;
    }
    
    
    //method to insert data
    public String insertItem(String id, String area, String gridName, String compType, String complaint) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "INSERT INTO maintenance (id,area,gridName,compType,complaint) VALUES (?,?,?,?,?)";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setString(1, id);
        	preparedStatement.setString(2, area);
        	preparedStatement.setString(3, gridName);
        	preparedStatement.setString(4, compType);
        	
        	//execute the SQL statement
        	preparedStatement.execute();
        	conn.close();

			String newItems = readItems(); 
			Output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\": \"Failed to insert the item\"}";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to update data
    public String updateItem(String compID, String id, String area, String gridName, String compType, String complaint) {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "UPDATE items SET id = ?,area = ?,gridName = ?,compType = ?, complaint = ? WHERE compID = ?";
        	
        	//binding data to SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setInt(1,Integer.parseInt(id));
        	preparedStatement.setString(2, area);
        	preparedStatement.setString(3, gridName);
        	preparedStatement.setString(4, compType);
        	preparedStatement.setString(5, complaint);
        	
        	//execute the SQL statement
        	preparedStatement.executeUpdate();
        	conn.close();
        	
        	String newItems = readItems(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
        	
    	} catch(Exception e) {
    		Output = "{\"status\":\"error\", \"data\":\"Failed to update the item.\"}"; 
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    
    //method to read data
    public String readItems() {
    	Connection conn = connect();
    	
    	String Output = "";
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "SELECT * FROM maintenance";
        	
        	//executing the SQL query
        	Statement statement = conn.createStatement();
        	ResultSet resultSet = statement.executeQuery(query);
        	
        	// Prepare the HTML table to be displayed
    		Output = "<table border='1'><tr><th>Id</th>" +"<th>Area</th><th>Grid Name</th>"
    		+ "<th>Complaint Type</th>"
    		+ "<th>Complaint</th>"
    		+ "<th>Update</th><th>Remove</th></tr>";
        	
        	while(resultSet.next()) {
        		String compID = Integer.toString(resultSet.getInt("compID"));
        		String id = Integer.toString(resultSet.getInt("id"));
        		String area = resultSet.getString("area");
        		String gridName = resultSet.getString("gridName");
        		String compType = Double.toString(resultSet.getDouble("compType"));
        		String complaint = resultSet.getString("complaint");
        		
        		// Add a row into the HTML table
        		Output += "<tr><td>" + id + "</td>"; 
        		Output += "<td>" + area + "</td>"; 
        		Output += "<td>" + gridName + "</td>"; 
        		Output += "<td>" + compType + "</td>";
        		Output += "<td>" + complaint + "</td>";
        		
        		// buttons
        		Output += "<td>"
						+ "<input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-sm btn-secondary' data-itemid='" + compID + "'>"
						+ "</td>" 
        				+ "<td>"
						+ "<input name='btnRemove' type='button' value='Remove' class='btn btn-sm btn-danger btnRemove' data-itemid='" + compID + "'>"
						+ "</td></tr>";
        	}

        	conn.close();
        	
        	// Complete the HTML table
        	Output += "</table>";
        	
    	} catch(Exception e) {
    		Output = "Failed to read the items";
    		System.err.println(e.getMessage());
    	}
    	
    	return Output;
    }
    
    //method to delete data
    public String deleteItem(String compID) {
    	String Output = "";
    	Connection conn = connect();
    	
    	try {
        	if (conn == null) {
        		return "Database connection error";
        	}
        	
        	//SQL query
        	String query = "DELETE FROM maintenance WHERE compID = ?";
        	
        	//binding data to the SQL query
        	PreparedStatement preparedStatement = conn.prepareStatement(query);
        	preparedStatement.setInt(1, Integer.parseInt(compID));
        	
        	//executing the SQL statement
        	preparedStatement.execute();
        	conn.close();
        	
        	String newItems = readItems(); 
      		Output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}"; 
        	
    	} catch(Exception e) {
			Output = "{\"status\":\"error\", \"data\":\"Failed to delete the item.\"}";
    		System.err.println(e.getMessage());
    	}
    	return Output;
    }
}
