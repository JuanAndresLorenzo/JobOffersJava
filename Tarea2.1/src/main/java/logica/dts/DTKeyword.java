package logica.dts;

public class DTKeyword {

	private String nombre;
	
	//Constructores
	
	public DTKeyword(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	
	//Getters y setters
	
	public String getNombre() {
		return nombre;
	}
	
	//Funciones

	public String getKey() {
		return this.nombre;
	}

	public String toString() {
		return nombre;
	}

}
