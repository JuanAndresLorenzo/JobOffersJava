package presentacion.frames_casos_de_uso.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Date;
import com.toedter.calendar.JDateChooser;

import logica.interfaces.IControladorUsuario;
import logica.dts.DTPostulante;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;
//import excepciones.ObjetoRepetidoException;

@SuppressWarnings("serial")
public class ModificarDatosDePostulante extends JInternalFrame {

	private IControladorUsuario controlU;
	private JTextField textFieldNickname;
	private JLabel lblNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCorreo;
	//private JTextField textFieldFechaNacimiento;
	private JDateChooser dateChooserFechaNacimiento;
	private JTextField textFieldNacionalidad;
	private JComboBox<DTPostulante> comboBoxPostulante;
	
	private EnumRol rol = EnumRol.POSTULANTE;

	// Create the frame
	public ModificarDatosDePostulante(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Modificar datos de Postulante");
		setBounds(10, 40, 523, 327);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblPostulante = new JLabel("Postulante");
		GridBagConstraints gbc_lblPostulante = new GridBagConstraints();
		gbc_lblPostulante.insets = new Insets(0, 0, 5, 5);
		gbc_lblPostulante.gridx = 0;
		gbc_lblPostulante.gridy = 3;
		getContentPane().add(lblPostulante, gbc_lblPostulante);

		comboBoxPostulante = new JComboBox<DTPostulante>();
		this.cargarComboPostulante();
		comboBoxPostulante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBoxPostulante.getSelectedIndex() != -1) {
					DTPostulante data_postulante = (DTPostulante) comboBoxPostulante.getSelectedItem();
					cargarDatosPostulanteSeleccionado(data_postulante);
				}
			}
		});

		GridBagConstraints gbc_comboBoxPostulante = new GridBagConstraints();
		gbc_comboBoxPostulante.gridwidth = 2;
		gbc_comboBoxPostulante.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPostulante.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPostulante.gridx = 1;
		gbc_comboBoxPostulante.gridy = 3;
		getContentPane().add(comboBoxPostulante, gbc_comboBoxPostulante);

		lblNickname = new JLabel("Nickname");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.fill = GridBagConstraints.VERTICAL;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 0;
		gbc_lblNickname.gridy = 4;
		getContentPane().add(lblNickname, gbc_lblNickname);

		// Una campo de texto (JTextField) para ingresar el nombre del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 2;
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNickname.gridx = 1;
		gbc_textFieldNickname.gridy = 4;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);
		textFieldNickname.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 1;
		gbc_textFieldNombre.gridy = 5;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 0;
		gbc_lblApellido.gridy = 6;
		getContentPane().add(lblApellido, gbc_lblApellido);

		textFieldApellido = new JTextField();
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 1;
		gbc_textFieldApellido.gridy = 6;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 0;
		gbc_lblCorreo.gridy = 7;
		getContentPane().add(lblCorreo, gbc_lblCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setEditable(false);
		GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
		gbc_textFieldCorreo.gridwidth = 2;
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridx = 1;
		gbc_textFieldCorreo.gridy = 7;
		getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);
		textFieldCorreo.setColumns(10);

		JLabel lblFechanacimiento = new JLabel("FechaNacimiento");
		GridBagConstraints gbc_lblFechanacimiento = new GridBagConstraints();
		gbc_lblFechanacimiento.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechanacimiento.gridx = 0;
		gbc_lblFechanacimiento.gridy = 8;
		getContentPane().add(lblFechanacimiento, gbc_lblFechanacimiento);

		/*
		textFieldFechaNacimiento = new JTextField();
		GridBagConstraints gbc_textFieldFechaNacimiento = new GridBagConstraints();
		gbc_textFieldFechaNacimiento.gridwidth = 2;
		gbc_textFieldFechaNacimiento.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldFechaNacimiento.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFechaNacimiento.gridx = 1;
		gbc_textFieldFechaNacimiento.gridy = 8;
		getContentPane().add(textFieldFechaNacimiento, gbc_textFieldFechaNacimiento);
		textFieldFechaNacimiento.setColumns(10);
		*/
		dateChooserFechaNacimiento = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
		Date fecha_actual = null;
		dateChooserFechaNacimiento.setDate(fecha_actual);

		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.gridwidth = 2;
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 1;
		gbc_dateChooser.gridy = 8;
		getContentPane().add(dateChooserFechaNacimiento, gbc_dateChooser);

		JLabel lblNacionalidad = new JLabel("Nacionalidad");
		GridBagConstraints gbc_lblNacionalidad = new GridBagConstraints();
		gbc_lblNacionalidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblNacionalidad.gridx = 0;
		gbc_lblNacionalidad.gridy = 9;
		getContentPane().add(lblNacionalidad, gbc_lblNacionalidad);

		textFieldNacionalidad = new JTextField();
		textFieldNacionalidad.setText("");
		GridBagConstraints gbc_textFieldNacionalidad = new GridBagConstraints();
		gbc_textFieldNacionalidad.gridwidth = 2;
		gbc_textFieldNacionalidad.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldNacionalidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNacionalidad.gridx = 1;
		gbc_textFieldNacionalidad.gridy = 9;
		getContentPane().add(textFieldNacionalidad, gbc_textFieldNacionalidad);
		textFieldNacionalidad.setColumns(10);

		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdModificarDatosPostulanteActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 10;
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
		gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 2;
		gbc_btnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarFormulario() {
		
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCorreo.setText("");
		//textFieldFechaNacimiento.setText("");
		dateChooserFechaNacimiento.setDate(null);
		textFieldNacionalidad.setText("");
		
		this.comboBoxPostulante.removeAllItems();

	}

	protected void cmdModificarDatosPostulanteActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {
			
			String nickname = this.textFieldNickname.getText();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
			//String correo = this.textFieldCorreo.getText();
			// String fechaNacimiento = this.textFieldFechaNacimiento.getText();
			Date fechaNacimiento = this.dateChooserFechaNacimiento.getDate();
			String nacionalidad = this.textFieldNacionalidad.getText();

			try {
				controlU.editarPostulante(nickname, nombre, apellido, fechaNacimiento, nacionalidad, "", "");
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
			}

			// Muestro éxito de la operación
			JOptionPane.showMessageDialog(this, "Los datos del postulante se han modificado con exito",
					"Modificar datos postulante", JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
			limpiarFormulario();
			setVisible(false);
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {
		
		if (this.comboBoxPostulante.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un postulante para modificar", "Modificar datos Postulante",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		String nickname = this.textFieldNickname.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String correo = this.textFieldCorreo.getText();
		// String fechaNacimiento = this.textFieldFechaNacimiento.getText();
		Date fechaNacimiento = this.dateChooserFechaNacimiento.getDate();
		String nacionalidad = this.textFieldNacionalidad.getText();

		if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || correo.isEmpty() || nacionalidad.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar datos Postulante",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		
		if (fechaNacimiento == null) {
			JOptionPane.showMessageDialog(this, "El campo fecha no puede quedar vacío o incorrecto", "Modificar datos Postulante",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		

		return true;
	}
	
	public void cargarComboPostulante() {
		DefaultComboBoxModel<DTPostulante> modelPostulante;
		try {
			
			modelPostulante = new DefaultComboBoxModel<DTPostulante>(controlU.listarPostulantes());

			this.comboBoxPostulante.setModel(modelPostulante);
						
			this.comboBoxPostulante.insertItemAt(new DTPostulante("", "", "", "", "", "", this.rol, null, ""), 0);
			this.comboBoxPostulante.setSelectedIndex(-1);
			
		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarDatosPostulanteSeleccionado(DTPostulante dt_postulante) {

		String nickname = dt_postulante.getNickname();
		String nombre = dt_postulante.getNombre();
		String apellido = dt_postulante.getApellido();
		String correo = dt_postulante.getCorreo();
		// String nacimiento = dt_postulante.getFechaNacimiento();
		Date fechaNacimiento = dt_postulante.getFechaNacimiento();
		String nacionalidad = dt_postulante.getNacionalidad();

		textFieldNickname.setText(nickname);
		textFieldNombre.setText(nombre);
		textFieldApellido.setText(apellido);
		textFieldCorreo.setText(correo);
		dateChooserFechaNacimiento.setDate(fechaNacimiento);
		// textFieldFechaNacimiento.setText(nacimiento);
		textFieldNacionalidad.setText(nacionalidad);

	}

}
