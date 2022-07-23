<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Event Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="col-md-6 col-md-offset-3" style="overflow: auto">
		<h1 style="text-align: left;font-size: 20px;">Login Form</h1>
		<%-- <form action="<%=request.getContextPath()%>/login" method="post"> --%>
		<form method="post">
			<div class="form-group" style="width:50%">
				<label for="uname">User Name:</label> <input type="text"
					class="form-control" id="username" placeholder="User Name"
					name="username" required>
			</div>
			
			<div class="form-group" style="width:50%">
				<label for="uname">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" required>
			</div>

		<div>
		<button type="submit" class="btn btn-primary">Submit</button>
		<span style="color: red">${errorMessage}</span>
		</div>
			
		</form>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>