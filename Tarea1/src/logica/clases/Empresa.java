package logica.clases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import logica.dts.DTUsuario;
import logica.dts.DTEmpresa;

public class Empresa extends Usuario {

	private String nombreEmpresa;
	private String descripcion;
	private String linkWeb;
	private Map<String, OfertaLaboral> ofertasLaborales;
	private Map<String, EmpresaPaquete> paquetesComprados; 

	
	//Constructores
	public Empresa() {

		super();
		// Inicializo mapa vacío
		this.ofertasLaborales = new HashMap<String, OfertaLaboral>();

	}

	public Empresa(DTEmpresa data_empresa) {
		super(data_empresa.getNickname(), data_empresa.getNombre(), data_empresa.getApellido(),
				data_empresa.getCorreo(), data_empresa.getClave(), data_empresa.getImagen(), data_empresa.getRol());
		this.nombreEmpresa = data_empresa.getNombreEmpresa();
		this.descripcion = data_empresa.getDescripcion();
		this.linkWeb = data_empresa.getSitioWeb();
		//Inicializo mapa vacío
		this.ofertasLaborales = new HashMap<String, OfertaLaboral>();
		this.paquetesComprados = new HashMap<String, EmpresaPaquete>();

	}

	// Getters y setters

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombre_empresa) {
		this.nombreEmpresa = nombre_empresa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getLinkWeb() {
		return linkWeb;
	}

	public void setLinkWeb(String link_web) {
		this.linkWeb = link_web;
	}

	public Map<String, OfertaLaboral> getOfertasLaborales() {
		return this.ofertasLaborales;
	}

	public void setOfertasLaborales(Map<String, OfertaLaboral> ofertas_laborales) {
		this.ofertasLaborales = ofertas_laborales;
	}
	
	public Map<String, EmpresaPaquete> getPaquetesComprados() {
		return this.paquetesComprados;
	}

	public void setPaquetesComprados(Map<String, EmpresaPaquete> paquetes_comprados) {
		this.paquetesComprados = paquetes_comprados;
	}


	// Funciones

	// Retorna un string, equivalente al identificador de esta clase
	// Se usa el identificador de la clase padre
	// Se usa principalmente en manejadores
	public String getKey() {
		// return super.getNickname();
		return super.getKey();
	}

	//////// Gestión de ofertas asociadas //////////////////////

	// Retorna booleano que revisa si la empresa tiene asociada una oferta laboral
	// con el nombre del parámetro
	// Retorna true si en el mapa de ofertas_laborales, existe una oferta laboral
	// con
	// nombre igual al string recibido por parámetro
	public Boolean existeOfertaLaboral(String oferta_laboral_nombre) {

		// Me fijo si el nombre de la oferta (clave) existe en el mapa de
		// ofertas_laborales
		return ofertasLaborales.containsKey(oferta_laboral_nombre);

	}

	// TODO: Reemplace
	public OfertaLaboral obtenerOfertaLaboral(String nombre_oferta_laboral) {
		return this.ofertasLaborales.get(nombre_oferta_laboral);
	}

	public OfertaLaboral getOfertaLaboral(String nombre_oferta_laboral) {
		return this.ofertasLaborales.get(nombre_oferta_laboral);
	}

	// Recibe un objeto OfertaLaboral y lo agrega al mapa de ofertas_laborales
	// usa de clave la clave de la oferta laboral que será asociada a la empresa
	public void agregarOfertaLaboral(OfertaLaboral oferta_laboral) {
		String clave = oferta_laboral.getKey();
		this.ofertasLaborales.put(clave, oferta_laboral);
	}

	public OfertaLaboral[] getOfertasLaboralesArray() {

		if (ofertasLaborales == null)
			return null;
		else {
			Collection<OfertaLaboral> objetos = ofertasLaborales.values();
			Object[] objects = objetos.toArray();
			OfertaLaboral[] objetos_resultado = new OfertaLaboral[objects.length];
			for (int i = 0; i < objects.length; i++) {
				objetos_resultado[i] = (OfertaLaboral) objects[i];
			}
			return objetos_resultado;
		}
	}

	public List<OfertaLaboral> getOfertasLaboralesIngresadas() {

		if (ofertasLaborales == null)
			return null;
		else {

			List<OfertaLaboral> ofertas_laborales_ingresadas = new ArrayList<OfertaLaboral>();

			Collection<OfertaLaboral> objetos = ofertasLaborales.values();
			Object[] objects = objetos.toArray();
			for (int i = 0; i < objects.length; i++) {
				OfertaLaboral oferta_laboral = (OfertaLaboral) objects[i];
				if (oferta_laboral.getEstado() == EnumEstadoOL.INGRESADA) {
					ofertas_laborales_ingresadas.add(oferta_laboral);
				}
			}
			return ofertas_laborales_ingresadas;
			// return objetos_resultado;
		}
	}

	public List<OfertaLaboral> getOfertasLaboralesVigentesList() {

		if (ofertasLaborales == null)
			return null;
		else {

			List<OfertaLaboral> ofertas_laborales_vigentes = new ArrayList<OfertaLaboral>();

			Collection<OfertaLaboral> objetos = ofertasLaborales.values();
			Object[] objects = objetos.toArray();
			for (int i = 0; i < objects.length; i++) {
				OfertaLaboral oferta_laboral = (OfertaLaboral) objects[i];
				if (oferta_laboral.estaVigente()) {
					ofertas_laborales_vigentes.add(oferta_laboral);
				}
			}

			return ofertas_laborales_vigentes;
			// return objetos_resultado;
		}
	}

	public List<OfertaLaboral> getOfertasLaboralesConfirmadasList() {

		if (ofertasLaborales == null)
			return null;
		else {

			List<OfertaLaboral> ofertas_laborales_confirmadas = new ArrayList<OfertaLaboral>();

			Collection<OfertaLaboral> objetos = ofertasLaborales.values();
			Object[] objects = objetos.toArray();
			for (int i = 0; i < objects.length; i++) {
				OfertaLaboral oferta_laboral = (OfertaLaboral) objects[i];
				if (oferta_laboral.estaConfirmada()) {
					ofertas_laborales_confirmadas.add(oferta_laboral);
				}
			}

			return ofertas_laborales_confirmadas;
			// return objetos_resultado;
		}
	}

	public List<OfertaLaboral> getOfertasLaboralesVigentesConfirmadasList() {

		if (ofertasLaborales == null)
			return null;
		else {

			List<OfertaLaboral> ofertas_laborales_vigentes_confirmadas = new ArrayList<OfertaLaboral>();

			Collection<OfertaLaboral> objetos = ofertasLaborales.values();
			Object[] objects = objetos.toArray();
			for (int i = 0; i < objects.length; i++) {
				OfertaLaboral oferta_laboral = (OfertaLaboral) objects[i];
				if (oferta_laboral.estaVigente() && oferta_laboral.estaConfirmada()) {
					ofertas_laborales_vigentes_confirmadas.add(oferta_laboral);
				}
			}

			return ofertas_laborales_vigentes_confirmadas;
			// return objetos_resultado;
		}
	}

	////////Fin Gestión de ofertas asociadas //////////////////////
		
	
	////////Gestión de paquetes comprados asociadas //////////////////////
	
	// Retorna booleano que revisa si la oferta laboral tienen un keyword asociado con clave igual al valor del parámetro
	public Boolean existePaqueteComprado(String empresa_paquete_clave) {
	
		// Me fijo si la clave del postulante existe en el mapa de Keywords
		return paquetesComprados.containsKey(empresa_paquete_clave);
	
	}
	
	public EmpresaPaquete obtenerPaqueteComprado(String empresa_paquete_clave) {
		return this.paquetesComprados.get(empresa_paquete_clave);
	}
	
	// Recibe un objeto Keyword y lo agrega al mapa de Keywords
	// usa de clave la clave de la keyword
	public void agregarPaqueteComprado(EmpresaPaquete empresa_paquete_comprado) {
		String clave = empresa_paquete_comprado.getPaquete().getKey();
		this.paquetesComprados.put(clave, empresa_paquete_comprado);
	}
	
	////////Fin Gestión de paquetes comprados asociadas //////////////////////

	public DTEmpresa getDT() {
		return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(),
				this.getClave(), this.getImagen(), this.getRol(), this.getNombreEmpresa(), this.getDescripcion(),
				this.getLinkWeb());
	}

	// Funciones sobreescritas por abstractas en claes padre

	@Override
	public String getNombreCompleto() {
		return this.nombreEmpresa;
	}

	// Retorna un DTUsuario, cargado con los datos de esta clase y su clase padre
	@Override
	public DTUsuario getDTUsuario() {
		return new DTEmpresa(this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(),
				this.getClave(), this.getImagen(), this.getRol(), this.getNombreEmpresa(), this.getDescripcion(),
				this.getLinkWeb());
	}
}
