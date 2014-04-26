package Juego;

/**
 * Autor:Daniel Chaves Coto y Oswáldo
 * 01, 02, 03/04/2013
 */

/**
 * Son importadas todas las librerias necesarias para el desarrollo de la clase JuegoPrincipal.
 */
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Piezas.Ganador;
import Piezas.Hobbit;
import Piezas.Orco;
import Piezas.Anillo;
import Piezas.Aragorn;
import Piezas.Eldarion;
import Piezas.Espada;
import Piezas.Arwen;
import Piezas.Frodo;
import Piezas.Gandalf;
import Piezas.Gollum;
import Piezas.Saruman;
import Piezas.Nigromante;
import Piezas.Huargo;
/**
 * Esta clase abstracta establecerá¡ las funciones basicas de toda pieza, sera la clase padre, de las demas piezas
 * Se declara abstracta para que no pueda ser instanciada.
 */
public abstract class Pieza {



	/**
	 * Es una variable static o variable de clase, establece cuantos mavimientos hay actualmente sin realizar algun cambio(comer a otras piezas)
	 */

	private boolean esta_muerto;
	private static boolean tiene_poderes;
	private boolean firstmov = true;
	/**
	 * Establece el peso de la pieza, esto quiere decir cuanto vale una pieza, los valores de las piezas serán establecidos por las clases que hereden.
	 * Los valores son los siquientes:

	 */
	private int vidas;
	private int color;
	private int raza;
	/**
	 * Es la imagen de la pieza, que será declarada en las clases hijas.
	 */
	private ImageIcon imagenPieza;
	/**
	 * Es el cuadro del tablero en donde actualmente esta la pieza.
	 */
	private CuadroPieza cuadroPieza = null;

	/**
	 * Constructor.
	 * @param color
	 * Comunidad: 1
	 * Mordor: -1
	 */
	public Pieza(int color) {
		this.color = color;

	}

	/**Autor: Oswaldo Dávila
	 * Este método valida el movimiento, solo valida, NO Mueve las piezas
	 * Este método es un método general, que todas las piezas van a cumplir. Además cada pieza tiene otras validaciones
	 * Las clases piezas que hereden pueden sobreescribir el método y reutilizarlo a la vez.
	 * @param Destino
	 * @param tbl
	 * @return 
	 * Entradas: Cuadro Destino y el tablero en juego
	 * Salidas: Permitir o no el movimeinto de una pieza
	 * Precondiciones: Todas las piezas debe cumplir este método
	 * Postcondiciones: Las clases piezas que hereden pueden sobreescribir el método y reutilizarlo a la vez.
	 */
	public boolean validarMovimiento(CuadroPieza Destino, Tablero tbl) {
		if (Destino.getPieza() != null) {//Si el cuadro destino esta ocupado entonces:
			if (Destino.getPieza().getColor() == getCuadroPieza().getPieza().getColor()) {
				//Si la pieza destino tiene el mismo color que la pieza que voy a mover
				return false; //El movimiento es inválido, no puedo comer una pieza de mi mismo equipo.
			}
		}
		return true; // retornamos true al llevarse a cabo el movimiento de la ficha en una posición válida!

	}

	/**
	 * Autor: Oswaldo Dávila y Daniel Chávez
	 * Este método mueve la pieza a un cuadro destino
	 * @param Destino
	 * @param tbl
	 * @return boolean FALSE si no logra mover la pieza.
	 * Entradas: Destino de la Pieza y el tablero en juego.
	 * Salidas: Valda el movimiento de la pieza a su cuadro destino.
	 * Precondiciones:Validar que las clases hijas sbreesccriban dicho método
	 * Postcondiciones: Realizar la validación correcta de los movimeintos de las piezas
	 */
	public boolean MoverPieza(CuadroPieza Destino, Tablero tbl) {
		/*
		 * Valido el movimiento, antes de mover, tener en cuenta que en las clases hijas este método debe haber sido sobreescrito
		 * Por lo que no solo va a validar lo que hay en el método validarMovimiento de Pieza, si no va a usar el metodo sobreescrito en la clase hija
		 */

		if (validarMovimiento(Destino, tbl)) {
			
			
			/**
			 * Todo esto es un gran bloque de desición que internamente contiene mas bloques de desición, debido
			 * a que tiene que tomar en cuenta todas las validaciones de los posibles movimientos entre 
			 * las fichas de ambos bandos
			 */
			
			
			/**
			 * Este bloque de desición valida los movimiento de Aragorn contra: Gollum, Nigromante, Saruman y Gandalf, asi mismo a la vez se encarga de 
			 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Aragon en el juego.
			 */

			if (Destino.getPieza() != null) {//Si hay una pieza en el destino
				if (getCuadroPieza().getPieza().getRaza() == 6){ //aragorn

					if (Destino.getPieza().getRaza() == 12){
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Gollum.setv_Gollum(-1);
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}

					}
					else if (Destino.getPieza().getRaza() == 15){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar el  nigromante, su ficha será destruida");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						if (Gandalf.get_Resucitar()){
							Gandalf.set_Resucitar(false);
							JuegoPrincipal.v_Gandalf.setText("1");
							JuegoPrincipal.v_Aragorn.setText("Muerto");
						}
						else{
							JuegoPrincipal.v_Gandalf.setText("Muerto");
						}

						return true;
					}
					else if (Destino.getPieza().getRaza() == 17){ //Saruman
						if (Math.abs(Destino.getInY() - getCuadroPieza().getInY()) == Math.abs(Destino.getInX() - getCuadroPieza().getInX())){
							getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
							Destino.setPieza(this);//Muevo la pieza al cuadro destino
							JuegoPrincipal.v_Saruman.setText("Muerto");
							Tablero.CrearEldarionAragorn(Destino.getInX(), Destino.getInY());
							Aragorn.setPosicion_y(Destino.getInY());
							return true;
						}
						else{
							getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
							Destino.setPieza(this);//Muevo la pieza al cuadro destino
							Tablero.CrearEldarionAragorn(Destino.getInX(), Destino.getInY());
							Aragorn.setPosicion_y(Destino.getInY());
							return true;
						}
					}
					else if (Destino.getPieza().getRaza() == 9){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Has completado tu busqueda, la espada narsil es ahora tuya");
						Destino.setPieza(new Ganador(-1));//Muevo la pieza al cuadro destino
						reiniciar();
						return true;
					}
					else if (Destino.getPieza().getRaza() == 5){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar al anillo, te convertiras en un despreciable gollum");
						getCuadroPieza().setPieza(new Gollum(-1));//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						Gandalf.set_Resucitar(false);
						JuegoPrincipal.v_Gandalf.setText("1");
						JuegoPrincipal.v_Aragorn.setText("Muerto");
						Gollum.setv_Gollum(1);
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}
					else
					{
						//vs huargos u orcos
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Tablero.CrearEldarionAragorn(Destino.getInX(), Destino.getInY());
						Aragorn.setPosicion_y(Destino.getInY());
						return true;
					}
				}
				
				/**
				 * Este bloque de desición valida los movimiento de Arwen contra: Gollum, Nigromante, Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
				 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Arwen en el juego.
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 7){ // arwen
					if (Destino.getPieza().getRaza() == 12){
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Gollum.setv_Gollum(-1);
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}

					}

					else if (Destino.getPieza().getRaza() == 15){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar el  nigromante, su ficha será destruida");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						JuegoPrincipal.v_Arwen.setText("Muerta");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 17){
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Tablero.CrearEldarionArwen(Destino.getInX(), Destino.getInY());
						JuegoPrincipal.v_Saruman.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 9){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Has completado tu busqueda, la espada narsil es ahora tuya");
						Destino.setPieza(new Ganador(-1));//Muevo la pieza al cuadro destino
						reiniciar();
						return true;
					}
					else if (Destino.getPieza().getRaza() == 5){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar al anillo, te convertiras en un despreciable gollum");
						getCuadroPieza().setPieza(new Gollum(-1));//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						JuegoPrincipal.v_Arwen.setText("Muerta");
						Gollum.setv_Gollum(1);
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}

						return true;
					}	
					else
					{
						//vs huargos u orcos
						Tablero.CrearEldarionArwen(Destino.getInX(), Destino.getInY());
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}

				}
				/**
				 * Este bloque de desición valida los movimiento de Eldarion contra: Gollum,Arwen, Nigromante, Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
				 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Eldarion en el juego.
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 8){ // eldarion
					if (Destino.getPieza().getRaza() == 12){
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Gollum.setv_Gollum(-1);
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}
					else if (Destino.getPieza().getRaza() == 15){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar el  nigromante, su ficha será destruida");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						JuegoPrincipal.v_Eldarion.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 17){
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						JuegoPrincipal.v_Saruman.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 9){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Has completado tu busqueda, la espada narsil es ahora tuya");
						Destino.setPieza(new Ganador(-1));//Muevo la pieza al cuadro destino
						reiniciar();
						return true;
					}
					else if (Destino.getPieza().getRaza() == 5){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar al anillo, te convertiras en un despreciable gollum");
						getCuadroPieza().setPieza(new Gollum(-1));//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						JuegoPrincipal.v_Eldarion.setText("Muerto");
						Gollum.setv_Gollum(1);
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}
					else
					{
						//vs huargos u orcos
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}

				}
				/**
				 * Este bloque de desición valida los movimiento de Frodo contra: Gollum,Arwen, Eldarion, Nigromante, Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
				 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Frodo en el juego.
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 10){ //  frodo
					if (Destino.getPieza().getRaza() == 5){ // vs anillo
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar al anillo, te convertiras en un despreciable gollum");
						getCuadroPieza().setPieza(new Gollum(-1));//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");

						JuegoPrincipal.v_Frodo.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 12){ // vs golum.
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Frodo.setM_Gollum(true);
						Gollum.setv_Gollum(-1);
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}

					else if (Destino.getPieza().getRaza() == 15){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar el  nigromante, su ficha será destruida");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						JuegoPrincipal.v_Frodo.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 17){
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						JuegoPrincipal.v_Saruman.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 9){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Has completado tu busqueda, la espada narsil es ahora tuya");
						Destino.setPieza(new Ganador(-1));//Muevo la pieza al cuadro destino
						reiniciar();
						return true;
					}
					else
					{
						//vs huargos u orcos
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}
				}

				/**
				 * Este bloque de desición valida los movimiento de Gandalf contra: Gollum,Arwen, Eldarion,Frodo , Nigromante, Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
				 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Gandalf en el juego.
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 11){ //  gandalf

					if (Destino.getPieza().getRaza() == 5){ // vs anillo
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar al anillo, te convertiras en un despreciable gollum");
						getCuadroPieza().setPieza(new Gollum(-1));//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						Gandalf.set_Resucitar(false);
						JuegoPrincipal.v_Gandalf.setText("Muerto");
						Gollum.setv_Gollum(1);
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}
					
					/**
					 * Este bloque de desición valida los movimiento de Gollum contra: Gandalf,Arwen, Eldarion,Frodo , Nigromante, Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
					 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Gollum en el juego.
					 */
					else if (Destino.getPieza().getRaza() == 12){ // vs golum.
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Gollum.setv_Gollum(-1);
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;

					}
					
					/**
					 * Este bloque de desición valida los movimiento de Nigromante contra: Gandalf,Arwen, Eldarion,Frodo , Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
					 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Nigromante en el juego.
					 */

					else if (Destino.getPieza().getRaza() == 15){// vs nigromante
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar el  nigromante, su ficha será destruida");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						if (Gandalf.get_Resucitar()){
							int tiempo = (CronometroThread.getMinutos() * 60) + CronometroThread.getSegundos() + 120;
							CronometroThread.setTiempo_a_resucitar(tiempo);
							CronometroThread.setDebe_Resucitar(true);
							getCuadroPieza().setPieza(null);
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gandalf ha muerto, en 2 minutos resucitará");
							JuegoPrincipal.v_Gandalf.setText("Resucitando");
							Gandalf.set_Resucitar(false);
							Tablero.destruye_Senuelos();
							Tablero.ubica_Senuelos("Nigromante");
							Tablero.ubica_Senuelos("Anillo");
							Tablero.ubica_Senuelos("Espada");
							return true;
						}
						else{
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gandalf ha muerto al atacar al nigromante");
							getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
							JuegoPrincipal.v_Gandalf.setText("Muerto");
							Tablero.destruye_Senuelos();
							Tablero.ubica_Senuelos("Nigromante");
							Tablero.ubica_Senuelos("Anillo");
							Tablero.ubica_Senuelos("Espada");
							return true;
						}
					}
					
					/**
					 * Este bloque de desición valida los movimiento de Saruma contra: Gandalf,Arwen, Eldarion,Frodo , Nigromante, Aragorn y Gandalf, asi mismo a la vez se encarga de 
					 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Saruma en el juego.
					 */
					else if (Destino.getPieza().getRaza() == 17){//vs saruman
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Saruman ha sido vencido por gandalf, Gandalf es ahora el mago blanco");
						Gandalf.set_Poder_Saruman(true);
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						JuegoPrincipal.v_Saruman.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 9){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Has completado tu busqueda, la espada narsil es ahora tuya");
						Destino.setPieza(new Ganador(-1));//Muevo la pieza al cuadro destino
						reiniciar();
						return true;
					}
					else
					{
						//vs huargos u orcos

						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}
				}
				
				/**
				 * Este bloque de desición valida los movimiento de Hobbit contra: Gandalf,Arwen, Eldarion,Frodo , Nigromante, Saruman, Aragorn y Gandalf, asi mismo a la vez se encarga de 
				 * realizar todas las validaciones de las vidas los ataques al Nigromante, la Espada Narsil en fin todos los movimeintos permitidos para Hobbit en el juego.
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 13){ //  hobbit
					if (Destino.getPieza().getRaza() == 5){ // vs anillo
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar al anillo, te convertiras en un despreciable gollum");
						getCuadroPieza().setPieza(new Gollum(-1));//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						Gollum.setv_Gollum(1);
						
						//Validaciones de la vida de Gollum
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}
					else if (Destino.getPieza().getRaza() == 12){ // vs golum.
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						Gollum.setv_Gollum(-1);
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}

					else if (Destino.getPieza().getRaza() == 15){  //vs nigro
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Upss acabas de atacar el  nigromante, su ficha será destruida");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.destruye_Senuelos();
						Tablero.ubica_Senuelos("Nigromante");
						Tablero.ubica_Senuelos("Anillo");
						Tablero.ubica_Senuelos("Espada");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 17){ // vs saruman
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						JuegoPrincipal.v_Saruman.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 9){
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Has completado tu busqueda, la espada narsil es ahora tuya");
						Destino.setPieza(new Ganador(-1));//Muevo la pieza al cuadro destino
						reiniciar();
						return true;
					}
					else
					{
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}
				}
				/**
				 * Se dan todas las validaciones de los moviemiento de Golum en diagonal, laterla y atrás
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 12){ //  golum
					if (Destino.getPieza().getRaza() == 6){ // vs aragorn
						if (Aragorn.getDiagonal()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Perdiste el movimiento en diagonal");
							Aragorn.setDiagonal(false);
							return true;
						}
						else if (Aragorn.getLateral()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Perdiste el movimiento lateral y hacia atrás");
							Aragorn.setAdelante(true);
							Aragorn.setLateral(false);
							return true;
						}
						else if (Aragorn.getAdelante() ){
							Aragorn.setAdelante(true);
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Aragorn ya no puede perder más poderes");
							return true;
						}

					}
					/**
					 * Validación de Los moviemiento de Arwen y de acuerdo a ellos los poderes que puede perder.
					 */
					else if (Destino.getPieza().getRaza() == 7){ // vs arwen

						if (Arwen.getCaballo()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Arwen pierde su caballo");
							Arwen.setCaballo(false);
							Arwen.setAdelante(true);
							return true;

						}
						else if (Arwen.getAdelante()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Arwen ya no puede perder más poderes");
							return true;
						}    
					}
					
					/**
					 * Validación de los movimientos y poderes de Eldarion
					 */
					else if (Destino.getPieza().getRaza() == 8){  //vs Eldarion
						if (Eldarion.getEldarion() == true){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Heldarion ha sido despojado de sus poderes");
							Eldarion.setEldarion(false);
							Eldarion.setAdelante(true);
						}
						else{
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);
						}

						return true;
					}
					
					/**
					 * Validación de los movimientos, vidas y poderes entre Gollum y Frodo
					 */
					else if (Destino.getPieza().getRaza() == 10){ // vs frodo
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gollum es destruido por frodo");
						getCuadroPieza().setPieza(null);
						Frodo.setM_Gollum(true);
						Gollum.setv_Gollum(-1);
						if(Gollum.getv_Gollum() == 1){
							JuegoPrincipal.v_Gollum.setText("1");
						}
						else if(Gollum.getv_Gollum() == 2){
							JuegoPrincipal.v_Gollum.setText("2");
						}
						else if(Gollum.getv_Gollum() == 3){
							JuegoPrincipal.v_Gollum.setText("3");
						}
						else if(Gollum.getv_Gollum() == 4){
							JuegoPrincipal.v_Gollum.setText("4");
						}
						else if(Gollum.getv_Gollum() == 5){
							JuegoPrincipal.v_Gollum.setText("5");
						}
						else{
							JuegoPrincipal.v_Gollum.setText("Muerto");
						}
						return true;
					}
					/**
					 * Los moviemiento de y poderes de Gandalf son validados en este bloque
					 */
					else if (Destino.getPieza().getRaza() == 11){ // vs gandalf
						if(Gandalf.get_Poder_Saruman()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Perdiste los poderes de Saruman");
							Gandalf.set_Poder_Saruman(false);
							return true;
						}
						else if(Gandalf.get_Movimiento_Diagonal()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Perdiste el movimiento en diagonal");
							Gandalf.set_Movimiento_Diagonal(false);
							return true;
						}
						else if (Gandalf.get_Movimiento_Lateral()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Solo puedes moverte hacia adelante");
							Gandalf.set_Movimiento_Lateral(false);
							return true;
						}
						else{
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Ya no tienes poderes");
							return true;
						}

					}
					else
					{
						// vs Hobbits
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}
				}

				/**
				 * Aqui quedan validados los movimeintos, vidas y poderes del Huargo en contra de las demas fichas entre ellas Aragorn, Arwen, Eldarion, Frodo
				 */
				else if (getCuadroPieza().getPieza().getRaza() == 14){ //  huargo
					if (Destino.getPieza().getRaza() == 6){ // vs aragorn
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);
						if(Gandalf.get_Resucitar()){
							Gandalf.set_Resucitar(false);
							JuegoPrincipal.v_Gandalf.setText("1");
						}
						JuegoPrincipal.v_Aragorn.setText("Muerto");
						JuegoPrincipal.v_Gandalf.setText("Muerto");
						CronometroThread.setDebe_Resucitar(false);
						return true;
					}
					else if (Destino.getPieza().getRaza() == 11){
						if (Gandalf.get_Resucitar()){
							int tiempo = (CronometroThread.getMinutos() * 60) + CronometroThread.getSegundos() + 120;
							CronometroThread.setTiempo_a_resucitar(tiempo);
							CronometroThread.setDebe_Resucitar(true);
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);

							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gandalf ha muerto, en 2 minutos resucitará");
							JuegoPrincipal.v_Gandalf.setText("Resucitando");
							Gandalf.set_Resucitar(false);
							return true;
						}
						else{
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gandalf ha muerto ya que le restaba una sola vida");
							getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
							Destino.setPieza(this);
							JuegoPrincipal.v_Gandalf.setText("Muerto");
							return true;
						}
					}
					else if (Destino.getPieza().getRaza() == 7){ // vs arwen
						if (Arwen.getInmortal() && (Huargo.getmovimento_Caballo() == false) && (Arwen.getCaballo()==true)){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Arwen ha sido despojada de su montura");
							Arwen.setCaballo(false);
							Arwen.setAdelante(true);
							Huargo.setmovimiento_Caballo(true);
							Huargo.setmovimiento_Adelante(false);
							return true;
						}
						else if (Arwen.getInmortal() == false && Arwen.getCaballo() == true && Huargo.getmovimento_Caballo() == false){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Arwen ha sido despojada de su montura");
							Arwen.setCaballo(false);
							Arwen.setAdelante(true);
							Huargo.setmovimiento_Caballo(true);
							Huargo.setmovimiento_Adelante(false);
							return true;
						}
						else if (Arwen.getInmortal()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Arwen es inmortal");
							return true;
						}


						else{
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);
							JuegoPrincipal.v_Arwen.setText("Muerta");
							return true;
						}
					}

					else if (Destino.getPieza().getRaza() == 8){  //vs Eldarion
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);
						JuegoPrincipal.v_Eldarion.setText("Muerta");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 10){ // vs frodo
						if (Frodo.get_vidasDisponibles() == 3){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Frodo ha perdido su Espada");	
							Frodo.set_vidasDisponibles(Frodo.get_vidasDisponibles() -1);
							JuegoPrincipal.v_Frodo.setText("2");
							return true;
						}
						else if (Frodo.get_vidasDisponibles() == 2){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Frodo ha perdido el Mithil");
							Frodo.set_vidasDisponibles(Frodo.get_vidasDisponibles() -1);
							JuegoPrincipal.v_Frodo.setText("1");
							return true;
						}
						else if (Frodo.get_vidasDisponibles() == 1){
							Frodo.set_vidasDisponibles(Frodo.get_vidasDisponibles() -1);
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);
							JuegoPrincipal.v_Frodo.setText("Muerto");
							return true;
						}

					}
					else
					{
						// vs Hobbits
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}
				}
				else if (getCuadroPieza().getPieza().getRaza() == 16){ //  orco
					if (Destino.getPieza().getRaza() == 6){ // vs aragorn
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);
						if(Gandalf.get_Resucitar()){
							Gandalf.set_Resucitar(false);
							JuegoPrincipal.v_Gandalf.setText("1");
						}
						JuegoPrincipal.v_Aragorn.setText("Muerto");
						JuegoPrincipal.v_Gandalf.setText("Muerto");
						CronometroThread.setDebe_Resucitar(false);
						return true;
					}
					else if (Destino.getPieza().getRaza() == 7){ // vs arwen
						if (Arwen.getInmortal()){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Arwen es inmortal");
							return true;
						}

						else{
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);
							JuegoPrincipal.v_Arwen.setText("Muerta");
							return true;
						}
					}

					else if (Destino.getPieza().getRaza() == 8){  //vs Eldarion
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);
						JuegoPrincipal.v_Eldarion.setText("Muerto");
						return true;
					}
					else if (Destino.getPieza().getRaza() == 10){ // vs frodo            			
						if (Frodo.get_vidasDisponibles() == 3){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Frodo ha perdido su Espada");	
							Frodo.set_vidasDisponibles(Frodo.get_vidasDisponibles() -1);
							JuegoPrincipal.v_Frodo.setText("2");
							return true;
						}
						else if (Frodo.get_vidasDisponibles() == 2){
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Frodo ha perdido el Mithril");
							Frodo.set_vidasDisponibles(Frodo.get_vidasDisponibles() -1);
							JuegoPrincipal.v_Frodo.setText("1");
							return true;
						}
						else if (Frodo.get_vidasDisponibles() == 1){
							Frodo.set_vidasDisponibles(Frodo.get_vidasDisponibles() -1);
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);
							JuegoPrincipal.v_Frodo.setText("Muerto");
							return true;
						}
					}
					else if (Destino.getPieza().getRaza() == 11){ // vs gandalf
						if (Gandalf.get_Resucitar()){
							//Se activa la cuenta del cronómetro para resucitarlo en 2 minutos
							int tiempo = (CronometroThread.getMinutos() * 60) + CronometroThread.getSegundos() + 120;
							CronometroThread.setTiempo_a_resucitar(tiempo);
							CronometroThread.setDebe_Resucitar(true);
							getCuadroPieza().setPieza(null);
							Destino.setPieza(this);
							JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gandalf ha muerto, en 2 minutos resucitará");
							JuegoPrincipal.v_Gandalf.setText("Resucitando");
							Gandalf.set_Resucitar(false);
							return true;
						}
						else{
							getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
							Destino.setPieza(this);
							JuegoPrincipal.v_Gandalf.setText("Muerto");
							return true;
						}
					}
					else
					{
						// vs Hobbits
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Destino.setPieza(this);//Muevo la pieza al cuadro destino
						return true;
					}
				}
				else if (getCuadroPieza().getPieza().getRaza() == 17){ //  saruman
					if (Destino.getPieza().getRaza() == 6){ // vs aragorn
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Acabas de ser movido por Saruman a la parte mas baja posible de la mina");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.P_saruman(Destino.getPieza());
						Destino.setPieza(this);
						return true;

					}
					else if (Destino.getPieza().getRaza() == 7){ // vs arwen
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Acabas de ser movido por Saruman a la parte mas baja posible de la mina");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.P_saruman(Destino.getPieza());
						Destino.setPieza(this);
						return true;
					}

					else if (Destino.getPieza().getRaza() == 8){  //vs Eldarion
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Acabas de ser movido por Saruman a la parte mas baja posible de la mina");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.P_saruman(Destino.getPieza());
						Destino.setPieza(this);
						return true;
					}
					else if (Destino.getPieza().getRaza() == 10){ // vs frodo
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Acabas de ser movido por Saruman a la parte mas baja posible de la mina");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.P_saruman(Destino.getPieza());
						Destino.setPieza(this);
						return true;
					}
					else if (Destino.getPieza().getRaza() == 11){ // vs gandalf
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Acabas de ser movido por Saruman a la parte mas baja posible de la mina");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.P_saruman(Destino.getPieza());
						Destino.setPieza(this);
						return true;
					}
					else
					{
						// vs Hobbits
						JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Acabas de ser movido por Saruman a la parte mas baja posible de la mina");
						getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
						Tablero.P_saruman(Destino.getPieza());
						Destino.setPieza(this);
						return true;
					}
				}	
			}

			else{

				if (getCuadroPieza().getPieza().getRaza() == 6 ){ // Si la pieza a mover es aragorn o arwen.        
					Tablero.CrearEldarionAragorn(Destino.getInX(), Destino.getInY());
					//Tablero.CrearEldarion(Destino.getInX(), Destino.getInY());


				}
				else if(getCuadroPieza().getPieza().getRaza() == 7){
					Tablero.CrearEldarionArwen(Destino.getInX(), Destino.getInY());
				}
				else{

				}
			}

			getCuadroPieza().setPieza(null);//Le paso al cuadro donde actualmente esta la pieza el valor de null, que quiere decir que ya no tiene pieza
			Destino.setPieza(this);//Muevo la pieza al cuadro destino
			setFirstmov(false);//El siguiente movimiento, ya no serÃ­a el primero.
			return true;
		} else {
			return false;
		}

	}

	/**
	 *
	 * @return color de la pieza.
	 * Comunidad: 1
	 * Mordor: -1
	 */
	public int getColor() {
		return color;
	}

	/**
	 *
	 * @param color
	 * Comunidad: 1
	 * Mordor: -1
	 */
	public boolean getMuerto() {
		return esta_muerto;  //  true/false
	}

	// establece si el jugador fue aniquilado
	public void setMuerto(boolean esta_muerto) {
		this.esta_muerto = esta_muerto; // el valor puede ser true o false
	}

	// Obtiene el numero de vidas de cada personaj
	public int getVidas() {
		return vidas; // devuelve el numero de vidas restantes
	}

	public void setVidas(int vid) { // Establece el numero de vidas
		this.vidas = vid; // se toma el valor
	}

	// Verifica si el jugador tiene poderes
	public static boolean getPoderes() {
		return tiene_poderes; // Devuelve si su poder esta activado o desactivado
	}

	// Establece el jugador tiene o no poderes
	public static void setPoderes(boolean tiene_poder) {
		tiene_poderes = tiene_poder; //se le asigna el poder 
	}
	public void setColor(int color) {
		this.color = color;
	}

	public void setRaza(int raz) {raza=raz;}
	public int getRaza(){return raza;}

	/**
	 * Retorna el primer movimiento
	 * @return the firstmov
	 */
	public boolean isFirstmov() {
		return firstmov;
	}

	/**
	 * Retorna el primer moviemiento obtenido
	 * @param firstmov the firstmov to set
	 */
	public void setFirstmov(boolean firstmov) {
		this.firstmov = firstmov;
	}


	public ImageIcon getImagenPieza() {
		return imagenPieza;
	}

	/**
	 * @param imagenPieza la imagenPieza que se obtiene
	 */
	public void setImagenPieza(ImageIcon imagenPieza) {
		this.imagenPieza = imagenPieza;
	}

	/**
	 * @return El cuadroPieza
	 */
	public CuadroPieza getCuadroPieza() {
		return cuadroPieza;
	}

	/**
	 * @param cuadroPieza el cuadroPieza que se obtiene
	 */
	public void setCuadroPieza(CuadroPieza cuadroPieza) {
		this.cuadroPieza = cuadroPieza;
	}
	
	/**
	 * Mediante esta funcón se emplea la funcionalidad de reiniciar todo el sistem sus variables, movimientos, en fin absolutamente todo 
	 * se pone desde cero, para poder dar inicio a una nueva partida es,
	 * utilizada cada vez que el ususario desea jugar una nueva partida.
	 */
	public static void reiniciar(){
		Cronometro.stopActionPerformed();
		CronometroAlianza.stopActionPerformed();
		int Eleccion =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "¿Desea volver a iniciar la partida?", "Confirmacion", JOptionPane.YES_NO_OPTION);
		if (Eleccion == 0){
			JuegoPrincipal.frame.dispose();
			JuegoPrincipal.tablero1=new Juego.Tablero(); 
			JuegoPrincipal.a_h_turno.setText("Comunidad");
			Tablero.setTurno(Tablero.getTurno()*-1);

			try {
				//Inicializacion de cronómetro
				Cronometro.stopActionPerformed();
				CronometroAlianza.stopActionPerformed();
				JuegoPrincipal.initialize();//Inicializa contodas las varaibles del juego principal
				Tablero.setTurno(Tablero.getTurno()*-1);
				//Validación de la vidas , movimientos y posición de Aragorn y Arwen.
				Aragorn.vidas="1";
				Aragorn.movimiento_Diagonal  = true;
				Aragorn.movimiento_Lateral  = true;
				Aragorn.movimiento_Adelante = false;
				Aragorn.posicion_y = 7;
				Tablero.setProcrear(true);
				Arwen.Inmortalidad = true;
				Arwen.movimiento_Caballo = true;
				Arwen.movimiento_Adelante = false;
				
				//Validación del Movimiento de Eldarion
				Eldarion.movimiento_Adelante = false;
				Eldarion.movimiento_Eldarion = true;
				
				//Movimeinto de Frodo, vidas disponobles
				Frodo.movimiento_Gollum = false;
				Frodo.vidas_Disponibles = 3; // Si este atributo es 3, tiene la espada, el mithril y su vida propia
				Frodo.movimiento_Hobbit = true;
				
				//Valida los movimientos de Gandalg en diagonal, lateral, adelante y resucitarlo.
				Gandalf.movimiento_Saruman = false;
				Gandalf.movimiento_Diagonal = true;
				Gandalf.movimiento_Lateral = true;
				Gandalf.movimiento_Adelante = true;
				Gandalf.resucitar = true;
				
				//Valida los movientosd e Huargo en forma de caballo, adelante, las vidas de Frodo, Gandalf, Arwen, Aragorn
				Huargo.movimiento_Caballo = false;
				Huargo.movimiento_Adelante = true;
				JuegoPrincipal.v_Frodo.setText("3");
				JuegoPrincipal.v_Eldarion.setText("No creado");
				JuegoPrincipal.v_Gandalf.setText("2");
				JuegoPrincipal.v_Arwen.setText("Inmortal");
				JuegoPrincipal.v_Aragorn.setText("1");
				Gollum.vidas = 1;

			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
		else{
			JuegoPrincipal.a_h_turno.setText("Finalizado");
			Tablero.setTurno(0);
		}



	}
}