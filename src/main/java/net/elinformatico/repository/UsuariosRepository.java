package net.elinformatico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.elinformatico.model.Usuario;

public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {
	
}
