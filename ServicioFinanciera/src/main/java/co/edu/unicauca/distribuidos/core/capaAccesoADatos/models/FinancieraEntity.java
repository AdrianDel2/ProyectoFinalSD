package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class FinancieraEntity {

	private integer idEstudiante;
    private String nombreUsuario;
	private String apellidoEstudiante;
    private integer valorDeuda;
	private String tipoDeuda;	
	private Date fechaVencimientoDeuda;

	public FinancieraEntity() {

	}
}
