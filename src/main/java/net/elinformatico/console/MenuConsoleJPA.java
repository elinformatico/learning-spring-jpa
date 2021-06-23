package net.elinformatico.console;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Categoria;
import net.elinformatico.model.Perfil;
import net.elinformatico.model.Usuario;
import net.elinformatico.model.Vacantes;
import net.elinformatico.service.IUsuarioService;
import net.elinformatico.service.jpa.PerfilesService;
import net.elinformatico.service.jpa.UsuarioService;
import net.elinformatico.service.jpa.VacantesService;

@Service
public class MenuConsoleJPA implements IMenuConsole{

	@Autowired
	private UsuarioService usuarioService;
	//@Autowired
	//private IUsuarioService usuarioService;
	
	@Autowired
	private VacantesService vacanteService;
	
	@Autowired
	private PerfilesService perfilService;
	
	private void showMainMenu() {
		System.out.println("\n\n\n-------- Java Persistance API (JPA) Menu -----------\n"
				+ "1) Guardar datos 'save(Entity)' \n"
				+ "2) Buscar Usuario por ID 'findById(Integer)' \n"
				+ "3) Eliminar Usuario por ID 'deleteById(Integer)' \n"
				+ "4) Eliminar Usuario por Entidad 'delete(Entity)' \n"
				+ "5) Modificar Usuario 'save(Entity)' --> with ID to Update\n"
				+ "6.1) Mostrar todos los Usuarios \n"
				+ "6.2) Mostrar todos los Usuarios v2\n"
				+ "7) Obtener numero de Usuarios Registrado 'count()' \n"
				+ "8) Eliminar todos los Usuarios 'deleteAll()' \n"
				+ "9) Buscar por varios ID 'findAllById(Iterable<ID> ids)'\n"
				+ "10) Verificar si existe Usuario por ID 'existsById(Integer)' \n"
				+ "11) Guardar varios Usuarios 'saveAll(Iterable<Usuarios>)' \n\n"
				+ "------- Advance options ----------------------------------\n"
				+ "12) Paginacion de Registros 'Page<T> findAll(Pageable pageable)' \n"
				+ "13) Mostras datos Ligados (SQL INNER) \n"
				+ "14) Guardar Vacante con relacion a tabla Categoria \n"
				+ "15) Registrar Perfiles \n"
				+ "\n (*) Pruebas Rapidas \n"
				+ "(exit) Para salir de la Aplicacion");
	}
	
	@Override
	public void startShowingMenu() {
		
		Scanner input = new Scanner(System.in);
		String option = "";
		String idUsuario = "";
		String inputLine = "";
		Usuario usuario;
		List<Usuario> usuarios;
			
		do {
			showMainMenu();
			option = input.nextLine();
			
			switch (option) {
				
				// PRUEBAS RAPIDAS
				case "*" : 
					
					
				break;
			
				// INSERT VALUES TO THE TABLE "USER"
				case "1" :
					
					Usuario newUser = new Usuario();
					System.out.println("\n\nRegistrando Datos del nuevo Usuario\n\n");

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
				case "6.1" :
					long countUsers = usuarioService.numeroRegistros();
					System.out.println("\nMostrando " + countUsers + " Usuarios Registrados de la Tabla Usuarios:\n");
					Iterable<Usuario> allUsers = usuarioService.buscarTodos();
					
					for(Usuario user : allUsers) {
						System.out.println(user);
					}
					
				break;
				
				case "6.2" : 
					
					// List<Usuarios>
					usuarios = usuarioService.buscarTodos(Sort.by("nombre").descending());
					System.out.println("\nMostrando listado de usuarios registrados: \n");
					for(Usuario user : usuarios) {
						System.out.println(user.getId() + ", " + user.getNombre() + ", " + user.getUsername());
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
					Iterable<Usuario> users = usuarioService.obtenerUsuario(ids);
					for(Usuario user : users) {
						System.out.println(user);
					}
				break;
				
				case "10" : 
					System.out.println("\n Introduce el ID del Usuario a Buscar");
					inputLine = input.nextLine();
					
					if(usuarioService.existePorId(Integer.valueOf(inputLine))) {
						System.out.println("El Usuario con ID " + inputLine + " si existe y es el siguiente: \n");
						
						Usuario foundUser = usuarioService.buscarPorId(Integer.valueOf(inputLine));
						System.out.println(foundUser);
						
					} else
						System.err.println("El Usuario no Existe");
					
				break;
				
				case "11" : 
					System.out.println("\nCuanto Usuarios quieres Registrar?");
					inputLine = input.nextLine();
					
					int numUserToBeSaved = Integer.valueOf(inputLine);
					List<Usuario> newUsers = new LinkedList<Usuario>();
					
					for(int i=0; i < numUserToBeSaved; i++) {
						
						// Declaring a new User Entity
						Usuario userEntity = new Usuario();
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
					Iterable<Usuario> newUsersSaved = usuarioService.guardarVarios(newUsers);
					System.err.println("Se registraron " + numUserToBeSaved + " nuevos Usuarios en la Base de Datos: \n");
					
					// Showing the new User saved
					for(Usuario user : newUsersSaved) {
						System.out.println(user);
					}
					
				break;
				
				case "12" : 
					
					Page<Usuario> paginas= usuarioService.obtenerRepo()
						.findAll(PageRequest.of(1, 3, Sort.by("nombre")));
					
					System.out.println("Numero Total de Registros (getTotalElements): " + paginas.getTotalElements());
					System.out.println("Numero Total de Paginas (getTotalPages): " + paginas.getTotalPages());
					System.out.println("getNumberOfElements: " + paginas.getNumberOfElements());
					
					for(Usuario u : paginas.getContent()) {
						System.out.println(u.getId() + ", " + u.getNombre());
					}
					
				break;
				
				case "13" : 
					// Showing relation tables beetwen Vacantes & Categorias
					System.out.println("\n\nMostrando Vacantes Registradas y su categoria relacionada \n");
					List<Vacantes> vacantes = vacanteService.obtenerRepo().findAll();
					for(Vacantes v : vacantes) {
						System.out.println(v.getId() + ", " + v.getNombre() + ", " + v.getCategoria().getNombre());
					}
				break;
				
				case "14" : 
					
					System.out.println("\nAlmacenando datos para Vacantes \n");
					Vacantes vacante = new Vacantes();
					vacante.setNombre("Full Stack Developer");
					vacante.setDescripcion("Descripcion Fullstack Developer");
					vacante.setFecha(new Date());
					vacante.setSalario(75000.0);
					vacante.setEstatus("Creada");
					vacante.setDestacado(1);
					vacante.setImagen("imageHcl.png");
					vacante.setDetalles("Detalles Vacantes");
					
					// Creating a Category ID --> Full Stack Developer
					Categoria categoria = new Categoria();
					categoria.setId(3);
					
					// Set a Category Object
					vacante.setCategoria(categoria);
					
					// Persist to the Database
					vacanteService.obtenerRepo().save(vacante);
					
					System.out.println("\nSe almaceno la Vacante Correctamente \n");
					
				break;
				
				case "15" : 
					
					System.out.println("\nGuardando Perfiles.....\n");
					List<Perfil> perfiles = new LinkedList<Perfil>();
					Perfil supervisor = new Perfil();
					supervisor.setPerfil("SUPERVISOR");
					
					Perfil admin = new Perfil();
					admin.setPerfil("ADMINISTRADOR");
					
					Perfil user = new Perfil();
					user.setPerfil("USUARIO");
					
					perfiles.add(supervisor);
					perfiles.add(admin);
					perfiles.add(user);
					
					perfilService.obtenerRepo().saveAll(perfiles);
					
				break;
				
				case "exit" : break;
				default:
					System.out.println("\n\nOption Invalida!\n\n");
					break;
			}
		
		}while(!option.contains("exit"));
		
		input.close();
		
		System.out.println("Goodbye! Thanks for learning and using JPA =)");
	}
}
