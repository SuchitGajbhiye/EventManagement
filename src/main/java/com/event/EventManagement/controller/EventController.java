package com.event.EventManagement.controller;

import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.model.RegisterModel;
import com.event.EventManagement.service.EventService;


@Controller
@WebServlet("/")
public class EventController {
	private static final Logger LOG = LoggerFactory.getLogger(EventController.class);
	
	@Autowired
	EventService eventService;
	
	@RequestMapping(value = "/eventsSummary", method=RequestMethod.GET)
	public String getAllEvents(ModelMap map,HttpServletRequest request){
		String value  = (String) request.getSession().getAttribute("user");
		String role  = (String) request.getSession().getAttribute("role");
		if(value!=null){
		try {
			map.put("list", eventService.getEvents());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(role.equals("admin")){
			return "eventsList";
		}else{
			return "eventsListUser";
		}
			
		}else{
			return "login";
		}
	}

	@RequestMapping(value = "/createEvent", method=RequestMethod.POST)
	public String createNewEvent(ModelMap map,EventModel model,HttpServletRequest request){
		eventService.createEvent(model);
		try {
			map.put("list", eventService.getEvents());
			map.put("successMessage", "Event Created Successfully");
			request.getSession().setAttribute("successMessage","Event Created Successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "createEvent";
	}
	@RequestMapping(value = "/new", method=RequestMethod.GET)
	public String showNewForm(ModelMap map,HttpServletRequest request){
		String value  = (String) request.getSession().getAttribute("user");
		if(value!=null){
		return "createEvent";
		}else{
			return "login";
		}
	}
	@RequestMapping(value = "/searchEvent", method=RequestMethod.GET)
	public String searchEvent(ModelMap map){
		/*List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("watch");
		map.put("list", list);*/
		return "searchEvent";
	}
	@RequestMapping(value = "/pendingApprovals", method=RequestMethod.GET)
	public String pendingApproval(ModelMap map){
		/*List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("watch");
		map.put("list", list);*/
		return "pendingApprovals";
	}
	/*@RequestMapping(value = "/new", method=RequestMethod.POST)
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("createEvent.jsp");
		dispatcher.forward(request, response);
	}*/
	@RequestMapping(value = "/edit", method=RequestMethod.GET)
	public String updateEvent(ModelMap map,@RequestParam int id){
		System.out.println("event id to be Edited "+id);
		// Method to be writen for Edit
		//retrive the records for this id and pass it in the model using "eventDetails" name
		map.put("successMessage", "Event Updated Successfully");
		return "createEvent";
	}
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(ModelMap map,@RequestParam int id){
		//write a method to delete the event based on ID
		try {
			map.put("list", eventService.getEvents());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "eventsList";
	}
	@RequestMapping(value = "/registerForEvent", method=RequestMethod.GET)
	public String registerForEvent(ModelMap map,@RequestParam int eventId){
		map.put("successMessage", "You are successfully registered for event");
		try {
			map.put("list", eventService.getEvents());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "eventsListUser";
	}
	@RequestMapping(value = "/searchEventsPage", method=RequestMethod.GET)
	public String searchEventsPage(ModelMap map){

		return "searchEvents";
	}
	@RequestMapping(value = "/searchEvents", method=RequestMethod.GET)
	public String searchEvents(ModelMap map,@RequestParam String searchParameter, @RequestParam String searchText){
		try {
			map.put("list", eventService.getEvents());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "eventsListUser";
	}
	

}
