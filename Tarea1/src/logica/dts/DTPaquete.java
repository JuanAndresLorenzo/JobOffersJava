package logica.dts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DTPaquete {

	private String nombre;
	private String descripcion;
	private Integer validezDias;
	private Float descuento;
	private Date fechaAlta;
	private Map<String, DTPaqueteTipoPublicacion> tiposPublicacion;
	private String img;

	//Atributos calculados en la clase original
	private Float costo;

	//Constructores

	//Constructor que se usa para crear un paquete
	public DTPaquete(String nombre, String descripcion, Integer validez_dias, Float descuento, Date fecha_alta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validezDias = validez_dias;
		this.descuento = descuento;
		this.fechaAlta = fecha_alta;
		this.img = img;
		//Inicializo mapa vacío
		this.tiposPublicacion = new HashMap<String, DTPaqueteTipoPublicacion>();
	}

	public DTPaquete(String nombre, String descripcion, Integer validez_dias, Float descuento, Float costo, Date fecha_alta, String img) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validezDias = validez_dias;
		this.descuento = descuento;
		this.fechaAlta = fecha_alta;
		//Inicializo mapa vacío
		this.tiposPublicacion = new HashMap<String, DTPaqueteTipoPublicacion>();
		this.img = img;
		this.costo = costo;
	}

	public DTPaquete(String nombre, String descripcion, Integer validez_dias, Float descuento, Date fecha_alta, Map<String, DTPaqueteTipoPublicacion> tipos_publicaciones, Float costo) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.validezDias = validez_dias;
		this.descuento = descuento;
		this.fechaAlta = fecha_alta;
		this.tiposPublicacion = tipos_publicaciones;

		this.costo = costo;

	}

	//Getters y setters

	public String getNombre() {
		return nombre;
	}

	public String getImg() {
		return img;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public Integer getValidezDias() {
		return validezDias;
	}

	public Float getDescuento() {
		return descuento;
	}


	public Date getFechaAlta() {
		return fechaAlta;
	}

	public String getFechaAltaFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fechaAlta);
	}

	public Map<String, DTPaqueteTipoPublicacion> getTiposPublicaciones() {

		return this.tiposPublicacion;
	}


	public Float getCosto() {
		return costo;
	}

	//Funciones

	public String getKey() {
		return nombre;
	}

	public String toString() {
		return nombre;
	}

}
