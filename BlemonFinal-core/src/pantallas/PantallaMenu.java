package pantallas;

import botones.Boton;
import configuraciones.Cfg;
import recursos.Diseño;
import recursos.Imagen;
import recursos.Ruta;
import recursos.Texto;
import utili.Render;

public class PantallaMenu extends PantallaManager{
	
	private Texto txtBlemon;
	private Imagen fondo;
	private Boton btnTumbal, 
				  btnBuscaminas,
				  btnPong,
				  btnArkanoid;
	
	@Override
	public void show() {
		txtBlemon = new Texto("B L E M O N");
		txtBlemon.setearMedio(Cfg.ANCHO, Cfg.ALTO);
		txtBlemon.setColor(Diseño.VERDE);
		btnTumbal = new Boton(0, 100, Cfg.ANCHO/4, 200, "TUMBAL");
		btnTumbal.color = Diseño.NARANJA;
		btnBuscaminas = new Boton(Cfg.ANCHO/4, 100, Cfg.ANCHO/4, 200, "BUSCAMINAS");
		btnBuscaminas.color = Diseño.ROJO;
		btnPong = new Boton(Cfg.ANCHO/4*2, 100, Cfg.ANCHO/4, 200, "PONG");
		btnPong.color = Diseño.VIOLETA;
		btnArkanoid= new Boton(Cfg.ANCHO/4*3, 100, Cfg.ANCHO/4, 200, "ARKANOID");
		btnArkanoid.color = Diseño.AZUL;
		fondo = new Imagen(Ruta.FONDOMENU);
		fondo.maximizarCompleto();
		PantallaManager.setInput(this);
	}
	@Override
	public void render(float delta) {
		Render.iniciar();
		Render.limpiar();
		fondo.dibujar();
		txtBlemon.draw();
		btnTumbal.dibujarLleno();
		btnBuscaminas.dibujarLleno();
		btnPong.dibujarLleno();
		btnArkanoid.dibujarLleno();
		Render.terminar();
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button){
		screenY = (Cfg.ALTO)-screenY;
		if (btnTumbal.apretaBoton(screenX, screenY)) {
			PantallaManager.setScreen(new PantallaTumbal());
		}
		if (btnBuscaminas.apretaBoton(screenX, screenY)) {
			PantallaManager.setScreen(new PantallaBuscaminas());
		}
		return true;
	}
	
	public boolean apretaBoton(Boton b, int x, int y) {
		if ((x >= b.x && x <= (b.ancho + b.x) && 
				y >= b.y && y <= (b.alto+b.y))) {
			return true;	
		}
		return false;
	}
	
	@Override
	public void dispose() {
		Render.batch.dispose();
	}
}
