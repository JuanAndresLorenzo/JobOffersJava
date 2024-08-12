package presentacion.frames_casos_de_uso.oferta_laboral;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.toedter.calendar.JDateChooser;

import java.util.Date;
import java.util.List;

import logica.interfaces.IControladorOfertaLaboral;
import logica.dts.DTEmpresa;
import logica.dts.DTKeyword;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTTipoPublicacion;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;
import excepciones.ObjetoRepetidoException;

import javax.swing.JList;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AltadeOfertaLaboral extends JInternalFrame {

	private IControladorOfertaLaboral controlOL;

	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JTextArea textFieldDescripcion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textFieldHorario;
	private JTextField textFieldRemuneracion;
	// private JTextField textFieldFechaAlta;
	private JDateChooser dateChooserFechaAlta;

	private final JLabel lblRemuneracion = new JLabel("Remuneracion");
	private JLabel lblFechaAlta;
	private JComboBox<DTEmpresa> comboBoxEmpresa;
	private JLabel lblEmpresa;
	private JLabel lblTipoPublicacion;
	private JLabel lblKeywords;
	private JComboBox<DTTipoPublicacion> comboBoxTipoPublicacion;
	private JList<String> listKeywords;
	private JLabel lblFechaVencimiento;
	private JDateChooser dateChooserFechaVencimiento;

	// Create the frame
	public AltadeOfertaLaboral(IControladorOfertaLaboral ICOL) {

		controlOL = ICOL;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Registrar una Oferta Laboral");
		setBounds(10, 40, 507, 579);

		/*
		 * GridBagLayout gridBagLayout = new GridBagLayout(); gridBagLayout.columnWidths
		 * = new int[] { 100, 120, 120, 0 }; gridBagLayout.rowHeights = new int[] { 30,
		 * 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0 }; gridBagLayout.columnWeights = new
		 * double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE }; gridBagLayout.rowWeights = new
		 * double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		 * Double.MIN_VALUE }; getContentPane().setLayout(gridBagLayout);
		 */

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0 };
		gridBagLayout.columnWidths = new int[] { 57, 0, 114, 137, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		// gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		// gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		// 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };

		getContentPane().setLayout(gridBagLayout);

		lblEmpresa = new JLabel("Empresa");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 0;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);

		comboBoxEmpresa = new JComboBox<DTEmpresa>();
		GridBagConstraints gbc_comboBoxEmpresa = new GridBagConstraints();
		gbc_comboBoxEmpresa.gridwidth = 2;
		gbc_comboBoxEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresa.gridx = 2;
		gbc_comboBoxEmpresa.gridy = 0;
		getContentPane().add(comboBoxEmpresa, gbc_comboBoxEmpresa);

		lblTipoPublicacion = new JLabel("TipoPublicacion");
		GridBagConstraints gbc_lblTipoPublicacion = new GridBagConstraints();
		gbc_lblTipoPublicacion.anchor = GridBagConstraints.WEST;
		gbc_lblTipoPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoPublicacion.gridx = 1;
		gbc_lblTipoPublicacion.gridy = 1;
		getContentPane().add(lblTipoPublicacion, gbc_lblTipoPublicacion);

		comboBoxTipoPublicacion = new JComboBox<DTTipoPublicacion>();
		GridBagConstraints gbc_comboBoxTipoPublicacion = new GridBagConstraints();
		gbc_comboBoxTipoPublicacion.gridwidth = 2;
		gbc_comboBoxTipoPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipoPublicacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoPublicacion.gridx = 2;
		gbc_comboBoxTipoPublicacion.gridy = 1;
		getContentPane().add(comboBoxTipoPublicacion, gbc_comboBoxTipoPublicacion);

		lblKeywords = new JLabel("Keywords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.anchor = GridBagConstraints.WEST;
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeywords.gridx = 1;
		gbc_lblKeywords.gridy = 2;
		getContentPane().add(lblKeywords, gbc_lblKeywords);

		listKeywords = new JList<String>();
		listKeywords.setValueIsAdjusting(true);
		GridBagConstraints gbc_listKeywords = new GridBagConstraints();
		gbc_listKeywords.gridheight = 3;
		gbc_listKeywords.gridwidth = 2;
		gbc_listKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_listKeywords.fill = GridBagConstraints.BOTH;
		gbc_listKeywords.gridx = 2;
		gbc_listKeywords.gridy = 2;
		getContentPane().add(listKeywords, gbc_listKeywords);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.EAST;
		gbc_lblNombre.fill = GridBagConstraints.VERTICAL;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textField_6 = new GridBagConstraints();
		gbc_textField_6.gridwidth = 2;
		gbc_textField_6.insets = new Insets(0, 0, 5, 5);
		gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_6.gridx = 2;
		gbc_textField_6.gridy = 5;
		getContentPane().add(textFieldNombre, gbc_textField_6);
		textFieldNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 6;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textFieldDescripcion = new JTextArea();
		textFieldDescripcion.setLineWrap(true);
		GridBagConstraints gbc_textFieldDescripcion = new GridBagConstraints();
		gbc_textFieldDescripcion.gridwidth = 2;
		gbc_textFieldDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcion.gridx = 2;
		gbc_textFieldDescripcion.gridy = 6;
		getContentPane().add(textFieldDescripcion, gbc_textFieldDescripcion);
		textFieldDescripcion.setColumns(10);

		/*
		 * textFieldFechaAlta = new JTextField(); GridBagConstraints
		 * gbc_textFieldFechaAlta = new GridBagConstraints();
		 * gbc_textFieldFechaAlta.gridwidth = 2; gbc_textFieldFechaAlta.insets = new
		 * Insets(0, 0, 5, 0); gbc_textFieldFechaAlta.fill =
		 * GridBagConstraints.HORIZONTAL; gbc_textFieldFechaAlta.gridx = 1;
		 * gbc_textFieldFechaAlta.gridy = 9; getContentPane().add(textFieldFechaAlta,
		 * gbc_textFieldFechaAlta); textFieldFechaAlta.setColumns(10);
		 */

		Date fecha_actual = new Date();

		JLabel lblCiudad = new JLabel("Ciudad");
		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.EAST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 7;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		textFieldCiudad = new JTextField();
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.gridwidth = 2;
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 7;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);
		textFieldCiudad.setColumns(10);

		JLabel lblDepartamento = new JLabel("Departamento");
		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.EAST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 8;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		textFieldDepartamento = new JTextField();
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.gridwidth = 2;
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 2;
		gbc_textFieldDepartamento.gridy = 8;
		getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);
		textFieldDepartamento.setColumns(10);

		JLabel lblHorario = new JLabel("Horario");
		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.EAST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 1;
		gbc_lblHorario.gridy = 9;
		getContentPane().add(lblHorario, gbc_lblHorario);

		textFieldHorario = new JTextField();
		GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
		gbc_textFieldHorario.gridwidth = 2;
		gbc_textFieldHorario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorario.gridx = 2;
		gbc_textFieldHorario.gridy = 9;
		getContentPane().add(textFieldHorario, gbc_textFieldHorario);
		textFieldHorario.setColumns(10);
		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 1;
		gbc_lblRemuneracion.gridy = 10;
		getContentPane().add(lblRemuneracion, gbc_lblRemuneracion);

		textFieldRemuneracion = new JTextField();
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.gridwidth = 2;
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 10;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);
		textFieldRemuneracion.setColumns(10);
		lblFechaAlta = new JLabel("Fecha Alta");
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.anchor = GridBagConstraints.EAST;
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 11;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdregistrarOfertaLaboralActionPerformed(arg0);
			}
		});
		dateChooserFechaAlta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		dateChooserFechaAlta.setDate(fecha_actual);

		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 11;
		getContentPane().add(dateChooserFechaAlta, gbc_dateChooser);

		lblFechaVencimiento = new JLabel("Fecha Vencimiento");
		GridBagConstraints gbc_lblFechaVencimiento = new GridBagConstraints();
		gbc_lblFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaVencimiento.gridx = 1;
		gbc_lblFechaVencimiento.gridy = 12;
		getContentPane().add(lblFechaVencimiento, gbc_lblFechaVencimiento);

		dateChooserFechaVencimiento = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		GridBagConstraints gbc_dateChooserFechaVencimiento = new GridBagConstraints();
		gbc_dateChooserFechaVencimiento.gridwidth = 2;
		gbc_dateChooserFechaVencimiento.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFechaVencimiento.fill = GridBagConstraints.BOTH;
		gbc_dateChooserFechaVencimiento.gridx = 2;
		gbc_dateChooserFechaVencimiento.gridy = 12;
		getContentPane().add(dateChooserFechaVencimiento, gbc_dateChooserFechaVencimiento);

		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 14;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

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
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 14;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textFieldDescripcion.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		// textFieldFechaAlta.setText("");
		dateChooserFechaAlta.setDate(new Date());

	}

	protected void cmdregistrarOfertaLaboralActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String nombre_oferta_laboral = this.textFieldNombre.getText();
			String descripcion_oferta_laboral = this.textFieldDescripcion.getText();
			String ciudad_oferta_laboral = this.textFieldCiudad.getText();
			String departamento_oferta_laboral = this.textFieldDepartamento.getText();
			String horario_oferta_laboral = this.textFieldHorario.getText();
			Float remuneracion_oferta_laboral = Float.parseFloat(this.textFieldRemuneracion.getText());

			// String fechaAlta_oferta_laboral_str = this.textFieldFechaAlta.getText();
			Date fechaAlta_oferta_laboral = this.dateChooserFechaAlta.getDate();
			Date fechaVencimientoOfertaLaboral = this.dateChooserFechaVencimiento.getDate();
			DTEmpresa nombre_empresa = (DTEmpresa) this.comboBoxEmpresa.getSelectedItem();
			String nombree = nombre_empresa.getNickname();

			DTTipoPublicacion nombre_tipo_publicacion = (DTTipoPublicacion) this.comboBoxTipoPublicacion
					.getSelectedItem();
			String nombretp = nombre_tipo_publicacion.getNombre();

			List<String> keywords = this.listKeywords.getSelectedValuesList();

			try {

				DTOfertaLaboral dtOfertaLaboral = new DTOfertaLaboral(nombre_oferta_laboral, descripcion_oferta_laboral,
						ciudad_oferta_laboral, departamento_oferta_laboral, horario_oferta_laboral,
						remuneracion_oferta_laboral, fechaAlta_oferta_laboral, fechaVencimientoOfertaLaboral, closable,
						nombre_empresa, nombre_tipo_publicacion);

				controlOL.registrarOfertaLaboral(dtOfertaLaboral);

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "La oferta laboral se ha creado con éxito",
						"Registrar Oferta Laboral", JOptionPane.INFORMATION_MESSAGE);

				// Limpio el internal frame antes de cerrar la ventana
				limpiarFormulario();
				setVisible(false);

			} catch (ObjetoRepetidoException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Oferta Laboral",
						JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		if (this.comboBoxEmpresa.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa para la oferta laboral",
					"Modificar datos Empresa", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (this.comboBoxTipoPublicacion.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de publicaicón para la oferta laboral",
					"Modificar datos Empresa", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String nombre_empresa = this.comboBoxEmpresa.getSelectedItem().toString();
		String nombre_tipo_publicacion = this.comboBoxTipoPublicacion.getSelectedItem().toString();
		String nombre_oferta_laboral = this.textFieldNombre.getText();
		String descripcion_oferta_laboral = this.textFieldDescripcion.getText();
		String ciudad_oferta_laboral = this.textFieldCiudad.getText();
		String departamento_oferta_laboral = this.textFieldDepartamento.getText();
		String horario_oferta_laboral = this.textFieldHorario.getText();
		String remuneracion_oferta_laboral = this.textFieldRemuneracion.getText();
		// String fechaAlta_oferta_laboral = this.textFieldFechaAlta.getText();
		Date fechaAlta_oferta_laboral = this.dateChooserFechaAlta.getDate();

		if (nombre_oferta_laboral.isEmpty() || descripcion_oferta_laboral.isEmpty() || ciudad_oferta_laboral.isEmpty()
				|| departamento_oferta_laboral.isEmpty() || horario_oferta_laboral.isEmpty()
				|| remuneracion_oferta_laboral.isEmpty() || nombre_empresa.isEmpty()
				|| nombre_tipo_publicacion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Oferta Laboral",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(remuneracion_oferta_laboral);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La remuneración debe ser un número decimal",
					"Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (fechaAlta_oferta_laboral == null) {
			JOptionPane.showMessageDialog(this, "El campo fecha no puede quedar vacío o incorrecto",
					"Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (controlOL.existeOfertaLaboral(nombre_oferta_laboral)) {
			JOptionPane.showMessageDialog(this, "Ya existe una oferta laboral con ese nombre",
					"Registrar Oferta Laboral", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void cargarComboEmpresa() {
		DefaultComboBoxModel<DTEmpresa> modelEmpresa;
		try {
			modelEmpresa = new DefaultComboBoxModel<DTEmpresa>(controlOL.listarEmpresas());

			this.comboBoxEmpresa.setModel(modelEmpresa);

			this.comboBoxEmpresa.insertItemAt(new DTEmpresa("", "", "", "", "", "", EnumRol.EMPRESA, "", "", ""), 0);
			this.comboBoxEmpresa.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarComboTipoPublicacion() {
		DefaultComboBoxModel<DTTipoPublicacion> modelTP;
		try {

			modelTP = new DefaultComboBoxModel<DTTipoPublicacion>(controlOL.listarTiposPublicaciones());

			this.comboBoxTipoPublicacion.setModel(modelTP);

			this.comboBoxTipoPublicacion.insertItemAt(new DTTipoPublicacion("", "", 0, 0, (float) 0, new Date()), 0);
			this.comboBoxTipoPublicacion.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarListKeyword() {

		DTKeyword[] keywords = null;

		try {
			keywords = controlOL.listarKeywords();
		} catch (ObjetoNoExisteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		DefaultListModel<String> listModel = new DefaultListModel<String>();

		for (int i = 0; i < keywords.length; i++) {

			listModel.add(i, keywords[i].getNombre());

		}

		this.listKeywords.setModel(listModel);
	}
}
