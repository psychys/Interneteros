package es.uma.interneteros.jpa;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class DepositadaId implements Serializable {

	private int saldo;
	private String iban_pooled;
	private String iban_referencia;
	public DepositadaId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	public String getIban_finetch() {
		return iban_pooled;
	}
	public void setIban_finetch(String iban_finetch) {
		this.iban_pooled = iban_finetch;
	}
	public String getIban_referencia() {
		return iban_referencia;
	}
	public void setIban_referencia(String iban_referencia) {
		this.iban_referencia = iban_referencia;
	}

	public String getIban_pooled() {
		return iban_pooled;
	}

	public void setIban_pooled(String iban_pooled) {
		this.iban_pooled = iban_pooled;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (object == null || getClass() != object.getClass()) return false;
		if (!super.equals(object)) return false;
		DepositadaId that = (DepositadaId) object;
		return saldo == that.saldo && java.util.Objects.equals(iban_pooled, that.iban_pooled) && java.util.Objects.equals(iban_referencia, that.iban_referencia);
	}

	public int hashCode() {
		//return Objects.hash(super.hashCode(), saldo, iban_pooled, iban_referencia);
		return Integer.hashCode(saldo) + iban_pooled.hashCode() + iban_referencia.hashCode();
	}

	@java.lang.Override
	public java.lang.String toString() {
		return "DepositadaId{" +
				"saldo=" + saldo +
				", iban_pooled='" + iban_pooled + '\'' +
				", iban_referencia='" + iban_referencia + '\'' +
				'}';
	}
}
