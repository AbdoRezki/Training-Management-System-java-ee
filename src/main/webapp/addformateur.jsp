<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 class="text-center">Ajouter un formateur</h2>
<form class="text-center" action="FormateurControllerServlet" method="GET" style="width:40%;margin-left:30%;margin-top:3%;padding-bottom:6.55%" >
<input type="hidden" name="commande" value="ADD" />
	<div class="form-group row">
  		<label for="staticEmail" class="col-sm-2 col-form-label">CIN</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="cin" placeholder="Cin">
  		</div>
	</div>
	<div class="form-group row" style="margin-top:2%">
  		<label for="staticEmail" class="col-sm-2 col-form-label">Nom</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="nom" placeholder="Nom">
  		</div>
	</div>
	<div class="form-group row" style="margin-top:2%">
  		<label for="staticEmail" class="col-sm-2 col-form-label">Age</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="age" placeholder="Age">
  		</div>
	</div>
  		<input type="submit" value="submit" class="btn btn-primary" style="margin-top:2%"><br><br>
	</form>
</body>
</html>