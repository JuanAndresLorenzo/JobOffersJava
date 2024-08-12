<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="logica.dts.DTEmpresa"%>

<% DTEmpresa[] dts_objetos = (DTEmpresa[]) request.getAttribute("dts_objetos"); %>

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

				<!-- Sección de listado de empresas -->
				<div class="col col-12">
					<div id="listado_empresas">

						<div class="mb-5">
							<h1>Empresas</h1>
						</div>
						
							<% if (dts_objetos == null) { %>

							No hay empresas registradas.

						<% }else{ %>

							<% for(int i = 0; i < dts_objetos.length; i+=1) { %>														
	
	
							<div class="row p-3">
								<div class="col col-3">
										
										<% if (dts_objetos[i].getImagen() != null && !dts_objetos[i].getImagen().isEmpty()) { %>
							
											<img class="img-fluid" title="Imagen <%= dts_objetos[i].getNickname() %>"
												src="<%= request.getContextPath() %>/assets/images/usuarios/empresa/<%= dts_objetos[i].getImagen() %>">
												
											<% }else{ %>
											
											<img class="img-fluid" title="Imagen <%= dts_objetos[i].getNickname() %>"
												src="<%= request.getContextPath() %>/assets/images/default_image.png">
										
										<% } %>
						
								</div>
								<div class="col col-9">
									<h3><%= dts_objetos[i].getNombreEmpresa() %></h3>
									<div class="item-info">
										<p>
											<span class="fw-bold">Nickname: </span><%= dts_objetos[i].getNickname() %>
										</p>
										<p>
											<span class="fw-bold">Nombre: </span><%= dts_objetos[i].getNombre() %>
										</p>
										<p>
											<span class="fw-bold">Apellido: </span><%= dts_objetos[i].getApellido() %>
										</p>
										<p>
											<span class="fw-bold">Email: </span><%= dts_objetos[i].getCorreo() %>
										</p>
										<div class="item-acciones pt-3">
											<a type="button" class="btn btn-sm btn-primary btn-ver"
												href="usuarios?nickname=<%= dts_objetos[i].getNickname() %>">
												<i class="fa fa-eye"></i> Ver
											</a>
										</div>

									</div>
								</div>
							</div>
							<hr>
							<% } %>
						
						<% } %>
						

					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>