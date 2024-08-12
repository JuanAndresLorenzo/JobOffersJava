<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTKeyword"%>

<% DTOfertaLaboral[] dts_objetos = (DTOfertaLaboral[]) request.getAttribute("dts_objetos"); %> 

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
							<h1>Ofertas laborales</h1>
						</div>
						
						<% if (dts_objetos == null) { %>

							No hay ofertas registradas.

						<% }else{ %>

			 			<% for(int i = 0; i < dts_objetos.length; i+=1) { %>

						<div class="row p-3">
							<div class="col col-3">
							
								<% if (!dts_objetos[i].getNombre().isEmpty()) { %>
							
									<img class="img-fluid" title="Imagen <%= dts_objetos[i].getNombre() %>"
										src="<%= request.getContextPath() %>/assets/images/ofertas/<%= dts_objetos[i].getNombre() %>">
										
								<% }else{ %>
									
									<img class="img-fluid" title="Imagen <%= dts_objetos[i].getNombre() %>"
										src="<%= request.getContextPath() %>/assets/images/default_image.png">
								
								<% } %>
									
							</div>
							<div class="col col-9">
								<h3><%= dts_objetos[i].getNombre() %></h3>
								
														<div class="item-info">
										<span class="text-muted float-start">
											<%= dts_objetos[i].getDepartamento() %>,
											<%=  dts_objetos[i].getCiudad() %>										
										</span>
										<span class="text-muted float-end">
											Fecha de alta: <%= dts_objetos[i].getFechaAltaFormat() %>
										</span>
										<br>
										<span class="badge bg-danger">
											<i class="fa fa-building"></i>
											<%= dts_objetos[i].getEmpresa() %>
										</span>
										<span class="badge bg-primary">
											<i class="fa fa-clock"></i>
											Horario: <%= dts_objetos[i].getHorario() %>
										</span>
										<span class="badge bg-success">
											<i class="fa fa-money-bill"></i> 
											Remuneración: <%= dts_objetos[i].getRemuneracion() %>
										</span>
										<span class="badge bg-success"> 
											<i class="fa fa-money-bill"></i>
											Tipo de Publicación: <%= dts_objetos[i].getTipoPublicacion() %>
										</span>
										<br>
										
										<% Map<String, DTKeyword> dts_keywords = dts_objetos[i].getKeywords(); %>
																				
										<% for (String clave:dts_keywords.keySet()) { %>
										
											<% 	DTKeyword dt_keyword = dts_keywords.get(clave); %>
																				
											<span class="badge bg-secondary"><%= dt_keyword.getNombre() %></span>
										
										<% } %>
										
									</div>
									<div class="item-descripcion pt-3">
										<span>
											<%= dts_objetos[i].getDescripcion() %>
										</span>
									</div>
								
								<div class="item-acciones pt-3">
									<a type="button" class="btn btn-sm btn-info text-white btn-ver"
										href="ofertas?nombre=<%= dts_objetos[i].getKey() %>"> <i class="fa fa-eye"></i> Ver
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