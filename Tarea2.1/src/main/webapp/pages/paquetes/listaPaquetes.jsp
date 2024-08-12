<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="logica.dts.DTPaquete"%>

<% DTPaquete[] dts_objetos = (DTPaquete[]) request.getAttribute("dts_objetos"); %> 

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

				<!-- Sección de listado de paquetes -->
				<div class="col col-12">

					<div class="mt-5" id="listado_paquetes">

						<div class="mb-5">
							<h1>Paquetes</h1>
						</div>
						
						<% if (dts_objetos == null) { %>

							No hay paquetes registrados.

						<% }else{ %>

			 			<% for(int i = 0; i < dts_objetos.length; i+=1) { %>


						<div class="row p-3">
							<div class="col col-3">
							
								<% if (dts_objetos[i].getImg() != null && !dts_objetos[i].getImg().isEmpty()) { %>
							
									<img class="img-fluid" title="Imagen <%= dts_objetos[i].getNombre() %>"
										src="<%= request.getContextPath() %>/assets/images/paquetes/<%= dts_objetos[i].getImg() %>">
										
								<% }else{ %>
									
									<img class="img-fluid" title="Imagen <%= dts_objetos[i].getNombre() %>"
										src="<%= request.getContextPath() %>/assets/images/default_image.png">
								
								<% } %>
									
							</div>
							<div class="col col-9">
								<h3><%= dts_objetos[i].getNombre() %></h3>
								<div class="item-info">
									<span class="badge bg-primary">Validez: <%= dts_objetos[i].getValidezDias() %> días</span>
									<span class="badge bg-success">Descuento: <%= dts_objetos[i].getDescuento() %>%</span>
								</div>
								<div class="item-descripcion pt-3">
									<span><%= dts_objetos[i].getDescripcion() %></span>
								</div>
								<div class="item-acciones pt-3">
									<a type="button" class="btn btn-sm btn-info text-white btn-ver"
										href="paquetes?nombre=<%= dts_objetos[i].getKey() %>"> <i class="fa fa-eye"></i> Ver
									</a>
								</div>

							</div>
						</div>
						<hr>
						<% } %>
						
						<% } %>
						

					</div>

				</div>
				<!-- Fin Sección de listado de paquetes -->
								

			</div>
		</div>
	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>