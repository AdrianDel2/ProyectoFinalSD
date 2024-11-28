
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.FinancieraEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.FinancieraRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ClienteDTO;

/*@AllArgsConstructor para no tener que poner el constructor y ya se haria la inyeccion: MEJOR FORMA */
@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class FinancieraServiceImpl implements IFinancieraService {
	/*@Autowired Inyeccion de dependencias por atributo -> Busca en el contenedor un objeto que correpsonda a UsuarioRepository y lo inyecta aqui */
	private FinancieraRepository servicioAccesoBaseDatos; /*Se utiliza el repositorio inyectado para hacer las sentencias SQL */

	private ModelMapper modelMapper; /*Se utiliza el mapeador inyectado para hacer las sentencias SQL */

	/*Inyeccion por constructor -> Es mas recomendada que autowired para hacer la inteccion de dependencias porque FACILITA LAS PRUEBAS UNITARIAS */
	public FinancieraServiceImpl(FinancieraRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ClienteDTO> findAll() {
		/*Obtiene una lista de clienteentity */
		List<FinancieraEntity> clientesEntity = this.servicioAccesoBaseDatos.findAll();
		/*Mapea la lista de clientes entity a clientes DTO */
		List<ClienteDTO> clientesDTO = this.modelMapper.map(clientesEntity, new TypeToken<List<ClienteDTO>>() {
		}.getType());
		return clientesDTO; /*Retornamos una lista DTO, no de entity como tal por seguridad supongo */
	}

	@Override
	public ClienteDTO findById(Integer id) {
		FinancieraEntity objCLienteEntity = this.servicioAccesoBaseDatos.findById(id);
		ClienteDTO clienteDTO = this.modelMapper.map(objCLienteEntity, ClienteDTO.class);
		return clienteDTO;
	}

	@Override
	public ClienteDTO save(ClienteDTO cliente) {
		FinancieraEntity clienteEntity = this.modelMapper.map(cliente, FinancieraEntity.class); /*Conversion de cliente DTO a cliente entity */
		clienteEntity.setCreateAt(new Date()); /*Fija una fecha de creacion */
		FinancieraEntity objCLienteEntity = this.servicioAccesoBaseDatos.save(clienteEntity) /*Almacena cliente entity */;
		ClienteDTO clienteDTO = this.modelMapper.map(objCLienteEntity, ClienteDTO.class) /*Mapeo a cliente DTO */;
		return clienteDTO; /*Retorno cliente DTO */
	}

	@Override
	public ClienteDTO update(Integer id, ClienteDTO cliente) {
		FinancieraEntity clienteEntity = this.modelMapper.map(cliente, FinancieraEntity.class);
		FinancieraEntity clienteEntityActualizado = this.servicioAccesoBaseDatos.update(id, clienteEntity);
		ClienteDTO clienteDTO = this.modelMapper.map(clienteEntityActualizado, ClienteDTO.class);
		return clienteDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}
}
