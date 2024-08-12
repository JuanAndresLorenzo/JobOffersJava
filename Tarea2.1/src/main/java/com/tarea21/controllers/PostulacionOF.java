package com.tarea21.controllers;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.cargar_datos.cargarDatosInicio;
import logica.clases.EnumRol;
import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPaquete;
import logica.dts.DTPostulante;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTTipoPublicacion;
import logica.dts.DTUsuario;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;

/**
 * Servlet implementation class PostulacionOF
 */
@WebServlet("/postulacionOF")
public class PostulacionOF extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorOfertaLaboral ICOL;
	private IControladorUsuario ICU;
	
	private String nombreOL;
	private String nombre_usuario;
	private DTUsuario dt_usuario_logueado;

    /**
     * Default constructor. 
     */
    public PostulacionOF() {
    	super();
        
	    Fabrica fabrica = Fabrica.getInstance();
	    	
		this.ICOL = fabrica.getIControladorOfertaLaboral();
		this.ICU = fabrica.getIControladorUsuario();

    }
    
    /** 
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.nombreOL = request.getParameter("nombreOL");
		
		
		HttpSession objSesion = request.getSession();
		this.dt_usuario_logueado = (DTUsuario) objSesion.getAttribute("usuario_logueado");
		
		if (dt_usuario_logueado!=null)
			this.nombre_usuario = this.dt_usuario_logueado.getNickname();
		else {
			//VER QUE HACER
		}
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		try {
			
			if ( (dt_usuario_logueado instanceof DTPostulante) && dt_usuario_logueado.getRol().equals(EnumRol.POSTULANTE)){
				
				DTPostulante dt_usuario_postulante_logueado = (DTPostulante) dt_usuario_logueado;
				Boolean postulanteAOfertaLaboral = ICU.existePostulacionAOferta(dt_usuario_postulante_logueado.getNickname(),nombreOL);
				
				if(postulanteAOfertaLaboral) {
				DTPostulanteOfertaLaboral dt_postulacion_a_ol = ICOL.getDataPostulacionOfertaLaboral(this.nombreOL, this.nombre_usuario);
					request.setAttribute("dt_objeto", dt_postulacion_a_ol);
				}
				
				request.setAttribute("dt_postulante", dt_usuario_postulante_logueado);
				DTOfertaLaboral dt_oferta_laboral = ICOL.getDataOfertaLaboral(this.nombreOL);
			
				
				request.setAttribute("dt_ofertaLaboral", dt_oferta_laboral);
				
				
				request.getRequestDispatcher("pages/usuarios/postulacionAOF.jsp").
				forward(request, response);
			}
			
		} catch (ObjetoNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
		LinkedHashMap<String, String> mensajes_error = new LinkedHashMap<String, String>();			
		LinkedHashMap<String, String> mensajes_exito = new LinkedHashMap<String, String>();	
		
		
		try {
			
			DTOfertaLaboral dt_oferta_laboral = ICOL.getDataOfertaLaboral(nombreOL);
			
			
			
			//OBTENER USUARIO EMPRESA LOGUEADO
			//Si el usuario logueado tiene perfil postulante y tiene rol postulante
			if ( (dt_usuario_logueado instanceof DTPostulante) && dt_usuario_logueado.getRol().equals(EnumRol.POSTULANTE)){
				
				DTPostulante dt_usuario_postulante_logueado = (DTPostulante) dt_usuario_logueado;
				
				
				String empresa_nickname = dt_usuario_postulante_logueado.getNickname();


				//VALIDAR QUE NO SE HAYA POSTULADO
				
				Boolean postulanteAOfertaLaboral = ICU.existePostulacionAOferta(dt_usuario_postulante_logueado.getNickname(),nombreOL);
				
				// si el postulante no se postulo, me postulo
				//if(!postulanteAOfertaLaboral) {
					//me postulo
					
					LinkedHashMap<String, String> mensajes = new LinkedHashMap<String, String>();	
					Validador validador = new Validador();
					
				    
				    String cv_reducido = request.getParameter("cv_reducido");
				    String motivacion = request.getParameter("motivacion");
				    String adjuntos = request.getParameter("adjuntos");
				    
					
				
					System.out.println("postulacion hecha");
					
					System.out.println("PAQUETES COMPRADOS POR EMPRESA: "+ICU.listarPostulacionesPorOfertaLaboral(nombreOL));
					
					if (cv_reducido.isEmpty()) {
						
						//MENSAJE ERROR
				        mensajes.put("cv_reducido_vacio", "El campo cv es obligatorio");
						
					}
					if (motivacion.isEmpty()) {
						
						//MENSAJE ERROR
				        mensajes.put("motivacion_vacio", "El campo motivacion es obligatorio");
						
					}
					
					// Si hubo errores en el completado
					if (!mensajes.isEmpty()) {
						
						//System.out.println("HAY MENSAJES: "+mensajes);
						
						request.setAttribute("mensajes_error", mensajes);
						
						request.getRequestDispatcher("pages/usuarios/postulacionAOF.jsp").
								forward(request, response);
						
					} 
					else {
					
						Date fecha_actual = new Date();
						
						DTPostulanteOfertaLaboral dt_postulacion = new DTPostulanteOfertaLaboral(fecha_actual,
									cv_reducido, motivacion, adjuntos, dt_usuario_postulante_logueado,
									dt_oferta_laboral);
						ICU.registrarPostulacionAOfertaLaboral(dt_postulacion);
						mensajes_exito.put("postulacion_exito", "Postulacion realizada exitosamente!");
						request.setAttribute("mensajes_exito", mensajes_exito);
						
						/*
						request.getRequestDispatcher("/index.jsp").
								forward(request, response);		*/
						response.sendRedirect("mi-perfil");

					}
			}else {	
					
					//MUESTRO MENSAJE ERROR
					//MENSAJE ERROR
					mensajes_error.put("usuario_sin_permiso", "El usuario actual no tiene permitido postularse. Debe ser postulante.");
			}	
					


			
			
			
			// Si hubo errores en el completado
			if (!mensajes_error.isEmpty()) {
				
				//System.out.println("HAY MENSAJES: "+mensajes);
				
				request.setAttribute("mensajes_error", mensajes_error);
				request.setAttribute("ofertaLaboral", dt_oferta_laboral);

				request.getRequestDispatcher("pages/paquetes/postulacionAOF.jsp").
				forward(request, response);
				
			}
			
			
		} catch (ObjetoNoExisteException e) {

			e.printStackTrace();
			
			e.printStackTrace();
			
			response.sendError(404); // el usuario no existe
			request.getRequestDispatcher("pages/errorPages/404.jsp").
					include(request, response);
			return;
		}

	}
		
	}


