package es.uma.ejb;


import es.uma.jpa.Cliente;
import es.uma.jpa.Usuario;
import es.uma.exceptions.ClienteException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.logging.Logger;

@Stateless
public class ClienteEJB implements GestionCliente {

    private static final Logger LOGGER =java.util.logging.Logger.getLogger(ClienteEJB.class.getCanonicalName());

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;

    //@Requisito 2
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
    // @Requisito 3
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

    //@Requisito 4
    @Override
    public void MarcarCliente(Cliente c, String estado, Usuario admin) throws ClienteException {

        if(admin.isAdministrador()) {

            Cliente cliente = em.find(Cliente.class, c.getID());

            if (cliente == null) {
                throw new ClienteException("Cliente no existente");
            }

            c.setEstado(estado);
            em.merge(c);

        }else{
            throw new ClienteException("NO ERES ADMINISTRADOR");
        }
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
