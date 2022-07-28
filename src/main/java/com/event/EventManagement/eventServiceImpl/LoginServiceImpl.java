package com.event.EventManagement.eventServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.event.EventManagement.dbConnection.ConnectionDB;
import com.event.EventManagement.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	private static final Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
	Connection connection;

	@Override
	public String authorizeUser(String username, String password) {
		String query = "SELECT COUNT(*) AS USERCOUNT FROM EVENTUSERS WHERE EMAILID='"+username+"' AND PASSWORD = '"+password+"'";
		String count = null;
		try {
			connection = new ConnectionDB().getNewConnection();
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			 count = rs.getString("USERCOUNT");			
		
		}catch(Exception e) {
			LOG.info("Exception occures while authoriz user:",e.getMessage());
		}
		return count;
		
	}

}
