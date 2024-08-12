<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="logica.dts.DTUsuario"%>

<% DTUsuario[] dts_objetos = (DTUsuario[]) request.getAttribute("dts_objetos"); %>

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
							<h1>Empresas y Postulantes</h1>
						</div>

						<% for(int i = 0; i < dts_objetos.length; i+=1) { %>


						<div class="row p-3">
							<div class="col col-3">
								<img class="img-fluid" title="Imágen empresa1"
									src="https://i.pinimg.com/originals/14/8b/db/148bdbafbb2acf7c02b4ac507e4adacb.jpg">
							</div>
							<div class="col col-9">
								<h3><%= dts_objetos[i].getNombreCompleto() %></h3>
								<div class="item-info">
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
											href="verUsuario.jsp?nickname=<%= dts_objetos[i].getNickname() %>">
											<i class="fa fa-eye"></i> Ver
										</a>
									</div>
								</div>
							</div>
						</div>
						<hr>
						<% } %>

					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>