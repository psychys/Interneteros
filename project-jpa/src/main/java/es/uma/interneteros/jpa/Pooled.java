package es.uma.interneteros.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pooled extends Cuenta_Fintech {

    private String tipo;

    public Pooled() {
        super();
    }

    public Pooled(String IBAN, String SWIFT, String estado, Date fecha_apertura) {
        super(IBAN, SWIFT, estado, fecha_apertura);
    }

    public Pooled(String IBAN, String SWIFT, String estado, Date fecha_apertura, String tipo, Cliente c) {
        super(IBAN, SWIFT, estado, fecha_apertura,tipo,c);
    }

    @OneToMany(mappedBy = "cuenta_pooled")
    private Set<DepositadaPooledReferencia> DepositadaPooled = new HashSet<DepositadaPooledReferencia>();


    public Set<DepositadaPooledReferencia> getDepositadaPooled() {
        return DepositadaPooled;
    }

    public void setDepositadaPooled(Set<DepositadaPooledReferencia> depositadaPooled) {
        DepositadaPooled = depositadaPooled;
    }

    public String getTipo() {
        return tipo;
    }
}
