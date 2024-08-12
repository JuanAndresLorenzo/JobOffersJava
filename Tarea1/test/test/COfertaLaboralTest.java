package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.sql.Date;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;
import logica.Fabrica;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;

class COfertaLaboralTest {

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
	void testRegistrarKeywordOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearKeywords(controladorOfertaLaboral);
			DTKeyword[] lista_keywords = controladorOfertaLaboral.listarKeywords();

			assertEquals(ini.getKeywords()[0], lista_keywords[0].getNombre());

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
	void testRegistrarKeywordRepetida() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearKeywords(controladorOfertaLaboral);

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
		assertThrows(ObjetoRepetidoException.class, () -> {
			ini.crearKeywords(controladorOfertaLaboral);
		});
	}

	@Test
	void testListarKeywordVacia() {

		controladorOfertaLaboral.eliminarKeywords();
		assertThrows(ObjetoNoExisteException.class, () -> {
			controladorOfertaLaboral.listarKeywords();
		});
	}

	@Test
	void testregistrarOfertaLaboralOK() {

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearKeywords(controladorOfertaLaboral);
			ini.crearEmpresas(controladorUsuario);
			ini.crearTiposPublicacion(controladorTipoPublicacion);
			ini.crearOfertasLaborales(controladorOfertaLaboral);
			DTOfertaLaboral data_oferta_laboral_get = controladorOfertaLaboral
					.getDataOfertaLaboral(ini.getOfertasLaborales()[0].getNombre());

			assertEquals(ini.getOfertasLaborales()[0].getNombre(), data_oferta_laboral_get.getNombre());
			assertEquals(ini.getOfertasLaborales()[0].getDescripcion(), data_oferta_laboral_get.getDescripcion());
			assertEquals(ini.getOfertasLaborales()[0].getCiudad(), data_oferta_laboral_get.getCiudad());
			assertEquals(ini.getOfertasLaborales()[0].getDepartamento(), data_oferta_laboral_get.getDepartamento());
			assertEquals(ini.getOfertasLaborales()[0].getHorario(), data_oferta_laboral_get.getHorario());
			assertEquals(ini.getOfertasLaborales()[0].getRemuneracion(), data_oferta_laboral_get.getRemuneracion());
			assertEquals(ini.getOfertasLaborales()[0].getFechaAlta(), data_oferta_laboral_get.getFechaAlta());

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
	void testObtenerPostulacionesOK() {

		Date fecha = new Date(11112001);
		String cv_reducido = "cv1";
		String motivacion = "m1";
		String adjuntos = "a1";

		try {
			ini.limpiarColecciones(controladorUsuario, controladorOfertaLaboral, controladorTipoPublicacion);
			ini.crearKeywords(controladorOfertaLaboral);
			ini.crearEmpresas(controladorUsuario);
			ini.crearTiposPublicacion(controladorTipoPublicacion);
			ini.crearOfertasLaborales(controladorOfertaLaboral);
			controladorUsuario.registrarPostulacionAOfertaLaboral(new DTPostulanteOfertaLaboral(fecha, cv_reducido,
					motivacion, adjuntos, ini.getPostulantes()[0], ini.getOfertasLaborales()[0]));
			DTPostulanteOfertaLaboral[] data_oferta_laboral_get = controladorOfertaLaboral
					.listarPostulacionesPorOfertaLaboral(ini.getOfertasLaborales()[0].getNombre());

			assertEquals(cv_reducido, data_oferta_laboral_get[0].getCvReducido());
			assertEquals(adjuntos, data_oferta_laboral_get[0].getAdjuntos());
			assertEquals(motivacion, data_oferta_laboral_get[0].getMotivacion());
			assertEquals(fecha, data_oferta_laboral_get[0].getFecha());

			if (controladorOfertaLaboral.existeOfertaLaboral(ini.getOfertasLaborales()[0].getNombre())) {
				assertEquals(ini.getKeywords()[0], controladorOfertaLaboral
						.listarKeywordsPorOfertaLaboral(ini.getOfertasLaborales()[0].getNombre()));
			}

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
	void testNoExisteOferta() {
		assertFalse(controladorOfertaLaboral.existeOfertaLaboral("No existe oferta"));
	}
}