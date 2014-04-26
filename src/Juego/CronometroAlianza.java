//Se realizan los import necesarios así como el package a juego.
package Juego;

import Juego.CronometroThreadAlianza;
import javax.swing.JPanel;
import javax.swing.JLabel;



public class CronometroAlianza extends JPanel { //Se crea la Clase CronometroAlianza que extiende de Jpanel
	private javax.swing.JLabel display; // Se define como privado el JLabel display 
	public static CronometroThreadAlianza cronometroJ; //Se crea la variable global del cronometroJ
	Object source;

	/**
	* Se crea la nueva forma Cronómetro Alianza.
	*/
   public CronometroAlianza() {
       initComponents();
       cronometroJ = new CronometroThreadAlianza(this);
       startActionPerformed();

   }

   /**
    * Este método es llamado por el constructor del CronómetroAlianza para iniciar sus componentes.
    */
   private void initComponents() {
	   setLayout(null);
       display = new JLabel();
       setBackground(new java.awt.Color(240, 240, 240));

       display.setFont(new java.awt.Font("Times New Roman", 0, 50)); 
       display.setText("0 : 0");
       display.setBounds(33, 11, 150, 60);

       add(display);
       //Aquí ya ha agregado el display al Cronómetro además de setear sus parámetros.
   }

   /**
    * Este método se usa para activar las acciones del cronómetro.
    */
   public static void startActionPerformed() {
           cronometroJ.createThread();
           cronometroJ.setLive(true);
           cronometroJ.setGo(true);
   }


   /**
    * Este método se usa para pausar las acciones del cronómetro.
    */
   
   public static void detenerAlianza() {
       cronometroJ.suspenderThread();
   }

   /**
    * Este método se usa para continuar las acciones del cronómetro.
    */
   public static void bcontinueActionPerformed() {
       cronometroJ.continuarThread();
   }

   /**
    * Este método se usa para detener las acciones del cronómetro.
    */
   public static void stopActionPerformed() {
       cronometroJ.setLive(false);
       cronometroJ.setGo(false);
       cronometroJ.setSegundos(0);
   }

   /**
    * @retorna el display del Cronómetro.
    */
   public javax.swing.JLabel getDisplay() {
       return display;
   }


   /**
    * Este método setea el display cronómetro.
    */
   public void setDisplay(javax.swing.JLabel display) {
       this.display = display;
   }
}
