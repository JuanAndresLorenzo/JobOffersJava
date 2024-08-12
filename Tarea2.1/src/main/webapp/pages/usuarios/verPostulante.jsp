<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulante"%>
<%@ page import="logica.dts.DTEmpresa"%>

<% DTPostulante dt_objeto = (DTPostulante) request.getAttribute("dt_objeto"); %>

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
					
						<% if (dt_objeto.getImagen() != null && !dt_objeto.getImagen().isEmpty()) { %>
						
						<img class="img-fluid" title="Imagen <%= dt_objeto.getNickname() %>"
							src="<%= request.getContextPath() %>/assets/images/usuarios/postulante/<%= dt_objeto.getImagen() %>">
							
						<% }else{ %>
						
						<img class="img-fluid" title="Imagen <%= dt_objeto.getNickname() %>"
							src="<%= request.getContextPath() %>/assets/images/default_image.png">
						
						<% } %>
					
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
						
						<p>
							<span class="fw-bold">Fecha de nacimiento: </span><%= dt_objeto.getFechaNacimientoFormat() %>
						</p>
						<p>
							<span class="fw-bold">Nacionalidad: </span><%= dt_objeto.getNacionalidad() %>
						</p>
					
					</div>

				</div>

			</div>
		</div>
	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
