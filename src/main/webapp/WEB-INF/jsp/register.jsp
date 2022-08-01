<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Event Management</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

</head>
<body>
	<%-- <jsp:include page="../common/header.jsp"></jsp:include> --%>
	<jsp:include page="menu.jsp"></jsp:include>
	<div class="container col-md-5" style="padding-top: 15px;">
		<div class="card">
			<div class="card-body">

	<h2>User Register Form</h2>
			
				<form action="<%=request.getContextPath()%>/register" method="post">

					<div class="form-group">
						<label for="uname">First Name:</label> <input type="text"
							class="form-control" id="firstName" placeholder="First Name"
							name="firstName" required>
					</div>

					<div class="form-group">
						<label for="uname">Last Name:</label> <input type="text"
							class="form-control" id="lastName" placeholder="last Name"
							name="lastName" required>
					</div>

					<div class="form-group">
						<label for="uname">Email ID:</label> <input type="email"
							class="form-control" id="emailId" placeholder="Email ID"
							name="emailId" required>
					</div>
					<div class="form-group">
						<label for="uname">Organization:</label> <input type="text"
							class="form-control" id="organisation" placeholder="Name of College OR Institute"
							name="organisation" required>
					</div>
					<div class="form-group">
						<label for="uname">Designation:</label> <input type="text"
							class="form-control" id="designation" placeholder="Name of College OR Institute"
							name="designation" required>
					</div>
					<div class="form-group">
						<label for="uname">Password:</label> <input type="password"
							class="form-control" id="password" placeholder="Password"
							name="password" required>
					</div>

					<div>
					<button type="submit" class="btn btn-primary">Submit</button>
					
					<span style="color: green">${returnMessage}</span>
					</div>

				</form>
			</div>
		</div>
		</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>