package com.event.EventManagement.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if(value!=null){
		try {
			map.put("list", eventService.getEvents(userEmail));
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
		String message = null;		
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		int id = (int) request.getSession().getAttribute("eventId");
		if(id==0)
		message = eventService.createEvent(model);
		else
			message = eventService.updateEvent(model,id);
		if(message!=null && message.equalsIgnoreCase("Success")) {
			map.put("successMessage", "Event Created Successfully");
			request.getSession().setAttribute("successMessageEvent","Event Created Successfully");
		}		
		else if(message!=null && message.equalsIgnoreCase("Update Success")) {
			map.put("successMessage", "Event updated Successfully");
		}
		else {
			map.put("errorMesage", "Event Already Exist");
			request.getSession().setAttribute("errorMessageEvent","Event Already Exist");
		}
		//map.put("list", eventService.getEvents(userEmail));
		request.getSession().setAttribute("eventId", 0);
		return "createEvent";
	}
	@RequestMapping(value = "/new", method=RequestMethod.GET)
	public String showNewForm(ModelMap map,HttpServletRequest request){
		String value  = (String) request.getSession().getAttribute("user");
		request.getSession().setAttribute("erroMessageEvent", null);
		request.getSession().setAttribute("successMessageEvent", null);
		request.getSession().setAttribute("eventId", 0);
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
		return "pendingApprovals";
	}
	/*@RequestMapping(value = "/new", method=RequestMethod.POST)
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("createEvent.jsp");
		dispatcher.forward(request, response);
	}*/
	@RequestMapping(value = "/edit", method=RequestMethod.GET)
	public String updateEvent(ModelMap map,@RequestParam int id, HttpServletRequest request){
		LOG.info("event id to be Edited "+id);
		EventModel model = eventService.getEventBasedOnId(id);
		request.getSession().setAttribute("eventId", id);
		map.put("eventDetails", model);//retrive the records for this id and pass it in the model using "eventDetails" name		
		//map.put("successMessage", "Event Updated Successfully");
		return "createEvent";
	}
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String delete(ModelMap map,@RequestParam int id,HttpServletRequest request){
		//write a method to delete the event based on ID
		try {
			String userEmail = (String) request.getSession().getAttribute("userEmail");
			eventService.deleteEvent(id);
			map.put("list", eventService.getEvents(userEmail));			
		} catch (SQLException e) {
			LOG.info("Exception occures while edit events in EventController:",e.getMessage());
			e.printStackTrace();
		}
		return "eventsList";
	}
	
	@RequestMapping(value = "/registerForEvent", method=RequestMethod.GET)
	public String registerForEvent(ModelMap map,@RequestParam int eventId,@RequestParam String noOfStudents,HttpServletRequest request){
		//map.put("successMessage", "You are successfully registered for event");
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if(userEmail==null || userEmail.isEmpty()) {
			return "login";
		}else {
			String message = eventService.registerEvents(eventId,noOfStudents,userEmail);
			if(message.contains("Already") || message.contains("Maximum Allowed")) {
				map.put("erroMessage",message );
			}else {
				map.put("successMessage",message );
			}
						
			try {
				map.put("list", eventService.getEvents(userEmail));
			} catch (SQLException e) {
				LOG.info("Exception occures while getting list from registerEvent API:"+e.getMessage());
				e.printStackTrace();
			}
			return "eventsListUser";
		}
		
	}
	@RequestMapping(value = "/searchEventsPage", method=RequestMethod.GET)
	public String searchEventsPage(ModelMap map){

		return "searchEvents";
	}
	
	@RequestMapping(value = "/searchEvents", method=RequestMethod.GET)
	public String searchEvents(ModelMap map,@RequestParam String searchParameter, @RequestParam String searchText ){
		try {
			List<EventModel> model = eventService.searchEvents(searchParameter,searchText);
			if(model.isEmpty())
				map.put("error", "No records found");
			else {
				map.put("list", model);
			}
		} catch (Exception e) {
			LOG.info("Exception occred while search event on EventController:",e.getMessage());			
		}
		return "eventsListUser";
	}
	
	@RequestMapping(value = "/getPendingApprovals", method=RequestMethod.GET)
	public String getPendingApproval(ModelMap map,HttpServletRequest request){
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		String role = (String) request.getSession().getAttribute("role");
		if(role.equalsIgnoreCase("admin")) {
			List<EventModel> list =	eventService.getPendingApprovalsForAdmin();
			map.put("pendingApprovalsAdmin", list);
			return "pendingApprovalsAdmin";
		}else {
			List<EventModel> list =	eventService.getPendingApprovalsForStudent(userEmail);
			map.put("pendingApprovalsUser", list);
			return "pendingApprovalsUser";
		}		
		
	}
	
	@RequestMapping(value = "/updateApproval", method=RequestMethod.GET)
	public String updateApproval(ModelMap map,HttpServletRequest request, String eventId,String eventName,String action){
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		String role = (String) request.getSession().getAttribute("role");
		if(userEmail==null || userEmail.isEmpty()) {
			return "login";
		}else {
			eventService.updateApprovalStatus(eventId,eventName,action);
			List<EventModel> list =	eventService.getPendingApprovalsForAdmin();
			map.put("pendingApprovalsAdmin", list);
			return "pendingApprovalsAdmin";
		}	
	
	}
	
	@RequestMapping(value = "/downloadEventData", method=RequestMethod.GET)
	public String downlaodEventData(ModelMap map,HttpServletRequest request, HttpServletResponse response){
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		String role = (String) request.getSession().getAttribute("role");
		if(userEmail==null || userEmail.isEmpty()) {
			return "login";
		}else {
			eventService.downloadEventData(request,response);	
			return "pendingApprovalsAdmin";
		}	
	
	}
	
	@RequestMapping(value = "/downloadPendingApprovalData", method=RequestMethod.GET)
	public String downlaodEventPendingData(ModelMap map,HttpServletRequest request, HttpServletResponse response){
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		String role = (String) request.getSession().getAttribute("role");
		if(userEmail==null || userEmail.isEmpty()) {
			return "login";
		}else {
			eventService.downloadEventPendingData(request, response);
			return "pendingApprovalsAdmin";
		}	
	
	}
	
}
