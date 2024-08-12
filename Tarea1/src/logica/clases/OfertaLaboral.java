package logica.clases;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import logica.dts.DTEmpresa;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTTipoPublicacion;

public class OfertaLaboral {

	private String nombre;
	private String descripcion;
	private String ciudad;
	private String departamento;
	private String horario;
	private Float remuneracion;
	private Date fechaAlta;
	private Empresa empresa;
	private TipoPublicacion tipoPublicacion;
	private EnumEstadoOL estado;
	private Paquete paquete;
	private String img;
	private Map<String, Keyword> keywords;
	private Map<String, PostulanteOfertaLaboral> postulaciones;
	

	// Constructores

	public OfertaLaboral() {
		super();
		// Inicializo mapas vacíos
		this.keywords = new HashMap<String, Keyword>();
		this.postulaciones = new HashMap<String, PostulanteOfertaLaboral>();
	}

	public OfertaLaboral(Empresa empresa, TipoPublicacion tipo_publicacion, String nombre, String descripcion, String ciudad, String departamento, String horario,
			Float remuneracion, EnumEstadoOL estado, Paquete Paquete, String img, Date fecha_alta)  {

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
		this.estado = estado;
		this.paquete = paquete;
		this.img = img;
		//Inicializo mapas vacíos
		this.keywords = new HashMap<String, Keyword>();
		this.postulaciones = new HashMap<String, PostulanteOfertaLaboral>();
	}

	// Getters y setters

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

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public Float getRemuneracion() {
		return remuneracion;
	}

	public void setRemuneracion(Float remuneracion) {
		this.remuneracion = remuneracion;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fecha_alta) {
		this.fechaAlta = fecha_alta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public TipoPublicacion getTipoPublicacion() {
		return tipoPublicacion;
	}

	public void setTipoPublicacion(TipoPublicacion tipo_publicacion) {
		this.tipoPublicacion = tipo_publicacion;
	}

	public Map<String, Keyword> getKeyword() {
		return keywords;
	}

	public void setKeywords(Map<String, Keyword> keywords) {
		this.keywords = keywords;
	}

	public Map<String, PostulanteOfertaLaboral> getPostulaciones() {
		return postulaciones;
	}

	public void setPostulaciones(Map<String, PostulanteOfertaLaboral> postulaciones) {
		this.postulaciones = postulaciones;
	}

	public EnumEstadoOL getEstado() {
		return estado;
	}

	public void setEstado(EnumEstadoOL estado) {
		this.estado = estado;
	}
	
	public String getImg() {
		return img;
	}
	
	public void setImg(String img) {
		this.img=img;
	}
	
	public Paquete getPaquete() {
		return paquete;
	}	
	
	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	// Funciones

	public String getKey() {
		return this.nombre;
	}

	public Date getFechaVencimiento() {

		Date oferta_fecha_alta = this.fechaAlta;
		Integer tp_duracion_dias = this.tipoPublicacion.getDuracion_dias();

		// GregorianCalendar cal = new GregorianCalendar();
		Calendar cal = Calendar.getInstance();
		cal.setTime(oferta_fecha_alta);
		cal.add(Calendar.DATE, tp_duracion_dias);

		Date oferta_fecha_vencimiento = cal.getTime();

		return oferta_fecha_vencimiento;
	}

	public Boolean estaVigente() {

		Date oferta_fecha_vencimiento = this.getFechaVencimiento();

		// Date fecha_actual = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date fecha_actual = calendar.getTime();

		Boolean esta_vigente = oferta_fecha_vencimiento.compareTo(fecha_actual) >= 0;

		// Si la fecha actual es anterior a la fecha de vencimiento, o son iguales, está
		// vigente
		return esta_vigente;

	}

	public Boolean estaConfirmada() {

		return this.estado == EnumEstadoOL.CONFIRMADA;
	}

	public Float getCosto() {

		return this.tipoPublicacion.getCosto();
	}

////////Gestión de keywords asociadas //////////////////////

	// Retorna booleano que revisa si la oferta laboral tienen un keyword asociado
	// con clave igual al valor del parámetro
	public Boolean existeKeyword(String keyword_clave) {

		// Me fijo si la clave del postulante existe en el mapa de Keywords
		return keywords.containsKey(keyword_clave);

	}

	public Keyword obtenerKeyword(String keyword_clave) {
		return this.keywords.get(keyword_clave);
	}

	// Recibe un objeto Keyword y lo agrega al mapa de Keywords
	// usa de clave la clave de la keyword
	public void agregarKeyword(Keyword keyword) {
		String clave = keyword.getKey();
		this.keywords.put(clave, keyword);
	}

	//////// Fin Gestión de keywords asociadas //////////////////////

	//////// Gestión de postulaciones asociadas //////////////////////

	// Retorna booleano que revisa si el postulante tienen una postulación para el
	// postulante con clave igual al valor del parámetro
	// Retorna true si en el mapa de postulaciones, existe una postulación a oferta
	// laboral
	// que tiene un postulante con clave igual al string recibido por parámetro
	public Boolean existePostulacion(String postulante_clave) {

		// Me fijo si la clave del postulante existe en el mapa de postulaciones
		return postulaciones.containsKey(postulante_clave);

	}

	public PostulanteOfertaLaboral obtenerPostulacion(String postulante_clave) {
		return this.postulaciones.get(postulante_clave);
	}

	// Recibe un objeto PostulanteOfertaLaboral y lo agrega al mapa de postulaciones
	// usa de clave la clave del postulante asociado a la postuación
	public void agregarPostulacion(PostulanteOfertaLaboral postulante_oferta_laboral) {
		String clave = postulante_oferta_laboral.getPostulante().getKey();
		this.postulaciones.put(clave, postulante_oferta_laboral);
	}

	//////// Fin Gestión de postulaciones asociadas //////////////////////

	// Retorna un dt, armado en base a los valores de los parámetros de la clase
	// En este caso, en el dt no se retornan las asociaciones a postulaciones ni a
	// keywords
	public DTOfertaLaboral getDT() {

		DTEmpresa dt_empresa = this.empresa.getDT();
		DTTipoPublicacion dt_tipo_publicacion = this.tipoPublicacion.getDT();

		Map<String, DTKeyword> dts_keywords = new HashMap<String, DTKeyword>();

		for (String clave : this.keywords.keySet()) {

			Keyword keyword = this.keywords.get(clave);

			if (keyword != null) {
				DTKeyword dt_keyword = keyword.getDT();
				dts_keywords.put(clave, dt_keyword);
			}

		}

		return new DTOfertaLaboral(this.nombre, this.descripcion, this.ciudad, this.departamento, this.horario,
				this.remuneracion, this.fechaAlta, this.getFechaVencimiento(), this.estaVigente(), dt_empresa,
				dt_tipo_publicacion, dts_keywords, this.estado);
	}

}
