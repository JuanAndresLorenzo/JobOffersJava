package logica.controladores;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import logica.interfaces.IControladorTipoPublicacion;

import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;
import logica.dts.DTTipoPublicacion;
import logica.clases.Empresa;
import logica.clases.EmpresaPaquete;
import logica.clases.Paquete;
import logica.clases.PaqueteTipoPublicacion;
import logica.clases.TipoPublicacion;

import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorTipoPublicacion;
import logica.manejadores.ManejadorUsuario;

public class ControladorTipoPublicacion implements IControladorTipoPublicacion {

    public ControladorTipoPublicacion() {
    }

    public DTTipoPublicacion[] listarTiposPublicaciones() throws ObjetoNoExisteException {
        ManejadorTipoPublicacion m_tp = ManejadorTipoPublicacion.getinstance();
        TipoPublicacion[] tipos_publicaciones = m_tp.listar();

        if (tipos_publicaciones != null) {

            DTTipoPublicacion[] dts_tipos_publicaciones = new DTTipoPublicacion[tipos_publicaciones.length];

            TipoPublicacion tipo_publicacion;

            for (int i = 0; i < tipos_publicaciones.length; i++) {
                tipo_publicacion = tipos_publicaciones[i];
                dts_tipos_publicaciones[i] = tipo_publicacion.getDT();
            }

            return dts_tipos_publicaciones;
        } else
            throw new ObjetoNoExisteException("No existen tipos de publicaciones registrados");
    }


    public DTPaquete[] listarPaquetes() throws ObjetoNoExisteException {
        ManejadorPaquete m_p = ManejadorPaquete.getinstance();
        Paquete[] paquetes = m_p.listar();

        if (paquetes != null) {

            DTPaquete[] dts_paquetes = new DTPaquete[paquetes.length];

            Paquete paquete;

            for (int i = 0; i < paquetes.length; i++) {
                paquete = paquetes[i];
//                dts_paquetes[i] = new DTPaquete(paquete.getNombre(), paquete.getDescripcion(),
//                        paquete.getValidezDias(), paquete.getDescuento(), paquete.getFechaAlta());
                dts_paquetes[i] = paquete.getDT();
            }

            return dts_paquetes;
        } else
            throw new ObjetoNoExisteException("No existen paquetes registrados");

    }

    public Boolean existeTipoPublicacion(String tipo_publicacion_nombre) {

        ManejadorTipoPublicacion m_tp = ManejadorTipoPublicacion.getinstance();
        TipoPublicacion tipo_publicacion = m_tp.obtener(tipo_publicacion_nombre);

        if (tipo_publicacion != null) {
            return true;
        }
        return false;

    }

    public Boolean existePaquete(String paquete_nombre) {

        ManejadorPaquete m_p = ManejadorPaquete.getinstance();
        Paquete paquete = m_p.obtener(paquete_nombre);

        if (paquete != null) {
            return true;
        }
        return false;
    }

    public Boolean existeCompraPaquetePorEmpresa(String paquete_nombre, String empresa_nickname) {
    	
    	ManejadorPaquete m_p = ManejadorPaquete.getinstance();
		Paquete paquete = m_p.obtener(paquete_nombre);

		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Empresa empresa = mu.obtenerEmpresa(empresa_nickname);
		
		//Si existe la empresa
		if(empresa != null && paquete != null) {
			
			return empresa.existePaqueteComprado(paquete_nombre);
			
		}
		
		return false;		

    }

    
    public DTTipoPublicacion getDataTipoPublicacion(String nombre) throws ObjetoNoExisteException {

        ManejadorTipoPublicacion m_tp = ManejadorTipoPublicacion.getinstance();

        TipoPublicacion tipo_publicacion = m_tp.obtener(nombre);

        if (tipo_publicacion != null)
            return tipo_publicacion.getDT();
        else
            throw new ObjetoNoExisteException("El tipo de publicación " + nombre + " no existe");

    }

    public DTPaquete getDataPaquete(String nombre) throws ObjetoNoExisteException {
        ManejadorPaquete m_p = ManejadorPaquete.getinstance();

        Paquete paquete = m_p.obtener(nombre);

        if (paquete != null)
            return paquete.getDT();
        else
            throw new ObjetoNoExisteException("El paquete " + nombre + " no existe");
    }

    public DTPaqueteTipoPublicacion[] listarTiposPublicacionesPorPaquete(String paquete_nombre) throws ObjetoNoExisteException {

        ManejadorPaquete m_p = ManejadorPaquete.getinstance();
        Paquete paquete = m_p.obtener(paquete_nombre);

        if (paquete != null) {

            Map<String, DTPaqueteTipoPublicacion> tipos_publicaciones_de_paquete = paquete.getDT()
                    .getTiposPublicaciones();

            if (!tipos_publicaciones_de_paquete.isEmpty()) {

                Collection<DTPaqueteTipoPublicacion> tipos_publicaciones_de_paquete_collection = tipos_publicaciones_de_paquete
                        .values();
                Object[] tipos_publicaciones_de_paquete_objects = tipos_publicaciones_de_paquete_collection.toArray();

                DTPaqueteTipoPublicacion[] tipos_publicaciones_de_paquete_array = new DTPaqueteTipoPublicacion[tipos_publicaciones_de_paquete_objects.length];

                for (int i = 0; i < tipos_publicaciones_de_paquete_objects.length; i++) {
                    tipos_publicaciones_de_paquete_array[i] = (DTPaqueteTipoPublicacion) tipos_publicaciones_de_paquete_objects[i];
                }

                return tipos_publicaciones_de_paquete_array;
            } else {
                throw new ObjetoNoExisteException("El paquete " + paquete_nombre + " no tiene tipos de publicaciones asociadas");

            }

        } else {
            throw new ObjetoNoExisteException("El paquete " + paquete_nombre + " no existe");
        }
    }

    public void ingresarTipoPublicacion(DTTipoPublicacion dt_tipo_publicacion) throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException {

        ManejadorTipoPublicacion m_tp = ManejadorTipoPublicacion.getinstance();
        TipoPublicacion tipo_publicacion = m_tp.obtener(dt_tipo_publicacion.getNombre());

        if (tipo_publicacion != null) {
            throw new ObjetoRepetidoException(
                    "El tipo de publicación " + dt_tipo_publicacion.getNombre() + " ya está registrado");
        }else if (dt_tipo_publicacion.getCosto() < 0) {
        	throw new ObjetoNoRespetaFormatoException("EL costo debe ser positivo");
        }else {
	        tipo_publicacion = new TipoPublicacion(dt_tipo_publicacion.getNombre(),
	                dt_tipo_publicacion.getDescripcion(), dt_tipo_publicacion.getDuracion_dias(),
	                dt_tipo_publicacion.getExposicion(), dt_tipo_publicacion.getCosto(),
	                dt_tipo_publicacion.getFechaAlta());
	        m_tp.agregar(tipo_publicacion);
        }
    }

    public void ingresarPaquete(DTPaquete dt_paquete) throws ObjetoRepetidoException, ObjetoNoRespetaFormatoException {

        ManejadorPaquete m_p = ManejadorPaquete.getinstance();
        Paquete paquete = m_p.obtener(dt_paquete.getNombre());

        System.out.print("FECHAAAA: " + dt_paquete.getFechaAlta());

        if (paquete != null) {
            throw new ObjetoRepetidoException("El paquete " + dt_paquete.getNombre() + " ya está registrado");
        }else if (dt_paquete.getDescuento()<0 || dt_paquete.getDescuento()>100) {
        	throw new ObjetoNoRespetaFormatoException("El descuento debe estar en un rango del 0 al 100");
    	}else {
        	paquete = new Paquete(dt_paquete.getNombre(), dt_paquete.getDescripcion(), dt_paquete.getValidezDias(),
                dt_paquete.getDescuento(), dt_paquete.getFechaAlta(), dt_paquete.getImg());
        	m_p.agregar(paquete);
        }
    }

    // public void agregarTipoPublicacionAPaquete(DTPaquete dt_paquete,
    // DTTipoPublicacion dt_tipo_publicacion, Integer cantidad) throws
    // ObjetoNoExisteException {
    public void agregarTipoPublicacionAPaquete(String paquete_nombre, String tipo_publicacion_nombre, Integer cantidad)
            throws ObjetoNoExisteException {

        ManejadorPaquete m_p = ManejadorPaquete.getinstance();
        // Paquete paquete = m_p.obtener(dt_paquete.getNombre());
        Paquete paquete = m_p.obtener(paquete_nombre);

        if (paquete == null)
            // throw new ObjetoNoExisteException("El paquete " + dt_paquete.getNombre() +
            // " no existe");
            throw new ObjetoNoExisteException("El paquete " + paquete_nombre + " no existe");

        ManejadorTipoPublicacion m_tp = ManejadorTipoPublicacion.getinstance();
        // TipoPublicacion tipo_publicacion =
        // m_tp.obtener(dt_tipo_publicacion.getNombre());
        TipoPublicacion tipo_publicacion = m_tp.obtener(tipo_publicacion_nombre);

        if (tipo_publicacion == null)
            // throw new ObjetoNoExisteException("El tipo de publicación " +
            // dt_tipo_publicacion.getNombre() + " no existe");
            throw new ObjetoNoExisteException("El tipo de publicación " + tipo_publicacion_nombre + " no existe");

        PaqueteTipoPublicacion paquete_tipo_publicacion = new PaqueteTipoPublicacion(paquete, tipo_publicacion, cantidad);
        paquete.agregarTipoPublicacion(paquete_tipo_publicacion);

    }

	public void compraPaquete(String empresa_nickname, String paquete_nombre, Date fecha, Float costo) throws ObjetoNoExisteException {


		ManejadorUsuario mu = ManejadorUsuario.getinstance();
		Empresa empresa = mu.obtenerEmpresa(empresa_nickname);

		ManejadorPaquete m_p = ManejadorPaquete.getinstance();
		Paquete paquete = m_p.obtener(paquete_nombre);
		
		System.out.println("COMPRA PAQUETE existe  "+ paquete_nombre + "------>"+empresa.existePaqueteComprado(paquete.getKey()));

		
		//Si el paquete no fue comprado por esta empresa
		if(empresa !=null && paquete !=null && !empresa.existePaqueteComprado(paquete.getKey())) {
			
			System.out.println("COMPRA PAQUETE ENTRE IF");

			
			EmpresaPaquete empresa_paquete = new EmpresaPaquete(empresa, paquete, fecha, costo);
			
			empresa.agregarPaqueteComprado(empresa_paquete);

		}	


	}

    // Operación para precargar algunos paquetes

//	public void preCargarPaquetes(Integer cantidad) throws ObjetoRepetidoException {
//
//		System.out.println("PAQUETES CARGADOS:");
//
//		for (int i = 0; i < cantidad; i++) {
//
//			DTPaquete dt_paquete = new DTPaquete("Paquete " + i, "Desc " + i, i, (float) (i + 30));
//
//			System.out.println("PAQUETE: " + dt_paquete.getNombre());
//
//			this.ingresarPaquete(dt_paquete);
//
//		}
//
//	}


    // Operación para precargar algunos tipos de publicaciones
//	public void preCargarTiposPublicaciones(Integer cantidad) throws ObjetoRepetidoException {
//
//		System.out.println("TIPOS PUBLICACIONES CARGADOS:");
//
//		for (int i = 0; i < cantidad; i++) {
//
//			DTTipoPublicacion dt_tipo_publicacion = new DTTipoPublicacion("TP " + i, "TP desc " + i, i, i + 10,
//					(float) i + 30, new Date());
//
//			System.out.println("TIPO DE PUBLICACIÓN: " + dt_tipo_publicacion.getNombre());
//
//			this.ingresarTipoPublicacion(dt_tipo_publicacion);
//
//		}
//
//	}

    public void eliminarTiposDePublicacion() {
        ManejadorTipoPublicacion mtp = ManejadorTipoPublicacion.getinstance();
        mtp.vaciar();
    }

    public void eliminarPaquetes() {
        ManejadorPaquete mp = ManejadorPaquete.getinstance();
        mp.vaciar();
    }


}
