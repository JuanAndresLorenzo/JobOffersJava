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

import com.tarea21.model.EstadoSesion;
import com.tarea21.model.Usuario;

import logica.Fabrica;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTEmpresa;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;
import logica.dts.DTPostulante;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTTipoPublicacion;
import logica.dts.DTUsuario;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;
import logica.clases.EnumRol;

import logica.cargar_datos.cargarDatosInicio;


/**
 * Servlet implementation class Usuarios
 */
@WebServlet ("/postulaciones")
public class Postulaciones extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorOfertaLaboral ICOL;
	
	private String oferta_nombre;
	private String postulante_nombre;

	private DTUsuario dt_usuario_logueado;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Postulaciones() {
        super();
        
	    Fabrica fabrica = Fabrica.getInstance();
	    	
		this.ICOL = fabrica.getIControladorOfertaLaboral();
        
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

		this.oferta_nombre = request.getParameter("oferta");
		this.postulante_nombre = request.getParameter("postulante");

		HttpSession objSesion = request.getSession();
		this.dt_usuario_logueado = (DTUsuario) objSesion.getAttribute("usuario_logueado");
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
		//Listado no hay
		if(oferta_nombre == null || postulante_nombre == null ) {

			response.sendError(404); // el usuario no existe
			request.getRequestDispatcher("pages/errorPages/404.jsp").
					include(request, response);
			return;
			

				
		}else { //Ver postulacion
			
			
			System.out.println("Eoferta: "+oferta_nombre);
			System.out.println("Epostulante_nombre: "+postulante_nombre);
			
			try {
									
				DTPostulanteOfertaLaboral dt_postulacion = ICOL.getDataPostulacionOfertaLaboral(oferta_nombre, postulante_nombre);		
				
				//Si el usuario logueado es empresa
				if (dt_usuario_logueado instanceof DTEmpresa) {
					
					//Si la empresa logueada es la dueña de la postulacion
					if(dt_usuario_logueado.getNickname().equals(dt_postulacion.getOfertaLaboral().getEmpresa().getNickname())) {
						
						request.setAttribute("dt_objeto", dt_postulacion);
						request.setAttribute("empresa_duenia_oferta", true);
						
					}				

				}
				
				//Si el usuario logueado es postulante
				if (dt_usuario_logueado instanceof DTPostulante) {
					
					//Si el postulante logueado hizo postulaci{on
					if(dt_usuario_logueado.getNickname().equals(dt_postulacion.getPostulante().getNickname())) {
						
						request.setAttribute("dt_objeto", dt_postulacion);
						request.setAttribute("postulante_hizo_postulacion", true);
						
					}
									
				}

				
			} catch(ObjetoNoExisteException e){
				
				e.printStackTrace();
			}
			
		}

		request.getRequestDispatcher("pages/ofertas/postulaciones/verPostulacion.jsp?oferta="+oferta_nombre+"&postulante="+postulante_nombre).
				forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
