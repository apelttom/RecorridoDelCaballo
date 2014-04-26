
//Se realizan los import necesarios de piezas y otras librerias, así como el package a juego.
package Juego;
//@autores Daniel Chaves Coto, Deiby Diaz y Oswaldo Dávila.
// Se iniciaron comentarios de drag and drop el 25/4/2013.
// Se inició con al día siguiente con recopilación y retroalimentación para llevar a cabo el proyecto.

import Piezas.Huargo;
import Piezas.Hobbit;
import Piezas.Orco;
import Piezas.Eldarion;
import Piezas.Aragorn;
import Piezas.Arwen;
import Piezas.Saruman;
import Piezas.Frodo;
import Piezas.Gandalf;
import Piezas.Nigromante;
import Piezas.Espada;
import Piezas.Anillo;
import Piezas.Gollum;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JLabel;
import javax.swing.JOptionPane;


import javax.swing.JPanel;

/**
 * Esta clase es la principal del Tablero, extiende de JPanel
 * */
public class Tablero extends JPanel {
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
	static CuadroPieza tablero[][];
	/*
	 * Estoy arreglos sirven para realizar el terremoto.
	 */
	private ArrayList<Pieza> piezasComidas = new ArrayList<Pieza>();

	private  static Stack<Pieza> terremoto = new Stack<Pieza>();

	/*
	 * Especifica el color de los cuadros del tablero "negros"
	 */
	private static Color negro = new Color(0,0,0);
	/*
	 * Especifica el color de los cuadros del tablero "blancos"
	 */
	private static     Color blanco = new Color(255, 255, 255, 255);
	/*
	 * Cuando un cuadro es resaltado, y es blanco se usará este color para diferenciarlo de los demás.
	 */
	private static    Color blancoResaltado = new Color(80, 240, 0, 255);
	/*
	 * Cuando un cuadro es resaltado y es negro, se usará este color para diferenciarlo de los demás.
	 */
	private static Color negroResaltado = new Color(87, 167, 26, 142);
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
	public Tablero() {
		super();
		llenarCuadrosTablero();
		ordenarTablero();
		init();// para darle eventos mouse
	}
	/**
	 * Este metodo crea el tablero, quiere decir que grafica el tablero en el Jpanel.
	 */
	//@autor Daniel Chaves Coto.
	// 27/3/2013.
	// Modificaciones el 4/4/13
	private void llenarCuadrosTablero() {
		tmp = new JLabel();
		tmp.setBounds(-131, -131, 87, 87);
		add(tmp);
		tablero = new CuadroPieza[parametro][parametro];
		setLayout(null);
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				tablero[x][y] = new CuadroPieza(x, y);
				add(tablero[x][y]);// Para agregar los elementos por iteración.
			}
		}
		rePintarTablero();
	}
	/**
	 * Inicializo todos los listener de cada pieza.
	 */
	//@autor Daniel Chaves Coto.
	// 26/3/2013.
	// Modificaciones el 6/4/13
	public void init() {//lo que es evento con el Mouse
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				tablero[x][y].addMouseListener(new java.awt.event.MouseAdapter() {

					public void mousePressed(java.awt.event.MouseEvent evt) {
						seleccionarPieza(evt);//cuando mouse se levantado o presionado haga esto
					}

					public void mouseReleased(java.awt.event.MouseEvent evt) {
						dejarPieza(evt);
					}
				});
				tablero[x][y].addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {

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
	//@autor Daniel Chaves Coto.
	// 27/3/2013.
	// Modificaciones el 4/4/13
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
						tablero[x][y].opacarPieza();//Si hay piezas seleccionadas, las opaco.
						if (isSeleccionarAlternativas()) {//Resalto los posibles movimientos.
							if (cuadroSeleccionado.getPieza().validarMovimiento(tablero[x][y], this)) {
								tablero[x][y].resaltarPieza(tablero[x][y].getPieza() != null ? getAlerta() : null);
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
	//@autor Daniel Chaves Coto.
	// 29/3/2013.
	// Modificaciones el 4/4/13
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
	//@autor Oswaldo Dávila.
	// 10/4/2013.

	public static void cambiaTurno(){
		setTurno(getTurno()*-1);

	}
	/*
	 * Retorna el estado del booleano procrear usado para el nacimiento de Eldarion.
	 */
	//@autor Daniel Chaves Coto.
	// 8/4/2013.
	// Modificaciones el 9/4/13
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
		//@autor Daniel Chaves Coto.
		 // 27/3/2013.
		 // Modificaciones el 5/4/13
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
						tablero[x][y].opacarPieza();//Regreso todos los cuadros a su estado inicial, para que no esten resaltados.
					}
				}
				if (getTurno() == -1){
					JuegoPrincipal.a_h_turno.setText("Comunidad");

					CronometroThreadAlianza.segundos = -1;
				}
				else{
					JuegoPrincipal.a_h_turno.setText("Mordor");
					CronometroThreadAlianza.segundos = -1;
				}

				setTurno(getTurno() * -1);//Cambio de turno al hacer una jugada válida.
			} 
		} catch (Exception e) {

		}
	}

	//@autor Daniel Chaves.
	// 10/4/2013.
	// Modificaciones el 12/4/13 a cargo de Deiby Diaz por discusión de soluciones.
	/**
	 * Este método ordena todo el tablero, quiere decir que pone las piezas en su sitio.(Resetea el juego).
	 */
	public void ordenarTablero() {    	
		Posiciones_Aleatorias P_senuelo = new Posiciones_Aleatorias(0,7); // Se crea una instancia de números aleatorios para la inserción de los señuelos.
		int o = 0, p = 0, q=0, r = 0, s = 0, t = 0, u = 0, v = 0 ;

		setTurno(1); // Se empieza con el turno de Comunidad.

		//Limpiar el tablero según el parámetro correspondiente.
		for (int x = 0; x < parametro; x++) {
			for (int y = 0; y < parametro; y++) {
				tablero[x][y].setPieza(null);
			}
		}


		// Generar los aleatorios
		o = P_senuelo.generar();
		p = P_senuelo.generar();
		q = P_senuelo.generar();
		r = P_senuelo.generar();
		s = P_senuelo.generar();
		t = P_senuelo.generar();
		u = P_senuelo.generar();
		v = P_senuelo.generar();

		// Se le asigan las variables a los señuelos.
		posicion_Nigromante = o;
		posicion_Espada = p;
		posicion_Anillo = q;

		int color = -1; // Se define el bando al cual va a ser instanciado.
		/**
		 * Mediante estos for y bloques de decisión agregamos las piezas al tablero para los personajes de Mordor.
		 */
		for (int i = 0; i < 1; i++) {	
			tablero[o][0].setPieza(new Nigromante(color));
			tablero[p][0].setPieza(new Espada(color));
			tablero[q][0].setPieza(new Anillo(color));
			tablero[r][0].setPieza(new Saruman(color));
			tablero[s][0].setPieza(new Huargo(color));
			tablero[t][0].setPieza(new Gollum(color));
			tablero[u][0].setPieza(new Orco(color));
			tablero[v][0].setPieza(new Huargo(color));

			for (int y=1; y<=2; y++){
				for (int j = 0; j < parametro; j++) { // Se colocan orcos según lo permita el tablero.
					tablero[j][y].setPieza(new Orco(color));
				}
			}

			if (parametro  == 9){
				tablero[8][0].setPieza(new Orco(color));
			}
			else if (parametro  == 10){
				tablero[8][0].setPieza(new Orco(color));
				tablero[9][0].setPieza(new Orco(color));
			}
			else if (parametro  == 11){
				tablero[8][0].setPieza(new Orco(color));
				tablero[9][0].setPieza(new Orco(color));
				tablero[10][0].setPieza(new Orco(color));
			}
			else if (parametro  == 12){
				tablero[8][0].setPieza(new Orco(color));
				tablero[9][0].setPieza(new Orco(color));
				tablero[10][0].setPieza(new Orco(color));
				tablero[11][0].setPieza(new Orco(color));
			}

		}

		int color2 = 1;// Se define el bando al cual va a ser instanciado.
		/**
		 * Mediante estos for y bloques de decisión agregamos las piezas al tablero para los personajes de la Comunidad.
		 */
		for (int i = 1; i <= 1; i++) {
			tablero[0][i* parametro-1].setPieza(new Hobbit(color2));
			tablero[1][i * parametro-1].setPieza(new Hobbit(color2));
			tablero[2][i * parametro-1].setPieza(new Arwen(color2));
			tablero[3][i * parametro-1].setPieza(new Gandalf(color2));
			tablero[4][i * parametro-1].setPieza(new Frodo(color2));
			tablero[5][i * parametro-1].setPieza(new Aragorn(color2));
			tablero[6][i * parametro-1].setPieza(new Hobbit(color2));
			tablero[7][i* parametro-1].setPieza(new Hobbit(color2));
			for (int j = 0; j < parametro; j++) { // Se colocan Hobbits según lo permita el tablero.
				tablero[j][parametro-2].setPieza(new Hobbit(color2));
			}
		}
		if (parametro  == 9){
			tablero[8][parametro-1].setPieza(new Hobbit(color2));
		}
		else if (parametro  == 10){
			tablero[8][parametro-1].setPieza(new Hobbit(color2));
			tablero[9][parametro-1].setPieza(new Hobbit(color2));
		}
		else if (parametro  == 11){
			tablero[8][parametro-1].setPieza(new Hobbit(color2));
			tablero[9][parametro-1].setPieza(new Hobbit(color2));
			tablero[10][parametro-1].setPieza(new Hobbit(color2));
		}
		else if (parametro  == 12){
			tablero[8][parametro-1].setPieza(new Hobbit(color2));
			tablero[9][parametro-1].setPieza(new Hobbit(color2));
			tablero[10][parametro-1].setPieza(new Hobbit(color2));
			tablero[11][parametro-1].setPieza(new Hobbit(color2));
		}

		rePintarTablero(); // Se llama al método repintar tablero que básicamente como su nombre lo indica reposiciona en el tablero las imagenes, su ícono.
	}

	//@autor Destruye_Senuelos() y ubica_Senuelos() le pertenecen a Oswaldo Dávila y Daniel Chaves Coto por trabajo en equipo.
	// 9/4/2013.
	/**
	 * Este método pretende destruir los señuelos para así a la hora que sean atacados
	 * facilitar su posicionamiento aleatorio en el tablero.
	 */
	public static void destruye_Senuelos(){
		tablero[posicion_Nigromante][0].setPieza(null);
		tablero[posicion_Anillo][0].setPieza(null);
		tablero[posicion_Espada][0].setPieza(null);
	}


	/**
	 * Este método pretende incorporar los señuelos destruidos por destruye_Señuelos al tablero.
	 * Precondiciones: Al recibir un string del nombre del señuelo tiene que colocar esa ficha en un número aleatorio de las casillas de la fila superior.
	 * Entradas: Recibe el string de la pieza a colocar.
	 * Salidas: Se coloca la pieza en un número aleatorio correspondiente a una casilla vacía de la fila superior de la mina.
	 * Postcondiciones: La ficha tiene que ser colocada de manera correcta.
	 */
	public static void ubica_Senuelos(String pieza_Nueva){
		Posiciones_Aleatorias Posiciones_Nuevas = new Posiciones_Aleatorias(0,7);
		int w = Posiciones_Nuevas.generar();
		for (int k = 0; k < parametro; k++){
			if (pieza_Nueva.equals("Nigromante") && tablero[w][0].getPieza() == null){
				tablero[w][0].setPieza(new Nigromante(-1));
				posicion_Nigromante = w;
				break;
			}
			else if(pieza_Nueva.equals("Anillo") && tablero[w][0].getPieza() == null){
				tablero[w][0].setPieza(new Anillo(-1));
				posicion_Anillo = w;
				break;

			}
			else if (pieza_Nueva.equals("Espada") && tablero[w][0].getPieza() == null){
				tablero[w][0].setPieza(new Espada(-1));
				posicion_Espada = w;
				break;

			}
			else{ // Si la posición está ocupada, no se puede poner la ficha por lo tanto se genera otro número aleatorio.
				w = Posiciones_Nuevas.generar();
			}
		}
	}

	//@autor Deiby Diaz.
	// 5/4/2013.
	/**
     * Este método pretende ubicar una Gandalf una vez que ha sido eliminado por algún contrincante.
     * Precondiciones: Gandalf ha muerto.
     * Postcondiciones: Colocar a Gandalf de manera correcta en la posición de Aragorn.
     */
	public static void ubica_Gandalf(){
		Posiciones_Aleatorias posicion_Gandalf = new Posiciones_Aleatorias(0,7);
		int posicion_g = posicion_Gandalf.generar();
		while(true){
			if (tablero[posicion_g][Aragorn.getPosicion_y()].getPieza() == null){
				tablero[posicion_g][Aragorn.getPosicion_y()].setPieza(new Gandalf(1));
				JuegoPrincipal.v_Gandalf.setText("1");
				Gandalf.set_Resucitar(false);
				JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Gandalf ha resucitado");
				break;
			}
			else{
				posicion_g = posicion_Gandalf.generar(); // Si la posición está ocupada, no se puede poner la ficha por lo tanto se genera otro número aleatorio.
			}
		}
	}

	//@autor Oswaldo Dávila.
	// 28/3/2013.
	// Modificaciones el 2/4/13
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
					tablero[x][y].setFondo(y % 2 == 1 ? getNegro() : getBlanco());
					tablero[x][y].setResaltar(y % 2 == 1 ? getNegroResaltado() : getBlancoResaltado());
				} else {
					tablero[x][y].setFondo(y % 2 == 0 ? getNegro() : getBlanco());
					tablero[x][y].setResaltar(y % 2 == 0 ? getNegroResaltado() : getBlancoResaltado());
				}
				tablero[x][y].setBounds(anchoCuadro * (colorArriba == -1 ? x : (7 - x)), altoCuadro * (colorArriba == -1 ? y : (7 - y)), anchoCuadro, altoCuadro);
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
		return tablero;
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
	 public static Color getNegro() {
		 return negro;
	 }

	 /**
	  * Setea el Color negro para el tablero.
	  */
	 public void setNegro(Color negro) {
		 this.negro = negro;
	 }

	 /**
	* Retorna el Color blanco para el tablero.
	* */
	 
	 public static Color getBlanco() {
		 return blanco;
	 }

	 /**
	  * Setea el Color negro para el tablero.
	  */
	 public void setBlanco(Color blanco) {
		 this.blanco = blanco;
	 }

	 /**
	  * Retorna el Color para la casilla blanca cuando esta resaltada en el tablero.
	  */
	 public static Color getBlancoResaltado() {
		 return blancoResaltado;
	 }

	 /**
	  * Setea el Color para la casilla blanca cuando esta resaltada en el tablero.
	  */
	 public void setBlancoResaltado(Color blancoResaltado) {
		 this.blancoResaltado = blancoResaltado;
	 }

	 /**
	  * Retorna el Color para la casilla negro cuando esta resaltada en el tablero.
	  */
	 public static Color getNegroResaltado() {
		 return negroResaltado;
	 }

	 /**
	  * Setea el Color para la casilla blanca cuando esta resaltada en el tablero.
	  */
	 public void setNegroResaltado(Color negroResaltado) {
		 this.negroResaltado = negroResaltado;
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
	//@autor Daniel Chaves Coto.
	// 3/4/2013.
	

	 /**
	  * Precondiciones: Saruman debe atacar a alguna pieza.
	  * Entradas: La pieza a la cual Saruman ataca.
	  * Salidas: Mensaje acerca del movimineto de la pieza(se lleva a cabo en la función MoverPieza de la clase Pieza), al extremo inferior de la mina.
	  * Postcondiciones: La ficha es movida al extremo mas bajo de la mina donde no choque con una posición contraria o amiga, 
	  * ya que sino ese será su extremo más bajo. (Veamoslo del modo siguiente: 1. Si es enemiga está rodeada. 2. Si es amiga entonces encuentra refuerzos).
	  */
	 public static void P_saruman(Pieza piece){
		 for (int j = piece.getCuadroPieza().getInY(); 0<j && j<parametro-1; j++) {
			 if (tablero[piece.getCuadroPieza().getInX()][j+1].getPieza() == null){
				 tablero[piece.getCuadroPieza().getInX()][j+1].setPieza(piece.getCuadroPieza().getPieza());
				 tablero[piece.getCuadroPieza().getInX()][j].setPieza(null);
				 P_saruman(tablero[piece.getCuadroPieza().getInX()][j+1].getPieza()); // Se llama recursiva al no cumplir la condición de stop establecida.
				 break;
			 }
			 else{
				 break;
			 }
		 }
	 }
	//@autor Daniel Chaves Coto.
	// 11/4/2013.

	 /**
	  * 
	  * Precondiciones: Se debe haber validado la procreación de Eldarion por parte de sus padres, además de tener una posición y válida.
	  * Entradas: Entero de la fila en la cual se procreará a Eldarion.
	  * Salidas: Posicionamiento de Eldarion.
	  * Postcondiciones: Si la fila esta llena tendremos un error por validaciones, pero en caso contrario, eldarion se mostrará en el tablero de juego.
	  */
	 public static void ubica_Eldarion(int y){
		 Posiciones_Aleatorias posicion_eldarion = new Posiciones_Aleatorias(0,parametro-1);
		 int aleatorio = posicion_eldarion.generar();
		 while (true){
			 if (tablero[aleatorio][y].getPieza() == null){

				 tablero[aleatorio][y].setPieza(new Eldarion(1));
				 break;
			 }
			 else{

				 aleatorio = posicion_eldarion.generar(); // Si la posición x evaluada esta llena, se debe de generar otro número hasta que salga una posición válida.
			 }
		 }
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
		 if(ocurre.generar() == 1){
			 JOptionPane.showMessageDialog(JuegoPrincipal.tablero1, "Al parecer Minas Tirith esta sufriendo un terremoto");	 // Informe de terremoto.
			 Terremoto(0); //Ejecuto el terremoto.
		 }
	 }

	 public static void Terremoto(int posicion_y){ // Se recibe La fila inicial de la ejecución del terremoto, en vida real el epicentro que será la fila 0.
		 if (posicion_y < parametro){ // Se verifica que sea menor que el tamaño total del tablero.
			 //Se generan números aleatorios.
			 Posiciones_Aleatorias pieza = new Posiciones_Aleatorias(0, parametro-1);
			 Posiciones_Aleatorias nula = new Posiciones_Aleatorias(0, parametro-1);
			 int posicion_pieza = pieza.generar();
			 int posicion_nula = nula.generar();
			 //Se insertan los parámetros en la pila.
			 for(int d = 0; d < parametro-1; d++){
				 if (tablero[d][posicion_y].getPieza() != null){
					 terremoto.push(tablero[d][posicion_y].getPieza());
					 tablero[d][posicion_y].setPieza(null);
				 }
			 }
			 while(!terremoto.isEmpty()){ // Si la pila no es vacía.
				 if (posicion_pieza != -1){
					 if (tablero[posicion_pieza][posicion_y].getPieza() == null){
						 tablero[posicion_pieza][posicion_y].setPieza(terremoto.pop());
					 }
					 else{
						 posicion_pieza = pieza.generar(); //Genera otro número aleatorio si fuera el caso de que la posición este ocupada.
					 }
				 }
				 else{
					 break;
				 }

			 }
			 Terremoto(posicion_y+1); //Hace las recursiones necesarias para recorrer todas las filas.
		 }

	 }
	//@autor Daniel Chaves Coto.
	 // 8/4/2013.
	 // Modificaciones el 11/4/13
	 /** Precondiciones: 
	  * Tomese a Aragorn y sus validaciones como los siguientes casos:
	  *  __________
	  * |          |
	  * |x-1 & y-1 | Posición superior izquierda.
	  * |x & y-1   | Posición superior Central.
	  * |x-1 & y+1 | Posición superior derecha.
	  * |x-1 & y   | Posición lateral izquierda.
	  * |x+1 & y   | Posición lateral derecha.
	  * |x-1 & y+1 | Posición inferior izquierda.
	  * |x & y+1   | Posición inferior central.
	  * |x+1 & y+1 | Posición inferior derecha.
	  * |__________|
	  * 
	  * Entradas: Las posiciones (x, y) que corresponden al Destino de la ficha que se movió(Esta función aplica solo para Aragorn y Arwen).
	  * Salidas: Un JOptionPane.showConfirmDialog el cual se encargará de recoger la opción solicitada por el usuario con respecto a Eldarion.
	  * Postcondiciones: Se colocará a Eldarion mediante el método Ubica_Eldarion(y) con la posición en y de la ficha destino. (Tómese posición en y como
	  * un número el cual indica la fila donde se encuentra el destino).
	  * 
	  * */
	 public static void CrearEldarionAragorn(int x_x, int y_y){
		 int x = Math.abs(x_x);
		 int y = Math.abs(y_y);
		 if (x-1!=-1 && x+1!=parametro && y-1!=-1 && y+1!=parametro ){
			 if (procrear){

				 if ((tablero[x-1][y-1].getPieza() != null) && (tablero[x-1][y-1].getPieza().getRaza() == 7)){ // esquina superior izquierda
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y-1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }
				 else if ((tablero[x][y-1].getPieza() != null) && (tablero[x][y-1].getPieza().getRaza() == 7)){ //Arriba de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y-1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }			}
				 else if ((tablero[x+1][y-1].getPieza() != null) && (tablero[x+1][y-1].getPieza().getRaza() == 7)){ //Esquina superior derecha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y-1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }		
				 else if ((tablero[x-1][y].getPieza() != null) &&(tablero[x-1][y].getPieza().getRaza() == 7)){ // Izquierda de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }		
				 else if ((tablero[x+1][y].getPieza() != null) && (tablero[x+1][y].getPieza().getRaza() == 7)){ //Derecha de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }
				 else if ((tablero[x-1][y+1].getPieza() != null) && (tablero[x-1][y+1].getPieza().getRaza() == 7)){ //esquina inferior izquierda de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }			}
				 else if ((tablero[x][y+1].getPieza() != null) && (tablero[x][y+1].getPieza().getRaza() == 7)){ // Debajo de la ficha

					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }
				 else if ((tablero[x+1][y+1].getPieza() != null) && (tablero[x+1][y+1].getPieza().getRaza() == 7)){ //esquina inferior derecha de a ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }			
				 }
				 else if ((tablero[x+1][y+1].getPieza() != null) && (tablero[x+1][y+1].getPieza().getRaza() == 7)){ //esquina inferior derecha de a ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }

				 else{
				
				 }
			 }
		 }
		 else{ // Esta validación se produce con respuesta al error que se presento en la validación de que x-1  fuera negativo o x+1 fuera mayor que el parámetro inicial del tablero.
			 if (y-1 == -1 || y+1 == parametro){
			 }
			 else{

				 if (y-1 != -1 || y+1 != parametro){

					 if (     (tablero[x][y-1].getPieza() != null && (tablero[x][y-1].getPieza().getRaza() == 6 || tablero[x][y-1].getPieza().getRaza() == 7)) || (tablero[x][y+1].getPieza() != null && (tablero[x][y+1].getPieza().getRaza() == 6 || tablero[x][y+1].getPieza().getRaza() == 7))        ){ // esquina superior izquierda
						 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
						 Arwen.setInmortal(false);
						 JuegoPrincipal.v_Arwen.setText("Mortal");
						 if (Eleccion_Usuario==0){
							 ubica_Eldarion(y-1);
							 procrear=false;
							 JuegoPrincipal.v_Eldarion.setText("1");
						 }
					 }
				 }
			 }
		 }


	 }
	//@autor Daniel Chaves Coto.
	// 8/4/2013.
	 // Modificaciones el 11/4/13
	 /** Precondiciones: 
	  * Tomese a Arwem y sus validaciones como los siguientes casos:
	  *  __________
	  * |          |
	  * |x-1 & y-1 | Posición superior izquierda.
	  * |x & y-1   | Posición superior Central.
	  * |x-1 & y+1 | Posición superior derecha.
	  * |x-1 & y   | Posición lateral izquierda.
	  * |x+1 & y   | Posición lateral derecha.
	  * |x-1 & y+1 | Posición inferior izquierda.
	  * |x & y+1   | Posición inferior central.
	  * |x+1 & y+1 | Posición inferior derecha.
	  * |__________|
	  * 
	  * Entradas: Las posiciones (x, y) que corresponden al Destino de la ficha que se movió(Esta función aplica solo para Aragorn y Arwen).
	  * Salidas: Un JOptionPane.showConfirmDialog el cual se encargará de recoger la opción solicitada por el usuario con respecto a Eldarion.
	  * Postcondiciones: Se colocará a Eldarion mediante el método Ubica_Eldarion(y) con la posición en y de la ficha destino. (Tómese posición en y como
	  * un número el cual indica la fila donde se encuentra el destino).
	  * 
	  * */
	 public static void CrearEldarionArwen(int x_x, int y_y){
		 int x = Math.abs(x_x);
		 int y = Math.abs(y_y);
		 if(procrear==true){    	
			 if (x-1!=-1 && x+1!=parametro && y-1!=-1 && y+1!=parametro){

				 if ((tablero[x-1][y-1].getPieza() != null) && (tablero[x-1][y-1].getPieza().getRaza() == 6)){ // esquina superior izquierda
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y-1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }
				 else if ((tablero[x][y-1].getPieza() != null) && (tablero[x][y-1].getPieza().getRaza() == 6)){ //Arriba de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y-1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }			}
				 else if ((tablero[x+1][y-1].getPieza() != null) && (tablero[x+1][y-1].getPieza().getRaza() == 6)){ //Esquina superior derecha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 procrear=false;
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y-1);
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }		
				 else if ((tablero[x-1][y].getPieza() != null) && (tablero[x-1][y].getPieza().getRaza() == 6)){ // Izquierda de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }		
				 else if ((tablero[x+1][y].getPieza() != null) && (tablero[x+1][y].getPieza().getRaza() == 6)){ //Derecha de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }
				 else if ((tablero[x-1][y+1].getPieza() != null) && (tablero[x-1][y+1].getPieza().getRaza() == 6)){ //esquina inferior izquierda de la ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }			
				 }
				 else if ((tablero[x][y+1].getPieza() != null) && (tablero[x][y+1].getPieza().getRaza() == 6)){ // Debajo de la ficha


					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }
				 else if ((tablero[x+1][y+1].getPieza() != null) && (tablero[x+1][y+1].getPieza().getRaza() == 6)){ //esquina inferior derecha de a ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }			
				 }
				 else if ((tablero[x+1][y+1].getPieza() != null) && (tablero[x+1][y+1].getPieza().getRaza() == 6)){ //esquina inferior derecha de a ficha
					 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
					 Arwen.setInmortal(false);
					 JuegoPrincipal.v_Arwen.setText("Mortal");
					 if (Eleccion_Usuario==0){
						 ubica_Eldarion(y+1);
						 procrear=false;
						 JuegoPrincipal.v_Eldarion.setText("1");
					 }
				 }

				 else{
					
				 }
			 }
			 else{// Esta validación se produce con respuesta al error que se presento en la validación de que x-1  fuera negativo o x+1 fuera mayor que el parámetro inicial del tablero.
				 if (y-1 == -1 || y+1 == parametro){
				 }
				 else{

					 if (y-1 != -1 || y+1 != parametro){

						 if (     (tablero[x][y-1].getPieza() != null && (tablero[x][y-1].getPieza().getRaza() == 6 || tablero[x][y-1].getPieza().getRaza() == 7)) || (tablero[x][y+1].getPieza() != null && (tablero[x][y+1].getPieza().getRaza() == 6 || tablero[x][y+1].getPieza().getRaza() == 7))        ){ // esquina superior izquierda
							 int Eleccion_Usuario =JOptionPane.showConfirmDialog(JuegoPrincipal.tablero1, "Desea Procrear a Eldarion", "Confirmacion", JOptionPane.YES_NO_OPTION);
							 Arwen.setInmortal(false);
							 JuegoPrincipal.v_Arwen.setText("Mortal");
							 if (Eleccion_Usuario==0){
								 ubica_Eldarion(y-1);
								 procrear=false;
								 JuegoPrincipal.v_Eldarion.setText("1");
							 }
						 }
					 }
				 }
			 }
		 }
	 }

}



