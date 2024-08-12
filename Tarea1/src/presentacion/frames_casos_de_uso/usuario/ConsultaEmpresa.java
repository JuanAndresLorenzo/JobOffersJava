package presentacion.frames_casos_de_uso.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import logica.interfaces.IControladorUsuario;

import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTEmpresa;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;

@SuppressWarnings("serial")
public class ConsultaEmpresa extends JInternalFrame {

	private IControladorUsuario controlU;

	private JLabel lblEmpresa;

	private JComboBox<DTEmpresa> comboBoxEmpresa;

	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblLink;
	private JTextField txtNombre;
	private JTextField textFieldDescripcion;
	private JTextField textFieldLink;
	private JLabel lblApellido;
	private JTextField textFieldApellido;
	private JLabel lblCorreo;
	private JTextField textFieldCorreo;
	private JLabel lblNombreEmpresa;
	private JTextField textFieldEmpresaNombre;

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
	private JTextField textFieldDescripcionOf;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textFieldHorario;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldFechaAlta;

	private JLabel lblPostulantes;
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
	private JTextField textFieldCvReducido;
	private JTextField textFieldMotivacion;
	private JTextField textFieldAdjuntos;

	private EnumRol rol = EnumRol.EMPRESA;

	// Create the frame
	public ConsultaEmpresa(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consulta Empresa");
		setBounds(10, 40, 642, 854);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 44, 152, 114, 0, 139, 133, 120, 0 };

		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };

		/*
		 * gridBagLayout.rowHeights = new int[] { 30, 30, 30, 0, 0, 24, 24, 24, 24, 24,
		 * 24, 24, 24, 24, 24, 24, 0, 0, 0, 0, 0, 0 }; gridBagLayout.rowWeights = new
		 * double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		 * 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		 */
		getContentPane().setLayout(gridBagLayout);

		lblEmpresa = new JLabel("Empresa:");
		lblEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblEmpresa.fill = GridBagConstraints.VERTICAL;
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 2;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);

		comboBoxEmpresa = new JComboBox<DTEmpresa>();
		this.cargarComboEmpresa();
		comboBoxEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {

				cmdConsultarEmpresaActionPerformed(event);
			}
		});
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 4;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBoxEmpresa, gbc_comboBox);

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
		gbc_txtNombre.gridwidth = 4;
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
		gbc_textFieldApellido.gridwidth = 4;
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
		gbc_textFieldCorreo.gridwidth = 4;
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridx = 2;
		gbc_textFieldCorreo.gridy = 6;
		getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);

		lblNombreEmpresa = new JLabel("Nombre de la empresa:");
		lblNombreEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombreEmpresa = new GridBagConstraints();
		gbc_lblNombreEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblNombreEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreEmpresa.gridx = 1;
		gbc_lblNombreEmpresa.gridy = 7;
		getContentPane().add(lblNombreEmpresa, gbc_lblNombreEmpresa);

		textFieldEmpresaNombre = new JTextField();
		textFieldEmpresaNombre.setEditable(false);
		textFieldEmpresaNombre.setColumns(10);
		GridBagConstraints gbc_textFieldEmpresaNombre = new GridBagConstraints();
		gbc_textFieldEmpresaNombre.gridwidth = 4;
		gbc_textFieldEmpresaNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldEmpresaNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldEmpresaNombre.gridx = 2;
		gbc_textFieldEmpresaNombre.gridy = 7;
		getContentPane().add(textFieldEmpresaNombre, gbc_textFieldEmpresaNombre);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 8;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textFieldDescripcion = new JTextField();
		textFieldDescripcion.setEditable(false);
		textFieldDescripcion.setColumns(10);
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.gridwidth = 4;
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcion.gridx = 2;
		gbc_textFieldDescripcion.gridy = 8;
		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);

		lblLink = new JLabel("Sitio web :");
		lblLink.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblLink = new GridBagConstraints();
		gbc_lblLink.anchor = GridBagConstraints.WEST;
		gbc_lblLink.insets = new Insets(0, 0, 5, 5);
		gbc_lblLink.gridx = 1;
		gbc_lblLink.gridy = 9;
		getContentPane().add(lblLink, gbc_lblLink);

		textFieldLink = new JTextField();
		textFieldLink.setEditable(false);
		textFieldLink.setColumns(10);
		GridBagConstraints gbc_textFieldLink = new GridBagConstraints();
		gbc_textFieldLink.gridwidth = 4;
		gbc_textFieldLink.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLink.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLink.gridx = 2;
		gbc_textFieldLink.gridy = 9;
		getContentPane().add(textFieldLink, gbc_textFieldLink);

		lblOfertasLaborales = new JLabel("Ofertas Laborales:");
		lblOfertasLaborales.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblOfertasLaborales = new GridBagConstraints();
		gbc_lblOfertasLaborales.anchor = GridBagConstraints.WEST;
		gbc_lblOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertasLaborales.gridx = 1;
		gbc_lblOfertasLaborales.gridy = 11;
		getContentPane().add(lblOfertasLaborales, gbc_lblOfertasLaborales);

		comboBoxOfertasLaborales = new JComboBox<DTOfertaLaboral>();
		comboBoxOfertasLaborales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdConsultarOfertaLaboralActionPerformed(event);
			}
		});
		GridBagConstraints gbc_comboBoxOfertasLaborales = new GridBagConstraints();
		gbc_comboBoxOfertasLaborales.gridwidth = 4;
		gbc_comboBoxOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOfertasLaborales.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOfertasLaborales.gridx = 2;
		gbc_comboBoxOfertasLaborales.gridy = 11;
		getContentPane().add(comboBoxOfertasLaborales, gbc_comboBoxOfertasLaborales);

		lblNombreOf = new JLabel("Nombre:");
		lblNombreOf.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombreOf = new GridBagConstraints();
		gbc_lblNombreOf.anchor = GridBagConstraints.WEST;
		gbc_lblNombreOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreOf.gridx = 1;
		gbc_lblNombreOf.gridy = 12;
		getContentPane().add(lblNombreOf, gbc_lblNombreOf);

		textFieldNombreOf = new JTextField();
		textFieldNombreOf.setEditable(false);
		textFieldNombreOf.setColumns(10);
		GridBagConstraints gbc_textFieldNombreOf = new GridBagConstraints();
		gbc_textFieldNombreOf.gridwidth = 4;
		gbc_textFieldNombreOf.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreOf.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreOf.gridx = 2;
		gbc_textFieldNombreOf.gridy = 12;
		getContentPane().add(textFieldNombreOf, gbc_textFieldNombreOf);

		lblDescripcionOf = new JLabel("Descripcion:");
		lblDescripcionOf.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcionOf = new GridBagConstraints();
		gbc_lblDescripcionOf.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcionOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionOf.gridx = 1;
		gbc_lblDescripcionOf.gridy = 13;
		getContentPane().add(lblDescripcionOf, gbc_lblDescripcionOf);

		textFieldDescripcionOf = new JTextField();
		textFieldDescripcionOf.setEditable(false);
		textFieldDescripcionOf.setColumns(10);
		GridBagConstraints gbc_textFieldDescripcionOf = new GridBagConstraints();
		gbc_textFieldDescripcionOf.gridwidth = 4;
		gbc_textFieldDescripcionOf.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcionOf.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcionOf.gridx = 2;
		gbc_textFieldDescripcionOf.gridy = 13;
		getContentPane().add(textFieldDescripcionOf, gbc_textFieldDescripcionOf);

		lblCiudad = new JLabel("Ciudad:");
		lblCiudad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.WEST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 14;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		textFieldCiudad = new JTextField();
		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.gridwidth = 4;
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 14;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);

		lblDepartamento = new JLabel("Departamento:");
		lblDepartamento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.WEST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 15;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		textFieldDepartamento = new JTextField();
		textFieldDepartamento.setEditable(false);
		textFieldDepartamento.setColumns(10);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.gridwidth = 4;
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 2;
		gbc_textFieldDepartamento.gridy = 15;
		getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);

		lblHorario = new JLabel("Horario:");
		lblHorario.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.WEST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 1;
		gbc_lblHorario.gridy = 16;
		getContentPane().add(lblHorario, gbc_lblHorario);

		textFieldHorario = new JTextField();
		textFieldHorario.setEditable(false);
		textFieldHorario.setColumns(10);
		GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
		gbc_textFieldHorario.gridwidth = 4;
		gbc_textFieldHorario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorario.gridx = 2;
		gbc_textFieldHorario.gridy = 16;
		getContentPane().add(textFieldHorario, gbc_textFieldHorario);

		lblRemuneracion = new JLabel("Remuneracion:");
		lblRemuneracion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.WEST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 1;
		gbc_lblRemuneracion.gridy = 17;
		getContentPane().add(lblRemuneracion, gbc_lblRemuneracion);

		textFieldRemuneracion = new JTextField();
		textFieldRemuneracion.setEditable(false);
		textFieldRemuneracion.setColumns(10);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.gridwidth = 4;
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 17;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);

		lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 18;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 4;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAlta.gridx = 2;
		gbc_textFieldFechaAlta.gridy = 18;
		getContentPane().add(textFieldFechaAlta, gbc_textFieldFechaAlta);

		lblFechaVencimiento = new JLabel("Fecha vencimiento:");
		lblFechaVencimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaVencimiento = new GridBagConstraints();
		gbc_lblFechaVencimiento.anchor = GridBagConstraints.WEST;
		gbc_lblFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaVencimiento.gridx = 1;
		gbc_lblFechaVencimiento.gridy = 19;
		getContentPane().add(lblFechaVencimiento, gbc_lblFechaVencimiento);

		textFieldFechaVencimiento = new JTextField();
		textFieldFechaVencimiento.setEditable(false);
		textFieldFechaVencimiento.setColumns(10);
		GridBagConstraints gbc_textFieldFechaVencimiento = new GridBagConstraints();
		gbc_textFieldFechaVencimiento.gridwidth = 4;
		gbc_textFieldFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaVencimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaVencimiento.gridx = 2;
		gbc_textFieldFechaVencimiento.gridy = 19;
		getContentPane().add(textFieldFechaVencimiento, gbc_textFieldFechaVencimiento);

		lblVigente = new JLabel("Vigente:");
		lblVigente.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblVigente = new GridBagConstraints();
		gbc_lblVigente.anchor = GridBagConstraints.WEST;
		gbc_lblVigente.insets = new Insets(0, 0, 5, 5);
		gbc_lblVigente.gridx = 1;
		gbc_lblVigente.gridy = 20;
		getContentPane().add(lblVigente, gbc_lblVigente);

		textFieldVigente = new JTextField();
		textFieldVigente.setEditable(false);
		textFieldVigente.setColumns(10);
		GridBagConstraints gbc_textFieldVigente = new GridBagConstraints();
		gbc_textFieldVigente.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldVigente.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldVigente.gridx = 2;
		gbc_textFieldVigente.gridy = 20;
		getContentPane().add(textFieldVigente, gbc_textFieldVigente);

		// Postulaciones//

		lblPostulantes = new JLabel("Postulantes:");
		lblPostulantes.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPostulantes = new GridBagConstraints();
		gbc_lblPostulantes.anchor = GridBagConstraints.WEST;
		gbc_lblPostulantes.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostulantes.gridx = 1;
		gbc_lblPostulantes.gridy = 23;
		getContentPane().add(lblPostulantes, gbc_lblPostulantes);

		comboBoxPostulaciones = new JComboBox<DTPostulanteOfertaLaboral>();
		comboBoxPostulaciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdConsultarOLPostulacionActionPerformed(event);
			}
		});
		GridBagConstraints gbc_comboBoxPostulaciones = new GridBagConstraints();
		gbc_comboBoxPostulaciones.gridwidth = 4;
		gbc_comboBoxPostulaciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPostulaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPostulaciones.gridx = 2;
		gbc_comboBoxPostulaciones.gridy = 23;
		getContentPane().add(comboBoxPostulaciones, gbc_comboBoxPostulaciones);

		lblFechaPostulacion = new JLabel("Fecha postulación");
		lblFechaPostulacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaPostulacion = new GridBagConstraints();
		gbc_lblFechaPostulacion.anchor = GridBagConstraints.WEST;
		gbc_lblFechaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaPostulacion.gridx = 1;
		gbc_lblFechaPostulacion.gridy = 24;
		getContentPane().add(lblFechaPostulacion, gbc_lblFechaPostulacion);

		textFieldFechaPostulacion = new JTextField();
		textFieldFechaPostulacion.setEditable(false);
		textFieldFechaPostulacion.setColumns(10);
		GridBagConstraints gbc_textFieldFechaPostulacion = new GridBagConstraints();
		gbc_textFieldFechaPostulacion.gridwidth = 4;
		gbc_textFieldFechaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaPostulacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaPostulacion.gridx = 2;
		gbc_textFieldFechaPostulacion.gridy = 24;
		getContentPane().add(textFieldFechaPostulacion, gbc_textFieldFechaPostulacion);

		lblCvReducido = new JLabel("Cv reducido:");
		lblCvReducido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCvReducido = new GridBagConstraints();
		gbc_lblCvReducido.anchor = GridBagConstraints.WEST;
		gbc_lblCvReducido.insets = new Insets(0, 0, 5, 5);
		gbc_lblCvReducido.gridx = 1;
		gbc_lblCvReducido.gridy = 25;
		getContentPane().add(lblCvReducido, gbc_lblCvReducido);

		textFieldCvReducido = new JTextField();
		textFieldCvReducido.setEditable(false);
		textFieldCvReducido.setColumns(10);
		GridBagConstraints gbc_textFieldCvReducido = new GridBagConstraints();
		gbc_textFieldCvReducido.gridwidth = 4;
		gbc_textFieldCvReducido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCvReducido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCvReducido.gridx = 2;
		gbc_textFieldCvReducido.gridy = 25;
		getContentPane().add(textFieldCvReducido, gbc_textFieldCvReducido);

		lblMotivacion = new JLabel("Motivación:");
		lblMotivacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblMotivacion = new GridBagConstraints();
		gbc_lblMotivacion.anchor = GridBagConstraints.WEST;
		gbc_lblMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivacion.gridx = 1;
		gbc_lblMotivacion.gridy = 26;
		getContentPane().add(lblMotivacion, gbc_lblMotivacion);

		textFieldMotivacion = new JTextField();
		textFieldMotivacion.setEditable(false);
		textFieldMotivacion.setColumns(10);
		GridBagConstraints gbc_textFieldMotivacion = new GridBagConstraints();
		gbc_textFieldMotivacion.gridwidth = 4;
		gbc_textFieldMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMotivacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMotivacion.gridx = 2;
		gbc_textFieldMotivacion.gridy = 26;
		getContentPane().add(textFieldMotivacion, gbc_textFieldMotivacion);

		lblAdjuntos = new JLabel("Adjuntos:");
		lblAdjuntos.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAdjuntos = new GridBagConstraints();
		gbc_lblAdjuntos.anchor = GridBagConstraints.WEST;
		gbc_lblAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdjuntos.gridx = 1;
		gbc_lblAdjuntos.gridy = 27;
		getContentPane().add(lblAdjuntos, gbc_lblAdjuntos);

		textFieldAdjuntos = new JTextField();
		textFieldAdjuntos.setEditable(false);
		textFieldAdjuntos.setColumns(10);
		GridBagConstraints gbc_textFieldAdjuntos = new GridBagConstraints();
		gbc_textFieldAdjuntos.gridwidth = 4;
		gbc_textFieldAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAdjuntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdjuntos.gridx = 2;
		gbc_textFieldAdjuntos.gridy = 27;
		getContentPane().add(textFieldAdjuntos, gbc_textFieldAdjuntos);

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
		GridBagConstraints gbc_btnCerrar = new GridBagConstraints();
		gbc_btnCerrar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCerrar.fill = GridBagConstraints.BOTH;
		gbc_btnCerrar.gridx = 3;
		gbc_btnCerrar.gridy = 28;
		getContentPane().add(btnCerrar, gbc_btnCerrar);
	}

	private void limpiarFormulario() {

		this.comboBoxEmpresa.removeAllItems();
		limpiarFormularioEmpresa();
		limpiarFormularioOL();

	}

	private void limpiarFormularioEmpresa() {
		// this.comboBoxEmpresa.removeAllItems();
		this.comboBoxOfertasLaborales.removeAllItems();

		txtNombre.setText("");
		textFieldApellido.setText("");
		textFieldCorreo.setText("");

		textFieldEmpresaNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldLink.setText("");

		textFieldNombreOf.setText("");
		textFieldDescripcionOf.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		textFieldFechaAlta.setText("");

		limpiarFormularioOL();

	}

	private void limpiarFormularioOL() {
		// this.comboBoxEmpresa.removeAllItems();
		// this.comboBoxOfertasLaborales.removeAllItems();

		/*
		 * txtNombre.setText(""); textFieldApellido.setText("");
		 * textFieldCorreo.setText("");
		 * 
		 * textFieldNombreOf.setText(""); textFieldDescripcionOf.setText("");
		 * textFieldCiudad.setText(""); textFieldDepartamento.setText("");
		 * textFieldHorario.setText(""); textFieldRemuneracion.setText("");
		 * textFieldFechaAlta.setText("");
		 */

		textFieldNombreOf.setText("");
		textFieldDescripcionOf.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		textFieldFechaAlta.setText("");
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

	protected void cmdConsultarEmpresaActionPerformed(ActionEvent arg0) {

		limpiarFormularioEmpresa();

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String empresa_nombre = this.comboBoxEmpresa.getSelectedItem().toString();

			if (!empresa_nombre.isEmpty()) {

				this.cargarDatosEmpresaSeleccionado(empresa_nombre);
				this.cargarComboOfertas(empresa_nombre);
				// Muestro éxito de la operación
				// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
				// "Registrar Usuario",
				// JOptionPane.INFORMATION_MESSAGE);

				// Limpio el internal frame antes de cerrar la ventana
			}
		}
	}

	protected void cmdConsultarOfertaLaboralActionPerformed(ActionEvent event) {

		limpiarFormularioOL();

		if (checkFormularioOL()) {

			// Obtengo datos de los controles Swing
			// String oferta_nombre =
			// this.comboBoxOfertasLaborales.getSelectedItem().toString();
			DTOfertaLaboral dt_oferta_laboral = (DTOfertaLaboral) this.comboBoxOfertasLaborales.getSelectedItem();

			this.cargarDatosOfertaSeleccionado(dt_oferta_laboral);
			this.cargarComboPostulante(dt_oferta_laboral.getNombre());

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
			// JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa a
			// consultar",
			// "Consultar Postulante", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String empresa_nombre = this.comboBoxEmpresa.getSelectedItem().toString();

		if (empresa_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa a consultar", "Consultar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
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

	private boolean checkFormularioOL() {

		if (this.comboBoxOfertasLaborales.getSelectedIndex() == -1) {
			// JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral a
			// consultar",
			// "Consultar Postulante", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String ofertalaboral_nombre = this.comboBoxOfertasLaborales.getSelectedItem().toString();

		if (ofertalaboral_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral a consultar",
					"Consultar Postulantes", JOptionPane.ERROR_MESSAGE);
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

	// Método que permite cargar un nuevo modelo para el combo con la información
	// actualizada de usuarios, provista por la operación del sistema getUsuarios().
	// Se invoca el método antes de hacer visible el JInternalFrame
	public void cargarComboEmpresa() {
		DefaultComboBoxModel<DTEmpresa> model;
		try {

			model = new DefaultComboBoxModel<DTEmpresa>(controlU.listarEmpresas());
			this.comboBoxEmpresa.setModel(model);
			this.comboBoxEmpresa.insertItemAt(new DTEmpresa("", "", "", "", "", "", this.rol, "", "", ""), 0);
			this.comboBoxEmpresa.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarComboOfertas(String empresa_nickname) {
		DefaultComboBoxModel<DTOfertaLaboral> model;
		try {

			// DTEmpresa data_empresa = (DTEmpresa) comboBoxEmpresa.getSelectedItem();
			model = new DefaultComboBoxModel<DTOfertaLaboral>(
					controlU.listarOfertasLaboralesPorEmpresa(empresa_nickname));
			this.comboBoxOfertasLaborales.setModel(model);

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

	public void cargarDatosOfertaSeleccionado(DTOfertaLaboral dt_ofertaLaboral) {

		textFieldNombreOf.setText(dt_ofertaLaboral.getNombre());
		textFieldDescripcionOf.setText(dt_ofertaLaboral.getDescripcion());
		textFieldCiudad.setText(dt_ofertaLaboral.getCiudad());
		textFieldDepartamento.setText(dt_ofertaLaboral.getDepartamento());
		textFieldHorario.setText(dt_ofertaLaboral.getHorario());
		textFieldRemuneracion.setText(dt_ofertaLaboral.getRemuneracion().toString());
		textFieldFechaAlta.setText(dt_ofertaLaboral.getFechaAltaFormat().toString());

		textFieldFechaVencimiento.setText(dt_ofertaLaboral.getFechaVencimientoFormat());
		textFieldVigente.setText(dt_ofertaLaboral.getVigenteFormat());
	}

	public void cargarDatosEmpresaSeleccionado(String empresa_nombre) {

		try {

			DTEmpresa dt_empresa = (DTEmpresa) controlU.getDataUsuario(empresa_nombre);

			txtNombre.setText(dt_empresa.getNombre());
			textFieldApellido.setText(dt_empresa.getApellido());
			textFieldCorreo.setText(dt_empresa.getCorreo());

			textFieldEmpresaNombre.setText(dt_empresa.getNombreEmpresa());
			textFieldDescripcion.setText(dt_empresa.getDescripcion());
			textFieldLink.setText(dt_empresa.getSitioWeb().toString());

			// ACA SEGURAMENTE TAMBIEN LO DE LAS OFERTAS LABORALES

		} catch (ObjetoNoExisteException e) {
			// Muestro error de registro
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Empresa", JOptionPane.ERROR_MESSAGE);
		}

	}

	public void cargarDatosPostulacionSeleccionada(DTPostulanteOfertaLaboral dt_postulacion) {

		textFieldFechaPostulacion.setText(dt_postulacion.getFechaFormat());
		textFieldMotivacion.setText(dt_postulacion.getMotivacion());
		textFieldCvReducido.setText(dt_postulacion.getCvReducido());
		textFieldAdjuntos.setText(dt_postulacion.getAdjuntos());

	}

}
