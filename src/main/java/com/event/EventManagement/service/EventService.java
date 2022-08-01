package com.event.EventManagement.service;

import java.sql.SQLException;
import java.util.List;

import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;

public interface EventService {
	public List<EventModel> getEvents() throws SQLException;

	public boolean isUserExist(RegisterModel userModel);

	public void registerUser(RegisterModel userModel);

	public void createEvent(EventModel model);

	public EventModel getEventBasedOnId(int id);

	public void deleteEvent(int id);

	public List<EventModel> searchEvents(String searchParameter, String searchText);

	public void registerEvents(int eventId, String noOfStudents, String userEmail);

}
