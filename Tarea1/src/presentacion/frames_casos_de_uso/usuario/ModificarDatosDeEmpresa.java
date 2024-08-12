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

import logica.interfaces.IControladorUsuario;
import logica.dts.DTEmpresa;
import logica.clases.EnumRol;

import excepciones.ObjetoNoExisteException;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
//import excepciones.ObjetoRepetidoException;

@SuppressWarnings("serial")
public class ModificarDatosDeEmpresa extends JInternalFrame {

	private IControladorUsuario controlU;
	private JTextField textFieldNickname;
	private JLabel lblNickname;
	private JTextField textFieldNombre;
	private JTextField textFieldApellido;
	private JTextField textFieldCorreo;
	private JTextField textFieldNombreEmpresa;
	private JComboBox<DTEmpresa> comboBoxEmpresa;
	private JLabel lblLinkweb;
	private JTextField textFieldLinkWeb;

	private EnumRol rol = EnumRol.EMPRESA;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public ModificarDatosDeEmpresa(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Modificar datos de Empresa");
		setBounds(10, 40, 496, 344);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 57, 114, 134, 54, 58, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 22, 30, 30, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblEmpresa = new JLabel("Empresa");
		GridBagConstraints gbc_lblEmpresa = new GridBagConstraints();
		gbc_lblEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmpresa.gridx = 1;
		gbc_lblEmpresa.gridy = 1;
		gbc_lblEmpresa.anchor = GridBagConstraints.WEST;
		getContentPane().add(lblEmpresa, gbc_lblEmpresa);

		comboBoxEmpresa = new JComboBox<DTEmpresa>();
		this.cargarComboEmpresa();
		comboBoxEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (comboBoxEmpresa.getSelectedIndex() != -1) {
					DTEmpresa dataempresa = (DTEmpresa) comboBoxEmpresa.getSelectedItem();
					cargarDatosEmpresaSeleccionada(dataempresa);
				}
			}
		});

		GridBagConstraints gbc_ComboBoxEmpresa = new GridBagConstraints();
		gbc_ComboBoxEmpresa.gridwidth = 2;
		gbc_ComboBoxEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_ComboBoxEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_ComboBoxEmpresa.gridx = 2;
		gbc_ComboBoxEmpresa.gridy = 1;
		getContentPane().add(comboBoxEmpresa, gbc_ComboBoxEmpresa);

		lblNickname = new JLabel("Nickname");
		lblNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNickname = new GridBagConstraints();
		gbc_lblNickname.anchor = GridBagConstraints.WEST;
		gbc_lblNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblNickname.gridx = 1;
		gbc_lblNickname.gridy = 2;
		getContentPane().add(lblNickname, gbc_lblNickname);

		// Una campo de texto (JTextField) para ingresar el nombre del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 2;
		gbc_textFieldNickname.fill = GridBagConstraints.BOTH;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNickname.gridx = 2;
		gbc_textFieldNickname.gridy = 2;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);
		textFieldNickname.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 1;
		gbc_lblNombre.gridy = 3;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textFieldNombre = new JTextField();
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 3;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel lblApellido = new JLabel("Apellido");
		GridBagConstraints gbc_lblApellido = new GridBagConstraints();
		gbc_lblApellido.anchor = GridBagConstraints.WEST;
		gbc_lblApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblApellido.gridx = 1;
		gbc_lblApellido.gridy = 4;
		getContentPane().add(lblApellido, gbc_lblApellido);

		textFieldApellido = new JTextField();
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 2;
		gbc_textFieldApellido.gridy = 4;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);
		textFieldApellido.setColumns(10);

		JLabel lblCorreo = new JLabel("Correo");
		GridBagConstraints gbc_lblCorreo = new GridBagConstraints();
		gbc_lblCorreo.anchor = GridBagConstraints.WEST;
		gbc_lblCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCorreo.gridx = 1;
		gbc_lblCorreo.gridy = 5;
		getContentPane().add(lblCorreo, gbc_lblCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setEditable(false);
		GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
		gbc_textFieldCorreo.gridwidth = 2;
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridx = 2;
		gbc_textFieldCorreo.gridy = 5;
		getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);
		textFieldCorreo.setColumns(10);

		JLabel lblNombreEmpresa = new JLabel("NombreEmpresa");
		GridBagConstraints gbc_lblNombreEmpresa = new GridBagConstraints();
		gbc_lblNombreEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblNombreEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreEmpresa.gridx = 1;
		gbc_lblNombreEmpresa.gridy = 6;
		getContentPane().add(lblNombreEmpresa, gbc_lblNombreEmpresa);

		textFieldNombreEmpresa = new JTextField();
		GridBagConstraints gbc_textFieldNombreEmpresa = new GridBagConstraints();
		gbc_textFieldNombreEmpresa.gridwidth = 2;
		gbc_textFieldNombreEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreEmpresa.gridx = 2;
		gbc_textFieldNombreEmpresa.gridy = 6;
		getContentPane().add(textFieldNombreEmpresa, gbc_textFieldNombreEmpresa);
		textFieldNombreEmpresa.setColumns(10);

		JLabel lblDescripcion = new JLabel("Descripcion");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 7;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 7;
		getContentPane().add(scrollPane, gbc_scrollPane);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		scrollPane.setViewportView(textAreaDescripcion);

		lblLinkweb = new JLabel("Sitio web");
		GridBagConstraints gbc_lblLinkweb = new GridBagConstraints();
		gbc_lblLinkweb.anchor = GridBagConstraints.WEST;
		gbc_lblLinkweb.insets = new Insets(0, 0, 5, 5);
		gbc_lblLinkweb.gridx = 1;
		gbc_lblLinkweb.gridy = 8;
		getContentPane().add(lblLinkweb, gbc_lblLinkweb);

		textFieldLinkWeb = new JTextField();
		GridBagConstraints gbc_textFieldLinkWeb = new GridBagConstraints();
		gbc_textFieldLinkWeb.gridwidth = 2;
		gbc_textFieldLinkWeb.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldLinkWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLinkWeb.gridx = 2;
		gbc_textFieldLinkWeb.gridy = 8;
		getContentPane().add(textFieldLinkWeb, gbc_textFieldLinkWeb);
		textFieldLinkWeb.setColumns(10);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdModificarDatosEmpresaActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
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
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 3;
		gbc_btnCancelar.gridy = 10;
		getContentPane().add(btnCancelar, gbc_btnCancelar);

	}

	private void limpiarFormulario() {
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCorreo.setText("");
		textFieldNombreEmpresa.setText("");
		textAreaDescripcion.setText("");
		textFieldLinkWeb.setText("");

		this.comboBoxEmpresa.removeAllItems();

	}

	protected void cmdModificarDatosEmpresaActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			String nickname = this.textFieldNickname.getText();
			String nombre = this.textFieldNombre.getText();
			String apellido = this.textFieldApellido.getText();
			// String correo = this.textFieldCorreo.getText();
			String nombreEmpresa = this.textFieldNombreEmpresa.getText();
			String descripcion = this.textAreaDescripcion.getText();
			String sitioweb = this.textFieldLinkWeb.getText();

			try {
				controlU.editarEmpresa(nickname, nombre, apellido, nombreEmpresa, descripcion, sitioweb, "", "");
			} catch (ObjetoNoExisteException e) {
				e.printStackTrace();
			}

			// Muestro éxito de la operación
			JOptionPane.showMessageDialog(this, "Los datos de la empresa se han modificado con exito",
					"Modificar datos Empresa", JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
			limpiarFormulario();
			setVisible(false);
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		if (this.comboBoxEmpresa.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una empresa para modificar",
					"Modificar datos Empresa", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		String nickname = this.textFieldNickname.getText();
		String nombre = this.textFieldNombre.getText();
		String apellido = this.textFieldApellido.getText();
		String correo = this.textFieldCorreo.getText();
		String nombreEmpresa = this.textFieldNombreEmpresa.getText();
		String descripcion = this.textAreaDescripcion.getText();
		String sitioweb = this.textFieldLinkWeb.getText();

		if (nickname.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || correo.isEmpty()
				|| nombreEmpresa.isEmpty() || descripcion.isEmpty() || sitioweb.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Modificar datos Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	public void cargarComboEmpresa() {
		DefaultComboBoxModel<DTEmpresa> modelEmpresa;
		try {

			modelEmpresa = new DefaultComboBoxModel<DTEmpresa>(controlU.listarEmpresas());

			this.comboBoxEmpresa.setModel(modelEmpresa);
			this.comboBoxEmpresa.insertItemAt(new DTEmpresa("", "", "", "", "", "", this.rol, "", "", ""), 0);
			this.comboBoxEmpresa.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	public void cargarDatosEmpresaSeleccionada(DTEmpresa dtempresa) {

		String nickname = dtempresa.getNickname();
		String nombre = dtempresa.getNombre();
		String apellido = dtempresa.getApellido();
		String correo = dtempresa.getCorreo();
		String nombreEmpresa = dtempresa.getNombreEmpresa();
		String descripcion = dtempresa.getDescripcion();
		String sitioweb = dtempresa.getSitioWeb();

		textFieldNickname.setText(nickname);
		textFieldNombre.setText(nombre);
		textFieldApellido.setText(apellido);
		textFieldCorreo.setText(correo);
		textFieldNombreEmpresa.setText(nombreEmpresa);
		textAreaDescripcion.setText(descripcion);
		textFieldLinkWeb.setText(sitioweb);

	}

}
