package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

/*
 * Esta es la clase Frodo que pertenece a la alianza, este tiene la caracteristica
 * especial de poseer 3 vidas disponibles, y el poder de eliminar a Gollum,
 * pero sus movimientos son las de un hobbit.  
 *
 *
 * Precondiciones:
 * 
 * 1- No se debe matar piezas de su mismo bando
 * 
 * 2- Como tiene 3 vidas disponibles, este morira cuando un orco
 * lo capture y lo despoje de su espada, de Mithil y finalmente le quite la vida
 * 
 * 3- No debe tener movimientos especiales, solo se mueve como un  hobbit
 * 
 * 
 * Postcondiciones:
 * 
 * Si es capturado 3 veces,este muere y debe desparecer del tablero 
 *
 * */

public class Frodo extends Pieza {

	int raza = 10;// Numero de identificador
	
	public static boolean movimiento_Gollum = false;// Este no adquiere los movimientos de Gollum  hasta que lo mate
	public static int vidas_Disponibles = 3; // Si éste atributo es 3, tiene la espada, el mithril y su vida propia
	public static boolean movimiento_Hobbit = true;// Si no ha matado a Gollum, este tendra el movimiento de un hobbit
	
    public Frodo(int color) {
        super(color);// numero de bando(alianza)
        setImagenPieza(new ImageIcon("src/Imagenes/Frodo.gif"));// carga la imagen

    }

 
    public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
        if (Destino.getPieza() != null) {//No se puede comer de frente
            if (Destino.getInX() - getCuadroPieza().getInX() == 0) {//Movimiento recto
                if (Destino.getInY() - getCuadroPieza().getInY() == getColor() * -1) {
                    return super.validarMovimiento(Destino, tbl);
                }
                if (isFirstmov()) {//Si es su primer movimiento
                    if (Math.abs(getCuadroPieza().getInY() - Destino.getInY()) == 0) {
                    	try
                    	{
                    		if (tbl.getTablero()[Destino.getInX()][Destino.getInY() + getColor()].getPieza() == null) {//Verificamos que no salte.
                                return super.validarMovimiento(Destino, tbl);}	
                    	}
                        
                        catch(ArrayIndexOutOfBoundsException e)
                        {}
                        
                    }
                }
            }
        } 
        else {
        	if (movimiento_Hobbit){ // Mientras no mate a Gollum
        		if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
        			if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
        				return super.validarMovimiento(Destino, tbl);
        			}  // Se movera una posicion adelante

        		}
        	}
        	if (movimiento_Gollum){ // Si ya mato una gollum
        		if (Destino.getInY() - getCuadroPieza().getInY() == 1) {//Si avanza uno
        			if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
        				return super.validarMovimiento(Destino, tbl);
        			}// Tendra ahora los movimientos de Gollum

        		}
        		if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
        			if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
        				return super.validarMovimiento(Destino, tbl);
        			}   //Se mueve a la derecha e izqueirda

        		}
        		if (Destino.getInX() - getCuadroPieza().getInX() == -1) {//Si avanza uno
        			if (Math.abs(Destino.getInY() - getCuadroPieza().getInY()) == 0) {
        				return super.validarMovimiento(Destino, tbl);
        			} //Se mueve a la derecha e izquiedrda

        		}
        		if (Destino.getInX() - getCuadroPieza().getInX() == 1) {//Si avanza uno
        			if (Math.abs(Destino.getInY() - getCuadroPieza().getInY()) == 0) {
        				return super.validarMovimiento(Destino, tbl);
        				//Se mueve a la derecha e izquirda
        			}
        		} /*LAS BLOQUES DE IFS QUE TENGAN UN CODIGO PARECIDO
                NO SIGNFICA QUE VA A SER INUTILIZABLES, DIFERIRAN EN
                LA POSICION DEL TABLERO*/
        	}
        }
        return false;// Si el movimiento no es valido , regresa a su lugar de origen
    }
    public void setRaza(int raz){this.raza = raz;} // Establece el numero de raza
    public int getRaza(){return raza;} // Devuelve el numero de raza
    public static void setM_Gollum(boolean m_gollum){ //Establece los movimientos de Gollum
    	movimiento_Gollum = m_gollum;
    }
    public static void set_vidasDisponibles(int vida){ vidas_Disponibles = vida; } 
    // Establece los numeros de vida 
    public static int get_vidasDisponibles(){ return vidas_Disponibles; }
    // Devuelve el numero de vidas
}
