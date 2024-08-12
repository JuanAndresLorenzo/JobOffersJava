package presentacion;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import cargar_datos.cargarDatosInicio;
import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;

import javax.swing.JMenu;

import logica.Fabrica;
import logica.interfaces.IControladorOfertaLaboral;
import logica.interfaces.IControladorTipoPublicacion;
import logica.interfaces.IControladorUsuario;
import presentacion.frames_casos_de_uso.keyword.AltaDeKeywrods;
import presentacion.frames_casos_de_uso.oferta_laboral.AceptarORechazarOfertaLaboral;
import presentacion.frames_casos_de_uso.oferta_laboral.AltadeOfertaLaboral;
import presentacion.frames_casos_de_uso.oferta_laboral.ConsultaOfertaLaboral;
import presentacion.frames_casos_de_uso.oferta_laboral.PostulacionaOfertaLaboral;
import presentacion.frames_casos_de_uso.tipo_publicacion.AgregarTipodePublicaciondeOfertaLaboralaPaquete;
import presentacion.frames_casos_de_uso.tipo_publicacion.AltadeTipodePublicaciondeOfertaLaboral;
import presentacion.frames_casos_de_uso.tipo_publicacion.ConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral;
import presentacion.frames_casos_de_uso.tipo_publicacion.CrearPaquetedeTiposdePublicaciondeOfertasLaborales;
import presentacion.frames_casos_de_uso.usuario.AltaUsuarioEmpresa;
import presentacion.frames_casos_de_uso.usuario.AltaUsuarioPostulante;
import presentacion.frames_casos_de_uso.usuario.ConsultaEmpresa;
import presentacion.frames_casos_de_uso.usuario.ConsultaPostulante;
import presentacion.frames_casos_de_uso.usuario.ModificarDatosDeEmpresa;
import presentacion.frames_casos_de_uso.usuario.ModificarDatosDePostulante;

public class Principal {

	private JFrame frmPrograma;
	private IControladorUsuario ictrlUsuario;
	private IControladorOfertaLaboral ictrlOfertaLaboral;
	private IControladorTipoPublicacion ictrlTipoPubli;

	// Usuario
	private AltaUsuarioEmpresa altaDeUsuarioEmpresaInternalFrame;
	private AltaUsuarioPostulante altaUsuarioPostulanteInternalFrame;

	private ConsultaEmpresa consultadeEmpresaInternalFrame;

	private ModificarDatosDePostulante modificarDatosDePostulanteInternalFrame;
	private ModificarDatosDeEmpresa modificarDatosDeEmpresaInternalFrame;

	// Oferta laboral
	private AltadeOfertaLaboral altadeOfertaLaboralInternalFrame;
	private ConsultaOfertaLaboral consultaOfertaLaboralInternalFrame;
	private PostulacionaOfertaLaboral postulacionaOfertaLaboralInternalFrame;
	private AceptarORechazarOfertaLaboral aceptarORechazarOfertaLaboralInternalFrame;

	// Tipo de publicación
	private AltadeTipodePublicaciondeOfertaLaboral altadeTipodePublicaciondeOfertaLaboralInternalFrame;
	private CrearPaquetedeTiposdePublicaciondeOfertasLaborales crearPaquetedeTiposdePublicaciondeOfertasLaboralesInternalFrame;
	private AgregarTipodePublicaciondeOfertaLaboralaPaquete agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame;
	private ConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame;

	// Keyword
	private AltaDeKeywrods altaDeKeywrodsInternalFrame;
	private ConsultaPostulante consultadePostulanteInternalFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Principal window = new Principal();
				window.frmPrograma.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();

		Fabrica fabrica = Fabrica.getInstance();
		ictrlUsuario = fabrica.getIControladorUsuario();
		ictrlOfertaLaboral = fabrica.getIControladorOfertaLaboral();
		ictrlTipoPubli = fabrica.getIControladorTipoPublicacion();

		// Usuarios

		altaDeUsuarioEmpresaInternalFrame = new AltaUsuarioEmpresa(ictrlUsuario);
		altaDeUsuarioEmpresaInternalFrame.setLocation(0, 0);
		altaDeUsuarioEmpresaInternalFrame.setVisible(false);

		altaUsuarioPostulanteInternalFrame = new AltaUsuarioPostulante(ictrlUsuario);
		altaUsuarioPostulanteInternalFrame.setLocation(0, 0);
		altaUsuarioPostulanteInternalFrame.setVisible(false);

		consultadeEmpresaInternalFrame = new ConsultaEmpresa(ictrlUsuario);
		consultadeEmpresaInternalFrame.setLocation(100, 10);
		consultadeEmpresaInternalFrame.setVisible(false);

		consultadePostulanteInternalFrame = new ConsultaPostulante(ictrlUsuario);
		consultadePostulanteInternalFrame.setLocation(100, 10);
		consultadePostulanteInternalFrame.setVisible(false);

		modificarDatosDeEmpresaInternalFrame = new ModificarDatosDeEmpresa(ictrlUsuario);
		modificarDatosDeEmpresaInternalFrame.setLocation(100, 10);
		modificarDatosDeEmpresaInternalFrame.setVisible(false);

		modificarDatosDePostulanteInternalFrame = new ModificarDatosDePostulante(ictrlUsuario);
		modificarDatosDePostulanteInternalFrame.setLocation(100, 10);
		modificarDatosDePostulanteInternalFrame.setVisible(false);

		// Oferta Laboral
		altadeOfertaLaboralInternalFrame = new AltadeOfertaLaboral(ictrlOfertaLaboral);
		altadeOfertaLaboralInternalFrame.setLocation(100, 10);
		altadeOfertaLaboralInternalFrame.setVisible(false);

		consultaOfertaLaboralInternalFrame = new ConsultaOfertaLaboral(ictrlUsuario);
		consultaOfertaLaboralInternalFrame.setLocation(100, 10);
		consultaOfertaLaboralInternalFrame.setVisible(false);

		postulacionaOfertaLaboralInternalFrame = new PostulacionaOfertaLaboral(ictrlUsuario);
		postulacionaOfertaLaboralInternalFrame.setLocation(100, 10);
		postulacionaOfertaLaboralInternalFrame.setVisible(false);

		aceptarORechazarOfertaLaboralInternalFrame = new AceptarORechazarOfertaLaboral(ictrlUsuario);
		aceptarORechazarOfertaLaboralInternalFrame.setLocation(100, 10);
		aceptarORechazarOfertaLaboralInternalFrame.setVisible(false);

		// Fin Oferta laboral

		// Tipo publicación

		altadeTipodePublicaciondeOfertaLaboralInternalFrame = new AltadeTipodePublicaciondeOfertaLaboral(ictrlTipoPubli);
		altadeTipodePublicaciondeOfertaLaboralInternalFrame.setLocation(100, 10);
		altadeTipodePublicaciondeOfertaLaboralInternalFrame.setVisible(false);

		crearPaquetedeTiposdePublicaciondeOfertasLaboralesInternalFrame = new CrearPaquetedeTiposdePublicaciondeOfertasLaborales(
				ictrlTipoPubli);
		crearPaquetedeTiposdePublicaciondeOfertasLaboralesInternalFrame.setLocation(100, 10);
		crearPaquetedeTiposdePublicaciondeOfertasLaboralesInternalFrame.setVisible(false);

		agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame = new AgregarTipodePublicaciondeOfertaLaboralaPaquete(
				ictrlTipoPubli);
		agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame.setLocation(100, 10);
		agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame.setVisible(false);

		consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame = new ConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral(
				ictrlTipoPubli);
		consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame.setLocation(100, 10);
		consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame.setVisible(false);

		// Fin Tipo publicación

		// Keywords

		altaDeKeywrodsInternalFrame = new AltaDeKeywrods(ictrlOfertaLaboral);
		altaDeKeywrodsInternalFrame.setLocation(100, 10);
		altaDeKeywrodsInternalFrame.setVisible(false);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		frmPrograma.getContentPane().add(altaDeUsuarioEmpresaInternalFrame);
		frmPrograma.getContentPane().add(altaUsuarioPostulanteInternalFrame);
		frmPrograma.getContentPane().add(altaUsuarioPostulanteInternalFrame);
		frmPrograma.getContentPane().add(consultadeEmpresaInternalFrame);
		frmPrograma.getContentPane().add(consultadePostulanteInternalFrame);

		frmPrograma.getContentPane().add(altadeOfertaLaboralInternalFrame);
		frmPrograma.getContentPane().add(consultaOfertaLaboralInternalFrame);
		frmPrograma.getContentPane().add(postulacionaOfertaLaboralInternalFrame);
		frmPrograma.getContentPane().add(aceptarORechazarOfertaLaboralInternalFrame);

		frmPrograma.getContentPane().add(altadeTipodePublicaciondeOfertaLaboralInternalFrame);
		frmPrograma.getContentPane().add(crearPaquetedeTiposdePublicaciondeOfertasLaboralesInternalFrame);
		frmPrograma.getContentPane().add(agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame);
		frmPrograma.getContentPane().add(consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame);

		frmPrograma.getContentPane().add(altaDeKeywrodsInternalFrame);
		frmPrograma.getContentPane().add(modificarDatosDePostulanteInternalFrame);
		frmPrograma.getContentPane().add(modificarDatosDeEmpresaInternalFrame);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrograma = new JFrame();
		frmPrograma.setTitle("Tarea 1 gr_45");
		frmPrograma.setBounds(100, 100, 900, 600);
		frmPrograma.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrograma.getContentPane().setLayout(null);

		// LOS CASOS DE USO QUE TIENEN LISTADOS HAY QUE CARGAR LAS LISTAS ANTES DE
		// MOSTRAR EL CASO DE USO

		JMenuBar menuBar = new JMenuBar();
		frmPrograma.setJMenuBar(menuBar);

		JMenu mnUsuario = new JMenu("Usuario");
		menuBar.add(mnUsuario);

		JMenuItem mntmAltaDeEmpresa = new JMenuItem("Registrar Empresa");
		mntmAltaDeEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Muestro el InternalFrame para registrar un usuario
				altaDeUsuarioEmpresaInternalFrame.setVisible(true);
			}
		});
		mnUsuario.add(mntmAltaDeEmpresa);

		JMenuItem mntmAltaDePostulante = new JMenuItem("Registrar Postulante");
		mntmAltaDePostulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Muestro el InternalFrame para registrar un usuario
				altaUsuarioPostulanteInternalFrame.setVisible(true);
			}
		});
		mnUsuario.add(mntmAltaDePostulante);

		JMenuItem mntmConsultaEmpresa = new JMenuItem("Consulta Empresa");
		mntmConsultaEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				consultadeEmpresaInternalFrame.cargarComboEmpresa();
//hacer que cargue el primero o el elemento vacio al combobox 	  
				// me falta cargar el combo de las ofertas
				consultadeEmpresaInternalFrame.setVisible(true);
			}
		});
		mnUsuario.add(mntmConsultaEmpresa);

		JMenuItem mntmConsultaPostulante = new JMenuItem("Consulta Postulante");
		mntmConsultaPostulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				consultadePostulanteInternalFrame.cargarComboPostulante();
//hacer que cargue el primero o el elemento vacio al combobox 	            	
				consultadePostulanteInternalFrame.setVisible(true);
			}
		});
		mnUsuario.add(mntmConsultaPostulante);

		JMenuItem mntmModificarDatosDeEmpresa = new JMenuItem("Modificar Datos de Empresa");
		mntmModificarDatosDeEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				modificarDatosDeEmpresaInternalFrame.cargarComboEmpresa();
				modificarDatosDeEmpresaInternalFrame.setVisible(true);
			}
		});
		mnUsuario.add(mntmModificarDatosDeEmpresa);

		JMenuItem mntmModificarDatosDePostulante = new JMenuItem("Modificar Datos de Postulante");
		mntmModificarDatosDePostulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				modificarDatosDePostulanteInternalFrame.cargarComboPostulante();
				modificarDatosDePostulanteInternalFrame.setVisible(true);
			}
		});
		mnUsuario.add(mntmModificarDatosDePostulante);

		JMenu mnOfertaLaboral = new JMenu("Oferta Laboral");
		menuBar.add(mnOfertaLaboral);

		JMenuItem mntmAltaDeOferta = new JMenuItem("Alta de Oferta Laboral");
		mntmAltaDeOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altadeOfertaLaboralInternalFrame.cargarListKeyword();
				altadeOfertaLaboralInternalFrame.cargarComboTipoPublicacion();
				altadeOfertaLaboralInternalFrame.cargarComboEmpresa();
				altadeOfertaLaboralInternalFrame.setVisible(true);
			}
		});

		mnOfertaLaboral.add(mntmAltaDeOferta);

		JMenuItem mntmConsultaDeOferta = new JMenuItem("Consulta de Oferta Laboral");
		mntmConsultaDeOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				consultaOfertaLaboralInternalFrame.cargarComboEmpresa();
				consultaOfertaLaboralInternalFrame.setVisible(true);
			}
		});
		mnOfertaLaboral.add(mntmConsultaDeOferta);

		JMenuItem mntmPostulacinAOferta = new JMenuItem("Postulación a Oferta Laboral");
		mntmPostulacinAOferta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				postulacionaOfertaLaboralInternalFrame.cargarComboEmpresa();
				postulacionaOfertaLaboralInternalFrame.setVisible(true);
			}
		});
		mnOfertaLaboral.add(mntmPostulacinAOferta);

		JMenuItem mntmAceptarORechazarOfertaLaboral = new JMenuItem("Aceptar/RechazarOfertaLaboral");
		mntmAceptarORechazarOfertaLaboral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				aceptarORechazarOfertaLaboralInternalFrame.cargarComboEmpresa();
				aceptarORechazarOfertaLaboralInternalFrame.setVisible(true);
			}
		});
		mnOfertaLaboral.add(mntmAceptarORechazarOfertaLaboral);

		// Fin Gestión de oferta laboral

		// Gestión de tipo de publicación

		JMenu mnTipoDePublicacion = new JMenu("Tipo de Publicacion");
		menuBar.add(mnTipoDePublicacion);

		JMenuItem mntmCAltadeTipodePublicaciondeOfertaLaboral = new JMenuItem(
				"Alta de Tipo de publicación de Oferta Laboral");
		mntmCAltadeTipodePublicaciondeOfertaLaboral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altadeTipodePublicaciondeOfertaLaboralInternalFrame.setVisible(true);
			}
		});
		mnTipoDePublicacion.add(mntmCAltadeTipodePublicaciondeOfertaLaboral);

		JMenuItem mntmCrearPaquetedeTiposdePublicaciondeOfertasLaborales = new JMenuItem(
				"Crear Paquete de Tipos de publicación de Ofertas Laborales");
		mntmCrearPaquetedeTiposdePublicaciondeOfertasLaborales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				crearPaquetedeTiposdePublicaciondeOfertasLaboralesInternalFrame.setVisible(true);
			}
		});
		mnTipoDePublicacion.add(mntmCrearPaquetedeTiposdePublicaciondeOfertasLaborales);

		JMenuItem mntmAgregarTipodePublicaciondeOfertaLaboralaPaquete = new JMenuItem(
				"Agregar Tipo de publicación de Oferta Laboral a Paquete");
		mntmAgregarTipodePublicaciondeOfertaLaboralaPaquete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame.cargarComboBoxPaquetes();
				agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame.cargarComboBoxTiposPublicaciones();
				agregarTipodePublicaciondeOfertaLaboralaPaqueteInternalFrame.setVisible(true);
			}
		});
		mnTipoDePublicacion.add(mntmAgregarTipodePublicaciondeOfertaLaboralaPaquete);

		JMenuItem mntmConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral = new JMenuItem(
				"Consulta de Paquete de Tipos de publicación de Ofertas Laborales");
		mntmConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame.cargarComboBoxPaquetes();
				consultadePaquetedeTiposdePublicacionesdeOfertaLaboralInternalFrame.setVisible(true);
			}
		});
		mnTipoDePublicacion.add(mntmConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral);

		// Fin Gestión de tipo de publicación

		// Gestión de keywords

		JMenu mnKeywords = new JMenu("Keywords");
		menuBar.add(mnKeywords);

		JMenuItem mntmAltaDeKeywrods = new JMenuItem("Alta de Keywrods");
		mntmAltaDeKeywrods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				altaDeKeywrodsInternalFrame.setVisible(true);
			}
		});
		mnKeywords.add(mntmAltaDeKeywrods);

		JMenu mnCargardatos = new JMenu("CargarDatos");

		JMenuItem mntmPrecargarDatos = new JMenuItem("Precargar Datos");
		mntmPrecargarDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				try {
					cargarDatosInicio.cargarKeywords(ictrlOfertaLaboral);
					cargarDatosInicio.cargarPaquetes(ictrlTipoPubli);
					cargarDatosInicio.cargarTipoPublicacion(ictrlTipoPubli);
					cargarDatosInicio.cargarEmpresas(ictrlUsuario);
					cargarDatosInicio.cargarPostulantes(ictrlUsuario);
					cargarDatosInicio.cargarTipoPublicacionEnPaquete(ictrlTipoPubli);
					cargarDatosInicio.cargarOfertasLaborales(ictrlOfertaLaboral);
					cargarDatosInicio.cargarPostulaciones(ictrlUsuario);
				} catch (ObjetoRepetidoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ObjetoNoExisteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ObjetoNoRespetaFormatoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		menuBar.add(mnCargardatos);

		mnCargardatos.add(mntmPrecargarDatos);

	}
}