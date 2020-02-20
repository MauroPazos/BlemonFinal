package piezas;

import recursos.Diseño;

public class I extends Pieza{
	private boolean girado = false; 
	//PARA QUE NO ALTERE LAS POSICIONES TOTALES CUANDO NO NECESITA MAS DE UNA ROTACION
	//Las distancia que tiene con respecto al punto de origen.
	public I() {
		super(Diseño.VIOLETA,  new Bloque[] {new Bloque(-1, 0),
											 new Bloque(1, 0),
											 new Bloque(2, 0)});
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
