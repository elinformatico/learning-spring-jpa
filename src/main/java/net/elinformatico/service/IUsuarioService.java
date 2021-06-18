package net.elinformatico.service;

import net.elinformatico.model.Usuarios;

public interface IUsuarioService {

	public Usuarios guardar(Usuarios usuario);
	public Usuarios buscarPorId(Integer id);
	public void eliminarPorId(Integer id);
	public void eliminarPorEntidad(Usuarios usuario);
}
