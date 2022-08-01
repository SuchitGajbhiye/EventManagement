package com.event.EventManagement.service;

public interface LoginService {

	String authorizeUser(String username, String password);

	String fetchUserFirstName(String username);

}
