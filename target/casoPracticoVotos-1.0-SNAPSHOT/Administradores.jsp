<%-- 
    Document   : empadronarVotantes
    Created on : 25 mar 2025, 17:17:05
    Author     : Gabse
--%>
<%@page import="logica.Votante"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="componentes/head.jsp" %>
    <body class="sb-nav-fixed">
        <%@include file="componentes/navbar.jsp" %>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            <div class="sb-sidenav-menu-heading">Usuarios</div>
                            <a class="nav-link" href="${pageContext.request.contextPath}/SvVotante">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Votantes
                            </a>
                            <a class="nav-link" href="index.html">
                                <div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>
                                Administradores
                            </a>
                        </div>
                    </div>
                    <div class="sb-sidenav-footer">
                        <div class="small">Sesion Iniciada como:</div>
                        <!-- aqui va el nombre jaja -->
                    </div>
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <% if (request.getAttribute("mensajeError") != null) {%>
                        <div class="alert alert-danger">
                            <%= request.getAttribute("mensajeError")%>
                        </div>
                        <% }%>
                        <%
                            if (session.getAttribute("mensajeExito") != null) {
                        %>
                        <div class="alert alert-success">
                            <%= session.getAttribute("mensajeExito")%>
                            <%
                                session.removeAttribute("mensajeExito");
                            %>
                        </div>
                        <%
                            }
                        %>
                        <h1 class="mt-4">Administradores</h1>
                        <form action="SvVotante" method="POST">
                            <div class="card mb-4">
                                <div class="card-body mb-6">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputFirstName" type="text" name="inputNom"/>
                                                <label for="inputFirstName">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input class="form-control" id="inputLastName" type="text" name="inputApe" />
                                                <label for="inputLastName">Apellido</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputEmail" type="text" name="inputCed" />
                                                <label for="inputEmail">Cargo</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input class="form-control" id="inputPhone" type="text" name="inputProv" />
                                                <label for="inputPhone">Departamento</label>
                                            </div>
                                        </div>
                                    </div>                                   
                                </div>
                                <div class="card-body mb-6">
                                <div class="row mb-3">
                                    <h3 class="mt-4">Usuario Admin</h3>
                                    <div class="col-md-6">
                                        <div class="form-floating mb-3 mb-md-0">
                                            <input class="form-control" id="inputPassword" type="text" name="inputCiu" />
                                            <label for="inputPassword">Usuario</label>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-floating mb-3 mb-md-0">
                                            <input class="form-control" id="inputPasswordConfirm" type="text" name="inputGen" />
                                            <label for="inputPasswordConfirm">Contraseña</label>
                                        </div>
                                    </div>
                                </div>
                                </div>
                            </div>
                            <div class="mt-4 mb-6">
                                <div class="mt-4 mb-6 text-end"> 
                                    <button type="submit" class="btn btn-primary btn-sm">Crear</button> 
                                </div>
                            </div>
                        </form>
                        <div class="card mt-4 mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                Administradores Registrados
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Cedula</th>
                                            <th>Nombre</th>
                                            <th>Provincia</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Cedula</th>
                                            <th>Nombre</th>
                                            <th>Provincia</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="componentes/footer.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
