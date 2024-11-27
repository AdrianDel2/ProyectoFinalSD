package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ServicioBibliotecaRespuestaDTO {
    private int id;
    private String nombre;
    private String nombreLibro;
    private String nombreAutor;
    private Date fechaPrestamo;
    private Date fechaEntrega;
}
