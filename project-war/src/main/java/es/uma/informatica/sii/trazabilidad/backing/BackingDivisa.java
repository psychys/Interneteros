package es.uma.informatica.sii.trazabilidad.backing;

import es.uma.interneteros.ejb.GestionCliente;
import es.uma.interneteros.ejb.GestionCuenta;
import es.uma.interneteros.ejb.GestionDivisa;
import es.uma.interneteros.ejb.exceptions.ClienteException;
import es.uma.interneteros.ejb.exceptions.CuentaException;
import es.uma.interneteros.ejb.exceptions.DivisaException;
import es.uma.interneteros.jpa.*;
import org.eclipse.persistence.internal.jpa.rs.metadata.model.Query;

import javax.annotation.Resource;
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
import javax.transaction.*;
import java.util.Date;
import java.util.List;

@Named(value= "divi")
@RequestScoped
public class BackingDivisa {
    @Inject
    private InfoSesion sesion;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private UserTransaction user;

    @Inject
    private GestionDivisa divisas;
    @Inject
    private GestionCuenta cuentas;

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

    public String realizarcambio() throws CuentaException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException, DivisaException {

        String res = "vista_admin.xhtml";

        Cuenta_referencia c = (Cuenta_referencia) cuentas.BuscarCuenta(IBAN_cuenta);
        if(c!=null){
            divisas.CambioDivisa(c,divisa);
            res = "CambioDivisaExito.xhtml";
        }
       /* Divisa euros = new Divisa("EUR","Euro","€");
        Divisa libras = new Divisa("GBD","Libra","£");
        Divisa dolares = new Divisa("USD", "Dolar", "$");


            if (c != null) {
                String abreviatura = c.getDivisa().getAbreviatura();
                //obtengo la divisa que tiene mi cuenta
                //y en divisa tenemos a la que va a cambiar

                if (abreviatura.equals("USD") && divisa.equals("EUR")) {
                    //1º cambio abreviatura
                    c.setDivisa(euros);
                    //2º cambio saldo
                    c.setSaldo((int) (c.getSaldo() * 0.93));

                } else if (abreviatura.equals("USD") && divisa.equals("GBD")) {
                    c.setDivisa(libras);
                    c.setSaldo((int) (c.getSaldo() * 0.80));

                } else if (abreviatura.equals("EUR") && divisa.equals("USD")) {
                    c.setDivisa(dolares);
                    c.setSaldo((int) ( c.getSaldo() * 2));

                } else if (abreviatura.equals("GBD") && divisa.equals("USD")) {
                    c.setDivisa(dolares);
                    c.setSaldo((int) (c.getSaldo() * 1.22));

                } else if (abreviatura.equals("EUR") && divisa.equals("GBD")) {
                    c.setDivisa(libras);
                    c.setSaldo((int) (c.getSaldo() * 0.90));

                } else if (abreviatura.equals("GBD") && divisa.equals("EUR")) {
                    c.setDivisa(euros);
                    c.setSaldo((int) (c.getSaldo() * 1.14));

                }


                user.begin();
                em.merge(c);
                user.commit();


           }*/
               return res;
    }

    public String mostrarBanco() throws CuentaException {
        Cuenta_referencia c = (Cuenta_referencia)  cuentas.BuscarCuenta(IBAN_cuenta);
        String nombre_banco = c.getNombre_banco();
        return nombre_banco;
    }

    public int mostrarSaldo() throws CuentaException {
        Cuenta_referencia c = (Cuenta_referencia)  cuentas.BuscarCuenta(IBAN_cuenta);
        int saldo = c.getSaldo();
        return saldo;
    }

}
