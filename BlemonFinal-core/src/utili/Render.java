package utili;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Render {
	public static SpriteBatch batch = new SpriteBatch();
	
	public static void iniciar() {		
		batch.begin();
	}
	
	public static void terminar() {
		batch.end();
	}
	
	public static void limpiar() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
