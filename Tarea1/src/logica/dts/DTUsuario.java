package logica.dts;

import logica.clases.EnumRol;

public abstract class DTUsuario {

	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;

	private String clave;
	private String imagen;
	private EnumRol rol;
	
	//Constructores
	
	public DTUsuario(
			String nickname, String nombre, String apellido, String correo,
			String clave, String imagen, EnumRol rol
	) {
		super();
		this.nickname = nickname;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;

		this.clave = clave;
		this.imagen = imagen;
		this.rol = rol;

	}
	
	//Getters y setters
	
	public String getNickname() {
		return nickname;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public String getCorreo() {
		return correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public EnumRol getRol() {
		return rol;
	}

	public void setRol(EnumRol rol) {
		this.rol = rol;
	}

	//Funciones

	public String toString() {
		return nickname;
	}

	// Retorna un string que varía según la  subclase. EL objetivo es tener un nombre lindo para mostrar
	public abstract String getNombreCompleto();

}
