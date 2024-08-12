package presentacion.frames_casos_de_uso.oferta_laboral;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import logica.dts.DTEmpresa;
import logica.dts.DTOfertaLaboral;
import logica.dts.DTPostulanteOfertaLaboral;
import logica.dts.DTPostulante;
import logica.clases.EnumRol;

import logica.interfaces.IControladorUsuario;

import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import excepciones.ObjetoNoExisteException;

import com.toedter.calendar.JDateChooser;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class PostulacionaOfertaLaboral extends JInternalFrame {

	private IControladorUsuario controlU;

	private JComboBox<DTEmpresa> comboBoxEmpresa;
	private JComboBox<DTOfertaLaboral> comboBoxOfertasLaborales;

	private JTextField textFieldNombre;
	private JTextArea textPaneDescripcion;
	private JTextField textFieldCiudad;
	private JTextField textFieldDepartamento;
	private JTextField textFieldHorario;
	private JTextField textFieldRemuneracion;
	private JTextField textFieldFechaAlta;

	private JComboBox<DTPostulante> comboBoxPostulante;

	private JTextArea textFieldCvReducido;
	private JTextArea textFieldMotivacion;
	private JTextField textFieldAdjuntos;
	private JDateChooser dateChooserFechaPostulacion;

	private JButton btnPostular;

	public PostulacionaOfertaLaboral(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Postulacion a oferta laboral");
		setBounds(10, 40, 559, 666);

		/*
		 * GridBagLayout gridBagLayout = new GridBagLayout(); gridBagLayout.columnWidths
		 * = new int[] { 0, 0, 0, 165, 0, 0, 0 }; gridBagLayout.rowHeights = new int[] {
		 * 0, 0, 0, 0, 78, 0, 0, 0, 0, 0, 0, 59, 0, 0, 0, 0, 0, 0, 0, 0 };
		 * gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0,
		 * Double.MIN_VALUE }; gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0,
		 * 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		 * 0.0, Double.MIN_VALUE }; getContentPane().setLayout(gridBagLayout);
		 */

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 57, 0, 114, 134, 54, 58, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0 };
		// gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		// gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
		// 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblEmpresa = new JLabel("Empresa :");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 0;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);

		comboBoxEmpresa = new JComboBox<DTEmpresa>();
		comboBoxEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				cmdCargarOfertasPorEmpresaActionPerformed(event);
			}
		});
		GridBagConstraints gbc_comboBoxEmpresa = new GridBagConstraints();
		gbc_comboBoxEmpresa.gridwidth = 2;
		gbc_comboBoxEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxEmpresa.gridx = 2;
		gbc_comboBoxEmpresa.gridy = 0;
		getContentPane().add(comboBoxEmpresa, gbc_comboBoxEmpresa);

		JLabel lblOfertasLaborales = new JLabel("Oferta Laboral :");
		GridBagConstraints gbc_lblOfertasLaborales = new GridBagConstraints();
		gbc_lblOfertasLaborales.anchor = GridBagConstraints.WEST;
		gbc_lblOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_lblOfertasLaborales.gridx = 1;
		gbc_lblOfertasLaborales.gridy = 2;
		getContentPane().add(lblOfertasLaborales, gbc_lblOfertasLaborales);

		comboBoxOfertasLaborales = new JComboBox<DTOfertaLaboral>();
		comboBoxOfertasLaborales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdConsultarOfertaLaboralActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_comboBoxOfertasLaborales = new GridBagConstraints();
		gbc_comboBoxOfertasLaborales.gridwidth = 2;
		gbc_comboBoxOfertasLaborales.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxOfertasLaborales.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxOfertasLaborales.gridx = 2;
		gbc_comboBoxOfertasLaborales.gridy = 2;
		getContentPane().add(comboBoxOfertasLaborales, gbc_comboBoxOfertasLaborales);

		JLabel labelNombre = new JLabel("Nombre : ");

		GridBagConstraints gbc_labelNombre = new GridBagConstraints();
		gbc_labelNombre.anchor = GridBagConstraints.WEST;
		gbc_labelNombre.insets = new Insets(0, 0, 5, 5);
		gbc_labelNombre.gridx = 1;
		gbc_labelNombre.gridy = 3;
		getContentPane().add(labelNombre, gbc_labelNombre);

		textFieldNombre = new JTextField();

		textFieldNombre.setEditable(false);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion :");

		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 4;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		textPaneDescripcion = new JTextArea();
		textPaneDescripcion.setLineWrap(true);
		// textPane.setBorder(new LineBorder(new Color(222, 221, 218), 2));
		// textPane.setBackground(new Color(238, 238, 238));

		textPaneDescripcion.setEditable(false);
		GridBagConstraints gbc_textPane = new GridBagConstraints();
		gbc_textPane.gridwidth = 2;
		gbc_textPane.insets = new Insets(0, 0, 5, 5);
		gbc_textPane.fill = GridBagConstraints.BOTH;
		gbc_textPane.gridx = 2;
		gbc_textPane.gridy = 4;
		getContentPane().add(textPaneDescripcion, gbc_textPane);

		JLabel lblCiudad = new JLabel("Ciudad :");

		GridBagConstraints gbc_lblCiudad = new GridBagConstraints();
		gbc_lblCiudad.anchor = GridBagConstraints.WEST;
		gbc_lblCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCiudad.gridx = 1;
		gbc_lblCiudad.gridy = 5;
		getContentPane().add(lblCiudad, gbc_lblCiudad);

		textFieldCiudad = new JTextField();

		textFieldCiudad.setEditable(false);
		textFieldCiudad.setColumns(10);
		GridBagConstraints gbc_textFieldCiudad = new GridBagConstraints();
		gbc_textFieldCiudad.gridwidth = 2;
		gbc_textFieldCiudad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCiudad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCiudad.gridx = 2;
		gbc_textFieldCiudad.gridy = 5;
		getContentPane().add(textFieldCiudad, gbc_textFieldCiudad);

		JLabel lblDepartamento = new JLabel("Departamento :");

		GridBagConstraints gbc_lblDepartamento = new GridBagConstraints();
		gbc_lblDepartamento.anchor = GridBagConstraints.WEST;
		gbc_lblDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDepartamento.gridx = 1;
		gbc_lblDepartamento.gridy = 6;
		getContentPane().add(lblDepartamento, gbc_lblDepartamento);

		textFieldDepartamento = new JTextField();

		textFieldDepartamento.setEditable(false);
		textFieldDepartamento.setColumns(10);
		GridBagConstraints gbc_textFieldDepartamento = new GridBagConstraints();
		gbc_textFieldDepartamento.gridwidth = 2;
		gbc_textFieldDepartamento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDepartamento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDepartamento.gridx = 2;
		gbc_textFieldDepartamento.gridy = 6;
		getContentPane().add(textFieldDepartamento, gbc_textFieldDepartamento);

		JLabel lblHorario = new JLabel("Horario :");

		GridBagConstraints gbc_lblHorario = new GridBagConstraints();
		gbc_lblHorario.anchor = GridBagConstraints.WEST;
		gbc_lblHorario.insets = new Insets(0, 0, 5, 5);
		gbc_lblHorario.gridx = 1;
		gbc_lblHorario.gridy = 7;
		getContentPane().add(lblHorario, gbc_lblHorario);

		textFieldHorario = new JTextField();

		textFieldHorario.setEditable(false);
		textFieldHorario.setColumns(10);
		GridBagConstraints gbc_textFieldHorario = new GridBagConstraints();
		gbc_textFieldHorario.gridwidth = 2;
		gbc_textFieldHorario.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldHorario.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldHorario.gridx = 2;
		gbc_textFieldHorario.gridy = 7;
		getContentPane().add(textFieldHorario, gbc_textFieldHorario);

		JLabel lblRemuneracion = new JLabel("Remuneracion :");

		GridBagConstraints gbc_lblRemuneracion = new GridBagConstraints();
		gbc_lblRemuneracion.anchor = GridBagConstraints.WEST;
		gbc_lblRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblRemuneracion.gridx = 1;
		gbc_lblRemuneracion.gridy = 8;
		getContentPane().add(lblRemuneracion, gbc_lblRemuneracion);

		textFieldRemuneracion = new JTextField();

		textFieldRemuneracion.setEditable(false);
		textFieldRemuneracion.setColumns(10);
		GridBagConstraints gbc_textFieldRemuneracion = new GridBagConstraints();
		gbc_textFieldRemuneracion.gridwidth = 2;
		gbc_textFieldRemuneracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldRemuneracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldRemuneracion.gridx = 2;
		gbc_textFieldRemuneracion.gridy = 8;
		getContentPane().add(textFieldRemuneracion, gbc_textFieldRemuneracion);

		JLabel lblFechaDeAlta = new JLabel("Fecha de Alta: ");

		GridBagConstraints gbc_lblFechaDeAlta = new GridBagConstraints();
		gbc_lblFechaDeAlta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaDeAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeAlta.gridx = 1;
		gbc_lblFechaDeAlta.gridy = 9;
		getContentPane().add(lblFechaDeAlta, gbc_lblFechaDeAlta);

		textFieldFechaAlta = new JTextField();
		textFieldFechaAlta.setEditable(false);
		textFieldFechaAlta.setColumns(10);
		GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		gbc_textFieldFechaAlta.gridwidth = 2;
		gbc_textFieldFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFechaAlta.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaAlta.gridx = 2;
		gbc_textFieldFechaAlta.gridy = 9;
		getContentPane().add(textFieldFechaAlta, gbc_textFieldFechaAlta);

		JLabel lblPostulante = new JLabel("Postulante :");
		GridBagConstraints gbc_lblPostulante = new GridBagConstraints();
		gbc_lblPostulante.anchor = GridBagConstraints.WEST;
		gbc_lblPostulante.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostulante.gridx = 1;
		gbc_lblPostulante.gridy = 11;
		getContentPane().add(lblPostulante, gbc_lblPostulante);

		comboBoxPostulante = new JComboBox<DTPostulante>();
		GridBagConstraints gbc_comboBoxPostulante = new GridBagConstraints();
		gbc_comboBoxPostulante.gridwidth = 2;
		gbc_comboBoxPostulante.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPostulante.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPostulante.gridx = 2;
		gbc_comboBoxPostulante.gridy = 11;
		getContentPane().add(comboBoxPostulante, gbc_comboBoxPostulante);

		JLabel lblCVReducido = new JLabel("CV Reducido: ");
		GridBagConstraints gbc_lblCVReducido = new GridBagConstraints();
		gbc_lblCVReducido.anchor = GridBagConstraints.WEST;
		gbc_lblCVReducido.insets = new Insets(0, 0, 5, 5);
		gbc_lblCVReducido.gridx = 1;
		gbc_lblCVReducido.gridy = 12;
		getContentPane().add(lblCVReducido, gbc_lblCVReducido);

		textFieldCvReducido = new JTextArea();
		textFieldCvReducido.setLineWrap(true);
		GridBagConstraints gbc_textFieldCvReducido = new GridBagConstraints();
		gbc_textFieldCvReducido.gridwidth = 2;
		gbc_textFieldCvReducido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCvReducido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCvReducido.gridx = 2;
		gbc_textFieldCvReducido.gridy = 12;
		getContentPane().add(textFieldCvReducido, gbc_textFieldCvReducido);
		textFieldCvReducido.setColumns(10);

		JLabel lblMotivacion = new JLabel("Motivacion: ");
		GridBagConstraints gbc_lblMotivacion = new GridBagConstraints();
		gbc_lblMotivacion.anchor = GridBagConstraints.WEST;
		gbc_lblMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblMotivacion.gridx = 1;
		gbc_lblMotivacion.gridy = 13;
		getContentPane().add(lblMotivacion, gbc_lblMotivacion);

		textFieldMotivacion = new JTextArea();
		textFieldMotivacion.setLineWrap(true);
		textFieldMotivacion.setColumns(10);
		GridBagConstraints gbc_textFieldMotivacion = new GridBagConstraints();
		gbc_textFieldMotivacion.gridwidth = 2;
		gbc_textFieldMotivacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMotivacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMotivacion.gridx = 2;
		gbc_textFieldMotivacion.gridy = 13;
		getContentPane().add(textFieldMotivacion, gbc_textFieldMotivacion);

		JLabel lblAdjuntos = new JLabel("Adjuntos: ");
		GridBagConstraints gbc_lblAdjuntos = new GridBagConstraints();
		gbc_lblAdjuntos.anchor = GridBagConstraints.WEST;
		gbc_lblAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_lblAdjuntos.gridx = 1;
		gbc_lblAdjuntos.gridy = 14;
		getContentPane().add(lblAdjuntos, gbc_lblAdjuntos);

		textFieldAdjuntos = new JTextField();
		textFieldAdjuntos.setColumns(10);
		GridBagConstraints gbc_textFieldAdjuntos = new GridBagConstraints();
		gbc_textFieldAdjuntos.gridwidth = 2;
		gbc_textFieldAdjuntos.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldAdjuntos.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdjuntos.gridx = 2;
		gbc_textFieldAdjuntos.gridy = 14;
		getContentPane().add(textFieldAdjuntos, gbc_textFieldAdjuntos);

		JLabel lblFechaPostulacion = new JLabel("Fecha Postulacion: ");
		GridBagConstraints gbc_lblFechaPostulacion = new GridBagConstraints();
		gbc_lblFechaPostulacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaPostulacion.gridx = 1;
		gbc_lblFechaPostulacion.gridy = 15;
		getContentPane().add(lblFechaPostulacion, gbc_lblFechaPostulacion);

		dateChooserFechaPostulacion = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		Date fecha_actual = new Date();
		dateChooserFechaPostulacion.setDate(fecha_actual);

		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 15;
		getContentPane().add(dateChooserFechaPostulacion, gbc_dateChooser);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				limpiarFormulario();
				setVisible(false);
			}
		});

		btnPostular = new JButton("Postular");
		btnPostular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdPostularActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnPostular = new GridBagConstraints();
		gbc_btnPostular.insets = new Insets(0, 0, 0, 5);
		gbc_btnPostular.gridx = 2;
		gbc_btnPostular.gridy = 19;
		getContentPane().add(btnPostular, gbc_btnPostular);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 19;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

	private void limpiarFormulario() {

		this.comboBoxEmpresa.removeAllItems();

		limpiarFormularioEmpresa();
		limpiarFormularioOL();
	}

	private void limpiarFormularioEmpresa() {

		// this.comboBoxEmpresa.removeAllItems();
		this.comboBoxOfertasLaborales.removeAllItems();

		limpiarFormularioOL();

	}

	private void limpiarFormularioOL() {

		// this.comboBoxEmpresa.removeAllItems();

		// this.comboBoxOfertasLaborales.removeAllItems();

		textFieldNombre.setText("");
		textPaneDescripcion.setText("");
		textFieldCiudad.setText("");
		textFieldDepartamento.setText("");
		textFieldHorario.setText("");
		textFieldRemuneracion.setText("");
		textFieldFechaAlta.setText("");

		comboBoxPostulante.removeAllItems();
		textFieldCvReducido.setText("");
		textFieldMotivacion.setText("");
		textFieldAdjuntos.setText("");
		dateChooserFechaPostulacion.setDate(new Date());

	}

	protected void cmdCargarOfertasPorEmpresaActionPerformed(ActionEvent arg0) {

		limpiarFormularioEmpresa();

		if (checkFormularioEmpresa()) {

			String empresa_nombre = comboBoxEmpresa.getSelectedItem().toString();

			if (!empresa_nombre.isEmpty()) {

				cargarComboOfertas(empresa_nombre);
			}
		}
	}

	protected void cmdConsultarOfertaLaboralActionPerformed(ActionEvent event) {

		limpiarFormularioOL();

		if (checkFormularioOL()) {

			// Obtengo datos de los controles Swing

			String oferta_laboral_nombre = comboBoxOfertasLaborales.getSelectedItem().toString();

			cargarDataOfertaLaboral(oferta_laboral_nombre);
			cargarComboPostulantes();

			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
		}
	}

	protected void cmdPostularActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String postulacion_cv_reducido = this.textFieldCvReducido.getText();
			String postulacion_motivacion = this.textFieldMotivacion.getText();
			String postulacion_adjuntos = this.textFieldAdjuntos.getText();
			Date postulacion_fecha = this.dateChooserFechaPostulacion.getDate();

			DTPostulante data_postulante = (DTPostulante) comboBoxPostulante.getSelectedItem();
			DTOfertaLaboral data_oferta_laboral = (DTOfertaLaboral) comboBoxOfertasLaborales.getSelectedItem();

			DTPostulanteOfertaLaboral dt_postulacion = new DTPostulanteOfertaLaboral(postulacion_fecha,
					postulacion_cv_reducido, postulacion_motivacion, postulacion_adjuntos, data_postulante,
					data_oferta_laboral);

			try {
				controlU.registrarPostulacionAOfertaLaboral(dt_postulacion);

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "La postulación se ha realizado con éxito", "Postulación",
						JOptionPane.INFORMATION_MESSAGE);

				limpiarFormulario();
				setVisible(false);

			} catch (ObjetoNoExisteException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Postulación", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormularioEmpresa() {

		if (this.comboBoxEmpresa.getSelectedIndex() == -1) {
			return false;
		}

		String empresa_nombre = this.comboBoxEmpresa.getSelectedItem().toString();

		if (empresa_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa para hacer la postulación", "Postulación",
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
			JOptionPane.showMessageDialog(this, "Debe seleccionar una oferta laboral para postular", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		if (this.comboBoxEmpresa.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una Empresa", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (this.comboBoxOfertasLaborales.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una Oferta Laboral", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (this.comboBoxPostulante.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un Postulante", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String empresa_nombre = comboBoxEmpresa.getSelectedItem().toString();
		String oferta_nombre = comboBoxOfertasLaborales.getSelectedItem().toString();
		String postulante_nickname = comboBoxPostulante.getSelectedItem().toString();

		String postulacion_cv_reducido = this.textFieldCvReducido.getText();
		String postulacion_motivacion = this.textFieldMotivacion.getText();
		String postulacion_adjuntos = this.textFieldAdjuntos.getText();
		Date postulacion_fecha = this.dateChooserFechaPostulacion.getDate();

		if (empresa_nombre.isEmpty() || oferta_nombre.isEmpty() || postulante_nickname.isEmpty()
				|| postulacion_cv_reducido.isEmpty() || postulacion_motivacion.isEmpty()
		// ||postulacion_adjuntos.isEmpty()//No es obligatorio
		) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (postulacion_fecha == null) {
			JOptionPane.showMessageDialog(this, "El campo fecha no puede quedar vacío o incorrecto", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (controlU.existePostulacionAOferta(postulante_nickname, oferta_nombre)) {
			JOptionPane.showMessageDialog(this, "Ya existe una postulación para ese postulante", "Postulación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;

	}

	public void cargarComboOfertas(String empresa_nickname) {
		DefaultComboBoxModel<DTOfertaLaboral> modelOL;
		try {
			modelOL = new DefaultComboBoxModel<DTOfertaLaboral>(
					controlU.listarOfertasLaboralesVigentesConfirmadasPorEmpresa(empresa_nickname));

			this.comboBoxOfertasLaborales.setModel(modelOL);

			this.comboBoxOfertasLaborales.insertItemAt(new DTOfertaLaboral("", "", "", "", "", (float) 0, new Date(), new Date(), closable, null, null), 0);
			this.comboBoxOfertasLaborales.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

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

	public void cargarDataOfertaLaboral(String oferta_nombre) {
		try {

			DTOfertaLaboral dt_ofertaLaboral = controlU.getDataOfertaLaboral(oferta_nombre);

			textFieldNombre.setText(dt_ofertaLaboral.getNombre());
			textPaneDescripcion.setText(dt_ofertaLaboral.getDescripcion());
			textFieldCiudad.setText(dt_ofertaLaboral.getCiudad());
			textFieldDepartamento.setText(dt_ofertaLaboral.getDepartamento());
			textFieldHorario.setText(dt_ofertaLaboral.getHorario());
			textFieldRemuneracion.setText(dt_ofertaLaboral.getRemuneracion().toString());
			textFieldFechaAlta.setText(dt_ofertaLaboral.getFechaAltaFormat().toString());

		} catch (ObjetoNoExisteException e) {
			// Muestro error de registro
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Postulante", JOptionPane.ERROR_MESSAGE);
		}
	}

	public void cargarComboPostulantes() {

		DefaultComboBoxModel<DTPostulante> modelPostulante;

		try {

			modelPostulante = new DefaultComboBoxModel<DTPostulante>(controlU.listarPostulantes());
			this.comboBoxPostulante.setModel(modelPostulante);

			this.comboBoxPostulante.insertItemAt(new DTPostulante("", "", "", "", "", "", EnumRol.POSTULANTE, null, ""),
					0);
			this.comboBoxPostulante.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}
	}

}
