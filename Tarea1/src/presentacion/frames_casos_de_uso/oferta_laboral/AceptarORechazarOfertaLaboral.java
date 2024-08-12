package presentacion.frames_casos_de_uso.oferta_laboral;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
import logica.clases.EnumEstadoOL;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

@SuppressWarnings("serial")
public class AceptarORechazarOfertaLaboral extends JInternalFrame {

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

	private JTextField textFieldKeywords;
	private JLabel lblKeywords;
	private JLabel lblVigente;
	private JTextField textFieldVigente;
	private JLabel lblFechaVencimiento;
	private JTextField textFieldFechaVencimiento;
	private JLabel lblTipoPublicacion;
	private JTextField textFieldTipoPublicacion;
	private JButton btnConfirmar;
	private JButton btnRechazar;
	private JScrollPane scrollPaneDescrip;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public AceptarORechazarOfertaLaboral(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Aceptar/RechazarOfertaLaboral");
		setBounds(10, 40, 924, 729);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 122, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0 };

		gridBagLayout.columnWidths = new int[] { 44, 217, 136, 108, 141, 120, 0 };
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

		scrollPaneDescrip = new JScrollPane();
		scrollPaneDescrip.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPaneDescrip = new GridBagConstraints();
		gbc_scrollPaneDescrip.gridwidth = 3;
		gbc_scrollPaneDescrip.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPaneDescrip.fill = GridBagConstraints.BOTH;
		gbc_scrollPaneDescrip.gridx = 2;
		gbc_scrollPaneDescrip.gridy = 5;
		getContentPane().add(scrollPaneDescrip, gbc_scrollPaneDescrip);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setLineWrap(true);
		scrollPaneDescrip.setViewportView(textAreaDescripcion);

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

		btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdModificarEstadoOfertaLaboralConfirmar(event);
			}
		});
		GridBagConstraints gbc_btnConfirmar = new GridBagConstraints();
		gbc_btnConfirmar.insets = new Insets(0, 0, 0, 5);
		gbc_btnConfirmar.gridx = 2;
		gbc_btnConfirmar.gridy = 16;
		getContentPane().add(btnConfirmar, gbc_btnConfirmar);

		btnRechazar = new JButton("Rechazar");
		btnRechazar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdModificarEstadoOfertaLaboralRechazar(event);
			}
		});
		GridBagConstraints gbc_btnRechazar = new GridBagConstraints();
		gbc_btnRechazar.insets = new Insets(0, 0, 0, 5);
		gbc_btnRechazar.gridx = 3;
		gbc_btnRechazar.gridy = 16;
		getContentPane().add(btnRechazar, gbc_btnRechazar);

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
		gbc_btnCancelar.gridy = 16;

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

			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
		}
	}

	protected void cmdModificarEstadoOfertaLaboralConfirmar(ActionEvent event) {

		if (checkFormularioOL()) {
			String oferta_nombre = this.textFieldNombreOf.getText();

			controlU.modificarEstadoOL(oferta_nombre, EnumEstadoOL.CONFIRMADA);

			// Muestro éxito de la operación
			JOptionPane.showMessageDialog(this, "El estado de la oferta laboral se ha modificado con exito",
					"Modificar estado Oferta laboral", JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
			limpiarFormulario();
			setVisible(false);
		}
	}

	protected void cmdModificarEstadoOfertaLaboralRechazar(ActionEvent event) {

		if (checkFormularioOL()) {
			String oferta_nombre = this.textFieldNombreOf.getText();

			controlU.modificarEstadoOL(oferta_nombre, EnumEstadoOL.RECHAZADA);

			// Muestro éxito de la operación
			JOptionPane.showMessageDialog(this, "El estado de la oferta laboral se ha modificado con exito",
					"Modificar estado Oferta laboral", JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
			limpiarFormulario();
			setVisible(false);
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
					controlU.listarOfertasLaboralesIngresadasPorEmpresa(empresa_nickname));
			this.comboBoxOfertasLaborales.setModel(modelOL);

			this.comboBoxOfertasLaborales.insertItemAt(new DTOfertaLaboral("", "", "", "", "", (float) 0, new Date(), new Date(), false, null, null), 0); // REVISAAR
			this.comboBoxOfertasLaborales.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	// cambiar el listar postulantes por obtener posutlantes de ofertalaboral

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
}
