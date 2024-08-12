package logica.controladores;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.clases.Empresa;
import logica.clases.EmpresaPaquete;
import logica.clases.EnumEstadoOL;
import logica.clases.OfertaLaboral;
import logica.clases.Postulante;
import logica.clases.PostulanteOfertaLaboral;
import logica.clases.Usuario;
import logica.dts.DTEmpresa;
import logica.dts.DTEmpresaPaquete;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTUsuario;

import logica.interfaces.IControladorUsuario;
//import logica.interfaces.string;
import logica.manejadores.ManejadorOfertaLaboral;
import logica.manejadores.ManejadorUsuario;
import validaciones.Validador;

public class ControladorUsuario implements IControladorUsuario {

	public ControladorUsuario() {
	}

	public DTUsuario[] listarUsuarios() throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Usuario[] usuarios = manejadorUsuario.listar();

		if (usuarios != null) {
			DTUsuario[] data_usuario = new DTUsuario[usuarios.length];
			Usuario usuario;

			// Para separar lógica de presentación, no se deben devolver los Usuario,
			// sino los DataUsuofertas_laboralesario
			for (int i = 0; i < usuarios.length; i++) {
				usuario = usuarios[i];
				if (usuario instanceof Empresa) {
					Empresa empresa = (Empresa) usuario;
					data_usuario[i] = empresa.getDT();
				} else if (usuario instanceof Postulante) {
					Postulante postulante = (Postulante) usuario;
					data_usuario[i] = postulante.getDT();
				}
			}

			return data_usuario;
		} else
			throw new ObjetoNoExisteException("No existen empresas registradas");
	}

	public DTEmpresa[] listarEmpresas() throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		List<Empresa> empresas = manejadorUsuario.listarEmpresas();

		if (empresas != null) {
			DTEmpresa[] data_empresa = new DTEmpresa[empresas.size()];
			Empresa empresa;

			// Para separar lógica de presentación, no se deben devolver los Usuario,
			// sino los DataUsuario
			for (int i = 0; i < empresas.size(); i++) {
				empresa = empresas.get(i);
				data_empresa[i] = empresa.getDT();
			}

			return data_empresa;
		} else
			throw new ObjetoNoExisteException("No existen empresas registradas");
	}

	public DTPostulante[] listarPostulantes() throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		List<Postulante> postulantes = manejadorUsuario.listarPostulantes();

		if (postulantes != null) {
			DTPostulante[] data_postulante = new DTPostulante[postulantes.size()];
			Postulante postulante;

			// Para separar lógica de presentación, no se deben devolver los Usuario,
			// sino los DataUsuario
			for (int i = 0; i < postulantes.size(); i++) {
				postulante = postulantes.get(i);
				data_postulante[i] = postulante.getDT();
			}

			return data_postulante;
		} else
			throw new ObjetoNoExisteException("No existen postulantes registrados");
	}

	public DTOfertaLaboral[] listarOfertasLaborales() throws ObjetoNoExisteException {

		ControladorOfertaLaboral cof = new ControladorOfertaLaboral();
		return cof.listarOfertasLaborales();

	}

	public Boolean existeUsuario(String usuario_nickname) {

		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Usuario usuario = manejadorUsuario.obtener(usuario_nickname);

		if (usuario != null) {
			return true;
		}
		return false;
	}
	
	public Boolean existeUsuarioPorCorreo(String usuario_correo) {
		
		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Usuario usuario = mu.obtenerUsuarioPorCorreo(usuario_correo);

		if (usuario != null) {
			return true;
		}
		return false;
		
	}


	public Boolean existePostulacionAOferta(String postulante_usuario_nickname, String oferta_laboral_nombre) {

		ManejadorUsuario m_u = ManejadorUsuario.getinstance();
		Postulante postulante = m_u.obtenerPostulante(postulante_usuario_nickname);

		if (postulante != null) {

			return postulante.existePostulacion(oferta_laboral_nombre);

		}
		return false;
	}

	public DTUsuario getDataUsuario(String nickname) throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Usuario usuario = manejadorUsuario.obtener(nickname);
		if (usuario != null) {
			DTUsuario data_usuario;
			if (usuario instanceof Empresa) {
				Empresa empresa = (Empresa) usuario;
				data_usuario = empresa.getDT();
			} else {
				Postulante postulante = (Postulante) usuario;
				data_usuario = postulante.getDT();
			}

			return data_usuario;
		} else {
			throw new ObjetoNoExisteException("El usuario no existe en el sistema");
		}
	}

	public DTOfertaLaboral getDataOfertaLaboral(String nombre) throws ObjetoNoExisteException {
		ControladorOfertaLaboral col = new ControladorOfertaLaboral();
		try {
			return col.getDataOfertaLaboral(nombre);
		} catch (ObjetoNoExisteException e) {
			throw e;
		}
	}

	public DTOfertaLaboral getDataOfertaLaboralPorEmpresa(String nombre_oferta_laboral, String nombre_empresa)
			throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Empresa empresa = manejadorUsuario.obtenerEmpresa(nombre_empresa);

		if (empresa != null) {
			OfertaLaboral oferta_laboral = empresa.getOfertaLaboral(nombre_oferta_laboral);
			if (oferta_laboral != null) {
				return oferta_laboral.getDT();
			} else {
				throw new ObjetoNoExisteException("La oferta laboral ingresada no pertenece a esta empresa.");
			}
		} else {
			throw new ObjetoNoExisteException("La empresa ingresada no esta registrada.");
		}
	}

	public DTOfertaLaboral[] listarOfertasLaboralesPorEmpresa(String nombre_empresa) throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Empresa empresa = manejadorUsuario.obtenerEmpresa(nombre_empresa);
		if (empresa != null) {

			OfertaLaboral[] ofertas_laborales = empresa.getOfertasLaboralesArray();
			OfertaLaboral oferta_laboral;

			if (ofertas_laborales != null) {
				
				DTOfertaLaboral[] data_oferta_laboral = new DTOfertaLaboral[ofertas_laborales.length];
				
				for (int i = 0; i < ofertas_laborales.length; i++) {
					
					oferta_laboral = ofertas_laborales[i];
					data_oferta_laboral[i] = oferta_laboral.getDT();
				}
				return data_oferta_laboral;

			} else {
				throw new ObjetoNoExisteException("Esta empresa no posee ninguna oferta laboral en este momento");
			}
			
		} else {
			throw new ObjetoNoExisteException("La empresa ingresada, no esta registrada en el sistema");
		}
	}

	public DTOfertaLaboral[] listarOfertasLaboralesIngresadasPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Empresa empresa = manejadorUsuario.obtenerEmpresa(nombre_empresa);

		List<OfertaLaboral> ofertas_laborales_ingresadas_list = empresa.getOfertasLaboralesIngresadas();

		if (ofertas_laborales_ingresadas_list != null) {

			OfertaLaboral[] ofertas_laborales_ingresadas_array = new OfertaLaboral[ofertas_laborales_ingresadas_list
					.size()];
			DTOfertaLaboral[] dts_ofertas_laborales_ingresadas_array = new DTOfertaLaboral[ofertas_laborales_ingresadas_array.length];

			for (int i = 0; i < ofertas_laborales_ingresadas_list.size(); i++) {

				// System.out.println(ofertas_laborales_ingresadas_list.get(i));

				OfertaLaboral oferta_laboral_ingresada = ofertas_laborales_ingresadas_list.get(i);
				dts_ofertas_laborales_ingresadas_array[i] = oferta_laboral_ingresada.getDT();

			}

			return dts_ofertas_laborales_ingresadas_array;
		} else {
			throw new ObjetoNoExisteException(
					"Esta empresa no posee ninguna oferta laboral en estado de ingresada este momento");
		}

	}

	public DTOfertaLaboral[] listarOfertasLaboralesVigentesPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Empresa empresa = manejadorUsuario.obtenerEmpresa(nombre_empresa);

		List<OfertaLaboral> ofertas_laborales_vigentes_list = empresa.getOfertasLaboralesVigentesList();

		if (ofertas_laborales_vigentes_list != null) {

			OfertaLaboral[] ofertas_laborales_vigentes_array = new OfertaLaboral[ofertas_laborales_vigentes_list
					.size()];
			DTOfertaLaboral[] dts_ofertas_laborales_vigentes_array = new DTOfertaLaboral[ofertas_laborales_vigentes_array.length];

			for (int i = 0; i < ofertas_laborales_vigentes_list.size(); i++) {

				// System.out.println(ofertas_laborales_vigentes_list.get(i));

				OfertaLaboral oferta_laboral_vigente = ofertas_laborales_vigentes_list.get(i);
				dts_ofertas_laborales_vigentes_array[i] = oferta_laboral_vigente.getDT();

			}

			return dts_ofertas_laborales_vigentes_array;
		} else {
			throw new ObjetoNoExisteException("Esta empresa no posee ninguna oferta laboral vigente en este momento");
		}

	}

	public DTOfertaLaboral[] listarOfertasLaboralesVigentesConfirmadasPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Empresa empresa = manejadorUsuario.obtenerEmpresa(nombre_empresa);

		List<OfertaLaboral> ofertas_laborales_vigentes_confirmadas_list = empresa
				.getOfertasLaboralesVigentesConfirmadasList();

		// Si el mapa no es null y no está vacío
		if (ofertas_laborales_vigentes_confirmadas_list != null) {

			System.out.print("NO ES NULLL");

			OfertaLaboral[] ofertas_laborales_vigentes_confirmadas_array = new OfertaLaboral[ofertas_laborales_vigentes_confirmadas_list
					.size()];
			DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas_array = new DTOfertaLaboral[ofertas_laborales_vigentes_confirmadas_array.length];

			for (int i = 0; i < ofertas_laborales_vigentes_confirmadas_list.size(); i++) {

				// System.out.println(ofertas_laborales_vigentes_list.get(i));

				OfertaLaboral oferta_laboral_vigente = ofertas_laborales_vigentes_confirmadas_list.get(i);
				dts_ofertas_laborales_vigentes_confirmadas_array[i] = oferta_laboral_vigente.getDT();

			}

			return dts_ofertas_laborales_vigentes_confirmadas_array;
		} else {
			System.out.print("ES NULLL");

			throw new ObjetoNoExisteException(
					"Esta empresa no posee ninguna oferta laboral vigente y confirmada en este momento");
		}

	}

	public DTKeyword[] listarKeywordsPorOfertaLaboral(String nombre_ol) throws ObjetoNoExisteException {
		ControladorOfertaLaboral ctrlOfertaLaboral = new ControladorOfertaLaboral();
		return ctrlOfertaLaboral.listarKeywordsPorOfertaLaboral(nombre_ol);
	}

	public DTPostulante[] listarPostulantesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException {

		ControladorOfertaLaboral cof = new ControladorOfertaLaboral();
		return cof.listarPostulantesPorOfertaLaboral(ofertalaboral_nombre);

	}

	public DTPostulanteOfertaLaboral[] listarPostulacionesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException {

		ControladorOfertaLaboral cof = new ControladorOfertaLaboral();
		return cof.listarPostulacionesPorOfertaLaboral(ofertalaboral_nombre);

	}

	public DTPostulanteOfertaLaboral[] listarPostulacionesPorPostulante(String nickname)
			throws ObjetoNoExisteException {

		ManejadorUsuario m_u = ManejadorUsuario.getinstance();
		Postulante postulante = m_u.obtenerPostulante(nickname);

		if (postulante != null) {

			Map<String, PostulanteOfertaLaboral> postulaciones_de_postulante = postulante.getPostulaciones();

			if (!postulaciones_de_postulante.isEmpty()) {

				Collection<PostulanteOfertaLaboral> objetos = postulaciones_de_postulante.values();
				Object[] objects = objetos.toArray();
				DTPostulanteOfertaLaboral[] objetos_resultado = new DTPostulanteOfertaLaboral[objects.length];

				PostulanteOfertaLaboral postulacion_oferta;

				for (int i = 0; i < objects.length; i++) {
					postulacion_oferta = (PostulanteOfertaLaboral) objects[i];

					objetos_resultado[i] = postulacion_oferta.getDT();
				}
				return objetos_resultado;
			} else {
				throw new ObjetoNoExisteException("El postulante " + nickname + " no tiene postulaciones asociadas");

			}

		} else {
			throw new ObjetoNoExisteException("El postulante " + nickname + " no existe");
		}
	}

	public DTEmpresaPaquete[] listarPaquetesCompradosPorEmpresa(String empresa_nickname)
			throws ObjetoNoExisteException {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Empresa empresa = mu.obtenerEmpresa(empresa_nickname);

		if (empresa != null) {

			Map<String, EmpresaPaquete> empresa_paquetes_comprados = empresa.getPaquetesComprados();

			if (!empresa_paquetes_comprados.isEmpty()) {

				Collection<EmpresaPaquete> objetos = empresa_paquetes_comprados.values();

				Object[] o = objetos.toArray();
				DTEmpresaPaquete[] objetos_resultado = new DTEmpresaPaquete[o.length];

				EmpresaPaquete empresa_paquete;

				for (int i = 0; i < o.length; i++) {
					empresa_paquete = (EmpresaPaquete) o[i];

					objetos_resultado[i] = empresa_paquete.getDT();
				}
				return objetos_resultado;
			} else {
				
				throw new ObjetoNoExisteException("La empresa " + empresa_nickname + " no tiene paquetes comprados");

			}

		} else {
			throw new ObjetoNoExisteException("La empresa " + empresa_nickname + " no existe");
		}
	}

	/*
	 * public DTPostulanteOfertaLaboral[] listarPostulacionesPorPostulante(String
	 * nickname) throws ObjetoNoExisteException {
	 * 
	 * ManejadorUsuario m_u = ManejadorUsuario.getinstance(); Postulante postulante
	 * = m_u.obtenerPostulante(nickname);
	 * 
	 * if (postulante != null) {
	 * 
	 * PostulanteOfertaLaboral[] postulaciones_de_postulante =
	 * this.getPostulacionAOfertaLaboral(nickname);
	 * 
	 * if (postulaciones_de_postulante != null) {
	 * 
	 * DTPostulanteOfertaLaboral[] objetos_resultado = new
	 * DTPostulanteOfertaLaboral[postulaciones_de_postulante.length]; for (int i =
	 * 0; i < postulaciones_de_postulante.length; i++) {
	 * 
	 * PostulanteOfertaLaboral postulacion_oferta = (PostulanteOfertaLaboral)
	 * postulaciones_de_postulante[i];
	 * 
	 * System.out.println("ESTOY EN: " + i + "DE POSTULACION_OFERTA: " +
	 * postulacion_oferta.getOferta_laboral().getNombre() + "POSTULANTE: " +
	 * postulacion_oferta.getPostulante().getNickname());
	 * 
	 * DTPostulanteOfertaLaboral dt_postualcion_oferta = postulacion_oferta.getDT();
	 * // DTPostulanteOfertaLaboral dt_postualcion_oferta = new
	 * DTPostulanteOfertaLaboral( // postulacion_oferta.getFecha(),
	 * postulacion_oferta.getCv_reducido(), // postulacion_oferta.getMotivacion(),
	 * postulacion_oferta.getAdjuntos(), //
	 * postulacion_oferta.getPostulante().getDT(),
	 * postulacion_oferta.getOferta_laboral().getDT());
	 * 
	 * System.out.println("ESTOY EN: " + i + "DE DT_OSTULACION_OFERTA: " +
	 * dt_postualcion_oferta.getOferta_laboral().getNombre() + "POSTULANTE: " +
	 * dt_postualcion_oferta.getPostulante().getNickname());
	 * 
	 * objetos_resultado[i] = dt_postualcion_oferta; } return objetos_resultado; }
	 * else { throw new ObjetoNoExisteException("El postulante " + nickname +
	 * " no tiene postulaciones asociadas"); }
	 * 
	 * } else { throw new ObjetoNoExisteException("El postulante " + nickname +
	 * " no existe"); } }
	 */

	public void registrarEmpresa(DTEmpresa data_empresa)
			throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Validador validador = new Validador();
		if (!this.existeUsuario(data_empresa.getNickname())) {
			Usuario empresa_correo = manejadorUsuario.obtenerUsuarioPorCorreo(data_empresa.getCorreo());
			if (empresa_correo == null) {
				// Validaciones de usuario
				if (!validador.validarUsuario(data_empresa.getNickname())) {
					throw new ObjetoNoRespetaFormatoException("El nickname ingresado no es valido");
				} else if (!validador.validarCorreo(data_empresa.getCorreo())) {
					throw new ObjetoNoRespetaFormatoException("El correo ingresado no es valido");
				} else if (data_empresa.getClave().length() < 6) {
					throw new ObjetoNoRespetaFormatoException("La clave debe tener un largo de al menos 6 caracteres");
				} else if (!validador.validarUrl(data_empresa.getSitioWeb())) {
					throw new ObjetoNoRespetaFormatoException("El sitio ingresado no es valido eg: https://google.com");
				} else {
					Empresa empresa = new Empresa(data_empresa);
					manejadorUsuario.agregar((Empresa) empresa);
				}

			} else {
				throw new ObjetoRepetidoException("El correo que esta intentando ingresar, ya se encuentra registrado");
			}
		} else {
			throw new ObjetoRepetidoException("El nickname que esta intentando ingresar, ya se encuentra registrado");
		}

	}

	public void registrarPostulante(DTPostulante data_postulante)
			throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Validador validador = new Validador();
		if (!this.existeUsuario(data_postulante.getNickname())) {
			Usuario postulante_correo = manejadorUsuario.obtenerUsuarioPorCorreo(data_postulante.getCorreo());
			if (postulante_correo == null) {
				// Validaciones de usuario
				if (!validador.validarUsuario(data_postulante.getNickname())) {
					throw new ObjetoNoRespetaFormatoException("El nickname ingresado no es valido");
				} else if (!validador.validarCorreo(data_postulante.getCorreo())) {
					throw new ObjetoNoRespetaFormatoException("El correo ingresado no es valido");
				} else if (data_postulante.getClave().length() < 6) {
					throw new ObjetoNoRespetaFormatoException("La clave debe tener un largo de al menos 6 caracteres");
				} else {
					Postulante postulante = new Postulante(data_postulante);
					manejadorUsuario.agregar((Postulante) postulante);
				}
			} else {
				throw new ObjetoRepetidoException("El correo que esta intentando ingresar, ya se encuentra registrado");
			}
		} else {
			throw new ObjetoRepetidoException("El postulante que esta intentando ingresar, ya se encuentra registrado");
		}
	}

	public void registrarPostulacionAOfertaLaboral(DTPostulanteOfertaLaboral data_postulacion)
			throws ObjetoNoExisteException {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		Postulante postulante = manejadorUsuario.obtenerPostulante(data_postulacion.getPostulante().getNickname());

		if (postulante != null) {

			ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
			OfertaLaboral oferta_laboral = mol.obtener(data_postulacion.getOfertaLaboral().getNombre());

			if (oferta_laboral != null) {

				PostulanteOfertaLaboral postulacion_a_oferta = new PostulanteOfertaLaboral(data_postulacion.getFecha(),
						data_postulacion.getCvReducido(), data_postulacion.getMotivacion(),
						data_postulacion.getAdjuntos(), postulante, oferta_laboral);

				oferta_laboral.agregarPostulacion(postulacion_a_oferta);
				postulante.agregarPostulacion(postulacion_a_oferta);

			} else {
				throw new ObjetoNoExisteException("La oferta laboral ingresada, no existe en el sistema");
			}
		} else {
			throw new ObjetoNoExisteException("El postulante ingresado, no esta registrado en el sistema");
		}
	}

	public void editarEmpresa(String nickname, String nombre, String apellido, String nombre_empresa,
			String descripcion, String sitio_web, String clave, String imagen) throws ObjetoNoExisteException {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Empresa empresa = mu.obtenerEmpresa(nickname);

		if (empresa != null) {

			empresa.setNombre(nombre);
			empresa.setApellido(apellido);
			empresa.setNombreEmpresa(nombre_empresa);
			empresa.setDescripcion(descripcion);
			empresa.setLinkWeb(sitio_web);
			empresa.setClave(clave);
			if (imagen != "") {
				empresa.setImagen(imagen);
			}

		} else {
			throw new ObjetoNoExisteException("La empresa no existe en el sistema");

		}
	}

	public void editarPostulante(String nickname, String nombre, String apellido, Date fecha_nacimiento,
			String nacionalidad, String clave, String imagen) throws ObjetoNoExisteException {

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Postulante postulante = mu.obtenerPostulante(nickname);

		if (postulante != null) {

			postulante.setNombre(nombre);
			postulante.setApellido(apellido);
			postulante.setFechaNacimiento(fecha_nacimiento);
			postulante.setNacionalidad(nacionalidad);
			postulante.setClave(clave);
			if (imagen != "") {
				postulante.setImagen(imagen);
			}
		} else {
			throw new ObjetoNoExisteException("El postulante no existe en el sistema");
		}

	}

	public void eliminarUsuarios() {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getinstance();
		manejadorUsuario.vaciar();
	}

	public void modificarEstadoOL(String oferta_nombre, EnumEstadoOL estado) {

		ControladorOfertaLaboral cof = new ControladorOfertaLaboral();
		cof.modificarEstadoOL(oferta_nombre, estado);
	}

}
