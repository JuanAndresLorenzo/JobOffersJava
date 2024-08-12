package presentacion.frames_casos_de_uso.tipo_publicacion;

import javax.swing.JInternalFrame;

import logica.dts.DTPaquete;
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

import com.toedter.calendar.JDateChooser;

import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class CrearPaquetedeTiposdePublicaciondeOfertasLaborales extends JInternalFrame {

	private IControladorTipoPublicacion controlTP;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDescripcion;
	private JLabel lblValidezDias;
	private JTextField textFieldValidezDias;
	private JLabel lblDescuento;
	private JTextField textFieldDescuento;
	private JLabel lblFechaAlta;
	private JDateChooser dateChooserFechaAlta;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public CrearPaquetedeTiposdePublicaciondeOfertasLaborales(IControladorTipoPublicacion ICU) {
		setVisible(true);

		controlTP = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Creación Paquete");
		setBounds(10, 40, 448, 317);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 57, 114, 134, 54, 58, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 22, 30, 30, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 2;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 3;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 3;
		getContentPane().add(scrollPane, gbc_scrollPane);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		scrollPane.setViewportView(textAreaDescripcion);

		lblValidezDias = new JLabel("Validez (dias):");
		lblValidezDias.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblValidezDias = new GridBagConstraints();
		gbc_lblValidezDias.anchor = GridBagConstraints.WEST;
		gbc_lblValidezDias.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidezDias.gridx = 1;
		gbc_lblValidezDias.gridy = 4;
		getContentPane().add(lblValidezDias, gbc_lblValidezDias);

		textFieldValidezDias = new JTextField();
		textFieldValidezDias.setColumns(10);
		GridBagConstraints gbc_textFieldValidezDias = new GridBagConstraints();
		gbc_textFieldValidezDias.gridwidth = 2;
		gbc_textFieldValidezDias.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldValidezDias.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValidezDias.gridx = 2;
		gbc_textFieldValidezDias.gridy = 4;
		getContentPane().add(textFieldValidezDias, gbc_textFieldValidezDias);

		lblDescuento = new JLabel("Descuento:");
		lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.anchor = GridBagConstraints.WEST;
		gbc_lblDescuento.gridx = 1;
		gbc_lblDescuento.gridy = 5;
		getContentPane().add(lblDescuento, gbc_lblDescuento);

		textFieldDescuento = new JTextField();
		textFieldDescuento.setColumns(10);
		GridBagConstraints gbc_textFieldDescuento = new GridBagConstraints();
		gbc_textFieldDescuento.gridwidth = 2;
		gbc_textFieldDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescuento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescuento.gridx = 2;
		gbc_textFieldDescuento.gridy = 5;
		getContentPane().add(textFieldDescuento, gbc_textFieldDescuento);

		lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 6;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		dateChooserFechaAlta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		Date fecha_actual = new Date();
		dateChooserFechaAlta.setDate(fecha_actual);

		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 6;
		getContentPane().add(dateChooserFechaAlta, gbc_dateChooser);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdCrearPaqueteActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 8;
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
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 8;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldValidezDias.setText("");
		textFieldDescuento.setText("");
		dateChooserFechaAlta.setDate(new Date());
	}

	protected void cmdCrearPaqueteActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String paquete_nombre = this.textFieldNombre.getText();
			String paquete_descripcion = this.textAreaDescripcion.getText();
			Integer paquete_validez = Integer.parseInt(this.textFieldValidezDias.getText());
			Float paquete_descuento = Float.parseFloat(this.textFieldDescuento.getText());
			Date paquete_fecha_alta = this.dateChooserFechaAlta.getDate();

			try {
				DTPaquete dt_paquete = new DTPaquete(paquete_nombre, paquete_descripcion, paquete_validez,
						paquete_descuento, paquete_fecha_alta);

				controlTP.ingresarPaquete(dt_paquete);

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "El paquete se ha registrado con éxito", "Creación Paquete",
						JOptionPane.INFORMATION_MESSAGE);

				// Limpio el internal frame antes de cerrar la ventana
				limpiarFormulario();
				setVisible(false);
			} catch (ObjetoRepetidoException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Creación Paquete", JOptionPane.ERROR_MESSAGE);
			} catch (ObjetoNoRespetaFormatoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		String paquete_nombre = this.textFieldNombre.getText();
		String paquete_descripcion = this.textAreaDescripcion.getText();
		String paquete_validez = this.textFieldValidezDias.getText();
		String paquete_descuento = this.textFieldDescuento.getText();
		Date paquete_fecha_alta = this.dateChooserFechaAlta.getDate();

		if (paquete_nombre.isEmpty() || paquete_descripcion.isEmpty() || paquete_validez.isEmpty()
				|| paquete_descuento.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Crear Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(paquete_validez);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La validéz en días debe ser un número entero", "Crear Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Float paqueteDescuentoFloat = Float.parseFloat(paquete_descuento);
			if (paqueteDescuentoFloat < 0 || paqueteDescuentoFloat > 100) {
				JOptionPane.showMessageDialog(this, "El descuento debe estar en un rango del 0 al 100", "Crear Paquete",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El descuento debe ser un número decimal", "Crear Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (paquete_fecha_alta == null) {
			JOptionPane.showMessageDialog(this, "El campo fecha no puede quedar vacío o incorrecto", "Crear Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (controlTP.existePaquete(paquete_nombre)) {
			JOptionPane.showMessageDialog(this, "Ya existe un paquete con ese nombre", "Crear Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}
