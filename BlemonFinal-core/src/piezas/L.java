package piezas;

import recursos.Dise�o;

public class L extends Pieza{
	//Las distancia que tiene con respecto al punto de origen.
	public L() {
		super(Dise�o.CELESTE, new Bloque[] {new Bloque(-1, 0),
											new Bloque(1, 0),
											new Bloque(1, 1)});
	}
}