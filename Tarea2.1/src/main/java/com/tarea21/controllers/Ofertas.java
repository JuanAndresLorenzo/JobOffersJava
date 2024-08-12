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
import logica.dts.DTOfertaLaboral;
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
@WebServlet ("/ofertas")
public class Ofertas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorOfertaLaboral ICOL;
	private IControladorUsuario ICU;

	private String oferta_nombre;
	private DTUsuario dt_usuario_logueado;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ofertas() {
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

		this.oferta_nombre = request.getParameter("nombre");
		
		HttpSession objSesion = request.getSession();
		this.dt_usuario_logueado = (DTUsuario) objSesion.getAttribute("usuario_logueado");
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		
		//Listado
		if(oferta_nombre == null) {
			
			
			try {
				DTOfertaLaboral[] dts_objetos;
				//dts_objetos = ICOL.listarOfertasLaboralesVigentesConfirmadas();
				dts_objetos = ICOL.listarOfertasLaborales();
				request.setAttribute("dts_objetos", dts_objetos);
	
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
			}
			
			request.getRequestDispatcher("pages/ofertas/listaOfertas.jsp").
					forward(request, response);
				
		}else { //Ver oferta
			
			try {
									
				DTOfertaLaboral dt_oferta = ICOL.getDataOfertaLaboral(oferta_nombre);		
				request.setAttribute("dt_objeto", dt_oferta);

				//Si el usuario logueado es empresa
				if (dt_usuario_logueado instanceof DTEmpresa) {
					
					//Si la empresa logueada es la dueña de la oferta
					if(dt_usuario_logueado.getNickname().equals(dt_oferta.getEmpresa().getNickname())) {
						
						DTPostulanteOfertaLaboral[] dts_postulaciones_oferta_laboral_de_empresa_logueada = ICOL.listarPostulacionesPorOfertaLaboral(oferta_nombre);
						request.setAttribute("dts_postulaciones_oferta_laboral_de_empresa_logueada", dts_postulaciones_oferta_laboral_de_empresa_logueada);

						request.setAttribute("empresa_duenia_oferta", true);

						
					}				

				}
				
				//Si el usuario logueado es postulante
				if (dt_usuario_logueado instanceof DTPostulante) {
					
					//Si la empresa logueada es la dueña de la oferta
					if(ICU.existePostulacionAOferta(dt_usuario_logueado.getNickname(), oferta_nombre)) {
					
						DTPostulanteOfertaLaboral dt_postulacion_oferta_laboral_de_postulante_logueado = ICOL.getDataPostulacionOfertaLaboral(dt_oferta.getNombre(), dt_usuario_logueado.getNickname());
	
						request.setAttribute("dt_postulacion_oferta_laboral_de_postulante_logueado", dt_postulacion_oferta_laboral_de_postulante_logueado);
						request.setAttribute("postulante_hizo_postulacion", true);
					
					}

				}

				
			} catch(ObjetoNoExisteException e){
				
				e.printStackTrace();
			}
			

			request.getRequestDispatcher("pages/ofertas/verOferta.jsp?nombre="+oferta_nombre).
					forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processRequest(request, response);
	}

}
