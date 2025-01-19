package AEV3_Mongo;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.json.*;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import java.nio.file.Files;
import java.nio.file.Paths;
import org.json.JSONObject;

public class Model {

	// Información extraída del Json
	private String ip;
	private Integer port;
	private String bbddName;
	private String nomUsuari;
	private String contrasenya;
	private String coleccionUsers;
	private String coleccionScores;
	private String coleccionCards_es;
	private String coleccionCards_fr;

	// Variables de la lógica del juego
	private int playerPoints = 0;
	private int croupierPoints = 0;

	private List<String> cards = new ArrayList<>();
	private List<String> playerCards = new ArrayList<>();
	private List<String> croupierCards = new ArrayList<>();

	private boolean playerStands = false;
	private boolean croupierStands = false;

	private MongoDatabase bbdd;
	private String barajaSeleccionada;

	/**
	 * Genera un timestamp (sello de tiempo) en formato "dd/MM-HH:mm:ss", que representa la fecha
	 * y la hora actual del sistema.
	 * 
	 * El timestamp generado sigue el patrón de formato especificado, con el día del mes, 
	 * la hora (en formato de 24 horas), minutos y segundos.
	 *
	 * @return Una cadena de texto que contiene el timestamp actual en formato "dd/MM-HH:mm:ss".
	 */
	public String agregarTimeStamp() {
		LocalDateTime ahora = LocalDateTime.now();

		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM-HH:mm:ss");
		String timeStamp = ahora.format(formato);

		return timeStamp;
	}

	public String hashSHA256(String input) {
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] encodedhash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

			return bytesToHex(encodedhash);

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Error al generar el hash SHA-256", e);
		}
	}

	/**
	 * Genera un hash SHA-256 para un texto de entrada proporcionado.
	 * 
	 * Utiliza el algoritmo de hashing SHA-256 para generar una representación segura de la cadena de texto
	 * proporcionada como entrada. El resultado es un valor de 256 bits expresado en formato hexadecimal.
	 *
	 * @param input La cadena de texto que se desea hashear (en formato UTF-8).
	 * @return El hash SHA-256 generado como una cadena de texto en formato hexadecimal.
	 */
	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder();
		for (byte b : hash) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	/**
	 * Establece una conexión con la base de datos MongoDB utilizando la información de configuración
	 * cargada desde un archivo JSON.
	 * 
	 * Este método lee los datos de configuración (como la IP, puerto, nombre de la base de datos,
	 * y colecciones específicas) desde un archivo JSON y utiliza esa información para configurar
	 * la conexión a MongoDB. El archivo JSON debe contener los campos necesarios para la conexión.
	 * Si los valores son válidos, se crea un cliente de MongoDB y se establece la conexión con
	 * la base de datos.
	 * 
	 * Si no se encuentra la información necesaria o ocurre un error al intentar conectar,
	 * se mostrará un mensaje de advertencia al usuario.
	 */
	public void conexionBBDD() throws IOException {
		// Ruta del fitxer JSON
		String filePath = "./src/AEV3_Mongo/config.json";

		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		StringBuilder jsonContentBuilder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			jsonContentBuilder.append(line);
		}
		reader.close();

		String jsonContent = jsonContentBuilder.toString();

		JSONObject jsonObject = new JSONObject(jsonContent);

		this.ip = (String) jsonObject.get("ip");
		this.port = (Integer) jsonObject.get("port");
		this.bbddName = (String) jsonObject.get("baseDeDades");
		this.coleccionUsers = (String) jsonObject.get("collectionUsers");
		this.coleccionScores = (String) jsonObject.get("collectionScores");
		this.coleccionCards_es = (String) jsonObject.get("collectionCards_es");
		this.coleccionCards_fr = (String) jsonObject.get("collectionCards_fr");
		this.nomUsuari = (String) jsonObject.get("user");
		this.contrasenya = (String) jsonObject.get("password");

		// ENTRAR CON NOMBRE Y CONTRASENYA DEL MONGOCLIENT PROPORCIONADOS PREVIAMENTE
//		if (ip != null && port != null && nomUsuari != null && contrasenya != null) {
//			try {
//                // Construir la URI utilizando los atributos
//                String uri = String.format(
//                        "mongodb://%s:%s@%s:%d/%s",
//                        nomUsuari,
//                        contrasenya,
//                        ip,
//                        port,
//                        bbddName
//                );
//
//                // Crear el cliente Mongo
//                MongoClient mongoClient = new MongoClient(uri);
//
//                // Obtener la base de datos
//                this.bbdd = mongoClient.getDatabase(bbddName);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Error al conectar con MongoDB: " + e.getMessage(), "Error",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//		} else {
//            JOptionPane.showMessageDialog(null, "Faltan datos para la conexión, revisa la configuración", "Advertencia",
//                    JOptionPane.WARNING_MESSAGE);
//        }

		if (ip != null && port != null) {
			try {
				MongoClient mongoClient = new MongoClient(ip, port);
				this.bbdd = mongoClient.getDatabase(bbddName);
			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(null, "No tienes IP o puerto añadido, compruébalo", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	/**
	 * Verifica si un usuario con el nombre de usuario y la contraseña (en formato hash) existe en la base de datos MongoDB.
	 * 
	 * Este método busca un documento en la colección de usuarios (`coleccionUsers`) de la base de datos MongoDB.
	 * Si se encuentra un documento que coincide con el nombre de usuario y la contraseña (hash) proporcionada,
	 * el método devuelve `true`, lo que indica que el usuario existe en la base de datos. Si no se encuentra,
	 * el método retorna `false`.
	 * 
	 * @param name Nombre del usuario que se desea comprobar.
	 * @param pwdHashed Contraseña del usuario en formato hash.
	 * 
	 * @return `true` si el usuario existe en la base de datos con el nombre y la contraseña proporcionados,
	 *         de lo contrario, retorna `false`.
	 */
	public boolean usuarioExistenteBBDD(String name, String pwdHashed) {
		if (this.bbdd != null) {
			MongoCollection<Document> users = bbdd.getCollection(coleccionUsers);

			Document filter = new Document("user", name).append("password", pwdHashed);

			MongoCursor<Document> cursor = users.find(filter).iterator();

			while (cursor.hasNext()) {
				this.nomUsuari = name;
				return true;
			}
		}
		return false;
	}

	/**
	 * Añade un nuevo usuario con su nombre y contraseña (en formato hash) a la base de datos MongoDB.
	 * 
	 * Este método crea un nuevo documento con los datos del usuario (nombre y contraseña) y lo inserta en la 
	 * colección de usuarios (`coleccionUsers`) de la base de datos MongoDB. 
	 * Si la base de datos no está conectada, el método no realiza ninguna operación.
	 *
	 * @param name Nombre del usuario que se desea agregar.
	 * @param pwdHashed Contraseña del usuario en formato hash.
	 */
	public void añadirUsuarioBBDD(String name, String pwdHashed) {

		if (this.bbdd != null) {
			MongoCollection<Document> users = bbdd.getCollection(coleccionUsers);

			Document user = new Document();
			user.append("user", name);
			user.append("password", pwdHashed);
			users.insertOne(user);
		}
	}

	/**
	 * Carga las imágenes de las barajas de cartas (española y francesa) desde el sistema de archivos y las 
	 * almacena en la base de datos MongoDB en formato Base64.
	 *
	 * Este método lee los archivos de imagen correspondientes a las cartas de la baraja española 
	 * y francesa desde dos directorios predefinidos. Luego, convierte las imágenes a formato Base64 y las 
	 * inserta en las colecciones de MongoDB relacionadas con la baraja española y francesa. Si la colección 
	 * ya existe, se elimina previamente.
	 * 
	 * Si alguna imagen no se puede cargar, el proceso se cancela y el método devuelve `false`. 
	 * Si todas las cartas se cargan correctamente, el método devuelve `true`.
	 *
	 * @return `true` si las imágenes de las cartas han sido cargadas correctamente en la base de datos, 
	 *         `false` si ocurre algún error al cargar las imágenes.
	 */
	public boolean cargarBarajasEnMongoDB() throws IOException {

		File directorioEsp = new File("./src/AEV3_Mongo/img/cards_es");
		File directorioFr = new File("./src/AEV3_Mongo/img/cards_fr");

		File[] fitxersBarajaEsp = directorioEsp.listFiles();
		File[] fitxersBarajaFr = directorioFr.listFiles();

		List<Document> barajaEsp = new ArrayList<>();
		List<Document> barajaFr = new ArrayList<>();

		if (bbdd.getCollection(coleccionCards_es) != null) {
			bbdd.getCollection(coleccionCards_es).drop();
		}
		if (bbdd.getCollection(coleccionCards_fr) != null) {
			bbdd.getCollection(coleccionCards_fr).drop();
		}

		MongoCollection<Document> coleccionEsp = bbdd.getCollection(coleccionCards_es);
		MongoCollection<Document> coleccionFr = bbdd.getCollection(coleccionCards_fr);

		for (File carta : fitxersBarajaEsp) {
			if (carta.isFile() && carta.exists()) {

				String nombreArchivo = carta.getName().replace(".jpg", "");
				String[] partes = nombreArchivo.split("_");

				String puntos = partes[1];
				String palo = partes[0];
				String base64 = ImageToBase64(carta);

				if (base64 != null) { // Solo si se encontró la imagen
					Document ca = new Document("suit", palo).append("points", puntos).append("base64", base64);
					barajaEsp.add(ca);
				} else {
					JOptionPane.showMessageDialog(null, "No se han podido cargar las imágenes!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}

		// Crear baraja francesa
		for (File carta : fitxersBarajaFr) {
			if (carta.isFile() && carta.exists()) {

				String nombreArchivo = carta.getName().replace(".png", "");
				String[] partes = nombreArchivo.split("_");

				String puntos = partes[1];
				String palo = partes[0];
				String base64 = ImageToBase64(carta);

				if (base64 != null) { // Solo si se encontró la imagen
					Document ca = new Document("suit", palo).append("points", puntos).append("base64", base64);
					barajaFr.add(ca);
				} else {
					JOptionPane.showMessageDialog(null, "No se han podido cargar las imágenes!", "Error",
							JOptionPane.ERROR_MESSAGE);
					return false;
				}
			}
		}

		coleccionEsp.insertMany(barajaEsp);
		coleccionFr.insertMany(barajaFr);

		JOptionPane.showMessageDialog(null, "Las cartas han sido cargadas correctamente en la BBDD!", "CARDS LOADES!",
				JOptionPane.INFORMATION_MESSAGE);

		return true;
	}

	/**
	 * Carga una imagen en base64 dado un fichero.
	 *
	 * @param fitxer fichero que será una imagen.
	 * @return La imagen codificada en base64 o null si no se encontró.
	 * @throws IOException
	 */
	public static String ImageToBase64(File fitxer) throws IOException {
		FileInputStream fis = new FileInputStream(fitxer);
		byte[] fileBytes = fis.readAllBytes();
		fis.close();
		return Base64.getEncoder().encodeToString(fileBytes);

	}

	/**
	 * Convierte una cadena de texto en formato Base64 a un objeto de imagen (BufferedImage).
	 *
	 * Este método toma una cadena de texto codificada en Base64, la decodifica a bytes, y luego convierte esos 
	 * bytes en un objeto `BufferedImage` que puede ser utilizado para mostrar o manipular imágenes en Java.
	 * Si ocurre algún error en el proceso de decodificación o en la creación de la imagen, el método devuelve `null`.
	 *
	 * @param base64Bytes La cadena de texto codificada en Base64 que representa la imagen.
	 * @return Un objeto `BufferedImage` que representa la imagen decodificada, o `null` si hubo algún error en el proceso.
	 */
	public static BufferedImage base64ToImage(String base64Bytes) {
		try {

			byte[] imageBytes = Base64.getDecoder().decode(base64Bytes);
			// Convertir los bytes a un BufferedImage
			ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);

			return ImageIO.read(bais);
		} catch (Exception e) {
			System.err.println("Error al convertir Base64 a Imagen: " + e.getMessage());
			return null;
		}
	}

	/**
	 * Inicia una nueva partida de cartas, permitiendo al usuario seleccionar la baraja con la que quiere jugar
	 * (española o francesa) y quién comenzará el juego (jugador o crupier).
	 * 
	 * El método despliega cuadros de diálogo para permitir al usuario realizar las selecciones, carga la baraja
	 * correspondiente desde la base de datos, la mezcla, y devuelve quién será el primero en jugar.
	 * 
	 * @return Un entero que indica quién comienza el juego:
	 *         <ul>
	 *         <li>0 si el jugador comienza.</li>
	 *         <li>1 si el crupier comienza.</li>
	 *         </ul>
	 *         Si el usuario cancela alguna selección, no se inicia la partida.
	 */
	public int empezarPartida() {

		// Mostrar opciones al usuario
		String[] opciones = { "B. Española", "B. Francesa" };
		int eleccion = JOptionPane.showOptionDialog(null, "¿Con qué baraja quieres jugar?", "Seleccionar Baraja",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

		// Validar selección del usuario
		if (eleccion == -1) {
			JOptionPane.showMessageDialog(null, "No se seleccionó ninguna baraja. Operación cancelada.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}

		this.barajaSeleccionada = (eleccion == 0) ? coleccionCards_es : coleccionCards_fr;
		MongoCollection<Document> baraja = this.bbdd.getCollection(barajaSeleccionada);

		try (MongoCursor<Document> cursor = baraja.find().iterator()) {
			while (cursor.hasNext()) {
				Document carta = cursor.next();
				String puntos = carta.getString("points");
				String palo = carta.getString("suit");

				cards.add(puntos + "_" + palo);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ocurrió un error al procesar la partida: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

		Collections.shuffle(cards);

		String[] turno = { "Jugador", "Croupier" };
		int eleccion2 = JOptionPane.showOptionDialog(null, "¿Quién empieza?", "Selecciona", JOptionPane.DEFAULT_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, turno, turno[0]);

		// Validar selección del usuario
		if (eleccion2 == -1) {
			JOptionPane.showMessageDialog(null, "No se seleccionó quien empieza. Operación cancelada.", "Advertencia",
					JOptionPane.WARNING_MESSAGE);
		}

		return eleccion2;
	}

	/**
	 * Maneja el turno del jugador en el juego de cartas. Durante el turno:
	 * <ul>
	 * <li>Muestra un mensaje indicando al jugador que es su turno.</li>
	 * <li>Saca una carta de la baraja, actualiza los puntos del jugador, y guarda la carta en su lista de cartas.</li>
	 * <li>Gestiona casos especiales, como la elección del valor del As (1 o 11).</li>
	 * <li>Busca la carta extraída en la base de datos para obtener su imagen en formato Base64 y la convierte a un objeto {@link Image}.</li>
	 * </ul>
	 * 
	 * @return Un objeto Image que representa la imagen de la carta extraída. Devuelve null si no se puede cargar la carta.
	 */
	public Image turnoJugador() {

		JOptionPane.showMessageDialog(null, "¡Te toca " + nomUsuari + "!", "Turno Jugador",
				JOptionPane.INFORMATION_MESSAGE);

		// Variables de la carta y extracción base64
		String card = cards.getFirst();
		cards.removeFirst();
		String[] parts = card.split("_");
		String cardPoints = parts[0];
		int points = Integer.parseInt(cardPoints);
		String suite = parts[1];
		String base64 = null;

		if (cardPoints != null) {
			if (points == 1 && (this.playerPoints + 11) <= 21) {
				String[] opciones = { "1", "11" };
				int eleccion = JOptionPane.showOptionDialog(null, "Elige cuanto vale el 'A': ", "Elige puntos",
						JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
				if (eleccion == 0) {
					playerCards.add(cardPoints);
					playerPoints += 1;
				} else {
					playerCards.add("11");
					playerPoints += 11;
				}
			} else if (points == 1 && (this.playerPoints + 11) > 21) {
				playerCards.add(cardPoints);
				playerPoints += 1;
			} else if (points > 10) {
				playerCards.add("10");
				playerPoints += 10;
			} else {
				playerCards.add(cardPoints);
				playerPoints += points;
			}
		} else {
			JOptionPane.showMessageDialog(null, "¡No se pudo cargar la carta!", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

		if (this.bbdd != null) {
			MongoCollection<Document> cards = bbdd.getCollection(barajaSeleccionada);

			Document filter = new Document("points", cardPoints).append("suit", suite);

			MongoCursor<Document> cursor = cards.find(filter).iterator();

			while (cursor.hasNext()) {
				Document carta = cursor.next();
				base64 = carta.getString("base64");
			}
			cursor.close();
		}

		return base64ToImage(base64);

	}

	/**
	 * Maneja el turno del Croupier en el juego de cartas. Durante el turno:
	 * <ul>
	 * <li>Muestra un mensaje indicando que es el turno del Croupier.</li>
	 * <li>Extrae una carta de la baraja, actualiza los puntos del Croupier y registra la carta.</li>
	 * <li>Gestiona casos especiales, como el valor del As (1 o 11) y cartas con un valor mayor a 10.</li>
	 * <li>Busca la carta extraída en la base de datos para obtener su imagen en formato Base64 y la convierte en un objeto {@link Image}.</li>
	 * <li>Determina las acciones del Croupier según las reglas:
	 *   <ul>
	 *     <li>Si los puntos superan 21, el Croupier pierde automáticamente.</li>
	 *     <li>Si los puntos son mayores o iguales a 17, el Croupier se planta.</li>
	 *   </ul>
	 * </li>
	 * <li>Si ambos, el jugador y el Croupier, se han plantado, compara las jugadas.</li>
	 * </ul>
	 * 
	 * @return Un objeto Image que representa la imagen de la carta extraída.
	 *         Devuelve null si no se encuentra o se carga incorrectamente.
	 */
	public Image turnoCroupier() {

		JOptionPane.showMessageDialog(null, "¡Le toca al Croupier!", "Turno Croupier", JOptionPane.INFORMATION_MESSAGE);

		String card = cards.getFirst();
		cards.removeFirst();
		String[] parts = card.split("_");
		String cardPoints = parts[0];
		int points = Integer.parseInt(cardPoints);
		String suite = parts[1];
		String base64 = null;

		if (cardPoints != null) {
			if (points == 1 && (this.croupierPoints + 11) <= 21) {
				croupierCards.add("11");
				croupierPoints += 11;
			} else if (points == 1 && (this.croupierPoints + 11) > 21) {
				croupierCards.add(cardPoints);
				croupierPoints += 1;
			} else if (points > 10) {
				croupierCards.add("10");
				croupierPoints += 10;
			} else {
				croupierCards.add(cardPoints);
				croupierPoints += points;
			}
		} else {
			JOptionPane.showMessageDialog(null, "¡No se pudieron cargar las carta!", "ERROR",
					JOptionPane.ERROR_MESSAGE);
		}

		if (this.bbdd != null) {
			MongoCollection<Document> cards = bbdd.getCollection(barajaSeleccionada);

			Document filter = new Document("points", cardPoints).append("suit", suite);

			MongoCursor<Document> cursor = cards.find(filter).iterator();

			while (cursor.hasNext()) {
				Document carta = cursor.next();
				base64 = carta.getString("base64");
			}
			cursor.close();
		}

		if (croupierPoints > 21) {
			JOptionPane.showMessageDialog(null, "Croupier pierde la partida! ", "Información", JOptionPane.INFORMATION_MESSAGE);
			croupierStands = true;
		} else {
			if (croupierPoints >= 17) {
				croupierStands = true;
				JOptionPane.showMessageDialog(null, "Croupier se planta! ", "Información", JOptionPane.INFORMATION_MESSAGE);
			}
			if (croupierStands && playerStands) {
				compararJugada();
			}
		}

		return base64ToImage(base64);
	}

	/**
	 * Compara las puntuaciones del jugador y del Croupier al final de la partida para determinar el ganador.
	 * <ul>
	 * <li>Si los puntos del Croupier son mayores que los del jugador, el Croupier gana.</li>
	 * <li>Si los puntos del Croupier son iguales a los del jugador, hay un empate.</li>
	 * <li>Si los puntos del jugador son mayores que los del Croupier, el jugador gana.</li>
	 * </ul>
	 */
	public void compararJugada() {
		if (croupierPoints > playerPoints) {
			// La banca gana
			JOptionPane.showMessageDialog(null, "El Croupier gana!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
		} else if (croupierPoints == playerPoints) {
			// Empate
			JOptionPane.showMessageDialog(null, "¡Empate!", "Resultado", JOptionPane.INFORMATION_MESSAGE);
		} else {
			// El jugador gana
			JOptionPane.showMessageDialog(null, "¡Ganaste " + nomUsuari + "!", "Resultado",
					JOptionPane.INFORMATION_MESSAGE);
			guardarPartida();
		}
	}

	/**
	 * Ofrece al usuario la posibilidad de guardar la partida actual y actúa según su elección.
	 * 
	 * Un cuadro de diálogo permite al usuario decidir si desea guardar la partida:
	 * <ul>
	 * <li>Si elige "Sí", se llama al método {@code guardarPartidaBBDD()} para persistir los datos en la base de datos.</li>
	 * <li>Si elige "No" o cierra el cuadro de diálogo, la partida no se guarda.</li>
	 * </ul>
	 * </p>
	 * <p>
	 * Al finalizar, se muestra un mensaje para informar que se puede volver a jugar pulsando el botón "START".
	 * </p>
	 */
	public void guardarPartida() {
		String[] opciones = { "Sí", "No" };
		int eleccion = JOptionPane.showOptionDialog(null, "Quieres guardar la partida?", "Guardar Partida",
				JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
		if (eleccion != -1 || eleccion == 1) {
			if (eleccion == 0) {
				guardarPartidaBBDD();
			}
		} else {
			JOptionPane.showMessageDialog(null, "La partida no se guardó! ", "Guardar Partida",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Guarda los datos de la partida actual en la base de datos.
	 * <p>
	 * Este método persiste información sobre la partida jugada, incluyendo:
	 * <ul>
	 * <li>Nombre del usuario que jugó la partida.</li>
	 * <li>Tipo de baraja seleccionada (española o francesa).</li>
	 * <li>Puntos obtenidos por el jugador.</li>
	 * <li>Marca de tiempo de la partida.</li>
	 * </ul>
	 * </p>
	 * 
	 * <p>
	 * Requiere que la base de datos esté previamente configurada y conectada mediante el atributo bbdd.
	 * </p>
	 */
	private void guardarPartidaBBDD() {

		if (this.bbdd != null) {
			String fechaHora = agregarTimeStamp();
			String suit = this.barajaSeleccionada == coleccionCards_es ? "es" : "fr";

			MongoCollection<Document> puntuaciones = bbdd.getCollection(coleccionScores);

			Document doc = new Document();
			doc.append("user", this.nomUsuari);
			doc.append("suit", suit);
			doc.append("points", this.playerPoints);
			doc.append("timestamp", fechaHora);

			puntuaciones.insertOne(doc);

			JOptionPane.showMessageDialog(null, "La partida se guardó con éxito! ", "Información",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Carga las partidas guardadas desde la base de datos y devuelve un resumen como una cadena de texto.
	 * 
	 * Este método consulta la colección configurada para almacenar puntuaciones y recupera los documentos correspondientes.
	 * Cada documento representa una partida guardada y contiene información como:
	 * <ul>
	 * <li>Nombre del usuario.</li>
	 * <li>Puntos obtenidos.</li>
	 * <li>Tipo de baraja utilizada (española o francesa).</li>
	 * <li>Marca de tiempo de la partida.</li>
	 * </ul>
	 * 
	 * @return Una cadena con los datos de todas las partidas guardadas en formato legible, o una cadena vacía
	 *         si no hay datos en la base de datos o si no se establece conexión con esta.
	 */
	public String cargarPartidasGuardadas() {

		String resultado = "";
		
		if(this.bbdd != null) {
			MongoCollection<Document> coleccionPuntuaciones = bbdd.getCollection(this.coleccionScores);
			FindIterable<Document> documents = coleccionPuntuaciones.find(); 
			
			for (Document doc : documents) {
				String nombreUsuario = doc.getString("user");
				int puntos = doc.getInteger("points");
				String suit = doc.getString("suit");
				String fechaHora = doc.getString("timestamp");
				
				resultado += nombreUsuario+" "+puntos+" points (Suit "+suit.toUpperCase()+" "+fechaHora+")\n";
			}
		}
		
		return resultado;
	}

	
	// GETTERS Y SETTERS DE LOS ATRIBUTOS
	public int getPlayerPoints() {
		return playerPoints;
	}

	public void setPlayerPoints(int playerPoints) {
		this.playerPoints = playerPoints;
	}

	public int getCroupierPoints() {
		return croupierPoints;
	}

	public void setCroupierPoints(int croupierPoints) {
		this.croupierPoints = croupierPoints;
	}

	public boolean getPlayerStands() {
		return playerStands;
	}

	public void setPlayerStands(boolean playerStands) {
		this.playerStands = playerStands;
	}

	public boolean getCroupierStands() {
		return croupierStands;
	}

	public void setCroupierStands(boolean croupierStands) {
		this.croupierStands = croupierStands;
	}

	public List<String> getPlayerCards() {
		return playerCards;
	}

	public List<String> getCroupierCards() {
		return croupierCards;
	}

	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public List<String> getCards() {
		return cards;
	}

	public void setCards(List<String> cards) {
		this.cards = cards;
	}

	public void setPlayerCards(List<String> playerCards) {
		this.playerCards = playerCards;
	}

	public void setCroupierCards(List<String> croupierCards) {
		this.croupierCards = croupierCards;
	}

}
