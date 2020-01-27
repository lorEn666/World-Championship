package principal;

import java.util.Scanner;

public class Menu {

	public static int menuPrincipal() throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * * * * ");
		System.out.println("* * * WORLD CHAMPIONSHIP! * * *");
		System.out.println(" * * * * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * * * * *");
		System.out.println("1- Carrera");
		System.out.println("2- Registrar piloto");
		System.out.println("3- Mejorar vehículo");
		System.out.println("4- Palmarés");
		System.out.println("5- Salir");
		return leer.nextInt();
	}
	
	public static int menuModoDeJuego() throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *   MODALIDAD   * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
		System.out.println("1- Individual");
		System.out.println("2- Multijugador");
		return leer.nextInt();
	}
	
	public static int menuNumeroJugadores() throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * Nº DE PLAYERS * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
		System.out.println("1- 2 Jugadores");
		System.out.println("2- 3 Jugadores");
		System.out.println("3- 4 Jugadores");
		return leer.nextInt();
	}
	
	public static int menuMejoras() throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * *  MEJORAS  * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
		System.out.println("1- Mejorar potencia (2.000$)");
		System.out.println("2- Mejorar resistencia (1.500$)");
		System.out.println("3- Volver");
		return leer.nextInt();
	}
	
	public static int menuSeleccionCircuito(Carrera c) throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *    CIRCUITO   * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
		c.listaCircuitos();
		
		return leer.nextInt();
	}
	
	public static int menuSeleccionDificultad(Carrera c) throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *  DIFICULTAD   * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
		c.listaDificultad();
		
		return leer.nextInt();
	}
	
	public static int menuSeleccionVista() throws Exception {
		Scanner leer = new Scanner(System.in);
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * VISUALIZACIÓN * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
		System.out.println("1- Clásica");
		System.out.println("2- Por posición");
		
		return leer.nextInt();
	}
	
	public static int menuCarrera() throws Exception {
		Scanner leer = new Scanner(System.in);
		System.out.println("1) Arrancar\t2) Acelerar\t3) Frenar\t4) Rearrancar\t5) Abandonar");
		return leer.nextInt();
	}
	
	public static void menuSeleccionPilotos() {
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * *  PILOTOS  * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
	}
	
	public static void menuRegistroPilotos() {
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *   REGISTRO    * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
	}
	
	public static void menuPalmaresPilotos() {
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *   PALMARÉS    * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
	}
	
	public static void menuSalida() {
		Utilidades.limpiarPantalla();
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *ORDEN DE SALIDA* * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
	}
	
	public static void menuPodio() {
		System.out.println("* * * * * * * * * * * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * *     PODIO     * * *");
		System.out.println(" * * * * * * * * * * * * ");
		System.out.println("* * * * * * * * * * * * *");
	}
}
