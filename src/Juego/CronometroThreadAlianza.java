//Se realiza el package a juego.
package Juego;

import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import Juego.Board_Horses;


public class CronometroThreadAlianza implements Runnable {
   private Thread hiloCronometro;// Se define el hilo correspondiente a hiloCronometro.
   public boolean go,live; // Se definen booleanos privados.
   public static int segundos;//Se define segundos como un entero global privado.
   public int seg;// Se define seg como un entero privado.
   private CronometroAlianza reloj;//Se define reloj como una instancia de la clase CronometroAlianza.

   /**
    * Este es el constructor de la clase CronometroThread.
    */
   public CronometroThreadAlianza(CronometroAlianza v) {
	   //Entradas: Se le pasa como parámetro una instancia de Cronometro.
       reloj = v;
       //Salidas: no retorna nada, pero setea el reloj con el parámetro de entrada.
   }

   /**
   * Este método sirve para hacer que el cronómetro se valide y además se ponga en el display, tiene la condición de que isLive() la
   * cual si es falsa, no permite el funcionamiento del reloj en pantalla.
   */

public void run() {
	//Mediante un try - catch hacemos que el programa tenga robustez frente a errores.
	try {
		while (isLive()) {
			synchronized(this) {
				while (!isGo())
					wait();
			}
			Thread.sleep(1000);
			segundos++;
			actualizarThread();
			//Mediante el uso de bloques de decisión limitamos el tiempo de cada jugador a 30 por turno y seteamos si pierde o no el turno a este límite de tiempo.
			if (getSegundos()==30){
				if (Board_Horses.getTurno() == -1)
				{
					JOptionPane.showMessageDialog(JuegoPrincipal.table, "Mordor pierde su turno");
					//JuegoPrincipal.a_h_turno.setText("Comunidad");
					segundos = 0;
					Board_Horses.cambiaTurno();
					Board_Horses.rePintarTablero();      

				}
				else if (Board_Horses.getTurno() == 1){
					JOptionPane.showMessageDialog(JuegoPrincipal.table, "La comunidad pierde su turno");
					//JuegoPrincipal.a_h_turno.setText("Mordor");
					segundos = 0;
					Board_Horses.cambiaTurno();
					Board_Horses.rePintarTablero();   
				}
			}
		}
	} catch (InterruptedException e) {}
}

/**
 * Este método se crea el hilo para el cronómetro.
 */
   public void createThread() {
       hiloCronometro = new Thread(this);
       hiloCronometro.start();
   }

   /**
    * Este método actualiza el hilo del cronómetro.
    */
   private void actualizarThread() {
       if (isLive() == true) {
           int hr= segundos/3600;
           int min =(segundos-hr*3600)/60;
           seg = segundos-hr*3600-min*60;
           reloj.getDisplay().setText(""+min+" : "+seg);
       } else {
           segundos = 0;
           reloj.getDisplay().setText("0 : 0");
       }
   }

   /**
    * Este método suspende el cronómetro.
    */
   public void suspenderThread() {
       setGo(false);
   }


   /**
    * Este método continua el avance en el cronómetro.
    */
   public synchronized void continuarThread() {
       setGo(true);
       notify();
   }

   //********** MÉTODOS SET Y GET DE LAS VARIABLES DE TIPO BOOLEAN e INT ************
   /**
    * Returna si esta vivo o no el Cronómetro
    */
   public boolean isLive() {
       return live;
   }

   /**
    * Setea si esta vivo o no el cronómetro.
    */
   public void setLive(boolean live) {
       this.live = live;
   }
   
   /**
    * Retorna el valor del booleano go.
    */
   
   public boolean isGo() {
       return go;
   }

   /**
    * Setea en go el parámetro que requerimos.
    */
   public void setGo(boolean go) {
       this.go = go;
   }

   /**
    * Retorna la cantidad de segundos.
    */
   public static int getSegundos() {
       return segundos;
   }
   
   /**
    * Es un set que recibe un parámetro y setea los segundos.
    */
   public static void setSegundos(int seconds) {
       segundos = seconds;
   }
}
