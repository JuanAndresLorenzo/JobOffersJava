package presentacion.frames_casos_de_uso.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import logica.interfaces.IControladorUsuario;

import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class ConsultaPostulante extends JInternalFrame {

	private IControladorUsuario controlU;

	private JLabel lblPostulante;

	private JComboBox<DTPostulante> comboBoxPostulante;

	private JLabel lblNombre;
	private JLabel lblNacionalidad;
	private JTextField txtNombre;
	private JTextField textfieldNacionalidad;
	private JLabel lblApellido;
	private JTextField textFieldApellido;
	private JLabel lblCorreo;
	private JTextField textFieldCorreo;
	private JLabel lblFechaNacimiento;
	private JTextField textFieldFechadeNacimiento;

	//////////////////////////////////////////
	private JComboBox<DTPostulanteOfertaLaboral> comboBoxPostulacionesOfertasLaborales;
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

	private JLabel lblFechaAltaPostulacion;
	private JTextField textFieldFechadePostulacion;
	private JLabel lblCvReducido;
	private JTextArea textFieldCvReducido;
	private JLabel lblMotivacion;
	private JTextArea textFieldMotivacion;
	private JLabel lblAdjuntos;
	private JTextField textFieldAdjuntos;
	private JLabel lblEmpresa;
	private JTextField textFieldEmpresa;

	private EnumRol rol = EnumRol.POSTULANTE;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcionOf;

	// Create the frame
	public ConsultaPostulante(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Postulante");
		setBounds(10, 40, 712, 722);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0 };
		gridBagLayout.columnWidths = new int[] { 44, 152, 114, 139, 133, 120, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };

		/*
		 * gridBagLayout.rowHeights = new int[] { 30, 30, 30, 0, 0, 24, 24, 24, 24, 24,
		 * 24, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; gridBagLayout.rowWeights
		 * = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		 * 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		 * Double.MIN_VALUE };
		 */
		getContentPane().setLayout(gridBagLayout);

		lblPostulante = new JLabel("Postulante:");
		lblPostulante.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPostulante = new GridBagConstraints();
		gbc_lblPostulante.anchor = GridBagConstraints.WEST;
		gbc_lblPostulante.fill = GridBagConstraints.VERTICAL;
		gbc_lblPostulante.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostulante.gridx = 1;
		gbc_lblPostulante.gridy = 2;
		getContentPane().add(lblPostulante, gbc_lblPostulante);

		comboBoxPostulante = new JComboBox<DTPostulante>();
		this.cargarComboPostulante();
		comboBoxPostulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				cmdConsultarPostulanteActionPerformed(event);

			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBoxPostulante, gbc_comboBox);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 4;
		getContentPane().add(lblNombre, gbc_lblNombre);

		txtNombre = new JTextField();
		txtNombre.setEditable(false);
		GridBagConstraints gbc_txtNombre = new GridBagConstraints();
		gbc_txtNombre.gridwidth = 3;
		gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
		gbc_txtNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtNombre.gridx = 2;
		gbc_txtNombre.gridy = 4;
		getContentPane().add(txtNombre, gbc_txtNombre);
		txtNombre.setColumns(10);

		lblApellido = new JLabel("Apellido:");
		lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 5;
		getContentPane().add(lblApellido, gbc_lblApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setEditable(false);
		textFieldApellido.setColumns(10);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 3;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 2;
		gbc_textFieldApellido.gridy = 5;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);

		lblCorreo = new JLabel("Correo:");
		lblCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.anchor = GridBagConstraints.WEST;
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 1;
		gbc_lblCorreo.gridy = 6;
		getContentPane().add(lblCorreo, gbc_lblCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setEditable(false);
		textFieldCorreo.setColumns(10);
		GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
		gbc_textFieldCorreo.gridwidth = 3;
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridx = 2;
		gbc_textFieldCorreo.gridy = 6;
		getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);

		lblFechaNacimiento = new JLabel("Fecha de nacimiento:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaNacimiento = new GridBagConstraints();
		gbc_lblFechaNacimiento.anchor = GridBagConstraints.WEST;
		gbc_lblFechaNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimiento.gridx = 1;
		gbc_lblFechaNacimiento.gridy = 7;
		getContentPane().add(lblFechaNacimiento, gbc_lblFechaNacimiento);

		textFieldFechadeNacimiento = new JTextField();
		textFieldFechadeNacimiento.setEditable(false);
		textFieldFechadeNacimiento.setColumns(10);
		GridBagConstraints gbc_textFieldFechadeNacimiento = new GridBagConstraints();
		gbc_textFieldFechadeNacimiento.gridwidth = 3;
		gbc_textFieldFechadeNacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechadeNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechadeNacimiento.gridx = 2;
		gbc_textFieldFechadeNacimiento.gridy = 7;
		getContentPane().add(textFieldFechadeNacimiento, gbc_textFieldFechadeNacimiento);

		lblNacionalidad = new JLabel("Nacionalidad:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.anchor = GridBagConstraints.WEST;
		gbc_lblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblNacionalidad.gridx = 1;
		gbc_lblNacionalidad.gridy = 8;
		getContentPane().add(lblNacionalidad, gbc_lblNacionalidad);

		textfieldNacionalidad = new JTextField();
		textfieldNacionalidad.setEditable(false);
		textfieldNacionalidad.setColumns(10);
		GridBagConstraints gbc_textfieldNacionalidad = new GridBagConstraints();
		gbc_textfieldNacionalidad.gridwidth = 3;
		gbc_textfieldNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_textfieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textfieldNacionalidad.gridx = 2;
		gbc_textfieldNacionalidad.gridy = 8;
		getContentPane().add(textfieldNacionalidad, gbc_textfieldNacionalidad);

		comboBoxPostulacionesOfertasLaborales = new JComboBox<DTPostulanteOfertaLaboral>();
		comboBoxPostulacionesOfertasLaborales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				if (comboBoxPostulacionesOfertasLaborales.getSelectedIndex() != -1) {
					cmdConsultarOfertaLaboralActionPerformed(event);
				}

			}
		});

		lblOfertasLaborales = new JLabel("Ofertas Laborales:");
		lblOfertasLaborales.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblOfertasLaborales = new GridBagConstraints();
		gbc_lblOfertasLaborales.anchor = GridBagConstraints.WEST;
		gbc_lblOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertasLaborales.gridx = 1;
		gbc_lblOfertasLaborales.gridy = 12;
		getContentPane().add(lblOfertasLaborales, gbc_lblOfertasLaborales);
		GridBagConstraints gbc_comboBoxPostulacionesOfertasLaborales = new GridBagConstraints();
		gbc_comboBoxPostulacionesOfertasLaborales.gridwidth = 3;
		gbc_comboBoxPostulacionesOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPostulacionesOfertasLaborales.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPostulacionesOfertasLaborales.gridx = 2;
		gbc_comboBoxPostulacionesOfertasLaborales.gridy = 12;
		getContentPane().add(comboBoxPostulacionesOfertasLaborales, gbc_comboBoxPostulacionesOfertasLaborales);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 13;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);

		textFieldEmpresa = new JTextField();
		textFieldEmpresa.setEditable(false);
		textFieldEmpresa.setColumns(10);
		GridBagConstraints gbc_textFieldEmpresa = new GridBagConstraints();
		gbc_textFieldEmpresa.gridwidth = 3;
		gbc_textFieldEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmpresa.gridx = 2;
		gbc_textFieldEmpresa.gridy = 13;
		getContentPane().add(textFieldEmpresa, gbc_textFieldEmpresa);

		lblNombreOf = new JLabel("Nombre:");
		lblNombreOf.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombreOf = new GridBagConstraints();
		gbc_lblNombreOf.anchor = GridBagConstraints.WEST;
		gbc_lblNombreOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreOf.gridx = 1;
		gbc_lblNombreOf.gridy = 14;
		getContentPane().add(lblNombreOf, gbc_lblNombreOf);

		textFieldNombreOf = new JTextField();
		textFieldNombreOf.setEditable(false);
		textFieldNombreOf.setColumns(10);
		GridBagConstraints gbc_textFieldNombreOf = new GridBagConstraints();
		gbc_textFieldNombreOf.gridwidth = 3;
		gbc_textFieldNombreOf.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreOf.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOf.gridx = 2;
		gbc_textFieldNombreOf.gridy = 14;
		getContentPane().add(textFieldNombreOf, gbc_textFieldNombreOf);

		lblDescripcionOf = new JLabel("Descripcion:");
		lblDescripcionOf.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcionOf = new GridBagConstraints();
		gbc_lblDescripcionOf.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcionOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionOf.gridx = 1;
		gbc_lblDescripcionOf.gridy = 15;
		getContentPane().add(lblDescripcionOf, gbc_lblDescripcionOf);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 15;
		getContentPane().add(scrollPane, gbc_scrollPane);

		textAreaDescripcionOf = new JTextArea();
		textAreaDescripcionOf.setBorder(null);
		textAreaDescripcionOf.setBackground(UIManager.getColor("FormattedTextField.inactiveBackground"));
		textAreaDescripcionOf.setLineWrap(true);
		textAreaDescripcionOf.setEditable(false);
		scrollPane.setViewportView(textAreaDescripcionOf);

		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.WEST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 16;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.gridwidth = 3;
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 16;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);

		lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.WEST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 17;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		textFieldDepartamento = new JTextField();
		textFieldDepartamento.setEditable(false);
		textFieldDepartamento.setColumns(10);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.gridwidth = 3;
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 2;
		gbc_textFieldDepartamento.gridy = 17;
		getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);

		lblHorario = new JLabel("Horario:");
		lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.WEST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 1;
		gbc_lblHorario.gridy = 18;
		getContentPane().add(lblHorario, gbc_lblHorario);

		textFieldHorario = new JTextField();
		textFieldHorario.setEditable(false);
		textFieldHorario.setColumns(10);
		GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
		gbc_textFieldHorario.gridwidth = 3;
		gbc_textFieldHorario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorario.gridx = 2;
		gbc_textFieldHorario.gridy = 18;
		getContentPane().add(textFieldHorario, gbc_textFieldHorario);

		lblRemuneracion = new JLabel("Remuneracion:");
		lblRemuneracion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.WEST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 1;
		gbc_lblRemuneracion.gridy = 19;
		getContentPane().add(lblRemuneracion, gbc_lblRemuneracion);

		textFieldRemuneracion = new JTextField();
		textFieldRemuneracion.setEditable(false);
		textFieldRemuneracion.setColumns(10);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.gridwidth = 3;
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 19;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);

		lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 20;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 3;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAlta.gridx = 2;
		gbc_textFieldFechaAlta.gridy = 20;
		getContentPane().add(textFieldFechaAlta, gbc_textFieldFechaAlta);

		// Un botón (JButton) con un evento asociado que permite cerrar el formulario
		// (solo ocultarlo).
		// Dado que antes de cerrar se limpia el formulario, se invoca un método
		// reutilizable para ello.
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		lblFechaAltaPostulacion = new JLabel("Fecha alta de postulacion:");
		lblFechaAltaPostulacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAltaPostulacion = new GridBagConstraints();
		gbc_lblFechaAltaPostulacion.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAltaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAltaPostulacion.gridx = 1;
		gbc_lblFechaAltaPostulacion.gridy = 21;
		getContentPane().add(lblFechaAltaPostulacion, gbc_lblFechaAltaPostulacion);

		textFieldFechadePostulacion = new JTextField();
		textFieldFechadePostulacion.setEditable(false);
		textFieldFechadePostulacion.setColumns(10);
		GridBagConstraints gbc_textFieldFechadePostulacion = new GridBagConstraints();
		gbc_textFieldFechadePostulacion.gridwidth = 3;
		gbc_textFieldFechadePostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechadePostulacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechadePostulacion.gridx = 2;
		gbc_textFieldFechadePostulacion.gridy = 21;
		getContentPane().add(textFieldFechadePostulacion, gbc_textFieldFechadePostulacion);

		lblCvReducido = new JLabel("Cv_reducido:");
		lblCvReducido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCv_reducido = new GridBagConstraints();
		gbc_lblCv_reducido.anchor = GridBagConstraints.WEST;
		gbc_lblCv_reducido.insets = new Insets(0, 0, 5, 5);
		gbc_lblCv_reducido.gridx = 1;
		gbc_lblCv_reducido.gridy = 22;
		getContentPane().add(lblCvReducido, gbc_lblCv_reducido);

		textFieldCvReducido = new JTextArea();
		textFieldCvReducido.setLineWrap(true);
		textFieldCvReducido.setEditable(false);
		textFieldCvReducido.setColumns(10);
		GridBagConstraints gbc_textFieldCv_reducido = new GridBagConstraints();
		gbc_textFieldCv_reducido.gridwidth = 3;
		gbc_textFieldCv_reducido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCv_reducido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCv_reducido.gridx = 2;
		gbc_textFieldCv_reducido.gridy = 22;
		getContentPane().add(textFieldCvReducido, gbc_textFieldCv_reducido);

		lblMotivacion = new JLabel("Motivacion:");
		lblMotivacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMotivacion = new GridBagConstraints();
		gbc_lblMotivacion.anchor = GridBagConstraints.WEST;
		gbc_lblMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivacion.gridx = 1;
		gbc_lblMotivacion.gridy = 23;
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
		gbc_textFieldMotivacion.gridy = 23;
		getContentPane().add(textFieldMotivacion, gbc_textFieldMotivacion);

		lblAdjuntos = new JLabel("Adjuntos:");
		lblAdjuntos.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAdjuntos = new GridBagConstraints();
		gbc_lblAdjuntos.anchor = GridBagConstraints.WEST;
		gbc_lblAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdjuntos.gridx = 1;
		gbc_lblAdjuntos.gridy = 24;
		getContentPane().add(lblAdjuntos, gbc_lblAdjuntos);

		textFieldAdjuntos = new JTextField();
		textFieldAdjuntos.setEditable(false);
		textFieldAdjuntos.setColumns(10);
		GridBagConstraints gbc_textFieldAdjuntos = new GridBagConstraints();
		gbc_textFieldAdjuntos.gridwidth = 3;
		gbc_textFieldAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAdjuntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdjuntos.gridx = 2;
		gbc_textFieldAdjuntos.gridy = 24;
		getContentPane().add(textFieldAdjuntos, gbc_textFieldAdjuntos);

		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCerrar.fill = GridBagConstraints.BOTH;
		gbc_btnCerrar.gridx = 4;
		gbc_btnCerrar.gridy = 26;
		getContentPane().add(btnCerrar, gbc_btnCerrar);

	}

	private void limpiarFormulario() {

		this.comboBoxPostulante.removeAllItems();
		limpiarFormularioPostulante();
		limpiarFormularioOL();

	}

	private void limpiarFormularioPostulante() {

		// this.comboBoxPostulante.removeAllItems();
		this.comboBoxPostulacionesOfertasLaborales.removeAllItems();

		txtNombre.setText("");
		textFieldApellido.setText("");
		textFieldCorreo.setText("");

		textFieldFechadeNacimiento.setText("");
		textfieldNacionalidad.setText("");

		limpiarFormularioOL();

	}

	private void limpiarFormularioOL() {

		// this.comboBoxPostulante.removeAllItems();
		// this.comboBoxPostulacionesOfertasLaborales.removeAllItems();

		/*
		 * txtNombre.setText(""); textFieldApellido.setText("");
		 * textFieldCorreo.setText("");
		 * 
		 * textFieldFechadeNacimiento.setText(""); textfieldNacionalidad.setText("");
		 */

		textFieldEmpresa.setText("");
		textFieldNombreOf.setText("");
		textAreaDescripcionOf.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		textFieldFechaAlta.setText("");

		textFieldFechadePostulacion.setText("");
		textFieldCvReducido.setText("");
		textFieldMotivacion.setText("");
		textFieldAdjuntos.setText("");

	}

	// Operación lanzada por evento sobre comboPostulantes
	protected void cmdConsultarPostulanteActionPerformed(ActionEvent arg0) {

		limpiarFormularioPostulante();

		if (checkFormulario()) {

			String postulante_nombre = this.comboBoxPostulante.getSelectedItem().toString();

			if (!postulante_nombre.isEmpty()) {
				System.out.println("POSTULANTE NOMBRE CARGO DATOS CMD: " + postulante_nombre);

				// Obtengo datos de los controles Swing
				this.cargarDatosPostulanteSeleccionado(postulante_nombre);
				this.cargarComboPostulacionesOfertas(postulante_nombre);

			}
			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
		}
	}

	// Operación lanzada por evento sobre comboOfertasLaborales
	protected void cmdConsultarOfertaLaboralActionPerformed(ActionEvent event) {

		limpiarFormularioOL();

		if (checkFormularioOL()) {

			// Obtengo datos de los controles Swing
			// String oferta_nombre =
			// this.comboBoxPostulacionesOfertasLaborales.getSelectedItem().toString();
			DTPostulanteOfertaLaboral dt_postulacion = (DTPostulanteOfertaLaboral) this.comboBoxPostulacionesOfertasLaborales
					.getSelectedItem();

			this.cargarDatosOfertaSeleccionado(dt_postulacion);

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

		if (this.comboBoxPostulante.getSelectedIndex() == -1) {
			// JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral a
			// consultar",
			// "Consultar Postulante", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String postulante_nombre = this.comboBoxPostulante.getSelectedItem().toString();

		if (postulante_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un postulante a consultar", "Consultar Postulante",
					JOptionPane.ERROR_MESSAGE);
			// this.limpiarFormulario();
			// this.cargarComboPostulante();
			return false;
		}

		return true;
	}

	private boolean checkFormularioOL() {

		if (this.comboBoxPostulacionesOfertasLaborales.getSelectedIndex() == -1) {
			// JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral a
			// consultar",
			// "Consultar Postulante", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String ofertalaboral_nombre = this.comboBoxPostulacionesOfertasLaborales.getSelectedItem().toString();

		if (ofertalaboral_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral a consultar",
					"Consultar Postulantes", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Método que permite cargar un nuevo modelo para el combo con la información
	// actualizada de usuarios, provista por la operación del sistema getUsuarios().
	// Se invoca el método antes de hacer visible el JInternalFrame
	public void cargarComboPostulante() {
		DefaultComboBoxModel<DTPostulante> modelPostulantes;
		try {

			modelPostulantes = new DefaultComboBoxModel<DTPostulante>(controlU.listarPostulantes());
			this.comboBoxPostulante.setModel(modelPostulantes);

			this.comboBoxPostulante.insertItemAt(new DTPostulante("", "", "", "", "", "", this.rol, null, ""), 0);
			this.comboBoxPostulante.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarComboPostulacionesOfertas(String postulante_nickname) {

		DefaultComboBoxModel<DTPostulanteOfertaLaboral> modelPOL;

		System.out.println("CARGAR COMBO OL POSTULANTE NOMBRE: " + postulante_nickname);

		try {

			modelPOL = new DefaultComboBoxModel<DTPostulanteOfertaLaboral>(
					controlU.listarPostulacionesPorPostulante(postulante_nickname));

			this.comboBoxPostulacionesOfertasLaborales.setModel(modelPOL);

			this.comboBoxPostulacionesOfertasLaborales
					.insertItemAt(new DTPostulanteOfertaLaboral(null, "", "", "", null, null), 0);
			this.comboBoxPostulacionesOfertasLaborales.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarDatosOfertaSeleccionado(DTPostulanteOfertaLaboral dt_postulacion) {

		DTOfertaLaboral dt_ofertaLaboral = dt_postulacion.getOfertaLaboral();

		DTEmpresa dt_empresa = dt_ofertaLaboral.getEmpresa();

		textFieldEmpresa.setText(dt_empresa.getNickname() + "-" + dt_empresa.getNombreEmpresa());
		textFieldNombreOf.setText(dt_ofertaLaboral.getNombre());
		textAreaDescripcionOf.setText(dt_ofertaLaboral.getDescripcion());
		textFieldCiudad.setText(dt_ofertaLaboral.getCiudad());
		textFieldDepartamento.setText(dt_ofertaLaboral.getDepartamento());
		textFieldHorario.setText(dt_ofertaLaboral.getHorario());
		textFieldRemuneracion.setText(dt_ofertaLaboral.getRemuneracion().toString());
		textFieldFechaAlta.setText(dt_ofertaLaboral.getFechaAltaFormat());

		textFieldFechadePostulacion.setText(dt_postulacion.getFechaFormat().toString());
		textFieldCvReducido.setText(dt_postulacion.getCvReducido());
		textFieldMotivacion.setText(dt_postulacion.getMotivacion());
		textFieldAdjuntos.setText(dt_postulacion.getAdjuntos());

	}

	public void cargarDatosPostulanteSeleccionado(String postulante_nombre) {

		try {

			DTPostulante dt_postulante = (DTPostulante) controlU.getDataUsuario(postulante_nombre);

			txtNombre.setText(dt_postulante.getNombre());
			textFieldApellido.setText(dt_postulante.getApellido());

			textFieldApellido.setText(dt_postulante.getImagen());

			textFieldCorreo.setText(dt_postulante.getCorreo());

			textFieldFechadeNacimiento.setText(dt_postulante.getFechaNacimientoFormat());
			textfieldNacionalidad.setText(dt_postulante.getNacionalidad());

			// ACA SEGURAMENTE TAMBIEN LO DE LAS OFERTAS LABORALES

		} catch (ObjetoNoExisteException e) {
			// Muestro error de registro
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Postulante", JOptionPane.ERROR_MESSAGE);
		}

	}

}
