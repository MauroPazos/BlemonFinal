package jugadores;

import redes.HiloCliente;

public class Jugador {
	public float dinero;
	public float puntaje;
	private String nombre;
	public HiloCliente hilo;
	public static boolean conectado;
	
	public Jugador(String nombre) {
		this.dinero = 0;
		this.puntaje = 0;
		this.nombre = nombre;
//		hilo = new HiloCliente();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void sumarPuntaje(float puntaje) {
		this.puntaje += puntaje;
	} 
	
	public void sumarDinero(float dinero) {
		this.dinero += dinero;
	}
	
//	private static void mandarTecla(int tecla) {
//		if(conectado) {
//			hilo.enviarTecla(tecla);
//		} else {
//			System.out.println("Debe conectarse al server");
//		}
//	}
	/*Aca creo que deberia hacer la funcion que dije de hacer en HiloCliente.
	 * La que devuelva los datos que llegan desde el servidor en forma de objeto
	 * o de lo que fuera para poder updatear las cosas que se muestran por el render
	 * de cada pantalla*/
}
