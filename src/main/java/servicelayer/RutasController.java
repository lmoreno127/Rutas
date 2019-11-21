package servicelayer;

import java.util.Hashtable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RutasController {
   
	@Autowired
	RutasService rs;
	
	
    @GetMapping("/")
	public Hashtable<String,String>  rutaMasCorta(@RequestParam("origen") String sorigen,@RequestParam("destino") String sdestino){
		int origen=Integer.parseInt(sorigen);
		int destino=Integer.parseInt(sdestino);
		Hashtable<String,String>respuesta=new Hashtable<String,String>();
		Hashtable<String,String>resultado=rs.rutaMasCorta(origen, destino);
		if(resultado!=null) {
			respuesta.put("RutaMasCorta",resultado.get("ruta"));
			respuesta.put("Distancia",resultado.get("distancia"));
		}
		else
			respuesta.put("ERROR","No hay camino entre "+origen+" y "+destino);
		return respuesta;
	}
}
