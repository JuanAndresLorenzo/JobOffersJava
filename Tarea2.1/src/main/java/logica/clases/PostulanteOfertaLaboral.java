package logica.clases;

import java.util.Date;

import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;

public class PostulanteOfertaLaboral {
	private Date fecha;
	private String cvReducido;
	private String motivacion;
	private String adjuntos;
	private Postulante postulante;
	private OfertaLaboral ofertaLaboral;
	
	public PostulanteOfertaLaboral(Date fecha, String cv_reducido, String motivacion, String adjuntos, Postulante postulante, OfertaLaboral oferta_laboral) {
		super();
		this.fecha = fecha;
		this.cvReducido = cv_reducido;
		this.motivacion = motivacion;
		this.adjuntos = adjuntos;
		this.postulante = postulante;
		this.ofertaLaboral = oferta_laboral;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getCv_reducido() {
		return cvReducido;
	}

	public void setCv_reducido(String cv_reducido) {
		this.cvReducido = cv_reducido;
	}

	public String getMotivacion() {
		return motivacion;
	}

	public void setMotivacion(String motivacion) {
		this.motivacion = motivacion;
	}

	public String getAdjuntos() {
		return adjuntos;
	}

	public void setAdjuntos(String adjuntos) {
		this.adjuntos = adjuntos;
	}

	public Postulante getPostulante() {
		return postulante;
	}

	public void setPostulante(Postulante postulante) {
		this.postulante = postulante;
	}

	public OfertaLaboral getOferta_laboral() {
		return ofertaLaboral;
	}

	public void setOferta_laboral(OfertaLaboral oferta_laboral) {
		this.ofertaLaboral = oferta_laboral;
	}
	
	//Funciones

	public String getKey() {
		return this.ofertaLaboral.getKey();
	}
	
	public DTPostulanteOfertaLaboral getDT() {
		
		DTOfertaLaboral dt_oferta_laboral = this.ofertaLaboral.getDT();
		DTPostulante dt_postulante = this.postulante.getDT();

		return new DTPostulanteOfertaLaboral(this.fecha, this.cvReducido, this.motivacion, this.adjuntos, dt_postulante, dt_oferta_laboral);
		//return new DTPostulanteOfertaLaboral(this.fecha, this.cv_reducido, this.motivacion, this.adjuntos);

	}
	
	
}
