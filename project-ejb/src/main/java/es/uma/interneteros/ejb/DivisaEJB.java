package es.uma.interneteros.ejb;

import es.uma.interneteros.jpa.Cuenta_referencia;
import es.uma.interneteros.jpa.Divisa;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.DivisaException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;
@Stateless
public class DivisaEJB implements GestionDivisa {

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    @Override
    public void CrearDivisa(Divisa d, Usuario u) throws DivisaException {
        if(!u.isAdministrador()){
            throw new DivisaException("No eres administrador");
        }

        Divisa divisaExiste = em.find(Divisa.class, d.getAbreviatura());
        if(divisaExiste != null){
            throw new DivisaException("Divisa repetida");
        }

        em.persist(d);

    }

    @Override
    public void ActualizarDivisa(Divisa d, Usuario u) throws DivisaException {
        if(!u.isAdministrador()){
            throw new DivisaException("No eres administrador");
        }

        Divisa divisaExiste = em.find(Divisa.class, d.getAbreviatura());
        if (divisaExiste == null) {
            throw new DivisaException("Divisa no existente");
        }

        em.merge(d);

    }

    @Override
    public Divisa BuscarDivisa(String abreviatura, Usuario u) throws DivisaException {
        if(!u.isAdministrador()){
            throw new DivisaException("No eres administrador");
        }

        Divisa d = em.find(Divisa.class, abreviatura);
        if(d == null){
            throw new DivisaException("Divisa no existente");
        }
        return d;
    }

    public Divisa BuscarDivisaCliente(String abreviatura) throws DivisaException {

        Divisa d = em.find(Divisa.class, abreviatura);
        if(d == null){
            throw new DivisaException("Divisa no existente");
        }
        return d;
    }

    @Override
    public void EliminarDivisa(Divisa d, Usuario u) throws DivisaException {
        if(!u.isAdministrador()){
            throw new DivisaException("No eres administrador");
        }

        Divisa divisaExiste = em.find(Divisa.class, d.getAbreviatura());
        if(divisaExiste == null){
            throw new DivisaException("Divisa no existente");
        }

        if (!em.contains(d)) {
            d = em.merge(d);
        }
        em.remove(d);

    }

    public void CambioDivisa(Cuenta_referencia c,String divisa) throws DivisaException {
        /*Cuenta_referencia c = (Cuenta_referencia) cuentas.BuscarCuenta(IBAN_cuenta);
        Divisa euros = new Divisa("EUR","Euro","€");
        Divisa libras = new Divisa("GBD","Libra","£");
        Divisa dolares = new Divisa("USD", "Dolar", "$");*/


        //if (c != null) {
            String abreviatura = c.getDivisa().getAbreviatura();
            //obtengo la divisa que tiene mi cuenta
            //y en divisa tenemos a la que va a cambiar

            if (abreviatura.equals("USD") && divisa.equals("EUR")) {
                //1º cambio abreviatura
                c.setDivisa(BuscarDivisaCliente("EUR"));
                //2º cambio saldo
                c.setSaldo((int) (c.getSaldo() * 0.93));

            } else if (abreviatura.equals("USD") && divisa.equals("GBD")) {
                c.setDivisa(BuscarDivisaCliente("GBD"));
                c.setSaldo((int) (c.getSaldo() * 0.80));

            } else if (abreviatura.equals("EUR") && divisa.equals("USD")) {
                c.setDivisa(BuscarDivisaCliente("USD"));
                c.setSaldo((int) (c.getSaldo() * 1.07));

            } else if (abreviatura.equals("GBD") && divisa.equals("USD")) {
                c.setDivisa(BuscarDivisaCliente("USD"));
                c.setSaldo((int) (c.getSaldo() * 1.22));

            } else if (abreviatura.equals("EUR") && divisa.equals("GBD")) {
                c.setDivisa(BuscarDivisaCliente("GBD"));
                c.setSaldo((int) (c.getSaldo() * 0.90));

            } else if (abreviatura.equals("GBD") && divisa.equals("EUR")) {
                c.setDivisa(BuscarDivisaCliente("EUR"));
                c.setSaldo((int) (c.getSaldo() * 1.14));

            }


            em.merge(c);


    }

}
