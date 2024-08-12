package logica.clases;

import logica.dts.DTEmpresa;
import logica.dts.DTPaquete;
import logica.dts.DTEmpresaPaquete;
import java.util.Calendar;
import java.util.Date;

public class EmpresaPaquete {
	private Date fecha;
	private Empresa empresa;
	private Paquete paquete;
	private Float costo;

	//Constructores

	public EmpresaPaquete() {
		super();
	}

	public EmpresaPaquete(Empresa empresa, Paquete paquete, Date fecha, Float costo) {
		this.empresa = empresa;
		this.paquete = paquete;
		this.fecha = fecha;
		this.costo = costo;

	}

	//Getters y setters

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	
	public void setCosto(Float costo) {
		this.costo = costo;
	}

	public Float getCosto() {
		return costo;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Paquete getPaquete() {
		return paquete;
	}

	public void setPaquete(Paquete paquete) {
		this.paquete = paquete;
	}

	//Funciones

	public String getKey() {
		return this.paquete.getKey();
	}

	//TODO: Para calcular, en base a la fecha de compra y la validéz del paquete.
	public Date getFechaVencimiento() {

		Date paquete_fecha_compra = this.fecha;
		Integer paquete_validez_dias = this.paquete.getValidezDias();	
		
		//GregorianCalendar cal = new GregorianCalendar();
        Calendar cal = Calendar.getInstance();
		cal.setTime(paquete_fecha_compra);
		cal.add(Calendar.DATE, paquete_validez_dias);
		
		Date paquete_fecha_vencimiento = cal.getTime();
		
		return paquete_fecha_vencimiento;
		
	}

	public DTEmpresaPaquete getDT() {

		DTEmpresa dt_empresa = this.empresa.getDT();
		DTPaquete dt_paquete = this.paquete.getDT();

		return new DTEmpresaPaquete(dt_empresa, dt_paquete, this.fecha, this.getFechaVencimiento());
	}
}