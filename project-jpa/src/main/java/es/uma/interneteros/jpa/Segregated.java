package es.uma.interneteros.jpa;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.util.Date;


@Entity
public class Segregated extends Cuenta_Fintech {


	//private String tipo;

	private int comision;
	@OneToOne //(mappedBy ="c_fintech_segregada")
	private Cuenta_referencia c_ref;

	public Segregated(String IBAN, String SWIFT, String estado, Date fecha_apertura, Cuenta_referencia c_ref) {
		super(IBAN, SWIFT, estado, fecha_apertura);
		this.c_ref = c_ref;
	}

	public Segregated(String IBAN, String SWIFT, String estado, Date fecha_apertura, Cuenta_referencia c_ref, String tipo, Cliente cliente) {
		super(IBAN, SWIFT, estado, fecha_apertura,tipo, cliente);
		this.c_ref = c_ref;
	}

	/*public Segregated(int comision , Cuenta_referencia c) {
                super();
                this.comision = comision;
                this.c_ref = c;

                // TODO Auto-generated constructor stub
            }
        */
	public Segregated() {

	}


	public Cuenta_referencia getC_ref() {
		return c_ref;
	}



	public void setC_ref(Cuenta_referencia c_ref) {
		this.c_ref = c_ref;
	}



	public int getComision() {
		return comision;
	}

	public void setComision(int comision) {
		this.comision = comision;
	}

	//public String getTipo() {return tipo;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((c_ref == null) ? 0 : c_ref.hashCode());
		result = prime * result + comision;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segregated other = (Segregated) obj;
		if (c_ref == null) {
			if (other.c_ref != null)
				return false;
		} else if (!c_ref.equals(other.c_ref))
			return false;
		if (comision != other.comision)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Segregated [comision=" + comision + ", c_ref=" + c_ref + "]";
	}

	
	
	

	
}
