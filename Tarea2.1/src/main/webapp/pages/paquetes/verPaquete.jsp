<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<%@ page import="logica.dts.DTPaquete"%>
<%@ page import="logica.dts.DTPaqueteTipoPublicacion"%>
<%@ page import="logica.dts.DTUsuario"%>
<%@ page import="logica.dts.DTTipoPublicacion" %>


<%
DTPaquete dt_objeto = (DTPaquete) request.getAttribute("dt_objeto");
Boolean puede_comprar_paquete = (Boolean) request.getAttribute("puede_comprar_paquete");

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

					<h1>
						Paquete
						<%=dt_objeto.getNombre()%>
					</h1>

					<div class="col-md-12 row p-3">

						<div class="col-md-3 p-3">

							<% if (dt_objeto.getImg() != null && !dt_objeto.getImg().isEmpty()) { %>
						
								<img class="img-fluid" title="Imagen <%= dt_objeto.getNombre() %>"
									src="<%= request.getContextPath() %>/assets/images/paquetes/<%= dt_objeto.getImg() %>">
									
							<% }else{ %>
								
								<img class="img-fluid" title="Imagen <%= dt_objeto.getNombre() %>"
									src="<%= request.getContextPath() %>/assets/images/default_image.png">
							
							<% } %>
							
							
						</div>

						<div class="col-md-5 p-3">

							<p>
								<span class="fw-bold">Nombre: </span><%=dt_objeto.getNombre()%></p>
							<p>
								<span class="fw-bold">Costo: </span>$<%=dt_objeto.getCosto()%></p>
							<p>
								<span class="fw-bold">Validez (días): </span><%=dt_objeto.getValidezDias()%>
								días
							</p>
							<p>
								<span class="fw-bold">Descuento: </span><%=dt_objeto.getDescuento()%>%
							</p>
							<p>
								<span class="fw-bold">Fecha de alta: </span><%=dt_objeto.getFechaAltaFormat()%></p>

							<p>
								<span class="fw-bold">Descripción: </span>
								<%=dt_objeto.getDescripcion()%>
							</p>

						</div>

						<div class="col-md-4 p-3">

							<table class="table">
								<thead>
									<tr>
										<th scope="col">Tipo de publicación</th>
										<th scope="col">Cantidad</th>
									</tr>
								</thead>
								<tbody>

									<%
									Map<String, DTPaqueteTipoPublicacion> dt_paquete_tps = dt_objeto.getTiposPublicaciones();
									%>

									<%
									for (Map.Entry<String, DTPaqueteTipoPublicacion> entry : dt_paquete_tps.entrySet()) {
									%>

									<%
									DTPaqueteTipoPublicacion dt_paquete_tp = entry.getValue();
									%>

									<tr>
										<td><a
											href="tipo_publicaciones?nombre=<%=dt_paquete_tp.getTipoPublicacion().getNombre()%>">
												<%=dt_paquete_tp.getTipoPublicacion().getNombre()%>
										</a></td>
										<td><%=dt_paquete_tp.getCantidad()%></td>
									</tr>
									<%
									}
									%>


								</tbody>
							</table>

						</div>

					</div>

					<%
					if (puede_comprar_paquete) {
					%>

					<div class="col-12 text-center p-3">

						<button type="button"
							class="btn btn-success btn-guardar btn-comprar"
							data-bs-toggle="modal" data-bs-target="#exampleModal">
							<i class="fa fa-dollar-sign"></i> Comprar
						</button>

					</div>

					<!-- Modal -->
					<div class="modal fade" id="exampleModal" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog modal-dialog-centered">
							<div class="modal-content">

								<form id="form_compra_paquete" method="post" action="#">

									<input type="hidden" name="nombre"
										value="<%=dt_objeto.getNombre()%>" />

									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Compra de
											paquete</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>

									<div class="modal-body">

										<p>
											<span class="fw-bold">Costo: </span>$<%=dt_objeto.getCosto()%></p>
										<p>
									</div>

									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Cerrar</button>

										<button type="submit"
											class="btn btn-success btn-guardar btn-comprar">
											<i class="fa fa-dollar-sign"></i> Comprar
										</button>

									</div>
								</form>

							</div>
						</div>
					</div>

					<%
					}else{
					%>
					<div class="col-12 text-center p-3">Este paquete ya fue
						comprado</div>

					<%
					}
					%>


				</div>


			</div>
		</div>
	</div>


	<jsp:include page="${request.getContextPath()}/layout/footer.jsp" />



</body>

</html>
