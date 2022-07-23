package com.event.EventManagement.service;

import java.sql.SQLException;
import java.util.List;

import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;

public interface EventService {
	public List<EventModel> getEvents() throws SQLException;

	public boolean isUserExist(RegisterModel userModel);

	public void registerUser();

	public void createEvent(EventModel model);

}
