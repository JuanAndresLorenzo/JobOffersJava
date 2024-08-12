package presentacion.frames_casos_de_uso.keyword;

import javax.swing.JInternalFrame;

import logica.interfaces.IControladorOfertaLaboral;


import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import excepciones.ObjetoRepetidoException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AltaDeKeywrods extends JInternalFrame {
	
	private IControladorOfertaLaboral controlOL;
	private JTextField textFieldNombre;
	private JLabel lblIngreseNombre;
	
	//Create the frame
	public AltaDeKeywrods(IControladorOfertaLaboral ICOL) {
		
		controlOL = ICOL;
		
		setResizable(true);
        setIconifiable(true);
        setMaximizable(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setClosable(true);
        setTitle("Registrar un Usuario");
        setBounds(10, 40, 360, 134);
        
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[] { 100, 120, 120, 0 };
        gridBagLayout.rowHeights = new int[] { 30, 30, 0, 0 };
        gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
        getContentPane().setLayout(gridBagLayout);
        
		setTitle("Alta De Keywords");
        
        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cmdRegistroUsuarioActionPerformed(arg0);
            }
        });
        
        lblIngreseNombre = new JLabel("Nombre:");
        lblIngreseNombre.setHorizontalAlignment(SwingConstants.RIGHT);
        GridBagConstraints gbc_lblIngreseNombre = new GridBagConstraints();
        gbc_lblIngreseNombre.fill = GridBagConstraints.BOTH;
        gbc_lblIngreseNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblIngreseNombre.gridx = 0;
        gbc_lblIngreseNombre.gridy = 1;
        getContentPane().add(lblIngreseNombre, gbc_lblIngreseNombre);
        
                // Una campo de texto (JTextField) para ingresar el nombre del usuario. 
                // Por defecto es posible ingresar cualquier string.
                textFieldNombre = new JTextField();
                GridBagConstraints gbc_textFieldNombre = new GridBagConstraints();
                gbc_textFieldNombre.gridwidth = 2;
                gbc_textFieldNombre.fill = GridBagConstraints.BOTH;
                gbc_textFieldNombre.insets = new Insets(0, 0, 5, 0);
                gbc_textFieldNombre.gridx = 1;
                gbc_textFieldNombre.gridy = 1;
                getContentPane().add(textFieldNombre, gbc_textFieldNombre);
                textFieldNombre.setColumns(10);

        GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
        gbc_btnAceptar.fill = GridBagConstraints.BOTH;
        gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
        gbc_btnAceptar.gridx = 1;
        gbc_btnAceptar.gridy = 2;
        getContentPane().add(btnAceptar, gbc_btnAceptar);

        // Un botón (JButton) con un evento asociado que permite cerrar el formulario (solo ocultarlo).
        // Dado que antes de cerrar se limpia el formulario, se invoca un método reutilizable para ello. 
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                limpiarFormulario();
                setVisible(false);
            }
        });
        GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
        gbc_btnCancelar.fill = GridBagConstraints.BOTH;
        gbc_btnCancelar.gridx = 2;
        gbc_btnCancelar.gridy = 2;
        getContentPane().add(btnCancelar, gbc_btnCancelar);
	}		
	
	private void limpiarFormulario() {
		textFieldNombre.setText("");
    }
	
    protected void cmdRegistroUsuarioActionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

        // Obtengo datos de los controles Swing
        String nombre_key_word = this.textFieldNombre.getText();

        if (checkFormulario()) {
            try {
            	controlOL.registrarKeyword(nombre_key_word);

                // Muestro éxito de la operación
                JOptionPane.showMessageDialog(this, "La keyword se ha creado con éxito", "Registrar Keyword",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (ObjetoRepetidoException e) {
                // Muestro error de registro
                JOptionPane.showMessageDialog(this, e.getMessage(), "Registrar Usuario", JOptionPane.ERROR_MESSAGE);
            }

            // Limpio el internal frame antes de cerrar la ventana
            limpiarFormulario();
            setVisible(false);
        }
    }

    // Permite validar la información introducida en los campos e indicar
    // a través de un mensaje de error (JOptionPane) cuando algo sucede.
    // Este tipo de chequeos se puede realizar de otras formas y con otras librerías de Java, 
    // por ejemplo impidiendo que se escriban caracteres no numéricos al momento de escribir en
    // en el campo de la cédula, o mostrando un mensaje de error apenas el foco pasa a otro campo.
    private boolean checkFormulario() {
        String nombreU = this.textFieldNombre.getText();
     
        if (nombreU.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No puede haber campos vacíos", "Registrar Keyword",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
}
