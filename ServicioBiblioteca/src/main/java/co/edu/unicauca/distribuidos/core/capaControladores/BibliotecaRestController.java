
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

import co.edu.unicauca.distribuidos.core.fachadaServices.services.IBibliotecaService;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.BibliotecaRespuestaDTO;


@RestController /*Crear una serie de metodos que ofrece servicios rest */
@RequestMapping("/api") /*Toda URL para ofrecer esos servicios web, debe empezar con /api */
@CrossOrigin(origins = "http://localhost:4200",  
methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE}) 
public class BibliotecaRestController {

	@Autowired
	private IBibliotecaService bibliotecaService; /*Inyeccion de un objeto que corresponde a la fachada 
											Esta debe tener la mayor responsabilidad para dejar mas limpio el contorlador*/

	/*Metodo para listar todos los estudiantes*/
	@GetMapping("/bibliotecas") 
	public List<BibliotecaRespuestaDTO> listaDeudasBiblioteca() {
		return bibliotecaService.findAll();
	}

	/*Metodo para buscar a un estudiante por un id especifico*/
	@GetMapping("/bibliotecas/{id}")/*Tambien se puede enviar este parametro con la anotacion @RequestParam */
	public BibliotecaRespuestaDTO consultarDeudasBiblioteca(@PathVariable Integer id) {
		try{
			BibliotecaRespuestaDTO objBiblioteca = bibliotecaService.findById(id);
			return objBiblioteca;

		}catch(ResponseStatusException e){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " no fue encontrado en las deudas de préstamos de libro");
		}
	}
	/*Metodo para eliminar a un estudiante por un id especifico*/
	@DeleteMapping("/bibliotecas/{id}")
	public Boolean eliminarDeudaBiblioteca(@PathVariable Integer id) {
		Boolean bandera = false;
		BibliotecaRespuestaDTO deudaActual = bibliotecaService.findById(id);
		if (deudaActual != null) {
			bandera = bibliotecaService.delete(id);
		}
		return bandera;
	}
	/*Metodo para buscar varios registros por medio de un id especifico */
	@GetMapping("/bibliotecas/especifico/{id}")
	public List<BibliotecaRespuestaDTO> buscarDeudasIdEspecifico(@PathVariable Integer id) {
		try {
			List<BibliotecaRespuestaDTO> listaDeudasBiblioteca = bibliotecaService.findAllById(id);
			return listaDeudasBiblioteca;

		} catch (ResponseStatusException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El estudiante con id: " + id + " NO tiene deudas de préstamos de libro registradas");
		}
	}

	/*Metodo para eliminar varios registros que pertenecen a un estudiante por un id especifico*/
	@DeleteMapping("/bibliotecas/especifico/{id}")
	public Boolean eliminarTodasDeudaBiblioteca(@PathVariable Integer id) {

		Boolean bandera = false;
		BibliotecaRespuestaDTO deudaActual = bibliotecaService.findById(id);
		if (deudaActual != null) {
			bandera = bibliotecaService.deleteAllById(id);
		} 
		return bandera;

	}

	@GetMapping("/bibliotecas/listarCabeceras")
	public void listarCabeceras(@RequestHeader Map<String, String> headers) {
		System.out.println("cabeceras");
		headers.forEach((key, value) -> {
			System.out.println(String.format("Cabecera '%s' = %s", key, value));
		});
	}

}
