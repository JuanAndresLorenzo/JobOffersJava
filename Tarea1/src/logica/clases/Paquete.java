package logica.clases;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;

public class Paquete {
	
	private String nombre;
	private String descripcion;
	private Integer validezDias;
	private Float descuento;
	private Date fechaAlta;
    private Map<String, PaqueteTipoPublicacion> tiposPublicacion;
	private String img;
	
	//Constructores
	
	public Paquete(String nombre, String descripcion, Integer validez_dias, Float descuento, Date fecha_alta, String img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validezDias = validez_dias;
		this.descuento = descuento;
		this.fechaAlta = fecha_alta;
		this.img = img;
		//Inicializo mapa vacío
		this.tiposPublicacion = new HashMap<String, PaqueteTipoPublicacion>();

	}

	//Getters y setters

	public String getNombre() {
		return nombre;
	}
	
	public String getImg() {
		return img;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getValidezDias() {
		return validezDias;
	}

	public void setValidezDias(Integer validez_dias) {
		this.validezDias = validez_dias;
	}

	public Float getDescuento() {
		return descuento;
	}

	public void setDescuento(Float descuento) {
		this.descuento = descuento;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fecha_alta) {
		this.fechaAlta = fecha_alta;
	}


	public Map<String, PaqueteTipoPublicacion> getTiposPublicaciones() {
		return tiposPublicacion;
	}

	public void setTiposPublicaciones(Map<String, PaqueteTipoPublicacion> tipos_publicaciones) {
		this.tiposPublicacion = tipos_publicaciones;
	}

	//Funciones

	public String getKey() {
		return this.nombre;
	}

	// Retorna la sumatoria de todos los costos de los tipos de publicaciones asociadas,
	// multiplicado en cada caso por la cnatidad que corresponda
	// sin aplicar ningún descuento
	public Float getCostoSinDescuento() {

		Float resultado = (float) 0;

		if (!this.tiposPublicacion.isEmpty()) {

			for (String clave : this.tiposPublicacion.keySet()) {

				PaqueteTipoPublicacion valor = this.tiposPublicacion.get(clave);

				resultado += (valor.getTipoPublicacion().getCosto()) * valor.getCantidad();

			}

		}

		return resultado;

	}

	// Retorna el resultad de aplicar el % de descuento basado en el atributo descuento,
	// al total retornado por la función de clase: getCostoSinDescuento()
	public Float getCostoConDescuento() {

		Float costo_sin_descuento = this.getCostoSinDescuento();

		Float valor_descuento = (costo_sin_descuento * this.descuento) / 100;

		return costo_sin_descuento - valor_descuento;
	}

	// Retorna de forma unificada el resultado de la función getCostoConDescuento()
	// Se separa por si en un futuro el cálculo cambia
	public Float getCosto() {

		return this.getCostoConDescuento();
	}

	// Retorna booleano que revisa si el paquete tiene una asociación para el tipo de publicación con el nombre del parámetro
	// Retorna true si en el mapa de tipos_publicaciones, que mantiene los objetos asociativos paquete-tipo de publicación
	// existe un tipo de publicación con nombre igual al string recibido por parámetro
	public Boolean existeTipoPublicacion(String tipo_de_publicacion_nombre) {

		// Me fijo si el nombre del tipo de publicación (clave) existe en el mapa de tipos_publicaciones
		return this.tiposPublicacion.containsKey(tipo_de_publicacion_nombre);

	}

	public PaqueteTipoPublicacion obtenerTipoPublicacion(String tipo_de_publicacion_nombre) {
		return this.tiposPublicacion.get(tipo_de_publicacion_nombre);
	}

	// Recibe un objeto PaqueteTipoPublicacion y lo agrega al mapa de tipos_publicaciones
	// usa de clave la clave del tipo de publicación asociada al objeto PaqueteTipoPublicacion
	public void agregarTipoPublicacion(PaqueteTipoPublicacion paquete_tipo_publicacion) {

		String clave = paquete_tipo_publicacion.getTipoPublicacion().getKey();
		this.tiposPublicacion.put(clave, paquete_tipo_publicacion);
	}

	public DTPaquete getDT() {

		Map<String, DTPaqueteTipoPublicacion> dts_tipos_publicaciones = new HashMap<String, DTPaqueteTipoPublicacion>();

		Collection<PaqueteTipoPublicacion> objetos_tipos_publicaciones_del_paquete_col = this.tiposPublicacion.values();
		Object[] objetos_tipos_publicaciones_del_paquete_array = objetos_tipos_publicaciones_del_paquete_col.toArray();

		for (int i = 0; i < objetos_tipos_publicaciones_del_paquete_array.length; i++) {

			PaqueteTipoPublicacion tipo_publicacion_de_paquete = (PaqueteTipoPublicacion) objetos_tipos_publicaciones_del_paquete_array[i];

			DTPaqueteTipoPublicacion dt_tipo_publicacion_de_paquete = tipo_publicacion_de_paquete.getDT();

			dts_tipos_publicaciones.put(dt_tipo_publicacion_de_paquete.getKey(), dt_tipo_publicacion_de_paquete);
		}

		return new DTPaquete(this.nombre, this.descripcion, this.validezDias, this.descuento, this.fechaAlta, dts_tipos_publicaciones, this.getCosto());
	}

}
