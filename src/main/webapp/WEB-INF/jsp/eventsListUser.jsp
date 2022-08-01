<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<style type="text/css">
  .popup {
    width:650px;
    height:150px;
    position:absolute;
    top:50%;
    left:50%;
    margin:-50px 0 0 -100px; /* [-(height/2)px 0 0 -(width/2)px] */
    display:none;
  }
</style>
<body>
<jsp:include page="userMenu.jsp"></jsp:include>

	<header>
	
	</header>
	
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
	<!--  <div class="alert alert-success center">
		<div style="background-color: green;width: 100%;"></div>
	</div>  -->
			
		<div class="container" style="background-color:white;">
		<!-- <img src="/jsp/eventPlanning.jpg" alt="View" style="width:304px;height:228px;"> -->
		<!--  <img src="eventPlanning.jpg" class="img-fluid" border=0 width="100px" height="100px"> -->
			<h3 class="text-center">List of Events</h3>
			
			<table class="table table-striped">
			<thead>
			<tr>
				<label for="searchOptions">Choose a Search Criteria:</label>
				<h3 style="font-size: 15px;font-weight: bold;color: green;text-align: right">${successMessage}</h3>
				<th style="width:10px;"><fieldset class="form-group">
				<select name="searchOptions" id="searchOptions">
				  <option value="eventId">Event ID</option>
				  <option value="eventName">Event Name</option>
				  <option value="eventLocation">Event Venue</option>
				</select>
				</fieldset></th>
				<th style="width:10px;"><fieldset class="form-group">
			<input type="text"  id="searchText" name="searchText" placeholder="Search with text" required="required" width="20%">
			</fieldset></th>
			<th><fieldset class="form-group">
			<button  type="button" onclick="searchEvents()" class="filebutton btn large grey" style="background-color: grey;color: #fff;width: 10%;">Search</button>
			</fieldset></th>
			</tr>
			</thead>
			</table>
			<table class="table table-striped">
				<thead>
					<tr>
					    
						<th>Event ID</th>
						<th>Event Name</th>
						<th>Venue</th>
						<th>Date</th>
						<th>No of Students</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="todo" items="${list}">

						<tr>
						 
							<td><c:out value="${todo.eventId}" /></td>
							<td><c:out value="${todo.eventName}" /></td>
							<td><c:out value="${todo.eventLocation}" /></td>
							<td><c:out value="${todo.eventDate}" /></td>
							<td><c:out value="${todo.noOfStudents}" /></td>
							 <%-- <td><a href="/register?id=<c:out value='${todo.eventId}' />">Register</a>
								&nbsp;&nbsp;&nbsp;&nbsp;</td> --%>
							<td><button onclick="register(${todo.eventId})" class="btn btn-success">Register</button></td>
							<%-- <td><a href="edit?id=<c:out value='${todo.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${todo.id}' />">Delete</a></td> --%>

							<!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
          							<button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		<div id="deletemodalAttach" class="w3-modal" style="display: none;padding-left: 300px;">
		  <div class="w3-modal-content" style="width: 450px;height:120px;position: absolute;top: 30%;letf:20%;background-color:white;border-style: groove;">
			    <div class="w3-container" style="height:auto;">
					<div class="modal-body" style="width: 100%;padding: 20px 0 0;">
					<div class="form-group">
						<label for="noOfStudents">Please Enter No of Students*</label> <input type="text" id="noOfStudents"
							placeholder="No of Students"
							name="noOfStudents" required>
					</div>
						
<!-- 				        <p style="display: inline-block;margin-bottom: 2%;font-size: 16px;margin-right: 10px;float: left;padding-left: 30px;	">Are you Sure you want to register for this event<span style="display: inline-block;font-weight: bold;font-size: 16px;" class="ng-binding"></span>?</p>
 -->				   
				    </div>
				    <div class="modal-footer" style="margin: 0;border: none;padding: 0 0 2%;float: left;padding-left: 250px;">
					 	<button type="button" class="filebutton btn large green" onclick="registerForEvent()" style="background-color: green;color: #fff;width: 100%;">Ok</button>
					 	<button type="button" class="filebutton btn large red" onclick="closePopup()" style="background-color: red;color: #fff;width: 100%;">Cancel</button>
				    </div> 	
			    </div>
		  </div>
	</div>
		</div>
	</div>
	
	<script type="text/javascript">
	var eventIdGlobal;
	function register(eventId) {
		eventIdGlobal = eventId;
		  document.getElementById("deletemodalAttach").style.display = "block";
		}
	function registerForEvent() {
		  var noOfStudents = document.getElementById("noOfStudents").value
		  if(document.getElementById("noOfStudents") && document.getElementById("noOfStudents").value)
			  window.location="registerForEvent?eventId="+eventIdGlobal+"&noOfStudents="+noOfStudents;
		 
		  else
			  window.alert("Please Enter No of Students");
		}
	function closePopup() {
		  var txt;
		  document.getElementById("deletemodalAttach").style.display = "none";
		  
		}
	function searchEvents() {
		  var searchText = document.getElementById("searchText").value
		  var searchParameter = document.getElementById("searchOptions").value
		 
		  window.location="searchEvents?searchText="+searchText+"&searchParameter="+searchParameter;
		}
	</script>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
