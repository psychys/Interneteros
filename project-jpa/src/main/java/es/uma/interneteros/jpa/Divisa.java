package es.uma.interneteros.jpa;

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
    
    @OneToMany (mappedBy = "emisor")
    //@JoinTable(name = "divisa_emisor",
	//joinColumns = @JoinColumn(name = "divisa"),
	//inverseJoinColumns = @JoinColumn(name = "id_trans"))
    private List<Transaccion> trans_emsior;
    
    @OneToMany (mappedBy = "receptor")
    //@JoinTable(name = "divisa_receptor",
	//joinColumns = @JoinColumn(name = "divisa"),
	//inverseJoinColumns = @JoinColumn(name = "id_trans"))
    private List<Transaccion> trans_receptor;

	@OneToMany (mappedBy = "divisa")
	//@JoinTable(name = "divisa_cref",
	//joinColumns = @JoinColumn(name = "divisa"),
	//inverseJoinColumns = @JoinColumn(name = "c_ref"))
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





	public Divisa() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Divisa(String abreviatura, String nombre, String simbolo) {
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
	}

	@Override
	public String toString() {
		return "Divisa{" +
				"abreviatura='" + abreviatura + '\'' +
				", nombre='" + nombre + '\'' +
				", simbolo='" + simbolo + '\'' +
				", trans_emsior=" + trans_emsior +
				", trans_receptor=" + trans_receptor +
				", c_ref=" + c_ref +
				'}';
	}

	@Override
	public int hashCode() {
		return Objects.hash(abreviatura, nombre, simbolo, trans_emsior, trans_receptor, c_ref);
	}

	public Divisa(String abreviatura, String nombre, String simbolo, List<Transaccion> trans_emsior, List<Transaccion> trans_receptor, List<Cuenta_referencia> c_ref) {
		this.abreviatura = abreviatura;
		this.nombre = nombre;
		this.simbolo = simbolo;
		this.trans_emsior = trans_emsior;
		this.trans_receptor = trans_receptor;
		this.c_ref = c_ref;

	}


}