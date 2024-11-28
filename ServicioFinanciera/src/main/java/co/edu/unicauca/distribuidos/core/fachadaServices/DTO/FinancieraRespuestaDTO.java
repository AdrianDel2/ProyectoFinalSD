package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinancieraRespuestaDTO {
	
	private integer idEstudiante;
    private String nombreUsuario;
	private String apellidoEstudiante;
    private integer valorDeuda;
	private String tipoDeuda;	
	private Date fechaVencimientoDeuda;

	public FinancieraRespuestaDTO() {

	}
}