package principal;

import java.util.Scanner;

/**
 * Clase que almacena métodos estáticos sin influencia sobre la funcionalidad,
 * pero sí sobre la estética durante la ejecución, asegurando al usuario una
 * experiencia limpia, concisa y garantizando el aporte de información
 * suficiente.
 * 
 * @author borjaLoren
 * @version 2.9.9
 * @since 20-01-2020
 *
 */
public class Utilidades {

	/**
	 * Método posterior a la asignación aleatoria de salida y que precede al inicio
	 * de la carrera. Dando control al usuario para que decida cuándo comenzarla.
	 */
	public static void enterParaSalida() {
		Scanner leer = new Scanner(System.in);
		System.out.println("¡Pulse ENTER para comenzar la carrera!");

		try {
			leer.nextLine();
		} catch (Exception e) {

		}
	}

	/**
	 * Evita impresión masiva de información por pantalla, permitiendo desglosar su
	 * aparición cuando el usuario decida.
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
	 * Previo a la visualización del menú principal. Útil cuando se genera una
	 * acción no permitida por la aplicación o al finalizar la carrera.
	 */
	public static void enterParaMenuPrincipal() {
		Scanner leer = new Scanner(System.in);
		System.out.println("Pulse ENTER para volver al menú principal.");

		try {
			leer.nextLine();
		} catch (Exception e) {

		}
	}

	/**
	 * Sanea la visualización por consola evitando el atasco de información y que la
	 * aplicación resulte especialmente abusiva.
	 */
	public static void limpiarPantalla() {
		for (int i = 0; i < 40; i++) {
			System.out.println();
		}
	}
}
