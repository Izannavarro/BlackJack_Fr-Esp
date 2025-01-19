package AEV3_Mongo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class VistaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Componentes de la interfaz 
	private JPanel contentPane;
	private JTextField textName;
	private JButton btnRegistrar;
	private JPasswordField password;
	private JPasswordField repeatedPassword;
	
	/**
	 * Create the frame.
	 */
	public VistaRegistro() {
		setBounds(100, 100, 727, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("NAME: ");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblName.setBounds(197, 116, 80, 25);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("PASSWORD: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPassword.setBounds(145, 181, 155, 25);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_2 = new JLabel("REPEAT PASSWORD: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(73, 241, 212, 32);
		contentPane.add(lblNewLabel_2);
		
		textName = new JTextField();
		textName.setBounds(322, 109, 244, 32);
		contentPane.add(textName);
		textName.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("REGISTRATION");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel_3.setBounds(234, 37, 233, 25);
		contentPane.add(lblNewLabel_3);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnRegistrar.setBounds(244, 315, 196, 39);
		contentPane.add(btnRegistrar);
		
		password = new JPasswordField();
		password.setEchoChar('*');
		password.setBounds(322, 174, 244, 32);
		contentPane.add(password);
		
		repeatedPassword = new JPasswordField();
		repeatedPassword.setEchoChar('*');
		repeatedPassword.setBounds(322, 241, 244, 32);
		contentPane.add(repeatedPassword);
		
		VistaPuntuaciones frame = new VistaPuntuaciones();
		frame.setVisible(false);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}

	public JTextField getTextName() {
		return textName;
	}

	public void setTextName(JTextField textName) {
		this.textName = textName;
	}

	public JPasswordField getPassword() {
		return password;
	}

	public void setPassword(JPasswordField password) {
		this.password = password;
	}

	public JPasswordField getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(JPasswordField repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(JButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
	}
}
