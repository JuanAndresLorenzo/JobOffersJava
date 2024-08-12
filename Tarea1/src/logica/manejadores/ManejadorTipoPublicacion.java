package logica.manejadores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.clases.TipoPublicacion;

public class ManejadorTipoPublicacion {
    private Map<String, TipoPublicacion> listado;
    private static ManejadorTipoPublicacion instancia = null;

    private ManejadorTipoPublicacion() {
        listado = new HashMap<String, TipoPublicacion>();
    }

    public static ManejadorTipoPublicacion getinstance() {
        if (instancia == null)
            instancia = new ManejadorTipoPublicacion();
        return instancia;
    }

    public void agregar(TipoPublicacion objeto) {
        String clave = objeto.getNombre();
        listado.put(clave, objeto);
    }

    public void quitar(String clave) {
        listado.remove(clave);
    }
    
    public TipoPublicacion obtener(String clave) {
        return (TipoPublicacion) listado.get(clave);
    }

    public TipoPublicacion[] listar() {
        if (listado.isEmpty())
            return null;
        else {
            Collection<TipoPublicacion> objetos = listado.values();
            Object[] objects = objetos.toArray();
            TipoPublicacion[] objetos_resultado = new TipoPublicacion[objects.length];
            for (int i = 0; i < objects.length; i++) {
            	objetos_resultado[i] = (TipoPublicacion) objects[i];
            }
            return objetos_resultado;
        }
    }
    
    public void vaciar() {
    	listado.clear();
    }

}
