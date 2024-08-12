package logica.manejadores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.clases.Keyword;

public class ManejadorKeyword {
	private Map<String, Keyword> listado;
	private static ManejadorKeyword instancia = null;

	private ManejadorKeyword() {
		listado = new HashMap<String, Keyword>();
	}

	public static ManejadorKeyword getinstance() {
		if (instancia == null)
			instancia = new ManejadorKeyword();
		return instancia;
	}

	public void agregar(Keyword objeto) {
		String clave = objeto.getKey();
		listado.put(clave, objeto);
	}

	public void quitar(String clave) {
		listado.remove(clave);
	}

	public Keyword obtener(String clave) {
		return (Keyword) listado.get(clave);
	}

	public Keyword[] listar() {
		if (listado.isEmpty())
			return null;
		else {
			Collection<Keyword> objetos = listado.values();
			Object[] objects = objetos.toArray();
			Keyword[] objetos_resultado = new Keyword[objects.length];
			for (int i = 0; i < objects.length; i++) {
				objetos_resultado[i] = (Keyword) objects[i];
			}
			return objetos_resultado;
		}
	}

	public void vaciar() {
		listado.clear();
	}

}
