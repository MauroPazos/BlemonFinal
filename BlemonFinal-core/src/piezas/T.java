package piezas;

import recursos.Diseño;

public class T extends Pieza{
	//Las distancia que tiene con respecto al punto de origen.
	public T() {
		super(Diseño.VERDE, new Bloque[] {new Bloque(0, 1),
										  new Bloque(-1, 0),
										  new Bloque(1, 0)});
	}
}