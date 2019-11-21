package businesslayer;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;

public class Rutas {
	
	   private int lugares;
	   LinkedList<Ruta> [] listadyacencia;
	  
	   public Rutas(int lugares) {
	            this.lugares= lugares;
	            listadyacencia = new LinkedList[lugares];
	            for (int i = 0; i <lugares ; i++) {
	                listadyacencia[i] = new LinkedList<>();
	            }
	   }

	   public void agregarLugar(int origen, int destino, int distancia) {
	          int i=origen-1;
	          Ruta ruta = new Ruta(origen, destino, distancia);
	          listadyacencia[i].addFirst(ruta);
	   }

	   public void mostrarRutas(){
	     for (int i = 0; i <lugares ; i++) {
	        LinkedList<Ruta> lista = listadyacencia[i];
	        for (int j = 0; j <lista.size() ; j++) {
	          System.out.println("La Ruta con origen " + lista.get(j).origen + " es adyacente al destino " +
	                            (lista.get(j).destino) + " con distancia " +  lista.get(j).distancia);
	        }
	     }
	   }
	   public Hashtable<String,String>  rutaMasCorta(int origen,int destino) {
	       HashMap<Integer, Integer> cambioEn = new HashMap<>();
	       boolean[] visitado=new boolean[lugares];
	       cambioEn.put(origen, 0);
	       HashMap<Integer,Integer>camino=new HashMap<Integer,Integer>();
	       for(int i=0;i<lugares;i++) {
	           if(i+1==origen)
	        	  camino.put(i+1, 0);
	        	else
	        	  camino.put(i+1,Integer.MAX_VALUE);
	        			
	       }
	       LinkedList<Ruta> lista=listadyacencia[origen-1];
	       for (Ruta arista : lista) {
	                camino.put(arista.destino, arista.distancia);
	                cambioEn.put(arista.destino, origen);
	       }
	       visitado[origen-1]=true;
	       while(true) {
	        		int actual=destinoMasCercanoNoVisitado(camino,visitado);
	                if(actual==0) {
	                	return null;
	                }
	                if(actual==destino) {
	                	int hijo=destino;
	                	String caminocorto=Integer.toString(destino);
	                	while(true) {
	                		int padre=cambioEn.get(hijo);
	                		if(padre == 0)
	                			break;
	                		caminocorto=padre+"->"+caminocorto;
	                		hijo=padre;
	                	}
	                	Hashtable<String,String> resultado=new Hashtable<String,String>();
	                	resultado.put("ruta", caminocorto);
	                	resultado.put("distancia",camino.get(destino).toString());
	                	return resultado;
	                }
            visitado[actual-1]=true;
	                lista=listadyacencia[actual-1]; 
	        		for(Ruta arista:lista) {
	        			if(visitado[arista.destino-1])
	        				continue;
	        			if(camino.get(actual)+arista.distancia<camino.get(arista.destino)) {
	        				camino.put(arista.destino, camino.get(actual)+arista.distancia);
	        				cambioEn.put(arista.destino,actual);
	        			}
	        		}
	        	}
	        }
	   
	   private int destinoMasCercanoNoVisitado(HashMap<Integer,Integer>camino,boolean[] visitado) {
	        	int distanciaMinima=Integer.MAX_VALUE;
	        	int verticeMasCercano=0;
	        	for(int i=0;i<lugares;i++) {
	        		if(visitado[i]) 
	        			continue;
	        		int distanciaActual=camino.get(i+1);  
	        		if(distanciaActual==Integer.MAX_VALUE)
	        			continue;
	        		if(distanciaActual<distanciaMinima) {
	        			distanciaMinima=distanciaActual;
	        			verticeMasCercano=i+1;
	        		}
	        	}
	        	return verticeMasCercano;
	    }
}
