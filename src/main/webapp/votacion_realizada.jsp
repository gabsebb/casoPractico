<%-- 
    Document   : votacion_realizada
    Created on : 26 mar 2025, 01:44:17
    Author     : Gabse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <%@include file="componentes/head.jsp" %>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="#">VOTACION ONLINE</a>
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
                <div class="container">
                    <h2>EJERCISTE TU DERECHO AL VOTO</h2>
                    <div class="card mb-4 mt-4">
                        <div class="card-body mb-6">
                            Ya haz realizado tu voto.
                            <h2>Bien Hecho</h2>
                        </div>
                    </div>
                    <img src="componentes/ryo-yamada-thumbs-up.gif" style="width: 800px; height: 400px;" alt="Bien" >
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
