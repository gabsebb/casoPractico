<%-- 
    Document   : Votantes
    Created on : 25 mar 2025, 17:17:05
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
                        <form action="SvVotante" method="POST" class="me-1">
                            <h1 class="mt-4">Votantes</h1>
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
                                                <label for="inputEmail">Cedula</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <div class="form-floating mb-3 mb-md-0">
                                                    <input class="form-control" type="text" name="inputProv" />
                                                    <label for="inputPasswordConfirm">Provincia</label>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control"type="text" name="inputCiu" />
                                                <label for="inputPasswordConfirm">Ciudad</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" type="text" name="inputGen" />
                                                <label for="inputPasswordConfirm">Genero</label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <button type="submit">Crear</button>
                        </form>
                            <div class="card mt-4 mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Votantes Registrados
                                </div>
                                <div class="card-body">
                                    <table id="datatablesSimple">
                                        <thead>
                                            <tr>
                                                <th>Cedula</th>
                                                <th>Nombre</th>                                           
                                                <th>Ciudad</th>
                                                <th>Genero</th>
                                                <th>Usuario</th>                                            
                                                <th>Eliminar</th>

                                            </tr>
                                        </thead>
                                        <tfoot>
                                            <tr>
                                                <th>Cedula</th>
                                                <th>Nombre</th>                                           
                                                <th>Ciudad</th>
                                                <th>Genero</th>
                                                <th>Usuario</th>                                            
                                                <th>Eliminar</th>

                                            </tr>
                                        </tfoot>
                                        <tbody>
                                            <%
                                                List<Map<String, String>> datos = (List<Map<String, String>>) request.getAttribute("datosVotantes");
                                                for (Map<String, String> votante : datos) {
                                            %>
                                            <tr>
                                                <td><%= votante.get("cedula")%></td>
                                                <td><%= votante.get("nombre") + " " + votante.get("apellido")%></td>
                                                <td><%= votante.get("ciudad")%></td>
                                                <td><%= votante.get("genero")%></td>
                                                <td><%= votante.get("usuario")%></td>

                                                <td>
                                                    <div class="d-flex gap-1">
                                                        <form action="SvVotanteEditar" method="GET" class="me-1">
                                                            <button type="submit" class="btn btn-sm btn-primary">
                                                                <i class="fas fa-edit"></i> Editar
                                                            </button>
                                                            <input type="hidden" name="id" value="<%=Integer.parseInt(votante.get("id"))%>">
                                                        </form>
                                                        <button type="button" class="btn btn-sm btn-danger" 
                                                                onclick="confirmarEliminacion('<%=votante.get("id")%>')">
                                                            <i class="fas fa-trash-alt"></i> Eliminar
                                                        </button>
                                                    </div>
                                                </td>
                                            </tr>
                                            <%
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                    </div>
                </main>
                <%@include file="componentes/footer.jsp" %>
            </div>
        </div>
        <script>
            document.querySelector("form").addEventListener("submit", function (e) {
                console.log("Formulario enviado");
            });
            function confirmarEliminacion(idVotante) {
                const modal = new bootstrap.Modal(document.getElementById('confirmModal'));
                const modalTitle = document.getElementById('modalTitle');
                const modalBody = document.getElementById('modalBody');
                const confirmBtn = document.getElementById('confirmDeleteBtn');

                // Configurar modal
                modalTitle.textContent = 'Confirmar Eliminación';
                modalBody.innerHTML = `
        <p>¿Estás seguro que deseas eliminar este votante y su usuario asociado?</p>
        <div class="alert alert-warning">¡Esta acción no se puede deshacer!</div>
    `;

                // Limpiar eventos previos
                confirmBtn.onclick = null;

                // Configurar acción de eliminación
                confirmBtn.onclick = function () {
                    fetch('SvVotanteEliminar', {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/x-www-form-urlencoded',
                        },
                        body: 'id=' + idVotante
                    })
                            .then(response => {
                                if (response.redirected) {
                                    window.location.href = response.url;
                                }
                            })
                            .catch(error => {
                                console.error('Error:', error);
                                modal.hide();
                                alert('Error al eliminar: ' + error.message);
                            });
                };

                modal.show();
            }
        </script>
        <!-- Modal de Confirmación (agrégalo antes del cierre de </body>) -->
        <div class="modal fade" id="confirmModal" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="modalTitle"></h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" id="modalBody"></div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-danger" id="confirmDeleteBtn">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
