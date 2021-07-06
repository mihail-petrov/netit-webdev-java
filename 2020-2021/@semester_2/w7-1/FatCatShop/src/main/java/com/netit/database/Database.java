package com.netit.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Database {

	private Connection connection;
	private String whereQuery = " WHERE ";
	
	public Database() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}		
		
		try {
			this.connection = DriverManager.getConnection(
					DatabaseConfig.getUrl(), 
					DatabaseConfig.getCredentials()
			);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insert(String table, String[] into, String[] values) {
		// INSERT INTO () VALUES()
		
		String intoColumString = "";
		for(String element : into) {
			intoColumString += (element + ",");
		}
		intoColumString = "(" + 
						  intoColumString.substring(0, intoColumString.length() - 1) + 
						  ")";
		
		String valuesColumString = "";
		for(String element: values) {
			valuesColumString += (element + ",");
		}
		valuesColumString = "(" + 
							 valuesColumString.substring(0, valuesColumString.length() - 1) + 
							 ")"; 
		
		String query = "INSERT INTO " 		+ 
						table 	 	  		+ 
						intoColumString 	+
						"VALUES"			+
						valuesColumString;
		
		this.executeQuery(query);
	}
	
	public void insert(String table, HashMap<String, String> relation) {
		
		String[] columnCollection  = relation.keySet().toArray(new String[0]);
		String[] valueCollection = relation.values().toArray(new String[0]);
		this.insert(table, columnCollection, valueCollection);
	}
	
	// UPDATE {table} 
	// SET {key} = {value}
	// WHERE {col_name} [operator] {value}
	public void update(String table, String[] column, String[] value) {
		
		String updateSet = "";
		for(int i = 0; i < column.length; i++) {
			updateSet += column[i] + " = " + value[i] + ",";
		}
		updateSet = updateSet.substring(0, updateSet.length() - 1);
		
		String query = "UPDATE " 	+ 
						table  		+ 
						" SET "		+ 
						updateSet	+
						this.dumpWhereQuery();
		
		this.executeQuery(query);
	}	
	
	public void update(String table, HashMap<String, String> relation) {

		String[] columnCollection  = relation.keySet().toArray(new String[0]);
		String[] valueCollection = relation.values().toArray(new String[0]);
		this.update(table, columnCollection, valueCollection);
	}
	
	// DELETE FROM users
	// WHERE 
	// phone IS NOT NULL
	public void delete(String table) {

		String query = "DELETE FROM " 	+ 
						table			+
						this.dumpWhereQuery();
		this.executeQuery(query);
	}		
	
	public ResultSet select(String table) {
		
		String query = "SELECT * FROM " + 
						table			+ 
						this.dumpWhereQuery();
		
		return fetchQuery(query);
	}	
	
	
	public ResultSet select(String table, String[] columnCollection) {
		
		String query = "SELECT " + 
						DatabaseQueryUnit.produceCSR(columnCollection) + 
						" FROM " +
						table 	 +
						this.dumpWhereQuery();

		return fetchQuery(query);
	}
	
	
	private String getWhereStatment(String column, 
									String value, 
									DatabaseQueryoperatorEnum operator,
									String logicOperator) {
		
		String logicConnector = (logicOperator != null) 
								? " " + logicOperator + " " 
								: "";
		
		return  logicConnector 	+ 
				column 			+ 
				" " 			+ 
				DatabaseQueryUnit.getOperator(operator) + 
				" " 			+ 
				value 			+ 
				" ";
	}
	
	
	public Database where(String column, String value) {
		
		this.whereQuery += this.getWhereStatment(column, value, DatabaseQueryoperatorEnum.EQUAL, null);
		return this;
	}
	
	public Database where(String column, DatabaseQueryoperatorEnum operator, String value) {
		
		this.whereQuery += this.getWhereStatment(column, value, operator, null);
		return this;		
	}
		
	public Database andWhere(String column, String value) {
		
		this.whereQuery += this.getWhereStatment(column, value, DatabaseQueryoperatorEnum.EQUAL, "AND");
		return this;		
	}
	
	public Database andWhere(String column, DatabaseQueryoperatorEnum operator, String value) {
		
		this.whereQuery += this.getWhereStatment(column, value, operator, "AND");
		return this;		
	}
	
	public Database orWhere(String column, String value) {
		
		this.whereQuery += this.getWhereStatment(column, value, DatabaseQueryoperatorEnum.EQUAL, "OR");
		return this;
	}
	
	public Database orWhere(String column,DatabaseQueryoperatorEnum operator, String value) {
		
		this.whereQuery += this.getWhereStatment(column, value, operator, "OR");
		return this;
	}
	

	private String getWhereQuery() {
		return this.whereQuery;
	}
	
	private String dumpWhereQuery() {

		String tempQuery = this.getWhereQuery();
		this.whereQuery = " WHERE ";
		return tempQuery;
	}
	
	private void executeQuery(String query) {

		try {
			Statement queryManager = this.connection.createStatement();
			
			System.out.println("@@@@ QUERY Monitor @@@");
			System.out.println(query);
			System.out.println("@@@@ ============= @@@");
			
			queryManager.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private ResultSet fetchQuery(String query) {
		
		try {
			Statement queryManager = this.connection.createStatement();
			System.out.println("@@@@ QUERY Monitor @@@");
			System.out.println(query);
			System.out.println("@@@@ ============= @@@");
			
			return queryManager.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
