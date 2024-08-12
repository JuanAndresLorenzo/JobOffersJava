<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulante"%>
<%@ page import="logica.dts.DTEmpresa"%>

<% DTUsuario dt_objeto_sin_cast = (DTUsuario) request.getAttribute("dt_objeto"); %>
<% String dt_objeto_tipo = (String) request.getAttribute("dt_objeto_tipo"); %>


<% if(dt_objeto_tipo.equals("postulante")) { %>

<% DTPostulante dt_objeto = (DTPostulante) dt_objeto_sin_cast; %>

<% }else if(dt_objeto_tipo.equals("empresa")) { %>

<% DTEmpresa dt_objeto = (DTEmpresa) dt_objeto_sin_cast; %>

<% }else{ %>

<% DTUsuario dt_objeto = (DTUsuario) dt_objeto_sin_cast; %>

<% } %>


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
				<div class="col col-12 row">

					<div class="col col-3">
						<img class="img-fluid" title="Imágen postulante1"
							src="https://imgv3.fotor.com/images/gallery/a-woman-linkedin-picture-with-grey-background-made-by-LinkedIn-Profile-Picture-Maker.jpg">
					</div>
					<div class="col col-9">
						<div class="mb-5">
							<h1><%= dt_objeto.getNombreCompleto() %></h1>
						</div>
						<p>
							<span class="fw-bold">Nickname: </span><%= dt_objeto.getNickname() %>
						</p>
						<p>
							<span class="fw-bold">Email: </span><%= dt_objeto.getCorreo() %>
						</p>
						<% if(dt_objeto_tipo.equals("postulante")) { %>

						<p>
							<span class="fw-bold">Fecha de nacimiento: </span><%= dt_objeto.getFechaNacimientoFormat() %>
						</p>
						<p>
							<span class="fw-bold">Nacionalidad: </span><%= dt_objeto.getNacionalidad() %>
						</p>

						<% } %>

						<% if(dt_objeto_tipo.equals("empresa")) { %>

						<p>
							<span class="fw-bold">Sitio web: </span><%= dt_objeto.getSitioWeb() %>
						</p>

						<% } %>

					</div>

					<% if(dt_objeto_tipo.equals("empresa")) { %>


					<div class="col col-5">

						<p>
							<span class="fw-bold">Descripción: </span>
						</p>

						<span><%= dt_objeto.getDescripcion() %></span>

					</div>

					<% } %>


				</div>

			</div>
		</div>
	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
