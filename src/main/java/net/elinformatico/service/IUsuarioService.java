package net.elinformatico.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import net.elinformatico.model.Vacante;
import net.elinformatico.repository.UsuariosRepository;

public interface IUsuarioService {

	public UsuariosRepository obtenerRepo();
	public Vacante guardar(Vacante usuario);
	public Vacante buscarPorId(Integer id);
	public boolean existePorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Vacante usuario);
	public long numeroRegistros();
	public void borrarTodo();
	public List<Vacante> buscarTodos();
	public List<Vacante> buscarTodos(Sort sort);
	public List<Vacante> obtenerUsuario(List<Integer> ids);
	public List<Vacante> guardarVarios(Iterable<Vacante> usuarios);
}
