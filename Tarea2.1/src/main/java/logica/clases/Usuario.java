package logica.clases;

import logica.dts.DTUsuario;
public abstract class Usuario {
	
	
	private String nickname;
	private String nombre;
	private String apellido;
	private String correo;
	
	private String clave;
	private String imagen;
	private EnumRol rol;

	
	//Constructores
	
	public Usuario() {
		super();
	}
	
	public Usuario(
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
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
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
	
	public String getKey() {
		return this.nickname;
	}

	// Funciones Abstractas

	// Retorna un string que varía según la  subclase. EL objetivo es tener un nombre lindo para mostrar
	public abstract String getNombreCompleto();

	// Retorna un DTUsuario, cargado con los datos de esta clase y la subclase correspondiente
	public abstract DTUsuario getDTUsuario();

}