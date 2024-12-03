package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.FinancieraEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.FinancieraRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.FinancieraRespuestaDTO;

@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class FinancieraServiceImpl implements IFinancieraService {
	
	private FinancieraRepository servicioAccesoBaseDatos; /*Se utiliza el repositorio inyectado para hacer las sentencias SQL */

	private ModelMapper modelMapper; /*Se utiliza el mapeador inyectado para hacer las sentencias SQL */

	/*Inyeccion por constructor -> Es mas recomendada que autowired para hacer la inteccion de dependencias porque FACILITA LAS PRUEBAS UNITARIAS */
	public FinancieraServiceImpl(FinancieraRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<FinancieraRespuestaDTO> findAll() {
		/*Obtiene una lista de clienteentity */
		List<FinancieraEntity> clientesEntity = this.servicioAccesoBaseDatos.findAll();
		/*Mapea la lista de financiera entity a financiera DTO */
		List<FinancieraRespuestaDTO> FinancieraDTO = this.modelMapper.map(clientesEntity, new TypeToken<List<FinancieraRespuestaDTO>>() {
		}.getType());
		return FinancieraDTO; /*Retornamos una lista DTO, no de entity como tal*/
	}

	@Override
	public FinancieraRespuestaDTO findById(Integer id) {
		FinancieraEntity objFinancieraEntity = this.servicioAccesoBaseDatos.findById(id);
		if (objFinancieraEntity == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " no fue encontrado en las deudas del area de financiera");
		}
		FinancieraRespuestaDTO financieraDTO = this.modelMapper.map(objFinancieraEntity, FinancieraRespuestaDTO.class);
		return financieraDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}
	
	@Override
	public List<FinancieraRespuestaDTO> findAllById(Integer id){

		List<FinancieraEntity> financieraEntity = this.servicioAccesoBaseDatos.findAllById(id);
		if (financieraEntity.isEmpty()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " NO tiene deudas registradas");
		}
		List<FinancieraRespuestaDTO> financieraRespuestaDTO = this.modelMapper.map(financieraEntity, new TypeToken<List<FinancieraRespuestaDTO>>() {
		}.getType());
		return financieraRespuestaDTO;
	}

	@Override
	public boolean deleteAllById(Integer id){
		return this.servicioAccesoBaseDatos.deleteAllById(id);
	}
}
