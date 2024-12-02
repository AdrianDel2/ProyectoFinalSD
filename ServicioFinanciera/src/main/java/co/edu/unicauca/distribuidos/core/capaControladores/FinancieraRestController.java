
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

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FinancieraRespuestaDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IFinancieraService;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController /*Crear na serie de metodos que ofrece servicios rest */
@RequestMapping("/api") /*Toda URL para ofrecer esos servicios web, debe empezar con /api */
@CrossOrigin(origins = "http://localhost:4200",  
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}) 
public class FinancieraRestController{

	@Autowired
	private IFinancieraService financieraService; /*Inyeccion de un objeto que corresponde a la fachada 
											Esta debe tener la mayor responsabilidad para dejar mas limpio el contorlador*/

	@GetMapping("/financiera") 
	public List<FinancieraRespuestaDTO> listarDeudasFinanciera() {
		return financieraService.findAll();
	}

	@GetMapping("/financiera/{id}")/*Tambien se puede enviar este parametro con la anotacion @RequestParam */
	public FinancieraRespuestaDTO consultarCliente(@PathVariable Integer id) {
		FinancieraRespuestaDTO objFinanciera = null;
		objFinanciera = financieraService.findById(id);
		return objFinanciera;
	}

	@DeleteMapping("/financiera/{id}")
	public Boolean eliminarCliente(@PathVariable Integer id) {
		Boolean bandera = false;
		FinancieraRespuestaDTO financieraActual = financieraService.findById(id);
		if (financieraActual != null) {
			bandera = financieraService.delete(id);
		}
		return bandera;
	}

	/*Metodo para buscar varios registros por medio de un id especifico */
	@GetMapping("/financiera/especifico/{id}")
	public List<FinancieraRespuestaDTO> buscarDeudasIdEspecifico(@PathVariable Integer id) {
		try {
			List<FinancieraRespuestaDTO> listaDeudasFinanciera = financieraService.findAllById(id);
			return listaDeudasFinanciera;

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El estudiante con id: " + id + " NO tiene deudas registradas");
		}
	}

	/*Metodo para eliminar varios registros que pertenecen a un estudiante por un id especifico*/
	@DeleteMapping("/financiera/especifico/{id}")
	public Boolean eliminarTodasDeudaFinanciera(@PathVariable Integer id) {

		Boolean bandera = false;
		FinancieraRespuestaDTO deudaActual = financieraService.findById(id);
		if (deudaActual != null) {
			bandera = financieraService.delete(id);

			if (!bandera) {
				// Si no se eliminaron deudas, muestra un mensaje
				System.out.println("No existen deudas pendientes para el estudiante con id: " + id);
			}
		} else {
			System.out.println("Estudiante con id: " + id + " no fue encontrado en las deudas ");
		}
		return bandera;

	}


	@GetMapping("/financiera/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}

}
