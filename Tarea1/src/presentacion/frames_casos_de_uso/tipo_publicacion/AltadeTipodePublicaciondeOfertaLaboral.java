package presentacion.frames_casos_de_uso.tipo_publicacion;

import javax.swing.JInternalFrame;
import java.util.Date;

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

import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AltadeTipodePublicaciondeOfertaLaboral extends JInternalFrame {

	private IControladorTipoPublicacion controlTP;
	private JLabel lblNombre;
	private JTextField textFieldNombre;
	private JLabel lblDescripcion;
	private JLabel lblExposicion;
	private JTextField textFieldExposicion;
	private JTextField textFieldDuracion;
	private JLabel lblDuracion;
	private JLabel lblCosto;
	private JTextField textFieldCosto;
	private JLabel lblFechaAlta;
	// private JTextField textFieldFechaAlta;
	private JDateChooser dateChooserFechaAlta;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public AltadeTipodePublicaciondeOfertaLaboral(IControladorTipoPublicacion ICU) {
		setVisible(true);

		controlTP = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta Tipo de Publicación");
		setBounds(10, 40, 448, 320);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 57, 114, 134, 54, 58, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 22, 30, 30, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
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

		lblDuracion = new JLabel("Duración (días):");
		lblDuracion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDuracion = new GridBagConstraints();
		gbc_lblDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDuracion.anchor = GridBagConstraints.WEST;
		gbc_lblDuracion.gridx = 1;
		gbc_lblDuracion.gridy = 4;
		getContentPane().add(lblDuracion, gbc_lblDuracion);

		textFieldDuracion = new JTextField();
		textFieldDuracion.setColumns(10);
		GridBagConstraints gbc_textFieldDuracion = new GridBagConstraints();
		gbc_textFieldDuracion.gridwidth = 2;
		gbc_textFieldDuracion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDuracion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDuracion.gridx = 2;
		gbc_textFieldDuracion.gridy = 4;
		getContentPane().add(textFieldDuracion, gbc_textFieldDuracion);

		lblExposicion = new JLabel("Exposición:");
		lblExposicion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblExposicion = new GridBagConstraints();
		gbc_lblExposicion.anchor = GridBagConstraints.WEST;
		gbc_lblExposicion.insets = new Insets(0, 0, 5, 5);
		gbc_lblExposicion.gridx = 1;
		gbc_lblExposicion.gridy = 5;
		getContentPane().add(lblExposicion, gbc_lblExposicion);

		textFieldExposicion = new JTextField();
		textFieldExposicion.setColumns(10);
		GridBagConstraints gbc_textFieldExposicion = new GridBagConstraints();
		gbc_textFieldExposicion.gridwidth = 2;
		gbc_textFieldExposicion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldExposicion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldExposicion.gridx = 2;
		gbc_textFieldExposicion.gridy = 5;
		getContentPane().add(textFieldExposicion, gbc_textFieldExposicion);

		lblCosto = new JLabel("Costo:");
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.anchor = GridBagConstraints.WEST;
		gbc_lblCosto.gridx = 1;
		gbc_lblCosto.gridy = 6;
		getContentPane().add(lblCosto, gbc_lblCosto);

		textFieldCosto = new JTextField();
		textFieldCosto.setColumns(10);
		GridBagConstraints gbc_textFieldCosto = new GridBagConstraints();
		gbc_textFieldCosto.gridwidth = 2;
		gbc_textFieldCosto.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCosto.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCosto.gridx = 2;
		gbc_textFieldCosto.gridy = 6;
		getContentPane().add(textFieldCosto, gbc_textFieldCosto);

		lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.anchor = GridBagConstraints.WEST;
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 1;
		gbc_lblFechaAlta.gridy = 7;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);

		/*
		 * textFieldFechaAlta = new JTextField(); textFieldFechaAlta.setColumns(10);
		 * GridBagConstraints gbc_textFieldFechaAlta = new GridBagConstraints();
		 * gbc_textFieldFechaAlta.gridwidth = 2; gbc_textFieldFechaAlta.insets = new
		 * Insets(0, 0, 5, 5); gbc_textFieldFechaAlta.fill =
		 * GridBagConstraints.HORIZONTAL; gbc_textFieldFechaAlta.gridx = 2;
		 * gbc_textFieldFechaAlta.gridy = 7; getContentPane().add(textFieldFechaAlta,
		 * gbc_textFieldFechaAlta);
		 */

		dateChooserFechaAlta = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		Date fecha_actual = new Date();
		dateChooserFechaAlta.setDate(fecha_actual);

		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 2;
		gbc_dateChooser.gridy = 7;
		getContentPane().add(dateChooserFechaAlta, gbc_dateChooser);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdCrearTipoPublicacionActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 9;
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
		gbc_btnCancelar.gridy = 9;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarFormulario() {
		textFieldNombre.setText("");
		textAreaDescripcion.setText("");
		textFieldDuracion.setText("");
		textFieldExposicion.setText("");
		textFieldCosto.setText("");
		// textFieldFechaAlta.setText("");
		dateChooserFechaAlta.setDate(new Date());
	}

	protected void cmdCrearTipoPublicacionActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String tipo_publicacion_nombre = this.textFieldNombre.getText();
			String tipo_publicacion_descripcion = this.textAreaDescripcion.getText();
			Integer tipo_publicacion_duracion = Integer.parseInt(this.textFieldDuracion.getText());
			Integer tipo_publicacion_exposicion = Integer.parseInt(this.textFieldExposicion.getText());
			Float tipo_publicacion_costo = Float.parseFloat(this.textFieldCosto.getText());
			// Date tipo_publicacion_fecha_alta =
			// Integer.parseInt(this.textFieldFechaAlta.getText());
			// Date tipo_publicacion_fecha_alta = new Date();
			Date tipo_publicacion_fecha_alta = this.dateChooserFechaAlta.getDate();

			try {
				DTTipoPublicacion dt_tipo_publicacion = new DTTipoPublicacion(tipo_publicacion_nombre,
						tipo_publicacion_descripcion, tipo_publicacion_duracion, tipo_publicacion_exposicion,
						tipo_publicacion_costo, tipo_publicacion_fecha_alta);

				controlTP.ingresarTipoPublicacion(dt_tipo_publicacion);

				// Muestro éxito de la operación
				JOptionPane.showMessageDialog(this, "El tipo publicación se ha registrado con éxito",
						"Alta Tipo de Publicación", JOptionPane.INFORMATION_MESSAGE);

				// Limpio el internal frame antes de cerrar la ventana
				limpiarFormulario();
				setVisible(false);
			} catch (ObjetoRepetidoException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Alta Tipo de Publicación",
						JOptionPane.ERROR_MESSAGE);
			} catch (ObjetoNoRespetaFormatoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		String tipo_publicacion_nombre = this.textFieldNombre.getText();
		String tipo_publicacion_descripcion = this.textAreaDescripcion.getText();
		String tipo_publicacion_duracion = this.textFieldDuracion.getText();
		String tipo_publicacion_exposicion = this.textFieldExposicion.getText();
		String tipo_publicacion_costo = this.textFieldCosto.getText();
		// Date tipo_publicacion_fecha_alta = new Date();
		Date tipo_publicacion_fecha_alta = this.dateChooserFechaAlta.getDate();

		if (tipo_publicacion_nombre.isEmpty() || tipo_publicacion_descripcion.isEmpty()
				|| tipo_publicacion_duracion.isEmpty() || tipo_publicacion_exposicion.isEmpty()
				|| tipo_publicacion_costo.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Alta Tipo de Publicación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (tipo_publicacion_fecha_alta == null) {
			JOptionPane.showMessageDialog(this, "El campo fecha no puede quedar vacío o incorrecto",
					"Alta Tipo de Publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(tipo_publicacion_duracion);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La duración debe ser un número entero", "Alta Tipo de Publicación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Integer.parseInt(tipo_publicacion_exposicion);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "La exposición debe ser un número entero", "Alta Tipo de Publicación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		try {
			Float tipoPublicacionCostoFloat = Float.parseFloat(tipo_publicacion_costo);
			if (tipoPublicacionCostoFloat < 0) {
				JOptionPane.showMessageDialog(this, "El costo debe ser un número positivo", "Alta Tipo de Publicación",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "El costo debe ser un número decimal", "Alta Tipo de Publicación",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (controlTP.existeTipoPublicacion(tipo_publicacion_nombre)) {
			JOptionPane.showMessageDialog(this, "Ya existe un tipo de publicación con ese nombre",
					"Alta Tipo de Publicación", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}
