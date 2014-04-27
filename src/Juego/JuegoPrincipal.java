package Juego;

/**
 * Autor:Daniel Chaves Coto y Oswaldo D�vila Padr�n
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
 * Esta es la Clase JuegoPrincipal, que ha sido declarada de tipo p�blica.
 */
public class JuegoPrincipal {

	/**
	 * Declaraci�n de del JFrame en el cual se ubicara todo lo del juego, adem�s de las declaraciones de todas las variables a ser utilizadas en esta clase.
	 */
	public static JFrame frame;
	public static Juego.Board_Horses table; //Variable para el tablero
	public static Cronometro relojCaballo_1; //Var9iable para el Cron�metro del juego
	public static Cronometro relojCaballo_2; //Var9iable para el Cron�metro del juego
	public static CronometroAlianza relojAlianza; //Variable para el cron�metro de la alianza
	/**
	 * Estas delcaraciones de varibales de JTextField, contienen la informaci�n de vidas, poderes y dem�s informaci�n de dichas piezas (cuyo nombre se encuentra en cada variable) del juego.
	 */
	public static JTextField v_Aragorn;
	public static JTextField v_Arwen;
	public static JTextField v_Gandalf;
	public static JTextField v_Eldarion;
	public static JTextField v_Frodo;
	public static JTextField a_h_turno;
	public static JTextField v_Saruman;
	public static JTextField v_Gollum;
	public static boolean salir; // Declaraci�n  de la variable booleana para permitir salir del juego.
	/**
	 * Corresponde a las declaraciones de la barra de men� que contiene la opsci�n de Iniciar Nueva Partida y Salir del juego.
	 */
	private javax.swing.JMenuItem MenuItemIniciarPartida;
	private javax.swing.JMenuItem MenuItemSalir;
	private javax.swing.JMenu jMenu1;
	private javax.swing.JMenuBar jMenuBar1;
	private javax.swing.JMenuItem jMenuItem1;
	public static Clip sonido; //Declaraci�n de la variable que sera utilizada ara el sonido o m�sica del juego.



	/**
	 * Funci�n principal main, que ejecuta a la clase y hace visible la ventana del juego.
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
	 * Esta funci�n es la encargada de inicializar todos los m�todos y funciones a utilizar en esta clase.
	 * @throws LineUnavailableException 
	 * @throws UnsupportedAudioFileException 
	 * @throws IOException 
	 */
	public static void initialize() throws LineUnavailableException, IOException, UnsupportedAudioFileException {

		/**
		 * Creaci�n del JFrame, asignaci�n del color, de las medidas de los par�metros del tablero seg�n lo elegido por el usuario, hacer visible la ventana,
		 * activar el boton del la X para poder cerrar la ventana desde ahi tambien.
		 */
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(0, 0, 50*Board_Horses.parametro+300, 50*Board_Horses.parametro+100);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		//Creaci�n del tablero
		table = new Juego.Board_Horses();

		table.setBounds(0, 0, 50*Board_Horses.parametro+4,50*Board_Horses.parametro+4);
		frame.getContentPane().add(table);

		/**
		 * Se inicializ el cron�metro del juego y el cron�metro de la alianza, con su visibilidad activada, sus par�metros y tama�o indicado respectivamente.
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
		 * Incializaci�n y creaci�n del barra de men� en la cual se encontrar�n las opciones de Iniciar Nueva Partida y Salir del Juego.
		 */
		JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
		JMenu jMenu1 = new javax.swing.JMenu();
		JMenuItem MenuItemIniciarPartida = new javax.swing.JMenuItem();
		JMenuItem jMenuItem1 = new javax.swing.JMenuItem();
		JMenuItem MenuItemSalir = new javax.swing.JMenuItem();


		table.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

		/**
		 * Creaci�n del item del nombre archivo en el cual se gener� o almacena la lista de las opsciones de Iniciar Nueva Partida y Salir.
		 */
		jMenu1.setText("Archivo");
		frame.setJMenuBar(jMenuBar1);

		/**
		 * Activaci�n del Sonido del juego.
		 */
		final Clip sonido = AudioSystem.getClip();
		sonido.open(AudioSystem.getAudioInputStream(new File("src/Imagenes/Sample.wav")));
		sonido.loop(20);

		/**
		 * Creaci�n y asignaci�n de la funcionalidad del men� Iniciar Partida Nueva.
		 */
		MenuItemIniciarPartida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
		MenuItemIniciarPartida.setText("Iniciar Nueva Partida");
		MenuItemIniciarPartida.addActionListener(new java.awt.event.ActionListener() {

			/**
			 * Las acciones que realiza dicho men� son:
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
		jMenu1.add(MenuItemIniciarPartida);


		jMenu1.add(jMenuItem1);

		/**
		 * Creaci�n y asignaci�n de la funcionalidad del men� Salir.
		 */
		MenuItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
		MenuItemSalir.setText("Salir");
		MenuItemSalir.addActionListener(new java.awt.event.ActionListener() {

			/**
			 * Este men� permitir� finalizar la partida y cerrar la ventana del juego
			 */
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});
		jMenu1.add(MenuItemSalir);
		jMenuBar1.add(jMenu1);

		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n de las vidas en este caso 1.
		 */
		v_Aragorn = new JTextField();
		v_Aragorn.setEditable(false);// No se permite que la caja de texto sea editable
		v_Aragorn.setBounds(80*Board_Horses.parametro+150, 362, 73, 30);
		frame.getContentPane().add(v_Aragorn);
		v_Aragorn.setColumns(10);

		v_Aragorn.setText(Caballo_1.vidas);

		/**
		 * Control mediante pantalla cual turno se est� jugando actualmente
		 */
		JLabel lblturno = new JLabel("Turno Actual");
		lblturno.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblturno.setBounds(80*Board_Horses.parametro+35, 250, 121, 30);
		frame.getContentPane().add(lblturno);

		a_h_turno= new JTextField();
		a_h_turno.setEditable(false);// No se permite que la caja de texto sea editable
		a_h_turno.setColumns(10);
		a_h_turno.setBounds(80*Board_Horses.parametro+150, 250, 121, 30);
		a_h_turno.setText("Comunidad"); //Muestra el turno que inicia jugando
		frame.getContentPane().add(a_h_turno);

		JLabel lblVidas = new JLabel("Vidas");
		lblVidas.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		lblVidas.setBounds(80*Board_Horses.parametro+100, 304, 121, 30);
		frame.getContentPane().add(lblVidas);

		JLabel lblAragorn = new JLabel("Aragorn");
		lblAragorn.setBounds(80*Board_Horses.parametro+50, 370, 46, 14);
		frame.getContentPane().add(lblAragorn);

		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n de la Inmortalidad 
		 */
		JLabel lblArwen = new JLabel("Arwen");
		lblArwen.setBounds(80*Board_Horses.parametro+50, 414, 46, 14);
		frame.getContentPane().add(lblArwen);

		v_Arwen = new JTextField();
		v_Arwen.setEditable(false);// No se permite que la caja de texto sea editable
		v_Arwen.setColumns(10);
		v_Arwen.setBounds(80*Board_Horses.parametro+150, 403, 73, 30);
		frame.getContentPane().add(v_Arwen);


		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n de las vidas que en este personaje son 2
		 */
		JLabel lblGandalf = new JLabel("Gandalf\r\n");
		lblGandalf.setBounds(80*Board_Horses.parametro+50, 452, 46, 14);
		frame.getContentPane().add(lblGandalf);

		v_Gandalf = new JTextField();
		v_Gandalf.setEditable(false);// No se permite que la caja de texto sea editable
		v_Gandalf.setColumns(10);
		v_Gandalf.setBounds(80*Board_Horses.parametro+150, 444, 73, 30);
		frame.getContentPane().add(v_Gandalf);


		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n del momento en que es creado o si todavia no a sido creado, una vez creado ya no peude ser creado mas.
		 */
		JLabel lblEldarion = new JLabel("Eldarion");
		lblEldarion.setBounds(80*Board_Horses.parametro+50, 534, 46, 14);
		frame.getContentPane().add(lblEldarion);        

		v_Eldarion = new JTextField();
		v_Eldarion.setEditable(false);// No se permite que la caja de texto sea editable
		v_Eldarion.setColumns(10);
		v_Eldarion.setBounds(80*Board_Horses.parametro+150, 526, 73, 30);
		v_Eldarion.setText("No creado"); //Muestra que Eldari�n no ha sido creado, esto en el inicio del juego.
		frame.getContentPane().add(v_Eldarion);

		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n de las vidas en este personaje ser�n tres
		 */
		JLabel lblFrodo = new JLabel("Frodo");
		lblFrodo.setBounds(80*Board_Horses.parametro+50, 493, 46, 14);
		frame.getContentPane().add(lblFrodo);

		v_Frodo = new JTextField();
		v_Frodo.setEditable(false);// No se permite que la caja de texto sea editable
		v_Frodo.setColumns(10);
		v_Frodo.setBounds(80*Board_Horses.parametro+150, 485, 73, 30);
		frame.getContentPane().add(v_Frodo);

		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n de las vidas en este personaje ser� una
		 */
		JLabel lblSaruman = new JLabel("Saruman");
		lblSaruman.setBounds(80*Board_Horses.parametro+50, 574, 60, 14);
		frame.getContentPane().add(lblSaruman);

		v_Saruman = new JTextField();
		v_Saruman.setEditable(false);// No se permite que la caja de texto sea editable
		v_Saruman.setColumns(10);
		v_Saruman.setBounds(80*Board_Horses.parametro+150, 567, 73, 30);
		v_Saruman.setText("1"); // El valor de la vidas a mostrar
		frame.getContentPane().add(v_Saruman);

		/**
		 * Asignaci�n a las cajitas de texto del personaje con la informaci�n de las vidas en este personaje ser� una
		 */ 
		JLabel lblGollums = new JLabel("Gollum");
		lblGollums.setBounds(80*Board_Horses.parametro+50, 616, 150, 14);
		frame.getContentPane().add(lblGollums);

		v_Gollum = new JTextField();
		v_Gollum.setEditable(false);// No se permite que la caja de texto sea editable
		v_Gollum.setColumns(10);
		v_Gollum.setBounds(80*Board_Horses.parametro+150, 608, 73, 30);
		v_Gollum.setText("1"); // El valor de la vidas a mostrar
		frame.getContentPane().add(v_Gollum);




	}


	public void salir(){frame.dispose();}
}

