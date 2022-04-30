package es.uma.jpa;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

//Entidad debil con saldo

@Entity
@Table(name = "divisa")
public class Divisa {
    @Id
    private String abreviatura;
    @Column(nullable = false)
    private String nombre;
    private String simbolo;
    @Column (nullable = false)
    private double cambio_euro;
    
    @OneToMany (mappedBy = "emisor")
    @JoinTable(name = "divisa_emisor", 
	joinColumns = @JoinColumn(name = "divisa"), 
	inverseJoinColumns = @JoinColumn(name = "id_trans"))
    private List<Transaccion> trans_emsior;
    
    @OneToMany (mappedBy = "receptor")
    @JoinTable(name = "divisa_receptor", 
	joinColumns = @JoinColumn(name = "divisa"), 
	inverseJoinColumns = @JoinColumn(name = "id_trans"))
    private List<Transaccion> trans_receptor;

	@OneToMany (mappedBy = "divisa")
	@JoinTable(name = "divisa_cref",
	joinColumns = @JoinColumn(name = "divisa"),
	inverseJoinColumns = @JoinColumn(name = "c_ref"))
	private List<Cuenta_referencia> c_ref;

	public List<Cuenta_referencia> getC_ref() {
		return c_ref;
	}

	public void setC_ref(List<Cuenta_referencia> c_ref) {
		this.c_ref = c_ref;
	}

	public List<Transaccion> getTrans_emsior() {
		return trans_emsior;
	}




	public void setTrans_emsior(List<Transaccion> trans_emsior) {
		this.trans_emsior = trans_emsior;
	}




	public List<Transaccion> getTrans_receptor() {
		return trans_receptor;
	}




	public void setTrans_receptor(List<Transaccion> trans_receptor) {
		this.trans_receptor = trans_receptor;
	}




	public String getAbreviatura() {
		return abreviatura;
	}




	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}




	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getSimbolo() {
		return simbolo;
	}




	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}




	public double getCambio_euro() {
		return cambio_euro;
	}




	public void setCambio_euro(double cambio_euro) {
		this.cambio_euro = cambio_euro;
	}




	public Divisa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Divisa(String abreviatura, String nombre, String simbolo, double cambio_euro) {
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.cambio_euro = cambio_euro;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Divisa divisa = (Divisa) o;
		return Double.compare(divisa.cambio_euro, cambio_euro) == 0 && Objects.equals(abreviatura, divisa.abreviatura) && Objects.equals(nombre, divisa.nombre) && Objects.equals(simbolo, divisa.simbolo) && Objects.equals(trans_emsior, divisa.trans_emsior) && Objects.equals(trans_receptor, divisa.trans_receptor) && Objects.equals(c_ref, divisa.c_ref);
	}

	@Override
	public int hashCode() {
		return Objects.hash(abreviatura, nombre, simbolo, cambio_euro, trans_emsior, trans_receptor, c_ref);
	}

	public Divisa(String abreviatura, String nombre, String simbolo, double cambio_euro, List<Transaccion> trans_emsior, List<Transaccion> trans_receptor, List<Cuenta_referencia> c_ref) {
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.cambio_euro = cambio_euro;
		this.trans_emsior = trans_emsior;
		this.trans_receptor = trans_receptor;
		this.c_ref = c_ref;

	}

	@Override
	public String toString() {
		return "Divisa{" +
				"abreviatura='" + abreviatura + '\'' +
				", nombre='" + nombre + '\'' +
				", simbolo='" + simbolo + '\'' +
				", cambio_euro=" + cambio_euro +
				", trans_emsior=" + trans_emsior +
				", trans_receptor=" + trans_receptor +
				", c_ref=" + c_ref +
				'}';
	}
}