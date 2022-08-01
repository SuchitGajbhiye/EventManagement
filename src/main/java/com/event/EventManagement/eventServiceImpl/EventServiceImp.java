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
		String query = "select * from events order by eventid desc";
		connection = new ConnectionDB().getNewConnection();
		
		List<EventModel> eventList = new ArrayList<>();		
		try {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while(rs.next()){
				EventModel event = new EventModel();
				event.setEventId(rs.getInt("eventid"));
				event.setEventName(rs.getString("eventName"));
				event.setEventLocation(rs.getString("eventLocation"));		
				event.setEventDate(rs.getString("eventDate"));	
				event.setNoOfStudents(rs.getString("NUMBER_OF_STUDENTS"));	
				eventList.add(event);				  
	         }
			stmt.close();
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
		String query = "select emailId from eventusers where emailId = '"+userModel.getEmailId()+"'";
		Statement stmt;
		boolean userExist = false;
		
		try {
			connection = new ConnectionDB().getNewConnection();
			
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				 email = rs.getString("emailId");
			}			
			
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
		int seq =0;
		try {
			String sequence = "select seq_eventId.nextval from dual";
			Statement stmtSeq = connection.createStatement();
			ResultSet rs = stmtSeq.executeQuery(sequence);
			
			while(rs.next()){
				
				seq = (rs.getInt("nextval"));			  
	         }
			String query = "INSERT INTO EVENTS(EVENTID,EVENTNAME,EVENTDATE,EVENTLOCATION,NUMBER_OF_STUDENTS,CREATED_BY,EVENT_DESCRIPTION) "
					+ "VALUES('" + seq + "','" + model.getEventName() + "','" + model.getEventDate()
					+ "','" + model.getEventLocation() + "'," + "'" + model.getNoOfStudents() + "','"
					+ model.getCreatedBy() + "','" + model.getEventdescription() + "')";
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void registerUser(RegisterModel userModel) {
		connection = new ConnectionDB().getNewConnection();
		int seq = 0;
		
		try {
			
			String sequence = "select seq_eventId.nextval from dual";
			Statement stmtSeq = connection.createStatement();
			ResultSet rs = stmtSeq.executeQuery(sequence);
			
			while(rs.next()){
				
				seq = (rs.getInt("nextval"));			  
	         }
			String query = "INSERT INTO EVENTUSERS(USERID,FIRSTNAME,LASTNAME,EMAILID,COMPANY,CITY,COUNTRY,PASSWORD,ADDRESS1,ADDRESS2,ROLE,DESIGNATION)"
					+ "VALUES('"+seq+"','" + userModel.getFirstName() + "','" + userModel.getLastName() + "','"
					+ userModel.getEmailId() + "'," + "'" + userModel.getOrganisation() + "','" + userModel.getCity()
					+ "','" + userModel.getCountry() + "'," + "'" + userModel.getPassword() + "','"
					+ userModel.getAddress1() + "','" + userModel.getAddress2() + "','student','"+userModel.getDesignation()+"')";

			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query);
			stmt.close();
			connection.close();

		} catch (Exception e) {
			LOG.info("Exception occures while registering user:", e.getMessage());
			e.printStackTrace();
		}

	}

	@Override
	public EventModel getEventBasedOnId(int id) {
		connection = new ConnectionDB().getNewConnection();
		String query = "SELECT * FROM EVENTS WHERE EVENTID = '"+id+"'";
		Statement stmt = null;
		EventModel model = new EventModel();
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {				
				model.setEventDate(rs.getString(query));
				model.setEventLocation(query);
				model.setEventName(query);
				model.setNoOfStudents(query);
				model.setEventdescription(query);				
			}
			stmt.close();
			connection.close();
		}catch (Exception e) {
			LOG.info("Exception occred while retriving event in EventServiceImpl",e.getMessage());
		}		
		
		return model;
	}

	@Override
	public void deleteEvent(int id) {
		connection = new ConnectionDB().getNewConnection();
		String query = "DELETE * FROM EVENTS WHERE EVENTID = '"+id+"'";
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			 stmt.executeUpdate(query);			
			stmt.close();
			connection.close();
		
	}catch(Exception e) {
		LOG.info("Exception occred while deleting event in EventServiceImpl");
	}
		}

	@Override
	public List<EventModel> searchEvents(String searchParameter, String searchText) {
		String query = null;
		List<EventModel> eventList = new ArrayList<>();
		connection = new ConnectionDB().getNewConnection();
		try {
			switch (searchParameter) {
			case "eventId":
				query = "SELECT * FROM EVENTS WHERE EVENTID = '"+searchText+"'";
				break;
			case "eventName":
				query = "SELECT * FROM EVENTS WHERE EVENTname = '"+searchText+"'";
				break;
			case "eventLocation":
				query = "SELECT * FROM EVENTS WHERE EVENTname = '"+searchText+"'";
			default:
				break;
			}
			Statement stmt = null;
						
				stmt = connection.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				while(rs.next()){
					EventModel event = new EventModel();
					event.setEventId(rs.getInt("eventid"));
					event.setEventName(rs.getString("eventName"));
					event.setEventLocation(rs.getString("eventLocation"));		
					event.setEventDate(rs.getString("eventDate"));	
					event.setNoOfStudents(rs.getString("NUMBER_OF_STUDENTS"));	
					eventList.add(event);				  
		         }
				stmt.close();
				connection.close();			
			
		}catch(Exception e) {
			LOG.info("Exception occred while search event in EventServiceImpl",e.getMessage());
		}
		return eventList;
	}

	@Override
	public void registerEvents(int eventId, String noOfStudents, String userEmail) {
		try {
			connection = new ConnectionDB().getNewConnection();
		}catch(Exception e) {
			LOG.info("Exception occures while egisterEvent in EvetServiceImpl:"+e.getMessage());
			e.printStackTrace();
		}
		
	}
	
}
