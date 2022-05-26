package es.uma.interneteros.jpa;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pooled extends Cuenta_Fintech {
    public Pooled() {
        super();
    }

    public Pooled(String IBAN, String SWIFT, String estado, Date fecha_apertura) {
        super(IBAN, SWIFT, estado, fecha_apertura);
    }

    @OneToMany(mappedBy = "cuenta_pooled")
    private Set<DepositadaPooledReferencia> DepositadaPooled = new HashSet<DepositadaPooledReferencia>();


    public Set<DepositadaPooledReferencia> getDepositadaPooled() {
        return DepositadaPooled;
    }

    public void setDepositadaPooled(Set<DepositadaPooledReferencia> depositadaPooled) {
        DepositadaPooled = depositadaPooled;
    }
}
