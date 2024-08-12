package logica.clases;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import logica.dts.DTUsuario;
import logica.dts.DTPostulante;


public class Postulante extends Usuario {


    private Date fechaNacimiento;
    private String nacionalidad;
    private Map<String, PostulanteOfertaLaboral> postulaciones;

    //Constructores

    public Postulante() {
        super();
        //Inicializo mapa vacío
        this.postulaciones = new HashMap<String, PostulanteOfertaLaboral>();

    }

    public Postulante(DTPostulante data_postulante) {
        super(
                data_postulante.getNickname(), data_postulante.getNombre(), data_postulante.getApellido(), data_postulante.getCorreo(),
                data_postulante.getClave(), data_postulante.getImagen(), data_postulante.getRol()
        );
        this.fechaNacimiento = data_postulante.getFechaNacimiento();
        this.nacionalidad = data_postulante.getNacionalidad();
        //Inicializo mapa vacío
        this.postulaciones = new HashMap<String, PostulanteOfertaLaboral>();
    }

    //Getters y setters

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fecha_nacimiento) {
        this.fechaNacimiento = fecha_nacimiento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Map<String, PostulanteOfertaLaboral> getPostulaciones() {
        return postulaciones;
    }

    public void setPostulaciones(Map<String, PostulanteOfertaLaboral> postulaciones) {
        this.postulaciones = postulaciones;
    }


    //Funciones

    // Retorna un string, equivalente al identificador de esta clase
    // Se usa el identificador de la clase padre
    // Se usa principalmente en manejadores
    public String getKey() {
        //return super.getNickname();
        return super.getKey();
    }

    ////////Gestión de postulaciones asociadas //////////////////////

    // Retorna booleano que revisa si el postulante tienen una postulación para la oferta laboral con el nombre del parámetro
    // Retorna true si en el mapa de postulaciones, existe una postulación a oferta laboral
    // que tienen una oferta laboral con nombre igual al string recibido por parámetro
    public Boolean existePostulacion(String oferta_laboral_clave) {

        // Me fijo si el nombre de la oferta (clave) existe en el mapa de postulaciones
        return postulaciones.containsKey(oferta_laboral_clave);

    }

    public PostulanteOfertaLaboral obtenerPostulacion(String oferta_laboral_clave) {
        return this.postulaciones.get(oferta_laboral_clave);
    }

    // Recibe un objeto PostulanteOfertaLaboral y lo agrega al mapa de postulaciones
    // usa de clave la clave de la oferta laboral asociada a la postuación
    public void agregarPostulacion(PostulanteOfertaLaboral postulante_oferta_laboral) {
        String clave = postulante_oferta_laboral.getOferta_laboral().getKey();
        this.postulaciones.put(clave, postulante_oferta_laboral);
    }


    ////////Fin Gestión de postulaciones asociadas //////////////////////

    public DTPostulante getDT() {
		
		/*
		Map<String, DTPostulanteOfertaLaboral> dts_postulaciones = new HashMap<String, DTPostulanteOfertaLaboral>();
		
		Collection<PostulanteOfertaLaboral> objetos_postulaciones_de_postulante_col = this.postulaciones.values();
		
		Object[] objetos_postulaciones_de_postulante_array = objetos_postulaciones_de_postulante_col.toArray();      

		
		for (int i = 0; i < objetos_postulaciones_de_postulante_array.length; i++) {
			
			PostulanteOfertaLaboral postulacion_de_postulante = (PostulanteOfertaLaboral) objetos_postulaciones_de_postulante_array[i];
			
			DTPostulanteOfertaLaboral dt_postulacion_de_postulante =  postulacion_de_postulante.getDT();
			
			dts_postulaciones.put(dt_postulacion_de_postulante.getKey(), dt_postulacion_de_postulante);
		}
			
		return new DTPostulante(super.getNickname(), super.getNombre(), super.getApellido(), super.getCorreo(),
				this.fecha_nacimiento, this.nacionalidad, dts_postulaciones);
		*/


        return new DTPostulante(
                this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(),
                this.getClave(), this.getImagen(), this.getRol(),
                this.fechaNacimiento, this.nacionalidad);
    }

    //Funciones sobreescritas por abstractas en claes padre

    // Retorna un string resultado de concatenar los valores de atributos: nombre y apellido de clase padre
    @Override
    public String getNombreCompleto() {
        return super.getNombre() + " " + super.getApellido();
    }

    // Retorna un DTUsuario, cargado con los datos de esta clase y su clase padre
    @Override
    public DTUsuario getDTUsuario() {
        return new DTPostulante(
                this.getNickname(), this.getNombre(), this.getApellido(), this.getCorreo(),
                this.getClave(), this.getImagen(), this.getRol(),
                this.fechaNacimiento, this.nacionalidad);
    }


}
