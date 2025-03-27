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
        </style>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="componentes/navbar.jsp" %>
        <div id="layoutSidenav">
            <%@include file="componentes/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Reporte por Ciudad</h1>
                        
                        <!-- Selector de Ciudad -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <form action="SvReportes_Ciudad" method="GET" class="row">
                                    <div class="col-md-6">
                                        <select name="ciudad" class="form-select">
                                            <option value="">Todas las ciudades</option>
                                            <option value="Quito" <%= "Quito".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Quito</option>
                                            <option value="Guayaquil" <%= "Guayaquil".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Guayaquil</option>
                                            <option value="Cuenca" <%= "Cuenca".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Cuenca</option>
                                            <option value="Santo Domingo" <%= "Santo Domingo".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Santo Domingo</option>
                                            <option value="Machala" <%= "Machala".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Machala</option>
                                            <option value="Durán" <%= "Durán".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Durán</option>
                                            <option value="Manta" <%= "Manta".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Manta</option>
                                            <option value="Portoviejo" <%= "Portoviejo".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Portoviejo</option>
                                            <option value="Ambato" <%= "Ambato".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Ambato</option>
                                            <option value="Riobamba" <%= "Riobamba".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Riobamba</option>
                                            <option value="Esmeraldas" <%= "Esmeraldas".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Esmeraldas</option>
                                            <option value="Quevedo" <%= "Quevedo".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Quevedo</option>
                                            <option value="Loja" <%= "Loja".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Loja</option>
                                            <option value="Ibarra" <%= "Ibarra".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Ibarra</option>
                                            <option value="Milagro" <%= "Milagro".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Milagro</option>
                                            <option value="Latacunga" <%= "Latacunga".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Latacunga</option>
                                            <option value="Tulcán" <%= "Tulcán".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Tulcán</option>
                                            <option value="Babahoyo" <%= "Babahoyo".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Babahoyo</option>
                                            <option value="Azogues" <%= "Azogues".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Azogues</option>
                                            <option value="Guaranda" <%= "Guaranda".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Guaranda</option>
                                            <option value="Puyo" <%= "Puyo".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Puyo</option>
                                            <option value="Tena" <%= "Tena".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Tena</option>
                                            <option value="Puerto Francisco de Orellana" <%= "Puerto Francisco de Orellana".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Puerto Francisco de Orellana</option>
                                            <option value="Zamora" <%= "Zamora".equals(request.getAttribute("ciudadSeleccionada")) ? "selected" : "" %>>Zamora</option>
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
                                <%= request.getAttribute("ciudadSeleccionada") == null 
                                    ? "Todas las ciudades" 
                                    : "Ciudad: " + request.getAttribute("ciudadSeleccionada") %>
                            </div>
                            <div class="card-body">
                                <div class="chart-container">
                                    <canvas id="graficoCiudad"></canvas>
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
            // Configuración del gráfico (similar al de provincias)
            document.addEventListener('DOMContentLoaded', () => {
                new Chart(document.getElementById('graficoCiudad'), {
                    type: 'pie',
                    data: {
                        labels: [
                            <% if(request.getAttribute("datosGrafico") != null) {
                                List<Object[]> datosG = (List<Object[]>) request.getAttribute("datosGrafico");
                                for(Object[] dato : datosG) { %>
                                    "<%= dato[0] %>",
                                <% }
                            } %>
                        ],
                        datasets: [{
                            data: [
                                <% if(request.getAttribute("datosGrafico") != null) {
                                    List<Object[]> datosG = (List<Object[]>) request.getAttribute("datosGrafico");
                                    for(Object[] dato : datosG) { %>
                                        <%= dato[1] %>,
                                    <% }
                                } %>
                            ],
                            backgroundColor: [
                                '#36A2EB', '#FF6384', '#FFCE56', '#4BC0C0', '#9966FF',
                                '#FF9F40', '#8AC24A', '#607D8B', '#E91E63', '#9C27B0'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        plugins: {
                            legend: { position: 'right' }
                        }
                    }
                });
            });
        </script>
    </body>
</html>