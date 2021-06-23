package net.elinformatico.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import net.elinformatico.model.Usuario;
import net.elinformatico.repository.UsuariosRepository;

public interface IUsuarioService {

	public UsuariosRepository obtenerRepo();
	public Usuario guardar(Usuario usuario);
	public Usuario buscarPorId(Integer id);
	public boolean existePorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Usuario usuario);
	public long numeroRegistros();
	public void borrarTodo();
	public List<Usuario> buscarTodos();
	public List<Usuario> buscarTodos(Sort sort);
	public List<Usuario> obtenerUsuario(List<Integer> ids);
	public List<Usuario> guardarVarios(Iterable<Usuario> usuarios);
}
