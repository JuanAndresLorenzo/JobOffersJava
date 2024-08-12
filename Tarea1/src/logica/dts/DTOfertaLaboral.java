package logica.dts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.clases.EnumEstadoOL;

public class DTOfertaLaboral {

	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private Float remuneracion;
	private Date fechaAlta;
	private DTEmpresa empresa;
	private DTTipoPublicacion tipoPublicacion;
	private EnumEstadoOL estado;
	private String paquete;
	private String img;
	private List<String> keylist;

	private Map<String, DTKeyword> keywords;

	private Map<String, DTPostulanteOfertaLaboral> postulaciones;

	// Atributos calculados en la clase original

	private Date fechaVencimiento;
	private Boolean vigente;

	// Constructores

	// Constructor completo con asociaciones map
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
			Float remuneracion, Date fecha_alta, Date fecha_vencimiento, Boolean vigente, DTEmpresa empresa,
			DTTipoPublicacion tipo_publicacion, Map<String, DTKeyword> keywords, EnumEstadoOL estado) {

		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaAlta = fecha_alta;
		this.fechaVencimiento = fecha_vencimiento;
		this.vigente = vigente;
		this.empresa = empresa;
		this.tipoPublicacion = tipo_publicacion;
		this.keywords = keywords;
		this.estado = estado;
	}

	// PORQUE HAY 3 CONSTRUCTORAS ???

	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
			Float remuneracion, Date fecha_alta, Date fecha_vencimiento, Boolean vigente, DTEmpresa empresa,
			DTTipoPublicacion tipo_publicacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaAlta = fecha_alta;
		this.fechaVencimiento = fecha_vencimiento;
		this.vigente = vigente;
		this.empresa = empresa;
		this.tipoPublicacion = tipo_publicacion;
		// Inicializo mapas vacíos
		this.keywords = new HashMap<String, DTKeyword>();
		this.postulaciones = new HashMap<String, DTPostulanteOfertaLaboral>();
	}

	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,
			Float remuneracion, Date fecha_alta, DTEmpresa empresa, DTTipoPublicacion tipo_publicacion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaAlta = fecha_alta;
		this.empresa = empresa;
		this.tipoPublicacion = tipo_publicacion;
		// Inicializo mapas vacíos
		this.keywords = new HashMap<String, DTKeyword>();
		this.postulaciones = new HashMap<String, DTPostulanteOfertaLaboral>();
	}

	// Constructor que se usa para pasarle info a crear una oferta laboral
	public DTOfertaLaboral(String nombre, String descripcion, String ciudad, String departamento, String horario,

						   Float remuneracion, Date fecha_alta, String estado, String paquete, String img, List keylist, DTEmpresa empresa, DTTipoPublicacion tipoPublicacion) {
		 
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.ciudad = ciudad;
		this.departamento = departamento;
		this.horario = horario;
		this.remuneracion = remuneracion;
		this.fechaAlta = fecha_alta;
		String estadoMayus = estado.toUpperCase();
		EnumEstadoOL estadoEnum = EnumEstadoOL.valueOf(estadoMayus);
		this.estado = estadoEnum;
		this.paquete = paquete;
		this.img = img;
		this.keylist = keylist;
		this.empresa = empresa;
		this.tipoPublicacion = tipoPublicacion;
		this.keywords = new HashMap<String, DTKeyword>();
		this.postulaciones = new HashMap<String, DTPostulanteOfertaLaboral>();
	}

	/*
	 * public DTOfertaLaboral(String nombre, String descripcion, String ciudad,
	 * String departamento, String horario, Float remuneracion, Date fecha_alta) {
	 * super(); this.nombre = nombre; this.descripcion = descripcion; this.ciudad =
	 * ciudad; this.departamento = departamento; this.horario = horario;
	 * this.remuneracion = remuneracion; this.fecha_alta = fecha_alta; //Inicializo
	 * sin tipo de publicación this.tipo_publicacion = null; //Inicializo mapas
	 * vacíos this.keywords = new HashMap<String, DTKeyword>(); this.postulaciones =
	 * new HashMap<String, DTPostulanteOfertaLaboral>(); }
	 */

	// Getters y setters

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public String getHorario() {
		return horario;
	}

	public Float getRemuneracion() {
		return remuneracion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public Date getFechaAltaVencimiento() {
		return fechaVencimiento;
	}

	public DTEmpresa getEmpresa() {
		return empresa;
	}

	public DTTipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

	public Map<String, DTKeyword> getKeywords() {
		return this.keywords;
	}

	public Map<String, DTPostulanteOfertaLaboral> getPostulaciones() {
		return this.postulaciones;
	}
	
	public String getPaquete() {
		return paquete;
	}
	
	public String getImg() {
		return img;
	}
	
	public List getKeyList() {
		return keylist;
	}

	public String getKey() {
		return nombre;
	}

	public String toString() {
		return nombre;
	}

	public EnumEstadoOL getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoOL estado) {
		this.estado = estado;
	}

	public String getFechaAltaFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fechaAlta);
	}

	public String getFechaVencimientoFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fechaVencimiento);
	}

	public Boolean getVigente() {

		if (vigente != null && vigente == true)
			return true;

		return false;
	}

	public String getVigenteFormat() {

		if (this.getVigente()) {
			return "Sí";
		}
		return "No";

	}

	public String getEstadoFormat() {
		switch (this.estado) {
		case CONFIRMADA:
			return "CONFIRMADA";
		case RECHAZADA:
			return "RECHAZADA";
		case INGRESADA:
			return "INGRESADA";
		}
		return "";
	}

}
