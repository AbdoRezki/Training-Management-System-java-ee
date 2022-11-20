<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file="header.jsp" %>
<section class="content">
            <div class="row" style="width:100%">
                    <div>
                        <div class="card">
                        <div class="card-header">
                            <h3 class="card-title text-center">Formation - Formateur</h3>

                            <div class="card-tools">
                            <div class="input-group input-group-sm" style="width: 200px; float:right;">
                                <div class="input-group-append">
                                <a class="btn btn-primary" value="Add Formateur" onclick="window.location.href='addformationformateur.jsp'; return false;" role="button">Ajouter Formation - Formateur</a>
                                </div>
                            </div>
                            </div>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body table-responsive p-0">
                            <table class="table table-head-fixed text-nowrap text-center">
                            <thead>
                                <tr>
                                <th>Id</th>
                                <th>Id Formation</th>
                                <th>Cin Formateur</th>
                                <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="tempFormationFormateur" items="${FORMATIONFORMATEUR_LIST}">
					
					<!-- set up a link for each student-->
					<c:url var="updateLink" value="">
						<c:param name="com" value="LOAD" />
						<c:param name="id" value="${tempFormationFormateur.id}" />
					</c:url> 

					<!--  set up a link to delete a student-->
					<c:url var="deleteLink" value="FormationFormateurControllerServlet">
						<c:param name="com" value="DELETE" />
						<c:param name="id" value="${tempFormationFormateur.id}" />
					</c:url> 
                                <tr>
                                <td>${tempFormationFormateur.id}</td>
                                <td>${tempFormationFormateur.idF}</td>
                                <td>${tempFormationFormateur.cin}</td>
                                
                                <td><a class="btn btn-primary" role="button" href="${updateLink}">Update</a> 
							 
							<a class="btn btn-danger" role="button"  href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this FormationFormateur?'))) return false">
							Delete</a>	</td>
                                 </tr>
                                </c:forEach>
                            </tbody>
                            </table>
                        </div>
                        <!-- /.card-body -->
                        </div>
                        <!-- /.card -->
                    </div>
                </div>
            </section>
</body>
</html>