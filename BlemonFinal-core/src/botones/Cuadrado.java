package botones;

import recursos.Imagen;
import recursos.Ruta;
import recursos.Texto;

public class Cuadrado {
	public Texto texto = new Texto("0");
	public Imagen imagen = new Imagen(Ruta.BLOQUE);
	public boolean bomba;
	public boolean apretado;
	
	public Cuadrado() {
		bomba = false;
		apretado = false;
	}

}
