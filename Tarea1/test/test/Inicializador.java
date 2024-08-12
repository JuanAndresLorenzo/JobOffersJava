package test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.clases.EnumEstadoOL;
import logica.clases.EnumRol;
import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTTipoPublicacion;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;

/**
 * Crea los objetos a ser usados en los tests JUnit.
 *
 * @author Juan Lorenzo
 */
public class Inicializador {

	private static String[] keywords = { "keyword1", "keyword2" };
	private static DTPostulante[] postulantes = { new DTPostulante("n1", "np1", "a1", "c1", "clave123", "C:/",
			EnumRol.POSTULANTE, new Date(10122001), "nac1") };
	private static DTEmpresa[] empresas = {
			new DTEmpresa("n1", "np1", "a1", "c1", "ne1", "d1", EnumRol.EMPRESA, "sw1", null, null) };
	private static DTTipoPublicacion[] tiposPublicacion = { new DTTipoPublicacion("Desarrollador Java",
			"Desarrolladores Junior con un año de experiencia en el rubro y carrera avanzada", 40, 1, (float) 10,
			new Date(10102001)) };
	private static DTOfertaLaboral[] ofertasLaborales = {
			new DTOfertaLaboral("o1", "d1", "c1", "d1", "h1", (float) 1, new Date(10102001), new Date(10102001), true, empresas[0], tiposPublicacion[0], null, EnumEstadoOL.CONFIRMADA) };

	public void crearKeywords(IControladorOfertaLaboral ctrlOfertaLaboral) throws ObjetoRepetidoException {
		for (String keyword : keywords) {
			try {
				ctrlOfertaLaboral.registrarKeyword(keyword);
			} catch (ObjetoRepetidoException e) {
				throw e;
			}
		}
	}

	/**
	 * Crea los postulantes para los tests.
	 *
	 * @author Juan Lorenzo
	 */
	public void crearPostulantes(IControladorUsuario ctrlUsuario) throws ObjetoRepetidoException {
		for (DTPostulante postulante : postulantes) {
			try {
				ctrlUsuario.registrarPostulante(postulante);
			} catch (ObjetoRepetidoException e) {
				throw e;
			} catch (ObjetoNoRespetaFormatoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void crearEmpresas(IControladorUsuario ctrlUsuario) throws ObjetoRepetidoException {
		for (final DTEmpresa empresa : empresas) {
			try {
				ctrlUsuario.registrarEmpresa(empresa);
			} catch (ObjetoRepetidoException e) {
				throw e;
			} catch (ObjetoNoRespetaFormatoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Crea los tipos de publicacion para los tests.
	 *
	 * @author Juan Lorenzo
	 */
	public void crearTiposPublicacion(final IControladorTipoPublicacion ctrlTipoPubli) throws ObjetoRepetidoException {
		for (final DTTipoPublicacion tipo_publicacion : tiposPublicacion) {
			try {
				ctrlTipoPubli.ingresarTipoPublicacion(tipo_publicacion);
			} catch (ObjetoRepetidoException e) {
				throw e;
			} catch (ObjetoNoRespetaFormatoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void crearOfertasLaborales(IControladorOfertaLaboral ctrlOfertaLaboral) throws ObjetoRepetidoException {
		for (DTOfertaLaboral oferta_laboral : ofertasLaborales) {
			try {
				// Data Keyword origen:
				String nombreKeyword = "k1";
				final List<String> keywords = new ArrayList<>();
				keywords.add(nombreKeyword);

				ctrlOfertaLaboral.registrarOfertaLaboral(oferta_laboral);

			} catch (ObjetoRepetidoException e) {
				throw e;
			}
		}
	}

	public void limpiarColecciones(final IControladorUsuario ctrlUsuario,
			final IControladorOfertaLaboral ctrlOfertaLaboral, final IControladorTipoPublicacion ctrlTipoPubli) {
		ctrlUsuario.eliminarUsuarios();
		ctrlTipoPubli.eliminarTiposDePublicacion();
		ctrlTipoPubli.eliminarPaquetes();
		ctrlOfertaLaboral.eliminarKeywords();
		ctrlOfertaLaboral.eliminarOfertasLaborales();
	}

	public DTEmpresa[] getEmpresas() {
		return empresas;
	}

	public DTTipoPublicacion[] getTiposPublicacion() {
		return tiposPublicacion;
	}

	public DTOfertaLaboral[] getOfertasLaborales() {
		return ofertasLaborales;
	}

	public String[] getKeywords() {
		return keywords;
	}

	public DTPostulante[] getPostulantes() {
		return postulantes;
	}

}
