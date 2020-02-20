package botones;

import com.badlogic.gdx.graphics.Color;

import recursos.Dise�o;
import recursos.Texto;
import utili.Render;

public class Boton {
	public float x,
				 y,
				 ancho,
				 alto;
	public Color color;
	public Texto texto;
	
	public Boton(float x, float y, float ancho, float alto, String texto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.texto = new Texto(texto);
	}
	
	
	public void dibujarLleno () {
		Render.batch.draw(Dise�o.hacerLinea(color), x, y, ancho, alto);
		texto.setearMedioBoton(this);
		texto.draw();
	}
	
	public void dibujarPerimetro() {
		Render.batch.draw(Dise�o.hacerLinea(Dise�o.NARANJA), x, y, ancho, 1);
		Render.batch.draw(Dise�o.hacerLinea(Dise�o.NARANJA), x, y, 1, alto);
		Render.batch.draw(Dise�o.hacerLinea(Dise�o.NARANJA), x+ancho, y, 1, alto);
		Render.batch.draw(Dise�o.hacerLinea(Dise�o.NARANJA), x, y+alto, ancho, 1);
		texto.setearMedioBoton(this);
		texto.draw();
	}
	
	public boolean apretaBoton(int x, int y) {
		if ((x >= this.x && x <= (this.ancho + this.x) && y >= this.y && y <= (this.alto+this.y))) {
			return true;	
		}
		return false;
	}
	
}
