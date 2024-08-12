package logica.dts;

import logica.clases.EnumRol;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DTPostulante extends DTUsuario {

	private Date fechaNacimiento;
	private String nacionalidad;
	private Map<String, DTPostulanteOfertaLaboral> postulaciones;

	// Constructores

	public DTPostulante(
			String nickname, String nombre, String apellido, String correo,
			String clave, String imagen, EnumRol rol,
			Date fecha_nacimiento, String nacionalidad
	) {
		super(nickname, nombre, apellido, correo, clave, imagen, rol);
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		//Inicializo mapa vacío
		this.postulaciones = new HashMap<String, DTPostulanteOfertaLaboral>();
	}
	
	public DTPostulante(
			String nickname, String nombre, String apellido, String correo,
			String clave, String imagen, EnumRol rol,
			Date fecha_nacimiento, String nacionalidad,
			Map<String, DTPostulanteOfertaLaboral> postulaciones) {
		super(nickname, nombre, apellido, correo, clave, imagen, rol);
		this.fechaNacimiento = fecha_nacimiento;
		this.nacionalidad = nacionalidad;
		this.postulaciones = postulaciones;
	}

	// Getters y setters

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getFechaNacimientoFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fechaNacimiento);
	}
	
	public String getFechaNacimientoFormatPicker() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(fechaNacimiento);
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public Map<String, DTPostulanteOfertaLaboral> getPostulaciones() {

		return this.postulaciones;
	}

	// Funciones
	public String toString() {
		return super.getNickname();
	}

	//Funciones sobreescritas por abstractas en claes padre

	// Retorna un string resultado de concatenar los valores de atributos: nombre y apellido de clase padre
	@Override
	public String getNombreCompleto() {
		return super.getNombre() + " " + super.getApellido();
	}

}
