package es.uma.jpa;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Cuenta {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private String IBAN;
	private String SWIFT;
	@Column(nullable = false)
	private String estado;

	@OneToMany (mappedBy = "origen")
	@JoinTable(name = "cuenta_origen", 
	joinColumns = @JoinColumn(name = "cuenta"), 
	inverseJoinColumns = @JoinColumn(name = "id_trans"))
	private List<Transaccion> cuenta_emsior;
	
	@OneToMany (mappedBy = "destino")
	@JoinTable(name = "cuenta_receptor", 
	joinColumns = @JoinColumn(name = "divisa"), 
	inverseJoinColumns = @JoinColumn(name = "id_trans"))
	private List<Transaccion> cuenta_receptor;
	
	public Cuenta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cuenta(String IBAN, String SWIFT, String estado) {
		this.IBAN = IBAN;
		this.SWIFT = SWIFT;
		this.estado = estado;
	}

	public String getIBAN() {
		return IBAN;
	}
	public void setIBAN(String iBAN) {
		IBAN = iBAN;
	}
	public String getSWIFT() {
		return SWIFT;
	}
	public void setSWIFT(String sWIFT) {
		SWIFT = sWIFT;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Transaccion> getCuenta_emsior() {
		return cuenta_emsior;
	}

	public void setCuenta_emsior(List<Transaccion> cuenta_emsior) {
		this.cuenta_emsior = cuenta_emsior;
	}

	public List<Transaccion> getCuenta_receptor() {
		return cuenta_receptor;
	}

	public void setCuenta_receptor(List<Transaccion> cuenta_receptor) {
		this.cuenta_receptor = cuenta_receptor;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		Cuenta cuenta = (Cuenta) object;
		return java.util.Objects.equals(IBAN, cuenta.IBAN) && java.util.Objects.equals(SWIFT, cuenta.SWIFT) && java.util.Objects.equals(estado, cuenta.estado);// && java.util.Objects.equals(cuenta_emsior, cuenta.cuenta_emsior) && java.util.Objects.equals(cuenta_receptor, cuenta.cuenta_receptor);
	}

	public int hashCode() {
		return Objects.hash(super.hashCode(), IBAN, SWIFT, estado);//, cuenta_emsior, cuenta_receptor);
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "Cuenta{" +
				"IBAN='" + IBAN + '\'' +
				", SWIFT='" + SWIFT + '\'' +
				", estado='" + estado + '\'';/* +
				", cuenta_emsior=" + cuenta_emsior +
				", cuenta_receptor=" + cuenta_receptor +
				'}';*/
	}
}
