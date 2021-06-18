package net.elinformatico.console;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.elinformatico.model.Usuarios;
import net.elinformatico.service.jpa.UsuarioService;

@Service
public class MenuConsoleJPA implements IMenuConsole{

	@Autowired
	private UsuarioService usuarioService;
	
	private void showMainMenu() {
		System.out.println("\n\n\n-------- Java Persistance API (JPA) Menu -----------\n"
				+ "1) Guardar Usuario \n"
				+ "2) Buscar Usuario por ID \n"
				+ "3) Eliminar Usuario por ID \n"
				+ "4) Eliminar Usuario por Entidad\n"
				+ "5) Modificar Usuario\n"
				+ "6) Mostrar todos los Usuarios\n"
				+ "(salir) Para salir de la Aplicacion");
	}
	
	@Override
	public void startShowingMenu() {
		
		Scanner input = new Scanner(System.in);
		String option = "";
		String idUsuario = "";
		Usuarios usuario;
			
		do {
			showMainMenu();
			option = input.nextLine();
			
			switch (option) {
			
				// INSERT VALUES TO THE TABLE "USER"
				case "1" :
					Usuarios newUser = new Usuarios();
					System.out.println("\n\nInsertar Datos del Usuario\n\n");
					
					System.out.println("Introduzca el nombre: ");
					String nombre = input.nextLine();
					newUser.setNombre(nombre);
					
					System.out.println("Introduzca el email: ");
					String email = input.nextLine();
					newUser.setEmail(email);
					
					System.out.println("Insert un nombre de Usuario: ");
					String userName = input.nextLine();
					newUser.setUsername(userName);
					
					System.out.println("Insert un Password: ");
					String password = input.nextLine();
					newUser.setPassword(password);
					
					newUser.setStatus(1);
					newUser.setFechaRegistro(new Date());
					
					usuarioService.guardar(newUser);
					
					System.out.println("\nSe registro el Usuario " + newUser.getNombre() + " en la Base de Datos! \n\n\n");
					System.out.println(newUser);
					break;
					
				// FIND USER BY ID
				case "2" :
					System.out.println("\n\nBuscar Usuario por ID\n\n");
					System.out.println("Introduzca el ID del Usuario: ");
					idUsuario = input.nextLine();
					
					usuario = usuarioService.buscarPorId(Integer.valueOf(idUsuario));
					System.out.println("\n\nSe encontro el siguiente usuario: \n" + usuario);
					
					break;
				
				// FIND USER BY ENTITY
				case "3" :
					System.out.println("\n\nEliminar Usuario por ID\n\n");
					System.out.println("Introduzca el ID del Usuario a eliminar ");
					idUsuario = input.nextLine();
					
					usuarioService.eliminarPorId(Integer.valueOf(idUsuario));
					System.out.println("\n\nSe elimino el usuario con el ID: " + idUsuario);
					
					break;
				case "4" :
					System.out.println("\n\nEliminar Usuario por Identidad\n\n");
					System.out.println("Introduzca el ID del Usuaria que sera buscado");
					idUsuario = input.nextLine();
					
					usuario = usuarioService.buscarPorId(Integer.valueOf(idUsuario));
					usuarioService.eliminarPorEntidad(usuario);
					
					break;
	
				case "salir" : break;
				default:
					System.out.println("\n\nOption Invalida!\n\n");
					break;
			}
		
		}while(!option.contains("salir"));
		
		input.close();
		
		System.out.println("Goodbye! Thanks for learning and using JPA =)");
	}
}
