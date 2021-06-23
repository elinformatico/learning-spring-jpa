package net.elinformatico.console;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.elinformatico.JpaExamples;

@Service
public class MenuConsoleJPA implements IMenuConsole{

	@Autowired
	private JpaExamples jpa;
	
	@Override
	public void startShowingMenu() {
		
		Scanner input = new Scanner(System.in);
		String option = "";
		
		do {
			showMainMenu();
			option = input.nextLine();
			
			switch (option) {
				case "1" :  jpa.registrarUsuario(); break;
				case "2" :  jpa.buscarUsuarioPorId(); break;
				case "3" :  jpa.eliminarUsuarioPorId(); break;
				case "4" :  jpa.eliminarUsuarioPorEntidad(); break;
				case "5" :  jpa.modificarUsuario(); break;
				case "6" :  jpa.mostrarUsuarios(); break;
				case "7" :  jpa.mostrarUsuariosUsandoOrdenamiento(); break;
				case "8" :  jpa.obtenerNumeroDeUsuarios(); break;
				case "9" :  jpa.eliminarTodosLosUsuarios(); break;
				case "10" : jpa.buscarUsuariosUsandoIds(); break;
				case "11" : jpa.verificarSiExisteUsuario(); break;
				case "12" : jpa.guardarVariosUsuarios(); break;
				case "13" : jpa.mostrarUsuariosUsandoPaginacion(); break;
				case "14" : jpa.mostrandoUsuariosUsandoJoinUnoaUno(); break;
				case "15" : jpa.registrarVacante(); break;
				case "16" : jpa.registrarPerfil(); break;
				case "exit" : break;
				default: System.err.println("\nOption Invalida!\n"); break;
			}
		
		} while(!option.contains("exit"));
		
		input.close();
		System.out.println("Goodbye! Thanks for learning and using JPA =)");
	}
	
	private void showMainMenu() {
		System.out.println("\n\n\n-------- Java Persistance API (JPA) Menu -----------\n"
				+ "1)  Registrar nuevo Usuario \n"
				+ "2)  Buscar Usuario por ID 'findById(Integer)' \n"
				+ "3)  Eliminar Usuario por ID 'deleteById(Integer)' \n"
				+ "4)  Eliminar Usuario por Entidad 'delete(Entity)' \n"
				+ "5)  Modificar Usuario 'save(Entity)' --> with ID to Update\n"
				+ "6)  Mostrar todos los Usuarios \n"
				+ "7)  Mostrar todos los Usuarios por Ordenamiento \n"
				+ "8)  Obtener numero de Usuarios Registrado 'count()' \n"
				+ "9)  Eliminar todos los Usuarios 'deleteAll()' \n"
				+ "10) Buscar por varios ID 'findAllById(Iterable<ID> ids)'\n"
				+ "11) Verificar si existe Usuario por ID 'existsById(Integer)' \n"
				+ "12) Guardar varios Usuarios 'saveAll(Iterable<Usuarios>)' \n\n"
				+ "------- Advance options ----------------------------------\n"
				+ "13) Paginacion de Registros 'Page<T> findAll(Pageable pageable)' \n"
				+ "14) Mostras datos Ligados (SQL INNER) \n"
				+ "15) Guardar Vacante con relacion a tabla Categoria \n"
				+ "16) Registrar Perfiles \n"
				+ "(*) Pruebas Rapidas (exit) Salir de la aplicacion \n");
	}
}
