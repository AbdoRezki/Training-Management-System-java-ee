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
<h2 class="text-center">Ajouter un lieu</h2>
<form class="text-center" action="LieuControllerServlet" method="GET" style="width:40%;margin-left:30%;margin-top:3%;padding-bottom:6.55%" >
<input type="hidden" name="comm" value="ADD" />
	<div class="form-group row">
  		<label for="staticEmail" class="col-sm-2 col-form-label">Adresse</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="adresse" placeholder="Adresse">
  		</div>
	</div>
	<div class="form-group row" style="margin-top:2%">
  		<label for="staticEmail" class="col-sm-2 col-form-label">Ville</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="ville" placeholder="Ville">
  		</div>
	</div>
  		<input type="submit" value="submit" class="btn btn-primary" style="margin-top:2%"><br><br>
	</form>
</body>
</html>