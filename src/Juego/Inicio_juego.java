package Juego;


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
import javax.swing.JSeparator;

/**
 * Esta es la Clase Inicio_juego, que ha sido declarada de tipo p�blica.
 */
public class Inicio_juego extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static JPanel panel_general; //Declaraci�n del panel general, sobre el cual se encuentra todo lo que se muestra en la pantalla de inicio del juego.
	private JLabel etqImagen; //Declaraci�n de la etiqueta que contiene la imagen de fondo utilizada en la pantalla de inicio del juego.

	/**
	 * rb1, rb2, rb3, rb4, rb5 Son las declaraciones de los botones que se utilizan en dicha pantalla del inicio del juego, para permitirle al usuario que escoga la dimensi�n que desee utilizar para jugar en el tablero.
	 */
	private JRadioButton dimension1;//contiene la dimensi�n 8x8
	private JRadioButton dimension2;//contiene la dimensi�n 12x12
	private JRadioButton dimension3;//contiene la dimensi�n 16x16
	private ButtonGroup grupo;
	private JButton boton_inciar; // Esta corresponde a la declaraci�n del bot�n iniciar que dar� inicio al juego del recorrido de los caballos.


	/**
	 * Inicia la creaci�n del frame
	 */
	public Inicio_juego() {
		super("Iniciando Juego");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Esto permite activar el bot�n de la "X" que se encuentra en la pantalla, con el fin de permitir el cierre de la misma cuando sea pulsado mediante un click
		setBounds(0, 0, 464, 419); //Define el tama�o de la ventana principal del juego.
		panel_general = new JPanel(); //Se ubica el panel donde se colocan todas las imagenes, botones y dem�s cosas a ser utilizados en la pantalla.

		/**
		 * Declaraci�n de par�metros para el uso de la interfaz gr�fica utilizada en el programa, son caracter�sticas que se relacionan con el panel, ya definido anteriormente.
		 */
		panel_general.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel_general);
		panel_general.setLayout(null);
		panel_general.setOpaque(false);

		initialize();}

	/**
	 * Esta es la funci�n initialize, en la cual se encuentra el desarrollo principal de todo lo que realiza esta clase.
	 */
	public void initialize(){

		boton_inciar = new JButton("Iniciar"); //Se le asigna el nombre a presentar en pantalla al boton_iniciar ya declarado anteriormente, dicho nombre es "Iniciar" y por medio de dicho bot�n  se dar�  paso a la siguiente pantalla en donde se inicia el juego.
		boton_inciar.setBounds(171, 318, 115, 27); //Se  asigna la ubicaci�n del bot�n.
		boton_inciar.setFont(new Font("Tahoma", Font.PLAIN, 13)); // Se asigna el tipo y tama�o de letra a utilizar para el bot�n.
		boton_inciar.setIcon(new ImageIcon(Inicio_juego.class.getResource("/javax/swing/plaf/metal/icons/ocean/expanded.gif"))); //Color del bot�n.
		boton_inciar.setHorizontalAlignment(SwingConstants.LEFT);

		boton_inciar.addActionListener(this);
		etqImagen = new JLabel();

		/**
		 * Se asigna el nombre, ubicaci�n, tama�o y color del primer bot�n que indica la dimensi�n de 8x8 del tablero.
		 */
		grupo = new ButtonGroup();
		dimension1 = new JRadioButton("8 x 8",false);
		dimension1.setHorizontalAlignment(SwingConstants.CENTER);
		dimension1.setBounds(73, 238, 77, 23);
		dimension1.setBorderPainted(true);
		dimension1.setBackground(Color.WHITE);

		/**
		 * Se asigna el nombre, ubicaci�n, tama�o y color del segundo bot�n que indica la dimensi�n de 12x12 del tablero.
		 */
		dimension2 = new JRadioButton("12 x 12",false);
		dimension2.setHorizontalAlignment(SwingConstants.CENTER);
		dimension2.setBounds(195, 238, 77, 23);
		dimension2.setBorderPainted(true);
		dimension2.setBackground(Color.white);

		/**
		 * Se asigna el nombre, ubicaci�n, tama�o y color del tercer bot�n que indica la dimensi�n de 16x16 del tablero.
		 */
		dimension3 = new JRadioButton("16 x 16",false);
		dimension3.setHorizontalAlignment(SwingConstants.CENTER);
		dimension3.setBounds(310, 238, 77, 23);
		dimension3.setBorderPainted(true);
		dimension3.setBackground(Color.white);

		/**
		 * Se carga la imagen a utilizar como fondo en la pantalla de inicio de juego.
		 */
		etqImagen.setIcon(new ImageIcon("src/Imagenes/Menu.png"));
		etqImagen.setBounds(0, 0, 491, 426);

		/**
		 * Se realiza un agregar al panel de todos los botones e imagenes explicadas anteriormente.
		 */
		grupo.add(dimension1);
		panel_general.add(boton_inciar);
		grupo.add(dimension2);
		grupo.add(dimension3);
		panel_general.add(dimension1);
		panel_general.add(dimension2);
		panel_general.add(dimension3);
		
		JLabel lblTitulo = new JLabel("Recorrido de los Caballos");//Corresponde a la etiqueta que contiene le t�tulo del proyecto
		lblTitulo.setBackground(Color.BLACK);//color del texto
		lblTitulo.setForeground(new Color(0, 0, 0));
		lblTitulo.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 25));//tama�o y tipo de letra del texto
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 87, 458, 27);
		panel_general.add(lblTitulo);
		
		JLabel lblSeleccioneDimensin = new JLabel("Seleccione la dimensi\u00F3n del tablero:");//T�tulo de selecci�n de dimensiones del tablero, como informaci�n para el ususario de lo que representan los tres botones con las dimensiones
		lblSeleccioneDimensin.setForeground(Color.WHITE);//color del texto 
		lblSeleccioneDimensin.setFont(new Font("Arial", Font.BOLD, 17));//tama�o y tipo de letra del texto
		lblSeleccioneDimensin.setBounds(0, 160, 458, 14);
		panel_general.add(lblSeleccioneDimensin);
		panel_general.add(etqImagen);

	}


	/**
	 * Esta es la funci�n que permite asignarle acci�n al bot�n iniciar.
	 * 
	 * Dentro de la funci�n encontramos un conjunto de bloques de desici�n que funciona de la siguiente manera:
	 *    Si el bot�n de dimensi�n de pantalla que presiona el ususario es:
	 *     1. 8x8, deshabilita la ventana actual del inicio del juego, crea un tablero con el par�metro elegido por el usuario y genera un nuevo juego de los caballos, esto invocando a la clase JuegoPrincipal.
	 *     2. 12x12, deshabilita la ventana actual del inicio del juego, crea un tablero con el par�metro elegido por el usuario y genera un nuevo juegode los caballos, esto invocando a la clase JuegoPrincipal.
	 *     3. 16x16,deshabilita la ventana actual del inicio del juego, crea un tablero con el par�metro elegido por el usuario y genera un nuevo juego de los caballos, esto invocando a la clase JuegoPrincipal.

	 *  
	 *  Estas tres validaciones cuentan internamente con un try cath, esto con el fin de hacer mas eficiente el sistema, previendo alg�n tipo de error. 
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==boton_inciar && dimension1.isSelected()){


			try {this.dispose();
			Board_Horses.parametro = 8;
			new JuegoPrincipal();


			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 

		}
		else if(e.getSource()==boton_inciar && dimension2.isSelected()){ 
			try {
				this.dispose();
				Board_Horses.parametro = 12;
				new JuegoPrincipal();
			} catch (LineUnavailableException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (UnsupportedAudioFileException e1) {
				e1.printStackTrace();
			} 
		}

		else if(e.getSource()==boton_inciar && dimension3.isSelected()){ 
			try {
				this.dispose();
				Board_Horses.parametro = 16;
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
	 * Esta es la funci�n main, es la funci�n principal de esta clase que permite la ejecuci�n de la misma, inicializa la pantalla de inicio de juego y la hace visible.
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