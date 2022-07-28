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
	   <%-- <div class="alert alert-success center" role="alert">
				<td>${successMessage}</td>
				<!-- <img alt="view" src="eventPlanning.jpg"> -->
			</div> --%>
			
		<div class="container">
		<!-- <img src="/jsp/eventPlanning.jpg" alt="View" style="width:304px;height:228px;"> -->
		<!--  <img src="eventPlanning.jpg" class="img-fluid" border=0 width="100px" height="100px"> -->
			<h3 class="text-center">List of Events</h3><td>${successMessage}</td>
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
		  <div class="w3-modal-content" style="width: 400px;height:120px;position: absolute;top: 30%;letf:20%;background-color:white;border-style: groove;">
			    <div class="w3-container" style="height:auto;">
					<div class="modal-body" style="width: 100%;padding: 20px 0 0;">
						
				        <p style="display: inline-block;margin-bottom: 2%;font-size: 16px;margin-right: 10px;float: left;padding-left: 30px;	">Are you Sure you want to register for this event<span style="display: inline-block;font-weight: bold;font-size: 16px;" class="ng-binding"></span>?</p>
				    <br>
				    <br>
				    </div>
				    <div class="modal-footer" style="margin: 0;border: none;padding: 0 0 2%;float: left;padding-left: 250px;">
				      	<button type="" class="filebutton btn large gray" style="width: 100%;display: inline-block;background-color: gray;font-weight: bold;color: #fff;" data-dismiss="modal" onclick="registerForEvent();">Yes</button>
					 	<button type="button" class="filebutton btn large red" onclick="closePopup()" style="background-color: red;color: #fff;width: 100%;">No</button>
				    </div> 	
			    </div>
		  </div>
	</div>
		</div>
	</div>
	
	<script type="text/javascript">
	function register(eventId) {
		  var txt;
		  //alert(document.getElementById("deletemodalAttach"));
		  document.getElementById("deletemodalAttach").style.display = "block";
		  <%-- if (confirm("Are you sure you want to register for this event ?")) {
		    window.alert("<%=request.getSession().getAttribute("user")%>")
		    window.location="registerForEvent?eventId="+eventId;
		  } else {
			  alert("cancelled")
		  } --%>
		}
	function registerForEvent(eventId) {
		  var txt;
		  window.location="registerForEvent?eventId="+eventId;
		  <%-- if (confirm("Are you sure you want to register for this event ?")) {
		    window.alert("<%=request.getSession().getAttribute("user")%>")
		    window.location="registerForEvent?eventId="+eventId;
		  } else {
			  alert("cancelled")
		  } --%>
		}
	function closePopup() {
		  var txt;
		  document.getElementById("deletemodalAttach").style.display = "none";
		  
		}
	</script>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
