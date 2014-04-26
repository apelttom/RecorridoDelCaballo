package Piezas;

import Juego.CuadroPieza;
import Juego.Pieza;
import Juego.Tablero;
import javax.swing.ImageIcon;

/*Importa los modulos de cargar pieza, validar los movimientos
y mover la respectiva pieza*/

/* Esta es la clase Saruman de la horda. Este malvado msgo tiene la habilidad 
 * de mover a sus adversarios a lo mas bajo de la cima con su magia pero
 * este solo puede ser derrotado por Gandalf que al final se convertira en 
 * el Mago Blanco
 * 
 * Precondiciones:
 * 
 * * Su poder no hace vefecto para orcos y huargos
 * 
 * * Es vulnerable ante Gandalf
 * 
 * Postcondiciones:
 * 
 * * Cuando es derrotado este muere, pierde sus poderes y son adquiridos
 * hacia Gandalf
 * */

public class Saruman extends Pieza {
	int raza = 17; //Numero de identificador 

    public Saruman(int color) { // Naumero de bando(horda)
        super(color);
        setImagenPieza(new ImageIcon("src/Imagenes/Saruman.gif"));
    }   // Carga la pieza hacia el tablero

    
    // Valida el movimiento de Saruman 
    public boolean validarMovimiento(CuadroPieza Destino,Tablero tbl) {
        //Si se mueve en Y
        if (Destino.getInX() - getCuadroPieza().getInX() == 0) {
            //Variables del for
            int inicio, fin;
            if(getCuadroPieza().getInY()<Destino.getInY()){
                inicio = getCuadroPieza().getInY()+1;
             // inicio tendra el valor que esta en los X del tablero 
                fin = Destino.getInY();
             // fin tendra el valor que se coloca en los Y del tablero 
            }else{
                inicio = Destino.getInY()+1;
                fin = getCuadroPieza().getInY();
            }
            //recorro todos los valores
            for (int i = inicio; i < fin; i++) {
                if (tbl.getTablero()[getCuadroPieza().getInX()][i].getPieza() != null) {
                    return false; /// Si esta ocupado el espacio, no se movera
                }
            }
            return super.validarMovimiento(Destino, tbl);
        }
        //Si se mueve en X
        if (Destino.getInY() - getCuadroPieza().getInY() == 0) {

            //Variables del for
            int inicio, fin;
            if(getCuadroPieza().getInX()<Destino.getInX()){
                inicio = getCuadroPieza().getInX()+1;
                fin = Destino.getInX();
            }else{
                inicio = Destino.getInX()+1;
             // inicio tendra el valor que esta en los X del tablero 
                fin = getCuadroPieza().getInX();
                
                // fin tendra el valor que se coloca en los Y del tablero 
            }
            //recorro todos los valores
            for (int i = inicio; i < fin; i++) {
                if (tbl.getTablero()[i][getCuadroPieza().getInY()].getPieza() != null) {
                    return false;/// Si esta ocupado el espacio, no se movera
                }
            }
            return super.validarMovimiento(Destino, tbl);
            // Si no esta ocupado se podra realizar el movimiento  
        }
        return false; // El movimiento es invalido y regresa al lugar de origen
    }
    public void setRaza(int raz){this.raza = raz;} // Establece numero de id
    public int getRaza(){return raza;} // Devuelve numero de id
}