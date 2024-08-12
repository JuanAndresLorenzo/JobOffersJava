<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>

<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTEmpresa"%>
<%@ page import="logica.dts.DTPostulante"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTPaquete"%>
<%@ page import="logica.dts.DTEmpresaPaquete"%>
<%@ page import="logica.dts.DTKeyword"%>


<%
//String tipo_usuario = (String) request.getAttribute("tipo_usuario");
%>

<%
//DTUsuario usuario = (DTUsuario) request.getAttribute("usuario");
%>

<%
DTUsuario dt_usuario_logueado = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
%>

<%
String tipo_usuario = (String) request.getSession().getAttribute("usuario_logueado_tipo");
%>

<form id="form_actualizar_usuario" method="post" action="#" enctype="multipart/form-data">

	<div class="col-md-12 p-3">
		<label for="tipo_usuario" class="form-label">Tipo de usuario</label> <select
			id="tipo_usuario" class="form-select form-select-lg" disabled>
			<option value="postulante" <%if (tipo_usuario == "postulante") {%>
				selected <%}%>>Postulante</option>
			<option value="">Seleccionar un tipo de usuario</option>
			<option value="empresa" <%if (tipo_usuario == "empresa") {%> selected
				<%}%>>Empresa</option>
		</select>
	</div>

	<div class="col-md-12 row p-3">

		<div class="col col-4">

			<% if (dt_usuario_logueado.getImagen() != null && !dt_usuario_logueado.getImagen().isEmpty()) { %>

			<img class="img-fluid"
				title="Imagen <%= dt_usuario_logueado.getNickname() %>"
				src="<%= request.getContextPath() %>/assets/images/usuarios/<%= tipo_usuario %>/<%= dt_usuario_logueado.getImagen() %>">

			<% }else{ %>

			<img class="img-fluid"
				title="Imagen <%= dt_usuario_logueado.getNickname() %>"
				src="<%= request.getContextPath() %>/assets/images/default_image.png">

			<% } %>


			<div class="mb-3">

				<label for="imagen" class="form-label">Imagen de perfil</label> <input
					class="form-control" type="file" id="imagen" name="imagen"
					value="${ param.imagen }">

			</div>



		</div>

		<div class="col col-8">

			<div class="col-md-12 row p-3">

				<div class="col-md-6 p-3">
					<label for="nombre" class="form-label">Nombre</label> <input
						type="text" class="form-control form-control-lg" id="nombre"
						name="nombre" value="<%=dt_usuario_logueado.getNombre()%>">
				</div>
				<div class="col-md-6 p-3">
					<label for="apellido" class="form-label">Apellido</label> <input
						type="text" class="form-control form-control-lg" id="apellido"
						name="apellido" value="<%=dt_usuario_logueado.getApellido()%>">
				</div>

				<%
				if (tipo_usuario == "postulante") {
				%>

				<%
				DTPostulante dt_postulante_usuario_logueado = (DTPostulante) dt_usuario_logueado;
				%>


				<div id="div_campos_postulante" class="col-md-12 row p-3">

					<div class="col-md-6 p-3">
						<label for="fecha_nacimiento" class="form-label">Fecha de
							nacimiento</label> <input type="date"
							class="form-control form-control-lg" id="fecha_nacimiento"
							name="fecha_nacimiento"
							value="<%=dt_postulante_usuario_logueado.getFechaNacimientoFormatPicker()%>">
					</div>
					<div class="col-md-6 p-3">
						<label for="nacionalidad" class="form-label">Nacionalidad</label>
						<input type="text" class="form-control form-control-lg"
							id="nacionalidad" name="nacionalidad"
							value="<%=dt_postulante_usuario_logueado.getNacionalidad()%>">
					</div>
				</div>

				<%
				}
				%>


			</div>

		</div>

	</div>


	<%
	if (tipo_usuario == "empresa") {
	%>

	<%
	DTEmpresa dt_empresa_usuario_logueado = (DTEmpresa) dt_usuario_logueado;
	%>



	<div id="div_campos_empresa" class="col-md-12 row p-3">

		<div class="col-md-6 p-3">
			<label for="nombre_empresa" class="form-label">Nombre de
				empresa</label> <input type="text" class="form-control form-control-lg"
				id="nombre_empresa" name="nombre_empresa"
				value="<%=dt_empresa_usuario_logueado.getNombreEmpresa()%>">
		</div>
		<div class="col-md-6 p-3">
			<label for="sitio_web" class="form-label">Página web</label> <input
				type="url" class="form-control form-control-lg" id="sitio_web"
				name="sitio_web"
				value="<%=dt_empresa_usuario_logueado.getSitioWeb()%>">
		</div>

		<div class="col-md-12 p-3">
			<label for="descripcion" class="form-label">Descripción</label>
			<textarea class="form-control" rows="5" id="descripcion"
				name="descripcion"><%=dt_empresa_usuario_logueado.getDescripcion()%></textarea>
		</div>
	</div>
	<%
	}
	%>


	<div class="col-md-12 row p-3">

		<div class="col-md-4 p-3">
			<label for="nickname" class="form-label">Nickname</label> <input
				type="text" class="form-control form-control-lg" id="nickname"
				name="nickname" disabled
				value="<%=dt_usuario_logueado.getNickname()%>">
			<div class="col-md-12">

				<!--
				<button id="btn_cambiar_clave_usuario" type="button"
					class="btn btn-primary btn-cambiar-clave">
					<i class="fa fa-lock"></i> Cambiar clave
				</button>
				-->

			</div>
		</div>
		<div class="col-md-4 p-3">
			<label for="correo" class="form-label">Correo</label> <input
				type="text" class="form-control form-control-lg" id="correo"
				name="correo" disabled value="<%=dt_usuario_logueado.getCorreo()%>">



		</div>

		<div class="col-md-4 p-3">

			<div class="form-check">
				<input class="form-check-input" type="checkbox" value=""
					id="cambiar_clave_check" name="cambiar_clave_check"> <label
					class="form-check-label" for="cambiar_clave_check"> Deseo
					cambiar la clave </label>
			</div>

			<div id="div_cambiar_clave_usuario" class="col-md-12 p-3">

				<div class="col-md-12">
					<label for="clave" class="form-label">Nueva clave:</label> <input
						type="password" class="form-control form-control-lg" id="clave"
						name="clave">
				</div>
				<div class="col-md-12">
					<label for="clave1" class="form-label">Confimación de nueva
						clave:</label> <input type="password" class="form-control form-control-lg"
						id="clave1" name="clave1">
				</div>

			</div>

		</div>



	</div>

	<div class="col-12 text-center p-3">

		<!-- 
		<a type="button" class="btn btn-danger btn-cancelar"
			onclick="window.history.back();"> <i class="fa fa-ban"></i>
			Cancelar
		</a>
 -->
		<button type="submit" class="btn btn-primary btn-guardar">
			<!-- falta lo de guardar el cambio de informacion  -->
			<i class="fa fa-save"></i> Guardar
		</button>
	</div>
</form>