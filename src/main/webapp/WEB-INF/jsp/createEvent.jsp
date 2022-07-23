<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
			<form method="post">
				<fieldset class="form-group">
					<label>Event Name</label> 
					<input type="text" name="eventName" required="required" class="form-control">
				</fieldset>

				<fieldset class="form-group">
					<label>Event Venue</label> <input type="text"
						class="form-control" required="required" name="Eventdescription">
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
						name="eventDate" required="required">
				</fieldset>
				
				<button  type="submit" class="btn btn-primary">Submit</button>
				<%-- <a href="<%=request.getContextPath()%>/createNewEvent"
					class="btn btn-success">Save</a> --%>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>
