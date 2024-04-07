<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/buttonCSS.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  <%@ include file="/WEB-INF/views/common/header.jsp" %>
  <!-- Left side column. contains the logo and sidebar -->
  <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Informations véhicule
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
                  <th>Marque</th>
                  <th>Modèle</th>
                  <th>Nombre de places</th>
                </tr>
                <tr>
                  <td>${vehicle.constructeur}</td>
                  <td>${vehicle.modele}</td>
                  <td>${vehicle.nb_places}</td>
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
                  <c:when test="${empty resasVehicleView}">
                    <tr>
                      <td colspan="3">Aucune réservation n'a été effectuée par ce client pour le moment.</td>
                    </tr>
                  </c:when>
                  <c:otherwise>
                    <tr>
                      <th>Client</th>
                      <th>A partir du</th>
                      <th>Jusqu'au</th>
                    </tr>
                    <c:forEach items="${resasVehicleView}" var="resa">
                      <tr>
                        <td>${resa.client.prenom} ${resa.client.nom}</td>
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