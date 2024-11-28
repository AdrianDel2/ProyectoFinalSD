package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FinancieraPeticionDTO{
    	
	private int valorDeuda;
	private String tipoDeuda;	
	private Date fechaVencimientoDeuda;
}