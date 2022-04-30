package es.uma.jpa;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pooled extends Cuenta_Fintech {


    @OneToMany(mappedBy = "cuenta_pooled")
    private Set<DepositadaPooledReferencia> DepositadaPooled = new HashSet<DepositadaPooledReferencia>();


	
}
