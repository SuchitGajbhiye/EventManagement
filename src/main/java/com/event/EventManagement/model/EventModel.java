package com.event.EventManagement.model;

public class EventModel {
	private Integer eventId;
	private String eventName;
	private String eventDate;
	private String eventLocation;
	private String noOfStudents;
	private String createdBy;
	private String updatedBy;
	private String eventDescription;
	private String isUserRegistered;
	private String approvalStatus;
	private String emailId;
	private String organization;
	
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getApprovalStatus() {
		return approvalStatus;
	}
	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
	public String getIsUserRegistered() {
		return isUserRegistered;
	}
	public void setIsUserRegistered(String isUserRegistered) {
		this.isUserRegistered = isUserRegistered;
	}
	
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventDate() {
		return eventDate;
	}
	public void setEventDate(String eventDate) {
		this.eventDate = eventDate;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(String noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getEventdescription() {
		return eventDescription;
	}
	public void setEventdescription(String eventdescription) {
		eventDescription = eventdescription;
	}
	@Override
	public String toString() {
		return "EventModel [eventId=" + eventId + ", eventName=" + eventName + ", eventDate=" + eventDate
				+ ", eventLocation=" + eventLocation + ", noOfStudents=" + noOfStudents + ", createdBy=" + createdBy
				+ ", updatedBy=" + updatedBy + ", eventDescription=" + eventDescription + ", isUserRegistered="
				+ isUserRegistered + ", approvalStatus=" + approvalStatus + ", emailId=" + emailId + "]";
	}
	
	
	
	

}
