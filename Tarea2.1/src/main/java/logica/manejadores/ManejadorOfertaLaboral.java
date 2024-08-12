package logica.manejadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.clases.EnumEstadoOL;
import logica.clases.OfertaLaboral;

public class ManejadorOfertaLaboral {
	private Map<String, OfertaLaboral> listado;
	private static ManejadorOfertaLaboral instancia = null;

	private ManejadorOfertaLaboral() {
		listado = new HashMap<String, OfertaLaboral>();
	}

	public static ManejadorOfertaLaboral getinstance() {
		if (instancia == null)
			instancia = new ManejadorOfertaLaboral();
		return instancia;
	}

	public void agregar(OfertaLaboral objeto) {
		String clave = objeto.getNombre();
		listado.put(clave, objeto);
	}

	public void quitar(String clave) {
		listado.remove(clave);
	}

	public OfertaLaboral obtener(String clave) {
		return (OfertaLaboral) listado.get(clave);
	}

	public OfertaLaboral[] listar() {
		if (listado.isEmpty())
			return null;
		else {
			Collection<OfertaLaboral> objetos = listado.values();
			Object[] objects = objetos.toArray();
			OfertaLaboral[] objetos_resultado = new OfertaLaboral[objects.length];
			for (int i = 0; i < objects.length; i++) {
				objetos_resultado[i] = (OfertaLaboral) objects[i];
			}
			return objetos_resultado;
		}
	}

	public List<OfertaLaboral> listarIngresadas() {
		if (listado.isEmpty())
			return null;
		else {
			Collection<OfertaLaboral> objetos = listado.values();
			Object[] objects = objetos.toArray();

			List<OfertaLaboral> ofertas_resultado = new ArrayList<OfertaLaboral>();

			for (int i = 0; i < objects.length; i++) {
				if (((OfertaLaboral) objects[i]).getEstado() == EnumEstadoOL.INGRESADA) {
					ofertas_resultado.add((OfertaLaboral) objects[i]);
				}
			}
			if (ofertas_resultado.isEmpty()) {
				return null;
			} else {
				return ofertas_resultado;
			}
		}
	}

	public void vaciar() {
		listado.clear();
	}

}
