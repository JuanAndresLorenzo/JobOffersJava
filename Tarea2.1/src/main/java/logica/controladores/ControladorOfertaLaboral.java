package logica.controladores;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logica.interfaces.IControladorOfertaLaboral;
import logica.manejadores.ManejadorKeyword;
import logica.manejadores.ManejadorOfertaLaboral;
import logica.manejadores.ManejadorPaquete;
import logica.manejadores.ManejadorTipoPublicacion;
import logica.manejadores.ManejadorUsuario;

import logica.clases.Empresa;
import logica.clases.EnumEstadoOL;
import logica.clases.Keyword;
import logica.clases.OfertaLaboral;
import logica.clases.Paquete;
import logica.clases.Postulante;
import logica.clases.PostulanteOfertaLaboral;
import logica.clases.TipoPublicacion;
import logica.dts.DTEmpresa;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.dts.DTTipoPublicacion;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;

public class ControladorOfertaLaboral implements IControladorOfertaLaboral {

	public ControladorOfertaLaboral() {

	}

	public DTKeyword[] listarKeywords() throws ObjetoNoExisteException {
		ManejadorKeyword m_k = ManejadorKeyword.getinstance();
		Keyword[] keywords = m_k.listar();

		if (keywords != null) {

			DTKeyword[] dts_keyword = new DTKeyword[keywords.length];

			Keyword keyword;

			for (int i = 0; i < keywords.length; i++) {
				keyword = keywords[i];
				dts_keyword[i] = keyword.getDT();
			}

			return dts_keyword;
		} else
			throw new ObjetoNoExisteException("No existen keywords registrados");
	}

	public DTOfertaLaboral[] listarOfertasLaborales() throws ObjetoNoExisteException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral[] ofertas_laborales = mol.listar();

		if (ofertas_laborales != null) {
			DTOfertaLaboral[] data_oferta_laboral = new DTOfertaLaboral[ofertas_laborales.length];
			OfertaLaboral oferta_laboral;

			// Para separar lógica de presentación, no se deben devolver los Usuario,
			// sino los DataUsuario
			for (int i = 0; i < ofertas_laborales.length; i++) {
				oferta_laboral = ofertas_laborales[i];
				/*
				 * data_oferta_laboral[i] = new DTOfertaLaboral(oferta_laboral.getNombre(),
				 * oferta_laboral.getDescripcion(), oferta_laboral.getCiudad(),
				 * oferta_laboral.getDepartamento(), oferta_laboral.getHorario(),
				 * oferta_laboral.getRemuneracion(), oferta_laboral.getFechaAlta());
				 */
				data_oferta_laboral[i] = oferta_laboral.getDT();

			}

			return data_oferta_laboral;
		} else
			throw new ObjetoNoExisteException("No existen ofertas laborales registradas");
	}

	public DTOfertaLaboral[] listarOfertasLaboralesIngresadas() throws ObjetoNoExisteException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		List<OfertaLaboral> ofertas_laborales = mol.listarIngresadas();

		if (ofertas_laborales != null) {
			DTOfertaLaboral[] data_oferta_laboral = new DTOfertaLaboral[ofertas_laborales.size()];
			OfertaLaboral oferta_laboral;

			// Para separar lógica de presentación, no se deben devolver los Usuario,
			// sino los DataUsuario
			for (int i = 0; i < ofertas_laborales.size(); i++) {
				oferta_laboral = ofertas_laborales.get(i);
				data_oferta_laboral[i] = oferta_laboral.getDT();

			}

			return data_oferta_laboral;
		} else
			throw new ObjetoNoExisteException("No existen ofertas laborales registradas");
	}
	
	public DTOfertaLaboral[] listarOfertasLaboralesVigentesConfirmadas() throws ObjetoNoExisteException {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral[] ofertas_laborales = mol.listar();
		
		if (ofertas_laborales != null) {
						
			List<OfertaLaboral> ofertas_laborales_vigentes_confirmadas = new ArrayList<OfertaLaboral>();        
            
            for (int i = 0; i < ofertas_laborales.length; i++) { 
            	
            	 OfertaLaboral oferta_laboral = (OfertaLaboral) ofertas_laborales[i];
            	 
            	if(oferta_laboral.estaVigente() && oferta_laboral.estaConfirmada()) {
            		ofertas_laborales_vigentes_confirmadas.add(oferta_laboral);
            	}
            	
            }
            
            DTOfertaLaboral[] dts_ofertas_laborales_vigentes_confirmadas = new DTOfertaLaboral[ofertas_laborales_vigentes_confirmadas.size()];
            
            for (int i = 0; i < ofertas_laborales_vigentes_confirmadas.size(); i++) { 
            	
           	 	OfertaLaboral oferta_laboral = (OfertaLaboral) ofertas_laborales_vigentes_confirmadas.get(i);
           	 	
           	 	dts_ofertas_laborales_vigentes_confirmadas[i] = oferta_laboral.getDT();
           	
           }
            
            return dts_ofertas_laborales_vigentes_confirmadas;
            
		} else {
			throw new ObjetoNoExisteException("No existen ofertas laborales vigentes confirmadas registradas");
		}
	}

	public Boolean existeOfertaLaboral(String oferta_laboral_nombre) {

		ManejadorOfertaLaboral m_tp = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = m_tp.obtener(oferta_laboral_nombre);

		if (oferta_laboral != null) {
			return true;
		}
		return false;

	}

	public DTOfertaLaboral getDataOfertaLaboral(String nombre) throws ObjetoNoExisteException {
		ManejadorOfertaLaboral mof = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = mof.obtener(nombre);
		if (oferta_laboral != null) {
			DTOfertaLaboral data_ofertaLaboral = null;
			if (oferta_laboral instanceof OfertaLaboral) {

				data_ofertaLaboral = oferta_laboral.getDT();

				/*
				 * data_ofertaLaboral = new DTOfertaLaboral(oferta_laboral.getNombre(),
				 * oferta_laboral.getDescripcion(), oferta_laboral.getCiudad(),
				 * oferta_laboral.getDepartamento(), oferta_laboral.getHorario(),
				 * oferta_laboral.getRemuneracion(), oferta_laboral.getFechaAlta());
				 */
			}
			return data_ofertaLaboral;
		} else {
			throw new ObjetoNoExisteException("La oferta laboral no existe en el sistema");
		}
	}

	
	public DTPostulanteOfertaLaboral getDataPostulacionOfertaLaboral(String oferta_nombre, String postulante_nickname) throws ObjetoNoExisteException{
		
		ManejadorOfertaLaboral m_of = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = m_of.obtener(oferta_nombre);

		if (oferta_laboral != null) {

			Map<String, PostulanteOfertaLaboral> postulaciones = oferta_laboral.getPostulaciones();

			if (postulaciones != null) {

				
				if(oferta_laboral.existePostulacion(postulante_nickname)) {
					PostulanteOfertaLaboral postulacion = oferta_laboral.obtenerPostulacion(postulante_nickname);
					DTPostulanteOfertaLaboral dt_postulacion = postulacion.getDT();
					return dt_postulacion;
				}else {
					
					throw new ObjetoNoExisteException(
							"La oferta laboral " + oferta_nombre + " no tiene postulacion de ese postulante");
				}		
				
			} else {
				throw new ObjetoNoExisteException(
						"La oferta laboral " + oferta_nombre + " no tiene postulacion de ese postulante");
			}
		} else {
			throw new ObjetoNoExisteException(
					"La oferta laboral " + oferta_nombre + " no existe");
		}
		
	}

	
	public DTPostulanteOfertaLaboral[] listarPostulacionesPorOfertaLaboral(String ofertalaboral_nombre) throws ObjetoNoExisteException {


		ManejadorOfertaLaboral m_of = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = m_of.obtener(ofertalaboral_nombre);

		if (oferta_laboral != null) {

			Map<String, PostulanteOfertaLaboral> postulaciones = oferta_laboral.getPostulaciones();

			if (postulaciones != null) {
				

				Collection<PostulanteOfertaLaboral> objetos = postulaciones.values();
				Object[] objects = objetos.toArray();
				DTPostulanteOfertaLaboral[] objetos_resultado = new DTPostulanteOfertaLaboral[objects.length];
				for (int i = 0; i < objects.length; i++) {
					PostulanteOfertaLaboral dt_postulacion = (PostulanteOfertaLaboral) objects[i];
					objetos_resultado[i] = dt_postulacion.getDT();

				}
				return objetos_resultado;
			} else {
				
				throw new ObjetoNoExisteException(
						"La oferta laboral " + ofertalaboral_nombre + " no tiene postulaciones asociadas");
			}
		} else {
			
			throw new ObjetoNoExisteException(
					"La oferta laboral " + ofertalaboral_nombre + " no tiene postulaciones asociadas");
		}

	}

	public DTPostulante[] listarPostulantesPorOfertaLaboral(String ofertalaboral_nombre)
			throws ObjetoNoExisteException {

		ManejadorOfertaLaboral m_of = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = m_of.obtener(ofertalaboral_nombre);

		if (oferta_laboral != null) {

			Map<String, PostulanteOfertaLaboral> postulaciones = oferta_laboral.getPostulaciones();

			if (postulaciones != null) {

				Collection<PostulanteOfertaLaboral> objetos = postulaciones.values();
				Object[] objects = objetos.toArray();
				DTPostulante[] objetos_resultado = new DTPostulante[objects.length];
				for (int i = 0; i < objects.length; i++) {

					PostulanteOfertaLaboral dt_postulacion = (PostulanteOfertaLaboral) objects[i];
					objetos_resultado[i] = dt_postulacion.getPostulante().getDT();

				}
				return objetos_resultado;
			} else {
				throw new ObjetoNoExisteException(
						"La oferta laboral " + ofertalaboral_nombre + " no tiene postulaciones asociadas");
			}
		} else {
			throw new ObjetoNoExisteException(
					"La oferta laboral " + ofertalaboral_nombre + " no tiene postulaciones asociadas");
		}

	}

	public DTKeyword[] listarKeywordsPorOfertaLaboral(String ofertalaboral_nombre) {

		ManejadorOfertaLaboral m_of = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = m_of.obtener(ofertalaboral_nombre);

		Map<String, Keyword> keywords = oferta_laboral.getKeyword();

		if (keywords != null) {

			Collection<Keyword> objetos = keywords.values();
			Object[] objects = objetos.toArray();
			DTKeyword[] objetos_resultado = new DTKeyword[objects.length];
			for (int i = 0; i < objects.length; i++) {

				Keyword keyword = (Keyword) objects[i];
				objetos_resultado[i] = keyword.getDT();

			}
			return objetos_resultado;

		} else {
			return null;
		}

	}

	public void registrarKeyword(String nombre_keyword) throws ObjetoRepetidoException {
		ManejadorKeyword manejadorKeyword = ManejadorKeyword.getinstance();
		Keyword keyword = manejadorKeyword.obtener(nombre_keyword);

		if (keyword == null) {
			Keyword newKeyword = new Keyword(nombre_keyword);
			manejadorKeyword.agregar(newKeyword);
		} else {
			throw new ObjetoRepetidoException("La keyword " + nombre_keyword + " ya esta registrada");
		}
	}

	public void registrarOfertaLaboral(DTOfertaLaboral data_oferta_laboral) throws ObjetoRepetidoException {
		
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		ManejadorKeyword manejadorKeyword = ManejadorKeyword.getinstance();
		ManejadorPaquete mpaquete = ManejadorPaquete.getinstance();
		OfertaLaboral ofertaLaboral = mol.obtener(data_oferta_laboral.getNombre());
		Paquete paqueteObjeto = mpaquete.obtener(data_oferta_laboral.getPaquete());
		EnumEstadoOL enumEstado = data_oferta_laboral.getEstado();

		if (ofertaLaboral == null) {
			ManejadorUsuario mu = ManejadorUsuario.getinstance();
			Empresa empresa = (Empresa) mu.obtenerEmpresa(data_oferta_laboral.getEmpresa().getNickname());

			ManejadorTipoPublicacion mtp = ManejadorTipoPublicacion.getinstance();
			TipoPublicacion tipo_publicacion = mtp.obtener(data_oferta_laboral.getTipoPublicacion().getNombre());
			OfertaLaboral ol = new OfertaLaboral(empresa, tipo_publicacion, data_oferta_laboral.getNombre(), data_oferta_laboral.getDescripcion(), data_oferta_laboral.getCiudad(), data_oferta_laboral.getDepartamento(), data_oferta_laboral.getHorario(), data_oferta_laboral.getRemuneracion(), enumEstado, paqueteObjeto, data_oferta_laboral.getImg(), data_oferta_laboral.getFechaAlta());
			
			List<String> keywords = data_oferta_laboral.getKeyList();
			
			if (keywords.size()>0 && keywords!= null) {
				Map<String, Keyword> keywordsMap = new HashMap <String, Keyword>();
				for (Integer i=0 ; i<keywords.size() ; i++) {
					Keyword ki = manejadorKeyword.obtener(keywords.get(i));
					keywordsMap.put(keywords.get(i), ki);
				}
				ol.setKeywords(keywordsMap);
			}

			mol.agregar(ol);
			empresa.agregarOfertaLaboral(ol);

		} else {
			throw new ObjetoRepetidoException(
					"La oferta laboral con nombre " + data_oferta_laboral.getNombre() + " ya esta registrada");
		}
	}
	
	public void modificarEstadoOL1(String oferta_nombre,EnumEstadoOL estado) {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = mol.obtener(oferta_nombre);
		oferta_laboral.setEstado(estado);
	}
	

	public void eliminarKeywords() {
		ManejadorKeyword manejadorKeyword = ManejadorKeyword.getinstance();
		manejadorKeyword.vaciar();
	}

	public void eliminarOfertasLaborales() {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		mol.vaciar();
	}

	public DTEmpresa[] listarEmpresas() throws ObjetoNoExisteException {
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		return controladorUsuario.listarEmpresas();
	}

	public DTTipoPublicacion[] listarTiposPublicaciones() throws ObjetoNoExisteException {
		ControladorTipoPublicacion ctp = new ControladorTipoPublicacion();
		return ctp.listarTiposPublicaciones();
	}

	public void modificarEstadoOL(String oferta_nombre, EnumEstadoOL estado) {
		ManejadorOfertaLaboral mol = ManejadorOfertaLaboral.getinstance();
		OfertaLaboral oferta_laboral = mol.obtener(oferta_nombre);
		oferta_laboral.setEstado(estado);
	}

}
