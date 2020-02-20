package niveles;

public class Nivel {
	private float velocidad;
	private float puntosMin;
	
	public Nivel(float velocidad, float puntosMin) {
		this.velocidad = velocidad;
		this.puntosMin = puntosMin;
	}
	
	public float getVelocidad() {
		return velocidad;
	}
	
	public float getPuntosMin() {
		return puntosMin;
	}
}
