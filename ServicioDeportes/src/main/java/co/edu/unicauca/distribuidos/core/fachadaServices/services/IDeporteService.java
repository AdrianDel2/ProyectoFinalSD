
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.DeporteRespuestaDTO;

/*Se trabaja con un cliente DTO porque no se debe exponer los entitys al exterior
 * Aqui en los DTO se define que informacion enviar
 */
public interface IDeporteService {

	public List<DeporteRespuestaDTO> findAll();

	public DeporteRespuestaDTO findById(Integer id);
	
	public boolean delete(Integer id);
}
