<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTKeyword"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulanteOfertaLaboral"%>


<%
DTPostulanteOfertaLaboral dt_objeto = (DTPostulanteOfertaLaboral) request.getAttribute("dt_objeto");
%>

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
				
														
					<% if (dt_objeto.getPostulante().getImagen() != null && !dt_objeto.getPostulante().getImagen().isEmpty()) { %>
						
						<img class="img-fluid" title="Imagen <%= dt_objeto.getPostulante().getNickname() %>"
							src="<%= request.getContextPath() %>/assets/images/usuarios/postulante/<%= dt_objeto.getPostulante().getImagen() %>">
							
					<% }else{ %>
					
						<img class="img-fluid" title="Imagen <%= dt_objeto.getPostulante().getNickname() %>"
							src="<%= request.getContextPath() %>/assets/images/default_image.png">
					
					<% } %>

				</div>
				<div class="col col-3">
					<div class="mb-5">
						<h1><%= dt_objeto.getPostulante().getNombreCompleto() %></h1>
					</div>
					<p>
						<span class="fw-bold">Nickname: </span> <a
							href="usuarios?nickname=<%= dt_objeto.getPostulante().getNickname() %>">
							<%= dt_objeto.getPostulante().getNickname() %>
						</a>
					</p>
					<p>
						<span class="fw-bold">Email: </span><%= dt_objeto.getPostulante().getCorreo() %></p>
					<p>
						<span class="fw-bold">Fecha de nacimiento: </span><%= dt_objeto.getPostulante().getFechaNacimientoFormat() %>
					</p>
					<p>
						<span class="fw-bold">Nacionalidad: </span><%= dt_objeto.getPostulante().getNacionalidad() %></p>

				</div>

				<div class="col-md-3 p-3 border border-1">


					<p>
						<span class="fw-bold">Fecha de postulación: </span><%= dt_objeto.getFechaFormat() %></p>

					<p>
						<span class="fw-bold">Oferta: </span><a
							href="ofertas?nombre=<%= dt_objeto.getOfertaLaboral().getNombre() %>"><%= dt_objeto.getOfertaLaboral() %></a>
					</p>
					<p>
						<span class="fw-bold">Empresa: </span><a
							href="usuarios?nickname=<%= dt_objeto.getOfertaLaboral().getEmpresa().getNickname() %>"><%= dt_objeto.getOfertaLaboral().getEmpresa() %></a>
					</p>

					<p>
						<span class="fw-bold">Departamento: </span><%= dt_objeto.getOfertaLaboral().getDepartamento() %></p>
					<p>
						<span class="fw-bold">Ciudad: </span><%= dt_objeto.getOfertaLaboral().getCiudad() %>
					</p>

					<p>
						<span class="fw-bold">Horario: </span><%= dt_objeto.getOfertaLaboral().getHorario() %>
					</p>
					<p>
						<span class="fw-bold">Remuneración: </span>$<%= dt_objeto.getOfertaLaboral().getRemuneracion() %>
					</p>


				</div>

				<div class="col-md-3 border border-1 p-3">

					<h6 class="fw-bold pb-3">Archivos adjuntos</h6>

					<ul class="list-group">
						<li class="list-group-item"><a
							href="../../assets/archivos/adjuntos_postulaciones/pdf_blanco.pdf"
							target="_blank">Archivo1.pdf</a></li>
						<li class="list-group-item"><a
							href="../../assets/archivos/adjuntos_postulaciones/pdf_blanco.pdf"
							target="_blank">Archivo2.pdf</a></li>
						<li class="list-group-item"><a
							href="../../assets/archivos/adjuntos_postulaciones/pdf_blanco.pdf"
							target="_blank">Archivo3.pdf</a></li>
					</ul>

				</div>

				<div class="col-md-12 row p-3">
					<p>
						<span class="fw-bold">Cv reducido: </span>
					</p>

					<span> <%= dt_objeto.getCvReducido() %>
					</span>
				</div>
				<div class="col-md-12 row p-3">
					<p>
						<span class="fw-bold">Motivación: </span>
					</p>

					<span> <%= dt_objeto.getMotivacion() %>
					</span>
				</div>

			</div>
		</div>
	</div>
</div>


<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />


</body>

</html>
