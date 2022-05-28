package es.uma.informatica.sii.trazabilidad.backing;

import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionDivisa;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Cuenta_referencia;
import es.uma.interneteros.jpa.Individual;
import es.uma.interneteros.jpa.Usuario;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Query;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Typed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Named(value= "divi")
@RequestScoped
public class BackingDivisa {
    @Inject
    private InfoSesion sesion;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private GestionDivisa divisas;

    private String IBAN_cuenta;
    private String divisa;

    public String getIBAN_cuenta() {
        return IBAN_cuenta;
    }

    public void setIBAN_cuenta(String IBAN_cuenta) {
        this.IBAN_cuenta = IBAN_cuenta;
    }

    public String getDivisa() {
        return divisa;
    }

    public void setDivisa(String divisa) {
        this.divisa = divisa;
    }

    public String realizarcambio(){

        return null;
    }

}
