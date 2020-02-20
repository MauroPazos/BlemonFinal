package configuraciones;

import java.net.InetAddress;
import java.net.UnknownHostException;

public abstract class Cfg {
	
	//Configuracion de la red
	public static final int PUERTO = 9000;
	
	//Configuracion de los graficos
	public static final String TITULO = "Blemon 2.2.7";
	public static final int ANCHO = 1366;
	public static final int ALTO = 688;
	public static final boolean REDIMENSIONABLE = true;
	public static final int BACKGROUNDFPS = 60;
	public static final int FOREGROUNDFPS = 60;
	
	//Configuracion del Tumbal
	public static final float TAMAÑOBLOQUE = 20;
	public static final int ALTOMATRIZ = 20;
	public static final int ANCHOMATRIZ = 10;
	public static final int CANTIDADLINEASCAMBIO = 10;
	public static final int PUNTAJELINEA = 100;
	
	
	//Configuracion del Buscaminas
	public static final float TAMAÑOMINA = 25;
	public static final int ALTOMATRIZDIFICIL = 16;
	public static final int ANCHOMATRIZDIFICIL = 30;
	public static final int CANTIDADMINAS = 100;
	
	//Configuracion de la red
	public static boolean empieza = false;
	public static boolean esServidor;
	public static int NUMCLIEN = 1;
	public static int CANTIDADJUGADORES = 2;
	public static InetAddress getIp() {
		InetAddress ip = null;
		try {
			ip= InetAddress.getByName(new String("255.255.255.255"));
		} catch (UnknownHostException e) {}
		return ip;
	}
}
