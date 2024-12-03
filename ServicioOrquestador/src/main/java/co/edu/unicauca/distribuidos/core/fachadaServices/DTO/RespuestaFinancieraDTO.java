package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class RespuestaFinancieraDTO {
    private int idEstudiante;
    private String nombreUsuario;
	private String apellidoEstudiante;
    private int valorDeuda;
	private String tipoDeuda;	
	private Date fechaVencimientoDeuda;    
}
