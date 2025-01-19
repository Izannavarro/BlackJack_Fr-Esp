package AEV3_Mongo;

import java.io.IOException;

import AEV3_Mongo.Controlador;
import AEV3_Mongo.Model;
import AEV3_Mongo.VistaLogin;
import AEV3_Mongo.VistaRegistro;
import AEV3_Mongo.VistaPrincipal;
import AEV3_Mongo.VistaPuntuaciones;

/**
 * Clase principal de la aplicación que arranca la interfaz de usuario y configura
 * los componentes necesarios, incluyendo las vistas y el controlador. Es el punto de entrada 
 * a la ejecución del programa.
 */
public class AEV3_Mongo {
	
	/**
     * Método principal que arranca la aplicación, inicializando las vistas y el controlador.
     * Establece la conexión entre las vistas (login, registro, principal, puntuaciones) y el modelo
     * de datos a través del controlador.
     */
	public static void main(String[] args) throws IOException {
		VistaLogin vLogin = new VistaLogin();
		VistaRegistro vRegistro = new VistaRegistro();
		VistaPrincipal vPrincipal = new VistaPrincipal();
		VistaPuntuaciones vPuntuaciones = new VistaPuntuaciones();
		Model model = new Model();
		Controlador controlador = new Controlador(vLogin,vRegistro,vPrincipal,vPuntuaciones,model);
	}
	
}
