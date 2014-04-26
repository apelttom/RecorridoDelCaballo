/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

//Importa los modulos de cargar pieza, validar los movimientos
//y mover la respectiva pieza

/*
* Esta es la Clase Gollum perteneciente a la horda. Este tiene la
* habilidad especial de quitar los poderes a sus adversarios pero este
* es vulnerable a frodo, ya que este es el unico que lo puede destruir
* 
* Precondiciones:
*  * Gollum no le puede quitar los poderes a su mismo bando
*  
*  *Gollum se puede duplicar a traves del poder del anillo
*  
*  * Golllum puede ser eliminado por Frodo
*  
*  Postcondiciones:
*  
*  * Como Frodo puede quitarle la vida a Gollum, Frodo puede adquirir la habilidad
*   de moverse como gollum 
* 
* */

public class Gollum extends Pieza {
	int raza = 12;
	public static int vidas = 1;
    public Gollum(int color) {
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Gollum.gif"));

    }
    public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
        if (Destino.getPieza() != null) {//No se puede comer de frente
            if (Destino.getInX() - getCuadroPieza().getInX() == 0) {//Movimiento recto
                if (Destino.getInY() - getCuadroPieza().getInY() == getColor() * -1) {
                    return super.validarMovimiento(Destino, tbl);
                }
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == 1) {//Movimiento derecha
                if (Destino.getInY() - getCuadroPieza().getInY() == 0) {
                    return super.validarMovimiento(Destino, tbl);
                }
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == -1) {//Movimiento izquierda
                if (Destino.getInY() - getCuadroPieza().getInY() == 0) {
                    return super.validarMovimiento(Destino, tbl);
                }
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == 0) {//Movimiento recto
                if (Destino.getInY() - getCuadroPieza().getInY() == getColor() * 1) {
                    return super.validarMovimiento(Destino, tbl);
                }
            }
            
        } 
        else {
                if (Destino.getInY() - getCuadroPieza().getInY() == 1) {//Si avanza uno
                    if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
                        return super.validarMovimiento(Destino, tbl);
                    }

                }
                if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
                    if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
                        return super.validarMovimiento(Destino, tbl);
                    }

                }
                if (Destino.getInX() - getCuadroPieza().getInX() == -1) {//Si avanza uno
                    if (Math.abs(Destino.getInY() - getCuadroPieza().getInY()) == 0) {
                        return super.validarMovimiento(Destino, tbl);
                    }

                }
                if (Destino.getInX() - getCuadroPieza().getInX() == 1) {//Si avanza uno
                    if (Math.abs(Destino.getInY() - getCuadroPieza().getInY()) == 0) {
                        return super.validarMovimiento(Destino, tbl);
                    }

                }
            } 
        
        return false;
    }
    public static void setv_Gollum(int vida){
    	vidas = vidas + vida;
    }
    public static int getv_Gollum(){
    	return vidas;
    }
    public void setRaza(int raz){this.raza = raz;}
    public int getRaza(){return raza;}
}
