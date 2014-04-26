package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

/*
	 * Esta  es la clase espada en la que pertenece la clase horda,
	 *  su funcion es que cuando un miembro de la alianza la encuentre
	 *   que finalice el juego, no se muestran 
	 *   
	 *   Precondiciones:
	 *   1. No se deben mostrar en el mapa, esta oculto
	 *   2. Su funcion es que si es hallada por el miembro de la alianza
	 *   significa que ya Gano el juego
	 *   
	 *    Postcondiciones:
	 *    1- Que muestre un mensaje de finalizado el juego para que finalice
	 *    el programa
	 */
// Esta clase SOLO muestra los señuelos para poder ubicarlos en el mapa

public class Espada extends Pieza {
	
	int raza = 9;

    public Espada(int color) {
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Senuelo.gif"));
    }
    
    /*No se mueve ya que es un señuelo */
    public boolean validarMovimiento(CuadroPieza Destino,Tablero tbl) {
      
        return false;
    }
    
    public void setRaza(int raz){this.raza = raz;}// Establece numero de identificador
    public int getRaza(){return raza;}// Devuelve numero de identificador
}
