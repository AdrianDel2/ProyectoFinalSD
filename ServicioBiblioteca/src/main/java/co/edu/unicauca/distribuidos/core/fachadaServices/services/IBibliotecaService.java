
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.BibliotecaRespuestaDTO;

/*Se trabaja con un cliente DTO porque no se debe exponer los entitys al exterior
 * Aqui en los DTO se define que informacion enviar
 */
public interface IBibliotecaService {

	public List<BibliotecaRespuestaDTO> findAll();

	public BibliotecaRespuestaDTO findById(Integer id);
	public List<BibliotecaRespuestaDTO> findAllById(Integer id);
	
	public boolean delete(Integer id);
	public boolean deleteAllById(Integer id);
}
