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
import javax.websocket.Session;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.event.EventManagement.model.EventModel;
import com.event.EventManagement.service.EventService;


@Controller
@WebServlet("/")
public class LoginController extends HttpServlet {

	@Autowired
	EventService eventService;
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
		
		// check is username exixts and matches with Role
		
		if(username.equals("Siddhesh") && password.equals("Siddhesh")){
			map.put("username", username);
			try {
				map.put("list", eventService.getEvents());
				HttpSession session = request.getSession();
				session.setAttribute("user", username);
				session.setAttribute("role", role);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
}

