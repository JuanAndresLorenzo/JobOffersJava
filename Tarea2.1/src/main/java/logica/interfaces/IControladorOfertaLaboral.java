package logica.interfaces;

import java.util.List;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTTipoPublicacion;
import logica.clases.EnumEstadoOL;
import logica.dts.DTEmpresa;
import logica.dts.DTKeyword;

public interface IControladorOfertaLaboral {

	// Listados

	public abstract DTKeyword[] listarKeywords() throws ObjetoNoExisteException;

	public abstract DTOfertaLaboral[] listarOfertasLaborales() throws ObjetoNoExisteException;
	
	public abstract DTOfertaLaboral[] listarOfertasLaboralesIngresadas() throws ObjetoNoExisteException;

	// Existencia

	public abstract DTOfertaLaboral[] listarOfertasLaboralesVigentesConfirmadas() throws ObjetoNoExisteException;


	public abstract Boolean existeOfertaLaboral(String nombre);

	// Obtener objeto particular

	public abstract DTOfertaLaboral getDataOfertaLaboral(String oferta_nombre) throws ObjetoNoExisteException;


	public abstract DTPostulanteOfertaLaboral getDataPostulacionOfertaLaboral(String oferta_nombre, String postulante_nickname) throws ObjetoNoExisteException;

	public abstract DTPostulanteOfertaLaboral[] listarPostulacionesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException;

	public abstract DTPostulante[] listarPostulantesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException;

	public abstract DTKeyword[] listarKeywordsPorOfertaLaboral(String nombre);

	// Operaciones

	public abstract void registrarKeyword(String nombre_keyword) throws ObjetoRepetidoException;

	public abstract void registrarOfertaLaboral(DTOfertaLaboral data_oferta_laboral) throws ObjetoRepetidoException;

	public void modificarEstadoOL(String oferta_nombre,EnumEstadoOL estado);
	
	public void eliminarKeywords();

	public void eliminarOfertasLaborales();

	// Operaciones de otros controladores

	public abstract DTEmpresa[] listarEmpresas() throws ObjetoNoExisteException;

	public abstract DTTipoPublicacion[] listarTiposPublicaciones() throws ObjetoNoExisteException;


}