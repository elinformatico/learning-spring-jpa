package net.elinformatico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.elinformatico.model.Vacantes;
import net.elinformatico.repository.VacantesRepository;
import net.elinformatico.service.IVacanteService;

@Service
public class VacantesService implements IVacanteService {

	@Autowired
	private VacantesRepository repo;
	
	@Override
	public Vacantes guardar(Vacantes usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vacantes buscarPorId(Integer id) {
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
	public void eliminarPorEntidad(Vacantes vacante) {
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
	public List<Vacantes> buscarTodo() {
		return repo.findAll();
	}

	@Override
	public List<Vacantes> obtenerUsuario(List<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Vacantes> guardarVarios(List<Vacantes> vacantes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public VacantesRepository obtenerRepo() {
		return repo;
	}

}
