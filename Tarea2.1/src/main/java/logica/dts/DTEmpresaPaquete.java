package logica.dts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DTEmpresaPaquete {

	private DTEmpresa empresa;
	private DTPaquete paquete;
	private Date fecha;

	//Atributos calculados en la clase original
	private Date fechaVencimiento;

	//Constructores

	public DTEmpresaPaquete(DTEmpresa empresa, DTPaquete paquete, Date fecha, Date fecha_vencimiento) {
		super();
		this.empresa = empresa;
		this.paquete = paquete;
		this.fecha = fecha;
		this.fechaVencimiento = fecha_vencimiento;
	}

	//Getters y setters

	public DTEmpresa getEmpresa() {
		return empresa;
	}

	public DTPaquete getPaquete() {
		return paquete;
	}

	public Date getFecha() {
		return fecha;
	}
	public String getFechaFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fecha);
	}

	public Date getFechaVencimiento() {
		return fechaVencimiento;
	}

	public String getFechaVencimientoFormat() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(fechaVencimiento);
	}

	//Funciones

	public String getKey() {

		return this.paquete.getKey();
	}

	public String toString() {

		if (this.paquete != null) {

			return "Paquete: " + this.paquete.getNombre() + " - Fecha compra: " + this.getFechaFormat();

		} else {
			//Caso cargo DT vacío para combo
			return "";
		}

	}

}