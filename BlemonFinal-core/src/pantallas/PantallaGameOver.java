package pantallas;

import com.badlogic.gdx.Gdx;
import com.blemonpp.game.Blemon;

import botones.Boton;
import configuraciones.Cfg;
import recursos.Diseño;
import recursos.Imagen;
import recursos.Ruta;
import recursos.Texto;
import utili.Render;

public class PantallaGameOver extends PantallaManager {
	
	private Imagen fondo;
	private Boton btnMenu,
				  btnVolverAJugar;
	private Texto txtPuntaje;
	private PantallaManager pantallaAnterior;
	
	
	public PantallaGameOver(PantallaManager p) {
		pantallaAnterior = p;
	}
	
	@Override
	public void show() {
		PantallaManager.setInput(this);
		btnMenu = new Boton(Gdx.graphics.getWidth()/4 -10, 100, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, "M E N U");
		btnMenu.color = Diseño.AZUL;
		btnVolverAJugar = new Boton(Gdx.graphics.getWidth()/2 -10, 100, Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4, "R E V A N C H A");
		btnVolverAJugar.color = Diseño.ROJO;
		fondo = new Imagen(Ruta.FONDOTUMBALGAMEOVER);
		fondo.maximizarCompleto();
		txtPuntaje = new Texto("Hiciste " + Blemon.jugador.puntaje + " puntos");
		Blemon.jugador.puntaje = 0;
		txtPuntaje.setearMedio(Cfg.ANCHO, Cfg.ALTO+350);
	}
	@Override
	public void render(float delta) {
		Render.iniciar();
		Render.limpiar();
		fondo.dibujar();
		btnMenu.dibujarLleno();
		btnVolverAJugar.dibujarLleno();
		if (pantallaAnterior.getClass() == PantallaTumbal.class) {
			txtPuntaje.draw();
		}
		Render.terminar();
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenY = (Cfg.ALTO)-screenY;
		if (btnMenu.apretaBoton(screenX, screenY)) {
			PantallaManager.setScreen(new PantallaMenu());
		}
		else if (btnVolverAJugar.apretaBoton(screenX, screenY)) {
			try {
				PantallaManager.setScreen(pantallaAnterior.getClass().newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
}
