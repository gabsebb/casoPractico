<%-- 
    Document   : i_provincia
    Created on : 26 mar 2025, 02:12:55
    Author     : Gabse
--%>
<%@page import="java.util.Arrays"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/head.jsp" %>
        <style>
            .chart-container {
                width: 60%;
                height: 400px;
                margin: 20px auto;
            }
            @media (max-width: 768px) {
                .chart-container {
                    width: 90%;
                    height: 300px;
                }
            }
        </style>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="componentes/navbar.jsp" %>
        <div id="layoutSidenav">
            <%@include file="componentes/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Reporte por Provincia</h1>

                        <!-- Selector de Provincia -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <form action="SvReportes_Provincia" method="GET" class="row">
                                    <div class="col-md-6">
                                        <select name="provincia" class="form-select">
                                            <option value="">Todas las provincias</option>
                                            <option value="Azuay" <%= "Azuay".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Azuay</option>
                                            <option value="Bolívar" <%= "Bolívar".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Bolívar</option>
                                            <option value="Cañar" <%= "Cañar".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Cañar</option>
                                            <option value="Carchi" <%= "Carchi".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Carchi</option>
                                            <option value="Chimborazo" <%= "Chimborazo".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Chimborazo</option>
                                            <option value="Cotopaxi" <%= "Cotopaxi".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Cotopaxi</option>
                                            <option value="El Oro" <%= "El Oro".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>El Oro</option>
                                            <option value="Esmeraldas" <%= "Esmeraldas".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Esmeraldas</option>
                                            <option value="Galápagos" <%= "Galápagos".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Galápagos</option>
                                            <option value="Guayas" <%= "Guayas".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Guayas</option>
                                            <option value="Imbabura" <%= "Imbabura".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Imbabura</option>
                                            <option value="Loja" <%= "Loja".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Loja</option>
                                            <option value="Los Ríos" <%= "Los Ríos".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Los Ríos</option>
                                            <option value="Manabí" <%= "Manabí".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Manabí</option>
                                            <option value="Morona Santiago" <%= "Morona Santiago".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Morona Santiago</option>
                                            <option value="Napo" <%= "Napo".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Napo</option>
                                            <option value="Orellana" <%= "Orellana".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Orellana</option>
                                            <option value="Pastaza" <%= "Pastaza".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Pastaza</option>
                                            <option value="Pichincha" <%= "Pichincha".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Pichincha</option>
                                            <option value="Santa Elena" <%= "Santa Elena".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Santa Elena</option>
                                            <option value="Santo Domingo" <%= "Santo Domingo".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Santo Domingo</option>
                                            <option value="Sucumbíos" <%= "Sucumbíos".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Sucumbíos</option>
                                            <option value="Tungurahua" <%= "Tungurahua".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Tungurahua</option>
                                            <option value="Zamora Chinchipe" <%= "Zamora Chinchipe".equals(request.getAttribute("provinciaSeleccionada")) ? "selected" : ""%>>Zamora Chinchipe</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <button type="submit" class="btn btn-primary">Filtrar</button>
                                        <a href="estadisticas-generales" class="btn btn-secondary">Ver General</a>
                                    </div>
                                </form>
                            </div>
                        </div>

                        <!-- Gráfico -->
                        <div class="card">
                            <div class="card-header">
                                <%= request.getAttribute("provinciaSeleccionada") == null
                                        ? "Todas las provincias"
                                        : "Provincia: " + request.getAttribute("provinciaSeleccionada")%>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="graficoProvincia"></canvas>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="componentes/footer.jsp" %>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            const datos = {
            labels: [
            <% if (request.getAttribute("datosGrafico") != null) {
                    List<Object[]> datosG = (List<Object[]>) request.getAttribute("datosGrafico");
                    for (Object[] dato : datosG) {%>
            "<%= dato[0]%>",
            <% }
                } %>
            ],
                    datasets: [{
                    data: [
            <% if (request.getAttribute("datosGrafico") != null) {
                    List<Object[]> datosG = (List<Object[]>) request.getAttribute("datosGrafico");
                    for (Object[] dato : datosG) {%>
            <%= dato[1]%>,
            <% }
                }%>
                    ],
                            backgroundColor: [
                                    '#36A2EB', '#FF6384', '#FFCE56', '#4BC0C0', '#9966FF',
                                    '#FF9F40', '#8AC24A', '#607D8B', '#E91E63', '#9C27B0'
                            ],
                            borderWidth: 1
                    }]
            };
            // Crear el gráfico
            document.addEventListener('DOMContentLoaded', () => {
            new Chart(document.getElementById('graficoProvincia'), {
            type: 'pie',
                    data: datos,
                    options: {
                    responsive: true,
                            maintainAspectRatio: false,
                            plugins: {
                            legend: {
                            position: 'right',
                                    labels: {
                                    boxWidth: 12,
                                            font: { size: 12 }
                                    }
                            },
                                    tooltip: {
                                    callbacks: {
                                    label: function(context) {
                                    const label = context.label || '';
                                    const value = context.raw || 0;
                                    const total = context.dataset.data.reduce((a, b) => a + b, 0);
                                    const percentage = Math.round((value / total) * 100);
                                    return `${label}: ${value} votos (${percentage}%)`;
                                    }
                                    }
                                    }
                            }
                    }
            });
            });
        </script>
    </body>
</html>
