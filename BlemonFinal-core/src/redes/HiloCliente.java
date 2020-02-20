package redes;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;

import configuraciones.Cfg;
import pantallas.PantallaTumbal;
import piezas.Piezas;
import piezas.Z;
import servidores.ObjetoTransporte;

public class HiloCliente extends Thread {

	private DatagramSocket conexion;
	private InetAddress ipServer;
	private int puertoServer=Cfg.PUERTO;
	private boolean fin = false;
	private int nroCliente = 0;
	private boolean listo = false;
	private PantallaTumbal p;
	
	public HiloCliente(PantallaTumbal p)  {
		ipServer = Cfg.getIp();
		this.p = p;
		
		try {
			conexion = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void enviarMensaje(String mensaje) {
		byte[] datos = mensaje.getBytes();
		DatagramPacket dp = new DatagramPacket(datos,datos.length,ipServer,puertoServer);
		try {
			conexion.send(dp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		do {
			recibirDatos();
		}while(!fin);
		conexion.close();
	}
	
	public void procesarObjetos(DatagramPacket dp) throws IOException, ClassNotFoundException {
		Object mensaje = UtilConexion.bytesToObject(dp);
		final ObjetoTransporte aux = (ObjetoTransporte) mensaje;
	}
//		Gdx.app.postRunnable(new Runnable() {
//			@Override
//			public void run() {				
//				p.pieza[0].origen.x = aux.origen.x;
//				p.pieza[0].origen.y = aux.origen.y;
//				p.pieza[0].posiciones = aux.posiciones;
//				
//				System.out.println(aux.matriz);
//				
//				String[] cuadrado = aux.matriz.split(".");
//				
//				for (int i = 0; i < cuadrado.length; i++) {
//					String color = cuadrado[i].split("-")[0];
//					String[] pos = cuadrado[i].split("-")[1].split("*");
//					p.terreno[0].matriz[Integer.parseInt(pos[0])][Integer.parseInt(pos[1])].set(Color.valueOf(color));
//				}
//				
				//aux.matriz.split("-");
//				for (int i = 0; i < aux.matriz[0].length; i++) {
//					for (int j = 0; j < aux.matriz[0][i].length; j++) {
//						if (aux.matriz[0][i][j]!=null) {
//							System.out.println(aux.matriz[0][i][j]);
//						}
//					}
//				}
//				p.terreno[0].matriz = aux.matriz[0];
//				p.terreno[1].matriz = aux.matriz[1];
//			}
//    	});
//	}
	
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
	
	private void procesarDatos(DatagramPacket dp) {
			final String msg = (new String(dp.getData())).trim();
			
			System.out.println("" + msg.charAt(msg.length() - 1));
			

//			Gdx.app.postRunnable(new Runnable() {
//				@Override
//				public void run() {
//					
//					if(msg.startsWith("pieza1")) {
//						p.pieza[0] = Piezas.valueOf("" + msg.charAt(msg.length() - 1)).pieza;
//						p.pieza[0].origen.x = Cfg.ANCHOMATRIZ / 2;
//						p.pieza[0].origen.y = Cfg.ALTOMATRIZ - 2;
//						p.piezaFantasma[0] = Piezas.valueOf("" + msg.charAt(msg.length() - 1)).pieza;
//						p.piezaFantasma[0].origen.x = p.pieza[0].origen.x;
//						
//					} else if (msg.startsWith("pieza2")) {
//						p.piezaSig[0] = Piezas.valueOf("" + msg.charAt(msg.length() - 1)).pieza;
//					} else if (msg.startsWith("pieza3")) {
//						p.piezaSigSig[0] = Piezas.valueOf("" + msg.charAt(msg.length() - 1)).pieza;
//						//if(!p.empieza) {							
//							p.empieza = true;
//						//}
////						else 
////						{
////							p.pieza[0] = p.piezaSig[0];
////							p.piezaSig[0] = p.piezaSigSig[0];	
////							p.piezaFantasma[0] = Piezas.setTetromino(p.pieza[0]);
////							p.posicionarPiezas();
////						}
//					} else if (msg.startsWith("coordenadas")){
//						String posiciones = msg.split("/")[1];
//						String coordenadas[] = posiciones.split("_");
//						for (int i = 0; i < coordenadas.length; i++) {
//							if (i==0) {
//								p.pieza[0].origen.x = Integer.parseInt(coordenadas[i].split("%")[0]);
//								p.pieza[0].origen.y = Integer.parseInt(coordenadas[i].split("%")[1]);
//							} else {
//								p.pieza[0].posiciones[i - 1].x = Integer.parseInt(coordenadas[i].split("%")[0]);
//								p.pieza[0].posiciones[i - 1].y = Integer.parseInt(coordenadas[i].split("%")[1]);
//							}
//						}
////						p.calcularFantasma();
//					}
//				}
//        	});
     }

	
	public void desconectar() {
		fin = true;
	}
	
	public int getNroCliente() {
		return nroCliente;
	}
	
	public boolean isListo() {
		return listo;
	}
	
}
