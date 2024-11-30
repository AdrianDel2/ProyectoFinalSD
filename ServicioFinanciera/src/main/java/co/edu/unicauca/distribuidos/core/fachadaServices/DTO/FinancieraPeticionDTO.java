package co.edu.unicauca.distribuidos.core.fachadaServices.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinancieraPeticionDTO{

	private int idEstudiante;
    private String nombreUsuario;
	private String apellidoEstudiante;
    private int valorDeuda;
	private String tipoDeuda;	
	private Date fechaVencimientoDeuda;
}