package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

/*
 * La clase Anillo es una clase que pertenece a uno de los señuelos del juego
 * 
 * Entradas:
 * 
 * 1. Numero de color: Este es el identificador que permite ver si este
 * pertenece a la horda o a la alianza, en este caso pertenece a la horda
 * 
 * Precondiciones:
 * 
 * 1- El señuelo solo debe afectar a la bando buscador de la espada
 * 
 * 2- Este anillo no puede "comer" otras fichas, es decir no se puede mover
 * 
 * 3- Cuando el anillo ataca no debe desaparecer y este debe estar en otra 
 * posicion de la fila
 * 
 * 
 * Postcondiciones:
 * 
 * El anillo tiene la funcion de atacar a los del bando en busqueda de
 * 
 * la espada y este los debe convertir en un despreciable Gollum, es decir, 
 * 
 * una vez transformado debe atacar al bando de la alianza 
 * */
// Esta clase SOLO muestra los señuelos para poder ubicarlos en el mapa

public class Anillo extends Pieza {
	int raza = 5;
	// Numero de raza a la que pertenece para atacar al bando contrario 

    public Anillo(int color) {
        super(color); // Identifica el numero de bando
        setImagenPieza(new ImageIcon("src/Imagenes/Senuelo.gif"));// Carga la imagen
    }

    public boolean validarMovimiento(CuadroPieza Destino,Tablero tbl) {
    	 /* Este devuelve un valor de false ya que es un señuelo y no se 
         * puede mover*/	
        return false;
    }
    public void setRaza(int raz){this.raza = raz;}
    public int getRaza(){return raza;}

    // Devuelve el numero de raza, este permitira transformar a Gollum 
    //cuando alguien lo toque, es decir es importante cuando vaya a hacer comparaciones con otras piezas 
}