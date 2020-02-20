package com.blemonpp.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.blemonpp.game.Blemon;

import configuraciones.Cfg;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = Cfg.TITULO;
		config.height = Cfg.ALTO;
		config.width = Cfg.ANCHO;
		config.backgroundFPS = Cfg.BACKGROUNDFPS; 
		config.foregroundFPS = Cfg.FOREGROUNDFPS;
		config.resizable = Cfg.REDIMENSIONABLE;		
		new LwjglApplication(new Blemon(), config);
	}
}
