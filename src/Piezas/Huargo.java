/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;

import Juego.Tablero;
import javax.swing.ImageIcon;

/*Importa los modulos de cargar pieza, validar los movimientos
y mover la respectiva pieza*/


/*Esta es la clase Huargo de la horda. Este tiene la especislidad de tener
 * los movimientos de Arwen si llegan a matarla y matar cualquiera a su paso pero es vulnerable
 * ante adversarios comom Aragorn o Gandalf
 * 
 * Precondiciones:
 * 
 * * No debe matar a miembros de su mismo bando
 * 
 * * Debe tener los mismos movimientos de Arwen si es matada, sino sus 
 * movimientos son como las de un orco
 * 
 *  Postcondiciones:
 *  
 *  * Si matan a Arwen en su estadi mortal, ellos adquiriran sus movimientos
 * */

public class Huargo extends Pieza {
	int raza = 14;
	
	public static boolean movimiento_Caballo = false;
	public static boolean movimiento_Adelante = true;
	
    public Huargo(int color) {
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Huargo.gif"));

    }

    public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {

    	// Si ya mato a Arwen en su estado mortal, adquiere sus movimientos
        if (movimiento_Caballo){
            if (Math.abs((getCuadroPieza().getInX() - Destino.getInX())) == 2) {//Si se mueve dos casillas en x
                if (Math.abs(getCuadroPieza().getInY() - Destino.getInY()) == 1) {//Si se mueve una casillas en y
                    return super.validarMovimiento(Destino, tbl);
                    // Si el movimiento es valido, retorna true y se procedera su movimiento
                }
            }

            if (Math.abs(getCuadroPieza().getInY() - Destino.getInY()) == 2) {//Si se mueve dos casillas en y
                if (Math.abs(getCuadroPieza().getInX() - Destino.getInX()) == 1) {//Si se mueve 1 casillas en x
                    return super.validarMovimiento(Destino, tbl);
                }    // Si el movimiento es valido, retorna true y se procedera su movimiento
            }
        }
        
        // Mientras esten en su estado normal
        if (movimiento_Adelante){
            if (Destino.getInY() - getCuadroPieza().getInY() == 1) {//Si avanza uno
                   if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
                       return super.validarMovimiento(Destino, tbl);
                   }   // Si el movimiento es valido, retorna true y se procedera su movimiento

               }
           }        
        return false; // si esta ocupado y es de su bando no se movera y regresara a su lugar original
    }
    public void setRaza(int raz){this.raza = raz;} // Establece el numero de raza
    public int getRaza(){return raza;}// Devuelve el numero de raza
    
    // Establece si el movimiento es adelante
    public static void setmovimiento_Adelante(boolean adelante){
    
    	movimiento_Adelante = adelante;
    }
    
 // Establece si el movimiento es  a caballo
    public static void setmovimiento_Caballo(boolean caballo){
    	movimiento_Caballo = caballo;
    }
 // Devuelve si el movimiento es adelante y a caballo respectivamente
    public static boolean getmovimiento_Adelante(){ return movimiento_Adelante; }
    public static boolean getmovimento_Caballo () {return movimiento_Caballo; }
}
