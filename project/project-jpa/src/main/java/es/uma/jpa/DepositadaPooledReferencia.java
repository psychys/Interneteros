package es.uma.jpa;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class DepositadaPooledReferencia {

    @EmbeddedId
    private DepositadaId id;

    @ManyToOne
    @MapsId("iban_pooled")
    @JoinColumn(name = "POOLED")
    private Pooled cuenta_pooled;

    @ManyToOne
    @MapsId("iban_referencia")
    @JoinColumn(name = "REFERENCIA")
    private Cuenta_referencia cuenta_referencia;

    private int saldo;

    public DepositadaPooledReferencia(){

    }
    public DepositadaPooledReferencia(DepositadaId id, Pooled cuenta_pooled, Cuenta_referencia cuenta_referencia, int saldo) {
        this.id = id;
        this.cuenta_pooled = cuenta_pooled;
        this.cuenta_referencia = cuenta_referencia;
        this.saldo = saldo;
    }

    public DepositadaId getId() {
        return id;
    }

    public void setId(DepositadaId id) {
        this.id = id;
    }

    public Pooled getCuenta_pooled() {
        return cuenta_pooled;
    }

    public void setCuenta_pooled(Pooled cuenta_pooled) {
        this.cuenta_pooled = cuenta_pooled;
    }

    public Cuenta_referencia getCuenta_referencia() {
        return cuenta_referencia;
    }

    public void setCuenta_referencia(Cuenta_referencia cuenta_referencia) {
        this.cuenta_referencia = cuenta_referencia;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepositadaPooledReferencia that = (DepositadaPooledReferencia) o;
        return saldo == that.saldo && Objects.equals(id, that.id) && Objects.equals(cuenta_pooled, that.cuenta_pooled) && Objects.equals(cuenta_referencia, that.cuenta_referencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cuenta_pooled, cuenta_referencia, saldo);
    }

    @Override
    public String toString() {
        return "DepositadaPooledReferencia{" +
                "id=" + id +
                ", cuenta_pooled=" + cuenta_pooled +
                ", cuenta_referencia=" + cuenta_referencia +
                ", saldo=" + saldo +
                '}';
    }
}
