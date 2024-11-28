
package co.edu.unicauca.distribuidos.core.capaControladores;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.server.ResponseStatusException;

//import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ClienteDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.services.IBibliotecaService;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.BibliotecaRespuestaDTO;
//import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ClienteDTO;

@RestController /*Crear na serie de metodos que ofrece servicios rest */
@RequestMapping("/api") /*Toda URL para ofrecer esos servicios web, debe empezar con /api */
@CrossOrigin(origins = "http://localhost:4200",  
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}) 
public class ClienteRestController {

	@Autowired
	private IBibliotecaService bibliotecaService; /*Inyeccion de un objeto que corresponde a la fachada 
											Esta debe tener la mayor responsabilidad para dejar mas limpio el contorlador*/

	@GetMapping("/clientes") 
	public List<BibliotecaRespuestaDTO> listaDeudasBiblioteca() {
		return bibliotecaService.findAll();
	}

	@GetMapping("/clientes/{id}")/*Tambien se puede enviar este parametro con la anotacion @RequestParam */
	public BibliotecaRespuestaDTO consultarDeudasBiblioteca(@PathVariable Integer id) {
		try{
			BibliotecaRespuestaDTO objBiblioteca = bibliotecaService.findById(id);
			return objBiblioteca;

		}catch(NoSuchElementException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " no fue encontrado en las deudas de préstamos de libro");
		}
	
	}

	@GetMapping("consultarClientes")
	public String consultarClientesConVariosParametros(@RequestParam String nombreEstudiante,
			@RequestParam String apellidoEstudiante) {
		String msg = String.format("buscando un cliente por nombre: %s, apellidos: %s", nombreEstudiante,apellidoEstudiante );
		System.out.println(msg);
		return msg;
	}
	
	@DeleteMapping("/clientes/{id}")
	public Boolean eliminarDeudaBiblioteca(@PathVariable Integer id) {
		Boolean bandera = false;
		BibliotecaRespuestaDTO deudaActual = bibliotecaService.findById(id);
		if (deudaActual != null) {
			bandera = bibliotecaService.delete(id);
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
