<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="logica.dts.DTUsuario" %>
<%@ page import="logica.dts.DTEmpresa" %>
<%@ page import="logica.dts.DTPostulante" %>


<% String tipo_usuario = (String) request.getAttribute("tipo_usuario"); %>
    
<% DTUsuario usuario = (DTUsuario) request.getAttribute("usuario"); %>


<!DOCTYPE html>
<html lang="en">

<head>

	<jsp:include page="${request.contextPath}/layout/head.jsp" />

</head>

<body class="d-flex flex-column min-vh-100">

<jsp:include page="${request.contextPath}/layout/header.jsp" />

<% if (tipo_usuario=="empresa") { %>


<div class="container px-4 px-lg-5 mt-5">

    <div class="row gx-4 gx-lg-5 justify-content-center mb-5">

        <div class="col-12 row">

            <h3 class="text-center">Mi Perfil de empresa</h3>

            <nav id="nav_contenido">

                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <button class="nav-link active" id="nav-home-tab" data-bs-toggle="tab" data-bs-target="#nav-home"
                            type="button" role="tab" aria-controls="nav-home" aria-selected="true">Perfil
                    </button>
                    <button class="nav-link" id="nav-ofertas-tab" data-bs-toggle="tab" data-bs-target="#nav-ofertas"
                            type="button" role="tab" aria-controls="nav-ofertas" aria-selected="false">Ofertas laborales
                    </button>
                    <button class="nav-link" id="nav-paquetes-tab" data-bs-toggle="tab" data-bs-target="#nav-paquetes"
                            type="button" role="tab" aria-controls="nav-paquetes" aria-selected="false">Paquetes
                    </button>
                </div>

                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">

                        <form>

                            <div class="col-md-12 p-3">
                                <label for="tipo_usuario" class="form-label">Tipo de usuario</label>
                                <select id="tipo_usuario" class="form-select form-select-lg" disabled>
                                    <option value="postulante">Postulante</option>
                                    <option value="">Seleccionar un tipo de usuario</option>
                                    <option value="empresa" selected>Empresa</option>
                                </select>
                            </div>

                            <div class="col-md-12 row p-3">

                                <div class="col col-4">
                                    <img class="img-fluid" title="Imágen Empresa"
                                         src="https://i.pinimg.com/originals/14/8b/db/148bdbafbb2acf7c02b4ac507e4adacb.jpg">
                                </div>

                                <div class="col col-8">

                                    <div class="col-md-12 row p-3">

                                        <div class="col-md-6 p-3">
                                            <label for="nombre" class="form-label">Nombre</label>
                                            <input type="text" class="form-control form-control-lg" id="nombre"
                                                   value="<%= usuario.getNombre() %>">
                                        </div>
                                        <div class="col-md-6 p-3">
                                            <label for="apellido" class="form-label">Apellido</label>
                                            <input type="text" class="form-control form-control-lg" id="apellido"
                                                   value="<%= usuario.getApellido() %>">
                                        </div>
                                    </div>

									<% if (tipo_usuario=="postulante") { %>

<!-- 
                                    <div id="div_campos_postulante" class="col-md-12 row p-3">

                                        <div class="col-md-6 p-3">
                                            <label for="fecha_nacimiento" class="form-label">Fecha de nacimiento</label>
                                            <input type="date" class="form-control form-control-lg"
                                                   id="fecha_nacimiento"
                                                   value="<%= //usuario.getFechaNacimiento() %>">
                                        </div>
                                        <div class="col-md-6 p-3">
                                            <label for="nacionalidad" class="form-label">Nacionalidad</label>
                                            <input type="text" class="form-control form-control-lg" id="nacionalidad"
                                                   value="<%= //usuario.getNacionalidad() %>">
                                        </div>
                                    </div>
                                     -->
                                    
                                    <% } %>
                                    
                                    <% if (tipo_usuario=="empresa") { %>    
                                    
                                    <!--                                                                    

                                    <div id="div_campos_empresa" class="col-md-12 row p-3">

                                        <div class="col-md-6 p-3">
                                            <label for="nombre_empresa" class="form-label">Nombre de empresa</label>
                                            <input type="text" class="form-control form-control-lg" id="nombre_empresa" value="EcoTech">
                                        </div>
                                        <div class="col-md-12 p-3">
                                            <label for="sitio_web" class="form-label">Página web</label>
                                            <input type="url" class="form-control form-control-lg" id="sitio_web" value="<%= //usuario.getSitioWeb() %>">
                                        </div>

                                        <div class="col-md-12 p-3">
                                            <label for="descripcion" class="form-label">Descripción</label>
                                            <textarea class="form-control" rows="10" id="descripcion">
                                            	
                                    		</textarea>
                                        </div>
                                    </div>
                                    
                                    -->                                   
                                    
                                    <% } %>
                                    

                                </div>

                            </div>

                            <div class="col-md-12 row p-3">

                                <div class="col-md-4 p-3">
                                    <label for="nickname" class="form-label">Nickname</label>
                                    <input type="text" class="form-control form-control-lg" id="nickname" disabled
                                           value="<%= usuario.getNickname() %>">
                                    <div class="col-md-12">
                                        <button id="btn_cambiar_clave_usuario" type="button"
                                                class="btn btn-primary btn-cambiar-clave">
                                            <i class="fa fa-lock"></i>
                                            Cambiar clave
                                        </button>
                                    </div>
                                </div>
                                <div class="col-md-4 p-3">
                                    <label for="correo" class="form-label">Correo</label>
                                    <input type="text" class="form-control form-control-lg" id="correo" disabled
                                           value="info@EcoTech.com">
                                </div>

                                <div id="div_cambiar_clave_usuario" class="col-md-4 p-3">

                                    <div class="col-md-12">
                                        <label for="clave" class="form-label">Nueva clave:</label>
                                        <input type="password" class="form-control form-control-lg" id="clave">
                                    </div>
                                    <div class="col-md-12">
                                        <label for="clave1" class="form-label">Confimación de nueva clave:</label>
                                        <input type="password" class="form-control form-control-lg" id="clave1">
                                    </div>

                                </div>

                            </div>


                            <div class="col-12 text-center p-3">
                                <a type="button" class="btn btn-danger btn-cancelar" onclick="window.history.back();">
                                    <i class="fa fa-ban"></i>
                                    Cancelar
                                </a>
                                <button type="submit" class="btn btn-primary btn-guardar">
                                    <!-- falta lo de guardar el cambio de informacion  -->
                                    <i class="fa fa-save"></i>
                                    Guardar
                                </button>
                            </div>
                        </form>

                    </div>

                    <div class="tab-pane fade p-5" id="nav-ofertas" role="tabpanel" aria-labelledby="nav-ofertas-tab">

                        <div class="row p-3">
                            <div class="col col-3">
                                <img class="img-fluid" title="Imagen oferta1"
                                     src="../../assets/images/ofertas_laborales/frontEndDev.jpg"/>
                            </div>
                            <div class="col col-9">
                                <p>Desarrollador Frontend <span class="badge bg-info">
                                        Tipo de Publicacion: Premium
                                    </span> </p>
                                <div class="item-info">
                                    <span class="text-muted float-start">Montevideo, Montevideo</span>
                                    <span class="text-muted float-end">Fecha de alta: 14/08/2023</span>
                                    <br>
                                    <span class="badge bg-danger">
                                        <i class="fa fa-building"></i> EcoTech
                                    </span>
                                    <span class="badge bg-primary">
                                        <i class="fa fa-clock"></i> Horario: 09:00 - 18:00
                                    </span>
                                    <span class="badge bg-success">
                                        <i class="fa fa-money-bill"></i> Remuneración: $90.000
                                    </span>

                                    <br>
                                    <span class="badge bg-secondary">Keyword 1</span>
                                    <span class="badge bg-secondary">Keyword 2</span>
                                    <span class="badge bg-secondary">Keyword 3</span>

                                </div>
                                <div class="item-descripcion pt-3">
                                    <span>
                                        Unete a nuestro equipo de desarrollo frontend y creá experiencias de usuario excepcionales.
                                    </span>
                                </div>
                                <div class="item-acciones pt-3">
                                    <a type="button" class="btn btn-sm btn-primary btn-ver" href="verOferta1Empresa.html">
                                        <i class="fa fa-eye"></i>
                                        Ver
                                    </a>

                                </div>
                            </div>
                        </div>

                        <hr>

                        <div class="row p-3">
                            <div class="col col-3">
                                <img class="img-fluid" title="Imagen oferta2"
                                     src="../../assets/images/ofertas_laborales/analistaDeMarketing.jpg"/>
                            </div>
                            <div class="col col-9">
                                <p>A. de Marketing Digital <span class="badge bg-info">
                                        Tipo de Publicacion: Premium
                                    </span> </p>
                                <div class="item-info">
                                    <span class="text-muted float-start">Flores, Flores</span>
                                    <span class="text-muted float-end">Fecha de alta: 15/08/2023</span>
                                    <br>
                                    <span class="badge bg-danger">
                                        <i class="fa fa-building"></i> EcoTech
                                    </span>
                                    <span class="badge bg-primary">
                                        <i class="fa fa-clock"></i> Horario: 10:00 - 19:00
                                    </span>
                                    <span class="badge bg-success">
                                        <i class="fa fa-money-bill"></i> Remuneración: $80.000
                                    </span>
                                    <br>
                                    <span class="badge bg-secondary">Keyword 4</span>
                                    <span class="badge bg-secondary">Keyword 5</span>
                                </div>
                                <div class="item-descripcion pt-3">
                                    <span>
                                        Únete a nuestro equipo de marketing y trabaja en estrategias digitales innovadoras.
                                    </span>
                                </div>
                                <div class="item-acciones pt-3">
                                    <a type="button" class="btn btn-sm btn-primary btn-ver" href="verOferta2Empresa.html">
                                        <i class="fa fa-eye"></i>
                                        Ver
                                    </a>
                                    <!--                                    <a type="button" class="btn btn-sm btn-info text-white btn-postularse" href="postulacionOferta.html">-->
                                    <!--                                        <i class="fa fa-paper-plane"></i>-->
                                    <!--                                        Postularse-->
                                    <!--                                    </a>-->

                                </div>
                            </div>
                        </div>

                        <hr>

                    </div>
                    <div class="tab-pane fade p-5" id="nav-paquetes" role="tabpanel" aria-labelledby="nav-paquetes-tab">

                        <div class="row p-3">
                            <div class="col col-3">
                                <img class="img-fluid" title="Imagen paquete1"
                                     src="https://shorturl.at/ceCD2"/>
                            </div>
                            <div class="col col-9">
                                <h3>Básico</h3>
                                <div class="item-info">
<!--                                    <span class="text-muted float-end">Fecha de alta: 16/08/2023</span>-->
                                    <span class="text-muted float-end">Fecha de compra: 17/08/2023</span>
                                    <span class="badge bg-primary">Validez: 30 días</span>
                                    <span class="badge bg-success">Descuento: 20%</span>
                                    <span class="badge bg-success">Costo: $240</span>
                                </div>
                                <div class="item-descripcion mt-3">
                                <span>
                                    Publica ofertas laborales en nuestra plataforma por un período de 30 días
                                </span>
                                </div>
                                <div class="item-acciones pt-3">
                                    <a type="button" class="btn btn-sm btn-info text-white btn-ver" href="verPaqueteEmpresa.html">
                                        <i class="fa fa-eye"></i>
                                        Ver
                                    </a>
<!--                                    <a type="button" class="btn btn-sm btn-success btn-comprar" href="compraPaquete.html">-->
<!--                                        <i class="fa fa-dollar-sign"></i>-->
<!--                                        Comprar-->
<!--                                    </a>-->
                                </div>

                            </div>
                        </div>

                        <hr>

                    </div>

                </div>

            </nav>

        </div>
    </div>
</div>
<% } %>
<% if (tipo_usuario=="postulante") { %>

	POSTULANTE
<% } %>



<jsp:include page="${request.contextPath}/layout/footer.jsp" />

</body>

</html>
