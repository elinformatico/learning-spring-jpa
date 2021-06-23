package net.elinformatico.service;

import java.util.List;

import net.elinformatico.model.Categoria;
import net.elinformatico.repository.CategoriasRepository;

public interface ICategoriasService {
	public List<Categoria> obtenerCategorias();
	public CategoriasRepository obtenerRepo();
}
