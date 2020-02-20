package piezas;

import java.io.Serializable;

public class Bloque implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3324591639304878697L;
	public int x,
			   y;
	
	public Bloque(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
