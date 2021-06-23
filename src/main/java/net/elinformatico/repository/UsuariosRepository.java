package net.elinformatico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import net.elinformatico.model.Vacante;

public interface UsuariosRepository extends JpaRepository<Vacante, Integer> {
	
}
