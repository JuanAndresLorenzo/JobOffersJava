package com.tarea21.controllers;

import java.io.IOException;
import java.util.LinkedHashMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tarea21.model.EstadoSesion;

/**
 * Servlet implementation class Home
 */
@WebServlet ("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * inicializa la sesión si no estaba creada 
	 * @param request 
	 */
	public static void initSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("paginas_navegadas") == null) {
			session.setAttribute("paginas_navegadas", 0);
		}
		if (session.getAttribute("estado_sesion") == null) {
			session.setAttribute("estado_sesion", EstadoSesion.NO_LOGIN);
		}
	}
	
	/**
	 * Devuelve el estado de la sesión
	 * @param request
	 * @return 
	 */
	public static EstadoSesion getEstado(HttpServletRequest request)
	{
		return (EstadoSesion) request.getSession().getAttribute("estado_sesion");
	}

	private void processRequest(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		initSession(req);
		
		System.out.println("MENSAJES: "+req.getParameter("mensajes_error"));
		
		LinkedHashMap<String, String> mensajes_error = new LinkedHashMap<String, String>();			

		
		switch(getEstado(req)){
			case NO_LOGIN:
	
				// manda una redirección a otra URL (cambia la URL)
				resp.sendRedirect("/Tarea2.1/");				
				
				break;
			case LOGIN_INCORRECTO:
				

				//MENSAJE ERROR
				// No se aclara el error específico para no dar datos
				mensajes_error.put("login_incorrecto", "Datos de acceso incorrectos.");
				
				req.setAttribute("mensajes_error", mensajes_error);

				// hace que se ejecute el jsp sin cambiar la url
				req.getRequestDispatcher("/login.jsp").
						forward(req, resp);
				break;
			case LOGIN_CORRECTO:
				// manda una redirección a otra URL (cambia la URL)
				resp.sendRedirect("mi-perfil");
				break;
		}
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
