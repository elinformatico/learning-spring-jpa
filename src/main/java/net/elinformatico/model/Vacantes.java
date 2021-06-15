package net.elinformatico.model;

import java.util.Date;

public class Vacantes {
	
	private int id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Double salario;
	private String status;
	private int destacado;
	private String imagen;
	private String detalles;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getDestacado() {
		return destacado;
	}
	public void setDestacado(int destacado) {
		this.destacado = destacado;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getDetalles() {
		return detalles;
	}
	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	@Override
	public String toString() {
		return "Vacantes [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ ", salario=" + salario + ", status=" + status + ", destacado=" + destacado + ", imagen=" + imagen
				+ ", detalles=" + detalles + "]";
	}
}
