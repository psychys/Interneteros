package es.uma.ejb;

import es.uma.jpa.Usuario;
import es.uma.exceptions.UsuarioException;

import javax.ws.rs.core.UriBuilder;

public interface GestionUsuario {

    public void AltaUsuario(Usuario admin, Usuario u) throws UsuarioException;

    public void ActualizarUsuario(Usuario admin, Usuario u) throws UsuarioException;

    public Usuario BuscarUsuario(int id) throws UsuarioException;

    public void MarcarUsuario(Usuario admin, Usuario u, String estado) throws UsuarioException;

}
