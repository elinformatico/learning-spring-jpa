package net.elinformatico.model;

import java.util.Date;

public class Solicitudes {

	private int id;
	private Date fecha;
	private String archivo;
	private String comentarios;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	@Override
	public String toString() {
		return "Solicitudes [id=" + id + ", fecha=" + fecha + ", archivo=" + archivo + ", comentarios=" + comentarios
				+ "]";
	}
}
