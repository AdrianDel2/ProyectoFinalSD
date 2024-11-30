
package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaPazSalvoDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IGenerarPazSalvoService;
import reactor.core.publisher.Mono;

@RestController /*Crear na serie de metodos que ofrece servicios rest */
@RequestMapping("/api") /*Toda URL para ofrecer esos servicios web, debe empezar con /api */
public class PazSalvoRestController {

	@Autowired
	private IGenerarPazSalvoService objFachada; /*Inyeccion de un objeto que corresponde a la fachada 
											Esta debe tener la mayor responsabilidad para dejar mas limpio el contorlador*/

	//@PathVariable indica que el valor del parámetro se extraerá de la parte correspondiente de la URL										
	@GetMapping("/orquestadorSincrono/{id}")						
	public RespuestaPazSalvoDTO orquestarServicioSincronicamente (@PathVariable int idEstudiante ){
		RespuestaPazSalvoDTO objResultado = this.objFachada.generarPazSalvoSincrono(idEstudiante);
		return objResultado;
	}

	@GetMapping("/orquestadorAsincrono/{id}")
	public Mono<RespuestaPazSalvoDTO> orquestarServicioAsincronicamente(@PathVariable int idEstudiante){
		Mono<RespuestaPazSalvoDTO> objResultado = this.objFachada.generarPazSalvoAsincrono(idEstudiante);
		return objResultado;
	}

}
