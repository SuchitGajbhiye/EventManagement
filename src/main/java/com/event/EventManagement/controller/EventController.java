package com.event.EventManagement.controller;

import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;
import com.event.EventManagement.service.EventService;

@RestController
public class EventController {
	private static final Logger LOG = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	EventService eventService;
	
	@GetMapping("/testApi")
	public String test() {
		return "Welcome to event application";
	}
	
	@GetMapping("/getEvents")
	public String getEvents(ModelMap model) throws SQLException{
		LOG.info("Inside getEvents");	
		model.put("eventList", eventService.getEvents());		
		return "event";// Return jsp page name
	}
	
	/* This method is used to register new user */
	
	@PostMapping("/registerUser")
	public String registerUser(@RequestBody RegisterModel userModel) {
		LOG.info("Entering into registering user");	
		boolean isExistingUser = eventService.isUserExist(userModel);
		if(!isExistingUser) {
			eventService.registerUser();
		}		
		return "Success";
	}
	
	
	/* This method is used to create new event in the system */
	
	@PostMapping("/createEvent")
	public String creteEvent(ModelMap map,@RequestBody EventModel model) {
		LOG.info("Entering into createEvent method");	
		try {
		if(model!=null) {
			eventService.createEvent(model);
		}		
			map.put("event", eventService.getEvents());
		} catch (SQLException e) {			
			e.printStackTrace();
		}		
		return "event";
		
	}

}
