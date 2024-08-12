package logica.dts;

import logica.clases.EnumRol;

public class DTEmpresa extends DTUsuario {

	private String nombreEmpresa;
	private String descripcion;
	private String sitioWeb;

	// Constructores

	public DTEmpresa(String nickname, String nombre, String apellido, String correo, String clave, String imagen,
			EnumRol rol, String nombre_empresa, String descripcion, String sitio_web) {
		super(nickname, nombre, apellido, correo, clave, imagen, rol);
		this.nombreEmpresa = nombre_empresa;
		this.descripcion = descripcion;
		this.sitioWeb = sitio_web;
	}

	// Getters y setters

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getSitioWeb() {
		return sitioWeb;
	}

	// Funciones
	public String toString() {
		return super.getNickname();
	}

	// Funciones sobreescritas por abstractas en claes padre

	// Retorna un string resultado de concatenar los valores de atributos:
	// nombre_empresa de esta clase
	@Override
	public String getNombreCompleto() {
		return this.nombreEmpresa;
	}

}
