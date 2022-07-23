package com.event.EventManagement.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.stereotype.Service;

//@Service("connectionDb")
public class ConnectionDB {
	
	public ConnectionDB() {
		
	}
	
	public Connection getPostgresConnection() {
		Connection connection = null;
	      try {
	         Class.forName("org.postgresql.Driver");
	         connection = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/postgres",
	            "postgres", "Test@123");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      return connection;
	}
	
	public Connection getNewConnection() {
		Connection connection = null;
	      try {
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         connection = DriverManager
	            .getConnection("jdbc:oracle:thin:@dlcqtovdb01.nonprod.avaya.com:1526/CQTDEV",
	            "CSQT3", "schemaOWN2014#");
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	      System.out.println("Opened database successfully");
	      return connection;
	}
}
