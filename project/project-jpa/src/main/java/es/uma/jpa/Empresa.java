package es.uma.jpa;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Empresa extends Cliente{

	@Column(nullable = false)
	private String Razon_Social;

	@OneToMany(mappedBy = "empresa")
	private Set<AutorizacionEmpresaAutorizado> empresaAutorizadoSet = new HashSet<AutorizacionEmpresaAutorizado>();

	public String getRazon_Social() {
		return Razon_Social;
	}

	public void setRazon_Social(String razon_Social) {
		Razon_Social = razon_Social;
	}

	public Set<AutorizacionEmpresaAutorizado> getEmpresaAutorizadoSet() {
		return empresaAutorizadoSet;
	}

	public void setEmpresaAutorizadoSet(Set<AutorizacionEmpresaAutorizado> empresaAutorizadoSet) {
		this.empresaAutorizadoSet = empresaAutorizadoSet;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Empresa empresa = (Empresa) object;
		return java.util.Objects.equals(Razon_Social, empresa.Razon_Social) && java.util.Objects.equals(empresaAutorizadoSet, empresa.empresaAutorizadoSet);
	}

	public int hashCode() {
		//return Objects.hash(super.hashCode(), Razon_Social, empresaAutorizadoSet);
		return Razon_Social.hashCode() + empresaAutorizadoSet.hashCode();
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Empresa{" +
				"Razon_Social='" + Razon_Social + '\'' +
				", empresaAutorizadoSet=" + empresaAutorizadoSet +
				'}';
	}
}