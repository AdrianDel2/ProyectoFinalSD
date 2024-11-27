package main.java.co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServicioFinancieraPeticionDTO {  
    
    private integer id;
    private String nombreUsuario;
    private integer valorDeuda;
	private String tipoDeuda;	
	private Date fechaVencimientoDeuda;
}
