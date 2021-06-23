package net.elinformatico.service;

import java.util.List;

import net.elinformatico.model.Vacante;

public interface IUsuarioService {

	public Vacante guardar(Vacante usuario);
	public Vacante buscarPorId(Integer id);
	public boolean existePorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Vacante usuario);
	public long numeroRegistros();
	public void borrarTodo();
	public Iterable<Vacante> buscarTodos();
	public Iterable<Vacante> obtenerUsuario(List<Integer> ids);
	public Iterable<Vacante> guardarVarios(Iterable<Vacante> usuarios);
}
