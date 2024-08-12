<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTKeyword"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulanteOfertaLaboral"%>


<%

DTOfertaLaboral dt_objeto = (DTOfertaLaboral) request.getAttribute("dt_objeto");
DTUsuario dt_usuario_logueado = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
String tipo_usuario = (String) request.getSession().getAttribute("usuario_logueado_tipo");

DTPostulanteOfertaLaboral[] dts_postulaciones_oferta_laboral_de_empresa_logueada = (DTPostulanteOfertaLaboral[]) request.getAttribute("dts_postulaciones_oferta_laboral_de_empresa_logueada");
DTPostulanteOfertaLaboral dt_postulacion_oferta_laboral_de_postulante_logueado = (DTPostulanteOfertaLaboral) request.getAttribute("dt_postulacion_oferta_laboral_de_postulante_logueado");


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

				<!-- Sección de listado de paquetes -->
				<div class="col col-12">

					<h1><%= dt_objeto.getNombre() %></h1>

					<div class="col-md-12 row p-3">

						<div class="col col-3">
						
							<% if (dt_objeto.getImg() != null && !dt_objeto.getImg().isEmpty()) { %>
						
								<img class="img-fluid" title="Imagen <%= dt_objeto.getNombre() %>"
									src="<%= request.getContextPath() %>/assets/images/ofertas/<%= dt_objeto.getImg() %>">
								
							<% }else{ %>
							
								<img class="img-fluid" title="Imagen <%= dt_objeto.getNombre() %>"
									src="<%= request.getContextPath() %>/assets/images/default_image.png">
							
							<% } %>
							
								
								
						</div>


						<div class="col-md-3 p-3">

							<p>
								<span class="fw-bold">Nombre: </span><%= dt_objeto.getNombre() %>
							</p>
							<p>
								<span class="fw-bold">Empresa: </span><a
									href="usuarios?nickname=<%= dt_objeto.getEmpresa().getNickname() %>"><%= dt_objeto.getEmpresa() %></a>
							</p>
							<p>
								<span class="fw-bold">Fecha de alta: </span><%= dt_objeto.getFechaAltaFormat() %>
							</p>

							<% Map<String, DTKeyword> dts_keywords = dt_objeto.getKeywords(); %>

							<p>
								<span class="fw-bold">Keywords: </span>
							</p>


							<% for (String clave:dts_keywords.keySet()) { %>

							<% 	DTKeyword dt_keyword = dts_keywords.get(clave); %>

							<span class="badge bg-secondary"><%= dt_keyword.getNombre() %></span>

							<% } %>

							<p>
								<span class="fw-bold">Departamento: </span><%= dt_objeto.getDepartamento() %>
							</p>
							<p>
								<span class="fw-bold">Ciudad: </span><%= dt_objeto.getCiudad() %>
							</p>
							<p>
								<span class="fw-bold">Tipo de Publicacion:</span> <a
									href="tipos_publicaciones?nombre=<%= dt_objeto.getTipoPublicacion().getNombre() %>">
									<%= dt_objeto.getTipoPublicacion() %>
								</a>
							</p>
							<p>
								<span class="fw-bold">Horario: </span><%= dt_objeto.getHorario() %>
							</p>
							<p>
								<span class="fw-bold">Remuneración: </span>$<%= dt_objeto.getRemuneracion() %>

							</p>
							<!--                        <p><span class="fw-bold">Paquete:</span> Paq1</p>-->

						</div>

						<div class="col-md-6 p-3">


							<p>
								<span class="fw-bold">Descripción: </span><%= dt_objeto.getDescripcion() %>
							</p>

						</div>

					</div>


				</div>
				
				<!-- Postulaciones para empresa dueña de la oferta -->


				<% if (tipo_usuario=="empresa" && dts_postulaciones_oferta_laboral_de_empresa_logueada != null && dts_postulaciones_oferta_laboral_de_empresa_logueada.length == 0) { %>

				No hay postulaciones

				<% }else if (tipo_usuario=="empresa" && dts_postulaciones_oferta_laboral_de_empresa_logueada != null && dts_postulaciones_oferta_laboral_de_empresa_logueada.length != 0) { %>


				<nav id="nav_contenido">

					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<button class="nav-link" id="nav-postulaciones-tab"
							data-bs-toggle="tab" data-bs-target="#nav-postulaciones"
							type="button" role="tab" aria-controls="nav-postulaciones"
							aria-selected="false">Postulaciones</button>

					</div>

					<div class="tab-content" id="nav-tabContent">

						<div class="tab-pane fade show active p-5" id="nav-postulaciones"
							role="tabpanel" aria-labelledby="nav-postulaciones-tab">

							<table class="table">
								<thead>
									<tr>
										<th scope="col">Postulación</th>
										<th scope="col">Fecha</th>
									</tr>
								</thead>
								<tbody>


									<% for(int i = 0; i < dts_postulaciones_oferta_laboral_de_empresa_logueada.length; i+=1) { %>

									<% DTPostulanteOfertaLaboral dt_postulacion_ol = dts_postulaciones_oferta_laboral_de_empresa_logueada[i]; %>

									<tr>
										<td>
											<div class="row p-3">
												<div class="col col-3">
												
													<% if (dt_postulacion_ol.getPostulante().getImagen() != null && !dt_postulacion_ol.getPostulante().getImagen().isEmpty()) { %>
														
														<img class="img-fluid" title="Imagen <%= dt_postulacion_ol.getPostulante().getNickname() %>"
															src="<%= request.getContextPath() %>/assets/images/usuarios/postulante/<%= dt_postulacion_ol.getPostulante().getImagen() %>">
															
													<% }else{ %>
													
														<img class="img-fluid" title="Imagen <%= dt_postulacion_ol.getPostulante().getNickname() %>"
															src="<%= request.getContextPath() %>/assets/images/default_image.png">
													
													<% } %>
																				
													
												</div>
												<div class="col col-9">
													<h3><%= dt_postulacion_ol.getPostulante().getNombreCompleto() %></h3>
													<div class="item-info">
														<p>
															<span class="fw-bold">Nickname: </span><%= dt_postulacion_ol.getPostulante().getNickname() %>
														</p>
														<p>
															<span class="fw-bold">Email: </span><%= dt_postulacion_ol.getPostulante().getCorreo() %>
														</p>
														<div class="item-descripcion pt-3">
															<span> <span class="fw-bold">CV reducido: </span>
																<%= dt_postulacion_ol.getCvReducido() %>
															</span>

														</div>
														<div class="item-descripcion pt-3">

															<span> <span class="fw-bold">Motivación: </span> <%= dt_postulacion_ol.getMotivacion() %>
															</span>
														</div>
														<div class="item-acciones pt-3">
															<a type="button"
																class="btn btn-sm btn-info text-white btn-ver"
																href="postulaciones?oferta=<%= dt_postulacion_ol.getOfertaLaboral().getNombre()%>&postulante=<%= dt_postulacion_ol.getPostulante().getNickname()%>">
																<i class="fa fa-eye"></i> Ver postulación
															</a>
														</div>
													</div>
												</div>
											</div>
										</td>
										<td><%= dt_postulacion_ol.getFechaFormat() %></td>
									</tr>
									<% } %>


								</tbody>
							</table>


						</div>

					</div>

				</nav>

				<% } %>
				
				<!-- Fin Postulaciones para empresa dueña de la oferta -->
				
				
				<!-- Postulaciones para postulante que se postulo a la oferta -->
				
				<% if (tipo_usuario=="postulante" && dt_postulacion_oferta_laboral_de_postulante_logueado == null) { %>

				<p>No tiene postulaciones</p>
				
					<a type="button" class="btn btn-sm btn-info text-white btn-postularse"
										href="postulacionOF?nombreOL=<%= dt_objeto.getNombre() %>"> <i class="fa fa-paper-plane"></i> Postularse
									</a>
				

				<% }else if (tipo_usuario=="postulante" && dt_postulacion_oferta_laboral_de_postulante_logueado != null) { %>


				<nav id="nav_contenido">

					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<button class="nav-link" id="nav-postulaciones-tab"
							data-bs-toggle="tab" data-bs-target="#nav-postulaciones"
							type="button" role="tab" aria-controls="nav-postulaciones"
							aria-selected="false">Mi Postulación</button>

					</div>

					<div class="tab-content" id="nav-tabContent">

						<div class="tab-pane fade show active p-5" id="nav-postulaciones"
							role="tabpanel" aria-labelledby="nav-postulaciones-tab">

							<table class="table">
								<thead>
									<tr>
										<th scope="col">Postulación</th>
										<th scope="col">Fecha</th>
									</tr>
								</thead>
								<tbody>


									<tr>
										<td>
											<div class="row p-3">
												<div class="col col-3">
												
												
													<% if (dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getImagen() != null && !dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getImagen().isEmpty()) { %>
														
														<img class="img-fluid" title="Imagen <%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getNickname() %>"
															src="<%= request.getContextPath() %>/assets/images/usuarios/postulante/<%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getImagen() %>">
															
													<% }else{ %>
													
														<img class="img-fluid" title="Imagen <%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getNickname() %>"
															src="<%= request.getContextPath() %>/assets/images/default_image.png">
													
													<% } %>
																		
												
												</div>
												<div class="col col-9">
													<h3><%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getNombreCompleto() %></h3>
													<div class="item-info">
														<p>
															<span class="fw-bold">Nickname: </span><%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getNickname() %>
														</p>
														<p>
															<span class="fw-bold">Email: </span><%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getCorreo() %>
														</p>
														<div class="item-descripcion pt-3">
															<span> <span class="fw-bold">CV reducido: </span>
																<%= dt_postulacion_oferta_laboral_de_postulante_logueado.getCvReducido() %>
															</span>

														</div>
														<div class="item-descripcion pt-3">

															<span> <span class="fw-bold">Motivación: </span> <%= dt_postulacion_oferta_laboral_de_postulante_logueado.getMotivacion() %>
															</span>
														</div>
														<div class="item-acciones pt-3">
															<a type="button"
																class="btn btn-sm btn-info text-white btn-ver"
																href="postulaciones?oferta=<%= dt_postulacion_oferta_laboral_de_postulante_logueado.getOfertaLaboral().getNombre()%>&postulante=<%= dt_postulacion_oferta_laboral_de_postulante_logueado.getPostulante().getNickname()%>">
																<i class="fa fa-eye"></i> Ver postulación
															</a>
														</div>
													</div>
												</div>
											</div>
										</td>
										<td><%= dt_postulacion_oferta_laboral_de_postulante_logueado.getFechaFormat() %></td>
									</tr>

								</tbody>
							</table>


						</div>

					</div>

				</nav>

				<% } %>
				
				<!-- Fin Postulaciones para postulante que se postulo a la oferta -->
				

			</div>
		</div>
	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
