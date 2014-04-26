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
* Esta es la clase Gandalf de la alianza cuya caracteristica es moverse
* en diagonal, lateralmente y hacia adelante. Ademas cuenta con una habilidad
*  especial que si logra destruir a Saruman, este adquirira sus poderes
*  
*   Precondiciones:
*   * No se podra comer a fichas del mismo bando
*   * Solo se tendra los poderes de Saruman si Gandalf logra 
*   matarlo
*   
*   * Si lo aniquilan, tendra 2 minutos para poder resucitar
*   
*   * Si es atacado por Gollum, este perdera la capacidad de resucitar y
*    sera vulnerable
*    
*     Postcondiciones:
*  
*     * Tendra los poderes de Saruman si lo mata lo que lo hace mas poderoso
*     pero ante el nigromante
*   **/



public class Gandalf extends Pieza {
	int raza = 11; // numero de identificador
	public static boolean movimiento_Saruman = false; // no ha matado a Saruman aun
	public static boolean movimiento_Diagonal = true; // se puede mover en diagonal
	public static boolean movimiento_Lateral = true; // se puede mover diagonalmente
	public static boolean movimiento_Adelante = true; // se podra mover adelante
	public static boolean resucitar = true; // Podra resucitar una unica vez
    public Gandalf(int color) { //numero de bando (1)
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Gandalf.gif"));

    }
 
public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
    	
    	// Verifica si se puede mover en diagonal 
    	if (movimiento_Lateral){
    	

            if (Destino.getInX() - getCuadroPieza().getInX() == 1) {//Movimiento derecha
                if (Destino.getInY() - getCuadroPieza().getInY() == 0) {
                    return super.validarMovimiento(Destino, tbl);
                    // Si el movimiento es valido, se hace el movimiento
                }
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == -1) {//Movimiento izquierda
                if (Destino.getInY() - getCuadroPieza().getInY() == 0) {
                    return super.validarMovimiento(Destino, tbl);
                }   // Si el movimiento es valido, se hace el movimiento
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == 0) {//Movimiento recto
                if (Destino.getInY() - getCuadroPieza().getInY() == getColor() * 1) {
                    return super.validarMovimiento(Destino, tbl);
                }   // Si el movimiento es valido, se hace el movimiento
            }
    	}
////////////////////////////////////
    	// Si el movimiento es en diagonal
    	if (movimiento_Diagonal){
            if (Destino.getInX() - getCuadroPieza().getInX() == -1) {//Movimiento sup izq
                if (Destino.getInY() - getCuadroPieza().getInY() == 1) {
                    return super.validarMovimiento(Destino, tbl);
                 // Si el movimiento es valido, se hace el movimiento
                }
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == -1) {//Movimiento inf izq
                if (Destino.getInY() - getCuadroPieza().getInY() == -1) {
                    return super.validarMovimiento(Destino, tbl);
                }   // Si el movimiento es valido, se hace el movimiento
            }

            if (Destino.getInX() - getCuadroPieza().getInX() == 1) {//Movimiento sup der
                if (Destino.getInY() - getCuadroPieza().getInY() == 1) {
                    return super.validarMovimiento(Destino, tbl);
                 // Si el movimiento es valido, se hace el movimiento
                }
            }
            if (Destino.getInX() - getCuadroPieza().getInX() == 1) {//Movimiento inf der
                if (Destino.getInY() - getCuadroPieza().getInY() == -1) {
                    return super.validarMovimiento(Destino, tbl);
                 // Si el movimiento es valido, se hace el movimiento
                }
            }
            
    	}
    	//Si se puede mover adelante, como un hobbit 
    	if (movimiento_Adelante){
            if (Destino.getInX() - getCuadroPieza().getInX() == 0) {//Movimiento recto
                if (Destino.getInY() - getCuadroPieza().getInY() == getColor() * -1) {
                    return super.validarMovimiento(Destino, tbl);
                }   // Si el movimiento es valido, se hace el movimiento
            }

    	}
    	// Si Gandalf mata a Saruman
    	if (movimiento_Saruman){
            Saruman s = new Saruman(0); 
            // Se crea a Saruman pero a favor de la alianza
            s.setCuadroPieza(getCuadroPieza());//Pone la pieza en el cuadro
            // Realiza que no haya choques con otras piezas
            if (s.validarMovimiento(Destino, tbl)) {
                return super.validarMovimiento(Destino, tbl);
            }   // Si el movimiento es valido, se hace el movimiento
    	}
            
        return false;
    }
    public void setRaza(int raz){this.raza = raz;}// Establece numero de raza
    public int getRaza(){return raza;}// devuelve numero de raza
    
    public static void set_Poder_Saruman(boolean poder){
    	movimiento_Saruman = poder; // Establece el poder de Saruman
    }
    public static void set_Movimiento_Lateral(boolean lateral){
    	movimiento_Lateral = lateral; // Establece el movimiento lateral
    }
    public static void set_Movimiento_Diagonal(boolean diagonal){
    	movimiento_Diagonal = diagonal; // Establece el movimiento diagonal
    }
    public static void set_Movimiento_Adelante(boolean adelante){
    	movimiento_Adelante = adelante; // Establece el movimiento adelante
    }
    public static void set_Resucitar(boolean resurect){
    	resucitar = resurect; // Establece si se resucita
    }
    public static boolean get_Poder_Saruman(){
    	return movimiento_Saruman; // Verifica si mato a Saruman
    }
    public static boolean get_Movimiento_Lateral(){
    	return movimiento_Lateral; // Verifica el movimiento Lateral
    }
    public static boolean get_Movimiento_Diagonal(){
    	return movimiento_Diagonal;// Verifica el movimiento diagonal
    }
    public static boolean get_Movimiento_Adelante(){
    	return movimiento_Adelante;// Verifica el movimiento adelante
    }
    public static boolean get_Resucitar(){
    	return resucitar; // Verifica si esta resucitado
    }
}
