<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/validationVehicle.css">
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                Modifier les informations d'une voiture
            </h1>
        </section>

        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                                <div class="form-group">
                                    <label for="manufacturer" class="col-sm-2 control-label">Marque</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="manufacturer" name="manufacturer" placeholder="Marque" value="${vehicle.constructeur}">
                                        <span id="marqueError" class="error-message"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="modele" class="col-sm-2 control-label">Modele</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="modele" name="modele" placeholder="Modele" value="${vehicle.modele}">
                                        <span id="modeleError" class="error-message"></span>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="seats" class="col-sm-2 control-label">Nombre de places</label>

                                    <div class="col-sm-10">
                                        <input type="text" class="form-control" id="seats" name="seats" placeholder="Nombre de places" value="${vehicle.nb_places}">
                                        <span id="nbPlacesError" class="error-message"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="box-footer">
                                <button id="adButton" type="submit" class="btn btn-info pull-right">Ajouter</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <script src="${pageContext.request.contextPath}/resources/js/validationVehicle.js"></script>
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>