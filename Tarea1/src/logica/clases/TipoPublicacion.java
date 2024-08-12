package logica.clases;

import java.util.Date;

import logica.dts.DTTipoPublicacion;

public class TipoPublicacion {
	
	private String nombre;
	private String descripcion;
	private Integer duracionDias;
	private Integer exposicion;
	private Float costo;
	private Date fechaAlta;
	
	//Constructores
	
	public TipoPublicacion() {
		super();
	}
	
	public TipoPublicacion(String nombre, String descripcion, Integer duracion_dias, Integer exposicion, Float costo,
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
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getDuracion_dias() {
		return duracionDias;
	}
	public void setDuracion_dias(Integer duracion_dias) {
		this.duracionDias = duracion_dias;
	}
	public Integer getExposicion() {
		return exposicion;
	}
	public void setExposicion(Integer exposicion) {
		this.exposicion = exposicion;
	}
	public Float getCosto() {
		return costo;
	}
	public void setCosto(Float costo) {
		this.costo = costo;
	}
	public Date getFechaAlta() {
		return fechaAlta;
	}
	public void setFechaAlta(Date fecha_alta) {
		this.fechaAlta = fecha_alta;
	}
	
	
	//Funciones

	public String getKey() {
		return this.nombre;
	}	
	
	public DTTipoPublicacion getDT() {
		return new DTTipoPublicacion(this.nombre, this.descripcion, this.duracionDias, this.exposicion, this.costo, this.fechaAlta);
	}
}
