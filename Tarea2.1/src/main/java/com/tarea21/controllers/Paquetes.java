package com.tarea21.controllers;

import java.io.IOException;
import java.text.ParseException;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import logica.Fabrica;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTEmpresa;
import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;
import logica.dts.DTTipoPublicacion;
import logica.dts.DTUsuario;
import logica.cargar_datos.cargarDatosInicio;
import logica.clases.EnumRol;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;



/**
 * Servlet implementation class Usuarios
 */
@WebServlet ("/paquetes")
public class Paquetes extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorTipoPublicacion ICTP;
	private IControladorUsuario ICU;
	
	private String paquete_nombre;
	private DTUsuario dt_usuario_logueado;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Paquetes() {
        super();
        
	    Fabrica fabrica = Fabrica.getInstance();
	    	
		this.ICTP = fabrica.getIControladorTipoPublicacion();
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

		this.paquete_nombre = request.getParameter("nombre");
		
		HttpSession objSesion = request.getSession();
		this.dt_usuario_logueado = (DTUsuario) objSesion.getAttribute("usuario_logueado");
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
		//Listado
		if(paquete_nombre == null) {
			
			
			try {
				DTPaquete[] dts_objetos;
				dts_objetos = ICTP.listarPaquetes();
				request.setAttribute("dts_objetos", dts_objetos);
	
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("pages/paquetes/listaPaquetes.jsp").
					forward(request, response);
				
		}else { //Ver paquete
			
			try {
									
				DTPaquete dt_paquete = ICTP.getDataPaquete(paquete_nombre);			
				//DTPaqueteTipoPublicacion[] dt_paquete_tps = ICTP.listarTiposPublicacionesPorPaquete(paquete_nombre);
				
				request.setAttribute("dt_objeto", dt_paquete);
				//request.setAttribute("dt_paquete_tps", dt_paquete_tps);
				
				//Si el usuario logueado es empresa
				if (dt_usuario_logueado instanceof DTEmpresa) {
				    
					Boolean empresaYaComproPaquete = ICTP.existeCompraPaquetePorEmpresa(paquete_nombre, dt_usuario_logueado.getNickname());

					if(!empresaYaComproPaquete) {
						
						request.setAttribute("puede_comprar_paquete", true);

					}else {
						request.setAttribute("puede_comprar_paquete", false);

					}
					
					
				}else {
					request.setAttribute("puede_comprar_paquete", false);
					
				}
				
				
				
			} catch(ObjetoNoExisteException e){
				
				e.printStackTrace();
				
				
				response.sendError(404); // el usuario no existe
				request.getRequestDispatcher("pages/errorPages/404.jsp").
						include(request, response);
				return;
			}
			

			request.getRequestDispatcher("pages/paquetes/verPaquete.jsp?nombre="+paquete_nombre).
					forward(request, response);
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
			
			DTPaquete dt_paquete = ICTP.getDataPaquete(paquete_nombre);				
			
			
			System.out.println("USUARIO LOGUEADO PAQ: "+dt_usuario_logueado);
			System.out.println("PAQUETE: "+dt_paquete);

			//OBTENER USUARIO EMPRESA LOGUEADO
			//Si el usuario logueado tiene perfil empresa y tiene rol empresa
			if ( (dt_usuario_logueado instanceof DTEmpresa) && dt_usuario_logueado.getRol().equals(EnumRol.EMPRESA)){

				DTEmpresa dt_usuario_empresa_logueado = (DTEmpresa) dt_usuario_logueado;
				
				String empresa_nickname = dt_usuario_empresa_logueado.getNickname();


				//VALIDAR QUE NO SE HAYA COMPRADO YA
				
				Boolean empresaYaComproPaquete = ICTP.existeCompraPaquetePorEmpresa(paquete_nombre, empresa_nickname);
				
				// si la empresa aún nmo compró el paquete, entonces lo compro
				if(!empresaYaComproPaquete) {
					//COMPRAR PAQUETE
				    Date fecha_actual = new Date();
					ICTP.compraPaquete(empresa_nickname, paquete_nombre, fecha_actual, dt_paquete.getCosto());
					
					//System.out.println("empresa paquetes: "+);
					System.out.println("PAQUETE COMPRADO");
					
					System.out.println("PAQUETES COMPRADOS POR EMPRESA: "+ICU.listarPaquetesCompradosPorEmpresa(empresa_nickname));
					
					
					mensajes_exito.put("paquete_comprado_exito", "Paquete comprado exitosamente!");
					request.setAttribute("mensajes_exito", mensajes_exito);
					
					
					
					System.out.println("okk mando a mi perfil PERFIL");

					/*
					request.getRequestDispatcher("mi-perfil").
							forward(request, response);			*/
					
					// manda una redirección a otra URL (cambia la URL), sino sigue con post y da problemas
					response.sendRedirect("mi-perfil");


					
				}else {
					
					System.out.println("PAQUETE YA COMPRADO");

					//MENSAJE ERROR
					mensajes_error.put("paquete_ya_comprado", "El usuario actual ya compró ese paquete. No puede comprar un paquete dos veces.");
				}


			}else {
				
				System.out.println("USUARIO SIN PERMISO DE COMPRA NO ES EMPRESA");

				//MUESTRO MENSAJE ERROR
				//MENSAJE ERROR
				mensajes_error.put("usuario_sin_permiso", "El usuario actual no tiene permitido comprar paquetes. Debe ser una empresa.");
				
			}
			
			
			// Si hubo errores en el completado
			if (!mensajes_error.isEmpty()) {
				
				//System.out.println("HAY MENSAJES: "+mensajes);
				
				request.setAttribute("mensajes_error", mensajes_error);
				request.setAttribute("dt_objeto", dt_paquete);

				request.getRequestDispatcher("pages/paquetes/verPaquete.jsp").
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
