package logica.dts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DTTipoPublicacion {

	private String nombre;
	private String descripcion;
	private Integer duracionDias;
	private Integer exposicion;
	private Float costo;
	private Date fechaAlta;

	//Constructores

	public DTTipoPublicacion(String nombre, String descripcion, Integer duracion_dias, Integer exposicion, Float costo,
							 Date fecha_alta) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.duracionDias = duracion_dias;
		this.exposicion = exposicion;
		this.costo = costo;
		this.fechaAlta = fecha_alta;
	}

	//Getters y setters

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Integer getDuracion_dias() {
		return duracionDias;
	}

	public Integer getExposicion() {
		return exposicion;
	}

	public Float getCosto() {
		return costo;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public String getFechaAltaFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fechaAlta);
	}

	//Funciones

	public String getKey() {
		return nombre;
	}

	public String toString() {
		return nombre;
	}

}