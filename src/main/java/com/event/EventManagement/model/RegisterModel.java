package com.event.EventManagement.model;

public class RegisterModel {
	private String firstName;
	private String lastName;
	private String emailId;
	private String organisation;
	private String password;
	private String phoneNumber;
	private String address1;
	private String address2;
	private String city;
	private String country;
	private String designation;
	private String isUserRegistered;
	
	public String getIsUserRegistered() {
		return isUserRegistered;
	}
	public void setIsUserRegistered(String isUserRegistered) {
		this.isUserRegistered = isUserRegistered;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getOrganisation() {
		return organisation;
	}
	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	@Override
	public String toString() {
		return "RegisterModel [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", organisation=" + organisation + ", password=" + password + ", phoneNumber=" + phoneNumber
				+ ", address1=" + address1 + ", address2=" + address2 + ", city=" + city + ", country=" + country
				+ ", designation=" + designation + ", isUserRegistered=" + isUserRegistered + "]";
	}
	

}
