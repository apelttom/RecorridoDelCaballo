package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;

import Juego.Tablero;
import javax.swing.ImageIcon;

/*
 * Esta es la clase Arwen que permite moverse el L, es decir adelante 
 * hacia los lados y en diagonal, (como los movimientos del caballo).
 * 
 * Tiene la caracteristica de ser inmortal, nadie la puede atacar excepto un 
 * nigromante o si un anillo lo convierte en un despreciable Gollum
 * 
 * Tiene la capacidad de procrear a Eldarion si se encuentra con Aragorn
 * 
 * Precondiciones:
 * 
 * 1. No puede comer fichas de su propio bando
 * 
 * 2. En estado de inmortalidad, no puede ser asesinada, solo si es atacada 
 * por un nigromante
 * 
 * 3. Puede procrear a Eldarion, pero ella debe perder su inmortalidad
 * 
 * Postcondiciones:
 * 
 * 1. Si esta en estado mortal, cualquiera puede atacarla y es vulnerable
 * 
 * 2. Si es atacada por Gollum, esta automaticamente pierde su movimiento a caballo
 * y solo se podra mover adelante
 * 
 * */
public class Arwen extends Pieza {

	int raza = 7; 
	// Numero de identificador, importante a la hora de comparar si esta cerca de Aragorn
	public static boolean Inmortalidad = true; 
	// En el juego es inmortal mientras no procree a Eldarion
	public static boolean movimiento_Caballo = true; 
	//Se podra mover a caballo mientas no sea atacada por Gollum
	
	public static boolean movimiento_Adelante = false;
	// No se movera adelante hasta que Gollum la ataque
	
    public Arwen(int color) {
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Arwen.gif"));
        
    }

    

    public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
    	// Si se puede mover en daigonal y a los lados
    	if (movimiento_Caballo){
        if (Math.abs((getCuadroPieza().getInX() - Destino.getInX())) == 2) {//Si se mueve dos casillas en x
            if (Math.abs(getCuadroPieza().getInY() - Destino.getInY()) == 1) {//Si se mueve una casillas en y
                return super.validarMovimiento(Destino, tbl);
            }
        }

        if (Math.abs(getCuadroPieza().getInY() - Destino.getInY()) == 2) {//Si se mueve dos casillas en y
            if (Math.abs(getCuadroPieza().getInX() - Destino.getInX()) == 1) {//Si se mueve 1 casillas en x
                return super.validarMovimiento(Destino, tbl);
            }
        }
        }
    	if (movimiento_Adelante){// Se activa si es atacada por gollum
            if (Destino.getInY() - getCuadroPieza().getInY() == -1) {//Si avanza uno
                if (Math.abs(Destino.getInX() - getCuadroPieza().getInX()) == 0) {
                    return super.validarMovimiento(Destino, tbl);
                }

            }
    	}
        return false; // Si existe algun elemento ocupado o si quiere mover mas de uno se devuelve
    }
    
    public void setRaza(int raz){this.raza = raz;}// Establece numero de raza
    public int getRaza(){return raza;}// Devuelve numero de raza
    
    public static void setCaballo(boolean caballo){// Establece el movimiento a caballo
    	movimiento_Caballo = caballo;// Verifica si se puede mover a caballo
    }
    public static void setInmortal(boolean inmortal){// Establece inmortalidad
    	Inmortalidad = inmortal;
    }
    public static void setAdelante(boolean adelante){// Verifica si arwen es inmortalidad
    	movimiento_Adelante = adelante;
    }
    public static boolean getCaballo() {return movimiento_Caballo;}
    public static boolean getInmortal() {return Inmortalidad;}
    public static boolean getAdelante() {return movimiento_Adelante;}    
}