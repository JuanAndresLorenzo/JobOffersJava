package presentacion.frames_casos_de_uso.tipo_publicacion;

import javax.swing.JInternalFrame;

import logica.dts.DTPaquete;
import logica.dts.DTTipoPublicacion;

import logica.interfaces.IControladorTipoPublicacion;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.SwingConstants;

import excepciones.ObjetoNoExisteException;
//import excepciones.ObjetoRepetidoException;

import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JComboBox;

public class AgregarTipodePublicaciondeOfertaLaboralaPaquete extends JInternalFrame {

	private IControladorTipoPublicacion controlTP;
	private JLabel lblPaquete;
	private JComboBox<DTPaquete> comboBoxPaquetes;
	private JComboBox<DTTipoPublicacion> comboBoxTiposPublicaciones;
	private JTextField textFieldCantidad;

	// Create the frame
	public AgregarTipodePublicaciondeOfertaLaboralaPaquete(IControladorTipoPublicacion ICTP) {

		controlTP = ICTP;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Agregar Tipo de Publicación a Paquete");
		setBounds(10, 40, 448, 320);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 57, 114, 134, 54, 58, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 22, 30, 30, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblPaquete = new JLabel("Paquete:");
		lblPaquete.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPaquete = new GridBagConstraints();
		gbc_lblPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquete.anchor = GridBagConstraints.WEST;
		gbc_lblPaquete.gridx = 1;
		gbc_lblPaquete.gridy = 2;
		getContentPane().add(lblPaquete, gbc_lblPaquete);

		comboBoxPaquetes = new JComboBox<DTPaquete>();
		this.cargarComboBoxPaquetes();

		GridBagConstraints gbc_comboBoxPaquetes = new GridBagConstraints();
		gbc_comboBoxPaquetes.gridwidth = 2;
		gbc_comboBoxPaquetes.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPaquetes.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPaquetes.gridx = 2;
		gbc_comboBoxPaquetes.gridy = 2;
		getContentPane().add(comboBoxPaquetes, gbc_comboBoxPaquetes);

		JLabel lblTipoDePublicacin = new JLabel("Tipo de publicación:");
		lblTipoDePublicacin.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTipoDePublicacin = new GridBagConstraints();
		gbc_lblTipoDePublicacin.anchor = GridBagConstraints.EAST;
		gbc_lblTipoDePublicacin.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoDePublicacin.gridx = 1;
		gbc_lblTipoDePublicacin.gridy = 3;
		getContentPane().add(lblTipoDePublicacin, gbc_lblTipoDePublicacin);

		comboBoxTiposPublicaciones = new JComboBox<DTTipoPublicacion>();
		this.cargarComboBoxTiposPublicaciones();

		GridBagConstraints gbc_comboBoxTiposPublicaciones = new GridBagConstraints();
		gbc_comboBoxTiposPublicaciones.gridwidth = 2;
		gbc_comboBoxTiposPublicaciones.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTiposPublicaciones.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTiposPublicaciones.gridx = 2;
		gbc_comboBoxTiposPublicaciones.gridy = 3;
		getContentPane().add(comboBoxTiposPublicaciones, gbc_comboBoxTiposPublicaciones);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.anchor = GridBagConstraints.WEST;
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.gridx = 1;
		gbc_lblCantidad.gridy = 4;
		getContentPane().add(lblCantidad, gbc_lblCantidad);

		textFieldCantidad = new JTextField();
		GridBagConstraints gbc_textFieldCantidad = new GridBagConstraints();
		gbc_textFieldCantidad.gridwidth = 2;
		gbc_textFieldCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidad.gridx = 2;
		gbc_textFieldCantidad.gridy = 4;
		getContentPane().add(textFieldCantidad, gbc_textFieldCantidad);
		textFieldCantidad.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdAgregarTipoPublicacionAPaqueteActionPerformed(arg0);
			}
		});

		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 6;
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
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarFormulario() {
		this.comboBoxPaquetes.removeAllItems();
		this.comboBoxTiposPublicaciones.removeAllItems();

		this.textFieldCantidad.setText("");
	}

	protected void cmdAgregarTipoPublicacionAPaqueteActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String paquete_nombre = this.comboBoxPaquetes.getSelectedItem().toString();
			String tipo_publicacion_nombre = this.comboBoxTiposPublicaciones.getSelectedItem().toString();
			Integer cantidad = Integer.parseInt(this.textFieldCantidad.getText());

			try {

				controlTP.agregarTipoPublicacionAPaquete(paquete_nombre, tipo_publicacion_nombre, cantidad);

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "El Tipo de Publicación se ha agregado al paquete con éxito.",
						"Agregar Tipo de Publicación a Paquete", JOptionPane.INFORMATION_MESSAGE);

			} catch (ObjetoNoExisteException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Agregar Tipo de Publicación a Paquete",
						JOptionPane.ERROR_MESSAGE);
			}

			// Limpio el internal frame antes de cerrar la ventana
			limpiarFormulario();
			setVisible(false);
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		String cantidad = this.textFieldCantidad.getText();

		if (this.comboBoxPaquetes.getSelectedIndex() == -1 || this.comboBoxTiposPublicaciones.getSelectedIndex() == -1
				|| cantidad.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Tipo de Publicación a Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String paquete_nombre = this.comboBoxPaquetes.getSelectedItem().toString();
		String tipo_publicacion_nombre = this.comboBoxTiposPublicaciones.getSelectedItem().toString();

		if (paquete_nombre.isEmpty() || tipo_publicacion_nombre.isEmpty() || cantidad.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Agregar Tipo de Publicación a Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(cantidad);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero",
					"Agregar Tipo de Publicación a Paquete", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Método que permite cargar un nuevo modelo para el combo con la información
	// actualizada de paquetes, provista por la operación del controlador:
	// listarPaquetes().
	// Se invoca el método desde Principal, antes de hacer visible el JInternalFrame
	public void cargarComboBoxPaquetes() {

		DefaultComboBoxModel<DTPaquete> model;
		try {
			model = new DefaultComboBoxModel<DTPaquete>(controlTP.listarPaquetes());
			this.comboBoxPaquetes.setModel(model);

			this.comboBoxPaquetes.insertItemAt(new DTPaquete("", "", 0, (float) 0, null), 0);
			this.comboBoxPaquetes.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	// Método que permite cargar un nuevo modelo para el combo con la información
	// actualizada de tipos de publicaciones, provista por la operación del
	// controlador: listarTiposPublicaciones().
	// Se invoca el método desde Principal, antes de hacer visible el JInternalFrame
	public void cargarComboBoxTiposPublicaciones() {

		DefaultComboBoxModel<DTTipoPublicacion> model;
		try {
			model = new DefaultComboBoxModel<DTTipoPublicacion>(controlTP.listarTiposPublicaciones());
			this.comboBoxTiposPublicaciones.setModel(model);

			this.comboBoxTiposPublicaciones.insertItemAt(new DTTipoPublicacion("", "", 0, 0, (float) 0, new Date()), 0);
			this.comboBoxTiposPublicaciones.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	/*
	 * Operación para precargar algunos tipos de publicaciones public void
	 * preCargarTiposPublicaciones(Integer cantidad) {
	 * 
	 * try { controlTP.preCargarTiposPublicaciones(cantidad); } catch
	 * (ObjetoRepetidoException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
}
