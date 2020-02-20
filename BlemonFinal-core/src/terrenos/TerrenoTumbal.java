package terrenos;

import com.badlogic.gdx.graphics.Color;
import com.blemonpp.game.Blemon;

import configuraciones.Cfg;
import recursos.Diseño;
import recursos.Imagen;
import recursos.Ruta;
import utili.Render;

public class TerrenoTumbal {
	private float ancho,
	  			  alto,
	  			  x,
				  y;
	public Color[][] matriz = null;
	private Imagen bloque = new Imagen(Ruta.BLOQUE);	
	private int lineasCompletas;	
	
	public TerrenoTumbal(float ancho, float alto, float x, float y) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		matriz = new Color[Cfg.ANCHOMATRIZ][Cfg.ALTOMATRIZ];
	}
	
	public void dibujar() {
		float xaux = x;
		float yaux = y;
		for (int i = 0; i <= alto/Cfg.TAMAÑOBLOQUE; i++) {
			Render.batch.draw(Diseño.hacerLinea(Color.WHITE), x, yaux, ancho, 1);
			yaux += Cfg.TAMAÑOBLOQUE;
		}
		
		for (int i = 0; i <= ancho/Cfg.TAMAÑOBLOQUE; i++) {
			Render.batch.draw(Diseño.hacerLinea(Color.WHITE), xaux, y, 1, alto);
			xaux += Cfg.TAMAÑOBLOQUE;
		}
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {	
				if(matriz[i][j]!=null) {
					bloque.setColor(matriz[i][j]);
					bloque.draw(i*Cfg.TAMAÑOBLOQUE + (int)x, 
							 	j*Cfg.TAMAÑOBLOQUE + (int)y, 
							 	Cfg.TAMAÑOBLOQUE, Cfg.TAMAÑOBLOQUE);
				}
			}
		}
		verificarMatriz();
	}
	
	public void verificarMatriz() { 
		int cont=0;
		for (int j = Cfg.ALTOMATRIZ-1; j >= 0 ; j--) { 
			//Leo la matriz de arriba a bajo porque al encontrar la fila llena, la bajo, 
			//por lo tanto no puedo bajar todas si se baja mas de una en el caso de abajo 
			//para arriba.
			if (verificarFila(j)) {//Si entra aca, la fila esta llena;
				cont++;
				lineasCompletas++;
				vaciarFila(j);
				bajarFilasSuperiores(j);
			}
		}
		Blemon.jugador.puntaje+=Cfg.PUNTAJELINEA*cont;

		if (cont == 4) {
			Blemon.jugador.puntaje+=(Cfg.PUNTAJELINEA*cont*0.5f);
		} else if (cont == 3) {
			Blemon.jugador.puntaje+=(Cfg.PUNTAJELINEA*cont*0.25f);
		} else if (cont == 2) {
			Blemon.jugador.puntaje+=(Cfg.PUNTAJELINEA*cont*0.08f);
		}

	}

	private void bajarFilasSuperiores(int j) {
		for (int i = j; i < Cfg.ALTOMATRIZ -2; i++) {
			for (int k = 0; k < Cfg.ANCHOMATRIZ; k++) {
				matriz[k][i] = matriz[k][i+1];
				matriz[k][i+1] = null;
			}
		}
	}

	private void vaciarFila(int j) {
//		for (int k = 0; k < Configuracion.ANCHOMATRIZ; k++) {
//			Configuracion.tetromino.dibujarCuadrado(this, j, k, matriz[k][j]);
//		}
		for (int k = 0; k < Cfg.ANCHOMATRIZ; k++) {
			matriz[k][j] = null;
		}
	}
	
	private boolean verificarFila(int j) {
		for (int i = 0; i < Cfg.ANCHOMATRIZ; i++) {
			if (matriz[i][j] == null) {
				return false;
			}
		}
		return true;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	public float getAncho() {
		return ancho+x;
	}
	public float getAlto() {
		return alto+y;
	}
	public int getLineasCompletas() {
		return lineasCompletas;
	}
}
