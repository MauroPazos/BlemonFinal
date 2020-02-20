package redes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.badlogic.gdx.Gdx;
import com.blemonpp.game.Blemon;

import configuraciones.Cfg;
import pantallas.PantallaTumbal;
import piezas.Pieza;
import piezas.Piezas;
import servidores.ObjetoTransporte;

//import Screens.PlayScreen;

public class ServidorTumbal extends Thread {

	private DatagramSocket conexion;
	
	private InetAddress ips[] = new InetAddress[Cfg.CANTIDADJUGADORES];
	private int puertos[] = new int[Cfg.CANTIDADJUGADORES];
	private int cont = 0;
	private ObjetoTransporte obj[] = {new ObjetoTransporte(), new ObjetoTransporte()};
	int j = 0; //J Hace referencia al jugador que manda el dato
	//private PantallaTumbal p[] = new PantallaTumbal[Cfg.CANTIDADJUGADORES];
	private PantallaTumbal pSer;
	private boolean fin;
	
	//CONSTRUCTOR 
	public ServidorTumbal(PantallaTumbal pSer)  {
		this.pSer = pSer;
		try {
			conexion = new DatagramSocket(9000);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void mandarMensaje(String mensaje) {
		for (int i = 0; i < ips.length; i++) {
			 enviarMensaje(mensaje,ips[i],puertos[i]);
		}
	}
	public void mandarMensaje(String mensaje,boolean jugador) {
		
		if(jugador==false) {
			enviarMensaje(mensaje,ips[0],puertos[0]);
			
		}else {		
			enviarMensaje(mensaje,ips[1],puertos[1]);
		}
	}
	 
	@Override
	public void run() {
		fin=true;
		do {
			recibirDatos();
		}while(fin);
	}

	private void recibirDatos() {
		byte[] datos = new byte[1024];
		DatagramPacket dp = new DatagramPacket(datos, datos.length);
		try {
			conexion.receive(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		procesarDatos(dp);
	}
	
	private void procesarDatos(final DatagramPacket dp)  {
//		Gdx.app.postRunnable(new Runnable() {
//	         @Override
//	         public void run() {
//				String msg = null;
//				msg = (new String(dp.getData())).trim();
////				final String[] mensaje = msg.split("-");
//				
//				if(msg.equals("conexion")) {
//					ips[cont] = dp.getAddress();
//					puertos[cont] = dp.getPort();
//					iniciarPiezas(cont);
//					pSer.empieza = true;
//					obj[cont].origen = pSer.pieza[0].origen;
//					obj[cont].posiciones = pSer.pieza[0].posiciones;
//					cont++;
//				} else {
//					j = encontrarEmisor(dp);
//					if(msg.equals("IZQ")) {
//						pSer.pieza[j].origen.x--;
//						if (pSer.chocaPieza(pSer.pieza[0])) {
//							pSer.pieza[0].origen.x++;
//						}
//					} else if(msg.equals("DER")) {
//						pSer.pieza[0].origen.x++;
//						if (pSer.chocaPieza(pSer.pieza[0])) {
//							pSer.pieza[0].origen.x--;
//						}
//					} else if (msg.equals("ABAJO")) {
//						pSer.pieza[0].origen.y--;
//						if (pSer.chocaPieza(pSer.pieza[0])) {
//							pSer.pieza[0].origen.y++;
//						}
//
//					} else if (msg.equals("ARRIBA")) {
//						pSer.pieza[0].rotarTetrominoIzq();
//						if (pSer.chocaPieza(pSer.pieza[0])) {
//							pSer.pieza[0].origen.x--;
//							if (pSer.chocaPieza(pSer.pieza[0])) {
//								pSer.pieza[0].origen.x+=2;
//								if (pSer.chocaPieza(pSer.pieza[0])) {
//									pSer.pieza[0].origen.x--;
//									if (pSer.chocaPieza(pSer.pieza[0])) {
//										pSer.pieza[0].rotarTetrominoDer();
//									}
//								}
//							}
//						}
//					} else if (msg.equals("Z")) {
//						pSer.pieza[0].rotarTetrominoDer();
//						if (pSer.chocaPieza(pSer.pieza[0])) {
//							pSer.pieza[0].origen.x--;
//							if (pSer.chocaPieza(pSer.pieza[0])) {
//								pSer.pieza[0].origen.x+=2;
//								if (pSer.chocaPieza(pSer.pieza[0])) {
//									pSer.pieza[0].origen.x--;
//									if (pSer.chocaPieza(pSer.pieza[0])) {
//										pSer.pieza[0].rotarTetrominoIzq();
//									}
//								}
//							}
//						}
//					} else if (msg.equals("C")) {
//						pSer.guardarPieza();
//						pSer.flag = true;
////						mandarMensaje("C"+j);
//					} else if (msg.equals("ESPACIO")){	
//						bajarPieza();
//					} else if (fin) {
//						
//					}
//					
//				}
//				String posiciones= "coordenadas/";
//				
//				posiciones += pSer.pieza[0].origen.x + "%" + pSer.pieza[0].origen.y;
//				for (int i = 0; i < pSer.pieza[0].posiciones.length; i++) {
//					posiciones += "_" + pSer.pieza[0].posiciones[i].x + "%" + pSer.pieza[0].posiciones[i].y;
//				}
//				
//				enviarMensaje(posiciones, ips[0], puertos[0]);
//	         }
//		});
//	     		
	}
	
	private void enviarMensaje(String mensaje,InetAddress ip, int puerto) {
		byte[] datos = mensaje.getBytes();
		DatagramPacket dp = new DatagramPacket(datos,datos.length,ip,puerto);
		try {
			conexion.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void enviarObjetos(ObjetoTransporte o) throws IOException {
		byte[] datos = UtilConexion.objectToBytes(o);
		DatagramPacket paquete = new DatagramPacket(datos,datos.length,ips[0],puertos[0]);
		try {
			conexion.send(paquete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(Object objeto,InetAddress ip, int puerto) throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream (bytes);
		os.writeObject(objeto);  // this es de tipo DatoUdp
		os.close();		
		byte[] datos = bytes.toByteArray(); // devuelve byte[]

		DatagramPacket dp = new DatagramPacket(datos,datos.length,ip,puerto);
		try {
			conexion.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void desconectar() {
		fin = true;
	}
	
//	public void bajarPieza() {
//		while (!pSer.chocaPieza(pSer.pieza[0])) {
//			pSer.pieza[0].origen.y--;
//			Blemon.jugador.puntaje++;
//		}
//		pSer.pieza[0].origen.y++;
//		pegarPieza(pSer.pieza[0]);
//	}
	
	public void pegarPieza(Pieza pieza) {
		for (int i = 0; i <= pieza.posiciones.length; i++) {
			if (pieza.pos(i)[1]>=Cfg.ALTOMATRIZ) {
//				PantallaManager.setScreen(new PantallaGameOver(this));
//				break;
			} else {
//				obj[j].matriz[j][(int)pieza.pos(i)[0]][(int)(pieza.pos(i)[1])] = pieza.color.toString();
				if (i==0) {
					obj[j].matriz = pieza.color.toString() + "-" + (int)pieza.pos(i)[0] + "*" + (int)(pieza.pos(i)[1]) + ".";	
				} else {
					obj[j].matriz += pieza.color.toString() + "-" + (int)pieza.pos(i)[0] + "*" + (int)(pieza.pos(i)[1]) + (i == pieza.posiciones.length ? "" : ".");	
				}
				
				for (int k = 0; k < ips.length; k++) {
					if (k!=j) {
//						obj[k].matriz[k][(int)pieza.pos(i)[0]][(int)(pieza.pos(i)[1])] = pieza.color.toString();	
					}
				}
			}
		}
//			cambiarPieza();
	}
	
//	private void cambiarPieza() {
//		pSer.pieza[0] = pSer.piezaSig[0];
//		pSer.piezaSig[0] = pSer.piezaSigSig[0];
//		pSer.piezaSigSig[0] = Piezas.getTetromino();
//		pSer.piezaFantasma[0] = Piezas.setTetromino(pSer.pieza[0]);
//		pSer.posicionarPiezas();
//		enviarMensaje("pieza3" + Piezas.getTetromino().getClass().getName(), ips[0], puertos[0]);
////		pSer.guardado = false;
//	}
//
//	private void calcularFantasma() {
//		pSer.piezaFantasma[0].origen.y = pSer.pieza[0].origen.y;
//		pSer.piezaFantasma[0].origen.x = pSer.pieza[0].origen.x;
//		while (!pSer.chocaPieza(pSer.piezaFantasma[0])) {
//			pSer.piezaFantasma[0].origen.y--;		
//		} 
//		pSer.piezaFantasma[0].origen.y++;
//	}
//	
//	public void iniciarPiezas(int cont) {
//		pSer.pieza[0] = Piezas.getTetromino();
//		pSer.piezaSig[0] = Piezas.getTetromino();
//		pSer.piezaSigSig[0] = Piezas.getTetromino();
//		pSer.piezaFantasma[0] = Piezas.setTetromino(pSer.pieza[0]);
//		pSer.pieza[0].origen.x = Cfg.ANCHOMATRIZ/2;
//		pSer.pieza[0].origen.y = Cfg.ALTOMATRIZ-2;
//		pSer.piezaFantasma[0].origen.x = pSer.pieza[0].origen.x;
//		pSer.piezaFantasma[0].origen.y = 0;
//		enviarMensaje("pieza1" + pSer.pieza[0].getClass().getName(), ips[cont], puertos[cont]);
//		enviarMensaje("pieza2" + pSer.piezaSig[0].getClass().getName(), ips[cont], puertos[cont]);
//		enviarMensaje("pieza3" + pSer.piezaSigSig[0].getClass().getName(), ips[cont], puertos[cont]);
//	}

	
	private int encontrarEmisor(DatagramPacket paquete) {
		for (int i = 0; i < ips.length; i++) {
			if (paquete.getAddress().equals(ips[i]) && paquete.getPort() == puertos[i]) {
			System.out.println("Se encontro la ip, es: " + i);
			return i;
		}
	}
	return 11;
	}
}