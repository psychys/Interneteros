package es.uma.ejb;

import es.uma.jpa.Cliente;
import es.uma.jpa.Usuario;
import es.uma.exceptions.ClienteException;

public interface GestionCliente {

    public void AltaCliente(Usuario admin, Cliente c) throws ClienteException;

    public void ActualizarCliente(Usuario admin, Cliente c) throws ClienteException;

    public Cliente BuscarCliente(int id) throws ClienteException;

    //@Requisitos({"RF4"})
    public void MarcarCliente(Cliente c,String s, Usuario admin) throws ClienteException;

}
