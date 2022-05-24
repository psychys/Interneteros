package es.uma.interneteros.ejb;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.UsuarioException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;
import java.util.*;
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
    public Usuario BuscarUsuario(String id) throws UsuarioException {
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
    @Requisitos({"RF1"})
    @Override
    public boolean LoginAdministrador(Usuario u,String id,String contrasena) throws UsuarioException {

        try{
            Usuario user = BuscarUsuario(id);
            return user.getContrasena().equals(contrasena) && user.isAdministrador();
        }catch(UsuarioException e){
            System.err.println("Usuario inexistente");
            return false;
        }

        //1º compruebo que existe el usuario
        /*if (u == null) {
            throw new UsuarioException("Prueba");
        }else{
            //2º compruebo que es admin
            if(u.isAdministrador()) {
                int id_admin = u.getId();
                String contra_admin = u.getContrasena();

                if(id_admin==id && contrasena==contra_admin){
                    return true;
                }else{
                    return false;
                }
            }else{
                throw new UsuarioException("No es administrador");
            }
        }*/
    }
    @Requisitos({"RF10"})
    @Override
    public boolean LoginCliente(Usuario u,String id,String contrasena) throws UsuarioException {
        //1º compruebo que existe el usuario
        try{
            Usuario user = BuscarUsuario(id);
            return user.getContrasena().equals(contrasena);
        }catch(UsuarioException e){
            System.err.println("Usuario inexistente");
            return false;
        }
        /*
        if (u == null) {
            throw new UsuarioException("Prueba");
        }else{
            //2º compruebo que es admin
            if(!u.isAdministrador()) {
                int id_u = u.getId();
                String contra_u = u.getContrasena();

                if(id_u==id && contrasena==contra_u){
                    return true;
                }else{
                    return false;
                }
            }else{
                throw new UsuarioException("Es administrador, cambiar login");
            }
        }*/



    }


}
