<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTPostulante"%>
<%@ page import="logica.dts.DTEmpresa"%>
<%@ page import="logica.dts.DTOfertaLaboral"%>
<%@ page import="logica.dts.DTKeyword"%>

<% DTEmpresa dt_objeto = (DTEmpresa) request.getAttribute("dt_objeto"); %>
<% DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas_de_empresa = (DTOfertaLaboral[]) request.getAttribute("dts_ofertas_laborales_vigentes_confirmadas_de_empresa"); %>

<% Integer cant_ol_vigentes_confirmads_asciadas = dts_ofertas_laborales_vigentes_confirmadas_de_empresa.length; %>

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
				<div class="col col-12 row">

					<div class="col col-3">
					
						<% if (dt_objeto.getImagen() != null && !dt_objeto.getImagen().isEmpty()) { %>
						
						<img class="img-fluid" title="Imagen <%= dt_objeto.getNickname() %>"
							src="<%= request.getContextPath() %>/assets/images/usuarios/empresa/<%= dt_objeto.getImagen() %>">
							
						<% }else{ %>
						
						<img class="img-fluid" title="Imagen <%= dt_objeto.getNickname() %>"
							src="<%= request.getContextPath() %>/assets/images/default_image.png">
						
						<% } %>
						
							
					</div>
										
					<div class="col col-4">
						<div class="mb-5">
							<h1><%= dt_objeto.getNombreCompleto() %></h1>
						</div>
						<p>
							<span class="fw-bold">Nombre: </span><%= dt_objeto.getNombre() %>
						</p>
						<p>
							<span class="fw-bold">Apellido: </span><%= dt_objeto.getApellido() %>
						</p>
						<p>
							<span class="fw-bold">Nickname: </span><%= dt_objeto.getNickname() %>
						</p>
						<p>
							<span class="fw-bold">Email: </span><%= dt_objeto.getCorreo() %>
						</p>

						<p>
							<span class="fw-bold">Sitio web: </span><%= dt_objeto.getSitioWeb() %>
						</p>


					</div>

					<div class="col col-5">

						<p>
							<span class="fw-bold">Descripción: </span>
						</p>

						<span><%= dt_objeto.getDescripcion() %></span>

					</div>

				</div>

				<!-- Tabs ofertas con estado "Confirmada" -->

				<div class="col col-12 p-3" id="datos">

					<nav id="nav_contenido">

						<div class="nav nav-tabs" id="nav-tab" role="tablist">
							<!--                        <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab"-->
							<!--                                data-bs-target="#nav-home" type="button" role="tab" aria-controls="nav-home"-->
							<!--                                aria-selected="true">Perfil-->
							<!--                        </button>-->
							<button class="nav-link active" id="nav-ofertas-tab"
								data-bs-toggle="tab" data-bs-target="#nav-ofertas" type="button"
								role="tab" aria-controls="nav-ofertas" aria-selected="true">
								Ofertas Laborales disponibles <span class="badge bg-secondary rounded-pill"><%= cant_ol_vigentes_confirmads_asciadas %></span>
							</button>
						</div>
					</nav>

					<div class="tab-content" id="nav-tabContent">

						<div class="tab-pane fade show active p-5" id="nav-ofertas"
							role="tabpanel" aria-labelledby="nav-ofertas-tab">

							<% if (dts_ofertas_laborales_vigentes_confirmadas_de_empresa == null || cant_ol_vigentes_confirmads_asciadas == 0) { %>

							Esta empresa no tiene ofertas laborales disponibles.

							<% }else{ %>

							<% for(int i = 0; i < dts_ofertas_laborales_vigentes_confirmadas_de_empresa.length; i+=1) { %>
							
							<% DTOfertaLaboral dt_ol = dts_ofertas_laborales_vigentes_confirmadas_de_empresa[i]; %>

							<!--  Oferta laboral de empresa -->

							<div class="row p-3">
								<div class="col col-3">
									
										
										<% if (dt_ol.getImg() != null && !dt_ol.getImg().isEmpty()) { %>
						
											<img class="img-fluid" title="Imagen <%= dt_ol.getNombre() %>"
												src="<%= request.getContextPath() %>/assets/images/ofertas/<%= dt_ol.getImg() %>">
											
										<% }else{ %>
										
											<img class="img-fluid" title="Imagen <%= dt_objeto.getNombre() %>"
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
											<%= dt_ol.getEmpresa().getNombreCompleto() %>
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
										
										<% Map<String, DTKeyword> dts_keywords = dt_ol.getKeywords(); %>
																				
										<% for (String clave:dts_keywords.keySet()) { %>
										
											<% 	DTKeyword dt_keyword = dts_keywords.get(clave); %>
																				
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
									</div>
								</div>
							</div>

							<hr>
							
							<!--  Fin Oferta laboral de empresa -->

							<% } %>
							<% } %>


						</div>

					</div>
				</div>

				<!-- Fin Tabs ofertas con estado "Confirmada" -->

			</div>
		</div>
	</div>

	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
