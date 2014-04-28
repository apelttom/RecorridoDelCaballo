package Juego;

/**
 * Autor:Daniel Chaves Coto y Oswaldo Dávila Padrón
 * 01 hasta el 11 /04/2013
 */

/**
 * Son importadas todas las librerias necesarias para el desarrollo de la clase JuegoPrincipal.
 */
import java.awt.EventQueue;
import java.io.*;

import javax.sound.*;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.GroupLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Piezas.Caballo_1;
import Piezas.Caballo_2;

import java.awt.Font;
import java.awt.Color;


/**
 * Esta es la Clase JuegoPrincipal, que ha sido declarada de tipo pública.
 */
public class JuegoPrincipal {

	/**
	 * Declaración de del JFrame en el cual se ubicara todo lo del juego, además de las declaraciones de todas las variables a ser utilizadas en esta clase.
	 */
	public static JFrame frame;
	public static Juego.Board_Horses table; //Variable para el tablero
	public static Cronometro relojCaballo_1; //Var9iable para el Cronómetro del juego
	public static Cronometro relojCaballo_2; //Var9iable para el Cronómetro del juego	
	
	public static boolean salir; // Declaración  de la variable booleana para permitir salir del juego.
	/**
	 * Corresponde a las declaraciones de la barra de menú que contiene la opsción de Iniciar Nueva Partida y Salir del juego.
	 */
	private javax.swing.JMenuItem MenuItemIniciarPartida;
	private javax.swing.JMenuItem MenuItemSalir;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	public static Clip sonido; //Declaración de la variable que sera utilizada ara el sonido o música del juego.



	/**
	 * Función principal main, que ejecuta a la clase y hace visible la ventana del juego.
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		JuegoPrincipal window = new JuegoPrincipal();
		window.frame.setVisible(true);
	}

	/**
	 * Este es el constructor de la clase
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public JuegoPrincipal() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		initialize();


	}

	/**
	 * Esta función es la encargada de inicializar todos los métodos y funciones a utilizar en esta clase.
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public static void initialize() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

		/**
		 * Creación del JFrame, asignación del color, de las medidas de los parámetros del tablero según lo elegido por el usuario, hacer visible la ventana,
		 * activar el boton del la X para poder cerrar la ventana desde ahi tambien.
		 */
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(0, 0, 50*Board_Horses.parametro+300, 50*Board_Horses.parametro+100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//Creación del tablero
		table = new Juego.Board_Horses();

		table.setBounds(0, 0, 50*Board_Horses.parametro+4,50*Board_Horses.parametro+4);
		frame.getContentPane().add(table);

		/**
		 * Se inicializa el cronómetro del juego y el cronómetro de la alianza, con su visibilidad activada, sus parámetros y tamaño indicado respectivamente.
		 */
		relojCaballo_1 = new Cronometro();
		relojCaballo_1.setVisible(true);
		relojCaballo_1.setBounds(50*Board_Horses.parametro+50, 40, 220, 76);
		//frame.getContentPane().add(relojCaballo_1);
		
		relojCaballo_2 = new Cronometro();
		relojCaballo_2.setVisible(true);
		relojCaballo_2.setBounds(50*Board_Horses.parametro+50, 127, 220, 76);
		
		//Inician el reloj de ambos caballos a correr almismo tiempo
		frame.getContentPane().add(relojCaballo_1);
		frame.getContentPane().add(relojCaballo_2);

		table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		/**
		 * Incialización y creación del barra de menú en la cual se encontrarán las opciones de Iniciar Nueva Partida y Salir del Juego.
		 */
		JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
		JMenu jMenu1 = new javax.swing.JMenu();
		JMenuItem MenuItemSalir = new javax.swing.JMenuItem();


		table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		/**
		 * Creación del item del nombre archivo en el cual se genera o almacena la lista de las opsciones de Iniciar Nueva Partida y Salir.
		 */
		jMenu1.setText("Archivo");
		frame.setJMenuBar(jMenuBar1);

		/**
		 * Activación del Sonido del juego.
		 */
		final Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(new File("src/Imagenes/Sample.wav")));
		sonido.loop(20);

		/**
		 * Creación y asignación de la funcionalidad del menú Salir.
		 */
		MenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
		MenuItemSalir.setText("Salir");
		MenuItemSalir.addActionListener(new java.awt.event.ActionListener() {

			/**
			 * Este menú permitirá finalizar la partida y cerrar la ventana del juego
			 */
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});
		jMenu1.add(MenuItemSalir);
		jMenuBar1.add(jMenu1);
		
		JMenu mnOpciones = new JMenu("Opciones");
		jMenuBar1.add(mnOpciones);
		JMenuItem MenuItemNuevo_Juego = new javax.swing.JMenuItem();
		mnOpciones.add(MenuItemNuevo_Juego);
		MenuItemNuevo_Juego.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
		MenuItemNuevo_Juego.setText("Nuevo Juego");
		
		JMenuItem mntmCorrerRecorrido = new JMenuItem("Correr Recorrido");
		mnOpciones.add(mntmCorrerRecorrido);
		
		
		
		MenuItemNuevo_Juego.addActionListener(new java.awt.event.ActionListener() {

			/**
			 * Las acciones que realiza dicho menú son:
			 *  1: Generar una nueva partida
			 *  2: Inicializar todas las variables del juego para la nueva partida
			 *  3: Cerrar el sonido de la partida anterior, para generar de cero el sonido con la nueva partida.
			 */
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sonido.close();	
				Pieza.reiniciar();
				Board_Horses.setTurno(Board_Horses.getTurno()*-1);
			}
		});








	}


	public void salir(){frame.dispose();}
}

