package logica;

import logica.interfaces.IControladorUsuario;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorOfertaLaboral;
import logica.controladores.ControladorUsuario;
import logica.controladores.ControladorTipoPublicacion;
import logica.controladores.ControladorOfertaLaboral;


public class Fabrica {

    private static Fabrica instancia;

    private Fabrica() {
    };

    public static Fabrica getInstance() {
        if (instancia == null) {
            instancia = new Fabrica();
        }
        return instancia;
    }

    public IControladorUsuario getIControladorUsuario() {
        return new ControladorUsuario();
    }
    
    public IControladorTipoPublicacion getIControladorTipoPublicacion() {
        return new ControladorTipoPublicacion();
    }
    
    public IControladorOfertaLaboral getIControladorOfertaLaboral() {
        return new ControladorOfertaLaboral();
    }

}
