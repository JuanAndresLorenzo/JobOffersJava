package logica.manejadores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.clases.Empresa;
import logica.clases.Postulante;
import logica.clases.Usuario;

public class ManejadorUsuario {
	private Map<String, Usuario> listado;
	private static ManejadorUsuario instancia = null;

	private ManejadorUsuario() {
		listado = new HashMap<String, Usuario>();
	}

	public static ManejadorUsuario getinstance() {
		if (instancia == null)
			instancia = new ManejadorUsuario();
		return instancia;
	}

	public void agregar(Usuario objeto) {
		// String clave = objeto.getNickname();
		String clave = objeto.getKey();
		listado.put(clave, objeto);
	}

	public void quitar(String clave) {
		listado.remove(clave);
	}

	public Usuario obtener(String clave) {
		return (Usuario) listado.get(clave);
	}

	public Empresa obtenerEmpresa(String clave) {
		return (Empresa) listado.get(clave);
	}

	public Usuario obtenerUsuarioPorCorreo(String clave) {

		for (Map.Entry<String, Usuario> entry : listado.entrySet()) {
			if (entry.getValue().getCorreo().equals(clave)) {
				return obtener(entry.getKey());
			}
		}
		return null;
	}

	public Postulante obtenerPostulante(String clave) {
		return (Postulante) listado.get(clave);
	}

	// Lista tanto usuarios empresas como postulantes, mezclados
	public Usuario[] listar() {
		if (listado.isEmpty())
			return null;
		else {
			Collection<Usuario> objetos = listado.values();
			Object[] objects = objetos.toArray();
			Usuario[] objetos_resultado = new Usuario[objects.length];
			for (int i = 0; i < objects.length; i++) {
				objetos_resultado[i] = (Usuario) objects[i];
			}
			return objetos_resultado;
		}
	}

	// Recorrer el listar comùn y cargar al resultado solo los que son del tipo
	// correspondiente
	public List<Empresa> listarEmpresas() {
		if (listado.isEmpty())
			return null;
		else {
			Collection<Usuario> objetos = listado.values();
			Object[] objects = objetos.toArray();

			List<Empresa> empresas_resultado = new ArrayList<Empresa>();

			for (int i = 0; i < objects.length; i++) {
				if (objects[i] instanceof Empresa) {
					empresas_resultado.add((Empresa) objects[i]);
				}
			}
			if (empresas_resultado.isEmpty()) {
				return null;
			} else {
				return empresas_resultado;
			}
		}
	}

	// Recorrer el listar comùn y cargar al resultado solo los que son del tipo
	// correspondiente
	public List<Postulante> listarPostulantes() {
		if (listado.isEmpty()) {
			return null;
		} else {
			Collection<Usuario> objetos = listado.values();
			Object[] objetosPos = objetos.toArray();

			List<Postulante> postulantes_resultado = new ArrayList<Postulante>();

			for (int i = 0; i < objetosPos.length; i++) {
				if (objetosPos[i] instanceof Postulante) {
					postulantes_resultado.add((Postulante) objetosPos[i]);
				}
			}

			if (postulantes_resultado.isEmpty()) {
				return null;
			} else {
				return postulantes_resultado;
			}
		}
	}

	public void vaciar() {
		listado.clear();
	}

}
