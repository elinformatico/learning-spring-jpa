package net.elinformatico.service.jpa;

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
	public Usuarios guardarUsuario(Usuarios usuario) {
		return repo.save(usuario);
	}
}
