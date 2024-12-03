
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.DeporteEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.DeporteRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.DeporteRespuestaDTO;

@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class DeporteServiceImpl implements IDeporteService {

	
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
		/*Mapea la lista de deporte entity a deporte DTO */
		List<DeporteRespuestaDTO> deportesDTO = this.modelMapper.map(deportesEntity, new TypeToken<List<DeporteRespuestaDTO>>() {
		}.getType());
		return deportesDTO; /*Retornamos una lista DTO, no de entity como tal*/
	}

	@Override
	public DeporteRespuestaDTO findById(Integer id) {
		DeporteEntity objDeporteEntity = this.servicioAccesoBaseDatos.findById(id);
		if(objDeporteEntity == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " No tiene deudas de implementos registradas");
		}
		DeporteRespuestaDTO deporteDTO = this.modelMapper.map(objDeporteEntity, DeporteRespuestaDTO.class);
		return deporteDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}

	@Override
	public List<DeporteRespuestaDTO> findAllById(Integer id){
		/*Obtiene una lista de deporteentity */
		List<DeporteEntity> deportesEntity = this.servicioAccesoBaseDatos.findAllById(id);
		if(deportesEntity.isEmpty()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " No tiene deudas registradas");
		}
		/*Mapea la lista de deporte entity  a deporte DTO */
		List<DeporteRespuestaDTO> deportesDTO = this.modelMapper.map(deportesEntity, new TypeToken<List<DeporteRespuestaDTO>>() {
		}.getType());
		return deportesDTO;
	}
	@Override
	public boolean deleteAllById(Integer id){
		return this.servicioAccesoBaseDatos.deleteAllById(id);

	}
}
