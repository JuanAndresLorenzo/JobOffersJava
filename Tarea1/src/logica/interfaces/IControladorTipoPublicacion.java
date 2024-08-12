package logica.interfaces;

import java.util.Date;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;

import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;
import logica.dts.DTTipoPublicacion;

public interface IControladorTipoPublicacion {

    //Listados

    public abstract DTTipoPublicacion[] listarTiposPublicaciones() throws ObjetoNoExisteException;

    public abstract DTPaquete[] listarPaquetes() throws ObjetoNoExisteException;

    //Existencia

    public abstract Boolean existeTipoPublicacion(String nombre);

    public abstract Boolean existePaquete(String nombre);
    
    public abstract Boolean existeCompraPaquetePorEmpresa(String paquete_nombre, String empresa_nombre);


    //Obtener objeto particular

    public abstract DTTipoPublicacion getDataTipoPublicacion(String nombre) throws ObjetoNoExisteException;

    public abstract DTPaquete getDataPaquete(String nombre) throws ObjetoNoExisteException;

    //Listados por objeto (asociaciones)

    public abstract DTPaqueteTipoPublicacion[] listarTiposPublicacionesPorPaquete(String paquete_nombre) throws ObjetoNoExisteException;
    
    //Operaciones

    public abstract void ingresarTipoPublicacion(DTTipoPublicacion dt_tipo_publicacion) throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException;

    //public abstract void ingresarPaquete(DTPaquete data_paquete, PaqueteTipoPublicacion[] cantidad_tipos_de_publicacion) throws ObjetoRepetidoException;
    public abstract void ingresarPaquete(DTPaquete dt_paquete) throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException;

    //   public abstract void agregarTipoPublicacionAPaquete(DTPaquete data_paquete, DTTipoPublicacion data_tipo_publicacion, Integer cantidad) throws ObjetoNoExisteException;
    public abstract void agregarTipoPublicacionAPaquete(String paquete_nombre, String tipo_publicacion_nombre, Integer cantidad) throws ObjetoNoExisteException;

	public abstract void compraPaquete(String empresa_nickname, String paquete_nombre, Date fecha, Float costo) throws ObjetoNoExisteException;
    
    //Operaciones para precargar algunos paquetes y tipos de publicaciones respectivamente
//   public abstract void preCargarPaquetes(Integer cantidad) throws ObjetoRepetidoException;
//   public abstract void preCargarTiposPublicaciones(Integer cantidad) throws ObjetoRepetidoException;

    //Operaciones para limpiar las colecciones de los manejadores
    public abstract void eliminarTiposDePublicacion();

    public abstract void eliminarPaquetes();

}
