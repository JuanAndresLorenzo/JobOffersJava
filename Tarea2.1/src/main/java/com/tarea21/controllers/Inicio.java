package com.tarea21.controllers;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import logica.Fabrica;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.interfaces.IControladorOfertaLaboral;

import com.tarea21.model.EstadoSesion;


import excepciones.ObjetoNoExisteException;

/**
 * Servlet implementation class Home
 */
@WebServlet ("")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IControladorOfertaLaboral IOL;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
        
        
	    Fabrica fabrica = Fabrica.getInstance();
	    	
		this.IOL = fabrica.getIControladorOfertaLaboral();
    }


	private void processRequest(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		
		//TODO: Revisar por qué no hay ofertas confirmadas
		DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas;
		try {
			//dts_ofertas_laborales_vigentes_confirmadas = IOL.listarOfertasLaboralesVigentesConfirmadas();
			dts_ofertas_laborales_vigentes_confirmadas = IOL.listarOfertasLaborales();

			request.setAttribute("dts_ofertas_laborales_vigentes_confirmadas", dts_ofertas_laborales_vigentes_confirmadas);

		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		DTKeyword[] dts_keywords;
		try {
			dts_keywords = IOL.listarKeywords();
			request.setAttribute("dts_keywords", dts_keywords);

		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		// hace que se ejecute el jsp sin cambiar la url
		request.getRequestDispatcher("/index.jsp").
				forward(request, resp);
				
		
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
