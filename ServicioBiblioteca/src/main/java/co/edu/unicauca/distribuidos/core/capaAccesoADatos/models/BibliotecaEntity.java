package co.edu.unicauca.distribuidos.core.capaAccesoADatos.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BibliotecaEntity {
	
	private int idEstudiante;
    private String nombreEstudiante;
	private String apellidoEstuduante;
    private String nombreLibro;
    private String nombreAutor;
    private Date fechaPrestamo;
    private Date fechaEntrega;

	public BibliotecaEntity() {

	}
}
