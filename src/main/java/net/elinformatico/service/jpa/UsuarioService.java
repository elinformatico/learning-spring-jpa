package net.elinformatico.service.jpa;

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
	public void eliminarPorEntidad(Usuarios usuario) {
		repo.delete(usuario);
	}
}
