package servicelayer;

import java.util.Hashtable;

import org.springframework.stereotype.Service;

import businesslayer.Rutas;

@Service
public class RutasService {
	private int lugares;
	private  Rutas rutas;
	public RutasService() {
		lugares = 5;
	    rutas = new Rutas(lugares);
	    rutas.agregarLugar(1, 2, 7);
	    rutas.agregarLugar(1, 4, 2);
	    rutas.agregarLugar(2, 3, 1);
	    rutas.agregarLugar(2,4,2);
	    rutas.agregarLugar(3,5,5);
	    rutas.agregarLugar(4, 2,3);
	    rutas.agregarLugar(4, 5, 5);
	    rutas.agregarLugar(4, 3, 8);
	    rutas.agregarLugar(5, 3, 4);
	}
    public Hashtable<String,String> rutaMasCorta(int origen,int destino){
    	return rutas.rutaMasCorta(origen, destino);
    }
}
