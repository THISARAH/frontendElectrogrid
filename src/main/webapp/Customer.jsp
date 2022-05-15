<%@page import="com.CustomerService.customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/Orders.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Details</h1>
				<form id="formOrder" name="formOrder" method="post"
					action="Customers.jsp">
					CustomerName: <input id="CustomerName" name="CustomerName"
						type="text" class="form-control form-control-sm"> <br>
					CustomerAddress: <input id="CustomerAddress" name="CustomerAddress" type="text"
						class="form-control form-control-sm"> <br> 
					PremisesID: <input id="PremisesID" name="PremisesID" type="text"
						class="form-control form-control-sm"> <br> 

						 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidCusIDSave" name="hidCustomerIDSave" value="">

				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divOrdersGrid">
					<%
					Customer Cus = new Customer();
					out.print(Cus.readCustomers());
					%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>