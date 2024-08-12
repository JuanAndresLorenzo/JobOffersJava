<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTEmpresa"%>
<%@ page import="logica.clases.EnumRol"%>

<%
DTUsuario dt_usuario = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
%>

<!-- Nav principal pública -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">

		<a class="navbar-brand" href="/Tarea2.1/">
			<h1>TRABAJO.UY</h1>
		</a>

		<ul class="navbar-nav mb-2 mb-lg-0">

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#"
				id="navbarDropdownEmpresas" role="button" data-bs-toggle="dropdown"
				aria-expanded="false"> Opciones </a>
				<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
				
					<li>
						<a class="dropdown-item" href="cargar-datos">
						Cargar datos
						</a>
					</li>
				
					<li><a class="dropdown-item" href="paquetes">Paquetes</a></li>
					<li><a class="dropdown-item"
						href="tipos_publicaciones">Tipos de publicaciones</a></li>
					<li>
						<hr class="dropdown-divider">
					</li>
					<li><a class="dropdown-item" href="usuarios?tipo=postulante">Ver
							Postulantes</a></li>
					<li><a class="dropdown-item" href="usuarios?tipo=empresa">Ver
							Empresas</a></li>
							
					<%	if (dt_usuario instanceof DTEmpresa && dt_usuario.getRol() == EnumRol.EMPRESA) { %>
						<li><a class="dropdown-item" href="svAltaOfertaLaboral">Alta Oferta Laboral</a></li>
				  	<%}%>
				  	
				</ul>
			</li>

		</ul>

		<div class="search col-6">
			<form class="search-box" action="#">
				<input id="input_buscar" class="form-control-lg" type="text"
					placeholder=" Ofertas laborales, Empresas " name="search">
				<button class="btn btn-lg">
					<i class="fa fa-search" style="font-size: 30px;"> </i>
				</button>
			</form>
		</div>

		<ul class="navbar-nav mb-2 mb-lg-0">
			<!--            <div class="vr text-white"></div>-->

			<%
			if (dt_usuario != null) {
			%>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-bs-toggle="dropdown" aria-expanded="false"> <i
					class="fa fa-user" style="font-size: 18px;"> </i> <%=dt_usuario.getNickname()%>
			</a>
				<ul class="dropdown-menu dropdown-menu-end"
					aria-labelledby="navbarDropdown">
					<li><a class="dropdown-item"
						href="mi-perfil">Mi perfil</a></li>
					<li>
						<hr class="dropdown-divider">
					</li>
					<li><a class="dropdown-item" href="cerrar-sesion">Cerrar
							sesión</a></li>
				</ul></li>

			<%
			} else {
			%>

			<li class="nav-item"><a class="nav-link" href="login.jsp">Iniciar
					Sesión</a></li>

			<div class="vr text-white"></div>
			<li class="nav-item"><a class="nav-link"
				href="usuarios?accion=registrar">Registrarse</a></li>

			<%
			}
			%>

		</ul>
	</div>
</nav>

<!-- Fin Nav principal pública -->

<!-- Mensajes -->
<jsp:include page="${request.getContextPath()}/layout/mensajes.jsp" />
<!-- Fin Mensajes -->