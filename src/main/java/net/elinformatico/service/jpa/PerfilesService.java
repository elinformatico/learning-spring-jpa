package net.elinformatico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Perfil;
import net.elinformatico.model.Vacante;
import net.elinformatico.repository.PefilesRepository;
import net.elinformatico.service.IPerfilesService;

@Service
public class PerfilesService implements IPerfilesService {

	@Autowired
	private PefilesRepository repo;
	
	@Override
	public PefilesRepository obtenerRepo() {
		return repo;
	}

	@Override
	public Perfil guardar(Perfil perfil) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Perfil buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existePorId(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminarPorId(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPorEntidad(Perfil perfil) {
		// TODO Auto-generated method stub

	}

	@Override
	public long numeroRegistros() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void borrarTodo() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Perfil> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> obtenerUsuario(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Perfil> guardarVarios(Iterable<Vacante> usuarios) {
		// TODO Auto-generated method stub
		return null;
	}

}
