package piezas;

import recursos.Dise�o;

public class O extends Pieza{
	//Las distancia que tiene con respecto al punto de origen.
	public O() {
		super(Dise�o.AMARILLO, new Bloque[] {new Bloque(1, 0),
										 	 new Bloque(0, 1),
											 new Bloque(1, 1)});
	}
	
	@Override
	public void rotarTetrominoDer() {
		//NO VA A GIRAR
	}
	@Override
	public void rotarTetrominoIzq() {
		//NO VA A GIRAR
	}
}