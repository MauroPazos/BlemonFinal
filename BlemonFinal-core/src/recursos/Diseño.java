package recursos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

//Con esta clase consigo usar los colores que yo mismo busque como constantes de manera sencilla

public abstract class Diseño {
	//COLORES
	public final static Color CELESTE = new Color(0.29f, 0.78f, 0.86f, 1); //new Color(75, 201, 219, 1);
	public final static Color NARANJA = new Color(0.92f, 0.65f, 0.14f, 1);//new Color(235, 166, 36, 1);
	public final static Color AZUL = new Color(0f, 0.11f, 0.75f, 1);//new Color(0, 29, 190, 1);
	public final static Color AMARILLO = new Color(0.90f, 0.92f, 0.24f, 1);//new Color(229, 235, 62, 1);
	public final static Color VERDE = new Color(0.21f, 0.85f, 0.12f, 1);//new Color(54, 216, 32, 1);
	public final static Color VIOLETA  = new Color(0.70f, 0.16f, 0.91f, 1);//new Color(180, 42, 232, 1);
	public final static Color ROJO = new Color(0.80f, 0.31f, 0.31f, 1);//new Color(206, 80, 80, 1);
	public final static Color ROSA = new Color(0.88f, 0.48f, 0.70f, 1);//new Color(228, 124, 179, 1);
	
	//LINEA
	public static Texture hacerLinea(Color color) {
		Pixmap linea = new Pixmap(1, 1, Pixmap.Format.RGB888);
		linea.setColor(color);
	    linea.fill();
	    Texture lineaTextura = new Texture(linea);
	    return lineaTextura;
    }
}
