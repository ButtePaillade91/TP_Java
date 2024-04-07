<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

try {
    List<Reservations> lesResasView = ReservationService.instance.findAll();
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
                Reservations
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/rents/create">Ajouter</a>
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
                                    <th style="width: 10px">#</th>
                                    <th>Voiture</th>
                                    <th>Client</th>
                                    <th>Debut</th>
                                    <th>Fin</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${lesResasView}" var="reservation">
                                    <c:url var="deleteRentUrl" value="/rents/delete">
                                        <c:param name="action" value="someAction" />
                                        <c:param name="rentId" value="${reservation.id}" />
                                    </c:url>
                                    <tr>
                                        <td>${reservation.id}</td>
                                        <td>${reservation.vehicle.constructeur} ${reservation.vehicle.modele}</td>
                                        <td>${reservation.client.prenom} ${reservation.client.nom}</td>
                                        <td>${reservation.debut}</td>
                                        <td>${reservation.fin}</td>
                                        <td>
                                            <form id="deleteForm${reservation.id}" action="${deleteRentUrl}" method="post" style="display: none;">
                                                <!-- Ajoutez d'autres champs de formulaire ici si nécessaire -->
                                            </form>
                                            <a class="btn btn-primary" href="#">
                                                <i class="fa fa-play"></i>
                                            </a>
                                            <a class="btn btn-success" href="#">
                                                <i class="fa fa-edit"></i>
                                            </a>
                                            <a class="btn btn-danger" href="rents/delete" onclick="document.getElementById('deleteForm${reservation.id}').submit(); return false;">
                                                <i class="fa fa-trash"></i>
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
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
