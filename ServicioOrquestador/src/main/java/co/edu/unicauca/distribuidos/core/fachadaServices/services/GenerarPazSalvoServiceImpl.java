
package co.edu.unicauca.distribuidos.core.fachadaServices.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.web.reactive.function.client.WebClient;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaPazSalvoDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaBibliotecaDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaDeporteDTO;
import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.RespuestaFinancieraDTO;
import reactor.core.publisher.Mono;

@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class GenerarPazSalvoServiceImpl implements IGenerarPazSalvoService {
	
	@Autowired
    private WebClient webClient;

	@Override
	public RespuestaPazSalvoDTO generarPazSalvoSincrono (Integer idEstudiante){

		/*Crear el objeto de respuesta*/
		RespuestaPazSalvoDTO objRespuestaPazSalvo = new RespuestaPazSalvoDTO();
		try{
			String url1 = "http://localhost:8085/api/biblioteca" + idEstudiante;
			String url2 = "http://localhost:8086/api/deporte" + idEstudiante;
			String url3 = "http://localhost:8087/api/financiera" + idEstudiante;
			
			/*Servicio Bibliotwca */
			List<RespuestaBibliotecaDTO> prestamos = webClient.get()
													.uri(url1)
													.retrieve()
													.bodyToFlux(RespuestaBibliotecaDTO.class)
													.collectList()
													.block(); /*Se bloquea el hilo principal hasta que se obtenga la respuesta*/
			
			/*Servicio Deporte */
			List<RespuestaDeporteDTO> deportes = webClient.get()
													.uri(url2)
													.retrieve()
													.bodyToFlux(RespuestaDeporteDTO.class)
													.collectList()
													.block();
			
			/*Servicio Financiera */
			List<RespuestaFinancieraDTO> deudas = webClient.get()
													.uri(url3)
													.retrieve()
													.bodyToFlux(RespuestaFinancieraDTO.class)
													.collectList()
													.block();
			
			boolean estadoSalvo = (prestamos == null || prestamos.isEmpty()) &&
							(deportes == null || deportes.isEmpty()) &&
							(deudas == null || deudas.isEmpty());
			
			if(estadoSalvo == true){
				objRespuestaPazSalvo.setMensaje("El estudiante no tiene deudas, Se encuentra a paz y salvo");

			}else{
				objRespuestaPazSalvo.setMensaje("El estudiante tiene deudas pendientes, No se encuentra a paz y salvo");
				objRespuestaPazSalvo.setObjBibliotecas(prestamos);
				objRespuestaPazSalvo.setObjDeportes(deportes);
				objRespuestaPazSalvo.setObjFinanciera(deudas);
			}
			objRespuestaPazSalvo.setMensaje("Paz y salvo generado correctamente");	

		}catch(Exception e){
			System.out.println(e.getMessage());
			objRespuestaPazSalvo.setMensaje("Error al generar el paz y salvo");

		}
		return objRespuestaPazSalvo;
	}

	@Override
	public Mono<RespuestaPazSalvoDTO>generarPazSalvoAsincrono(Integer idEstudiante){

		/*Crear el objeto de respuesta*/
		RespuestaPazSalvoDTO objRespuestaPazSalvo = new RespuestaPazSalvoDTO();
		try{
			String url1 = "http://localhost:8085/api/biblioteca" + idEstudiante;
			String url2 = "http://localhost:8086/api/deporte" + idEstudiante;
			String url3 = "http://localhost:8087/api/financiera" + idEstudiante;

			Mono<List<RespuestaBibliotecaDTO>> prestamos = webClient.get()
																		.uri(url1)
																		.retrieve()
																		.bodyToFlux(RespuestaBibliotecaDTO.class)
																		.collectList();
			Mono<List<RespuestaDeporteDTO>> deportes = webClient.get()
																		.uri(url2)
																		.retrieve()
																		.bodyToFlux(RespuestaDeporteDTO.class)
																		.collectList();
			Mono<List<RespuestaFinancieraDTO>> deudas = webClient.get()
																		.uri(url3)
																		.retrieve()
																		.bodyToFlux(RespuestaFinancieraDTO.class)
																		.collectList();

			return Mono.zip(prestamos, deportes, deudas)
						.map(tuple -> {
							List<RespuestaBibliotecaDTO> prestamosList = tuple.getT1();
							List<RespuestaDeporteDTO> deportesList = tuple.getT2();
							List<RespuestaFinancieraDTO> deudasList = tuple.getT3();

							boolean estadoSalvo = (prestamosList == null || prestamosList.isEmpty()) &&
												  (deportesList == null || deportesList.isEmpty()) &&
												  (deudasList == null || deudasList.isEmpty());

							if (estadoSalvo) {
								objRespuestaPazSalvo.setMensaje("El estudiante no tiene deudas, Se encuentra a paz y salvo");
							} else {
								objRespuestaPazSalvo.setMensaje("El estudiante tiene deudas pendientes, No se encuentra a paz y salvo");
								objRespuestaPazSalvo.setObjBibliotecas(prestamosList);
								objRespuestaPazSalvo.setObjDeportes(deportesList);
								objRespuestaPazSalvo.setObjFinanciera(deudasList);
							}
							return objRespuestaPazSalvo;
						})
						.onErrorResume(e -> {
							objRespuestaPazSalvo.setMensaje("Error al generar el paz y salvo");
							return Mono.just(objRespuestaPazSalvo);
						});
	}catch(Exception e){
		System.out.println(e.getMessage());
		objRespuestaPazSalvo.setMensaje("Error al generar el paz y salvo");
		return Mono.just(objRespuestaPazSalvo);
		}

	}
}
