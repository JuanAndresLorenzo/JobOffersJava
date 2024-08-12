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
<%@ page import="logica.dts.DTPostulanteOfertaLaboral"%>


<% //String tipo_usuario = (String) request.getAttribute("tipo_usuario"); %>

<% //DTUsuario usuario = (DTUsuario) request.getAttribute("usuario"); %>

<% DTUsuario dt_usuario_logueado = (DTUsuario) request.getSession().getAttribute("usuario_logueado"); %>

<% String tipo_usuario = (String) request.getSession().getAttribute("usuario_logueado_tipo"); %>

<!DOCTYPE html>
<html lang="en">

<head>

<jsp:include page="${request.contextPath}/layout/head.jsp" />

<!-- Plugins de terceros -->
<script src="plugins/jquery/jquery-3.7.1.min.js"></script>
<script
	src="plugins/jquery/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
<script
	src="plugins/jquery/jquery-validation-1.19.5/dist/localization/messages_es_AR.min.js"></script>
<!-- Fin Plugins de terceros -->


<!-- Nuestros scripts -->
<script src="assets/js/miPerfil.js"></script>
<!-- Fin Nuestros scripts -->


</head>

<body class="d-flex flex-column min-vh-100">

	<jsp:include page="${request.contextPath}/layout/header.jsp" />


	<div class="container px-4 px-lg-5 mt-5">

		<div class="row gx-4 gx-lg-5 justify-content-center mb-5">

			<div class="col-12 row">

				<h3 class="text-center">Mi Perfil</h3>

				<nav id="nav_contenido">

					<div class="nav nav-tabs" id="nav-tab" role="tablist">
						<button class="nav-link active" id="nav-home-tab"
							data-bs-toggle="tab" data-bs-target="#nav-home" type="button"
							role="tab" aria-controls="nav-home" aria-selected="true">Perfil
						</button>

						<% if (tipo_usuario=="empresa") { %>

						<button class="nav-link" id="nav-ofertas-tab" data-bs-toggle="tab"
							data-bs-target="#nav-ofertas" type="button" role="tab"
							aria-controls="nav-ofertas" aria-selected="false">Ofertas
							laborales</button>
						<button class="nav-link" id="nav-paquetes-tab"
							data-bs-toggle="tab" data-bs-target="#nav-paquetes" type="button"
							role="tab" aria-controls="nav-paquetes" aria-selected="false">Paquetes
						</button>

						<% } %>

						<% if (tipo_usuario=="postulante") { %>


						<button class="nav-link" id="nav-post-tab" data-bs-toggle="tab"
							data-bs-target="#nav-post" type="button" role="tab"
							aria-controls="nav-post" aria-selected="false">Postulaciones
						</button>

						<% } %>

					</div>

					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home"
							role="tabpanel" aria-labelledby="nav-home-tab">

							<jsp:include
								page="${request.contextPath}/pages/usuarios/miPerfilForm.jsp" />


						</div>

						<% if (tipo_usuario=="empresa") { %>


						<% DTOfertaLaboral[] dts_ofertas_laborales_por_empresa = (DTOfertaLaboral[]) request.getAttribute("dts_ofertas_laborales_por_empresa"); %>
						<% DTEmpresaPaquete[] dts_paquetes_comprados_por_empresa = (DTEmpresaPaquete[]) request.getAttribute("dts_paquetes_comprados_por_empresa"); %>


						<% Integer cant_ol_asociadas = dts_ofertas_laborales_por_empresa.length; %>
						<% Integer cant_paquetes_asociados = dts_paquetes_comprados_por_empresa.length; %>



						<div class="tab-pane fade p-5" id="nav-ofertas" role="tabpanel"
							aria-labelledby="nav-ofertas-tab">

							<% if (dts_ofertas_laborales_por_empresa == null || cant_ol_asociadas == 0) { %>

							No tiene ofertas laborales disponibles.

							<% }else{ %>

							<% for(int i = 0; i < dts_ofertas_laborales_por_empresa.length; i+=1) { %>

							<% DTOfertaLaboral dt_ol = dts_ofertas_laborales_por_empresa[i]; %>

							<!--  Oferta laboral de empresa -->

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
									<h3>
										<%= dt_ol.getNombre() %>

									</h3>

									<div class="item-info">

										<p>
											<span
												class="badge 
										<% if(dt_ol.getEstadoFormat().equals("INGRESADA")){ %>
											bg-info											
										<% } %>
										<% if(dt_ol.getEstadoFormat().equals("RECHAZADA")){ %>
											bg-danger									
										<% } %>
										<% if(dt_ol.getEstadoFormat().equals("CONFIRMADA")){ %>										
											bg-success											
										<% } %>
										display-5">
												<%= dt_ol.getEstado() %>
											</span>

											<% if(dt_ol.getVigente()){ %>
											<span class="badge bg-success"> VIGENTE </span>
											<% }else{ %>
											<span class="badge bg-danger"> NO VIGENTE </span>
											<% } %>

										</p>

										<span class="text-muted float-start"> <%= dt_ol.getDepartamento() %>,
											<%=  dt_ol.getCiudad() %>
										</span> <span class="text-muted float-end"> Fecha de alta: <%= dt_ol.getFechaAltaFormat() %>
											| Fecha de vencimiento: <%= dt_ol.getFechaVencimientoFormat() %>

										</span> <br> <span class="badge bg-danger"> <i
											class="fa fa-building"></i> <%= dt_ol.getEmpresa().getNombreCompleto() %>
										</span> <span class="badge bg-primary"> <i class="fa fa-clock"></i>
											Horario: <%= dt_ol.getHorario() %>
										</span> <span class="badge bg-success"> <i
											class="fa fa-money-bill"></i> Remuneración: <%= dt_ol.getRemuneracion() %>
										</span> <span class="badge bg-success"> <i
											class="fa fa-money-bill"></i> Tipo de Publicación: <%= dt_ol.getTipoPublicacion() %>
										</span> <br>

										<% Map<String, DTKeyword> dts_keywords = dt_ol.getKeywords(); %>

										<% for (String clave:dts_keywords.keySet()) { %>

										<% 	DTKeyword dt_keyword = dts_keywords.get(clave); %>

										<span class="badge bg-secondary"><%= dt_keyword.getNombre() %></span>

										<% } %>

									</div>
									<div class="item-descripcion pt-3">
										<span> <%= dt_ol.getDescripcion() %>
										</span>
									</div>
									<div class="item-acciones pt-3">
										<a type="button" class="btn btn-sm btn-primary btn-ver"
											href="ofertas?nombre=<%= dt_ol.getNombre() %>"> <i class="fa fa-eye"></i> Ver
										</a>
									</div>
								</div>
							</div>

							<hr>

							<!--  Fin Oferta laboral de empresa -->

							<% } %>
							<% } %>


						</div>


						<div class="tab-pane fade p-5" id="nav-paquetes" role="tabpanel"
							aria-labelledby="nav-paquetes-tab">


							<% if (dts_paquetes_comprados_por_empresa == null || cant_paquetes_asociados == 0) { %>

							No tiene paquetes comprados.

							<% }else{ %>

							<% for(int i = 0; i < dts_paquetes_comprados_por_empresa.length; i+=1) { %>

							<% DTEmpresaPaquete dt_empresa_paquete = dts_paquetes_comprados_por_empresa[i]; %>
							<% DTPaquete dt_paquete = dt_empresa_paquete.getPaquete(); %>

							<!-- Paquete comprado por empresa -->

							<div class="row p-3">
								<div class="col col-3">
									
									<% if (dt_paquete.getImg() != null && !dt_paquete.getImg().isEmpty()) { %>
								
										<img class="img-fluid" title="Imagen <%= dt_paquete.getNombre() %>"
											src="<%= request.getContextPath() %>/assets/images/paquetes/<%= dt_paquete.getImg() %>">
											
									<% }else{ %>
										
										<img class="img-fluid" title="Imagen <%= dt_paquete.getNombre() %>"
											src="<%= request.getContextPath() %>/assets/images/default_image.png">
									
									<% } %>
										
										
								</div>
								<div class="col col-9">
									<h3><%= dt_paquete.getNombre() %></h3>
									<div class="item-info">

										<p class="float-end">
											<span class="fw-bold">Fecha de compra: </span>
											<%= dt_empresa_paquete.getFechaFormat() %>

											| <span class="fw-bold">Fecha de vencimiento: </span>
											<%= dt_empresa_paquete.getFechaVencimientoFormat() %>
										</p>

										<p>
											<span class="badge bg-success">Descuento: <%= dt_paquete.getDescuento() %>%
											</span> <span class="badge bg-primary">Validez: <%= dt_paquete.getValidezDias() %>
												días
											</span>
										</p>

										<p>
											<span class="fw-bold">Costo: </span>
											$ <%= dt_paquete.getCosto() %>
										</p>


									</div>

									<div class="item-acciones pt-3">
										<a type="button"
											class="btn btn-sm btn-info text-white btn-ver"
											href="paquetes?nombre=<%= dt_paquete.getKey() %>"> <i
											class="fa fa-eye"></i> Ver
										</a>
									</div>

								</div>
							</div>
							<hr>

							<!-- Fin Paquete comprado por empresa -->

							<% } %>

							<% } %>

						</div>

						<% } %>

						<% if (tipo_usuario=="postulante") { %>


						<% DTPostulanteOfertaLaboral[] dts_postulaciones_ofertas_laborales_de_postulante = (DTPostulanteOfertaLaboral[]) request.getAttribute("dts_postulaciones_ofertas_laborales_de_postulante"); %>

						<% Integer cant_postulaciones_asociadas = dts_postulaciones_ofertas_laborales_de_postulante.length; %>



						<div class="tab-pane fade p-5" id="nav-post" role="tabpanel"
							aria-labelledby="nav-post-tab">


							<% if (dts_postulaciones_ofertas_laborales_de_postulante == null || cant_postulaciones_asociadas == 0) { %>

							No tiene postulaciones.

							<% }else{ %>

							<% for(int i = 0; i < dts_postulaciones_ofertas_laborales_de_postulante.length; i+=1) { %>

							<% DTPostulanteOfertaLaboral dt_postulacion = dts_postulaciones_ofertas_laborales_de_postulante[i]; %>

							<!--  Postulación de postulante -->

							<div class="row p-3">
								<div class="col col-3">
								
								<% if (dt_postulacion.getOfertaLaboral().getImg() != null && !dt_postulacion.getOfertaLaboral().getImg().isEmpty()) { %>
								
										<img class="img-fluid" title="Imagen <%= dt_postulacion.getOfertaLaboral().getNombre() %>"
											src="<%= request.getContextPath() %>/assets/images/ofertas/<%= dt_postulacion.getOfertaLaboral().getImg() %>">
											
									<% }else{ %>
										
										<img class="img-fluid" title="Imagen <%= dt_postulacion.getOfertaLaboral().getNombre() %>"
											src="<%= request.getContextPath() %>/assets/images/default_image.png">
									
									<% } %>
								
								
								</div>
								<div class="col col-9">
									<h3>
										<%= dt_postulacion.getOfertaLaboral().getNombre() %>
										
									</h3>
									<div class="item-info">
										<!--                                    <span class="text-muted float-end">Fecha de alta: 14/08/2023</span>-->
										
										
										<span class="fw-bold">Empresa: </span>
										<a href="usuarios?nickname=<%= dt_postulacion.getOfertaLaboral().getEmpresa().getNickname() %>"><%= dt_postulacion.getOfertaLaboral().getEmpresa() %></a>
											
										<span class="text-muted float-end">Fecha de
											postulación: <%= dt_postulacion.getFechaFormat() %></span> <br> <span
											class="badge bg-danger"> <i class="fa fa-building"></i>
											<%= dt_postulacion.getOfertaLaboral().getEmpresa() %>
										</span> <span class="badge bg-primary"> <i class="fa fa-clock"></i>
											Horario: <%= dt_postulacion.getOfertaLaboral().getHorario() %>
										</span> <span class="badge bg-success"> <i
											class="fa fa-money-bill"></i> Remuneración: $<%= dt_postulacion.getOfertaLaboral().getRemuneracion() %>
										</span>

									</div>

									<div class="item-acciones pt-3">
										<a type="button" class="btn btn-sm btn-primary btn-ver"
											href="ofertas?nombre=<%= dt_postulacion.getOfertaLaboral().getNombre() %>"> <i class="fa fa-eye"></i>
											Ver oferta
										</a> <a type="button"
											class="btn btn-sm btn-info text-white btn-ver"
											href="postulaciones?oferta=<%= dt_postulacion.getOfertaLaboral().getNombre() %>&postulante=<%= dt_postulacion.getPostulante().getNickname() %>"> <i
											class="fa fa-eye"></i> Ver postulación
										</a>

									</div>
								</div>
							</div>

							<hr>

							<!--  Fin Postulación de postulante -->
							<% } %>
							<% } %>


						</div>

						<% } %>

					</div>

				</nav>

			</div>
		</div>
	</div>


	<jsp:include page="${request.contextPath}/layout/footer.jsp" />

</body>

</html>
