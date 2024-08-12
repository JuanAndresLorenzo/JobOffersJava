package com.tarea21.model;

public class Mensaje {
	
	private EnumTipoMensaje tipo;
	private String texto;
	
	public Mensaje(EnumTipoMensaje tipo, String texto) {
		super();
		this.tipo = tipo;
		this.texto = texto;
	}

	public EnumTipoMensaje getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoMensaje tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
	

}
