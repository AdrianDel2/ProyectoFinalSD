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
		cargarClientes();
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

	private void cargarDeudasFinanciera() {
		FinancieraEntity objFinanciera1 = new FinancieraEntity(1, "Juan", "Perez", "juan@unicauca.edu.co", new Date());
		this.listaDeudasFinanciera.add(objCliente1);
		FinancieraEntity objFinanciera2 = new FinancieraEntity(2, "Catalina", "Lopez", "catalina@unicauca.edu.co", new Date());
		this.listaDeudasFinanciera.add(objCliente2);
		FinancieraEntity objFinanciera3 = new FinancieraEntity(3, "Sandra", "Sanchez", "Sandra@unicauca.edu.co", new Date());
		this.listaDeudasFinanciera.add(objCliente3);
		FinancieraEntity objFinanciera = new FinancieraEntity(4, "Andres", "Perez", "andres@unicauca.edu.co", new Date());
		this.listaDeudasFinanciera.add(objCliente);
	}

}
