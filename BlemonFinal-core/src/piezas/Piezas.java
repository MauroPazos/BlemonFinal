package piezas;

import utili.Numeros;

public enum Piezas {
	Z(new Z()),
	S(new S()),
	L(new L()),
	J(new J()),
	I(new I()),
	O(new O()),
	T(new T());
	
	private Piezas(Pieza pieza) {
		this.pieza = pieza;
	}
	
	public Pieza pieza;
	
	public static Pieza getTetromino() {
		try {
			return values()[Numeros.r.nextInt(values().length)].pieza.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Pieza setTetromino(Pieza p) {
		try {
			return p.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return p;
	}
}
