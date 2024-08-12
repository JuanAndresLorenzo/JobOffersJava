package logica.manejadores;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import logica.clases.Paquete;

public class ManejadorPaquete {
    private Map<String, Paquete> listado;
    private static ManejadorPaquete instancia = null;

    private ManejadorPaquete() {
        listado = new HashMap<String, Paquete>();
    }

    public static ManejadorPaquete getinstance() {
        if (instancia == null)
            instancia = new ManejadorPaquete();
        return instancia;
    }

    public void agregar(Paquete objeto) {
        String clave = objeto.getKey();
        listado.put(clave, objeto);
    }

    public void quitar(String clave) {
        listado.remove(clave);
    }
    
    public Paquete obtener(String clave) {
        return (Paquete) listado.get(clave);
    }

    public Paquete[] listar() {
        if (listado.isEmpty())
            return null;
        else {
            Collection<Paquete> objetos = listado.values();
            Object[] objects = objetos.toArray();
            Paquete[] objetos_resultado = new Paquete[objects.length];
            for (int i = 0; i < objects.length; i++) {
            	objetos_resultado[i] = (Paquete) objects[i];
            }
            return objetos_resultado;
        }
    }
    
    public void vaciar() {
    	listado.clear();
    }


}
