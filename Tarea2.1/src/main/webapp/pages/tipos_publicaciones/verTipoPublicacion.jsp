<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="logica.dts.DTTipoPublicacion" %>
<% DTTipoPublicacion dt_objeto= (DTTipoPublicacion) request.getAttribute("dt_objeto");%>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="${request.getContextPath()}/layout/head.jsp" />

</head>
<body class="d-flex flex-column min-vh-100">

<jsp:include page="${request.getContextPath()}/layout/header.jsp" />

<div class="container px-4 px-lg-5 mt-5">

    <div class="row gx-4 gx-lg-5 justify-content-center mb-5">

        <div class="col-12 row">

            <!-- Sección de listado de paquetes -->
            <div class="col col-12">

                <h1>Tipo de publicación <%= dt_objeto.getNombre() %></h1>

                <div class="col-md-12 row p-3">

                    <div class="col-md-12 p-3">

                        <p><span class="fw-bold">Nombre: </span><%= dt_objeto.getNombre() %></p>
                        <p><span class="fw-bold">Duración (días): </span><%= dt_objeto.getDuracion_dias() %></p>
                        <p><span class="fw-bold">Exposición: </span><%= dt_objeto.getExposicion() %></p>
                        <p><span class="fw-bold">Fecha de alta: </span><%= dt_objeto.getFechaAltaFormat() %></p>
                        <p><span class="fw-bold">Costo: </span><%= dt_objeto.getCosto() %></p>

                        <p><span
                                class="fw-bold">Descripción: </span> <%= dt_objeto.getDescripcion() %>                              
                        </p>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />
</body>
</html>