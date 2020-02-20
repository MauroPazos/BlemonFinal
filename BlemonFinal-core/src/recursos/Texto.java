package recursos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import botones.Boton;
import utili.Render;

public class Texto {
	public Label.LabelStyle labelSytle = new Label.LabelStyle();
	private BitmapFont fuente = new BitmapFont(Gdx.files.internal(Ruta.CENTURYGOTHIC));;
	public Label label;
	private Color color = Diseño.AMARILLO;
	private String texto;
	
	public Texto(String texto) {
		this.texto = texto;
		labelSytle.font = fuente;
		label = new Label(texto, labelSytle);
	}
	
	public void draw() {
		label.draw(Render.batch, 1f);
	}
	
	public void draw(float x, float y) {
		fuente.setColor(color);
		fuente.draw(Render.batch, texto, x, y);
	}
	
	public void setPosicion(float x, float y) {
		label.setPosition(x, y);
	}
	
	public void setTexto(String texto) {
		this.texto = texto;
		label.setText(texto);
	}
	
	public void setColor(Color color) {
		this.color = color;
		label.setColor(color);
	}
	
	public void setearMedioBoton (Boton b) { 
		//Poner el texto en medio del boton unicamente
		label.setSize(b.ancho, b.alto);
		label.setPosition(b.x, b.y);
		label.setAlignment(Align.center);
	}
	
	public void setearMedio (float ancho, float alto) { 
		//Poner el texto en medio de una posicion ingresando el ancho y alto de donde se quiera ingresar
		label.setSize(ancho, alto);
		label.setAlignment(Align.center);
	}
	
	public float getX() {
		return label.getX();
	}
	public float getY() {
		return label.getY();
	}
	public float getAncho() {
		return label.getWidth();
	}
	public float getAlto() {
		return label.getHeight();
	}
	public void setX(int x) {
		this.label.setX(x);
	}
	public void setY(int y) {
		this.label.setY(y);
	}
}