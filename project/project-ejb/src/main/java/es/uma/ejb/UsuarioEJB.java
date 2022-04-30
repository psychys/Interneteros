package es.uma.ejb;

import es.uma.jpa.Usuario;
import es.uma.exceptions.UsuarioException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.logging.Logger;

@Stateless
public class UsuarioEJB implements GestionUsuario {

    @PersistenceContext(name="eBuryEJB")
    private EntityManager em;
    private static final Logger LOGGER =java.util.logging.Logger.getLogger(UsuarioEJB.class.getCanonicalName());

    @Override
    public void CrearUsuario(Usuario c, UriBuilder uriBuilder) throws UsuarioException {
        Usuario u1 = em.find(Usuario.class, c.getId());
        if(u1!= null){
            throw new UsuarioException("Cliente repetido");
        }
        //c.setId(generarIdAleatorio());
        em.persist(c);

        URI uriValidacion = uriBuilder.build(c.getId());

        LOGGER.info(uriValidacion.toString());

    }

    @Override
    public void ActualizarUsuario(Usuario c) throws UsuarioException {
        Usuario usuarioExiste = em.find(Usuario.class, c);
        if (usuarioExiste == null) {
            throw new UsuarioException("Usuario no existente");
        }

        em.merge(c);

    }

    @Override
    public Usuario BuscarUsuario(int id) throws UsuarioException {
        Usuario u = em.find(Usuario.class, id);
        if(u == null){
            throw new UsuarioException("Usuario no existente");
        }
        return u;
    }

    @Override
    public void BorrarUsuario(Usuario c) throws UsuarioException {

        Usuario usuarioExistente = em.find(Usuario.class, c);

        if(usuarioExistente == null) {
            throw new UsuarioException("Usuario no existente");
        }


        em.remove(c);


    }

    /*public List<Usuario> getListaUsuarios() {
        // TODO
        EntityTransaction tx=em.getTransaction();
        tx.begin();
        TypedQuery<Usuario> query = em.createQuery("SELECT p from Usuario p", Usuario.class);
        List<Usuario> listaProductos = query.getResultList();
        tx.commit();
        return listaProductos;

    private int generarIdAleatorio() {
        List lista = new ArrayList();
        lista = getListaUsuarios();
        return lista.size()+1;
    }*/

}
