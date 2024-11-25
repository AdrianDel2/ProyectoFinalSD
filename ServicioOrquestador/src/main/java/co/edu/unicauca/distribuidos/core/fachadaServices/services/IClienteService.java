
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ClienteDTO;

/*Se trabaja con un cliente DTO porque no se debe exponer los entitys al exterior
 * Aqui en los DTO se define que informacion enviar
 */
public interface IClienteService {

	public List<ClienteDTO> findAll();

	public ClienteDTO findById(Integer id);

	public ClienteDTO save(ClienteDTO cliente);

	public ClienteDTO update(Integer id, ClienteDTO cliente);

	public boolean delete(Integer id);
}
