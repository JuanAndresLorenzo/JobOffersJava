<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulante"%>
<%@ page import="logica.dts.DTEmpresa"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTPostulanteOfertaLaboral"%>


<% DTPostulante postulante = (DTPostulante) request.getAttribute("dt_postulante");%> 
<% DTOfertaLaboral ofertaLaboral = (DTOfertaLaboral) request.getAttribute("dt_ofertaLaboral");%> 
<% DTPostulanteOfertaLaboral dt_objeto = (DTPostulanteOfertaLaboral) request.getAttribute("dt_objeto"); %>


<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="${request.getContextPath()}/layout/head.jsp" />
   
    <!-- Plugins de terceros -->
    <script src="plugins/jquery/jquery-3.7.1.min.js"></script>
    <script src="plugins/jquery/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
    <script src="plugins/jquery/jquery-validation-1.19.5/dist/localization/messages_es_AR.min.js"></script>
    <!-- Fin Plugins de terceros -->

    <!-- Nuestros scripts -->
	<script src="assets/js/postulacionOferta.js"></script>
    <!-- Fin Nuestros scripts -->

</head>

<body class="d-flex flex-column min-vh-100">

	<jsp:include page="${request.getContextPath()}/layout/header.jsp" />

	<div class="container px-4 px-lg-5 mt-5">

		<div class="row gx-4 gx-lg-5 justify-content-center mb-5">
	
			<div class="col-12 row">
	
				<h3 class="text-center">Postulación a Oferta Laboral</h3>
	
				<form id="form_postulacion" method="post">
	
					<div class="col-md-12 p-3">
						<label for="postulante" class="form-label">Postulante</label>
						<input type="text" class="form-control form-control-lg" disabled value="<%= postulante.getNombre().concat(" "+postulante.getApellido()) %>" >
					</div>
	
					<div class="col-md-12 row p-3">
	
						<div class="col-md-12 p-3">
							<label for="empresa" class="form-label">Empresa</label>
							<input type="text" class="form-control form-control-lg" disabled value="<%= ofertaLaboral.getEmpresa().getNombreEmpresa()%>">
						</div>
	
						<h5>Datos de la empresa seleccionada</h5>
	
						<div class="col-md-12 row p-3">
	
							<div class="col-md-6 p-3">
								<label for="nombre" class="form-label">Nombre</label>
								<input type="text" class="form-control form-control-lg" id="nombre" value="<%= ofertaLaboral.getEmpresa().getNombre()%>"
									   disabled>
							</div>
							<div class="col-md-6 p-3">
								<label for="apellido" class="form-label">Apellido</label>
								<input type="text" class="form-control form-control-lg" id="apellido" value="<%= ofertaLaboral.getEmpresa().getNombre()%>" disabled>
							</div>
						</div>
	
						<div id="div_campos_empresa" class="col-md-12 row p-3">
	
							<div class="col-md-6 p-3">
								<label for="nombre_empresa" class="form-label">Nombre de empresa</label>
								<input type="text" class="form-control form-control-lg" id="nombre_empresa"
									   value="<%= ofertaLaboral.getEmpresa().getNombreEmpresa()%>" disabled>
							</div>
							<div class="col-md-6 p-3">
								<label for="sitio_web" class="form-label">Página web</label>
								<input type="url" class="form-control form-control-lg" id="sitio_web"
									   value="<%= ofertaLaboral.getEmpresa().getSitioWeb()%>" disabled>
							</div>
	
							<div class="col-md-12 p-3">
								<label for="descripcion" class="form-label">Descripción</label>
								<textarea class="form-control" rows="10" id="descripcion" disabled><%= ofertaLaboral.getEmpresa().getDescripcion() %>
								</textarea>
							</div>
						</div>
	
						<div class="col-md-12 row p-3">
	
							<div class="col-md-6 p-3">
								<label for="nickname" class="form-label">Nickname</label>
								<input type="text" class="form-control form-control-lg" id="nickname" disabled
									   value="<%= ofertaLaboral.getEmpresa().getNickname() %>">
							</div>
							<div class="col-md-6 p-3">
								<label for="correo" class="form-label">Correo</label>
								<input type="email" class="form-control form-control-lg" id="correo" disabled
									   value="<%= ofertaLaboral.getEmpresa().getCorreo() %>">
							</div>
						</div>
	
					</div>
	
					<div class="col-md-12 row p-3 ">
	
						<h5>Postulación</h5>
	
	
						<div class="col-md-12 p-3">
							<label for="oferta" class="form-label">Ofertas de la empresa</label>
							<input type="text" class="form-control form-control-lg" disabled value="<%= ofertaLaboral.getNombre()%>">
						</div>
	
						<div class="col-md-12 p-3">
							<label for="fecha" class="form-label">Fecha</label>
							<input type="text" class="form-control form-control-lg" id="todayDate" disabled>
						</div>
						<div class="col-md-12 p-3">
							<label for="cv_reducido" class="form-label">CV reducido</label>
							<textarea class="form-control" rows="5" id="cv_reducido" name="cv_reducido"><% if (dt_objeto!=null){%><%= dt_objeto.getCvReducido() %>${ param.cv_reducido }<%} %></textarea>
						</div>
	
						<div class="col-md-12 p-3">
							<label for="motivacion" class="form-label">Motivación</label>
							<textarea class="form-control" rows="5" id="motivacion" name="motivacion" ><% if (dt_objeto!=null){%><%= dt_objeto.getMotivacion() %>${ param.motivacion }<%} %></textarea>
						</div>
	
						<div class="col-md-12 p-3">
							<label for="adjuntos" class="form-label">Adjuntos</label>
							<input type="file" class="form-control form-control-lg" id="adjuntos" name="adjuntos" value="<% if (dt_objeto!=null){%><%= dt_objeto.getAdjuntos() %>${ param.adjuntos }<%} %>" >   
						</div>
	
					</div>
	
	
					<div class="col-12 text-center p-3">
						<a type="button" class="btn btn-danger btn-cancelar" onclick="window.history.back();">
							<i class="fa fa-ban"></i>
							Cancelar
						</a>
						<button type="submit" class="btn btn-info text-white btn-guardar btn-postularse">
									<i class="fa fa-paper-plane text-white"></i>
									Postularse
						   </button>
					</div>
				</form>
	
			</div>
		</div>
	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>