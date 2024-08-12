package com.tarea21.controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedHashMap;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import com.tarea21.model.EstadoSesion;
import com.tarea21.controllers.Validador;
import com.tarea21.model.Usuario;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;
import logica.dts.DTUsuario;

import logica.interfaces.IControladorUsuario;
import logica.Fabrica;
import logica.clases.EnumRol;
import logica.dts.DTEmpresa;
import logica.dts.DTEmpresaPaquete;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTPostulanteOfertaLaboral;

/**
 * Servlet implementation class Perfil
 */
@WebServlet("/mi-perfil")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class Perfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorUsuario ICU;
	
	DTUsuario dt_usuario_logueado;
	private String usuario_logueado_tipo;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Perfil() {
		super();

	    Fabrica fabrica = Fabrica.getInstance();
    	
		this.ICU = fabrica.getIControladorUsuario();
	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 * 
	 * @param request  servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		DTUsuario dt_usuario_logueado = (DTUsuario) request.getSession().getAttribute("usuario_logueado");
		String usuario_logueado_tipo = (String) request.getSession().getAttribute("usuario_logueado_tipo");

		if (dt_usuario_logueado == null) {
			// no existe el usuario, se trata como deslogueado
			request.getSession().setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
			request.getRequestDispatcher("/").forward(request, response);
		}
		
		this.dt_usuario_logueado = dt_usuario_logueado;
		this.usuario_logueado_tipo = usuario_logueado_tipo;

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		processRequest(request, response);
		
		
		String usuario_nickname = dt_usuario_logueado.getNickname();
		
		this.cargarDatosAsociados(request);		
				
		request.getRequestDispatcher("/pages/usuarios/miPerfil.jsp").forward(request, response);	
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		processRequest(request, response);
		

		String tipo_usuario = this.usuario_logueado_tipo;
		LinkedHashMap<String, String> mensajes_error = new LinkedHashMap<String, String>();			
		LinkedHashMap<String, String> mensajes_exito = new LinkedHashMap<String, String>();
		
		//String tipo_usuario = request.getParameter("tipo_usuario");
		String imagen = request.getParameter("imagen");
		
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		
		String nombre_empresa = request.getParameter("nombre_empresa");
		String sitio_web = request.getParameter("sitio_web");
		String descripcion = request.getParameter("descripcion");
		
		String fecha_nacimiento = request.getParameter("fecha_nacimiento");
		String nacionalidad = request.getParameter("nacionalidad");

		//String nickname = request.getParameter("nickname");
		//String correo = request.getParameter("correo");
		Boolean desea_cambiar_clave = request.getParameter("cambiar_clave_check") != null;
		String clave = request.getParameter("clave");
		
		System.out.println("CAMBIAR CLAVE: "+desea_cambiar_clave);
		
		/////////////////////Obtener archivo imagen/////////////////////////////////
		
		Part filePart = request.getPart("imagen");
		String fileName = filePart.getSubmittedFileName();			
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		
		//System.out.println("IMAGEN: "+imagen);

		System.out.println("PART: "+filePart);
		
		System.out.println("PART NO NULL: "+filePart!=null);


		/////////////////////Fin Obtener archivo imagen/////////////////////////////////
		
		//Validaciones
		
		Validador validador = new Validador();
		
		
		//Valido que la imagen sea de un tipo de imagen
		 if(filePart != null && !extension.isEmpty() && !validador.validarTipoImagen(extension)){
				//MENSAJE ERROR
			 mensajes_error.put("imagen_tipo", "El formato de la imagen debe ser: png, jpg o jpeg. Su archivo es de tipo: "+extension);					

		 }
		
		//Si el tamaño en bytes excede los 10MB
		 if(filePart != null && filePart.getSize() > 10000000){
				//MENSAJE ERROR
			 mensajes_error.put("imagen_tamanio", "El tamaño de la im{agen no puede superar los 10MB");					

		 }
				

		if (nombre.isEmpty()) {
			
			//MENSAJE ERROR
			mensajes_error.put("nombre_vacio", "El campo nombre es obligatorio");
			
		}
		if (apellido.isEmpty()) {
			
			//MENSAJE ERROR
			mensajes_error.put("apellido_vacio", "El campo apellido es obligatorio");
			
		}
		
		if (!tipo_usuario.isEmpty() && tipo_usuario.equals("empresa")) {
			
			//Validaciones empresa

			if (nombre_empresa.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("nombre_empresa_vacio", "El campo nombre de empresa es obligatorio");

			}
			
			System.out.print("DESC "+descripcion.isEmpty());

			if (descripcion.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("descripcion_vacio", "El campo descripción es obligatorio");

			}
				
		}else if (!tipo_usuario.isEmpty() && tipo_usuario.equals("postulante")) {
			
			//Validaciones postulante				
			
			if (fecha_nacimiento.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("fecha_nacimiento_vacio", "El campo fecha de nacimiento es obligatorio");

			}
			if (nacionalidad.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("nacionalidad_vacio", "El campo nacionalidad es obligatorio");

			}
			//Fin Validaciones
			
		}
		
		if (desea_cambiar_clave && clave != null && clave.isEmpty()) {
			
			//MENSAJE ERROR
			mensajes_error.put("clave_vacio", "Como indico que desea cambiar la clave. El campo clave es obligatorio");
			
		}

		//VALIDAR FORMATO SITIO WEB
		Boolean es_valido_url = validador.validarUrl(sitio_web);
		if(!sitio_web.isEmpty() && !es_valido_url) {
			
			//MENSAJE ERROR
			mensajes_error.put(
	        		"sitio_web_invalido", "El campo sitio web no es válido. "
			        		+ "El sitio web debe ser una dirección url válida."
			        		+ "");				
		}
		//Fin Validaciones
		
		// Si hubo errores en el completado
		if (!mensajes_error.isEmpty()) {
			
			//System.out.println("HAY MENSAJES: "+mensajes);
			
			request.setAttribute("mensajes_error", mensajes_error);
			
			cargarDatosAsociados(request);
			
			request.getRequestDispatcher("pages/usuarios/miPerfil.jsp").
					forward(request, response);
			
		} else {
			
			String nickname = dt_usuario_logueado.getNickname();
			
			String clave_guardar = dt_usuario_logueado.getClave();

			if (clave != null && !clave.isEmpty()) {
			
				clave_guardar = clave;
			}
			
			if (tipo_usuario.equals("empresa")) {					
				
				try {
					
					
					//////////////////Gestión archivo /////////////////////////
					
					if(filePart != null && filePart.getSize() > 0){
						
						String root = System.getProperty("user.dir");
						String ruta_nueva = root +"/eclipse-workspace/tpgr45/Tarea2.1/src/main/webapp/assets/images/usuarios/empresa";
						File directorio = new File(ruta_nueva);
						if (!directorio.exists()) {
							directorio.mkdir();
						}
						
						imagen = nickname+"."+extension;

						//String ruta_nueva_con_archivo = ruta_nueva + File.separator + fileName;
						String ruta_nueva_con_archivo = ruta_nueva + File.separator + imagen;

						System.out.println("PATH: "+ruta_nueva);			
						//System.out.println("IMAGEN: "+imagen);
						System.out.println("PART: "+filePart);
						System.out.println("FILANEMA: "+fileName);
						System.out.println("ext: "+extension);
						System.out.println("tamaño: "+filePart.getSize());				

						//GUARDAR ARCHIVO
						filePart.write(ruta_nueva_con_archivo);
						
					}else {
						imagen = "";
					}
					
					
					//////////////////Fin Gestión archivo /////////////////////////
					
					ICU.editarEmpresa(nickname, nombre, apellido, nombre_empresa, descripcion, sitio_web, clave_guardar, imagen);
					
					
					//MENSAJE EXITO
					mensajes_exito.put("guardado_exitoso", "El perfil se actualizó con éxito.");	
					
				} catch (ObjetoNoExisteException e) {
					
					e.printStackTrace();
				}
			
			}
			if (tipo_usuario.equals("postulante")) {					
			
				
				try {
					
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					Date fecha_nacimiento_date;
					
					try {
						fecha_nacimiento_date = format.parse(fecha_nacimiento);
					} catch (ParseException e) {
						e.printStackTrace();
						fecha_nacimiento_date = null;
					}
					
					
					//////////////////Gestión archivo /////////////////////////
					
					if(filePart != null && filePart.getSize() > 0){
						
						String root = System.getProperty("user.dir");
						String ruta_nueva = root +"/eclipse-workspace/tpgr45/Tarea2.1/src/main/webapp/assets/images/usuarios/empresa";
						File directorio = new File(ruta_nueva);
						if (!directorio.exists()) {
							directorio.mkdir();
						}
						
						imagen = nickname+"."+extension;

						//String ruta_nueva_con_archivo = ruta_nueva + File.separator + fileName;
						String ruta_nueva_con_archivo = ruta_nueva + File.separator + imagen;

						System.out.println("PATH: "+ruta_nueva);			
						//System.out.println("IMAGEN: "+imagen);
						System.out.println("PART: "+filePart);
						System.out.println("FILANEMA: "+fileName);
						System.out.println("ext: "+extension);
						System.out.println("tamaño: "+filePart.getSize());				

						//GUARDAR ARCHIVO
						filePart.write(ruta_nueva_con_archivo);
						
					}else {
						imagen = "";
					}
					
					
					//////////////////Fin Gestión archivo /////////////////////////
					
					ICU.editarPostulante(nickname, nombre, apellido, fecha_nacimiento_date, nacionalidad, clave_guardar, imagen);
					//MENSAJE EXITO
					mensajes_exito.put("guardado_exitoso", "El perfil se actualizó con éxito.");	
					
				} catch (ObjetoNoExisteException e) {
					
					e.printStackTrace();
				}

			}
			// actualizo el dt de usuario logueado, de lo contrario en mi perfil va a cargar datos viejos
			DTUsuario dt_usuario_actualizado;
			try {
				dt_usuario_actualizado = ICU.getDataUsuario(nickname);
				request.getSession().setAttribute("usuario_logueado", dt_usuario_actualizado);
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
			}
			
			/*
			request.setAttribute("mensajes_exito", mensajes_exito);			
			request.getRequestDispatcher("/pages/usuarios/miPerfil.jsp").forward(request, response);	
			*/
			response.sendRedirect("mi-perfil");


		}
		

	}
	private void cargarDatosAsociados(HttpServletRequest request) {
		
		String usuario_nickname = this.dt_usuario_logueado.getNickname();
		
		if(usuario_logueado_tipo.equals("empresa")) {
			
			try {
				
				DTOfertaLaboral[] dts_ofertas_laborales_por_empresa = ICU.listarOfertasLaboralesPorEmpresa(usuario_nickname);
				request.setAttribute("dts_ofertas_laborales_por_empresa", dts_ofertas_laborales_por_empresa);				
				
			} catch (ObjetoNoExisteException e) {
				
				e.printStackTrace();					
				DTOfertaLaboral[] dts_ofertas_laborales_por_empresa = new DTOfertaLaboral[0];
				request.setAttribute("dts_ofertas_laborales_por_empresa", dts_ofertas_laborales_por_empresa);				

			}
			
			try {

				DTEmpresaPaquete[] dts_paquetes_comprados_por_empresa = ICU.listarPaquetesCompradosPorEmpresa(usuario_nickname);
				request.setAttribute("dts_paquetes_comprados_por_empresa", dts_paquetes_comprados_por_empresa);

				
			} catch (ObjetoNoExisteException e) {
				
				e.printStackTrace();					
				DTEmpresaPaquete[] dts_paquetes_comprados_por_empresa = new DTEmpresaPaquete[0];
				request.setAttribute("dts_paquetes_comprados_por_empresa", dts_paquetes_comprados_por_empresa);

			}
			
			
		}
		if(usuario_logueado_tipo.equals("postulante")) {
			
			try {
				
				DTPostulanteOfertaLaboral[] dts_postulaciones_ofertas_laborales_de_postulante = ICU.listarPostulacionesPorPostulante(usuario_nickname);
				request.setAttribute("dts_postulaciones_ofertas_laborales_de_postulante", dts_postulaciones_ofertas_laborales_de_postulante);

	
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
				DTPostulanteOfertaLaboral[] dts_postulaciones_ofertas_laborales_de_postulante = new DTPostulanteOfertaLaboral[0];
				request.setAttribute("dts_postulaciones_ofertas_laborales_de_postulante", dts_postulaciones_ofertas_laborales_de_postulante);

			}
			
	
		}
		
	}

}
