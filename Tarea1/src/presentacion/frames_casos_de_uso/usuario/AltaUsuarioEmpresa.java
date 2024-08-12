package presentacion.frames_casos_de_uso.usuario;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import logica.interfaces.IControladorUsuario;
import validaciones.Validador;
import logica.dts.DTEmpresa;
import logica.clases.EnumRol;
import excepciones.ObjetoNoRespetaFormatoException;
import excepciones.ObjetoRepetidoException;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class AltaUsuarioEmpresa extends JInternalFrame {

	private IControladorUsuario controlU;
	private JTextField textFieldNickname;
	private JLabel lblIngreseNickname;
	private JLabel lblIngreseNombre;
	private JTextField textFieldNombre;
	private JLabel lblIngreseApellido;
	private JTextField textFieldApellido;
	private JLabel lblIngreseCorreo;
	private JTextField textFieldCorreo;
	private JTextField textFieldNombreEmpresa;
	private JLabel lblIngreseNombreEmpresa;
	private JLabel lblIngreseDescripcion;
	private JLabel lblIngresePaginaWeb;
	private JTextField textFieldPaginaWeb;
	private JPasswordField textFieldClave;
	private JPasswordField textFieldClaveConfirmacion;
	private JLabel lblClave;
	private JLabel lblClaveConfirmacion;
	private JLabel lblImagen;

	private EnumRol rol = EnumRol.EMPRESA;
	private JTextField textFieldArchivoImagen;
	private JButton btnSeleccionarArchivoImagen;
	private JLabel lblImagenVista;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public AltaUsuarioEmpresa(IControladorUsuario ICU) {

		controlU = ICU;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Alta Empresa");
		setBounds(10, 40, 448, 592);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 57, 114, 134, 54, 58, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 22, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblIngreseNickname = new JLabel("Nickname:");
		lblIngreseNickname.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseNickname = new GridBagConstraints();
		gbc_lblIngreseNickname.anchor = GridBagConstraints.WEST;
		gbc_lblIngreseNickname.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseNickname.gridx = 1;
		gbc_lblIngreseNickname.gridy = 1;
		getContentPane().add(lblIngreseNickname, gbc_lblIngreseNickname);

		// Una campo de texto (JTextField) para ingresar el nombre del usuario.
		// Por defecto es posible ingresar cualquier string.
		textFieldNickname = new JTextField();
		GridBagConstraints gbc_textFieldNickname = new GridBagConstraints();
		gbc_textFieldNickname.gridwidth = 2;
		gbc_textFieldNickname.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNickname.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNickname.gridx = 2;
		gbc_textFieldNickname.gridy = 1;
		getContentPane().add(textFieldNickname, gbc_textFieldNickname);
		textFieldNickname.setColumns(10);

		lblIngreseNombre = new JLabel("Nombre:");
		lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
		gbc_lblIngreseNombre.anchor = GridBagConstraints.WEST;
		gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseNombre.gridx = 1;
		gbc_lblIngreseNombre.gridy = 2;
		getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);

		textFieldNombre = new JTextField();
		textFieldNombre.setColumns(10);
		GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
		gbc_textFieldNombre.gridwidth = 2;
		gbc_textFieldNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombre.gridx = 2;
		gbc_textFieldNombre.gridy = 2;
		getContentPane().add(textFieldNombre, gbc_textFieldNombre);

		lblIngreseApellido = new JLabel("Apellido:");
		lblIngreseApellido.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseApellido = new GridBagConstraints();
		gbc_lblIngreseApellido.anchor = GridBagConstraints.WEST;
		gbc_lblIngreseApellido.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseApellido.gridx = 1;
		gbc_lblIngreseApellido.gridy = 3;
		getContentPane().add(lblIngreseApellido, gbc_lblIngreseApellido);

		textFieldApellido = new JTextField();
		textFieldApellido.setColumns(10);
		GridBagConstraints gbc_textFieldApellido = new GridBagConstraints();
		gbc_textFieldApellido.gridwidth = 2;
		gbc_textFieldApellido.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellido.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellido.gridx = 2;
		gbc_textFieldApellido.gridy = 3;
		getContentPane().add(textFieldApellido, gbc_textFieldApellido);

		lblIngreseCorreo = new JLabel("Correo:");
		lblIngreseCorreo.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseCorreo = new GridBagConstraints();
		gbc_lblIngreseCorreo.anchor = GridBagConstraints.WEST;
		gbc_lblIngreseCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseCorreo.gridx = 1;
		gbc_lblIngreseCorreo.gridy = 4;
		getContentPane().add(lblIngreseCorreo, gbc_lblIngreseCorreo);

		textFieldCorreo = new JTextField();
		textFieldCorreo.setColumns(10);
		GridBagConstraints gbc_textFieldCorreo = new GridBagConstraints();
		gbc_textFieldCorreo.gridwidth = 2;
		gbc_textFieldCorreo.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCorreo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCorreo.gridx = 2;
		gbc_textFieldCorreo.gridy = 4;
		getContentPane().add(textFieldCorreo, gbc_textFieldCorreo);

		lblIngreseNombreEmpresa = new JLabel("Empresa:");
		lblIngreseNombreEmpresa.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseNombreEmpresa = new GridBagConstraints();
		gbc_lblIngreseNombreEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseNombreEmpresa.anchor = GridBagConstraints.WEST;
		gbc_lblIngreseNombreEmpresa.gridx = 1;
		gbc_lblIngreseNombreEmpresa.gridy = 5;
		getContentPane().add(lblIngreseNombreEmpresa, gbc_lblIngreseNombreEmpresa);

		textFieldNombreEmpresa = new JTextField();
		textFieldNombreEmpresa.setColumns(10);
		GridBagConstraints gbc_textFieldNombreEmpresa = new GridBagConstraints();
		gbc_textFieldNombreEmpresa.gridwidth = 2;
		gbc_textFieldNombreEmpresa.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreEmpresa.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreEmpresa.gridx = 2;
		gbc_textFieldNombreEmpresa.gridy = 5;
		getContentPane().add(textFieldNombreEmpresa, gbc_textFieldNombreEmpresa);

		lblIngreseDescripcion = new JLabel("Descripcion:");
		lblIngreseDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngreseDescripcion = new GridBagConstraints();
		gbc_lblIngreseDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngreseDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblIngreseDescripcion.gridx = 1;
		gbc_lblIngreseDescripcion.gridy = 6;
		getContentPane().add(lblIngreseDescripcion, gbc_lblIngreseDescripcion);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 6;
		getContentPane().add(scrollPane, gbc_scrollPane);

		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setAlignmentX(Component.LEFT_ALIGNMENT);
		scrollPane.setViewportView(textAreaDescripcion);

		lblIngresePaginaWeb = new JLabel("Sitio Web:");
		lblIngresePaginaWeb.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblIngresePaginaWeb = new GridBagConstraints();
		gbc_lblIngresePaginaWeb.anchor = GridBagConstraints.WEST;
		gbc_lblIngresePaginaWeb.insets = new Insets(0, 0, 5, 5);
		gbc_lblIngresePaginaWeb.gridx = 1;
		gbc_lblIngresePaginaWeb.gridy = 7;
		getContentPane().add(lblIngresePaginaWeb, gbc_lblIngresePaginaWeb);

		textFieldPaginaWeb = new JTextField();
		GridBagConstraints gbc_textFieldPaginaWeb = new GridBagConstraints();
		gbc_textFieldPaginaWeb.gridwidth = 2;
		gbc_textFieldPaginaWeb.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPaginaWeb.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPaginaWeb.gridx = 2;
		gbc_textFieldPaginaWeb.gridy = 7;
		getContentPane().add(textFieldPaginaWeb, gbc_textFieldPaginaWeb);
		textFieldPaginaWeb.setColumns(10);

		lblClave = new JLabel("Clave:");
		lblClave.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblClave = new GridBagConstraints();
		gbc_lblClave.insets = new Insets(0, 0, 5, 5);
		gbc_lblClave.anchor = GridBagConstraints.WEST;
		gbc_lblClave.gridx = 1;
		gbc_lblClave.gridy = 9;
		getContentPane().add(lblClave, gbc_lblClave);

		textFieldClave = new JPasswordField();
		GridBagConstraints gbc_textFieldClave = new GridBagConstraints();
		gbc_textFieldClave.gridwidth = 2;
		gbc_textFieldClave.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldClave.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldClave.gridx = 2;
		gbc_textFieldClave.gridy = 9;
		getContentPane().add(textFieldClave, gbc_textFieldClave);

		lblClaveConfirmacion = new JLabel("Confirmar clave:");
		lblClaveConfirmacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblClaveConfirmacion = new GridBagConstraints();
		gbc_lblClaveConfirmacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblClaveConfirmacion.anchor = GridBagConstraints.WEST;
		gbc_lblClaveConfirmacion.gridx = 1;
		gbc_lblClaveConfirmacion.gridy = 10;
		getContentPane().add(lblClaveConfirmacion, gbc_lblClaveConfirmacion);

		textFieldClaveConfirmacion = new JPasswordField();
		GridBagConstraints gbc_textFieldClaveConfirmacion = new GridBagConstraints();
		gbc_textFieldClaveConfirmacion.gridwidth = 2;
		gbc_textFieldClaveConfirmacion.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldClaveConfirmacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldClaveConfirmacion.gridx = 2;
		gbc_textFieldClaveConfirmacion.gridy = 10;
		getContentPane().add(textFieldClaveConfirmacion, gbc_textFieldClaveConfirmacion);

		lblImagen = new JLabel("Imagen:");
		lblImagen.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblImagen = new GridBagConstraints();
		gbc_lblImagen.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagen.anchor = GridBagConstraints.WEST;
		gbc_lblImagen.gridx = 1;
		gbc_lblImagen.gridy = 12;
		getContentPane().add(lblImagen, gbc_lblImagen);

		textFieldArchivoImagen = new JTextField();
		textFieldArchivoImagen.setEditable(false);
		GridBagConstraints gbc_textFieldArchivoImagen = new GridBagConstraints();
		gbc_textFieldArchivoImagen.gridwidth = 2;
		gbc_textFieldArchivoImagen.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldArchivoImagen.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldArchivoImagen.gridx = 2;
		gbc_textFieldArchivoImagen.gridy = 12;
		getContentPane().add(textFieldArchivoImagen, gbc_textFieldArchivoImagen);

		btnSeleccionarArchivoImagen = new JButton("Seleccionar");
		btnSeleccionarArchivoImagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser file_chooser = new JFileChooser();
				// int res = file_chooser.showOpenDialog(null);
				int res = file_chooser.showSaveDialog(null);

				if (res == JFileChooser.APPROVE_OPTION) {

					File file = file_chooser.getSelectedFile();
					String filename = file.getAbsolutePath();

					textFieldArchivoImagen.setText(filename);

					// File nuevo_archivo_imagen = new File(filename);

					lblImagenVista.setIcon(new ImageIcon(file.toString()));

				}

			}
		});

		GridBagConstraints gbc_btnSeleccionarArchivoImagen = new GridBagConstraints();
		gbc_btnSeleccionarArchivoImagen.insets = new Insets(0, 0, 5, 5);
		gbc_btnSeleccionarArchivoImagen.gridx = 2;
		gbc_btnSeleccionarArchivoImagen.gridy = 13;
		getContentPane().add(btnSeleccionarArchivoImagen, gbc_btnSeleccionarArchivoImagen);

		lblImagenVista = new JLabel("Imágen seleccionada");
		lblImagenVista.setMinimumSize(new Dimension(50, 50));
		lblImagenVista.setMaximumSize(new Dimension(150, 150));
		lblImagenVista.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblImagenVista = new GridBagConstraints();
		gbc_lblImagenVista.gridheight = 5;
		gbc_lblImagenVista.gridwidth = 2;
		gbc_lblImagenVista.insets = new Insets(0, 0, 5, 5);
		gbc_lblImagenVista.gridx = 2;
		gbc_lblImagenVista.gridy = 14;
		getContentPane().add(lblImagenVista, gbc_lblImagenVista);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdRegistroUsuarioActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 2;
		gbc_btnAceptar.gridy = 20;
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
		gbc_btnCancelar.gridy = 20;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarFormulario() {
		textFieldNickname.setText("");
		textFieldNombre.setText("");
		textFieldApellido.setText("");
		textFieldCorreo.setText("");

		textFieldNombreEmpresa.setText("");
		textAreaDescripcion.setText("");
		textFieldPaginaWeb.setText("");
		textFieldClave.setText("");
		textFieldClaveConfirmacion.setText("");
		textFieldArchivoImagen.setText("");
		lblImagenVista.setIcon(null);

	}

	protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {

		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String nicknameU = this.textFieldNickname.getText();
			String nombreU = this.textFieldNombre.getText();
			String apellidoU = this.textFieldApellido.getText();
			String correoU = this.textFieldCorreo.getText();

			String nombreEmpresaU = this.textFieldNombreEmpresa.getText();
			String descripcionU = this.textAreaDescripcion.getText();
			String PaginaWebU = this.textFieldPaginaWeb.getText();

			String claveU = this.textFieldClave.getText();
			String imagenU = this.textFieldArchivoImagen.getText();

			try {
				if (!imagenU.equals("")) { // si no es vacia guardo la imagen

					//////////// Guardar imagen//////////////////

					String nombre_empresa_archivo_imagen = nicknameU;

					String extension = imagenU.substring(imagenU.lastIndexOf(".") + 1);
					File archivo_origen_seleccionado = null;
					File archivo_destino_nuevo = null;

					String root = System.getProperty("user.dir");
					String ruta_nueva = root + "/Tarea2/public/assets/images/usuarios/empresa";
					File directorio = new File(ruta_nueva);
					if (!directorio.exists()) {
						directorio.mkdir();
					}

					archivo_origen_seleccionado = new File(imagenU);
					String archivo_nuevo_nombre = nombre_empresa_archivo_imagen + "." + extension;
					String ruta_nueva_archivo = ruta_nueva + "/" + archivo_nuevo_nombre;
					archivo_destino_nuevo = new File(ruta_nueva_archivo);

					try {
						Files.copy(archivo_origen_seleccionado.toPath(), archivo_destino_nuevo.toPath());

						// No me sirve guardar la ruta, porque desde la app web es diferente, entonces
						// solo guardo el nombre del archivo
						// Como estas imágenes van a estar siempre en la misma carpeta, no hay problema
						// imagenU = ruta_nueva_archivo;
						imagenU = archivo_nuevo_nombre;

					} catch (IOException e) {

						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					//////////// Fin Guardar imagen//////////////////
				}

				else { // si el usuario no asigno imagen guardo defaultAvatar
					imagenU = "defaultAvatar.png";
				}

				DTEmpresa data_empresa = new DTEmpresa(nicknameU, nombreU, apellidoU, correoU, claveU, imagenU,
						this.rol, nombreEmpresaU, descripcionU, PaginaWebU);
				try {
					controlU.registrarEmpresa(data_empresa);
					// Muestro éxito de la operación
					JOptionPane.showMessageDialog(this, "La empresa se ha registrado con éxito", "Registrar Empresa",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (ObjetoNoRespetaFormatoException onrfe) {
					JOptionPane.showMessageDialog(this, onrfe.getMessage(), "Registrar Empresa",
							JOptionPane.ERROR_MESSAGE);
				}
				// Limpio el internal frame antes de cerrar la ventana
				limpiarFormulario();
				setVisible(false);

			} catch (ObjetoRepetidoException e) {
				// Muestro error de registro
				JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Empresa", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {
		String nicknameU = this.textFieldNickname.getText();
		String nombreU = this.textFieldNombre.getText();
		String apellidoU = this.textFieldApellido.getText();
		String correoU = this.textFieldCorreo.getText();

		String nombreEmpresaU = this.textFieldNombreEmpresa.getText();
		String descripcionU = this.textAreaDescripcion.getText();
		String paginaWebU = this.textFieldPaginaWeb.getText();

		String claveU = this.textFieldClave.getText();
		String claveConfirmacionU = this.textFieldClaveConfirmacion.getText();

		String imagenU = this.textFieldArchivoImagen.getText();
		String extension = imagenU.substring(imagenU.lastIndexOf(".") + 1);

		Validador validador = new Validador();

		if (nicknameU.isEmpty() || nombreU.isEmpty() || apellidoU.isEmpty() || correoU.isEmpty()
				|| descripcionU.isEmpty() || nombreEmpresaU.isEmpty()) {
			JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (controlU.existeUsuario(nicknameU)) {
			JOptionPane.showMessageDialog(this, "Ya existe un usuario con ese nickname", "Registrar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!correoU.contains("@")) {
			JOptionPane.showMessageDialog(this, "El correo ingresado es invalido, ingresar un correo que contenga @",
					"Registrar Empresa", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!extension.equals("png") && !extension.equals("jpg") && !extension.equals("jpeg") && !imagenU.equals("")) {
			JOptionPane.showMessageDialog(this,
					"La imagen  ingresada es inválida, debe tener la extensión: png, jpg o jpeg", "Registrar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!claveU.equals(claveConfirmacionU)) {
			JOptionPane.showMessageDialog(this, "Las claves no coinciden", "Registrar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!validador.validarUsuario(nicknameU)) {
			JOptionPane.showMessageDialog(this, "El nickname ingresado no es valido", "Registrar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!validador.validarCorreo(correoU)) {
			JOptionPane.showMessageDialog(this, "El correo ingresado no es valido", "Registrar Empresa",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (claveU.length() < 8) {
			JOptionPane.showMessageDialog(this, "La clave debe tener un largo de al menos 8 caracteres",
					"Registrar Empresa", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!validador.validarUrl(paginaWebU)) {
			JOptionPane.showMessageDialog(this, "El sitio ingresado no es valido eg: https://google.com",
					"Registrar Empresa", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}
}
