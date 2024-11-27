package main.java.co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServicioDeporteRespuestaDTO {
    private int idEstudiante;
    private String nombreEstudiante;
    private String apellidoEstuduante;
    private String nombreImplemento;
    private Date fechaPrestamo;
    private Date fechaEntrega;
    private String nombreDeporte;
    
}
