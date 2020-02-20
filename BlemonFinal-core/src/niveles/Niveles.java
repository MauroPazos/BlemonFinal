package niveles;

public enum Niveles {
	NIVEL1(new Nivel1()),
	NIVEL2(new Nivel2()),
	NIVEL3(new Nivel3()),
	NIVEL4(new Nivel4());
	
	private Niveles(Nivel nivel) {
		this.nivel = nivel;
	}

	Nivel nivel;
	
//	public Nivel getNivel(int puntaje) {
//		int puntajes[] = new int[values().length];
//		for (int i = 0; i < puntajes.length; i++) {
//			puntajes[i] = values()[0].nivel.getPuntosMin();
//		}
//		
//	}
}
