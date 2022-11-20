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
                            <h3 class="card-title text-center">Liste des lieux</h3>

                            <div class="card-tools">
                            <div class="input-group input-group-sm" style="width: 200px; float:right;">
                                <div class="input-group-append">
                                <a class="btn btn-primary" value="Add Lieu" onclick="window.location.href='addlieu.jsp'; return false;" role="button">Ajouter lieu</a>
                                </div>
                            </div>
                            </div>
                        </div>
                        <!-- /.card-header -->
                        <div class="card-body table-responsive p-0">
                            <table class="table table-head-fixed text-nowrap text-center">
                            <thead>
                                <tr>
                                <th>ID</th>
                                <th>Adresse</th>
                                <th>Ville</th>
                                <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="tempLieu" items="${LIEU_LIST}">
					
					<!-- set up a link for each student-->
					<c:url var="updateLink" value="">
						<c:param name="comm" value="LOAD" />
						<c:param name="lid" value="${tempLieu.id}" />
					</c:url> 

					<!--  set up a link to delete a student-->
					<c:url var="deleteLink" value="LieuControllerServlet">
						<c:param name="comm" value="DELETE" />
						<c:param name="lid" value="${tempLieu.id}" />
					</c:url> 
                                <tr>
                                <td>${tempLieu.id}</td>
                                <td>${tempLieu.adresse}</td>
                                <td>${tempLieu.ville}</td>
                                
                                <td><a class="btn btn-primary" role="button" href="${updateLink}">Update</a> 
							 
							<a class="btn btn-danger" role="button"  href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this lieu?'))) return false">
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