package net.elinformatico;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Categoria;
import net.elinformatico.model.Perfil;
import net.elinformatico.model.Usuario;
import net.elinformatico.model.Vacantes;
import net.elinformatico.service.ICategoriasService;
import net.elinformatico.service.IUsuarioService;
import net.elinformatico.service.jpa.PerfilesService;
import net.elinformatico.service.jpa.VacantesService;

@Service
public class JpaExamples {

	private Scanner input;
	private String inputLine;
	
	public JpaExamples() {
		input = new Scanner(System.in);
	}
	
	/**
	 * Inject Usuario Service to consume JPA Funtions using the IUsuarioService instead UsuarioService
	 * */
	@Autowired
	private IUsuarioService usuarioService;
	
	/**
	 * Inject Usuario Service to consume JPA Funtions using the UsuarioService instead IUsuarioService
	 * @Autowired
	 * private UsuarioService usuarioService; 
	 * */
	
	@Autowired
	private VacantesService vacanteService;

	@Autowired
	private PerfilesService perfilService;
	
	@Autowired
	private ICategoriasService categoriaService;
	
	/**
	 * Get all Categories order by name
	 * @JPAExample findAll(Sort sort)
	 * @author Noe Hernandez
	 * @return List<Categoria>
	 */
	public void mostrarCategorias() 
	{
		System.out.println("Mostrando Categorias Registradas");
		List<Categoria> categorias = categoriaService.obtenerCategorias();
		
		for(Categoria c : categorias) {
			System.out.println(c.getId() + ", " + c.getNombre());
		}
	}
	
	/**
	 * Save a Perfil to the Database
	 * @JPAExample save(Entity perfil)
	 * @author Noe Hernandez
	 * @return Perfil
	 */
	public void registrarPerfil() 
	{
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
	}
	
	/**
	 * Save Vacante to the Database using a Foreign Key
	 * @JPAExample save(Entity vacante)
	 * @author Noe Hernandez
	 * @return Vacante
	 */
	public void registrarVacante() 
	{
		System.out.println("\nRegistrando una Vacante \n");
		Vacantes vacante = new Vacantes();
		
		System.out.println("Introduzca un nombre de Vacante:");
		inputLine = input.nextLine();
		vacante.setNombre(inputLine);
		
		System.out.println("Introduzca una descripcion para la Vacante:");
		inputLine = input.nextLine();
		vacante.setDescripcion(inputLine);
		
		System.out.println("Introduzca los detalles para la Vacante:");
		inputLine = input.nextLine();
		vacante.setDetalles(inputLine);
		
		vacante.setFecha(new Date());
		
		System.out.println("Introduzca un Salario (Solo numerico):");
		inputLine = input.nextLine();
		vacante.setSalario(Double.valueOf(inputLine));
		
		System.out.println("Introduzca un estatus ['Creada','Aprobada','Eliminada']:");
		inputLine = input.nextLine();
		vacante.setEstatus(inputLine);
		
		System.out.println("??Es una Vacantes destacada? si/no");
		inputLine = input.nextLine();
		vacante.setDestacado((inputLine.contains("si") ? 1 : 0));
		
		System.out.println("Introduzca un nombre de imagen sin extension:");
		inputLine = input.nextLine();
		vacante.setImagen(inputLine + ".png");
				
		System.out.println("Introduzca un ID de Categoria de acuerdo a las siguientes Categorias: \n");

		// Getting Categories
		List<Categoria> categorias = categoriaService.obtenerCategorias();
		for(Categoria c : categorias) {
			System.err.println("ID: " + c.getId() + ", " + c.getNombre());
		}
		System.out.println("Introduzca el ID de Categoria: ");
		inputLine = input.nextLine();

		// Set the Category ID only
		Categoria categoria = new Categoria();
		categoria.setId(Integer.valueOf(inputLine));
		
		// Set a Category Object
		vacante.setCategoria(categoria);
		
		// Persist to the Database
		vacanteService.obtenerRepo().save(vacante);
		
		System.out.println("\nSe almaceno la Vacante Correctamente \n");
	}
	
	/**
	 * Get Users joining (One By One) tables (Entities)
	 * @JPAExample findAll()
	 * @author Noe Hernandez
	 * @return List<Vacante>
	 */
	public void mostrandoUsuariosUsandoJoinUnoaUno() 
	{
		// Showing relation tables beetwen Vacantes & Categorias
		System.out.println("\n\nMostrando Vacantes Registradas y su categoria relacionada \n");
		List<Vacantes> vacantes = vacanteService.obtenerRepo().findAll();
		for(Vacantes v : vacantes) {
			System.out.println(v.getId() + ", " + v.getNombre() + ", " + v.getCategoria().getNombre());
		}
	}
	
	/**
	 * Get Users using Paging
	 * @JPAExample findAll(Pageable pageable)
	 * @author Noe Hernandez
	 * @return Page<Usuario>
	 */
	public void mostrarUsuariosUsandoPaginacion() 
	{
		System.out.println("??Cuantos elementos desea mostrar por Pagina?");
		inputLine = input.nextLine();
		int size = Integer.valueOf(inputLine); 
		
		System.out.println("Introduzca el numero de Pagina a Mostrar");
		inputLine = input.nextLine();
		int page = Integer.valueOf(inputLine);
		
		System.out.println("??Por cual columna deseas ordernarlo?");
		inputLine = input.nextLine();
		String orderBy = inputLine;
				
		Page<Usuario> paginas= usuarioService.obtenerRepo()
				.findAll(PageRequest.of(page, size, Sort.by(orderBy)));

		System.out.println("Value of getTotalElements(): " + paginas.getTotalElements());
		System.out.println("Value of getTotalPages(): " + paginas.getTotalPages());
		System.out.println("Value of getNumberOfElements(): " + paginas.getNumberOfElements());

		for(Usuario u : paginas.getContent()) {
			System.out.println(u.getId() + ", " + u.getNombre());
		}
	}
	
	/**
	 * Creating multiples users in the Database
	 * @JPAExample saveAll(Iterable<Usuarios>)
	 * @author Noe Hernandez
	 * @return List<Usuario>
	 */
	public void guardarVariosUsuarios() 
	{
		System.out.println("\n??Cuantos Usuarios quieres Registrar?");
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
			
			userEntity.setEstatus(1);
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
		
	}
	
	/**
	 * Check if an Users exist on the Data Base
	 * @JPAExample existsById(Integer)
	 * @author Noe Hernandez
	 * @return boolean
	 */
	public void verificarSiExisteUsuario() 
	{
		System.out.println("\n Introduce el ID del Usuario a Buscar");
		inputLine = input.nextLine();
		
		if(usuarioService.existePorId(Integer.valueOf(inputLine))) {
			System.out.println("El Usuario con ID " + inputLine + " si existe y es el siguiente: \n");
			
			Usuario foundUser = usuarioService.buscarPorId(Integer.valueOf(inputLine));
			System.out.println(foundUser);
			
		} else
			System.err.println("\nEl Usuario no Existe\n");
	}
	
	/**
	 * Get Users using a collections of ids
	 * @JPAExample findAllById(Iterable<Integer> ids)
	 * @author Noe Hernandez
	 * @return List<Usuario>
	 */
	public void buscarUsuariosUsandoIds() 
	{
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
	}
	
	/**
	 * Get the number of Users
	 * @JPAExample deleteAll(One by One) & deleteAllInBatch (Delete without WHERE)
	 * @author Noe Hernandez
	 */
	public void eliminarTodosLosUsuarios() 
	{
		System.out.println("??Estas seguro de eliminar todos los Usuarios? y/n");
		inputLine = input.nextLine();
		
		if(inputLine.contains("y") || inputLine.contains("si")) 
		{
			usuarioService.borrarTodo();
			System.out.println("\nSe Eliminaron todos los Usuarios de la Base de Datos");
		}
	}
	
	/**
	 * Get the number of Users
	 * @JPAExample 
	 * @author Noe Hernandez
	 * @return Integer (Number of Users)
	 */
	public void obtenerNumeroDeUsuarios() 
	{
		long numOfUsers = usuarioService.numeroRegistros();  
		System.out.println("\nEl numero de Usuarios registrado es: " + numOfUsers + "\n");
	}
	
	/**
	 * Show users using Sort
	 * @JPAExample findAll(Sort sort)
	 * @author Noe Hernandez
	 * @return List<Usuario>
	 */
	public void mostrarUsuariosUsandoOrdenamiento() 
	{
		System.out.println("??Por cual columna deseas ordenar?");
		String orderBy = input.nextLine();
		
		System.out.println("??Que tipo de Ordenamiento? asc o des");
		String typeOrder = input.nextLine();
		
		List<Usuario> usuarios = null; 
		if(typeOrder.contains("asc")) {
			usuarios = usuarioService.buscarTodos(Sort.by(orderBy).ascending());
		} else if(typeOrder.contains("des")) {
			usuarios = usuarioService.buscarTodos(Sort.by(orderBy).descending());
		}
		
		if(usuarios != null) {
			System.out.println("\nMostrando listado de usuarios registrados: \n");
			for(Usuario user : usuarios) {
				System.out.println(user.getId() + ", " + user.getNombre() + ", " + user.getUsername());
			} 
		} else {
			System.err.println("Ocurrio un error al configurar el ordenamiento");
		}
	}
	
	/**
	 * Show users without Sort
	 * @JPAExample findAll();
	 * @author Noe Hernandez
	 * @return List<Usuario>
	 */
	public void mostrarUsuarios() 
	{	
		long countUsers = usuarioService.numeroRegistros();
		System.out.println("\nMostrando " + countUsers + " Usuarios Registrados de la Tabla Usuarios:\n");
		Iterable<Usuario> allUsers = usuarioService.buscarTodos();
		
		for(Usuario user : allUsers) {
			System.out.println(user);
		}
	}
	
	/**
	 * Update an User using an User as Entity
	 * @JPAExample save(Entity user) & existsById(Integer id);
	 * @author Noe Hernandez
	 */
	public void modificarUsuario() 
	{
		System.out.println("\nModificando Usuario:\n");
		System.out.println("Introduzca el ID del Usuario a modificar "
				+ "(Solo le modificara el nombre y Password)");
		String idUsuario = input.nextLine();
		
		// Checking if the Entity exist
		boolean existeUsuario = usuarioService.existePorId(Integer.valueOf(idUsuario));
		
		if(existeUsuario) {
			
			// LOCATE THE USER ENTITY FIRST
			Usuario usuario = usuarioService.buscarPorId(Integer.valueOf(idUsuario));
			
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
	}
	
	/**
	 * Delete an User using an User as Entity
	 * @JPAExample deleteById(Integer id);
	 * @author Noe Hernandez
	 */
	public void eliminarUsuarioPorEntidad() 
	{
		System.out.println("\nEliminar Usuario por Identidad\n");
		System.out.println("Introduzca el ID del Usuaria que sera buscado");
		String idUsuario = input.nextLine();
		
		Usuario usuario = usuarioService.buscarPorId(Integer.valueOf(idUsuario));
		usuarioService.eliminarPorEntidad(usuario);
	}
	
	/**
	 * Delete an User Entity using a ID as Integer
	 * @JPAExample deleteById(Integer id);
	 * @author Noe Hernandez
	 */
	public void eliminarUsuarioPorId() 
	{
		System.out.println("\nEliminar Usuario por ID\n");
		System.out.println("Introduzca el ID del Usuario a eliminar ");
		String idUsuario = input.nextLine();
		
		usuarioService.eliminarPorId(Integer.valueOf(idUsuario));
		
		System.out.println("\nSe elimino el usuario con el ID: " + idUsuario + "\n");
	}
	
	/**
	 * Find an User Entity using a Usuario as Entity
	 * @JPAExample 
	 * @author Noe Hernandez
	 */
	public void buscarUsuarioPorEntidad() 
	{
		
	}
	
	/**
	 * Find an User Entity using a ID as Integer
	 * @JPAExample findById(Integer id)
	 * @author Noe Hernandez
	 */
	public void buscarUsuarioPorId() 
	{	
		String idUsuario = "";
		System.out.println("\nBuscar Usuario por ID\n\n");
		System.out.println("Introduzca el ID del Usuario: ");
		idUsuario = input.nextLine();
		
		Usuario usuario = usuarioService.buscarPorId(Integer.valueOf(idUsuario));
		
		System.out.println("\nSe encontro el siguiente usuario: \n" + usuario + "\n");
	}
	
	/**
	 * Create an User in the Database
	 * @JPAExample save(Entity)
	 * @author Noe Hernandez
	 */
	public void registrarUsuario() 
	{	
		Usuario newUser = new Usuario();
		System.out.println("\nRegistrando Datos del nuevo Usuario\n");

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

		newUser.setEstatus(1);
		newUser.setFechaRegistro(new Date());

		usuarioService.guardar(newUser);

		System.out.println("\nSe registro el Usuario " + newUser.getNombre() + " en la Base de Datos! \n");
		System.out.println(newUser);
	}
	
	
}
