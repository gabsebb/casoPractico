<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <%@include file="componentes/head.jsp" %>
    <body class="sb-nav-fixed">
        <%@include file="componentes/navbar.jsp" %>
        <div id="layoutSidenav">
            <%@include file="componentes/sidebar.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">REPORTE GENERAL</h1>
                        <div class="card mb-4">
                            <div class="card-body mb-6">
                                En esta pagina se observa un reporte general 
                                del porcentaje de votos de cada partido político
                            </div>
                            <div class="col-lg-12"> <!-- Cambiado a col-lg-12 para más espacio -->
                                <div class="card mb-4">
                                    <div class="card-header">
                                        <i class="fas fa-chart-pie me-1"></i>
                                        Distribución de Votos por Partido
                                    </div>
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-md-6">
                                                <canvas id="graficoVotos" height="300"></canvas>
                                            </div>
                                            <div class="col-md-6">
                                                <div class="table-responsive">
                                                    <table class="table table-bordered table-hover">
                                                        <thead class="table-primary">
                                                            <tr>
                                                                <th>Partido Político</th>
                                                                <th>Votos</th>
                                                                <th>Porcentaje</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <% 
                                                            if(request.getAttribute("votosPorPartido") != null) {
                                                                List<Object[]> votosPorPartido = (List<Object[]>) request.getAttribute("votosPorPartido");
                                                                int totalVotos = votosPorPartido.stream().mapToInt(dato -> ((Long)dato[1]).intValue()).sum();
                                                                
                                                                for(Object[] dato : votosPorPartido) {
                                                                    String partido = (String) dato[0];
                                                                    Long votos = (Long) dato[1];
                                                                    double porcentaje = totalVotos > 0 ? (votos.doubleValue() / totalVotos) * 100 : 0;
                                                            %>
                                                            <tr>
                                                                <td><%= partido %></td>
                                                                <td><%= String.format("%,d", votos) %></td>
                                                                <td><%= String.format("%.1f%%", porcentaje) %></td>
                                                            </tr>
                                                            <% 
                                                                }
                                                            } 
                                                            %>
                                                        </tbody>
                                                        <tfoot>
                                                            <tr class="table-active">
                                                                <th>Total</th>
                                                                <th><%= request.getAttribute("votosPorPartido") != null ? 
                                                                    String.format("%,d", ((List<Object[]>)request.getAttribute("votosPorPartido"))
                                                                    .stream().mapToInt(dato -> ((Long)dato[1]).intValue()).sum()) : "0" %>
                                                                </th>
                                                                <th>100%</th>
                                                            </tr>
                                                        </tfoot>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-footer">
                                        <div class="d-flex justify-content-between">
                                            <div class="text-muted">Actualizado el <%= new java.util.Date() %></div>
                                            <div>
                                                <a href="${pageContext.request.contextPath}/i_genero.jsp" class="btn btn-primary btn-sm">Ver por Género</a>
                                                <a href="${pageContext.request.contextPath}/i_provincia.jsp" class="btn btn-primary btn-sm">Ver por Provincia</a>
                                                <a href="${pageContext.request.contextPath}/i_ciudad.jsp" class="btn btn-primary btn-sm">Ver por Ciudad</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
                <%@include file="componentes/footer.jsp" %>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script>
            // Configuración del gráfico (igual que antes)
            document.addEventListener('DOMContentLoaded', function() {
                const ctx = document.getElementById('graficoVotos').getContext('2d');
                new Chart(ctx, {
                    type: 'pie',
                    data: {
                        labels: [
                            <% if(request.getAttribute("votosPorPartido") != null) {
                                List<Object[]> votosPorPartido = (List<Object[]>) request.getAttribute("votosPorPartido");
                                for(Object[] resultado : votosPorPartido) { %>
                                    "<%= resultado[0] %>",
                                <% }
                            } %>
                        ],
                        datasets: [{
                            data: [
                                <% if(request.getAttribute("votosPorPartido") != null) {
                                    List<Object[]> votosPorPartido = (List<Object[]>) request.getAttribute("votosPorPartido");
                                    for(Object[] resultado : votosPorPartido) { %>
                                        <%= resultado[1] %>,
                                    <% }
                                } %>
                            ],
                            backgroundColor: [
                                '#FF6384', '#36A2EB', '#FFCE56', '#4BC0C0', '#9966FF',
                                '#FF9F40', '#8AC24A', '#607D8B', '#E91E63', '#9C27B0'
                            ],
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        maintainAspectRatio: false,
                        plugins: {
                            legend: {
                                position: 'right',
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