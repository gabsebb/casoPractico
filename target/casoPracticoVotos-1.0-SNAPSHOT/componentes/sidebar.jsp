<%-- 
    Document   : sidebar
    Created on : 25 mar 2025, 14:18:35
    Author     : Gabse
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        
    </nav>
</div>

