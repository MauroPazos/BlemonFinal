package piezas;

import recursos.Dise�o;

public class J extends Pieza{
	//Las distancia que tiene con respecto al punto de origen.
	public J() {
		super(Dise�o.NARANJA, new Bloque[] {new Bloque(-1, 1),
											new Bloque(-1, 0),
											new Bloque(1, 0)});
	}
}
