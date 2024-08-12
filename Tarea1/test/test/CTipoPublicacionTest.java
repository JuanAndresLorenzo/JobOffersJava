package test;




import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.dts.DTTipoPublicacion;
import logica.Fabrica;
import logica.clases.Paquete;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;
import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;

class CTipoPublicacionTest {

	private static IControladorTipoPublicacion ctrlTipoPubli;
	private static IControladorUsuario ctrlUsuario;
	private static IControladorOfertaLaboral ctrlOfertaLaboral;
	private static Inicializador ini;

	@BeforeAll
	public static void iniciar() {
		Fabrica fabrica = Fabrica.getInstance();
		ctrlTipoPubli = fabrica.getIControladorTipoPublicacion();
		ctrlUsuario = fabrica.getIControladorUsuario();
		ctrlOfertaLaboral = fabrica.getIControladorOfertaLaboral();
		ini = new Inicializador();
	}

	@Test
	void testIngresarTipoPublicacionOK() {

		try {
			ini.limpiarColecciones(ctrlUsuario, ctrlOfertaLaboral, ctrlTipoPubli);
			ini.crearTiposPublicacion(ctrlTipoPubli);
			DTTipoPublicacion dtp = ctrlTipoPubli.getDataTipoPublicacion(ini.getTiposPublicacion()[0].getNombre());

			assertEquals(dtp.getNombre(), ini.getTiposPublicacion()[0].getNombre());
			assertEquals(dtp.getDescripcion(), ini.getTiposPublicacion()[0].getDescripcion());
			assertEquals(dtp.getDuracion_dias(), ini.getTiposPublicacion()[0].getDuracion_dias());
			assertEquals(dtp.getExposicion(), ini.getTiposPublicacion()[0].getExposicion());
			assertEquals(dtp.getCosto(), ini.getTiposPublicacion()[0].getCosto());
			assertEquals(dtp.getFechaAlta(), ini.getTiposPublicacion()[0].getFechaAlta());

			assertEquals(true, ctrlTipoPubli.existeTipoPublicacion(ini.getTiposPublicacion()[0].getNombre()));
			// assertTrue(du.equals(origen)) pero pierdo informaci�n

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
	void testRegistrarTipoPublicacionRepetido() {

		try {
			ini.limpiarColecciones(ctrlUsuario, ctrlOfertaLaboral, ctrlTipoPubli);
			ini.crearTiposPublicacion(ctrlTipoPubli);
		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		}
		;
		// esta es la prueba
		assertThrows(ObjetoRepetidoException.class, () -> {
			ini.crearTiposPublicacion(ctrlTipoPubli);
		});
	}

	@Test
	void testCrearPaqueteTipoPublicacionOK() {

		try {
			ini.limpiarColecciones(ctrlUsuario, ctrlOfertaLaboral, ctrlTipoPubli);
			ini.crearTiposPublicacion(ctrlTipoPubli);

			String nombre_paquete = "Paquete";
			String descripcion_paquete = "Descripcion paquete";
			Integer validez_dias_paquete = 1;
			Float descuento_paquete = (float) 20;
			Date fecha_alta_paquete = new Date(10122001);

			Paquete paquete = new Paquete(nombre_paquete, descripcion_paquete, validez_dias_paquete, descuento_paquete,
					fecha_alta_paquete, "");

			Integer cantidad = 2;
			DTPaqueteTipoPublicacion data_paquete_tipo = new DTPaqueteTipoPublicacion(ini.getTiposPublicacion()[0],
					cantidad);

			Map<String, DTPaqueteTipoPublicacion> paquetes_tipo_publicacion = new HashMap<String, DTPaqueteTipoPublicacion>();
			paquetes_tipo_publicacion.put(data_paquete_tipo.getTipoPublicacion().getNombre(), data_paquete_tipo);

			ctrlTipoPubli.ingresarPaquete(paquete.getDT());

			assertEquals(true, ctrlTipoPubli.existePaquete(nombre_paquete));

			ctrlTipoPubli.agregarTipoPublicacionAPaquete(nombre_paquete, ini.getTiposPublicacion()[0].getNombre(),
					cantidad);

			DTPaqueteTipoPublicacion[] data_paquete_tipo_Publicacion = ctrlTipoPubli
					.listarTiposPublicacionesPorPaquete(nombre_paquete);

			assertEquals(data_paquete_tipo_Publicacion[0].getTipoPublicacion().getNombre(),
					ini.getTiposPublicacion()[0].getNombre());
			assertEquals(data_paquete_tipo_Publicacion[0].getTipoPublicacion().getDescripcion(),
					ini.getTiposPublicacion()[0].getDescripcion());
			assertEquals(data_paquete_tipo_Publicacion[0].getTipoPublicacion().getDuracion_dias(),
					ini.getTiposPublicacion()[0].getDuracion_dias());
			assertEquals(data_paquete_tipo_Publicacion[0].getTipoPublicacion().getCosto(),
					ini.getTiposPublicacion()[0].getCosto());
			assertEquals(data_paquete_tipo_Publicacion[0].getTipoPublicacion().getFechaAlta(),
					ini.getTiposPublicacion()[0].getFechaAlta());
			assertEquals(data_paquete_tipo_Publicacion[0].getCantidad(), cantidad);

			DTPaquete dataPaqueteGet = ctrlTipoPubli.getDataPaquete(nombre_paquete);

			Map<String, DTPaqueteTipoPublicacion> paquetes_tipo_publicacion_res = dataPaqueteGet
					.getTiposPublicaciones();

			assertEquals(dataPaqueteGet.getNombre(), nombre_paquete);
			assertEquals(dataPaqueteGet.getDescripcion(), descripcion_paquete);
			assertEquals(dataPaqueteGet.getValidezDias(), validez_dias_paquete);
			assertEquals(descuento_paquete, dataPaqueteGet.getDescuento()); 
			assertEquals(dataPaqueteGet.getFechaAlta(), fecha_alta_paquete);

			for (Map.Entry<String, DTPaqueteTipoPublicacion> entry : paquetes_tipo_publicacion_res.entrySet()) {
				assertEquals(entry.getValue().getTipoPublicacion().getNombre(),
						paquetes_tipo_publicacion.get(entry.getKey()).getTipoPublicacion().getNombre());
				assertEquals(entry.getValue().getCantidad(),
						paquetes_tipo_publicacion.get(entry.getKey()).getCantidad());
			}

			// assertTrue(du.equals(origen)) pero pierdo informaci�n

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
	}

	@Test
	void testCrearPaqueteTipoPublicacionOKSinTipoPublicaciones() {

		String nombre_paquete = "Paquete 1";
		String descripcion_paquete = "Descripcion paquete";
		Integer validez_dias_paquete = 1;
		Float descuento_paquete = (float) 20;
		Date fecha_alta_paquete = new Date(10122001);

		DTPaquete data_paquete = new DTPaquete(nombre_paquete, descripcion_paquete, validez_dias_paquete,
				descuento_paquete, fecha_alta_paquete);

		try {
			ini.limpiarColecciones(ctrlUsuario, ctrlOfertaLaboral, ctrlTipoPubli);

			ctrlTipoPubli.ingresarPaquete(data_paquete);
			DTPaquete[] data_paquetes = ctrlTipoPubli.listarPaquetes();
			DTPaquete data_paquete_listar = data_paquetes[0];
			DTPaquete dataPaqueteGet = ctrlTipoPubli.getDataPaquete(nombre_paquete);

			Map<String, DTPaqueteTipoPublicacion> paquetes_tipo_publicacion_res = dataPaqueteGet
					.getTiposPublicaciones();

			assertEquals(data_paquete_listar.getNombre(), nombre_paquete);
			assertEquals(data_paquete_listar.getDescripcion(), descripcion_paquete);
			assertEquals(data_paquete_listar.getValidezDias(), validez_dias_paquete);
			assertEquals(data_paquete_listar.getDescuento().toString(), descuento_paquete);

			assertEquals(dataPaqueteGet.getNombre(), nombre_paquete);
			assertEquals(dataPaqueteGet.getDescripcion(), descripcion_paquete);
			assertEquals(dataPaqueteGet.getValidezDias(), validez_dias_paquete);
			assertEquals(dataPaqueteGet.getDescuento(), descuento_paquete);

			assertThrows(ObjetoNoExisteException.class, () -> {
				ctrlTipoPubli.listarTiposPublicacionesPorPaquete(nombre_paquete);
			});

			// assertTrue(du.equals(origen)) pero pierdo informaci�n

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

	}

	@Test
	void testCrearPaqueteTipoPublicacionRepetido() {
		// DataUsuario origen;
		String nombreTipoPublicacion = "Tipo Publicacion 5";
		String descripcion = "Desarrolladores Junior con un año de experiencia en el rubro y carrera avanzada";
		Integer duracionDias = 40;
		Integer exposicion = 1;
		float costo = 10;
		Date fecha_alta = new Date(10102001);
		DTTipoPublicacion data_tipo_publicacion = new DTTipoPublicacion(nombreTipoPublicacion, descripcion,
				duracionDias, exposicion, costo, fecha_alta);

		String nombreTipoPublicacion2 = "Tipo Publicacion 6";
		String descripcion2 = "Desarrolladores Junior con un año de experiencia en el rubro y carrera avanzada";
		Integer duracionDias2 = 40;
		Integer exposicion2 = 1;
		float costo2 = 10;
		Date fecha_alta2 = new Date(10102001);
		DTTipoPublicacion data_tipo_publicacion2 = new DTTipoPublicacion(nombreTipoPublicacion2, descripcion2,
				duracionDias2, exposicion2, costo2, fecha_alta2);

		String nombre_paquete = "Paquete 2";
		String descripcion_paquete = "Descripcion paquete";
		Integer validez_dias_paquete = 1;
		float descuento_paquete = 20;
		Date fecha_alta_paquete = new Date(10122001);

		DTPaquete data_paquete = new DTPaquete(nombre_paquete, descripcion_paquete, validez_dias_paquete,
				descuento_paquete, fecha_alta_paquete);

		Integer cantidad = 2;
		DTPaqueteTipoPublicacion data_paquete_tipo = new DTPaqueteTipoPublicacion(data_tipo_publicacion, cantidad);

		Integer cantidad2 = 4;
		DTPaqueteTipoPublicacion data_paquete_tipo2 = new DTPaqueteTipoPublicacion(data_tipo_publicacion2, cantidad2);

		Map<String, DTPaqueteTipoPublicacion> paquetes_tipo_publicacion = new HashMap<String, DTPaqueteTipoPublicacion>();
		paquetes_tipo_publicacion.put(data_paquete_tipo.getTipoPublicacion().getNombre(), data_paquete_tipo);
		paquetes_tipo_publicacion.put(data_paquete_tipo2.getTipoPublicacion().getNombre(), data_paquete_tipo2);

		try {
			ctrlTipoPubli.eliminarPaquetes();
			;
			ctrlTipoPubli.eliminarTiposDePublicacion();

			ctrlTipoPubli.ingresarPaquete(data_paquete);

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		// esta es la prueba
		assertThrows(ObjetoRepetidoException.class, () -> {
			ctrlTipoPubli.ingresarPaquete(data_paquete);
		});

	}

	@Test
	void listarPaquetesVacios() {
		ctrlTipoPubli.eliminarPaquetes();

		// esta es la prueba
		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.listarPaquetes();
		});
	}

	@Test
	void listarTiposPublicacionVacios() {
		ctrlTipoPubli.eliminarTiposDePublicacion();

		// esta es la prueba
		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.listarTiposPublicaciones();
		});
	}

	@Test
	void getDataPaqueteVacio() {
		ctrlTipoPubli.eliminarTiposDePublicacion();
		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.getDataPaquete("clave2");
		});
	}

	@Test
	void getDataTipoPublicacionVacio() {
		ctrlTipoPubli.eliminarTiposDePublicacion();
		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.getDataTipoPublicacion("clave1");
		});
	}

	@Test
	void getDataTipoPublicacionPaqueteVacio() {
		ctrlTipoPubli.eliminarTiposDePublicacion();
		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.listarTiposPublicacionesPorPaquete("clave1");
		});
	}

	@Test
	void agregarTipoDePublicacionAPaqueteConTipoPublicacionVacia() {

		String nombreTipoPublicacion = "Tipo Publicacion 5";
		String descripcion = "Desarrolladores Junior con un año de experiencia en el rubro y carrera avanzada";
		Integer duracionDias = 40;
		Integer exposicion = 1;
		float costo = 10;
		Date fecha_alta = new Date(10102001);
		DTTipoPublicacion data_tipo_publicacion = new DTTipoPublicacion(nombreTipoPublicacion, descripcion,
				duracionDias, exposicion, costo, fecha_alta);

		try {
			ctrlTipoPubli.eliminarTiposDePublicacion();
			ctrlTipoPubli.ingresarTipoPublicacion(data_tipo_publicacion);

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.agregarTipoPublicacionAPaquete(null, nombreTipoPublicacion, null);
		});
	}

	@Test
	void agregarTipoDePublicacionAPaqueteConPaqueteVacio() {
		String nombreTipoPublicacion = "Tipo Publicacion 7";
		String descripcion = "Desarrolladores Junior con un año de experiencia en el rubro y carrera avanzada";
		Integer duracionDias = 40;
		Integer exposicion = 1;
		float costo = 10;
		Date fecha_alta = new Date(10102001);
		DTTipoPublicacion data_tipo_publicacion = new DTTipoPublicacion(nombreTipoPublicacion, descripcion,
				duracionDias, exposicion, costo, fecha_alta);

		String nombreTipoPublicacion2 = "Tipo Publicacion 8";
		String descripcion2 = "Desarrolladores Junior con un año de experiencia en el rubro y carrera avanzada";
		Integer duracionDias2 = 40;
		Integer exposicion2 = 1;
		float costo2 = 10;
		Date fecha_alta2 = new Date(10102001);
		DTTipoPublicacion data_tipo_publicacion2 = new DTTipoPublicacion(nombreTipoPublicacion2, descripcion2,
				duracionDias2, exposicion2, costo2, fecha_alta2);

		String nombre_paquete = "Paquete";
		String descripcion_paquete = "Descripcion paquete";
		Integer validez_dias_paquete = 1;
		float descuento_paquete = 20;
		Date fecha_alta_paquete = new Date(10122001);

		DTPaquete data_paquete = new DTPaquete(nombre_paquete, descripcion_paquete, validez_dias_paquete,
				descuento_paquete, fecha_alta_paquete);

		Integer cantidad = 2;
		DTPaqueteTipoPublicacion data_paquete_tipo = new DTPaqueteTipoPublicacion(data_tipo_publicacion, cantidad);

		Integer cantidad2 = 4;
		DTPaqueteTipoPublicacion data_paquete_tipo2 = new DTPaqueteTipoPublicacion(data_tipo_publicacion2, cantidad2);

		Map<String, DTPaqueteTipoPublicacion> paquetes_tipo_publicacion = new HashMap<String, DTPaqueteTipoPublicacion>();
		paquetes_tipo_publicacion.put(data_paquete_tipo.getTipoPublicacion().getNombre(), data_paquete_tipo);
		paquetes_tipo_publicacion.put(data_paquete_tipo2.getTipoPublicacion().getNombre(), data_paquete_tipo2);

		try {
			ctrlTipoPubli.eliminarPaquetes();
			ctrlTipoPubli.eliminarTiposDePublicacion();

			ctrlTipoPubli.ingresarPaquete(data_paquete);

		} catch (ObjetoRepetidoException e) {
			// TODO Auto-generated catch block
			fail(e.getMessage());
			e.printStackTrace();
		} catch (ObjetoNoRespetaFormatoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		assertThrows(ObjetoNoExisteException.class, () -> {
			ctrlTipoPubli.agregarTipoPublicacionAPaquete(nombre_paquete, null, null);
		});
	}

	@Test
	void noExistePaquete() {
		ctrlTipoPubli.eliminarPaquetes();
		assertEquals(false, ctrlTipoPubli.existePaquete("clave"));
	}

	@Test
	void noExisteTipoPublicacion() {
		ctrlTipoPubli.eliminarTiposDePublicacion();
		assertEquals(false, ctrlTipoPubli.existeTipoPublicacion("clave"));
	}

}