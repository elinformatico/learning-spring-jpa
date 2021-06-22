package net.elinformatico.service;

import java.util.List;
import net.elinformatico.model.Vacantes;

public interface IVacanteService {

	public Vacantes guardar(Vacantes usuario);
	public Vacantes buscarPorId(Integer id);
	public boolean existePorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Vacantes vacante);
	public long numeroRegistros();
	public void borrarTodo();
	public List<Vacantes> buscarTodo();
	public List<Vacantes> obtenerUsuario(List<Integer> ids);
	public List<Vacantes> guardarVarios(List<Vacantes> vacantes);
}
