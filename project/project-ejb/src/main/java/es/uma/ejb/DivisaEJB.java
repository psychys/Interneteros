package es.uma.ejb;

import es.uma.jpa.Divisa;
import es.uma.jpa.Usuario;
import es.uma.exceptions.DivisaException;

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

}
