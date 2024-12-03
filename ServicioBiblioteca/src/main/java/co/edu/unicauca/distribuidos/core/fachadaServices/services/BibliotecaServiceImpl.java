
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.BibliotecaEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.BibliotecaRepository;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.BibliotecaRespuestaDTO;

@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class BibliotecaServiceImpl implements IBibliotecaService {

	private BibliotecaRepository servicioAccesoBaseDatos; /*Se utiliza el repositorio inyectado para hacer las sentencias SQL */

	private ModelMapper modelMapper; /*Se utiliza el mapeador inyectado para hacer las sentencias SQL */

	/*Inyeccion por constructor -> Es mas recomendada que autowired para hacer la inteccion de dependencias porque FACILITA LAS PRUEBAS UNITARIAS */
	public BibliotecaServiceImpl(BibliotecaRepository servicioAccesoBaseDatos, ModelMapper modelMapper) {
		this.servicioAccesoBaseDatos = servicioAccesoBaseDatos;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<BibliotecaRespuestaDTO> findAll() {
		/*Obtiene una lista de BibliotecaEntity */
		List<BibliotecaEntity> bibliotecaEntity = this.servicioAccesoBaseDatos.findAll();
		/*Mapea la lista de clientes entity a clientes DTO */
		List<BibliotecaRespuestaDTO> blibliotecaRespuestaDTO = this.modelMapper.map(bibliotecaEntity, new TypeToken<List<BibliotecaRespuestaDTO>>() {
		}.getType());
		return blibliotecaRespuestaDTO; /*Retornamos una lista DTO, no de entity */
	}

	/*Metodo para buscar a un estudiante por un id especifico*/
	@Override
	public BibliotecaRespuestaDTO findById(Integer id) {
		BibliotecaEntity objBibliotecaEntity = this.servicioAccesoBaseDatos.findById(id);
		if (objBibliotecaEntity == null){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " No tiene deudas de préstamos de libros registradas");
		}
		BibliotecaRespuestaDTO blibliotecaRespuestaDTO = this.modelMapper.map(objBibliotecaEntity, BibliotecaRespuestaDTO.class);
		return blibliotecaRespuestaDTO;
	}

	@Override
	public List<BibliotecaRespuestaDTO> findAllById(Integer id) {
		List<BibliotecaEntity> bibliotecaEntity = this.servicioAccesoBaseDatos.findAllById(id);
		if (bibliotecaEntity.isEmpty()){
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Estudiante con id: " + id + " NO tiene deudas de préstamos de libros registradas");
		}
		List<BibliotecaRespuestaDTO> blibliotecaRespuestaDTO = this.modelMapper.map(bibliotecaEntity, new TypeToken<List<BibliotecaRespuestaDTO>>() {
		}.getType());
		return blibliotecaRespuestaDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}

	@Override
	public boolean deleteAllById(Integer id) {
		return this.servicioAccesoBaseDatos.deleteAllById(id);
	
	}

}

