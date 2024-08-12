<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulante"%>
<%@ page import="logica.dts.DTEmpresa"%>

<% String tipo_usuario = request.getParameter("tipo_usuario"); %>	

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
    	<script src="assets/js/registrarUsuario.js"></script>
    <!-- Fin Nuestros scripts -->

</head>

<body class="d-flex flex-column min-vh-100">

	<jsp:include page="${request.getContextPath()}/layout/header.jsp" />

	<div class="container px-4 px-lg-5 mt-5">
	

		<div class="row gx-4 gx-lg-5 justify-content-center mb-5">


			<div class="col-12 row">

				<h3 class="text-center">Registrar de Usuario</h3>

				<form id="form_registrar_usuario" method="post" action="#" enctype="multipart/form-data">

					<div class="col-md-12 p-3">

						<label for="tipo_usuario" class="form-label">Tipo de
							usuario</label> <select id="tipo_usuario" name="tipo_usuario"
							class="form-select form-select-lg">

							<option value="" selected>Seleccionar un tipo de usuario</option>

							<option value="empresa" <% if (tipo_usuario != null && tipo_usuario.equals("empresa")) { %>selected<% } %>>Empresa</option>

							<option value="postulante" <% if (tipo_usuario != null && tipo_usuario.equals("postulante")) { %>selected<% } %>>Postulante</option>

						</select>

					</div>

					<div class="col-md-12 row p-3">

						<div class="mb-3">

							<label for="imagen" class="form-label">Imagen de perfil</label>

							<input class="form-control" type="file" id="imagen" name="imagen" value="${ param.imagen }">

						</div>

					</div>

					<div class="row">

						<div class="col-6 ">

							<label for="nombre" class="form-label">Nombre</label> <input
								type="text" class="form-control form-control-lg" id="nombre"
								name="nombre" value="${ param.nombre }">

						</div>

						<div class="col-6 ">

							<label for="apellido" class="form-label">Apellido</label> <input
								type="text" class="form-control form-control-lg" id="apellido"
								name="apellido" value=${ param.apellido }>

						</div>

					</div>

					<div id="div_campos_empresa" class="col-md-12 row">


						<div class="col-md-6 p-3">

							<label for="nombre_empresa" class="form-label">Nombre de
								empresa</label> <input type="text" class="form-control form-control-lg"
								id="nombre_empresa" name="nombre_empresa" value="${ param.nombre_empresa }">

						</div>

						<div class="col-md-6 p-3">

							<label for="sitio_web" class="form-label">Sitio web</label> <input
								type="url" class="form-control form-control-lg" id="sitio_web"
								name="sitio_web" value="${ param.sitio_web }">

						</div>


						<div class="col-md-12 p-3">

							<label for="descripcion" class="form-label">Descripción</label>

							<textarea class="form-control" rows="5" id="descripcion"
								name="descripcion">${ param.descripcion }</textarea>

						</div>

					</div>


					<div id="div_campos_postulante" class="col-md-12 row p-3">


						<div class="col-md-6 p-3">

							<label for="fecha_nacimiento" class="form-label">Fecha de
								nacimiento</label> <input type="date"
								class="form-control form-control-lg" id="fecha_nacimiento"
								name="fecha_nacimiento" value="${ param.fecha_nacimiento }">

						</div>

						<div class="col-md-6 p-3">

							<label for="nacionalidad" class="form-label">Nacionalidad</label>

							<input type="text" class="form-control form-control-lg"
								id="nacionalidad" name="nacionalidad" value="${ param.nacionalidad }">

						</div>

					</div>


					<div class="mb-3">

						<label for="nickname" class="form-label">Nickname</label> <input
							type="text" class="form-control form-control-lg" id="nickname"
							name="nickname" value="${ param.nickname }">

					</div>


					<div class="mb-3">

						<label for="correo" class="form-label">Correo</label> <input
							type="email" class="form-control form-control-lg" id="correo"
							name="correo" value="${ param.correo }">

					</div>


					<div class="row mb-3 ">

						<div class="col-md-6">

							<label for="clave" class="form-label">Clave:</label> <input
								type="password" class="form-control form-control-lg" id="clave"
								name="clave">

						</div>

						<div class="col-md-6">

							<label for="clave1" class="form-label">Confimación clave:</label>

							<input type="password" class="form-control form-control-lg"
								id="clave1" name="clave1">

						</div>


					</div>


					<div class="col-12 text-center p-3">

						<a type="button" class="btn btn-danger btn-cancelar"
							onclick="window.history.back();"> 
							<i class="fa fa-ban"></i>
							Cancelar
						</a>

						<button type="submit" class="btn btn-primary btn-guardar">
							<i class="fa fa-user"></i>
							Registrarse
						</button>

					</div>

				</form>


			</div>

		</div>

	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
