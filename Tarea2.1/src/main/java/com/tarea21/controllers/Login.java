package com.tarea21.controllers;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.tarea21.model.EstadoSesion;
import logica.Fabrica;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTUsuario;
import logica.dts.DTEmpresa;
import logica.dts.DTPostulante;
import excepciones.ObjetoNoExisteException;

/**
 * Servlet implementation class Login
 */
@WebServlet("/iniciar-sesion")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IControladorUsuario ICU;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		HttpSession objSesion = request.getSession();
		String login = request.getParameter("usuario");
		String password = request.getParameter("clave");
		EstadoSesion nuevoEstado;
		
		// chequea contraseña
		try {
			
			//Usuario usr = GestorUsuario.getInstance().buscarUsuario(login);
			DTUsuario dt_usuario = ICU.getDataUsuario(login);
			
			if (!dt_usuario.getClave().equals(password)) {
			//if (!usr.getPassword().equals(password))
				nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;
			
		        
			}else {
				nuevoEstado = EstadoSesion.LOGIN_CORRECTO;
				// setea el usuario logueado
				request.getSession().setAttribute("usuario_logueado", dt_usuario);
				
				//Reviso que tipo de usuario es
				String tipo_usuario = "";
				//DTUsuario usuario = null;
				if (dt_usuario instanceof DTEmpresa) {
				    tipo_usuario = "empresa";	
					request.getSession().setAttribute("usuario_logueado_tipo", tipo_usuario);
				}
				if (dt_usuario instanceof DTPostulante) {
				    tipo_usuario = "postulante";
					request.getSession().setAttribute("usuario_logueado_tipo", tipo_usuario);

				}
				
			}
			
			System.out.println("ALGO HIZO");

		}catch (ObjetoNoExisteException e) {
			
			System.out.println("NO EXISTE USER");

			//e.printStackTrace();

			nuevoEstado = EstadoSesion.LOGIN_INCORRECTO;			
			
		}
		
		objSesion.setAttribute("estado_sesion", nuevoEstado);
		
		// redirige a la página principal para que luego rediriga a la página
		// que corresponde
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}
