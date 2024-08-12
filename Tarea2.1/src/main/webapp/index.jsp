<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page import="logica.dts.DTKeyword"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTUsuario" %>
<%@ page import="logica.dts.DTPostulante" %>
<%@ page import="logica.clases.EnumRol" %>
<%@ page import="logica.dts.DTPostulanteOfertaLaboral" %>

<% DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas = (DTOfertaLaboral[]) request.getAttribute("dts_ofertas_laborales_vigentes_confirmadas"); %>
<% DTKeyword[] dts_keywords = (DTKeyword[]) request.getAttribute("dts_keywords"); %>
<%
DTUsuario dt_usuario = (DTUsuario) request.getSession().getAttribute("usuario_logueado");

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
				<!-- Sección de listado de keywords -->
				<div class="col col-3">


					<!--                <div class="row p-3 bg-light">-->
					<!--                    <div class="col col-12">-->
					<!--                        <p style="text-align: center;">Filtrar por Keyword:</p>-->
					<!--                    </div>-->
					<!--                </div>-->

					<div id="listado_keywords" class="text-center">

						<ul class="list-group list-group-flush">
							<li class="list-group-item ">Filtrar por keywords</li>


							<% if (dts_keywords == null) { %>

								No hay keywords en el sistema.

							<% }else{ %>

								<% for(int i = 0; i < dts_keywords.length; i+=1) { %>
	
									<% DTKeyword dt_keyword = dts_keywords[i]; %>
		
		
									<li
										class="list-group-item d-flex justify-content-between align-items-center">
										<a href="#">
											<div>
												<span class="badge bg-secondary mx-3"><%= dt_keyword.getNombre() %></span>
												<%--                                <input class="form-check-input me-1" type="checkbox" value="">--%>
											</div>
									</a> <span class="badge bg-secondary rounded-pill">1</span>
									</li>
		
								<% } %>

							<% } %>

						</ul>

					</div>

				</div>
				<!-- Fin Sección de listado de keywords -->

				<!-- Sección de listado de ofertas -->
				<div class="col col-9">
					<div id="listado_ofertas">


						<% if (dts_ofertas_laborales_vigentes_confirmadas == null || dts_ofertas_laborales_vigentes_confirmadas.length == 0) { %>

						No hay ofertas laborales disponibles en el sistema.

						<% }else{ %>

						<% for(int i = 0; i < dts_ofertas_laborales_vigentes_confirmadas.length; i+=1) { %>

						<% DTOfertaLaboral dt_ol = dts_ofertas_laborales_vigentes_confirmadas[i]; %>

						<!-- Oferta laboral index -->

						<div class="row p-3">
							<div class="col col-3">
														
								<% if (dt_ol.getImg() != null && !dt_ol.getImg().isEmpty()) { %>
							
									<img class="img-fluid" title="Imagen <%= dt_ol.getNombre() %>"
										src="<%= request.getContextPath() %>/assets/images/ofertas/<%= dt_ol.getImg() %>">
										
								<% }else{ %>
									
									<img class="img-fluid" title="Imagen <%= dt_ol.getNombre() %>"
										src="<%= request.getContextPath() %>/assets/images/default_image.png">
								
								<% } %>
									
							</div>
							<div class="col col-9">
									<h3><%= dt_ol.getNombre() %></h3>
									<div class="item-info">
										<span class="text-muted float-start">
											<%= dt_ol.getDepartamento() %>,
											<%=  dt_ol.getCiudad() %>										
										</span>
										<span class="text-muted float-end">
											Fecha de alta: <%= dt_ol.getFechaAltaFormat() %>
										</span>
										<br>
										<span class="badge bg-danger">
											<i class="fa fa-building"></i>
											<%= dt_ol.getEmpresa() %>
										</span>
										<span class="badge bg-primary">
											<i class="fa fa-clock"></i>
											Horario: <%= dt_ol.getHorario() %>
										</span>
										<span class="badge bg-success">
											<i class="fa fa-money-bill"></i> 
											Remuneración: <%= dt_ol.getRemuneracion() %>
										</span>
										<span class="badge bg-success"> 
											<i class="fa fa-money-bill"></i>
											Tipo de Publicación: <%= dt_ol.getTipoPublicacion() %>
										</span>
										<br>
										
										<% Map<String, DTKeyword> dts_ol_keywords = dt_ol.getKeywords(); %>
																				
										<% for (String clave:dts_ol_keywords.keySet()) { %>
										
											<% 	DTKeyword dt_keyword = dts_ol_keywords.get(clave); %>
																				
											<span class="badge bg-secondary"><%= dt_keyword.getNombre() %></span>
										
										<% } %>
										
									</div>
									<div class="item-descripcion pt-3">
										<span>
											<%= dt_ol.getDescripcion() %>
										</span>
									</div>
								<div class="item-acciones pt-3">
									<a type="button" class="btn btn-sm btn-primary btn-ver"
										href="ofertas?nombre=<%= dt_ol.getNombre() %>"> <i class="fa fa-eye"></i> Ver
									</a>

									<% if (dt_usuario instanceof DTPostulante && dt_usuario.getRol()==EnumRol.POSTULANTE) {%>
										
									<a type="button" class="btn btn-sm btn-info text-white btn-postularse"
										href="postulacionOF?nombreOL=<%= dt_ol.getNombre() %>"> <i class="fa fa-paper-plane"></i> Postularse
									</a>
									
									<%} %>
								</div>
							</div>
						</div>
						<hr>

						<!-- Fin Oferta laboral index -->
						<% } %>

						<% } %>
					

					</div>
				</div>
			</div>
			<!-- Fin Sección de listado de ofertas -->

		</div>

	</div>

	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />


</body>

</html>