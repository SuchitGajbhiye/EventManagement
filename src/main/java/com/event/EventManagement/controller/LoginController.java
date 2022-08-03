package com.event.EventManagement.controller;

import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.event.EventManagement.dbConnection.ConnectionDB;
import com.event.EventManagement.model.RegisterModel;
import com.event.EventManagement.service.EventService;
import com.event.EventManagement.service.LoginService;


@Controller
@WebServlet("/")
public class LoginController extends HttpServlet {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	EventService eventService;
	
	@Autowired
	LoginService loginService;
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
	@RequestMapping(value = "/logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
		
	}
	
	@RequestMapping(value = "/login", method=RequestMethod.POST)
	public String Register(ModelMap map,@RequestParam String username, @RequestParam String password,@RequestParam String role,HttpServletRequest request){
		
		String count = loginService.authorizeUser(username, password);		
		String firstName = loginService.fetchUserFirstName(username);
		HttpSession session = request.getSession();
		session.setAttribute("user", firstName); 
		session.setAttribute("role", role);
		session.setAttribute("userEmail", username);
		String userEmail = (String) request.getSession().getAttribute("userEmail");
		if(!count.equals(null) && !count.equals("0")){
			map.put("username", username);
			try {
				map.put("list", eventService.getEvents(userEmail));
				
			} catch (SQLException e) {
				LOG.info("Exception occured while authorizing user in LoginController :",e.getMessage());
				e.printStackTrace();
			}
			if(role.equals("admin")){
				return "eventsList";
			}else{
				return "eventsListUser";
			}
			
		}			
		map.put("errorMessage", "Please use correct credentials");
		return "login";
	}
	@RequestMapping(value = "/registerUser", method=RequestMethod.GET)
	public String registerUser(){
		return "register";
		
	}
	@RequestMapping(value = "/register", method=RequestMethod.POST)
	public String register(ModelMap map,RegisterModel model){
		boolean userExist = eventService.isUserExist(model);
		if(!userExist) {
			eventService.registerUser(model);
			map.put("returnMessage", "User Registration successful");
		}else {
			map.put("errorMessage", "U");
		}		
		return "register";
		
	}
	
}

