
package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

/* Esta es la clase Eldarion  que tiene la caracteristica especial de
 *  crearse cuando Arwen y Aragorn se encuentran
 *  
 *  Precondiciones:
 *  
 * 1- Este aparecera en el tablero unicamente cuando Arwen y Aragorn se
 * encuentren y el usuario desea procrearlos
 * 
 * 2- Por Herencia, este debe adquirir los poderes de Arwen y Aragorn, es
 * 
 * decir los movimientos de las piezas
 * 
 * 3- No podra matar fichas del mismo bando
 * 
 * Postcondiciones:
 * 
 * 1. Debera convertirse en Gollum si este es ataca por el anillo(atacara a la alianza)
 * 2. Debera perder los poderes de eldarion y solo se movera adelante
 * si lo ataca Gollum 
 * 
 *  */
public class Eldarion extends Pieza {
	int raza = 8;// Naumero de identificador
	public static boolean movimiento_Adelante = false;
	public static boolean movimiento_Eldarion = true;
	// No se movera adelante a menos que lo ataque Gollum

	// Mientras no lo ataque Gollum, adquirira los movimientos de Arwen y Argorn
    public Eldarion(int color) {
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Eldarion.gif"));
    }
    public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
    	if (movimiento_Eldarion){// Si esta activa
    		Aragorn t = new Aragorn(0);// Adquiere los movimientos de Aragorn

    		t.setCuadroPieza(getCuadroPieza());
    		if (t.validarMovimiento(Destino, tbl)) { // Valida los movimientos como si fueran con Aragorn
    			return super.validarMovimiento(Destino, tbl);// si es vallido, realiza el movimiento
    		}
    		Arwen a = new Arwen(0);
    		a.setCuadroPieza(getCuadroPieza());// Adquiere los movimientos de Aragorn
    		if (a.validarMovimiento(Destino, tbl)) {// Valida los movimientos como si fueran con Arwen
    			return super.validarMovimiento(Destino, tbl);// si es vallido, realiza el movimiento
    		}
    	}
    	// Si es atacada por  Gollum
        if (movimiento_Adelante){

			if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
				if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
					return super.validarMovimiento(Destino, tbl);
				}

			}
		}
        return false; // Si no es valido, regresa a su lugar de origen

    }
    public void setRaza(int raz){this.raza = raz;}// setea el numero de raza, para hacer comparaciones
    public int getRaza(){return raza;}// Devuelve el numero de raza, para hacer comparaciones
    
    public static void setAdelante(boolean adelante){
		movimiento_Adelante = adelante;    	//Establece el movimiento hacia adelante
	}
	public static void setEldarion(boolean poderes){
		movimiento_Eldarion = poderes; // Establece los poderes de Eldarion
	}

	public static boolean getEldarion(){return movimiento_Eldarion;}
	//Verifica si los poderes de Eldarion estan activos
	public static boolean getAdelante(){return movimiento_Adelante;}
}  // Verifica si el movimiento adelante esta activo 
