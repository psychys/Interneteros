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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cuenta cuenta = (Cuenta) o;
		return Objects.equals(IBAN, cuenta.IBAN) && Objects.equals(SWIFT, cuenta.SWIFT) && Objects.equals(estado, cuenta.estado) && Objects.equals(cuenta_emsior, cuenta.cuenta_emsior) && Objects.equals(cuenta_receptor, cuenta.cuenta_receptor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(IBAN, SWIFT, estado, cuenta_emsior, cuenta_receptor);
	}

	@Override
	public String toString() {
		return "Cuenta{" +
				"IBAN='" + IBAN + '\'' +
				", SWIFT='" + SWIFT + '\'' +
				", estado='" + estado + '\'' ;
	}
}
