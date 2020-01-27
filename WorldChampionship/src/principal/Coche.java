package principal;

import java.util.Random;

public class Coche {
	private String nombreDelPiloto;
	private int dorsal;
	private String estado;
	private String inteligencia;
	private int potencia;
	private double velocidad;
	private int resistencia;
	private double kmRecorridos;
	private int posicion;
	private int dinero;
	private int handicap;
	private boolean bloqueado;
	private int victoriasTotales;
	private int victoriasNormal;
	private int victoriasDificil;
	private int victoriasExperto;
	private int victoriasMotorland;
	private int victoriasMonaco;
	private int victoriasLeMans;
	private int victoriasRally;
	private int segundosPuestos;
	private int tercerosPuestos;
	private int abandonos;
	private int turno;

	public Coche(String nombreDelPiloto, int dorsal) {
		this.nombreDelPiloto = nombreDelPiloto;
		this.dorsal = dorsal;
		estado = "PARADO";
		inteligencia = "IA";
		potencia = 50;
		velocidad = 0;
		resistencia = 200;
		kmRecorridos = 0;
		posicion = 0;
		dinero = 0;
		handicap = 0;
		bloqueado = false;
		victoriasTotales = victoriasNormal + victoriasDificil + victoriasExperto;
		victoriasNormal = 0;
		victoriasDificil = 0;
		victoriasExperto = 0;
		victoriasMotorland = 0;
		victoriasMonaco = 0;
		victoriasLeMans = 0;
		victoriasRally = 0;
		segundosPuestos = 0;
		tercerosPuestos = 0;
		abandonos = 0;
		turno = 0;
	}

	public Coche(String nombreDelPiloto, int dorsal, int potencia, int resistencia, boolean bloqueado) {
		this.nombreDelPiloto = nombreDelPiloto;
		this.dorsal = dorsal;
		estado = "PARADO";
		inteligencia = "IA";
		this.potencia = potencia;
		velocidad = 0;
		this.resistencia = resistencia;
		kmRecorridos = 0;
		posicion = 0;
		dinero = 0;
		handicap = 0;
		this.bloqueado = bloqueado;
		victoriasTotales = victoriasNormal + victoriasDificil + victoriasExperto;
		victoriasNormal = 0;
		victoriasDificil = 0;
		victoriasExperto = 0;
		victoriasMotorland = 0;
		victoriasMonaco = 0;
		victoriasLeMans = 0;
		victoriasRally = 0;
		segundosPuestos = 0;
		tercerosPuestos = 0;
		abandonos = 0;
		turno = 0;
	}

	public void arrancar() {
		if (estado.equals("MARCHA")) {
			System.out.println("¡El coche ya está en marcha!");
			return;
		}
		if (estado.equals("PARADO")) {
			estado = "MARCHA";
			System.out.println("¡" + nombreDelPiloto + " arranca!");
		}
		if (estado.equals("ACCIDENTADO")) {
			System.out.println("¡Necesita rearrancar primero!");
		}
	}

	public void acelerar() {
		Random r = new Random();
		if (estado.equals("PARADO")) {
			System.out.println("¡Primero tiene que arrancar!");
			return;
		}
		if (estado.equals("ACCIDENTADO")) {
			System.out.println("¡Necesita rearrancar primero!");
			return;
		}
		if (estado.equals("MARCHA")) {
			velocidad += r.nextInt(potencia) + 1 + (r.nextInt(handicap + 1));
			System.out.println("¡" + nombreDelPiloto + " acelera!");
		}
		if (velocidad > resistencia + (r.nextInt(handicap + 1))) {
			estado = "ACCIDENTADO";
			velocidad = 0;
			System.out.println("¡" + nombreDelPiloto + " ha tenido un brutal accidente!");
		} else {
			kmRecorridos += velocidad;
		}

	}

	public void frenar() {
		Random r = new Random();
		if (estado.equals("PARADO")) {
			System.out.println("¡Primero tiene que arrancar!");
			return;
		}
		if (estado.equals("ACCIDENTADO")) {
			System.out.println("¡Necesita rearrancar primero!");
			return;
		}
		if (estado.equals("MARCHA")) {
			velocidad -= r.nextInt(potencia) + 1;
			if (velocidad < 0) {
				velocidad = 0;
			}
			kmRecorridos += velocidad;
			System.out.println("¡" + nombreDelPiloto + " frena!");
		}
	}

	public void rearrancar() {
		if (estado.equals("PARADO")) {
			System.out.println("¡El coche no está en situación de accidente!");
			return;
		}
		if (estado.equals("MARCHA")) {
			System.out.println("¡El coche no está en situación de accidente!");
			return;
		}
		if (estado.equals("ACCIDENTADO")) {
			System.out.println("¡" + nombreDelPiloto + " consigue volver a pista!");
			estado = "PARADO";
		}
	}

	public String getNombreDelPiloto() {
		return nombreDelPiloto;
	}

	public int getDorsal() {
		return dorsal;
	}

	public String getEstado() {
		return estado;
	}

	public String getInteligencia() {
		return inteligencia;
	}

	public int getPotencia() {
		return potencia;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public int getResistencia() {
		return resistencia;
	}

	public double getKmRecorridos() {
		return kmRecorridos;
	}

	public int getPosicion() {
		return posicion;
	}

	public int getDinero() {
		return dinero;
	}

	public int getVictorias() {
		return victoriasTotales;
	}

	public int getVictoriasNormal() {
		return victoriasNormal;
	}

	public int getVictoriasDificil() {
		return victoriasDificil;
	}

	public int getVictoriasExperto() {
		return victoriasExperto;
	}

	public int getVictoriasMotorland() {
		return victoriasMotorland;
	}

	public int getVictoriasMonaco() {
		return victoriasMonaco;
	}

	public int getVictoriasLeMans() {
		return victoriasLeMans;
	}

	public boolean isBloqueado() {
		return bloqueado;
	}

	public int getTurno() {
		return turno;
	}

	public int getHandicap() {
		return handicap;
	}

	public int getVictoriasRally() {
		return victoriasRally;
	}

	public int getSegundosPuestos() {
		return segundosPuestos;
	}

	public int getTercerosPuestos() {
		return tercerosPuestos;
	}

	public int getAbandonos() {
		return abandonos;
	}

	public void setSegundosPuestos(int segundosPuestos) {
		this.segundosPuestos = segundosPuestos;
	}

	public void setTercerosPuestos(int tercerosPuestos) {
		this.tercerosPuestos = tercerosPuestos;
	}

	public void setAbandonos(int abandonos) {
		this.abandonos = abandonos;
	}

	public void setVictoriasRally(int victoriasRally) {
		this.victoriasRally = victoriasRally;
	}

	public void setNombreDelPiloto(String nombreDelPiloto) {
		this.nombreDelPiloto = nombreDelPiloto;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setInteligencia(String inteligencia) {
		this.inteligencia = inteligencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public void setResistencia(int resistencia) {
		this.resistencia = resistencia;
	}

	public void setKmRecorridos(double kmRecorridos) {
		this.kmRecorridos = kmRecorridos;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public void setVictorias(int victorias) {
		this.victoriasTotales = victorias;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	public void setHandicap(int handicap) {
		this.handicap = handicap;
	}

	public void setTurno(int turno) {
		this.turno = turno;
	}

	public int getVictoriasTotales() {
		return victoriasTotales;
	}

	public void setVictoriasTotales(int victoriasTotales) {
		this.victoriasTotales = victoriasTotales;
	}

	public void setVictoriasNormal(int victoriasNormal) {
		this.victoriasNormal = victoriasNormal;
	}

	public void setVictoriasDificil(int victoriasDificil) {
		this.victoriasDificil = victoriasDificil;
	}

	public void setVictoriasExperto(int victoriasExperto) {
		this.victoriasExperto = victoriasExperto;
	}

	public void setVictoriasMotorland(int victoriasMotorland) {
		this.victoriasMotorland = victoriasMotorland;
	}

	public void setVictoriasMonaco(int victoriasMonaco) {
		this.victoriasMonaco = victoriasMonaco;
	}

	public void setVictoriasLeMans(int victoriasLeMans) {
		this.victoriasLeMans = victoriasLeMans;
	}

	@Override
	public String toString() {
		return "(" + posicion + "º) " + nombreDelPiloto + " " + dorsal + " " + estado
				+ "\nVelocidad: " + velocidad + "/" + resistencia + " Km/h"
				+ "\nKm Recorridos: " + kmRecorridos + "\n";
	}

}
