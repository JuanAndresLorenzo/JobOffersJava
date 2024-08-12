package presentacion.frames_casos_de_uso.oferta_laboral;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;

import logica.interfaces.IControladorUsuario;

import logica.dts.DTTipoPublicacion;
import logica.dts.DTEmpresa;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ConsultaOfertaLaboral extends JInternalFrame {

	private IControladorUsuario controlU;

	private JLabel lblEmpresa;

	private JComboBox<DTEmpresa> comboBoxEmpresa;

	private JComboBox<DTOfertaLaboral> comboBoxOfertasLaborales;
	private JLabel lblOfertasLaborales;
	private JLabel lblNombreOf;
	private JLabel lblDescripcionOf;
	private JLabel lblCiudad;
	private JLabel lblDepartamento;
	private JLabel lblHorario;
	private JLabel lblRemuneracion;
	private JLabel lblFechaAlta;
	private JTextField textFieldNombreOf;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textFieldHorario;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldFechaAlta;
	private JLabel lblPostulantes;

	private JTextField textFieldKeywords;
	private JLabel lblKeywords;

	private JComboBox<DTPostulanteOfertaLaboral> comboBoxPostulaciones;
	private JLabel lblVigente;
	private JTextField textFieldVigente;
	private JLabel lblFechaVencimiento;
	private JTextField textFieldFechaVencimiento;
	private JLabel lblFechaPostulacion;
	private JLabel lblCvReducido;
	private JLabel lblMotivacion;
	private JLabel lblAdjuntos;
	private JTextField textFieldFechaPostulacion;
	private JTextArea textFieldCvReducido;
	private JTextArea textFieldMotivacion;
	private JTextField textFieldAdjuntos;
	private JLabel lblTipoPublicacion;
	private JTextField textFieldTipoPublicacion;
	private JScrollPane scrollPaneDescripcion;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public ConsultaOfertaLaboral(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Oferta Laboral");
		setBounds(10, 40, 924, 729);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		gridBagLayout.columnWidths = new int[] { 44, 152, 114, 139, 133, 120, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };

		// gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 24, 24,
		// 24, 24, 24, 24, 0, 0, 0, 0,
		// 0, 0, 0, 0 };
		// gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		// 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		// 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);
		this.cargarComboEmpresa();

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblEmpresa.fill = GridBagConstraints.VERTICAL;
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 1;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);

		comboBoxEmpresa = new JComboBox<DTEmpresa>();
		comboBoxEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdConsultarEmpresaActionPerformed(event);
			}
		});

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 1;
		getContentPane().add(comboBoxEmpresa, gbc_comboBox);

		lblOfertasLaborales = new JLabel("Ofertas Laborales:");
		lblOfertasLaborales.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblOfertasLaborales = new GridBagConstraints();
		gbc_lblOfertasLaborales.anchor = GridBagConstraints.WEST;
		gbc_lblOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertasLaborales.gridx = 1;
		gbc_lblOfertasLaborales.gridy = 2;
		getContentPane().add(lblOfertasLaborales, gbc_lblOfertasLaborales);
		comboBoxOfertasLaborales = new JComboBox<DTOfertaLaboral>();

		comboBoxOfertasLaborales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdConsultarOfertaLaboralActionPerformed(event);
			}
		});

		GridBagConstraints gbc_comboBoxOfertasLaborales = new GridBagConstraints();
		gbc_comboBoxOfertasLaborales.gridwidth = 3;
		gbc_comboBoxOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOfertasLaborales.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOfertasLaborales.gridx = 2;
		gbc_comboBoxOfertasLaborales.gridy = 2;
		getContentPane().add(comboBoxOfertasLaborales, gbc_comboBoxOfertasLaborales);

		lblTipoPublicacion = new JLabel("Tipo Publicacion:");
		lblTipoPublicacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTipoPublicacion = new GridBagConstraints();
		gbc_lblTipoPublicacion.anchor = GridBagConstraints.WEST;
		gbc_lblTipoPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoPublicacion.gridx = 1;
		gbc_lblTipoPublicacion.gridy = 3;
		getContentPane().add(lblTipoPublicacion, gbc_lblTipoPublicacion);

		textFieldTipoPublicacion = new JTextField();
		textFieldTipoPublicacion.setEditable(false);
		textFieldTipoPublicacion.setColumns(10);
		GridBagConstraints gbc_textFieldTipoPublicacion = new GridBagConstraints();
		gbc_textFieldTipoPublicacion.gridwidth = 3;
		gbc_textFieldTipoPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTipoPublicacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTipoPublicacion.gridx = 2;
		gbc_textFieldTipoPublicacion.gridy = 3;
		getContentPane().add(textFieldTipoPublicacion, gbc_textFieldTipoPublicacion);

		lblNombreOf = new JLabel("Nombre:");
		lblNombreOf.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombreOf = new GridBagConstraints();
		gbc_lblNombreOf.anchor = GridBagConstraints.WEST;
		gbc_lblNombreOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreOf.gridx = 1;
		gbc_lblNombreOf.gridy = 4;
		getContentPane().add(lblNombreOf, gbc_lblNombreOf);

		textFieldNombreOf = new JTextField();
		textFieldNombreOf.setEditable(false);
		textFieldNombreOf.setColumns(10);
		GridBagConstraints gbc_textFieldNombreOf = new GridBagConstraints();
		gbc_textFieldNombreOf.gridwidth = 3;
		gbc_textFieldNombreOf.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreOf.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOf.gridx = 2;
		gbc_textFieldNombreOf.gridy = 4;
		getContentPane().add(textFieldNombreOf, gbc_textFieldNombreOf);

		lblDescripcionOf = new JLabel("Descripcion:");
		lblDescripcionOf.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcionOf = new GridBagConstraints();
		gbc_lblDescripcionOf.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcionOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionOf.gridx = 1;
		gbc_lblDescripcionOf.gridy = 5;
		getContentPane().add(lblDescripcionOf, gbc_lblDescripcionOf);

		scrollPaneDescripcion = new JScrollPane();
		scrollPaneDescripcion.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneDescripcion = new GridBagConstraints();
		gbc_scrollPaneDescripcion.gridwidth = 3;
		gbc_scrollPaneDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneDescripcion.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDescripcion.gridx = 2;
		gbc_scrollPaneDescripcion.gridy = 5;
		getContentPane().add(scrollPaneDescripcion, gbc_scrollPaneDescripcion);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setEditable(false);
		scrollPaneDescripcion.setViewportView(textAreaDescripcion);

		lblKeywords = new JLabel("Keywords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeywords.anchor = GridBagConstraints.WEST;
		gbc_lblKeywords.gridx = 1;
		gbc_lblKeywords.gridy = 6;
		getContentPane().add(lblKeywords, gbc_lblKeywords);

		textFieldKeywords = new JTextField();
		textFieldKeywords.setEditable(false);
		GridBagConstraints gbc_textFieldKeywords = new GridBagConstraints();
		gbc_textFieldKeywords.gridwidth = 3;
		gbc_textFieldKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldKeywords.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldKeywords.gridx = 2;
		gbc_textFieldKeywords.gridy = 6;
		getContentPane().add(textFieldKeywords, gbc_textFieldKeywords);
		textFieldKeywords.setColumns(10);

		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.WEST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 7;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.gridwidth = 3;
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 7;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);

		lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.WEST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 8;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		textFieldDepartamento = new JTextField();
		textFieldDepartamento.setEditable(false);
		textFieldDepartamento.setColumns(10);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.gridwidth = 3;
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 2;
		gbc_textFieldDepartamento.gridy = 8;
		getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);

		lblHorario = new JLabel("Horario:");
		lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.WEST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 1;
		gbc_lblHorario.gridy = 9;
		getContentPane().add(lblHorario, gbc_lblHorario);

		textFieldHorario = new JTextField();
		textFieldHorario.setEditable(false);
		textFieldHorario.setColumns(10);
		GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
		gbc_textFieldHorario.gridwidth = 3;
		gbc_textFieldHorario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorario.gridx = 2;
		gbc_textFieldHorario.gridy = 9;
		getContentPane().add(textFieldHorario, gbc_textFieldHorario);

		lblRemuneracion = new JLabel("Remuneracion:");
		lblRemuneracion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.WEST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 1;
		gbc_lblRemuneracion.gridy = 10;
		getContentPane().add(lblRemuneracion, gbc_lblRemuneracion);

		textFieldRemuneracion = new JTextField();
		textFieldRemuneracion.setEditable(false);
		textFieldRemuneracion.setColumns(10);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.gridwidth = 3;
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 10;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);

		lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 11;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 3;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAlta.gridx = 2;
		gbc_textFieldFechaAlta.gridy = 11;
		getContentPane().add(textFieldFechaAlta, gbc_textFieldFechaAlta);

		lblFechaVencimiento = new JLabel("Fecha vencimiento:");
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaVencimiento = new GridBagConstraints();
		gbc_lblFechaVencimiento.anchor = GridBagConstraints.WEST;
		gbc_lblFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaVencimiento.gridx = 1;
		gbc_lblFechaVencimiento.gridy = 12;
		getContentPane().add(lblFechaVencimiento, gbc_lblFechaVencimiento);

		textFieldFechaVencimiento = new JTextField();
		textFieldFechaVencimiento.setEditable(false);
		textFieldFechaVencimiento.setColumns(10);
		GridBagConstraints gbc_textFieldFechaVencimiento = new GridBagConstraints();
		gbc_textFieldFechaVencimiento.gridwidth = 3;
		gbc_textFieldFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaVencimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaVencimiento.gridx = 2;
		gbc_textFieldFechaVencimiento.gridy = 12;
		getContentPane().add(textFieldFechaVencimiento, gbc_textFieldFechaVencimiento);

		lblVigente = new JLabel("Vigente:");
		lblVigente.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblVigente = new GridBagConstraints();
		gbc_lblVigente.anchor = GridBagConstraints.WEST;
		gbc_lblVigente.insets = new Insets(0, 0, 5, 5);
		gbc_lblVigente.gridx = 1;
		gbc_lblVigente.gridy = 13;
		getContentPane().add(lblVigente, gbc_lblVigente);

		textFieldVigente = new JTextField();
		textFieldVigente.setEditable(false);
		textFieldVigente.setColumns(10);
		GridBagConstraints gbc_textFieldVigente = new GridBagConstraints();
		gbc_textFieldVigente.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVigente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVigente.gridx = 2;
		gbc_textFieldVigente.gridy = 13;
		getContentPane().add(textFieldVigente, gbc_textFieldVigente);

		comboBoxPostulaciones = new JComboBox<DTPostulanteOfertaLaboral>();
		comboBoxPostulaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdConsultarOLPostulacionActionPerformed(event);
			}
		});

		// Postulaciones

		lblPostulantes = new JLabel("Postulantes:");
		lblPostulantes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPostulantes = new GridBagConstraints();
		gbc_lblPostulantes.anchor = GridBagConstraints.WEST;
		gbc_lblPostulantes.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostulantes.gridx = 1;
		gbc_lblPostulantes.gridy = 15;

		getContentPane().add(lblPostulantes, gbc_lblPostulantes);
		GridBagConstraints gbc_comboBoxPostulaciones = new GridBagConstraints();
		gbc_comboBoxPostulaciones.gridwidth = 3;
		gbc_comboBoxPostulaciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPostulaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPostulaciones.gridx = 2;
		gbc_comboBoxPostulaciones.gridy = 15;
		getContentPane().add(comboBoxPostulaciones, gbc_comboBoxPostulaciones);

		lblFechaPostulacion = new JLabel("Fecha postulación");
		lblFechaPostulacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaPostulacion = new GridBagConstraints();
		gbc_lblFechaPostulacion.anchor = GridBagConstraints.WEST;
		gbc_lblFechaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaPostulacion.gridx = 1;
		gbc_lblFechaPostulacion.gridy = 17;
		getContentPane().add(lblFechaPostulacion, gbc_lblFechaPostulacion);

		textFieldFechaPostulacion = new JTextField();
		textFieldFechaPostulacion.setEditable(false);
		textFieldFechaPostulacion.setColumns(10);
		GridBagConstraints gbc_textFieldFechaPostulacion = new GridBagConstraints();
		gbc_textFieldFechaPostulacion.gridwidth = 3;
		gbc_textFieldFechaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaPostulacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaPostulacion.gridx = 2;
		gbc_textFieldFechaPostulacion.gridy = 17;
		getContentPane().add(textFieldFechaPostulacion, gbc_textFieldFechaPostulacion);

		lblCvReducido = new JLabel("Cv reducido:");
		lblCvReducido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCvReducido = new GridBagConstraints();
		gbc_lblCvReducido.anchor = GridBagConstraints.WEST;
		gbc_lblCvReducido.insets = new Insets(0, 0, 5, 5);
		gbc_lblCvReducido.gridx = 1;
		gbc_lblCvReducido.gridy = 18;
		getContentPane().add(lblCvReducido, gbc_lblCvReducido);

		textFieldCvReducido = new JTextArea();
		textFieldCvReducido.setEditable(false);
		textFieldCvReducido.setLineWrap(true);
		textFieldCvReducido.setColumns(10);
		GridBagConstraints gbc_textFieldCvReducido = new GridBagConstraints();
		gbc_textFieldCvReducido.gridwidth = 3;
		gbc_textFieldCvReducido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCvReducido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCvReducido.gridx = 2;
		gbc_textFieldCvReducido.gridy = 18;
		getContentPane().add(textFieldCvReducido, gbc_textFieldCvReducido);

		lblMotivacion = new JLabel("Motivación:");
		lblMotivacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMotivacion = new GridBagConstraints();
		gbc_lblMotivacion.anchor = GridBagConstraints.WEST;
		gbc_lblMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivacion.gridx = 1;
		gbc_lblMotivacion.gridy = 19;
		getContentPane().add(lblMotivacion, gbc_lblMotivacion);

		textFieldMotivacion = new JTextArea();
		textFieldMotivacion.setLineWrap(true);
		textFieldMotivacion.setEditable(false);
		textFieldMotivacion.setColumns(10);
		GridBagConstraints gbc_textFieldMotivacion = new GridBagConstraints();
		gbc_textFieldMotivacion.gridwidth = 3;
		gbc_textFieldMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMotivacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMotivacion.gridx = 2;
		gbc_textFieldMotivacion.gridy = 19;
		getContentPane().add(textFieldMotivacion, gbc_textFieldMotivacion);

		lblAdjuntos = new JLabel("Adjuntos:");
		lblAdjuntos.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAdjuntos = new GridBagConstraints();
		gbc_lblAdjuntos.anchor = GridBagConstraints.WEST;
		gbc_lblAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdjuntos.gridx = 1;
		gbc_lblAdjuntos.gridy = 20;
		getContentPane().add(lblAdjuntos, gbc_lblAdjuntos);

		textFieldAdjuntos = new JTextField();
		textFieldAdjuntos.setEditable(false);
		textFieldAdjuntos.setColumns(10);
		GridBagConstraints gbc_textFieldAdjuntos = new GridBagConstraints();
		gbc_textFieldAdjuntos.gridwidth = 3;
		gbc_textFieldAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAdjuntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdjuntos.gridx = 2;
		gbc_textFieldAdjuntos.gridy = 20;
		getContentPane().add(textFieldAdjuntos, gbc_textFieldAdjuntos);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 22;

		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

	private void limpiarFormulario() {
		this.comboBoxEmpresa.removeAllItems();

		textFieldKeywords.setText("");
		limpiarFormularioEmpresa();
		limpiarFormularioOL();
	}

	private void limpiarFormularioEmpresa() {
		// this.comboBoxEmpresa.removeAllItems();
		this.comboBoxOfertasLaborales.removeAllItems();
		textFieldKeywords.setText("");
		limpiarFormularioOL();

	}

	private void limpiarFormularioOL() {

		// this.comboBoxEmpresa.removeAllItems();
		// this.comboBoxOfertasLaborales.removeAllItems();

		textFieldTipoPublicacion.setText("");
		textFieldNombreOf.setText("");
		textAreaDescripcion.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		textFieldFechaAlta.setText("");

		textFieldKeywords.setText("");

		textFieldFechaVencimiento.setText("");
		textFieldVigente.setText("");

		this.comboBoxPostulaciones.removeAllItems();

		limpiarFormularioOLPostulacion();
	}

	private void limpiarFormularioOLPostulacion() {

		// this.comboBoxEmpresa.removeAllItems();
		// this.comboBoxOfertasLaborales.removeAllItems();
		// this.comboBoxPostulaciones.removeAllItems();

		textFieldFechaPostulacion.setText("");
		textFieldMotivacion.setText("");
		textFieldCvReducido.setText("");
		textFieldAdjuntos.setText("");

	}

	// En base a la empresa seleccionada, carga el combo de ofertas laborales de esa
	// empresa
	protected void cmdConsultarEmpresaActionPerformed(ActionEvent arg0) {

		limpiarFormularioEmpresa();

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String empresa_nombre = this.comboBoxEmpresa.getSelectedItem().toString();

			if (!empresa_nombre.isEmpty()) {

				this.cargarComboOfertas(empresa_nombre);

			}
			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
		}
	}

	// En base a la oferta laboral seleccionada, carga los datos de esa oferta y el
	// combo de postulaciones de esa oferta
	protected void cmdConsultarOfertaLaboralActionPerformed(ActionEvent event) {

		limpiarFormularioOL();

		if (checkFormularioOL()) {

			// Obtengo datos de los controles Swing
			String oferta_nombre = this.comboBoxOfertasLaborales.getSelectedItem().toString();

			this.cargarDatosOfertaSeleccionado(oferta_nombre);
			this.cargarComboPostulante(oferta_nombre);

			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
		}
	}

	protected void cmdConsultarOLPostulacionActionPerformed(ActionEvent arg0) {

		limpiarFormularioOLPostulacion();

		if (checkFormularioOLPostulacion()) {

			// Obtengo datos de los controles Swing
			DTPostulanteOfertaLaboral dt_postulacion = (DTPostulanteOfertaLaboral) this.comboBoxPostulaciones
					.getSelectedItem();

			if (dt_postulacion != null) {

				this.cargarDatosPostulacionSeleccionada(dt_postulacion);

			}
			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		if (this.comboBoxEmpresa.getSelectedIndex() == -1) {
			return false;
		}

		String empresa_nombre = this.comboBoxEmpresa.getSelectedItem().toString();

		if (empresa_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa a consultar", "Consultar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean checkFormularioOL() {

		if (this.comboBoxOfertasLaborales.getSelectedIndex() == -1) {
			return false;
		}

		String oferta_nombre = this.comboBoxOfertasLaborales.getSelectedItem().toString();

		if (oferta_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral a consultar",
					"Consultar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean checkFormularioOLPostulacion() {

		if (this.comboBoxPostulaciones.getSelectedIndex() == -1) {
			return false;
		}

		String postulacion_string = this.comboBoxPostulaciones.getSelectedItem().toString();

		if (postulacion_string.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una postulación a consultar",
					"Consultar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void cargarComboEmpresa() {
		DefaultComboBoxModel<DTEmpresa> modelEmpresa;
		try {
			modelEmpresa = new DefaultComboBoxModel<DTEmpresa>(controlU.listarEmpresas());

			this.comboBoxEmpresa.setModel(modelEmpresa);

			this.comboBoxEmpresa.insertItemAt(new DTEmpresa("", "", "", "", "", "", EnumRol.EMPRESA, "", "", ""), 0);
			this.comboBoxEmpresa.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarComboOfertas(String empresa_nickname) {
		DefaultComboBoxModel<DTOfertaLaboral> modelOL;
		try {
			modelOL = new DefaultComboBoxModel<DTOfertaLaboral>(
					controlU.listarOfertasLaboralesPorEmpresa(empresa_nickname));

			this.comboBoxOfertasLaborales.setModel(modelOL);

			this.comboBoxOfertasLaborales.insertItemAt(new DTOfertaLaboral("", "", "", "", "", (float) 0, new Date(), new Date(), closable, null, null), 0);
			this.comboBoxOfertasLaborales.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	// cambiar el listar postulantes por obtener posutlantes de ofertalaboral
	public void cargarComboPostulante(String oferta_laboral_nombre) {
		DefaultComboBoxModel<DTPostulanteOfertaLaboral> modelPostulantes;

		try {

			DTPostulanteOfertaLaboral[] postulaciones = controlU
					.listarPostulacionesPorOfertaLaboral(oferta_laboral_nombre);

			modelPostulantes = new DefaultComboBoxModel<DTPostulanteOfertaLaboral>(postulaciones);

			this.comboBoxPostulaciones.setModel(modelPostulantes);

			this.comboBoxPostulaciones.insertItemAt(new DTPostulanteOfertaLaboral(null, "", "", ""), 0);
			this.comboBoxPostulaciones.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarDatosOfertaSeleccionado(String oferta_nombre) {

		try {

			DTOfertaLaboral dt_ofertaLaboral = controlU.getDataOfertaLaboral(oferta_nombre);
			DTTipoPublicacion dt_tipo_publicacion = dt_ofertaLaboral.getTipoPublicacion();

			DTKeyword[] Arreglokeywords = controlU.listarKeywordsPorOfertaLaboral(oferta_nombre);

			String keywords = "";

			if (Arreglokeywords != null) {
				int cont;
				String[] key = new String[Arreglokeywords.length];

				for (cont = 0; cont < Arreglokeywords.length; cont++) {
					key[cont] = Arreglokeywords[cont].getNombre();
				}

				keywords = String.join(",", key);
				System.out.println(keywords);

			}

			textFieldTipoPublicacion.setText(dt_tipo_publicacion.getNombre());

			textFieldNombreOf.setText(dt_ofertaLaboral.getNombre());
			textAreaDescripcion.setText(dt_ofertaLaboral.getDescripcion());
			textFieldKeywords.setText(keywords);
			textFieldCiudad.setText(dt_ofertaLaboral.getCiudad());
			textFieldDepartamento.setText(dt_ofertaLaboral.getDepartamento());
			textFieldHorario.setText(dt_ofertaLaboral.getHorario());
			textFieldRemuneracion.setText(dt_ofertaLaboral.getRemuneracion().toString());
			textFieldFechaAlta.setText(dt_ofertaLaboral.getFechaAltaFormat().toString());
			textFieldFechaVencimiento.setText(dt_ofertaLaboral.getFechaVencimientoFormat());
			textFieldVigente.setText(dt_ofertaLaboral.getVigenteFormat());

		} catch (ObjetoNoExisteException e) {
			// Muestro error de registro
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cargarDatosPostulacionSeleccionada(DTPostulanteOfertaLaboral dt_postulacion) {

		textFieldFechaPostulacion.setText(dt_postulacion.getFechaFormat());
		textFieldMotivacion.setText(dt_postulacion.getMotivacion());
		textFieldCvReducido.setText(dt_postulacion.getCvReducido());
		textFieldAdjuntos.setText(dt_postulacion.getAdjuntos());

	}

}
