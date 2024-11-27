package main.java.co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServicioDeporteRespuestaDTO {
    private Integer id;
    private String nombre;
    private String nombreImplemento;
    private Date fechaPrestamo;
    private Date fechaEntrega;
    private String nombreDeporte;
    
}
