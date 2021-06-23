package net.elinformatico.service;

import java.util.List;

import net.elinformatico.model.Perfil;
import net.elinformatico.model.Usuario;
import net.elinformatico.repository.PefilesRepository;

public interface IPerfilesService {
	public PefilesRepository obtenerRepo();
	public Perfil guardar(Perfil perfil);
	public Perfil buscarPorId(Integer id);
	public boolean existePorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Perfil perfil);
	public long numeroRegistros();
	public void borrarTodo();
	public List<Perfil> buscarTodos();
	public List<Perfil> obtenerUsuario(List<Integer> ids);
	public List<Perfil> guardarVarios(Iterable<Usuario> usuarios);
}
