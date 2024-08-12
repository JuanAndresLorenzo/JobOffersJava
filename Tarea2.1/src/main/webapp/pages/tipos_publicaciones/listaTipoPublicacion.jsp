<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ page import="logica.dts.DTTipoPublicacion" %>
<% DTTipoPublicacion[] dts_objetos = (DTTipoPublicacion[]) request.getAttribute("dts_tipo_publicacion");%>

<!DOCTYPE html>
<html lang="en">
<head>

    <jsp:include page="${request.getContextPath()}/layout/head.jsp"/>


<body class="d-flex flex-column min-vh-100">
<jsp:include page="${request.getContextPath()}/layout/header.jsp"/>
<div class="container px-4 px-lg-5 mt-5">

    <div class="row gx-4 gx-lg-5 justify-content-center mb-5">

        <div class="col-12 row">

            <!-- Sección de listado de Tipo Publicacion -->
            <div class="col col-12">

                <div class="mt-5" id="listado_tipo_publicacion">

                    <div class="mb-5">
                        <h1>Tipos de publicaciones</h1>
                    </div>

                    <% if (dts_objetos == null) { %>

                    No hay tipos de publicaciones registradas.

                    <% } else { %>


                        <% for (int i = 0; i < dts_objetos.length; i += 1) { %>

                        <div class="row p-3">
                            <div class="col col-12">
                                <h3><%= dts_objetos[i].getNombre()%>
                                </h3>
                                <div class="item-info">
                                    <span class="text-muted float-end">Fecha de alta: <%= dts_objetos[i].getFechaAltaFormat() %></span>
                                    <span class="badge bg-primary">Duración: <%= dts_objetos[i].getDuracion_dias() %> días</span>
                                    <span class="badge bg-info">Exposición: <%= dts_objetos[i].getExposicion() %></span>
                                    <span class="badge bg-success">Costo: <%= dts_objetos[i].getCosto() %></span>
                                </div>
                                <div class="item-descripcion pt-3">
                                    <span>
                                        <%= dts_objetos[i].getDescripcion() %>
                                    </span>
                                </div>
                                <div class="item-acciones pt-3">
                                    <a type="button" class="btn btn-sm btn-info text-white btn-ver"
                                       href="tipos_publicaciones?nombre=<%= dts_objetos[i].getKey() %>">
                                        <i class="fa fa-eye"></i>
                                        Ver
                                    </a>
                                </div>

                            </div>
                        </div>
                        <hr>
                        <% } %>

                    <% } %>


                </div>

            </div>
            <!-- Fin Sección de listado de tipo publicacion -->


        </div>
    </div>
</div>


<jsp:include page="${request.getContextPath()}/layout/footer.jsp"/>
</body>
</html>