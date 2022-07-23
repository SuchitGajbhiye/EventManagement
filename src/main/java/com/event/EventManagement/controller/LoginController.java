package com.event.EventManagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@WebServlet("/")
public class LoginController extends HttpServlet {

	
	/*private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/createNewEvent":
			showNewForm(request, response);
			break;
		default:
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			break;
		}
	}
	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("eventsList.jsp");
		dispatcher.forward(request, response);
	}*/
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String loginPage(){
		return "login";
		
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String Register(ModelMap map,@RequestParam String username, @RequestParam String password){
		if(username.equals("Siddhesh") && password.equals("Siddhesh")){
			map.put("username", username);
			/*List<String> list = new ArrayList<>();
			list.add("Hello");
			list.add("watch");
			map.put("list", list);*/							
			return "eventsList";
		}			
		map.put("errorMessage", "Please use correct credentials");
		return "login";
	}
	@RequestMapping(value = "/new", method=RequestMethod.POST)
	public String createNewEvent(ModelMap map,@RequestParam String eventName, @RequestParam String Eventdescription,@RequestParam String eventDate){
		List<String> list = new ArrayList<>();
		list.add(eventName);
		map.put("list", list);
		return "eventsList";
	}
	@RequestMapping(value = "/new", method=RequestMethod.GET)
	public String showNewForm(ModelMap map){
		/*List<String> list = new ArrayList<>();
		list.add("Hello");
		list.add("watch");
		map.put("list", list);*/
		return "createEvent";
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

