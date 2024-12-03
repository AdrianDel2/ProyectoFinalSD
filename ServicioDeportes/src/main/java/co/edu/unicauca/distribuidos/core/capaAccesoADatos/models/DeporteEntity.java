package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DeporteEntity {
	private int idEstudiante;
    private String nombreEstudiante;
    private String apellidoEstuduante;
    private String nombreImplemento;
    private Date fechaPrestamo;
    private Date fechaEntrega;
    private String nombreDeporte;
	
	public DeporteEntity() {

	}
}
