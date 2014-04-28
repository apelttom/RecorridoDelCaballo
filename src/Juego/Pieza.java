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

import Piezas.Caballo_1;
import Piezas.Caballo_2;

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
	public boolean validarMovimiento(CuadroPieza Destino, Board_Horses tbl) {
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
	public boolean MoverPieza(CuadroPieza Destino, Board_Horses tbl) {
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

			if (Destino.getPieza() != null) {//si hay una pieza en donde se va a mover
				
			}

			else{

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
		Cronometro.stopActionPerformed();		
		//CronometroAlianza.stopActionPerformed();
		int Eleccion =JOptionPane.showConfirmDialog(JuegoPrincipal.table, "¿Desea volver a iniciar la partida?", "Confirmacion", JOptionPane.YES_NO_OPTION);
		if (Eleccion == 0){
			JuegoPrincipal.frame.dispose();
			JuegoPrincipal.table=new Juego.Board_Horses(); 			
			Board_Horses.setTurno(Board_Horses.getTurno()*-1);

			try {
				//Inicializacion de cronómetro
				Cronometro.stopActionPerformed();
				Cronometro.stopActionPerformed();
				//CronometroAlianza.stopActionPerformed();
				JuegoPrincipal.initialize();//Inicializa contodas las varaibles del juego principal
				Board_Horses.setTurno(Board_Horses.getTurno()*-1);


			} catch (LineUnavailableException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
		}
		else{			
			Board_Horses.setTurno(0);
		}



	}
}