
package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.DeporteRespuestaDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IDeporteService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
@RestController /*Crear na serie de metodos que ofrece servicios rest */
@RequestMapping("/api") /*Toda URL para ofrecer esos servicios web, debe empezar con /api */
@CrossOrigin(origins = "http://localhost:4200",  
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) 
public class DeporteRestController {

	@Autowired
	private IDeporteService deporteService; /*Inyeccion de un objeto que corresponde a la fachada 
											Esta debe tener la mayor responsabilidad para dejar mas limpio el contorlador*/

	@GetMapping("/deportes") 
	public List<DeporteRespuestaDTO> listarDeudasDeporte() {
		return deporteService.findAll();
	}

	@GetMapping("/deportes/{id}")/*Tambien se puede enviar este parametro con la anotacion @RequestParam */
	public DeporteRespuestaDTO consultarDeudasDeporte(@PathVariable Integer id) {
		try{

			DeporteRespuestaDTO objDeporte = deporteService.findById(id);
			return objDeporte;
			
		}catch(ResponseStatusException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El estudiante con id: " + id + " No tiene deudas de implementos registradas");
		}
	}

	@DeleteMapping("/deportes/{id}")
	public Boolean eliminarDeudasDeporte(@PathVariable Integer id) {
		Boolean bandera = false;
		DeporteRespuestaDTO DeporteActual = deporteService.findById(id);
		if (DeporteActual != null) {
			bandera = deporteService.delete(id);
		}
		return bandera;
	}

	/*Metodo para buscar varios registros por medio de un id especifico */
	@GetMapping("/deportes/especifico/{id}")
	public List<DeporteRespuestaDTO> buscarDeudasIdEspecifico(@PathVariable Integer id){
		try{

			List<DeporteRespuestaDTO> listaDeudasDeporte = deporteService.findAllById(id);
			return listaDeudasDeporte;
		}catch(ResponseStatusException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"El estudiante con id: " + id + " No tiene deudas de implementos registradas");
		}
	}
	/*Metodo para eliminar varios registros que pertenecen a un estudiante por un id especifico*/
	@DeleteMapping("/deportes/especifico/{id}")
	public Boolean eliminarTodasDeudasDeporte(@PathVariable Integer id){
		Boolean bandera = false;
		DeporteRespuestaDTO DeporteActual = deporteService.findById(id);
		if (DeporteActual != null) {
			bandera = deporteService.deleteAllById(id);
		}
		return bandera;
	}

	@GetMapping("/deportes/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}
}
