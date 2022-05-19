package es.uma.interneteros.jpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pooled extends Cuenta_Fintech {
    public Pooled() {
    }

    @OneToMany(mappedBy = "cuenta_pooled")
    private Set<DepositadaPooledReferencia> DepositadaPooled = new HashSet<DepositadaPooledReferencia>();


	
}
