package pantallas;

import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.blemonpp.game.Blemon;

import configuraciones.Cfg;
import niveles.Nivel;
import piezas.Pieza;
import piezas.Piezas;
import recursos.Imagen;
import recursos.Ruta;
import recursos.Texto;
import redes.HiloCliente;
import redes.ServidorTumbal;
import servidores.ObjetoTransporte;
import terrenos.TerrenoTumbal;
import utili.Render;

public class PantallaTumbal extends PantallaManager{
	private TerrenoTumbal[] terreno = new TerrenoTumbal[2];
	
	public Pieza pieza,
				   piezaSig,
				   piezaSigSig,
				   piezaGuardada,
				   piezaFantasma;
	private Imagen fondo;
	
	private Nivel[] niveles = {new Nivel(0.1f, 0f), new Nivel(0.2f, 200f), new Nivel(0.3f, 450f), new Nivel(0.36f, 1000f), new Nivel(0.4f, 2000f),
								new Nivel(0.5f, 5000f), new Nivel(0.65f, 8500f), new Nivel(0.8f, 10000f), new Nivel(0.9f, 12000f), new Nivel(1f, 15000f)};
	private Nivel nivel;
	private boolean flag;
	private boolean guardado;
	private float cont = 0;
	private Texto txtPuntaje,
				  txtNombreJugador,
				  txtLineasHechas;
	private HiloCliente cliente;
	
//	private Jugador player = new Jugador("Maurin");
	
	public static ObjetoTransporte datosActualizados;
	
	@Override
	public void show() {
		iniciarParametros();
		PantallaManager.setInput(this);
//		System.out.println("Ingrese si es cliente o servidor");
//		int opc = new Scanner(System.in).nextInt();
//		if(opc==1) { 
//			Cfg.esServidor=true;
//			
//			ServidorTumbal servidor = new ServidorTumbal(this);
//			servidor.start();
//			Gdx.graphics.setTitle("SERVIDOR");
//			
//		} else {
//			Cfg.esServidor = false;
//			cliente = new HiloCliente(this);
//			cliente.start();
//			cliente.enviarMensaje("conexion");
//			if(opc == 2) {
//				Gdx.graphics.setTitle("CLIENTE 1");	
//			}
//			else {
//				Gdx.graphics.setTitle("CLIENTE 2");	
//
//			}
//		}
	}
	
	@Override
	public void render(float delta) {
		actualizarDatos();
		Render.limpiar();
		Render.iniciar();
			if (chocaPieza(pieza)) {
				pieza.origen.y++;
				pegarPieza(pieza);
			}
			for (int i = 0; i < niveles.length; i++) {
				if (Blemon.jugador.puntaje >= niveles[i + (i == niveles.length-1?0:1)].getPuntosMin()) {
					nivel = niveles[i];
				}
			}
			fondo.dibujar();
			pieza.dibujar(terreno[0]);
			piezaFantasma.dibujar(terreno[0]);
			piezaSig.dibujar(terreno[0].getX()+220, terreno[0].getY());
			piezaSigSig.dibujar(terreno[0].getX()+320, terreno[0].getY());
			if (flag) {
				piezaGuardada.dibujar(terreno[0].getAncho()+50, terreno[0].getAlto()- 300);
			}
			terreno[0].dibujar();
			terreno[1].dibujar();
			if (cont/Gdx.graphics.getDeltaTime()>= 500) {
				cont = 0;
				pieza.origen.y--;
			}
			cont += nivel.getVelocidad();
			txtPuntaje.setTexto(Float.toString(Blemon.jugador.puntaje));
			txtPuntaje.draw();
			txtNombreJugador.draw();
			txtLineasHechas.setTexto("Lineas: " + terreno[0].getLineasCompletas());
			txtLineasHechas.draw();
		Render.terminar();
	}

	private void actualizarDatos() {
		if (datosActualizados!= null) {
//			pieza.posiciones = datosActualizados.posiciones;
			pieza.origen = datosActualizados.origen;	
		}
	}

	@Override
	public void dispose() {
		Render.batch.dispose();
	}
	
	private void iniciarParametros() {
		iniciarPiezas();
		terreno[0] = new TerrenoTumbal(Cfg.ANCHOMATRIZ*Cfg.TAMAÑOBLOQUE, 
							  		   Cfg.ALTOMATRIZ*Cfg.TAMAÑOBLOQUE, 
							  		   Cfg.ANCHO/6, Cfg.ALTO/4);
		terreno[1] = new TerrenoTumbal(Cfg.ANCHOMATRIZ*Cfg.TAMAÑOBLOQUE, 
				  					   Cfg.ALTOMATRIZ*Cfg.TAMAÑOBLOQUE, 
				  					   Cfg.ANCHO/6*4, Cfg.ALTO/4);
		fondo = new Imagen(Ruta.FONDOTUMBAL);
		fondo.maximizarCompleto();
		nivel = niveles[0];
		txtPuntaje = new Texto(Float.toString(Blemon.jugador.puntaje));
		txtPuntaje.setPosicion(terreno[0].getX(), terreno[0].getY()-35);
		txtNombreJugador = new Texto(Blemon.jugador.getNombre());
		txtNombreJugador.setPosicion(terreno[0].getX(), terreno[0].getY()-105);
		txtLineasHechas = new Texto("Lineas: " + terreno[0].getLineasCompletas());
		txtLineasHechas.setPosicion(terreno[0].getX(), terreno[0].getY()-70);
//		cliente.start();
	}

	private void iniciarPiezas() {
		pieza = Piezas.getTetromino();
		piezaSig = Piezas.getTetromino();
		piezaSigSig = Piezas.getTetromino();
		piezaFantasma = Piezas.setTetromino(pieza);
		pieza.origen.x = Cfg.ANCHOMATRIZ/2;
		pieza.origen.y = Cfg.ALTOMATRIZ-2;
		piezaFantasma.origen.x = pieza.origen.x;
		piezaFantasma.origen.y = 0;
	}
	
	private void posicionarPiezas() {
		pieza.origen.x = Cfg.ANCHOMATRIZ/2;
		pieza.origen.y = Cfg.ALTOMATRIZ;
		piezaFantasma.origen.x = Cfg.ANCHOMATRIZ/2;
		piezaFantasma.origen.y = Cfg.ALTOMATRIZ-2;
		calcularFantasma();
	}
	
	public Pieza getPieza() {
		return pieza;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
//			moverIzq();
			cliente.enviarMensaje("IZQ");
			break;

		case Keys.RIGHT:
//			moverDer();
			cliente.enviarMensaje("DER");
			break;
		
		case Keys.DOWN:
			pieza.origen.y--;
			if (chocaPieza(pieza)) {
				pieza.origen.y++;
			} else {
				Blemon.jugador.puntaje++;
			}
			cliente.enviarMensaje("ABAJO");

			break;
			
		case Keys.UP:
//			pieza.rotarTetrominoIzq();
//			if (chocaPieza(pieza)) {
//				pieza.origen.x--;
//				if (chocaPieza(pieza)) {
//					pieza.origen.x+=2;
//					if (chocaPieza(pieza)) {
//						pieza.origen.x--;
//						if (chocaPieza(pieza)) {
//							pieza.rotarTetrominoDer();
//						}
//					}
//				}
//			}

			cliente.enviarMensaje("ARRIBA");

			piezaFantasma.posiciones = pieza.posiciones;
			break;
			
		case Keys.Z:
			pieza.rotarTetrominoDer();
			if (chocaPieza(pieza)) {
				pieza.origen.x--;
				if (chocaPieza(pieza)) {
					pieza.origen.x+=2;
					if (chocaPieza(pieza)) {
						pieza.origen.x--;
						if (chocaPieza(pieza)) {
							pieza.rotarTetrominoIzq();
						}
					}
				}
			}
			piezaFantasma.posiciones = pieza.posiciones;
			break;
		
		case Keys.C:
			guardarPieza();
			flag = true;
			break;

		case Keys.SPACE:
			bajarPieza();
			break;
		}

		calcularFantasma();
		return true;
	}

	public void moverDer() {
		pieza.origen.x++;
		if (chocaPieza(pieza)) {
			pieza.origen.x--;
		}
	}

	public void moverIzq() {
		pieza.origen.x--;
		if (chocaPieza(pieza)) {
			pieza.origen.x++;
		}
	}
	
	public void guardarPieza() {
		if (!flag) {
			piezaGuardada = pieza;
			cambiarPieza();
			guardado = true;
		} else {
			if (!guardado) {
				Pieza aux = Piezas.setTetromino(pieza);
				pieza = Piezas.setTetromino(piezaGuardada);
				piezaFantasma = Piezas.setTetromino(pieza);
				piezaGuardada = aux;
				posicionarPiezas();
				guardado = true;
			}
		}
	}
	
	public void bajarPieza() {
		while (!chocaPieza(pieza)) {
			pieza.origen.y--;
			Blemon.jugador.puntaje++;
		}
		pieza.origen.y++;
		pegarPieza(pieza);
	}
	
	public void pegarPieza(Pieza p) {
		for (int i = 0; i <= p.posiciones.length; i++) {
			if (p.pos(i)[1]>=Cfg.ALTOMATRIZ) {
				PantallaManager.setScreen(new PantallaGameOver(this));
				break;
			} else {
				terreno[0].matriz[(int)p.pos(i)[0]][(int)(p.pos(i)[1])] = p.color;	
			}
		}
			cambiarPieza();
	}
	
	private void cambiarPieza() {
		pieza = piezaSig;
		piezaSig = piezaSigSig;
		piezaSigSig = Piezas.getTetromino();
		piezaFantasma = Piezas.setTetromino(pieza);
		posicionarPiezas();
		guardado = false;
	}

	private void calcularFantasma() {
		piezaFantasma.origen.y = pieza.origen.y;
		piezaFantasma.origen.x = pieza.origen.x;
		while (!chocaPieza(piezaFantasma)) {
			piezaFantasma.origen.y--;		
		} 
		piezaFantasma.origen.y++;
	}

	public boolean chocaPieza(Pieza p) {
		for (int i = 0; i <= p.posiciones.length; i++) {
			if ((p.pos(i)[0] < 0 || p.pos(i)[0] > Cfg.ANCHOMATRIZ-1) || (p.pos(i)[1] < 0)){
//					|| (terreno.matriz[(int)(p.pos(i)[0])][(int)(p.pos(i)[1])] != null)) {
				return true;
			}
			if ((p.pos(i)[1]<Cfg.ALTOMATRIZ)) {
				if ((terreno[0].matriz[(int)(p.pos(i)[0])][(int)(p.pos(i)[1])] != null)) {
					return true;
				}
			}
		}
		return false;
	}

}