package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;
/*Importa los modulos de cargar pieza, validar los movimientos
y mover la respectiva pieza*/


/* Esta es la clase hobbit  de la alianza y solo puede matar orcos, huargos pero 
 * movimiendo una casilla adelante
 * 
 * Precondiciones:
 * 
 * * Solo puede comer orcos y huargos. No puede comer miembros de
 *  su propio bando
 *  
 *  * Solo se puede realizar un movimiento a la vez
 *  
 *  Postcondiciones: No hay.
 *  */


public class Hobbit extends Pieza {
	
	int raza = 13;// Numero de identificador

    public Hobbit(int color) {// numero de bando(alianza)
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Hobbit.gif"));

    }

 
    public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
        if (Destino.getPieza() != null) {//No se puede comer de frente
            if (Destino.getInX() - getCuadroPieza().getInX() == 0) {//Movimiento recto
                if (Destino.getInY() - getCuadroPieza().getInY() == getColor() * -1) {
                    return super.validarMovimiento(Destino, tbl);
                    // Si el movimiento es valido, retorna true, y hace dicho movimiento
                }
                
                if (isFirstmov()) {//Si es su primer movimiento
                    if (Math.abs(getCuadroPieza().getInY() - Destino.getInY()) == 0) {
                        if (tbl.getTablero()[Destino.getInX()][Destino.getInY()].getPieza() == null) {//Verificamos que no salte.
                            return super.validarMovimiento(Destino, tbl);
                         // Si el movimiento es valido, retorna true, y hace dicho movimiento
                        }
                    }
                }
                
            }
        } 
        else {
        	
            if (getColor() == -1) {//Si es negro

                if (Destino.getInY() - getCuadroPieza().getInY() == 1) {//Si avanza uno
                    if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
                        return super.validarMovimiento(Destino, tbl);
                     // Si el movimiento es valido, retorna true, y hace dicho movimiento
                    }

                }

                
              

            } else {
            	
                if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
                    if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
                        return super.validarMovimiento(Destino, tbl);
                    }   // Si el movimiento es valido, retorna true, y hace dicho movimiento

                }
                
            } 
        }
        return false; 
        /* Si la cuadricula esta ocupada o es del mismo bando no se 
         * hace el moviento y regresa*/
         
    }
    public void setRaza(int raz){this.raza = raz;} // Establece el numero de raza
    public int getRaza(){return raza;} // Devuelve el numero de raza
}