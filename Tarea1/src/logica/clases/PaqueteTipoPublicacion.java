package logica.clases;

import logica.dts.DTTipoPublicacion;
import logica.dts.DTPaqueteTipoPublicacion;

public class PaqueteTipoPublicacion {

	private Paquete paquete;
	private TipoPublicacion tipoPublicacion;
	private Integer cantidad;

	//Constructores

	public PaqueteTipoPublicacion() {
		super();
	}

	public PaqueteTipoPublicacion(Paquete paquete, TipoPublicacion tipo_publicacion, Integer cantidad) {
		this.paquete = paquete;
		this.tipoPublicacion = tipo_publicacion;
		this.cantidad = cantidad;
	}

	//Getters y setters

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	public TipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tipo_publicacion) {
		this.tipoPublicacion = tipo_publicacion;
	}


	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	//Funciones

	public String getKey() {
		return this.tipoPublicacion.getKey();
	}

	public DTPaqueteTipoPublicacion getDT() {

		//DTPaquete dt_paquete = this.paquete.getDT();
		DTTipoPublicacion dt_tipo_publicacion = this.tipoPublicacion.getDT();
		return new DTPaqueteTipoPublicacion(/*dt_paquete,*/ dt_tipo_publicacion, this.cantidad);
	}

}
