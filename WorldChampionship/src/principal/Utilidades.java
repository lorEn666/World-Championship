package principal;

import java.util.Scanner;

/**
 * Clase que almacena m�todos est�ticos sin influencia sobre la funcionalidad,
 * pero s� sobre la est�tica durante la ejecuci�n, asegurando al usuario una
 * experiencia limpia, concisa y garantizando el aporte de informaci�n
 * suficiente.
 * 
 * @author borjaLoren
 * @version 2.9.9
 * @since 20-01-2020
 *
 */
public class Utilidades {

	/**
	 * M�todo posterior a la asignaci�n aleatoria de salida y que precede al inicio
	 * de la carrera. Dando control al usuario para que decida cu�ndo comenzarla.
	 */
	public static void enterParaSalida() {
		Scanner leer = new Scanner(System.in);
		System.out.println("�Pulse ENTER para comenzar la carrera!");

		try {
			leer.nextLine();
		} catch (Exception e) {

		}
	}

	/**
	 * Evita impresi�n masiva de informaci�n por pantalla, permitiendo desglosar su
	 * aparici�n cuando el usuario decida.
	 */
	public static void enterParaContinuar() {
		Scanner leer = new Scanner(System.in);
		System.out.println("Pulse ENTER para continuar.");

		try {
			leer.nextLine();
		} catch (Exception e) {

		}
	}

	/**
	 * Previo a la visualizaci�n del men� principal. �til cuando se genera una
	 * acci�n no permitida por la aplicaci�n o al finalizar la carrera.
	 */
	public static void enterParaMenuPrincipal() {
		Scanner leer = new Scanner(System.in);
		System.out.println("Pulse ENTER para volver al men� principal.");

		try {
			leer.nextLine();
		} catch (Exception e) {

		}
	}

	/**
	 * Sanea la visualizaci�n por consola evitando el atasco de informaci�n y que la
	 * aplicaci�n resulte especialmente abusiva.
	 */
	public static void limpiarPantalla() {
		for (int i = 0; i < 40; i++) {
			System.out.println();
		}
	}
}
