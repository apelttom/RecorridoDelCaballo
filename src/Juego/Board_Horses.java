

package Juego;


import Piezas.Caballo_1;
import Piezas.Caballo_2;





import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import javax.swing.JPanel;

/**
 * Esta clase es la principal del Tablero, extiende de JPanel
 * */

public class Board_Horses extends JPanel {

	public static int Fila_Caballo_1 = 0, Fila_Caballo_2 = 0, Columna_Caballo_1 = 0, Columna_Caballo_2 = 0;
	/*
	 * Se crea un entero global con el cual se trabajará la parametrización el tablero.
	 */

	public static int parametro;

	/*
	 * Especifica el turno que le toca jugar, siempre empieza con la comunidad.
	 */
	public static int turno = 1;//1 para la Comunidad  1 y -1 para Mordor.
	/*
	 * Es el array principal que diseña el tablero, es un conjunto de cuadrosPiezas. 
	 */
	static CuadroPieza board_horses[][];
	/*
	 * Estoy arreglos sirven para realizar el terremoto.
	 */
	private ArrayList<Pieza> piezasComidas = new ArrayList<Pieza>();

	private  static Stack<Pieza> terremoto = new Stack<Pieza>();

	/*
	 * Especifica el color de los cuadros del tablero "negros"
	 */
	private static Color Cafe_Oscuro = new Color(139,69,19);
	/*
	 * Especifica el color de los cuadros del tablero "blancos"
	 */
	private static     Color Cafe_Claro = new Color(222,184,135);
	/*
	 * Cuando un cuadro es resaltado, y es blanco se usará este color para diferenciarlo de los demás.
	 */
	private static    Color ClaroResaltado = new Color(80, 240, 0, 255);
	/*
	 * Cuando un cuadro es resaltado y es negro, se usará este color para diferenciarlo de los demás.
	 */
	private static Color OscuroResaltado = new Color(87, 167, 26, 142);
	/*
	 * Cuando selecciono alguna pieza el cuadro se colorea de este color.
	 */
	private static Color Seleccionado = Color.LIGHT_GRAY;
	/*
	 * Cuando quiero resaltar un color, como alerta(Especialmente cuando la pieza puede comer a otra pieza, uso este color)
	 */
	private static Color Alerta = Color.RED;
	/*
	 * Se define este booleano para verificar las alternativas.
	 */

	private static boolean seleccionarAlternativas = true;
	/*
	 * Este booleano se usa para saber si Eldarion ha sido procreado o no.
	 */
	public static boolean procrear = true;

	/*Se crea un Jlabel temporal con el nombre tmp utilizado por algunos métodos.
	 */
	static JLabel tmp;
	/*
	 * Indica el tamaño de cada cuadrado del tablero.
	 */
	static int altoCuadro = 50;
	static int anchoCuadro = 50;
	/*
	 * Indica el cuadro que esta seleccionado actualmente.
	 */
	static CuadroPieza cuadroSeleccionado = new CuadroPieza(0, 0);

	//Se crean estas variables globales enteras para uso diversificado, más que todo en ubica_Senuelos.
	public static int posicion_Nigromante;
	public static int posicion_Espada;
	public static int posicion_Anillo;
	

	/*
	 * Constructor de Tablero, en el se inicializa, se llena con las fichas y se pinta el tablero de juego.
	 */
	public Board_Horses() {
		super();
		llenarCuadrosTablero();
		ordenarTablero();
		init();// para darle eventos mouse
	}
	
	
	/**
	 * Este metodo crea el tablero, quiere decir que grafica el tablero en el Jpanel.
	 */

	private void llenarCuadrosTablero() {
		tmp = new JLabel();
		tmp.setForeground(new Color(0, 0, 0));
		tmp.setBounds(-131, -131, 87, 87);
		add(tmp);
		board_horses = new CuadroPieza[parametro][parametro];
		setLayout(null);
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				board_horses[x][y] = new CuadroPieza(x, y);
				add(board_horses[x][y]);// Para agregar los elementos por iteración.
			}
		}
		rePintarTablero();
	}
	/**
	 * Inicializo todos los listener de cada pieza.
	 */

	public void init() {//lo que es evento con el Mouse
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				board_horses[x][y].addMouseListener(new java.awt.event.MouseAdapter() {

					public void mousePressed(java.awt.event.MouseEvent evt) {
						seleccionarPieza(evt);//cuando mouse se levantado o presionado haga esto
					}

					public void mouseReleased(java.awt.event.MouseEvent evt) {
						dejarPieza(evt);
					}
				});
				board_horses[x][y].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

					public void mouseDragged(java.awt.event.MouseEvent evt) {
						/*
						 * Para simular que la pieza es arrastrada, uso el label tmp, y le cambio sus coordenadas, con la del mouse.
						 */
						tmp.setLocation(cuadroSeleccionado.getLocation().x + evt.getX() - 18, cuadroSeleccionado.getLocation().y + evt.getY() - 28);
					}
				});


			}
		}
	}
	/**
	 * Usado para seleccionar la pieza, es llamada gracias al evento de clic del raton.
	 * @param evt
	 * 
	 */
	public void seleccionarPieza(java.awt.event.MouseEvent evt) {
		// Se pasa como parametro el evento del mouse.
		CuadroPieza t = ((CuadroPieza) evt.getComponent());//Averiguo en que cuadro sucedió el evento.
		if (t.getPieza() != null) {//Veo si el cuadro no esta vacío.
			if (t.getPieza().getColor() == getTurno()) {//Veo si es del mismo color del turno que actualmente le toca.
				cuadroSeleccionado = t;
				/*
				 * Con esto hago que se resalten los posibles movimientos en todo el tablero.
				 */
				for (int x = 0; x < parametro; x++) {
					for (int y = 0; y < parametro; y++) {
						board_horses[x][y].opacarPieza();//Si hay piezas seleccionadas, las opaco.
						if (isSeleccionarAlternativas()) {//Resalto los posibles movimientos.
							if (cuadroSeleccionado.getPieza().validarMovimiento(board_horses[x][y], this)) {
								board_horses[x][y].resaltarPieza(board_horses[x][y].getPieza() != null ? getAlerta() : null);
							}
						}
					}
				}
				/*
				 * Resalto el cuadro que ha sido seleccionado para que el usuario sepa que cuadro seleccionó.
				 */
				cuadroSeleccionado.resaltarPieza(getSeleccionado());
				/*
				 * Establezco la imagen de la pieza que ha sido seleccionada al label.
				 */
				tmp.setIcon(cuadroSeleccionado.getPieza().getImagenPieza());
				/*
				 * Borro la imagen de la pieza del cuadro.
				 */
				cuadroSeleccionado.lbl.setIcon(null);
				/*
				 * Establezco la nueva posición del label, que tiene la imagen de la pieza.
				 */
				tmp.setLocation(cuadroSeleccionado.getLocation().x + evt.getX() - 18, cuadroSeleccionado.getLocation().y + evt.getY() - 28);
			}
		}
	}
	/*
	 * Es el método que permite soltar una pieza en determinado lugar.
	 */
	public void dejarPieza(java.awt.event.MouseEvent evt) {
		if (cuadroSeleccionado.getPieza() != null) {//Si hay alguna pieza seleccionada
			tmp.setLocation(-100, -100);//Pongo el label tmp, por donde no se vea para que no estorbe.
			cuadroSeleccionado.lbl.setIcon(cuadroSeleccionado.getPieza().getImagenPieza());//Regreso el ícono de la pieza al cuadro que había sido seleccionado.
			tmp.setIcon(null);//Borro el icono del label.
			CuadroPieza cuadroDestino = (CuadroPieza) this.getComponentAt(evt.getComponent().getX() + evt.getX(), evt.getComponent().getY() + evt.getY());
			if (cuadroDestino != cuadroSeleccionado) {//Pregunto si el cuadro destino es diferente del cuadro seleccionado.
				MoverPieza(cuadroSeleccionado, cuadroDestino);//Muevo la pieza, o al menos si es válido, el metodo decide eso.
			}

		}


	}

	/*
	 * Se usa para cambiar el turno si el jugador que le toca mover no ha hecho su jugada después de 30 segundos.
	 */
	public static void cambiaTurno(){
		setTurno(getTurno()*-1);

	}
	/*
	 * Retorna el estado del booleano procrear usado para el nacimiento de Eldarion.
	 */
	public static boolean cambiaProcear(){
		return procrear;
	}
	/*
	 * Especifica el set del booleano procrear con el cual se pone a Eldarion como procreado.
	 */
	public static void setProcrear(boolean procrear2){
		procrear = procrear2;
	}



	/*
	 * Este metodo es el que mueve la pieza a un determinado lugar.
	 */
	public void MoverPieza(CuadroPieza cuadroActual, CuadroPieza cuadroDestino) {
		/**
		 * Precondiciones: Se Necesita mover una Pieza de un lugar a otro.
		 * Entradas: El cuadro proveniente de la pieza y el cuadro al cuál se quiere mover.
		 * Salidas: Depende de la evaluación de la función.
		 * Postcondiciones: Si la validación es correcta, se mueve la pieza de la manera en que el usuario lo solicitó.
		 * */
		try {
			if (cuadroActual.getPieza().MoverPieza(cuadroDestino, this)) {//Si el movimiento es válido.

				for (int x = 0; x < parametro; x++) {
					for (int y = 0; y < parametro; y++) {
						board_horses[x][y].opacarPieza();//Regreso todos los cuadros a su estado inicial, para que no esten resaltados.
					}
				}
				if (getTurno() == -1){					
					//CronometroThreadAlianza.segundos = -1;
				}
				else{					
					//CronometroThreadAlianza.segundos = -1;
				}

				setTurno(getTurno() * -1);//Cambio de turno al hacer una jugada válida.
			} 
		} catch (Exception e) {

		}
	}


	/**
	 * Este método ordena todo el tablero, quiere decir que pone las piezas en su sitio.(Resetea el juego).
	 */
	public void ordenarTablero() {    	
		Posiciones_Aleatorias Pos_Caballos = new Posiciones_Aleatorias(0,(parametro-1)); // Se crea una instancia de números aleatorios para la inserción de los señuelos.
		//int Fila_Caballo_1 = 0, Fila_Caballo_2 = 0, Columna_Caballo_1 = 0, Columna_Caballo_2 = 0;

		setTurno(1); // Se empieza con el turno de Comunidad.

		//Limpiar el tablero según el parámetro, el cual es el tamaño de la dimensión del tablero, correspondiente.
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				board_horses[x][y].setPieza(null);
			}
		}


		// Generar los aleatorios de las filas y columnas para ambos caballos 
		Fila_Caballo_1 = Pos_Caballos.generar_numero_aleatorio();
		Fila_Caballo_2 = Pos_Caballos.generar_numero_aleatorio();
		Columna_Caballo_1 = Pos_Caballos.generar_numero_aleatorio();
		Columna_Caballo_2 = Pos_Caballos.generar_numero_aleatorio();


		/**
		 * Mediante este for y colocamos ambos caballo en el tablero de forma aleatoria.
		 */
		for (int i = 0; i < 1; i++) {	
			board_horses[Fila_Caballo_1][Columna_Caballo_1].setPieza(new Caballo_1(0));//asignación aleatoria del caballo 1 en el tablero
			board_horses[Fila_Caballo_2][Columna_Caballo_2].setPieza(new Caballo_2(0));//asignación aleatoria del caballo 2 en el tablero
		}
		


		rePintarTablero(); // Se llama al método repintar tablero que básicamente como su nombre lo indica reposiciona en el tablero las imagenes, su ícono.
	}


	/**
     * Este método pretende repintar el tablero al inicializar las piezas y tiene otros usos comunes pero destinados al mismo fin, 
     * refrescar el tablero cuando lo necesitemos y movamos piezas.
     */
	public static void rePintarTablero() {
		int colorArriba = -1;
		//Mediante estos for recorremos toda la matriz.
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				if (x % 2 == 0) {
					board_horses[x][y].setFondo(y % 2 == 1 ? getOscuro() : getClaro());
					board_horses[x][y].setResaltar(y % 2 == 1 ? getOscuroResaltado() : getClaroResaltado());
				} else {
					board_horses[x][y].setFondo(y % 2 == 0 ? getOscuro() : getClaro());
					board_horses[x][y].setResaltar(y % 2 == 0 ? getOscuroResaltado() : getClaroResaltado());
				}
				board_horses[x][y].setBounds(anchoCuadro * (colorArriba == -1 ? x : (7 - x)), altoCuadro * (colorArriba == -1 ? y : (7 - y)), anchoCuadro, altoCuadro);
			}
		}
	}

	/**
     * Este método pretende hacer un get del cuadro que seleccionó el usuario.
     */
	public CuadroPieza getPiezaSeleccionada() {
		return cuadroSeleccionado;
	}

	/**
     * Este método pretende hacer una instancia del tablero actual de juego.
     */
	public CuadroPieza[][] getTablero() {
		return board_horses;
	}
	
	/**
     * Este método pretende hacer un get turno actual.
     */

	public static int getTurno() {
		return turno;
	}

	/**
     * Este método pretende hacer un set turno actual.
     */
	public static void setTurno(int JTurno) {
		turno = JTurno;
	}

	/**
     * Este método pretende retornar el booleano seleccionarAlternativas para saber si la ficha a mover tiene  o no
     * movimientos válidos.
     */
	public static boolean isSeleccionarAlternativas() {
		return seleccionarAlternativas;
	}

	/**
     * Este método pretende setear el booleano seleccionarAlternativas para indicar si la ficha a mover tiene o no
     * movimientos válidos.
     */
	public void setSeleccionarAlternativas(boolean seleccionarAlternativas) {
		this.seleccionarAlternativas = seleccionarAlternativas;
	}

	/**
	 * Retorna el Color negro para el tablero.
	 */
	 public static Color getOscuro() {
		 return Cafe_Oscuro;
	 }

	 /**
	  * Setea el Color negro para el tablero.
	  */
	 public void setOscuro(Color CafeOscuro) {
		 this.Cafe_Oscuro = CafeOscuro;
	 }

	 /**
	* Retorna el Color blanco para el tablero.
	* */
	 
	 public static Color getClaro() {
		 return Cafe_Claro;
	 }

	 /**
	  * Setea el Color negro para el tablero.
	  */
	 public void setClaro(Color CafeClaro) {
		 this.Cafe_Claro = CafeClaro;
	 }

	 /**
	  * Retorna el Color para la casilla blanca cuando esta resaltada en el tablero.
	  */
	 public static Color getClaroResaltado() {
		 return ClaroResaltado;
	 }

	 /**
	  * Setea el Color para la casilla blanca cuando esta resaltada en el tablero.
	  */
	 public void setClaroResaltado(Color Cafe_Claro_Resaltado) {
		 this.ClaroResaltado = Cafe_Claro_Resaltado;
	 }

	 /**
	  * Retorna el Color para la casilla negro cuando esta resaltada en el tablero.
	  */
	 public static Color getOscuroResaltado() {
		 return OscuroResaltado;
	 }

	 /**
	  * Setea el Color para la casilla blanca cuando esta resaltada en el tablero.
	  */
	 public void setOscuroResaltado(Color Cafe_Oscuro_Resaltado) {
		 this.OscuroResaltado = Cafe_Oscuro_Resaltado;
	 }

	 /**
	  * Retorna el color seleccionado por el usuario.(De la pieza.)
	  */
	 public static Color getSeleccionado() {
		 return Seleccionado;
	 }

	 /**
	  * Setea el color seleccionado por el usuario.(De la pieza.)
	  */
	 public void setSeleccionado(Color Seleccionado) {
		 this.Seleccionado = Seleccionado;
	 }

	 /**
	  * Setea el color de alerta para la ficha que está disponible de comer (Resalta una opción válida).
	  */
	 public static Color getAlerta() {
		 return Alerta;
	 }

	 /**
	  * Setea el color de alerta para la ficha que está disponible de comer.
	  */
	 public void setAlerta(Color Alerta) {
		 this.Alerta = Alerta;
	 }


	//@autor Oswaldo Dávila.
	// 5/4/2013.
	// Modificaciones el 12/4/13
	 /**
	  * Precondiciones: Deben de haber pasado 10 minutos (600 segundos en evaluación) para que se pueda hacer efectivo el terremoto.
	  * Entradas: Ninguna en sí pero se generan números aleatorios a lo interno de la función.
	  * Salidas: Un mensaje que comunica al usuario que ha ocurrido un terremoto.
	  * Postcondiciones: Se desordenan las fichas en forma aleatorio en el tablero.
	  */
	 public static void ocurreTerremoto(){
		 Posiciones_Aleatorias ocurre = new Posiciones_Aleatorias(0,1); // Genera aleatorios con un 50% de probabilidad de que haya un terremoto.
		 if(ocurre.generar_numero_aleatorio() == 1){
			// JOptionPane.showMessageDialog(JuegoPrincipal.table, "Al parecer Minas Tirith esta sufriendo un terremoto");	 // Informe de terremoto.
			 Terremoto(0); //Ejecuto el terremoto.
		 }
	 }

	 public static void Terremoto(int posicion_y){ // Se recibe La fila inicial de la ejecución del terremoto, en vida real el epicentro que será la fila 0.
		 if (posicion_y < parametro){ // Se verifica que sea menor que el tamaño total del tablero.
			 //Se generan números aleatorios.
			 Posiciones_Aleatorias pieza = new Posiciones_Aleatorias(0, parametro-1);
			 Posiciones_Aleatorias nula = new Posiciones_Aleatorias(0, parametro-1);
			 int posicion_pieza = pieza.generar_numero_aleatorio();
			 int posicion_nula = nula.generar_numero_aleatorio();
			 //Se insertan los parámetros en la pila.
			 for(int d = 0; d < parametro-1; d++){
				 if (board_horses[d][posicion_y].getPieza() != null){
					 terremoto.push(board_horses[d][posicion_y].getPieza());
					 board_horses[d][posicion_y].setPieza(null);
				 }
			 }
			 while(!terremoto.isEmpty()){ // Si la pila no es vacía.
				 if (posicion_pieza != -1){
					 if (board_horses[posicion_pieza][posicion_y].getPieza() == null){
						 board_horses[posicion_pieza][posicion_y].setPieza(terremoto.pop());
					 }
					 else{
						 posicion_pieza = pieza.generar_numero_aleatorio(); //Genera otro número aleatorio si fuera el caso de que la posición este ocupada.
					 }
				 }
				 else{
					 break;
				 }

			 }
			 Terremoto(posicion_y+1); //Hace las recursiones necesarias para recorrer todas las filas.
		 }

	 }

 

}



