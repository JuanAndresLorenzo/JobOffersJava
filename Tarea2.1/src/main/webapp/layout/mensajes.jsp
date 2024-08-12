<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.LinkedHashMap"%>

<!-- Mensajes -->

<%
LinkedHashMap<String, String> mensajes_exito = (LinkedHashMap<String, String>) request.getAttribute("mensajes_exito");
LinkedHashMap<String, String> mensajes_error = (LinkedHashMap<String, String>) request.getAttribute("mensajes_error");
LinkedHashMap<String, String> mensajes_info = (LinkedHashMap<String, String>) request.getAttribute("mensajes_info");

%>

<%
if (mensajes_exito != null) {
%>

<div class="container px-4 px-lg-5 mt-5">


	<div class="row gx-4 gx-lg-5 justify-content-center mb-5">


		<div class="col-12 row">



			<%
			for (String clave : mensajes_exito.keySet()) {
			%>

			<%
			String mensaje = mensajes_exito.get(clave);
			%>

			<p class="alert alert-success"><%=mensaje%></span>

				<%
				}
				%>
			
		</div>
	</div>

</div>

<%
}
%>
<%
if (mensajes_error != null) {
%>

<div class="container px-4 px-lg-5 mt-5">


	<div class="row gx-4 gx-lg-5 justify-content-center mb-5">


		<div class="col-12 row">



			<%
			for (String clave : mensajes_error.keySet()) {
			%>

			<%
			String mensaje = mensajes_error.get(clave);
			%>

			<p class="alert alert-danger"><%=mensaje%></span>

				<%
				}
				%>
			
		</div>
	</div>

</div>

<%
}
%>
<%
if (mensajes_info != null) {
%>

<div class="container px-4 px-lg-5 mt-5">


	<div class="row gx-4 gx-lg-5 justify-content-center mb-5">


		<div class="col-12 row">



			<%
			for (String clave : mensajes_info.keySet()) {
			%>

			<%
			String mensaje = mensajes_info.get(clave);
			%>

			<p class="alert alert-info"><%=mensaje%></span>

				<%
				}
				%>
			
		</div>
	</div>

</div>

<%
}
%>


<!-- Fin Mensajes -->

