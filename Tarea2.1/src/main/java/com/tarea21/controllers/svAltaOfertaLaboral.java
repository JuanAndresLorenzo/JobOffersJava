package com.tarea21.controllers;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;
import logica.Fabrica;
import logica.clases.Empresa;
import logica.clases.EnumEstadoOL;
import logica.clases.OfertaLaboral;
import logica.clases.Paquete;
import logica.clases.TipoPublicacion;
import logica.controladores.ControladorOfertaLaboral;
import logica.dts.DTEmpresa;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPaquete;
import logica.dts.DTTipoPublicacion;
import logica.dts.DTUsuario;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.manejadores.ManejadorOfertaLaboral;
import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorTipoPublicacion;
import logica.manejadores.ManejadorUsuario;

/**
 * Servlet implementation class svAltaOfertaLaboral
 */
@WebServlet("/svAltaOfertaLaboral")
@MultipartConfig(
		  fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		  maxFileSize = 1024 * 1024 * 10,      // 10 MB
		  maxRequestSize = 1024 * 1024 * 100   // 100 MB
		)
public class svAltaOfertaLaboral extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Fabrica fabrica = Fabrica.getInstance();
		IControladorOfertaLaboral COL = fabrica.getIControladorOfertaLaboral();
		DTKeyword[] arreglokey = null;
		try {
			arreglokey = COL.listarKeywords();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("key", arreglokey);

		/////////////////////////////////////////////////////////////////////////////

		IControladorTipoPublicacion CTP = fabrica.getIControladorTipoPublicacion();
		DTTipoPublicacion[] arreglotp = null;
		try {
			arreglotp = CTP.listarTiposPublicaciones();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("arrtp", arreglotp);

		////////////////////////////////////////////////////////////////////////////////

		DTPaquete[] arreglopaquetes = null;
		try {
			arreglopaquetes = CTP.listarPaquetes();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("arrpaq", arreglopaquetes);

		/////////////////////////////////////////////////////////////

		request.getRequestDispatcher("pages/ofertasLaborales/altaOL.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nombre = request.getParameter("nombre");

		String fecha_alta = request.getParameter("fecha_alta");
		Date fecha_alta_tf = null;
		try {
			fecha_alta_tf = new SimpleDateFormat("dd-MM-yyyy").parse(fecha_alta);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String descripcion = request.getParameter("descripcion");
		String ciudad = request.getParameter("ciudad");
		String departamento = request.getParameter("departamento");
		String horario = request.getParameter("horario");

		String imagen = request.getParameter("imagen");

		///////////////////// Obtener archivo imagen/////////////////////////////////

		Part filePart = request.getPart("imagen");
		String fileName = filePart.getSubmittedFileName();
		String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

		// System.out.println("IMAGEN: "+imagen);

		System.out.println("PART: " + filePart);

		System.out.println("PART NO NULL: " + filePart != null);

		///////////////////// Fin Obtener archivo
		///////////////////// imagen/////////////////////////////////

		String remuneracion = request.getParameter("remuneracion");
		Float remuneracion_ti = Float.parseFloat(remuneracion);

		String tipoPublicacionString = request.getParameter("tipo_publicacion");
		ManejadorTipoPublicacion mtp = ManejadorTipoPublicacion.getinstance();
		TipoPublicacion tipoPublicacionObjeto = mtp.obtener(tipoPublicacionString);
		DTTipoPublicacion tipoPublicacionDT = tipoPublicacionObjeto.getDT();

		String empresaString = request.getParameter("empresa");
		ManejadorUsuario musuario = ManejadorUsuario.getinstance();
		HttpSession objSesion = request.getSession();
		DTUsuario data_usaurio = (DTUsuario) objSesion.getAttribute("usuario_logueado");
		Empresa empresaObjeto = (Empresa) musuario.obtener(data_usaurio.getNickname());
		DTEmpresa empresaDT = empresaObjeto.getDT();

		String paqueteString = request.getParameter("paquete");
		String[] keywords = request.getParameterValues("keywords");

		List<String> keylist = new ArrayList<String>();
		for (int i = 0; i < keywords.length; i++) {
			keylist.add(keywords[i]);
		}

		// Validaciones

		Validador validador = new Validador();

		LinkedHashMap<String, String> mensajes_error = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> mensajes_exito = new LinkedHashMap<String, String>();

		// Valido que la imagen sea de un tipo de imagen
		if (filePart != null && !extension.isEmpty() && !validador.validarTipoImagen(extension)) {
			// MENSAJE ERROR
			mensajes_error.put("imagen_tipo",
					"El formato de la imagen debe ser: png, jpg o jpeg. Su archivo es de tipo: " + extension);

		}

		// Si el tamaño en bytes excede los 10MB
		if (filePart != null && filePart.getSize() > 10000000) {
			// MENSAJE ERROR
			mensajes_error.put("imagen_tamanio", "El tamaño de la im{agen no puede superar los 10MB");

		}

		// Fin Validaciones

		// Si hubo errores en el completado
		if (!mensajes_error.isEmpty()) {

			// System.out.println("HAY MENSAJES: "+mensajes);

			request.setAttribute("mensajes_error", mensajes_error);

			request.getRequestDispatcher("pages/ofertasLaborales/altaOL.jsp").forward(request, response);

		} else {

			////////////////// Gestión archivo /////////////////////////

			if (filePart != null && filePart.getSize() > 0) {

				String root = System.getProperty("user.dir");
				String ruta_nueva = root
						+ "/eclipse-workspace/tpgr45/Tarea2.1/src/main/webapp/assets/images/ofertas";
				File directorio = new File(ruta_nueva);
				if (!directorio.exists()) {
					directorio.mkdir();
				}

				imagen = nombre + "." + extension;

				// String ruta_nueva_con_archivo = ruta_nueva + File.separator + fileName;
				String ruta_nueva_con_archivo = ruta_nueva + File.separator + imagen;

				System.out.println("PATH: " + ruta_nueva);
				// System.out.println("IMAGEN: "+imagen);
				System.out.println("PART: " + filePart);
				System.out.println("FILANEMA: " + fileName);
				System.out.println("ext: " + extension);
				System.out.println("tamaño: " + filePart.getSize());

				// GUARDAR ARCHIVO
				filePart.write(ruta_nueva_con_archivo);

			} else {
				imagen = "";
			}

			////////////////// Fin Gestión archivo /////////////////////////

			DTOfertaLaboral dtol = new DTOfertaLaboral(nombre, descripcion, ciudad, departamento, horario,
					remuneracion_ti, fecha_alta_tf, "ingresada", paqueteString, imagen, keylist, empresaDT,
					tipoPublicacionDT);
			Fabrica fabrica = Fabrica.getInstance();
			IControladorOfertaLaboral ICOL = fabrica.getIControladorOfertaLaboral();
			try {
				ICOL.registrarOfertaLaboral(dtol);
			} catch (ObjetoRepetidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// request.getRequestDispatcher("/").forward(request, response);

			// manda una redirección a otra URL (cambia la URL), sino sigue con post y da
			// problemas
			response.sendRedirect("mi-perfil");

		}

	}
}