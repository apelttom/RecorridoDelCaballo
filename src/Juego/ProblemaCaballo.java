package Juego;

import java.util.ArrayList;

import Piezas.Caballo_1;

public class ProblemaCaballo {
   public static int numFilas;
    public static int numColumnas;
    public static int[][] tablero;
    public static int     contador;
    public static ArrayList filas=new ArrayList();
    public static ArrayList columnas=new ArrayList();
 
    public ProblemaCaballo(int nf, int nc) {
        numFilas = nf;
        numColumnas = nc;
        tablero     = new int[nf][nc];
    }
 
    public void mostrarTablero() {
        for(int i=0; i<tablero.length; i++) {
            for(int j=0; j<tablero[i].length; j++) {
                System.out.printf("  %2d  |", tablero[i][j]);
            }
            System.out.println();
            for(int j=0; j<tablero[i].length; j++) System.out.print("------+");
            System.out.println();
        }
    }
 
    public boolean resolverProblema(int f, int c, int num) {    	
            contador++;
            tablero[f][c] = num; 
            filas.add(f);
            columnas.add(c);                       
            if(num==numFilas*numColumnas) return true;
            int[][] posibles = buscarPosibles(f, c);
            ordenarPosibles(posibles);              
            for(int i=0; i<posibles.length; i++) {
                if(resolverProblema(posibles[i][0], posibles[i][1], num+1)) 
                {                	
                	return true;
                }
                else{
                	return false;
                }
            }
            tablero[f][c]=0;
            return false;
    }
    

    void ordenarPosibles(int[][] posibles) {
        for(int i=0; i<posibles.length; i++) {
            for(int j=i+1; j<posibles.length; j++) {
                int cuantosPosiblesI = buscarPosibles(posibles[i][0], posibles[i][1]).length;
                int cuantosPosiblesJ = buscarPosibles(posibles[j][0], posibles[j][1]).length;
                if(cuantosPosiblesJ<cuantosPosiblesI) {
                    int tmp0 = posibles[i][0];
                    posibles[i][0] = posibles[j][0];
                    posibles[j][0] = tmp0;
                    int tmp1 = posibles[i][1];
                    posibles[i][1] = posibles[j][1];
                    posibles[j][1] = tmp1;
                }
            }
        }
    }
 
    int[][] buscarPosibles(int f, int c) {
        int[][] resp = new int[8][2];
        int     pos  = 0;
        if(esValido(f-2,c-1)){ resp[pos][0]=f-2; resp[pos++][1]=c-1; }
        if(esValido(f-2,c+1)){ resp[pos][0]=f-2; resp[pos++][1]=c+1; }
        if(esValido(f-1,c-2)){ resp[pos][0]=f-1; resp[pos++][1]=c-2; }
        if(esValido(f-1,c+2)){ resp[pos][0]=f-1; resp[pos++][1]=c+2; }
        if(esValido(f+2,c-1)){ resp[pos][0]=f+2; resp[pos++][1]=c-1; }
        if(esValido(f+2,c+1)){ resp[pos][0]=f+2; resp[pos++][1]=c+1; }
        if(esValido(f+1,c-2)){ resp[pos][0]=f+1; resp[pos++][1]=c-2; }
        if(esValido(f+1,c+2)){ resp[pos][0]=f+1; resp[pos++][1]=c+2; }
        int[][] tmp = new int[pos][2];
        for(int i=0; i<pos; i++) { tmp[i][0] = resp[i][0]; tmp[i][1]=resp[i][1]; }
        return tmp;
    }
 
    boolean esValido(int f, int c) {
        if(f<0 || f>numFilas-1 || c<0 || c>numColumnas-1) return false;
        if(tablero[f][c]!=0) return false;
        return true;
    }
 
}