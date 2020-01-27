package principal;

import java.util.Random;
import java.util.Scanner;

/**
 * El n�cleo de la aplicaci�n. Contiene los m�todos fundamentales con los que el
 * usuario podr� registrar nuevos pilotos y llevar a cabo las mejoras. Desde
 * aqu� se gestiona la carrera, se asigna orden de salida de los pilotos,
 * durante la competici�n se da prioridad al jugador que la tiene (tambi�n a la
 * IA), se muestra y asigna el podio dado el resultado de una competici�n, el
 * progreso de la carrera y se gestiona el sistema de recompensas por posici�n
 * final.
 * 
 * @author borjaLoren
 * @version 2.9.9
 * @since 20-01-2020
 *
 */
public class Carrera {
	private Coche[] vCoche;
	private String[] vCircuito;
	private String[] vDificultad;
	private String nombreCircuito;
	private int longitudCircuito;
	private Coche[] podio;
	private String vista;

	/**
	 * Constructor �nico y principal de la clase. Cuenta con los atributos en los
	 * cuales se almacenar�n los pilotos registrados, los circuitos disponibles, los
	 * niveles de dificultad aplicables, los jugadores que ocupar�n posiciones en el
	 * podio, el nombre del circuito, su longitud y el tipo de vista seleccionada
	 * durante la configuraci�n de la carrera.
	 */
	public Carrera() {
		vCoche = new Coche[6];
		vCircuito = new String[4];
		vDificultad = new String[3];
		nombreCircuito = "";
		longitudCircuito = 0;
		podio = new Coche[3];
		vista = "CL�SICA";
	}

	/**
	 * M�todo que permitir� registrar un nuevo piloto si hay espacio disponible. El
	 * usuario podr� establecer un nombre y un dorsal para el nuevo piloto.
	 * 
	 * @throws InputMismatchException Generada por inserci�n de datos defectuosa
	 *                                dentro del m�todo registraDorsal() llamado en
	 *                                su interior. La excepci�n se gestionar� en la
	 *                                clase 'Main'
	 */
	public void registrarPiloto() {
		Menu.menuRegistroPilotos();
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] == null) {
				vCoche[i] = new Coche(registraNombre(), registraDorsal());
				compruebaValidezDelRegistro(i);
				break;
			}
		}
	}

	/**
	 * Pedir� al usuario asignar el nombre del piloto que desea registrar.
	 * 
	 * @return String Devuelve el nombre del piloto a registrar.
	 */
	private String registraNombre() {
		Scanner leer = new Scanner(System.in);
		System.out.println("Inserte nombre del piloto: ");
		return leer.nextLine();
	}

	/**
	 * Pedir� al usuario asignar el dorsal del piloto que desea registrar.
	 * 
	 * @return int Devuelve el n�mero de dorsal que lucir� el piloto a registrar.
	 */
	private int registraDorsal() {
		Scanner leer = new Scanner(System.in);
		System.out.println("Inserte dorsal del piloto: ");
		return leer.nextInt();
	}

	/**
	 * Determina si un registro es apto. Tras la inserci�n de nombre y dorsal, se
	 * proceder� a comprobar la validez de ambos. Un dorsal ser� v�lido si no tiene
	 * m�s de 2 cifras, si no es un n�mero negativo, si no lo posee otro piloto
	 * registrado anteriormente o si el dorsal no coincide con el reservado para los
	 * pilotos desbloqueables. Un nombre ser� v�lido si no se ha introducido un
	 * campo vac�o o si no extiende los 14 caracteres.
	 * 
	 * @param posicion Recibe la posici�n en la cual el usuario est� registrando el
	 *                 piloto.
	 */
	private void compruebaValidezDelRegistro(int posicion) {
		if (vCoche[posicion].getDorsal() == 1 || vCoche[posicion].getDorsal() == 14) {
			vCoche[posicion] = null;
			System.err.println("El dorsal est� reservado. No se ha podido registrar al piloto.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		if (String.valueOf(vCoche[posicion].getDorsal()).length() >= 3) {
			vCoche[posicion] = null;
			System.err.println("No pueden registrarse dorsales de m�s de dos cifras.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		if (vCoche[posicion].getDorsal() < 0) {
			vCoche[posicion] = null;
			System.err.println("No pueden registrarse dorsales negativos.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && i != posicion && vCoche[posicion].getDorsal() == vCoche[i].getDorsal()
					&& vCoche[i].isBloqueado() == false) {
				vCoche[posicion] = null;
				System.err.println("El dorsal escogido ya est� asignado. No se ha podido registrar al piloto.");
				Utilidades.enterParaMenuPrincipal();
				return;
			}
		}
		if (vCoche[posicion].getNombreDelPiloto().length() > 14) {
			vCoche[posicion] = null;
			System.err.println("No pueden registrarse nombres que sobrepasen los 14 caracteres.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		if (vCoche[posicion].getNombreDelPiloto().isEmpty()) {
			vCoche[posicion] = null;
			System.err.println("No pueden registrar un piloto sin nombre.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		System.err.println("�Piloto registrado!");
		Utilidades.enterParaMenuPrincipal();
	}

	/**
	 * M�todo ejecutado al inicio de la aplicaci�n que registra los pilotos
	 * bloqueados en las posiciones [4] y [5] del array que almacena los pilotos. Se
	 * hace uso del segundo constructor de la clase 'Coche' para poder delimitar la
	 * potencia, resistencia y asignar al atributo 'bloqueado' el valor 'true'.
	 */
	public void registraPilotosBloqueados() {
		vCoche[4] = new Coche("F. Alonso", 14, 110, 440, true);
		vCoche[5] = new Coche("M. Schumacher", 1, 140, 410, true);
	}

	/**
	 * M�todo ejecutado al inicio de la aplicaci�n que registra los circuitos
	 * disponibles sobre el array vCircuito[]
	 */
	public void registraCircuitos() {
		vCircuito[0] = "1- GP Motorland Arag�n (400Km)";
		vCircuito[1] = "2- GP M�naco (800Km)";
		vCircuito[2] = "3- 24 Horas de Le Mans (2000Km)";
		vCircuito[3] = "4- ????????";
	}

	/**
	 * M�todo ejecutado al inicio de la aplicaci�n que registra los niveles de
	 * dificultad disponibles sobre el array vDificultad[]
	 */
	public void registraDificultad() {
		vDificultad[0] = "1- Normal";
		vDificultad[1] = "2- Dif�cil";
		vDificultad[2] = "3- ????????";
	}

	/**
	 * Lista los circuitos disponibles. Este m�todo ser� llamado por la clase 'Menu'
	 * cuando a esta lo invoque la clase 'Main'.
	 */
	public void listaCircuitos() {
		System.out.println(vCircuito[0]);
		System.out.println(vCircuito[1]);
		System.out.println(vCircuito[2]);
		System.out.println(vCircuito[3]);
	}

	/**
	 * Lista los niveles de dificultad disponibles. Este m�todo ser� llamado por la
	 * clase 'Menu' cuando a esta lo invoque la clase 'Main'.
	 */
	public void listaDificultad() {
		System.out.println(vDificultad[0]);
		System.out.println(vDificultad[1]);
		System.out.println(vDificultad[2]);
	}

	/**
	 * Fija el nombre y la longitud del circuito seleccionado por el usuario al
	 * configurar la carrera. Esto ser� especialmente importante para establecer los
	 * kil�metros que deber�n recorrer los pilotos en competici�n y para asignar las
	 * recompensas pertinentes.
	 * 
	 * @param n Recibe un valor de 0,1,2 o 3 seg�n la opci�n escogida por el usuario
	 *          al configurar la carrera. En base a este valor se determinan unos
	 *          valores u otros para los atributos que configura el m�todo.
	 * 
	 * @throws ExcepcionModalidadBloqueada Lanzada cuando el usuario selecciona un
	 *                                     circuito no desbloqueado y gestionada por
	 *                                     la clase 'Main'. Permitir� controlar el
	 *                                     flujo.
	 */
	public void fijaNombreLongitud(int n) throws Exception {
		if (n == 0) {
			nombreCircuito = "GP Motorland Arag�n";
			longitudCircuito = 400;
		}
		if (n == 1) {
			nombreCircuito = "GP M�naco";
			longitudCircuito = 800;
		}
		if (n == 2) {
			nombreCircuito = "24 Horas de Le Mans";
			longitudCircuito = 2000;
		}
		if (n == 3 && vCircuito[3].equals("4- ????????")) {
			throw new ExcepcionModalidadBloqueada("Todav�a no ha desbloqueado ese circuito.");
		} else if (n == 3 && vCircuito[3].equals("4- Rally Dakar (4000Km)")) {
			nombreCircuito = "Rally Dakar";
			longitudCircuito = 4000;
		}
	}

	/**
	 * Permite seleccionar piloto para competir entre los listados en el m�todo
	 * listadoDePilotos() que invoca. Al piloto escogido por el usuario se le
	 * asignar� el valor 'HUMANO' para el atributo 'inteligencia' en la clase
	 * 'Coche'.
	 * 
	 * @throws InputMismatchException      Lanzada si la inserci�n de datos no es
	 *                                     v�lida. Excepci�n gestionada por la clase
	 *                                     'Main'.
	 *
	 * @throws ExcepcionModalidadBloqueada Lanzada si no hay 2 pilotos registrados o
	 *                                     si se selecciona un valor del array
	 *                                     vCoche[] el cual contiene valor 'null'.
	 *                                     Excepci�n gestionada desde la clase
	 *                                     'Main'.
	 */
	public void eleccionDePilotoParaCompetir() throws Exception {
		if (vCoche[0] == null || vCoche[1] == null) {
			throw new ExcepcionModalidadBloqueada("Debe registrar al menos dos pilotos para competir.");
		}
		listadoDePilotos();
		Scanner leer = new Scanner(System.in);
		System.out.println("Seleccione piloto con el que desea competir.");
		int eleccion = leer.nextInt() - 1;
		if (vCoche[eleccion] != null && vCoche[eleccion].isBloqueado() == false) {
			vCoche[eleccion].setInteligencia("HUMANO");
		} else {
			throw new ExcepcionModalidadBloqueada("Elecci�n no v�lida.");
		}
	}

	/**
	 * M�todo que comprueba si hay al menos tantos pilotos registrados como
	 * jugadores ha seleccionado el usuario. Si la validaci�n es positiva, se invoca
	 * el m�todo modoMultijugadorSeleccionPilotos() que recibir� el mismo valor
	 * recibido por este m�todo.
	 * 
	 * @param numeroJugadores Fija el n�mero de jugadores que ha escogido el
	 *                        usuario.
	 * 
	 * @throws ExcepcionModalidadBloqueada Lanzada cuando el n�mero de pilotos
	 *                                     registrados es inferior al n�mero de
	 *                                     jugadores con los que desea competir el
	 *                                     usuario. Permitir� controlar el flujo.
	 *                                     Excepci�n gestionada en la clase 'Main'.
	 */
	public void modoMultijugadorSeleccionJugadores(int numeroJugadores) throws Exception {
		if (numeroJugadores == 2) {
			if (vCoche[1] == null) {
				throw new ExcepcionModalidadBloqueada("Debe registrar al menos dos pilotos para competir.");
			}
			modoMultijugadorSeleccionPilotos(numeroJugadores);
		}
		if (numeroJugadores == 3) {
			if (vCoche[2] == null) {
				throw new ExcepcionModalidadBloqueada("Debe registrar al menos tres pilotos para competir.");
			}
			modoMultijugadorSeleccionPilotos(numeroJugadores);
		}
		if (numeroJugadores == 4) {
			if (vCoche[3] == null) {
				throw new ExcepcionModalidadBloqueada("Debe registrar al menos cuatro pilotos para competir.");
			}
			modoMultijugadorSeleccionPilotos(numeroJugadores);
		}
	}

	/**
	 * Lista pilotos disponibles en vCoche[] permitiendo la selecci�n de los mismos
	 * a los usuarios en modo multijugador.
	 * 
	 * @param numeroJugadores Recibe el n�mero de jugadores que participar�n en la
	 *                        carrera.
	 * 
	 * @throws ExcepcionModalidadBloqueada Lanzada cuando el usuario introduce una
	 *                                     posici�n que no existe en el array
	 *                                     vCoche[]. Ser� gestionada en la clase
	 *                                     'Main'
	 * 
	 * @throws Excepcion                   Lanzada cuando el usuario introduce un
	 *                                     dato inv�lido al leer un int. Ser�
	 *                                     gestionada en la clase 'Main'
	 */
	private void modoMultijugadorSeleccionPilotos(int numeroJugadores) throws Exception {
		int player = 0;
		do {
			listadoDePilotos();
			Scanner leer = new Scanner(System.in);
			player++;
			System.out.println("Seleccione piloto Player " + player);
			int eleccion = leer.nextInt() - 1;
			compruebaPlayersSeleccionanDistintosPilotos(eleccion);
			if (vCoche[eleccion] != null && vCoche[eleccion].isBloqueado() == false) {
				vCoche[eleccion].setInteligencia("HUMANO");
				numeroJugadores--;
			} else {
				throw new ExcepcionModalidadBloqueada("Elecci�n no v�lida.");
			}
		} while (numeroJugadores > 0);
	}

	/**
	 * Cuando el usuario escoge un piloto en modo multijugador, comprueba que ese
	 * piloto no haya sido seleccionado antes por otro usuario.
	 * 
	 * @param eleccion int Posici�n del array vCoche[] en la cual se va a realizar
	 *                 la comprobaci�n.
	 * 
	 * @throws ExcepcionModalidadBloqueada Lanzada cuando se verifica que un mismo
	 *                                     piloto ha sido seleccionado por distintos
	 *                                     jugadores. Gestionada en la clase 'Main'.
	 */
	private void compruebaPlayersSeleccionanDistintosPilotos(int eleccion) throws Exception {
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[eleccion] != null && vCoche[i] != null && vCoche[i].isBloqueado() == false
					&& vCoche[eleccion].getDorsal() == vCoche[i].getDorsal()
					&& vCoche[i].getInteligencia().equals("HUMANO")) {
				throw new ExcepcionModalidadBloqueada("No puede seleccionar el mismo piloto que otro Player.");
			}
		}
	}

	/**
	 * Asigna un valor al atributo 'handicap' de la clase 'Coche' para todos los
	 * pilotos controlados por la IA en base al nivel de dificultad escogido por el
	 * usuario.
	 * 
	 * @param dificultad int Valor que diferencia el nivel de dificultad escogido.
	 * 
	 * @throws ExcepcionModalidadBloqueada Lanzada cuando el nivel de dificultad en
	 *                                     la posicion vDificultad[2] todav�a no ha
	 *                                     sido desbloqueado y el usuario lo
	 *                                     selecciona. Gestionada en la clase
	 *                                     'Main'.
	 */
	public void fijaDificultadIA(int dificultad) throws Exception {
		if (dificultad == 1 && vDificultad[2].equals("3- ????????")) {
			throw new ExcepcionModalidadBloqueada("Todav�a no ha desbloqueado ese nivel de dificultad.");
		}
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].getInteligencia().equals("IA")) {
				if (dificultad == 0) {
					vCoche[i].setHandicap(15);
				}
				if (dificultad == 1) {
					vCoche[i].setHandicap(30);
				}
			}
		}
	}

	/**
	 * Lista pilotos tanto registrados como no registrados. De los primeros, se
	 * visualizar�n tambi�n algunas cuestiones tales como los atributos 'potencia',
	 * 'resistencia', 'fondos', etc.
	 */
	public void listadoDePilotos() {
		Menu.menuSeleccionPilotos();
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == true) {
				System.out.println((i + 1) + ") ????????\n");
				continue;
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false) {
				System.out.println((i + 1) + ") " + "Nombre: " + vCoche[i].getNombreDelPiloto() + "   Dorsal: "
						+ vCoche[i].getDorsal() + "   Potencia: " + vCoche[i].getPotencia() + "   Resistencia: "
						+ vCoche[i].getResistencia() + "   Fondos: " + vCoche[i].getDinero() + "$" + "   Victorias: "
						+ vCoche[i].getVictorias() + "\n");
			} else {
				System.out.println((i + 1) + ") Sin registrar\n");
			}
		}
	}

	/**
	 * Muestra aspectos del piloto seleccionado relacionados con la competici�n
	 * tales como n� de victorias totales, desglosadas por dificultad, circuito y n�
	 * de abandonos.
	 */
	public void verPalmaresPiloto() {
		Scanner leer = new Scanner(System.in);
		System.out.println("Seleccione un piloto para consultar su palmar�s completo o pulse Enter para salir.");
		String eleccion = leer.nextLine();
		if (eleccion.isEmpty()) {
			return;
		}

		if (vCoche[Integer.valueOf(eleccion) - 1] != null
				&& vCoche[Integer.valueOf(eleccion) - 1].isBloqueado() == false) {
			Menu.menuPalmaresPilotos();
			System.out.println("Victorias totales: " + vCoche[Integer.valueOf(eleccion) - 1].getVictorias()
					+ "\nSegundos puestos: " + vCoche[Integer.valueOf(eleccion) - 1].getSegundosPuestos()
					+ "\nTerceros puestos: " + vCoche[Integer.valueOf(eleccion) - 1].getTercerosPuestos()
					+ "\n\nVictorias en modo normal: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasNormal()
					+ "\nVictorias en modo dif�cil: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasDificil()
					+ "\nVictorias en modo experto: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasExperto()
					+ "\n\nVictorias GP de Motorland: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasMotorland()
					+ "\nVictorias GP de M�naco: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasMonaco()
					+ "\nVictorias 24 horas de Le Mans: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasLeMans()
					+ "\nVictorias Rally Dakar: " + vCoche[Integer.valueOf(eleccion) - 1].getVictoriasRally()
					+ "\n\nAbandonos: " + vCoche[Integer.valueOf(eleccion) - 1].getAbandonos() + "\n");
			Utilidades.enterParaMenuPrincipal();
		}
	}

	/**
	 * Suma un int aleatorio al atributo 'potencia' del piloto escogido por el
	 * usuario en el array vCoche[]. Si el usuario no dispone de fondos suficientes,
	 * la mejora no se llevar� a cabo. Debe haber m�nimo un piloto registrado.
	 * 
	 * @throws Exception Lanzada cuando el usuario introduce un dato err�neo tras la
	 *                   lectura de un int. Gestionada por la clase 'Main'.
	 */
	public void mejoraPotencia() throws Exception {
		if (vCoche[0] == null) {
			System.err.println("Debe registrar al menos un piloto.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		Scanner leer = new Scanner(System.in);
		Random r = new Random();
		int piloto;
		listadoDePilotos();
		System.out.println("Seleccione un veh�culo para mejorar:");
		piloto = leer.nextInt() - 1;
		if (vCoche[piloto] != null && vCoche[piloto].getDinero() > 2000) {
			vCoche[piloto].setDinero(vCoche[piloto].getDinero() - 2000);
			vCoche[piloto].setPotencia(vCoche[piloto].getPotencia() + r.nextInt(3) + 3);
			System.out.println("�Mejora realizada!");
			Utilidades.enterParaMenuPrincipal();
		} else {
			System.err.println("No dispone de esa cantidad.");
			Utilidades.enterParaMenuPrincipal();
		}
	}

	/**
	 * Suma un int aleatorio al atributo 'resistencia' del piloto escogido por el
	 * usuario en el array vCoche[]. Si el usuario no dispone de fondos suficientes,
	 * la mejora no se llevar� a cabo. Debe haber m�nimo un piloto registrado.
	 * 
	 * @throws Exception Lanzada cuando el usuario introduce un dato err�neo tras la
	 *                   lectura de un int. Gestionada por la clase 'Main'.
	 */
	public void mejoraResistencia() throws Exception {
		if (vCoche[0] == null) {
			System.err.println("Debe registrar al menos un piloto.");
			Utilidades.enterParaMenuPrincipal();
			return;
		}
		Scanner leer = new Scanner(System.in);
		Random r = new Random();
		int piloto;
		listadoDePilotos();
		System.out.println("Seleccione un veh�culo para mejorar:");
		piloto = leer.nextInt() - 1;
		if (vCoche[piloto] != null && vCoche[piloto].getDinero() > 1500) {
			vCoche[piloto].setDinero(vCoche[piloto].getDinero() - 1500);
			vCoche[piloto].setResistencia(vCoche[piloto].getResistencia() + r.nextInt(6) + 3);
			System.out.println("�Mejora realizada!");
			Utilidades.enterParaMenuPrincipal();
		} else {
			System.err.println("No dispone de esa cantidad.");
			Utilidades.enterParaMenuPrincipal();
		}
	}

	/**
	 * Restaura los valores por defecto de atributos susceptibles de cambio durante
	 * la configuraci�n de la carrera a cada una de las posiciones en vCoche[]
	 */
	public void reset() {
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null) {
				vCoche[i].setInteligencia("IA");
				vCoche[i].setKmRecorridos(0);
				vCoche[i].setVelocidad(0);
				vCoche[i].setHandicap(0);
				vCoche[i].setEstado("PARADO");
				vCoche[i].setPosicion(0);
				vCoche[i].setTurno(0);
				vCoche[i].setVictoriasTotales(vCoche[i].getVictoriasNormal() + vCoche[i].getVictoriasDificil()
						+ vCoche[i].getVictoriasExperto());
			}
		}
	}

	/**
	 * Invoca los m�todos necesarios (secuencialmente) para que la carrera se lleve
	 * a cabo.
	 */
	public void inicioCarrera() {
		asignarOrdenDeSalida(cuentaPilotosEnCarrera());
		pintaCarrera();
		accionPilotoPrioridadPorTurnos(cuentaPilotosEnCarrera());
		Utilidades.enterParaContinuar();
		Utilidades.limpiarPantalla();
		imprimePodio();
		recompensaPorPosicion(rastreaHumano(), rastreaIA());
		Utilidades.enterParaMenuPrincipal();
		Utilidades.limpiarPantalla();
	}

	/**
	 * Recorre las posiciones en vCoche[j] y los turnos(i) hasta encontrar al piloto
	 * que le corresponde realizar acci�n en base al turno asignado antes de
	 * comenzar la carrera. El m�todo invocar� turnoJugador() o turnoIA() seg�n
	 * preferencia. El m�todo se ejecutar� en bucle mientras el m�todo
	 * carreraFinalizada() no dictamine que la carrera ha finalizado.
	 * 
	 * @param pilotos int Recibe el n�mero de pilotos participantes en la carrera.
	 */
	private void accionPilotoPrioridadPorTurnos(int pilotos) {
		do {
			for (int i = 0; i < pilotos; i++) {
				for (int j = 0; j < pilotos; j++) {
					if (vCoche[j] != null && vCoche[j].isBloqueado() == false && vCoche[j].getTurno() == i + 1
							&& vCoche[j].getInteligencia().equals("HUMANO")
							&& !vCoche[j].getEstado().equals("TERMINADO")
							&& !vCoche[j].getEstado().equals("ABANDONADO")) {
						turnoJugador(j);
						continue;
					}
					if (vCoche[j] != null && vCoche[j].isBloqueado() == false && vCoche[j].getTurno() == i + 1
							&& vCoche[j].getInteligencia().equals("IA") && !vCoche[j].getEstado().equals("TERMINADO")) {
						turnoIA(j);
						continue;
					}
				}
			}
		} while (!carreraFinalizada());
	}

	/**
	 * Imprime listado de acciones a realizar por el piloto controlado por el
	 * usuario. El m�todo castiga las decisiones mal tomadas (ej, acelerar cuando el
	 * coche est� parado), cediendo el turno de acci�n directamente al siguiente
	 * piloto. De la misma manera, una inserci�n de datos err�nea tambi�n ser�
	 * penada perdiendo el turno de acci�n. Tras cada acci�n se realizar�n una serie
	 * de comprobaciones a trav�s de los m�todos actualizaPosicionDeLosPilotos(),
	 * pintaCarrera() y asignaPodio().
	 * 
	 * @param jugador int la posicion sobre el array vCoche[] del piloto controlado
	 *                por el usuario.
	 */
	private void turnoJugador(int jugador) {
		int accion;

		imprimeEstadisticasDeLosPilotos();

		try {
			System.out.println("\n�Tu turno " + vCoche[jugador].getNombreDelPiloto() + "!");
			switch (accion = Menu.menuCarrera()) {
			case 1:
				Utilidades.limpiarPantalla();
				vCoche[jugador].arrancar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				pintaCarrera();
				asignaPodio(jugador);
				break;
			case 2:
				Utilidades.limpiarPantalla();
				vCoche[jugador].acelerar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[jugador].getKmRecorridos() > longitudCircuito) {
					vCoche[jugador].setKmRecorridos(longitudCircuito);
					vCoche[jugador].setEstado("TERMINADO");
					System.out.println("�" + vCoche[jugador].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[jugador].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(jugador);
				break;
			case 3:
				Utilidades.limpiarPantalla();
				vCoche[jugador].frenar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[jugador].getKmRecorridos() > longitudCircuito) {
					vCoche[jugador].setKmRecorridos(longitudCircuito);
					vCoche[jugador].setEstado("TERMINADO");
					System.out.println("�" + vCoche[jugador].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[jugador].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(jugador);
				break;
			case 4:
				Utilidades.limpiarPantalla();
				vCoche[jugador].rearrancar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				pintaCarrera();
				asignaPodio(jugador);
				break;
			case 5:
				vCoche[jugador].setEstado("ABANDONADO");
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				Utilidades.limpiarPantalla();
				System.out.println("�" + vCoche[jugador].getNombreDelPiloto() + " ha abandonado la carrera!");
				pintaCarrera();
				vCoche[jugador].setAbandonos(vCoche[jugador].getAbandonos() + 1);
				break;
			default:
				Utilidades.limpiarPantalla();
				pintaCarrera();
				System.out.println("�Acci�n nula!");
			}
		} catch (Exception e) {
			Utilidades.limpiarPantalla();
			pintaCarrera();
			System.err.println("Acci�n no v�lida.");
		}
	}

	/**
	 * Gestiona la toma decisiones de la IA seg�n las circunstancias en las que se
	 * encuentre. Seg�n la dificultad escogida cuando se configur� la carrera el
	 * comportamiento de la IA variar� sustancialmente.
	 * 
	 * @param maquina int la posicion sobre el array vCoche[] del piloto controlado
	 *                por la IA.
	 */
	private void turnoIA(int maquina) {
		if (vCoche[maquina].getEstado().equals("PARADO")) {
			vCoche[maquina].arrancar();
			actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
			pintaCarrera();
			asignaPodio(maquina);
			return;
		}
		if (vCoche[maquina].getEstado().equals("ACCIDENTADO")) {
			vCoche[maquina].rearrancar();
			actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
			pintaCarrera();
			asignaPodio(maquina);
			return;
		}
		if (vCoche[maquina].getEstado().equals("MARCHA") && vCoche[maquina].getHandicap() == 0) {
			if (vCoche[maquina].getVelocidad() > vCoche[maquina].getResistencia() * 0.60) {
				vCoche[maquina].frenar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[maquina].getKmRecorridos() > longitudCircuito) {
					vCoche[maquina].setKmRecorridos(longitudCircuito);
					vCoche[maquina].setEstado("TERMINADO");
					System.out.println("�" + vCoche[maquina].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[maquina].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(maquina);
				return;
			} else {
				vCoche[maquina].acelerar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[maquina].getKmRecorridos() > longitudCircuito) {
					vCoche[maquina].setKmRecorridos(longitudCircuito);
					vCoche[maquina].setEstado("TERMINADO");
					System.out.println("�" + vCoche[maquina].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[maquina].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(maquina);
				return;
			}
		}
		if (vCoche[maquina].getEstado().equals("MARCHA") && vCoche[maquina].getHandicap() == 15) {
			if (vCoche[maquina].getVelocidad() > vCoche[maquina].getResistencia() * 0.72) {
				vCoche[maquina].frenar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[maquina].getKmRecorridos() > longitudCircuito) {
					vCoche[maquina].setKmRecorridos(longitudCircuito);
					vCoche[maquina].setEstado("TERMINADO");
					System.out.println("�" + vCoche[maquina].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[maquina].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(maquina);
				return;
			} else {
				vCoche[maquina].acelerar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[maquina].getKmRecorridos() > longitudCircuito) {
					vCoche[maquina].setKmRecorridos(longitudCircuito);
					vCoche[maquina].setEstado("TERMINADO");
					System.out.println("�" + vCoche[maquina].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[maquina].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(maquina);
				return;
			}
		}
		if (vCoche[maquina].getEstado().equals("MARCHA") && vCoche[maquina].getHandicap() == 30) {
			if (vCoche[maquina].getVelocidad() > vCoche[maquina].getResistencia() * 0.84) {
				vCoche[maquina].frenar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[maquina].getKmRecorridos() > longitudCircuito) {
					vCoche[maquina].setKmRecorridos(longitudCircuito);
					vCoche[maquina].setEstado("TERMINADO");
					System.out.println("�" + vCoche[maquina].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[maquina].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(maquina);
			} else {
				vCoche[maquina].acelerar();
				actualizaPosicionDeLosPilotos(cuentaPilotosEnCarrera());
				if (vCoche[maquina].getKmRecorridos() > longitudCircuito) {
					vCoche[maquina].setKmRecorridos(longitudCircuito);
					vCoche[maquina].setEstado("TERMINADO");
					System.out.println("�" + vCoche[maquina].getNombreDelPiloto() + " ha terminado la carrera en "
							+ vCoche[maquina].getPosicion() + "� posici�n!");
				}
				pintaCarrera();
				asignaPodio(maquina);
			}
		}
	}

	/**
	 * Comprueba si la carrera ha finalizado.
	 * 
	 * @return false Devuelto si cualquier piloto tiene un valor distinto de
	 *         'TERMINADO o 'ABANDONADO' en el atributo 'estado' de la clase Coche.
	 * 
	 * @return true Devuelto si todos los pilotos en vCoche[] tienen un valor igual
	 *         a 'TERMINADO' o 'ABANDONADO' en el atributo 'estado' de la clase
	 *         Coche.
	 */
	private boolean carreraFinalizada() {
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && !vCoche[i].getEstado().equals("TERMINADO")
					&& !vCoche[i].getEstado().equals("ABANDONADO")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Comprueba la posici�n de un piloto al finalizar una carrera y lo registra en
	 * podio[] si ha terminado entre las tres primeras posiciones.
	 * 
	 * @param jugador Recibe la posici�n que ocupa un piloto al terminar la carrera
	 *                en el array vCoche[] para posteriormente comprobar su puesto
	 *                en la carrera.
	 */
	private void asignaPodio(int jugador) {
		if (vCoche[jugador].getEstado().equals("TERMINADO") && vCoche[jugador].getPosicion() == 1) {
			podio[0] = vCoche[jugador];
			return;
		}
		if (vCoche[jugador].getEstado().equals("TERMINADO") && vCoche[jugador].getPosicion() == 2) {
			podio[1] = vCoche[jugador];
			return;
		}
		if (vCoche[jugador].getEstado().equals("TERMINADO") && vCoche[jugador].getPosicion() == 3) {
			podio[2] = vCoche[jugador];
		}
	}

	/**
	 * Invocado cuando todos los participantes han terminado o abandonado la
	 * carrera. Imprime la posici�n y los pilotos almacenados en el array podio[]
	 */
	private void imprimePodio() {
		Menu.menuPodio();
		if (podio[0] != null) {
			System.out.printf("%-8s%-8s\n", " ", "   1�   ");
			System.out.printf("%-8s%-8s\n", " ", podio[0].getNombreDelPiloto() + " " + podio[0].getDorsal() + "\n");
		}
		if (podio[1] != null) {
			System.out.printf("%-1s\n", "   2�   ");
			System.out.printf("%-1s\n", podio[1].getNombreDelPiloto() + " " + podio[1].getDorsal() + "\n");
		}
		if (podio[2] != null) {
			System.out.printf("%-15s%-15s\n", " ", "   3�   ");
			System.out.printf("%-15s%-15s\n", " ", podio[2].getNombreDelPiloto() + " " + podio[2].getDorsal() + "\n");
		}
	}

	/**
	 * Imprime el resumen estad�stico de cada piloto en carrera tales como posici�n,
	 * velocidad, kil�metros recorridos, estado, etc.
	 * 
	 * @deprecated En desuso.
	 */
	private void imprimeResumenCarrera() {
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false) {
				System.out.println(vCoche[i]);
			}
		}
	}

	/**
	 * Imprime informaci�n relevante y estad�stica de los pilotos participantes en
	 * una carrera para conocer el desarrollo de la misma. La salida por pantalla
	 * ser� en bloques por columnas debidamente espaciadas formateadas con 'printf'.
	 */
	private void imprimeEstadisticasDeLosPilotos() {
		if (cuentaPilotosEnCarrera() == 2) {
			System.out.printf("%-25s%-25s\n",
					"(" + vCoche[0].getPosicion() + "�) " + vCoche[0].getNombreDelPiloto() + " "
							+ vCoche[0].getDorsal(),
					"(" + vCoche[1].getPosicion() + "�) " + vCoche[1].getNombreDelPiloto() + " "
							+ vCoche[1].getDorsal());
			System.out.printf("%-25s%-25s\n", "Pot: " + vCoche[0].getPotencia() + " Res: " + vCoche[0].getResistencia(),
					"Pot: " + vCoche[1].getPotencia() + " Res: " + vCoche[1].getResistencia());
			System.out.printf("%-25s%-25s\n", "Velocidad: " + vCoche[0].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[1].getVelocidad() + "Km/h");
			System.out.printf("%-25s%-25s\n", "Km Recorridos: " + vCoche[0].getKmRecorridos(),
					"Km Recorridos: " + vCoche[1].getKmRecorridos());
			System.out.printf("%-25s%-25s\n", vCoche[0].getEstado(), vCoche[1].getEstado());
			return;
		}
		if (cuentaPilotosEnCarrera() == 3) {
			System.out.printf("%-25s%-25s%-25s\n",
					"(" + vCoche[0].getPosicion() + "�) " + vCoche[0].getNombreDelPiloto() + " "
							+ vCoche[0].getDorsal(),
					"(" + vCoche[1].getPosicion() + "�) " + vCoche[1].getNombreDelPiloto() + " "
							+ vCoche[1].getDorsal(),
					"(" + vCoche[2].getPosicion() + "�) " + vCoche[2].getNombreDelPiloto() + " "
							+ vCoche[2].getDorsal());
			System.out.printf("%-25s%-25s%-25s\n",
					"Pot: " + vCoche[0].getPotencia() + " Res: " + vCoche[0].getResistencia(),
					"Pot: " + vCoche[1].getPotencia() + " Res: " + vCoche[1].getResistencia(),
					"Pot: " + vCoche[2].getPotencia() + " Res: " + vCoche[2].getResistencia());
			System.out.printf("%-25s%-25s%-25s\n", "Velocidad: " + vCoche[0].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[1].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[2].getVelocidad() + "Km/h");
			System.out.printf("%-25s%-25s%-25s\n", "Km Recorridos: " + vCoche[0].getKmRecorridos(),
					"Km Recorridos: " + vCoche[1].getKmRecorridos(), "Km Recorridos: " + vCoche[2].getKmRecorridos());
			System.out.printf("%-25s%-25s%-25s\n", vCoche[0].getEstado(), vCoche[1].getEstado(), vCoche[2].getEstado());
			return;
		}
		if (cuentaPilotosEnCarrera() == 4) {
			System.out.printf("%-25s%-25s%-25s%-25s\n",
					"(" + vCoche[0].getPosicion() + "�) " + vCoche[0].getNombreDelPiloto() + " "
							+ vCoche[0].getDorsal(),
					"(" + vCoche[1].getPosicion() + "�) " + vCoche[1].getNombreDelPiloto() + " "
							+ vCoche[1].getDorsal(),
					"(" + vCoche[2].getPosicion() + "�) " + vCoche[2].getNombreDelPiloto() + " "
							+ vCoche[2].getDorsal(),
					"(" + vCoche[3].getPosicion() + "�) " + vCoche[3].getNombreDelPiloto() + " "
							+ vCoche[3].getDorsal());
			System.out.printf("%-25s%-25s%-25s%-25s\n",
					"Pot: " + vCoche[0].getPotencia() + " Res: " + vCoche[0].getResistencia(),
					"Pot: " + vCoche[1].getPotencia() + " Res: " + vCoche[1].getResistencia(),
					"Pot: " + vCoche[2].getPotencia() + " Res: " + vCoche[2].getResistencia(),
					"Pot: " + vCoche[3].getPotencia() + " Res: " + vCoche[3].getResistencia());
			System.out.printf("%-25s%-25s%-25s%-25s\n", "Velocidad: " + vCoche[0].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[1].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[2].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[3].getVelocidad() + "Km/h");
			System.out.printf("%-25s%-25s%-25s%-25s\n", "Km Recorridos: " + vCoche[0].getKmRecorridos(),
					"Km Recorridos: " + vCoche[1].getKmRecorridos(), "Km Recorridos: " + vCoche[2].getKmRecorridos(),
					"Km Recorridos: " + vCoche[3].getKmRecorridos());
			System.out.printf("%-25s%-25s%-25s%-25s\n", vCoche[0].getEstado(), vCoche[1].getEstado(),
					vCoche[2].getEstado(), vCoche[3].getEstado());
			return;
		}
		if (cuentaPilotosEnCarrera() == 5) {
			System.out.printf("%-25s%-25s%-25s%-25s%-25s\n",
					"(" + vCoche[0].getPosicion() + "�) " + vCoche[0].getNombreDelPiloto() + " "
							+ vCoche[0].getDorsal(),
					"(" + vCoche[1].getPosicion() + "�) " + vCoche[1].getNombreDelPiloto() + " "
							+ vCoche[1].getDorsal(),
					"(" + vCoche[2].getPosicion() + "�) " + vCoche[2].getNombreDelPiloto() + " "
							+ vCoche[2].getDorsal(),
					"(" + vCoche[3].getPosicion() + "�) " + vCoche[3].getNombreDelPiloto() + " "
							+ vCoche[3].getDorsal(),
					"(" + vCoche[4].getPosicion() + "�) " + vCoche[4].getNombreDelPiloto() + " "
							+ vCoche[4].getDorsal());
			System.out.printf("%-25s%-25s%-25s%-25s%-25s\n",
					"Pot: " + vCoche[0].getPotencia() + " Res: " + vCoche[0].getResistencia(),
					"Pot: " + vCoche[1].getPotencia() + " Res: " + vCoche[1].getResistencia(),
					"Pot: " + vCoche[2].getPotencia() + " Res: " + vCoche[2].getResistencia(),
					"Pot: " + vCoche[3].getPotencia() + " Res: " + vCoche[3].getResistencia(),
					"Pot: " + vCoche[4].getPotencia() + " Res: " + vCoche[4].getResistencia());
			System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", "Velocidad: " + vCoche[0].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[1].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[2].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[3].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[4].getVelocidad() + "Km/h");
			System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", "Km Recorridos: " + vCoche[0].getKmRecorridos(),
					"Km Recorridos: " + vCoche[1].getKmRecorridos(), "Km Recorridos: " + vCoche[2].getKmRecorridos(),
					"Km Recorridos: " + vCoche[3].getKmRecorridos(), "Km Recorridos: " + vCoche[4].getKmRecorridos());
			System.out.printf("%-25s%-25s%-25s%-25s%-25s\n", vCoche[0].getEstado(), vCoche[1].getEstado(),
					vCoche[2].getEstado(), vCoche[3].getEstado(), vCoche[4].getEstado());
			return;
		}
		if (cuentaPilotosEnCarrera() == 6) {
			System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n",
					"(" + vCoche[0].getPosicion() + "�) " + vCoche[0].getNombreDelPiloto() + " "
							+ vCoche[0].getDorsal(),
					"(" + vCoche[1].getPosicion() + "�) " + vCoche[1].getNombreDelPiloto() + " "
							+ vCoche[1].getDorsal(),
					"(" + vCoche[2].getPosicion() + "�) " + vCoche[2].getNombreDelPiloto() + " "
							+ vCoche[2].getDorsal(),
					"(" + vCoche[3].getPosicion() + "�) " + vCoche[3].getNombreDelPiloto() + " "
							+ vCoche[3].getDorsal(),
					"(" + vCoche[4].getPosicion() + "�) " + vCoche[4].getNombreDelPiloto() + " "
							+ vCoche[4].getDorsal(),
					"(" + vCoche[5].getPosicion() + "�) " + vCoche[5].getNombreDelPiloto() + " "
							+ vCoche[5].getDorsal());
			System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n",
					"Pot: " + vCoche[0].getPotencia() + " Res: " + vCoche[0].getResistencia(),
					"Pot: " + vCoche[1].getPotencia() + " Res: " + vCoche[1].getResistencia(),
					"Pot: " + vCoche[2].getPotencia() + " Res: " + vCoche[2].getResistencia(),
					"Pot: " + vCoche[3].getPotencia() + " Res: " + vCoche[3].getResistencia(),
					"Pot: " + vCoche[4].getPotencia() + " Res: " + vCoche[4].getResistencia(),
					"Pot: " + vCoche[5].getPotencia() + " Res: " + vCoche[5].getResistencia());
			System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n", "Velocidad: " + vCoche[0].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[1].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[2].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[3].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[4].getVelocidad() + "Km/h",
					"Velocidad: " + vCoche[5].getVelocidad() + "Km/h");
			System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n", "Km Recorridos: " + vCoche[0].getKmRecorridos(),
					"Km Recorridos: " + vCoche[1].getKmRecorridos(), "Km Recorridos: " + vCoche[2].getKmRecorridos(),
					"Km Recorridos: " + vCoche[3].getKmRecorridos(), "Km Recorridos: " + vCoche[4].getKmRecorridos(),
					"Km Recorridos: " + vCoche[5].getKmRecorridos());
			System.out.printf("%-25s%-25s%-25s%-25s%-25s%-25s\n", vCoche[0].getEstado(), vCoche[1].getEstado(),
					vCoche[2].getEstado(), vCoche[3].getEstado(), vCoche[4].getEstado(), vCoche[5].getEstado());
		}
	}

	/**
	 * Usado en varios m�todos para conocer el n�mero de participantes en una
	 * carrera.
	 * 
	 * @return numeroDePilotos N�mero de participantes en una carrera.
	 */
	private int cuentaPilotosEnCarrera() {
		int numeroDePilotos = 0;
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false) {
				numeroDePilotos++;
			}
		}
		return numeroDePilotos;
	}

	/**
	 * Asigna y muestra el orden de salida y acci�n de los pilotos antes de comenzar
	 * la carrera de manera aleatoria.
	 * 
	 * @param pilotos int Recibe el n�mero de pilotos que participar�n en la
	 *                carrera.
	 */
	private void asignarOrdenDeSalida(int pilotos) {
		Random r = new Random();
		int[] asignadorDeTurnos = new int[pilotos];
		int turnoAsignado = 0;

		for (int i = 0; i < asignadorDeTurnos.length; i++) {
			asignadorDeTurnos[i] = i + 1;
		}

		for (int i = 0; i < vCoche.length; i++) {
			while (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getTurno() == 0) {
				turnoAsignado = r.nextInt(pilotos);
				if (vCoche[i] != null && vCoche[i].isBloqueado() == false) {
					vCoche[i].setTurno(asignadorDeTurnos[turnoAsignado]);
					vCoche[i].setPosicion(asignadorDeTurnos[turnoAsignado]);
					asignadorDeTurnos[turnoAsignado] = 0;
				}
			}
		}
		Menu.menuSalida();
		for (int i = 0; i < pilotos; i++) {
			System.out.println(
					"�" + vCoche[i].getNombreDelPiloto() + " sale en " + vCoche[i].getTurno() + "� posici�n!\n");
		}
		Utilidades.enterParaSalida();
		Utilidades.limpiarPantalla();
	}

	/**
	 * Actualiza la posici�n de los pilotos tras cada acci�n que realicen. Esto ser�
	 * vital a la hora de asignar las posiciones del podio[] y para imprimir la
	 * carrera a trav�s de los m�todos asignaPodio() y pintaCarrera().
	 * 
	 * @param pilotos int Recibe el n�mero de pilotos participando en carrera.
	 */
	private void actualizaPosicionDeLosPilotos(int pilotos) {
		int[] asignadorDePosiciones = new int[pilotos];
		int posicionAsignada = (pilotos - 1);

		for (int i = 0; i < asignadorDePosiciones.length; i++) {
			asignadorDePosiciones[i] = i + 1;
		}

		for (int i = 0; i < pilotos; i++) {
			posicionAsignada = (pilotos - 1);
			for (int j = 0; j < pilotos; j++) {
				if (i != j) {
					if (vCoche[i] != null && vCoche[i].isBloqueado() == false
							&& !vCoche[i].getEstado().equals("TERMINADO")) {
						if (vCoche[j] != null && vCoche[j].isBloqueado() == false
								&& !vCoche[j].getEstado().equals("TERMINADO")) {
							if (vCoche[i].getKmRecorridos() > vCoche[j].getKmRecorridos()) {
								posicionAsignada--;
							}
						}
					}
				}
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && !vCoche[i].getEstado().equals("TERMINADO")) {
				vCoche[i].setPosicion(asignadorDePosiciones[posicionAsignada]);
			}
		}
	}

	/**
	 * Imprime un array bidimensional String[][] que emula la carrera sin tener en
	 * cuenta la distancia recorrida por los pilotos, tan s�lo su posici�n. Ser� el
	 * m�todo invocado si el usuario seleccion� esta vista cuando configur� la
	 * carrera. Internamente corrige las fallas que provocar�a en un String imprimir
	 * un piloto con dorsal de una cifra frente a otro de dos.
	 */
	private void vistaPorPosicion() {
		String[][] circuito = new String[cuentaPilotosEnCarrera()][9];

		for (int i = 0; i < circuito.length; i++) {
			for (int j = 0; j < circuito[i].length; j++) {
				if (j == 7) {
					if (vCoche[i] != null && vCoche[i].isBloqueado() == false
							&& String.valueOf(vCoche[i].getDorsal()).length() == 2) {
						circuito[i][j] = "      |";
					} else {
						circuito[i][j] = "       |";
					}
				} else {
					circuito[i][j] = "        ";
				}
			}
		}

		for (int i = 0; i < circuito.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getPosicion() == 1) {
				circuito[i][5] = " o(" + vCoche[i].getDorsal() + ")o ";
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getPosicion() == 2) {
				circuito[i][4] = " o(" + vCoche[i].getDorsal() + ")o ";
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getPosicion() == 3) {
				circuito[i][3] = " o(" + vCoche[i].getDorsal() + ")o ";
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getPosicion() == 4) {
				circuito[i][2] = " o(" + vCoche[i].getDorsal() + ")o ";
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getPosicion() == 5) {
				circuito[i][1] = " o(" + vCoche[i].getDorsal() + ")o ";
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getPosicion() == 6) {
				circuito[i][0] = " o(" + vCoche[i].getDorsal() + ")o ";
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getEstado().equals("TERMINADO")) {
				for (int j = 0; j < 7; j++) {
					circuito[i][j] = "        ";
				}

				circuito[i][8] = " o(" + vCoche[i].getDorsal() + ")o ";

				if (vCoche[i] != null && vCoche[i].isBloqueado() == false
						&& String.valueOf(vCoche[i].getDorsal()).length() != 2) {
					circuito[i][6] = "       ";
				}
			}
		}

		for (int i = 0; i < circuito.length; i++) {
			for (int j = 0; j < circuito[i].length; j++) {
				System.out.print(circuito[i][j] + " ");
			}
			System.out.println("");
		}
	}

	/**
	 * Imprime la carrera bas�ndose en la distancia recorrida por los pilotos frente
	 * a la longitud del circuito seleccionado partiendo de que esta longitud
	 * representa el 100%. Ser� el m�todo invocado si el usuario seleccion� esta
	 * vista cuando configur� la carrera. Internamente corrige las fallas que
	 * provocar�a en un String imprimir un piloto con dorsal de una cifra frente a
	 * otro de dos.
	 */
	private void vistaClasica() {
		String[] pista = new String[cuentaPilotosEnCarrera()];

		for (int i = 0; i < pista.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getKmRecorridos() == 0) {
				if (String.valueOf(vCoche[i].getDorsal()).length() == 2) {
					pista[i] = "o(" + vCoche[i].getDorsal()
							+ ")o                                                                                                     |";
				} else {
					pista[i] = "o(" + vCoche[i].getDorsal()
							+ ")o                                                                                                      |";
				}
			} else {
				pista[i] = " ";
			}
		}
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < pista.length; j++) {
				if (vCoche[j] != null && vCoche[j].isBloqueado() == false
						&& vCoche[j].getKmRecorridos() >= (double) (longitudCircuito * (i + 0.001)) / 100
						&& vCoche[j].getKmRecorridos() <= (double) (longitudCircuito * (i + 1)) / 100
						&& !vCoche[j].getEstado().equals("TERMINADO")) {
					pista[j] += "o(" + vCoche[j].getDorsal() + ")o";
					continue;
				} else {
					pista[j] += " ";
				}
			}
		}
		for (int i = 0; i < pista.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getKmRecorridos() == 0) {
				System.out.println(pista[i]);
				continue;
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getEstado().equals("TERMINADO")) {
				System.out.println(pista[i] + "      |" + "o(" + vCoche[i].getDorsal() + ")o");
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false
					&& String.valueOf(vCoche[i].getDorsal()).length() == 2
					&& !vCoche[i].getEstado().equals("TERMINADO")) {
				System.out.println(pista[i] + " |");
			}
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false
					&& String.valueOf(vCoche[i].getDorsal()).length() == 1
					&& !vCoche[i].getEstado().equals("TERMINADO")) {
				System.out.println(pista[i] + "  |   ");
			}
		}
		System.out.println();
	}

	/**
	 * M�todo invocado tras cada acci�n llevada a cabo en carrera, tanto por el
	 * usuario como por la IA. Imprimir� uno de los dos m�todos de visualizaci�n en
	 * base a la opci�n que escogi� el usuario al configurar la carrera.
	 */
	private void pintaCarrera() {
		if (vista.equals("CL�SICA")) {
			vistaClasica();
		}
		if (vista.equals("POSICI�N")) {
			vistaPorPosicion();
		}
	}

	/**
	 * En base a la opci�n escogida por el usuario al configurar la carrera fija el
	 * valor del atributo 'vista' en 'CL�SICA' o 'POSICI�N'.
	 * 
	 * @param eleccion int que recibe el m�todo seg�n la opci�n escogida por el
	 *                 usuario.
	 */
	public void fijaVisualizacionDeCarrera(int eleccion) {
		if (eleccion == 0) {
			vista = "CL�SICA";
		}
		if (eleccion == 1) {
			vista = "POSICI�N";
		}
	}

	/**
	 * M�todo que otorga una cantidad aleatoria de dinero al finalizar la carrera a
	 * los pilotos en podio[] controlados por un usuario. Cuanto mejor sea la
	 * posici�n del piloto, m�s largo el circuito y mayor la dificultad seleccionada
	 * al configurar la carrera, los rangos del n�mero aleatorio que determina los
	 * fondos ganados ser�n superiores. Tambi�n comprueba si un piloto cumple los
	 * m�nimos para desbloquear el contenido oculto y de ser as�, lo desbloquea y
	 * pasa a ser seleccionable en una futura carrera.
	 * 
	 * @param humano Actualmente en desuso.
	 * 
	 * @param iA     int recibido a trav�s del m�todo rastreaIA(). En base a esta
	 *               posici�n se comprueba el valor del atributo 'handicap' y seg�n
	 *               �ste, sabremos el nivel de dificultad que configur� el usuario
	 *               para asignar una recompensa u otra.
	 */
	private void recompensaPorPosicion(int humano, int iA) {
		Random r = new Random();
		int fondosGanados;

		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 0
				&& nombreCircuito.equals("GP Motorland Arag�n")) {
			podio[0].setVictoriasNormal(podio[0].getVictoriasNormal() + 1);
			podio[0].setVictoriasMotorland(podio[0].getVictoriasMotorland() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(100) + r.nextInt(200);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 15
				&& nombreCircuito.equals("GP Motorland Arag�n")) {
			podio[0].setVictoriasDificil(podio[0].getVictoriasDificil() + 1);
			podio[0].setVictoriasMotorland(podio[0].getVictoriasMotorland() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(500) + r.nextInt(200);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 30
				&& nombreCircuito.equals("GP Motorland Arag�n")) {
			podio[0].setVictoriasExperto(podio[0].getVictoriasExperto() + 1);
			podio[0].setVictoriasMotorland(podio[0].getVictoriasMotorland() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(1000) + r.nextInt(200);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 0
				&& nombreCircuito.equals("GP M�naco")) {
			podio[0].setVictoriasNormal(podio[0].getVictoriasNormal() + 1);
			podio[0].setVictoriasMonaco(podio[0].getVictoriasMonaco() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(100) + r.nextInt(400);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 15
				&& nombreCircuito.equals("GP M�naco")) {
			podio[0].setVictoriasDificil(podio[0].getVictoriasDificil() + 1);
			podio[0].setVictoriasMonaco(podio[0].getVictoriasMonaco() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(500) + r.nextInt(400);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 30
				&& nombreCircuito.equals("GP M�naco")) {
			podio[0].setVictoriasExperto(podio[0].getVictoriasExperto() + 1);
			podio[0].setVictoriasMonaco(podio[0].getVictoriasMonaco() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(1000) + r.nextInt(400);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 0
				&& nombreCircuito.equals("24 Horas de Le Mans")) {
			podio[0].setVictoriasNormal(podio[0].getVictoriasNormal() + 1);
			podio[0].setVictoriasLeMans(podio[0].getVictoriasLeMans() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(100) + r.nextInt(800);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 15
				&& nombreCircuito.equals("24 Horas de Le Mans")) {
			podio[0].setVictoriasDificil(podio[0].getVictoriasDificil() + 1);
			podio[0].setVictoriasLeMans(podio[0].getVictoriasLeMans() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(500) + r.nextInt(800);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 30
				&& nombreCircuito.equals("24 Horas de Le Mans")) {
			podio[0].setVictoriasExperto(podio[0].getVictoriasExperto() + 1);
			podio[0].setVictoriasLeMans(podio[0].getVictoriasLeMans() + 1);
			fondosGanados = r.nextInt(300) + r.nextInt(1000) + r.nextInt(800);
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 0
				&& nombreCircuito.equals("Rally Dakar")) {
			podio[0].setVictoriasNormal(podio[0].getVictoriasNormal() + 1);
			podio[0].setVictoriasRally(podio[0].getVictoriasRally() + 1);
			fondosGanados = r.nextInt(300) + 300 + r.nextInt(100) + 300 + r.nextInt(1400) + 300;
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 15
				&& nombreCircuito.equals("Rally Dakar")) {
			podio[0].setVictoriasDificil(podio[0].getVictoriasDificil() + 1);
			podio[0].setVictoriasRally(podio[0].getVictoriasRally() + 1);
			fondosGanados = r.nextInt(300) + 500 + r.nextInt(500) + 500 + r.nextInt(1400) + 500;
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0] != null && podio[0].getInteligencia().equals("HUMANO") && vCoche[iA].getHandicap() == 30
				&& nombreCircuito.equals("Rally Dakar")) {
			podio[0].setVictoriasExperto(podio[0].getVictoriasExperto() + 1);
			podio[0].setVictoriasRally(podio[0].getVictoriasRally() + 1);
			fondosGanados = r.nextInt(300) + 800 + r.nextInt(1000) + 800 + r.nextInt(1400) + 800;
			podio[0].setDinero(podio[0].getDinero() + fondosGanados);
			System.out.println("�" + podio[0].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[1] != null && podio[1].getInteligencia().equals("HUMANO")) {
			podio[1].setSegundosPuestos(podio[1].getSegundosPuestos() + 1);
			fondosGanados = r.nextInt(300);
			podio[1].setDinero(podio[1].getDinero() + fondosGanados);
			System.out.println("�" + podio[1].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[2] != null && podio[2].getInteligencia().equals("HUMANO")) {
			podio[2].setTercerosPuestos(podio[1].getTercerosPuestos() + 1);
			fondosGanados = r.nextInt(150);
			podio[2].setDinero(podio[2].getDinero() + fondosGanados);
			System.out.println("�" + podio[2].getNombreDelPiloto() + " ha ganado " + fondosGanados + "$!");
		}
		if (podio[0].getVictoriasDificil() >= 3 && vDificultad[2].equals("3- ????????")) {
			vDificultad[2] = "3- Experto";
			System.out.println("�Has desbloqueado el modo Experto!");
		}
		if (podio[0].getVictoriasExperto() >= 3 && vCoche[4].isBloqueado() == true) {
			vCoche[4].setBloqueado(false);
			System.out.println("�Has desbloqueado a Fernando Alonso!");
		}
		if (podio[0].getVictoriasMotorland() >= 3 && podio[0].getVictoriasMonaco() >= 3
				&& podio[0].getVictoriasLeMans() >= 3 && podio[0].getVictoriasDificil() >= 5
				&& vCircuito[3].equals("4- ????????")) {
			vCircuito[3] = "4- Rally Dakar (4000Km)";
			System.out.println("�Has desbloqueado el Rally Dakar!");
		}
		if (podio[0].getVictoriasDificil() >= 10 && podio[0].getVictoriasExperto() >= 5
				&& podio[0].getVictoriasMotorland() >= 5 && podio[0].getVictoriasMonaco() >= 5
				&& podio[0].getVictoriasLeMans() >= 5 && podio[0].getVictoriasRally() >= 5
				&& vCoche[5].isBloqueado() == true) {
			vCoche[5].setBloqueado(false);
			System.out.println("�Has desbloqueado a Michael Schumacher!");
		}
	}

	/**
	 * Recorre vCoche[] hasta encontrar la primera posici�n que contiene el atributo
	 * 'inteligencia' como 'HUMANO'. �til en el modo individual en el que tan solo
	 * hay un jugador controlado por el usuario.
	 * 
	 * @return i Devuelve la primera posici�n que contiene un piloto controlado por
	 *         el jugador.
	 * 
	 * @deprecated En desuso, pero podr�a ser �til en una futura modificaci�n que
	 *             otorgara recompensas a jugadores que no ocupan puestos en
	 *             podio[].
	 */
	private int rastreaHumano() {
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getInteligencia().equals("HUMANO")) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Recorre vCoche[] hasta encontrar la primera posici�n que contiene el atributo
	 * 'inteligencia' como 'IA'. Usado en el m�todo recompensaPorPosicion() en el
	 * que tan solo necesitamos obtener el valor del atributo 'handicap' de la IA
	 * para saber el nivel de dificultad que escogi� el usuario al configurar la
	 * carrera y otorgar una recompensa en base a ello.
	 * 
	 * @return i Devuelve la primera posici�n que contiene un piloto controlado por
	 *         la IA.
	 * 
	 * @return 0 Devuelto si no hay pilotos controlados por la IA. Esto es �til en
	 *         el modo multijugador si todos los pilotos fueran controlados por
	 *         usuarios, ya que el atributo 'handicap' tendr�a un valor '0' y el
	 *         usuario no podr�a aprovecharse de haber escogido un modo de
	 *         dificultad superior con la finalidad de obtener mejores recompensas.
	 */
	private int rastreaIA() {
		for (int i = 0; i < vCoche.length; i++) {
			if (vCoche[i] != null && vCoche[i].isBloqueado() == false && vCoche[i].getInteligencia().equals("IA")) {
				return i;
			}
		}
		return 0;
	}
}
