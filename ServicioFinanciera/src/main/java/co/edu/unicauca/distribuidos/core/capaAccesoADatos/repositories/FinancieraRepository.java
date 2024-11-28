
package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.FinancieraEntity;

@Repository
public class FinancieraRepository {

	private int pos;
	private ArrayList<FinancieraEntity> listaDeClientes; 

	public FinancieraRepository() {
		this.listaDeClientes = new ArrayList<FinancieraEntity>();		
		cargarClientes();
		pos=this.listaDeClientes.size()+1;
	}

	public List<FinancieraEntity> findAll() {
		System.out.println("Invocando a listarclientes");
		return this.listaDeClientes;
	}

	public FinancieraEntity findById(Integer id) {
		System.out.println("Invocando a consultar un cliente");
		FinancieraEntity objCliente = null;

		for (FinancieraEntity cliente : listaDeClientes) {
			if (cliente.getId() == id) {
				objCliente = cliente;
				break;
			}
		}

		return objCliente;
	}

	public FinancieraEntity save(FinancieraEntity cliente) {
		System.out.println("Invocando a almacenar cliente");
		cliente.setId(pos);
		FinancieraEntity objCliente = null;
		if (this.listaDeClientes.add(cliente)) {
			objCliente = cliente;
			pos++;
		}

		return objCliente;
	}

	public FinancieraEntity update(Integer id, FinancieraEntity cliente) {
		System.out.println("Invocando a actualizar un cliente");
		FinancieraEntity objCliente = null;

		for (int i = 0; i < this.listaDeClientes.size(); i++) {
			if (this.listaDeClientes.get(i).getId() == id) {
				this.listaDeClientes.set(i, cliente);
				objCliente = cliente;
				break;
			}
		}

		return objCliente;
	}

	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar un cliente");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeClientes.size(); i++) {
			if (this.listaDeClientes.get(i).getId() == id) {
				this.listaDeClientes.remove(i);
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	private void cargarClientes() {
		FinancieraEntity objCliente1 = new FinancieraEntity(1, "Juan", "Perez", "juan@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente1);
		FinancieraEntity objCliente2 = new FinancieraEntity(2, "Catalina", "Lopez", "catalina@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente2);
		FinancieraEntity objCliente3 = new FinancieraEntity(3, "Sandra", "Sanchez", "Sandra@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente3);
		FinancieraEntity objCliente = new FinancieraEntity(4, "Andres", "Perez", "andres@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente);
	}

}
