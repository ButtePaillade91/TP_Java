<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttonCSS.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

try {
    List<Vehicle> lesVehicules = VehicleService.instance.findAll();
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
                Voitures
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/cars/create">Ajouter</a>
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
                                    <th>Marque</th>
                                    <th>Modele</th>
                                    <th>Nombre de places</th>
                                    <!--<th>Propriétaire</th>-->
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${lesVehicules}" var="vehicle">
                                    <c:url var="deleteVehicleUrl" value="/cars/delete">
                                        <c:param name="action" value="someAction" />
                                        <c:param name="vehicleId" value="${vehicle.id}" />
                                    </c:url>
                                    <tr>
                                        <td>${vehicle.id}</td>
                                        <td>${vehicle.constructeur}</td>
                                        <td>${vehicle.modele}</td>
                                        <td>${vehicle.nb_places}</td>
                                        <td>
                                            <div class="btn-group-horizontal">
                                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/cars/show?vehicleId=${vehicle.id}">
                                                    <i class="fa fa-play"></i>
                                                </a>
                                                <form id="updateForm" action="${pageContext.request.contextPath}/cars/set" method="post">
                                                    <!-- Autres champs du formulaire -->
                                                    <input type="hidden" id="vehicleId" name="vehicleId" value="${vehicle.id}">
                                                    <a class="btn btn-success" href="${pageContext.request.contextPath}/cars/set?vehicleId=${vehicle.id}" onclick="submitUpdateForm(${vehicle.id})">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </form>
                                                <form id="deleteForm${vehicle.id}" action="${deleteVehicleUrl}" method="post" style="display: none;">
                                                </form>
                                                <a class="btn btn-danger" href="cars/delete" onclick="document.getElementById('deleteForm${vehicle.id}').submit(); return false;">
                                                    <i class="fa fa-trash"></i>
                                                </a>
                                            </div>
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

    <script>
        function submitUpdateForm(vehicleId) {
            document.getElementById('vehicleId').value = vehicleId;
            document.getElementById('updateForm').submit();
        }
    </script>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
