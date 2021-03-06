package es.uma.interneteros.ejb;

import es.uma.informatica.sii.anotaciones.Requisitos;
import es.uma.interneteros.jpa.Usuario;
import es.uma.interneteros.ejb.exceptions.UsuarioException;

import javax.ws.rs.core.UriBuilder;

public interface GestionUsuario {

    public void AltaUsuario(Usuario u) throws UsuarioException;

    public void ActualizarUsuario(Usuario admin, Usuario u) throws UsuarioException;

    public Usuario BuscarUsuario(String id) throws UsuarioException;

    public void MarcarUsuario(Usuario admin, Usuario u, String estado) throws UsuarioException;

    @Requisitos({"RF1"})
    public boolean LoginAdministrador(Usuario u,String id,String contra) throws UsuarioException;
    @Requisitos({"RF10"})
    public boolean LoginCliente(Usuario u,String id,String contra) throws UsuarioException;
}
