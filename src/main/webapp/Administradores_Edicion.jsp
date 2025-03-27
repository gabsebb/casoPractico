<%-- 
    Document   : Administradores.jsp
    Created on : 26 mar 2025, 15:13:40
    Author     : Gabse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
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
    </body>
</html>
