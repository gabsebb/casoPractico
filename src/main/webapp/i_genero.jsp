<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="componentes/head.jsp" %>
        <style>
            .chart-container {
                width: 80%;
                margin: 20px auto;
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
                        <h1 class="mt-4">Reporte por Género</h1>

                        <!-- Selector de Género -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <form action="SvReportes_Genero" method="GET" class="row">
                                    <div class="col-md-6">
                                        <select name="genero" class="form-select">
                                            <option value="">Todos los géneros</option>
                                            <option value="Masculino" <%= "Masculino".equals(request.getAttribute("generoSeleccionado")) ? "selected" : ""%>>Masculino</option>
                                            <option value="Femenino" <%= "Femenino".equals(request.getAttribute("generoSeleccionado")) ? "selected" : ""%>>Femenino</option>
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
                                <%= request.getAttribute("generoSeleccionado") == null
                                        ? "Todos los géneros"
                                        : "Género: " + request.getAttribute("generoSeleccionado")%>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="graficoGenero"></canvas>
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
            // Datos para el gráfico
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
                        backgroundColor: ['#36A2EB', '#FF6384', '#FFCE56'], // Azul, Rosa, Amarillo
                                borderWidth: 1
                    }]
            };

            // Crear el gráfico
            document.addEventListener('DOMContentLoaded', () => {
                const ctx = document.getElementById('graficoGenero');
                ctx.height = 250; // Altura fija para el canvas

                new Chart(ctx, {
                    type: 'pie',
                    data: datos,
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                position: 'bottom', // Mover leyenda abajo
                                labels: {
                                    padding: 15,
                                    boxWidth: 10,
                                    font: {size: 10}
                                }
                            },
                            tooltip: {
                                bodyFont: {size: 12}
                            }
                        },
                        cutout: '60%' // Hacer el gráfico más delgado (tipo dona)
                    }
                });
            });
        </script>
    </body>
</html>