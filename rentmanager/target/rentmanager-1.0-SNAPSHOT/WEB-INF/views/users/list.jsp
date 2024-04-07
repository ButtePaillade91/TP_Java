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
                Utilisateurs
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">Ajouter</a>
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
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Email</th>
                                    <th>Action</th>
                                </tr>
                                <c:forEach items="${lesClients}" var="client">
                                    <c:url var="deleteUserUrl" value="/users/delete">
                                        <c:param name="action" value="someAction" />
                                        <c:param name="clientId" value="${client.id}" />
                                    </c:url>
                                    <tr>
                                        <td>${client.id}</td>
                                        <td>${client.nom}</td>
                                        <td>${client.prenom}</td>
                                        <td>${client.email}</td>
                                        <td>
                                            <div class="btn-group-horizontal">
                                                <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">
                                                    <i class="fa fa-play"></i>
                                                </a>
                                                <form id="updateForm" action="${pageContext.request.contextPath}/users/set" method="post">
                                                    <!-- Autres champs du formulaire -->
                                                    <input type="hidden" id="clientId" name="clientId" value="${client.id}">
                                                    <a class="btn btn-success" href="${pageContext.request.contextPath}/users/set?clientId=${client.id}" onclick="submitUpdateForm(${client.id})">
                                                        <i class="fa fa-edit"></i>
                                                    </a>
                                                </form>
                                                <form id="deleteForm${client.id}" action="${deleteUserUrl}" method="post" style="display: none;">
                                                </form>
                                                <a class="btn btn-danger" href="users/delete" onclick="document.getElementById('deleteForm${client.id}').submit(); return false;">
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
        function submitUpdateForm(clientId) {
            document.getElementById('clientId').value = clientId;
            document.getElementById('updateForm').submit();
        }
    </script>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>