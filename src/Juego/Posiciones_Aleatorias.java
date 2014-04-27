package Juego;

import java.util.ArrayList;

public class Posiciones_Aleatorias {
	   private int valorInicial;
	   private int valorFinal;
	    private ArrayList<Integer> Lista_Numeros_Aleatorios;// array que contiene una lista con los números aleatorios generados 
	    
	    public Posiciones_Aleatorias(int valorInicial, int valorFinal){
	        this.valorInicial = valorInicial;//recibe como parámetro el valor incial de partida del rango de números para la generación de números aleatorios
	        this.valorFinal = valorFinal;//recibe como parámetro el valor final de partida del rango de números para la generación de números aleatorios, en este caso será acorde a la cantidad de flas y columnas que eligio el ususario por la dimension del tablero
	        Lista_Numeros_Aleatorios = new ArrayList<Integer>();
	    }
	    
	    private int numeroAleatorio(){//Método que genrará uno a uno, un número aleatorio del rango estabelcido en el método Posiciones_Aleatorias
	        return (int)(Math.random()*(valorFinal-valorInicial+1)+valorInicial);//retorna el número que genera
	    }
	    
	    public int generar_numero_aleatorio(){
	        if(Lista_Numeros_Aleatorios.size() < (valorFinal - valorInicial)+1){//Aun no se han generado todos los números
	            int numero = numeroAleatorio();//genero un numero
	            if(Lista_Numeros_Aleatorios.isEmpty()){//si la lista esta vacia
	                Lista_Numeros_Aleatorios.add(numero);
	                return numero;
	            }
	            else{//si no esta vacia
	                if(Lista_Numeros_Aleatorios.contains(numero)){//Si el numero que generÃ© esta contenido en la lista
	                    return generar_numero_aleatorio();//recursivamente lo mando a generar otra vez
	                }else{//Si no esta contenido en la lista
	                    Lista_Numeros_Aleatorios.add(numero);
	                    return numero;
	                }
	            }
	        }
	        else{// ya se generaron todos los numeros
	            return -1;
	        }
	    }

}
