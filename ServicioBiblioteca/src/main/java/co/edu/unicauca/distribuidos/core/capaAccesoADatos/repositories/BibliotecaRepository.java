
package co.edu.unicauca.distribuidos.core.capaAccesoADatos.repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.unicauca.distribuidos.core.capaAccesoADatos.models.BibliotecaEntity;
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
		System.out.println("Invocando a listar prestamos de la biblioteca");
		return this.listaDeudasBiblioteca;
	}

	public BibliotecaEntity findById(Integer id) {
		System.out.println("Invocando a consultar un estudiante");
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
		System.out.println("Invocando a eliminar un prestamo de un estudiante de la biblioteca");
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

	public boolean deleteAllById(Integer id) {
		System.out.println("Invocando a eliminar todos los prestamos de un estudiante de la biblioteca");
		boolean bandera = false;		
		for (int i = 0; i < this.listaDeudasBiblioteca.size(); i++) {
			if (this.listaDeudasBiblioteca.get(i).getIdEstudiante() == id) {
				this.listaDeudasBiblioteca.remove(i);
				i--;  // Para mantener el índice correcto después de eliminar
				bandera = true;
			}
		}
		return bandera;

		/*Iterator<BibliotecaEntity> iterador = this.listaDeudasBiblioteca.iterator();
		while(iterador.hasNext()){
			BibliotecaEntity deuda = iterador.next();
			if(deuda.getIdEstudiante() == id){
				iterador.remove();
				bandera = true;
			}
		}
		// Si no se eliminó ninguna deuda, mostrar un mensaje
		if (!bandera) {
			System.out.println("No existen deudas pendientes para el estudiante con Id: " + id);
		}*/
		
	}
	
	public List<BibliotecaEntity> findAllById(Integer id) {
		System.out.println("Invocando a consultar todos los estudiantes con el mismo id");
		List<BibliotecaEntity> deudasBiblioteca = new ArrayList<>();
	
		for (BibliotecaEntity Biblioteca : listaDeudasBiblioteca) {
			if (Biblioteca.getIdEstudiante() == id) {
				deudasBiblioteca.add(Biblioteca);
			}
		}
		return deudasBiblioteca;
	}

	private void cargarDeudasBiblioteca() {

		BibliotecaEntity objBlioteca1 = new BibliotecaEntity(1, "Juan", "Perez", "Matematicas Basicas","Robert Prior", new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca1);
		BibliotecaEntity objBlioteca2 = new BibliotecaEntity(2, "Catalina", "Lopez", "Introducción a la ingenieria","Barbara Liskov", new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca2);
		BibliotecaEntity objBlioteca3 = new BibliotecaEntity(3, "Sandra", "Sanchez", "Introducción a circuitos","James Nilsson y Susan Riedel",new Date(),new Date());
		this.listaDeudasBiblioteca.add(objBlioteca3);

		BibliotecaEntity objBlioteca = new BibliotecaEntity(4, "Andres", "Perez", "La Maria", "Jorge Issac",new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca);
		BibliotecaEntity objBlioteca4 = new BibliotecaEntity(4, "Andres", "Perez", "Cien años de Soledad", "Gabriel García Marquez",new Date(), new Date());
		this.listaDeudasBiblioteca.add(objBlioteca4);
		
	}
}
