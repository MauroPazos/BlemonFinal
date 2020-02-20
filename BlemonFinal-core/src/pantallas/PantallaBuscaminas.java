package pantallas;

import com.badlogic.gdx.Input.Buttons;

import configuraciones.Cfg;
import recursos.Texto;
import terrenos.TerrenoBuscaminas;
import utili.Render;

public class PantallaBuscaminas extends PantallaManager{
	private TerrenoBuscaminas terreno;
	private long startTime;
	private boolean flag = false;
	private boolean fin = false;
	private Texto txtTiempo;
	
	
	@Override
	public void show() {
		terreno = new TerrenoBuscaminas(100, 100);
		txtTiempo = new Texto("000");
		txtTiempo.setPosicion(terreno.getX(), terreno.getY()-50);
		PantallaManager.setInput(this);
	}
	
	@Override
	public void render(float delta) {
		Render.limpiar();
		Render.iniciar();
		terreno.dibujar();
		txtTiempo.setTexto(Integer.toString((int)(System.currentTimeMillis() - startTime) / 1000));
		txtTiempo.draw();
		Render.terminar();
	}

	@Override
	public void dispose() {
		Render.batch.dispose();
	}
	
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (!flag) {
			startTime = System.currentTimeMillis();
			flag = true;
		}
		screenY = (Cfg.ALTO)-screenY;
		screenX-= terreno.getX();
		screenY-= terreno.getY();
		float x = (screenX/Cfg.TAMAÑOMINA);
		float y = (screenY/Cfg.TAMAÑOMINA);
		if (x >= 0 && x <Cfg.ANCHOMATRIZDIFICIL &&
			y >= 0 && y <Cfg.ALTOMATRIZDIFICIL ) {
			if (button == Buttons.LEFT) {
				if (terreno.bomba[(int)x][(int)y]==9) {
					PantallaManager.setScreen(new PantallaGameOver(this));
				} else {
					if (!terreno.apretado[(int)x][(int)y] && !terreno.salvado[(int)x][(int)y]) {
						terreno.apretado[(int)x][(int)y]=true;
					}
					calcularSalvadosVecinos((int)x, (int)y);
				}
			} else if(button == Buttons.RIGHT){
				if (!terreno.salvado[(int)x][(int)y] && !terreno.apretado[(int)x][(int)y]) {
					terreno.salvado[(int)x][(int)y] = true;
					if (terreno.bomba[(int)x][(int)y] == 9) {
						terreno.bombasEncontradas++;
						if (terreno.bombasEncontradas == Cfg.CANTIDADMINAS) {
							System.out.println("WIN");
						}
					}
				} else {
					terreno.salvado[(int)x][(int)y] = false;
					terreno.bombasEncontradas--;
				}
			}
		}
		return true;
	}

	private int calcularSalvadosVecinos(int x, int y) {
		int cont = 0;
		try {
			if (terreno.salvado[x+1][y]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x+1][y-1]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x+1][y+1]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x][y-1]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x][y+1]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x-1][y]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x-1][y-1]) {cont++;}
		} catch (Exception e) {}
		try {
			if (terreno.salvado[x-1][y+1]) {cont++;}
		} catch (Exception e) {}
		
		if (cont == terreno.bomba[x][y]) {
			try {
				if (!terreno.salvado[x+1][y]) {
					terreno.apretado[x+1][y] = true;
					if(terreno.bomba[x+1][y] == 9) fin = true;
//					if(terreno.bomba[x+1][y] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x+1][y-1]) {
					terreno.apretado[x+1][y-1] = true;
					if(terreno.bomba[x+1][y-1] == 9) fin = true;
//					if(terreno.bomba[x+1][y-1] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x+1][y+1]) {
					terreno.apretado[x+1][y+1] = true;
					if(terreno.bomba[x+1][y+1] == 9) fin = true;
//					if(terreno.bomba[x+1][y+1] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x][y-1]) {
					terreno.apretado[x][y-1] = true;
					if(terreno.bomba[x][y-1] == 9) fin = true;
//					if(terreno.bomba[x][y-1] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x][y+1]) {
					terreno.apretado[x][y+1] = true;
					if(terreno.bomba[x][y+1] == 9) fin = true;
//					if(terreno.bomba[x][y+1] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x-1][y]) {
					terreno.apretado[x-1][y] = true;
					if(terreno.bomba[x-1][y] == 9) fin = true;
//					if(terreno.bomba[x-1][y] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x-1][y-1]) {
					terreno.apretado[x-1][y-1] = true;
					if(terreno.bomba[x-1][y-1] == 9) fin = true;
//					if(terreno.bomba[x-1][y-1] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			try {
				if (!terreno.salvado[x-1][y+1]) {
					terreno.apretado[x-1][y+1] = true;
					if(terreno.bomba[x-1][y+1] == 9) fin = true;
//					if(terreno.bomba[x-1][y+1] == 0) recorrer = true;
				}
			} catch (Exception e) {}
			if (fin) {
				PantallaManager.setScreen(new PantallaGameOver(this));
			}
			
		}
		return cont;
		
		
	}
}