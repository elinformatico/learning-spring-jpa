package net.elinformatico.console;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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
				+ "1) Guardar Usuario 'save(Entity)' \n"
				+ "2) Buscar Usuario por ID 'findById(Integer)' \n"
				+ "3) Eliminar Usuario por ID 'deleteById(Integer)' \n"
				+ "4) Eliminar Usuario por Entidad 'delete(Entity)' \n"
				+ "5) Modificar Usuario 'save(Entity)' --> with ID to Update\n"
				+ "6) Mostrar todos los Usuarios \n"
				+ "7) Obtener numero de Usuarios Registrado 'count()' \n"
				+ "8) Eliminar todos los Usuarios 'deleteAll()' \n"
				+ "9) Buscar por varios ID 'findAllById(Iterable<ID> ids)'\n"
				+ "10) Verificar si existe Usuario por ID 'existsById(Integer)' \n"
				+ "11) Guardar varios Usuarios 'saveAll(Iterable<Usuarios>)' \n"
				+ "(salir) Para salir de la Aplicacion");
	}
	
	@Override
	public void startShowingMenu() {
		
		Scanner input = new Scanner(System.in);
		String option = "";
		String idUsuario = "";
		String inputLine = "";
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
					inputLine= input.nextLine();
					newUser.setNombre(inputLine);
					
					System.out.println("Introduzca el email: ");
					inputLine = input.nextLine();
					newUser.setEmail(inputLine);
					
					System.out.println("Insert un nombre de Usuario: ");
					inputLine = input.nextLine();
					newUser.setUsername(inputLine);
					
					System.out.println("Insert un Password: ");
					inputLine = input.nextLine();
					newUser.setPassword(inputLine);
					
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
	
				// UPDATING A DATABASE ROW
				case "5" : 
					System.out.println("\nModificar Usuario\n");
					System.out.println("Introduzca el ID del Usuario a modificar "
							+ "(Solo le modificara el nombre y Password)");
					idUsuario = input.nextLine();
					
					// Checking if the Entity exist
					boolean existeUsuario = usuarioService.existePorId(Integer.valueOf(idUsuario));
					
					if(existeUsuario) {
						
						// LOCATE THE USER ENTITY FIRST
						usuario = usuarioService.buscarPorId(Integer.valueOf(idUsuario));
						
						System.out.println("Introduce el Nombre a modificar: ");
						inputLine = input.nextLine();
						usuario.setNombre(inputLine);
		
						System.out.println("Introduce un Password a Modificar: ");
						inputLine = input.nextLine();
						usuario.setPassword(inputLine);
						
						// PERSIT TO THE DATABASE
						usuarioService.guardar(usuario);
						System.out.println("El Usuario se modifico Correctamente");
						
					} else
						System.err.println("\nNo se encontro el Usuario\n");
					
				break;
				
				// GETTING ALL Usuarios ENTITIES FOUND
				case "6" :
					long countUsers = usuarioService.numeroRegistros();
					System.out.println("\nMostrando " + countUsers + " Usuarios Registrados de la Tabla Usuarios:\n");
					Iterable<Usuarios> allUsers = usuarioService.buscarTodos();
					
					for(Usuarios user : allUsers) {
						System.out.println(user);
					}
					
				break;
									
				// GETTING THE NUM OF ROWS OF THE ENTITY Usuarios
				case "7" : 
					System.out.println("\nEl numero de Usuarios registrado es: " 
							+ usuarioService.numeroRegistros());
					
				break;
				
				// DELETING ALL ELEMENTS OF THE Usuario ENTIT
				case "8" : 
					usuarioService.borrarTodo();
					System.out.println("\nSe Eliminaron todos los Usuarios de la Base de Datos");
					
				break;
				
				// FIND ENTITIES WITH DIFFERENTS IDs
				case "9" : 
					System.out.println("\nCuanto Elementos quieres buscar?");
					inputLine = input.nextLine();
					
					int elementToFind = Integer.valueOf(inputLine);
					List<Integer> ids = new LinkedList<Integer>();
					
					for(int i=0; i < elementToFind; i++) {
						System.out.println("Introduzca el ID " + (i+1));
						inputLine = input.nextLine();
						ids.add(Integer.valueOf(inputLine));
					}
					
					System.out.println("\nMostrando los Usuarios seleccionado: \n");
					
					// Getting a List Entities of Usuarios
					Iterable<Usuarios> usuarios = usuarioService.obtenerUsuario(ids);
					for(Usuarios user : usuarios) {
						System.out.println(user);
					}
				break;
				
				case "10" : 
					System.out.println("\n Introduce el ID del Usuario a Buscar");
					inputLine = input.nextLine();
					
					if(usuarioService.existePorId(Integer.valueOf(inputLine))) {
						System.out.println("El Usuario con ID " + inputLine + " si existe y es el siguiente: \n");
						
						Usuarios foundUser = usuarioService.buscarPorId(Integer.valueOf(inputLine));
						System.out.println(foundUser);
						
					} else
						System.err.println("El Usuario no Existe");
					
				break;
				
				case "11" : 
					System.out.println("\nCuanto Usuarios quieres Registrar?");
					inputLine = input.nextLine();
					
					int numUserToBeSaved = Integer.valueOf(inputLine);
					List<Usuarios> newUsers = new LinkedList<Usuarios>();
					
					for(int i=0; i < numUserToBeSaved; i++) {
						
						// Declaring a new User Entity
						Usuarios userEntity = new Usuarios();
						System.out.println("Registrando Usuario numero " + (i+1) + "\n");
						
						System.out.println("Introduzca el nombre: ");
						inputLine= input.nextLine();
						userEntity.setNombre(inputLine);
						
						System.out.println("Introduzca el email: ");
						inputLine = input.nextLine();
						userEntity.setEmail(inputLine);
						
						System.out.println("Insert un nombre de Usuario: ");
						inputLine = input.nextLine();
						userEntity.setUsername(inputLine);
						
						System.out.println("Insert un Password: ");
						inputLine = input.nextLine();
						userEntity.setPassword(inputLine);
						
						userEntity.setStatus(1);
						userEntity.setFechaRegistro(new Date());
						
						// Adding the Usuario Entity to the List to be Save
						newUsers.add(userEntity);
					}
					
					// Saving the new Entities into the Database and returning the new Users Objects
					Iterable<Usuarios> newUsersSaved = usuarioService.guardarVarios(newUsers);
					System.err.println("Se registraron " + numUserToBeSaved + " nuevos Usuarios en la Base de Datos: \n");
					
					// Showing the new User saved
					for(Usuarios user : newUsersSaved) {
						System.out.println(user);
					}
					
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
