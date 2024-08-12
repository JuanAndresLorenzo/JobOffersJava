package logica.interfaces;

import java.util.Date;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.clases.EnumEstadoOL;
import logica.dts.DTEmpresa;
import logica.dts.DTEmpresaPaquete;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTUsuario;

public interface IControladorUsuario {

	// Listados

	public abstract DTUsuario[] listarUsuarios() throws ObjetoNoExisteException;

	public abstract DTEmpresa[] listarEmpresas() throws ObjetoNoExisteException;

	public abstract DTPostulante[] listarPostulantes() throws ObjetoNoExisteException;

	// Existencia

	public abstract Boolean existeUsuario(String usuario_nickname);
	//public abstract Boolean existeEmpresa(String empresa_nombre);
	
	public abstract Boolean existeUsuarioPorCorreo(String usuario_correo);

	public abstract Boolean existePostulacionAOferta(String postulante_usuario_nickname, String oferta_laboral_nombre);

	// Obtener objeto particular

	public abstract DTUsuario getDataUsuario(String nickname) throws ObjetoNoExisteException;

	public abstract DTOfertaLaboral getDataOfertaLaboralPorEmpresa(String nombre_oferta_laboral, String nombre_empresa)
			throws ObjetoNoExisteException;

	// Listados por objeto (asociaciones)

	public abstract DTOfertaLaboral[] listarOfertasLaboralesPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException;

	public abstract DTOfertaLaboral[] listarOfertasLaboralesVigentesPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException;

	public abstract DTOfertaLaboral[] listarOfertasLaboralesVigentesConfirmadasPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException;

	//public abstract PostulanteOfertaLaboral[] getPostulacionAOfertaLaboral(String nickname) throws ObjetoNoExisteException; //NO DEBE RETORNAR OBJETOS
	public abstract DTPostulanteOfertaLaboral[] listarPostulacionesPorPostulante(String nickname) throws ObjetoNoExisteException;
	
    public abstract DTEmpresaPaquete[] listarPaquetesCompradosPorEmpresa(String empresa_nickname) throws ObjetoNoExisteException;

	// Operaciones

	public abstract void registrarEmpresa(DTEmpresa data_empresa) throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException;

	public abstract void registrarPostulante(DTPostulante data_postulante) throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException;

	public abstract void registrarPostulacionAOfertaLaboral(DTPostulanteOfertaLaboral data_postulacion)
			throws ObjetoNoExisteException; // TODO: Revisar, usa manejOL

	public abstract void editarPostulante(String nickname, String nombre, String apellido, Date fecha_nacimiento,
			String nacionalidad, String clave, String imagen) throws ObjetoNoExisteException;

	public abstract void editarEmpresa(String nickname, String nombre, String apellido, String nombre_empresa,
			String descripcion, String sitio_web, String clave, String imagen) throws ObjetoNoExisteException;

	public abstract void eliminarUsuarios();

	// Operaciones de otros controladores

	public abstract DTOfertaLaboral[] listarOfertasLaborales() throws ObjetoNoExisteException;

	public abstract DTKeyword[] listarKeywordsPorOfertaLaboral(String nombre_ol) throws ObjetoNoExisteException;

	public abstract DTOfertaLaboral getDataOfertaLaboral(String nombre) throws ObjetoNoExisteException;

	public abstract DTPostulante[] listarPostulantesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException;

	public abstract DTPostulanteOfertaLaboral[] listarPostulacionesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException;

	public abstract void modificarEstadoOL(String oferta_nombre, EnumEstadoOL estado);

	public abstract DTOfertaLaboral[] listarOfertasLaboralesIngresadasPorEmpresa(String nombre_empresa)
			throws ObjetoNoExisteException;

}
