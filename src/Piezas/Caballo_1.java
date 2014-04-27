package Piezas;

import Juego.Pieza;
import Juego.CuadroPieza;
import Juego.Board_Horses;
import javax.swing.ImageIcon;

/*Esta es la clase Aragorn de la cual pertenece al tablero,
 * 
 * este permite mover su pieza dandole click en dicha pieza o arrastrando su
 * 
 * ficha al cuadro destino
 * 
 *  Entradas:
 *  1. La raza o el numero al que pertenece: esta variable es muy crucial, ya 
 *  que permite identificar otras fichas  a la hora de hacer sus validaciones o
 *  cuando mata a una ficha adversa. Por ejemplo: Si Aragorn se encuentra con 
 *  Arwen, este debe procrear a Eldarion( si el usuario lo desea)
 *  
 *   2. Movimientos en diagonal, lateral y adelante: Estos son valores de tipo
 *   booleano que permite activar o deactivar los movimientos permitidos, tal 
 *   es el caso si Gollum (cuya funcion es quitar poderes), se "come" a uno
 *   de sus jugadores, le quita los poderes o habilidades de moverse en diagonal
 *   o lateral en caso de un segundo encuentro
 *  
 * */
public class Caballo_1 extends Pieza {

	int raza = 6;// Numero de identificador de ficha
	public static String vidas = "1";// Cantidad de vidas de la ficha
	public static boolean movimiento_Diagonal  = true;// Permite moverse en diagonal
	public static boolean movimiento_Lateral  = true; // Permite moverse en lateral
	public static boolean movimiento_Adelante = false;// No permite moverse adelante, a menos que Gollum le quite sus poderes
	public static int posicion_y = 7; //Posicion inicial en el Tablero

	

	/*Se crea el constructor que recibe un entero (color) el cual es el numero
	 * 
	 * si este pertenece a la horda, cuyo numero es -1 o la alianza, cuyo numero es 1
	 * 
	 * */
	
	public Caballo_1(int color) {
		// Llama a la superclase pieza que identifica y este es de la horda
		// o de la alianza
		super(color);
		setImagenPieza(new ImageIcon("src/Imagenes/Horse_1.gif"));
		// Establece la pieza en el tablero
	}
	/*
	 * Validar Movimiento, metodo sobreescrito de la clase Pieza.
	 */
	/*
	 * Esta clase tiene validar movimientos, que devuelve un
	 * valor booleano si el movimiento que se va a hacer es un 
	 * movimiento valido
	 * 
	 * Precondiciones: 
	 * 
	 * 1. Aragorn solamente debe moverse en diagonal dos casillas como 
	 * maximo
	 * 
	 * 2. Aragorn se puede mover en lateral a ambos lados dos posiciones como
	 * maximo
	 * 
	 * 3. Aragorn no puede comerse a fichas de su mismo bando
	 * 
	 * 4. Aragorn debe comer un solo jugador del bando contrario
	 * */
	
	public boolean validarMovimiento(CuadroPieza Destino, Board_Horses tbl) {
		//Si la cantidad de cuadros movidos horizontalmente es la misma que verticalmente.
		if (movimiento_Diagonal){
			if (((Destino.getInY() - getCuadroPieza().getInY()== -2) && (Destino.getInX() - getCuadroPieza().getInX()== -2))   || ((Destino.getInY() - getCuadroPieza().getInY()== 2) && (Destino.getInX() - getCuadroPieza().getInX()== 2))   || ((Destino.getInY() - getCuadroPieza().getInY()== 2) && (Destino.getInX() - getCuadroPieza().getInX()== -2))  || ((Destino.getInY() - getCuadroPieza().getInY()== -2) && (Destino.getInX() - getCuadroPieza().getInX()== 2))  ) {
				return super.validarMovimiento(Destino, tbl);//Si todo esta bien debería retornar true, a menos que haya un problema con el validar movimiento del padre.
			}
		}
		
		if(movimiento_Lateral){

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
		/* El movimiento Hacia Adelante  solo se puede efectuar  si y solo
		    * si Golllum le haya quitado los poderes 2 veces*/
		if (movimiento_Adelante){

			if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
				if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
					return super.validarMovimiento(Destino, tbl);
				}

			}
		}
		return false;

	}
	public void setRaza(int raz){this.raza = raz;}
	public int getRaza(){return raza;}

	public static void setDiagonal(boolean diagonal){
		movimiento_Diagonal = diagonal;    	
	}
	public static void setLateral(boolean lateral){
		movimiento_Lateral = lateral;    	
	}
	public static void setAdelante(boolean adelante){
		movimiento_Adelante = adelante;    	
	}
	public static void setPosicion_y(int p_y){
		posicion_y = p_y;
	}

	public static boolean getDiagonal(){return movimiento_Diagonal;}
	public static boolean getLateral(){return movimiento_Lateral;}
	public static boolean getAdelante(){return movimiento_Adelante;}
	public static int getPosicion_y(){ return posicion_y; }




}


