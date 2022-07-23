package com.event.EventManagement.eventServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.event.EventManagement.dbConnection.ConnectionDB;
import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;
import com.event.EventManagement.service.EventService;

@Service("eventService")
public class EventServiceImp implements EventService{
	private static final Logger LOG = LoggerFactory.getLogger(EventServiceImp.class);
	
	Connection connection;

	@Override
	public List<EventModel> getEvents() throws SQLException {
		System.out.println("Object created");
		LOG.info("This is info level log");
		String query = "select * from events";
		connection = new ConnectionDB().getNewConnection();
		
		List<EventModel> eventList = new ArrayList<>();		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				EventModel event = new EventModel();
				event.setEventId(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventName"));
				event.setNoOfStudents(rs.getString("number_of_students"));				
				eventList.add(event);				  
	         }
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			connection.close();
		}
		return eventList;
	}
	
	@Override
	public boolean isUserExist(RegisterModel userModel) {
		String email = null;
		String query = "select emailId from users where emailId = '"+userModel.getEmailId()+"'";
		Statement stmt;
		boolean userExist = false;
		
		try {
			connection = new ConnectionDB().getNewConnection();
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			 email = rs.getString("emailId");
			
			if(email==null || email.isEmpty()) {
				userExist = false;
			}else if(email.equalsIgnoreCase(userModel.getEmailId())) {
				userExist = true;
			}
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		return userExist;
	}
	
	@Override
	public void createEvent(EventModel model) {
		connection = new ConnectionDB().getNewConnection();

		try {

			String query = "INSERT INTO EVENTS(EVENTID,EVENTNAME,EVENTDATE,EVENTLOCATION,NUMBER_OF_STUDENTS,CREATED_BY,EVENT_DESCRIPTION) "
					+ "VALUES('" + model.getEventId() + "','" + model.getEventName() + "','" + model.getEventDate()
					+ "','" + model.getEventLocation() + "'," + "'" + model.getNoOfStudents() + "','"
					+ model.getCreatedBy() + "','" + model.getDescription() + "')";
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void createTable() throws SQLException {
		
		connection = new ConnectionDB().getNewConnection();
		
		Statement stmt = connection.createStatement();
        String sql = "CREATE TABLE COMPANY " +
           "(ID INT PRIMARY KEY     NOT NULL," +
           " NAME           TEXT    NOT NULL, " +
           " AGE            INT     NOT NULL, " +
           " ADDRESS        CHAR(50), " +
           " SALARY         REAL)";
        stmt.executeUpdate(sql);
        stmt.close();
        connection.close();
	}
	
	public void insertStatement() throws SQLException
	{
		connection = new ConnectionDB().getNewConnection();
		Statement stmt = connection.createStatement();
        String sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
           + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
           + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
           + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
           + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
        stmt.executeUpdate(sql);

        stmt.close();       
        connection.close();
	}

	@Override
	public void registerUser() {
		// TODO Auto-generated method stub
		
	}

	













	
	

}
