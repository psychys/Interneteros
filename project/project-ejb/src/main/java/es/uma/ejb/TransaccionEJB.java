package es.uma.ejb;


import es.uma.jpa.*;
import es.uma.exceptions.TransaccionException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TransaccionEJB implements GestionTransaccion {

    //@PersistenceContext(name="Trazabilidad")
    private EntityManager em;

    @Override
    public void CrearTransaccion(Transaccion t) throws TransaccionException {
        Transaccion t1 = em.find(Transaccion.class, t);
        if(t1 != null)
            throw new TransaccionException("Transaccion ya existente");

        Cuenta_referencia origen = (Cuenta_referencia) t1.getOrigen();
        Cuenta_referencia destino = (Cuenta_referencia) t1.getDestino();

        origen.setSaldo(origen.getSaldo() - (int)(t1.getCantidad()*1.01));
        destino.setSaldo(destino.getSaldo() + t1.getCantidad());


        em.persist(t1);
        em.merge(origen);
        em.merge(destino);

    }

    @Override
    public List<Transaccion> obtenerTransacciones() {
        TypedQuery<Transaccion> lista = em.createQuery("Select e from Transaccion e", Transaccion.class);

        List<Transaccion> res = lista.getResultList();
        return res;
    }

    //requisito 18
    public void cambioDivisa(Usuario u, Transaccion t, Divisa d) throws TransaccionException{
        Usuario admin = em.find(Usuario.class,u);
        Transaccion t1 = em.find(Transaccion.class,t);
        DepositadaPooledReferencia x;

        if(!admin.isAdministrador())
            throw new TransaccionException("El usuario no es administrador");
        if(t1 != null)
            throw new TransaccionException("La transaccion ya existe");
        if(!t1.getOrigen().equals(t1.getDestino()))
            throw new TransaccionException("La cuenta origen debe ser la misma que la de destino");

        Pooled cuenta = (Pooled) t1.getOrigen();

        TypedQuery<Cuenta_referencia>  consulta_ref = em.createQuery("Select e from Cuenta_referencia e where e.IBAN = :iban AND e.divisa  = :divisa" , Cuenta_referencia.class);
        consulta_ref.setParameter("iban", cuenta.getIBAN());
        consulta_ref.setParameter("divisa", d.getAbreviatura());
        Cuenta_referencia aux = consulta_ref.getSingleResult();

        TypedQuery<Cuenta_referencia>  consulta_main = em.createQuery("Select e from Cuenta_referencia e where e.IBAN = :iban AND e.divisa  = :divisa" , Cuenta_referencia.class);
        consulta_main.setParameter("iban", cuenta.getIBAN());
        consulta_main.setParameter("divisa", "eur");
        Cuenta_referencia euros = consulta_main.getSingleResult();

        if(aux.getSaldo() == 0 || euros.getSaldo() == 0)
            throw new TransaccionException("El saldo en ambas divisas tiene que ser superior a 0");

        aux.setSaldo(aux.getSaldo() - t1.getCantidad());


        for(DepositadaPooledReferencia dr : aux.getDepositadaReferencia()){
            if(dr.getCuenta_referencia().equals(aux) && dr.getCuenta_pooled().equals(cuenta)){
                dr.setSaldo(aux.getSaldo() - t1.getCantidad());
                em.merge(dr);
                break;
            }
        }

        euros.setSaldo(euros.getSaldo() + (int) (t1.getCantidad() * d.getCambio_euro()));

        for(DepositadaPooledReferencia dr : aux.getDepositadaReferencia()){
            if(dr.getCuenta_referencia().equals(euros) && dr.getCuenta_pooled().equals(cuenta)){
                dr.setSaldo(euros.getSaldo() + (int) (t1.getCantidad() * d.getCambio_euro()));
                em.merge(dr);
                break;
            }
        }

        em.persist(t1);
        em.merge(cuenta);
        em.merge(aux);
        em.merge(euros);


    }


    @Override
    public Transaccion BuscarTransaccion(int id) throws TransaccionException {

        Transaccion t = em.find(Transaccion.class, id);
        if(t == null){
            throw new TransaccionException("Transaccion no existente");
        }

        return t;

    }

    public boolean EsAutorizada(Transaccion t) throws TransaccionException {

        boolean res = false;
        Cuenta c_origen = t.getOrigen();

        if(c_origen.getEstado().equals("Administrador"))
            res=true;


        return res;
    }


    @Override
    public void BorrarTransaccion(Transaccion t, String estado) throws TransaccionException {

        Transaccion TransaccionExistente = em.find(Transaccion.class, t);

        if(t == null) {
            throw new TransaccionException("CLiente no existente");
        }


        em.remove(TransaccionExistente);


        }

    }


