
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaPazSalvoDTO;
import reactor.core.publisher.Mono;

/**
 * Interface que define los metodos que se deben implementar para generar un paz y salvo
 * de un estudiantes*/
public interface IGenerarPazSalvoService {

	public RespuestaPazSalvoDTO generarPazSalvoSincrono (int idEstudiante);
	public Mono<RespuestaPazSalvoDTO>generarPazSalvoAsincrono(int idEstudiante);

	//public boolean delete(Integer id);
}
