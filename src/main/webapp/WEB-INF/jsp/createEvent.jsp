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

</head>
<body>
<jsp:include page="menu.jsp"></jsp:include>
<script type="text/javascript">
var name = request.getSession().getAttribute("username");
alert(name);
if(name!=null){
	var session = true;
}else{
	var session=false;
}
</script>
	<div class="container col-md-5" style="padding-top: 15px;">
		<div class="card">
			<div class="card-body">
			<h2>Create Event Form</h2>
			<form method="post" action="createEvent">
				<fieldset class="form-group">
					<label>Event Name</label> 
					<input type="text" name="eventName" required="required" class="form-control" value="<c:out value='${eventDetails.eventName}'">
				</fieldset>

				<fieldset class="form-group">
					<label>Event Venue</label> <input type="text"
						class="form-control" required="required" name="eventLocation" value="<c:out value='${eventDetails.eventLocation}'">
				</fieldset>

				<!-- <fieldset class="form-group">
					<label>Todo Status</label> <select class="form-control"
						name="isDone">
						<option value="false">In Progress</option>
						<option value="true">Complete</option>
					</select>
				</fieldset> -->

				<fieldset class="form-group">
					<label>Event Date</label> <input type="date" class="form-control"
						name="eventDate" required="required" value="<c:out value='${eventDetails.eventDate}'">
				</fieldset>
				
				<fieldset class="form-group">
					<label>No of Max Students allowed</label> <input type="text" pattern="\d*" maxlength="3" class="form-control"
						name="noOfStudents" required="required" value="<c:out value='${eventDetails.noOfStudents}'">
				</fieldset>
				<%if(request.getSession().getAttribute("successMessage")!=null){%>
				<div class="alert alert-success center" role="alert">
				<%} %>
				<button  type="submit" class="btn btn-primary">Submit</button>
				<p>${successMessage}</p>
				</div>
				
				<%-- <a href="<%=request.getContextPath()%>/createNewEvent"
					class="btn btn-success">Save</a> --%>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
