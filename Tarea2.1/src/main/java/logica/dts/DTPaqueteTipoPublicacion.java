package logica.dts;


public class DTPaqueteTipoPublicacion {

	//private DTPaquete paquete;
	private DTTipoPublicacion tipoPublicacion;
	private Integer cantidad;

	//Constructores

	public DTPaqueteTipoPublicacion(/*DTPaquete paquete,*/ DTTipoPublicacion tipo_publicacion, Integer cantidad) {
		//this.paquete = paquete;
		this.tipoPublicacion = tipo_publicacion;
		this.cantidad = cantidad;
	}

	/*public DTPaquete getPaquete() {
		return paquete;
	}*/

	public DTTipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
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

	public String toString() {

		if (this.tipoPublicacion != null) {

			return "Tipo: " + this.tipoPublicacion.getNombre() + " - Cant.: " + this.cantidad;

		} else {
			//Caso cargo DT vacío para combo
			return "";
		}

	}

}