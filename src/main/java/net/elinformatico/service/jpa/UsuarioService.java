package net.elinformatico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Vacante;
import net.elinformatico.repository.UsuariosRepository;
import net.elinformatico.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private UsuariosRepository repo;
	
	@Override
	public Vacante guardar(Vacante usuario) {
		return repo.save(usuario);
	}

	@Override
	public Vacante buscarPorId(Integer id) {
		Optional<Vacante> objUsuario = repo.findById(id);
		if(objUsuario.isPresent())
			return objUsuario.get();
		return null;
	}

	@Override
	public void eliminarPorId(Integer id) {
		repo.deleteById(id);
	}
	
	@Override
	public boolean existePorId(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public void eliminarPorEntidad(Vacante usuario) {
		repo.delete(usuario);
	}

	@Override
	public long numeroRegistros() {
		return repo.count();
	}
	
	// Returning the JPA Object Repository
	@Override
	public UsuariosRepository obtenerRepo() {
		return repo;
	}

	@Override
	public void borrarTodo() {
		repo.deleteAll(); // Delete executing One by One
		// repo.deleteAllInBatch(); // Delete all "DELETE FROM <TABLE>" without WHERE
	}

	@Override
	public List<Vacante> obtenerUsuario(List<Integer> ids) {
		return repo.findAllById(ids);
	}

	@Override
	public List<Vacante> buscarTodos() {
		return repo.findAll();
	}
	
	public List<Vacante> buscarTodos(Sort sort) {
		return repo.findAll(sort);
	}

	@Override
	public List<Vacante> guardarVarios(Iterable<Vacante> usuarios) {
		return repo.saveAll(usuarios);
	}
}
