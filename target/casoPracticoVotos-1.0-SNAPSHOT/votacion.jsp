<%-- 
    Document   : votacion
    Created on : 25 mar 2025, 21:20:16
    Author     : Gabse
--%>
<%@page import="logica.PartidoPol"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="componentes/head.jsp" %>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">VOTACION ONLINE</a>
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
            </form>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="login.jsp">Cerrar Sesión</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <main class="mt-5 pt-3">
            <div class="container-fluid px-4">
                <h1 class="mt-4">VOTACION CANDIDATURA PRESIDENCIAL</h1>
                <div class="card mb-4">
                    <div class="card-body mb-6">
                        Selecciona tu preferencia marcando la casilla correspondiente.
                        Podrás modificar tu elección antes de confirmar. 
                        Asegúrate de que tu selección sea correcta antes de pulsar el botón "Confirmar". 
                        Al hacerlo, tu voto quedará registrado de forma segura y anónima. 
                    </div>
                </div>


                <div class="container">
                    <h2>Votación</h2>
                    <%
                        String mensajeExito = (String) session.getAttribute("mensajeExito");

                        if (mensajeExito != null && !mensajeExito.isEmpty()) {
                    %>
                    <div class="alert alert-success">
                        <%= mensajeExito%>
                    </div>
                    <%
                            session.removeAttribute("mensajeExito");
                        }
                    %>

                    <%-- Mensaje de error --%>
                    <%
                        String error = (String) request.getAttribute("error");
                        if (error != null && !error.isEmpty()) {
                    %>
                    <div class="alert alert-danger"><%= error%></div>
                    <%
                        }
                    %>

                    <form action="${pageContext.request.contextPath}/SvVotar" method="POST">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Seleccionar</th>
                                    <th>Partido</th>
                                    <th>Presidente</th>
                                    <th>Vicepresidente</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<PartidoPol> partidos = (List<PartidoPol>) request.getAttribute("partidos");
                                    if (partidos != null) {
                                        for (PartidoPol partido : partidos) {
                                %>
                                <tr>
                                    <td>
                                        <input type="radio" name="idPartido" 
                                               value="<%= partido.getId_partidoPol()%>" required>
                                    </td>
                                    <td><%= partido.getNombre()%></td>
                                    <td><%= partido.getNombre_presidente()%></td>
                                    <td><%= partido.getNombre_vice()%></td>
                                </tr>
                                <%
                                        }
                                    }
                                %>
                            </tbody>
                        </table>
                        <button type="submit" class="btn btn-primary">Emitir Voto</button>
                    </form>
                </div>

                <div class="card mb-4 mt-4">
                    <div class="card-body mb-6">
                        Importante: No compartas pantallas ni credenciales durante el proceso. 
                        Si tienes dudas, solicita ayuda al personal designado.
                    </div>
                </div>
            </div>
        </main>
        <footer class="py-4 bg-light mt-auto">
            <div class="container-fluid px-4">
                <div class="d-flex align-items-center justify-content-between small">
                    <div class="text-muted">Copyright &copy; Gabriel Salazar 2025</div>
                    <div>
                        <a href="#">ITQ</a>
                        &middot;
                        <a href="#">Desarrollo &amp; Software</a>
                    </div>
                </div>
            </div>
        </footer>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>
