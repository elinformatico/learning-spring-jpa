package net.elinformatico.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String email;
	private String username;
	private String password;
	private int estatus;
	private Date FechaRegistro;
	
	/*** Set Many to Many to the Table/Entity Perfil 
	 * Many User can have Many Perfiles
	 * */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "usuarioperfil", // External Table to JOIN the colum
			joinColumns = @JoinColumn(name = "idUsuario"),    // Primary Key of the Table User
			inverseJoinColumns = @JoinColumn(name="idPerfil") // Foreign Key if the another Table/Entity
		)
	private List<Perfil> perfiles;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getEstatus() {
		return estatus;
	}
	public void setEstatus(int status) {
		this.estatus = status;
	}
	public Date getFechaRegistro() {
		return FechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		FechaRegistro = fechaRegistro;
	}
	public List<Perfil> getPerfiles() {
		return perfiles;
	}
	public void setPerfiles(List<Perfil> perfiles) {
		this.perfiles = perfiles;
	}
	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nombre=" + nombre + ", email=" + email + ", username=" + username
				+ ", password=" + password + ", status=" + estatus + ", FechaRegistro=" + FechaRegistro + "]";
	}
}
