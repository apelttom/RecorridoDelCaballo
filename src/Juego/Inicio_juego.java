package Juego;

/**
 * Autor:Oswaldo Dávila Padrón
 * 01, 02/04/2013
 */

/**
 * Son importadas todas las librerias necesarias para el desarrollo de la clase Inicio_juego.
 */
import java.awt.Color;
import java.awt.EventQueue;


import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JRadioButton;

/**
 * Esta es la Clase Inicio_juego, que ha sido declarada de tipo pública.
 */
public class Inicio_juego extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JPanel panel_general; //Declaración del panel general, sobre el cual se encuentra todo lo que se muestra en la pantalla de inicio del juego.
	private JLabel etqImagen; //Declaración de la etiqueta que contiene la imagen de fondo utilizada en la pantalla de inicio del juego.

	/**
	 * rb1, rb2, rb3, rb4, rb5 Son las declaraciones de los botones que se utilizan en dicha pantalla del inicio del juego, para permitirle al usuario que escoga la dimensión que desee utilizar para jugar en el tablero.
	 */
	private JRadioButton rb1;
	private JRadioButton rb2;
	private JRadioButton rb3;
	private JRadioButton rb4;
	private JRadioButton rb5;
	private JButton boton_aceptar; // Esta corresponde a la declaración del botón comenzar que dará inicio al juego.
	private ButtonGroup grupo;


	/**
	 * Inicia la creación del frame
	 */
	public Inicio_juego() {
		super("Iniciando Juego");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Esto permite activar el botón de la "X" que se encuentra en la pantalla, con el fin de permitir el cierre de la misma cuando sea pulsado mediante un click
		setBounds(0, 0, 1024, 768); //Define el tamaño de la ventana principal del juego.
		panel_general = new JPanel(); //Se ubica el panel donde se colocan todas las imagenes, botones y demás cosas a ser utilizados en la pantalla.

		/**
		 * Declaración de parámetros para el uso de la interfaz gráfica utilizada en el programa, son características que se relacionan con el panel, ya definido anteriormente.
		 */
		panel_general.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_general);
		panel_general.setLayout(null);
		panel_general.setOpaque(false);

		initialize();}

	/**
	 * Esta es la función initialize, en la cual se encuentra el desarrollo principal de todo lo que realiza esta clase.
	 */
	public void initialize(){

		JButton boton_aceptar = new JButton("Comenzar"); //Se le asigna el nombre a presentar en pantalla al boton_aceptar ya declarado anteriormente, dicho nombre es "Comenzar" y por medio de dicho botón  se dará  paso a la siguiente pantalla en donde se inicia el juego.
		boton_aceptar.setBounds(479, 702, 115, 27); //Se  asigna la ubicación del botón.
		boton_aceptar.setFont(new Font("Tahoma", Font.PLAIN, 13)); // Se asigna el tipo y tamaño de letra a utilizar para el botón.
		boton_aceptar.setIcon(new ImageIcon(Inicio_juego.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif"))); //Color del botón.
		boton_aceptar.setHorizontalAlignment(SwingConstants.LEFT);

		boton_aceptar.addActionListener(this);
		etqImagen = new JLabel();

		/**
		 * Se asigna el nombre, ubicación, tamaño y color del primer botón que indica la dimensión de 8x8 del tablero.
		 */
		grupo = new ButtonGroup();
		rb1 = new JRadioButton("8 x 8",false);
		rb1.setHorizontalAlignment(SwingConstants.CENTER);
		rb1.setBounds(300, 525, 77, 23);
		rb1.setBorderPainted(true);
		rb1.setBackground(Color.white);

		/**
		 * Se asigna el nombre, ubicación, tamaño y color del segundo botón que indica la dimensión de 9x9 del tablero.
		 */
		rb2 = new JRadioButton("9 x 9",false);
		rb2.setHorizontalAlignment(SwingConstants.CENTER);
		rb2.setBounds(400, 525, 77, 23);
		rb2.setBorderPainted(true);
		rb2.setBackground(Color.white);

		/**
		 * Se asigna el nombre, ubicación, tamaño y color del tercer botón que indica la dimensión de 10x10 del tablero.
		 */
		rb3 = new JRadioButton("10 x 10",false);
		rb3.setHorizontalAlignment(SwingConstants.CENTER);
		rb3.setBounds(500, 525, 77, 23);
		rb3.setBorderPainted(true);
		rb3.setBackground(Color.white);

		/**
		 * Se asigna el nombre, ubicación, tamaño y color del cuarto botón que indica la dimensión de 11x11 del tablero.
		 */
		rb4 = new JRadioButton("11 x 11",false);
		rb4.setHorizontalAlignment(SwingConstants.CENTER);
		rb4.setBounds(600, 525, 77, 23);
		rb4.setBorderPainted(true);
		rb4.setBackground(Color.white);

		/**
		 * Se asigna el nombre, ubicación, tamaño y color del quinto botón que indica la dimensión de 12x12 del tablero.
		 */
		rb5 = new JRadioButton("12 x 12",false);
		rb5.setHorizontalAlignment(SwingConstants.CENTER);
		rb5.setBounds(700, 525, 77, 23);
		rb5.setBorderPainted(true);
		rb5.setBackground(Color.white);

		/**
		 * Se carga la imagen a utilizar como fondo en la pantalla de inicio de juego.
		 */
		etqImagen.setIcon(new ImageIcon("src/Imagenes/Minas Tirith.png"));
		etqImagen.setBounds(10, 0, 1024, 768);

		/**
		 * Se realiza un agregar al panel de todos los botones e imagenes explicadas anteriormente.
		 */
		grupo.add(rb1);
		panel_general.add(boton_aceptar);
		grupo.add(rb2);
		grupo.add(rb3);
		grupo.add(rb4);
		grupo.add(rb5);
		panel_general.add(rb1);
		panel_general.add(rb2);
		panel_general.add(rb3);
		panel_general.add(rb4);
		panel_general.add(rb5);
		panel_general.add(etqImagen);

	}


	/**
	 * Función que permite asignarle acción al botón comenzar.
	 * 
	 * Dentro de la función encontramos un conjunto de bloques de desición que funciona de la siguiente manera:
	 *    Si el botón de dimensión de pantalla que presiona el ususario es:
	 *     1. 8x8, deshabilita la ventana actual del inicio del juego, crea un tablero con el parámetro elegido por el usuario y genera un nuevo juego, esto invocando a la clase JuegoPrincipal.
	 *     2. 9x9, deshabilita la ventana actual del inicio del juego, crea un tablero con el parámetro elegido por el usuario y genera un nuevo juego, esto invocando a la clase JuegoPrincipal.
	 *     3. 10x10,deshabilita la ventana actual del inicio del juego, crea un tablero con el parámetro elegido por el usuario y genera un nuevo juego, esto invocando a la clase JuegoPrincipal.
	 *     4. 11x11, deshabilita la ventana actual del inicio del juego, crea un tablero con el parámetro elegido por el usuario y genera un nuevo juego, esto invocando a la clase JuegoPrincipal.
	 *     5. 12x12, deshabilita la ventana actual del inicio del juego, crea un tablero con el parámetro elegido por el usuario y genera un nuevo juego, esto invocando a la clase JuegoPrincipal.
	 *  
	 *  Estas cinco validaciones ceuntan internamente con un try cath, esto con el finde hacer mas eficie3nte el sistema, previendo algún tipo de error. 
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==boton_aceptar || rb1.isSelected()){


			try {this.dispose();
			Tablero.parametro = 8;
			new JuegoPrincipal();


			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 

		}
		else if(e.getSource()==boton_aceptar || rb2.isSelected()){ 
			try {
				this.dispose();
				Tablero.parametro = 9;
				new JuegoPrincipal();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 
		}

		else if(e.getSource()==boton_aceptar || rb3.isSelected()){ 
			try {
				this.dispose();
				Tablero.parametro = 10;
				new JuegoPrincipal();

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 
		}
		else if(e.getSource()==boton_aceptar || rb4.isSelected()){ 
			try {
				this.dispose();
				Tablero.parametro = 11;
				new JuegoPrincipal();

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 				}
		else if(e.getSource()==boton_aceptar || rb5.isSelected()){ 
			try {
				this.dispose();
				Tablero.parametro = 12;
				new JuegoPrincipal();

			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 
		}

	}

	/**
	 * Esta es la función main, es la función principal de esta clase que permite la ejecución de la misma, inicializa la pantalla de inicio de juego y la hace visible.
	 * @param args
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio_juego frame = new Inicio_juego();
					frame.setVisible(true);
					panel_general.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}