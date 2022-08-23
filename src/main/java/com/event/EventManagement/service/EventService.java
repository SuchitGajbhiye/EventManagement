package com.event.EventManagement.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;

public interface EventService {
	public List<EventModel> getEvents(String userEmail) throws SQLException;

	public boolean isUserExist(RegisterModel userModel);

	public void registerUser(RegisterModel userModel);

	public String createEvent(EventModel model);

	public EventModel getEventBasedOnId(int id);

	public void deleteEvent(int id);

	public List<EventModel> searchEvents(String searchParameter, String searchText);

	public String registerEvents(int eventId, String noOfStudents, String userEmail);

	public List<EventModel> getPendingApprovalsForAdmin();

	public List<EventModel> getPendingApprovalsForStudent(String emailId);

	public void updateApprovalStatus(String eventId, String eventName, String status);

	public void downloadEventData(HttpServletRequest request, HttpServletResponse response);

	void downloadEventPendingData(HttpServletRequest request, HttpServletResponse response);

}
