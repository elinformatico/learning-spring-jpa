package net.elinformatico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Usuarios;
import net.elinformatico.repository.UsuariosRepository;
import net.elinformatico.service.IUsuarioService;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private UsuariosRepository repo;
	
	@Override
	public Usuarios guardar(Usuarios usuario) {
		return repo.save(usuario);
	}

	@Override
	public Usuarios buscarPorId(Integer id) {
		Optional<Usuarios> objUsuario = repo.findById(id);
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
	public void eliminarPorEntidad(Usuarios usuario) {
		repo.delete(usuario);
	}

	@Override
	public long numeroRegistros() {
		return repo.count();
	}
	
	// Returning the Object Repository
	public UsuariosRepository obtenerRepo() {
		return repo;
	}

	@Override
	public void borrarTodo() {
		repo.deleteAll();
	}

	@Override
	public Iterable<Usuarios> obtenerUsuario(List<Integer> ids) {
		return repo.findAllById(ids);
	}

	@Override
	public Iterable<Usuarios> buscarTodos() {
		return repo.findAll();
	}

	@Override
	public Iterable<Usuarios> guardarVarios(Iterable<Usuarios> usuarios) {
		return repo.saveAll(usuarios);
	}
}
