
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaPazSalvoDTO;
import reactor.core.publisher.Mono;

/*Se trabaja con un cliente DTO porque no se debe exponer los entitys al exterior
 * Aqui en los DTO se define que informacion enviar
 */
public interface IGenerarPazSalvoService {

	public RespuestaPazSalvoDTO generarPazSalvoSincrono (Integer idEstudiante);
	public Mono<RespuestaPazSalvoDTO>generarPazSalvoAsincrono(Integer idEstudiante);

	//public boolean delete(Integer id);
}
