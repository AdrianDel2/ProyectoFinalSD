
package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.BibliotecaEntity;
//import co.edu.unicauca.distribuidos.core.fachadaServices.DTO.ServicioBibliotecaRespuestaDTO;
@Repository
public class BibliotecaRepository {

	int pos;
	private ArrayList<BibliotecaEntity> listaDeudasBiblioteca; 

	public BibliotecaRepository() {
		this.listaDeudasBiblioteca = new ArrayList<BibliotecaEntity>();		
		cargarDeudasBiblioteca();
		pos=this.listaDeudasBiblioteca.size()+1;
	}
	public List<BibliotecaEntity> findAll() {
		System.out.println("Invocando a listar deudas de la biblioteca");
		return this.listaDeudasBiblioteca;
	}

	public BibliotecaEntity findById(Integer id) {
		System.out.println("Invocando a consultar un cliente");
		BibliotecaEntity objdeudasBiblioteca = null;

		for (BibliotecaEntity Biblioteca : listaDeudasBiblioteca) {
			if (Biblioteca.getIdEstudiante() == id) {
				objdeudasBiblioteca = Biblioteca;
				break;
			}
		}

		return objdeudasBiblioteca;
	}

	public boolean delete(Integer id) {
		System.out.println("Invocando a eliminar una deuda de un estudiante de la biblioteca");
		boolean bandera = false;

		for (int i = 0; i < this.listaDeudasBiblioteca.size(); i++) {
			if (this.listaDeudasBiblioteca.get(i).getIdEstudiante() == id) {
				this.listaDeudasBiblioteca.remove(i);
				bandera = true;
				break;
			}
		}

		return bandera;
	}

	private void cargarDeudasBiblioteca() {

		BibliotecaEntity objBlioteca = new BibliotecaEntity(1, "Andres", "Perez", "La Maria", "Jorge Issac",new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca);
		BibliotecaEntity objBlioteca1 = new BibliotecaEntity(2, "Juan", "Perez", "Matematicas Basicas","nombreAutor", new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca1);
		BibliotecaEntity objBlioteca2 = new BibliotecaEntity(3, "Catalina", "Lopez", "Introducción a la ingenieria","nombreAutor", new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca2);
		BibliotecaEntity objBlioteca3 = new BibliotecaEntity(4, "Sandra", "Sanchez", "Introducció a circuitos","nombreAutor",new Date(),new Date());
		this.listaDeudasBiblioteca.add(objBlioteca3);
		
	}

	/*private int pos;
	private ArrayList<ClienteEntity> listaDeClientes; 

	public UsuarioRepository() {
		this.listaDeClientes = new ArrayList<ClienteEntity>();		
		cargarClientes();
		pos=this.listaDeClientes.size()+1;
	}

	public List<ClienteEntity> findAll() {
		System.out.println("Invocando a listarclientes");
		return this.listaDeClientes;
	}

	public ClienteEntity findById(Integer id) {
		System.out.println("Invocando a consultar un cliente");
		ClienteEntity objCliente = null;

		for (ClienteEntity cliente : listaDeClientes) {
			if (cliente.getId() == id) {
				objCliente = cliente;
				break;
			}
		}

		return objCliente;
	}

	public ClienteEntity save(ClienteEntity cliente) {
		System.out.println("Invocando a almacenar cliente");
		cliente.setId(pos);
		ClienteEntity objCliente = null;
		if (this.listaDeClientes.add(cliente)) {
			objCliente = cliente;
			pos++;
		}

		return objCliente;
	}

	public ClienteEntity update(Integer id, ClienteEntity cliente) {
		System.out.println("Invocando a actualizar un cliente");
		ClienteEntity objCliente = null;

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
		ClienteEntity objCliente1 = new ClienteEntity(1, "Juan", "Perez", "juan@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente1);
		ClienteEntity objCliente2 = new ClienteEntity(2, "Catalina", "Lopez", "catalina@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente2);
		ClienteEntity objCliente3 = new ClienteEntity(3, "Sandra", "Sanchez", "Sandra@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente3);
		ClienteEntity objCliente = new ClienteEntity(4, "Andres", "Perez", "andres@unicauca.edu.co", new Date());
		this.listaDeClientes.add(objCliente);
	}*/

	

}
