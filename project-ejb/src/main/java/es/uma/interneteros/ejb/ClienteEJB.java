package es.uma.interneteros.ejb;


import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.interneteros.jpa.Cliente;
import es.uma.interneteros.jpa.Cuenta_Fintech;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.ClienteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class ClienteEJB implements GestionCliente {

    private static final Logger LOGGER =java.util.logging.Logger.getLogger(ClienteEJB.class.getCanonicalName());

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    @Requisitos({"RF2"})
    public void AltaCliente(Usuario admin, Cliente c) throws ClienteException {
        if(admin.isAdministrador()) {

            Cliente cliente = em.find(Cliente.class, c.getID());
            if (cliente != null) {
                throw new ClienteException("Cliente repetido");
            }

            em.persist(c);

        }else  {
            throw new ClienteException("NO ERES ADMINISTRADOR");
        }

    }
    @Requisitos({"RF3"})
    @Override
    public void ActualizarCliente(Usuario admin, Cliente c) throws ClienteException {
        if (admin.isAdministrador()) {

            Cliente cliente = BuscarCliente(c.getID());
            cliente.setDireccion(c.getDireccion());
            cliente.setC_postal(c.getC_postal());
            cliente.setPais(c.getPais());
            cliente.setCiudad(c.getCiudad());
            em.merge(cliente);


        }else{
            throw new ClienteException("NO ERES ADMINISTRADOR");
        }
    }

    @Override
    public Cliente BuscarCliente(int id) throws ClienteException {
        Cliente c = em.find(Cliente.class, id);
        if(c == null){
            throw new ClienteException("Cliente no existente");
        }
        return c;
    }

    @Requisitos({"RF4"})
    @Override
    public void MarcarCliente(Cliente c, String estado, Usuario admin) throws ClienteException {

        if(admin.isAdministrador()) {
            if(!abiertas(c)){ //comprueba que no tiene cuentas abiertas, si no tiene abiertas entra
                Cliente cliente = em.find(Cliente.class, c.getID());

                if (cliente == null) {
                    throw new ClienteException("Cliente no existente");
                }

                c.setEstado(estado);
                em.merge(c);
            }else{//tiene cuentas abiertas
                throw new ClienteException("Tiene cuentas abiertas");
            }
        }else{
            throw new ClienteException("NO ERES ADMINISTRADOR");
        }
    }

    private boolean abiertas(Cliente c) {
        boolean x = false; //false= tiene todas las cuentas cerradas

        List<Cuenta_Fintech> lista = new ArrayList<>();
        lista = c.getC_fintech();

        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getEstado()=="abierta"){
                x=true;
            }
        }
        return x;
    }

    /*public List<Cliente> getListaClientes() {
        // TODO
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        TypedQuery<Cliente> query = em.createQuery("SELECT p from Cliente p", Cliente.class);
        List<Cliente> listaProductos = query.getResultList();
        tx.commit();
        return listaProductos;

    }*/
}
