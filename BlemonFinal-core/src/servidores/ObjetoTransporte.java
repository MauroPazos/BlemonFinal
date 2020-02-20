package servidores;

import java.io.Serializable;

import com.badlogic.gdx.graphics.Color;

import configuraciones.Cfg;
import piezas.Bloque;

public class ObjetoTransporte implements Serializable	{
	private static final long serialVersionUID = 4576094998254628345L;

	//Del terreno solo necesito la matriz y que lugaers estan ocupados
//	public boolean[][] matriz = new boolean[Cfg.ANCHOMATRIZ][Cfg.ALTOMATRIZ];
	//De las piezas solo necesito sus posiciones
//	public Pieza pieza,
//				 piezaSig,
//				 piezaSigSig,
//				 piezaGuardada,
//				 piezaFantasma;
	public Bloque origen = new Bloque(1, 1);
	public Bloque posiciones[] = new Bloque[3];
//	public String matriz = new String[Cfg.CANTIDADJUGADORES][Cfg.ANCHOMATRIZ][Cfg.ALTOMATRIZ];
	public String matriz;
//	public boolean flag;
	
//	public float[] pos(int i) {
//		float[] coordenadas = {origen.x, origen.y};
//		if(i!=0) {
//			coordenadas[0] += posiciones[i-1].x;
//			coordenadas[1] += posiciones[i-1].y;
//		}
//		return coordenadas;
//	}
	

}
