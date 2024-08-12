package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;

import logica.dts.DTUsuario;
import logica.Fabrica;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;

class CUsuarioTest {

	private static IControladorOfertaLaboral controladorOfertaLaboral;
	private static IControladorUsuario controladorUsuario;
	private static IControladorTipoPublicacion controladorTipoPublicacion;
	private static Inicializador ini;

	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		controladorOfertaLaboral = fabrica.getIControladorOfertaLaboral();
		controladorUsuario = fabrica.getIControladorUsuario();
		controladorTipoPublicacion = fabrica.getIControladorTipoPublicacion();
		ini = new Inicializador();
	}

	@Test
	void testRegistrarPostulanteOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearPostulantes(controladorUsuario);
			DTPostulante[] data_postulante_lista = controladorUsuario.listarPostulantes();

			assertEquals(ini.getPostulantes()[0].getNickname(), data_postulante_lista[0].getNickname());
			assertEquals(ini.getPostulantes()[0].getNombre(), data_postulante_lista[0].getNombre());
			assertEquals(ini.getPostulantes()[0].getApellido(), data_postulante_lista[0].getApellido());
			assertEquals(ini.getPostulantes()[0].getCorreo(), data_postulante_lista[0].getCorreo());
			assertEquals(ini.getPostulantes()[0].getFechaNacimiento(), data_postulante_lista[0].getFechaNacimiento());
			assertEquals(ini.getPostulantes()[0].getNacionalidad(), data_postulante_lista[0].getNacionalidad());

		} catch (ObjetoRepetidoException e) {
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
	}

	@Test
	void testRegistrarPostulanteRepetido() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearPostulantes(controladorUsuario);
		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		};

		assertThrows(ObjetoRepetidoException.class, () -> {
			ini.crearPostulantes(controladorUsuario);
		});
	}

	@Test
	void testRegistrarEmpresaOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
			DTEmpresa[] data_empresa_lista = controladorUsuario.listarEmpresas();

			assertEquals(ini.getEmpresas()[0].getNickname(), data_empresa_lista[0].getNickname());
			assertEquals(ini.getEmpresas()[0].getNombre(), data_empresa_lista[0].getNombre());
			assertEquals(ini.getEmpresas()[0].getApellido(), data_empresa_lista[0].getApellido());
			assertEquals(ini.getEmpresas()[0].getCorreo(), data_empresa_lista[0].getCorreo());
			assertEquals(ini.getEmpresas()[0].getNombreEmpresa(), data_empresa_lista[0].getNombreEmpresa());
			assertEquals(ini.getEmpresas()[0].getDescripcion(), data_empresa_lista[0].getDescripcion());
			assertEquals(ini.getEmpresas()[0].getSitioWeb(), data_empresa_lista[0].getSitioWeb());

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
	}

	@Test
	void testRegistrarEmpresaRepetido() {
		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;

		assertThrows(ObjetoRepetidoException.class, () -> {
			ini.crearEmpresas(controladorUsuario);
		});
	}

	@Test
	void testListarUsuariosOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearPostulantes(controladorUsuario);
			ini.crearEmpresas(controladorUsuario);
			DTUsuario[] data_usuario_lista = controladorUsuario.listarUsuarios();

			assertEquals(ini.getEmpresas()[0].getNickname(), data_usuario_lista[1].getNickname());
			assertEquals(ini.getEmpresas()[0].getNombre(), data_usuario_lista[1].getNombre());
			assertEquals(ini.getEmpresas()[0].getApellido(), data_usuario_lista[1].getApellido());
			assertEquals(ini.getEmpresas()[0].getCorreo(), data_usuario_lista[1].getCorreo());

			assertEquals(ini.getPostulantes()[0].getNickname(), data_usuario_lista[0].getNickname());
			assertEquals(ini.getPostulantes()[0].getNombre(), data_usuario_lista[0].getNombre());
			assertEquals(ini.getPostulantes()[0].getApellido(), data_usuario_lista[0].getApellido());
			assertEquals(ini.getPostulantes()[0].getCorreo(), data_usuario_lista[0].getCorreo());

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
	}

	@Test
	void getDataUsuariosOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
			ini.crearPostulantes(controladorUsuario);
			DTUsuario data_usuario_postulante = controladorUsuario.getDataUsuario(ini.getPostulantes()[0].getNickname());
			DTUsuario data_usuario_empresa = controladorUsuario.getDataUsuario(ini.getEmpresas()[0].getNickname());

			assertEquals(ini.getEmpresas()[0].getNickname(), data_usuario_empresa.getNickname());
			assertEquals(ini.getEmpresas()[0].getNombre(), data_usuario_empresa.getNombre());
			assertEquals(ini.getEmpresas()[0].getApellido(), data_usuario_empresa.getApellido());
			assertEquals(ini.getEmpresas()[0].getCorreo(), data_usuario_empresa.getCorreo());

			assertEquals(ini.getPostulantes()[0].getNickname(), data_usuario_postulante.getNickname());
			assertEquals(ini.getPostulantes()[0].getNombre(), data_usuario_postulante.getNombre());
			assertEquals(ini.getPostulantes()[0].getApellido(), data_usuario_postulante.getApellido());
			assertEquals(ini.getPostulantes()[0].getCorreo(), data_usuario_postulante.getCorreo());

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
	}

	@Test
	void getDataOfertaLaboralOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
			ini.crearPostulantes(controladorUsuario);
			ini.crearTiposPublicacion(controladorTipoPublicacion);
			ini.crearKeywords(controladorOfertaLaboral);
			ini.crearOfertasLaborales(controladorOfertaLaboral);

			DTOfertaLaboral[] data_oferta_laboral_lista = controladorUsuario.listarOfertasLaborales();
			DTOfertaLaboral[] data_oferta_laboral_lista_por_empresa = controladorUsuario
					.listarOfertasLaboralesPorEmpresa(ini.getEmpresas()[0].getNickname());
			DTOfertaLaboral data_oferta_laboral_get_por_empresa = controladorUsuario
					.getDataOfertaLaboralPorEmpresa(data_oferta_laboral_lista_por_empresa[0].getNombre(), ini.getEmpresas()[0].getNickname());
			DTOfertaLaboral data_oferta_laboral_get = controladorUsuario
					.getDataOfertaLaboral(data_oferta_laboral_lista[0].getNombre());

			assertEquals(ini.getOfertasLaborales()[0].getNombre(), data_oferta_laboral_get.getNombre());
			assertEquals(ini.getOfertasLaborales()[0].getDescripcion(), data_oferta_laboral_get.getDescripcion());
			assertEquals(ini.getOfertasLaborales()[0].getCiudad(), data_oferta_laboral_get.getCiudad());
			assertEquals(ini.getOfertasLaborales()[0].getDepartamento(), data_oferta_laboral_get.getDepartamento());
			assertEquals(ini.getOfertasLaborales()[0].getHorario(), data_oferta_laboral_get.getHorario());
			assertEquals(ini.getOfertasLaborales()[0].getRemuneracion(), data_oferta_laboral_get.getRemuneracion());
			assertEquals(ini.getOfertasLaborales()[0].getFechaAlta(), data_oferta_laboral_get.getFechaAlta());

			assertEquals(ini.getOfertasLaborales()[0].getNombre(), data_oferta_laboral_get_por_empresa.getNombre());
			assertEquals(ini.getOfertasLaborales()[0].getDescripcion(), data_oferta_laboral_get_por_empresa.getDescripcion());
			assertEquals(ini.getOfertasLaborales()[0].getCiudad(), data_oferta_laboral_get_por_empresa.getCiudad());
			assertEquals(ini.getOfertasLaborales()[0].getDepartamento(), data_oferta_laboral_get_por_empresa.getDepartamento());
			assertEquals(ini.getOfertasLaborales()[0].getHorario(), data_oferta_laboral_get_por_empresa.getHorario());
			assertEquals(ini.getOfertasLaborales()[0].getRemuneracion(), data_oferta_laboral_get_por_empresa.getRemuneracion());
			assertEquals(ini.getOfertasLaborales()[0].getFechaAlta(), data_oferta_laboral_get_por_empresa.getFechaAlta());


		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
	}

	@Test
	void postulacionAOfertaLaboralOK() {

		try {

			Date fecha = new Date(11112001);
			String cv_reducido = "cv1";
			String motivacion = "m1";
			String adjuntos = "a1";
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
			ini.crearPostulantes(controladorUsuario);
			ini.crearTiposPublicacion(controladorTipoPublicacion);
			ini.crearKeywords(controladorOfertaLaboral);
			ini.crearOfertasLaborales(controladorOfertaLaboral);
			controladorUsuario.registrarPostulacionAOfertaLaboral(new DTPostulanteOfertaLaboral(fecha,
					cv_reducido, motivacion, adjuntos, ini.getPostulantes()[0], ini.getOfertasLaborales()[0]));
			
			DTPostulante[] data_postulante_list = controladorUsuario.listarPostulantesPorOfertaLaboral(ini.getOfertasLaborales()[0].getNombre());
			DTPostulanteOfertaLaboral[] data_postulacion_a_oferta_list = controladorUsuario
					.listarPostulacionesPorPostulante(ini.getPostulantes()[0].getNickname());

			assertEquals(ini.getPostulantes()[0].getNickname(), data_postulante_list[0].getNickname());
			assertEquals(ini.getPostulantes()[0].getNombre(), data_postulante_list[0].getNombre());
			assertEquals(ini.getPostulantes()[0].getApellido(), data_postulante_list[0].getApellido());
			assertEquals(ini.getPostulantes()[0].getCorreo(), data_postulante_list[0].getCorreo());
			assertEquals(ini.getPostulantes()[0].getFechaNacimiento(), data_postulante_list[0].getFechaNacimiento());
			assertEquals(ini.getPostulantes()[0].getNacionalidad(), data_postulante_list[0].getNacionalidad());

			assertEquals(fecha, data_postulacion_a_oferta_list[0].getFecha());
			assertEquals(cv_reducido, data_postulacion_a_oferta_list[0].getCvReducido());
			assertEquals(motivacion, data_postulacion_a_oferta_list[0].getMotivacion());
			assertEquals(adjuntos, data_postulacion_a_oferta_list[0].getAdjuntos());

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test

	void editarDatosPostulanteOK() {

		// Data Editada Postulante origen:
		String nombreEditado = "nombreEditado";
		String apellido_editado = "apellidoEditado";
		Date fechaNacimientoEditado = new Date(10112000);
		String nacionalidad_editado = "nacionalidad";
		String claveEditada = "claveEditada";
		String imagenEditada = "C://img.jpg";
		
		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearPostulantes(controladorUsuario);
			controladorUsuario.editarPostulante(ini.getPostulantes()[0].getNickname(), nombreEditado, apellido_editado, fechaNacimientoEditado, nacionalidad_editado, claveEditada, imagenEditada);

			DTPostulante[] data_postulante_list = controladorUsuario.listarPostulantes();

			assertEquals(ini.getPostulantes()[0].getNickname(), data_postulante_list[0].getNickname());
			assertEquals(nombreEditado, data_postulante_list[0].getNombre());
			assertEquals(apellido_editado, data_postulante_list[0].getApellido());
			assertEquals(fechaNacimientoEditado, data_postulante_list[0].getFechaNacimiento());
			assertEquals(nacionalidad_editado, data_postulante_list[0].getNacionalidad());

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
	}

	@Test
	void editarEmpresaOK() {

		// Data Editada Postulante origen:
		String nombreEditado = "nombreEditado";
		String apellidoEditado = "apellidoEditado";
		String nombreEmpresaEditado = "nombreEmpresaEditado";
		String descripcionEditada = "decripcionEditada";
		String sitioWebEditado = "swEditado";
		String claveEditada = "claveEditada";
		String imagenEditada = "C://img.jpg";
		
		
		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
			controladorUsuario.editarEmpresa(ini.getEmpresas()[0].getNickname(), nombreEditado, apellidoEditado, nombreEmpresaEditado,
					descripcionEditada, sitioWebEditado, claveEditada, imagenEditada);
			DTEmpresa[] data_empresa_list = controladorUsuario.listarEmpresas();

			assertEquals(ini.getEmpresas()[0].getNickname(), data_empresa_list[0].getNickname());
			assertEquals(nombreEditado, data_empresa_list[0].getNombre());
			assertEquals(apellidoEditado, data_empresa_list[0].getApellido());
			assertEquals(nombreEmpresaEditado, data_empresa_list[0].getNombreEmpresa());
			assertEquals(descripcionEditada, data_empresa_list[0].getDescripcion());
			assertEquals(sitioWebEditado, data_empresa_list[0].getSitioWeb());

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} 
	}

	@Test
	void existeUsuarioOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
			ini.crearPostulantes(controladorUsuario);

			assertTrue(controladorUsuario.existeUsuario(ini.getEmpresas()[0].getNickname()));
			assertTrue(controladorUsuario.existeUsuario(ini.getPostulantes()[0].getNickname()));
			assertFalse(controladorUsuario.existeUsuario("no existe"));

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
	}

	@Test
	void testNoHayPostulantes() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.listarPostulantes();
		});
	}

	@Test
	void testNoHayEmpresas() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.listarEmpresas();
		});
	}

	@Test
	void testNoHayOfertasLaborales() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.listarOfertasLaborales();
		});
	}

	@Test
	void testNoHayUsuarios() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.listarUsuarios();
		});
	}

	@Test
	void testNoHayEmpresaListarOfertas() throws ObjetoNoExisteException {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.listarOfertasLaboralesPorEmpresa("no existe");
		});
	}

	@Test
	void testNoHayEmpresaDataOfertas() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.getDataOfertaLaboralPorEmpresa("no existe oferta laboral", "empresa");
		});
	}


	@Test
	void testNoHayOfertaLaboralListarOfertas() {
		
		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearEmpresas(controladorUsuario);
		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;

		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.listarOfertasLaboralesPorEmpresa(ini.getEmpresas()[0].getNickname());
		});
	}

	@Test
	void testNoHayUsuariosData() {
		
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorUsuario.getDataUsuario("no existe usuario");
		});
	}

	@Test
	void testNoHayEmpresasListar() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		controladorTipoPublicacion.eliminarTiposDePublicacion();
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorOfertaLaboral.listarEmpresas();
		});
	}

	@Test
	void testNoHayTipoPublicacionesListar() {
		ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorOfertaLaboral.listarTiposPublicaciones();
		});
	}

//	
}