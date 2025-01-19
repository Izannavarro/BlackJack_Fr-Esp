package AEV3_Mongo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class VistaPuntuaciones extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Componentes de la interfaz 
	private JPanel contentPane;
	private JTextArea txtAreaPuntuaciones;

	/**
	 * Create the frame.
	 */
	public VistaPuntuaciones() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 705, 587);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtAreaPuntuaciones = new JTextArea();
		txtAreaPuntuaciones.setFont(new Font("Monospaced", Font.PLAIN, 18));
		txtAreaPuntuaciones.setBounds(21, 72, 649, 420);
		contentPane.add(txtAreaPuntuaciones);
		
		JLabel lblTitulo = new JLabel("REGISTRO DE PUNTUACIONES !! ");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblTitulo.setBounds(117, 10, 466, 59);
		contentPane.add(lblTitulo);
		
		this.setVisible(false);
	}

	public JTextArea getTxtAreaPuntuaciones() {
		return txtAreaPuntuaciones;
	}

	public void setTxtAreaPuntuaciones(JTextArea txtAreaPuntuaciones) {
		this.txtAreaPuntuaciones = txtAreaPuntuaciones;
	}
	
	
	
}
