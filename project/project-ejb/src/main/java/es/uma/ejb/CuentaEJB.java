package es.uma.ejb;

import es.uma.exceptions.CuentaException;
import es.uma.jpa.Cuenta;
import es.uma.jpa.Cuenta_referencia;
import es.uma.jpa.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class CuentaEJB implements GestionCuenta {

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    private static final Logger LOGGER =java.util.logging.Logger.getLogger(CuentaEJB.class.getCanonicalName());

    //@Requisitos({"RF5"})
    @Override
    public void CrearCuenta(Cuenta c, Usuario u) throws CuentaException {
        if(u.isAdministrador()) {//Comprueba si eres administrador
            Cuenta cuenta = em.find(Cuenta.class, c.getIBAN());

            if(cuenta!= null){
                throw new CuentaException("Cuenta repetida");
            }

            em.persist(c);

        }else{
            throw new CuentaException("NO ERES ADMINISTRADOR");
        }
    }



    @Override
    public void ActualizarCuenta(Cuenta c, Usuario u) throws CuentaException {
        if(u.isAdministrador()) {//Comprueba si eres administrador
            LOGGER.info("DENTRO DEL EJB :--------------> " + c.toString());
            Cuenta cu = BuscarCuenta(c.getIBAN());
            cu.setSWIFT(c.getSWIFT());
            em.merge(cu);


        }else{
            throw new CuentaException("NO ERES ADMINISTRADOR");
        }
    }

    @Override
    public Cuenta BuscarCuenta(String IBAN) throws CuentaException {
        Cuenta c = em.find(Cuenta.class, IBAN);
        if(c == null){
            throw new CuentaException("Cuenta no existente");
        }
        return c;
    }

    //@Requisitos({"RF9"})
    @Override
    public void MarcarCuenta(Cuenta c, String estado,Usuario u) throws CuentaException {
       if(u.isAdministrador()){//Comprueba si eres administrador
           //comprobar que tiene saldo 0 en todas sus divisas

           List<Cuenta_referencia> lista = pedirCuentaRef(c);
           //vamos a recorrer la lista y comprobar que todos sus saldos estan a 0
           boolean x = comprobarSaldo(lista);

           if(x){//tiene todas las divisas a 0
               Cuenta c1 = em.find(Cuenta.class, c.getIBAN());

               if(c1 == null) {
                   throw new CuentaException("Cuenta no existente");
               }

               c.setEstado(estado);
               em.merge(c);
           }else{
               throw new CuentaException("No tiene todas sus divisas a cero");
           }
       }else{
           throw new CuentaException("NO ERES ADMINISTRADOR");
       }
       }

    private List<Cuenta_referencia> pedirCuentaRef(Cuenta c) {
        //EntityTransaction tx=em.getTransaction();
        //tx.begin();
        TypedQuery<Cuenta_referencia> query = em.createQuery("SELECT p from Cuenta_referencia p", Cuenta_referencia.class);
        List<Cuenta_referencia> listaCuentaRef= query.getResultList();
        //tx.commit();
        return listaCuentaRef;
    }

    private boolean comprobarSaldo(List<Cuenta_referencia> lista ) {
        boolean sol = true; //si es false es que tiene dinero en alguna cuenta

        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getSaldo()!=0){
                sol=false;
            }
        }
        return sol;
    }


}
