package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class RespuestaDeporteDTO {
    private int idEstudiante;
    private String nombreEstudiante;
    private String apellidoEstuduante;
    private String nombreImplemento;
    private Date fechaPrestamo;
    private Date fechaEntrega;
    private String nombreDeporte;
    
}
