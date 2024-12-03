package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.DeporteEntity;

@Repository
public class DeporteRepository {

	private int pos;
	private ArrayList<DeporteEntity> listadeudasDeporte; 

	public DeporteRepository() {
		this.listadeudasDeporte = new ArrayList<DeporteEntity>();		
		cargarDeudasDeportes();
		pos=this.listadeudasDeporte.size()+1;
	}

	public List<DeporteEntity> findAll() {
		System.out.println("Invocando a listar todas las deudas");
		return this.listadeudasDeporte;
	}

	public DeporteEntity findById(Integer id) {
		System.out.println("Invocando a consultar un estudiante");
		DeporteEntity objDeporte = null;

		for (DeporteEntity deporte : listadeudasDeporte) {
			if (deporte.getIdEstudiante() == id) {
				objDeporte = deporte;
				break;
			}
		}

		return objDeporte;
	}

	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar un estudiante");
		boolean bandera = false;

		for (int i = 0; i < this.listadeudasDeporte.size(); i++) {
			if (this.listadeudasDeporte.get(i).getIdEstudiante() == id) {
				this.listadeudasDeporte.remove(i);
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	public List<DeporteEntity> findAllById(Integer id) {
		System.out.println("Invocando a consultar todos los estudiantes con el mismo id");
		List<DeporteEntity> deudasImplementos = new ArrayList<DeporteEntity>();
		
		for(DeporteEntity Deporte : listadeudasDeporte) {
			if(Deporte.getIdEstudiante() == id) {
				deudasImplementos.add(Deporte);
			}
		}
		return deudasImplementos;
	}

	public boolean deleteAllById(Integer id) {
		System.out.println("Invocando a eliminar todas las deudas de un estudiante de la biblioteca");
		boolean bandera = false;

		for (int i = 0; i < this.listadeudasDeporte.size(); i++) {
			if (this.listadeudasDeporte.get(i).getIdEstudiante() == id) {
				this.listadeudasDeporte.remove(i);//Se elimina el registro de la lista
				i--; // Para mantener el índice correcto después de eliminar
				bandera = true;
			}
		}
		return bandera;
	}
	
	private void cargarDeudasDeportes() {
		DeporteEntity objCliente1 = new DeporteEntity(1, "Juan", "Perez", "Balon", new Date(), new Date(), "Futbol");
		this.listadeudasDeporte.add(objCliente1);
		DeporteEntity objCliente2 = new DeporteEntity(2, "Catalina", "Lopez", "Aro", new Date(), new Date(), "Gimnasia");
		this.listadeudasDeporte.add(objCliente2);
		DeporteEntity objCliente3 = new DeporteEntity(3, "Sandra", "Sanchez", "Raqueta", new Date(), new Date(), "Tenis");
		this.listadeudasDeporte.add(objCliente3);
		
		DeporteEntity objCliente = new DeporteEntity(4, "Andres", "Perez", "Guantes", new Date(), new Date(), "Boxeo");
		this.listadeudasDeporte.add(objCliente);
		DeporteEntity objCliente4 = new DeporteEntity(4, "Andres", "Perez", "Balon", new Date(), new Date(), "Baloncesto");
		this.listadeudasDeporte.add(objCliente4);

		DeporteEntity objCliente5 = new DeporteEntity(5, "Luna", "Muñoz", "Raqueta", new Date(), new Date(), "Tenis");
		this.listadeudasDeporte.add(objCliente5);
	}

}
