<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedHashMap"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Sistema de Ofertas Laborales - FING - TPROG 2023 - gr45</title>

<!-- Plugins de terceros -->
<link href="plugins/fontawesome-free-5.15.4-web/css/all.min.css"
	rel="stylesheet">
<link href="plugins/bootstrap-5.3.1-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script src="plugins/bootstrap-5.3.1-dist/js/bootstrap.bundle.min.js"></script>
<script src="plugins/jquery/jquery-3.7.1.min.js"></script>
<script
	src="plugins/jquery/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
<script
	src="plugins/jquery/jquery-validation-1.19.5/dist/localization/messages_es_AR.min.js"></script>

<!-- Fin Plugins de terceros -->

<!-- Nuestros estilos -->
<link href="assets/css/navbar.css" rel="stylesheet">
<link href="assets/css/style.css" rel="stylesheet">
<!-- Fin Nuestros estilos -->

<!-- Nuestros scripts -->
<script src="assets/js/navbar.js"></script>
<script src="assets/js/loginUsuario.js"></script>
<!-- Fin Nuestros scripts -->


</head>

<body class="d-flex flex-column min-vh-100 bg-dark">



	<div class="container px-4 px-lg-5 mt-5">

		<div class="row gx-4 gx-lg-5 justify-content-center mb-5">

			<div class="col-5 row text-center p-5">

				<h2 class="text-center text-white">Acceso</h2>

				<!-- Mensajes -->
				<jsp:include page="${request.getContextPath()}/layout/mensajes.jsp" />
				<!-- Fin Mensajes -->

					<!-- Fin Mensajes -->
				<form class="bg-light" id="form_usuario" action="iniciar-sesion"
					method="POST">
					
					<div class="col-md-12 p-3">
						<input type="text" class="form-control form-control-lg"
							id="usuario" name="usuario" placeholder="Usuario" value="">
						<div id="errorUsuarioVacio">Por favor, ingrese un nombre de
							usuario</div>
						<input type="password" class="form-control form-control-lg"
							id="clave" name="clave" placeholder="Clave" value="">

					</div>

					<div class="col-md-12 p-3">

						<button type="submit" id="btn_ingresar"
							class="btn btn-lg btn-primary" name="ingresar">Ingresar
						</button>
						<br> <br> <br> 
						<a type="button" id="link_volver_inicio"
							class="p-3" href="/Tarea2.1/"> Volver al
							inicio </a>
						<a type="button" id="link_registrarse" class="p-3" href="usuarios?accion=registrar">
						Registrarse					
						</a>

					</div>

				</form>

			</div>
		</div>
	</div>


	<footer class="bg-light text-center text-lg-start mt-auto">
		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.2);">
			Â© 2023 Copyright: <a class="text-dark"
				href="https://www.fing.edu.uy">FING - TPROG - GR45</a>
		</div>
		<!-- Copyright -->
	</footer>

</body>

</html>