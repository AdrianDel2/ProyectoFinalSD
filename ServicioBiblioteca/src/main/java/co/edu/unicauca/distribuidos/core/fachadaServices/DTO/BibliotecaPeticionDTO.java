package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BibliotecaPeticionDTO {
    
    private String nombreLibro;
    private String nombreAutor;
    private Date fechaPrestamo;
    private Date fechaEntrega;

    public BibliotecaPeticionDTO() {

    }
    
}
