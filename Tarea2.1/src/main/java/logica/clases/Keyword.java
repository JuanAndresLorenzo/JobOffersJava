package logica.clases;

import logica.dts.DTKeyword;

public class Keyword {

	private String nombre;
	
	//Constructores
	
	public Keyword() {
		super();
	}
	
	public Keyword(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	//Getters y setters
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	//Funciones
	
	public String getKey() {
		return this.nombre;
	}	

	public DTKeyword getDT() {
		return new DTKeyword(this.nombre);
	}

}
