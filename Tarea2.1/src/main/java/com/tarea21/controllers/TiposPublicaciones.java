package com.tarea21.controllers;

import java.io.IOException;
import excepciones.ObjetoNoExisteException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import logica.Fabrica;
import logica.dts.DTTipoPublicacion;
import logica.interfaces.IControladorTipoPublicacion;

/**
 * Servlet implementation class TipoPublicacion
 */
@WebServlet("/tipos_publicaciones")
public class TiposPublicaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorTipoPublicacion ICTP;

    /**
     * Default constructor. 
     */
    public TiposPublicaciones() {
        
    	Fabrica fabrica = Fabrica.getInstance();
		this.ICTP = fabrica.getIControladorTipoPublicacion();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	String tipo_publicacion_nombre = request.getParameter("nombre");
    	if(tipo_publicacion_nombre == null) {
    		
			
			try {
				DTTipoPublicacion[] dts_tipo_publicacion;
				dts_tipo_publicacion = ICTP.listarTiposPublicaciones();
				request.setAttribute("dts_tipo_publicacion", dts_tipo_publicacion);
	
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("pages/tipos_publicaciones/listaTipoPublicacion.jsp").
					forward(request, response);
				
		}else {
			
			try {
				
				DTTipoPublicacion dt_tipo_publicacion = ICTP.getDataTipoPublicacion(tipo_publicacion_nombre);
								
				
				
				request.setAttribute("dt_objeto", dt_tipo_publicacion);
				
				
			} catch(ObjetoNoExisteException e){
				
				e.printStackTrace();
				
				
				response.sendError(404); // el usuario no existe
				request.getRequestDispatcher("pages/errorPages/404.jsp").
						include(request, response);
				return;
			}
			

			request.getRequestDispatcher("pages/tipos_publicaciones/verTipoPublicacion.jsp?nombre="+tipo_publicacion_nombre).
					forward(request, response);
		}
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
