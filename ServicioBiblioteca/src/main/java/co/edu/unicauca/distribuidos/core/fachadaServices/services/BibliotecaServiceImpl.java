
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.BibliotecaEntity;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories.BibliotecaRepository;
//import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ClienteDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.BibliotecaRespuestaDTO;

/*@AllArgsConstructor para no tener que poner el constructor y ya se haria la inyeccion: MEJOR FORMA */
@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class BibliotecaServiceImpl implements IBibliotecaService {

	/*@Autowired Inyeccion de dependencias por atributo -> Busca en el contenedor un objeto que correpsonda a 
	BibliotecaRepository y lo inyecta aqui */
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
		return blibliotecaRespuestaDTO; /*Retornamos una lista DTO, no de entity como tal por seguridad supongo */
	}

	@Override
	public BibliotecaRespuestaDTO findById(Integer id) {
		BibliotecaEntity objBibliotecaEntity = this.servicioAccesoBaseDatos.findById(id);
		if (objBibliotecaEntity == null){
			throw new RuntimeException("Estudiante con  id: " + id + " no fue encontrado en las deudas de prestamos de libros");
		}
		BibliotecaRespuestaDTO blibliotecaRespuestaDTO = this.modelMapper.map(objBibliotecaEntity, BibliotecaRespuestaDTO.class);
		return blibliotecaRespuestaDTO;
	}

	@Override
	public boolean delete(Integer id) {
		return this.servicioAccesoBaseDatos.delete(id);
	}
}
