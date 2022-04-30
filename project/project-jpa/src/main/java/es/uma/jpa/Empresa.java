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

}