package Modelo;
import javax.persistence.*;

import org.hibernate.annotations.GenerationTime;
@Entity
@Table (name="pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="IDPEDIDO")
	private int idpedido;
	@Column (name="NOMBRE")
	private String nombre;
	@Column (name="APELLIDOS")
	private String apellidos;
	@Column (name="DNI")
	private String dni;
	@Column (name="CORREO")
	private String correo;
	@Column (name="telegono")
	private String telefono;
	@Column (name="tpedido")
	private String tpedido;
	@Column (name="obs")
	private String obs;
	public Pedido(String nombre, String apellidos, String dni, String correo, String telefono, String tpedido,
			String obs) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.correo = correo;
		this.telefono = telefono;
		this.tpedido = tpedido;
		this.obs = obs;
	}
	public Pedido() {
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((correo == null) ? 0 : correo.hashCode());
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + idpedido;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((obs == null) ? 0 : obs.hashCode());
		result = prime * result + ((telefono == null) ? 0 : telefono.hashCode());
		result = prime * result + ((tpedido == null) ? 0 : tpedido.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (correo == null) {
			if (other.correo != null)
				return false;
		} else if (!correo.equals(other.correo))
			return false;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (idpedido != other.idpedido)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (obs == null) {
			if (other.obs != null)
				return false;
		} else if (!obs.equals(other.obs))
			return false;
		if (telefono == null) {
			if (other.telefono != null)
				return false;
		} else if (!telefono.equals(other.telefono))
			return false;
		if (tpedido == null) {
			if (other.tpedido != null)
				return false;
		} else if (!tpedido.equals(other.tpedido))
			return false;
		return true;
	}
	public int getIdpedido() {
		return idpedido;
	}
	public void setIdpedido(int idpedido) {
		this.idpedido = idpedido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getTpedido() {
		return tpedido;
	}
	public void setTpedido(String tipo) {
		this.tpedido = tipo;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	@Override
	public String toString() {
		return "Pedido [idpedido=" + idpedido + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni
				+ ", correo=" + correo + ", telefono=" + telefono + ", tpedido=" + tpedido + ", obs=" + obs + "]";
	}
	
	
	
	
}
	