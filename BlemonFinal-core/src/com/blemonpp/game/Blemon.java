package com.blemonpp.game;
import com.badlogic.gdx.Game;

import jugadores.Jugador;
import pantallas.PantallaManager;
import pantallas.PantallaMenu;

public class Blemon extends Game {
	public static Jugador jugador;
	
	@Override
	public void create () {
		PantallaManager.app = this;
		jugador = new Jugador("Mauro");
		PantallaManager.setScreen(new PantallaMenu());
	}
}
