package principal;

/**
 * Simulador de competiciones entre vehículos registrados manualmente por el
 * usuario. Dispone de varios niveles de dificultad en base a los cuales
 * responderá la IA, así como varios circuitos y logros desbloqueables.
 * Implementado un modo multijugador en el cual podrán participar hasta un
 * máximo de 4 jugadores. Cuenta con un apartado de mejoras en el que el usuario
 * podrá potenciar su vehículo con los fondos virtuales que adquiera tras lograr
 * entrar en el podio de cada competición. Durante la configuración de la
 * carrera el usuario podrá escoger entre dos tipos de visualización del
 * progreso de la misma.
 * 
 * @author borjaLoren
 * @version 2.9.9
 * @since 20-01-2020
 *
 */
public class Main {
	/**
	 * Método principal en el que comienza la ejecución de la aplicación. Al inicio
	 * se declara un nuevo tipo de dato 'Carrera' con el que se invocarán los
	 * métodos en dicha clase. Se llamará a los métodos estáticos contenidos en la
	 * clase 'Menu' para mostrar, navegar e interactuar con las opciones que
	 * propongan los menús con los que cuenta la aplicación. En base a las opciones
	 * escogidas el usuario podrá configurar la carrera, registrar pilotos y
	 * mejoras.
	 * 
	 * @param String[] args
	 * 
	 */
	public static void main(String[] args) {
		Carrera c = new Carrera();
		c.registraPilotosBloqueados();
		c.registraCircuitos();
		c.registraDificultad();
		int opcion;

		do {
			try {
				c.reset();
				switch (opcion = Menu.menuPrincipal()) {
				case 1:
					switch (opcion = Menu.menuModoDeJuego()) {
					case 1:
						c.eleccionDePilotoParaCompetir();
						switch (opcion = Menu.menuSeleccionCircuito(c)) {
						case 1:
							c.fijaNombreLongitud(0);
							switch (opcion = Menu.menuSeleccionDificultad(c)) {
							case 1:
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 2:
								c.fijaDificultadIA(0);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 3:
								c.fijaDificultadIA(1);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
							}
							break;
						case 2:
							c.fijaNombreLongitud(1);
							switch (opcion = Menu.menuSeleccionDificultad(c)) {
							case 1:
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 2:
								c.fijaDificultadIA(0);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 3:
								c.fijaDificultadIA(1);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
							}
							break;
						case 3:
							c.fijaNombreLongitud(2);
							switch (opcion = Menu.menuSeleccionDificultad(c)) {
							case 1:
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 2:
								c.fijaDificultadIA(0);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 3:
								c.fijaDificultadIA(1);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
							}
							break;
						case 4:
							c.fijaNombreLongitud(3);
							switch (opcion = Menu.menuSeleccionDificultad(c)) {
							case 1:
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 2:
								c.fijaDificultadIA(0);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
								break;
							case 3:
								c.fijaDificultadIA(1);
								switch (opcion = Menu.menuSeleccionVista()) {
								case 1:
									c.fijaVisualizacionDeCarrera(0);
									c.inicioCarrera();
									break;
								case 2:
									c.fijaVisualizacionDeCarrera(1);
									c.inicioCarrera();
								}
							}
						}
						break;
					case 2:
						switch (opcion = Menu.menuNumeroJugadores()) {
						case 1:
							c.modoMultijugadorSeleccionJugadores(2);
							switch (opcion = Menu.menuSeleccionCircuito(c)) {
							case 1:
								c.fijaNombreLongitud(0);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 2:
								c.fijaNombreLongitud(1);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 3:
								c.fijaNombreLongitud(2);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 4:
								c.fijaNombreLongitud(3);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
							}
							break;
						case 2:
							c.modoMultijugadorSeleccionJugadores(3);
							switch (opcion = Menu.menuSeleccionCircuito(c)) {
							case 1:
								c.fijaNombreLongitud(0);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 2:
								c.fijaNombreLongitud(1);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 3:
								c.fijaNombreLongitud(2);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 4:
								c.fijaNombreLongitud(3);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
							}
							break;
						case 3:
							c.modoMultijugadorSeleccionJugadores(4);
							switch (opcion = Menu.menuSeleccionCircuito(c)) {
							case 1:
								c.fijaNombreLongitud(0);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 2:
								c.fijaNombreLongitud(1);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 3:
								c.fijaNombreLongitud(2);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
								break;
							case 4:
								c.fijaNombreLongitud(3);
								switch (opcion = Menu.menuSeleccionDificultad(c)) {
								case 1:
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 2:
									c.fijaDificultadIA(0);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
									break;
								case 3:
									c.fijaDificultadIA(1);
									switch (opcion = Menu.menuSeleccionVista()) {
									case 1:
										c.fijaVisualizacionDeCarrera(0);
										c.inicioCarrera();
										break;
									case 2:
										c.fijaVisualizacionDeCarrera(1);
										c.inicioCarrera();
									}
								}
							}
							break;
						}
						break;
					}
					break;
				case 2:
					c.registrarPiloto();
					break;
				case 3:
					switch (opcion = Menu.menuMejoras()) {
					case 1:
						c.mejoraPotencia();
						break;
					case 2:
						c.mejoraResistencia();
						break;
					case 3:
					}
					break;
				case 4:
					c.listadoDePilotos();
					c.verPalmaresPiloto();
					break;
				case 5:
					System.exit(0);
				}
			} catch (ExcepcionModalidadBloqueada e) {
				System.err.println(e.getMessage());
				Utilidades.enterParaMenuPrincipal();
			} catch (Exception e) {
				System.err.println("Error. Dato no válido. La acción no se ha podido llevar a cabo.");
				Utilidades.enterParaMenuPrincipal();
			}
		} while (true);
	}
}
