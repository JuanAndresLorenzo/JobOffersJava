package presentacion.frames_casos_de_uso.tipo_publicacion;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import presentacion.Render;
import logica.interfaces.IControladorTipoPublicacion;

import logica.dts.DTPaquete;
import logica.dts.DTPaqueteTipoPublicacion;
import logica.dts.DTTipoPublicacion;
import excepciones.ObjetoNoExisteException;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import java.awt.Dimension;
//import excepciones.ObjetoRepetidoException;

public class ConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private IControladorTipoPublicacion controlTP;
	private JLabel lblPaquete;
	private JComboBox<DTPaquete> comboBoxPaquetes;
	// private JComboBox<String> comboBoxPaquetes;

	private JLabel lblNombre;
	private JLabel lblDescripcion;
	private JLabel lblValidez;
	private JLabel lblDescuento;
	private JLabel lblNombreValor;
	private JLabel lblValidezValor;
	private JLabel lblDescuentoValor;

	private JTable tablaTiposPublicacionesAsociadas;
	private JLabel lblFechaAlta;
	private JLabel lblFechaAltaValor;
	private JComboBox<DTPaqueteTipoPublicacion> comboBoxTipoPublicacion;
	private JLabel lblTipoPublicacion;
	private JLabel lblCosto;
	private JLabel lblCostoValor;
	private JScrollPane scrollPane;
	private JTextArea textAreaDescripcion;

	// Create the frame
	public ConsultadePaquetedeTiposdePublicacionesdeOfertaLaboral(IControladorTipoPublicacion ICTP) {

		controlTP = ICTP;

		setResizable(true);
		setIconifiable(true);
		setMaximizable(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setClosable(true);
		setTitle("Consultar paquete");
		setBounds(10, 40, 592, 707);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
		gridBagLayout.rowHeights = new int[] { 30, 30, 30, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		getContentPane().setLayout(gridBagLayout);

		lblPaquete = new JLabel("Paquete:");
		lblPaquete.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPaquete = new GridBagConstraints();
		gbc_lblPaquete.fill = GridBagConstraints.BOTH;
		gbc_lblPaquete.insets = new Insets(0, 0, 5, 5);
		gbc_lblPaquete.gridx = 0;
		gbc_lblPaquete.gridy = 2;
		getContentPane().add(lblPaquete, gbc_lblPaquete);

		comboBoxPaquetes = new JComboBox<DTPaquete>();
		this.cargarComboBoxPaquetes();

		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 2;
		getContentPane().add(comboBoxPaquetes, gbc_comboBox);

		JButton btnAceptar = new JButton("Mostrar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cmdConsultarPaqueteActionPerformed(arg0);
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.fill = GridBagConstraints.BOTH;
		gbc_btnAceptar.insets = new Insets(0, 0, 5, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 3;
		getContentPane().add(btnAceptar, gbc_btnAceptar);

		lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 0;
		gbc_lblNombre.gridy = 5;
		getContentPane().add(lblNombre, gbc_lblNombre);

		lblNombreValor = new JLabel("Nombre");
		lblNombreValor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblNombreValor = new GridBagConstraints();
		gbc_lblNombreValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreValor.gridx = 1;
		gbc_lblNombreValor.gridy = 5;
		getContentPane().add(lblNombreValor, gbc_lblNombreValor);

		lblDescripcion = new JLabel("Descripción:");
		lblDescripcion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 0;
		gbc_lblDescripcion.gridy = 6;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(15, 15));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 1;
		gbc_scrollPane.gridy = 6;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		textAreaDescripcion = new JTextArea();
		textAreaDescripcion.setEditable(false);
		textAreaDescripcion.setLineWrap(true);
		textAreaDescripcion.setBackground(UIManager.getColor("Button.background"));
		scrollPane.setViewportView(textAreaDescripcion);

		lblValidez = new JLabel("Validéz (días):");
		lblValidez.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblValidez = new GridBagConstraints();
		gbc_lblValidez.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidez.gridx = 0;
		gbc_lblValidez.gridy = 7;
		getContentPane().add(lblValidez, gbc_lblValidez);

		lblValidezValor = new JLabel("Validéz (días)");
		lblValidezValor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblValidezValor = new GridBagConstraints();
		gbc_lblValidezValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblValidezValor.gridx = 1;
		gbc_lblValidezValor.gridy = 7;
		getContentPane().add(lblValidezValor, gbc_lblValidezValor);

		lblDescuento = new JLabel("Descuento (%):");
		lblDescuento.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescuento = new GridBagConstraints();
		gbc_lblDescuento.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuento.gridx = 0;
		gbc_lblDescuento.gridy = 8;
		getContentPane().add(lblDescuento, gbc_lblDescuento);

		lblDescuentoValor = new JLabel("Descuento (%)");
		lblDescuentoValor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblDescuentoValor = new GridBagConstraints();
		gbc_lblDescuentoValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescuentoValor.gridx = 1;
		gbc_lblDescuentoValor.gridy = 8;
		getContentPane().add(lblDescuentoValor, gbc_lblDescuentoValor);
		
		lblFechaAlta = new JLabel("Fecha alta:");
		lblFechaAlta.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta = new GridBagConstraints();
		gbc_lblFechaAlta.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta.gridx = 0;
		gbc_lblFechaAlta.gridy = 9;
		getContentPane().add(lblFechaAlta, gbc_lblFechaAlta);
		
		lblFechaAltaValor = new JLabel("Fecha alta");
		lblFechaAltaValor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblFechaAlta_2 = new GridBagConstraints();
		gbc_lblFechaAlta_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaAlta_2.gridx = 1;
		gbc_lblFechaAlta_2.gridy = 9;
		getContentPane().add(lblFechaAltaValor, gbc_lblFechaAlta_2);

		/*
		tablaTiposPublicacionesAsociadas = new JTable();
		GridBagConstraints gbc_tablaTiposPublicacionesAsociadas = new GridBagConstraints();
		gbc_tablaTiposPublicacionesAsociadas.insets = new Insets(0, 0, 5, 5);
		gbc_tablaTiposPublicacionesAsociadas.gridx = 1;
		gbc_tablaTiposPublicacionesAsociadas.gridy = 10;
		gbc_tablaTiposPublicacionesAsociadas.gridwidth = 1;
		gbc_tablaTiposPublicacionesAsociadas.anchor = GridBagConstraints.CENTER;
		getContentPane().add(tablaTiposPublicacionesAsociadas, gbc_tablaTiposPublicacionesAsociadas);

		tablaTiposPublicacionesAsociadas.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				mostrarTipoPublicacion();
			}
		});*/
		
		lblCosto = new JLabel("Costo:");
		lblCosto.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCosto = new GridBagConstraints();
		gbc_lblCosto.insets = new Insets(0, 0, 5, 5);
		gbc_lblCosto.gridx = 0;
		gbc_lblCosto.gridy = 10;
		getContentPane().add(lblCosto, gbc_lblCosto);
		
		lblCostoValor = new JLabel("Costo");
		lblCostoValor.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblCostoValor = new GridBagConstraints();
		gbc_lblCostoValor.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostoValor.gridx = 1;
		gbc_lblCostoValor.gridy = 10;
		getContentPane().add(lblCostoValor, gbc_lblCostoValor);
		
		lblTipoPublicacion = new JLabel("Tipo publicación:");
		lblTipoPublicacion.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTipoPublicacion = new GridBagConstraints();
		gbc_lblTipoPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_lblTipoPublicacion.anchor = GridBagConstraints.EAST;
		gbc_lblTipoPublicacion.gridx = 0;
		gbc_lblTipoPublicacion.gridy = 12;
		getContentPane().add(lblTipoPublicacion, gbc_lblTipoPublicacion);
		
		comboBoxTipoPublicacion = new JComboBox<DTPaqueteTipoPublicacion>();
		comboBoxTipoPublicacion.setSelectedIndex(-1);
		GridBagConstraints gbc_comboBoxTipoPublicacion = new GridBagConstraints();
		gbc_comboBoxTipoPublicacion.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxTipoPublicacion.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxTipoPublicacion.gridx = 1;
		gbc_comboBoxTipoPublicacion.gridy = 12;
		getContentPane().add(comboBoxTipoPublicacion, gbc_comboBoxTipoPublicacion);
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		
		
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
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.fill = GridBagConstraints.BOTH;
		gbc_btnCancelar.gridx = 1;
		gbc_btnCancelar.gridy = 22;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
	}

	private void limpiarFormulario() {

		this.comboBoxPaquetes.removeAllItems();

		limpiarFormularioPaquete();
	}
	

	private void limpiarFormularioPaquete() {

		//this.comboBoxPaquetes.removeAllItems();
		this.comboBoxTipoPublicacion.removeAllItems();

		lblNombreValor.setText("");
		textAreaDescripcion.setText("");
		lblValidezValor.setText("");
		lblDescuentoValor.setText("");
		lblFechaAltaValor.setText("");
		lblCostoValor.setText("");

	}

	protected void cmdConsultarPaqueteActionPerformed(ActionEvent arg0) {

		limpiarFormularioPaquete();
		
		if (checkFormulario()) {

			// Obtengo datos de los controles Swing
			String paquete_nombre = this.comboBoxPaquetes.getSelectedItem().toString();

			this.cargarDatosPaqueteSeleccionado(paquete_nombre);

			// Muestro éxito de la operación
			// JOptionPane.showMessageDialog(this, "El Usuario se ha creado con éxito",
			// "Registrar Usuario",
			// JOptionPane.INFORMATION_MESSAGE);

			// Limpio el internal frame antes de cerrar la ventana
			// limpiarFormulario();
			// setVisible(false);
		}
	}

	// Permite validar la información introducida en los campos e indicar
	// a través de un mensaje de error (JOptionPane) cuando algo sucede.
	private boolean checkFormulario() {

		if (this.comboBoxPaquetes.getSelectedIndex() == -1) {			
			return false;
		}

		String paquete_nombre = this.comboBoxPaquetes.getSelectedItem().toString();

		if (paquete_nombre.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar un paquete a consultar", "Consultar Paquete",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	// Método que permite cargar un nuevo modelo para el combo con la información
	// actualizada de paquetes, provista por la operación del controaldor
	// listarPaquetes().
	// Se invoca el método antes de hacer visible el JInternalFrame
	public void cargarComboBoxPaquetes() {

		DefaultComboBoxModel<DTPaquete> modelPaquete;
		try {
			modelPaquete = new DefaultComboBoxModel<DTPaquete>(controlTP.listarPaquetes());
			this.comboBoxPaquetes.setModel(modelPaquete);

			this.comboBoxPaquetes.insertItemAt(new DTPaquete("", "", 0, (float) 0, null), 0);
			this.comboBoxPaquetes.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}
	
	public void cargarComboBoxTiposPublicacionesAsociadas(String paquete_nombre) {

		DefaultComboBoxModel<DTPaqueteTipoPublicacion> modelTP;
		try {
			modelTP = new DefaultComboBoxModel<DTPaqueteTipoPublicacion>(controlTP.listarTiposPublicacionesPorPaquete(paquete_nombre));
			this.comboBoxTipoPublicacion.setModel(modelTP);

			this.comboBoxTipoPublicacion.insertItemAt(new DTPaqueteTipoPublicacion(/*null,*/ null, 0), 0);
			this.comboBoxTipoPublicacion.setSelectedIndex(-1);

		} catch (ObjetoNoExisteException e) {
			// No se imprime mensaje de error sino que simplemente no se muestra ningún
			// elemento
		}

	}

	// Operación que toma el dato del paquete seleccionado en combo, y carga sus
	// datos completos a labels y tabla
	public void cargarDatosPaqueteSeleccionado(String paquete_nombre) {

		try {

			DTPaquete dt_paquete = controlTP.getDataPaquete(paquete_nombre);

			lblNombreValor.setText(dt_paquete.getNombre());
			textAreaDescripcion.setText(dt_paquete.getDescripcion());
			lblValidezValor.setText(dt_paquete.getValidezDias().toString());
			lblDescuentoValor.setText(dt_paquete.getDescuento().toString());
			lblFechaAltaValor.setText(dt_paquete.getFechaAltaFormat());
			
			lblCostoValor.setText(dt_paquete.getCosto().toString());

			//cargarTablaTiposPublicacionesdePaquete(dt_paquete.getNombre());
			cargarComboBoxTiposPublicacionesAsociadas(dt_paquete.getNombre());

		} catch (ObjetoNoExisteException e) {
			// Muestro error de registro
			JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Paquete", JOptionPane.ERROR_MESSAGE);
		}

	}

	// Operación que arma y carga la tabla, en base a un numbre de paquete recibido
	private void cargarTablaTiposPublicacionesdePaquete(String paquete_nombre) {

		try {
			DTPaquete dt_paquete = controlTP.getDataPaquete(paquete_nombre);

			List<DTPaqueteTipoPublicacion> dts_tipos_publicaciones_del_paquete_lista = new ArrayList<DTPaqueteTipoPublicacion>(
					dt_paquete.getTiposPublicaciones().values());

			String[] nombreColumnas = { "Tipo de Publicación", "Cantidad", "Ver" };

			/*
			 * El tamao de la tabla es, 6 columnas (cantidad de datos a mostrar) y la
			 * cantidad de filas depende de la cantida de mascotas
			 */
			Object[][] datos = new Object[dts_tipos_publicaciones_del_paquete_lista.size()][3];

			/* Cargamos la matriz con todos los datos */
			int fila = 0;

			for (DTPaqueteTipoPublicacion dt_tp_de_paquete : dts_tipos_publicaciones_del_paquete_lista) {

				datos[fila][0] = dt_tp_de_paquete.getKey();
				datos[fila][1] = dt_tp_de_paquete.getCantidad();

				JButton btn_ver = new JButton("Ver");
				btn_ver.setName("ver");

				datos[fila][2] = btn_ver;

				fila++;

			}

			/*
			 * Este codigo indica que las celdas no son editables y que son todas del tipos
			 * String
			 */
			DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

				/**
				 * 
				 */

				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}

				@Override
				public Class<?> getColumnClass(int columnIndex) {
					return String.class;
				}

			};

			this.tablaTiposPublicacionesAsociadas.setModel(model);
			this.tablaTiposPublicacionesAsociadas.setAutoscrolls(true);
			this.tablaTiposPublicacionesAsociadas.setCellSelectionEnabled(false);
			// this.tablaTiposPublicacionesAsociadas.setSize(700, 700);
			this.tablaTiposPublicacionesAsociadas.setDefaultRenderer(Object.class, new Render());
			// this.tablaTiposPublicacionesAsociadas.setRowHeight(30);
			this.tablaTiposPublicacionesAsociadas.setRowSelectionAllowed(true);

			// this.tablaTiposPublicacionesAsociadas.addMouseListener(this);

		} catch (ObjetoNoExisteException e) {
			e.printStackTrace();
		}

	}

	// @Override
	// public void mouseClicked(java.awt.event.MouseEvent e) {
	private void mostrarTipoPublicacion() {

		int row = this.tablaTiposPublicacionesAsociadas.getSelectedRow();
		int col = this.tablaTiposPublicacionesAsociadas.getSelectedColumn();

		// TableModel model = this.tablaTiposPublicacionesAsociadas.getModel();
		// String row_index = model.getValueAt(i, 1).toString();

		String tipo_publicacion_nombre = (String) this.tablaTiposPublicacionesAsociadas.getValueAt(row, 0);

		Object value = this.tablaTiposPublicacionesAsociadas.getValueAt(row, col);

		/*
		 * JOptionPane.showMessageDialog(frame,
		 * "Toque boton i: "+i+" y el  id: "+id_seleccionado+".", "Aviso!",
		 * JOptionPane.WARNING_MESSAGE);
		 */

		if (value instanceof JButton) {

			JButton btn = (JButton) value;

			/*
			 * JOptionPane.showMessageDialog(frame, "Toque boton i: "+i+" y el  j: "+j+".",
			 * "Aviso!", JOptionPane.WARNING_MESSAGE);
			 */

			if (btn.getName().equals("ver")) {

				try {

					DTTipoPublicacion dt_tp = controlTP.getDataTipoPublicacion(tipo_publicacion_nombre);
					String tp_info = "Nombre: " + tipo_publicacion_nombre + "\n" + "Descripción: "
							+ dt_tp.getDescripcion() + "\n" + "Duración (días): " + dt_tp.getDuracion_dias() + "\n"
							+ "Exposición: " + dt_tp.getExposicion() + "\n" + "Costo: " + dt_tp.getCosto() + "\n"
							+ "Fecha de alta: " + dt_tp.getFechaAltaFormat() + "\n";

					JOptionPane.showMessageDialog(this, tp_info, "Consultar Paquete - Ver Tipo de Publicación",
							JOptionPane.INFORMATION_MESSAGE);

				} catch (ObjetoNoExisteException e) {
					// e.printStackTrace();
					// Muestro error de registro
					JOptionPane.showMessageDialog(this, e.getMessage(), "Consultar Paquete", JOptionPane.ERROR_MESSAGE);
				}

			}

		}

	}

	/*
	 * Operación para precargar algunos paquetes public void
	 * preCargarPaquetes(Integer cantidad) {
	 * 
	 * try { controlTP.preCargarPaquetes(cantidad); } catch (ObjetoRepetidoException
	 * e) { e.printStackTrace(); } }
	 */

}
