package net.elinformatico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.elinformatico.model.Solicitud;

public interface SolicitudesRepository extends JpaRepository<Solicitud, Integer> {

}
