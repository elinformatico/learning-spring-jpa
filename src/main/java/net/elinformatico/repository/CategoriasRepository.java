package net.elinformatico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.elinformatico.model.Categoria;

public interface CategoriasRepository extends JpaRepository<Categoria, Integer> {

}
