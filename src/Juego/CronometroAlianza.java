//Se realizan los import necesarios as� como el package a juego.
package Juego;

import Juego.CronometroThreadAlianza;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class CronometroAlianza extends JPanel { //Se crea la Clase CronometroAlianza que extiende de Jpanel
	private javax.swing.JLabel display; // Se define como privado el JLabel display 
	public static CronometroThreadAlianza cronometroJ; //Se crea la variable global del cronometroJ
	Object source;

	/**
	* Se crea la nueva forma Cron�metro Alianza.
	*/
   public CronometroAlianza() {
       initComponents();
       cronometroJ = new CronometroThreadAlianza(this);
       startActionPerformed();

   }

   /**
    * Este m�todo es llamado por el constructor del Cron�metroAlianza para iniciar sus componentes.
    */
   private void initComponents() {
	   setLayout(null);
       display = new JLabel();
       setBackground(new java.awt.Color(240, 240, 240));

       display.setFont(new java.awt.Font("Times New Roman", 0, 50)); 
       display.setText("0 : 0");
       display.setBounds(33, 11, 150, 60);

       add(display);
       //Aqu� ya ha agregado el display al Cron�metro adem�s de setear sus par�metros.
   }

   /**
    * Este m�todo se usa para activar las acciones del cron�metro.
    */
   public static void startActionPerformed() {
           cronometroJ.createThread();
           cronometroJ.setLive(true);
           cronometroJ.setGo(true);
   }


   /**
    * Este m�todo se usa para pausar las acciones del cron�metro.
    */
   
   public static void detenerAlianza() {
       cronometroJ.suspenderThread();
   }

   /**
    * Este m�todo se usa para continuar las acciones del cron�metro.
    */
   public static void bcontinueActionPerformed() {
       cronometroJ.continuarThread();
   }

   /**
    * Este m�todo se usa para detener las acciones del cron�metro.
    */
   public static void stopActionPerformed() {
       cronometroJ.setLive(false);
       cronometroJ.setGo(false);
       cronometroJ.setSegundos(0);
   }

   /**
    * @retorna el display del Cron�metro.
    */
   public javax.swing.JLabel getDisplay() {
       return display;
   }


   /**
    * Este m�todo setea el display cron�metro.
    */
   public void setDisplay(javax.swing.JLabel display) {
       this.display = display;
   }
}
