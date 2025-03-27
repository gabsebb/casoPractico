<%-- 
    Document   : Votantes_Edicion
    Created on : 26 mar 2025, 09:44:30
    Author     : Gabse
--%>

<%@page import="java.util.Map"%>
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
                            <a class="nav-link" href="Administradores.jsp">
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
                            Votante votante = (Votante) request.getAttribute("votante");
                        %>
                        <%
                            String error = request.getParameter("error");
                            if (error != null && !error.isEmpty()) {
                                String decodedError = java.net.URLDecoder.decode(error, "UTF-8");
                        %>
                        <div class="alert alert-danger">
                            <%= decodedError%>
                        </div>
                        <%
                            }
                        %>
                        <h1 class="mt-4">Votantes</h1>
                        <form action="SvVotanteEditar" method="POST">
                            <input type="hidden" name="id_votante" value="${votante.id_votante}">
                            <div class="card mb-4">
                                <div class="card-body mb-6">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputFirstName" type="text" name="inputNom" value="<%=votante.getNombre()%>"/>
                                                <label for="inputFirstName">Nombre</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input class="form-control" id="inputLastName" type="text" name="inputApe" value="<%=votante.getApellido()%>"/>
                                                <label for="inputLastName">Apellido</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputEmail" type="text" name="inputCed" value="<%=votante.getCedula()%>"/>
                                                <label for="inputEmail">Cedula</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input class="form-control" id="inputPhone" type="text" name="inputProv" value="<%=votante.getProvinciaVotante()%>"/>
                                                <label for="inputPhone">Provincia</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" id="inputPassword" type="text" name="inputCiu" value="<%=votante.getCiudadVotante()%>" />
                                                <label for="inputPassword">Ciudad</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <select class="form-select" name="inputGen" required>
                                                    <option value="MASCULINO" <%= votante.getGeneroVotante().equals("MASCULINO") ? "selected" : ""%>>Masculino</option>
                                                    <option value="FEMENINO" <%= votante.getGeneroVotante().equals("FEMENINO") ? "selected" : ""%>>Femenino</option>
                                                </select>
                                                <label class="form-label">Género</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="mt-4 mb-6">
                                <div class="mt-4 mb-6 text-end"> 
                                    <button type="submit" class="btn btn-primary btn-sm">Editar</button> 
                                </div>
                            </div>
                        </form>
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
