package net.elinformatico;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.elinformatico.model.Usuarios;
import net.elinformatico.repository.UsuariosRepository;

@SpringBootApplication
public class SpringJpaApplication implements CommandLineRunner {

	@Autowired
	private UsuariosRepository usuariosRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(" -------- Java Persistance API (JPA) Running -----------");
		
		// Guardando un registro en la Base de Datos
		guardar();
		
		
		System.out.println(" -------------------------------------------------------");
	}
	
	private void guardar() {
		Usuarios usuario = new Usuarios();
		usuario.setNombre("Noe Hernandez");
		usuario.setUsername("elinformatico");
		usuario.setPassword("Noe321");
		usuario.setEmail("donoe1985@gmail.com");
		usuario.setStatus(1);
		usuario.setFechaRegistro(new Date());
		
		usuariosRepo.save(usuario);
		
		System.out.println(usuario);
	}
}
