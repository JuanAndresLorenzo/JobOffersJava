package com.tarea21.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.LinkedHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.Fabrica;
import logica.cargar_datos.cargarDatosInicio;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;

/**
 * Servlet implementation class Home
 */
@WebServlet ("/cargar-datos")
public class CargarDatos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorTipoPublicacion ICTP;
	private IControladorUsuario ICU;
	private IControladorOfertaLaboral IOL;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CargarDatos() {
        super();   
    
    }
	

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ObjetoNoRespetaFormatoException {
		
	       Fabrica fabrica = Fabrica.getInstance();
	    	
			this.ICTP = fabrica.getIControladorTipoPublicacion();
			this.ICU = fabrica.getIControladorUsuario();
			this.IOL = fabrica.getIControladorOfertaLaboral();
			
			
			//LinkedHashMap<String, Mensaje> mensajes = new LinkedHashMap<String, Mensaje>();			
			LinkedHashMap<String, String> mensajes = new LinkedHashMap<String, String>();			

	    	
	    	try {
	    		
	    		cargarDatosInicio.cargarPaquetes(ICTP);
				cargarDatosInicio.cargarTipoPublicacion(ICTP);
				cargarDatosInicio.cargarTipoPublicacionEnPaquete(ICTP);

	    		
				cargarDatosInicio.cargarEmpresas(ICU);
				cargarDatosInicio.cargarPostulantes(ICU);
				
				cargarDatosInicio.cargarKeywords(IOL);
				cargarDatosInicio.cargarOfertasLaborales(IOL);
				
				cargarDatosInicio.cargarPostulaciones(ICU);

				
				//MENSAJE ERROR
				//Mensaje mensaje = new Mensaje(EnumTipoMensaje.EXITO, "Se cargaron los datos correctamente.");
		        mensajes.put("datos_cargados", "Se cargaron los datos correctamente.");
				request.setAttribute("mensajes_exito", mensajes);
				
			} 
	    	catch (ObjetoNoExisteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
	    	catch (ObjetoRepetidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/").
			forward(request, response);
		
		
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
	}

}
