package net.elinformatico.service;

import java.util.List;

import net.elinformatico.model.Usuarios;

public interface IUsuarioService {

	public Usuarios guardar(Usuarios usuario);
	public Usuarios buscarPorId(Integer id);
	public boolean existePorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Usuarios usuario);
	public long numeroRegistros();
	public void borrarTodo();
	public Iterable<Usuarios> buscarTodos();
	public Iterable<Usuarios> obtenerUsuario(List<Integer> ids);
	public Iterable<Usuarios> guardarVarios(Iterable<Usuarios> usuarios);
}
