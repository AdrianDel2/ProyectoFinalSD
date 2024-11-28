
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

@RestController /*Crear na serie de metodos que ofrece servicios rest */
@RequestMapping("/api") /*Toda URL para ofrecer esos servicios web, debe empezar con /api */
@CrossOrigin(origins = "http://localhost:4200",  
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}) 
public class DeporteRestController {

	@Autowired
	private IDeporteService deporteService; /*Inyeccion de un objeto que corresponde a la fachada 
											Esta debe tener la mayor responsabilidad para dejar mas limpio el contorlador*/

	@GetMapping("/clientes") 
	public List<DeporteRespuestaDTO> listarClientes() {
		return deporteService.findAll();
	}

	@GetMapping("/clientes/{id}")/*Tambien se puede enviar este parametro con la anotacion @RequestParam */
	public DeporteRespuestaDTO consultarCliente(@PathVariable Integer id) {
		DeporteRespuestaDTO objDeporte = null;
		objDeporte = deporteService.findById(id);
		return objDeporte;
	}

	@GetMapping("consultarClientes")
	public String consultarClientesConVariosParametros(@RequestParam String nombres,
			@RequestParam String apellidos) {
		String msg = String.format("buscando un cliente por nombre: %s, apellidos: %s", nombres, apellidos);
		System.out.println(msg);
		return msg;
	}

	@DeleteMapping("/clientes/{id}")
	public Boolean eliminarCliente(@PathVariable Integer id) {
		Boolean bandera = false;
		DeporteRespuestaDTO DeporteActual = deporteService.findById(id);
		if (DeporteActual != null) {
			bandera = deporteService.delete(id);
		}
		return bandera;
	}

	@GetMapping("/clientes/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}

}
