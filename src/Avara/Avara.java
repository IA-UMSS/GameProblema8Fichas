
package Avara;

import java.util.ArrayList;
import java.util.Arrays;

public class Avara{

public static void main (String args[]){
int[][] inicio ={{0,2,3},{1,4,5},{7,8,6}};
int[][] solucion ={{1,2,3},{4,5,6},{7,8,0}};
Nodo inicial =new Nodo (inicio);
Nodo sol=buscarSolucion(inicial,solucion);
 
while(sol.padre!=null){
  imprimirEstado(sol.getEstado());
  System.out.println("----------------");
  sol=sol.padre;
 }
}

public static  Nodo buscarSolucion(Nodo inicio, int[][]solucion){
    ArrayList<Nodo> expandidos=new ArrayList<Nodo>();
    ArrayList<Nodo> visitados=new ArrayList<Nodo>();
    expandidos.add(inicio);
      int cont=0;
    
    while(expandidos.size()!=0){
       Nodo revisar=expandidos.remove(0);
       imprimirEstado(revisar.getEstado());
       int[]pcero = ubicarPosicionCero(revisar.getEstado());
       System.out.println("interacion nro; "+(++cont));
       if (Arrays.deepEquals(revisar.getEstado(), solucion)){
        System.out.println("*****SOLUCION ENCONTRADA***");
       return revisar;
       }   
    
       ArrayList<Nodo> hijos=new ArrayList<Nodo>();
           
      visitados.add(revisar);
      if(pcero[0]!=0){
      Nodo hijo =new Nodo(clonar(revisar.getEstado()));
      int arriba=hijo.getEstado()[pcero[0]-1][pcero[1]];
      hijo.getEstado()[pcero[0]][pcero[1]]=arriba;
      hijo.getEstado()[pcero[0]-1][pcero[1]]=0;
       if(!estaEnVisitados(visitados,hijo))
          expandidos.add(hijo);
          hijos.add(hijo);
        }
    
         if(pcero[0]!=2){
          Nodo hijo =new Nodo(clonar(revisar.getEstado()));
          int abajo=hijo.getEstado()[pcero[0]+1][pcero[1]];
          hijo.getEstado()[pcero[0]][pcero[1]]=abajo;
          hijo.getEstado()[pcero[0]+1][pcero[1]]=0;
          if(!estaEnVisitados(visitados,hijo))
             expandidos.add(hijo);
             hijos.add(hijo);
       }
    
        if(pcero[1]!=0){
        Nodo hijo =new Nodo(clonar(revisar.getEstado()));
        int izquierda=hijo.getEstado()[pcero[0]][pcero[1]-1];
        hijo.getEstado()[pcero[0]][pcero[1]]=izquierda;
        hijo.getEstado()[pcero[0]][pcero[1]-1]=0;
        if(!estaEnVisitados(visitados,hijo))
        expandidos.add(hijo);
         hijos.add(hijo);
       }
     
      if(pcero[1]!=2){
      Nodo hijo =new Nodo(clonar(revisar.getEstado()));
      int derecha=hijo.getEstado()[pcero[0]][pcero[1]+1];
      hijo.getEstado()[pcero[0]][pcero[1]]=derecha;
      hijo.getEstado()[pcero[0]][pcero[1]+1]=0;
      if(!estaEnVisitados(visitados,hijo))
        expandidos.add(hijo);
        hijos.add(hijo);
  
       }
      revisar.setHijos(hijos);
    }
    return null;
}
       

public static void imprimirEstado(int [][]estado){
       int[]posicion=new int[2];
       for(int i=0;i<estado.length;i++){
         for(int j=0;j<estado.length;j++){
           System.out.print("["+estado[i][j]+"]");
            }    
         System.out.println("");
    }
  
}

public static int[] ubicarPosicionCero(int[][]estado){
  int []posicion=new int[2];
  for(int i=0;i<estado.length;i++){
   for(int j=0;j<estado.length;j++){
      if (estado[i][j]==0){
       posicion[0]=i;
       posicion[1]=j;
    }
   }
  }
  System.out.println("la posicion del espacio en:"+posicion[0]+","+posicion[1]);
  return posicion;
 }

private static int [][]clonar (int[][]estado){
  int[][] clon=new int[estado.length][];
   for(int i=0;i<estado.length;i++){ 
       clon[i]=estado[i].clone();
  }
  return clon;   
  }

   
private static boolean estaEnVisitados(ArrayList<Nodo>visitados,Nodo hijo){
   for(Nodo v:visitados){
   if(Arrays.deepEquals(v.getEstado(),hijo.getEstado())){
       return true;
    }
   }
   return false;
 }
}
    
