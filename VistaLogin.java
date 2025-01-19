package AEV3_Mongo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JPasswordField;

public class VistaLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    //Componentes de la interfaz 
    private JPanel contentPane;
    private JTextField txtName;
    private JButton btnLogin;
    private JButton btnRegistro;
    private JPasswordField password;
    private JPasswordField repeatedPassword;

	/**
     * Create the frame.
     */
    public VistaLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 924, 523);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panelFondo = new JPanel();
        panelFondo.setBackground(new Color(0, 128, 0));
        panelFondo.setBounds(0, 0, 920, 496);
        contentPane.add(panelFondo);
        panelFondo.setLayout(null);
        
        JLabel tituloLogin = new JLabel("LOGIN IZAN'S BLACKJACK");
        tituloLogin.setForeground(new Color(255, 127, 80));
        tituloLogin.setFont(new Font("Tahoma", Font.BOLD, 35));
        tituloLogin.setBounds(203, 46, 486, 72);
        panelFondo.add(tituloLogin);
        
        JLabel userName = new JLabel("USER'S NAME: ");
        userName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userName.setBounds(104, 160, 159, 33);
        panelFondo.add(userName);
        
        JLabel userPassword = new JLabel("USER'S PASSWORD: ");
        userPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userPassword.setBounds(92, 239, 188, 33);
        panelFondo.add(userPassword);
        
        txtName = new JTextField();
        txtName.setFont(new Font("Tahoma", Font.PLAIN, 20));
        txtName.setForeground(new Color(255, 127, 80));
        txtName.setBounds(342, 152, 298, 41);
        panelFondo.add(txtName);
        txtName.setColumns(10);
        
        JLabel lblInfo = new JLabel("You dont have an account yet? Click here -->");
        lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblInfo.setBounds(130, 397, 422, 33);
        panelFondo.add(lblInfo);
        
        btnRegistro = new JButton("REGISTER!!");
        btnRegistro.setForeground(new Color(255, 127, 80));
        btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnRegistro.setBounds(573, 389, 188, 50);
        panelFondo.add(btnRegistro);
        
        btnLogin = new JButton("LOGIN");
        btnLogin.setForeground(new Color(255, 127, 80));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnLogin.setBounds(674, 222, 188, 50);
        panelFondo.add(btnLogin);
        
        JLabel userRepeatPassword = new JLabel("REPEAT PASSWORD: ");
        userRepeatPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        userRepeatPassword.setBounds(92, 318, 219, 33);
        panelFondo.add(userRepeatPassword);
        
        password = new JPasswordField();
        password.setFont(new Font("Tahoma", Font.PLAIN, 20));
        password.setEchoChar('*');
        password.setBounds(342, 230, 298, 41);
        panelFondo.add(password);
        
        repeatedPassword = new JPasswordField();
        repeatedPassword.setFont(new Font("Tahoma", Font.PLAIN, 20));
        repeatedPassword.setEchoChar('*');
        repeatedPassword.setBounds(342, 310, 298, 41);
        panelFondo.add(repeatedPassword);
      
        this.setVisible(true);
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
	
    public JTextField getTxtName() {
        return txtName;
    }


    public JButton getBtnLogin() {
        return btnLogin;
    }

    public JButton getBtnRegistro() {
        return btnRegistro;
    }
}
