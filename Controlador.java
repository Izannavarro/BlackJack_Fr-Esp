package AEV3_Mongo;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Clase Controlador que coordina la interacción entre las vistas y el modelo de la aplicación.
 */
public class Controlador {
	
	// Vistas de la aplicación
	private VistaLogin vLogin;
	private VistaRegistro vRegistro;
	private VistaPrincipal vPrincipal;
	private VistaPuntuaciones vPuntuaciones;
	
	private Model model;

	// Manejadores de eventos de los botones en las vistas
	private ActionListener actionlistener_btnAbrirRegistro, actionlistener_btnLogin, actionlistener_btnRegistro,
			actionlistener_btnCargarCartas, actionlistener_btnStart, actionlistener_btnNewCard,
			actionlistener_btnHallFame, actionlistener_btnStand, actionlistener_btnLogout;

	/**
	 * Constructor de la clase Controlador que inicializa las vistas y el modelo.
	 * También configura los manejadores de eventos.
	 * 
	 * @param vL   Vista de inicio de sesión.
	 * @param vR   Vista de registro.
	 * @param vPri Vista principal.
	 * @param vPun Vista de puntuaciones.
	 * @param model Modelo de datos de la aplicación.
	 */
	public Controlador(VistaLogin vL, VistaRegistro vR, VistaPrincipal vPri, VistaPuntuaciones vPun, Model model)
			throws IOException {
		this.vLogin = vL;
		this.vRegistro = vR;
		this.vPrincipal = vPri;
		this.vPuntuaciones = vPun;
		this.model = model;
		initEventHandlers();
	}
	
	/**
	 * Limpia los campos de entrada de la vista de inicio de sesión.
	 */
	private void limpiarLogin() {
		vLogin.getPassword().setText("");
		vLogin.getTxtName().setText("");
		vLogin.getRepeatedPassword().setText("");
	}

	/**
	 * Limpia los campos de entrada de la vista de registro.
	 */
	private void limpiarRegistro() {
		vRegistro.getTextName().setText("");
		vRegistro.getPassword().setText("");
		vRegistro.getRepeatedPassword().setText("");
	}

	/**
	 * Restablece los elementos de la interfaz de la vista principal a su estado inicial.
	 */
	private void limpiarPrincipal() {
		vPrincipal.getBtnStart().setVisible(true);
		vPrincipal.getBtnNewCard().setVisible(false);
		vPrincipal.getBtnStand().setVisible(false);
		vPrincipal.getBtnPartidasGuardadas().setVisible(true);
		vPrincipal.getBtnLoadCards().setVisible(false);

		JOptionPane.showMessageDialog(null, "Pulse el botón 'START' si desea volver a jugar! ", "Información",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Configura la interfaz para el inicio de una nueva partida, incluyendo la limpieza de elementos visuales y variables del modelo.
	 */
	private void cargarComienzoPartida() {
		vPrincipal.getBtnNewCard().setVisible(true);
		vPrincipal.getBtnStand().setVisible(true);
		vPrincipal.getBtnPartidasGuardadas().setVisible(true);
		vPrincipal.getBtnLoadCards().setVisible(false);
		vPrincipal.getBtnStart().setVisible(false);
		vPrincipal.getLblScoreHistoryCroupier().setText("SCORE HISTORY");
		vPrincipal.getLblScoreHistoryPlayer().setText("SCORE HISTORY");
		vPrincipal.getLblTotalScoreCroupier().setText("TOTAL SCORE");
		vPrincipal.getLblTotalScorePlayer().setText("TOTAL SCORE");
		
		vPrincipal.getCartaJugador1().setIcon(null);
		vPrincipal.getCartaJugador2().setIcon(null);
		vPrincipal.getCartaJugador3().setIcon(null);
		vPrincipal.getCartaJugador4().setIcon(null);
		vPrincipal.getCartaJugador5().setIcon(null);
		vPrincipal.getCartaJugador6().setIcon(null);
		vPrincipal.getCartaJugador7().setIcon(null);
		vPrincipal.getCartaJugador8().setIcon(null);
		vPrincipal.getCartaJugador9().setIcon(null);
		vPrincipal.getCartaJugador10().setIcon(null);
		vPrincipal.getCartaJugador11().setIcon(null);
		vPrincipal.getCartaJugador12().setIcon(null);

		vPrincipal.getCartaCroupier1().setIcon(null);
		vPrincipal.getCartaCroupier2().setIcon(null);
		vPrincipal.getCartaCroupier3().setIcon(null);
		vPrincipal.getCartaCroupier4().setIcon(null);
		vPrincipal.getCartaCroupier5().setIcon(null);
		vPrincipal.getCartaCroupier6().setIcon(null);
		vPrincipal.getCartaCroupier7().setIcon(null);
		vPrincipal.getCartaCroupier8().setIcon(null);
		vPrincipal.getCartaCroupier9().setIcon(null);
		vPrincipal.getCartaCroupier10().setIcon(null);
		
		model.setCroupierPoints(0);
		model.setPlayerPoints(0);
		model.setPlayerStands(false);
		model.setCroupierStands(false);
		
		if(model.getCards() != null) {
			model.getCards().clear();
		}
		
		if(model.getCroupierCards() != null) {
			model.getCroupierCards().clear();
		}
		
		if(model.getPlayerCards() != null) {
			model.getPlayerCards().clear();
		}
	}

	public void initEventHandlers() throws IOException {

		// Aquí va lo que tenga que arrancar antes de darle a nada
		model.conexionBBDD();

		actionlistener_btnLogin = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				String userName = vLogin.getTxtName().getText().trim();
				String pwd = vLogin.getPassword().getText();
				String repeatedPwd = vLogin.getRepeatedPassword().getText();
				boolean comprobacion;
				String hashedPwd;

				if (!userName.isEmpty() && !pwd.isEmpty() && !repeatedPwd.isEmpty()) {
					if (pwd.equals(repeatedPwd)) {
						hashedPwd = model.hashSHA256(pwd);
						comprobacion = model.usuarioExistenteBBDD(userName, hashedPwd);
						if (comprobacion) {
							JOptionPane.showMessageDialog(null, "Iniciaste sesión correctamente: " + userName + "!",
									"Información", JOptionPane.INFORMATION_MESSAGE);
							model.setNomUsuari(userName);

							//LIMPIAR LA VISTA PRINCIPAL SIN MOSTRAR EL JOPTIONPANE
							vPrincipal.getBtnStart().setVisible(true);
							vPrincipal.getBtnNewCard().setVisible(false);
							vPrincipal.getBtnStand().setVisible(false);
							vPrincipal.getBtnPartidasGuardadas().setVisible(true);
							vPrincipal.getBtnLoadCards().setVisible(false);
							
							vLogin.setVisible(false);
							vPrincipal.getBtnStart().setVisible(false);
							vPrincipal.getBtnLoadCards().setVisible(true);
							vPrincipal.setVisible(true);
							
						} else {
							JOptionPane.showMessageDialog(null, "No estás dado de alta como usuario!", "ERROR",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas introducidas son incorrectas! ", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No has completado todos los campos! ", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		actionlistener_btnAbrirRegistro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vRegistro.setVisible(true);
			}
		};

		actionlistener_btnRegistro = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = vRegistro.getTextName().getText().trim();
				String pwd = vRegistro.getPassword().getText();
				String repeatedPwd = vRegistro.getRepeatedPassword().getText();
				boolean comprobacion;
				String hashedPwd;

				if (!userName.isEmpty() && !pwd.isEmpty() && !repeatedPwd.isEmpty()) {
					if (pwd.equals(repeatedPwd)) {
						hashedPwd = model.hashSHA256(pwd);
						comprobacion = model.usuarioExistenteBBDD(userName, hashedPwd);
						if (comprobacion) {
							JOptionPane.showMessageDialog(null,
									"Ya estás dado de alta " + userName + ", Inicia Sesión!", "Información",
									JOptionPane.INFORMATION_MESSAGE);
							vRegistro.setVisible(false);
						} else {
							model.añadirUsuarioBBDD(userName, hashedPwd);
							JOptionPane.showMessageDialog(null, "Usuario --> " + userName + ", añadido con éxito!",
									"Información", JOptionPane.INFORMATION_MESSAGE);
							limpiarRegistro();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Las contraseñas deben coincidir! ", "Información",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No has completado todos los campos! ", "Información",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		actionlistener_btnCargarCartas = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (model.cargarBarajasEnMongoDB()) {
						vPrincipal.getBtnLoadCards().setVisible(false);
						vPrincipal.getBtnStart().setVisible(true);
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};

		actionlistener_btnStart = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cargarComienzoPartida();
				int eleccion = model.empezarPartida();
				if (eleccion != -1) {
					if (eleccion == 0) {
						//Cargar Imagen
						Image img = model.turnoJugador();
						JLabel card1 = vPrincipal.getCartaJugador(model.getPlayerCards().size());
						int width = card1.getWidth();
						int height = card1.getHeight();
						Image scaledImage2 = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgPlayer = new ImageIcon(scaledImage2);
						card1.setIcon(imgPlayer);
						
						//Cargar Puntuaciones
						vPrincipal.getLblTotalScorePlayer().setText(String.valueOf(model.getPlayerPoints()));
			
						String historialJugador = "";
						for(String num : model.getPlayerCards()) {
							historialJugador += num +", ";
						}
						vPrincipal.getLblScoreHistoryPlayer().setText(String.valueOf(historialJugador));
						
						//Turno Croupier
						//Cargar Imagen
						Image img2 = model.turnoCroupier();
						JLabel card2 = vPrincipal.getCartaCroupier(model.getCroupierCards().size());
						int width2 = card2.getWidth();
						int height2 = card2.getHeight();
						Image scaledImage = img2.getScaledInstance(width2, height2, java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgCroupier = new ImageIcon(scaledImage);
						card2.setIcon(imgCroupier);
						
						//Cargar Puntuaciones
						vPrincipal.getLblTotalScoreCroupier().setText(String.valueOf(model.getCroupierPoints()));
						
						String historialCroupier = "";
						for(String num : model.getCroupierCards()) {
							historialCroupier += num +", ";
						}
						vPrincipal.getLblScoreHistoryCroupier().setText(String.valueOf(historialCroupier));
						
					} else {
						//Cargar Imagen
						Image img2 = model.turnoCroupier();
						JLabel card2 = vPrincipal.getCartaCroupier(model.getCroupierCards().size());
						int width2 = card2.getWidth();
						int height2 = card2.getHeight();
						Image scaledImage = img2.getScaledInstance(width2, height2, java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgCroupier = new ImageIcon(scaledImage);
						card2.setIcon(imgCroupier);
						
						//Cargar Puntuaciones
						vPrincipal.getLblTotalScoreCroupier().setText(String.valueOf(model.getCroupierPoints()));
						
						String historialCroupier = "";
						for(String num : model.getCroupierCards()) {
							historialCroupier += num +", ";
						}
						vPrincipal.getLblScoreHistoryCroupier().setText(String.valueOf(historialCroupier));
					}
				} else {
					JOptionPane.showMessageDialog(null, "No se ha podido empezar la partida! ", "ERROR",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		};

		actionlistener_btnNewCard = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Cargar Imagen
				Image img = model.turnoJugador();
				JLabel card1 = vPrincipal.getCartaJugador(model.getPlayerCards().size());
				int width = card1.getWidth();
				int height = card1.getHeight();
				Image scaledImage2 = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
				ImageIcon imgPlayer = new ImageIcon(scaledImage2);
				card1.setIcon(imgPlayer);
				
				//Cargar Puntuaciones
				vPrincipal.getLblTotalScorePlayer().setText(String.valueOf(model.getPlayerPoints()));
	
				String historialJugador = "";
				for(String num : model.getPlayerCards()) {
					historialJugador += num +", ";
				}
				vPrincipal.getLblScoreHistoryPlayer().setText(String.valueOf(historialJugador));

				if (model.getPlayerPoints() > 21) {
					model.setPlayerStands(true);
					model.setCroupierStands(true);
					
					JOptionPane.showMessageDialog(null, "¡Perdiste " + model.getNomUsuari() + "! :(", "Resultado", JOptionPane.INFORMATION_MESSAGE);
					limpiarPrincipal();
					
				} else if (model.getPlayerPoints() == 21 && model.getCroupierStands() && model.getCroupierPoints() < 21)  {
					model.setPlayerStands(true);
					model.setCroupierStands(true);
					
					JOptionPane.showMessageDialog(null, "¡BLACKJACK de: " + model.getNomUsuari() + ", Ganaste!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
					model.guardarPartida();
					limpiarPrincipal();
				} else if(model.getPlayerPoints() == 21 && model.getCroupierPoints() == 21) {
					model.setPlayerStands(true);
					model.setCroupierStands(true);
					JOptionPane.showMessageDialog(null, "¡EMPATE DE " + model.getNomUsuari() + " Y EL CROUPIER!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
					limpiarPrincipal();
				} else {
					if (model.getCroupierStands() == false) {
						//Cargar Imagen
						Image img2 = model.turnoCroupier();
						JLabel card2 = vPrincipal.getCartaCroupier(model.getCroupierCards().size());
						int width2 = card2.getWidth();
						int height2 = card2.getHeight();
						Image scaledImage = img2.getScaledInstance(width2, height2, java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgCroupier = new ImageIcon(scaledImage);
						card2.setIcon(imgCroupier);
						
						//Cargar Puntuaciones
						vPrincipal.getLblTotalScoreCroupier().setText(String.valueOf(model.getCroupierPoints()));
						
						String historialCroupier = "";
						for(String num : model.getCroupierCards()) {
							historialCroupier += num +", ";
						}
						vPrincipal.getLblScoreHistoryCroupier().setText(String.valueOf(historialCroupier));
						
						if(model.getCroupierPoints() > 21) {
							JOptionPane.showMessageDialog(null, "¡Ganaste " + model.getNomUsuari() + "!", "Resultado",
									JOptionPane.INFORMATION_MESSAGE);
							model.guardarPartida();
							limpiarPrincipal();
						}
				
					} else {
		
//						if(model.getPlayerPoints() > model.getCroupierPoints()) {
//							model.compararJugada();
//							limpiarPrincipal();
//						}
						
						if (model.getCroupierStands() && model.getPlayerStands()) {
							model.compararJugada();
							limpiarPrincipal();
						} else {
							JOptionPane.showMessageDialog(null, "Te toca otra vez! ", "Información",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		};

		actionlistener_btnStand = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				JOptionPane.showMessageDialog(null,model.getNomUsuari() + " se planta!", "Jugador se planta", JOptionPane.INFORMATION_MESSAGE);
				model.setPlayerStands(true);

				if (model.getCroupierStands()) {
					model.compararJugada();
					limpiarPrincipal();
				} else {
					while (!model.getCroupierStands()) {
						//Cargar Imagen
						Image img2 = model.turnoCroupier();
						JLabel card2 = vPrincipal.getCartaCroupier(model.getCroupierCards().size());
						int width2 = card2.getWidth();
						int height2 = card2.getHeight();
						Image scaledImage = img2.getScaledInstance(width2, height2, java.awt.Image.SCALE_SMOOTH);
						ImageIcon imgCroupier = new ImageIcon(scaledImage);
						card2.setIcon(imgCroupier);
						
						//Cargar Puntuaciones
						vPrincipal.getLblTotalScoreCroupier().setText(String.valueOf(model.getCroupierPoints()));
						
						String historialCroupier = "";
						for(String num : model.getCroupierCards()) {
							historialCroupier += num +", ";
						}
						vPrincipal.getLblScoreHistoryCroupier().setText(String.valueOf(historialCroupier));
					}
					model.compararJugada();
					limpiarPrincipal();
				}
			}
		};

		actionlistener_btnHallFame = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vPuntuaciones.setVisible(true);
				String partidasGuardadas = model.cargarPartidasGuardadas();
				vPuntuaciones.getTxtAreaPuntuaciones().setText(partidasGuardadas);
			}
		};

		actionlistener_btnLogout = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarLogin();
				vLogin.setVisible(true);
				vPrincipal.setVisible(false);
				cargarComienzoPartida();

				vPrincipal.getBtnStart().setVisible(false);
				vPrincipal.getBtnNewCard().setVisible(false);
				vPrincipal.getBtnStand().setVisible(false);
				vPrincipal.getBtnPartidasGuardadas().setVisible(true);
				vPrincipal.getBtnLoadCards().setVisible(true);
			}
		};

		vLogin.getBtnLogin().addActionListener(actionlistener_btnLogin);
		vLogin.getBtnRegistro().addActionListener(actionlistener_btnAbrirRegistro);
		vRegistro.getBtnRegistrar().addActionListener(actionlistener_btnRegistro);
		vPrincipal.getBtnLoadCards().addActionListener(actionlistener_btnCargarCartas);
		vPrincipal.getBtnStart().addActionListener(actionlistener_btnStart);
		vPrincipal.getBtnNewCard().addActionListener(actionlistener_btnNewCard);
		vPrincipal.getBtnPartidasGuardadas().addActionListener(actionlistener_btnHallFame);
		vPrincipal.getBtnStand().addActionListener(actionlistener_btnStand);
		vPrincipal.getBtnLogout().addActionListener(actionlistener_btnLogout);
	}
}
