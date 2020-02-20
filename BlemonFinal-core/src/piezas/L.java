package piezas;

import recursos.Diseño;

public class L extends Pieza{
	//Las distancia que tiene con respecto al punto de origen.
	public L() {
		super(Diseño.CELESTE, new Bloque[] {new Bloque(-1, 0),
											new Bloque(1, 0),
											new Bloque(1, 1)});
	}
}