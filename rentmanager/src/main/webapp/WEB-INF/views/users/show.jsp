<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttonCSS.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    try {
    List<Cient> lesClients = ClientService.instance.findAll();
    } catch (ServiceException e) {
    System.out.println(e);
    }

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Informations client
            </h1>
        </section>
        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <tr>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Email</th>
                                    <th>Date de naissance</th>
                                </tr>
                                    <tr>
                                        <td>${client.nom}</td>
                                        <td>${client.prenom}</td>
                                        <td>${client.email}</td>
                                        <td>${client.naissance}</td>
                                    </tr>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <section class="content-header">
            <h2>
                Réservations clients
            </h2>
        </section>
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                                <c:choose>
                                    <c:when test="${empty resasClientView}">
                                        <tr>
                                            <td colspan="3">Aucune reservation n'a ete effectuee par ce client pour le moment.</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr>
                                            <th>Véhicule</th>
                                            <th>A partir du</th>
                                            <th>Jusqu'au</th>
                                        </tr>
                                        <c:forEach items="${resasClientView}" var="resa">
                                            <tr>
                                                <td>${resa.vehicle.constructeur} ${resa.vehicle.modele}</td>
                                                <td>${resa.debut}</td>
                                                <td>${resa.fin}</td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>