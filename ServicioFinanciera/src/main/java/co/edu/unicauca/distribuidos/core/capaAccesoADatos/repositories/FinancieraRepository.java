package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.FinancieraEntity;

@Repository
public class FinancieraRepository {

	private int pos;
	private ArrayList<FinancieraEntity> listaDeudasFinanciera; 

	public FinancieraRepository() {
		this.listaDeudasFinanciera = new ArrayList<FinancieraEntity>();		
		cargarDeudasFinanciera();
		pos=this.listaDeudasFinanciera.size()+1;
	}

	public List<FinancieraEntity> findAll() {
		System.out.println("Invocando a listarclientes");
		return this.listaDeudasFinanciera;
	}

	public FinancieraEntity findById(Integer id) {
		System.out.println("Invocando a consultar un cliente");
		FinancieraEntity objFinanciera = null;

		for (FinancieraEntity financiera : listaDeudasFinanciera) {
			if (financiera.getIdEstudiante()== id) {
				objFinanciera = financiera;
				break;
			}
		}
		return objFinanciera;
	}

	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar un cliente");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeudasFinanciera.size(); i++) {
			if (this.listaDeudasFinanciera.get(i).getIdEstudiante() == id) {
				this.listaDeudasFinanciera.remove(i);
				bandera = true;
				break;
			}
		}
		return bandera;
	}

	public boolean deleteAllById(Integer id) {
		System.out.println("Invocando a eliminar las deudas de un estudiante del area financiera");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeudasFinanciera.size(); i++) {
			if (this.listaDeudasFinanciera.get(i).getIdEstudiante() == id) {
				this.listaDeudasFinanciera.remove(i);
				i--;  // Para mantener el índice correcto después de eliminar
				bandera = true;
			}
		}

		if(bandera==false) {
			System.out.println("No existen deudas para el estudiante con Id: " + id);
		}

		return bandera;
	}
	
	public List<FinancieraEntity> findAllById(Integer id) {
		System.out.println("Invocando a consultar los estudiantes con el mismo id");
		List<FinancieraEntity> deudasfinanciera = new ArrayList<>();
	
		for (FinancieraEntity financiera : listaDeudasFinanciera) {
			if (financiera.getIdEstudiante() == id) {
				deudasfinanciera.add(financiera);
			}
		}
		return deudasfinanciera;
	}


	private void cargarDeudasFinanciera() {
		FinancieraEntity objFinanciera1 = new FinancieraEntity(1, "Juan", "Perez", 50000,"Matricula", new Date());
		this.listaDeudasFinanciera.add(objFinanciera1);
		FinancieraEntity objFinanciera2 = new FinancieraEntity(2, "Catalina", "Lopez", 100000,"Seguro Estudiantil", new Date());
		this.listaDeudasFinanciera.add(objFinanciera2);
		FinancieraEntity objFinanciera3 = new FinancieraEntity(3, "Sandra", "Sanchez",1000000 ,"Matricula", new Date());
		this.listaDeudasFinanciera.add(objFinanciera3);
		FinancieraEntity objFinanciera = new FinancieraEntity(4, "Andres", "Perez",500000 ,"Seguro Estudiantil", new Date());
		this.listaDeudasFinanciera.add(objFinanciera);
	}

}

