package es.uma.jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AutorizacionId implements Serializable {
	
	private int ID_empresa;
	private int ID_autorizado;
	private String tipo;
	
	
	
	public AutorizacionId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getID_empresa() {
		return ID_empresa;
	}
	public void setID_empresa(int id){ ID_empresa = id;}
	public void setID_cliente(int iD_cliente) {
		ID_empresa = iD_cliente;
	}
	public int getID_autorizado() {
		return ID_autorizado;
	}
	public void setID_autorizado(int iD_autorizado) {
		ID_autorizado = iD_autorizado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID_autorizado;
		result = prime * result + ID_empresa;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		AutorizacionId other = (AutorizacionId) obj;
		if (ID_autorizado != other.ID_autorizado)
			return false;
		if (ID_empresa != other.ID_empresa)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AutorizacionId [ID_cliente=" + ID_empresa + ", ID_autorizado="
				+ ID_autorizado + ", tipo=" + tipo + "]";
	}
	
	

}
