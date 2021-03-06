<%-- 
    Document   : frmpermisos
    Created on : 25-may-2021, 21:34:58
    Author     : Jorge
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"rel="stylesheet">
        <title>Permisos</title>
    </head>
    <body>
        <div class="container">
            <h1>Formulario de Permisos</h1>
            <jsp:include page="menu.jsp">
                <jsp:param name="opcion" value="permisos"/>
            </jsp:include>
            <br>
            <form action="PermisoControlador" method="post">
                <input type="hidden" name="id" value="${permiso.id}">
                <div class="mb-3">
                    <label for="" class="form-label">Id_Usuario</label>
                    <input type="text" class="form-control" name="id_usuario" value="${permiso.id_usuario}" placeholder="Escriba id del usuario">
                    
                </div>
                <div class="mb-3">
                    <label for="" class="form-label">ID_ROL</label>
                    <input type="text" class="form-control" name="id_rol" value="${permiso.id_rol}" placeholder="Escriba id rol">
                </div>
                
                <button type="submit" class="btn btn-primary">Enviar</button>
            </form>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>


    </body>
</html>
