<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<%@ page import="logica.dts.DTKeyword"%>  
	<%@ page import="logica.dts.DTTipoPublicacion"%>  
	 <%@ page import="logica.dts.DTUsuario"%>
	 <%@ page import="logica.dts.DTPaquete"%>
    <% DTKeyword[] arreglokey = (DTKeyword[]) request.getAttribute("key"); %>
    <% DTTipoPublicacion[] arreglotp = (DTTipoPublicacion[]) request.getAttribute("arrtp");%>
    <% DTPaquete[] arreglopaquetes = (DTPaquete[]) request.getAttribute("arrpaq");%>    
    <% DTUsuario dt_usuario = (DTUsuario) request.getSession().getAttribute("usuario_logueado"); %>
    
<!DOCTYPE html>
<html>
<head>

	<jsp:include page="${request.getContextPath()}/layout/head.jsp" />
	
	    <!-- Plugins de terceros -->
    <script src="plugins/jquery/jquery-3.7.1.min.js"></script>
    <script src="plugins/jquery/jquery-validation-1.19.5/dist/jquery.validate.min.js"></script>
    <script src="plugins/jquery/jquery-validation-1.19.5/dist/localization/messages_es_AR.min.js"></script>
    <!-- Fin Plugins de terceros -->

    <!-- Nuestros scripts -->
    	<script src="assets/js/altaOfertaLaboral.js"></script>
    <!-- Fin Nuestros scripts -->

</head>

<body class="d-flex flex-column min-vh-100">

<jsp:include page="${request.getContextPath()}/layout/header.jsp" />

<div class="container px-4 px-lg-5 mt-5">

    <div class="row gx-4 gx-lg-5 justify-content-center mb-5">

        <div class="col-12 row">

            <h3 class="text-center">Alta de Oferta Laboral</h3>

            <form id="form_oferta" method="post" action="" enctype="multipart/form-data">
            
                <div class="mb-3">
					<label for="imagen" class="form-label">Imagen de perfil</label>
					<input class="form-control" type="file" id="imagen" name="imagen" value="${ param.imagen }">
				</div>
            
                <div class="col-md-12 row p-3">

                <div class="col-md-9 p-3">
                    <label for="nombre" class="form-label">Nombre</label>
                    <input type="text" class="form-control form-control-lg" id="nombre" name="nombre">
                </div>
                <div class="col-md-3 p-3">
                    <label for="fecha_alta" class="form-label">Fecha de alta</label>
                    <input type="date" class="form-control form-control-lg" id="fecha_alta" name="fecha_alta">
                </div>
                </div>

                <div class="col-md-12 p-3">
                    <label for="descripcion" class="form-label">Descripción</label>
                    <textarea class="form-control" rows="5" id="descripcion" name="descripcion"> </textarea>
                </div>
                <div class="col-md-12 p-3 row">
                    <div class="col-md-6">
                        <label for="ciudad" class="form-label">Ciudad</label>
                        <input type="text" class="form-control form-control-lg" id="ciudad" name="ciudad">
                    </div>
                    <div class="col-md-6">
                        <label for="departamento" class="form-label">Departamento</label>
                        <input type="text" class="form-control form-control-lg" id="departamento" name="departamento">
                    </div>
                </div>
                <div class="col-md-12 row p-3">

                <div class="col-md-6 p-3">
                    <label for="horario" class="form-label">Horario</label>
                    <input type="text" class="form-control form-control-lg" id="horario" name="horario">
                </div>
                <div class="col-md-6 p-3">
                    <label for="remuneracion" class="form-label">Remuneración</label>
                    <input type="text" class="form-control form-control-lg" id="remuneracion" name="remuneracion">
                </div>
                </div>

                <div class="col-md-12 p-3">
                    <label for="empresa" class="form-label">Empresa</label>
                    <input type="text" class="form-control form-control-lg" id="empresa" name="empresa" disabled value="<%=dt_usuario.getNickname()%>">
                </div>
                <div class="col-md-12 row p-3">

                    <div class="col-md-4 p-3">

                        <label for="tipo_publicacion" class="form-label">Tipo de publicación</label>
                        <select id="tipo_publicacion" name="tipo_publicacion" class="form-select form-select-lg">
                            <option value="" selected>Seleccionar un tipo de publicación</option>
                            
                            <%for (int i=0 ; i<arreglotp.length ; i++) {
                        	DTTipoPublicacion dttp = arreglotp[i]; %>
                        	<option value="<%=dttp.getNombre()%>"><%= dttp.getNombre()%></option>
                        	<%}%>
                        </select>
                    </div>

                    <div class="col-md-4 p-3">

                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="" id="usar_paquete_checkbox">
                            <label class="form-check-label" for="usar_paquete_checkbox">
                                Usar paquete
                            </label>
                        </div>

                    </div>

                    <div class="col-md-4 p-3" id="div_paquete">

                        <label for="paquete" class="form-label">Paquete</label>
                        <select id="paquete" name="paquete" class="form-select form-select-lg">
                            <option value="" selected>Seleccionar un paquete</option>
                            
                             <%for (int i=0 ; i<arreglopaquetes.length ; i++) {
                        		DTPaquete dtpaq = arreglopaquetes[i]; %>
                        		<option value="<%=dtpaq.getNombre()%>"><%= dtpaq.getNombre()%></option>
                        	<%}%>
                       </select>

                    </div>


                </div>

                <div class="col-md-12 p-3">
                    <label for="keywords" class="form-label">Keywords</label>
                    <select id="keywords" name="keywords" class="form-select form-select-lg" multiple
                            aria-label="multiple select example">

                        <option value="" selected> </option>
                        
                        <%for (int i=0 ; i<arreglokey.length ; i++) {
                        	DTKeyword dtkey = arreglokey[i]; %>
                        	<option value="keyword<%=i%>"><%= dtkey.getNombre()%></option>
                        <%}%>
                        
                    </select>
                </div>

                <div class="col-12 text-center p-3">
                    <a type="button" class="btn btn-danger btn-cancelar" onclick="window.history.back();">
                        <i class="fa fa-ban"></i>
                        Cancelar
                    </a>
                    <button type="submit" class="btn btn-primary btn-guardar">
                        <i class="fa fa-save"></i>
                        Guardar
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>

<footer class="bg-light text-center text-lg-start mt-auto">
    <!-- Copyright -->
    <div class="text-center p-3" style="background-color: rgba(0, 0, 0, 0.2);">
        © 2023 Copyright:
        <a class="text-dark" href="https://www.fing.edu.uy">FING - TPROG - GR45</a>
    </div>
    <!-- Copyright -->
</footer>




</body>

</html>