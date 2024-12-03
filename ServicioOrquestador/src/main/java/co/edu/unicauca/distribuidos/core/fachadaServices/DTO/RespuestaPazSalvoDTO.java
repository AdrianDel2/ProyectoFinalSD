package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.List;
import lombok.Data;

@Data
public class RespuestaPazSalvoDTO {
	private List<RespuestaBibliotecaDTO> objBibliotecas;
	private List<RespuestaDeporteDTO> objDeportes;
	private List<RespuestaFinancieraDTO> objFinanciera;
	private String Mensaje;

}