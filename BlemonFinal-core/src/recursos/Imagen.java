package recursos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import configuraciones.Cfg;
import utili.Render;

public class Imagen {
	private Texture textura;
	private Sprite spr;
	
	public Imagen(String ruta) {
		textura = new Texture(ruta);
		spr = new Sprite(textura);
	}
	
//	TODO Ver que onda este constructor que nada que ver
	public Imagen() {
		Pixmap linea = new Pixmap(1, 1, Pixmap.Format.RGB888);;
		textura = new Texture(linea);
	}
	
	public void dibujar() {
		spr.draw(Render.batch);
	}
	
	public void draw(float x, float y, float a, float h) {
		spr.setPosition(x, y);
		spr.setSize(a, h);
		spr.draw(Render.batch);
	}
	
	public Texture getTexture() {
		return textura;
	}
	
	public float getWidth() {
		return spr.getWidth();
	}
	
	public float getHeight() {
		return spr.getHeight();
	}
	
	public float mitadAncho() {
		return getWidth()/2;
	}
	
	public float mitadAlto() {
		return getHeight()/2;
	}
	
	public void setColor(Color color) {
		spr.setColor(color);
	}
	
	public void maximizarCompleto() {
		spr.setSize(Cfg.ANCHO, Cfg.ALTO);
	}
}
