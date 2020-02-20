package terrenos;

import com.badlogic.gdx.graphics.Color;

import configuraciones.Cfg;
import recursos.Diseño;
import recursos.Imagen;
import recursos.Ruta;
import utili.Numeros;
import utili.Render;

public class TerrenoBuscaminas {
	private float ancho,
				  alto,
				  x,
				  y;
	public int[][] bomba = new int[Cfg.ANCHOMATRIZDIFICIL][Cfg.ALTOMATRIZDIFICIL]; //9 si hay bomba
	public boolean[][] apretado = new boolean[Cfg.ANCHOMATRIZDIFICIL][Cfg.ALTOMATRIZDIFICIL];
	public boolean[][] salvado = new boolean[Cfg.ANCHOMATRIZDIFICIL][Cfg.ALTOMATRIZDIFICIL];
//	public Imagen[][] cuadros = new Imagen[Cfg.ANCHOMATRIZDIFICIL][Cfg.ALTOMATRIZDIFICIL];
	public Imagen[] cuadros = {new Imagen(Ruta.BLOQUE0), new Imagen(Ruta.BLOQUE1),
							   new Imagen(Ruta.BLOQUE2), new Imagen(Ruta.BLOQUE3),
							   new Imagen(Ruta.BLOQUE4), new Imagen(Ruta.BLOQUE5),
							   new Imagen(Ruta.BLOQUE6), new Imagen(Ruta.BLOQUE7),
							   new Imagen(Ruta.BLOQUE8), new Imagen(Ruta.FONDOTUMBAL),
							   new Imagen(Ruta.BLOQUESALVADO)};
	
	public int bombasEncontradas;
	
	public Imagen cuadroCapa = new Imagen(Ruta.BLOQUE);
//	private Imagen cuadroBomba = new Imagen(Ruta.BLOQUEPIEZA);
//	private Texto texto = new Texto("2");
	
	public int cantBombasSalvadas = Cfg.CANTIDADMINAS; 
	
	public TerrenoBuscaminas(float x, float y) {
		this.x = x;
		this.y = y;
		this.ancho = Cfg.ANCHOMATRIZDIFICIL * Cfg.TAMAÑOMINA;
		this.alto = Cfg.ALTOMATRIZDIFICIL * Cfg.TAMAÑOMINA;
		llenarMatriz();
	}
	
	public void llenarMatriz() {
		int cont = Cfg.CANTIDADMINAS;
		do {
			int x = Numeros.r.nextInt(Cfg.ANCHOMATRIZDIFICIL);
			int y = Numeros.r.nextInt(Cfg.ALTOMATRIZDIFICIL);
			if (bomba[x][y]!=9) {
				try {
					if (bomba[x+1][y]!=9)bomba[x+1][y]++;
				} catch (Exception e) {}
				try {
					if (bomba[x+1][y-1]!=9)bomba[x+1][y-1]++;
				} catch (Exception e) {}
				try {
					if (bomba[x+1][y+1]!=9)bomba[x+1][y+1]++;
				} catch (Exception e) {}
				try {
					if (bomba[x][y-1]!=9)bomba[x][y-1]++;
				} catch (Exception e) {}
				try {
					if (bomba[x][y+1]!=9)bomba[x][y+1]++;
				} catch (Exception e) {}
				try {
					if (bomba[x-1][y]!=9)bomba[x-1][y]++;
				} catch (Exception e) {}
				try {
					if (bomba[x-1][y-1]!=9)bomba[x-1][y-1]++;
				} catch (Exception e) {}
				try {
					if (bomba[x-1][y+1]!=9)bomba[x-1][y+1]++;
				} catch (Exception e) {}
				bomba[x][y]= 9;
				cont--;
			}
		} while (cont>0);
	}
	
	
	public void dibujar() {
		float xaux = x;
		float yaux = y;
		for (int i = 0; i <= alto/Cfg.TAMAÑOMINA; i++) {
			Render.batch.draw(Diseño.hacerLinea(Color.WHITE), x, yaux, ancho, 1); 
			yaux += Cfg.TAMAÑOMINA;
		}
		
		for (int i = 0; i <= ancho/Cfg.TAMAÑOMINA; i++) {
			Render.batch.draw(Diseño.hacerLinea(Color.WHITE), xaux, y, 1, alto);
			xaux += Cfg.TAMAÑOMINA;
		}
		
		for (int i = 0; i < bomba.length; i++) {
			for (int j = 0; j < bomba[i].length; j++) {
				if (!apretado[i][j]) {
					cuadroCapa.draw(i*Cfg.TAMAÑOMINA + (int)x, 
									j*Cfg.TAMAÑOMINA + (int)y, 
									Cfg.TAMAÑOMINA, Cfg.TAMAÑOMINA);
				} else {
					cuadros[bomba[i][j]].draw(i*Cfg.TAMAÑOMINA + (int)x, 
									   		  j*Cfg.TAMAÑOMINA + (int)y, 
									   		  Cfg.TAMAÑOMINA, Cfg.TAMAÑOMINA);
				}
				if (salvado[i][j]) {
					cuadros[10].draw(i*Cfg.TAMAÑOMINA + (int)x, 
									j*Cfg.TAMAÑOMINA + (int)y, 
									Cfg.TAMAÑOMINA, Cfg.TAMAÑOMINA);
				}
				
//				switch (bomba[i][j]) {
//				case 0:
//					cuad
//					break;
//
//				default:
//					break;
//				}
			}
		}
	}
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
}
