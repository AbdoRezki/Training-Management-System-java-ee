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
                            <h3 class="card-title text-center">Liste des formations</h3>

                            <div class="card-tools">
                            <div class="input-group input-group-sm" style="width: 200px; float:right;">
                                <div class="input-group-append">
                                <a class="btn btn-primary" value="Add Formation" onclick="window.location.href='addformation.jsp'; return false;" role="button">Ajouter formation</a>
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
                                <th>Theme</th>
                                <th>IdLieu</th>
                                <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="tempFormation" items="${FORMATION_LIST}">
					
					<!-- set up a link for each student-->
					<c:url var="updateLink" value="">
						<c:param name="command" value="LOAD" />
						<c:param name="fid" value="${tempFormation.id}" />
					</c:url> 

					<!--  set up a link to delete a student-->
					<c:url var="deleteLink" value="FormationControllerServlet">
						<c:param name="command" value="DELETE" />
						<c:param name="fid" value="${tempFormation.id}" />
					</c:url> 
                                <tr>
                                <td>${tempFormation.id}</td>
                                <td>${tempFormation.theme}</td>
                                <td>${tempFormation.idlieu}</td>
                                
                                <td><a class="btn btn-primary" role="button" href="${updateLink}">Update</a> 
							 
							<a class="btn btn-danger" role="button"  href="${deleteLink}"
							onclick="if (!(confirm('Are you sure you want to delete this formation?'))) return false">
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