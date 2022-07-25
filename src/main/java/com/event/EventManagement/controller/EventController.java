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
		if(value!=null){
		try {
			map.put("list", eventService.getEvents());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return "eventsList";
		}else{
			return "login";
		}
	}

	@RequestMapping(value = "/createEvent", method=RequestMethod.POST)
	public String createNewEvent(ModelMap map,EventModel model){
		eventService.createEvent(model);
		try {
			map.put("list", eventService.getEvents());
			map.put("successMessage", "Event Created Successfully");
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

}
