package net.elinformatico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Usuario;
import net.elinformatico.repository.UsuariosRepository;
import net.elinformatico.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private UsuariosRepository repo;
	
	@Override
	public Usuario guardar(Usuario usuario) {
		return repo.save(usuario);
	}

	@Override
	public Usuario buscarPorId(Integer id) {
		Optional<Usuario> objUsuario = repo.findById(id);
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
	public void eliminarPorEntidad(Usuario usuario) {
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
	public List<Usuario> obtenerUsuario(List<Integer> ids) {
		return repo.findAllById(ids);
	}

	@Override
	public List<Usuario> buscarTodos() {
		return repo.findAll();
	}
	
	public List<Usuario> buscarTodos(Sort sort) {
		return repo.findAll(sort);
	}

	@Override
	public List<Usuario> guardarVarios(Iterable<Usuario> usuarios) {
		return repo.saveAll(usuarios);
	}
}
