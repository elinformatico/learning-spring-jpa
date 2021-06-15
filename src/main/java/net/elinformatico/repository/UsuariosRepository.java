package net.elinformatico.repository;

import org.springframework.data.repository.CrudRepository;
import net.elinformatico.model.Usuarios;

public interface UsuariosRepository extends CrudRepository<Usuarios, Integer> {
	
}
