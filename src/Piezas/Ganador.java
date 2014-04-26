package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;


/* Esta clase unicamente muestra la espada en señal que ya gano el juego*/

// Esta clase SOLO muestra los señuelos para poder ubicarlos en el mapa
// Esta clase SOLO muestra los señuelos para poder ubicarlos en el mapa
public class Ganador extends Pieza {


    public Ganador(int color) {
        super(color);// Numero de identificador
        setImagenPieza(new ImageIcon("src/Imagenes/Hw.gif")); // Carga la imagen
    }

    public boolean validarMovimiento(CuadroPieza Destino,Tablero tbl) {
    	 /* No se mueve por es un señuelo */
        return false;
    }
    
}
