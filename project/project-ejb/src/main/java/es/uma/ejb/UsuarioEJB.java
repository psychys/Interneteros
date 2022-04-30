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
    public void AltaUsuario(Usuario admin, Usuario u) throws UsuarioException {
        if(admin.isAdministrador()){

            Usuario u1 = em.find(Usuario.class, u.getId());
            if(u1!= null){
                throw new UsuarioException("Usuario repetido");
            }

            em.persist(u);

        }else{
            throw new UsuarioException("NO ERES ADMINISTRADOR");
        }

    }

    @Override
    public void ActualizarUsuario(Usuario admin, Usuario u) throws UsuarioException {
        if(admin.isAdministrador()) {

            Usuario usuario = this.BuscarUsuario(u.getId());
            usuario.setC_cliente(u.getC_cliente());
            usuario.setAdministrador(u.isAdministrador());
            usuario.setContrasena(u.getContrasena());
            usuario.setId(u.getId());

            em.merge(u);
        }else{
            throw new UsuarioException("NO ERES ADMINISTRADOR");
        }

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
    public void MarcarUsuario(Usuario admin, Usuario u, String estado) throws UsuarioException {
        if(admin.isAdministrador()) {

            Usuario usuarioExistente = em.find(Usuario.class, u.getId());

            if (usuarioExistente == null) {
                throw new UsuarioException("Usuario no existente");
            }

            u.setEstado(estado);
            em.merge(u);

        }else{
            throw new UsuarioException("NO ERES ADMINISTRADOR");
        }

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
