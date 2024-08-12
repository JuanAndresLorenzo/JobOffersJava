package com.tarea21.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import logica.Fabrica;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTUsuario;
import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulante;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.clases.EnumRol;


/**
 * Servlet implementation class Usuarios
 */
@WebServlet ("/usuarios")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class Usuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorUsuario ICU;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Usuarios() {
        super(); 
        
	    Fabrica fabrica = Fabrica.getInstance();
	    	
		this.ICU = fabrica.getIControladorUsuario();
		
    }

	/** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 * @throws ObjetoNoRespetaFormatoException 
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ObjetoNoRespetaFormatoException {
		
		boolean isPost = "POST".equals(request.getMethod());
		
		System.out.println("METODO: "+isPost);

		
		String usuario_nickname = request.getParameter("nickname");
		String usuario_tipo = request.getParameter("tipo");
		String accion = request.getParameter("accion");
		
		
		//Si es get
		if(!isPost) {
		
			if (accion != null && accion.equals("registrar")) {
				
				request.getRequestDispatcher("pages/usuarios/registrarUsuario.jsp").
				forward(request, response);
				
				//this.registrarUsuario();
				//return;
			}
	
			if(usuario_nickname == null) {
				
				//System.out.println("TIPOOOO: "+usuario_tipo.equals("empresa"));
				
				if(usuario_tipo == null) {
					
					try {
						DTUsuario[] dts_objetos;
						dts_objetos = ICU.listarUsuarios();
						request.setAttribute("dts_objetos", dts_objetos);
			
					} catch (ObjetoNoExisteException e) {
						e.printStackTrace();
					}
					
					request.getRequestDispatcher("pages/usuarios/listaUsuarios.jsp").
							forward(request, response);
				}
				
				if(usuario_tipo.equals("empresa")) {
					
					try {
						DTUsuario[] dts_objetos;
						dts_objetos = ICU.listarEmpresas();
						request.setAttribute("dts_objetos", dts_objetos);
			
					} catch (ObjetoNoExisteException e) {
						e.printStackTrace();
					}
					
					request.getRequestDispatcher("pages/usuarios/listaEmpresas.jsp").
							forward(request, response);
					
				}
				
				if(usuario_tipo.equals("postulante")) {
					
					try {
						DTUsuario[] dts_objetos;
						dts_objetos = ICU.listarPostulantes();
						request.setAttribute("dts_objetos", dts_objetos);
			
					} catch (ObjetoNoExisteException e) {
						e.printStackTrace();
					}
					
					request.getRequestDispatcher("pages/usuarios/listaPostulantes.jsp").
							forward(request, response);
				}
					
			} else {
				
				try {
					
					DTUsuario dt_usuario = ICU.getDataUsuario(usuario_nickname);
					
					if(dt_usuario instanceof DTEmpresa) {
						
						request.setAttribute("dt_objeto_tipo", "empresa");
						request.setAttribute("dt_objeto", dt_usuario);	
	
						//TODO: Revisar por qué no hay ofertas confirmadas
						DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas_de_empresa = ICU.listarOfertasLaboralesVigentesConfirmadasPorEmpresa(usuario_nickname);
						//DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas_de_empresa = ICU.listarOfertasLaboralesPorEmpresa(usuario_nickname);
	
						request.setAttribute("dts_ofertas_laborales_vigentes_confirmadas_de_empresa", dts_ofertas_laborales_vigentes_confirmadas_de_empresa);
	
						request.getRequestDispatcher("pages/usuarios/verEmpresa.jsp?nickname="+usuario_nickname).
						forward(request, response);
	
					}
					if(dt_usuario instanceof DTPostulante) {
						request.setAttribute("dt_objeto_tipo", "postulante");
						request.setAttribute("dt_objeto", dt_usuario);
						request.getRequestDispatcher("pages/usuarios/verPostulante.jsp?nickname="+usuario_nickname).
						forward(request, response);
					}				
	
				} catch(ObjetoNoExisteException e){
					
					e.printStackTrace();
					
					response.sendError(404); // el usuario no existe
					request.getRequestDispatcher("pages/errorPages/404.jsp").
							include(request, response);
					return;
				}
	
				request.getRequestDispatcher("pages/usuarios/verUsuario.jsp?nickname="+usuario_nickname).
						forward(request, response);
			}
		}//Si es post
		else {

			String tipo_usuario = request.getParameter("tipo_usuario");
			String imagen = request.getParameter("imagen");
			
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			
			String nombre_empresa = request.getParameter("nombre_empresa");
			String sitio_web = request.getParameter("sitio_web");
			String descripcion = request.getParameter("descripcion");
			
			String fecha_nacimiento = request.getParameter("fecha_nacimiento");
			String nacionalidad = request.getParameter("nacionalidad");

			String nickname = request.getParameter("nickname");
			String correo = request.getParameter("correo");
			String clave = request.getParameter("clave");
			
			
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

			
			LinkedHashMap<String, String> mensajes_error = new LinkedHashMap<String, String>();			
			LinkedHashMap<String, String> mensajes_exito = new LinkedHashMap<String, String>();		
							
			
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
			
			
			if (tipo_usuario.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("tipo_usuario_vacio", "El campo tipo de usuario es obligatorio");
				
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
			
			if (nickname.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("nickname_vacio", "El campo nickname es obligatorio");
				
			}else {
				//VALIDAR FORMATO NICKNAME
				Boolean es_valido_usuario = validador.validarUsuario(nickname);
				if(!es_valido_usuario) {
					
					//MENSAJE ERROR
					mensajes_error.put(
			        		"nickname_invalido", "El campo nickname no es válido. "
			        		+ "El usuario sólo puede contener letras minúsculas, números, guión medio, guión bajo o punto."
			        		+ "");
					
				}

				
			}
			if (correo.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("correo_vacio", "El campo correo es obligatorio");
				
			}else {
				
				//VALIDAR FORMATO CORREO
				Boolean es_valido_correo = validador.validarCorreo(correo);
				if(!es_valido_correo) {
					
					//MENSAJE ERROR
					mensajes_error.put(
			        		"correo_invalido", "El campo correo no es válido. "
					        		+ "El correo debe ser una dirección de correo válida."
					        		+ "");				
				}
			}
			
			if (clave.isEmpty()) {
				
				//MENSAJE ERROR
				mensajes_error.put("clave_vacio", "El campo clave es obligatorio");
				
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
			

			//VALIDAR UNICIDAD NICKNAME
			if (ICU.existeUsuario(nickname)) {
				//MENSAJE ERROR
				mensajes_error.put("nickname_unicidad", "Ya existe un usuario con ese nickname. Por favor ingrese otro.");
			}
			//VALIDAR UNICIDAD CORREO
			///TODO: poner funcion crrecta unicadad correo
			if (ICU.existeUsuarioPorCorreo(correo)) {
				//MENSAJE ERROR
				mensajes_error.put("correo_unicidad", "Ya existe un usuario con ese correo. Por favor ingrese otro.");
			}
			
			//Fin Validaciones

			// Si hubo errores en el completado
			if (!mensajes_error.isEmpty()) {
				
				//System.out.println("HAY MENSAJES: "+mensajes);
				
				request.setAttribute("mensajes_error", mensajes_error);
				
				request.getRequestDispatcher("pages/usuarios/registrarUsuario.jsp").
						forward(request, response);
				
			} else {

			
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
						
						DTEmpresa data_empresa = new DTEmpresa(nickname, nombre, apellido, correo, clave, imagen,
								EnumRol.EMPRESA, nombre_empresa, descripcion, sitio_web);
		
						ICU.registrarEmpresa(data_empresa);
						
						mensajes_exito.put("registro_exito", "Usuario registrado exitosamente!");
						request.setAttribute("mensajes_exito", mensajes_exito);
						request.setAttribute("mensajes_error", null);
						
						
					} catch (ObjetoRepetidoException e) {
						
						e.printStackTrace();
					}
				
				}
				if (tipo_usuario.equals("postulante")) {					
				
					
					try {
						
						System.out.println("PARAMETOS FECHA: "+fecha_nacimiento);
						
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
						Date fecha_nacimiento_date;
						
						try {
							fecha_nacimiento_date = format.parse(fecha_nacimiento);
						} catch (ParseException e) {
							e.printStackTrace();
							fecha_nacimiento_date = null;
						}
						
						System.out.println("PARAMETOS FECHA: "+fecha_nacimiento_date);
						
						//////////////////Gestión archivo /////////////////////////
						
						
						if(filePart != null && filePart.getSize() > 0){
							
							String root = System.getProperty("user.dir");
							String ruta_nueva = root +"/eclipse-workspace/tpgr45/Tarea2.1/src/main/webapp/assets/images/usuarios/postulante";
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
	
	
						
						DTPostulante data_postulante = new DTPostulante(nickname, nombre, apellido, correo, clave, imagen, 
								EnumRol.POSTULANTE, fecha_nacimiento_date, nacionalidad);
	
						ICU.registrarPostulante(data_postulante);
						
						mensajes_exito.put("registro_exito", "Usuario registrado exitosamente!");
						request.setAttribute("mensajes_exito", mensajes_exito);
						request.setAttribute("mensajes_error", null);

						
					} catch (ObjetoRepetidoException e) {
						
						e.printStackTrace();
					}
	
				}
	
			    request.getRequestDispatcher("login.jsp").forward(request, response);

			}
			
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ENTRO GET");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			processRequest(request, response);
		} catch (ServletException | IOException | ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ENTRO PROCESS");

	}
	
	protected Boolean subirArchivo(InputStream is, String path) {
		
		Boolean resultado = false;
		
		try {
			
			byte[] byt = new byte[is.available()];
			is.read();
			FileOutputStream fops = new FileOutputStream(path);
			fops.write(byt);
			fops.flush();
			fops.close();
			
			resultado = true;
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return resultado;
	}

}