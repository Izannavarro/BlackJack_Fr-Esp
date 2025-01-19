package AEV3_Mongo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.Icon;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	
	//Componentes de la interfaz 
	private JPanel contentPane;
	private JButton btnNewCard;
    private JButton btnStand;
    private JButton btnLoadCards;
    private JButton btnPartidasGuardadas;
    private JButton btnLogout;
    private JButton btnStart;
    private JLabel lblScoreHistoryCroupier;
    private JLabel lblScoreHistoryPlayer;
    private JLabel lblTotalScoreCroupier;
    private JLabel lblTotalScorePlayer;
    
    // CARTAS JUGADOR
    private JLabel cartaJugador1;
    private JLabel cartaJugador2;
    private JLabel cartaJugador3;
    private JLabel cartaJugador4;
    private JLabel cartaJugador5;
    private JLabel cartaJugador6;
    private JLabel cartaJugador7;
    private JLabel cartaJugador8;
    private JLabel cartaJugador9;
    private JLabel cartaJugador10;
    private JLabel cartaJugador11;
    private JLabel cartaJugador12;
    
    //CARTAS CROUPIER
    private JLabel cartaCroupier1;
    private JLabel cartaCroupier2;
    private JLabel cartaCroupier3;
    private JLabel cartaCroupier4;
    private JLabel cartaCroupier5;
    private JLabel cartaCroupier6;
    private JLabel cartaCroupier7;
    private JLabel cartaCroupier8;
    private JLabel cartaCroupier9;
    private JLabel cartaCroupier10;
    

	/**
	 * Create the frame.
	 */
	public VistaPrincipal() {
		setDefaultCloseOperation(VistaPrincipal.EXIT_ON_CLOSE);
		setBounds(100, 100, 1188, 746);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelFondo = new JPanel();
		panelFondo.setBackground(new Color(0, 128, 0));
		panelFondo.setBounds(0, 0, 1192, 744);
		contentPane.add(panelFondo);
		panelFondo.setLayout(null);
		
		JPanel mesaCroupier = new JPanel();
		mesaCroupier.setBackground(new Color(165, 42, 42));
		mesaCroupier.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EmptyBorder(0, 0, 0, 0)));
		mesaCroupier.setBounds(321, 0, 504, 227);
		panelFondo.add(mesaCroupier);
		mesaCroupier.setLayout(null);
		
		cartaCroupier1 = new JLabel("");
		cartaCroupier1.setBounds(61, 39, 88, 162);
		mesaCroupier.add(cartaCroupier1);

		cartaCroupier2 = new JLabel("");
		cartaCroupier2.setBounds(94, 39, 88, 162);
		mesaCroupier.add(cartaCroupier2);

		cartaCroupier3 = new JLabel("");
		cartaCroupier3.setBounds(129, 39, 88, 162);
		mesaCroupier.add(cartaCroupier3);

		cartaCroupier4 = new JLabel("");
		cartaCroupier4.setBounds(170, 39, 88, 162);
		mesaCroupier.add(cartaCroupier4);

		cartaCroupier5 = new JLabel("");
		cartaCroupier5.setBounds(204, 39, 88, 162);
		mesaCroupier.add(cartaCroupier5);

		cartaCroupier6 = new JLabel("");
		cartaCroupier6.setBounds(244, 39, 88, 162);
		mesaCroupier.add(cartaCroupier6);

		cartaCroupier7 = new JLabel("");
		cartaCroupier7.setBounds(286, 39, 88, 162);
		mesaCroupier.add(cartaCroupier7);

		cartaCroupier8 = new JLabel("");
		cartaCroupier8.setBounds(328, 39, 88, 162);
		mesaCroupier.add(cartaCroupier8);

		cartaCroupier9 = new JLabel("");
		cartaCroupier9.setBounds(368, 39, 88, 162);
		mesaCroupier.add(cartaCroupier9);

		cartaCroupier10 = new JLabel("");
		// Si necesitas añadir más posiciones o adaptarlas, puedes hacerlo.
		mesaCroupier.add(cartaCroupier10);
		
		JPanel mesaJugador = new JPanel();
		mesaJugador.setBackground(new Color(165, 42, 42));
		mesaJugador.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null), new EmptyBorder(0, 0, 0, 0)));
		mesaJugador.setBounds(321, 482, 504, 227);
		panelFondo.add(mesaJugador);
		mesaJugador.setLayout(null);
		
		cartaJugador1 = new JLabel("");
		cartaJugador1.setBounds(0, 37, 88, 162);
		mesaJugador.add(cartaJugador1);

		cartaJugador2 = new JLabel("");
		cartaJugador2.setBounds(30, 37, 88, 162);
		mesaJugador.add(cartaJugador2);

		cartaJugador3 = new JLabel("");
		cartaJugador3.setBounds(63, 37, 88, 162);
		mesaJugador.add(cartaJugador3);

		cartaJugador4 = new JLabel("");
		cartaJugador4.setBounds(98, 37, 88, 162);
		mesaJugador.add(cartaJugador4);

		cartaJugador5 = new JLabel("");
		cartaJugador5.setBounds(139, 37, 88, 162);
		mesaJugador.add(cartaJugador5);

		cartaJugador6 = new JLabel("");
		cartaJugador6.setBounds(173, 37, 88, 162);
		mesaJugador.add(cartaJugador6);

		cartaJugador7 = new JLabel("");
		cartaJugador7.setBounds(213, 37, 88, 162);
		mesaJugador.add(cartaJugador7);

		cartaJugador8 = new JLabel("");
		cartaJugador8.setBounds(255, 37, 88, 162);
		mesaJugador.add(cartaJugador8);

		cartaJugador9 = new JLabel("");
		cartaJugador9.setBounds(297, 37, 88, 162);
		mesaJugador.add(cartaJugador9);

		cartaJugador10 = new JLabel("");
		cartaJugador10.setBounds(337, 37, 88, 162);
		mesaJugador.add(cartaJugador10);

		cartaJugador11 = new JLabel("");
		cartaJugador11.setBounds(371, 37, 88, 162);
		mesaJugador.add(cartaJugador11);

		cartaJugador12 = new JLabel("");
		cartaJugador12.setBounds(406, 37, 88, 162);
		mesaJugador.add(cartaJugador12);
		
		btnNewCard = new JButton("NEW CARD");
		btnNewCard.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewCard.setBounds(850, 536, 149, 42);
		panelFondo.add(btnNewCard);
		btnNewCard.setVisible(false);
		
		btnStand = new JButton("STAND");
		btnStand.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStand.setBounds(850, 611, 149, 42);
		panelFondo.add(btnStand);
		btnStand.setVisible(false);
		
		lblScoreHistoryCroupier = new JLabel("SCORE HISTORY ");
		lblScoreHistoryCroupier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScoreHistoryCroupier.setBounds(331, 237, 299, 25);
		panelFondo.add(lblScoreHistoryCroupier);
		
		lblScoreHistoryPlayer = new JLabel("SCORE HISTORY");
		lblScoreHistoryPlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblScoreHistoryPlayer.setBounds(331, 441, 311, 31);
		panelFondo.add(lblScoreHistoryPlayer);
		
		lblTotalScoreCroupier = new JLabel("TOTAL SCORE");
		lblTotalScoreCroupier.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalScoreCroupier.setBounds(707, 237, 142, 25);
		panelFondo.add(lblTotalScoreCroupier);
		
		lblTotalScorePlayer = new JLabel("TOTAL SCORE");
		lblTotalScorePlayer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTotalScorePlayer.setBounds(707, 441, 149, 31);
		panelFondo.add(lblTotalScorePlayer);
		
		btnLoadCards = new JButton("LOAD CARDS !!!");
		btnLoadCards.setForeground(new Color(0, 0, 0));
		btnLoadCards.setBackground(new Color(255, 255, 0));
		btnLoadCards.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoadCards.setBounds(21, 41, 202, 42);
		panelFondo.add(btnLoadCards);
		
		btnPartidasGuardadas = new JButton("HALL OF FAME");
		btnPartidasGuardadas.setForeground(Color.BLACK);
		btnPartidasGuardadas.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnPartidasGuardadas.setBackground(new Color(230, 230, 250));
		btnPartidasGuardadas.setBounds(95, 564, 202, 42);
		panelFondo.add(btnPartidasGuardadas);
		
		ImageIcon rutaImg = new ImageIcon("./src/AEV3_Mongo/apagar.jpeg");
		Image img = rutaImg.getImage();
		Image scaledImage = img.getScaledInstance(109, 100, java.awt.Image.SCALE_SMOOTH);
		ImageIcon imageIconScaled = new ImageIcon(scaledImage);
		
		btnLogout = new JButton("");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLogout.setIcon(imageIconScaled);
		btnLogout.setBounds(1005, 27, 109, 100);
		panelFondo.add(btnLogout);
		
		btnStart = new JButton("¡ START !");
		btnStart.setForeground(Color.RED);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnStart.setBounds(493, 330, 149, 42);
		panelFondo.add(btnStart);
		btnStart.setVisible(false);
		
		this.setVisible(false);
	}
	
	
	public JLabel getCartaJugador(int indice) {
		switch(indice) {
			case 1:
				return cartaJugador1;
			case 2:
				return cartaJugador2;
			case 3:
				return cartaJugador3;
			case 4:
				return cartaJugador4;
			case 5:
				return cartaJugador5;
			case 6:
				return cartaJugador6;
			case 7:
				return cartaJugador7;
			case 8:
				return cartaJugador8;
			case 9:
				return cartaJugador9;
			case 10:
				return cartaJugador10;
			case 11:
				return cartaJugador11;
			case 12:
				return cartaJugador12;
			default:
	            throw new IllegalArgumentException("Índice de carta del Jugador no válido: " + indice);
		}
	}
	
	public JLabel getCartaCroupier(int indice) {
		switch(indice) {
			case 1:
				return cartaCroupier1;
			case 2:
				return cartaCroupier2;
			case 3:
				return cartaCroupier3;
			case 4:
				return cartaCroupier4;
			case 5:
				return cartaCroupier5;
			case 6:
				return cartaCroupier6;
			case 7:
				return cartaCroupier7;
			case 8:
				return cartaCroupier8;
			case 9:
				return cartaCroupier9;
			case 10:
				return cartaCroupier10;
			default:
	            throw new IllegalArgumentException("Índice de carta del crupier no válido: " + indice);
		}
	}
	
	// Setters y Getters de los Labels
	public JLabel getLblScoreHistoryCroupier() {
	    return lblScoreHistoryCroupier;
	}

	public JLabel getLblScoreHistoryPlayer() {
	    return lblScoreHistoryPlayer;
	}

	public JLabel getLblTotalScoreCroupier() {
	    return lblTotalScoreCroupier;
	}

	public JLabel getLblTotalScorePlayer() {
	    return lblTotalScorePlayer;
	}

	// Setters y Getters de los Buttons
	public JButton getBtnNewCard() {
	    return btnNewCard;
	}

	public void setBtnNewCard(JButton btnNewCard) {
	    this.btnNewCard = btnNewCard;
	}

	public JButton getBtnStand() {
	    return btnStand;
	}

	public void setBtnStand(JButton btnStand) {
	    this.btnStand = btnStand;
	}

	public JButton getBtnLoadCards() {
	    return btnLoadCards;
	}

	public void setBtnLoadCards(JButton btnLoadCards) {
	    this.btnLoadCards = btnLoadCards;
	}

	public JButton getBtnPartidasGuardadas() {
	    return btnPartidasGuardadas;
	}

	public void setBtnPartidasGuardadas(JButton btnPartidasGuardadas) {
	    this.btnPartidasGuardadas = btnPartidasGuardadas;
	}

	public JButton getBtnLogout() {
	    return btnLogout;
	}

	public void setBtnLogout(JButton btnLogout) {
	    this.btnLogout = btnLogout;
	}

	public JButton getBtnStart() {
	    return btnStart;
	}

	public void setBtnStart(JButton btnStart) {
	    this.btnStart = btnStart;
	}


	public JLabel getCartaJugador1() {
		return cartaJugador1;
	}


	public JLabel getCartaJugador2() {
		return cartaJugador2;
	}


	public JLabel getCartaJugador3() {
		return cartaJugador3;
	}


	public JLabel getCartaJugador4() {
		return cartaJugador4;
	}


	public JLabel getCartaJugador5() {
		return cartaJugador5;
	}


	public JLabel getCartaJugador6() {
		return cartaJugador6;
	}


	public JLabel getCartaJugador7() {
		return cartaJugador7;
	}


	public JLabel getCartaJugador8() {
		return cartaJugador8;
	}


	public JLabel getCartaJugador9() {
		return cartaJugador9;
	}


	public JLabel getCartaJugador10() {
		return cartaJugador10;
	}


	public JLabel getCartaJugador11() {
		return cartaJugador11;
	}


	public JLabel getCartaJugador12() {
		return cartaJugador12;
	}


	public JLabel getCartaCroupier1() {
		return cartaCroupier1;
	}


	public JLabel getCartaCroupier2() {
		return cartaCroupier2;
	}


	public JLabel getCartaCroupier3() {
		return cartaCroupier3;
	}


	public JLabel getCartaCroupier4() {
		return cartaCroupier4;
	}


	public JLabel getCartaCroupier5() {
		return cartaCroupier5;
	}


	public JLabel getCartaCroupier6() {
		return cartaCroupier6;
	}


	public JLabel getCartaCroupier7() {
		return cartaCroupier7;
	}


	public JLabel getCartaCroupier8() {
		return cartaCroupier8;
	}


	public JLabel getCartaCroupier9() {
		return cartaCroupier9;
	}


	public JLabel getCartaCroupier10() {
		return cartaCroupier10;
	}
	
	
}
