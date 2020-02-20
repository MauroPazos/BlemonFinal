//package utili;
//
//import piezas.Pieza;
//
//public class Tiempo extends Thread {
//	
//	private int velocidad;
//	private Pieza p;
//	
//	public void setVelocidad(int velocidad) {
//		this.velocidad = velocidad;
//	}
//	
//	public void setPieza(Pieza p) {
//		this.p = p;
//	}
//	
//	@Override
//	public void run() {
//		while(true) {
//			try {
//				sleep(velocidad);
////				while(chochaPieza(p))
////				{p.origen.y--;}
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//}
