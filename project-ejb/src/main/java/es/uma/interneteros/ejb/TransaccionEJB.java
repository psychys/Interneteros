package es.uma.interneteros.ejb;


import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.interneteros.jpa.*;
import es.uma.interneteros.ejb.exceptions.TransaccionException;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.*;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Stateless
public class TransaccionEJB implements GestionTransaccion {

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    private static final Logger LOGGER =java.util.logging.Logger.getLogger(CuentaEJB.class.getCanonicalName());

    @Requisitos({"RF13"})
    @Override
    public void CrearTransaccion(Transaccion t, Divisa d) throws TransaccionException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        /*Transaccion t1 = em.find(Transaccion.class, t);
        if(t1 != null)
            throw new TransaccionException("Transaccion ya existente");*/
       if(t.getOrigen().getTipo().equals(t.getDestino().getTipo()) && t.getOrigen().getTipo().equals("segregated"))
            TransaccionSegregated(t.getCantidad(),(Segregated) t.getOrigen(),(Segregated) t.getDestino());
       else if (t.getOrigen().getTipo().equals("pooled") && t.getDestino().getTipo().equals("pooled")) {
           TransaccionPooled(t.getCantidad(),(Pooled) t.getOrigen(), (Pooled) t.getDestino(),d);
       }
        em.persist(t);

    }

    private void TransaccionPooled(int cantidad, Pooled origen, Pooled destino, Divisa d) {
        Cuenta_referencia aux = null;
        Set<DepositadaPooledReferencia> x = origen.getDepositadaPooled();
        //Cambiamos el saldo de la cuenta origen

        for(DepositadaPooledReferencia dep : x){
            if(dep.getCuenta_referencia().getDivisa().equals(d))
                aux = dep.getCuenta_referencia();
            break;
        }
        for(DepositadaPooledReferencia dep : x){
            if(dep.getCuenta_referencia().equals(aux) && dep.getCuenta_pooled().equals(origen))
                if(dep.getSaldo() >= cantidad){
                    dep.setSaldo(dep.getSaldo() - (int) (cantidad * 1.01));
                }
            break;
        }

        //Cambiamos el saldo de la cuenta destino
        for(DepositadaPooledReferencia dep : x){
            if(dep.getCuenta_referencia().getDivisa().equals(d))
                aux = dep.getCuenta_referencia();
            break;
        }
        for(DepositadaPooledReferencia dep : x){
            if(dep.getCuenta_referencia().equals(aux) && dep.getCuenta_pooled().equals(destino))

                    dep.setSaldo(dep.getSaldo() + (int) (cantidad * 1.01));

            break;
        }
        em.merge(x);

    }

    private void TransaccionSegregated(int cantidad, Segregated origen, Segregated destino) throws TransaccionException, SystemException, NotSupportedException, HeuristicRollbackException, HeuristicMixedException, RollbackException {
        if(origen.getC_ref().getSaldo() >= cantidad){
            Cuenta_referencia cor = origen.getC_ref();
            Cuenta_referencia cdr = destino.getC_ref();
            cor.setSaldo(cor.getSaldo() - (int) (cantidad * 1.01));
            cdr.setSaldo(cdr.getSaldo() + cantidad);
            em.merge(cdr);

            em.merge(cor);
        }else{
            throw new TransaccionException("No hay suficiente dinero en la cuenta");
        }
    }

    @Override
    public void CrearTransaccionAdministrador(Usuario u, Transaccion t) throws TransaccionException {
        Transaccion t1 = em.find(Transaccion.class, t);
        if(t1 != null)
            throw new TransaccionException("Transaccion ya existente");
        Usuario admin = em.find(Usuario.class,u);
        if(admin == null)
            throw new TransaccionException("Usuario inexistente");
        if(!admin.isAdministrador())
            throw new TransaccionException("No eres administrador");


        Cuenta_referencia origen = (Cuenta_referencia) t1.getOrigen();
        Cuenta_referencia destino = (Cuenta_referencia) t1.getDestino();


        if(origen.equals(origen.getNombre_banco().equalsIgnoreCase("eBury")) || destino.getNombre_banco().equalsIgnoreCase("eBury")){

            String tipo = t.getTipo();
            if(tipo.equals("ingreso")){

                 origen.setSaldo(origen.getSaldo() + (t1.getCantidad()));
                 destino.setSaldo((int) (destino.getSaldo() - (t1.getCantidad()*1.01)));

                em.persist(t1);
                em.merge(origen);
                em.merge(destino);

            } else if (tipo.equals("cargo")) {

                origen.setSaldo(origen.getSaldo() - (int)(t1.getCantidad()*1.01));
                destino.setSaldo(destino.getSaldo() + t1.getCantidad());

                em.persist(t1);
                em.merge(origen);
                em.merge(destino);

            }else
                throw new TransaccionException("Tipo incorrecto");



        }else{
            throw new TransaccionException("Ninguna de las cuentas es eBury");
        }


    }

    @Override
    public List<Transaccion> obtenerTransacciones() {
        TypedQuery<Transaccion> lista = em.createQuery("Select e from Transaccion e", Transaccion.class);

        List<Transaccion> res = lista.getResultList();
        return res;
    }

    @Requisitos({"RF18"})
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

        // euros.setSaldo(euros.getSaldo() + (int) (t1.getCantidad() * d.getCambio_euro()));

        /*for(DepositadaPooledReferencia dr : aux.getDepositadaReferencia()){
            if(dr.getCuenta_referencia().equals(euros) && dr.getCuenta_pooled().equals(cuenta)){
                dr.setSaldo(euros.getSaldo() + (int) (t1.getCantidad() * d.getCambio_euro()));
                em.merge(dr);
                break;
            }
        }*/

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


