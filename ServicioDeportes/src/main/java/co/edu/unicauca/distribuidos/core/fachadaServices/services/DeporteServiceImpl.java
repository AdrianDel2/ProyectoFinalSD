
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.DeporteEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.DeporteRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.DeporteRespuestaDTO;

/*@AllArgsConstructor para no tener que poner el constructor y ya se haria la inyeccion: MEJOR FORMA */
@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class DeporteServiceImpl implements IDeporteService {

	/*@Autowired Inyeccion de dependencias por atributo -> Busca en el contenedor un objeto que correpsonda a UsuarioRepository y lo inyecta aqui */
	private DeporteRepository servicioAccesoBaseDatos; /*Se utiliza el repositorio inyectado para hacer las sentencias SQL */

	private ModelMapper modelMapper; /*Se utiliza el mapeador inyectado para hacer las sentencias SQL */

	/*Inyeccion por constructor -> Es mas recomendada que autowired para hacer la inteccion de dependencias porque FACILITA LAS PRUEBAS UNITARIAS */
	public DeporteServiceImpl(DeporteRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<DeporteRespuestaDTO> findAll() {
		/*Obtiene una lista de deporteentity */
		List<DeporteEntity> deportesEntity = this.servicioAccesoBaseDatos.findAll();
		/*Mapea la lista de clientes entity a clientes DTO */
		List<DeporteRespuestaDTO> deportesDTO = this.modelMapper.map(deportesEntity, new TypeToken<List<DeporteRespuestaDTO>>() {
		}.getType());
		return deportesDTO; /*Retornamos una lista DTO, no de entity como tal por seguridad supongo */
	}

	@Override
	public DeporteRespuestaDTO findById(Integer id) {
		DeporteEntity objDeporteEntity = this.servicioAccesoBaseDatos.findById(id);
		DeporteRespuestaDTO deporteDTO = this.modelMapper.map(objDeporteEntity, DeporteRespuestaDTO.class);
		return deporteDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}
}
