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
import org.springframework.web.reactive.function.client.WebClientResponseException.NotFound;

@Service /*Indica que se creara un objeto automaticamente de esta clase para meterlo en el contenedor */
public class GenerarPazSalvoServiceImpl implements IGenerarPazSalvoService {
	
	@Autowired
    private WebClient webClient;

	@Override
	public RespuestaPazSalvoDTO generarPazSalvoSincrono (int idEstudiante){

		/*Crear el objeto de respuesta*/
		RespuestaPazSalvoDTO objRespuestaPazSalvo = new RespuestaPazSalvoDTO();
		try{
			/* Definir las URLs de los servicios */
			String url1 = "http://localhost:8085/api/bibliotecas/especifico/" + idEstudiante;
			String url2 = "http://localhost:8086/api/deportes/especifico/" + idEstudiante;
			String url3 = "http://localhost:8087/api/financiera/especifico/" + idEstudiante;
			
			/*Servicio Bibliotwca */
			List<RespuestaBibliotecaDTO> prestamos;
			
			try{
				prestamos= webClient.get() //Hacer una peticion GET
				.uri(url1)
				.retrieve() // recuperar la respuesta HTTP
				.bodyToFlux(RespuestaBibliotecaDTO.class)//Deserializa la respuesta a objetos del tipo RespuestaBibliotecaDTO
				.collectList()//Se convierte en una lista
				.block(); /*bloquea el hilo principal hasta que se obtenga la respuesta*/
			}
			catch(NotFound e){
				prestamos = null;
			}
			/*Servicio Deporte */
			List<RespuestaDeporteDTO> deportes; 
			
			try{
				deportes= webClient.get()
				.uri(url2)
				.retrieve()//recupera la respuesta HTTP
				.bodyToFlux(RespuestaDeporteDTO.class)
				.collectList()
				.block();//bloquea el hilo principal hasta que se obtenga la respuesta
			}
			catch(NotFound e){
				deportes = null;
			}
			
			/*Servicio Financiera */
			List<RespuestaFinancieraDTO> deudas; 
			
			try{
				deudas= webClient.get()
				.uri(url3)
				.retrieve()
				.bodyToFlux(RespuestaFinancieraDTO.class)
				.collectList()
				.block();
			}
			catch(NotFound e){
				deudas = null;
			}
			
			//Verifica si el estudiante tiene deudas en algun servicio
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
			//objRespuestaPazSalvo.setMensaje("Paz y salvo generado correctamente");	

		}catch(Exception e){
			System.out.println(e.getMessage());
			objRespuestaPazSalvo.setMensaje("Error al generar el paz y salvo"+e);

		}
		return objRespuestaPazSalvo;
	}

	@Override
	public Mono<RespuestaPazSalvoDTO>generarPazSalvoAsincrono(int idEstudiante){

		/*Crear el objeto de respuesta*/
		RespuestaPazSalvoDTO objRespuestaPazSalvo = new RespuestaPazSalvoDTO();
		try{
			/* Definir las URLs de los servicios */
			String url1 = "http://localhost:8085/api/bibliotecas/especifico/" + idEstudiante;
			String url2 = "http://localhost:8086/api/deportes/especifico/" + idEstudiante;
			String url3 = "http://localhost:8087/api/financiera/especifico/" + idEstudiante;

			//Servicio Biblioteca
			Mono<List<RespuestaBibliotecaDTO>> prestamos = Mono.just(List.of());//Se inicializa con una lista vacía porque no puede ser null

			try{
				prestamos = webClient.get()
							.uri(url1)
							.retrieve()// recupera la respuesta HTTP
							.bodyToFlux(RespuestaBibliotecaDTO.class)//Deserializa la respuesta a objetos del tipo RespuestaBibliotecaDTO
							.collectList()
							.onErrorResume(e -> {
								System.out.println("Error al recuperar deportes: " + e.getMessage());
								return Mono.just(List.of()); // Devuelve una lista vacía en caso de error
							});

			}catch(NotFound e){
				System.out.println("Error al recuperar prestamos: " + e.getMessage());
    			prestamos = Mono.just(List.of()); 
			}

			//Servicio Deporte
			Mono<List<RespuestaDeporteDTO>> deportes = Mono.just(List.of());;
			try{
			deportes = webClient.get()
						.uri(url2)
						.retrieve()
						.bodyToFlux(RespuestaDeporteDTO.class)
						.collectList()
						.onErrorResume(e -> {
							System.out.println("Error al recuperar financiera: " + e.getMessage());
							return Mono.just(List.of()); // Devuelve una lista vacía en caso de error
						});

			}catch(NotFound e){
				System.out.println("Error al recuperar deportes: " + e.getMessage());
				deportes = Mono.just(List.of());
			}
			//Servicio Financiera
			Mono<List<RespuestaFinancieraDTO>> deudas = Mono.just(List.of());;
			try{
				deudas = webClient.get()
						.uri(url3)
						.retrieve()
						.bodyToFlux(RespuestaFinancieraDTO.class)
						.collectList()
						.onErrorResume(e -> {
							System.out.println("Error al recuperar deportes: " + e.getMessage());
							return Mono.just(List.of()); // Devuelve una lista vacía en caso de error
						});
						
			}catch(NotFound e){
				deudas = Mono.just(List.of());

			}
			
			return Mono.zip(prestamos, deportes, deudas)//Mono.zip permite combinar los 3 mono de cada lista en un solo mono
						.map(resultado -> {//Se Procesan los resultados 
							
							// Extraer las listas de cada servicio
							List<RespuestaBibliotecaDTO> prestamosBib = resultado.getT1();
							List<RespuestaDeporteDTO> implementosDep = resultado.getT2();
							List<RespuestaFinancieraDTO> deudasFin = resultado.getT3();

							//Verifica si el estudiante tiene deudas en algun servicio
							boolean estadoSalvo = (prestamosBib == null || prestamosBib.isEmpty()) &&
												  (implementosDep == null || implementosDep.isEmpty()) &&
												  (deudasFin == null || deudasFin.isEmpty());

							if (estadoSalvo) {
								objRespuestaPazSalvo.setMensaje("El estudiante no tiene deudas, Se encuentra a paz y salvo");
							} else {
								objRespuestaPazSalvo.setMensaje("El estudiante tiene deudas pendientes, No se encuentra a paz y salvo");
								objRespuestaPazSalvo.setObjBibliotecas(prestamosBib);
								objRespuestaPazSalvo.setObjDeportes(implementosDep);
								objRespuestaPazSalvo.setObjFinanciera(deudasFin);
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
