<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/style.css">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<h2 class="text-center">Modifier une formation</h2>
<form class="text-center" action="FormationControllerServlet" method="GET" style="width:40%;margin-left:30%;margin-top:3%;padding-bottom:6.55%" >
<input type="hidden" name="command" value="UPDATE" />
<div class="form-group row">
  		<label for="staticEmail" class="col-sm-2 col-form-label">Id</label>
  		<div class="col-sm-10">
    		<input type="text" readonly class="form-control" id="staticEmail" name="fid" value="${FORMATION.id}">
  		</div>
	</div>
  		<div class="form-group row" style="margin-top:2%">
  		<label for="staticEmail" class="col-sm-2 col-form-label">Theme</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="theme" value="${FORMATION.theme}">
  		</div>
	</div>
	<!--  --><div class="form-group row" style="margin-top:2%">
  		<label for="staticEmail" class="col-sm-2 col-form-label">idlieu</label>
  		<div class="col-sm-10">
    		<input type="text" class="form-control" id="staticEmail" name="idlieu" value="${FORMATION.idlieu}">
  		</div>
	</div>
  		<input type="submit" value="submit" class="btn btn-primary" style="margin-top:2%"><br><br>
	</form>

</body>
</html>