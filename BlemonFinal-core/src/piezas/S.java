package piezas;

import recursos.Dise�o;

public class S extends Pieza{
	private boolean girado = false; 
	//PARA QUE NO ALTERE LAS POSICIONES TOTALES CUANDO NO NECESITA MAS DE UNA ROTACION
	//Las distancia que tiene con respecto al punto de origen.
	public S() {
		super(Dise�o.ROJO, new Bloque[] {new Bloque(-1, 0),
										 new Bloque(1, 1),
										 new Bloque(0, 1)});
	}
	
	@Override
	public void rotarTetrominoDer() {
		if(!girado) {
			super.rotarTetrominoDer();
			girado = true;
		} else {
			super.rotarTetrominoIzq();
			girado = false;
		}
	}
	
	@Override
	public void rotarTetrominoIzq() {
		if(!girado) {
			super.rotarTetrominoIzq();
			girado = true;
		} else {
			super.rotarTetrominoDer();
			girado = false;
		}
	}
}