package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

/*Importa los modulos de cargar pieza, validar los movimientos
y mover la respectiva pieza*/

/*
 * Precondiciones: Podra matar a cualquier miembro de la alianza
 * ya que practicamente es indestructible. No se podra mover ya que es un 
 * se�uelo
 * 
 * Postcondiciones: no hay
 * */

// Esta clase SOLO muestra los se�uelos para poder ubicarlos en el mapa
public class Nigromante extends Pieza {
	
	int raza = 15; // Numero de identificador

    public Nigromante(int color) {// Numero de bando(horda)
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Senuelo.gif"));//Carga el se�uelo
    }

    

    @Override
    public boolean validarMovimiento(CuadroPieza Destino,Tablero tbl) {
    	/*No se podra mover por que es un se�uelo*/
      
        return false;
    }
    public void setRaza(int raz){this.raza = raz;}//Establece el numero de id
    public int getRaza(){return raza;} // Devuelve el numero de id
}