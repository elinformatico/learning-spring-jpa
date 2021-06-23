package net.elinformatico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Categoria;
import net.elinformatico.repository.CategoriasRepository;
import net.elinformatico.service.ICategoriasService;

@Service
public class CategoriasService implements ICategoriasService {

	@Autowired 
	private CategoriasRepository repo;
	
	@Override
	public List<Categoria> obtenerCategorias() {
		return repo.findAll(Sort.by("nombre"));
	}

	@Override
	public CategoriasRepository obtenerRepo() {
		return repo;
	}
}
