package piezas;

import com.badlogic.gdx.graphics.Color;

import configuraciones.Cfg;
import recursos.Imagen;
import recursos.Ruta;
import terrenos.TerrenoTumbal;

public abstract class Pieza{

	public Color color;
	public Bloque origen = new Bloque(1, 1);
	public Bloque[] posiciones;
	public Imagen bloque = new Imagen(Ruta.BLOQUE);
	
	public Pieza(Color color, Bloque[] posiciones) {
		this.color = color;
		this.posiciones = posiciones;
	}
	
	public void dibujar(TerrenoTumbal t) { 
		bloque.setColor(color);
		for (int i = 0; i <= posiciones.length; i++) {
			dibujarBloque(pos(i)[0], pos(i)[1], t.getX(), t.getY());			
		} 
	}
	
	public void dibujar(float x, float y) { 
		bloque.setColor(color);
		for (int i = 0; i <= posiciones.length; i++) {
			dibujarBloque(pos(i)[0], pos(i)[1], x, y);			
		} 
	}
	
	private void dibujarBloque(float posX, float posY, float  x, float y) {
		if (posY<Cfg.ALTOMATRIZ) {
			bloque.draw(posX*Cfg.TAMAÑOBLOQUE + x, 
						posY*Cfg.TAMAÑOBLOQUE + y,
						Cfg.TAMAÑOBLOQUE, 
						Cfg.TAMAÑOBLOQUE);	
		}
	}

	
	public void rotarTetrominoIzq() {
		int x;
		for (int i = 0; i < posiciones.length; i++) {
			x = posiciones[i].x;
			posiciones[i].x = posiciones[i].y;
			posiciones[i].y = -x;
		}
	}

	public void rotarTetrominoDer() {
		int x;
		for (int i = 0; i < posiciones.length; i++) {
			x = posiciones[i].y;
			posiciones[i].y = posiciones[i].x;
			posiciones[i].x = -x;				
		}
	}
	
	public float[] pos(int i) {
		float[] coordenadas = {origen.x, origen.y};
		if(i!=0) {
			coordenadas[0] += posiciones[i-1].x;
			coordenadas[1] += posiciones[i-1].y;
		}
		return coordenadas;
	}
}
