package logica.dts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DTPostulanteOfertaLaboral {
	private Date fecha;
	private String cvReducido;
	private String motivacion;
	private String adjuntos;
	private DTPostulante postulante;
	private DTOfertaLaboral ofertaLaboral;

	public DTPostulanteOfertaLaboral(Date fecha, String cv_reducido, String motivacion, String adjuntos) {
		super();
		this.fecha = fecha;
		this.cvReducido = cv_reducido;
		this.motivacion = motivacion;
		this.adjuntos = adjuntos;
	}

	public DTPostulanteOfertaLaboral(Date fecha, String cv_reducido, String motivacion, String adjuntos,
			DTPostulante postulante, DTOfertaLaboral oferta_laboral) {
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

	public String getFechaFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fecha);
	}

	public String getCvReducido() {
		return cvReducido;

	}

	public String getMotivacion() {
		return motivacion;
	}

	public String getAdjuntos() {
		return adjuntos;
	}

	public DTPostulante getPostulante() {
		return postulante;
	}

	public DTOfertaLaboral getOfertaLaboral() {
		return ofertaLaboral;
	}

	// Funciones

	public String getKey() {

		return this.ofertaLaboral.getKey();
	}

	public String toString() {
		// return "OFERTA LABORAL: " + this.oferta_laboral.getNombre();
		if (this.postulante != null && this.ofertaLaboral != null) {

			// return "OFERTA LABORAL: " + this.oferta_laboral.getNombre() + " - POSTULANTE:
			// " + this.postulante.getNickname() + " - FECHA POSTULACIÓN:
			// "+this.getFechaFormat();

			return "Postulante: " + this.postulante.getNickname() + " - Oferta: " + this.ofertaLaboral.getNombre()
					+ " - Fecha: " + this.getFechaFormat();

		} else {
			// Caso cargo DT vacío para combo
			return "";
		}

	}

}
