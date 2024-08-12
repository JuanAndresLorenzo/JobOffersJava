<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTPaquete"%>
<%@ page import="logica.dts.DTPaqueteTipoPublicacion"%>
<%@ page import="logica.dts.DTUsuario"%>

<% DTPaquete dt_objeto = (DTPaquete) request.getAttribute("dt_objeto"); %> 

<%
DTUsuario dt_usuario = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
%>


<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="${request.getContextPath()}/layout/head.jsp" />
</head>

<body class="d-flex flex-column min-vh-100">

<jsp:include page="${request.getContextPath()}/layout/header.jsp" />

<%= dt_objeto %>



<div class="container px-4 px-lg-5 mt-5">

    <div class="row gx-4 gx-lg-5 justify-content-center mb-5">

        <div class="col-12 row">

            <h3 class="text-center">Compra de paquete</h3>

            <form id="form_compra_paquete" method="post" action="#">
            
                <div class="col-md-12 row p-3">

                <div class="col-md-9 p-3">
                    <label for="paquete" class="form-label">Paquete</label>
                    <input type="text" class="form-control form-control-lg" id="nombre" value="Basico" disabled>
                </div>
                <div class="col-md-3 p-3">
                    <label for="fecha_alta" class="form-label">Fecha de alta</label>
                    <input type="text" class="form-control form-control-lg" id="fecha_alta" value="16/08/2023" disabled>
                </div>
                </div>

                <div class="col-md-12 row p-3">

                <div class="col-md-6 p-3">
                    <label for="tipo_publicacion" class="form-label">Tipo de publicación</label>
                    <select id="tipo_publicacion" class="form-select form-select-lg">
                        <option value="" selected>Ver tipos de publicación</option>
                        <option value="tipoPublicacion1" disabled>Premium: 1</option>
                        <option value="tipoPublicacion2" disabled>Destacada: 1</option>
                        <option value="tipoPublicacion3" disabled>Estándar: 1</option>
                    </select>
                </div>

                <div class="col-md-6 p-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control form-control-lg" id="nombre" value="Paquete 1" disabled>
                </div>

                </div>

                <div class="col-md-12 row p-3">


                <div class="col-md-4 p-3">
                    <label for="validez_dias" class="form-label">Validez días</label>
                    <input type="text" class="form-control form-control-lg" id="validez_dias" value="30" disabled>
                </div>

                <div class="col-md-4 p-3">
                    <label for="descuento" class="form-label">Descuento %</label>
                    <input type="text" class="form-control form-control-lg" id="descuento" value="20" disabled>
                </div>

                    <div class="col-md-4 p-3">
                        <label for="costo" class="form-label">Costo $</label>
                        <input type="text" class="form-control form-control-lg" id="costo" value="240" disabled>
                    </div>

                </div>


                <div class="col-md-12 p-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" rows="5" id="descripcion" disabled>Publica ofertas laborales en nuestra plataforma porun período de 30 días.</textarea>
                </div>

                <div class="col-12 text-center p-3">
                    <a type="button" class="btn btn-danger btn-cancelar" onclick="window.history.back();">
                        <i class="fa fa-ban"></i>
                        Cancelar
                    </a>
                    <button type="submit" class="btn btn-success btn-guardar btn-comprar">
                        <i class="fa fa-dollar-sign"></i>
                        Comprar
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>


<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
