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

<body>
<jsp:include page="menu.jsp"></jsp:include>

	<header>
	
	</header>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->
	   
		<div class="container">
		<!-- <img src="/jsp/eventPlanning.jpg" alt="View" style="width:304px;height:228px;"> -->
		<!--  <img src="eventPlanning.jpg" class="img-fluid" border=0 width="100px" height="100px"> -->
			<h3 class="text-center">List of Events</h3>
			<button onclick="downloadData();" class="btnDownload" style="float: right;"><i class="fa fa-download"></i> Download</button>
			</br>
			</br>
			<table class="table table-striped">
				<thead>
					<tr>
					    <th>Action</th>
						<th>Event ID</th>
						<th>Event Name</th>
						<th>Venue</th>
						<th>Date</th>
						<th>No of Students</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="todo" items="${list}">

						<tr>
						   <td><a href="/edit?id=<c:out value='${todo.eventId}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="/delete?id=<c:out value='${todo.eventId}' />">Delete</a></td>
							<td><c:out value="${todo.eventId}" /></td>
							<td><c:out value="${todo.eventName}" /></td>
							<td><c:out value="${todo.eventLocation}" /></td>
							<td><c:out value="${todo.eventDate}" /></td>
							<td><c:out value="${todo.noOfStudents}" /></td>
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
		</div>
	</div>
	<script type="text/javascript">

	function downloadData() {
		  window.location="downloadEventData";
		}
	
	</script>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
