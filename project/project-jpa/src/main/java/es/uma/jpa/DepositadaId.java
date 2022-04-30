package es.uma.jpa;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((iban_pooled == null) ? 0 : iban_pooled.hashCode());
		result = prime * result
				+ ((iban_referencia == null) ? 0 : iban_referencia.hashCode());
		result = prime * result + saldo;
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
		DepositadaId other = (DepositadaId) obj;
		if (iban_pooled == null) {
			if (other.iban_pooled != null)
				return false;
		} else if (!iban_pooled.equals(other.iban_pooled))
			return false;
		if (iban_referencia == null) {
			if (other.iban_referencia != null)
				return false;
		} else if (!iban_referencia.equals(other.iban_referencia))
			return false;
		if (saldo != other.saldo)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DeposidataId [saldo=" + saldo + ", iban_finetch="
				+ iban_pooled + ", iban_referencia=" + iban_referencia + "]";
	}
	
	
	
}
