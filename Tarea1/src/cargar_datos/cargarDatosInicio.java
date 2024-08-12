package cargar_datos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.clases.EnumRol;
import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPaquete;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTTipoPublicacion;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;

public class cargarDatosInicio {

	private static Map<String, DTPostulante> mappos = new HashMap<String, DTPostulante>();
	private static Map<String, DTEmpresa> mapemp = new HashMap<String, DTEmpresa>();

	private static Map<String, DTPaquete> mappaquetes = new HashMap<String, DTPaquete>();
	private static Map<String, DTTipoPublicacion> maptp = new HashMap<String, DTTipoPublicacion>();
	private static Map<String, DTOfertaLaboral> mapol = new HashMap<String, DTOfertaLaboral>();
	private static Map<String, String> mapk = new HashMap<String, String>();

	private static String inicioPath = System.getProperty("user.dir");
	private static String basePath = inicioPath + "/src/cargar_datos/";

	public cargarDatosInicio() {

	}

	public static void cargarKeywords(IControladorOfertaLaboral ICOL) throws ObjetoRepetidoException {
		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "Keywords.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");
				String nombre = campos[1].trim();
				mapk.put(campos[0], nombre);

				ICOL.registrarKeyword(nombre);

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();
			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarPostulantes(IControladorUsuario ICU)
			throws ObjetoRepetidoException, IOException, ParseException, ObjetoNoRespetaFormatoException {

		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "Usuarios.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");
				String tipo = campos[1].trim();

				if (tipo.equals("P")) {

					String codigo = campos[0].trim();
					String nickname = campos[2].trim().toLowerCase();
					String nombre = campos[3].trim();
					String apellido = campos[4].trim();
					String email = campos[5].trim().toLowerCase();
					String clave = campos[6].trim();

					String img = basePath + "cargar_datos.img.Usuarios/" + "nickname" + ".jpg";

					EnumRol rol = EnumRol.POSTULANTE;

					Date fecha_nac = null;
					String nacionalidad = "";

					DTPostulante dtpostulante = new DTPostulante(nickname, nombre, apellido, email, clave, img, rol,
							fecha_nac, nacionalidad);

					mappos.put(codigo, dtpostulante);
				}

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();

			}
			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader bufferLectura2 = null;
		bufferLectura2 = new BufferedReader(new FileReader(basePath + "Usuarios-Postulantes.csv"));

		String linea2 = bufferLectura2.readLine();
		linea2 = bufferLectura2.readLine();

		while (linea2 != null) {

			String[] campos = linea2.split(";");

			String codigo = campos[0].trim();

			DTPostulante postulante = (DTPostulante) mappos.get(codigo);

			String fecha = campos[1].trim();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date fechaFormat = formatter.parse(fecha);

			String nac = campos[2].trim();

			DTPostulante dtpostulante2 = new DTPostulante(postulante.getNickname(), postulante.getNombre(),
					postulante.getApellido(), postulante.getCorreo(), postulante.getClave(), postulante.getImagen(),
					postulante.getRol(), fechaFormat, nac);

			ICU.registrarPostulante(dtpostulante2);

			// Leer otra línea del archivo
			linea2 = bufferLectura2.readLine();
		}

		// Cierro el buffer de lectura
		bufferLectura2.close();

	}

	public static void cargarEmpresas(IControladorUsuario ICU)
			throws ObjetoRepetidoException, IOException, ObjetoNoRespetaFormatoException {

		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "Usuarios.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");
				String tipo = campos[1].trim();

				if (tipo.equals("E")) {

					String codigo = campos[0].trim();
					String nickname = campos[2].trim().toLowerCase();
					String nombre = campos[3].trim();
					String apellido = campos[4].trim();
					String email = campos[5].trim().toLowerCase();
					String clave = campos[6].trim();

					String img = basePath + "cargar_datos.img.Usuarios/" + "nickname" + ".jpg";

					EnumRol rol = EnumRol.EMPRESA;

					String nombre_empresa = "";
					String descripcion = "";
					String sitio_web = "";

					DTEmpresa dtempresa = new DTEmpresa(nickname, nombre, apellido, email, clave, img, rol,
							nombre_empresa, descripcion, sitio_web);

					mapemp.put(codigo, dtempresa);

				}

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();

			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		BufferedReader bufferLectura2 = null;
		bufferLectura2 = new BufferedReader(new FileReader(basePath + "Usuarios-Empresas.csv"));

		String linea2 = bufferLectura2.readLine();
		linea2 = bufferLectura2.readLine();

		while (linea2 != null) {

			String[] campos = linea2.split(";");

			String codigo = campos[0].trim();

			DTEmpresa empresa = (DTEmpresa) mapemp.get(codigo);

			String desc = campos[1].trim();
			String web = campos[2].trim().toLowerCase();

			DTEmpresa dtempresa2 = new DTEmpresa(empresa.getNickname(), empresa.getNombre(), empresa.getApellido(),
					empresa.getCorreo(), empresa.getClave(), empresa.getImagen(), empresa.getRol(), codigo, desc, web);

			ICU.registrarEmpresa(dtempresa2);

			// Leer otra línea del archivo
			linea2 = bufferLectura2.readLine();
		}

		// Cierro el buffer de lectura
		bufferLectura2.close();

	}

	public static void cargarPaquetes(IControladorTipoPublicacion ICTP)
			throws ObjetoRepetidoException, ParseException, ObjetoNoRespetaFormatoException {
		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "Paquetes.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");

				String codigo = campos[0].trim();
				String nombre = campos[1].trim();
				String des = campos[2].trim();

				String per = campos[3].trim();
				Integer periodo = Integer.valueOf(per);

				String desc = campos[4].trim();
				Float descuento = Float.parseFloat(desc);

				String fecha = campos[5].trim();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				Date fechaFormat = formatter.parse(fecha);

				String costo = campos[6].trim();
				Float costofloat = Float.parseFloat(costo);

				String img = basePath + "cargar_datos.img.paquete/" + "codigo" + ".jpg";

				DTPaquete dtpaquete = new DTPaquete(nombre, des, periodo, descuento, costofloat, fechaFormat, img);

				mappaquetes.put(codigo, dtpaquete);
				ICTP.ingresarPaquete(dtpaquete);

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();

			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarTipoPublicacion(IControladorTipoPublicacion ICTP)
			throws ObjetoRepetidoException, ParseException, ObjetoNoRespetaFormatoException {
		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "TipoPublicacion.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");

				String codigo = campos[0].trim();
				String nombre = campos[1].trim();
				String des = campos[2].trim();

				String exp = campos[3].trim();
				Integer exposicion = Integer.valueOf(exp);

				String dur = campos[4].trim();
				Integer duracion = Integer.valueOf(dur);

				String cos = campos[5].trim();
				Float costo = Float.parseFloat(cos);

				String fecha = campos[6].trim();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				Date fechaFormat = formatter.parse(fecha);

				DTTipoPublicacion dttp = new DTTipoPublicacion(nombre, des, duracion, exposicion, costo, fechaFormat);

				maptp.put(codigo, dttp);
				ICTP.ingresarTipoPublicacion(dttp);

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();

			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarTipoPublicacionEnPaquete(IControladorTipoPublicacion ICTP)
			throws ObjetoRepetidoException, ParseException, ObjetoNoExisteException {
		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "TiposPublicacionPaquetes.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");
				String codigop = campos[1].trim();
				String codigotp = campos[2].trim();

				String cant = campos[3].trim();
				Integer cantidad = Integer.valueOf(cant);

				DTPaquete p = (DTPaquete) mappaquetes.get(codigop);

				DTTipoPublicacion tp = maptp.get(codigotp);

				ICTP.agregarTipoPublicacionAPaquete(p.getNombre(), tp.getNombre(), cantidad);

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();

			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarOfertasLaborales(IControladorOfertaLaboral ICOL)
			throws ObjetoRepetidoException, ParseException, ObjetoNoExisteException {
		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "OfertasLaborales.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");

				String codigo = campos[0].trim();

				String nombre = campos[1].trim();
				String desc = campos[2].trim();
				String departamento = campos[3].trim();
				String ciudad = campos[4].trim();
				String horario = campos[5].trim();

				String rem = campos[6].trim();
				Float remuneracion = Float.parseFloat(rem);

				String ce = campos[7].trim();
				String ctp = campos[8].trim();

				String fecha = campos[9].trim();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				Date fechaFormat = formatter.parse(fecha);

				String estado = campos[10].trim();
				String paquete = campos[11].trim();
				String img = basePath + "cargar_datos.img.ol/" + "codigo" + ".jpg";

				DTEmpresa empresa = mapemp.get(ce);
				DTTipoPublicacion tipopublicacion = maptp.get(ctp);

				BufferedReader bufferLectura2 = null;
				bufferLectura2 = new BufferedReader(new FileReader(basePath + "OfertasLaboralesKeywords.csv"));

				String linea2 = bufferLectura2.readLine();
				linea2 = bufferLectura2.readLine();

				String kw;

				List<String> keylist = new ArrayList<String>();

				while (linea2 != null) {

					String[] campos2 = linea2.split(";");
					String codigo2 = campos2[0].trim();

					if (codigo2.equals(codigo)) {

						kw = campos2[1].trim();
						String[] camposkey = kw.split(",");

						Integer i;

						for (i = 0; i < camposkey.length; i++) {

							String p = mapk.get(camposkey[i].trim());
							keylist.add(p);
							// System.out.println(camposkey[i].trim());

						}

					}

					// Leer otra línea del archivo
					linea2 = bufferLectura2.readLine();

				}

				DTOfertaLaboral dtol2 = new DTOfertaLaboral(nombre, desc, ciudad, departamento, horario, remuneracion,
						fechaFormat, estado, paquete, img, keylist, empresa, tipopublicacion);

				ICOL.registrarOfertaLaboral(dtol2);

				mapol.put(codigo, dtol2);
				linea = bufferLectura.readLine();

				bufferLectura2.close();
			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void cargarPostulaciones(IControladorUsuario ICU)
			throws ObjetoRepetidoException, ParseException, ObjetoNoExisteException {
		try {

			BufferedReader bufferLectura = null;
			bufferLectura = new BufferedReader(new FileReader(basePath + "Postulaciones.csv"));

			String linea = bufferLectura.readLine();
			linea = bufferLectura.readLine();

			while (linea != null) {

				String[] campos = linea.split(";");

				String codigo = campos[0].trim();

				String nombre = campos[1].trim();
				String cv = campos[2].trim();
				String motivacion = campos[3].trim();

				String fecha = campos[4].trim();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
				Date fechaFormat = formatter.parse(fecha);

				String oferta = campos[5].trim();

				DTPostulante postulante = mappos.get(nombre);
				DTOfertaLaboral ol = mapol.get(oferta);

				DTPostulanteOfertaLaboral dt = new DTPostulanteOfertaLaboral(fechaFormat, cv, motivacion, "",
						postulante, ol);

				// mapol.put(codigo, dt);

				ICU.registrarPostulacionAOfertaLaboral(dt);

				// Leer otra línea del archivo
				linea = bufferLectura.readLine();

			}

			// Cierro el buffer de lectura
			bufferLectura.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cargarCompraPaquetes(IControladorTipoPublicacion ICTP)
			throws ObjetoNoExisteException, IOException, ParseException {

		BufferedReader bufferLectura = null;
		bufferLectura = new BufferedReader(new FileReader(basePath + "PaquetesCompras.csv"));

		String linea = bufferLectura.readLine();
		linea = bufferLectura.readLine();

		while (linea != null) {

			String[] campos = linea.split(";");

			// String codigo = campos[0].trim();

			String empresa = campos[1].trim();
			String paquete = campos[2].trim();

			String fecha = campos[3].trim();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
			Date fechaFormat = formatter.parse(fecha);

			String costo = campos[4].trim();
			Float costofloat = Float.parseFloat(costo);

			DTEmpresa empresadt = mapemp.get(empresa);
			DTPaquete paquetedt = mappaquetes.get(paquete);

			// mapol.put(codigo, dt);

			ICTP.compraPaquete(empresadt.getNickname(), paquetedt.getNombre(), fechaFormat, costofloat);
			// Leer otra línea del archivo
			linea = bufferLectura.readLine();

		}

		// Cierro el buffer de lectura
		bufferLectura.close();

	}

}