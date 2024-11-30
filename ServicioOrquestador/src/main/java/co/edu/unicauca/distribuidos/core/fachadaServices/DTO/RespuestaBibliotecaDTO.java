package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;
import lombok.Data;

@Data
public class RespuestaBibliotecaDTO {

    private int idEstudiante;
    private String nombreEstudiante;
	private String apellidoEstuduante;
    private String nombreLibro;
    private String nombreAutor;
    private Date fechaPrestamo;
    private Date fechaEntrega;
}
